/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Dao;
import utils.XJdbc;
import entiTy.quanLy;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author ACER
 */
public class quanLyDao {
    public void insert(quanLy model) {
        String sql = "INSERT INTO QuanLy (idQuanly, matKhau, diaChi, sDT) VALUES (?, ?, ?, ?)";
        XJdbc.update(sql,
                model.getIdQuanly(),
                model.getMatKhau(),
                model.getDiaChi(),
                model.getsDT());
    }

    public void update(quanLy model) {
        String sql = "UPDATE Quanly SET idQuanly=?, matKhau=?, diaChi=?, sDT=? WHERE idQuanly=?";
        XJdbc.update(sql,
                model.getIdQuanly(),
                model.getMatKhau(),
                model.getDiaChi(),
                model.getsDT());
    }

    public void delete(String idquanly) {
        String sql = "DELETE FROM QuanLy WHERE idQuanly=?";
        XJdbc.update(sql, idquanly);
    }

    public List<quanLy> selectAll() {
        String sql = "SELECT * FROM QuanLy";
        return selectBySql(sql);
    }

    public quanLy selectById(String idquanly) {
        String sql = "SELECT * FROM QuanLy WHERE idQuanly=?";
        List<quanLy> list = selectBySql(sql, idquanly);
        return list.size() > 0 ? list.get(0) : null;
    }

    protected List<quanLy> selectBySql(String sql, Object... args) {
        List<quanLy> list = new ArrayList<>();
        try {
            ResultSet rs = null;
            try {
                rs = XJdbc.query(sql, args);
                while (rs.next()) {
                    quanLy entity = new quanLy();
                    entity.setIdQuanly(rs.getString("idQuanly"));
                    entity.setMatKhau(rs.getString("matKhau"));
                    entity.setDiaChi(rs.getString("diaChi"));
                    entity.setsDT(rs.getString("sDT"));
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
