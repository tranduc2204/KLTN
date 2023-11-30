/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package form;

import connect.Connect;
import java.awt.Color;
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
import model.ModelCustomers;
import model.ModelCustomersv2;

/**
 *
 * @author TeeDee
 */
public class FormQLCustomers extends javax.swing.JPanel {

    /**
     * Creates new form FormQLCustomers
     */
    
    Connect cn = new Connect();
    Connection conn;
    DefaultTableModel tbmodel;
    
    
    public FormQLCustomers() {
        initComponents();
    }
    
    private String username, password, quyen, DisplayName;

    public FormQLCustomers(String username, String password, String DisplayName, String quyen) {
        initComponents();
        this.username = username;
        this.password = password;
        this.DisplayName = DisplayName;
        this.quyen = quyen;
        
        inittable();
        loadkhachhang();
        

    }

    private void inittable() {
        tbmodel = new DefaultTableModel();
        tbmodel.setColumnIdentifiers(new String[]{"Mã khách hàng", "Họ khách hàng", "Tên khách hàng", "Giới tính", "Địa chỉ", "Ngày sinh", "Số điện thoại"});
        TBKHACHHANG.setModel(tbmodel);
    }

    public void loadkhachhang() {
        try {
            ModelCustomers ql = new ModelCustomers();
            ArrayList<ModelCustomersv2> list = ql.findALL();
            tbmodel.setRowCount(0);
            for (ModelCustomersv2 kh : list) {
                tbmodel.addRow(new Object[]{
                    kh.getMaKH(), kh.getHoKH(), kh.getTenKH(), kh.getGioiTinh(),kh.getDiaChi(), kh.getNgaySinh(), kh.getSDT()
                });
            }
            tbmodel.fireTableDataChanged();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "error " + e.getMessage());
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

        buttonGroupsex = new javax.swing.ButtonGroup();
        buttonGroupfind = new javax.swing.ButtonGroup();
        panelBorder1 = new swing.PanelBorder();
        roundPanel5 = new swing.RoundPanel();
        jLabel15 = new javax.swing.JLabel();
        RBNNam = new javax.swing.JRadioButton();
        RBNNNU = new javax.swing.JRadioButton();
        btnEdit = new swing.Button();
        btnAdd = new swing.Button();
        bthDelete = new swing.Button();
        btnRefresh = new swing.Button();
        jDateChooserngaysinh = new com.toedter.calendar.JDateChooser();
        txtHOKH = new component.TextField();
        txtMAKH = new component.TextField();
        txtTENKH = new component.TextField();
        txtDIACHI = new component.TextField();
        txtSDT = new component.TextField();
        panelBorder2 = new swing.PanelBorder();
        rbMAKH1 = new javax.swing.JRadioButton();
        rbSDT1 = new javax.swing.JRadioButton();
        txtSEARCHMAKH = new javax.swing.JTextField();
        jTextField = new javax.swing.JTextField();
        rbNGAYSINH1 = new javax.swing.JRadioButton();
        btnSearch = new swing.Button();
        btnView = new swing.Button();
        jDateChooserTim = new com.toedter.calendar.JDateChooser();
        jScrollPane1 = new javax.swing.JScrollPane();
        TBKHACHHANG = new javax.swing.JTable();
        jLabel8 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();

        roundPanel5.setBackground(new java.awt.Color(36, 87, 157));
        roundPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Thông tin khách hàng", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 24), new java.awt.Color(255, 255, 255))); // NOI18N

        jLabel15.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(255, 255, 255));
        jLabel15.setText("Ngày sinh:");

        RBNNam.setBackground(new java.awt.Color(36, 87, 157));
        buttonGroupsex.add(RBNNam);
        RBNNam.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        RBNNam.setForeground(new java.awt.Color(255, 255, 255));
        RBNNam.setText("Nam");

        RBNNNU.setBackground(new java.awt.Color(36, 87, 157));
        buttonGroupsex.add(RBNNNU);
        RBNNNU.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        RBNNNU.setForeground(new java.awt.Color(255, 255, 255));
        RBNNNU.setText("Nữ");

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

        txtHOKH.setLabelText("Họ khách hàng");

        txtMAKH.setLabelText("Mã khách hàng");

        txtTENKH.setLabelText("Tên khách hàng");

        txtDIACHI.setLabelText("Địa chỉ");

        txtSDT.setLabelText("Số điện thoại");

        javax.swing.GroupLayout roundPanel5Layout = new javax.swing.GroupLayout(roundPanel5);
        roundPanel5.setLayout(roundPanel5Layout);
        roundPanel5Layout.setHorizontalGroup(
            roundPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(roundPanel5Layout.createSequentialGroup()
                .addGap(79, 79, 79)
                .addGroup(roundPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(roundPanel5Layout.createSequentialGroup()
                        .addComponent(jLabel15)
                        .addGap(25, 25, 25)
                        .addComponent(jDateChooserngaysinh, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(roundPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(txtMAKH, javax.swing.GroupLayout.PREFERRED_SIZE, 393, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtHOKH, javax.swing.GroupLayout.PREFERRED_SIZE, 393, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtTENKH, javax.swing.GroupLayout.PREFERRED_SIZE, 393, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGroup(roundPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(roundPanel5Layout.createSequentialGroup()
                        .addGap(121, 121, 121)
                        .addGroup(roundPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, roundPanel5Layout.createSequentialGroup()
                                .addGap(61, 61, 61)
                                .addComponent(RBNNam)
                                .addGap(69, 69, 69)
                                .addComponent(RBNNNU))
                            .addComponent(txtSDT, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 377, Short.MAX_VALUE)
                            .addComponent(txtDIACHI, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(roundPanel5Layout.createSequentialGroup()
                        .addGap(88, 88, 88)
                        .addComponent(btnAdd, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnEdit, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(bthDelete, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnRefresh, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 37, Short.MAX_VALUE))))
        );
        roundPanel5Layout.setVerticalGroup(
            roundPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(roundPanel5Layout.createSequentialGroup()
                .addGroup(roundPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(roundPanel5Layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(txtSDT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(roundPanel5Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(roundPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtMAKH, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtDIACHI, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(roundPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtHOKH, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(RBNNam)
                            .addComponent(RBNNNU))
                        .addGap(18, 18, Short.MAX_VALUE)
                        .addComponent(txtTENKH, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(roundPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, roundPanel5Layout.createSequentialGroup()
                        .addGroup(roundPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnEdit, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(bthDelete, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnRefresh, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnAdd, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(12, 12, 12))
                    .addGroup(roundPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(jLabel15)
                        .addComponent(jDateChooserngaysinh, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE))))
        );

        panelBorder2.setBackground(new java.awt.Color(36, 87, 157));
        panelBorder2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Tìm kiếm:", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 20), new java.awt.Color(255, 255, 255))); // NOI18N
        panelBorder2.setForeground(new java.awt.Color(255, 255, 255));

        rbMAKH1.setBackground(new java.awt.Color(36, 87, 157));
        buttonGroupfind.add(rbMAKH1);
        rbMAKH1.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        rbMAKH1.setForeground(new java.awt.Color(255, 255, 255));
        rbMAKH1.setText("Mã khách hàng:");

        rbSDT1.setBackground(new java.awt.Color(36, 87, 157));
        buttonGroupfind.add(rbSDT1);
        rbSDT1.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        rbSDT1.setForeground(new java.awt.Color(255, 255, 255));
        rbSDT1.setText("Số điện thoại:");

        rbNGAYSINH1.setBackground(new java.awt.Color(36, 87, 157));
        buttonGroupfind.add(rbNGAYSINH1);
        rbNGAYSINH1.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        rbNGAYSINH1.setForeground(new java.awt.Color(255, 255, 255));
        rbNGAYSINH1.setText("Ngày sinh:");

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
                        .addGroup(panelBorder2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(rbSDT1)
                            .addComponent(rbNGAYSINH1))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(panelBorder2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jTextField, javax.swing.GroupLayout.DEFAULT_SIZE, 245, Short.MAX_VALUE)
                            .addComponent(jDateChooserTim, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(panelBorder2Layout.createSequentialGroup()
                        .addComponent(rbMAKH1)
                        .addGap(31, 31, 31)
                        .addComponent(txtSEARCHMAKH, javax.swing.GroupLayout.PREFERRED_SIZE, 245, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(41, 41, 41)
                .addGroup(panelBorder2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnView, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panelBorder2Layout.setVerticalGroup(
            panelBorder2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBorder2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelBorder2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(rbMAKH1)
                    .addComponent(txtSEARCHMAKH, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(panelBorder2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelBorder2Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(panelBorder2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(rbSDT1)
                            .addComponent(jTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(panelBorder2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(rbNGAYSINH1)
                            .addComponent(jDateChooserTim, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(panelBorder2Layout.createSequentialGroup()
                        .addGap(27, 27, 27)
                        .addComponent(btnView, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        TBKHACHHANG.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        TBKHACHHANG.setModel(new javax.swing.table.DefaultTableModel(
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
        TBKHACHHANG.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TBKHACHHANGMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(TBKHACHHANG);

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel8.setText("Thông tin khách hàng:");

        javax.swing.GroupLayout panelBorder1Layout = new javax.swing.GroupLayout(panelBorder1);
        panelBorder1.setLayout(panelBorder1Layout);
        panelBorder1Layout.setHorizontalGroup(
            panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBorder1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(roundPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane1)
                    .addGroup(panelBorder1Layout.createSequentialGroup()
                        .addGroup(panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 183, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel8)
                            .addComponent(panelBorder2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        panelBorder1Layout.setVerticalGroup(
            panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBorder1Layout.createSequentialGroup()
                .addComponent(roundPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(panelBorder2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 21, Short.MAX_VALUE)
                .addComponent(jLabel8)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 12, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(64, 64, 64))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelBorder1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelBorder1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void TBKHACHHANGMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TBKHACHHANGMouseClicked
        // TODO add your handling code here:
        int row = TBKHACHHANG.getSelectedRow();

        //if (row >= 0) {
            txtMAKH.setText(TBKHACHHANG.getValueAt(row, 0).toString());
            txtHOKH.setText(TBKHACHHANG.getValueAt(row, 1).toString());
            txtTENKH.setText(TBKHACHHANG.getValueAt(row, 2).toString());
            String gioitinh = TBKHACHHANG.getValueAt(row, 3).toString();
            if (gioitinh.equals("1")) {
                RBNNam.setSelected(true);
                RBNNNU.setSelected(false);
            } else {
                RBNNNU.setSelected(true);
                RBNNam.setSelected(false);

            }
            txtDIACHI.setText(TBKHACHHANG.getValueAt(row, 4).toString());
            String ngaysinh = TBKHACHHANG.getValueAt(row, 5).toString();
            
            txtSDT.setText(TBKHACHHANG.getValueAt(row, 6).toString());

            System.out.println("" + ngaysinh);
            String strngay, strthang, strnam;
            strngay = ngaysinh.substring(8, 10);
            strthang = ngaysinh.substring(5, 7);
            strnam = ngaysinh.substring(0, 4);
            int ngay, thang, nam;
            ngay = Integer.valueOf(strngay);
            thang = Integer.valueOf(strthang);
            nam = Integer.valueOf(strnam);
            
            
            String dateStr = TBKHACHHANG.getValueAt(row, 5).toString();
            System.out.println(dateStr);
//            java.sql.Date selectedDate = java.sql.Date.valueOf(dateStr);
//            jDateChooserngaysinh.setDate(selectedDate);
            try {
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd"); // Định dạng của chuỗi ngày
                Date selectedDate = dateFormat.parse(dateStr); // Phân tích chuỗi thành đối tượng Date
                jDateChooserngaysinh.setDate(selectedDate); // Đặt giá trị ngày cho JDateChooser
            } catch (ParseException e) {
                e.printStackTrace();
            }
            
           
    }//GEN-LAST:event_TBKHACHHANGMouseClicked

    private void btnAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddActionPerformed
        // TODO add your handling code here:
//        String ngay = cmbNGAY.getSelectedItem().toString();
//        String thang = cmbTHANG.getSelectedItem().toString();
//        String nam = cmbNAM.getSelectedItem().toString();
//        String ngaysinh = nam + "-" + thang + "-" + ngay;
        Date selectedDate = jDateChooserngaysinh.getDate();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(selectedDate);

        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH) + 1; // Tháng bắt đầu từ 0, nên cộng thêm 1 để có giá trị tháng thực
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        String ngaysinh = year + "-" + month + "-" + day;
        
        StringBuilder sb = new StringBuilder();
        if (txtMAKH.getText().equals("")) {
            sb.append("mã khách hàng không được để trống");
            txtMAKH.setBackground(Color.red);

        } else {
            txtMAKH.setBackground(Color.white);
        }
        if (sb.length() > 0) {
            JOptionPane.showMessageDialog(this, sb);
        }
        try {
            ModelCustomersv2 kh = new ModelCustomersv2();
            kh.setMaKH(txtMAKH.getText());
            kh.setHoKH(txtHOKH.getText());
            kh.setTenKH(txtTENKH.getText());
            kh.setGioiTinh(RBNNam.isSelected() ? 1 : 0);
            kh.setDiaChi(txtDIACHI.getText());
            kh.setNgaySinh(ngaysinh);
            kh.setSDT(txtSDT.getText());

            ModelCustomers ql = new ModelCustomers();
            ql.insert(kh);

            JOptionPane.showMessageDialog(this, "Lưu thành công");
            loadkhachhang();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "error " + e.getMessage());
            e.printStackTrace();
        }
    }//GEN-LAST:event_btnAddActionPerformed

    private void btnEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditActionPerformed
        // TODO add your handling code here:
//        String ngay = cmbNGAY.getSelectedItem().toString();
//        String thang = cmbTHANG.getSelectedItem().toString();
//        String nam = cmbNAM.getSelectedItem().toString();
//        String ngaysinh = nam + "-" + thang + "-" + ngay;
        Date selectedDate = jDateChooserngaysinh.getDate();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(selectedDate);

        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH) + 1; // Tháng bắt đầu từ 0, nên cộng thêm 1 để có giá trị tháng thực
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        String ngaysinh = year + "-" + month + "-" + day;

        StringBuilder sb = new StringBuilder();
        if (txtMAKH.getText().equals("")) {
            sb.append("mã khách hàng không được để trống");
            txtMAKH.setBackground(Color.red);

        } else {
            txtMAKH.setBackground(Color.white);
        }
        if (sb.length() > 0) {
            JOptionPane.showMessageDialog(this, sb);
        }
        if (JOptionPane.showConfirmDialog(this, "bạn có muốn sửa khách hàng không??") == JOptionPane.NO_OPTION) {
            return;
        }
        try {

            ModelCustomersv2 kh = new ModelCustomersv2();
            kh.setMaKH(txtMAKH.getText());
            kh.setHoKH(txtHOKH.getText());
            kh.setTenKH(txtTENKH.getText());
            kh.setGioiTinh(RBNNam.isSelected() ? 1 : 0);
            kh.setDiaChi(txtDIACHI.getText());
            kh.setNgaySinh(ngaysinh);
            kh.setSDT(txtSDT.getText());

            ModelCustomers ql = new ModelCustomers();
            ql.update(kh);

            JOptionPane.showMessageDialog(this, "sửa thành công");
            loadkhachhang();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "error " + e.getMessage());
            e.printStackTrace();
        }
    }//GEN-LAST:event_btnEditActionPerformed

    private void bthDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bthDeleteActionPerformed
        // TODO add your handling code here:
        StringBuilder sb = new StringBuilder();
        if (txtMAKH.getText().equals("")) {
            sb.append("mã khách hàng không được để trống");
            txtMAKH.setBackground(Color.red);

        } else {
            txtMAKH.setBackground(Color.white);
        }
        if (sb.length() > 0) {
            JOptionPane.showMessageDialog(this, sb);
        }
        if (JOptionPane.showConfirmDialog(this, "bạn có muốn sửa khách hàng không??") == JOptionPane.NO_OPTION) {
            return;
        }
        try {
            ModelCustomersv2 khh = new ModelCustomersv2();
            khh.setMaKH(txtMAKH.getText());
            ModelCustomers qll = new ModelCustomers();
            qll.deletecomeroot(khh);

            JOptionPane.showMessageDialog(this, "xóa thành công");
            loadkhachhang();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "error " + e.getMessage());
            e.printStackTrace();
        }
    }//GEN-LAST:event_bthDeleteActionPerformed

    private void btnRefreshActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRefreshActionPerformed
        // TODO add your handling code here:
        txtDIACHI.setText("");
        txtHOKH.setText("");
        txtMAKH.setText("");
        jDateChooserngaysinh.setDate(null);
        txtSDT.setText("");
        txtTENKH.setText("");
        txtSEARCHMAKH.setText("");
        jTextField.setText("");
        jDateChooserTim.setDate(null);
        RBNNam.setSelected(false);
        RBNNNU.setSelected(false);
        txtMAKH.requestFocus();
        
    }//GEN-LAST:event_btnRefreshActionPerformed

    private void btnSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSearchActionPerformed
        // TODO add your handling code here:
        
        
        
        if (rbMAKH1.isSelected() == false && rbNGAYSINH1.isSelected() == false && rbSDT1.isSelected() == false) {
            JOptionPane.showMessageDialog(this, "bạn hãy chọn tiêu chí tìm kiếm");
        } else if (rbMAKH1.isSelected() == true) {

            try {

                String sql = "select * from khachhang where makh = ? and isvisible = '1'  ";
                conn = cn.getConnection();
                PreparedStatement pstmt = conn.prepareStatement(sql);

                pstmt.setString(1, txtSEARCHMAKH.getText());
                ResultSet rs = pstmt.executeQuery();
                tbmodel.setRowCount(0);
                while (rs.next()) {
                    tbmodel.addRow(new Object[]{
                        rs.getString("makh"), rs.getString("hokh"), rs.getString("tenkh"), rs.getString("tenkh"), rs.getString("gioitinh"), rs.getString("diachi"), rs.getString("ngaysinh"), rs.getString("sodt")

                    });
                }
                tbmodel.fireTableDataChanged();
                //loadthongtintaikhoan();

            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, e.getMessage());
                e.printStackTrace();
            }

        } else if (rbSDT1.isSelected() == true) {
            try {
                
                
                

                String sql = "select * from khachhang where sodt = ? and isvisible = '1'  ";
                conn = cn.getConnection();
                PreparedStatement pstmt = conn.prepareStatement(sql);

                pstmt.setString(1, txtSEARCHMAKH.getText());
                ResultSet rs = pstmt.executeQuery();
                tbmodel.setRowCount(0);
                while (rs.next()) {
                    tbmodel.addRow(new Object[]{
                        rs.getString("makh"), rs.getString("hokh"), rs.getString("tenkh"), rs.getString("tenkh"), rs.getString("gioitinh"), rs.getString("diachi"), rs.getString("ngaysinh"), rs.getString("sodt")

                    });
                }
                tbmodel.fireTableDataChanged();
                //loadthongtintaikhoan();

            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, e.getMessage());
                e.printStackTrace();
            }
        } else if (rbNGAYSINH1.isSelected() == true) {
            try {
                Date selectedDate = jDateChooserTim.getDate();
                Calendar calendar = Calendar.getInstance();
                calendar.setTime(selectedDate);

                int year = calendar.get(Calendar.YEAR);
                int month = calendar.get(Calendar.MONTH) + 1; // Tháng bắt đầu từ 0, nên cộng thêm 1 để có giá trị tháng thực
                int day = calendar.get(Calendar.DAY_OF_MONTH);
                String ngaytranef = year + "-" + month + "-" + day;
                
                

                String sql = "select * from khachhang where ngaysinh = ? and isvisible = '1'  ";
                conn = cn.getConnection();
                PreparedStatement pstmt = conn.prepareStatement(sql);

                pstmt.setString(1, ngaytranef);
                ResultSet rs = pstmt.executeQuery();
                tbmodel.setRowCount(0);
                while (rs.next()) {
                    tbmodel.addRow(new Object[]{
                        rs.getString("makh"), rs.getString("hokh"), rs.getString("tenkh"), rs.getString("tenkh"), rs.getString("gioitinh"), rs.getString("diachi"), rs.getString("ngaysinh"), rs.getString("sodt")

                    });
                }
                tbmodel.fireTableDataChanged();
                //loadthongtintaikhoan();

            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, e.getMessage());
                e.printStackTrace();
            }
        }
    }//GEN-LAST:event_btnSearchActionPerformed

    private void btnViewActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnViewActionPerformed
        // TODO add your handling code here:
        loadkhachhang();
    }//GEN-LAST:event_btnViewActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JRadioButton RBNNNU;
    private javax.swing.JRadioButton RBNNam;
    private javax.swing.JTable TBKHACHHANG;
    private swing.Button bthDelete;
    private swing.Button btnAdd;
    private swing.Button btnEdit;
    private swing.Button btnRefresh;
    private swing.Button btnSearch;
    private swing.Button btnView;
    private javax.swing.ButtonGroup buttonGroupfind;
    private javax.swing.ButtonGroup buttonGroupsex;
    private com.toedter.calendar.JDateChooser jDateChooserTim;
    private com.toedter.calendar.JDateChooser jDateChooserngaysinh;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTextField jTextField;
    private swing.PanelBorder panelBorder1;
    private swing.PanelBorder panelBorder2;
    private javax.swing.JRadioButton rbMAKH1;
    private javax.swing.JRadioButton rbNGAYSINH1;
    private javax.swing.JRadioButton rbSDT1;
    private swing.RoundPanel roundPanel5;
    private component.TextField txtDIACHI;
    private component.TextField txtHOKH;
    private component.TextField txtMAKH;
    private component.TextField txtSDT;
    private javax.swing.JTextField txtSEARCHMAKH;
    private component.TextField txtTENKH;
    // End of variables declaration//GEN-END:variables
}
