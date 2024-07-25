/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Dao;
import utils.XJdbc;
import entiTy.hoaDon;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author ACER
 */
public class hoaDonDao{
    public void insert(hoaDon model){
        String sql="INSERT INTO ChuyenDe (idHoadon, idKhachhang,idNhanvien, ngayBan,tongTien) VALUES (?, ?, ?, ?, ?)";
        XJdbc.update(sql, 
                model.getIdHoaDon(), 
                model.getIdKhachHang(), 
                model.getIdNhanVien(), 
                model.getDate(), 
                model.getTongTien());
    }
    public void update(hoaDon model){
        String sql="UPDATE HoaDon SET idHoadon=?, idKhachhang=?, idNhanvien=?, ngayBan=?, tongTien=? WHERE idHoadon=?";
        XJdbc.update(sql,
                model.getIdHoaDon(), 
                model.getIdKhachHang(), 
                model.getIdNhanVien(), 
                model.getDate(), 
                model.getTongTien());
                }
     public void delete(String idHoadon){
        String sql="DELETE FROM HoaDon WHERE idHoadon=?";
        XJdbc.update(sql, idHoadon);
    }
     public List<hoaDon> selectAll(){
        String sql="SELECT * FROM HoaDon";
        return selectBySql(sql);
    }
     public hoaDon selectById(String idHoadon){
        String sql="SELECT * FROM ChuyenDe WHERE idHoadon=?";
        List<hoaDon> list = selectBySql(sql, idHoadon);
        return list.size() > 0 ? list.get(0) : null;
    }
    
    protected List<hoaDon> selectBySql(String sql, Object...args){
        List<hoaDon> list=new ArrayList<>();
        try {
            ResultSet rs = null;
            try {
                rs = XJdbc.query(sql, args);
                while(rs.next()){
                    hoaDon entity=new hoaDon();
                    entity.setIdHoaDon(rs.getString("idHoanDon"));
                    entity.setIdKhachHang(rs.getString("idKhachhang"));
                    entity.setIdNhanVien(rs.getString("idNhanvien"));
                    entity.setDate(rs.getString("MoTa"));
                    entity.setTongTien(rs.getFloat("tongTien"));
                    list.add(entity);
                }
            }
            finally{
                rs.getStatement().getConnection().close();
            }
        } 
        catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
        return list;
    }
}
