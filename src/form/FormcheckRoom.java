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
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author TeeDee
 */
public class FormcheckRoom extends javax.swing.JFrame {

    /**
     * Creates new form FormcheckRoom
     */
    public FormcheckRoom() {
        initComponents();
    }
    
    Connect cn = new Connect();
    Connection conn;
    private DefaultTableModel tableModel;
    

    private String username, password, quyen, DisplayName, ngaythue, ngaytra;
    
    public FormcheckRoom(String username, String password, String DisplayName, String quyen, String ngaythue, String ngaytra) {
        initComponents();
        this.username = username;
        this.password = password;
        this.DisplayName = DisplayName;
        this.quyen = quyen;
        this.ngaythue = ngaythue;
        this.ngaytra = ngaytra;
        
        txtngayThuePhong.setText(ngaythue);
        txtngayTraPhong.setText(ngaytra);
        initCombobox_maloaiphong();
        loaddata();
    }

    private void initCombobox_maloaiphong() {
        try {
            conn = cn.getConnection();
            String sql = "select TenLoaiPhong from LOAIPHONG ";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();
            cmbLoaiPhong.removeAllItems();
          
            while (rs.next()) {
                cmbLoaiPhong.addItem(rs.getString("TenLoaiPhong"));
            }
            rs.close();
            pstmt.close();
            conn.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
            e.printStackTrace();
        }
    }
    
    private void loaddata(){
        // Khởi tạo model và đặt tên cột
        tableModel = new DefaultTableModel();
        tableModel.addColumn("Mã phòng");
        tableModel.addColumn("Tên phòng");
        tableModel.addColumn("Tên loại phòng");
        tableModel.addColumn("Tên vật tư");
        tableModel.addColumn("Số lượng");
        tableModel.addColumn("Giảm giá");
        
        // Thiết lập tableModel cho JTable
        table.setModel(tableModel);
        
        // Load dữ liệu từ SQL Server vào JTable
        loadDataFromSQL();
        
    }
    
    private void loadDataFromSQL() {
        Connection connection = null;
        try {
             // Kết nối đến cơ sở dữ liệu SQL Server
            String ngaythuephong = txtngayThuePhong.getText();
            String ngaytraphong = txtngayTraPhong.getText();
            String server = "localhost";
            String port = "1433";
            String database = "KLTN";
            String username = "sa";
            String password = "sa";

            String jdbcUrl = "jdbc:sqlserver://" + server + ":" + port + ";databaseName=" + database + ";user=" + username + ";password=" + password;
            connection = DriverManager.getConnection(jdbcUrl);
            
            // Thực hiện truy vấn SQL để lấy dữ liệu từ bảng
            
            String sql = "select distinct p.MaPhong, TenPhong, TenLoaiPhong, TenVatTu, SoLuong , Concat(GiamGia,'%') as GiamGia\n" +
"from phong p join LOAIPHONG lp on p.MaLoaiPhong = lp.MaLoaiPhong join CTVatTu ctvt on ctvt.MaLoaiPhong = lp.MaLoaiPhong join VATTU vt on vt.MaVatTu = ctvt.MaVatTu\n" +
"where TenVatTu = N'Giường' and MaTinhtrangphong = 'TT2' and p.isvisible = '1' and p.MaPhong not in (select distinct MaPhong from PhieuDatPhong where booked_status = 0 \n" +
"and (NgayDuKienThue between '" + ngaythuephong + "' AND '" + ngaytraphong + "' or  NgayDuKienTra between '" + ngaythuephong + "' AND '" + ngaytraphong + "' ) ) \n" +
"order by p.MaPhong ";
            
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql);
                ResultSet resultSet = preparedStatement.executeQuery()) {

                // Lặp qua kết quả và thêm vào model
                while (resultSet.next()) {
                    Object[] rowData = {
                            resultSet.getObject("MaPhong"),
                            resultSet.getObject("TenPhong"),
                            resultSet.getObject("TenLoaiPhong"),
                            resultSet.getObject("TenVatTu"),
                            resultSet.getObject("SoLuong"),
                            resultSet.getObject("GiamGia"),
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
    
    private void loaiphong(){
        // Khởi tạo model và đặt tên cột
        tableModel = new DefaultTableModel();
        tableModel.addColumn("Mã phòng");
        tableModel.addColumn("Tên phòng");
        tableModel.addColumn("Tên loại phòng");
        tableModel.addColumn("Tên vật tư");
        tableModel.addColumn("Số lượng");
        tableModel.addColumn("Giảm giá");
        
        // Thiết lập tableModel cho JTable
        table.setModel(tableModel);
        
        // Load dữ liệu từ SQL Server vào JTable
        loadDataFromSQL_loaiphong();
        
    }
    
    
    private void loadDataFromSQL_loaiphong() {
        
        String tenloaiphong = cmbLoaiPhong.getSelectedItem().toString();
        Connection connection = null;
        try {
            
             // Kết nối đến cơ sở dữ liệu SQL Server
            String ngaythuephong = txtngayThuePhong.getText();
            String ngaytraphong = txtngayTraPhong.getText();
            String server = "localhost";
            String port = "1433";
            String database = "KLTN";
            String username = "sa";
            String password = "sa";

            String jdbcUrl = "jdbc:sqlserver://" + server + ":" + port + ";databaseName=" + database + ";user=" + username + ";password=" + password;
            connection = DriverManager.getConnection(jdbcUrl);
            
            // Thực hiện truy vấn SQL để lấy dữ liệu từ bảng
            
            String sql = "select distinct p.MaPhong, TenPhong, TenLoaiPhong, TenVatTu, SoLuong , Concat(GiamGia,'%') as GiamGia\n" +
"from phong p join LOAIPHONG lp on p.MaLoaiPhong = lp.MaLoaiPhong join CTVatTu ctvt on ctvt.MaLoaiPhong = lp.MaLoaiPhong join VATTU vt on vt.MaVatTu = ctvt.MaVatTu\n" +
"where TenVatTu = N'Giường' and MaTinhtrangphong = 'TT2' and tenloaiphong = N'"+tenloaiphong+"' and p.isvisible = '1' and p.MaPhong not in (select distinct MaPhong from PhieuDatPhong where booked_status = 0 \n" +
"and (NgayDuKienThue between '" + ngaythuephong + "' AND '" + ngaytraphong + "' or  NgayDuKienTra between '" + ngaythuephong + "' AND '" + ngaytraphong + "' ) ) \n" +
"order by p.MaPhong ";
            
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql);
                ResultSet resultSet = preparedStatement.executeQuery()) {

                // Lặp qua kết quả và thêm vào model
                while (resultSet.next()) {
                    Object[] rowData = {
                            resultSet.getObject("MaPhong"),
                            resultSet.getObject("TenPhong"),
                            resultSet.getObject("TenLoaiPhong"),
                            resultSet.getObject("TenVatTu"),
                            resultSet.getObject("SoLuong"),
                            resultSet.getObject("GiamGia"),
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

    private void soluong(){
        // Khởi tạo model và đặt tên cột
        tableModel = new DefaultTableModel();
        tableModel.addColumn("Mã phòng");
        tableModel.addColumn("Tên phòng");
        tableModel.addColumn("Tên loại phòng");
        tableModel.addColumn("Tên vật tư");
        tableModel.addColumn("Số lượng");
        tableModel.addColumn("Giảm giá");
        
        // Thiết lập tableModel cho JTable
        table.setModel(tableModel);
        
        // Load dữ liệu từ SQL Server vào JTable
        loadDataFromSQL_soluong();
        
    }
    
    
    private void loadDataFromSQL_soluong() {
        
        Object selectedValue = spSL.getValue();
        int spinnerValue = (int) selectedValue;
        if (spinnerValue > 0){
            
            Connection connection = null;
            try {

                 // Kết nối đến cơ sở dữ liệu SQL Server
                String ngaythuephong = txtngayThuePhong.getText();
                String ngaytraphong = txtngayTraPhong.getText();
                String server = "localhost";
                String port = "1433";
                String database = "KLTN";
                String username = "sa";
                String password = "sa";

                String jdbcUrl = "jdbc:sqlserver://" + server + ":" + port + ";databaseName=" + database + ";user=" + username + ";password=" + password;
                connection = DriverManager.getConnection(jdbcUrl);

                // Thực hiện truy vấn SQL để lấy dữ liệu từ bảng

                String sql = "select distinct p.MaPhong, TenPhong, TenLoaiPhong, TenVatTu, SoLuong , Concat(GiamGia,'%') as GiamGia\n" +
                "from phong p join LOAIPHONG lp on p.MaLoaiPhong = lp.MaLoaiPhong join CTVatTu ctvt on ctvt.MaLoaiPhong = lp.MaLoaiPhong join VATTU vt on vt.MaVatTu = ctvt.MaVatTu\n" +
                "where TenVatTu = N'Giường' and MaTinhtrangphong = 'TT2' and soluong = "+spinnerValue+"  and p.isvisible = '1' and p.MaPhong not in (select distinct MaPhong from PhieuDatPhong where booked_status = 0 \n" +
                "and (NgayDuKienThue between '" + ngaythuephong + "' AND '" + ngaytraphong + "' or  NgayDuKienTra between '" + ngaythuephong + "' AND '" + ngaytraphong + "' ) ) \n" +
                "order by p.MaPhong ";

                try (PreparedStatement preparedStatement = connection.prepareStatement(sql);
                    ResultSet resultSet = preparedStatement.executeQuery()) {

                    // Lặp qua kết quả và thêm vào model
                    while (resultSet.next()) {
                        Object[] rowData = {
                                resultSet.getObject("MaPhong"),
                                resultSet.getObject("TenPhong"),
                                resultSet.getObject("TenLoaiPhong"),
                                resultSet.getObject("TenVatTu"),
                                resultSet.getObject("SoLuong"),
                                resultSet.getObject("GiamGia"),
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
       
        }else {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn số lượng dịch vụ lớn hơn 0");
        }

    }
    
    private void cahai(){
        // Khởi tạo model và đặt tên cột
        tableModel = new DefaultTableModel();
        tableModel.addColumn("Mã phòng");
        tableModel.addColumn("Tên phòng");
        tableModel.addColumn("Tên loại phòng");
        tableModel.addColumn("Tên vật tư");
        tableModel.addColumn("Số lượng");
        tableModel.addColumn("Giảm giá");
        
        // Thiết lập tableModel cho JTable
        table.setModel(tableModel);
        
        // Load dữ liệu từ SQL Server vào JTable
        loadDataFromSQL_cahai();
        
    }
    
    
    private void loadDataFromSQL_cahai() {
        
        String tenloaiphong = cmbLoaiPhong.getSelectedItem().toString();
        
        Object selectedValue = spSL.getValue();
        int spinnerValue = (int) selectedValue;
        if (spinnerValue > 0){
            
            Connection connection = null;
            try {

                 // Kết nối đến cơ sở dữ liệu SQL Server
                String ngaythuephong = txtngayThuePhong.getText();
                String ngaytraphong = txtngayTraPhong.getText();
                String server = "localhost";
                String port = "1433";
                String database = "KLTN";
                String username = "sa";
                String password = "sa";

                String jdbcUrl = "jdbc:sqlserver://" + server + ":" + port + ";databaseName=" + database + ";user=" + username + ";password=" + password;
                connection = DriverManager.getConnection(jdbcUrl);

                // Thực hiện truy vấn SQL để lấy dữ liệu từ bảng

                String sql = "select distinct p.MaPhong, TenPhong, TenLoaiPhong, TenVatTu, SoLuong , Concat(GiamGia,'%') as GiamGia\n" +
                "from phong p join LOAIPHONG lp on p.MaLoaiPhong = lp.MaLoaiPhong join CTVatTu ctvt on ctvt.MaLoaiPhong = lp.MaLoaiPhong join VATTU vt on vt.MaVatTu = ctvt.MaVatTu\n" +
                "where TenVatTu = N'Giường' and MaTinhtrangphong = 'TT2' and tenloaiphong = N'"+tenloaiphong+"' and soluong = "+spinnerValue+"  and p.isvisible = '1' and p.MaPhong not in (select distinct MaPhong from PhieuDatPhong where booked_status = 0 \n" +
                "and (NgayDuKienThue between '" + ngaythuephong + "' AND '" + ngaytraphong + "' or  NgayDuKienTra between '" + ngaythuephong + "' AND '" + ngaytraphong + "' ) ) \n" +
                "order by p.MaPhong ";

                try (PreparedStatement preparedStatement = connection.prepareStatement(sql);
                    ResultSet resultSet = preparedStatement.executeQuery()) {

                    // Lặp qua kết quả và thêm vào model
                    while (resultSet.next()) {
                        Object[] rowData = {
                                resultSet.getObject("MaPhong"),
                                resultSet.getObject("TenPhong"),
                                resultSet.getObject("TenLoaiPhong"),
                                resultSet.getObject("TenVatTu"),
                                resultSet.getObject("SoLuong"),
                                resultSet.getObject("GiamGia"),
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
       
        }else {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn số lượng dịch vụ lớn hơn 0");
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
        panelBorder5 = new swing.PanelBorder();
        jSeparator1 = new javax.swing.JSeparator();
        cmbLoaiPhong = new javax.swing.JComboBox<>();
        jLabel43 = new javax.swing.JLabel();
        ckLoaiPhong = new javax.swing.JCheckBox();
        ckSoGiuong = new javax.swing.JCheckBox();
        spSL = new component.Spinner();
        txtngayThuePhong = new component.TextField();
        txtngayTraPhong = new component.TextField();
        btnLoc = new swing.Button();
        btnLoc1 = new swing.Button();
        jScrollPane2 = new javax.swing.JScrollPane();
        table = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setAlwaysOnTop(true);

        panelBorder3.setBackground(new java.awt.Color(36, 87, 157));
        panelBorder3.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Hệ thống phòng", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 20), new java.awt.Color(255, 255, 255))); // NOI18N
        panelBorder3.setForeground(new java.awt.Color(255, 255, 255));

        panelBorder5.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Lọc thông tin phòng", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 20))); // NOI18N

        cmbLoaiPhong.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cmbLoaiPhongItemStateChanged(evt);
            }
        });
        cmbLoaiPhong.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbLoaiPhongActionPerformed(evt);
            }
        });

        jLabel43.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        jLabel43.setText("Lọc thông tin phòng nâng cao:");

        ckLoaiPhong.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        ckLoaiPhong.setText("Loại phòng:");

        ckSoGiuong.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        ckSoGiuong.setText("Số giường:");

        spSL.setLabelText("Số lượng");

        txtngayThuePhong.setEditable(false);
        txtngayThuePhong.setBackground(new java.awt.Color(255, 255, 255));
        txtngayThuePhong.setLabelText("Ngày thuê phòng");

        txtngayTraPhong.setEditable(false);
        txtngayTraPhong.setBackground(new java.awt.Color(255, 255, 255));
        txtngayTraPhong.setLabelText("Ngày trả phòng");
        txtngayTraPhong.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtngayTraPhongActionPerformed(evt);
            }
        });

        btnLoc.setText("Lọc");
        btnLoc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLocActionPerformed(evt);
            }
        });

        btnLoc1.setText("Xem");
        btnLoc1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLoc1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelBorder5Layout = new javax.swing.GroupLayout(panelBorder5);
        panelBorder5.setLayout(panelBorder5Layout);
        panelBorder5Layout.setHorizontalGroup(
            panelBorder5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jSeparator1, javax.swing.GroupLayout.Alignment.TRAILING)
            .addGroup(panelBorder5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelBorder5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel43)
                    .addGroup(panelBorder5Layout.createSequentialGroup()
                        .addGap(43, 43, 43)
                        .addGroup(panelBorder5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(ckLoaiPhong)
                            .addComponent(ckSoGiuong))
                        .addGap(33, 33, 33)
                        .addGroup(panelBorder5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(cmbLoaiPhong, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(spSL, javax.swing.GroupLayout.DEFAULT_SIZE, 285, Short.MAX_VALUE))
                        .addGap(165, 165, 165)
                        .addGroup(panelBorder5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(btnLoc1, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnLoc, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(391, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelBorder5Layout.createSequentialGroup()
                .addGap(87, 87, 87)
                .addComponent(txtngayThuePhong, javax.swing.GroupLayout.PREFERRED_SIZE, 433, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 132, Short.MAX_VALUE)
                .addComponent(txtngayTraPhong, javax.swing.GroupLayout.PREFERRED_SIZE, 433, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(66, 66, 66))
        );
        panelBorder5Layout.setVerticalGroup(
            panelBorder5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBorder5Layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(panelBorder5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtngayThuePhong, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtngayTraPhong, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel43)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 25, Short.MAX_VALUE)
                .addGroup(panelBorder5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cmbLoaiPhong, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ckLoaiPhong)
                    .addComponent(btnLoc, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(panelBorder5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(ckSoGiuong)
                    .addComponent(spSL, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnLoc1, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(44, 44, 44))
        );

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
        jScrollPane2.setViewportView(table);

        javax.swing.GroupLayout panelBorder3Layout = new javax.swing.GroupLayout(panelBorder3);
        panelBorder3.setLayout(panelBorder3Layout);
        panelBorder3Layout.setHorizontalGroup(
            panelBorder3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBorder3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelBorder3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(panelBorder5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane2))
                .addContainerGap())
        );
        panelBorder3Layout.setVerticalGroup(
            panelBorder3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelBorder3Layout.createSequentialGroup()
                .addGap(65, 65, 65)
                .addComponent(panelBorder5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(32, 32, 32)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 461, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
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

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void cmbLoaiPhongItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cmbLoaiPhongItemStateChanged
        // TODO add your handling code here:
       
    }//GEN-LAST:event_cmbLoaiPhongItemStateChanged

    private void cmbLoaiPhongActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbLoaiPhongActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cmbLoaiPhongActionPerformed

    private void txtngayTraPhongActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtngayTraPhongActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtngayTraPhongActionPerformed

    private void btnLoc1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLoc1ActionPerformed
        // TODO add your handling code here:
        loaddata();
    }//GEN-LAST:event_btnLoc1ActionPerformed

    private void btnLocActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLocActionPerformed
        // TODO add your handling code here:
        if (ckLoaiPhong.isSelected() == true && ckSoGiuong.isSelected() == false){
            System.out.println("loại phòng");
            loaiphong();
        }else if(ckLoaiPhong.isSelected() == false && ckSoGiuong.isSelected() == true){
            System.out.println("Số giường");
            soluong();
        }else if (ckLoaiPhong.isSelected() == true && ckSoGiuong.isSelected() == true){
            System.out.println("cả 2");
            cahai();
        }else if (ckLoaiPhong.isSelected() == false && ckSoGiuong.isSelected() == false){
            System.out.println("chọn lọc");
            JOptionPane.showMessageDialog(this, "Vui lòng chọn phương thức lọc");
        }
    }//GEN-LAST:event_btnLocActionPerformed

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
            java.util.logging.Logger.getLogger(FormcheckRoom.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FormcheckRoom.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FormcheckRoom.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FormcheckRoom.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FormcheckRoom().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private swing.Button btnLoc;
    private swing.Button btnLoc1;
    private javax.swing.JCheckBox ckLoaiPhong;
    private javax.swing.JCheckBox ckSoGiuong;
    private javax.swing.JComboBox<String> cmbLoaiPhong;
    private javax.swing.JLabel jLabel43;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSeparator jSeparator1;
    private swing.PanelBorder panelBorder3;
    private swing.PanelBorder panelBorder5;
    private component.Spinner spSL;
    private javax.swing.JTable table;
    private component.TextField txtngayThuePhong;
    private component.TextField txtngayTraPhong;
    // End of variables declaration//GEN-END:variables
}
