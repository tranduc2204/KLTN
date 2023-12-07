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
public class ModelService {
    Connect cn = new Connect();
    Connection conn;
    
    public ArrayList<ModelServicev2> findALL() throws Exception {
        String sql = "select MaDV, TenDichVu,dgdv.MaDonGiaDV,GiamGia, DonGia  from DICHVU dv join DonGiaDV dgdv on dv.MaDonGiaDV = dgdv.MaDonGiaDV where isvisible = '1' ";

        conn = cn.getConnection();
        PreparedStatement pstmt = conn.prepareStatement(sql);
        ResultSet rs = pstmt.executeQuery();
        ArrayList<ModelServicev2> list = new ArrayList<>();
        while (rs.next()) {
            ModelServicev2 dv = new ModelServicev2();
            dv.setMaDV(rs.getString("MaDV"));
            dv.setTenDichVu(rs.getString("TenDichVu"));
            dv.setMaDonGiaDV(rs.getString("MaDonGiaDV"));
            dv.setGiamGia(rs.getInt("GiamGia"));
            dv.setDonGia(rs.getBigDecimal("DonGia"));
          

            list.add(dv);
        }
        return list;
    }


    public ModelServicev2 findByID(String MaDichVu) throws Exception {
        String sql = "select * from dichvu where MaDV = ? ";
        conn = cn.getConnection();
        PreparedStatement pstmt = conn.prepareStatement(sql);

        pstmt.setString(1, MaDichVu);
        ResultSet rs = pstmt.executeQuery();

        if (rs.next()) {
            ModelServicev2 dv = new ModelServicev2();
            dv.setMaDV(rs.getString("MaDV"));
            dv.setTenDichVu(rs.getString("TenDichVu"));
            dv.setDonGia(rs.getBigDecimal("DonGia"));
           
            return dv;
        }
        return null;
    }
    
    
    
    
    public boolean insert(ModelServicev2 dv) throws Exception {
        String sql = "insert into dichvu (MaDV, tendichvu,madongiadv,GiamGia) values (?,?,?,?)";

        conn = cn.getConnection();
        PreparedStatement pstmt = conn.prepareStatement(sql);

        pstmt.setString(1, dv.getMaDV());
        pstmt.setString(2, dv.getTenDichVu());
        pstmt.setString(3, dv.getMaDonGiaDV());
        pstmt.setInt(4, dv.getGiamGia());

        return pstmt.executeUpdate() > 0;

    }
    
    public boolean update(ModelServicev2 dv) throws Exception {
        String sql = "update dichvu set tendichvu =?,madongiadv=?,GiamGia?  where MaDV = ?";

        conn = cn.getConnection();
        PreparedStatement pstmt = conn.prepareStatement(sql);

        pstmt.setString(4, dv.getMaDV());
        pstmt.setString(1, dv.getTenDichVu());
        pstmt.setString(2, dv.getMaDonGiaDV());
        pstmt.setBigDecimal(3, dv.getDonGia());
       

        return pstmt.executeUpdate() > 0;

    }

    public boolean delete(String madv) throws Exception {
        String sql = "delete from dichvu where MaDV= ?";

        conn = cn.getConnection();
        PreparedStatement pstmt = conn.prepareStatement(sql);

        pstmt.setString(1, madv);

        return pstmt.executeUpdate() > 0;

    }
    
//    public boolean deletecomeroot(ModelServicev2 nv) throws Exception {
//        String sql1 = "update HoaDonDV set isvisible = '0' where MaDV = ? ";
//       
//        String sql = "update dichvu set isvisible = '0' where MaDV = ? ";
//
//        conn = cn.getConnection();
//        conn.setAutoCommit(false); // Tắt chế độ tự động commit
//
//        try {
//            PreparedStatement pstmt1 = conn.prepareStatement(sql1);
//            pstmt1.setString(1, nv.getMaDV());
//
//            int rowsAffected1 = pstmt1.executeUpdate(); // Số dòng bị ảnh hưởng bởi lệnh sql1
//            
//
//            PreparedStatement pstmt = conn.prepareStatement(sql);
//            pstmt.setString(1, nv.getMaDV());
//
//            int rowsAffected = pstmt.executeUpdate(); // Số dòng bị ảnh hưởng bởi lệnh sql
//
//            if (rowsAffected1 > 0 || rowsAffected > 0) {
//                conn.commit(); // Commit thay đổi nếu cả hai lệnh thành công
//                System.out.println("ok");
//                return true;
//                
//            } else {
//                conn.rollback(); // Rollback nếu có lỗi
//                System.out.println("no");
//                return false;
//            }
//        } catch (Exception e) {
//            conn.rollback(); // Rollback nếu có lỗi
//            throw e;
//        } finally {
//            conn.setAutoCommit(true); // Bật chế độ tự động commit trở lại
//        }
//    }
    
    public boolean deletecomeroot(ModelServicev2 nv) throws Exception {
        String sql1 = "update HoaDonDV set isvisible = '0' where MaDV = ? ";

        conn = cn.getConnection();
        conn.setAutoCommit(false); // Tắt chế độ tự động commit

        try {
            PreparedStatement pstmt1 = conn.prepareStatement(sql1);
            pstmt1.setString(1, nv.getMaDV());

            int rowsAffected1 = pstmt1.executeUpdate(); // Số dòng bị ảnh hưởng bởi lệnh sql1

            if (rowsAffected1 > 0 ) {
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
