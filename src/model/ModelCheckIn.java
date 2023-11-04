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
}
