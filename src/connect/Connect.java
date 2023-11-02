/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package connect;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author TeeDee
 */
public class Connect {
    
    Connection conn;
    
    public Connection getConnection()  {
        //Connection conn = null;
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            String url = "jdbc:sqlserver://localhost:1433;databaseName=KLTN;";//QL_KS_NewWorldHotel///TranDuc_DB_THNN
            String user = "sa";
            String password = "sa";
            conn = DriverManager.getConnection(url, user, password);
            if (conn != null) {
                System.out.println("Ket noi thanh cong");
            }

        } catch (Exception e) {
            System.out.println(e.toString());
            System.out.println("nope");
        }
        return conn;
    }
    
    //Thực thi câu lệnh SELECT
    public ResultSet ExcuteQueryGetTable(String cauTruyVanSQL) {
        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(cauTruyVanSQL);
            return rs;
        } catch (SQLException ex) {
            System.out.println(ex.toString());
        }

        return null;
    }

    //Thực thi INSERT, DELETE, UPDATE
    public void ExcuteQueryUpdateDB(String cauTruyVanSQL) {

        try {
            Statement stmt = conn.createStatement();
            stmt.executeUpdate(cauTruyVanSQL);
        } catch (SQLException ex) {
            System.out.println(ex.toString());
        }
    }
}
