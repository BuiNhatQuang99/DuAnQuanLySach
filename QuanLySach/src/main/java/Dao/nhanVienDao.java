/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Dao;

import utils.XJdbc;
import entiTy.nhaVien;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ACER
 */
public class nhanVienDao {

    public void insert(nhaVien model) {
        String sql = "INSERT INTO NhanVien (idNhanvien, idQuanly,matKhau, sDT, diaChi) VALUES (?, ?, ?, ?, ?)";
        XJdbc.update(sql,
                model.getIdNhanvien(),
                model.getIdQuanly(),
                model.getMatKhau(),
                model.getsDT(),
                model.getDiaChi());
    }

    public void update(nhaVien model) {
        String sql = "UPDATE HoaDon SET idNhanvien=?, idQuanly=?, matKhau=?, sDT=?, diaChi=? WHERE idNhanvien=?";
        XJdbc.update(sql,
                model.getIdNhanvien(),
                model.getIdQuanly(),
                model.getMatKhau(),
                model.getsDT(),
                model.getDiaChi());
    }

    public void delete(String idnhanVien) {
        String sql = "DELETE FROM NhanVien WHERE idNhanvien=?";
        XJdbc.update(sql, idnhanVien);
    }

    public List<nhaVien> selectAll() {
        String sql = "SELECT * FROM NhanVien";
        return selectBySql(sql);
    }

    public nhaVien selectById(String idnhanvien) {
        String sql = "SELECT * FROM NhanVien WHERE idNhanvien=?";
        List<nhaVien> list = selectBySql(sql, idnhanvien);
        return list.size() > 0 ? list.get(0) : null;
    }

    protected List<nhaVien> selectBySql(String sql, Object... args) {
        List<nhaVien> list = new ArrayList<>();
        try {
            ResultSet rs = null;
            try {
                rs = XJdbc.query(sql, args);
                while (rs.next()) {
                    nhaVien entity = new nhaVien();
                    entity.setIdNhanvien(rs.getString("idNhanvien"));
                    entity.setIdQuanly(rs.getString("idQuanly"));
                    entity.setMatKhau(rs.getString("matKhau"));
                    entity.setsDT(rs.getString("sDT"));
                    entity.setDiaChi(rs.getString("diaChi"));
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
