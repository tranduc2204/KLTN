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

/**
 *
 * @author TeeDee
 */
public class ModelGiaDV {
    Connect cn = new Connect();
    Connection conn;
    
    public ModelGiaDVv2 findByID_giahddv(String MaDichVu) throws Exception {
        String sql = "select madongiadv, NgayApDung,DonGia from DonGiaDV where MaDonGiaDV = ? ";
        conn = cn.getConnection();
        PreparedStatement pstmt = conn.prepareStatement(sql);

        pstmt.setString(1, MaDichVu);
        ResultSet rs = pstmt.executeQuery();

        if (rs.next()) {
            ModelGiaDVv2 dv = new ModelGiaDVv2();
            dv.setMaDonGiaDV(rs.getString("MaDonGiaDV"));
            dv.setNgayApDung(rs.getString("NgayApDung"));
            dv.setDonGia(rs.getBigDecimal("DonGia"));
           
            return dv;
        }
        return null;
    }
}
