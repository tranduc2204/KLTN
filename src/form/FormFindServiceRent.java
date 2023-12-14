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
public class FormFindServiceRent extends javax.swing.JFrame {

    /**
     * Creates new form FormFindServiceRent
     */
    public FormFindServiceRent() {
        initComponents();
    }
    
    Connect cn = new Connect();
    Connection conn;
    private DefaultTableModel tableModel;

    private String username, password, quyen, DisplayName;
    
    public FormFindServiceRent(String username, String password, String DisplayName, String quyen) {
        initComponents();
        this.username = username;
        this.password = password;
        this.DisplayName = DisplayName;
        this.quyen = quyen;
        
        ServiceRent();
    }
    
    private void ServiceRent(){
        // Khởi tạo model và đặt tên cột
        tableModel = new DefaultTableModel();
        tableModel.addColumn("Tên dịch vụ");
        tableModel.addColumn("Tên khách hàng");
        tableModel.addColumn("Đơn giá");
        tableModel.addColumn("Số lượng");
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
            String sqlQuery = "select  TenDichVu, HoKH +' '+ TenKH as hotenkh,NgayLapHD, CONCAT(FORMAT(DonGia, 'N0'),' VND') as DonGia,Concat(GiamGia,'%') as GiamGia, SL,\n" +
"CONCAT(FORMAT(((DonGia*SL)*0.1), 'N0'),' VND') as VAT, CONCAT(FORMAT(((DonGia*SL) +  ((DonGia*SL)*0.1) - ((DonGia * SL)*GiamGia/100)), 'N0'),' VND') AS GiaHD \n" +
"from HoaDonDV hddv join NHANVIEN nv on hddv.MaNV = nv.MaNV join KHACHHANG kh on hddv.MaKH = kh.MaKH \n" +
"join DICHVU dv on dv.MaDV = hddv.MaDV join dongiadv dgdv on dv.MaDonGiaDV = dgdv.MaDonGiaDV  "
                    + "where hddv.isvisible = '1'  ";
            try (PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);
                ResultSet resultSet = preparedStatement.executeQuery()) {

                // Lặp qua kết quả và thêm vào model
                while (resultSet.next()) {
                    Object[] rowData = {
                            resultSet.getObject("TenDichVu"),
                            resultSet.getObject("hotenkh"),
                            resultSet.getObject("DonGia"),
                            resultSet.getObject("SL"),
                            resultSet.getObject("GiamGia"),
                            resultSet.getObject("VAT"),
                            resultSet.getObject("GiaHD"),
                            resultSet.getObject("NgayLapHD"),
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
    
    ///////////////////////// tên dịch vụ
    
    private void tendichvu(){
        // Khởi tạo model và đặt tên cột
        tableModel = new DefaultTableModel();
        tableModel.addColumn("Tên dịch vụ");
        tableModel.addColumn("Tên khách hàng");
        tableModel.addColumn("Đơn giá");
        tableModel.addColumn("Số lượng");
        tableModel.addColumn("Giảm giá");
        tableModel.addColumn("VAT");
        tableModel.addColumn("Giá hóa đơn");
        tableModel.addColumn("Ngày lập hóa đơn");


        table.setModel(tableModel);
        

        loadDataFromSQL_tendichvu();
    }
     
    private void loadDataFromSQL_tendichvu() {
        String tendichvu = txtTenDichVu.getText();
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
            String sqlQuery = "select  TenDichVu, HoKH +' '+ TenKH as hotenkh,NgayLapHD, CONCAT(FORMAT(DonGia, 'N0'),' VND') as DonGia,Concat(GiamGia,'%') as GiamGia, SL,\n" +
"CONCAT(FORMAT(((DonGia*SL)*0.1), 'N0'),' VND') as VAT, CONCAT(FORMAT(((DonGia*SL) +  ((DonGia*SL)*0.1) - ((DonGia * SL)*GiamGia/100)), 'N0'),' VND') AS GiaHD \n" +
"from HoaDonDV hddv join NHANVIEN nv on hddv.MaNV = nv.MaNV join KHACHHANG kh on hddv.MaKH = kh.MaKH \n" +
"join DICHVU dv on dv.MaDV = hddv.MaDV join dongiadv dgdv on dv.MaDonGiaDV = dgdv.MaDonGiaDV  "
                    + "where hddv.isvisible = '1' and TenDichVu like N'%"+tendichvu+"%'  ";
            try (PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);
                ResultSet resultSet = preparedStatement.executeQuery()) {

                // Lặp qua kết quả và thêm vào model
                while (resultSet.next()) {
                    Object[] rowData = {
                            resultSet.getObject("TenDichVu"),
                            resultSet.getObject("hotenkh"),
                            resultSet.getObject("DonGia"),
                            resultSet.getObject("SL"),
                            resultSet.getObject("GiamGia"),
                            resultSet.getObject("VAT"),
                            resultSet.getObject("GiaHD"),
                            resultSet.getObject("NgayLapHD"),
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
    
    ///////////////////////// ngay
    
    private void ngay(){
        // Khởi tạo model và đặt tên cột
        tableModel = new DefaultTableModel();
        tableModel.addColumn("Tên dịch vụ");
        tableModel.addColumn("Tên khách hàng");
        tableModel.addColumn("Đơn giá");
        tableModel.addColumn("Số lượng");
        tableModel.addColumn("Giảm giá");
        tableModel.addColumn("VAT");
        tableModel.addColumn("Giá hóa đơn");
        tableModel.addColumn("Ngày lập hóa đơn");


        table.setModel(tableModel);
        

        loadDataFromSQL_ngay();
    }
     
    private void loadDataFromSQL_ngay() {
        String tendichvu = txtTenDichVu.getText();
        Connection connection = null;
        try {
            Date selectedDate = jDateChooserngaylaphd.getDate();
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(selectedDate);

            int year = calendar.get(Calendar.YEAR);
            int month = calendar.get(Calendar.MONTH) + 1; // Tháng bắt đầu từ 0, nên cộng thêm 1 để có giá trị tháng thực
            int day = calendar.get(Calendar.DAY_OF_MONTH);
            String ngaylaphd = year + "-" + month + "-" + day;

            String server = "localhost";
            String port = "1433";
            String database = "KLTN";
            String username = "sa";
            String password = "sa";

            String jdbcUrl = "jdbc:sqlserver://" + server + ":" + port + ";databaseName=" + database + ";user=" + username + ";password=" + password;
            connection = DriverManager.getConnection(jdbcUrl);
            
            // Thực hiện truy vấn SQL để lấy dữ liệu từ bảng
            String sqlQuery = "select  TenDichVu, HoKH +' '+ TenKH as hotenkh,NgayLapHD, CONCAT(FORMAT(DonGia, 'N0'),' VND') as DonGia,Concat(GiamGia,'%') as GiamGia, SL,\n" +
"CONCAT(FORMAT(((DonGia*SL)*0.1), 'N0'),' VND') as VAT, CONCAT(FORMAT(((DonGia*SL) +  ((DonGia*SL)*0.1) - ((DonGia * SL)*GiamGia/100)), 'N0'),' VND') AS GiaHD \n" +
"from HoaDonDV hddv join NHANVIEN nv on hddv.MaNV = nv.MaNV join KHACHHANG kh on hddv.MaKH = kh.MaKH \n" +
"join DICHVU dv on dv.MaDV = hddv.MaDV join dongiadv dgdv on dv.MaDonGiaDV = dgdv.MaDonGiaDV  "
                    + "where hddv.isvisible = '1' and NgayLapHD = '"+ngaylaphd+"'  ";
            try (PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);
                ResultSet resultSet = preparedStatement.executeQuery()) {

                // Lặp qua kết quả và thêm vào model
                while (resultSet.next()) {
                    Object[] rowData = {
                            resultSet.getObject("TenDichVu"),
                            resultSet.getObject("hotenkh"),
                            resultSet.getObject("DonGia"),
                            resultSet.getObject("SL"),
                            resultSet.getObject("GiamGia"),
                            resultSet.getObject("VAT"),
                            resultSet.getObject("GiaHD"),
                            resultSet.getObject("NgayLapHD"),
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
    
    ///////////////////////// tên khách hàng
    
    private void tenkhachhang(){
        // Khởi tạo model và đặt tên cột
        tableModel = new DefaultTableModel();
        tableModel.addColumn("Tên dịch vụ");
        tableModel.addColumn("Tên khách hàng");
        tableModel.addColumn("Đơn giá");
        tableModel.addColumn("Số lượng");
        tableModel.addColumn("Giảm giá");
        tableModel.addColumn("VAT");
        tableModel.addColumn("Giá hóa đơn");
        tableModel.addColumn("Ngày lập hóa đơn");


        table.setModel(tableModel);
        

        loadDataFromSQL_tenkhachhang();
    }
     
    private void loadDataFromSQL_tenkhachhang() {
        String tenkhachhang = txtHotenKH.getText();
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
            String sqlQuery = "select  TenDichVu, HoKH +' '+ TenKH as hotenkh,NgayLapHD, CONCAT(FORMAT(DonGia, 'N0'),' VND') as DonGia,Concat(GiamGia,'%') as GiamGia, SL,\n" +
"CONCAT(FORMAT(((DonGia*SL)*0.1), 'N0'),' VND') as VAT, CONCAT(FORMAT(((DonGia*SL) +  ((DonGia*SL)*0.1) - ((DonGia * SL)*GiamGia/100)), 'N0'),' VND') AS GiaHD \n" +
"from HoaDonDV hddv join NHANVIEN nv on hddv.MaNV = nv.MaNV join KHACHHANG kh on hddv.MaKH = kh.MaKH \n" +
"join DICHVU dv on dv.MaDV = hddv.MaDV join dongiadv dgdv on dv.MaDonGiaDV = dgdv.MaDonGiaDV  "
                    + "where hddv.isvisible = '1' and HoKH +' '+ TenKH like N'%"+tenkhachhang+"%'  ";
            try (PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);
                ResultSet resultSet = preparedStatement.executeQuery()) {

                // Lặp qua kết quả và thêm vào model
                while (resultSet.next()) {
                    Object[] rowData = {
                            resultSet.getObject("TenDichVu"),
                            resultSet.getObject("hotenkh"),
                            resultSet.getObject("DonGia"),
                            resultSet.getObject("SL"),
                            resultSet.getObject("GiamGia"),
                            resultSet.getObject("VAT"),
                            resultSet.getObject("GiaHD"),
                            resultSet.getObject("NgayLapHD"),
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
    
    ///////////////////////// tên khách hàng, tên dịch vụ
    
    private void tenkhachhangtendichvu(){
        // Khởi tạo model và đặt tên cột
        tableModel = new DefaultTableModel();
        tableModel.addColumn("Tên dịch vụ");
        tableModel.addColumn("Tên khách hàng");
        tableModel.addColumn("Đơn giá");
        tableModel.addColumn("Số lượng");
        tableModel.addColumn("Giảm giá");
        tableModel.addColumn("VAT");
        tableModel.addColumn("Giá hóa đơn");
        tableModel.addColumn("Ngày lập hóa đơn");


        table.setModel(tableModel);
        

        loadDataFromSQL_tenkhachhangtendichvu();
    }
     
    private void loadDataFromSQL_tenkhachhangtendichvu() {
        String tenkhachhang = txtHotenKH.getText();
        String tendichvu = txtTenDichVu.getText();
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
            String sqlQuery = "select  TenDichVu, HoKH +' '+ TenKH as hotenkh,NgayLapHD, CONCAT(FORMAT(DonGia, 'N0'),' VND') as DonGia,Concat(GiamGia,'%') as GiamGia, SL,\n" +
"CONCAT(FORMAT(((DonGia*SL)*0.1), 'N0'),' VND') as VAT, CONCAT(FORMAT(((DonGia*SL) +  ((DonGia*SL)*0.1) - ((DonGia * SL)*GiamGia/100)), 'N0'),' VND') AS GiaHD \n" +
"from HoaDonDV hddv join NHANVIEN nv on hddv.MaNV = nv.MaNV join KHACHHANG kh on hddv.MaKH = kh.MaKH \n" +
"join DICHVU dv on dv.MaDV = hddv.MaDV join dongiadv dgdv on dv.MaDonGiaDV = dgdv.MaDonGiaDV  "
                    + "where hddv.isvisible = '1' and HoKH +' '+ TenKH like N'%"+tenkhachhang+"%'  and TenDichVu like N'%"+tendichvu+"%'   ";
            try (PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);
                ResultSet resultSet = preparedStatement.executeQuery()) {

                // Lặp qua kết quả và thêm vào model
                while (resultSet.next()) {
                    Object[] rowData = {
                            resultSet.getObject("TenDichVu"),
                            resultSet.getObject("hotenkh"),
                            resultSet.getObject("DonGia"),
                            resultSet.getObject("SL"),
                            resultSet.getObject("GiamGia"),
                            resultSet.getObject("VAT"),
                            resultSet.getObject("GiaHD"),
                            resultSet.getObject("NgayLapHD"),
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
    
    ///////////////////////// ngay tên dịch vụ
    
    private void tendichvungay(){
        // Khởi tạo model và đặt tên cột
        tableModel = new DefaultTableModel();
        tableModel.addColumn("Tên dịch vụ");
        tableModel.addColumn("Tên khách hàng");
        tableModel.addColumn("Đơn giá");
        tableModel.addColumn("Số lượng");
        tableModel.addColumn("Giảm giá");
        tableModel.addColumn("VAT");
        tableModel.addColumn("Giá hóa đơn");
        tableModel.addColumn("Ngày lập hóa đơn");


        table.setModel(tableModel);
        

        loadDataFromSQL_tendichvungay();
    }
     
    private void loadDataFromSQL_tendichvungay() {
        String tendichvu = txtTenDichVu.getText();
        Connection connection = null;
        try {
            Date selectedDate = jDateChooserngaylaphd.getDate();
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(selectedDate);

            int year = calendar.get(Calendar.YEAR);
            int month = calendar.get(Calendar.MONTH) + 1; // Tháng bắt đầu từ 0, nên cộng thêm 1 để có giá trị tháng thực
            int day = calendar.get(Calendar.DAY_OF_MONTH);
            String ngaylaphd = year + "-" + month + "-" + day;

            String server = "localhost";
            String port = "1433";
            String database = "KLTN";
            String username = "sa";
            String password = "sa";

            String jdbcUrl = "jdbc:sqlserver://" + server + ":" + port + ";databaseName=" + database + ";user=" + username + ";password=" + password;
            connection = DriverManager.getConnection(jdbcUrl);
            
            // Thực hiện truy vấn SQL để lấy dữ liệu từ bảng
            String sqlQuery = "select  TenDichVu, HoKH +' '+ TenKH as hotenkh,NgayLapHD, CONCAT(FORMAT(DonGia, 'N0'),' VND') as DonGia,Concat(GiamGia,'%') as GiamGia, SL,\n" +
"CONCAT(FORMAT(((DonGia*SL)*0.1), 'N0'),' VND') as VAT, CONCAT(FORMAT(((DonGia*SL) +  ((DonGia*SL)*0.1) - ((DonGia * SL)*GiamGia/100)), 'N0'),' VND') AS GiaHD \n" +
"from HoaDonDV hddv join NHANVIEN nv on hddv.MaNV = nv.MaNV join KHACHHANG kh on hddv.MaKH = kh.MaKH \n" +
"join DICHVU dv on dv.MaDV = hddv.MaDV join dongiadv dgdv on dv.MaDonGiaDV = dgdv.MaDonGiaDV  "
                    + "where hddv.isvisible = '1' and NgayLapHD = '"+ngaylaphd+"' and TenDichVu like N'%"+tendichvu+"%'   ";
            try (PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);
                ResultSet resultSet = preparedStatement.executeQuery()) {

                // Lặp qua kết quả và thêm vào model
                while (resultSet.next()) {
                    Object[] rowData = {
                            resultSet.getObject("TenDichVu"),
                            resultSet.getObject("hotenkh"),
                            resultSet.getObject("DonGia"),
                            resultSet.getObject("SL"),
                            resultSet.getObject("GiamGia"),
                            resultSet.getObject("VAT"),
                            resultSet.getObject("GiaHD"),
                            resultSet.getObject("NgayLapHD"),
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
    
    ///////////////////////// ngay tên kh
    
    private void tenkhngay(){
        // Khởi tạo model và đặt tên cột
        tableModel = new DefaultTableModel();
        tableModel.addColumn("Tên dịch vụ");
        tableModel.addColumn("Tên khách hàng");
        tableModel.addColumn("Đơn giá");
        tableModel.addColumn("Số lượng");
        tableModel.addColumn("Giảm giá");
        tableModel.addColumn("VAT");
        tableModel.addColumn("Giá hóa đơn");
        tableModel.addColumn("Ngày lập hóa đơn");


        table.setModel(tableModel);
        

        loadDataFromSQL_tenkhngay();
    }
     
    private void loadDataFromSQL_tenkhngay() {
        String tenkh = txtHotenKH.getText();
        Connection connection = null;
        try {
            Date selectedDate = jDateChooserngaylaphd.getDate();
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(selectedDate);

            int year = calendar.get(Calendar.YEAR);
            int month = calendar.get(Calendar.MONTH) + 1; // Tháng bắt đầu từ 0, nên cộng thêm 1 để có giá trị tháng thực
            int day = calendar.get(Calendar.DAY_OF_MONTH);
            String ngaylaphd = year + "-" + month + "-" + day;

            String server = "localhost";
            String port = "1433";
            String database = "KLTN";
            String username = "sa";
            String password = "sa";

            String jdbcUrl = "jdbc:sqlserver://" + server + ":" + port + ";databaseName=" + database + ";user=" + username + ";password=" + password;
            connection = DriverManager.getConnection(jdbcUrl);
            
            // Thực hiện truy vấn SQL để lấy dữ liệu từ bảng
            String sqlQuery = "select  TenDichVu, HoKH +' '+ TenKH as hotenkh,NgayLapHD, CONCAT(FORMAT(DonGia, 'N0'),' VND') as DonGia,Concat(GiamGia,'%') as GiamGia, SL,\n" +
"CONCAT(FORMAT(((DonGia*SL)*0.1), 'N0'),' VND') as VAT, CONCAT(FORMAT(((DonGia*SL) +  ((DonGia*SL)*0.1) - ((DonGia * SL)*GiamGia/100)), 'N0'),' VND') AS GiaHD \n" +
"from HoaDonDV hddv join NHANVIEN nv on hddv.MaNV = nv.MaNV join KHACHHANG kh on hddv.MaKH = kh.MaKH \n" +
"join DICHVU dv on dv.MaDV = hddv.MaDV join dongiadv dgdv on dv.MaDonGiaDV = dgdv.MaDonGiaDV  "
                    + "where hddv.isvisible = '1' and NgayLapHD = '"+ngaylaphd+"' and HoKH +' '+ TenKH like N'%"+tenkh+"%'   ";
            try (PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);
                ResultSet resultSet = preparedStatement.executeQuery()) {

                // Lặp qua kết quả và thêm vào model
                while (resultSet.next()) {
                    Object[] rowData = {
                            resultSet.getObject("TenDichVu"),
                            resultSet.getObject("hotenkh"),
                            resultSet.getObject("DonGia"),
                            resultSet.getObject("SL"),
                            resultSet.getObject("GiamGia"),
                            resultSet.getObject("VAT"),
                            resultSet.getObject("GiaHD"),
                            resultSet.getObject("NgayLapHD"),
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
    
    
    ///////////////////////// all
    
    private void all(){
        // Khởi tạo model và đặt tên cột
        tableModel = new DefaultTableModel();
        tableModel.addColumn("Tên dịch vụ");
        tableModel.addColumn("Tên khách hàng");
        tableModel.addColumn("Đơn giá");
        tableModel.addColumn("Số lượng");
        tableModel.addColumn("Giảm giá");
        tableModel.addColumn("VAT");
        tableModel.addColumn("Giá hóa đơn");
        tableModel.addColumn("Ngày lập hóa đơn");


        table.setModel(tableModel);
        

        loadDataFromSQL_all();
    }
     
    private void loadDataFromSQL_all() {
        String tenkh = txtHotenKH.getText();
        String tendv = txtTenDichVu.getText();
        Connection connection = null;
        try {
            Date selectedDate = jDateChooserngaylaphd.getDate();
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(selectedDate);

            int year = calendar.get(Calendar.YEAR);
            int month = calendar.get(Calendar.MONTH) + 1; // Tháng bắt đầu từ 0, nên cộng thêm 1 để có giá trị tháng thực
            int day = calendar.get(Calendar.DAY_OF_MONTH);
            String ngaylaphd = year + "-" + month + "-" + day;

            String server = "localhost";
            String port = "1433";
            String database = "KLTN";
            String username = "sa";
            String password = "sa";

            String jdbcUrl = "jdbc:sqlserver://" + server + ":" + port + ";databaseName=" + database + ";user=" + username + ";password=" + password;
            connection = DriverManager.getConnection(jdbcUrl);
            
            // Thực hiện truy vấn SQL để lấy dữ liệu từ bảng
            String sqlQuery = "select  TenDichVu, HoKH +' '+ TenKH as hotenkh,NgayLapHD, CONCAT(FORMAT(DonGia, 'N0'),' VND') as DonGia,Concat(GiamGia,'%') as GiamGia, SL,\n" +
"CONCAT(FORMAT(((DonGia*SL)*0.1), 'N0'),' VND') as VAT, CONCAT(FORMAT(((DonGia*SL) +  ((DonGia*SL)*0.1) - ((DonGia * SL)*GiamGia/100)), 'N0'),' VND') AS GiaHD \n" +
"from HoaDonDV hddv join NHANVIEN nv on hddv.MaNV = nv.MaNV join KHACHHANG kh on hddv.MaKH = kh.MaKH \n" +
"join DICHVU dv on dv.MaDV = hddv.MaDV join dongiadv dgdv on dv.MaDonGiaDV = dgdv.MaDonGiaDV  "
                    + "where hddv.isvisible = '1' and NgayLapHD = '"+ngaylaphd+"' and HoKH +' '+ TenKH like N'%"+tenkh+"%' and TenDichVu like N'%"+tendv+"%'  ";
            try (PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);
                ResultSet resultSet = preparedStatement.executeQuery()) {

                // Lặp qua kết quả và thêm vào model
                while (resultSet.next()) {
                    Object[] rowData = {
                            resultSet.getObject("TenDichVu"),
                            resultSet.getObject("hotenkh"),
                            resultSet.getObject("DonGia"),
                            resultSet.getObject("SL"),
                            resultSet.getObject("GiamGia"),
                            resultSet.getObject("VAT"),
                            resultSet.getObject("GiaHD"),
                            resultSet.getObject("NgayLapHD"),
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
        ckTenDV = new javax.swing.JCheckBox();
        ckNgayLapHD = new javax.swing.JCheckBox();
        txtTenDichVu = new component.TextField();
        btnView = new swing.Button();
        jDateChooserngaylaphd = new com.toedter.calendar.JDateChooser();
        ckHoTenKH = new javax.swing.JCheckBox();
        txtHotenKH = new component.TextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        roundPanel5.setBackground(new java.awt.Color(36, 87, 157));
        roundPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Trang tìm kiếm hóa đơn dịch vụ", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 24), new java.awt.Color(255, 255, 255))); // NOI18N

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

        ckTenDV.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        ckTenDV.setText("Tên dịch vụ:");

        ckNgayLapHD.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        ckNgayLapHD.setText("Ngày lập hóa đơn:");

        txtTenDichVu.setLabelText("Mã phiếu trả phòng");

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

        ckHoTenKH.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        ckHoTenKH.setText("Họ tên khách hàng:");

        txtHotenKH.setLabelText("Họ tên khách hàng");

        javax.swing.GroupLayout panelBorder5Layout = new javax.swing.GroupLayout(panelBorder5);
        panelBorder5.setLayout(panelBorder5Layout);
        panelBorder5Layout.setHorizontalGroup(
            panelBorder5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBorder5Layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addGroup(panelBorder5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelBorder5Layout.createSequentialGroup()
                        .addComponent(ckHoTenKH)
                        .addGap(18, 18, 18))
                    .addGroup(panelBorder5Layout.createSequentialGroup()
                        .addComponent(ckTenDV)
                        .addGap(76, 76, 76)))
                .addGroup(panelBorder5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtTenDichVu, javax.swing.GroupLayout.PREFERRED_SIZE, 337, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtHotenKH, javax.swing.GroupLayout.PREFERRED_SIZE, 337, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(panelBorder5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btnLoc, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ckNgayLapHD))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panelBorder5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelBorder5Layout.createSequentialGroup()
                        .addComponent(jDateChooserngaylaphd, javax.swing.GroupLayout.PREFERRED_SIZE, 336, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(45, 45, 45))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelBorder5Layout.createSequentialGroup()
                        .addComponent(btnView, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(230, 230, 230))))
        );
        panelBorder5Layout.setVerticalGroup(
            panelBorder5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBorder5Layout.createSequentialGroup()
                .addGap(39, 39, 39)
                .addGroup(panelBorder5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(ckTenDV)
                    .addComponent(txtTenDichVu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ckNgayLapHD)
                    .addComponent(jDateChooserngaylaphd, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(31, 31, 31)
                .addGroup(panelBorder5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(ckHoTenKH)
                    .addComponent(txtHotenKH, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnView, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnLoc, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(25, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout roundPanel5Layout = new javax.swing.GroupLayout(roundPanel5);
        roundPanel5.setLayout(roundPanel5Layout);
        roundPanel5Layout.setHorizontalGroup(
            roundPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(roundPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(roundPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(panelBorder5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 1176, Short.MAX_VALUE))
                .addContainerGap())
        );
        roundPanel5Layout.setVerticalGroup(
            roundPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(roundPanel5Layout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addComponent(panelBorder5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(29, 29, 29)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 389, Short.MAX_VALUE)
                .addGap(12, 12, 12))
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
        if (ckTenDV.isSelected() == true && ckHoTenKH.isSelected() == false && ckNgayLapHD.isSelected() == false){
            System.out.println("tên dịch vụ");
            tendichvu();

        }else if(ckTenDV.isSelected() == false && ckHoTenKH.isSelected() == true && ckNgayLapHD.isSelected() == false){
            System.out.println("họ tên kh");
            tenkhachhang();

        }else if (ckTenDV.isSelected() == false && ckHoTenKH.isSelected() == false && ckNgayLapHD.isSelected() == true){
            System.out.println("ngaylaphd");
            ngay();
        }else if (ckTenDV.isSelected() == true && ckHoTenKH.isSelected() == true && ckNgayLapHD.isSelected() == false){
            System.out.println("tenkhtendv");
            tenkhachhangtendichvu();
        }else if (ckTenDV.isSelected() == true && ckHoTenKH.isSelected() == false && ckNgayLapHD.isSelected() == true){
            System.out.println("tendv ngay");
            tendichvungay();
        }else if (ckTenDV.isSelected() == true && ckHoTenKH.isSelected() == true && ckNgayLapHD.isSelected() == true){
            System.out.println("all true");
            all();
        }else if (ckTenDV.isSelected() == false && ckHoTenKH.isSelected() == true && ckNgayLapHD.isSelected() == true){
            System.out.println("tenkhngay");
            tenkhngay();
        }
        else if (ckTenDV.isSelected() == false && ckHoTenKH.isSelected() == false && ckNgayLapHD.isSelected() == false){
            System.out.println("chọn lọc");
            JOptionPane.showMessageDialog(this, "Vui lòng chọn phương thức lọc");
        }
    }//GEN-LAST:event_btnLocActionPerformed

    private void btnViewActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnViewActionPerformed
        // TODO add your handling code here:
        ServiceRent();
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
            java.util.logging.Logger.getLogger(FormFindServiceRent.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FormFindServiceRent.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FormFindServiceRent.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FormFindServiceRent.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FormFindServiceRent().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private swing.Button btnLoc;
    private swing.Button btnView;
    private javax.swing.JCheckBox ckHoTenKH;
    private javax.swing.JCheckBox ckNgayLapHD;
    private javax.swing.JCheckBox ckTenDV;
    private com.toedter.calendar.JDateChooser jDateChooserngaylaphd;
    private javax.swing.JScrollPane jScrollPane1;
    private swing.PanelBorder panelBorder5;
    private swing.RoundPanel roundPanel5;
    private javax.swing.JTable table;
    private component.TextField txtHotenKH;
    private component.TextField txtTenDichVu;
    // End of variables declaration//GEN-END:variables
}
