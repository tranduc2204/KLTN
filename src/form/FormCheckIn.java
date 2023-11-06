/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package form; 

import connect.Connect;
import java.awt.Color;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import model.ModelCheckIn;
import model.ModelCheckInv2;
import model.ModelCustomers;
import model.ModelRent;
import model.ModelRentv2;
import model.ModelRoom;

/**
 *
 * @author TeeDee
 */
public class FormCheckIn extends javax.swing.JPanel {

    /**
     * Creates new form FormCheckIn
     */
    public FormCheckIn() {
        initComponents();
    }
    
    Connect cn = new Connect();
    Connection conn;
    DefaultTableModel tbmodel;

    private String username, password, quyen, DisplayName;
    
    public FormCheckIn(String username, String password, String DisplayName, String quyen) {
        initComponents();
        this.username = username;
        this.password = password;
        this.DisplayName = DisplayName;
        this.quyen = quyen;
        
        
        initCombobox_maphieudatphong();
  
       

        inittable();
        loaddulieu1();
    }
    
    private void inittable() {
        tbmodel = new DefaultTableModel();
        tbmodel.setColumnIdentifiers(new String[]{"Mã phiếu thuê phòng", "Mã phiếu đặt phòng", "Ngày đặt phòng", "Mã khách hàng", "Tên khách hàng", "Mã phòng", "Tên phòng", "Loại phòng",  "Giá phòng", "Ngày thuê phòng"});
        TBRent.setModel(tbmodel);
    }
    
    public void loaddulieu1() {
        try {
            ModelCheckIn ql = new ModelCheckIn();
            ArrayList<ModelCheckInv2> list = ql.findALL();
            tbmodel.setRowCount(0);
            for (ModelCheckInv2 p : list) {
                tbmodel.addRow(new Object[]{
                    p.getMaPhieuThuePhong(), p.getMaPhieuDatPhong(), p.getNgayDatPhong(), p.getMaKH(), p.getTenKH(), p.getMaPhong(),p.getTenPhong(), p.getLoaiPhong(),  p.getGia(), p.getNgayThuePhong()
                });
            }
            tbmodel.fireTableDataChanged();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "error " + e.getMessage());
            e.printStackTrace();
        }

    }
     
   
    
    
    private void initCombobox_maphieudatphong() {
        try {
            conn = cn.getConnection();
            String sql = "Select MaPhieuDatPhong from PhieuDatPhong where isvisible = '1' ";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();
            cmbmaphieudatphong.removeAllItems();
            cmbmaphieudatphong.removeAllItems();
            while (rs.next()) {
                cmbmaphieudatphong.addItem(rs.getString("MaPhieuDatPhong"));
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

        roundPanel5 = new swing.RoundPanel();
        jLabel10 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        txtTenPhong = new javax.swing.JTextField();
        txtMaphieuthuephong = new javax.swing.JTextField();
        btnEdit = new swing.Button();
        btnAdd = new swing.Button();
        bthDelete = new swing.Button();
        btnRefresh = new swing.Button();
        txtGiaPhong = new javax.swing.JTextField();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        txtTenKH = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        jDateChooserngaydatphong = new com.toedter.calendar.JDateChooser();
        jLabel20 = new javax.swing.JLabel();
        cmbmaphieudatphong = new javax.swing.JComboBox<>();
        txtloaiphong = new javax.swing.JTextField();
        jLabel21 = new javax.swing.JLabel();
        jDateChooserngaythuephong = new com.toedter.calendar.JDateChooser();
        txtMaKH = new javax.swing.JTextField();
        txtMaPhong = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        TBRent = new javax.swing.JTable();
        jLabel8 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        panelBorder2 = new swing.PanelBorder();
        rbMAKH1 = new javax.swing.JRadioButton();
        txtSEARCHMAKH = new javax.swing.JTextField();
        rbsearchngaydat = new javax.swing.JRadioButton();
        btnSearch = new swing.Button();
        btnView = new swing.Button();
        jDateChooserTim = new com.toedter.calendar.JDateChooser();

        roundPanel5.setBackground(new java.awt.Color(77, 79, 45));
        roundPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Thông tin thuê phòng", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 24), new java.awt.Color(255, 255, 255))); // NOI18N

        jLabel10.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setText("Mã phiếu thuê phòng:");

        jLabel12.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(255, 255, 255));
        jLabel12.setText("Mã phiếu đặt phòng:");

        jLabel14.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(255, 255, 255));
        jLabel14.setText("Mã phòng:");

        jLabel16.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(255, 255, 255));
        jLabel16.setText("Tên phòng:");

        txtTenPhong.setEnabled(false);

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

        txtGiaPhong.setEnabled(false);

        jLabel17.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(255, 255, 255));
        jLabel17.setText("Giá phòng:");

        jLabel18.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        jLabel18.setForeground(new java.awt.Color(255, 255, 255));
        jLabel18.setText("Mã khách hàng:");

        jLabel19.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        jLabel19.setForeground(new java.awt.Color(255, 255, 255));
        jLabel19.setText("Tên khách hàng:");

        txtTenKH.setEnabled(false);

        jLabel15.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(255, 255, 255));
        jLabel15.setText("Ngày đặt phòng:");

        jDateChooserngaydatphong.setEnabled(false);

        jLabel20.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        jLabel20.setForeground(new java.awt.Color(255, 255, 255));
        jLabel20.setText("Loại phòng:");

        cmbmaphieudatphong.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cmbmaphieudatphongItemStateChanged(evt);
            }
        });

        txtloaiphong.setEnabled(false);

        jLabel21.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        jLabel21.setForeground(new java.awt.Color(255, 255, 255));
        jLabel21.setText("Ngày thuê phòng:");

        txtMaKH.setEnabled(false);

        txtMaPhong.setEnabled(false);

        javax.swing.GroupLayout roundPanel5Layout = new javax.swing.GroupLayout(roundPanel5);
        roundPanel5.setLayout(roundPanel5Layout);
        roundPanel5Layout.setHorizontalGroup(
            roundPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(roundPanel5Layout.createSequentialGroup()
                .addGroup(roundPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(roundPanel5Layout.createSequentialGroup()
                        .addGap(51, 51, 51)
                        .addGroup(roundPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel10)
                            .addComponent(jLabel12)
                            .addComponent(jLabel18)
                            .addComponent(jLabel19)
                            .addComponent(jLabel15))
                        .addGap(18, 18, 18)
                        .addGroup(roundPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(txtMaphieuthuephong, javax.swing.GroupLayout.DEFAULT_SIZE, 249, Short.MAX_VALUE)
                            .addComponent(txtTenKH, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(cmbmaphieudatphong, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jDateChooserngaydatphong, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtMaKH))
                        .addGap(53, 53, 53))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, roundPanel5Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(btnAdd, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(19, 19, 19)
                        .addComponent(btnEdit, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)))
                .addGroup(roundPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(roundPanel5Layout.createSequentialGroup()
                        .addComponent(bthDelete, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(31, 31, 31)
                        .addComponent(btnRefresh, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(roundPanel5Layout.createSequentialGroup()
                        .addGap(17, 17, 17)
                        .addGroup(roundPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(roundPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(roundPanel5Layout.createSequentialGroup()
                                    .addGroup(roundPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel14)
                                        .addComponent(jLabel16))
                                    .addGap(70, 70, 70)
                                    .addGroup(roundPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(txtTenPhong, javax.swing.GroupLayout.PREFERRED_SIZE, 281, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(txtMaPhong, javax.swing.GroupLayout.PREFERRED_SIZE, 281, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, roundPanel5Layout.createSequentialGroup()
                                    .addComponent(jLabel21)
                                    .addGap(18, 18, 18)
                                    .addComponent(jDateChooserngaythuephong, javax.swing.GroupLayout.PREFERRED_SIZE, 281, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(roundPanel5Layout.createSequentialGroup()
                                .addGroup(roundPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel20)
                                    .addComponent(jLabel17))
                                .addGap(67, 67, 67)
                                .addGroup(roundPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtloaiphong, javax.swing.GroupLayout.PREFERRED_SIZE, 281, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtGiaPhong, javax.swing.GroupLayout.PREFERRED_SIZE, 281, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                .addContainerGap(60, Short.MAX_VALUE))
        );
        roundPanel5Layout.setVerticalGroup(
            roundPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(roundPanel5Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(roundPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(jLabel14)
                    .addComponent(txtMaphieuthuephong, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtMaPhong, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(26, 26, 26)
                .addGroup(roundPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel12)
                    .addComponent(jLabel16)
                    .addComponent(txtTenPhong, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cmbmaphieudatphong, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(27, 27, 27)
                .addGroup(roundPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(roundPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel20)
                        .addComponent(txtloaiphong, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel15))
                    .addComponent(jDateChooserngaydatphong, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(25, 25, 25)
                .addGroup(roundPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel18)
                    .addComponent(jLabel17)
                    .addComponent(txtGiaPhong, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtMaKH, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(26, 26, 26)
                .addGroup(roundPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(roundPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel19)
                        .addComponent(txtTenKH, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel21))
                    .addComponent(jDateChooserngaythuephong, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(roundPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(roundPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnEdit, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(bthDelete, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnRefresh, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(btnAdd, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        TBRent.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        TBRent.setModel(new javax.swing.table.DefaultTableModel(
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
        TBRent.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TBRentMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(TBRent);

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel8.setText("Thông tin thuê phòng:");

        panelBorder2.setBackground(new java.awt.Color(77, 79, 45));
        panelBorder2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Tìm kiếm:", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 20), new java.awt.Color(255, 255, 255))); // NOI18N
        panelBorder2.setForeground(new java.awt.Color(255, 255, 255));

        rbMAKH1.setBackground(new java.awt.Color(77, 79, 45));
        rbMAKH1.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        rbMAKH1.setForeground(new java.awt.Color(255, 255, 255));
        rbMAKH1.setText("Mã khách hàng:");

        rbsearchngaydat.setBackground(new java.awt.Color(77, 79, 45));
        rbsearchngaydat.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        rbsearchngaydat.setForeground(new java.awt.Color(255, 255, 255));
        rbsearchngaydat.setText("Ngày đặt phòng:");

        btnSearch.setText("Tìm kiếm");
        btnSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSearchActionPerformed(evt);
            }
        });

        btnView.setText("Xem");
        btnView.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnViewActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelBorder2Layout = new javax.swing.GroupLayout(panelBorder2);
        panelBorder2.setLayout(panelBorder2Layout);
        panelBorder2Layout.setHorizontalGroup(
            panelBorder2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBorder2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelBorder2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(panelBorder2Layout.createSequentialGroup()
                        .addComponent(rbsearchngaydat)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jDateChooserTim, javax.swing.GroupLayout.PREFERRED_SIZE, 245, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panelBorder2Layout.createSequentialGroup()
                        .addComponent(rbMAKH1)
                        .addGap(31, 31, 31)
                        .addComponent(txtSEARCHMAKH, javax.swing.GroupLayout.PREFERRED_SIZE, 245, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 53, Short.MAX_VALUE)
                .addGroup(panelBorder2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnView, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(37, 37, 37))
        );
        panelBorder2Layout.setVerticalGroup(
            panelBorder2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelBorder2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(panelBorder2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(rbMAKH1)
                    .addComponent(txtSEARCHMAKH, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(15, 15, 15)
                .addGroup(panelBorder2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnView, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(panelBorder2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(jDateChooserTim, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(rbsearchngaydat)))
                .addGap(12, 12, 12))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(roundPanel5, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane1)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel8)
                            .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 183, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(panelBorder2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(roundPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(panelBorder2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 78, Short.MAX_VALUE)
                .addComponent(jLabel8)
                .addGap(2, 2, 2)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 12, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(4, 4, 4)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditActionPerformed
        // TODO add your handling code here:
        Date selectedDate = jDateChooserngaythuephong.getDate();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(selectedDate);

        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH) + 1; // Tháng bắt đầu từ 0, nên cộng thêm 1 để có giá trị tháng thực
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        String ngaythue = year + "-" + month + "-" + day;

        StringBuilder sb = new StringBuilder();
        if (txtMaphieuthuephong.getText().equals("")) {
            sb.append("Mã phiếu thuê phòng không được để trống");
            txtMaphieuthuephong.setBackground(Color.red);

        } else {
            txtMaphieuthuephong.setBackground(Color.white);
        }
        if (sb.length() > 0) {
            JOptionPane.showMessageDialog(this, sb);
        }
        String Madatphong = cmbmaphieudatphong.getSelectedItem().toString();
        
        try {
            ModelCheckInv2 ci = new ModelCheckInv2();
            ci.setMaPhieuThuePhong(txtMaphieuthuephong.getText());
            ci.setNgayThuePhong(ngaythue);
            ci.setMaPhieuDatPhong(Madatphong);
            

            ModelCheckIn ql1 = new ModelCheckIn();
            ql1.update(ci);

            JOptionPane.showMessageDialog(this, "Lưu thành công!!!");
            loaddulieu1();
        } catch (Exception e) {
            //            JOptionPane.showMessageDialog(this, "error " + e.getMessage());
            JOptionPane.showMessageDialog(this, "Mã phiếu thuê phòng này đã tồn tại, nếu muốn thêm với mã phiếu đặt phòng này vui lòng xóa phiếu đặt phòng trong db");
            e.printStackTrace();
        }

    }//GEN-LAST:event_btnEditActionPerformed

    private void btnAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddActionPerformed
        // TODO add your handling code here:

        Date selectedDate = jDateChooserngaythuephong.getDate();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(selectedDate);

        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH) + 1; // Tháng bắt đầu từ 0, nên cộng thêm 1 để có giá trị tháng thực
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        String ngaythue = year + "-" + month + "-" + day;

        StringBuilder sb = new StringBuilder();
        if (txtMaphieuthuephong.getText().equals("")) {
            sb.append("Mã phiếu thuê phòng không được để trống");
            txtMaphieuthuephong.setBackground(Color.red);

        } else {
            txtMaphieuthuephong.setBackground(Color.white);
        }
        if (sb.length() > 0) {
            JOptionPane.showMessageDialog(this, sb);
        }
        
        
        String Madatphong = cmbmaphieudatphong.getSelectedItem().toString();
        
        try {
            ModelCheckInv2 ci = new ModelCheckInv2();
            ci.setMaPhieuThuePhong(txtMaphieuthuephong.getText());
            ci.setNgayThuePhong(ngaythue);
            ci.setMaPhieuDatPhong(Madatphong);
            

            ModelCheckIn ql1 = new ModelCheckIn();
            ql1.insert(ci);

            JOptionPane.showMessageDialog(this, "Lưu thành công!!!");
            loaddulieu1();
        } catch (Exception e) {
            //            JOptionPane.showMessageDialog(this, "error " + e.getMessage());
            JOptionPane.showMessageDialog(this, "Mã phiếu thuê phòng này đã tồn tại, nếu muốn thêm với mã phiếu thuê phòng này vui lòng xóa phiếu đặt phòng trong db");
            e.printStackTrace();
        }

    }//GEN-LAST:event_btnAddActionPerformed

    private void bthDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bthDeleteActionPerformed
        // TODO add your handling code here:

        StringBuilder sb = new StringBuilder();
        if (txtMaphieuthuephong.getText().equals("")) {
            sb.append("Mã phiếu thuê phòng không được để trống");
            txtMaphieuthuephong.setBackground(Color.red);

        } else {
            txtMaphieuthuephong.setBackground(Color.white);
        }
        if (sb.length() > 0) {
            JOptionPane.showMessageDialog(this, sb);
        }

        try {
            ModelCheckInv2 ci = new ModelCheckInv2();
            ci.setMaPhieuThuePhong(txtMaphieuthuephong.getText());

            ModelCheckIn ql1 = new ModelCheckIn();
            ql1.deletecomeroot(ci);

            JOptionPane.showMessageDialog(this, "Lưu thành công!!!");
            loaddulieu1();
        } catch (Exception e) {
            //            JOptionPane.showMessageDialog(this, "error " + e.getMessage());
            JOptionPane.showMessageDialog(this, "Mã phiếu đặt phòng này đã tồn tại, nếu muốn thêm với mã phiếu đặt phòng này vui lòng xóa phiếu đặt phòng trong db");
            e.printStackTrace();
        }

    }//GEN-LAST:event_bthDeleteActionPerformed

    private void btnRefreshActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRefreshActionPerformed
        // TODO add your handling code here:

    }//GEN-LAST:event_btnRefreshActionPerformed

    private void TBRentMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TBRentMouseClicked
        // TODO add your handling code here:
        int row = TBRent.getSelectedRow();

        if (row >= 0) {
            txtMaphieuthuephong.setText(TBRent.getValueAt(row, 0).toString());

            String maphieudatphong = TBRent.getValueAt(row, 1).toString();
            System.out.println("" + maphieudatphong);
            cmbmaphieudatphong.setSelectedItem(maphieudatphong);

            txtMaKH.setText(TBRent.getValueAt(row, 3).toString());
            

            txtTenKH.setText(TBRent.getValueAt(row, 4).toString());
            txtMaPhong.setText(TBRent.getValueAt(row, 5).toString());
            txtTenPhong.setText(TBRent.getValueAt(row, 6).toString());
            txtloaiphong.setText(TBRent.getValueAt(row, 7).toString());
        
            
            ModelCheckInv2 ms =new ModelCheckInv2();
            BigDecimal x = (BigDecimal) TBRent.getValueAt(row, 8);

            ms.setGia(x);

            String formattedDonGia = ms.getFormattedGia();
            System.out.println(formattedDonGia);
            txtGiaPhong.setText(formattedDonGia);
            
            
            String ngaythuephong = TBRent.getValueAt(row, 9).toString();

            try {
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd"); // Định dạng của chuỗi ngày
                Date selectedDate = dateFormat.parse(ngaythuephong); // Phân tích chuỗi thành đối tượng Date
                jDateChooserngaythuephong.setDate(selectedDate); // Đặt giá trị ngày cho JDateChooser
            } catch (ParseException e) {
                e.printStackTrace();
            }

           

        }
    }//GEN-LAST:event_TBRentMouseClicked

    private void btnSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSearchActionPerformed
        // TODO add your handling code here:

    }//GEN-LAST:event_btnSearchActionPerformed

    private void btnViewActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnViewActionPerformed
        // TODO add your handling code here:

    }//GEN-LAST:event_btnViewActionPerformed

    private void cmbmaphieudatphongItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cmbmaphieudatphongItemStateChanged
        // TODO add your handling code here:
        String Mapdp = cmbmaphieudatphong.getSelectedItem().toString();
        try {
            ModelRent ql = new ModelRent();

            model.ModelRentv2 ttp = ql.findByID(Mapdp);
            if (ttp != null) {

                String ngaydatphong = ttp.getNgayDatPhong();

                try {
                    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd"); // Định dạng của chuỗi ngày
                    Date selectedDatee = dateFormat.parse(ngaydatphong); // Phân tích chuỗi thành đối tượng Date
                    jDateChooserngaydatphong.setDate(selectedDatee); // Đặt giá trị ngày cho JDateChooser
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            } else {
                JOptionPane.showMessageDialog(this, "Không tim thấy mã khách hàng");
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "error " + e.getMessage());
            e.printStackTrace();
        }
    }//GEN-LAST:event_cmbmaphieudatphongItemStateChanged


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable TBRent;
    private swing.Button bthDelete;
    private swing.Button btnAdd;
    private swing.Button btnEdit;
    private swing.Button btnRefresh;
    private swing.Button btnSearch;
    private swing.Button btnView;
    private javax.swing.JComboBox<String> cmbmaphieudatphong;
    private com.toedter.calendar.JDateChooser jDateChooserTim;
    private com.toedter.calendar.JDateChooser jDateChooserngaydatphong;
    private com.toedter.calendar.JDateChooser jDateChooserngaythuephong;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private swing.PanelBorder panelBorder2;
    private javax.swing.JRadioButton rbMAKH1;
    private javax.swing.JRadioButton rbsearchngaydat;
    private swing.RoundPanel roundPanel5;
    private javax.swing.JTextField txtGiaPhong;
    private javax.swing.JTextField txtMaKH;
    private javax.swing.JTextField txtMaPhong;
    private javax.swing.JTextField txtMaphieuthuephong;
    private javax.swing.JTextField txtSEARCHMAKH;
    private javax.swing.JTextField txtTenKH;
    private javax.swing.JTextField txtTenPhong;
    private javax.swing.JTextField txtloaiphong;
    // End of variables declaration//GEN-END:variables
}
