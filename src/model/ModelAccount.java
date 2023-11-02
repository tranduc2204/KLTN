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
public class ModelAccount {
    Connect cn = new Connect();
    Connection conn;
    public ArrayList<ModelAccountv2> findALL() throws Exception {
        String sql = "select * from Account where isvisible = '1' and ( Typpe = 'Admin' or Typpe = 'User' )";
        conn = cn.getConnection();
        PreparedStatement pstmt = conn.prepareStatement(sql);
        ResultSet rs = pstmt.executeQuery();
        ArrayList<ModelAccountv2> list = new ArrayList<>();
        while (rs.next()) {
            ModelAccountv2 acc = new ModelAccountv2();
            acc.setUsername(rs.getString("UserName"));
            acc.setDisplayname(rs.getString("DisplayName"));
            acc.setPassword(rs.getString("PassWorrd"));
            acc.setType(rs.getString("Typpe"));
            
            list.add(acc);
        }
        return list;
    }

    public boolean insert(ModelAccountv2 acc) throws Exception {
        String sql = "insert into Account (username, displayname, passworrd, typpe) values (?,?,?,?)";

        conn = cn.getConnection();
        PreparedStatement pstmt = conn.prepareStatement(sql);

        pstmt.setString(1, acc.getUsername());
        pstmt.setString(2, acc.getDisplayname());
        pstmt.setString(3, acc.getPassword());
        pstmt.setString(4, acc.getType());

        return pstmt.executeUpdate() > 0;

    }

    

    public boolean update(ModelAccountv2 acc) throws Exception {
        String sql = "update account set displayname =?,passworrd=?,typpe=? where username =?";

        conn = cn.getConnection();
        PreparedStatement pstmt = conn.prepareStatement(sql);

        pstmt.setString(4, acc.getUsername());
        pstmt.setString(1, acc.getDisplayname());
        pstmt.setString(2, acc.getPassword());
        pstmt.setString(3, acc.getType());
        

        return pstmt.executeUpdate() > 0;

    }

    public boolean delete(String username) throws Exception {
        String sql = "delete from account where username= ? ";

        conn = cn.getConnection();
        PreparedStatement pstmt = conn.prepareStatement(sql);

        pstmt.setString(1, username);

        return pstmt.executeUpdate() > 0;

    }
    
    public boolean deletecomeroot (ModelAccountv2 acc) throws Exception {
        String sql = "update account set isvisible = '0' where username = ? ";

        conn = cn.getConnection();
        PreparedStatement pstmt = conn.prepareStatement(sql);

        pstmt.setString(1, acc.getUsername());

        return pstmt.executeUpdate() > 0;

    }
}
