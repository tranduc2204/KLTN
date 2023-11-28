/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package form;

import static com.formdev.flatlaf.util.MultiResolutionImageSupport.map;
import connect.Connect;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import static jdk.nashorn.internal.objects.NativeArray.map;
import model.ModelServiceRent;
import model.ModelServiceRentv2;
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
public class FormServiceRent extends javax.swing.JPanel {

    /**
     * Creates new form FormServiceRent
     */
    public FormServiceRent() {
        initComponents();
    }
    
    Connect cn = new Connect();
    Connection conn;
    DefaultTableModel tbmodel;

    private String username, password, quyen, DisplayName;
    
    public FormServiceRent(String username, String password, String DisplayName, String quyen) {
        initComponents();
        this.username = username;
        this.password = password;
        this.DisplayName = DisplayName;
        this.quyen = quyen;
        
        
        initCombobox_madichvu();
        initCombobox_manhanvien();
        initCombobox_makhachhang();
  
       

        inittable();
        loaddulieu1();
    }
    
    private void inittable() {
        tbmodel = new DefaultTableModel();
        tbmodel.setColumnIdentifiers(new String[]{"Mã dịch vụ", "Tên dịch vụ", "Mã nhân viên",  "Tên nhân viên",
            "Mã khách hàng", "Tên khách hàng", "Ngày lập hóa đơn", "Đơn giá",  "Số lượng", "Giá hóa đơn"});
        TBServiceRent.setModel(tbmodel);
    }
    
     public void loaddulieu1() {
        try {
            ModelServiceRent ql = new ModelServiceRent();
            ArrayList<ModelServiceRentv2> list = ql.findALL();
            tbmodel.setRowCount(0);
            for (ModelServiceRentv2 p : list) {
                tbmodel.addRow(new Object[]{
                    p.getMaDV(), p.getTenDichVu(), p.getMaNV(), p.getTenNhanVien(), p.getMaKH(),p.getTenKH(),p.getNgayLapHD(), p.getFormattedGia(),  p.getSL(),p.getFormattedGiahd()
                });
            }
            tbmodel.fireTableDataChanged();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "error " + e.getMessage());
            e.printStackTrace();
        }

    }
     
    private void initCombobox_madichvu() {
        try {
            conn = cn.getConnection();
            String sql = "select MaDV from DICHVU where isvisible = '1' ";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();
            cmbmadichvu.removeAllItems();
          
            while (rs.next()) {
                cmbmadichvu.addItem(rs.getString("MaDV"));
            }
            rs.close();
            pstmt.close();
            conn.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
            e.printStackTrace();
        }
    }
    
    private void initCombobox_manhanvien() {
        try {
            conn = cn.getConnection();
            String sql = "select MaNV from NhanVien where isvisible = '1' ";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();
            cmbMaNhanvien.removeAllItems();
          
            while (rs.next()) {
                cmbMaNhanvien.addItem(rs.getString("MaNV"));
            }
            rs.close();
            pstmt.close();
            conn.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
            e.printStackTrace();
        }
    }
    
    private void initCombobox_makhachhang() {
        try {
            conn = cn.getConnection();
            String sql = "select MaKH from KhachHang where isvisible = '1' ";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();
            cmbmakhachhang.removeAllItems();
          
            while (rs.next()) {
                cmbmakhachhang.addItem(rs.getString("MaKH"));
            }
            rs.close();
            pstmt.close();
            conn.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
            e.printStackTrace();
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

        txtloaiphong = new javax.swing.JTextField();
        roundPanel5 = new swing.RoundPanel();
        jLabel10 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        txtTenKH = new javax.swing.JTextField();
        btnEdit = new swing.Button();
        btnAdd = new swing.Button();
        bthDelete = new swing.Button();
        btnRefresh = new swing.Button();
        txtGiaDichvu = new javax.swing.JTextField();
        jLabel17 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        cmbmadichvu = new javax.swing.JComboBox<>();
        jLabel18 = new javax.swing.JLabel();
        txtGiahd = new javax.swing.JTextField();
        jLabel22 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jDateChooserngaylaphoadon = new com.toedter.calendar.JDateChooser();
        txtTenDichVu = new javax.swing.JTextField();
        cmbMaNhanvien = new javax.swing.JComboBox<>();
        txtTenNhanVien = new javax.swing.JTextField();
        cmbmakhachhang = new javax.swing.JComboBox<>();
        spSL = new javax.swing.JSpinner();
        btnPay = new javax.swing.JButton();
        btnReport = new javax.swing.JButton();
        jLabel8 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jScrollPane1 = new javax.swing.JScrollPane();
        TBServiceRent = new javax.swing.JTable();
        button1 = new swing.Button();
        button2 = new swing.Button();

        txtloaiphong.setEnabled(false);

        roundPanel5.setBackground(new java.awt.Color(36, 87, 157));
        roundPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Thông tin thuê dịch vụ", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 24), new java.awt.Color(255, 255, 255))); // NOI18N

        jLabel10.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setText("Tên dịch vụ:");

        jLabel16.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(255, 255, 255));
        jLabel16.setText("Tên khách hàng:");

        txtTenKH.setEnabled(false);

        btnEdit.setText("Sửa");
        btnEdit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditActionPerformed(evt);
            }
        });

        btnAdd.setText("Thêm");
        btnAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddActionPerformed(evt);
            }
        });

        bthDelete.setText("Xóa");
        bthDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bthDeleteActionPerformed(evt);
            }
        });

        btnRefresh.setText("Làm mới");
        btnRefresh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRefreshActionPerformed(evt);
            }
        });

        txtGiaDichvu.setEnabled(false);

        jLabel17.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(255, 255, 255));
        jLabel17.setText("Giá dịch vụ:");

        jLabel15.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(255, 255, 255));
        jLabel15.setText("Tên nhân viên:");

        jLabel20.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        jLabel20.setForeground(new java.awt.Color(255, 255, 255));
        jLabel20.setText("Số lượng:");

        jLabel21.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        jLabel21.setForeground(new java.awt.Color(255, 255, 255));
        jLabel21.setText("Mã nhân viên:");

        jLabel11.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(255, 255, 255));
        jLabel11.setText("Mã dịch vụ:");

        cmbmadichvu.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cmbmadichvuItemStateChanged(evt);
            }
        });

        jLabel18.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        jLabel18.setForeground(new java.awt.Color(255, 255, 255));
        jLabel18.setText("Giá hóa đơn:");

        txtGiahd.setEnabled(false);

        jLabel22.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        jLabel22.setForeground(new java.awt.Color(255, 255, 255));
        jLabel22.setText("Mã khách hàng:");

        jLabel19.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        jLabel19.setForeground(new java.awt.Color(255, 255, 255));
        jLabel19.setText("Ngày lập hóa đơn:");

        cmbMaNhanvien.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cmbMaNhanvienItemStateChanged(evt);
            }
        });

        cmbmakhachhang.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cmbmakhachhangItemStateChanged(evt);
            }
        });

        btnPay.setBackground(new java.awt.Color(255, 102, 102));
        btnPay.setText("Tính tiền");
        btnPay.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPayActionPerformed(evt);
            }
        });

        btnReport.setBackground(new java.awt.Color(255, 99, 76));
        btnReport.setText("In Report");
        btnReport.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnReportActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout roundPanel5Layout = new javax.swing.GroupLayout(roundPanel5);
        roundPanel5.setLayout(roundPanel5Layout);
        roundPanel5Layout.setHorizontalGroup(
            roundPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(roundPanel5Layout.createSequentialGroup()
                .addGroup(roundPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(roundPanel5Layout.createSequentialGroup()
                        .addGap(305, 305, 305)
                        .addComponent(btnAdd, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(19, 19, 19)
                        .addComponent(btnEdit, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(roundPanel5Layout.createSequentialGroup()
                        .addGap(40, 40, 40)
                        .addGroup(roundPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel10)
                            .addComponent(jLabel21)
                            .addComponent(jLabel11)
                            .addComponent(jLabel15)
                            .addComponent(jLabel20))
                        .addGap(28, 28, 28)
                        .addGroup(roundPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(cmbmadichvu, 0, 249, Short.MAX_VALUE)
                            .addComponent(txtTenDichVu)
                            .addComponent(cmbMaNhanvien, javax.swing.GroupLayout.Alignment.TRAILING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtTenNhanVien, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(spSL))))
                .addGap(18, 18, 18)
                .addGroup(roundPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(roundPanel5Layout.createSequentialGroup()
                        .addComponent(bthDelete, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(31, 31, 31)
                        .addComponent(btnRefresh, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(41, 41, 41)
                        .addComponent(btnPay, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnReport, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(roundPanel5Layout.createSequentialGroup()
                        .addGroup(roundPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel17)
                            .addComponent(jLabel18)
                            .addComponent(jLabel16)
                            .addComponent(jLabel22)
                            .addComponent(jLabel19))
                        .addGap(47, 47, 47)
                        .addGroup(roundPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtTenKH, javax.swing.GroupLayout.DEFAULT_SIZE, 281, Short.MAX_VALUE)
                            .addComponent(txtGiahd, javax.swing.GroupLayout.DEFAULT_SIZE, 281, Short.MAX_VALUE)
                            .addComponent(txtGiaDichvu)
                            .addComponent(jDateChooserngaylaphoadon, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(cmbmakhachhang, 0, 281, Short.MAX_VALUE))))
                .addContainerGap(38, Short.MAX_VALUE))
        );
        roundPanel5Layout.setVerticalGroup(
            roundPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(roundPanel5Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(roundPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(roundPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel22)
                        .addComponent(cmbmakhachhang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(roundPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel11)
                        .addComponent(cmbmadichvu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(26, 26, 26)
                .addGroup(roundPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(jLabel16)
                    .addComponent(txtTenKH, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtTenDichVu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(29, 29, 29)
                .addGroup(roundPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(roundPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel21)
                        .addComponent(cmbMaNhanvien, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel19)
                    .addComponent(jDateChooserngaylaphoadon, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(23, 23, 23)
                .addGroup(roundPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(roundPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel17)
                        .addComponent(txtGiaDichvu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(roundPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel15)
                        .addComponent(txtTenNhanVien, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(20, 20, 20)
                .addGroup(roundPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel18)
                    .addComponent(txtGiahd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel20)
                    .addComponent(spSL, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(roundPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(roundPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnEdit, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(bthDelete, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnRefresh, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnPay, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnReport, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(btnAdd, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel8.setText("Thông tin thuê dịch vụ:");

        TBServiceRent.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        TBServiceRent.setModel(new javax.swing.table.DefaultTableModel(
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
        TBServiceRent.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TBServiceRentMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(TBServiceRent);

        button1.setBackground(new java.awt.Color(36, 87, 157));
        button1.setForeground(new java.awt.Color(255, 255, 255));
        button1.setText("Thêm khách hàng");
        button1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button1ActionPerformed(evt);
            }
        });

        button2.setBackground(new java.awt.Color(36, 87, 157));
        button2.setForeground(new java.awt.Color(255, 255, 255));
        button2.setText("Xem dịch vụ");
        button2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(63, Short.MAX_VALUE)
                .addComponent(button1, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(33, 33, 33)
                .addComponent(button2, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(664, 664, 664))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane1)
                        .addContainerGap())
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(roundPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(17, 17, 17))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel8)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(roundPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(39, 39, 39)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(button1, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(button2, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jLabel8)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 12, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 237, Short.MAX_VALUE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditActionPerformed
        // TODO add your handling code here:
        Date selectedDate = jDateChooserngaylaphoadon.getDate();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(selectedDate);

        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH) + 1; // Tháng bắt đầu từ 0, nên cộng thêm 1 để có giá trị tháng thực
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        String ngayhd = year + "-" + month + "-" + day;

        String MaDichvu = cmbmadichvu.getSelectedItem().toString();
        String MaNV = cmbMaNhanvien.getSelectedItem().toString();
        String MaKH = cmbmakhachhang.getSelectedItem().toString();

        // Lấy giá trị hiện tại của JSpinner
        Object selectedValue = spSL.getValue();
        
        int spinnerValue = (int) selectedValue;
        
        if (spinnerValue > 0){
            try {
                ModelServiceRentv2 ci = new ModelServiceRentv2();
                ci.setMaDV(MaDichvu);
                ci.setMaNV(MaNV);
                ci.setMaKH(MaKH);
                ci.setNgayLapHD(ngayhd);
                ci.setSL(spinnerValue);

                ModelServiceRent ql1 = new ModelServiceRent();
                ql1.update(ci);

                JOptionPane.showMessageDialog(this, "Lưu thành công!!!");


                loaddulieu1();
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, "");
                e.printStackTrace();
            }
        }else {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn số lượng dịch vụ lớn hơn 0");

        }

    }//GEN-LAST:event_btnEditActionPerformed

    private void btnAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddActionPerformed
        // TODO add your handling code here:

        Date selectedDate = jDateChooserngaylaphoadon.getDate();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(selectedDate);

        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH) + 1; // Tháng bắt đầu từ 0, nên cộng thêm 1 để có giá trị tháng thực
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        String ngayhd = year + "-" + month + "-" + day;

        String MaDichvu = cmbmadichvu.getSelectedItem().toString();
        String MaNV = cmbMaNhanvien.getSelectedItem().toString();
        String MaKH = cmbmakhachhang.getSelectedItem().toString();
        
        // Lấy giá trị hiện tại của JSpinner
        Object selectedValue = spSL.getValue();

//        // Chuyển đổi giá trị thành chuỗi
//        String stringValue = selectedValue.toString();
        
        int spinnerValue = (int) selectedValue;
        if (spinnerValue > 0){
            try {
                ModelServiceRentv2 ci = new ModelServiceRentv2();
                ci.setMaDV(MaDichvu);
                ci.setMaNV(MaNV);
                ci.setMaKH(MaKH);
                ci.setNgayLapHD(ngayhd);
                ci.setSL(spinnerValue);

                ModelServiceRent ql1 = new ModelServiceRent();
                ql1.insert(ci);

                JOptionPane.showMessageDialog(this, "Lưu thành công!!!");


                loaddulieu1();
            } catch (Exception e) {
                //            JOptionPane.showMessageDialog(this, "error " + e.getMessage());
                JOptionPane.showMessageDialog(this, "");
                e.printStackTrace();
            }
        }else {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn số lượng dịch vụ lớn hơn 0");

        }
        

    }//GEN-LAST:event_btnAddActionPerformed

    private void bthDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bthDeleteActionPerformed
        // TODO add your handling code here:

        String MaDichvu = cmbmadichvu.getSelectedItem().toString();
        String MaNV = cmbMaNhanvien.getSelectedItem().toString();
        String MaKH = cmbmakhachhang.getSelectedItem().toString();

        try {
            ModelServiceRentv2 ci = new ModelServiceRentv2();
            ci.setMaDV(MaDichvu);
            ci.setMaNV(MaNV);
            ci.setMaKH(MaKH);


            ModelServiceRent ql1 = new ModelServiceRent();
            ql1.deletecomeroot(ci);

            JOptionPane.showMessageDialog(this, "Xóa thành công!!!");


            loaddulieu1();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "");
            e.printStackTrace();
        }
        

    }//GEN-LAST:event_bthDeleteActionPerformed

    private void btnRefreshActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRefreshActionPerformed
        // TODO add your handling code here:
        
    }//GEN-LAST:event_btnRefreshActionPerformed

    private void cmbmadichvuItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cmbmadichvuItemStateChanged
        // TODO add your handling code here:
        String Mapdp = cmbmadichvu.getSelectedItem().toString();
        try {
            ModelServiceRent ql = new ModelServiceRent();

            ModelServiceRentv2 ttp = ql.findByID_MADV(Mapdp);
            if (ttp != null) {

                txtTenDichVu.setText(ttp.getTenDichVu());
                txtGiaDichvu.setText(ttp.getFormattedGia());

            } else {
                JOptionPane.showMessageDialog(this, "Không tim thấy dịch vụ");
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "error " + e.getMessage());
            e.printStackTrace();
        }
    }//GEN-LAST:event_cmbmadichvuItemStateChanged

    private void cmbMaNhanvienItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cmbMaNhanvienItemStateChanged
        // TODO add your handling code here:
        String MaNV = cmbMaNhanvien.getSelectedItem().toString();
        try {
            ModelServiceRent ql = new ModelServiceRent();

            ModelServiceRentv2 ttp = ql.findByID_MANV(MaNV);
            if (ttp != null) {

                txtTenNhanVien.setText(ttp.getTenNhanVien());

            } else {
                JOptionPane.showMessageDialog(this, "Không tim thấy nhân viên");
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "error " + e.getMessage());
            e.printStackTrace();
        }
        
    }//GEN-LAST:event_cmbMaNhanvienItemStateChanged

    private void cmbmakhachhangItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cmbmakhachhangItemStateChanged
        // TODO add your handling code here:
        String MaKH = cmbmakhachhang.getSelectedItem().toString();
        try {
            ModelServiceRent ql = new ModelServiceRent();

            ModelServiceRentv2 ttp = ql.findByID_MAKH(MaKH);
            if (ttp != null) {

                txtTenKH.setText(ttp.getTenKH());

            } else {
                JOptionPane.showMessageDialog(this, "Không tim thấy khách hàng");
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "error " + e.getMessage());
            e.printStackTrace();
        }
    }//GEN-LAST:event_cmbmakhachhangItemStateChanged

    private void TBServiceRentMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TBServiceRentMouseClicked
        // TODO add your handling code here:
        int row = TBServiceRent.getSelectedRow();

        if (row >= 0) {
            
            
            String madv = TBServiceRent.getValueAt(row, 0).toString();
            System.out.println("" + madv);
            cmbmadichvu.setSelectedItem(madv);
            
            txtTenDichVu.setText(TBServiceRent.getValueAt(row, 1).toString());

            String manv = TBServiceRent.getValueAt(row, 2).toString();
            System.out.println("" + manv);
            cmbMaNhanvien.setSelectedItem(manv);
            
            txtTenNhanVien.setText(TBServiceRent.getValueAt(row, 3).toString());
            
            String makh = TBServiceRent.getValueAt(row, 4).toString();
            System.out.println("" + makh);
            cmbmakhachhang.setSelectedItem(makh);
            
            txtTenKH.setText(TBServiceRent.getValueAt(row, 5).toString());

            String ngaylaphd = TBServiceRent.getValueAt(row, 6).toString();

            try {
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd"); // Định dạng của chuỗi ngày
                Date selectedDate = dateFormat.parse(ngaylaphd); // Phân tích chuỗi thành đối tượng Date
                jDateChooserngaylaphoadon.setDate(selectedDate); // Đặt giá trị ngày cho JDateChooser
            } catch (ParseException e) {
                e.printStackTrace();
            }
            
            txtGiaDichvu.setText(TBServiceRent.getValueAt(row, 7).toString());
            
            Object sl = TBServiceRent.getValueAt(row, 8);
            spSL.setValue(sl);
            txtGiahd.setText(TBServiceRent.getValueAt(row, 9).toString());
            

        }
    }//GEN-LAST:event_TBServiceRentMouseClicked

    private void btnPayActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPayActionPerformed
        // TODO add your handling code here:
        // Lấy giá trị hiện tại của JSpinner
        Object selectedValue = spSL.getValue();

        // lấy giá trị kiểu int cho sốluongwj
        int spinnerValue = (int) selectedValue;
        
        String tmp = txtGiaDichvu.getText();
        
        DecimalFormat decimalFormat = new DecimalFormat("#,### VND");
        
        try {
            Number giaNumber = decimalFormat.parse(tmp);
            // Lấy giá trị số từ đối tượng Number
            Float gia = giaNumber.floatValue();
            
            System.out.println(gia);
            
            Float tmp2;
            
            if (spinnerValue > 0){
                tmp2 = (float) ((float)spinnerValue * gia);
                BigDecimal giahd = BigDecimal.valueOf(tmp2);

                ModelServiceRentv2 sr = new ModelServiceRentv2();
                sr.setGiaHD(giahd);
                String formattedhoadon = sr.getFormattedGiahd();
                txtGiahd.setText(formattedhoadon);
            }else {
                JOptionPane.showMessageDialog(this, "Vui lòng chọn số lượng dịch vụ lớn hơn 0");
            }
            
            
        } catch (ParseException ex) {
            Logger.getLogger(FormServiceRent.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnPayActionPerformed

    private void button1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button1ActionPerformed
        // TODO add your handling code here:
        FormaddKH l = new FormaddKH(username, password, DisplayName,quyen);
        l.setVisible(true);
        l.setLocationRelativeTo(null);
    }//GEN-LAST:event_button1ActionPerformed

    private void button2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button2ActionPerformed
        // TODO add your handling code here:
        FormViewService l = new FormViewService(username, password, DisplayName,quyen);
        l.setVisible(true);
        l.setLocationRelativeTo(null);
    }//GEN-LAST:event_button2ActionPerformed

    private void btnReportActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnReportActionPerformed
        // TODO add your handling code here:
        String MaDichvu = cmbmadichvu.getSelectedItem().toString();
        String MaNV = cmbMaNhanvien.getSelectedItem().toString();
        String MaKH = cmbmakhachhang.getSelectedItem().toString();
        
        if (MaDichvu.equals("")==true || MaNV.equals("")==true || MaKH.equals("")==true) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn mã phiếu dịch vụ mã nhân viên và mã khách hàng");
        } else {

            //All Files	D:\TranDuc\java\nhom4_qlksnewworld_5804_Ct6\src\nhom4_qlksnewworld_5804_ct6\newReport.jrxml
            try {//All Files	D:\TranDuc\java\nhom4_qlksnewworld_5804_Ct6\src\report\rptphieuthuephong.jrxml
//                Hashtable map = new Hashtable();
                Map<String, Object> map = new HashMap<>();
                JasperReport rpt = JasperCompileManager.compileReport("src/report/reportServiceRent.jrxml");
                System.out.println(MaDichvu);
                map.put("madv", MaDichvu);
                map.put("manv", MaNV);
                map.put("makh", MaKH);
                conn = cn.getConnection();
                JasperPrint p = JasperFillManager.fillReport(rpt, map, conn);
                JasperViewer.viewReport(p, false);
            } catch (JRException ex) {
                JOptionPane.showMessageDialog(this, ex.toString());
            }

        }
        
        
    }//GEN-LAST:event_btnReportActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable TBServiceRent;
    private swing.Button bthDelete;
    private swing.Button btnAdd;
    private swing.Button btnEdit;
    private javax.swing.JButton btnPay;
    private swing.Button btnRefresh;
    private javax.swing.JButton btnReport;
    private swing.Button button1;
    private swing.Button button2;
    private javax.swing.JComboBox<String> cmbMaNhanvien;
    private javax.swing.JComboBox<String> cmbmadichvu;
    private javax.swing.JComboBox<String> cmbmakhachhang;
    private com.toedter.calendar.JDateChooser jDateChooserngaylaphoadon;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private swing.RoundPanel roundPanel5;
    private javax.swing.JSpinner spSL;
    private javax.swing.JTextField txtGiaDichvu;
    private javax.swing.JTextField txtGiahd;
    private javax.swing.JTextField txtTenDichVu;
    private javax.swing.JTextField txtTenKH;
    private javax.swing.JTextField txtTenNhanVien;
    private javax.swing.JTextField txtloaiphong;
    // End of variables declaration//GEN-END:variables
}
