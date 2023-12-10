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
public class ModelRoom {
    Connect cn = new Connect();
    Connection conn;

    public ArrayList<ModelRoomv2> findALL() throws Exception {
        String sql = "select MaPhong,TenPhong, TinhTrangPhong.MaTinhtrangphong, TinhTrangPhong, LoaiPhong.MaLoaiPhong, TenLoaiPhong, dgp.MaDonGiaPhong, GiamGia,  dgp.DonGia from phong \n" +
"join LOAIPHONG on PHONG.MaLoaiPhong = LOAIPHONG.MaLoaiPhong join TINHTRANGPHONG on TinhTrangPhong.MaTinhTrangPhong = PHONG.MaTinhtrangphong \n" +
"join DonGiaPhong dgp on dgp.MaDonGiaPhong = phong.MaDonGiaPhong\n" +
"where isvisible = '1'   ";
        conn = cn.getConnection();
        PreparedStatement pstmt = conn.prepareStatement(sql);
        ResultSet rs = pstmt.executeQuery();
        ArrayList<ModelRoomv2> list = new ArrayList<>();
        while (rs.next()) {
            ModelRoomv2 tp = new ModelRoomv2();
            tp.setMaPhong(rs.getString("MaPhong"));
            tp.setTenPhong(rs.getString("TenPhong"));
            tp.setMaTinhTrangPhong(rs.getString("MaTinhTrangPhong"));
            tp.setTinhtrangphong(rs.getString("TinhTrangPhong"));
            tp.setMaLoaiPhong(rs.getString("MaLoaiPhong"));
            tp.setTenLoaiPhong(rs.getString("TenLoaiPhong"));
            tp.setMaDonGiaPhong(rs.getString("MaDonGiaPhong"));
            tp.setGiamGia(rs.getInt("GiamGia"));
            tp.setTien(rs.getBigDecimal("DonGia"));
            
            list.add(tp);
        }
        return list;
    }
    
    public ModelRoomv2 findByID(String MaPhong) throws Exception {
        String sql = "select MaPhong,TenPhong, TinhTrangPhong.MaTinhtrangphong, TinhTrangPhong, LoaiPhong.MaLoaiPhong, TenLoaiPhong, DonGia from phong \n" +
"join LOAIPHONG on PHONG.MaLoaiPhong = LOAIPHONG.MaLoaiPhong join TINHTRANGPHONG on TinhTrangPhong.MaTinhTrangPhong = PHONG.MaTinhtrangphong  \n" +
"join DonGiaPhong dgp on dgp.MaDonGiaPhong = phong.MaDonGiaPhong where maphong =? and isvisible = '1' ";
        
        conn = cn.getConnection();
        PreparedStatement pstmt = conn.prepareStatement(sql);

        pstmt.setString(1, MaPhong);
        ResultSet rs = pstmt.executeQuery();

        if (rs.next()) {
            ModelRoomv2 tp = new ModelRoomv2();
              tp.setMaPhong(rs.getString("MaPhong"));
            tp.setTenPhong(rs.getString("TenPhong"));
            tp.setMaTinhTrangPhong(rs.getString("MaTinhTrangPhong"));
            tp.setTinhtrangphong(rs.getString("TinhTrangPhong"));
            tp.setMaLoaiPhong(rs.getString("MaLoaiPhong"));
            tp.setTenLoaiPhong(rs.getString("TenLoaiPhong"));
            tp.setTien(rs.getBigDecimal("DonGia"));
            
            return tp;
        }
        return null;
    }
    
    public ModelRoomv2 findByID_dongiaphong(String MaDonGia) throws Exception {
        String sql = "select * from DonGiaPhong where MaDonGiaPhong = ? ";
        
        conn = cn.getConnection();
        PreparedStatement pstmt = conn.prepareStatement(sql);

        pstmt.setString(1, MaDonGia);
        ResultSet rs = pstmt.executeQuery();

        if (rs.next()) {
            ModelRoomv2 tp = new ModelRoomv2();
           
            tp.setTien(rs.getBigDecimal("DonGia"));
            
            return tp;
        }
        return null;
    }
    
    public boolean insert(ModelRoomv2 p) throws Exception {
        String sql = "insert into phong (MaPhong, TenPhong,MaTinhTrangphong,MaLoaiPhong,MaDonGiaPhong, GiamGia) values (?,?,?,?,?,?)";

        conn = cn.getConnection();
        PreparedStatement pstmt = conn.prepareStatement(sql);

        pstmt.setString(1, p.getMaPhong());
        pstmt.setString(2, p.getTenPhong());
        pstmt.setString(3, p.getMaTinhTrangPhong());
        pstmt.setString(4, p.getMaLoaiPhong());
        pstmt.setString(5, p.getMaDonGiaPhong());
        pstmt.setInt(6, p.getGiamGia());
        return pstmt.executeUpdate() > 0;

    }
    
    public boolean update(ModelRoomv2 p) throws Exception {
        String sql = "update phong set TenPhong=?, matinhtrangphong=? ,maloaiphong=?,  MaDonGiaPhong=? , GiamGia = ? where maphong = ?";

        conn = cn.getConnection();
        PreparedStatement pstmt = conn.prepareStatement(sql);

        pstmt.setString(6, p.getMaPhong());
        pstmt.setString(1, p.getTenPhong());
        pstmt.setString(2, p.getMaTinhTrangPhong());
        pstmt.setString(3, p.getMaLoaiPhong());
        pstmt.setString(4, p.getMaDonGiaPhong());
        pstmt.setInt(5, p.getGiamGia());
        return pstmt.executeUpdate() > 0;
    }
     
    

    public boolean delete(String MaPhong) throws Exception {
        String sql = "delete from Phong where MaPhong= ?";

        conn = cn.getConnection();
        PreparedStatement pstmt = conn.prepareStatement(sql);

        pstmt.setString(1, MaPhong);

        return pstmt.executeUpdate() > 0;

    }
    
    public boolean deletecomeroot(ModelRoomv2 nv) throws Exception {
       
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
}
