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
public class ModelBillOfService {
    Connect cn = new Connect();
    Connection conn;
    
    public ArrayList<ModelBillOfServicev2> findALL() throws Exception {//, DonGia*sl as tongtien
        String sql = "select dv.MaDV, TenDichVu, nv.MaNV, TenNV, kh.MaKH, TenKH, DonGia,SL , NgayLapHD, (DonGia * sl) as tongtien "
                + "from HoaDonDV hdv join DICHVU dv on hdv.MaDV = dv.MaDV join KHACHHANG kh on hdv.MaKH = kh.MaKH "
                + "join NHANVIEN nv on nv.MaNV = hdv.MaNV where hdv.isvisible = '1' ";
        conn = cn.getConnection();
        PreparedStatement pstmt = conn.prepareStatement(sql);
        ResultSet rs = pstmt.executeQuery();
        ArrayList<ModelBillOfServicev2> list = new ArrayList<>();
        while (rs.next()) {
            ModelBillOfServicev2 tp = new ModelBillOfServicev2();
            tp.setMaDV(rs.getString("MaDV"));
            tp.setTenDichVu(rs.getString("TenDichVu"));
            tp.setMaNV(rs.getString("MaNV"));
            tp.setTenNV(rs.getString("TenNV"));
            tp.setMaKH(rs.getString("MaKH"));
            tp.setTenKH(rs.getString("TenKH"));
            tp.setGiadv(rs.getBigDecimal("DonGia"));
            tp.setSL(rs.getInt("SL"));
            tp.setNgayLapHD(rs.getString("NgayLapHD"));
            
            
            list.add(tp);
        }
        return list;
    }
}
