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
public class ModelRoomLP {
    Connect cn = new Connect();
    Connection conn;

    public ArrayList<ModelRoomLPv2> findALL() throws Exception {
        String sql = "select * from loaiphong ";
        conn = cn.getConnection();
        PreparedStatement pstmt = conn.prepareStatement(sql);
        ResultSet rs = pstmt.executeQuery();
        ArrayList<ModelRoomLPv2> list = new ArrayList<>();
        while (rs.next()) {
            ModelRoomLPv2 lp = new ModelRoomLPv2();
            lp.setMaLoaiPhong(rs.getString("MaLoaiPhong"));
            lp.setTenLoaiPhong(rs.getString("TenLoaiPhong"));
           
            

            list.add(lp);
        }
        return list;
    }

    public ModelRoomLPv2 findByID(String MaLoaiPhong) throws Exception {
        String sql = "select * from loaiphong where maloaiphong=?";
        conn = cn.getConnection();
        PreparedStatement pstmt = conn.prepareStatement(sql);

        pstmt.setString(1, MaLoaiPhong);
        ResultSet rs = pstmt.executeQuery();

        if (rs.next()) {
            ModelRoomLPv2 lp = new ModelRoomLPv2();
             lp.setMaLoaiPhong(rs.getString("MaLoaiPhong"));
             lp.setTenLoaiPhong(rs.getString("TenLoaiPhong"));
            
            return lp;
        }
        return null;
    }
}
