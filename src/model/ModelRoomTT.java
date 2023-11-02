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
public class ModelRoomTT {
    Connect cn = new Connect();
    Connection conn;

    public ArrayList<ModelRoomTTv2> findALL() throws Exception {
        String sql = "select * from tinhtrangphong ";
        conn = cn.getConnection();
        PreparedStatement pstmt = conn.prepareStatement(sql);
        ResultSet rs = pstmt.executeQuery();
        ArrayList<ModelRoomTTv2> list = new ArrayList<>();
        while (rs.next()) {
            ModelRoomTTv2 ttp = new ModelRoomTTv2();
            ttp.setMatinhtrangphong(rs.getString("MaTinhtrangPhong"));
            ttp.setTinhtrangphong(rs.getString("TinhTrangPhong"));
           
            

            list.add(ttp);
        }
        return list;
    }

    public ModelRoomTTv2 findByID(String MaTinhTrangPhong) throws Exception {
        String sql = "select * from TinhTrangPhong where matinhtrangphong =?";
        conn = cn.getConnection();
        PreparedStatement pstmt = conn.prepareStatement(sql);

        pstmt.setString(1, MaTinhTrangPhong);
        ResultSet rs = pstmt.executeQuery();

        if (rs.next()) {
            ModelRoomTTv2 ttp = new ModelRoomTTv2();
             ttp.setMatinhtrangphong(rs.getString("MaTinhTrangPhong"));
             ttp.setTinhtrangphong(rs.getString("TinhTrangPhong"));
            
            return ttp;
        }
        return null;
    }
}
