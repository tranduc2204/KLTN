/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package form;

import connect.Connect;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import model.ModelPhong;
import model.ModelPhongv2;
import model.ModelSuppliesforRoom;
import model.ModelSuppliesforRoomv2;
import model.ModelRoom;
import model.ModelRoomLP;
import model.ModelRoomv2;
import model.ModelSupplies;

/**
 *
 * @author TeeDee
 */
public class FormQLSuppliesForRoom extends javax.swing.JPanel {

    /**
     * Creates new form FormQLSuppliesForRoom
     */
    public FormQLSuppliesForRoom() {
        initComponents();
    }
    
    Connect cn = new Connect();
    Connection conn;
    DefaultTableModel tbmodel;

    private String username, password, quyen, DisplayName;
    
    public FormQLSuppliesForRoom(String username, String password, String DisplayName, String quyen) {
        initComponents();
        this.username = username;
        this.password = password;
        this.DisplayName = DisplayName;
        this.quyen = quyen;
        
        initCombobox_mavt();
        initCombobox_malp();
        inittable();
        loaddulieu1();
        
    }
    
    private void inittable() {
        tbmodel = new DefaultTableModel();
        tbmodel.setColumnIdentifiers(new String[]{"Mã vật tư", "Loại vật tư", "Mã loại phòng", "Loại phòng", "Số lượng"});
        tbSupplies.setModel(tbmodel);
    }

    public void loaddulieu1() {
        try {
            ModelSuppliesforRoom ql = new ModelSuppliesforRoom();
            ArrayList<ModelSuppliesforRoomv2> list = ql.findALL();
            tbmodel.setRowCount(0);
            for (ModelSuppliesforRoomv2 p : list) {
                tbmodel.addRow(new Object[]{
                    p.getMaVatTu(), p.getTenVatTu(), p.getMaLoaiPhong(), p.getTenLoaiPhong(), p.getSoLuong()
                });
            }
            tbmodel.fireTableDataChanged();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "error " + e.getMessage());
            e.printStackTrace();
        }

    }
    
    private void initCombobox_mavt() {
        try {
            conn = cn.getConnection();
            String sql = "Select MaVatTu from VATTU where isvisible = '1' ";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();
            cmbMavattu.removeAllItems();
            cmbSerchMavattu.removeAllItems();
            while (rs.next()) {
                cmbMavattu.addItem(rs.getString("MaVatTu"));
                cmbSerchMavattu.addItem(rs.getString("MaVatTu"));
            }
            rs.close();
            pstmt.close();
            conn.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
            e.printStackTrace();
        }
    }
    
    private void initCombobox_malp() {
        try {
            conn = cn.getConnection();
            String sql = "Select MaLoaiPhong from LOAIPHONG";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();
            cmbMaloaiphong.removeAllItems();
            cmbSearchLP.removeAllItems();
            while (rs.next()) {
                cmbMaloaiphong.addItem(rs.getString("MaLoaiPhong"));
                cmbSearchLP.addItem(rs.getString("MaLoaiPhong"));
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
        jLabel22 = new javax.swing.JLabel();
        btnAdd = new swing.Button();
        btnEdit = new swing.Button();
        bthDelete = new swing.Button();
        btnAdd3 = new swing.Button();
        cmbMavattu = new javax.swing.JComboBox<>();
        cmbMaloaiphong = new javax.swing.JComboBox<>();
        txtLoaiVatTu = new component.TextField();
        txtSL = new component.TextField();
        jLabel23 = new javax.swing.JLabel();
        txtSL1 = new component.TextField();
        txtLoaiPhong = new component.TextField();
        jScrollPane3 = new javax.swing.JScrollPane();
        tbSupplies = new javax.swing.JTable();
        panelBorder2 = new swing.PanelBorder();
        rbmavt = new javax.swing.JRadioButton();
        rbmalp = new javax.swing.JRadioButton();
        btnSearch = new swing.Button();
        btnView = new swing.Button();
        rbsl = new javax.swing.JRadioButton();
        txtSearchSL = new javax.swing.JTextField();
        cmbSerchMavattu = new javax.swing.JComboBox<>();
        cmbSearchLP = new javax.swing.JComboBox<>();
        txtSearchLoaiVatTu = new javax.swing.JTextField();
        txtSearchLoaiPhong = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();

        roundPanel2.setBackground(new java.awt.Color(36, 87, 157));
        roundPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Thông tin vật tư", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 24), new java.awt.Color(255, 255, 255))); // NOI18N
        roundPanel2.setForeground(new java.awt.Color(255, 255, 255));

        jLabel22.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        jLabel22.setForeground(new java.awt.Color(255, 255, 255));
        jLabel22.setText("Mã vật tư:");

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

        bthDelete.setText("Xóa");
        bthDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bthDeleteActionPerformed(evt);
            }
        });

        btnAdd3.setText("Làm mới");
        btnAdd3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAdd3ActionPerformed(evt);
            }
        });

        cmbMavattu.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cmbMavattuItemStateChanged(evt);
            }
        });

        cmbMaloaiphong.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cmbMaloaiphongItemStateChanged(evt);
            }
        });

        txtLoaiVatTu.setLabelText("Loại vật tư");

        txtSL.setLabelText("Số lượng");

        jLabel23.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        jLabel23.setForeground(new java.awt.Color(255, 255, 255));
        jLabel23.setText("Mã loại phòng:");

        txtSL1.setLabelText("Số lượng");

        txtLoaiPhong.setLabelText("Loại phòng");

        javax.swing.GroupLayout roundPanel2Layout = new javax.swing.GroupLayout(roundPanel2);
        roundPanel2.setLayout(roundPanel2Layout);
        roundPanel2Layout.setHorizontalGroup(
            roundPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(roundPanel2Layout.createSequentialGroup()
                .addGap(290, 290, 290)
                .addComponent(btnAdd, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnEdit, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(bthDelete, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnAdd3, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(roundPanel2Layout.createSequentialGroup()
                .addGap(75, 75, 75)
                .addGroup(roundPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(roundPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(txtSL, javax.swing.GroupLayout.PREFERRED_SIZE, 415, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(roundPanel2Layout.createSequentialGroup()
                            .addComponent(jLabel22)
                            .addGap(52, 52, 52)
                            .addComponent(cmbMavattu, javax.swing.GroupLayout.PREFERRED_SIZE, 279, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(txtLoaiVatTu, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 415, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(roundPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(roundPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addGroup(roundPanel2Layout.createSequentialGroup()
                            .addComponent(jLabel23)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(cmbMaloaiphong, javax.swing.GroupLayout.PREFERRED_SIZE, 264, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addComponent(txtSL1, javax.swing.GroupLayout.PREFERRED_SIZE, 415, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(txtLoaiPhong, javax.swing.GroupLayout.PREFERRED_SIZE, 415, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(66, 66, 66))
        );
        roundPanel2Layout.setVerticalGroup(
            roundPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(roundPanel2Layout.createSequentialGroup()
                .addGroup(roundPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(roundPanel2Layout.createSequentialGroup()
                        .addGap(42, 42, 42)
                        .addComponent(jLabel23))
                    .addGroup(roundPanel2Layout.createSequentialGroup()
                        .addGap(29, 29, 29)
                        .addGroup(roundPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel22)
                            .addComponent(cmbMavattu, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, roundPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(cmbMaloaiphong, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(roundPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtLoaiVatTu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtSL1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, Short.MAX_VALUE)
                .addGroup(roundPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtSL, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtLoaiPhong, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(roundPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnAdd, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnEdit, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(bthDelete, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnAdd3, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(27, 27, 27))
        );

        tbSupplies.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        tbSupplies.setModel(new javax.swing.table.DefaultTableModel(
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
        tbSupplies.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbSuppliesMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(tbSupplies);

        panelBorder2.setBackground(new java.awt.Color(36, 87, 157));
        panelBorder2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Tìm kiếm:", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 20), new java.awt.Color(255, 255, 255))); // NOI18N
        panelBorder2.setForeground(new java.awt.Color(255, 255, 255));

        rbmavt.setBackground(new java.awt.Color(36, 87, 157));
        buttonGroup1.add(rbmavt);
        rbmavt.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        rbmavt.setForeground(new java.awt.Color(255, 255, 255));
        rbmavt.setText("Mã vật tư:");

        rbmalp.setBackground(new java.awt.Color(36, 87, 157));
        buttonGroup1.add(rbmalp);
        rbmalp.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        rbmalp.setForeground(new java.awt.Color(255, 255, 255));
        rbmalp.setText("Mã loại phòng:");

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

        rbsl.setBackground(new java.awt.Color(36, 87, 157));
        buttonGroup1.add(rbsl);
        rbsl.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        rbsl.setForeground(new java.awt.Color(255, 255, 255));
        rbsl.setText("Số lượng:");

        cmbSerchMavattu.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cmbSerchMavattuItemStateChanged(evt);
            }
        });

        cmbSearchLP.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cmbSearchLPItemStateChanged(evt);
            }
        });

        txtSearchLoaiVatTu.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        txtSearchLoaiPhong.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        javax.swing.GroupLayout panelBorder2Layout = new javax.swing.GroupLayout(panelBorder2);
        panelBorder2.setLayout(panelBorder2Layout);
        panelBorder2Layout.setHorizontalGroup(
            panelBorder2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBorder2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelBorder2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(rbmavt)
                    .addComponent(rbmalp)
                    .addComponent(rbsl))
                .addGap(60, 60, 60)
                .addGroup(panelBorder2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtSearchSL)
                    .addComponent(cmbSearchLP, 0, 215, Short.MAX_VALUE)
                    .addComponent(cmbSerchMavattu, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(panelBorder2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtSearchLoaiVatTu, javax.swing.GroupLayout.DEFAULT_SIZE, 144, Short.MAX_VALUE)
                    .addComponent(txtSearchLoaiPhong))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 28, Short.MAX_VALUE)
                .addGroup(panelBorder2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnView, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(46, 46, 46))
        );
        panelBorder2Layout.setVerticalGroup(
            panelBorder2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBorder2Layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addGroup(panelBorder2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(rbmavt)
                    .addComponent(cmbSerchMavattu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtSearchLoaiVatTu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(panelBorder2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(rbmalp)
                    .addComponent(cmbSearchLP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtSearchLoaiPhong, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(panelBorder2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(rbsl)
                    .addComponent(txtSearchSL, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(11, Short.MAX_VALUE))
            .addGroup(panelBorder2Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(btnSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnView, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26))
        );

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel8.setText("Thông tin vật tư:");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(roundPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 1087, Short.MAX_VALUE)
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
                .addComponent(roundPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(panelBorder2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(32, 32, 32)
                .addComponent(jLabel8)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 12, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(1, 1, 1)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddActionPerformed
        // TODO add your handling code here:

        try {
            int sl = Integer.parseInt(txtSL.getText());
            String mavt = cmbMavattu.getSelectedItem().toString();
            String malp = cmbMaloaiphong.getSelectedItem().toString();

            ModelSuppliesforRoomv2 p = new ModelSuppliesforRoomv2();
            p.setMaVatTu(mavt);
            p.setMaLoaiPhong(malp);
            p.setSoLuong(sl);
           

            ModelSuppliesforRoom ql = new ModelSuppliesforRoom();
            ql.insert(p);

            JOptionPane.showMessageDialog(this, "Lưu thành công!!! Vui lòng đăng nhập lại để cập nhật dữ liệu mới nhất");
            loaddulieu1();
        } catch (Exception e) {
            int sl = Integer.parseInt(txtSL.getText());
            String mavt = cmbMavattu.getSelectedItem().toString();
            String malp = cmbMaloaiphong.getSelectedItem().toString();

            ModelSuppliesforRoomv2 pa = new ModelSuppliesforRoomv2();
            pa.setMaVatTu(mavt);
            pa.setMaLoaiPhong(malp);
            pa.setSoLuong(sl);

            ModelSuppliesforRoom ql = new ModelSuppliesforRoom();
            try {
                ql.update(pa);
            } catch (Exception ex) {
                Logger.getLogger(FormQLSuppliesForRoom.class.getName()).log(Level.SEVERE, null, ex);
            }

            JOptionPane.showMessageDialog(this, "Sửa thành công!!! ");
            loaddulieu1();
        }
    }//GEN-LAST:event_btnAddActionPerformed

    private void btnEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditActionPerformed
        // TODO add your handling code here:

        
        try {

            int sl = Integer.parseInt(txtSL.getText());
            String mavt = cmbMavattu.getSelectedItem().toString();
            String malp = cmbMaloaiphong.getSelectedItem().toString();

            ModelSuppliesforRoomv2 p = new ModelSuppliesforRoomv2();
            p.setMaVatTu(mavt);
            p.setMaLoaiPhong(malp);
            p.setSoLuong(sl);

           ModelSuppliesforRoom ql = new ModelSuppliesforRoom();
            ql.update(p);

            JOptionPane.showMessageDialog(this, "Sửa thành công!!! ");
            loaddulieu1();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "error " + e.getMessage());
            e.printStackTrace();
        }
    }//GEN-LAST:event_btnEditActionPerformed

    private void bthDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bthDeleteActionPerformed
        // TODO add your handling code here:
        try {

            String mavt = cmbMavattu.getSelectedItem().toString();
            String malp = cmbMaloaiphong.getSelectedItem().toString();
            
            
            
            ModelSuppliesforRoomv2 qll = new ModelSuppliesforRoomv2();
            qll.setMaVatTu(mavt);
            qll.setMaLoaiPhong(malp);
            
            ModelSuppliesforRoom ql = new ModelSuppliesforRoom();
            ql.deletecomeroot(qll);

            JOptionPane.showMessageDialog(this, "Xóa thành công!!! ");
            loaddulieu1();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "error " + e.getMessage());
            e.printStackTrace();
        }
    }//GEN-LAST:event_bthDeleteActionPerformed

    private void btnAdd3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAdd3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnAdd3ActionPerformed

    private void cmbMavattuItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cmbMavattuItemStateChanged
        // TODO add your handling code here:
        String MaVatTu = cmbMavattu.getSelectedItem().toString();
        try {
            ModelSupplies ql = new ModelSupplies();

            model.ModelSuppliesv2 ttp = ql.findByID(MaVatTu);
            if (ttp != null) {
                txtLoaiVatTu.setText(ttp.getTenVatTu());

            } else {
                JOptionPane.showMessageDialog(this, "Không tim thấy mã vật tư");
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "error " + e.getMessage());
            e.printStackTrace();
        }
    }//GEN-LAST:event_cmbMavattuItemStateChanged

    private void cmbMaloaiphongItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cmbMaloaiphongItemStateChanged
        // TODO add your handling code here:
        String MaVatTu = cmbMaloaiphong.getSelectedItem().toString();
        try {
            ModelRoomLP ql = new ModelRoomLP();

            model.ModelRoomLPv2 ttp = ql.findByID(MaVatTu);
            if (ttp != null) {
                txtLoaiPhong.setText(ttp.getTenLoaiPhong());

            } else {
                JOptionPane.showMessageDialog(this, "Không tim thấy mã loại phòng");
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "error " + e.getMessage());
            e.printStackTrace();
        }
    }//GEN-LAST:event_cmbMaloaiphongItemStateChanged

    private void tbSuppliesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbSuppliesMouseClicked
        // TODO add your handling code here:
       int row = tbSupplies.getSelectedRow();

        if (row >= 0) {
            
            String mavt = tbSupplies.getValueAt(row, 0).toString();
            System.out.println("" + mavt);
            cmbMavattu.setSelectedItem(mavt);
            
            txtLoaiVatTu.setText(tbSupplies.getValueAt(row, 1).toString());
            
            String malp = tbSupplies.getValueAt(row, 2).toString();
            System.out.println("" + malp);
            cmbMaloaiphong.setSelectedItem(malp);
            
            txtLoaiPhong.setText(tbSupplies.getValueAt(row, 3).toString());
            
            txtSL.setText(tbSupplies.getValueAt(row, 4).toString());
            

        }
    }//GEN-LAST:event_tbSuppliesMouseClicked

    private void btnSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSearchActionPerformed
        // TODO add your handling code here:
        String mavt = cmbSerchMavattu.getSelectedItem().toString();
        String malp = cmbSearchLP.getSelectedItem().toString();
        
        if (rbmavt.isSelected() == false && rbmalp.isSelected() == false && rbsl.isSelected() == false) {
            JOptionPane.showMessageDialog(this, "bạn hãy chọn tiêu chí tìm kiếm: ");
        }
        if (rbmavt.isSelected() == true) {

            try {


                String sql = "select vt.MaVatTu, vt.TenVatTu, lp.MaLoaiPhong, lp.TenLoaiPhong, ctvt.SoLuong "
                        + "from CTVatTu ctvt join VATTU vt on vt.MaVatTu = ctvt.MaVatTu "
                        + "join LOAIPHONG lp on lp.MaLoaiPhong =ctvt.MaLoaiPhong where vt.MaVatTu = ?";
                conn = cn.getConnection();
                PreparedStatement pstmt = conn.prepareStatement(sql);

                pstmt.setString(1, mavt);
                ResultSet rs = pstmt.executeQuery();
                tbmodel.setRowCount(0);
                while (rs.next()) {
                    tbmodel.addRow(new Object[]{
                        rs.getString("MaVatTu"), rs.getString("TenVatTu"), rs.getString("MaLoaiPhong"), rs.getString("TenLoaiPhong"), rs.getString("SoLuong")
                    });
                }
                tbmodel.fireTableDataChanged();
                //loadthongtintaikhoan();

            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, e.getMessage());
                e.printStackTrace();
            }
        } else if (rbmalp.isSelected() == true) {

             try {


                String sql = "select vt.MaVatTu, vt.TenVatTu, lp.MaLoaiPhong, lp.TenLoaiPhong, ctvt.SoLuong "
                        + "from CTVatTu ctvt join VATTU vt on vt.MaVatTu = ctvt.MaVatTu "
                        + "join LOAIPHONG lp on lp.MaLoaiPhong =ctvt.MaLoaiPhong where lp.MaLoaiPhong = ?";
                conn = cn.getConnection();
                PreparedStatement pstmt = conn.prepareStatement(sql);

                pstmt.setString(1, malp);
                ResultSet rs = pstmt.executeQuery();
                tbmodel.setRowCount(0);
                while (rs.next()) {
                    tbmodel.addRow(new Object[]{
                        rs.getString("MaVatTu"), rs.getString("TenVatTu"), rs.getString("MaLoaiPhong"), rs.getString("TenLoaiPhong"), rs.getString("SoLuong")
                    });
                }
                tbmodel.fireTableDataChanged();
                //loadthongtintaikhoan();

            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, e.getMessage());
                e.printStackTrace();
            }

        }else if (rbsl.isSelected() == true) {

             try {


                String sql = "select vt.MaVatTu, vt.TenVatTu, lp.MaLoaiPhong, lp.TenLoaiPhong, ctvt.SoLuong "
                        + "from CTVatTu ctvt join VATTU vt on vt.MaVatTu = ctvt.MaVatTu "
                        + "join LOAIPHONG lp on lp.MaLoaiPhong =ctvt.MaLoaiPhong where ctvt.SoLuong like ?";
                conn = cn.getConnection();
                PreparedStatement pstmt = conn.prepareStatement(sql);

                pstmt.setString(1, txtSearchSL.getText());
                ResultSet rs = pstmt.executeQuery();
                tbmodel.setRowCount(0);
                while (rs.next()) {
                    tbmodel.addRow(new Object[]{
                        rs.getString("MaVatTu"), rs.getString("TenVatTu"), rs.getString("MaLoaiPhong"), rs.getString("TenLoaiPhong"), rs.getString("SoLuong")
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

    private void cmbSerchMavattuItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cmbSerchMavattuItemStateChanged
        // TODO add your handling code here:
        String MaVatTu = cmbSerchMavattu.getSelectedItem().toString();
        try {
            ModelSupplies ql = new ModelSupplies();

            model.ModelSuppliesv2 ttp = ql.findByID(MaVatTu);
            if (ttp != null) {
                txtSearchLoaiVatTu.setText(ttp.getTenVatTu());

            } else {
                JOptionPane.showMessageDialog(this, "Không tim thấy mã vật tư");
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "error " + e.getMessage());
            e.printStackTrace();
        }
    }//GEN-LAST:event_cmbSerchMavattuItemStateChanged

    private void cmbSearchLPItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cmbSearchLPItemStateChanged
        // TODO add your handling code here:
        String MaVatTu = cmbSearchLP.getSelectedItem().toString();
        try {
            ModelRoomLP ql = new ModelRoomLP();

            model.ModelRoomLPv2 ttp = ql.findByID(MaVatTu);
            if (ttp != null) {
                txtSearchLoaiPhong.setText(ttp.getTenLoaiPhong());

            } else {
                JOptionPane.showMessageDialog(this, "Không tim thấy mã loại phòng");
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "error " + e.getMessage());
            e.printStackTrace();
        }
    }//GEN-LAST:event_cmbSearchLPItemStateChanged


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private swing.Button bthDelete;
    private swing.Button btnAdd;
    private swing.Button btnAdd3;
    private swing.Button btnEdit;
    private swing.Button btnSearch;
    private swing.Button btnView;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JComboBox<String> cmbMaloaiphong;
    private javax.swing.JComboBox<String> cmbMavattu;
    private javax.swing.JComboBox<String> cmbSearchLP;
    private javax.swing.JComboBox<String> cmbSerchMavattu;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JSeparator jSeparator1;
    private swing.PanelBorder panelBorder2;
    private javax.swing.JRadioButton rbmalp;
    private javax.swing.JRadioButton rbmavt;
    private javax.swing.JRadioButton rbsl;
    private swing.RoundPanel roundPanel2;
    private javax.swing.JTable tbSupplies;
    private component.TextField txtLoaiPhong;
    private component.TextField txtLoaiVatTu;
    private component.TextField txtSL;
    private component.TextField txtSL1;
    private javax.swing.JTextField txtSearchLoaiPhong;
    private javax.swing.JTextField txtSearchLoaiVatTu;
    private javax.swing.JTextField txtSearchSL;
    // End of variables declaration//GEN-END:variables
}
