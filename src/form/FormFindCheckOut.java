/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package form;

import connect.Connect;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.Date;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author TeeDee
 */
public class FormFindCheckOut extends javax.swing.JFrame {

    /**
     * Creates new form FormFindCheckOut
     */
    public FormFindCheckOut() {
        initComponents();
    }
    
    Connect cn = new Connect();
    Connection conn;
    private DefaultTableModel tableModel;

    private String username, password, quyen, DisplayName, MaNV ;
    public FormFindCheckOut(String username, String password, String DisplayName, String quyen) {
        initComponents();
        this.username = username;
        this.password = password;
        this.DisplayName = DisplayName;
        this.quyen = quyen;
        this.MaNV = MaNV;
        
        Checkout();
    }
    
    private void Checkout(){
        // Khởi tạo model và đặt tên cột
        tableModel = new DefaultTableModel();
        tableModel.addColumn("Mã phiếu trả");
        tableModel.addColumn("Ngày thuê phòng");
        tableModel.addColumn("Ngày trả phòng");
        tableModel.addColumn("Tên phòng");
        tableModel.addColumn("Loại phòng");
        tableModel.addColumn("Giá phòng");
        tableModel.addColumn("Giảm giá");
        tableModel.addColumn("VAT");
        tableModel.addColumn("Giá hóa đơn");
        tableModel.addColumn("Ngày lập hóa đơn");

        table.setModel(tableModel);
        

        loadDataFromSQL();
    }
    
    private void loadDataFromSQL() {
         
        Connection connection = null;
        try {
             // Kết nối đến cơ sở dữ liệu SQL Server

            String server = "localhost";
            String port = "1433";
            String database = "KLTN";
            String username = "sa";
            String password = "sa";

            String jdbcUrl = "jdbc:sqlserver://" + server + ":" + port + ";databaseName=" + database + ";user=" + username + ";password=" + password;
            connection = DriverManager.getConnection(jdbcUrl);
            
            // Thực hiện truy vấn SQL để lấy dữ liệu từ bảng
            String sqlQuery = "select MaHoaDonPhong,  NgayDuKienThue, NgayDuKienTra, TenPhong, TenLoaiPhong, CONCAT(FORMAT(DonGia, 'N0'),' VND') as DonGia,Concat(GiamGia,'%') as GiamGia, \n" +
"CONCAT(FORMAT((((DATEDIFF(DAY, NgayDuKienThue, NgayDuKienTra)) *DonGia) * 0.1), 'N0'),' VND') as VAT , CONCAT(FORMAT((((DATEDIFF(DAY, NgayDuKienThue, NgayDuKienTra)) *DonGia)  +  (((DATEDIFF(DAY, NgayDuKienThue, NgayDuKienTra)) *DonGia) * 0.1) - (((DATEDIFF(DAY, NgayDuKienThue, NgayDuKienTra)) *DonGia) *GiamGia /100)), 'N0'),' VND') as GiaHD, NgayLapHoaDon\n" +
"from HoaDonPhong hdp join PhieuDatPhong pdp on hdp.MaPhieuDatPhong = pdp.MaPhieuDatPhong join phong p on p.MaPhong = pdp.MaPhong\n" +
"join DonGiaPhong dgp on dgp.MaDonGiaPhong = p.MaDonGiaPhong join LOAIPHONG lp on lp.MaLoaiPhong = p.MaLoaiPhong\n" +
"where hdp.isvisible = '1' ";
            try (PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);
                ResultSet resultSet = preparedStatement.executeQuery()) {

                // Lặp qua kết quả và thêm vào model
                while (resultSet.next()) {
                    Object[] rowData = {
                            resultSet.getObject("MaHoaDonPhong"),
                            resultSet.getObject("NgayDuKienThue"),
                            resultSet.getObject("NgayDuKienTra"),
                            resultSet.getObject("TenPhong"),
                            resultSet.getObject("TenLoaiPhong"),
                            resultSet.getObject("DonGia"),
                            resultSet.getObject("GiamGia"),
                            resultSet.getObject("VAT"),
                            resultSet.getObject("GiaHD"),
                            resultSet.getObject("NgayLapHoaDon"),
                            
                    };
                    tableModel.addRow(rowData);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error loading data from SQL: " + e.getMessage());
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    
    
    ////////////////////////////////////// mã phiếu trả phòng
    
    private void Ma(){
        // Khởi tạo model và đặt tên cột
        tableModel = new DefaultTableModel();
        tableModel.addColumn("Mã phiếu trả");
        tableModel.addColumn("Ngày thuê phòng");
        tableModel.addColumn("Ngày trả phòng");
        tableModel.addColumn("Tên phòng");
        tableModel.addColumn("Loại phòng");
        tableModel.addColumn("Giá phòng");
        tableModel.addColumn("Giảm giá");
        tableModel.addColumn("VAT");
        tableModel.addColumn("Giá hóa đơn");
        tableModel.addColumn("Ngày lập hóa đơn");

        table.setModel(tableModel);
        

        loadDataFromSQL_Ma();
    }
    
    private void loadDataFromSQL_Ma() {
        String MaPhieuTraPhong = txtMaphieutraphong.getText();
        Connection connection = null;
        try {
             // Kết nối đến cơ sở dữ liệu SQL Server

            String server = "localhost";
            String port = "1433";
            String database = "KLTN";
            String username = "sa";
            String password = "sa";

            String jdbcUrl = "jdbc:sqlserver://" + server + ":" + port + ";databaseName=" + database + ";user=" + username + ";password=" + password;
            connection = DriverManager.getConnection(jdbcUrl);
            
            // Thực hiện truy vấn SQL để lấy dữ liệu từ bảng
            String sqlQuery = "select MaHoaDonPhong,  NgayDuKienThue, NgayDuKienTra, TenPhong, TenLoaiPhong, CONCAT(FORMAT(DonGia, 'N0'),' VND') as DonGia,Concat(GiamGia,'%') as GiamGia, \n" +
"CONCAT(FORMAT((((DATEDIFF(DAY, NgayDuKienThue, NgayDuKienTra)) *DonGia) * 0.1), 'N0'),' VND') as VAT , CONCAT(FORMAT((((DATEDIFF(DAY, NgayDuKienThue, NgayDuKienTra)) *DonGia)  +  (((DATEDIFF(DAY, NgayDuKienThue, NgayDuKienTra)) *DonGia) * 0.1) - (((DATEDIFF(DAY, NgayDuKienThue, NgayDuKienTra)) *DonGia) *GiamGia /100)), 'N0'),' VND') as GiaHD, NgayLapHoaDon\n" +
"from HoaDonPhong hdp join PhieuDatPhong pdp on hdp.MaPhieuDatPhong = pdp.MaPhieuDatPhong join phong p on p.MaPhong = pdp.MaPhong\n" +
"join DonGiaPhong dgp on dgp.MaDonGiaPhong = p.MaDonGiaPhong join LOAIPHONG lp on lp.MaLoaiPhong = p.MaLoaiPhong\n" +
"where hdp.isvisible = '1' and MaHoaDonPhong = '"+MaPhieuTraPhong+"'   ";
            try (PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);
                ResultSet resultSet = preparedStatement.executeQuery()) {

                // Lặp qua kết quả và thêm vào model
                while (resultSet.next()) {
                    Object[] rowData = {
                            resultSet.getObject("MaHoaDonPhong"),
                            resultSet.getObject("NgayDuKienThue"),
                            resultSet.getObject("NgayDuKienTra"),
                            resultSet.getObject("TenPhong"),
                            resultSet.getObject("TenLoaiPhong"),
                            resultSet.getObject("DonGia"),
                            resultSet.getObject("GiamGia"),
                            resultSet.getObject("VAT"),
                            resultSet.getObject("GiaHD"),
                            resultSet.getObject("NgayLapHoaDon"),
                            
                    };
                    tableModel.addRow(rowData);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error loading data from SQL: " + e.getMessage());
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    
    ////////////////////////////////////// ngày thuê ngày trả
    
    private void ngaythuetra(){
        // Khởi tạo model và đặt tên cột
        tableModel = new DefaultTableModel();
        tableModel.addColumn("Mã phiếu trả");
        tableModel.addColumn("Ngày thuê phòng");
        tableModel.addColumn("Ngày trả phòng");
        tableModel.addColumn("Tên phòng");
        tableModel.addColumn("Loại phòng");
        tableModel.addColumn("Giá phòng");
        tableModel.addColumn("Giảm giá");
        tableModel.addColumn("VAT");
        tableModel.addColumn("Giá hóa đơn");
        tableModel.addColumn("Ngày lập hóa đơn");

        table.setModel(tableModel);
        

        loadDataFromSQL_ngaythuetra();
    }
    
    private void loadDataFromSQL_ngaythuetra() {

        String MaPhieuTraPhong = txtMaphieutraphong.getText();
        Connection connection = null;
        try {
            
            Date selectedDate = jDateChooserngaydkthue.getDate();
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(selectedDate);

            int year = calendar.get(Calendar.YEAR);
            int month = calendar.get(Calendar.MONTH) + 1; // Tháng bắt đầu từ 0, nên cộng thêm 1 để có giá trị tháng thực
            int day = calendar.get(Calendar.DAY_OF_MONTH);
            String ngaythue = year + "-" + month + "-" + day;


            Date selectedDate1 = jDateChooserngaydukientra.getDate();
            Calendar calendar1 = Calendar.getInstance();
            calendar1.setTime(selectedDate1);

            int year1 = calendar1.get(Calendar.YEAR);
            int month1 = calendar1.get(Calendar.MONTH) + 1; // Tháng bắt đầu từ 0, nên cộng thêm 1 để có giá trị tháng thực
            int day1 = calendar1.get(Calendar.DAY_OF_MONTH);
            String ngaytra = year1 + "-" + month1 + "-" + day1;
            
             // Kết nối đến cơ sở dữ liệu SQL Server

            String server = "localhost";
            String port = "1433";
            String database = "KLTN";
            String username = "sa";
            String password = "sa";

            String jdbcUrl = "jdbc:sqlserver://" + server + ":" + port + ";databaseName=" + database + ";user=" + username + ";password=" + password;
            connection = DriverManager.getConnection(jdbcUrl);
            
            // Thực hiện truy vấn SQL để lấy dữ liệu từ bảng
            String sqlQuery = "select MaHoaDonPhong,  NgayDuKienThue, NgayDuKienTra, TenPhong, TenLoaiPhong, CONCAT(FORMAT(DonGia, 'N0'),' VND') as DonGia,Concat(GiamGia,'%') as GiamGia, \n" +
"CONCAT(FORMAT((((DATEDIFF(DAY, NgayDuKienThue, NgayDuKienTra)) *DonGia) * 0.1), 'N0'),' VND') as VAT , CONCAT(FORMAT((((DATEDIFF(DAY, NgayDuKienThue, NgayDuKienTra)) *DonGia)  +  (((DATEDIFF(DAY, NgayDuKienThue, NgayDuKienTra)) *DonGia) * 0.1) - (((DATEDIFF(DAY, NgayDuKienThue, NgayDuKienTra)) *DonGia) *GiamGia /100)), 'N0'),' VND') as GiaHD, NgayLapHoaDon\n" +
"from HoaDonPhong hdp join PhieuDatPhong pdp on hdp.MaPhieuDatPhong = pdp.MaPhieuDatPhong join phong p on p.MaPhong = pdp.MaPhong\n" +
"join DonGiaPhong dgp on dgp.MaDonGiaPhong = p.MaDonGiaPhong join LOAIPHONG lp on lp.MaLoaiPhong = p.MaLoaiPhong\n" +
"where hdp.isvisible = '1' and NgayDuKienThue = '"+ngaythue+"' and NgayDuKienTra = '"+ngaytra+"'   ";
            try (PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);
                ResultSet resultSet = preparedStatement.executeQuery()) {

                // Lặp qua kết quả và thêm vào model
                while (resultSet.next()) {
                    Object[] rowData = {
                            resultSet.getObject("MaHoaDonPhong"),
                            resultSet.getObject("NgayDuKienThue"),
                            resultSet.getObject("NgayDuKienTra"),
                            resultSet.getObject("TenPhong"),
                            resultSet.getObject("TenLoaiPhong"),
                            resultSet.getObject("DonGia"),
                            resultSet.getObject("GiamGia"),
                            resultSet.getObject("VAT"),
                            resultSet.getObject("GiaHD"),
                            resultSet.getObject("NgayLapHoaDon"),
                            
                    };
                    tableModel.addRow(rowData);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error loading data from SQL: " + e.getMessage());
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    
    
    
    ////////////////////////////////////// ngày lập hđ
    
     private void ngaylaphd(){
        // Khởi tạo model và đặt tên cột
        tableModel = new DefaultTableModel();
        tableModel.addColumn("Mã phiếu trả");
        tableModel.addColumn("Ngày thuê phòng");
        tableModel.addColumn("Ngày trả phòng");
        tableModel.addColumn("Tên phòng");
        tableModel.addColumn("Loại phòng");
        tableModel.addColumn("Giá phòng");
        tableModel.addColumn("Giảm giá");
        tableModel.addColumn("VAT");
        tableModel.addColumn("Giá hóa đơn");
        tableModel.addColumn("Ngày lập hóa đơn");

        table.setModel(tableModel);
        

        loadDataFromSQL_ngaylaphd();
    }
    
    private void loadDataFromSQL_ngaylaphd() {

        Connection connection = null;
        try {
            
            Date selectedDate = jDateChooserngaylaphd.getDate();
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(selectedDate);

            int year = calendar.get(Calendar.YEAR);
            int month = calendar.get(Calendar.MONTH) + 1; // Tháng bắt đầu từ 0, nên cộng thêm 1 để có giá trị tháng thực
            int day = calendar.get(Calendar.DAY_OF_MONTH);
            String ngaylaphd = year + "-" + month + "-" + day;
            
            // Kết nối đến cơ sở dữ liệu SQL Server

            String server = "localhost";
            String port = "1433";
            String database = "KLTN";
            String username = "sa";
            String password = "sa";

            String jdbcUrl = "jdbc:sqlserver://" + server + ":" + port + ";databaseName=" + database + ";user=" + username + ";password=" + password;
            connection = DriverManager.getConnection(jdbcUrl);
            
            // Thực hiện truy vấn SQL để lấy dữ liệu từ bảng
            String sqlQuery = "select MaHoaDonPhong,  NgayDuKienThue, NgayDuKienTra, TenPhong, TenLoaiPhong, CONCAT(FORMAT(DonGia, 'N0'),' VND') as DonGia,Concat(GiamGia,'%') as GiamGia, \n" +
"CONCAT(FORMAT((((DATEDIFF(DAY, NgayDuKienThue, NgayDuKienTra)) *DonGia) * 0.1), 'N0'),' VND') as VAT , CONCAT(FORMAT((((DATEDIFF(DAY, NgayDuKienThue, NgayDuKienTra)) *DonGia)  +  (((DATEDIFF(DAY, NgayDuKienThue, NgayDuKienTra)) *DonGia) * 0.1) - (((DATEDIFF(DAY, NgayDuKienThue, NgayDuKienTra)) *DonGia) *GiamGia /100)), 'N0'),' VND') as GiaHD, NgayLapHoaDon\n" +
"from HoaDonPhong hdp join PhieuDatPhong pdp on hdp.MaPhieuDatPhong = pdp.MaPhieuDatPhong join phong p on p.MaPhong = pdp.MaPhong\n" +
"join DonGiaPhong dgp on dgp.MaDonGiaPhong = p.MaDonGiaPhong join LOAIPHONG lp on lp.MaLoaiPhong = p.MaLoaiPhong\n" +
"where hdp.isvisible = '1' and NgayLapHoaDon = '"+ngaylaphd+"'  ";
            try (PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);
                ResultSet resultSet = preparedStatement.executeQuery()) {

                // Lặp qua kết quả và thêm vào model
                while (resultSet.next()) {
                    Object[] rowData = {
                            resultSet.getObject("MaHoaDonPhong"),
                            resultSet.getObject("NgayDuKienThue"),
                            resultSet.getObject("NgayDuKienTra"),
                            resultSet.getObject("TenPhong"),
                            resultSet.getObject("TenLoaiPhong"),
                            resultSet.getObject("DonGia"),
                            resultSet.getObject("GiamGia"),
                            resultSet.getObject("VAT"),
                            resultSet.getObject("GiaHD"),
                            resultSet.getObject("NgayLapHoaDon"),
                            
                    };
                    tableModel.addRow(rowData);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error loading data from SQL: " + e.getMessage());
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    
    ////////////////////////////////////// mã ngày thuê trả
    
    private void MaThueTra(){
        // Khởi tạo model và đặt tên cột
        tableModel = new DefaultTableModel();
        tableModel.addColumn("Mã phiếu trả");
        tableModel.addColumn("Ngày thuê phòng");
        tableModel.addColumn("Ngày trả phòng");
        tableModel.addColumn("Tên phòng");
        tableModel.addColumn("Loại phòng");
        tableModel.addColumn("Giá phòng");
        tableModel.addColumn("Giảm giá");
        tableModel.addColumn("VAT");
        tableModel.addColumn("Giá hóa đơn");
        tableModel.addColumn("Ngày lập hóa đơn");

        table.setModel(tableModel);
        

        loadDataFromSQL_MaThueTra();
    }
    
    private void loadDataFromSQL_MaThueTra() {
        String MaPhieuTraPhong = txtMaphieutraphong.getText();
        Connection connection = null;
        try {
            
            Date selectedDate = jDateChooserngaydkthue.getDate();
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(selectedDate);

            int year = calendar.get(Calendar.YEAR);
            int month = calendar.get(Calendar.MONTH) + 1; // Tháng bắt đầu từ 0, nên cộng thêm 1 để có giá trị tháng thực
            int day = calendar.get(Calendar.DAY_OF_MONTH);
            String ngaythue = year + "-" + month + "-" + day;


            Date selectedDate1 = jDateChooserngaydukientra.getDate();
            Calendar calendar1 = Calendar.getInstance();
            calendar1.setTime(selectedDate1);

            int year1 = calendar1.get(Calendar.YEAR);
            int month1 = calendar1.get(Calendar.MONTH) + 1; // Tháng bắt đầu từ 0, nên cộng thêm 1 để có giá trị tháng thực
            int day1 = calendar1.get(Calendar.DAY_OF_MONTH);
            String ngaytra = year1 + "-" + month1 + "-" + day1;
            
             // Kết nối đến cơ sở dữ liệu SQL Server

            String server = "localhost";
            String port = "1433";
            String database = "KLTN";
            String username = "sa";
            String password = "sa";

            String jdbcUrl = "jdbc:sqlserver://" + server + ":" + port + ";databaseName=" + database + ";user=" + username + ";password=" + password;
            connection = DriverManager.getConnection(jdbcUrl);
            
            // Thực hiện truy vấn SQL để lấy dữ liệu từ bảng
            String sqlQuery = "select MaHoaDonPhong,  NgayDuKienThue, NgayDuKienTra, TenPhong, TenLoaiPhong, CONCAT(FORMAT(DonGia, 'N0'),' VND') as DonGia,Concat(GiamGia,'%') as GiamGia, \n" +
"CONCAT(FORMAT((((DATEDIFF(DAY, NgayDuKienThue, NgayDuKienTra)) *DonGia) * 0.1), 'N0'),' VND') as VAT , CONCAT(FORMAT((((DATEDIFF(DAY, NgayDuKienThue, NgayDuKienTra)) *DonGia)  +  (((DATEDIFF(DAY, NgayDuKienThue, NgayDuKienTra)) *DonGia) * 0.1) - (((DATEDIFF(DAY, NgayDuKienThue, NgayDuKienTra)) *DonGia) *GiamGia /100)), 'N0'),' VND') as GiaHD, NgayLapHoaDon\n" +
"from HoaDonPhong hdp join PhieuDatPhong pdp on hdp.MaPhieuDatPhong = pdp.MaPhieuDatPhong join phong p on p.MaPhong = pdp.MaPhong\n" +
"join DonGiaPhong dgp on dgp.MaDonGiaPhong = p.MaDonGiaPhong join LOAIPHONG lp on lp.MaLoaiPhong = p.MaLoaiPhong\n" +
"where hdp.isvisible = '1' and MaHoaDonPhong = '"+MaPhieuTraPhong+"' and NgayDuKienThue = '"+ngaythue+"' and NgayDuKienTra = '"+ngaytra+"'  ";
            try (PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);
                ResultSet resultSet = preparedStatement.executeQuery()) {

                // Lặp qua kết quả và thêm vào model
                while (resultSet.next()) {
                    Object[] rowData = {
                            resultSet.getObject("MaHoaDonPhong"),
                            resultSet.getObject("NgayDuKienThue"),
                            resultSet.getObject("NgayDuKienTra"),
                            resultSet.getObject("TenPhong"),
                            resultSet.getObject("TenLoaiPhong"),
                            resultSet.getObject("DonGia"),
                            resultSet.getObject("GiamGia"),
                            resultSet.getObject("VAT"),
                            resultSet.getObject("GiaHD"),
                            resultSet.getObject("NgayLapHoaDon"),
                            
                    };
                    tableModel.addRow(rowData);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error loading data from SQL: " + e.getMessage());
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    
    ////////////////////////////////////// mã ngày lập hóa đơn
    
    private void MaLapHoaDon(){
        // Khởi tạo model và đặt tên cột
        tableModel = new DefaultTableModel();
        tableModel.addColumn("Mã phiếu trả");
        tableModel.addColumn("Ngày thuê phòng");
        tableModel.addColumn("Ngày trả phòng");
        tableModel.addColumn("Tên phòng");
        tableModel.addColumn("Loại phòng");
        tableModel.addColumn("Giá phòng");
        tableModel.addColumn("Giảm giá");
        tableModel.addColumn("VAT");
        tableModel.addColumn("Giá hóa đơn");
        tableModel.addColumn("Ngày lập hóa đơn");

        table.setModel(tableModel);
        

        loadDataFromSQL_MaLapHoaDon();
    }
    
    private void loadDataFromSQL_MaLapHoaDon() {
        String MaPhieuTraPhong = txtMaphieutraphong.getText();
        Connection connection = null;
        try {
            
            Date selectedDate = jDateChooserngaylaphd.getDate();
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(selectedDate);

            int year = calendar.get(Calendar.YEAR);
            int month = calendar.get(Calendar.MONTH) + 1; // Tháng bắt đầu từ 0, nên cộng thêm 1 để có giá trị tháng thực
            int day = calendar.get(Calendar.DAY_OF_MONTH);
            String ngaylaphd = year + "-" + month + "-" + day;

            
             // Kết nối đến cơ sở dữ liệu SQL Server

            String server = "localhost";
            String port = "1433";
            String database = "KLTN";
            String username = "sa";
            String password = "sa";

            String jdbcUrl = "jdbc:sqlserver://" + server + ":" + port + ";databaseName=" + database + ";user=" + username + ";password=" + password;
            connection = DriverManager.getConnection(jdbcUrl);
            
            // Thực hiện truy vấn SQL để lấy dữ liệu từ bảng
            String sqlQuery = "select MaHoaDonPhong,  NgayDuKienThue, NgayDuKienTra, TenPhong, TenLoaiPhong, CONCAT(FORMAT(DonGia, 'N0'),' VND') as DonGia,Concat(GiamGia,'%') as GiamGia, \n" +
"CONCAT(FORMAT((((DATEDIFF(DAY, NgayDuKienThue, NgayDuKienTra)) *DonGia) * 0.1), 'N0'),' VND') as VAT , CONCAT(FORMAT((((DATEDIFF(DAY, NgayDuKienThue, NgayDuKienTra)) *DonGia)  +  (((DATEDIFF(DAY, NgayDuKienThue, NgayDuKienTra)) *DonGia) * 0.1) - (((DATEDIFF(DAY, NgayDuKienThue, NgayDuKienTra)) *DonGia) *GiamGia /100)), 'N0'),' VND') as GiaHD, NgayLapHoaDon\n" +
"from HoaDonPhong hdp join PhieuDatPhong pdp on hdp.MaPhieuDatPhong = pdp.MaPhieuDatPhong join phong p on p.MaPhong = pdp.MaPhong\n" +
"join DonGiaPhong dgp on dgp.MaDonGiaPhong = p.MaDonGiaPhong join LOAIPHONG lp on lp.MaLoaiPhong = p.MaLoaiPhong\n" +
"where hdp.isvisible = '1' and MaHoaDonPhong = '"+MaPhieuTraPhong+"' and NgayLapHoaDon = '"+ngaylaphd+"'  ";
            try (PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);
                ResultSet resultSet = preparedStatement.executeQuery()) {

                // Lặp qua kết quả và thêm vào model
                while (resultSet.next()) {
                    Object[] rowData = {
                            resultSet.getObject("MaHoaDonPhong"),
                            resultSet.getObject("NgayDuKienThue"),
                            resultSet.getObject("NgayDuKienTra"),
                            resultSet.getObject("TenPhong"),
                            resultSet.getObject("TenLoaiPhong"),
                            resultSet.getObject("DonGia"),
                            resultSet.getObject("GiamGia"),
                            resultSet.getObject("VAT"),
                            resultSet.getObject("GiaHD"),
                            resultSet.getObject("NgayLapHoaDon"),
                            
                    };
                    tableModel.addRow(rowData);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error loading data from SQL: " + e.getMessage());
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    

    ////////////////////////////////////// all
    
    private void all(){
        // Khởi tạo model và đặt tên cột
        tableModel = new DefaultTableModel();
        tableModel.addColumn("Mã phiếu trả");
        tableModel.addColumn("Ngày thuê phòng");
        tableModel.addColumn("Ngày trả phòng");
        tableModel.addColumn("Tên phòng");
        tableModel.addColumn("Loại phòng");
        tableModel.addColumn("Giá phòng");
        tableModel.addColumn("Giảm giá");
        tableModel.addColumn("VAT");
        tableModel.addColumn("Giá hóa đơn");
        tableModel.addColumn("Ngày lập hóa đơn");

        table.setModel(tableModel);
        

        loadDataFromSQL_all();
    }
    
    private void loadDataFromSQL_all() {
        String MaPhieuTraPhong = txtMaphieutraphong.getText();
        Connection connection = null;
        try {
            
            Date selectedDate = jDateChooserngaydkthue.getDate();
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(selectedDate);

            int year = calendar.get(Calendar.YEAR);
            int month = calendar.get(Calendar.MONTH) + 1; // Tháng bắt đầu từ 0, nên cộng thêm 1 để có giá trị tháng thực
            int day = calendar.get(Calendar.DAY_OF_MONTH);
            String ngaythue = year + "-" + month + "-" + day;


            Date selectedDate1 = jDateChooserngaydukientra.getDate();
            Calendar calendar1 = Calendar.getInstance();
            calendar1.setTime(selectedDate1);

            int year1 = calendar1.get(Calendar.YEAR);
            int month1 = calendar1.get(Calendar.MONTH) + 1; // Tháng bắt đầu từ 0, nên cộng thêm 1 để có giá trị tháng thực
            int day1 = calendar1.get(Calendar.DAY_OF_MONTH);
            String ngaytra = year1 + "-" + month1 + "-" + day1;
            
            
            
            Date selectedDate3 = jDateChooserngaylaphd.getDate();
            Calendar calendar3 = Calendar.getInstance();
            calendar3.setTime(selectedDate3);

            int year3 = calendar3.get(Calendar.YEAR);
            int month3 = calendar3.get(Calendar.MONTH) + 1; // Tháng bắt đầu từ 0, nên cộng thêm 1 để có giá trị tháng thực
            int day3 = calendar3.get(Calendar.DAY_OF_MONTH);
            String ngaylaphd = year3 + "-" + month3 + "-" + day3;

            
             // Kết nối đến cơ sở dữ liệu SQL Server

            String server = "localhost";
            String port = "1433";
            String database = "KLTN";
            String username = "sa";
            String password = "sa";

            String jdbcUrl = "jdbc:sqlserver://" + server + ":" + port + ";databaseName=" + database + ";user=" + username + ";password=" + password;
            connection = DriverManager.getConnection(jdbcUrl);
            
            // Thực hiện truy vấn SQL để lấy dữ liệu từ bảng
            String sqlQuery = "select MaHoaDonPhong,  NgayDuKienThue, NgayDuKienTra, TenPhong, TenLoaiPhong, CONCAT(FORMAT(DonGia, 'N0'),' VND') as DonGia,Concat(GiamGia,'%') as GiamGia, \n" +
"CONCAT(FORMAT((((DATEDIFF(DAY, NgayDuKienThue, NgayDuKienTra)) *DonGia) * 0.1), 'N0'),' VND') as VAT , CONCAT(FORMAT((((DATEDIFF(DAY, NgayDuKienThue, NgayDuKienTra)) *DonGia)  +  (((DATEDIFF(DAY, NgayDuKienThue, NgayDuKienTra)) *DonGia) * 0.1) - (((DATEDIFF(DAY, NgayDuKienThue, NgayDuKienTra)) *DonGia) *GiamGia /100)), 'N0'),' VND') as GiaHD, NgayLapHoaDon\n" +
"from HoaDonPhong hdp join PhieuDatPhong pdp on hdp.MaPhieuDatPhong = pdp.MaPhieuDatPhong join phong p on p.MaPhong = pdp.MaPhong\n" +
"join DonGiaPhong dgp on dgp.MaDonGiaPhong = p.MaDonGiaPhong join LOAIPHONG lp on lp.MaLoaiPhong = p.MaLoaiPhong\n" +
"where hdp.isvisible = '1' and MaHoaDonPhong = '"+MaPhieuTraPhong+"' and NgayLapHoaDon = '"+ngaylaphd+"'  and NgayDuKienThue = '"+ngaythue+"' and NgayDuKienTra = '"+ngaytra+"'  ";
            try (PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);
                ResultSet resultSet = preparedStatement.executeQuery()) {

                // Lặp qua kết quả và thêm vào model
                while (resultSet.next()) {
                    Object[] rowData = {
                            resultSet.getObject("MaHoaDonPhong"),
                            resultSet.getObject("NgayDuKienThue"),
                            resultSet.getObject("NgayDuKienTra"),
                            resultSet.getObject("TenPhong"),
                            resultSet.getObject("TenLoaiPhong"),
                            resultSet.getObject("DonGia"),
                            resultSet.getObject("GiamGia"),
                            resultSet.getObject("VAT"),
                            resultSet.getObject("GiaHD"),
                            resultSet.getObject("NgayLapHoaDon"),
                            
                    };
                    tableModel.addRow(rowData);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error loading data from SQL: " + e.getMessage());
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    
    ////////////////////////////////////// lập thuê trả
    
    private void lapthuetra(){
        // Khởi tạo model và đặt tên cột
        tableModel = new DefaultTableModel();
        tableModel.addColumn("Mã phiếu trả");
        tableModel.addColumn("Ngày thuê phòng");
        tableModel.addColumn("Ngày trả phòng");
        tableModel.addColumn("Tên phòng");
        tableModel.addColumn("Loại phòng");
        tableModel.addColumn("Giá phòng");
        tableModel.addColumn("Giảm giá");
        tableModel.addColumn("VAT");
        tableModel.addColumn("Giá hóa đơn");
        tableModel.addColumn("Ngày lập hóa đơn");

        table.setModel(tableModel);
        

        loadDataFromSQL_lapthuetra();
    }
    
    private void loadDataFromSQL_lapthuetra() {
        String MaPhieuTraPhong = txtMaphieutraphong.getText();
        Connection connection = null;
        try {
            
            Date selectedDate = jDateChooserngaydkthue.getDate();
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(selectedDate);

            int year = calendar.get(Calendar.YEAR);
            int month = calendar.get(Calendar.MONTH) + 1; // Tháng bắt đầu từ 0, nên cộng thêm 1 để có giá trị tháng thực
            int day = calendar.get(Calendar.DAY_OF_MONTH);
            String ngaythue = year + "-" + month + "-" + day;


            Date selectedDate1 = jDateChooserngaydukientra.getDate();
            Calendar calendar1 = Calendar.getInstance();
            calendar1.setTime(selectedDate1);

            int year1 = calendar1.get(Calendar.YEAR);
            int month1 = calendar1.get(Calendar.MONTH) + 1; // Tháng bắt đầu từ 0, nên cộng thêm 1 để có giá trị tháng thực
            int day1 = calendar1.get(Calendar.DAY_OF_MONTH);
            String ngaytra = year1 + "-" + month1 + "-" + day1;
            
            
            
            Date selectedDate3 = jDateChooserngaylaphd.getDate();
            Calendar calendar3 = Calendar.getInstance();
            calendar3.setTime(selectedDate3);

            int year3 = calendar3.get(Calendar.YEAR);
            int month3 = calendar3.get(Calendar.MONTH) + 1; // Tháng bắt đầu từ 0, nên cộng thêm 1 để có giá trị tháng thực
            int day3 = calendar3.get(Calendar.DAY_OF_MONTH);
            String ngaylaphd = year3 + "-" + month3 + "-" + day3;

            
             // Kết nối đến cơ sở dữ liệu SQL Server

            String server = "localhost";
            String port = "1433";
            String database = "KLTN";
            String username = "sa";
            String password = "sa";

            String jdbcUrl = "jdbc:sqlserver://" + server + ":" + port + ";databaseName=" + database + ";user=" + username + ";password=" + password;
            connection = DriverManager.getConnection(jdbcUrl);
            
            // Thực hiện truy vấn SQL để lấy dữ liệu từ bảng
            String sqlQuery = "select MaHoaDonPhong,  NgayDuKienThue, NgayDuKienTra, TenPhong, TenLoaiPhong, CONCAT(FORMAT(DonGia, 'N0'),' VND') as DonGia,Concat(GiamGia,'%') as GiamGia, \n" +
"CONCAT(FORMAT((((DATEDIFF(DAY, NgayDuKienThue, NgayDuKienTra)) *DonGia) * 0.1), 'N0'),' VND') as VAT , CONCAT(FORMAT((((DATEDIFF(DAY, NgayDuKienThue, NgayDuKienTra)) *DonGia)  +  (((DATEDIFF(DAY, NgayDuKienThue, NgayDuKienTra)) *DonGia) * 0.1) - (((DATEDIFF(DAY, NgayDuKienThue, NgayDuKienTra)) *DonGia) *GiamGia /100)), 'N0'),' VND') as GiaHD, NgayLapHoaDon\n" +
"from HoaDonPhong hdp join PhieuDatPhong pdp on hdp.MaPhieuDatPhong = pdp.MaPhieuDatPhong join phong p on p.MaPhong = pdp.MaPhong\n" +
"join DonGiaPhong dgp on dgp.MaDonGiaPhong = p.MaDonGiaPhong join LOAIPHONG lp on lp.MaLoaiPhong = p.MaLoaiPhong\n" +
"where hdp.isvisible = '1' and NgayLapHoaDon = '"+ngaylaphd+"'  and NgayDuKienThue = '"+ngaythue+"' and NgayDuKienTra = '"+ngaytra+"'  ";
            try (PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);
                ResultSet resultSet = preparedStatement.executeQuery()) {

                // Lặp qua kết quả và thêm vào model
                while (resultSet.next()) {
                    Object[] rowData = {
                            resultSet.getObject("MaHoaDonPhong"),
                            resultSet.getObject("NgayDuKienThue"),
                            resultSet.getObject("NgayDuKienTra"),
                            resultSet.getObject("TenPhong"),
                            resultSet.getObject("TenLoaiPhong"),
                            resultSet.getObject("DonGia"),
                            resultSet.getObject("GiamGia"),
                            resultSet.getObject("VAT"),
                            resultSet.getObject("GiaHD"),
                            resultSet.getObject("NgayLapHoaDon"),
                            
                    };
                    tableModel.addRow(rowData);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error loading data from SQL: " + e.getMessage());
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        roundPanel5 = new swing.RoundPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        table = new javax.swing.JTable();
        panelBorder5 = new swing.PanelBorder();
        btnLoc = new swing.Button();
        ckMaPhieuTraPhong = new javax.swing.JCheckBox();
        ckNgayLapHD = new javax.swing.JCheckBox();
        ckNgaythuetra = new javax.swing.JCheckBox();
        txtMaphieutraphong = new component.TextField();
        jLabel16 = new javax.swing.JLabel();
        jDateChooserngaydkthue = new com.toedter.calendar.JDateChooser();
        jLabel17 = new javax.swing.JLabel();
        jDateChooserngaydukientra = new com.toedter.calendar.JDateChooser();
        btnView = new swing.Button();
        jDateChooserngaylaphd = new com.toedter.calendar.JDateChooser();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        roundPanel5.setBackground(new java.awt.Color(36, 87, 157));
        roundPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Trang tìm kiếm phiếu trả phòng", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 24), new java.awt.Color(255, 255, 255))); // NOI18N

        table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        table.setRowHeight(40);
        table.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tableMouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                tableMousePressed(evt);
            }
        });
        jScrollPane1.setViewportView(table);

        panelBorder5.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Trang tìm kiếm phiếu trả phòng", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 20))); // NOI18N

        btnLoc.setText("Lọc");
        btnLoc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLocActionPerformed(evt);
            }
        });

        ckMaPhieuTraPhong.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        ckMaPhieuTraPhong.setText("Mã phiếu trả phòng:");

        ckNgayLapHD.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        ckNgayLapHD.setText("Ngày lập hóa đơn:");

        ckNgaythuetra.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N

        txtMaphieutraphong.setLabelText("Mã phiếu trả phòng");

        jLabel16.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        jLabel16.setText("Ngày thuê phòng:");

        jDateChooserngaydkthue.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                jDateChooserngaydkthuePropertyChange(evt);
            }
        });

        jLabel17.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        jLabel17.setText("Ngày trả phòng:");

        jDateChooserngaydukientra.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                jDateChooserngaydukientraPropertyChange(evt);
            }
        });

        btnView.setText("Xem");
        btnView.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnViewActionPerformed(evt);
            }
        });

        jDateChooserngaylaphd.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                jDateChooserngaylaphdPropertyChange(evt);
            }
        });

        javax.swing.GroupLayout panelBorder5Layout = new javax.swing.GroupLayout(panelBorder5);
        panelBorder5.setLayout(panelBorder5Layout);
        panelBorder5Layout.setHorizontalGroup(
            panelBorder5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBorder5Layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addGroup(panelBorder5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(ckMaPhieuTraPhong)
                    .addGroup(panelBorder5Layout.createSequentialGroup()
                        .addComponent(ckNgaythuetra)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel16)
                        .addGap(36, 36, 36)
                        .addGroup(panelBorder5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jDateChooserngaydkthue, javax.swing.GroupLayout.DEFAULT_SIZE, 337, Short.MAX_VALUE)
                            .addComponent(jDateChooserngaydukientra, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtMaphieutraphong, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(panelBorder5Layout.createSequentialGroup()
                        .addGap(21, 21, 21)
                        .addComponent(jLabel17)))
                .addGroup(panelBorder5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelBorder5Layout.createSequentialGroup()
                        .addGap(97, 97, 97)
                        .addComponent(btnLoc, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(52, 52, 52)
                        .addComponent(btnView, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelBorder5Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(ckNgayLapHD)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jDateChooserngaylaphd, javax.swing.GroupLayout.PREFERRED_SIZE, 336, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(45, 45, 45))))
        );
        panelBorder5Layout.setVerticalGroup(
            panelBorder5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBorder5Layout.createSequentialGroup()
                .addGap(39, 39, 39)
                .addGroup(panelBorder5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(ckMaPhieuTraPhong)
                    .addComponent(txtMaphieutraphong, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ckNgayLapHD)
                    .addComponent(jDateChooserngaylaphd, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(35, 35, 35)
                .addGroup(panelBorder5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(panelBorder5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelBorder5Layout.createSequentialGroup()
                            .addComponent(jDateChooserngaydkthue, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(18, 18, 18)
                            .addComponent(jDateChooserngaydukientra, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(panelBorder5Layout.createSequentialGroup()
                            .addGroup(panelBorder5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(ckNgaythuetra)
                                .addComponent(jLabel16))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(jLabel17)))
                    .addGroup(panelBorder5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnLoc, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnView, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(62, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout roundPanel5Layout = new javax.swing.GroupLayout(roundPanel5);
        roundPanel5.setLayout(roundPanel5Layout);
        roundPanel5Layout.setHorizontalGroup(
            roundPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(roundPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(roundPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 1176, Short.MAX_VALUE)
                    .addComponent(panelBorder5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        roundPanel5Layout.setVerticalGroup(
            roundPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(roundPanel5Layout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addComponent(panelBorder5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(44, 44, 44)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 389, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(roundPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(roundPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void tableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableMouseClicked
        // TODO add your handling code here:

    }//GEN-LAST:event_tableMouseClicked

    private void tableMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableMousePressed
        // TODO add your handling code here:
    }//GEN-LAST:event_tableMousePressed

    private void btnLocActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLocActionPerformed
        // TODO add your handling code here:
        if (ckMaPhieuTraPhong.isSelected() == true && ckNgaythuetra.isSelected() == false && ckNgayLapHD.isSelected() == false){
            System.out.println("mã phiếu trả phòng");
            Ma();

        }else if(ckMaPhieuTraPhong.isSelected() == false && ckNgaythuetra.isSelected() == true && ckNgayLapHD.isSelected() == false){
            System.out.println("ngàythuetra");
            ngaythuetra();
            
        }else if (ckMaPhieuTraPhong.isSelected() == false && ckNgaythuetra.isSelected() == false && ckNgayLapHD.isSelected() == true){
            System.out.println("ngaylaphd");
            ngaylaphd();
        }else if (ckMaPhieuTraPhong.isSelected() == true && ckNgaythuetra.isSelected() == true && ckNgayLapHD.isSelected() == false){
            System.out.println("mã thuetra");
            MaThueTra();
        }else if (ckMaPhieuTraPhong.isSelected() == true && ckNgaythuetra.isSelected() == false && ckNgayLapHD.isSelected() == true){
            System.out.println("mã lap");
            MaLapHoaDon();
        }else if (ckMaPhieuTraPhong.isSelected() == true && ckNgaythuetra.isSelected() == true && ckNgayLapHD.isSelected() == true){
            System.out.println("all true");
            all();
        }else if (ckMaPhieuTraPhong.isSelected() == false && ckNgaythuetra.isSelected() == true && ckNgayLapHD.isSelected() == true){
            System.out.println("thuetralaphd");
            lapthuetra();
        }
        else if (ckMaPhieuTraPhong.isSelected() == false && ckNgaythuetra.isSelected() == false && ckNgayLapHD.isSelected() == false){
            System.out.println("chọn lọc");
            JOptionPane.showMessageDialog(this, "Vui lòng chọn phương thức lọc");
        }
    }//GEN-LAST:event_btnLocActionPerformed

    private void jDateChooserngaydkthuePropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_jDateChooserngaydkthuePropertyChange
        // TODO add your handling code here:
    }//GEN-LAST:event_jDateChooserngaydkthuePropertyChange

    private void jDateChooserngaydukientraPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_jDateChooserngaydukientraPropertyChange
        // TODO add your handling code here:
    }//GEN-LAST:event_jDateChooserngaydukientraPropertyChange

    private void btnViewActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnViewActionPerformed
        // TODO add your handling code here:
        Checkout();
    }//GEN-LAST:event_btnViewActionPerformed

    private void jDateChooserngaylaphdPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_jDateChooserngaylaphdPropertyChange
        // TODO add your handling code here:
    }//GEN-LAST:event_jDateChooserngaylaphdPropertyChange

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(FormFindCheckOut.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FormFindCheckOut.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FormFindCheckOut.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FormFindCheckOut.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FormFindCheckOut().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private swing.Button btnLoc;
    private swing.Button btnView;
    private javax.swing.JCheckBox ckMaPhieuTraPhong;
    private javax.swing.JCheckBox ckNgayLapHD;
    private javax.swing.JCheckBox ckNgaythuetra;
    private com.toedter.calendar.JDateChooser jDateChooserngaydkthue;
    private com.toedter.calendar.JDateChooser jDateChooserngaydukientra;
    private com.toedter.calendar.JDateChooser jDateChooserngaylaphd;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JScrollPane jScrollPane1;
    private swing.PanelBorder panelBorder5;
    private swing.RoundPanel roundPanel5;
    private javax.swing.JTable table;
    private component.TextField txtMaphieutraphong;
    // End of variables declaration//GEN-END:variables
}
