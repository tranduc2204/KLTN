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
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import model.ModelAccount;
import model.ModelAccountv2;
import model.ModelSupplies;
import model.ModelSuppliesv2;

/**
 *
 * @author TeeDee
 */
public class FormQLSupplies extends javax.swing.JPanel {

    /**
     * Creates new form FormQLSupplies
     */
    Connect cn = new Connect();
    Connection conn;
    private String username, password, quyen, DisplayName;
    public FormQLSupplies() {
        initComponents();
    }
    DefaultTableModel tbmodel;
    public FormQLSupplies(String username, String password, String DisplayName, String quyen) {
        initComponents();
        this.username = username;
        this.password = password;
        this.DisplayName = DisplayName;
        this.quyen = quyen;
        
        inittable_Supplies();
        loadthongtinSupplies();
    }
    
    private void inittable_Supplies() {
        tbmodel = new DefaultTableModel();
        tbmodel.setColumnIdentifiers(new String[]{"Mã vật tư", "Tên vật tư"});
        tbSupplies.setModel(tbmodel);
    }
    
    public void loadthongtinSupplies() {
        try {
            ModelSupplies ql = new ModelSupplies();
            ArrayList<ModelSuppliesv2> list = ql.findALL();
            tbmodel.setRowCount(0);
            for (ModelSuppliesv2 acc : list) {
                tbmodel.addRow(new Object[]{
                    acc.getMaVatTu(), acc.getTenVatTu()
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

        buttonGroup1 = new javax.swing.ButtonGroup();
        roundPanel2 = new swing.RoundPanel();
        btnAdd = new swing.Button();
        btnEdit = new swing.Button();
        bthDelete = new swing.Button();
        btnAdd3 = new swing.Button();
        txtMAVatTu = new component.TextField();
        txtLoaiVatTu = new component.TextField();
        panelBorder1 = new swing.PanelBorder();
        v1 = new javax.swing.JRadioButton();
        txtSearchMa = new javax.swing.JTextField();
        rbtTENTAIKHOAN1 = new javax.swing.JRadioButton();
        txtSEARCHTen = new javax.swing.JTextField();
        btnView = new swing.Button();
        btnSearch = new swing.Button();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbSupplies = new javax.swing.JTable();
        jLabel8 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();

        roundPanel2.setBackground(new java.awt.Color(36, 87, 157));
        roundPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Thông tin vật tư", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 24), new java.awt.Color(255, 255, 255))); // NOI18N
        roundPanel2.setForeground(new java.awt.Color(255, 255, 255));

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

        txtMAVatTu.setLabelText("Mã vật tư");

        txtLoaiVatTu.setLabelText("Loại vật tư");

        javax.swing.GroupLayout roundPanel2Layout = new javax.swing.GroupLayout(roundPanel2);
        roundPanel2.setLayout(roundPanel2Layout);
        roundPanel2Layout.setHorizontalGroup(
            roundPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(roundPanel2Layout.createSequentialGroup()
                .addGroup(roundPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(roundPanel2Layout.createSequentialGroup()
                        .addGap(321, 321, 321)
                        .addComponent(btnAdd, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnEdit, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 31, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, roundPanel2Layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(txtMAVatTu, javax.swing.GroupLayout.PREFERRED_SIZE, 415, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(51, 51, 51)))
                .addGroup(roundPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(roundPanel2Layout.createSequentialGroup()
                        .addComponent(txtLoaiVatTu, javax.swing.GroupLayout.PREFERRED_SIZE, 415, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(84, 84, 84))
                    .addGroup(roundPanel2Layout.createSequentialGroup()
                        .addComponent(bthDelete, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnAdd3, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(284, Short.MAX_VALUE))))
        );
        roundPanel2Layout.setVerticalGroup(
            roundPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(roundPanel2Layout.createSequentialGroup()
                .addGroup(roundPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(roundPanel2Layout.createSequentialGroup()
                        .addGap(35, 35, 35)
                        .addGroup(roundPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtMAVatTu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtLoaiVatTu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 69, Short.MAX_VALUE))
                    .addGroup(roundPanel2Layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(roundPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnAdd, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnEdit, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(bthDelete, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnAdd3, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap())
        );

        panelBorder1.setBackground(new java.awt.Color(36, 87, 157));
        panelBorder1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Tìm kiếm:", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 20), new java.awt.Color(255, 255, 255))); // NOI18N
        panelBorder1.setForeground(new java.awt.Color(255, 255, 255));

        buttonGroup1.add(v1);
        v1.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        v1.setForeground(new java.awt.Color(255, 255, 255));
        v1.setText("Mã vật tư:");
        v1.setOpaque(false);

        buttonGroup1.add(rbtTENTAIKHOAN1);
        rbtTENTAIKHOAN1.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        rbtTENTAIKHOAN1.setForeground(new java.awt.Color(255, 255, 255));
        rbtTENTAIKHOAN1.setText("Loại vật tư:");
        rbtTENTAIKHOAN1.setOpaque(false);

        btnView.setText("Xem");
        btnView.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnViewActionPerformed(evt);
            }
        });

        btnSearch.setText("Tìm kiếm");
        btnSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSearchActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelBorder1Layout = new javax.swing.GroupLayout(panelBorder1);
        panelBorder1.setLayout(panelBorder1Layout);
        panelBorder1Layout.setHorizontalGroup(
            panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBorder1Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(v1)
                    .addComponent(rbtTENTAIKHOAN1))
                .addGap(54, 54, 54)
                .addGroup(panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtSearchMa, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtSEARCHTen, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 44, Short.MAX_VALUE)
                .addGroup(panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnView, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(23, 23, 23))
        );
        panelBorder1Layout.setVerticalGroup(
            panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBorder1Layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(v1)
                    .addComponent(txtSearchMa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(rbtTENTAIKHOAN1)
                    .addComponent(txtSEARCHTen, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(43, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelBorder1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnView, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
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
        jScrollPane1.setViewportView(tbSupplies);

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel8.setText("Thông tin vật tư:");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(roundPanel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane1)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel8)
                            .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 183, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(panelBorder1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(roundPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(panelBorder1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel8)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 12, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 245, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(50, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddActionPerformed
        // TODO add your handling code here:
        

        StringBuilder sb = new StringBuilder();
        if (txtMAVatTu.getText().equals("")) {
            sb.append("Mã vật tư không được để trống");
            txtMAVatTu.setBackground(Color.red);

        } else {
            txtMAVatTu.setBackground(Color.white);
        }
        if (sb.length() > 0) {
            JOptionPane.showMessageDialog(this, sb);
        }
        try {
            ModelSuppliesv2 nv = new ModelSuppliesv2();
            nv.setMaVatTu(txtMAVatTu.getText());
            nv.setTenVatTu(txtLoaiVatTu.getText());
            
            ModelSupplies ql1 = new ModelSupplies();
            ql1.insert(nv);

            JOptionPane.showMessageDialog(this, "Lưu thành công!!!");
            loadthongtinSupplies();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "error " + e.getMessage());
            e.printStackTrace();
        }
    }//GEN-LAST:event_btnAddActionPerformed

    private void btnEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditActionPerformed
        // TODO add your handling code here:

        StringBuilder sb = new StringBuilder();
        if (txtMAVatTu.getText().equals("")) {
            sb.append("Mã vật tư không được để trống");
            txtMAVatTu.setBackground(Color.red);

        } else {
            txtMAVatTu.setBackground(Color.white);
        }
        if (sb.length() > 0) {
            JOptionPane.showMessageDialog(this, sb);
        }
        if (JOptionPane.showConfirmDialog(this, "bạn có muốn sửa nhân viên không??") == JOptionPane.NO_OPTION) {
            return;
        }
        try {

            ModelSuppliesv2 nv = new ModelSuppliesv2();
            nv.setMaVatTu(txtMAVatTu.getText());
            nv.setTenVatTu(txtLoaiVatTu.getText());

            ModelSupplies ql1 = new ModelSupplies();
            ql1.update(nv);

            JOptionPane.showMessageDialog(this, "Sửa thành công!!! ");
            loadthongtinSupplies();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "error " + e.getMessage());
            e.printStackTrace();
        }
    }//GEN-LAST:event_btnEditActionPerformed

    private void bthDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bthDeleteActionPerformed
        // TODO add your handling code here:
        StringBuilder sb = new StringBuilder();
        if (txtMAVatTu.getText().equals("")) {
            sb.append("Mã vật tư không được để trống");
            txtMAVatTu.setBackground(Color.red);

        } else {
            txtMAVatTu.setBackground(Color.white);
        }
        if (sb.length() > 0) {
            JOptionPane.showMessageDialog(this, sb);
        }
        if (JOptionPane.showConfirmDialog(this, "Bạn có muốn xóa vật tư không??") == JOptionPane.NO_OPTION) {
            return;
        }
        try {
            
            ModelSuppliesv2 qll = new ModelSuppliesv2();
            qll.setMaVatTu(txtMAVatTu.getText());
            
            ModelSupplies ql = new ModelSupplies();
            ql.deletecomeroot(qll);

            JOptionPane.showMessageDialog(this, "Xóa thành công!!! ");
            loadthongtinSupplies();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "error " + e.getMessage());
            e.printStackTrace();
        }
    }//GEN-LAST:event_bthDeleteActionPerformed

    private void btnAdd3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAdd3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnAdd3ActionPerformed

    private void btnViewActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnViewActionPerformed
        // TODO add your handling code here:
        loadthongtinSupplies();
    }//GEN-LAST:event_btnViewActionPerformed

    private void btnSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSearchActionPerformed
        // TODO add your handling code here:
        if (v1.isSelected() == true) {

            try {
                String sql = "select * from vattu where MaVatTu like ? and isvisible = '1' ";
                conn = cn.getConnection();
                PreparedStatement pstmt = conn.prepareStatement(sql);

                String searchCriteria = "%";
                if (!txtSearchMa.getText().equals("")) {
                    searchCriteria += txtSearchMa.getText() + "%";

                }
                pstmt.setString(1, searchCriteria);
                ResultSet rs = pstmt.executeQuery();
                tbmodel.setRowCount(0);
                while (rs.next()) {
                    tbmodel.addRow(new Object[]{
                        rs.getString("MaVatTu"), rs.getString("TenVatTu")

                    });
                }
                tbmodel.fireTableDataChanged();
                //loadthongtintaikhoan();

            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, e.getMessage());
                e.printStackTrace();
            }
        } else if (rbtTENTAIKHOAN1.isSelected() == true) {

            try {
                String sql = "select * from vattu where TenVatTu like ? and isvisible = '1' ";
                conn = cn.getConnection();
                PreparedStatement pstmt = conn.prepareStatement(sql);

                String searchCriteria = "%";
                if (!txtSEARCHTen.getText().equals("")) {
                    searchCriteria += txtSEARCHTen.getText() + "%";

                }
                pstmt.setString(1, searchCriteria);
                ResultSet rs = pstmt.executeQuery();
                tbmodel.setRowCount(0);
                while (rs.next()) {
                    tbmodel.addRow(new Object[]{
                        rs.getString("MaVatTu"), rs.getString("TenVatTu")
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

    private void tbSuppliesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbSuppliesMouseClicked
        // TODO add your handling code here:
        int row = tbSupplies.getSelectedRow();

        if (row >= 0) {
            txtMAVatTu.setText(tbSupplies.getValueAt(row, 0).toString());
            txtLoaiVatTu.setText(tbSupplies.getValueAt(row, 1).toString());
            
        }
    }//GEN-LAST:event_tbSuppliesMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private swing.Button bthDelete;
    private swing.Button btnAdd;
    private swing.Button btnAdd3;
    private swing.Button btnEdit;
    private swing.Button btnSearch;
    private swing.Button btnView;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private swing.PanelBorder panelBorder1;
    private javax.swing.JRadioButton rbtTENTAIKHOAN1;
    private swing.RoundPanel roundPanel2;
    private javax.swing.JTable tbSupplies;
    private component.TextField txtLoaiVatTu;
    private component.TextField txtMAVatTu;
    private javax.swing.JTextField txtSEARCHTen;
    private javax.swing.JTextField txtSearchMa;
    private javax.swing.JRadioButton v1;
    // End of variables declaration//GEN-END:variables
}
