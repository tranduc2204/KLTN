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
public class ModelSuppliesforRoom {
    Connect cn = new Connect();
    Connection conn;

    public ArrayList<ModelSuppliesforRoomv2> findALL() throws Exception {
        String sql = "select vt.MaVatTu, vt.TenVatTu, lp.MaLoaiPhong, lp.TenLoaiPhong, ctvt.SoLuong from CTVatTu ctvt "
                + "join VATTU vt on vt.MaVatTu = ctvt.MaVatTu join LOAIPHONG lp on lp.MaLoaiPhong =ctvt.MaLoaiPhong where ctvt.isvisible = '1' ";
        conn = cn.getConnection();
        PreparedStatement pstmt = conn.prepareStatement(sql);
        ResultSet rs = pstmt.executeQuery();
        ArrayList<ModelSuppliesforRoomv2> list = new ArrayList<>();
        while (rs.next()) {
            ModelSuppliesforRoomv2 tp = new ModelSuppliesforRoomv2();
            tp.setMaVatTu(rs.getString("MaVatTu"));
            tp.setTenVatTu(rs.getString("TenVatTu"));
            tp.setMaLoaiPhong(rs.getString("MaLoaiPhong"));
            tp.setTenLoaiPhong(rs.getString("TenLoaiPhong"));
            tp.setSoLuong(rs.getInt("SoLuong"));
           
            
            list.add(tp);
        }
        return list;
    }
    
//    public ModelRoomv2 findByID(String MaPhong) throws Exception {
//        String sql = "select MaPhong,TenPhong, TinhTrangPhong.MaTinhtrangphong, TinhTrangPhong, LoaiPhong.MaLoaiPhong, TenLoaiPhong, SoGiuong,Tien from phong "
//                + "join LOAIPHONG on PHONG.MaLoaiPhong = LOAIPHONG.MaLoaiPhong join TINHTRANGPHONG on TinhTrangPhong.MaTinhTrangPhong = PHONG.MaTinhtrangphong where maphong =?";
//        conn = cn.getConnection();
//        PreparedStatement pstmt = conn.prepareStatement(sql);
//
//        pstmt.setString(1, MaPhong);
//        ResultSet rs = pstmt.executeQuery();
//
//        if (rs.next()) {
//            ModelRoomv2 tp = new ModelRoomv2();
//              tp.setMaPhong(rs.getString("MaPhong"));
//            tp.setTenPhong(rs.getString("TenPhong"));
//            tp.setMaTinhTrangPhong(rs.getString("MaTinhTrangPhong"));
//            tp.setTinhtrangphong(rs.getString("TinhTrangPhong"));
//            tp.setMaLoaiPhong(rs.getString("MaLoaiPhong"));
//            tp.setTenLoaiPhong(rs.getString("TenLoaiPhong"));
//            tp.setTien(rs.getBigDecimal("Tien"));
//            
//            return tp;
//        }
//        return null;
//    }
    public boolean insert(ModelSuppliesforRoomv2 p) throws Exception {
        String sql = "insert into CTVatTu (mavattu, maloaiphong,soluong) values (?,?,?)";

        conn = cn.getConnection();
        PreparedStatement pstmt = conn.prepareStatement(sql);

        pstmt.setString(1, p.getMaVatTu());
        pstmt.setString(2, p.getMaLoaiPhong());
        pstmt.setDouble(3, p.getSoLuong());

        return pstmt.executeUpdate() > 0;

    }
    public boolean update(ModelSuppliesforRoomv2 p) throws Exception {
        String sql = "update CTVatTu set soluong=? where mavattu = ? and maloaiphong = ?";

        conn = cn.getConnection();
        PreparedStatement pstmt = conn.prepareStatement(sql);

        
        pstmt.setDouble(1, p.getSoLuong());
        pstmt.setString(2, p.getMaVatTu());
        pstmt.setString(3, p.getMaLoaiPhong());
    
        return pstmt.executeUpdate() > 0;
    }
     
    

    public boolean delete(String Mavattu, String maloaiphong) throws Exception {
        String sql = "delete from CTVatTu where mavattu = ? and maloaiphong = ?";

        conn = cn.getConnection();
        PreparedStatement pstmt = conn.prepareStatement(sql);

        pstmt.setString(1, Mavattu);
        pstmt.setString(2, maloaiphong);
        return pstmt.executeUpdate() > 0;

    }
    
    public boolean deletecomeroot (ModelSuppliesforRoomv2 p) throws Exception {
        String sql = "update CTVatTu set isvisible = '0' where mavattu = ? and maloaiphong = ? ";

        conn = cn.getConnection();
        PreparedStatement pstmt = conn.prepareStatement(sql);

        pstmt.setString(1, p.getMaVatTu());
        pstmt.setString(2, p.getMaLoaiPhong());
        return pstmt.executeUpdate() > 0;

    }
}
