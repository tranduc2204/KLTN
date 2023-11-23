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
public class ModelCheckIn {
    Connect cn = new Connect();
    Connection conn;
    
    public ArrayList<ModelCheckInv2> findALL() throws Exception {
        String sql = "select MaPhieuThuePhong, pdp.MaPhieuDatPhong, NgayDatPhong, kh.MaKH, TenKH, p.MaPhong,"
                + " TenPhong, TenLoaiPhong,Tien, NgayThuePhong from PhieuThuePhong ptp "
                + "join PhieuDatPhong pdp on ptp.MaPhieuDatPhong = pdp.MaPhieuDatPhong "
                + "join KHACHHANG kh on kh.MaKH = pdp.MaKH  join PHONG p on p.MaPhong = pdp.MaPhong  "
                + "join LOAIPHONG lp on lp.MaLoaiPhong = p.MaLoaiPhong where ptp.isvisible = '1' ";
        conn = cn.getConnection();
        PreparedStatement pstmt = conn.prepareStatement(sql);
        ResultSet rs = pstmt.executeQuery();
        ArrayList<ModelCheckInv2> list = new ArrayList<>();
        while (rs.next()) {
            ModelCheckInv2 ci = new ModelCheckInv2();
            ci.setMaPhieuThuePhong(rs.getString("MaPhieuThuePhong"));
            ci.setMaPhieuDatPhong(rs.getString("MaPhieuDatPhong"));
            ci.setNgayDatPhong(rs.getString("NgayDatPhong"));
            ci.setMaKH(rs.getString("MaKH"));
            ci.setTenKH(rs.getString("TenKH"));
           
           
            ci.setMaPhong(rs.getString("MaPhong"));
            ci.setTenPhong(rs.getString("TenPhong"));
            ci.setLoaiPhong(rs.getString("TenLoaiPhong"));
    
            ci.setGia(rs.getBigDecimal("Tien"));
            ci.setNgayThuePhong(rs.getString("NgayThuePhong"));
            
            list.add(ci);
        }
        return list;
    }
    
    public ModelCheckInv2 findByID(String Maphieuthuephong) throws Exception {
        String sql = "select MaPhieuThuePhong, pdp.MaPhieuDatPhong, NgayDatPhong, kh.MaKH, TenKH, p.MaPhong,"
                + " TenPhong, TenLoaiPhong,Tien, NgayThuePhong from PhieuThuePhong ptp "
                + "join PhieuDatPhong pdp on ptp.MaPhieuDatPhong = pdp.MaPhieuDatPhong "
                + "join KHACHHANG kh on kh.MaKH = pdp.MaKH  join PHONG p on p.MaPhong = pdp.MaPhong  "
                + "join LOAIPHONG lp on lp.MaLoaiPhong = p.MaLoaiPhong where MaPhieuThuePhong =?";

        conn = cn.getConnection();
        PreparedStatement pstmt = conn.prepareStatement(sql);

        pstmt.setString(1, Maphieuthuephong);
        ResultSet rs = pstmt.executeQuery();

        if (rs.next()) {
            ModelCheckInv2 ci = new ModelCheckInv2();
            ci.setMaPhieuThuePhong(rs.getString("MaPhieuThuePhong"));
            ci.setMaPhieuDatPhong(rs.getString("MaPhieuDatPhong"));
            ci.setNgayDatPhong(rs.getString("NgayDatPhong"));
            ci.setMaKH(rs.getString("MaKH"));
            ci.setTenKH(rs.getString("TenKH"));
           
           
            ci.setMaPhong(rs.getString("MaPhong"));
            ci.setTenPhong(rs.getString("TenPhong"));
            ci.setLoaiPhong(rs.getString("TenLoaiPhong"));
    
            ci.setGia(rs.getBigDecimal("Tien"));
            ci.setNgayThuePhong(rs.getString("NgayThuePhong"));

            return ci;
        }
        return null;
    }
    
    public boolean insert(ModelCheckInv2 rt) throws Exception {
        String sql = "insert into PhieuThuePhong (MaPhieuThuePhong, NgayThuePhong, MaPhieuDatPhong) values (?,?,?)";

        conn = cn.getConnection();
        PreparedStatement pstmt = conn.prepareStatement(sql);
        
        pstmt.setString(1, rt.getMaPhieuThuePhong());
        pstmt.setString(2, rt.getNgayThuePhong());
        pstmt.setString(3, rt.getMaPhieuDatPhong());

        return pstmt.executeUpdate() > 0;

    }
    public boolean update(ModelCheckInv2 rt) throws Exception {
        String sql = "update PhieuThuePhong set NgayThuePhong =? , MaPhieuDatPhong=? where MaPhieuThuePhong = ? ";

        conn = cn.getConnection();
        PreparedStatement pstmt = conn.prepareStatement(sql);

        
        pstmt.setString(1, rt.getNgayThuePhong());
        pstmt.setString(2, rt.getMaPhieuDatPhong());
        pstmt.setString(3, rt.getMaPhieuThuePhong());

        return pstmt.executeUpdate() > 0;
  
    }
    
    public boolean deletecomeroot(ModelCheckInv2 rt) throws Exception {
       
        String sql = "update PhieuThuePhong set isvisible = '0' where MaPhieuThuePhong = ? ";
        

        conn = cn.getConnection();
        conn.setAutoCommit(false); // Tắt chế độ tự động commit

        try {

            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, rt.getMaPhieuThuePhong());

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
