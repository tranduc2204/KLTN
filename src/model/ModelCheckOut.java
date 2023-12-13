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
public class ModelCheckOut {
    Connect cn = new Connect();
    Connection conn;
    
    public ArrayList<ModelCheckOutv2> findALL() throws Exception {
        String sql = "select MaHoaDonPhong, pdp.MaPhieuDatPhong, NgayDuKienThue, NgayDuKienTra, p.MaPhong, TenPhong, TenLoaiPhong, DonGia,GiamGia, \n" +
"((DATEDIFF(DAY, NgayDuKienThue, NgayDuKienTra)) *DonGia) * 0.1 as VAT, ((DATEDIFF(DAY, NgayDuKienThue, NgayDuKienTra)) *DonGia)  +  (((DATEDIFF(DAY, NgayDuKienThue, NgayDuKienTra)) *DonGia) * 0.1) - (((DATEDIFF(DAY, NgayDuKienThue, NgayDuKienTra)) *DonGia) *GiamGia /100) AS GiaHD, NgayLapHoaDon\n" +
"from HoaDonPhong hdp join PhieuDatPhong pdp on hdp.MaPhieuDatPhong = pdp.MaPhieuDatPhong join phong p on p.MaPhong = pdp.MaPhong\n" +
"join DonGiaPhong dgp on dgp.MaDonGiaPhong = p.MaDonGiaPhong join LOAIPHONG lp on lp.MaLoaiPhong = p.MaLoaiPhong\n" +
"where hdp.isvisible = '1' ";
        conn = cn.getConnection();
        PreparedStatement pstmt = conn.prepareStatement(sql);
        ResultSet rs = pstmt.executeQuery();
        ArrayList<ModelCheckOutv2> list = new ArrayList<>();
        while (rs.next()) {
            ModelCheckOutv2 co = new ModelCheckOutv2();
            co.setMaHoaDonPhong(rs.getString("MaHoaDonPhong"));
            co.setMaPhieuDatPhong(rs.getString("MaPhieuDatPhong"));
            co.setNgayThuePhong(rs.getString("NgayDuKienThue"));
            co.setNgayTraPhong(rs.getString("NgayDuKienTra"));
            co.setMaPhong(rs.getString("MaPhong"));
            co.setTenPhong(rs.getString("TenPhong"));
            co.setLoaiPhong(rs.getString("TenLoaiPhong"));
            co.setGiamGia(rs.getInt("GiamGia"));
            co.setGia(rs.getBigDecimal("DonGia"));
            co.setVAT(rs.getBigDecimal("VAT"));
            co.setGiaHD(rs.getBigDecimal("GiaHD"));
            co.setNgayLapHoaDon(rs.getString("NgayLapHoaDon"));
            list.add(co);
        }
        return list;
    }
    
    public ModelCheckOutv2 findByID(String Maphieudatphong) throws Exception {
        String sql = "select MaPhieuDatPhong, NgayDuKienThue, NgayDuKienTra, p.MaPhong, TenPhong, TenLoaiPhong, DonGia, GiamGia from PhieuDatPhong pdp join phong p on p.MaPhong = pdp.MaPhong\n" +
"join DonGiaPhong dgp on dgp.MaDonGiaPhong = p.MaDonGiaPhong join LOAIPHONG lp on lp.MaLoaiPhong = p.MaLoaiPhong"
                + " where MaPhieuDatPhong =? ";

        conn = cn.getConnection();
        PreparedStatement pstmt = conn.prepareStatement(sql);

        pstmt.setString(1, Maphieudatphong);
        ResultSet rs = pstmt.executeQuery();

        if (rs.next()) {
            ModelCheckOutv2 ci = new ModelCheckOutv2();
            ci.setMaPhieuDatPhong(rs.getString("MaPhieuDatPhong"));
            ci.setNgayThuePhong(rs.getString("NgayDuKienThue"));
            ci.setNgayTraPhong(rs.getString("NgayDuKienTra"));
 
            ci.setMaPhong(rs.getString("MaPhong"));
            ci.setTenPhong(rs.getString("TenPhong"));
            ci.setLoaiPhong(rs.getString("TenLoaiPhong"));
    
            ci.setGia(rs.getBigDecimal("DonGia"));
            ci.setGiamGia(rs.getInt("GiamGia"));
//            ci.setNgayThuePhong(rs.getString("NgayThuePhong"));

            return ci;
        }
        return null;
    }
    
    

//    
    public boolean insert(ModelCheckOutv2 rt) throws Exception {
        String sql = "insert into HoaDonPhong (MaHoaDonPhong, NgayLapHoaDon, MaPhieuDatPhong) values (?,?,?)";

        conn = cn.getConnection();
        PreparedStatement pstmt = conn.prepareStatement(sql);
        
        pstmt.setString(1, rt.getMaHoaDonPhong());
        pstmt.setString(2, rt.getNgayLapHoaDon());
        pstmt.setString(3, rt.getMaPhieuDatPhong());

        return pstmt.executeUpdate() > 0;

    }
    public boolean update(ModelCheckOutv2 rt) throws Exception {
        String sql = "update HoaDonPhong set NgayLapHoaDon = ?  where MaHoaDonPhong = ? ";

        conn = cn.getConnection();
        PreparedStatement pstmt = conn.prepareStatement(sql);

        
        pstmt.setString(2, rt.getMaHoaDonPhong());
        pstmt.setString(1, rt.getNgayLapHoaDon());

        return pstmt.executeUpdate() > 0;
  
    }
    
    public boolean deletecomeroot(ModelCheckOutv2 rt) throws Exception {
       
        String sql = "update HoaDonPhong set isvisible = '0' where MaHoaDonPhong = ? ";
        conn = cn.getConnection();
        conn.setAutoCommit(false); // Tắt chế độ tự động commit
        try {

            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, rt.getMaHoaDonPhong());

            int rowsAffected = pstmt.executeUpdate(); // Số dòng bị ảnh hưởng bởi lệnh sql

            if (rowsAffected > 0) {
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
