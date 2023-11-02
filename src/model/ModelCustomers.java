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
public class ModelCustomers {
    Connect cn = new Connect();
    Connection conn;

    public ArrayList<ModelCustomersv2> findALL() throws Exception {
        String sql = "select * from khachhang where isvisible = '1' ";
        conn = cn.getConnection();
        PreparedStatement pstmt = conn.prepareStatement(sql);
        ResultSet rs = pstmt.executeQuery();
        ArrayList<ModelCustomersv2> list = new ArrayList<>();
        while (rs.next()) {
            ModelCustomersv2 kh = new ModelCustomersv2();
            kh.setMaKH(rs.getString("MaKH"));
            kh.setHoKH(rs.getString("HoKH"));
            kh.setTenKH(rs.getString("TenKH"));
            kh.setGioiTinh(rs.getInt("GioiTinh"));
            kh.setDiaChi(rs.getString("DiaChi"));
            kh.setNgaySinh(rs.getString("NgaySinh"));
            kh.setSDT(rs.getString("SoDT"));

            list.add(kh);
        }
        return list;
    }

    public boolean insert(ModelCustomersv2 kh) throws Exception {
        String sql = "insert into khachhang (MaKH, HoKH,TenKH,gioitinh,DiaChi,NgaySinh,SoDT) values (?,?,?,?,?,?,?)";

        conn = cn.getConnection();
        PreparedStatement pstmt = conn.prepareStatement(sql);

        pstmt.setString(1, kh.getMaKH());
        pstmt.setString(2, kh.getHoKH());
        pstmt.setString(3, kh.getTenKH());
        pstmt.setInt(4, kh.getGioiTinh());
        pstmt.setString(5, kh.getDiaChi());
        pstmt.setString(6, kh.getNgaySinh());
        pstmt.setString(7, kh.getSDT());

        return pstmt.executeUpdate() > 0;

    }

    public ModelCustomersv2 findByID(String MaKH) throws Exception {
        String sql = "select * from khachhang where makh =?";

        conn = cn.getConnection();
        PreparedStatement pstmt = conn.prepareStatement(sql);

        pstmt.setString(1, MaKH);
        ResultSet rs = pstmt.executeQuery();

        if (rs.next()) {
            ModelCustomersv2 kh = new ModelCustomersv2();
            kh.setMaKH(rs.getString("MaKH"));
            kh.setHoKH(rs.getString("HoKH"));
            kh.setTenKH(rs.getString("TenKH"));
            kh.setGioiTinh(rs.getInt("GioiTinh"));
            kh.setDiaChi(rs.getString("DiaChi"));
            kh.setNgaySinh(rs.getString("NgaySinh"));
            kh.setSDT(rs.getString("SoDT"));

            return kh;
        }
        return null;
    }

    public boolean update(ModelCustomersv2 kh) throws Exception {
        String sql = "update khachhang set HoKH =?,TenKH=?,gioitinh=? ,DiaChi=?, NgaySinh=? , SoDT=? where makh = ?";

        conn = cn.getConnection();
        PreparedStatement pstmt = conn.prepareStatement(sql);

        pstmt.setString(7, kh.getMaKH());
        pstmt.setString(1, kh.getHoKH());
        pstmt.setString(2, kh.getTenKH());

        pstmt.setInt(3, kh.getGioiTinh());
        pstmt.setString(4, kh.getDiaChi());
        pstmt.setString(5, kh.getNgaySinh());
        pstmt.setString(6, kh.getSDT());

        return pstmt.executeUpdate() > 0;

    }

//    public boolean delete(String MaKH) throws Exception {
//        String sql1 = "delte from HoaDonDV where makh = ?";
//        String sql = "delete from khachhang where makh= ?";
//
//        conn = cn.getConnection();
//        PreparedStatement pstmt = conn.prepareStatement(sql);
//
//        pstmt.setString(1, MaKH);
//     
//        return pstmt.executeUpdate() > 0;
//
//    }
    
    
    public boolean deletecomeroot(ModelCustomersv2 kh) throws Exception {
        String sql1 = "update HoaDonDV set isvisible = '0' where makh = ? ";
        String sql2 = "update PhieuDatPhong set isvisible = '0' where makh = ? ";
        String sql = "update khachhang set isvisible = '0' where makh = ? ";

        conn = cn.getConnection();
        conn.setAutoCommit(false); // Tắt chế độ tự động commit

        try {
            PreparedStatement pstmt1 = conn.prepareStatement(sql1);
            pstmt1.setString(1, kh.getMaKH());

            int rowsAffected1 = pstmt1.executeUpdate(); // Số dòng bị ảnh hưởng bởi lệnh sql1
            
            PreparedStatement pstmt2 = conn.prepareStatement(sql2);
            pstmt2.setString(1, kh.getMaKH());

            int rowsAffected2 = pstmt2.executeUpdate(); // Số dòng bị ảnh hưởng bởi lệnh sql1
            

            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, kh.getMaKH());

            int rowsAffected = pstmt.executeUpdate(); // Số dòng bị ảnh hưởng bởi lệnh sql

            if (rowsAffected1 > 0 || rowsAffected > 0 || rowsAffected2 > 0) {
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
    
    
    public boolean delete(String MaKH) throws Exception {
        String sql1 = "delete from HoaDonDV where makh = ?";
        String sql2 = "delete from PhieuDatPhong where makh = ?";
        
        String sql = "delete from khachhang where makh = ?";

        conn = cn.getConnection();
        conn.setAutoCommit(false); // Tắt chế độ tự động commit

        try {
            PreparedStatement pstmt1 = conn.prepareStatement(sql1);
            pstmt1.setString(1, MaKH);

            int rowsAffected1 = pstmt1.executeUpdate(); // Số dòng bị ảnh hưởng bởi lệnh sql1
            
            PreparedStatement pstmt2 = conn.prepareStatement(sql2);
            pstmt2.setString(1, MaKH);

            int rowsAffected2 = pstmt2.executeUpdate(); // Số dòng bị ảnh hưởng bởi lệnh sql1

            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, MaKH);

            int rowsAffected = pstmt.executeUpdate(); // Số dòng bị ảnh hưởng bởi lệnh sql

            if (rowsAffected1 > 0 || rowsAffected > 0 || rowsAffected2 > 0) {
                conn.commit(); // Commit thay đổi nếu cả hai lệnh thành công
                return true;
            } else {
                conn.rollback(); // Rollback nếu có lỗi
                return false;
            }
        } catch (Exception e) {
            conn.rollback(); // Rollback nếu có lỗi
            throw e;
        } finally {
            conn.setAutoCommit(true); // Bật chế độ tự động commit trở lại
        }
    }


//    public void insert(phong p) {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//    }
}
