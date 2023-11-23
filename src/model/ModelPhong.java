/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import connect.Connect;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 *
 * @author TeeDee
 */
public class ModelPhong {
    Connect cn = new Connect();
    Connection conn;

    public ArrayList<ModelPhongv2> findALL() throws Exception {
        String sql = "select * from phong isvisible = '1' ";
        conn = cn.getConnection();
        PreparedStatement pstmt = conn.prepareStatement(sql);
        ResultSet rs = pstmt.executeQuery();
        ArrayList<ModelPhongv2> list = new ArrayList<>();
        while (rs.next()) {
            ModelPhongv2 p = new ModelPhongv2();
            p.setMaPhong(rs.getString("MaPhong"));
            p.setTenPhong(rs.getString("TenPhong"));
            p.setMaTinhTrangPhong(rs.getString("MaTinhTrangPhong"));
            p.setMaLoaiPhong(rs.getString("MaLoaiPhong"));
            p.setTien(rs.getDouble("Tien"));
            

            list.add(p);
        }
        return list;
    }

    public boolean insert(ModelPhongv2 p) throws Exception {
        String sql = "insert into phong (MaPhong, TenPhong,MaTinhTrangphong,MaLoaiPhong,tien) values (?,?,?,?,?)";

        conn = cn.getConnection();
        PreparedStatement pstmt = conn.prepareStatement(sql);

        pstmt.setString(1, p.getMaPhong());
        pstmt.setString(2, p.getTenPhong());
        pstmt.setString(3, p.getMaTinhTrangPhong());
        pstmt.setString(4, p.getMaLoaiPhong());
        pstmt.setDouble(5, p.getTien());

        return pstmt.executeUpdate() > 0;

    }

    public ModelPhongv2 findByID(String MaPhong) throws Exception {
        String sql = "select * from phong where maphong =?";

        conn = cn.getConnection();
        PreparedStatement pstmt = conn.prepareStatement(sql);

        pstmt.setString(1, MaPhong);
        ResultSet rs = pstmt.executeQuery();

        if (rs.next()) {
            ModelPhongv2 p = new ModelPhongv2();
            p.setMaPhong(rs.getString("MaPhong"));
            p.setTenPhong(rs.getString("TenPhong"));
            p.setMaTinhTrangPhong(rs.getString("MaTinhtrangphong"));
            
            p.setMaLoaiPhong(rs.getString("MaLoaiPhong"));
            p.setTien(rs.getDouble("Tien"));

            return p;
        }
        return null;
    }

    public boolean update(ModelPhongv2 p) throws Exception {
        String sql = "update phong set TenPhong=?, matinhtrangphong=? ,maloaiphong=?,  tien=? where maphong = ?";

        conn = cn.getConnection();
        PreparedStatement pstmt = conn.prepareStatement(sql);

        pstmt.setString(5, p.getMaPhong());
        pstmt.setString(1, p.getTenPhong());
        pstmt.setString(2, p.getMaTinhTrangPhong());
        pstmt.setString(3, p.getMaLoaiPhong());
        pstmt.setDouble(4, p.getTien());
        return pstmt.executeUpdate() > 0;
    }
     
    

    public boolean delete(String MaPhong) throws Exception {
        String sql = "delete from Phong where MaPhong= ?";

        conn = cn.getConnection();
        PreparedStatement pstmt = conn.prepareStatement(sql);

        pstmt.setString(1, MaPhong);

        return pstmt.executeUpdate() > 0;

    }
    
    public boolean deletecomeroot(ModelPhongv2 nv) throws Exception {
       
        String sql1 = "update PhieuDatPhong set isvisible = '0' where MaPhong = ? ";
        String sql = "update Phong set isvisible = '0' where MaPhong = ? ";

        conn = cn.getConnection();
        conn.setAutoCommit(false); // Tắt chế độ tự động commit

        try {
            PreparedStatement pstmt1 = conn.prepareStatement(sql1);
            pstmt1.setString(1, nv.getMaPhong());

            int rowsAffected1 = pstmt1.executeUpdate(); // Số dòng bị ảnh hưởng bởi lệnh sql1
            
          
            

            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, nv.getMaPhong());

            int rowsAffected = pstmt.executeUpdate(); // Số dòng bị ảnh hưởng bởi lệnh sql

            if (rowsAffected1 > 0 || rowsAffected > 0) {
                conn.commit(); // Commit thay đổi nếu cả hai lệnh thành công
                System.out.println("ok");
                return true;
                
            } else {
                conn.rollback(); // Rollback nếu có lỗi
                System.out.println("no");
                return false;
            }
        } catch (Exception e) {
            conn.rollback(); // Rollback nếu có lỗi
            throw e;
        } finally {
            conn.setAutoCommit(true); // Bật chế độ tự động commit trở lại
        }
    }
    
    
    public boolean updateTT1(ModelPhongv2 p) throws Exception {
        String sql = "update phong set  matinhtrangphong= 'TT1' where maphong = ?";

        conn = cn.getConnection();
        PreparedStatement pstmt = conn.prepareStatement(sql);

        pstmt.setString(1, p.getMaPhong());

       
        return pstmt.executeUpdate() > 0;
    }
    
    public boolean updateTT2(ModelPhongv2 p) throws Exception {
        String sql = "update phong set  matinhtrangphong= 'TT2' where maphong = ?";

        conn = cn.getConnection();
        PreparedStatement pstmt = conn.prepareStatement(sql);

        pstmt.setString(1, p.getMaPhong());

       
        return pstmt.executeUpdate() > 0;
    }
    
    public boolean updateTT3(ModelPhongv2 p) throws Exception {
        String sql = "update phong set  matinhtrangphong= 'TT3' where maphong = ?";

        conn = cn.getConnection();
        PreparedStatement pstmt = conn.prepareStatement(sql);

        pstmt.setString(1, p.getMaPhong());

       
        return pstmt.executeUpdate() > 0;
    }
}
