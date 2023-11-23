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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import model.ModelBillOfService;
import model.ModelBillOfServicev2;
import model.ModelCustomers;
import model.ModelService;
import model.ModelServicev2;
import model.ModelStaff;
import model.ModelSupplies;
import model.ModelSuppliesforRoom;
import model.ModelSuppliesforRoomv2;

/**
 *
 * @author TeeDee
 */
public class FormQLBillOfService extends javax.swing.JPanel {

    /**
     * Creates new form FormQLBillOfService
     */
    public FormQLBillOfService() {
        initComponents();
    }
    
    Connect cn = new Connect();
    Connection conn;
    DefaultTableModel tbmodel;

    private String username, password, quyen, DisplayName;
    
    public FormQLBillOfService(String username, String password, String DisplayName, String quyen) {
        initComponents();
        this.username = username;
        this.password = password;
        this.DisplayName = DisplayName;
        this.quyen = quyen;
        
        initCombobox_madv();
        initCombobox_makh();
        initCombobox_manv();

        inittable();
        loaddulieu1();
    }
    
    private void inittable() {
        tbmodel = new DefaultTableModel();
        tbmodel.setColumnIdentifiers(new String[]{"Mã dịch vụ", "Loại dịch vụ", "Mã nhân viên", "Tên nhân viên", "Mã khách hàng", "Tên khách hàng", "Đơn giá", "Số lượng", "Ngày lập hóa đơn", "Tổng tiền"});
        tbBiillService.setModel(tbmodel);
    }
    
    public void loaddulieu1() {
        try {
            ModelBillOfService ql = new ModelBillOfService();
            ArrayList<ModelBillOfServicev2> list = ql.findALL();
            tbmodel.setRowCount(0);
            for (ModelBillOfServicev2 p : list) {
                tbmodel.addRow(new Object[]{
                    p.getMaDV(), p.getTenDichVu(), p.getMaNV(), p.getTenNV(), p.getMaKH(),p.getTenKH(), p.getGiadv(), p.getSL(), p.getNgayLapHD(),p.calculateTotal()
                });
            }
            tbmodel.fireTableDataChanged();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "error " + e.getMessage());
            e.printStackTrace();
        }

    }
    
    private void initCombobox_madv() {
        try {
            conn = cn.getConnection();
            String sql = "Select MaDV from DICHVU where isvisible = '1' ";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();
            cmbmadv.removeAllItems();
            cmbmadv.removeAllItems();
            while (rs.next()) {
                cmbmadv.addItem(rs.getString("MaDV"));
                cmbmadv.addItem(rs.getString("MaDV"));
            }
            rs.close();
            pstmt.close();
            conn.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
            e.printStackTrace();
        }
    }
    
    private void initCombobox_makh() {
        try {
            conn = cn.getConnection();
            String sql = "Select MaKH from KHACHHANG where isvisible = '1' ";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();
            cmbmakh.removeAllItems();
            cmbmakh.removeAllItems();
            while (rs.next()) {
                cmbmakh.addItem(rs.getString("MaKH"));
                cmbmakh.addItem(rs.getString("MaKH"));
            }
            rs.close();
            pstmt.close();
            conn.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
            e.printStackTrace();
        }
    }
    
    private void initCombobox_manv() {
        try {
            conn = cn.getConnection();
            String sql = "Select MaNV from NHANVIEN where isvisible = '1' ";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();
            cmbmanv.removeAllItems();
            cmbmanv.removeAllItems();
            while (rs.next()) {
                cmbmanv.addItem(rs.getString("MaNV"));
                cmbmanv.addItem(rs.getString("MaNV"));
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
        jLabel48 = new javax.swing.JLabel();
        jLabel49 = new javax.swing.JLabel();
        txtTENDICHVU = new javax.swing.JTextField();
        cmbmadv = new javax.swing.JComboBox<>();
        jLabel50 = new javax.swing.JLabel();
        jLabel51 = new javax.swing.JLabel();
        cmbmanv = new javax.swing.JComboBox<>();
        txtTENnv = new javax.swing.JTextField();
        jLabel53 = new javax.swing.JLabel();
        jLabel54 = new javax.swing.JLabel();
        cmbmakh = new javax.swing.JComboBox<>();
        txtTENkh = new javax.swing.JTextField();
        jLabel52 = new javax.swing.JLabel();
        txtdongia = new javax.swing.JTextField();
        jLabel55 = new javax.swing.JLabel();
        txtsl = new javax.swing.JTextField();
        jLabel56 = new javax.swing.JLabel();
        txttongtien = new javax.swing.JTextField();
        jLabel57 = new javax.swing.JLabel();
        jDateChooserNgayLapHD = new com.toedter.calendar.JDateChooser();
        jScrollPane3 = new javax.swing.JScrollPane();
        tbBiillService = new javax.swing.JTable();

        roundPanel5.setBackground(new java.awt.Color(36, 87, 157));
        roundPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Thông tin hóa đơn dịch vụ", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 24), new java.awt.Color(255, 255, 255))); // NOI18N

        jLabel48.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        jLabel48.setForeground(new java.awt.Color(255, 255, 255));
        jLabel48.setText("Mã dịch vụ:");

        jLabel49.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        jLabel49.setForeground(new java.awt.Color(255, 255, 255));
        jLabel49.setText("Tên dịch vụ:");

        cmbmadv.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cmbmadvItemStateChanged(evt);
            }
        });

        jLabel50.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        jLabel50.setForeground(new java.awt.Color(255, 255, 255));
        jLabel50.setText("Mã nhân viên:");

        jLabel51.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        jLabel51.setForeground(new java.awt.Color(255, 255, 255));
        jLabel51.setText("Tên nhân viên:");

        cmbmanv.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cmbmanvItemStateChanged(evt);
            }
        });

        jLabel53.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        jLabel53.setForeground(new java.awt.Color(255, 255, 255));
        jLabel53.setText("Mã khách hàng:");

        jLabel54.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        jLabel54.setForeground(new java.awt.Color(255, 255, 255));
        jLabel54.setText("Tên khách hàng:");

        cmbmakh.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cmbmakhItemStateChanged(evt);
            }
        });

        jLabel52.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        jLabel52.setForeground(new java.awt.Color(255, 255, 255));
        jLabel52.setText("Đơn giá dịch vụ:");

        jLabel55.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        jLabel55.setForeground(new java.awt.Color(255, 255, 255));
        jLabel55.setText("Số lượng dịch vụ:");

        jLabel56.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        jLabel56.setForeground(new java.awt.Color(255, 255, 255));
        jLabel56.setText("Tổng tiền:");

        jLabel57.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        jLabel57.setForeground(new java.awt.Color(255, 255, 255));
        jLabel57.setText("Ngày lập hóa đơn:");

        javax.swing.GroupLayout roundPanel5Layout = new javax.swing.GroupLayout(roundPanel5);
        roundPanel5.setLayout(roundPanel5Layout);
        roundPanel5Layout.setHorizontalGroup(
            roundPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, roundPanel5Layout.createSequentialGroup()
                .addGap(48, 48, 48)
                .addGroup(roundPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel49)
                    .addComponent(jLabel48)
                    .addComponent(jLabel54)
                    .addComponent(jLabel53)
                    .addComponent(jLabel57))
                .addGap(23, 23, 23)
                .addGroup(roundPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtTENkh)
                    .addComponent(cmbmakh, 0, 279, Short.MAX_VALUE)
                    .addComponent(cmbmadv, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtTENDICHVU, javax.swing.GroupLayout.DEFAULT_SIZE, 279, Short.MAX_VALUE)
                    .addComponent(jDateChooserNgayLapHD, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(roundPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel51)
                    .addComponent(jLabel50)
                    .addComponent(jLabel52)
                    .addComponent(jLabel55)
                    .addComponent(jLabel56))
                .addGap(36, 36, 36)
                .addGroup(roundPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txttongtien, javax.swing.GroupLayout.PREFERRED_SIZE, 279, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtsl, javax.swing.GroupLayout.PREFERRED_SIZE, 279, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtdongia, javax.swing.GroupLayout.PREFERRED_SIZE, 279, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtTENnv, javax.swing.GroupLayout.PREFERRED_SIZE, 279, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cmbmanv, javax.swing.GroupLayout.PREFERRED_SIZE, 279, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(82, 82, 82))
        );
        roundPanel5Layout.setVerticalGroup(
            roundPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(roundPanel5Layout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addGroup(roundPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(roundPanel5Layout.createSequentialGroup()
                        .addGroup(roundPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(roundPanel5Layout.createSequentialGroup()
                                .addGroup(roundPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel48)
                                    .addComponent(cmbmadv, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(28, 28, 28)
                                .addGroup(roundPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(txtTENDICHVU, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel49)))
                            .addGroup(roundPanel5Layout.createSequentialGroup()
                                .addGroup(roundPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel50)
                                    .addComponent(cmbmanv, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(28, 28, 28)
                                .addGroup(roundPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(txtTENnv, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel51))))
                        .addGroup(roundPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(roundPanel5Layout.createSequentialGroup()
                                .addGap(28, 28, 28)
                                .addComponent(jLabel53)
                                .addGap(28, 28, 28)
                                .addComponent(jLabel54))
                            .addGroup(roundPanel5Layout.createSequentialGroup()
                                .addGap(26, 26, 26)
                                .addGroup(roundPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel52)
                                    .addComponent(txtdongia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                    .addGroup(roundPanel5Layout.createSequentialGroup()
                        .addComponent(cmbmakh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(28, 28, 28)
                        .addGroup(roundPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtTENkh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel55)
                            .addComponent(txtsl, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 29, Short.MAX_VALUE)
                .addGroup(roundPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, roundPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel56)
                        .addComponent(txttongtien, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel57))
                    .addComponent(jDateChooserNgayLapHD, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(76, 76, 76))
        );

        tbBiillService.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        tbBiillService.setModel(new javax.swing.table.DefaultTableModel(
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
        tbBiillService.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbBiillServiceMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(tbBiillService);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(roundPanel5, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 1095, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(roundPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 239, Short.MAX_VALUE)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void cmbmadvItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cmbmadvItemStateChanged
        // TODO add your handling code here:
        String Madv = cmbmadv.getSelectedItem().toString();
        try {
            ModelService ql = new ModelService();

            model.ModelServicev2 ttp = ql.findByID(Madv);
            if (ttp != null) {
                txtTENDICHVU.setText(ttp.getTenDichVu());

            } else {
                JOptionPane.showMessageDialog(this, "Không tim thấy mã dịch vụ");
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "error " + e.getMessage());
            e.printStackTrace();
        }
    }//GEN-LAST:event_cmbmadvItemStateChanged

    private void cmbmanvItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cmbmanvItemStateChanged
        // TODO add your handling code here:
        String Manhanvien = cmbmanv.getSelectedItem().toString();
        try {
            ModelStaff ql = new ModelStaff();

            model.ModelStaffv2 ttp = ql.findByID(Manhanvien);
            if (ttp != null) {
                txtTENnv.setText(ttp.getTenNV());

            } else {
                JOptionPane.showMessageDialog(this, "Không tim thấy mã nhân viên");
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "error " + e.getMessage());
            e.printStackTrace();
        }
    }//GEN-LAST:event_cmbmanvItemStateChanged

    private void cmbmakhItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cmbmakhItemStateChanged
        // TODO add your handling code here:
        String Makh = cmbmakh.getSelectedItem().toString();
        try {
            ModelCustomers ql = new ModelCustomers();

            model.ModelCustomersv2 ttp = ql.findByID(Makh);
            if (ttp != null) {
                txtTENkh.setText(ttp.getTenKH());

            } else {
                JOptionPane.showMessageDialog(this, "Không tim thấy mã khách hàng");
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "error " + e.getMessage());
            e.printStackTrace();
        }
        
    }//GEN-LAST:event_cmbmakhItemStateChanged

    private void tbBiillServiceMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbBiillServiceMouseClicked
        // TODO add your handling code here:
        int row = tbBiillService.getSelectedRow();

        if (row >= 0) {

            String madv = tbBiillService.getValueAt(row, 0).toString();
            System.out.println("" + madv);
            cmbmadv.setSelectedItem(madv);

            txtTENDICHVU.setText(tbBiillService.getValueAt(row, 1).toString());

            String manv = tbBiillService.getValueAt(row, 2).toString();
            System.out.println("" + manv);
            cmbmanv.setSelectedItem(manv);

            txtTENnv.setText(tbBiillService.getValueAt(row, 3).toString());

            String makh = tbBiillService.getValueAt(row, 4).toString();
            System.out.println("" + makh);
            cmbmanv.setSelectedItem(makh);

            txtTENkh.setText(tbBiillService.getValueAt(row, 5).toString());
            
            ModelBillOfServicev2 ms =new ModelBillOfServicev2();
            BigDecimal x = (BigDecimal) tbBiillService.getValueAt(row, 6);
            ms.setGiadv(x);
            String formattedDonGia = ms.getFormattedGiaDv();
            System.out.println(formattedDonGia);
            txtdongia.setText(formattedDonGia);
            
            
            
            
            
            txtsl.setText(tbBiillService.getValueAt(row, 7).toString());
            
            String ngaylaphd = tbBiillService.getValueAt(row, 8).toString();
            try {
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd"); // Định dạng của chuỗi ngày
                Date selectedDate = dateFormat.parse(ngaylaphd); // Phân tích chuỗi thành đối tượng Date
                jDateChooserNgayLapHD.setDate(selectedDate); // Đặt giá trị ngày cho JDateChooser
            } catch (ParseException e) {
                e.printStackTrace();
            }
            
            
            ModelBillOfServicev2 mss =new ModelBillOfServicev2();
            BigDecimal xx = (BigDecimal) tbBiillService.getValueAt(row, 9);
            mss.setTongtien(xx);
            String formattedtongtien = mss.getFormattedTongTien();
            System.out.println(formattedtongtien);
            
            txttongtien.setText(formattedtongtien);
        }
    }//GEN-LAST:event_tbBiillServiceMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> cmbmadv;
    private javax.swing.JComboBox<String> cmbmakh;
    private javax.swing.JComboBox<String> cmbmanv;
    private com.toedter.calendar.JDateChooser jDateChooserNgayLapHD;
    private javax.swing.JLabel jLabel48;
    private javax.swing.JLabel jLabel49;
    private javax.swing.JLabel jLabel50;
    private javax.swing.JLabel jLabel51;
    private javax.swing.JLabel jLabel52;
    private javax.swing.JLabel jLabel53;
    private javax.swing.JLabel jLabel54;
    private javax.swing.JLabel jLabel55;
    private javax.swing.JLabel jLabel56;
    private javax.swing.JLabel jLabel57;
    private javax.swing.JScrollPane jScrollPane3;
    private swing.RoundPanel roundPanel5;
    private javax.swing.JTable tbBiillService;
    private javax.swing.JTextField txtTENDICHVU;
    private javax.swing.JTextField txtTENkh;
    private javax.swing.JTextField txtTENnv;
    private javax.swing.JTextField txtdongia;
    private javax.swing.JTextField txtsl;
    private javax.swing.JTextField txttongtien;
    // End of variables declaration//GEN-END:variables
}
