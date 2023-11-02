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
public class ModelRent {
    Connect cn = new Connect();
    Connection conn;
    
    public ArrayList<ModelRentv2> findALL() throws Exception {
        String sql = "select MaPhieuDatPhong, kh.MaKH, TenKH, nv.MaNV, TenNV, p.MaPhong, TenPhong, lp.TenLoaiPhong, "
                + "NgayDatPhong, Tien from PhieuDatPhong pdp join PHONG p on pdp.MaPhong = p.MaPhong "
                + "join KHACHHANG kh on kh.MaKH =pdp.MaKH join NHANVIEN nv on pdp.MaNV = nv.MaNV "
                + "join LOAIPHONG lp on lp.MaLoaiPhong = p.MaLoaiPhong where pdp.isvisible = '1' ";
        conn = cn.getConnection();
        PreparedStatement pstmt = conn.prepareStatement(sql);
        ResultSet rs = pstmt.executeQuery();
        ArrayList<ModelRentv2> list = new ArrayList<>();
        while (rs.next()) {
            ModelRentv2 tp = new ModelRentv2();
            tp.setMaPhieuDatPhong(rs.getString("MaPhieuDatPhong"));
            tp.setMaKH(rs.getString("MaKH"));
            tp.setTenKH(rs.getString("TenKH"));
            tp.setMaNV(rs.getString("MaNV"));
            tp.setTenNV(rs.getString("TenNV"));
           
            tp.setMaPhong(rs.getString("MaPhong"));
            tp.setTenPhong(rs.getString("TenPhong"));
            tp.setLoaiPhong(rs.getString("TenLoaiPhong"));
            tp.setNgayDatPhong(rs.getString("NgayDatPhong"));
            tp.setGia(rs.getBigDecimal("Tien"));
            
            
            list.add(tp);
        }
        return list;
    }
    
    public boolean insert(ModelRentv2 rt) throws Exception {
        String sql = "insert into PhieuDatPhong (MaPhieuDatPhong, NgayDatPhong,MaPhong,MaKH,MaNV) values (?,?,?,?,?)";

        conn = cn.getConnection();
        PreparedStatement pstmt = conn.prepareStatement(sql);

        pstmt.setString(1, rt.getMaPhieuDatPhong());
        pstmt.setString(2, rt.getNgayDatPhong());
        pstmt.setString(3, rt.getMaPhong());
        pstmt.setString(4, rt.getMaKH());
        pstmt.setString(5, rt.getMaNV());
       

        return pstmt.executeUpdate() > 0;

    }
    public boolean update(ModelRentv2 rt) throws Exception {
        String sql = "update PhieuDatPhong set NgayDatPhong =?,MaPhong=?,MaKH=? ,MaNV=? where MaPhieuDatPhong = ?";

        conn = cn.getConnection();
        PreparedStatement pstmt = conn.prepareStatement(sql);

        pstmt.setString(5, rt.getMaPhieuDatPhong());
        pstmt.setString(1, rt.getNgayDatPhong());
        pstmt.setString(2, rt.getMaPhong());
        pstmt.setString(3, rt.getMaKH());
        pstmt.setString(4, rt.getMaNV());

        return pstmt.executeUpdate() > 0;

    }
}
