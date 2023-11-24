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
public class FormTrashAccount extends javax.swing.JPanel {

    /**
     * Creates new form FormTrashAccount
     */
    Connect cn = new Connect();
    Connection conn;
    private DefaultTableModel tableModel;
    
    public FormTrashAccount() {
        initComponents();
        TrashAccount();
        
    }
    
    private void TrashAccount(){
        // Khởi tạo model và đặt tên cột
        tableModel = new DefaultTableModel();
        tableModel.addColumn("User Name");
        tableModel.addColumn("Display Name");
        tableModel.addColumn("Password");
        tableModel.addColumn("Type Account");
        tableModel.addColumn("Action");
        
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
        table.getColumnModel().getColumn(4).setCellRenderer(new TableActionCellRender());
        table.getColumnModel().getColumn(4).setCellEditor(new TableActionCellEditor(deleteEvent));
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
            String sqlQuery = "SELECT UserName, DisplayName, PassWorrd, Typpe FROM Account where isvisible = '0' and ( Typpe = 'Admin' or Typpe = 'User')   ";
            try (PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);
                ResultSet resultSet = preparedStatement.executeQuery()) {

                // Lặp qua kết quả và thêm vào model
                while (resultSet.next()) {
                    Object[] rowData = {
                            resultSet.getObject("UserName"),
                            resultSet.getObject("DisplayName"),
                            resultSet.getObject("PassWorrd"),
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
        tableModel.addColumn("User Name hi hi");
        tableModel.addColumn("Display Name");
        tableModel.addColumn("Password");
        tableModel.addColumn("Type Account");
        tableModel.addColumn("Action");
        
        // Thiết lập tableModel cho JTable
        table.setModel(tableModel);
        
        // Load dữ liệu từ SQL Server vào JTable
//        loadDataFromSQL();
//        setupDeleteAction();
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

        cmbLTK3.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Trash Account", "Trash Customer", "Trash Staff", " " }));
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
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 1006, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(38, Short.MAX_VALUE))
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
//            // Lấy giá trị của mục được chọn
//            Object selectedValue = cmbLTK3.getSelectedItem();
//
//            // Kiểm tra và xử lý giá trị của mục được chọn
//            if (selectedValue != null) {
//                String selectedItem = selectedValue.toString();
//                System.out.println("Selected Item: " + selectedItem);
//
//                TrashAccount();
//                
//            }
            
            int selectedIndex = cmbLTK3.getSelectedIndex();
            
            // Kiểm tra chỉ số và thực hiện hành động tương ứng
            if (selectedIndex == 0) {
                // Nếu là item 0, gọi hàm TrashAccount
                TrashAccount();
            } else if (selectedIndex == 1) {
                // Nếu là item 1, in chữ "Hello World" ra màn hình
                System.out.println("Hello World");
                TrashCustomers();
            }
        }
    }//GEN-LAST:event_cmbLTK3ItemStateChanged


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private swing.Button btnSearch;
    private javax.swing.JComboBox<String> cmbLTK3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField jTextField;
    private swing.PanelBorder panelBorder2;
    private swing.PanelBorder panelBorder3;
    private javax.swing.JRadioButton rbMAKH1;
    private javax.swing.JRadioButton rbNGAYSINH1;
    private javax.swing.JRadioButton rbSDT1;
    private javax.swing.JTable table;
    private javax.swing.JTextField txtSEARCHMAKH;
    // End of variables declaration//GEN-END:variables
}
