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
public class ModelBill {
    Connect cn = new Connect();
    Connection conn;

    public ArrayList<ModelBillv2> findALL() throws Exception {
        String sql = "select HD.MaHD, MaCTHOADON, NV.manv, tennv, KH.makh,tenkh,hd.MaHD,NgayDKThue, NgayTraPhong, TongTien, TenDichVu, (DonGia)  from HOADON hd join NHANVIEN nv on hd.MaNV= nv.MaNV \n"
                + "join KHACHHANG kh on kh.MaKH = hd.MaKH join CTHOADON cthd on cthd.MaHD = hd.MaHD join DICHVU dv\n"
                + " on dv.MaDichVu = cthd.MaDichVu join PHIEUTHUEPHONG pthue on pthue.MaKH = Kh.MaKH join CTPhieuThue ctpt on ctpt.MaPhieuThuePhong = pthue.MaPhieuThuePhong \n"
                + " join PHIEUTRAPHONG ptra on ptra.MaPhieuTraPhong = ctpt.MaPhieuTraPhong";
        conn = cn.getConnection();
        PreparedStatement pstmt = conn.prepareStatement(sql);
        ResultSet rs = pstmt.executeQuery();
        ArrayList<ModelBillv2> list = new ArrayList<>();
        while (rs.next()) {
            ModelBillv2 tp = new ModelBillv2();
            tp.setMahd(rs.getString("mahd"));
            tp.setMachd(rs.getString("MaCTHOADON"));
            tp.setManv(rs.getString("manv"));
            tp.setTennv(rs.getString("tennv"));
            tp.setMakh(rs.getString("makh"));
            tp.setTenkh(rs.getString("tenkh"));
            tp.setNgaythue(rs.getString("ngaydkthue"));
            tp.setNgaytra(rs.getString("ngaytraphong"));
            tp.setGiaphong(rs.getDouble("tongtien"));
            tp.setDichvu(rs.getString("tendichvu"));
            tp.setGiadichvu(rs.getDouble("dongia"));
           
            list.add(tp);
        }
        return list;
    }

   
}
