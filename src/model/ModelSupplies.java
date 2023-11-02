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
public class ModelSupplies {
    Connect cn = new Connect();
    Connection conn;
    public ArrayList<ModelSuppliesv2> findALL() throws Exception {
        String sql = "select * from vattu where isvisible = '1' ";
        conn = cn.getConnection();
        PreparedStatement pstmt = conn.prepareStatement(sql);
        ResultSet rs = pstmt.executeQuery();
        ArrayList<ModelSuppliesv2> list = new ArrayList<>();
        while (rs.next()) {
            ModelSuppliesv2 acc = new ModelSuppliesv2();
            acc.setMaVatTu(rs.getString("MaVatTu"));
            acc.setTenVatTu(rs.getString("TenVatTu"));
            
            
            list.add(acc);
        }
        return list;
    }

    public boolean insert(ModelSuppliesv2 acc) throws Exception {
        String sql = "insert into vattu (MaVatTu, TenVatTu) values (?,?)";

        conn = cn.getConnection();
        PreparedStatement pstmt = conn.prepareStatement(sql);

        pstmt.setString(1, acc.getMaVatTu());
        pstmt.setString(2, acc.getTenVatTu());
       

        return pstmt.executeUpdate() > 0;

    }

    public ModelSuppliesv2 findByID(String username) throws Exception {
        String sql = "select * from vattu where MaVatTu =?";

        conn = cn.getConnection();
        PreparedStatement pstmt = conn.prepareStatement(sql);

        pstmt.setString(1, username);
        ResultSet rs = pstmt.executeQuery();

        if (rs.next()) {
            ModelSuppliesv2 acc = new ModelSuppliesv2();
            acc.setMaVatTu(rs.getString("MaVatTu"));
            acc.setTenVatTu(rs.getString("TenVatTu"));
            
            
            return acc;
        }
        return null;
    }

    public boolean update(ModelSuppliesv2 acc) throws Exception {
        String sql = "update vattu set TenVatTu =? where MaVatTu =?";

        conn = cn.getConnection();
        PreparedStatement pstmt = conn.prepareStatement(sql);

       
        pstmt.setString(1, acc.getTenVatTu());
        pstmt.setString(2, acc.getMaVatTu());
       
        

        return pstmt.executeUpdate() > 0;

    }

    public boolean delete(String MaVatTu) throws Exception {
        String sql = "delete from vattu where MaVatTu= ?";

        conn = cn.getConnection();
        PreparedStatement pstmt = conn.prepareStatement(sql);

        pstmt.setString(1, MaVatTu);

        return pstmt.executeUpdate() > 0;

    }
    
    
    public boolean deletecomeroot(ModelSuppliesv2 vt) throws Exception {
        String sql1 = "update CTVatTu set isvisible = '0' where MaVatTu = ? ";
       
        String sql = "update vattu set isvisible = '0' where MaVatTu = ? ";

        conn = cn.getConnection();
        conn.setAutoCommit(false); // Tắt chế độ tự động commit

        try {
            PreparedStatement pstmt1 = conn.prepareStatement(sql1);
            pstmt1.setString(1, vt.getMaVatTu());

            int rowsAffected1 = pstmt1.executeUpdate(); // Số dòng bị ảnh hưởng bởi lệnh sql1
            

            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, vt.getMaVatTu());

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
}
