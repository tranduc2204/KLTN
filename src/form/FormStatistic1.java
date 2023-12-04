/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package form;

import connect.Connect;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import model.ModelStatistic1;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.view.JasperViewer;

/**
 *
 * @author TeeDee
 */
public class FormStatistic1 extends javax.swing.JPanel {

    /**
     * Creates new form FormStatistic1
     */
    Connect cn = new Connect();
    Connection conn;
    private DefaultTableModel tableModel;
    
    
    public FormStatistic1() {
        initComponents();
        Statistics();
    }
    
    private String username, password, quyen, DisplayName;
    
    public FormStatistic1(String username, String password, String DisplayName, String quyen) {
        initComponents();
        this.username = username;
        this.password = password;
        this.DisplayName = DisplayName;
        this.quyen = quyen;
        
      
        Statistics();
        loadDataFromSQL();
        ThemN();
    }
    
    private void Statistics(){
        // Khởi tạo model và đặt tên cột
        tableModel = new DefaultTableModel();
        tableModel.addColumn("Mã hóa đơn");
        tableModel.addColumn("Tên nhân viên");
        tableModel.addColumn("Tên khách hàng");
        tableModel.addColumn("Tên phòng");
        tableModel.addColumn("Ngày lập hóa đơn");
        tableModel.addColumn("Đơn giá");
      
        
        // Thiết lập tableModel cho JTable
        table.setModel(tableModel);
    }
    
    public void ThemN() {
        Calendar calendar = Calendar.getInstance();
        int currentYear = calendar.get(Calendar.YEAR);

        cmbNam1.removeAllItems();
        for (int i = 2000; i <= currentYear; i++) {
            cmbNam1.addItem(Integer.toString(i));
        }
        cmbThang1.removeAllItems();
        for (int i = 1; i <= 12; i++) {
            cmbThang1.addItem(String.valueOf(i));
        }
        cmbNam2.removeAllItems();
        for (int i = 2000; i <= currentYear; i++) {
            cmbNam2.addItem(Integer.toString(i));
        }
    }
    
    private void loaddoanhthuthangofnam(){

        // Thực hiện truy vấn SQL để lấy dữ liệu từ bảng
        String thang = cmbThang1.getSelectedItem().toString();
        String nam = cmbNam1.getSelectedItem().toString();
        try {
            conn = cn.getConnection();
            String sqlQuery = "select sum(Tien) as doanhthu, month(NgayLapHoaDon) as thang, year(NgayLapHoaDon) as nam from HoaDonPhong hdp join PhieuThuePhong ptp on hdp.MaPhieuThuePhong = ptp.MaPhieuThuePhong \n" +
"join PhieuDatPhong pdp on pdp.MaPhieuDatPhong = ptp.MaPhieuDatPhong join PHONG p on p.MaPhong = pdp.MaPhong \n" +
"join NHANVIEN nv on nv.MaNV = pdp.MaNV join KHACHHANG kh on kh.MaKH = pdp.MaKH \n" +
"group by month(NgayLapHoaDon), year(NgayLapHoaDon)\n" +
"having month(NgayLapHoaDon) = ? and YEAR (NgayLapHoaDon) = ? ";

            PreparedStatement preparedStatement = conn.prepareStatement(sqlQuery);

            preparedStatement.setString(1, thang);
            preparedStatement.setString(2, nam);

            ResultSet resultSet = preparedStatement.executeQuery();
            
            float doanhthu;
            // Lặp qua kết quả và thêm vào model
            if (resultSet.next()) {;
                doanhthu = resultSet.getFloat("doanhthu");
                BigDecimal x = new BigDecimal(doanhthu);
                ModelStatistic1 ms = new ModelStatistic1();
                ms.setTien(x);
                txtDoanhThu.setText(ms.getFormattedDonGia());
            }
        }catch(Exception e){
            JOptionPane.showMessageDialog(this, e.getMessage());
            e.printStackTrace();
        }
           
    }
    
    private void loadDataFromSQLthangofnam() {
        String thang = cmbThang1.getSelectedItem().toString();
        String nam = cmbNam1.getSelectedItem().toString();
                                                                                                                                                                                                                                                                                                                                     
        try {
            conn = cn.getConnection();
            String sqlQuery = "select MaHoaDonPhong, TenNV, TenKH,TenPhong, NgayLapHoaDon, Tien  from HoaDonPhong hdp join PhieuThuePhong ptp \n" +
"on hdp.MaPhieuThuePhong = ptp.MaPhieuThuePhong  join PhieuDatPhong pdp on pdp.MaPhieuDatPhong = ptp.MaPhieuDatPhong \n" +
"join PHONG p on p.MaPhong = pdp.MaPhong join NHANVIEN nv on nv.MaNV = pdp.MaNV join KHACHHANG kh on kh.MaKH = pdp.MaKH \n" +
"where month(NgayLapHoaDon) = ? and YEAR (NgayLapHoaDon) = ? ";

            PreparedStatement preparedStatement = conn.prepareStatement(sqlQuery);

            preparedStatement.setString(1, thang);
            preparedStatement.setString(2, nam);

            ResultSet resultSet = preparedStatement.executeQuery();

            // Lặp qua kết quả và thêm vào model
            while (resultSet.next()) {
                Object[] rowData = {
                        resultSet.getObject("MaHoaDonPhong"),
                        resultSet.getObject("TenNV"),
                        resultSet.getObject("TenKH"),
                        resultSet.getObject("TenPhong"),
                        resultSet.getObject("NgayLapHoaDon"),
                        resultSet.getObject("Tien"),
                };
                tableModel.addRow(rowData);
            }
        }catch(Exception e){
            JOptionPane.showMessageDialog(this, e.getMessage());
            e.printStackTrace();
        }
    } 
    
    private void loaddoanhthuofnam(){

        // Thực hiện truy vấn SQL để lấy dữ liệu từ bảng
        
        String nam = cmbNam2.getSelectedItem().toString();
        try {
            conn = cn.getConnection();
            String sqlQuery = "select sum(Tien) as doanhthu,  year(NgayLapHoaDon) as nam from HoaDonPhong hdp join PhieuThuePhong ptp on hdp.MaPhieuThuePhong = ptp.MaPhieuThuePhong \n" +
"join PhieuDatPhong pdp on pdp.MaPhieuDatPhong = ptp.MaPhieuDatPhong join PHONG p on p.MaPhong = pdp.MaPhong \n" +
"join NHANVIEN nv on nv.MaNV = pdp.MaNV join KHACHHANG kh on kh.MaKH = pdp.MaKH \n" +
"group by  year(NgayLapHoaDon)\n" +
"having year(NgayLapHoaDon) = ? ";

            PreparedStatement preparedStatement = conn.prepareStatement(sqlQuery);

            preparedStatement.setString(1, nam);
            

            ResultSet resultSet = preparedStatement.executeQuery();
            
            float doanhthu;
            // Lặp qua kết quả và thêm vào model
            if (resultSet.next()) {;
                doanhthu = resultSet.getFloat("doanhthu");
                BigDecimal x = new BigDecimal(doanhthu);
                ModelStatistic1 ms = new ModelStatistic1();
                ms.setTien(x);
                txtDoanhThu.setText(ms.getFormattedDonGia());
            }
        }catch(Exception e){
            JOptionPane.showMessageDialog(this, e.getMessage());
            e.printStackTrace();
        }
           
    }
    
    private void loadDataFromSQLofnam() {
       
        String nam = cmbNam2.getSelectedItem().toString();
                                                                                                                                                                                                                                                                                                                                     
        try {
            conn = cn.getConnection();
            String sqlQuery = "select MaHoaDonPhong, TenNV, TenKH,TenPhong, NgayLapHoaDon, Tien  from HoaDonPhong hdp join PhieuThuePhong ptp \n" +
"on hdp.MaPhieuThuePhong = ptp.MaPhieuThuePhong  join PhieuDatPhong pdp on pdp.MaPhieuDatPhong = ptp.MaPhieuDatPhong \n" +
"join PHONG p on p.MaPhong = pdp.MaPhong join NHANVIEN nv on nv.MaNV = pdp.MaNV join KHACHHANG kh on kh.MaKH = pdp.MaKH \n" +
"where YEAR (NgayLapHoaDon) = ? ";

            PreparedStatement preparedStatement = conn.prepareStatement(sqlQuery);

           
            preparedStatement.setString(1, nam);

            ResultSet resultSet = preparedStatement.executeQuery();

            // Lặp qua kết quả và thêm vào model
            while (resultSet.next()) {
                Object[] rowData = {
                        resultSet.getObject("MaHoaDonPhong"),
                        resultSet.getObject("TenNV"),
                        resultSet.getObject("TenKH"),
                        resultSet.getObject("TenPhong"),
                        resultSet.getObject("NgayLapHoaDon"),
                        resultSet.getObject("Tien"),
                };
                tableModel.addRow(rowData);
            }
        }catch(Exception e){
            JOptionPane.showMessageDialog(this, e.getMessage());
            e.printStackTrace();
        }
    } 
    
    private void loadDataFromSQL() {

        conn = cn.getConnection();

        // Thực hiện truy vấn SQL để lấy dữ liệu từ bảng
        String sqlQuery = "select MaHoaDonPhong, TenNV, TenKH,TenPhong, NgayLapHoaDon, Tien  from HoaDonPhong hdp join PhieuThuePhong ptp on hdp.MaPhieuThuePhong = ptp.MaPhieuThuePhong  join PhieuDatPhong pdp on pdp.MaPhieuDatPhong = ptp.MaPhieuDatPhong join PHONG p on p.MaPhong = pdp.MaPhong join NHANVIEN nv on nv.MaNV = pdp.MaNV join KHACHHANG kh on kh.MaKH = pdp.MaKH  ";
        //where NgayLapHoaDon between ? and ?

        try {
            PreparedStatement preparedStatement = conn.prepareStatement(sqlQuery);

            
            ResultSet resultSet = preparedStatement.executeQuery();

            // Lặp qua kết quả và thêm vào model
            while (resultSet.next()) {
                Object[] rowData = {
                        resultSet.getObject("MaHoaDonPhong"),
                        resultSet.getObject("TenNV"),
                        resultSet.getObject("TenKH"),
                        resultSet.getObject("TenPhong"),
                        resultSet.getObject("NgayLapHoaDon"),
                        resultSet.getObject("Tien"),


//                            "Action" // Cần thay thế "Action" bằng cột thực tế bạn muốn hiển thị hoặc xử lý

                };
                tableModel.addRow(rowData);
            }
        }catch(Exception e){

        }
        
    }
    
    
    
    
    private void report_monthofyear() { 

        String thang = cmbThang1.getSelectedItem().toString();
        String nam = cmbNam1.getSelectedItem().toString();
        
        try {
            Map<String, Object> map = new HashMap<>();
            JasperReport rpt = JasperCompileManager.compileReport("src/report/rpStatistic1.jrxml");
            map.put("thang", thang);
            map.put("nam", nam);
     
            conn = cn.getConnection();
            JasperPrint p = JasperFillManager.fillReport(rpt, map, conn);
            JasperViewer.viewReport(p, false);
        } catch (JRException ex) {
            JOptionPane.showMessageDialog(this, ex.toString());
        }
    }
    
    private void report_ofyear() { 

       
        String nam = cmbNam2.getSelectedItem().toString();
        
        try {
            Map<String, Object> map = new HashMap<>();
            JasperReport rpt = JasperCompileManager.compileReport("src/report/rpStatistic1.jrxml");
            map.put("thang", nam);
            
     
            conn = cn.getConnection();
            JasperPrint p = JasperFillManager.fillReport(rpt, map, conn);
            JasperViewer.viewReport(p, false);
        } catch (JRException ex) {
            JOptionPane.showMessageDialog(this, ex.toString());
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

        buttonGroup1 = new javax.swing.ButtonGroup();
        roundPanel2 = new swing.RoundPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        table = new javax.swing.JTable();
        txtDoanhThu = new javax.swing.JTextField();
        jLabel21 = new javax.swing.JLabel();
        btnReport = new javax.swing.JButton();
        panelBorder2 = new swing.PanelBorder();
        rbThangofNam = new javax.swing.JRadioButton();
        rbNam = new javax.swing.JRadioButton();
        btnRefresh1 = new swing.Button();
        btnStatistic = new swing.Button();
        cmbThang1 = new javax.swing.JComboBox<>();
        cmbNam1 = new javax.swing.JComboBox<>();
        cmbNam2 = new javax.swing.JComboBox<>();

        roundPanel2.setBackground(new java.awt.Color(36, 87, 157));
        roundPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Thống kê doanh thu hóa đơn phòng:", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 24), new java.awt.Color(255, 255, 255))); // NOI18N
        roundPanel2.setForeground(new java.awt.Color(255, 255, 255));

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

        jLabel21.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        jLabel21.setForeground(new java.awt.Color(255, 255, 255));
        jLabel21.setText("Doanh thu:");

        btnReport.setBackground(new java.awt.Color(255, 99, 76));
        btnReport.setText("In Report");
        btnReport.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnReportActionPerformed(evt);
            }
        });

        panelBorder2.setBackground(new java.awt.Color(36, 87, 157));
        panelBorder2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Tiêu chí thống kê", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 20), new java.awt.Color(255, 255, 255))); // NOI18N
        panelBorder2.setForeground(new java.awt.Color(255, 255, 255));

        rbThangofNam.setBackground(new java.awt.Color(36, 87, 157));
        buttonGroup1.add(rbThangofNam);
        rbThangofNam.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        rbThangofNam.setForeground(new java.awt.Color(255, 255, 255));
        rbThangofNam.setText("Thống kê theo tháng của năm");

        rbNam.setBackground(new java.awt.Color(36, 87, 157));
        buttonGroup1.add(rbNam);
        rbNam.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        rbNam.setForeground(new java.awt.Color(255, 255, 255));
        rbNam.setText("Thống kê theo năm");

        btnRefresh1.setText("Xem");
        btnRefresh1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRefresh1ActionPerformed(evt);
            }
        });

        btnStatistic.setText("Thống kê");
        btnStatistic.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnStatisticActionPerformed(evt);
            }
        });

        cmbThang1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        cmbNam1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        cmbNam2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        javax.swing.GroupLayout panelBorder2Layout = new javax.swing.GroupLayout(panelBorder2);
        panelBorder2.setLayout(panelBorder2Layout);
        panelBorder2Layout.setHorizontalGroup(
            panelBorder2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBorder2Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(panelBorder2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(panelBorder2Layout.createSequentialGroup()
                        .addComponent(rbNam)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(cmbNam2, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panelBorder2Layout.createSequentialGroup()
                        .addComponent(rbThangofNam)
                        .addGap(169, 169, 169)
                        .addComponent(cmbThang1, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addComponent(cmbNam1, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelBorder2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnRefresh1, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(90, 90, 90)
                .addComponent(btnStatistic, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(154, 154, 154))
        );
        panelBorder2Layout.setVerticalGroup(
            panelBorder2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBorder2Layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addGroup(panelBorder2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(rbThangofNam)
                    .addGroup(panelBorder2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(cmbThang1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(cmbNam1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(33, 33, 33)
                .addGroup(panelBorder2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(rbNam)
                    .addComponent(cmbNam2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 59, Short.MAX_VALUE)
                .addComponent(btnRefresh1, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelBorder2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnStatistic, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout roundPanel2Layout = new javax.swing.GroupLayout(roundPanel2);
        roundPanel2.setLayout(roundPanel2Layout);
        roundPanel2Layout.setHorizontalGroup(
            roundPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(roundPanel2Layout.createSequentialGroup()
                .addGap(33, 33, 33)
                .addGroup(roundPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(panelBorder2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, roundPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel21)
                        .addGap(18, 18, 18)
                        .addComponent(txtDoanhThu, javax.swing.GroupLayout.PREFERRED_SIZE, 186, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnReport, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(23, 23, 23))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 1006, Short.MAX_VALUE))
                .addGap(31, 31, 31))
        );
        roundPanel2Layout.setVerticalGroup(
            roundPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(roundPanel2Layout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addComponent(panelBorder2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(roundPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(roundPanel2Layout.createSequentialGroup()
                        .addGap(403, 403, 403)
                        .addGroup(roundPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel21)
                            .addComponent(txtDoanhThu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(roundPanel2Layout.createSequentialGroup()
                        .addGap(125, 125, 125)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 219, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnReport, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(23, 23, 23))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(roundPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(roundPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnStatisticActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnStatisticActionPerformed
        // TODO add your handling code here:
        
        txtDoanhThu.setText("");
        tableModel.setRowCount(0);
        
        if (rbThangofNam.isSelected() ==true){
            loaddoanhthuthangofnam();
            loadDataFromSQLthangofnam();
        }else if(rbNam.isSelected() == true){
            loaddoanhthuofnam();
            loadDataFromSQLofnam();
        }else {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn ngày");
        }
//        Date selectedDate = jDateChooserTuNgay.getDate();
//        Date selectedDate1 = jDateChooserDenNgay.getDate();
//        if (selectedDate != null && selectedDate1 != null){
//            loadDataFromSQL_serch();
//            loaddoanhthu();
//        }else {
//            
//        }
    
        
        
    }//GEN-LAST:event_btnStatisticActionPerformed

    private void btnRefresh1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRefresh1ActionPerformed
        // TODO add your handling code here:
        tableModel.setRowCount(0);
        txtDoanhThu.setText("");
        loadDataFromSQL();
    }//GEN-LAST:event_btnRefresh1ActionPerformed

    private void btnReportActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnReportActionPerformed
        // TODO add your handling code here:
        
        if (rbThangofNam.isSelected() ==true){
           report_monthofyear();
        }else if(rbNam.isSelected() == true){
            report_ofyear();
        }else {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn loại thống kê");
        }
        
        
        
    }//GEN-LAST:event_btnReportActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private swing.Button btnRefresh1;
    private javax.swing.JButton btnReport;
    private swing.Button btnStatistic;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JComboBox<String> cmbNam1;
    private javax.swing.JComboBox<String> cmbNam2;
    private javax.swing.JComboBox<String> cmbThang1;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JScrollPane jScrollPane1;
    private swing.PanelBorder panelBorder2;
    private javax.swing.JRadioButton rbNam;
    private javax.swing.JRadioButton rbThangofNam;
    private swing.RoundPanel roundPanel2;
    private javax.swing.JTable table;
    private javax.swing.JTextField txtDoanhThu;
    // End of variables declaration//GEN-END:variables
}
