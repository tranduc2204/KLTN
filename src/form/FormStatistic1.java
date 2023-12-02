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
    
    private void loaddoanhthu(){
        Date selectedDate = jDateChooserTuNgay.getDate();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(selectedDate);

        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH) + 1; // Tháng bắt đầu từ 0, nên cộng thêm 1 để có giá trị tháng thực
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        String tungay = year + "-" + month + "-" + day;


        Date selectedDate1 = jDateChooserDenNgay.getDate();
        Calendar calendar1 = Calendar.getInstance();
        calendar1.setTime(selectedDate1);

        int year1 = calendar1.get(Calendar.YEAR);
        int month1 = calendar1.get(Calendar.MONTH) + 1; // Tháng bắt đầu từ 0, nên cộng thêm 1 để có giá trị tháng thực
        int day1 = calendar1.get(Calendar.DAY_OF_MONTH);
        String denngay = year1 + "-" + month1 + "-" + day1;
        
        System.out.println(tungay);
        System.out.println(denngay);

        // Thực hiện truy vấn SQL để lấy dữ liệu từ bảng
        
        try {
            conn = cn.getConnection();
            String sqlQuery = "select sum(Tien) as doanhthu from HoaDonPhong hdp join PhieuThuePhong ptp on hdp.MaPhieuThuePhong = ptp.MaPhieuThuePhong  join PhieuDatPhong pdp on pdp.MaPhieuDatPhong = ptp.MaPhieuDatPhong join PHONG p on p.MaPhong = pdp.MaPhong join NHANVIEN nv on nv.MaNV = pdp.MaNV join KHACHHANG kh on kh.MaKH = pdp.MaKH where NgayLapHoaDon between ? and ? ";

            PreparedStatement preparedStatement = conn.prepareStatement(sqlQuery);

            preparedStatement.setString(1, tungay);
            preparedStatement.setString(2, denngay);

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
    
    
    private void loadDataFromSQL_serch() {

        Date selectedDate = jDateChooserTuNgay.getDate();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(selectedDate);

        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH) + 1; // Tháng bắt đầu từ 0, nên cộng thêm 1 để có giá trị tháng thực
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        String tungay = year + "-" + month + "-" + day;


        Date selectedDate1 = jDateChooserDenNgay.getDate();
        Calendar calendar1 = Calendar.getInstance();
        calendar1.setTime(selectedDate1);

        int year1 = calendar1.get(Calendar.YEAR);
        int month1 = calendar1.get(Calendar.MONTH) + 1; // Tháng bắt đầu từ 0, nên cộng thêm 1 để có giá trị tháng thực
        int day1 = calendar1.get(Calendar.DAY_OF_MONTH);
        String denngay = year1 + "-" + month1 + "-" + day1;
        
        System.out.println(tungay);
        System.out.println(denngay);

        // Thực hiện truy vấn SQL để lấy dữ liệu từ bảng
        
        try {
            conn = cn.getConnection();
            String sqlQuery = "select MaHoaDonPhong, TenNV, TenKH,TenPhong, NgayLapHoaDon, Tien  from HoaDonPhong hdp join PhieuThuePhong ptp on hdp.MaPhieuThuePhong = ptp.MaPhieuThuePhong  join PhieuDatPhong pdp on pdp.MaPhieuDatPhong = ptp.MaPhieuDatPhong join PHONG p on p.MaPhong = pdp.MaPhong join NHANVIEN nv on nv.MaNV = pdp.MaNV join KHACHHANG kh on kh.MaKH = pdp.MaKH where NgayLapHoaDon between ? and ? ";

            PreparedStatement preparedStatement = conn.prepareStatement(sqlQuery);

            preparedStatement.setString(1, tungay);
            preparedStatement.setString(2, denngay);

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
    
    private void report() { 

        Date selectedDate = jDateChooserTuNgay.getDate();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(selectedDate);

        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH) + 1; // Tháng bắt đầu từ 0, nên cộng thêm 1 để có giá trị tháng thực
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        String tungay = year + "-" + month + "-" + day;


        Date selectedDate1 = jDateChooserDenNgay.getDate();
        Calendar calendar1 = Calendar.getInstance();
        calendar1.setTime(selectedDate1);

        int year1 = calendar1.get(Calendar.YEAR);
        int month1 = calendar1.get(Calendar.MONTH) + 1; // Tháng bắt đầu từ 0, nên cộng thêm 1 để có giá trị tháng thực
        int day1 = calendar1.get(Calendar.DAY_OF_MONTH);
        String denngay = year1 + "-" + month1 + "-" + day1;
        
        System.out.println(tungay);
        System.out.println(denngay);
       

        try {
            Map<String, Object> map = new HashMap<>();
            JasperReport rpt = JasperCompileManager.compileReport("src/report/rpStatistic1.jrxml");
            map.put("tungay", tungay);
            map.put("denngay", denngay);
     
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

        roundPanel2 = new swing.RoundPanel();
        jDateChooserTuNgay = new com.toedter.calendar.JDateChooser();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jDateChooserDenNgay = new com.toedter.calendar.JDateChooser();
        jScrollPane1 = new javax.swing.JScrollPane();
        table = new javax.swing.JTable();
        btnRefresh = new swing.Button();
        txtDoanhThu = new javax.swing.JTextField();
        jLabel21 = new javax.swing.JLabel();
        btnRefresh1 = new swing.Button();
        btnReport = new javax.swing.JButton();

        roundPanel2.setBackground(new java.awt.Color(36, 87, 157));
        roundPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Thống kê doanh thu hóa đơn phòng:", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 24), new java.awt.Color(255, 255, 255))); // NOI18N
        roundPanel2.setForeground(new java.awt.Color(255, 255, 255));

        jLabel19.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        jLabel19.setForeground(new java.awt.Color(255, 255, 255));
        jLabel19.setText("Từ ngày:");

        jLabel20.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        jLabel20.setForeground(new java.awt.Color(255, 255, 255));
        jLabel20.setText("Đến ngày:");

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

        btnRefresh.setText("Thống kê");
        btnRefresh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRefreshActionPerformed(evt);
            }
        });

        jLabel21.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        jLabel21.setForeground(new java.awt.Color(255, 255, 255));
        jLabel21.setText("Doanh thu:");

        btnRefresh1.setText("Xem");
        btnRefresh1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRefresh1ActionPerformed(evt);
            }
        });

        btnReport.setBackground(new java.awt.Color(255, 99, 76));
        btnReport.setText("In Report");
        btnReport.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnReportActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout roundPanel2Layout = new javax.swing.GroupLayout(roundPanel2);
        roundPanel2.setLayout(roundPanel2Layout);
        roundPanel2Layout.setHorizontalGroup(
            roundPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(roundPanel2Layout.createSequentialGroup()
                .addGap(33, 33, 33)
                .addGroup(roundPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, roundPanel2Layout.createSequentialGroup()
                        .addGroup(roundPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(roundPanel2Layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(btnRefresh1, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(btnRefresh, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(roundPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel19)
                                .addGap(76, 76, 76)
                                .addComponent(jDateChooserTuNgay, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGap(129, 129, 129)
                                .addComponent(jLabel20)
                                .addGap(31, 31, 31)
                                .addComponent(jDateChooserDenNgay, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addGap(109, 109, 109))
                    .addGroup(roundPanel2Layout.createSequentialGroup()
                        .addGroup(roundPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(roundPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel21)
                                .addGap(18, 18, 18)
                                .addComponent(txtDoanhThu, javax.swing.GroupLayout.PREFERRED_SIZE, 186, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(btnReport, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(23, 23, 23))
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 1006, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(31, 31, 31))))
        );
        roundPanel2Layout.setVerticalGroup(
            roundPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(roundPanel2Layout.createSequentialGroup()
                .addGap(45, 45, 45)
                .addGroup(roundPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jDateChooserDenNgay, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel19)
                    .addComponent(jDateChooserTuNgay, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel20))
                .addGap(72, 72, 72)
                .addGroup(roundPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnRefresh, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnRefresh1, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(roundPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(roundPanel2Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 409, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnReport, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(roundPanel2Layout.createSequentialGroup()
                        .addGap(484, 484, 484)
                        .addGroup(roundPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addGroup(roundPanel2Layout.createSequentialGroup()
                                .addGap(2, 2, 2)
                                .addComponent(jLabel21))
                            .addGroup(roundPanel2Layout.createSequentialGroup()
                                .addGap(2, 2, 2)
                                .addComponent(txtDoanhThu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))))
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

    private void btnRefreshActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRefreshActionPerformed
        // TODO add your handling code here:
        tableModel.setRowCount(0);
        Date selectedDate = jDateChooserTuNgay.getDate();
        Date selectedDate1 = jDateChooserDenNgay.getDate();
        if (selectedDate != null && selectedDate1 != null){
            loadDataFromSQL_serch();
            loaddoanhthu();
        }else {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn ngày");
        }
        
        
        
    }//GEN-LAST:event_btnRefreshActionPerformed

    private void btnRefresh1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRefresh1ActionPerformed
        // TODO add your handling code here:
        tableModel.setRowCount(0);
        txtDoanhThu.setText("");
        loadDataFromSQL();
    }//GEN-LAST:event_btnRefresh1ActionPerformed

    private void btnReportActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnReportActionPerformed
        // TODO add your handling code here:

        Date selectedDate = jDateChooserTuNgay.getDate();
        Date selectedDate1 = jDateChooserDenNgay.getDate();
        if (selectedDate != null && selectedDate1 != null){
            report();
        }else {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn ngày");
        }
    }//GEN-LAST:event_btnReportActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private swing.Button btnRefresh;
    private swing.Button btnRefresh1;
    private javax.swing.JButton btnReport;
    private com.toedter.calendar.JDateChooser jDateChooserDenNgay;
    private com.toedter.calendar.JDateChooser jDateChooserTuNgay;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JScrollPane jScrollPane1;
    private swing.RoundPanel roundPanel2;
    private javax.swing.JTable table;
    private javax.swing.JTextField txtDoanhThu;
    // End of variables declaration//GEN-END:variables
}
