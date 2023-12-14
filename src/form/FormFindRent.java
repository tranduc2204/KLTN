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
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author TeeDee
 */
public class FormFindRent extends javax.swing.JFrame {

    /**
     * Creates new form FormFindRent
     */
    public FormFindRent() {
        initComponents();
    }
    
    Connect cn = new Connect();
    Connection conn;
    private DefaultTableModel tableModel;

    private String username, password, quyen, DisplayName, MaNV ;
    public FormFindRent(String username, String password, String DisplayName, String quyen) {
        initComponents();
        this.username = username;
        this.password = password;
        this.DisplayName = DisplayName;
        this.quyen = quyen;
        this.MaNV = MaNV;
        
        findrent();
    }
    
    private void findrent(){
        // Khởi tạo model và đặt tên cột
        tableModel = new DefaultTableModel();
        tableModel.addColumn("Mã phiếu đặt phòng");
        tableModel.addColumn("Họ tên khách hàng");
        tableModel.addColumn("Mã phòng");
        tableModel.addColumn("Tên phòng");
        
         tableModel.addColumn("Tên loại phòng");
        tableModel.addColumn("Ngày đặt phòng");
        tableModel.addColumn("Ngày thuê phòng");
        tableModel.addColumn("Ngày trả phòng");
        tableModel.addColumn("Đơn giá");
        tableModel.addColumn("Tình trạng");

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
            String sqlQuery = "select MaPhieuDatPhong, kh.MaKH, HoKH +' '+TenKH as hotenkh, nv.MaNV, HoNV,TenNV, p.MaPhong, TenPhong, lp.TenLoaiPhong, \n" +
"NgayDatPhong,NgayDuKienThue, NgayDuKienTra, DonGia , case when booked_status = '1' then N'Đã nhận phòng' when booked_status = '0' then N'Đẵ đặt phòng' end as TinhTrang\n" +
"from PhieuDatPhong pdp join PHONG p on pdp.MaPhong = p.MaPhong \n" +
"join KHACHHANG kh on kh.MaKH =pdp.MaKH join NHANVIEN nv on pdp.MaNV = nv.MaNV \n" +
"join LOAIPHONG lp on lp.MaLoaiPhong = p.MaLoaiPhong\n" +
"join DonGiaPhong dgp on dgp.MaDonGiaPhong = p.MaDonGiaPhong\n" +
"where pdp.isvisible = '1'  ";//and booked_status = 0 
            try (PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);
                ResultSet resultSet = preparedStatement.executeQuery()) {

                // Lặp qua kết quả và thêm vào model
                while (resultSet.next()) {
                    Object[] rowData = {
                            resultSet.getObject("MaPhieuDatPhong"),
                            resultSet.getObject("hotenkh"),
                            resultSet.getObject("MaPhong"),
                            resultSet.getObject("TenPhong"),
                            resultSet.getObject("TenLoaiPhong"),
                            resultSet.getObject("NgayDatPhong"),
                            resultSet.getObject("NgayDuKienThue"),
                            resultSet.getObject("NgayDuKienTra"),
                            resultSet.getObject("DonGia"),
                            resultSet.getObject("TinhTrang"),
                            
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
     
    
    //////////////////////////// mã
    private void ma(){
        // Khởi tạo model và đặt tên cột
        tableModel = new DefaultTableModel();
        tableModel.addColumn("Mã phiếu đặt phòng");
        tableModel.addColumn("Họ tên khách hàng");
        tableModel.addColumn("Mã phòng");
        tableModel.addColumn("Tên phòng");
        
         tableModel.addColumn("Tên loại phòng");
        tableModel.addColumn("Ngày đặt phòng");
        tableModel.addColumn("Ngày thuê phòng");
        tableModel.addColumn("Ngày trả phòng");
        tableModel.addColumn("Đơn giá");
        tableModel.addColumn("Tình trạng");

        table.setModel(tableModel);
        

        loadDataFromSQL_ma();
    }
     
    private void loadDataFromSQL_ma() {
        String maphong = txtMaphong.getText();
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
            String sqlQuery = "select MaPhieuDatPhong, kh.MaKH, HoKH +' '+TenKH as hotenkh, nv.MaNV, HoNV,TenNV, p.MaPhong, TenPhong, lp.TenLoaiPhong, \n" +
"NgayDatPhong,NgayDuKienThue, NgayDuKienTra, DonGia , case when booked_status = '1' then N'Đã nhận phòng' when booked_status = '0' then N'Đẵ đặt phòng' end as TinhTrang\n" +
"from PhieuDatPhong pdp join PHONG p on pdp.MaPhong = p.MaPhong \n" +
"join KHACHHANG kh on kh.MaKH =pdp.MaKH join NHANVIEN nv on pdp.MaNV = nv.MaNV \n" +
"join LOAIPHONG lp on lp.MaLoaiPhong = p.MaLoaiPhong\n" +
"join DonGiaPhong dgp on dgp.MaDonGiaPhong = p.MaDonGiaPhong\n" +
"where pdp.isvisible = '1' and p.MaPhong = '"+maphong+"'   ";
            try (PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);
                ResultSet resultSet = preparedStatement.executeQuery()) {

                // Lặp qua kết quả và thêm vào model
                while (resultSet.next()) {
                    Object[] rowData = {
                            resultSet.getObject("MaPhieuDatPhong"),
                            resultSet.getObject("hotenkh"),
                            resultSet.getObject("MaPhong"),
                            resultSet.getObject("TenPhong"),
                            resultSet.getObject("TenLoaiPhong"),
                            resultSet.getObject("NgayDatPhong"),
                            resultSet.getObject("NgayDuKienThue"),
                            resultSet.getObject("NgayDuKienTra"),
                            resultSet.getObject("DonGia"),
                            resultSet.getObject("TinhTrang"),
                            
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
    //////////////////////////// ngay
    private void ngay(){
        // Khởi tạo model và đặt tên cột
        tableModel = new DefaultTableModel();
        tableModel.addColumn("Mã phiếu đặt phòng");
        tableModel.addColumn("Họ tên khách hàng");
        tableModel.addColumn("Mã phòng");
        tableModel.addColumn("Tên phòng");
        
         tableModel.addColumn("Tên loại phòng");
        tableModel.addColumn("Ngày đặt phòng");
        tableModel.addColumn("Ngày thuê phòng");
        tableModel.addColumn("Ngày trả phòng");
        tableModel.addColumn("Đơn giá");
        tableModel.addColumn("Tình trạng");

        table.setModel(tableModel);
        

        loadDataFromSQL_ngay();
    }
     
    private void loadDataFromSQL_ngay() {
        
        
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
        
        
        String maphong = txtMaphong.getText();
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
            String sqlQuery = "select MaPhieuDatPhong, kh.MaKH, HoKH +' '+TenKH as hotenkh, nv.MaNV, HoNV,TenNV, p.MaPhong, TenPhong, lp.TenLoaiPhong, \n" +
"NgayDatPhong,NgayDuKienThue, NgayDuKienTra, DonGia , case when booked_status = '1' then N'Đã nhận phòng' when booked_status = '0' then N'Đẵ đặt phòng' end as TinhTrang\n" +
"from PhieuDatPhong pdp join PHONG p on pdp.MaPhong = p.MaPhong \n" +
"join KHACHHANG kh on kh.MaKH =pdp.MaKH join NHANVIEN nv on pdp.MaNV = nv.MaNV \n" +
"join LOAIPHONG lp on lp.MaLoaiPhong = p.MaLoaiPhong\n" +
"join DonGiaPhong dgp on dgp.MaDonGiaPhong = p.MaDonGiaPhong\n" +
"where pdp.isvisible = '1' and NgayDuKienThue = '"+ngaythue+"' and NgayDuKienTra = '"+ngaytra+"' ";
            try (PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);
                ResultSet resultSet = preparedStatement.executeQuery()) {

                // Lặp qua kết quả và thêm vào model
                while (resultSet.next()) {
                    Object[] rowData = {
                            resultSet.getObject("MaPhieuDatPhong"),
                            resultSet.getObject("hotenkh"),
                            resultSet.getObject("MaPhong"),
                            resultSet.getObject("TenPhong"),
                            resultSet.getObject("TenLoaiPhong"),
                            resultSet.getObject("NgayDatPhong"),
                            resultSet.getObject("NgayDuKienThue"),
                            resultSet.getObject("NgayDuKienTra"),
                            resultSet.getObject("DonGia"),
                           resultSet.getObject("TinhTrang"),
                            
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
    
    //////////////////////////// hoten
    private void hoten(){
        // Khởi tạo model và đặt tên cột
        tableModel = new DefaultTableModel();
        tableModel.addColumn("Mã phiếu đặt phòng");
        tableModel.addColumn("Họ tên khách hàng");
        tableModel.addColumn("Mã phòng");
        tableModel.addColumn("Tên phòng");
        
         tableModel.addColumn("Tên loại phòng");
        tableModel.addColumn("Ngày đặt phòng");
        tableModel.addColumn("Ngày thuê phòng");
        tableModel.addColumn("Ngày trả phòng");
        tableModel.addColumn("Đơn giá");
        tableModel.addColumn("Tình trạng");

        table.setModel(tableModel);
        

        loadDataFromSQL_hoten();
    }
     
    private void loadDataFromSQL_hoten() {
        
        String hoten = txtHotenKH.getText();
        

        String maphong = txtMaphong.getText();
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
            String sqlQuery = "select MaPhieuDatPhong, kh.MaKH, HoKH +' '+TenKH as hotenkh, nv.MaNV, HoNV,TenNV, p.MaPhong, TenPhong, lp.TenLoaiPhong, \n" +
"NgayDatPhong,NgayDuKienThue, NgayDuKienTra, DonGia , case when booked_status = '1' then N'Đã nhận phòng' when booked_status = '0' then N'Đẵ đặt phòng' end as TinhTrang\n" +
"from PhieuDatPhong pdp join PHONG p on pdp.MaPhong = p.MaPhong \n" +
"join KHACHHANG kh on kh.MaKH =pdp.MaKH join NHANVIEN nv on pdp.MaNV = nv.MaNV \n" +
"join LOAIPHONG lp on lp.MaLoaiPhong = p.MaLoaiPhong\n" +
"join DonGiaPhong dgp on dgp.MaDonGiaPhong = p.MaDonGiaPhong\n" +
"where pdp.isvisible = '1' and HoKH +' ' +TenKH like N'%"+hoten+"%' ";
            try (PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);
                ResultSet resultSet = preparedStatement.executeQuery()) {

                // Lặp qua kết quả và thêm vào model
                while (resultSet.next()) {
                    Object[] rowData = {
                            resultSet.getObject("MaPhieuDatPhong"),
                            resultSet.getObject("hotenkh"),
                            resultSet.getObject("MaPhong"),
                            resultSet.getObject("TenPhong"),
                            resultSet.getObject("TenLoaiPhong"),
                            resultSet.getObject("NgayDatPhong"),
                            resultSet.getObject("NgayDuKienThue"),
                            resultSet.getObject("NgayDuKienTra"),
                            resultSet.getObject("DonGia"),
                            resultSet.getObject("TinhTrang"),
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
    
    //////////////////////////// mã ngày ngay
    private void mangay(){
        // Khởi tạo model và đặt tên cột
        tableModel = new DefaultTableModel();
        tableModel.addColumn("Mã phiếu đặt phòng");
        tableModel.addColumn("Họ tên khách hàng");
        tableModel.addColumn("Mã phòng");
        tableModel.addColumn("Tên phòng");
        
         tableModel.addColumn("Tên loại phòng");
        tableModel.addColumn("Ngày đặt phòng");
        tableModel.addColumn("Ngày thuê phòng");
        tableModel.addColumn("Ngày trả phòng");
        tableModel.addColumn("Đơn giá");
        tableModel.addColumn("Tình trạng");

        table.setModel(tableModel);
        

        loadDataFromSQL_mangay();
    }
     
    private void loadDataFromSQL_mangay() {
        
        
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
        
        
        String maphong = txtMaphong.getText();
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
            String sqlQuery = "select MaPhieuDatPhong, kh.MaKH, HoKH +' '+TenKH as hotenkh, nv.MaNV, HoNV,TenNV, p.MaPhong, TenPhong, lp.TenLoaiPhong, \n" +
"NgayDatPhong,NgayDuKienThue, NgayDuKienTra, DonGia , case when booked_status = '1' then N'Đã nhận phòng' when booked_status = '0' then N'Đẵ đặt phòng' end as TinhTrang\n" +
"from PhieuDatPhong pdp join PHONG p on pdp.MaPhong = p.MaPhong \n" +
"join KHACHHANG kh on kh.MaKH =pdp.MaKH join NHANVIEN nv on pdp.MaNV = nv.MaNV \n" +
"join LOAIPHONG lp on lp.MaLoaiPhong = p.MaLoaiPhong\n" +
"join DonGiaPhong dgp on dgp.MaDonGiaPhong = p.MaDonGiaPhong\n" +
"where pdp.isvisible = '1' and NgayDuKienThue = '"+ngaythue+"' and NgayDuKienTra = '"+ngaytra+"' and p.MaPhong = '"+maphong+"'   ";
            try (PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);
                ResultSet resultSet = preparedStatement.executeQuery()) {

                // Lặp qua kết quả và thêm vào model
                while (resultSet.next()) {
                    Object[] rowData = {
                            resultSet.getObject("MaPhieuDatPhong"),
                            resultSet.getObject("hotenkh"),
                            resultSet.getObject("MaPhong"),
                            resultSet.getObject("TenPhong"),
                            resultSet.getObject("TenLoaiPhong"),
                            resultSet.getObject("NgayDatPhong"),
                            resultSet.getObject("NgayDuKienThue"),
                            resultSet.getObject("NgayDuKienTra"),
                            resultSet.getObject("DonGia"),
                           resultSet.getObject("TinhTrang"),
                            
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
    
    //////////////////////////// mã hoten
    private void mahoten(){
        // Khởi tạo model và đặt tên cột
        tableModel = new DefaultTableModel();
        tableModel.addColumn("Mã phiếu đặt phòng");
        tableModel.addColumn("Họ tên khách hàng");
        tableModel.addColumn("Mã phòng");
        tableModel.addColumn("Tên phòng");
        
         tableModel.addColumn("Tên loại phòng");
        tableModel.addColumn("Ngày đặt phòng");
        tableModel.addColumn("Ngày thuê phòng");
        tableModel.addColumn("Ngày trả phòng");
        tableModel.addColumn("Đơn giá");
        tableModel.addColumn("Tình trạng");

        table.setModel(tableModel);
        

        loadDataFromSQL_mahoten();
    }
     
    private void loadDataFromSQL_mahoten() {
        
        String hoten = txtHotenKH.getText();
        

        String maphong = txtMaphong.getText();
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
            String sqlQuery = "select MaPhieuDatPhong, kh.MaKH, HoKH +' '+TenKH as hotenkh, nv.MaNV, HoNV,TenNV, p.MaPhong, TenPhong, lp.TenLoaiPhong, \n" +
"NgayDatPhong,NgayDuKienThue, NgayDuKienTra, DonGia , case when booked_status = '1' then N'Đã nhận phòng' when booked_status = '0' then N'Đẵ đặt phòng' end as TinhTrang\n" +
"from PhieuDatPhong pdp join PHONG p on pdp.MaPhong = p.MaPhong \n" +
"join KHACHHANG kh on kh.MaKH =pdp.MaKH join NHANVIEN nv on pdp.MaNV = nv.MaNV \n" +
"join LOAIPHONG lp on lp.MaLoaiPhong = p.MaLoaiPhong\n" +
"join DonGiaPhong dgp on dgp.MaDonGiaPhong = p.MaDonGiaPhong\n" +
"where pdp.isvisible = '1' and HoKH +' ' +TenKH like N'%"+hoten+"%' and p.MaPhong = '"+maphong+"'   ";
            try (PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);
                ResultSet resultSet = preparedStatement.executeQuery()) {

                // Lặp qua kết quả và thêm vào model
                while (resultSet.next()) {
                    Object[] rowData = {
                            resultSet.getObject("MaPhieuDatPhong"),
                            resultSet.getObject("hotenkh"),
                            resultSet.getObject("MaPhong"),
                            resultSet.getObject("TenPhong"),
                            resultSet.getObject("TenLoaiPhong"),
                            resultSet.getObject("NgayDatPhong"),
                            resultSet.getObject("NgayDuKienThue"),
                            resultSet.getObject("NgayDuKienTra"),
                            resultSet.getObject("DonGia"),
                            resultSet.getObject("TinhTrang"),
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
    
    ////////////////////////////  ngày ho ten
    private void ngayHoten(){
        // Khởi tạo model và đặt tên cột
        tableModel = new DefaultTableModel();
        tableModel.addColumn("Mã phiếu đặt phòng");
        tableModel.addColumn("Họ tên khách hàng");
        tableModel.addColumn("Mã phòng");
        tableModel.addColumn("Tên phòng");
        
         tableModel.addColumn("Tên loại phòng");
        tableModel.addColumn("Ngày đặt phòng");
        tableModel.addColumn("Ngày thuê phòng");
        tableModel.addColumn("Ngày trả phòng");
        tableModel.addColumn("Đơn giá");
        tableModel.addColumn("Tình trạng");

        table.setModel(tableModel);
        

        loadDataFromSQL_ngayHoten();
    }
     
    private void loadDataFromSQL_ngayHoten() {
        
        
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
        
        
        String hoten = txtHotenKH.getText();
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
            String sqlQuery = "select MaPhieuDatPhong, kh.MaKH, HoKH +' '+TenKH as hotenkh, nv.MaNV, HoNV,TenNV, p.MaPhong, TenPhong, lp.TenLoaiPhong, \n" +
"NgayDatPhong,NgayDuKienThue, NgayDuKienTra, DonGia , case when booked_status = '1' then N'Đã nhận phòng' when booked_status = '0' then N'Đẵ đặt phòng' end as TinhTrang\n" +
"from PhieuDatPhong pdp join PHONG p on pdp.MaPhong = p.MaPhong \n" +
"join KHACHHANG kh on kh.MaKH =pdp.MaKH join NHANVIEN nv on pdp.MaNV = nv.MaNV \n" +
"join LOAIPHONG lp on lp.MaLoaiPhong = p.MaLoaiPhong\n" +
"join DonGiaPhong dgp on dgp.MaDonGiaPhong = p.MaDonGiaPhong\n" +
"where pdp.isvisible = '1' and NgayDuKienThue = '"+ngaythue+"' and NgayDuKienTra = '"+ngaytra+"' and HoKH +' ' +TenKH like N'%"+hoten+"%'   ";
            try (PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);
                ResultSet resultSet = preparedStatement.executeQuery()) {

                // Lặp qua kết quả và thêm vào model
                while (resultSet.next()) {
                    Object[] rowData = {
                            resultSet.getObject("MaPhieuDatPhong"),
                            resultSet.getObject("hotenkh"),
                            resultSet.getObject("MaPhong"),
                            resultSet.getObject("TenPhong"),
                            resultSet.getObject("TenLoaiPhong"),
                            resultSet.getObject("NgayDatPhong"),
                            resultSet.getObject("NgayDuKienThue"),
                            resultSet.getObject("NgayDuKienTra"),
                            resultSet.getObject("DonGia"),
                            resultSet.getObject("TinhTrang"),
                            
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
    
    ////////////////////////////  all
    private void all(){
        // Khởi tạo model và đặt tên cột
        tableModel = new DefaultTableModel();
        tableModel.addColumn("Mã phiếu đặt phòng");
        tableModel.addColumn("Họ tên khách hàng");
        tableModel.addColumn("Mã phòng");
        tableModel.addColumn("Tên phòng");
        
         tableModel.addColumn("Tên loại phòng");
        tableModel.addColumn("Ngày đặt phòng");
        tableModel.addColumn("Ngày thuê phòng");
        tableModel.addColumn("Ngày trả phòng");
        tableModel.addColumn("Đơn giá");
        tableModel.addColumn("Tình trạng");

        table.setModel(tableModel);
        

        loadDataFromSQL_all();
    }
     
    private void loadDataFromSQL_all() {
        
        
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
        
        
        String hoten = txtHotenKH.getText();
        String maphong = txtMaphong.getText();
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
            String sqlQuery = "select MaPhieuDatPhong, kh.MaKH, HoKH +' '+TenKH as hotenkh, nv.MaNV, HoNV,TenNV, p.MaPhong, TenPhong, lp.TenLoaiPhong, \n" +
"NgayDatPhong,NgayDuKienThue, NgayDuKienTra, DonGia , case when booked_status = '1' then N'Đã nhận phòng' when booked_status = '0' then N'Đẵ đặt phòng' end as TinhTrang\n" +
"from PhieuDatPhong pdp join PHONG p on pdp.MaPhong = p.MaPhong \n" +
"join KHACHHANG kh on kh.MaKH =pdp.MaKH join NHANVIEN nv on pdp.MaNV = nv.MaNV \n" +
"join LOAIPHONG lp on lp.MaLoaiPhong = p.MaLoaiPhong\n" +
"join DonGiaPhong dgp on dgp.MaDonGiaPhong = p.MaDonGiaPhong\n" +
"where pdp.isvisible = '1' and NgayDuKienThue = '"+ngaythue+"' and NgayDuKienTra = '"+ngaytra+"' and HoKH +' ' +TenKH like N'%"+hoten+"%' and p.MaPhong = '"+maphong+"'    ";
            try (PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);
                ResultSet resultSet = preparedStatement.executeQuery()) {

                // Lặp qua kết quả và thêm vào model
                while (resultSet.next()) {
                    Object[] rowData = {
                            resultSet.getObject("MaPhieuDatPhong"),
                            resultSet.getObject("hotenkh"),
                            resultSet.getObject("MaPhong"),
                            resultSet.getObject("TenPhong"),
                            resultSet.getObject("TenLoaiPhong"),
                            resultSet.getObject("NgayDatPhong"),
                            resultSet.getObject("NgayDuKienThue"),
                            resultSet.getObject("NgayDuKienTra"),
                            resultSet.getObject("DonGia"),
                           resultSet.getObject("TinhTrang"),
                            
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
        ckMaPhong = new javax.swing.JCheckBox();
        ckHoTen = new javax.swing.JCheckBox();
        ckNgay = new javax.swing.JCheckBox();
        txtMaphong = new component.TextField();
        txtHotenKH = new component.TextField();
        jLabel16 = new javax.swing.JLabel();
        jDateChooserngaydkthue = new com.toedter.calendar.JDateChooser();
        jLabel17 = new javax.swing.JLabel();
        jDateChooserngaydukientra = new com.toedter.calendar.JDateChooser();
        btnView = new swing.Button();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        roundPanel5.setBackground(new java.awt.Color(36, 87, 157));
        roundPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Trang tìm kiếm phiếu đặt phòng", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 24), new java.awt.Color(255, 255, 255))); // NOI18N

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

        panelBorder5.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Trang tìm kiếm phiếu đặt phòng", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 20))); // NOI18N

        btnLoc.setText("Lọc");
        btnLoc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLocActionPerformed(evt);
            }
        });

        ckMaPhong.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        ckMaPhong.setText("Mã phòng:");

        ckHoTen.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        ckHoTen.setText("Họ tên khách hàng:");

        ckNgay.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N

        txtMaphong.setLabelText("Mã phòng");

        txtHotenKH.setLabelText("Mã phiếu đặt phòng");

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

        javax.swing.GroupLayout panelBorder5Layout = new javax.swing.GroupLayout(panelBorder5);
        panelBorder5.setLayout(panelBorder5Layout);
        panelBorder5Layout.setHorizontalGroup(
            panelBorder5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBorder5Layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addGroup(panelBorder5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(ckMaPhong)
                    .addGroup(panelBorder5Layout.createSequentialGroup()
                        .addComponent(ckNgay)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(panelBorder5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jDateChooserngaydukientra, javax.swing.GroupLayout.PREFERRED_SIZE, 268, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(panelBorder5Layout.createSequentialGroup()
                                .addGroup(panelBorder5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelBorder5Layout.createSequentialGroup()
                                        .addComponent(jLabel17)
                                        .addGap(48, 48, 48))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelBorder5Layout.createSequentialGroup()
                                        .addComponent(jLabel16)
                                        .addGap(36, 36, 36)))
                                .addGroup(panelBorder5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jDateChooserngaydkthue, javax.swing.GroupLayout.DEFAULT_SIZE, 268, Short.MAX_VALUE)
                                    .addComponent(txtMaphong, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))))
                .addGroup(panelBorder5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelBorder5Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(ckHoTen)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtHotenKH, javax.swing.GroupLayout.PREFERRED_SIZE, 268, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(41, 41, 41))
                    .addGroup(panelBorder5Layout.createSequentialGroup()
                        .addGap(189, 189, 189)
                        .addComponent(btnLoc, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(26, 26, 26)
                        .addComponent(btnView, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        panelBorder5Layout.setVerticalGroup(
            panelBorder5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBorder5Layout.createSequentialGroup()
                .addGap(39, 39, 39)
                .addGroup(panelBorder5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(ckMaPhong)
                    .addComponent(txtMaphong, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ckHoTen)
                    .addComponent(txtHotenKH, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(35, 35, 35)
                .addGroup(panelBorder5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(ckNgay)
                    .addGroup(panelBorder5Layout.createSequentialGroup()
                        .addComponent(jLabel16)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel17))
                    .addGroup(panelBorder5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(panelBorder5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnLoc, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnView, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(panelBorder5Layout.createSequentialGroup()
                            .addComponent(jDateChooserngaydkthue, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(18, 18, 18)
                            .addComponent(jDateChooserngaydukientra, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(62, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout roundPanel5Layout = new javax.swing.GroupLayout(roundPanel5);
        roundPanel5.setLayout(roundPanel5Layout);
        roundPanel5Layout.setHorizontalGroup(
            roundPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(roundPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(roundPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 1078, Short.MAX_VALUE)
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
        if (ckMaPhong.isSelected() == true && ckNgay.isSelected() == false && ckHoTen.isSelected() == false){
            System.out.println("mã phòng");
            ma();

        }else if(ckMaPhong.isSelected() == false && ckNgay.isSelected() == true && ckHoTen.isSelected() == false){
            System.out.println("ngày");
            ngay();
        }else if (ckMaPhong.isSelected() == false && ckNgay.isSelected() == false && ckHoTen.isSelected() == true){
            System.out.println("hoten");
            hoten();
        }else if (ckMaPhong.isSelected() == true && ckNgay.isSelected() == true && ckHoTen.isSelected() == false){
            System.out.println("mã ngày");
            mangay();
        }else if (ckMaPhong.isSelected() == true && ckNgay.isSelected() == false && ckHoTen.isSelected() == true){
            System.out.println("mã họ tên");
            mahoten();
        }else if (ckMaPhong.isSelected() == true && ckNgay.isSelected() == true && ckHoTen.isSelected() == true){
            System.out.println("all true");
            all();
        }else if (ckMaPhong.isSelected() == false && ckNgay.isSelected() == true && ckHoTen.isSelected() == true){
            System.out.println("ngày họ tên");
            ngayHoten();
        }
        else if (ckMaPhong.isSelected() == false && ckNgay.isSelected() == false && ckHoTen.isSelected() == false){
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
        findrent();
    }//GEN-LAST:event_btnViewActionPerformed

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
            java.util.logging.Logger.getLogger(FormFindRent.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FormFindRent.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FormFindRent.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FormFindRent.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FormFindRent().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private swing.Button btnLoc;
    private swing.Button btnView;
    private javax.swing.JCheckBox ckHoTen;
    private javax.swing.JCheckBox ckMaPhong;
    private javax.swing.JCheckBox ckNgay;
    private com.toedter.calendar.JDateChooser jDateChooserngaydkthue;
    private com.toedter.calendar.JDateChooser jDateChooserngaydukientra;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JScrollPane jScrollPane1;
    private swing.PanelBorder panelBorder5;
    private swing.RoundPanel roundPanel5;
    private javax.swing.JTable table;
    private component.TextField txtHotenKH;
    private component.TextField txtMaphong;
    // End of variables declaration//GEN-END:variables
}
