/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Dao;
import utils.XJdbc;
import entiTy.khachHang;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author ACER
 */
public class khachHangDao {
    public void insert(khachHang model) {
        String sql = "INSERT INTO KhachHang (idKhachHang, tenKhachHang, diaChi, soluong, soDT, email) VALUES (?, ?, ?, ?, ?, ?)";
        XJdbc.update(sql,
                model.getIdKhachHang(),
                model.getTenKhachHang(),
                model.getDiaChi(),
                model.getSoLuong(),
                model.getSoDT(),
                model.getEmail());
    }

    public void update(khachHang model) {
        String sql = "UPDATE KhachHang SET idKhachHang=?, tenKhachHang=?, diaChi=?, soluong=?, soDT=?, email=? WHERE idKhachHang=?";
        XJdbc.update(sql,
                model.getIdKhachHang(),
                model.getTenKhachHang(),
                model.getDiaChi(),
                model.getSoLuong(),
                model.getSoDT(),
                model.getEmail());
    }

    public void delete(String idkhacHang) {
        String sql = "DELETE FROM KhachHang WHERE idKhachHang=?";
        XJdbc.update(sql, idkhacHang);
    }

    public List<khachHang> selectAll() {
        String sql = "SELECT * FROM KhachHang";
        return selectBySql(sql);
    }

    public khachHang selectById(String idkhachHang) {
        String sql = "SELECT * FROM ChuyenDe WHERE idHoadon=?";
        List<khachHang> list = selectBySql(sql, idkhachHang);
        return list.size() > 0 ? list.get(0) : null;
    }

    protected List<khachHang> selectBySql(String sql, Object... args) {
        List<khachHang> list = new ArrayList<>();
        try {
            ResultSet rs = null;
            try {
                rs = XJdbc.query(sql, args);
                while (rs.next()) {
                    khachHang entity = new khachHang();
                    entity.setIdKhachHang(rs.getString("idKhachHang"));
                    entity.setTenKhachHang(rs.getString("tenKhachHang"));
                    entity.setDiaChi(rs.getString("diaChi"));
                    entity.setSoLuong(rs.getInt("soLuong"));
                    entity.setSoDT(rs.getString("soDT"));
                    entity.setEmail(rs.getString("email"));
                    list.add(entity);
                }
            } finally {
                rs.getStatement().getConnection().close();
            }
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
        return list;
    }
}
