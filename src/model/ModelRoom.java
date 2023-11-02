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
        String sql = "select MaPhong,TenPhong, TinhTrangPhong.MaTinhtrangphong, TinhTrangPhong, LoaiPhong.MaLoaiPhong, TenLoaiPhong, Tien from phong "
                + "join LOAIPHONG on PHONG.MaLoaiPhong = LOAIPHONG.MaLoaiPhong join TINHTRANGPHONG on TinhTrangPhong.MaTinhTrangPhong = PHONG.MaTinhtrangphong where isvisible = '1' ";
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
            tp.setTien(rs.getBigDecimal("Tien"));
            
            list.add(tp);
        }
        return list;
    }
    
    public ModelRoomv2 findByID(String MaPhong) throws Exception {
        String sql = "select MaPhong,TenPhong, TinhTrangPhong.MaTinhtrangphong, TinhTrangPhong, LoaiPhong.MaLoaiPhong, TenLoaiPhong, Tien from phong "
                + "join LOAIPHONG on PHONG.MaLoaiPhong = LOAIPHONG.MaLoaiPhong join TINHTRANGPHONG on TinhTrangPhong.MaTinhTrangPhong = PHONG.MaTinhtrangphong where maphong =? and isvisible = '1' ";
        
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
            tp.setTien(rs.getBigDecimal("Tien"));
            
            return tp;
        }
        return null;
    }
}
