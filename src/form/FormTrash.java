/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package form;

import component.TableActionCellEditor;
import component.TableActionCellRender;
import component.TableActionEvent;
import connect.Connect;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import model.ModelAccount;
import model.ModelAccountv2;



/**
 *
 * @author TeeDee
 */
public class FormTrash extends javax.swing.JPanel {

    /**
     * Creates new form FormTrash
     */
    Connect cn = new Connect();
    Connection conn;
    private DefaultTableModel tableModel;
    
    public FormTrash() {
        initComponents();
        TrashAccount();
        
    }
    
    private void TrashAccount(){
        // Khởi tạo model và đặt tên cột
        tableModel = new DefaultTableModel();
        tableModel.addColumn("Tên đăng nhập");
        tableModel.addColumn("Tên hiển thị");
        tableModel.addColumn("Mật khẩu");
        tableModel.addColumn("Loại tài khoản");
        tableModel.addColumn("Mã nhân viên");
        tableModel.addColumn("Họ tên nhân viên");
        tableModel.addColumn("");
        
        // Thiết lập tableModel cho JTable
        table.setModel(tableModel);
        
        // Load dữ liệu từ SQL Server vào JTable
        loadDataFromSQL();
        setupDeleteAction();
    }
    
    private void setupDeleteAction() {
        
        TableActionEvent deleteEvent = new TableActionEvent() {
            
            @Override
            public void onDelete(int row) {
                if (table.isEditing()) {
                    table.getCellEditor().stopCellEditing();
                }
                // Lấy dữ liệu từ cột User Name để xóa trong cơ sở dữ liệu
                String userNameToDelete = table.getValueAt(row, 0).toString();
                 // Hiển thị hộp thoại xác nhận
                int option = showDeleteConfirmationDialog(userNameToDelete);
                if (option == JOptionPane.YES_OPTION) {
                    // Nếu người dùng đồng ý, xóa dữ liệu từ cơ sở dữ liệu
                    deleteDataFromSQL(userNameToDelete);
                    // Xóa dòng trong JTable
                    tableModel.removeRow(row);
                }

            }

            @Override
            public void onFresh(int row) {
                if (table.isEditing()) {
                    table.getCellEditor().stopCellEditing();
                }
                // Lấy dữ liệu từ cột User Name để khôi phục trong cơ sở dữ liệu
                String userNameToUpdate = table.getValueAt(row, 0).toString();
                 // Hiển thị hộp thoại xác nhận
                int option = showUpdateConfirmationDialog(userNameToUpdate);
                if (option == JOptionPane.YES_OPTION) {
                    // Nếu người dùng đồng ý, khôi phục dữ liệu từ cơ sở dữ liệu
                    updateDataFromSQL(userNameToUpdate);
                    // Xóa dòng trong JTable
                    tableModel.removeRow(row);
                }
            }
        };

        // Thiết lập renderer và editor cho cột Action
        table.getColumnModel().getColumn(6).setCellRenderer(new TableActionCellRender());
        table.getColumnModel().getColumn(6).setCellEditor(new TableActionCellEditor(deleteEvent));
    }
    
    private int showDeleteConfirmationDialog(String userName) {
        String message = "Bạn có chắc chắn muốn xóa '" + userName + "' không?";
        String title = "Xác nhận xóa";

        return JOptionPane.showConfirmDialog(this, message, title, JOptionPane.YES_NO_OPTION);
    }
    
    private int showUpdateConfirmationDialog(String userName) {
        String message = "Bạn có chắc chắn muốn khôi phục '" + userName + "' không?";
        String title = "Xác nhận khôi phục";

        return JOptionPane.showConfirmDialog(this, message, title, JOptionPane.YES_NO_OPTION);
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
            String sqlQuery = "select UserName, DisplayName, PassWorrd, Typpe, NV.MaNV, HoNV +' '+ TenNV as hotennv \n" +
"from Account join NHANVIEN nv on Account.MaNV = nv.MaNV  where account.isvisible = '0' and ( Typpe = 'Admin' or Typpe = 'User')   ";
            try (PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);
                ResultSet resultSet = preparedStatement.executeQuery()) {

                // Lặp qua kết quả và thêm vào model
                while (resultSet.next()) {
                    Object[] rowData = {
                        resultSet.getObject("UserName"),
                        resultSet.getObject("DisplayName"),
                        resultSet.getObject("PassWorrd"),
                        resultSet.getObject("Typpe"),
                        resultSet.getObject("MaNV"),
                        resultSet.getObject("hotennv"),
                        resultSet.getObject("Typpe"),
//                            "Action" // Cần thay thế "Action" bằng cột thực tế bạn muốn hiển thị hoặc xử lý
                            
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
    
    
    
    private void deleteDataFromSQL(String userName) {
        // Thực hiện xóa dữ liệu từ SQL tại đây
        Connection connection = null;
        try  {
            String server = "localhost";
            String port = "1433";
            String database = "KLTN";
            String username = "sa";
            String password = "sa";

            String jdbcUrl = "jdbc:sqlserver://" + server + ":" + port + ";databaseName=" + database + ";user=" + username + ";password=" + password;
            connection = DriverManager.getConnection(jdbcUrl);
            String deleteQuery = "DELETE FROM account WHERE UserName = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(deleteQuery)) {
                preparedStatement.setString(1, userName);

                // Thực hiện câu lệnh DELETE
                int rowsAffected = preparedStatement.executeUpdate();

                if (rowsAffected > 0) {
                    System.out.println("Account deleted successfully.");
                } else {
                    System.out.println("No account found with User Name: " + userName);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error deleting account: " + e.getMessage());
        }
    }
    private void updateDataFromSQL(String userName) {
        // Thực hiện xóa dữ liệu từ SQL tại đây
        Connection connection = null;
        try  {
            String server = "localhost";
            String port = "1433";
            String database = "KLTN";
            String username = "sa";
            String password = "sa";

            String jdbcUrl = "jdbc:sqlserver://" + server + ":" + port + ";databaseName=" + database + ";user=" + username + ";password=" + password;
            connection = DriverManager.getConnection(jdbcUrl);
            String deleteQuery = "update account set isvisible = '1' where username = ? ";
            try (PreparedStatement preparedStatement = connection.prepareStatement(deleteQuery)) {
                preparedStatement.setString(1, userName);

                // Thực hiện câu lệnh DELETE
                int rowsAffected = preparedStatement.executeUpdate();

                if (rowsAffected > 0) {
                    System.out.println("Account update successfully.");
                } else {
                    System.out.println("No account found with User Name: " + userName);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error deleting account: " + e.getMessage());
        }
    }

    private void TrashCustomers(){
        // Khởi tạo model và đặt tên cột
        tableModel = new DefaultTableModel();
        tableModel.addColumn("Mã khách hàng");
        tableModel.addColumn("Họ khách hàng");
        tableModel.addColumn("Tên khách hàng");
        tableModel.addColumn("Giới tính");
        tableModel.addColumn("Địa chỉ");
        tableModel.addColumn("Ngày sinh");
        tableModel.addColumn("Số điện thoại");
        tableModel.addColumn("");
        // Thiết lập tableModel cho JTable
        table.setModel(tableModel);
        
        // Load dữ liệu từ SQL Server vào JTable
        loadDataCustomerFromSQL();
        setupDeleteCustomerAction();
    }
    
    private void loadDataCustomerFromSQL() {
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
            String sqlQuery = "select MaKH, HoKH, TenKH, case when GioiTinh = 0 then 'Nam' when GioiTinh = 1 then N'Nữ' end as GioiTinh, DiaChi, NgaySinh,SoDT from KHACHHANG where isvisible = '0' ";
            try (PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);
                ResultSet resultSet = preparedStatement.executeQuery()) {

                // Lặp qua kết quả và thêm vào model
                while (resultSet.next()) {
                    Object[] rowData = {
                        resultSet.getObject("MaKH"),
                        resultSet.getObject("HoKH"),
                        resultSet.getObject("TenKH"),
                        resultSet.getObject("GioiTinh"),
                        resultSet.getObject("DiaChi"),
                        resultSet.getObject("NgaySinh"),
                        resultSet.getObject("SoDT"),
                            
//                            "Action" // Cần thay thế "Action" bằng cột thực tế bạn muốn hiển thị hoặc xử lý
                            
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
    
    private void setupDeleteCustomerAction() {
        
        TableActionEvent deleteEvent = new TableActionEvent() {
            
            @Override
            public void onDelete(int row) {
                if (table.isEditing()) {
                    table.getCellEditor().stopCellEditing();
                }
                // Lấy dữ liệu từ cột User Name để xóa trong cơ sở dữ liệu
                String userNameToDelete = table.getValueAt(row, 0).toString();
                 // Hiển thị hộp thoại xác nhận
                int option = showDeleteConfirmationDialog(userNameToDelete);
                if (option == JOptionPane.YES_OPTION) {
                    // Nếu người dùng đồng ý, xóa dữ liệu từ cơ sở dữ liệu
                    deleteDataCustomerFromSQL(userNameToDelete);
                    // Xóa dòng trong JTable
                    tableModel.removeRow(row);
                }

            }

            @Override
            public void onFresh(int row) {
                if (table.isEditing()) {
                    table.getCellEditor().stopCellEditing();
                }
                // Lấy dữ liệu từ cột User Name để khôi phục trong cơ sở dữ liệu
                String userNameToUpdate = table.getValueAt(row, 0).toString();
                 // Hiển thị hộp thoại xác nhận
                int option = showUpdateConfirmationDialog(userNameToUpdate);
                if (option == JOptionPane.YES_OPTION) {
                    // Nếu người dùng đồng ý, khôi phục dữ liệu từ cơ sở dữ liệu
                    updateDataCustomerFromSQL(userNameToUpdate);
                    // Xóa dòng trong JTable
                    tableModel.removeRow(row);
                }
                
            }

           
        };

        // Thiết lập renderer và editor cho cột Action
        table.getColumnModel().getColumn(7).setCellRenderer(new TableActionCellRender());
        table.getColumnModel().getColumn(7).setCellEditor(new TableActionCellEditor(deleteEvent));
    }
    
    private void deleteDataCustomerFromSQL(String MaKH) {
        // Thực hiện xóa dữ liệu từ SQL tại đây
        Connection connection = null;
        try  {
            String server = "localhost";
            String port = "1433";
            String database = "KLTN";
            String username = "sa";
            String password = "sa";

            String jdbcUrl = "jdbc:sqlserver://" + server + ":" + port + ";databaseName=" + database + ";user=" + username + ";password=" + password;
            connection = DriverManager.getConnection(jdbcUrl);
            String deleteQuery = "DELETE FROM khachhang WHERE MaKH = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(deleteQuery)) {
                preparedStatement.setString(1, MaKH);

                // Thực hiện câu lệnh DELETE
                int rowsAffected = preparedStatement.executeUpdate();

                if (rowsAffected > 0) {
                    System.out.println("Customer deleted successfully.");
                } else {
                    System.out.println("No Customer found with Mã nhân viên: " + MaKH);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error deleting Customer: " + e.getMessage());
        }
    }
    private void updateDataCustomerFromSQL(String MaKH) {
        // Thực hiện xóa dữ liệu từ SQL tại đây
        Connection connection = null;
        try  {
            String server = "localhost";
            String port = "1433";
            String database = "KLTN";
            String username = "sa";
            String password = "sa";

            String jdbcUrl = "jdbc:sqlserver://" + server + ":" + port + ";databaseName=" + database + ";user=" + username + ";password=" + password;
            connection = DriverManager.getConnection(jdbcUrl);
            String deleteQuery = "update khachhang set isvisible = '1' where MaKH = ? ";
            try (PreparedStatement preparedStatement = connection.prepareStatement(deleteQuery)) {
                preparedStatement.setString(1, MaKH);

                // Thực hiện câu lệnh DELETE
                int rowsAffected = preparedStatement.executeUpdate();

                if (rowsAffected > 0) {
                    System.out.println("Customer update successfully.");
                } else {
                    System.out.println("No Customer found with MaKH: " + MaKH);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error deleting Customer: " + e.getMessage());
        }
    }
    
    
    private void TrashStaff(){
        // Khởi tạo model và đặt tên cột
        tableModel = new DefaultTableModel();
        tableModel.addColumn("Mã nhân viên");
        tableModel.addColumn("Họ nhân viên");
        tableModel.addColumn("Tên nhân viên");
        tableModel.addColumn("Giới tính");
        tableModel.addColumn("Địa chỉ");
        tableModel.addColumn("Ngày sinh");
        tableModel.addColumn("Điện thoại");
        tableModel.addColumn("Email");
        tableModel.addColumn("Nơi sinh");
        tableModel.addColumn("Ngày vào làm");
        tableModel.addColumn("");
        // Thiết lập tableModel cho JTable
        table.setModel(tableModel);
        
        // Load dữ liệu từ SQL Server vào JTable

        loadDataStaffFromSQL();
        setupDeleteStaffAction();
    }
    
    private void loadDataStaffFromSQL() {
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
            String sqlQuery = "select MaNV, HoNV, TenNV, case when GioiTinh = 0 then 'Nam' when GioiTinh = 1 then N'Nữ' end as GioiTinh, DiaChi, NgaySinh, DienThoai, Email, NoiSinh, NgayVaoLam from NHANVIEN"
                    + " where isvisible = '0' ";
            try (PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);
                ResultSet resultSet = preparedStatement.executeQuery()) {

                // Lặp qua kết quả và thêm vào model
                while (resultSet.next()) {
                    Object[] rowData = {
                    resultSet.getObject("MaNV"),
                    resultSet.getObject("HoNV"),
                    resultSet.getObject("TenNV"),
                    resultSet.getObject("GioiTinh"),
                    resultSet.getObject("DiaChi"),
                    resultSet.getObject("NgaySinh"),
                    resultSet.getObject("DienThoai"),
                    resultSet.getObject("Email"),
                    resultSet.getObject("NoiSinh"),
                    resultSet.getObject("NgayVaoLam"),
 
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
    
    private void setupDeleteStaffAction() {
        
        TableActionEvent deleteEvent = new TableActionEvent() {
            
            @Override
            public void onDelete(int row) {
                if (table.isEditing()) {
                    table.getCellEditor().stopCellEditing();
                }
                // Lấy dữ liệu từ cột User Name để xóa trong cơ sở dữ liệu
                String userNameToDelete = table.getValueAt(row, 0).toString();
                 // Hiển thị hộp thoại xác nhận
                int option = showDeleteConfirmationDialog(userNameToDelete);
                if (option == JOptionPane.YES_OPTION) {
                    // Nếu người dùng đồng ý, xóa dữ liệu từ cơ sở dữ liệu
                    deleteDataStaffFromSQL(userNameToDelete);
                    // Xóa dòng trong JTable
                    tableModel.removeRow(row);
                }

            }

            @Override
            public void onFresh(int row) {
                if (table.isEditing()) {
                    table.getCellEditor().stopCellEditing();
                }
                // Lấy dữ liệu từ cột User Name để khôi phục trong cơ sở dữ liệu
                String userNameToUpdate = table.getValueAt(row, 0).toString();
                 // Hiển thị hộp thoại xác nhận
                int option = showUpdateConfirmationDialog(userNameToUpdate);
                if (option == JOptionPane.YES_OPTION) {
                    // Nếu người dùng đồng ý, khôi phục dữ liệu từ cơ sở dữ liệu
                    updateDataStaffCFromSQL(userNameToUpdate);
                    // Xóa dòng trong JTable
                    tableModel.removeRow(row);
                }
                
            }

         
        };

        // Thiết lập renderer và editor cho cột Action
        table.getColumnModel().getColumn(10).setCellRenderer(new TableActionCellRender());
        table.getColumnModel().getColumn(10).setCellEditor(new TableActionCellEditor(deleteEvent));
    }
    
    private void deleteDataStaffFromSQL(String MaNV) {
        // Thực hiện xóa dữ liệu từ SQL tại đây
        Connection connection = null;
        try  {
            String server = "localhost";
            String port = "1433";
            String database = "KLTN";
            String username = "sa";
            String password = "sa";

            String jdbcUrl = "jdbc:sqlserver://" + server + ":" + port + ";databaseName=" + database + ";user=" + username + ";password=" + password;
            connection = DriverManager.getConnection(jdbcUrl);
            String deleteQuery = "DELETE FROM nhanvien WHERE MaNV = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(deleteQuery)) {
                preparedStatement.setString(1, MaNV);

                // Thực hiện câu lệnh DELETE
                int rowsAffected = preparedStatement.executeUpdate();

                if (rowsAffected > 0) {
                    System.out.println("Staff deleted successfully.");
                } else {
                    System.out.println("No Staff found with User Name: " + MaNV);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error deleting account: " + e.getMessage());
        }
    }
    private void updateDataStaffCFromSQL(String MaNV) {
        // Thực hiện xóa dữ liệu từ SQL tại đây
        Connection connection = null;
        try  {
            String server = "localhost";
            String port = "1433";
            String database = "KLTN";
            String username = "sa";
            String password = "sa";

            String jdbcUrl = "jdbc:sqlserver://" + server + ":" + port + ";databaseName=" + database + ";user=" + username + ";password=" + password;
            connection = DriverManager.getConnection(jdbcUrl);
            String deleteQuery = "update nhanvien set isvisible = '1' where MaNV = ? ";
            try (PreparedStatement preparedStatement = connection.prepareStatement(deleteQuery)) {
                preparedStatement.setString(1, MaNV);

                // Thực hiện câu lệnh DELETE
                int rowsAffected = preparedStatement.executeUpdate();

                if (rowsAffected > 0) {
                    System.out.println("Account update successfully.");
                } else {
                    System.out.println("No account found with MaKH: " + MaNV);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error deleting Customer: " + e.getMessage());
        }
    }
    
    private void TrashService(){
        // Khởi tạo model và đặt tên cột
        tableModel = new DefaultTableModel();
        tableModel.addColumn("Mã dịch vụ ");
        tableModel.addColumn("Tên dịch vụ");
        tableModel.addColumn("Mã đơn giá dịch vụ");
        tableModel.addColumn("Giảm giá");
        tableModel.addColumn("Đơn giá");
        tableModel.addColumn("");
        // Thiết lập tableModel cho JTable
        table.setModel(tableModel);
        
        // Load dữ liệu từ SQL Server vào JTable

        loadDataServiceFromSQL();
        setupDeleteServiceAction();
    }
    
    private void loadDataServiceFromSQL() {
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
            String sqlQuery = "select MaDV, TenDichVu,dgdv.MaDonGiaDV,Concat(GiamGia,'%') as GiamGia, CONCAT(FORMAT(DonGia, 'N0'),' VND') as DonGia  from DICHVU dv \n" +
"join DonGiaDV dgdv on dv.MaDonGiaDV = dgdv.MaDonGiaDV where isvisible = '0' ";
            try (PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);
                ResultSet resultSet = preparedStatement.executeQuery()) {

                // Lặp qua kết quả và thêm vào model
                while (resultSet.next()) {
                    Object[] rowData = {
                    resultSet.getObject("MaDV"),
                    resultSet.getObject("TenDichVu"),
                    resultSet.getObject("MaDonGiaDV"),
                    resultSet.getObject("GiamGia"),
                    resultSet.getObject("DonGia"),
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
    
    private void setupDeleteServiceAction() {
        
        TableActionEvent deleteEvent = new TableActionEvent() {
            
            @Override
            public void onDelete(int row) {
                if (table.isEditing()) {
                    table.getCellEditor().stopCellEditing();
                }
                // Lấy dữ liệu từ cột User Name để xóa trong cơ sở dữ liệu
                String userNameToDelete = table.getValueAt(row, 0).toString();
                 // Hiển thị hộp thoại xác nhận
                int option = showDeleteConfirmationDialog(userNameToDelete);
                if (option == JOptionPane.YES_OPTION) {
                    // Nếu người dùng đồng ý, xóa dữ liệu từ cơ sở dữ liệu
                    deleteDataServiceFromSQL(userNameToDelete);
                    // Xóa dòng trong JTable
                    tableModel.removeRow(row);
                }

            }

            @Override
            public void onFresh(int row) {
                if (table.isEditing()) {
                    table.getCellEditor().stopCellEditing();
                }
                // Lấy dữ liệu từ cột User Name để khôi phục trong cơ sở dữ liệu
                String userNameToUpdate = table.getValueAt(row, 0).toString();
                 // Hiển thị hộp thoại xác nhận
                int option = showUpdateConfirmationDialog(userNameToUpdate);
                if (option == JOptionPane.YES_OPTION) {
                    // Nếu người dùng đồng ý, khôi phục dữ liệu từ cơ sở dữ liệu
                    updateDataServiceFromSQL(userNameToUpdate);
                    // Xóa dòng trong JTable
                    tableModel.removeRow(row);
                }
                
            }

            
            
        };

        // Thiết lập renderer và editor cho cột Action
        table.getColumnModel().getColumn(5).setCellRenderer(new TableActionCellRender());
        table.getColumnModel().getColumn(5).setCellEditor(new TableActionCellEditor(deleteEvent));
    }
    
    private void deleteDataServiceFromSQL(String MaDV) {
        // Thực hiện xóa dữ liệu từ SQL tại đây
        Connection connection = null;
        try  {
            String server = "localhost";
            String port = "1433";
            String database = "KLTN";
            String username = "sa";
            String password = "sa";

            String jdbcUrl = "jdbc:sqlserver://" + server + ":" + port + ";databaseName=" + database + ";user=" + username + ";password=" + password;
            connection = DriverManager.getConnection(jdbcUrl);
            String deleteQuery = "DELETE FROM dichvu WHERE MaDV = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(deleteQuery)) {
                preparedStatement.setString(1, MaDV);

                // Thực hiện câu lệnh DELETE
                int rowsAffected = preparedStatement.executeUpdate();

                if (rowsAffected > 0) {
                    System.out.println("Staff deleted successfully.");
                } else {
                    System.out.println("No Staff found with User Name: " + MaDV);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error deleting account: " + e.getMessage());
        }
    }
    private void updateDataServiceFromSQL(String MaDV) {
        // Thực hiện xóa dữ liệu từ SQL tại đây
        Connection connection = null;
        try  {
            String server = "localhost";
            String port = "1433";
            String database = "KLTN";
            String username = "sa";
            String password = "sa";

            String jdbcUrl = "jdbc:sqlserver://" + server + ":" + port + ";databaseName=" + database + ";user=" + username + ";password=" + password;
            connection = DriverManager.getConnection(jdbcUrl);
            String deleteQuery = "update dichvu set isvisible = '1' where MaDV = ? ";
            try (PreparedStatement preparedStatement = connection.prepareStatement(deleteQuery)) {
                preparedStatement.setString(1, MaDV);

                // Thực hiện câu lệnh DELETE
                int rowsAffected = preparedStatement.executeUpdate();

                if (rowsAffected > 0) {
                    System.out.println("Account update successfully.");
                } else {
                    System.out.println("No account found with MaKH: " + MaDV);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error deleting Customer: " + e.getMessage());
        }
    }
    
    private void TrashRoom(){
        // Khởi tạo model và đặt tên cột
        tableModel = new DefaultTableModel();
        tableModel.addColumn("Mã phòng");
        tableModel.addColumn("Tên phòng");
        tableModel.addColumn("Mã tình trạng phòng");
        tableModel.addColumn("Tình trạng phòng");
        tableModel.addColumn("Mã loại phòng");
        tableModel.addColumn("Tên loại phòng");
        tableModel.addColumn("Mã đơn giá");
        tableModel.addColumn("Giảm giá");
        tableModel.addColumn("Giá phòng");
        tableModel.addColumn("");
        // Thiết lập tableModel cho JTable
        table.setModel(tableModel);
        
        // Load dữ liệu từ SQL Server vào JTable

        loadDataRoomFromSQL();
        setupDeleteRoomAction();
    }
    
    private void loadDataRoomFromSQL() {
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
            String sqlQuery = " select MaPhong,TenPhong, TinhTrangPhong.MaTinhtrangphong, TinhTrangPhong, LoaiPhong.MaLoaiPhong, TenLoaiPhong, dgp.MaDonGiaPhong, Concat(GiamGia,'%') as GiamGia,  CONCAT(FORMAT(dgp.DonGia, 'N0'),' VND') as DonGia from phong \n" +
"join LOAIPHONG on PHONG.MaLoaiPhong = LOAIPHONG.MaLoaiPhong join TINHTRANGPHONG on TinhTrangPhong.MaTinhTrangPhong = PHONG.MaTinhtrangphong \n" +
"join DonGiaPhong dgp on dgp.MaDonGiaPhong = phong.MaDonGiaPhong where isvisible = '0' ";
            try (PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);
                ResultSet resultSet = preparedStatement.executeQuery()) {

                // Lặp qua kết quả và thêm vào model
                while (resultSet.next()) {
                    Object[] rowData = {
                    resultSet.getObject("MaPhong"),
                    resultSet.getObject("TenPhong"),
                    resultSet.getObject("MaTinhtrangphong"),
                    resultSet.getObject("TinhTrangPhong"),
                    resultSet.getObject("MaLoaiPhong"),
                    resultSet.getObject("TenLoaiPhong"),
                    resultSet.getObject("MaDonGiaPhong"),
                    resultSet.getObject("GiamGia"),
                    resultSet.getObject("DonGia"),
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
    
    private void setupDeleteRoomAction() {
        
        TableActionEvent deleteEvent = new TableActionEvent() {
            
            @Override
            public void onDelete(int row) {
                if (table.isEditing()) {
                    table.getCellEditor().stopCellEditing();
                }
                // Lấy dữ liệu từ cột User Name để xóa trong cơ sở dữ liệu
                String userNameToDelete = table.getValueAt(row, 0).toString();
                 // Hiển thị hộp thoại xác nhận
                int option = showDeleteConfirmationDialog(userNameToDelete);
                if (option == JOptionPane.YES_OPTION) {
                    // Nếu người dùng đồng ý, xóa dữ liệu từ cơ sở dữ liệu
                    deleteDataRoomFromSQL(userNameToDelete);
                    // Xóa dòng trong JTable
                    tableModel.removeRow(row);
                }

            }

            @Override
            public void onFresh(int row) {
                if (table.isEditing()) {
                    table.getCellEditor().stopCellEditing();
                }
                // Lấy dữ liệu từ cột User Name để khôi phục trong cơ sở dữ liệu
                String userNameToUpdate = table.getValueAt(row, 0).toString();
                 // Hiển thị hộp thoại xác nhận
                int option = showUpdateConfirmationDialog(userNameToUpdate);
                if (option == JOptionPane.YES_OPTION) {
                    // Nếu người dùng đồng ý, khôi phục dữ liệu từ cơ sở dữ liệu
                    updateDataRoomFromSQL(userNameToUpdate);
                    // Xóa dòng trong JTable
                    tableModel.removeRow(row);
                }
                
            }

           
            
        };

        // Thiết lập renderer và editor cho cột Action
        table.getColumnModel().getColumn(9).setCellRenderer(new TableActionCellRender());
        table.getColumnModel().getColumn(9).setCellEditor(new TableActionCellEditor(deleteEvent));
    }
    
    private void deleteDataRoomFromSQL(String MaPhong) {
        // Thực hiện xóa dữ liệu từ SQL tại đây
        Connection connection = null;
        try  {
            String server = "localhost";
            String port = "1433";
            String database = "KLTN";
            String username = "sa";
            String password = "sa";

            String jdbcUrl = "jdbc:sqlserver://" + server + ":" + port + ";databaseName=" + database + ";user=" + username + ";password=" + password;
            connection = DriverManager.getConnection(jdbcUrl);
            String deleteQuery = "DELETE FROM phong WHERE MaPhong = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(deleteQuery)) {
                preparedStatement.setString(1, MaPhong);

                // Thực hiện câu lệnh DELETE
                int rowsAffected = preparedStatement.executeUpdate();

                if (rowsAffected > 0) {
                    System.out.println("Room deleted successfully.");
                } else {
                    System.out.println("No Room found with MaPhong: " + MaPhong);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error deleting account: " + e.getMessage());
        }
    }
    private void updateDataRoomFromSQL(String MaPhong) {
        // Thực hiện xóa dữ liệu từ SQL tại đây
        Connection connection = null;
        try  {
            String server = "localhost";
            String port = "1433";
            String database = "KLTN";
            String username = "sa";
            String password = "sa";

            String jdbcUrl = "jdbc:sqlserver://" + server + ":" + port + ";databaseName=" + database + ";user=" + username + ";password=" + password;
            connection = DriverManager.getConnection(jdbcUrl);
            String deleteQuery = "update phong set isvisible = '1' where MaPhong = ? ";
            try (PreparedStatement preparedStatement = connection.prepareStatement(deleteQuery)) {
                preparedStatement.setString(1, MaPhong);

                // Thực hiện câu lệnh DELETE
                int rowsAffected = preparedStatement.executeUpdate();

                if (rowsAffected > 0) {
                    System.out.println("Room update successfully.");
                } else {
                    System.out.println("No room found with MaPhong: " + MaPhong);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error deleting Customer: " + e.getMessage());
        }
    }
    
    private void TrashSupplies(){
        // Khởi tạo model và đặt tên cột
        tableModel = new DefaultTableModel();
        tableModel.addColumn("Mã vật tư");
        tableModel.addColumn("Tên vật tư");
        tableModel.addColumn("");
        // Thiết lập tableModel cho JTable
        table.setModel(tableModel);
        
        // Load dữ liệu từ SQL Server vào JTable

        loadDataSuppliesFromSQL();
        setupDeleteSuppliesAction();
    }
    
    private void loadDataSuppliesFromSQL() {
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
            String sqlQuery = "select * from vattu where isvisible = '0' ";
            try (PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);
                ResultSet resultSet = preparedStatement.executeQuery()) {

                // Lặp qua kết quả và thêm vào model
                while (resultSet.next()) {
                    Object[] rowData = {
                    resultSet.getObject("MaVatTu"),
                    resultSet.getObject("TenVatTu"),
                
                   
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
    
    private void setupDeleteSuppliesAction() {
        
        TableActionEvent deleteEvent = new TableActionEvent() {
            
            @Override
            public void onDelete(int row) {
                if (table.isEditing()) {
                    table.getCellEditor().stopCellEditing();
                }
                // Lấy dữ liệu từ cột User Name để xóa trong cơ sở dữ liệu
                String userNameToDelete = table.getValueAt(row, 0).toString();
                 // Hiển thị hộp thoại xác nhận
                int option = showDeleteConfirmationDialog(userNameToDelete);
                if (option == JOptionPane.YES_OPTION) {
                    // Nếu người dùng đồng ý, xóa dữ liệu từ cơ sở dữ liệu
                    deleteDataSuppliesFromSQL(userNameToDelete);
                    // Xóa dòng trong JTable
                    tableModel.removeRow(row);
                }

            }

            @Override
            public void onFresh(int row) {
                if (table.isEditing()) {
                    table.getCellEditor().stopCellEditing();
                }
                // Lấy dữ liệu từ cột User Name để khôi phục trong cơ sở dữ liệu
                String userNameToUpdate = table.getValueAt(row, 0).toString();
                 // Hiển thị hộp thoại xác nhận
                int option = showUpdateConfirmationDialog(userNameToUpdate);
                if (option == JOptionPane.YES_OPTION) {
                    // Nếu người dùng đồng ý, khôi phục dữ liệu từ cơ sở dữ liệu
                    updateDataSuppliesFromSQL(userNameToUpdate);
                    // Xóa dòng trong JTable
                    tableModel.removeRow(row);
                }
                
            }

           
            
        };

        // Thiết lập renderer và editor cho cột Action
        table.getColumnModel().getColumn(2).setCellRenderer(new TableActionCellRender());
        table.getColumnModel().getColumn(2).setCellEditor(new TableActionCellEditor(deleteEvent));
    }
    
    private void deleteDataSuppliesFromSQL(String MaVatTu) {
        // Thực hiện xóa dữ liệu từ SQL tại đây
        Connection connection = null;
        try  {
            String server = "localhost";
            String port = "1433";
            String database = "KLTN";
            String username = "sa";
            String password = "sa";

            String jdbcUrl = "jdbc:sqlserver://" + server + ":" + port + ";databaseName=" + database + ";user=" + username + ";password=" + password;
            connection = DriverManager.getConnection(jdbcUrl);
            String deleteQuery = "DELETE FROM vattu WHERE MaVatTu = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(deleteQuery)) {
                preparedStatement.setString(1, MaVatTu);

                // Thực hiện câu lệnh DELETE
                int rowsAffected = preparedStatement.executeUpdate();

                if (rowsAffected > 0) {
                    System.out.println("Supplies deleted successfully.");
                } else {
                    System.out.println("No Supplies found with MaVatTu: " + MaVatTu);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error deleting Supplies: " + e.getMessage());
        }
    }
    private void updateDataSuppliesFromSQL(String MaVatTu) {
        // Thực hiện xóa dữ liệu từ SQL tại đây
        Connection connection = null;
        try  {
            String server = "localhost";
            String port = "1433";
            String database = "KLTN";
            String username = "sa";
            String password = "sa";

            String jdbcUrl = "jdbc:sqlserver://" + server + ":" + port + ";databaseName=" + database + ";user=" + username + ";password=" + password;
            connection = DriverManager.getConnection(jdbcUrl);
            String deleteQuery = "update vattu set isvisible = '1' where MaVatTu = ? ";
            try (PreparedStatement preparedStatement = connection.prepareStatement(deleteQuery)) {
                preparedStatement.setString(1, MaVatTu);

                // Thực hiện câu lệnh DELETE
                int rowsAffected = preparedStatement.executeUpdate();

                if (rowsAffected > 0) {
                    System.out.println("Supplies update successfully.");
                } else {
                    System.out.println("No Supplies found with MaVatTu: " + MaVatTu);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error deleting Supplies: " + e.getMessage());
        }
    }
    
    
    private void TrashRoomSupplies(){
        // Khởi tạo model và đặt tên cột
        tableModel = new DefaultTableModel();
        tableModel.addColumn("Mã vật tư");
        tableModel.addColumn("Tên vật tư");
        tableModel.addColumn("Mã loại phòng");
        tableModel.addColumn("Tên loại phòng");
        tableModel.addColumn("Số lượng");
        tableModel.addColumn("");
        // Thiết lập tableModel cho JTable
        table.setModel(tableModel);
        
        // Load dữ liệu từ SQL Server vào JTable

        loadDataRoomSuppliesFromSQL();
        setupDeleteRoomSuppliesAction();
    }
    
    private void loadDataRoomSuppliesFromSQL() {
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
            String sqlQuery = "select vt.MaVatTu, vt.TenVatTu, lp.MaLoaiPhong, lp.TenLoaiPhong, ctvt.SoLuong from CTVatTu ctvt "
                + "join VATTU vt on vt.MaVatTu = ctvt.MaVatTu join LOAIPHONG lp on lp.MaLoaiPhong =ctvt.MaLoaiPhong where ctvt.isvisible = '0' ";
            try (PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);
                ResultSet resultSet = preparedStatement.executeQuery()) {

                // Lặp qua kết quả và thêm vào model
                while (resultSet.next()) {
                    Object[] rowData = {
                    resultSet.getObject("MaVatTu"),
                    resultSet.getObject("TenVatTu"),
                    resultSet.getObject("MaLoaiPhong"),
                    resultSet.getObject("TenLoaiPhong"),
                    resultSet.getObject("SoLuong"),
                  
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
    
    private void setupDeleteRoomSuppliesAction() {
        
        TableActionEvent deleteEvent = new TableActionEvent() {
            
            @Override
            public void onDelete(int row) {
                if (table.isEditing()) {
                    table.getCellEditor().stopCellEditing();
                }
                // Lấy dữ liệu từ cột User Name để xóa trong cơ sở dữ liệu
                String userNameToDelete = table.getValueAt(row, 0).toString();
                String userNameToDelete1 = table.getValueAt(row, 2).toString();
                 // Hiển thị hộp thoại xác nhận
                int option = showDeleteConfirmationDialog(userNameToDelete);
                if (option == JOptionPane.YES_OPTION) {
                    // Nếu người dùng đồng ý, xóa dữ liệu từ cơ sở dữ liệu
                    deleteDataRoomSuppliesFromSQL(userNameToDelete,userNameToDelete1);
                    // Xóa dòng trong JTable
                    tableModel.removeRow(row);
                }

            }

            @Override
            public void onFresh(int row) {
                if (table.isEditing()) {
                    table.getCellEditor().stopCellEditing();
                }
                // Lấy dữ liệu từ cột User Name để khôi phục trong cơ sở dữ liệu
                String userNameToUpdate = table.getValueAt(row, 0).toString();
                
                String userNameToUpdate1 = table.getValueAt(row, 2).toString();
                 // Hiển thị hộp thoại xác nhận
                int option = showUpdateConfirmationDialog(userNameToUpdate);
                if (option == JOptionPane.YES_OPTION) {
                    // Nếu người dùng đồng ý, khôi phục dữ liệu từ cơ sở dữ liệu
                    updateDataRoomSuppliesFromSQL(userNameToUpdate,userNameToUpdate1);
                    // Xóa dòng trong JTable
                    tableModel.removeRow(row);
                }
                
            }

         
            
        };

        // Thiết lập renderer và editor cho cột Action
        table.getColumnModel().getColumn(5).setCellRenderer(new TableActionCellRender());
        table.getColumnModel().getColumn(5).setCellEditor(new TableActionCellEditor(deleteEvent));
    }
    
    private void deleteDataRoomSuppliesFromSQL(String MaVatTu, String MaLoaiPhong) {
        // Thực hiện xóa dữ liệu từ SQL tại đây
        Connection connection = null;
        try  {
            String server = "localhost";
            String port = "1433";
            String database = "KLTN";
            String username = "sa";
            String password = "sa";

            String jdbcUrl = "jdbc:sqlserver://" + server + ":" + port + ";databaseName=" + database + ";user=" + username + ";password=" + password;
            connection = DriverManager.getConnection(jdbcUrl);
            String deleteQuery = "DELETE FROM CTVatTu WHERE MaVatTu = ? and MaLoaiPhong = ? ";
            try (PreparedStatement preparedStatement = connection.prepareStatement(deleteQuery)) {
                preparedStatement.setString(1, MaVatTu);
                preparedStatement.setString(2, MaLoaiPhong);

                // Thực hiện câu lệnh DELETE
                int rowsAffected = preparedStatement.executeUpdate();

                if (rowsAffected > 0) {
                    System.out.println("Room Supplies deleted successfully.");
                } else {
                    System.out.println("No Room Supplies found with MaVatTu: " + MaVatTu + " MaLoaiPhong: "+MaLoaiPhong);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error deleting Supplies: " + e.getMessage());
        }
    }
    private void updateDataRoomSuppliesFromSQL(String MaVatTu, String MaLoaiPhong) {
        // Thực hiện xóa dữ liệu từ SQL tại đây
        Connection connection = null;
        try  {
            String server = "localhost";
            String port = "1433";
            String database = "KLTN";
            String username = "sa";
            String password = "sa";

            String jdbcUrl = "jdbc:sqlserver://" + server + ":" + port + ";databaseName=" + database + ";user=" + username + ";password=" + password;
            connection = DriverManager.getConnection(jdbcUrl);
            String deleteQuery = "update CTVatTu set isvisible = '1' WHERE mavattu = ? and maloaiphong = ? ";
            try (PreparedStatement preparedStatement = connection.prepareStatement(deleteQuery)) {
                preparedStatement.setString(1, MaVatTu);
                preparedStatement.setString(2, MaLoaiPhong);
                // Thực hiện câu lệnh DELETE
                int rowsAffected = preparedStatement.executeUpdate();

                if (rowsAffected > 0) {
                    System.out.println("Room Supplies update successfully.");
                } else {
                    System.out.println("No Room Supplies found with MaVatTu: " + MaVatTu + " MaLoaiPhong: "+MaLoaiPhong);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error deleting Supplies: " + e.getMessage());
        }
    }
    
    private void TrashBill(){
        // Khởi tạo model và đặt tên cột
        tableModel = new DefaultTableModel();
        tableModel.addColumn("Mã hóa đơn");
        tableModel.addColumn("Mã phiếu đặt phòng");
        tableModel.addColumn("Ngày thuê");
        tableModel.addColumn("Ngày trả");
        tableModel.addColumn("Mã phòng");
        tableModel.addColumn("Tên phòng");
        tableModel.addColumn("Tên loại phòng");
        tableModel.addColumn("Đơn giá");
        tableModel.addColumn("Giảm giá");
        tableModel.addColumn("VAT");
        tableModel.addColumn("Giá hóa đơn");
        tableModel.addColumn("Ngày lập hóa đơn");
        tableModel.addColumn("");
        // Thiết lập tableModel cho JTable
        table.setModel(tableModel);
        
        // Load dữ liệu từ SQL Server vào JTable
        loadDataBillFromSQL();
        setupDeleteBillAction();
    }
    
    private void loadDataBillFromSQL() {
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
            String sqlQuery = "select MaHoaDonPhong, pdp.MaPhieuDatPhong, NgayDuKienThue, NgayDuKienTra, p.MaPhong, TenPhong, TenLoaiPhong, CONCAT(FORMAT(DonGia, 'N0'),' VND') as DonGia,Concat(GiamGia,'%') as GiamGia, \n" +
"CONCAT(FORMAT( ((DATEDIFF(DAY, NgayDuKienThue, NgayDuKienTra)) *DonGia) * 0.1, 'N0'),' VND') as VAT,CONCAT(FORMAT(((DATEDIFF(DAY, NgayDuKienThue, NgayDuKienTra)) *DonGia)  +  (((DATEDIFF(DAY, NgayDuKienThue, NgayDuKienTra)) *DonGia) * 0.1) - (((DATEDIFF(DAY, NgayDuKienThue, NgayDuKienTra)) *DonGia) *GiamGia /100) , 'N0'),' VND') as GiaHD , NgayLapHoaDon\n" +
"from HoaDonPhong hdp join PhieuDatPhong pdp on hdp.MaPhieuDatPhong = pdp.MaPhieuDatPhong join phong p on p.MaPhong = pdp.MaPhong\n" +
"join DonGiaPhong dgp on dgp.MaDonGiaPhong = p.MaDonGiaPhong join LOAIPHONG lp on lp.MaLoaiPhong = p.MaLoaiPhong\n" +
" where hdp.isvisible = '0'  ";
            try (PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);
                ResultSet resultSet = preparedStatement.executeQuery()) {

                // Lặp qua kết quả và thêm vào model
                while (resultSet.next()) {
                    Object[] rowData = {
                        resultSet.getObject("MaHoaDonPhong"),
                        resultSet.getObject("MaPhieuDatPhong"),
                        resultSet.getObject("NgayDuKienThue"),
                        resultSet.getObject("NgayDuKienTra"),
                        resultSet.getObject("MaPhong"),
                        resultSet.getObject("TenPhong"),
                        resultSet.getObject("TenLoaiPhong"),
                        resultSet.getObject("DonGia"),
                        resultSet.getObject("GiamGia"),
                        resultSet.getObject("VAT"),
                        resultSet.getObject("GiaHD"),
                        resultSet.getObject("NgayLapHoaDon"),
                        
                            
//                            "Action" // Cần thay thế "Action" bằng cột thực tế bạn muốn hiển thị hoặc xử lý
                            
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
    
    private void setupDeleteBillAction() {
        
        TableActionEvent deleteEvent = new TableActionEvent() {
            
            @Override
            public void onDelete(int row) {
                if (table.isEditing()) {
                    table.getCellEditor().stopCellEditing();
                }
                // Lấy dữ liệu từ cột User Name để xóa trong cơ sở dữ liệu
                String userNameToDelete = table.getValueAt(row, 0).toString();
                 // Hiển thị hộp thoại xác nhận
                int option = showDeleteConfirmationDialog(userNameToDelete);
                if (option == JOptionPane.YES_OPTION) {
                    // Nếu người dùng đồng ý, xóa dữ liệu từ cơ sở dữ liệu
                    deleteDataBillFromSQL(userNameToDelete);
                    // Xóa dòng trong JTable
                    tableModel.removeRow(row);
                }

            }

            @Override
            public void onFresh(int row) {
                if (table.isEditing()) {
                    table.getCellEditor().stopCellEditing();
                }
                // Lấy dữ liệu từ cột User Name để khôi phục trong cơ sở dữ liệu
                String userNameToUpdate = table.getValueAt(row, 0).toString();
                 // Hiển thị hộp thoại xác nhận
                int option = showUpdateConfirmationDialog(userNameToUpdate);
                if (option == JOptionPane.YES_OPTION) {
                    // Nếu người dùng đồng ý, khôi phục dữ liệu từ cơ sở dữ liệu
                    updateDataBillFromSQL(userNameToUpdate);
                    // Xóa dòng trong JTable
                    tableModel.removeRow(row);
                }
                
            }

           
        };

        // Thiết lập renderer và editor cho cột Action
        table.getColumnModel().getColumn(12).setCellRenderer(new TableActionCellRender());
        table.getColumnModel().getColumn(12).setCellEditor(new TableActionCellEditor(deleteEvent));
    }
    
    private void deleteDataBillFromSQL(String MaKH) {
        // Thực hiện xóa dữ liệu từ SQL tại đây
        Connection connection = null;
        try  {
            String server = "localhost";
            String port = "1433";
            String database = "KLTN";
            String username = "sa";
            String password = "sa";

            String jdbcUrl = "jdbc:sqlserver://" + server + ":" + port + ";databaseName=" + database + ";user=" + username + ";password=" + password;
            connection = DriverManager.getConnection(jdbcUrl);
            String deleteQuery = "DELETE FROM HoaDonPhong WHERE MaHoaDonPhong = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(deleteQuery)) {
                preparedStatement.setString(1, MaKH);

                // Thực hiện câu lệnh DELETE
                int rowsAffected = preparedStatement.executeUpdate();

                if (rowsAffected > 0) {
                    System.out.println("Bill deleted successfully.");
                } else {
                    System.out.println("No bill found with mã hóa đơn phòng: " + MaKH);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error deleting Customer: " + e.getMessage());
        }
    }
    private void updateDataBillFromSQL(String MaKH) {
        // Thực hiện xóa dữ liệu từ SQL tại đây
        Connection connection = null;
        try  {
            String server = "localhost";
            String port = "1433";
            String database = "KLTN";
            String username = "sa";
            String password = "sa";

            String jdbcUrl = "jdbc:sqlserver://" + server + ":" + port + ";databaseName=" + database + ";user=" + username + ";password=" + password;
            connection = DriverManager.getConnection(jdbcUrl);
            String deleteQuery = "update HoaDonPhong set isvisible = '1' where MaHoaDonPhong = ? ";
            try (PreparedStatement preparedStatement = connection.prepareStatement(deleteQuery)) {
                preparedStatement.setString(1, MaKH);

                // Thực hiện câu lệnh DELETE
                int rowsAffected = preparedStatement.executeUpdate();

                if (rowsAffected > 0) {
                    System.out.println("Customer update successfully.");
                } else {
                    System.out.println("No Customer found with MaKH: " + MaKH);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error deleting Customer: " + e.getMessage());
        }
    }
    

    private void TrashBillService(){
        // Khởi tạo model và đặt tên cột
        tableModel = new DefaultTableModel();
        tableModel.addColumn("Mã dịch vụ");
        tableModel.addColumn("Tên dịch vụ");
        tableModel.addColumn("Mã nhân viên");
        tableModel.addColumn("Tên nhân viên");
        tableModel.addColumn("Mã khách hàng");
        tableModel.addColumn("Họ khách hàng");
        tableModel.addColumn("Tên khách hàng");
        tableModel.addColumn("Ngày lập hóa đơn");
        tableModel.addColumn("Đơn giá");
        tableModel.addColumn("Giảm giá");
        tableModel.addColumn("Số lượng");
        tableModel.addColumn("VAT");
        tableModel.addColumn("Giá hóa đơn");
        tableModel.addColumn("");
        // Thiết lập tableModel cho JTable
        table.setModel(tableModel);
        
        // Load dữ liệu từ SQL Server vào JTable
        loadDataBillServiceFromSQL();
        setupDeleteBillServiceAction();
    }
    
    private void loadDataBillServiceFromSQL() {
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
            String sqlQuery = "select dv.MaDV, TenDichVu, nv.MaNV, TenNV, kh.MaKH,HoKH,  TenKH,NgayLapHD, CONCAT(FORMAT(DonGia, 'N0'),' VND') as DonGia,Concat(GiamGia,'%') as GiamGia, SL, \n" +
"CONCAT(FORMAT(((DonGia*SL)*0.1), 'N0'),' VND') as VAT, CONCAT(FORMAT(((DonGia*SL) +  ((DonGia*SL)*0.1) - ((DonGia * SL)*GiamGia/100)), 'N0'),' VND') as GiaHD\n" +
"from HoaDonDV hddv join NHANVIEN nv on hddv.MaNV = nv.MaNV join KHACHHANG kh on hddv.MaKH = kh.MaKH \n" +
"join DICHVU dv on dv.MaDV = hddv.MaDV join dongiadv dgdv on dv.MaDonGiaDV = dgdv.MaDonGiaDV \n" +
"where hddv.isvisible = '0' ";
            try (PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);
                ResultSet resultSet = preparedStatement.executeQuery()) {

                // Lặp qua kết quả và thêm vào model
                while (resultSet.next()) {
                    Object[] rowData = {
                        resultSet.getObject("MaDV"),
                        resultSet.getObject("TenDichVu"),
                        resultSet.getObject("MaNV"),
                        resultSet.getObject("TenNV"),
                        resultSet.getObject("MaKH"),
                        resultSet.getObject("HoKH"),
                        resultSet.getObject("TenKH"),
                        resultSet.getObject("NgayLapHD"),
                        resultSet.getObject("DonGia"),
                        resultSet.getObject("GiamGia"),
                        resultSet.getObject("SL"),
                        resultSet.getObject("VAT"),
                        resultSet.getObject("GiaHD"),
//                            "Action" // Cần thay thế "Action" bằng cột thực tế bạn muốn hiển thị hoặc xử lý
                            
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
    
    private void setupDeleteBillServiceAction() {
        
        TableActionEvent deleteEvent = new TableActionEvent() {
            
            @Override
            public void onDelete(int row) {
                if (table.isEditing()) {
                    table.getCellEditor().stopCellEditing();
                }
                // Lấy dữ liệu từ cột User Name để xóa trong cơ sở dữ liệu
                String madv = table.getValueAt(row, 0).toString();
                String manv = table.getValueAt(row, 2).toString();
                String makh = table.getValueAt(row, 4).toString();
                 // Hiển thị hộp thoại xác nhận
                int option = showDeleteConfirmationDialog(madv);
                if (option == JOptionPane.YES_OPTION) {
                    // Nếu người dùng đồng ý, xóa dữ liệu từ cơ sở dữ liệu
                    deleteDataBillServiceFromSQL(madv, manv, makh);
                    // Xóa dòng trong JTable
                    tableModel.removeRow(row);
                }

            }

            @Override
            public void onFresh(int row) {
                if (table.isEditing()) {
                    table.getCellEditor().stopCellEditing();
                }
                // Lấy dữ liệu từ cột User Name để khôi phục trong cơ sở dữ liệu
                String madv = table.getValueAt(row, 0).toString();
                String manv = table.getValueAt(row, 2).toString();
                String makh = table.getValueAt(row, 4).toString();
                 // Hiển thị hộp thoại xác nhận
                int option = showUpdateConfirmationDialog(madv);
                if (option == JOptionPane.YES_OPTION) {
                    // Nếu người dùng đồng ý, khôi phục dữ liệu từ cơ sở dữ liệu
                    updateDataBillFromSQL(madv, manv,makh);
                    // Xóa dòng trong JTable
                    tableModel.removeRow(row);
                }
                
            }

           
        };

        // Thiết lập renderer và editor cho cột Action
        table.getColumnModel().getColumn(13).setCellRenderer(new TableActionCellRender());
        table.getColumnModel().getColumn(13).setCellEditor(new TableActionCellEditor(deleteEvent));
    }
    
    private void deleteDataBillServiceFromSQL(String MaDV, String MaNV, String MaKH) {
        // Thực hiện xóa dữ liệu từ SQL tại đây
        Connection connection = null;
        try  {
            String server = "localhost";
            String port = "1433";
            String database = "KLTN";
            String username = "sa";
            String password = "sa";

            String jdbcUrl = "jdbc:sqlserver://" + server + ":" + port + ";databaseName=" + database + ";user=" + username + ";password=" + password;
            connection = DriverManager.getConnection(jdbcUrl);
            String deleteQuery = "DELETE FROM HoaDonDV WHERE MaDV = ? and MaNV = ? and MaKH = ? ";
            try (PreparedStatement preparedStatement = connection.prepareStatement(deleteQuery)) {
                preparedStatement.setString(1, MaDV);
                preparedStatement.setString(2, MaNV);
                preparedStatement.setString(3, MaKH);

                // Thực hiện câu lệnh DELETE
                int rowsAffected = preparedStatement.executeUpdate();

                if (rowsAffected > 0) {
                    System.out.println("Bill service deleted successfully.");
                } else {
                    System.out.println("No bill service found with mã dịch vụ: " + MaDV +" mã nhân viên" + MaNV + " mã khách hàng " +MaKH);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error deleting bill service: " + e.getMessage());
        }
    }
    private void updateDataBillFromSQL(String MaDV, String MaNV, String MaKH) {
        // Thực hiện xóa dữ liệu từ SQL tại đây
        Connection connection = null;
        try  {
            String server = "localhost";
            String port = "1433";
            String database = "KLTN";
            String username = "sa";
            String password = "sa";

            String jdbcUrl = "jdbc:sqlserver://" + server + ":" + port + ";databaseName=" + database + ";user=" + username + ";password=" + password;
            connection = DriverManager.getConnection(jdbcUrl);
            String deleteQuery = "update HoaDonDV set isvisible = '1' WHERE MaDV = ? and MaNV = ? and MaKH = ? ";
            try (PreparedStatement preparedStatement = connection.prepareStatement(deleteQuery)) {
                preparedStatement.setString(1, MaDV);
                preparedStatement.setString(2, MaNV);
                preparedStatement.setString(3, MaKH);

                // Thực hiện câu lệnh DELETE
                int rowsAffected = preparedStatement.executeUpdate();

                if (rowsAffected > 0) {
                    System.out.println("Customer update successfully.");
                } else {
                    System.out.println("No Customer found with MaKH: " + MaKH);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error deleting Customer: " + e.getMessage());
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

        panelBorder3 = new swing.PanelBorder();
        jScrollPane1 = new javax.swing.JScrollPane();
        table = new javax.swing.JTable();
        cmbLTK3 = new javax.swing.JComboBox<>();

        panelBorder3.setBackground(new java.awt.Color(36, 87, 157));
        panelBorder3.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "System Trash:", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 20), new java.awt.Color(255, 255, 255))); // NOI18N
        panelBorder3.setForeground(new java.awt.Color(255, 255, 255));

        table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "User Name", "Display Name", "PassWord", "Type Account", "Action"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, true, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        table.setRowHeight(40);
        jScrollPane1.setViewportView(table);

        cmbLTK3.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Trash Accounts", "Trash Customers", "Trash Bills", "Trash Staffs", "Trash Services", "Trash Rooms", "Trash Supplies", "Trash Room Supplies", "Trash Service Bills" }));
        cmbLTK3.setActionCommand("cmbSelectItem");
        cmbLTK3.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cmbLTK3ItemStateChanged(evt);
            }
        });

        javax.swing.GroupLayout panelBorder3Layout = new javax.swing.GroupLayout(panelBorder3);
        panelBorder3.setLayout(panelBorder3Layout);
        panelBorder3Layout.setHorizontalGroup(
            panelBorder3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBorder3Layout.createSequentialGroup()
                .addGap(38, 38, 38)
                .addGroup(panelBorder3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cmbLTK3, javax.swing.GroupLayout.PREFERRED_SIZE, 266, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 1006, Short.MAX_VALUE))
                .addGap(38, 38, 38))
        );
        panelBorder3Layout.setVerticalGroup(
            panelBorder3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBorder3Layout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addComponent(cmbLTK3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(57, 57, 57)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 484, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(38, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(panelBorder3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(panelBorder3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void cmbLTK3ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cmbLTK3ItemStateChanged
        // TODO add your handling code here:
        if (evt.getStateChange() == ItemEvent.SELECTED) {

            
            int selectedIndex = cmbLTK3.getSelectedIndex();
            
            // Kiểm tra chỉ số và thực hiện hành động tương ứng
            if (selectedIndex == 0) {
                // Nếu là item 0, gọi hàm TrashAccount
                TrashAccount();
            } else if (selectedIndex == 1) {
                // Nếu là item 1, in chữ "Hello World" ra màn hình
                System.out.println("Hello World");
                TrashCustomers();
            }else if (selectedIndex == 2) {
                // Nếu là item 1, in chữ "Hello World" ra màn hình
                System.out.println("Hello World");
                TrashBill();
            }else if (selectedIndex == 3) {
                // Nếu là item 1, in chữ "Hello World" ra màn hình
                System.out.println("Hello World");
                TrashStaff();
            }else if (selectedIndex == 4) {
                // Nếu là item 1, in chữ "Hello World" ra màn hình
                System.out.println("Hello World");
                TrashService();
            }else if (selectedIndex == 5) {
                // Nếu là item 1, in chữ "Hello World" ra màn hình
                System.out.println("Hello World");
                TrashRoom();
            }else if (selectedIndex == 6) {
                // Nếu là item 1, in chữ "Hello World" ra màn hình
                System.out.println("Hello World");
                TrashSupplies();
            }else if (selectedIndex == 7) {
                // Nếu là item 1, in chữ "Hello World" ra màn hình
                System.out.println("Hello World");
                TrashRoomSupplies();
            }else if (selectedIndex == 8) {
                // Nếu là item 1, in chữ "Hello World" ra màn hình
                System.out.println("Hello World");
                TrashBillService();
            }
        }
    }//GEN-LAST:event_cmbLTK3ItemStateChanged


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> cmbLTK3;
    private javax.swing.JScrollPane jScrollPane1;
    private swing.PanelBorder panelBorder3;
    private javax.swing.JTable table;
    // End of variables declaration//GEN-END:variables
}
