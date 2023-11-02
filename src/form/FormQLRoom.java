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
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import model.ModelPhong;
import model.ModelPhongv2;
import model.ModelRoom;
import model.ModelRoomLP;
import model.ModelRoomTT;
import model.ModelRoomv2;
import model.ModelServicev2;

/**
 *
 * @author TeeDee
 */
public class FormQLRoom extends javax.swing.JPanel {

    /**
     * Creates new form FormQLRoom
     */
    public FormQLRoom() {
        initComponents();
    }
    
    Connect cn = new Connect();
    Connection conn;
    DefaultTableModel tbmodel;

    private String username, password, quyen, DisplayName;
    
     public FormQLRoom(String username, String password, String DisplayName, String quyen) {
        initComponents();
        this.username = username;
        this.password = password;
        this.DisplayName = DisplayName;
        this.quyen = quyen;
        
        initCombobox_mattphong();
        initCombobox_maloaiphong();
        inittable();
        loaddulieu1();
    }

    private void inittable() {
        tbmodel = new DefaultTableModel();
        tbmodel.setColumnIdentifiers(new String[]{"Mã phòng", "Tên phòng", "Mã tình trạng phòng", "Tình trạng phòng", "Mã loại phòng", "Tên loại phòng",  "Giá phòng"});
        tbPHONG.setModel(tbmodel);
    }

    public void loaddulieu1() {
        try {
            ModelRoom ql = new ModelRoom();
            ArrayList<ModelRoomv2> list = ql.findALL();
            tbmodel.setRowCount(0);
            for (ModelRoomv2 p : list) {
                tbmodel.addRow(new Object[]{
                    p.getMaPhong(), p.getTenPhong(), p.getMaTinhTrangPhong(), p.getTinhtrangphong(), p.getMaLoaiPhong(), p.getTenLoaiPhong(),  p.getTien()
                });
            }
            tbmodel.fireTableDataChanged();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "error " + e.getMessage());
            e.printStackTrace();
        }

    }

    private void initCombobox_mattphong() {
        try {
            conn = cn.getConnection();
            String sql = "Select matinhtrangphong from tinhtrangphong";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();
            cmbMATINHTRANGPHONG.removeAllItems();
            while (rs.next()) {
                cmbMATINHTRANGPHONG.addItem(rs.getString("matinhtrangphong"));
            }
            rs.close();
            pstmt.close();
            conn.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
            e.printStackTrace();
        }
    }

    private void initCombobox_maloaiphong() {
        try {
            conn = cn.getConnection();
            String sql = "Select maloaiphong from LoaiPhong";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();
            cmbMALOAIIPHONGa.removeAllItems();
            while (rs.next()) {
                cmbMALOAIIPHONGa.addItem(rs.getString("MaLoaiPhong"));
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

        buttonGroup1 = new javax.swing.ButtonGroup();
        roundPanel2 = new swing.RoundPanel();
        jLabel33 = new javax.swing.JLabel();
        jLabel34 = new javax.swing.JLabel();
        jLabel35 = new javax.swing.JLabel();
        txtTENPHONG = new javax.swing.JTextField();
        txtTTPHONG = new javax.swing.JTextField();
        jLabel37 = new javax.swing.JLabel();
        jLabel38 = new javax.swing.JLabel();
        jLabel39 = new javax.swing.JLabel();
        jLabel40 = new javax.swing.JLabel();
        txtTLP = new javax.swing.JTextField();
        txtDONGIA = new javax.swing.JTextField();
        txtMAPHONG = new javax.swing.JTextField();
        cmbMATINHTRANGPHONG = new javax.swing.JComboBox<>();
        cmbMALOAIIPHONGa = new javax.swing.JComboBox<>();
        btnAdd = new swing.Button();
        btnEdit = new swing.Button();
        btnDelete = new swing.Button();
        btnRefresh = new swing.Button();
        panelBorder2 = new swing.PanelBorder();
        rbMAPHONG = new javax.swing.JRadioButton();
        txtsearchmaphong = new javax.swing.JTextField();
        rbDONGIA = new javax.swing.JRadioButton();
        jLabel43 = new javax.swing.JLabel();
        txttugia = new javax.swing.JTextField();
        jLabel44 = new javax.swing.JLabel();
        txtdengia = new javax.swing.JTextField();
        btnSearch = new swing.Button();
        btnView = new swing.Button();
        jScrollPane3 = new javax.swing.JScrollPane();
        tbPHONG = new javax.swing.JTable();
        jLabel8 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();

        roundPanel2.setBackground(new java.awt.Color(77, 79, 45));
        roundPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Thông tin phòng", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 24), new java.awt.Color(255, 255, 255))); // NOI18N
        roundPanel2.setForeground(new java.awt.Color(255, 255, 255));

        jLabel33.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        jLabel33.setForeground(new java.awt.Color(255, 255, 255));
        jLabel33.setText("Tên phòng:");

        jLabel34.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        jLabel34.setForeground(new java.awt.Color(255, 255, 255));
        jLabel34.setText("Mã tình trạng phòng:");

        jLabel35.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        jLabel35.setForeground(new java.awt.Color(255, 255, 255));
        jLabel35.setText("Loại phòng:");

        txtTTPHONG.setEditable(false);

        jLabel37.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        jLabel37.setForeground(new java.awt.Color(255, 255, 255));
        jLabel37.setText("Đơn giá:");

        jLabel38.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        jLabel38.setForeground(new java.awt.Color(255, 255, 255));
        jLabel38.setText("Mã phòng:");

        jLabel39.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        jLabel39.setForeground(new java.awt.Color(255, 255, 255));
        jLabel39.setText("Tình trạng phòng");

        jLabel40.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        jLabel40.setForeground(new java.awt.Color(255, 255, 255));
        jLabel40.setText("Mã loại phòng:");

        txtTLP.setEditable(false);

        cmbMATINHTRANGPHONG.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cmbMATINHTRANGPHONGItemStateChanged(evt);
            }
        });

        cmbMALOAIIPHONGa.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cmbMALOAIIPHONGaItemStateChanged(evt);
            }
        });

        btnAdd.setText("Thêm");
        btnAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddActionPerformed(evt);
            }
        });

        btnEdit.setText("Sửa");
        btnEdit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditActionPerformed(evt);
            }
        });

        btnDelete.setText("Xóa");
        btnDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteActionPerformed(evt);
            }
        });

        btnRefresh.setText("Làm mới");
        btnRefresh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRefreshActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout roundPanel2Layout = new javax.swing.GroupLayout(roundPanel2);
        roundPanel2.setLayout(roundPanel2Layout);
        roundPanel2Layout.setHorizontalGroup(
            roundPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(roundPanel2Layout.createSequentialGroup()
                .addGap(41, 41, 41)
                .addGroup(roundPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(roundPanel2Layout.createSequentialGroup()
                        .addGroup(roundPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel38)
                            .addComponent(jLabel33))
                        .addGap(97, 97, 97)
                        .addGroup(roundPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtTENPHONG, javax.swing.GroupLayout.DEFAULT_SIZE, 234, Short.MAX_VALUE)
                            .addComponent(txtMAPHONG)))
                    .addGroup(roundPanel2Layout.createSequentialGroup()
                        .addGroup(roundPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel34)
                            .addComponent(jLabel39))
                        .addGap(18, 18, 18)
                        .addGroup(roundPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtTTPHONG, javax.swing.GroupLayout.PREFERRED_SIZE, 234, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cmbMATINHTRANGPHONG, javax.swing.GroupLayout.PREFERRED_SIZE, 234, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(roundPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(roundPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(jLabel35)
                        .addComponent(jLabel37, javax.swing.GroupLayout.Alignment.LEADING))
                    .addComponent(jLabel40))
                .addGap(49, 49, 49)
                .addGroup(roundPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(cmbMALOAIIPHONGa, 0, 224, Short.MAX_VALUE)
                    .addComponent(txtTLP)
                    .addComponent(txtDONGIA))
                .addGap(98, 98, 98))
            .addGroup(roundPanel2Layout.createSequentialGroup()
                .addGap(241, 241, 241)
                .addComponent(btnAdd, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(45, 45, 45)
                .addComponent(btnEdit, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(60, 60, 60)
                .addComponent(btnDelete, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(52, 52, 52)
                .addComponent(btnRefresh, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(260, Short.MAX_VALUE))
        );
        roundPanel2Layout.setVerticalGroup(
            roundPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(roundPanel2Layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addGroup(roundPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel38)
                    .addComponent(txtMAPHONG, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel40)
                    .addComponent(cmbMALOAIIPHONGa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(roundPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(roundPanel2Layout.createSequentialGroup()
                        .addGap(26, 26, 26)
                        .addGroup(roundPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtTENPHONG, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel35)
                            .addComponent(txtTLP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, roundPanel2Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel33)))
                .addGap(26, 26, 26)
                .addGroup(roundPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cmbMATINHTRANGPHONG, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel34)
                    .addComponent(jLabel37)
                    .addComponent(txtDONGIA, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(28, 28, 28)
                .addGroup(roundPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel39)
                    .addComponent(txtTTPHONG, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 23, Short.MAX_VALUE)
                .addGroup(roundPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnDelete, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnAdd, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnEdit, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnRefresh, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        panelBorder2.setBackground(new java.awt.Color(77, 79, 45));
        panelBorder2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Tìm kiếm:", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 20), new java.awt.Color(255, 255, 255))); // NOI18N
        panelBorder2.setForeground(new java.awt.Color(255, 255, 255));

        rbMAPHONG.setBackground(new java.awt.Color(77, 79, 45));
        buttonGroup1.add(rbMAPHONG);
        rbMAPHONG.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        rbMAPHONG.setForeground(new java.awt.Color(255, 255, 255));
        rbMAPHONG.setText("Mã phòng:");

        rbDONGIA.setBackground(new java.awt.Color(77, 79, 45));
        buttonGroup1.add(rbDONGIA);
        rbDONGIA.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        rbDONGIA.setForeground(new java.awt.Color(255, 255, 255));
        rbDONGIA.setText("Đơn giá:");

        jLabel43.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        jLabel43.setForeground(new java.awt.Color(255, 255, 255));
        jLabel43.setText("Từ giá:");

        jLabel44.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        jLabel44.setForeground(new java.awt.Color(255, 255, 255));
        jLabel44.setText("Đến giá:");

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
                .addGroup(panelBorder2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelBorder2Layout.createSequentialGroup()
                        .addComponent(rbMAPHONG)
                        .addGap(28, 28, 28)
                        .addComponent(txtsearchmaphong, javax.swing.GroupLayout.PREFERRED_SIZE, 234, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panelBorder2Layout.createSequentialGroup()
                        .addComponent(rbDONGIA)
                        .addGap(36, 36, 36)
                        .addGroup(panelBorder2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel43)
                            .addGroup(panelBorder2Layout.createSequentialGroup()
                                .addComponent(jLabel44)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(panelBorder2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(txttugia, javax.swing.GroupLayout.DEFAULT_SIZE, 166, Short.MAX_VALUE)
                                    .addComponent(txtdengia))))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 93, Short.MAX_VALUE)
                .addGroup(panelBorder2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnView, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(90, 90, 90))
        );
        panelBorder2Layout.setVerticalGroup(
            panelBorder2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBorder2Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(panelBorder2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(rbMAPHONG)
                    .addComponent(txtsearchmaphong, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(panelBorder2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(panelBorder2Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 10, Short.MAX_VALUE)
                        .addGroup(panelBorder2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txttugia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel43))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(panelBorder2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel44, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(txtdengia, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(25, 25, 25))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, panelBorder2Layout.createSequentialGroup()
                        .addGroup(panelBorder2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, panelBorder2Layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addComponent(rbDONGIA))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, panelBorder2Layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addComponent(btnView, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );

        tbPHONG.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        tbPHONG.setModel(new javax.swing.table.DefaultTableModel(
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
        tbPHONG.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbPHONGMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(tbPHONG);

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel8.setText("Thông tin phòng:");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(roundPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 1082, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(panelBorder2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 183, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel8))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(roundPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(panelBorder2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 48, Short.MAX_VALUE)
                .addComponent(jLabel8)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 12, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 192, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void cmbMATINHTRANGPHONGItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cmbMATINHTRANGPHONGItemStateChanged
        // TODO add your handling code here:
        String MaTinhTrangPhong = cmbMATINHTRANGPHONG.getSelectedItem().toString();
        try {
            ModelRoomTT ql = new ModelRoomTT();

            model.ModelRoomTTv2 ttp = ql.findByID(MaTinhTrangPhong);
            if (ttp != null) {
                txtTTPHONG.setText(ttp.getTinhtrangphong());

            } else {
                JOptionPane.showMessageDialog(this, "Không tim thấy mã tình trạng phòng");
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "error " + e.getMessage());
            e.printStackTrace();
        }
    }//GEN-LAST:event_cmbMATINHTRANGPHONGItemStateChanged

    private void cmbMALOAIIPHONGaItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cmbMALOAIIPHONGaItemStateChanged
        // TODO add your handling code here:
        String MaLoaiPHong = cmbMALOAIIPHONGa.getSelectedItem().toString();
        try {
            ModelRoomLP ql = new ModelRoomLP();

            model.ModelRoomLPv2 lp = ql.findByID(MaLoaiPHong);
            if (lp != null) {
                txtTLP.setText(lp.getTenLoaiPhong());

            } else {
                JOptionPane.showMessageDialog(this, "Không tim thấy mã loại phòng");
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "error " + e.getMessage());
            e.printStackTrace();
        }
    }//GEN-LAST:event_cmbMALOAIIPHONGaItemStateChanged

    private void btnAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddActionPerformed
        // TODO add your handling code here:
        StringBuilder sb = new StringBuilder();
        if (txtMAPHONG.getText().equals("")) {
            sb.append("mã phòng không được để trống");
            txtMAPHONG.setBackground(Color.red);

        } else {
            txtMAPHONG.setBackground(Color.white);
        }
        if (sb.length() > 0) {
            JOptionPane.showMessageDialog(this, sb);
        }
        if (JOptionPane.showConfirmDialog(this, "bạn có muốn thêm phòng không??") == JOptionPane.NO_OPTION) {
            return;
        }
        try {
           

            double tienn = Double.parseDouble(txtDONGIA.getText());
            String matinhtrangphong = cmbMATINHTRANGPHONG.getSelectedItem().toString();
            String maloaiphong = cmbMALOAIIPHONGa.getSelectedItem().toString();

            ModelPhongv2 p = new ModelPhongv2();
            p.setMaPhong(txtMAPHONG.getText());
            p.setTenPhong(txtTENPHONG.getText());
            p.setMaTinhTrangPhong(matinhtrangphong);
            p.setMaLoaiPhong(maloaiphong);
           
            p.setTien(tienn);

            ModelPhong ql = new ModelPhong();
            ql.insert(p);

            JOptionPane.showMessageDialog(this, "Lưu thành công!!! Vui lòng đăng nhập lại để cập nhật dữ liệu mới nhất");
            loaddulieu1();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "error " + e.getMessage());
            e.printStackTrace();
        }
    }//GEN-LAST:event_btnAddActionPerformed

    private void btnEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditActionPerformed
        // TODO add your handling code here:
        StringBuilder sb = new StringBuilder();
        if (txtMAPHONG.getText().equals("")) {
            sb.append("mã phòng không được để trống");
            txtMAPHONG.setBackground(Color.red);

        } else {
            txtMAPHONG.setBackground(Color.white);
        }
        if (sb.length() > 0) {
            JOptionPane.showMessageDialog(this, sb);
        }
        if (JOptionPane.showConfirmDialog(this, "bạn có muốn sửa phòng không??") == JOptionPane.NO_OPTION) {
            return;
        }
        try {

           

            double tienn = Double.parseDouble(txtDONGIA.getText());
            String matinhtrangphong = cmbMATINHTRANGPHONG.getSelectedItem().toString();
            String maloaiphong = cmbMALOAIIPHONGa.getSelectedItem().toString();

            ModelPhongv2 p = new ModelPhongv2();
            p.setMaPhong(txtMAPHONG.getText());
            p.setTenPhong(txtTENPHONG.getText());
            p.setMaTinhTrangPhong(matinhtrangphong);
            p.setMaLoaiPhong(maloaiphong);
           
            p.setTien(tienn);

            ModelPhong ql = new ModelPhong();
            ql.update(p);

            JOptionPane.showMessageDialog(this, "Sửa thành công!!! Vui lòng đăng nhập lại để cập nhật dữ liệu mới nhất");
            loaddulieu1();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "error " + e.getMessage());
            e.printStackTrace();
        }
    }//GEN-LAST:event_btnEditActionPerformed
   
    private void btnDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteActionPerformed
        // TODO add your handling code here:
        StringBuilder sb = new StringBuilder();
        if (txtMAPHONG.getText().equals("")) {
            sb.append("mã phòng không được để trống");
            txtMAPHONG.setBackground(Color.red);

        } else {
            txtMAPHONG.setBackground(Color.white);
        }
        if (sb.length() > 0) {
            JOptionPane.showMessageDialog(this, sb);
        }
        if (JOptionPane.showConfirmDialog(this, "bạn có muốn xóa phòng không??") == JOptionPane.NO_OPTION) {
            return;
        }
        try {
            

            ModelPhongv2 qll = new ModelPhongv2();
            qll.setMaPhong(txtMAPHONG.getText());
            
            ModelPhong ql = new ModelPhong();
            ql.deletecomeroot(qll);

            JOptionPane.showMessageDialog(this, "Xóa thành công!!! Vui lòng đăng nhập lại để cập nhật dữ liệu mới nhất");
            loaddulieu1();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "error " + e.getMessage());
            e.printStackTrace();
        }
    }//GEN-LAST:event_btnDeleteActionPerformed

    private void btnRefreshActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRefreshActionPerformed
        // TODO add your handling code here:

    }//GEN-LAST:event_btnRefreshActionPerformed

    private void btnSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSearchActionPerformed
        // TODO add your handling code here:
       if (rbMAPHONG.isSelected() == false && rbDONGIA.isSelected() == false) {
            JOptionPane.showMessageDialog(this, "bạn hãy chọn tiêu chí tìm kiếm");
        } else if (rbMAPHONG.isSelected() == true) {

            try {

                String sql = "select MaPhong,TenPhong, TinhTrangPhong.MaTinhtrangphong, TinhTrangPhong, LoaiPhong.MaLoaiPhong, TenLoaiPhong, Tien from phong "
                + "join LOAIPHONG on PHONG.MaLoaiPhong = LOAIPHONG.MaLoaiPhong join TINHTRANGPHONG on TinhTrangPhong.MaTinhTrangPhong = PHONG.MaTinhtrangphong where maphong =? and isvisible = '1' ";
                conn = cn.getConnection();
                PreparedStatement pstmt = conn.prepareStatement(sql);

                pstmt.setString(1, txtsearchmaphong.getText());
                ResultSet rs = pstmt.executeQuery();
                tbmodel.setRowCount(0);
                while (rs.next()) {
                    tbmodel.addRow(new Object[]{
                        rs.getString("maphong"), rs.getString("tenphong"), rs.getString("matinhtrangphong"), rs.getString("tinhtrangphong"), rs.getString("maloaiphong"), rs.getString("tenloaiphong"),  rs.getDouble("tien")

                    });
                }
                tbmodel.fireTableDataChanged();
                //loadthongtintaikhoan();

            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, e.getMessage());
                e.printStackTrace();
            }

        } else if (rbDONGIA.isSelected() == true) {
            try {

                String sql = "select MaPhong,TenPhong, TinhTrangPhong.MaTinhtrangphong, TinhTrangPhong, LoaiPhong.MaLoaiPhong, TenLoaiPhong, Tien from phong "
                + "join LOAIPHONG on PHONG.MaLoaiPhong = LOAIPHONG.MaLoaiPhong join TINHTRANGPHONG on TinhTrangPhong.MaTinhTrangPhong = PHONG.MaTinhtrangphong where isvisible = '1' and tien between ? and ? ";
                conn = cn.getConnection();
                PreparedStatement pstmt = conn.prepareStatement(sql);

                pstmt.setString(1, txttugia.getText());
                pstmt.setString(2, txtdengia.getText());
                ResultSet rs = pstmt.executeQuery();
                tbmodel.setRowCount(0);
                while (rs.next()) {
                    tbmodel.addRow(new Object[]{
                        rs.getString("maphong"), rs.getString("tenphong"), rs.getString("matinhtrangphong"), rs.getString("tinhtrangphong"), rs.getString("maloaiphong"), rs.getString("tenloaiphong"), rs.getInt("sogiuong"), rs.getDouble("tien")

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
        loaddulieu1();
    }//GEN-LAST:event_btnViewActionPerformed

    private void tbPHONGMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbPHONGMouseClicked
        // TODO add your handling code here:
        int row = tbPHONG.getSelectedRow();

        if (row >= 0) {
            txtMAPHONG.setText(tbPHONG.getValueAt(row, 0).toString());
            txtTENPHONG.setText(tbPHONG.getValueAt(row, 1).toString());
            //txtMATTPHONG.setText(tbPHONG.getValueAt(row, 2).toString());
            txtTTPHONG.setText(tbPHONG.getValueAt(row, 3).toString());
            //txtMALOAIPHONG.setText(tbPHONG.getValueAt(row, 4).toString());
            txtTLP.setText(tbPHONG.getValueAt(row, 5).toString());
//            txtDONGIA.setText(tbPHONG.getValueAt(row, 6).toString());

            String mattphong = tbPHONG.getValueAt(row, 2).toString();
            System.out.println("" + mattphong);
            cmbMATINHTRANGPHONG.setSelectedItem(mattphong);

            String maLOAIPHONG = tbPHONG.getValueAt(row, 4).toString();
            System.out.println("" + maLOAIPHONG);
            cmbMALOAIIPHONGa.setSelectedItem(maLOAIPHONG);
            
            
            ModelRoomv2 ms =new ModelRoomv2();
            BigDecimal x = (BigDecimal) tbPHONG.getValueAt(row, 6);

            ms.setTien(x);

            String formattedDonGia = ms.getFormattedDonGia();
            System.out.println(formattedDonGia);
            txtDONGIA.setText(formattedDonGia);
            
            

        }
    }//GEN-LAST:event_tbPHONGMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private swing.Button btnAdd;
    private swing.Button btnDelete;
    private swing.Button btnEdit;
    private swing.Button btnRefresh;
    private swing.Button btnSearch;
    private swing.Button btnView;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JComboBox<String> cmbMALOAIIPHONGa;
    private javax.swing.JComboBox<String> cmbMATINHTRANGPHONG;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabel39;
    private javax.swing.JLabel jLabel40;
    private javax.swing.JLabel jLabel43;
    private javax.swing.JLabel jLabel44;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JSeparator jSeparator1;
    private swing.PanelBorder panelBorder2;
    private javax.swing.JRadioButton rbDONGIA;
    private javax.swing.JRadioButton rbMAPHONG;
    private swing.RoundPanel roundPanel2;
    private javax.swing.JTable tbPHONG;
    private javax.swing.JTextField txtDONGIA;
    private javax.swing.JTextField txtMAPHONG;
    private javax.swing.JTextField txtTENPHONG;
    private javax.swing.JTextField txtTLP;
    private javax.swing.JTextField txtTTPHONG;
    private javax.swing.JTextField txtdengia;
    private javax.swing.JTextField txtsearchmaphong;
    private javax.swing.JTextField txttugia;
    // End of variables declaration//GEN-END:variables
}
