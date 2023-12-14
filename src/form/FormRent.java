/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package form;

import com.formdev.flatlaf.util.StringUtils;
import connect.Connect;
import java.awt.Color;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import model.ModelBillOfService;
import model.ModelBillOfServicev2;
import model.ModelCustomers;
import model.ModelPhong;
import model.ModelPhongv2;
import model.ModelRent;
import model.ModelRentv2;
import model.ModelRoom;
import model.ModelRoomv2;
import model.ModelStaff;
import model.ModelStaffv2;

/**
 *
 * @author TeeDee
 */
public class FormRent extends javax.swing.JPanel {

    /**
     * Creates new form FormRent
     */
    public FormRent() {
        initComponents();
    }
    
    Connect cn = new Connect();
    Connection conn;
    DefaultTableModel tbmodel;

    private String username, password, quyen, DisplayName, MaNV ;
    
    public FormRent(String username, String password, String DisplayName, String quyen, String MaNV) {
        initComponents();
        this.username = username;
        this.password = password;
        this.DisplayName = DisplayName;
        this.quyen = quyen;
        this.MaNV = MaNV;
        
        initCombobox_makh();
        
        inittable();
        loaddulieu1();
        txtMaNV.setText(MaNV);
        
        // lấy ngày hiện tại
        LocalDate currentDate = LocalDate.now();
        // Định dạng ngày theo "yyyy-MM-dd"
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String formattedDate = currentDate.format(formatter);
        txtNgayDatPhong.setText(formattedDate);

        
    }

    private void inittable() {
        tbmodel = new DefaultTableModel();
        tbmodel.setColumnIdentifiers(new String[]{"Mã phiếu đặt phòng", "Mã khách hàng", "Họ tên khách hàng", "Mã nhân viên", 
            "Họ tên nhân viên","Mã phòng", "Tên phòng", "Loại phòng", "Ngày đặt phòng","Ngày dự kiến thuê","Ngày dự kiến trả", "Giá phòng"});
        TBRent.setModel(tbmodel);
    }
    
    public void loaddulieu1() {
        try {
            ModelRent ql = new ModelRent();
            ArrayList<ModelRentv2> list = ql.findALL();
            tbmodel.setRowCount(0);
            for (ModelRentv2 p : list) {
                tbmodel.addRow(new Object[]{
                    p.getMaPhieuDatPhong(), p.getMaKH(),p.getHoKH()+" "+ p.getTenKH(), p.getMaNV(), p.getHoNV()+ " " + p.getTenNV(), p.getMaPhong(),p.getTenPhong(), p.getLoaiPhong(), p.getNgayDatPhong(), p.getNgayDuKienThue(), p.getNgayDuKienTra(), p.getFormattedGia()
                });
            }
            tbmodel.fireTableDataChanged();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "error " + e.getMessage());
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
            }
            rs.close();
            pstmt.close();
            conn.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
            e.printStackTrace();
        }
    }
    
    
    
    private void initCombobox_maphong() {
        try {
            
            Date selectedDate = jDateChooserngaydkthue.getDate();
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(selectedDate);

            int year = calendar.get(Calendar.YEAR);
            int month = calendar.get(Calendar.MONTH) + 1; // Tháng bắt đầu từ 0, nên cộng thêm 1 để có giá trị tháng thực
            int day = calendar.get(Calendar.DAY_OF_MONTH);
            String ngaythue = year + "-" + month + "-" + day;

            Date selectedDate1 = jDateChooserngaydukientra.getDate();
            Calendar calendar1 = Calendar.getInstance();
            calendar1.setTime(selectedDate1);

            int year1 = calendar1.get(Calendar.YEAR);
            int month1 = calendar1.get(Calendar.MONTH) + 1; // Tháng bắt đầu từ 0, nên cộng thêm 1 để có giá trị tháng thực
            int day1 = calendar1.get(Calendar.DAY_OF_MONTH);
            String ngaytra = year1 + "-" + month1 + "-" + day1;
            
            
            conn = cn.getConnection();
            String sql = "select distinct maphong from phong where MaTinhtrangphong = 'TT2' and isvisible = '1' and MaPhong not in (select distinct MaPhong from PhieuDatPhong where booked_status = 0 \n" +
"and (NgayDuKienThue between '" + ngaythue+ "' AND '" + ngaytra+ "' or  NgayDuKienTra between '" + ngaythue+"' AND '" + ngaytra+"' )  )  ";//
            PreparedStatement pstmt = conn.prepareStatement(sql);
 
            ResultSet rs = pstmt.executeQuery();

            cmbmaphon.removeAllItems();
            while (rs.next()) {
                cmbmaphon.addItem(rs.getString("maphong"));
            }
            rs.close();
            pstmt.close();
            conn.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
            e.printStackTrace();
            System.out.println("lỗi");
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
        roundPanel5 = new swing.RoundPanel();
        jLabel12 = new javax.swing.JLabel();
        btnEdit = new swing.Button();
        btnAdd = new swing.Button();
        bthDelete = new swing.Button();
        btnRefresh = new swing.Button();
        cmbmakh = new javax.swing.JComboBox<>();
        cmbmaphon = new javax.swing.JComboBox<>();
        txtMaphieudatphong = new component.TextField();
        txtTenPhong = new component.TextField();
        txtMaNV = new component.TextField();
        jLabel14 = new javax.swing.JLabel();
        txtTENkh = new component.TextField();
        txtloaiphong = new component.TextField();
        txtGiaPhong = new component.TextField();
        jLabel16 = new javax.swing.JLabel();
        jDateChooserngaydkthue = new com.toedter.calendar.JDateChooser();
        jLabel17 = new javax.swing.JLabel();
        jDateChooserngaydukientra = new com.toedter.calendar.JDateChooser();
        txtTenNV = new component.TextField();
        txtNgayDatPhong = new component.TextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        btnThemKH = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        TBRent = new javax.swing.JTable();
        jLabel8 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();

        roundPanel5.setBackground(new java.awt.Color(36, 87, 157));
        roundPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Thông tin đặt phòng", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 24), new java.awt.Color(255, 255, 255))); // NOI18N

        jLabel12.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(255, 255, 255));
        jLabel12.setText("Mã khách hàng:");

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

        cmbmakh.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cmbmakhItemStateChanged(evt);
            }
        });

        cmbmaphon.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cmbmaphonItemStateChanged(evt);
            }
        });
        cmbmaphon.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                cmbmaphonMousePressed(evt);
            }
        });

        txtMaphieudatphong.setLabelText("Mã phiếu đặt phòng");

        txtTenPhong.setEditable(false);
        txtTenPhong.setLabelText("Tên phòng");

        txtMaNV.setEditable(false);
        txtMaNV.setLabelText("Mã nhân viên");
        txtMaNV.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                txtMaNVPropertyChange(evt);
            }
        });

        jLabel14.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(255, 255, 255));
        jLabel14.setText("Mã phòng:");

        txtTENkh.setEditable(false);
        txtTENkh.setLabelText("Tên khách hàng");

        txtloaiphong.setEditable(false);
        txtloaiphong.setLabelText("Loại phòng");
        txtloaiphong.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtloaiphongActionPerformed(evt);
            }
        });

        txtGiaPhong.setEditable(false);
        txtGiaPhong.setLabelText("Giá phòng");
        txtGiaPhong.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtGiaPhongActionPerformed(evt);
            }
        });

        jLabel16.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(255, 255, 255));
        jLabel16.setText("Ngày thuê phòng:");

        jDateChooserngaydkthue.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                jDateChooserngaydkthuePropertyChange(evt);
            }
        });

        jLabel17.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(255, 255, 255));
        jLabel17.setText("Ngày trả phòng:");

        jDateChooserngaydukientra.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                jDateChooserngaydukientraPropertyChange(evt);
            }
        });

        txtTenNV.setEditable(false);
        txtTenNV.setLabelText("Tên nhân viên");

        txtNgayDatPhong.setEditable(false);
        txtNgayDatPhong.setLabelText("Ngày đặt phòng");

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 51, 0));
        jLabel1.setText("*");

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 51, 0));
        jLabel2.setText("*");

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 51, 0));
        jLabel3.setText("*");

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 51, 0));
        jLabel4.setText("*");

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 51, 0));
        jLabel5.setText("*");

        jButton1.setBackground(new java.awt.Color(255, 99, 76));
        jButton1.setText("Xem HT phòng");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        btnThemKH.setBackground(new java.awt.Color(255, 99, 76));
        btnThemKH.setText("Thêm khách hàng");
        btnThemKH.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemKHActionPerformed(evt);
            }
        });

        jButton2.setText("Tìm");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout roundPanel5Layout = new javax.swing.GroupLayout(roundPanel5);
        roundPanel5.setLayout(roundPanel5Layout);
        roundPanel5Layout.setHorizontalGroup(
            roundPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, roundPanel5Layout.createSequentialGroup()
                .addGroup(roundPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(roundPanel5Layout.createSequentialGroup()
                        .addGap(44, 44, 44)
                        .addComponent(jLabel1))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, roundPanel5Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel2)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(roundPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, roundPanel5Layout.createSequentialGroup()
                        .addComponent(txtTENkh, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnThemKH))
                    .addComponent(txtTenNV, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtMaNV, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(roundPanel5Layout.createSequentialGroup()
                        .addGroup(roundPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(roundPanel5Layout.createSequentialGroup()
                                .addGap(6, 6, 6)
                                .addComponent(jLabel12)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(cmbmakh, javax.swing.GroupLayout.PREFERRED_SIZE, 286, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(txtMaphieudatphong, javax.swing.GroupLayout.PREFERRED_SIZE, 424, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(txtNgayDatPhong, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGroup(roundPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(roundPanel5Layout.createSequentialGroup()
                        .addGroup(roundPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(roundPanel5Layout.createSequentialGroup()
                                .addGap(70, 70, 70)
                                .addGroup(roundPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(cmbmaphon, javax.swing.GroupLayout.PREFERRED_SIZE, 335, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtloaiphong, javax.swing.GroupLayout.PREFERRED_SIZE, 424, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtGiaPhong, javax.swing.GroupLayout.PREFERRED_SIZE, 424, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(roundPanel5Layout.createSequentialGroup()
                                        .addComponent(txtTenPhong, javax.swing.GroupLayout.PREFERRED_SIZE, 303, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addGroup(roundPanel5Layout.createSequentialGroup()
                                .addGap(56, 56, 56)
                                .addComponent(jLabel3)
                                .addGap(4, 4, 4)
                                .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(19, 19, 19))
                    .addGroup(roundPanel5Layout.createSequentialGroup()
                        .addGap(55, 55, 55)
                        .addGroup(roundPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(roundPanel5Layout.createSequentialGroup()
                                .addComponent(jLabel5)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel17)
                                .addGap(309, 309, 309))
                            .addGroup(roundPanel5Layout.createSequentialGroup()
                                .addGroup(roundPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jDateChooserngaydukientra, javax.swing.GroupLayout.PREFERRED_SIZE, 268, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(roundPanel5Layout.createSequentialGroup()
                                        .addComponent(jLabel4)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jLabel16)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(jDateChooserngaydkthue, javax.swing.GroupLayout.PREFERRED_SIZE, 268, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(19, 19, 19)))))
                .addGap(50, 50, 50))
            .addGroup(roundPanel5Layout.createSequentialGroup()
                .addGap(286, 286, 286)
                .addComponent(btnAdd, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnEdit, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(bthDelete, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnRefresh, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        roundPanel5Layout.setVerticalGroup(
            roundPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(roundPanel5Layout.createSequentialGroup()
                .addGroup(roundPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(roundPanel5Layout.createSequentialGroup()
                        .addGap(17, 17, 17)
                        .addGroup(roundPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(cmbmaphon, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel14)
                            .addComponent(jLabel3)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, roundPanel5Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(roundPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(txtMaphieudatphong, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(18, 18, 18)
                .addGroup(roundPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(roundPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(cmbmakh, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel12)
                        .addComponent(jLabel2))
                    .addComponent(txtTenPhong, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(22, 22, 22)
                .addGroup(roundPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnThemKH, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtloaiphong, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtTENkh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(roundPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtGiaPhong, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtMaNV, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(roundPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(roundPanel5Layout.createSequentialGroup()
                        .addGroup(roundPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtTenNV, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel16)
                            .addComponent(jLabel4))
                        .addGap(18, 18, 18)
                        .addGroup(roundPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtNgayDatPhong, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel17)
                            .addComponent(jLabel5)))
                    .addGroup(roundPanel5Layout.createSequentialGroup()
                        .addComponent(jDateChooserngaydkthue, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jDateChooserngaydukientra, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(roundPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(roundPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnAdd, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnEdit, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(bthDelete, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnRefresh, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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
        jLabel8.setText("Thông tin đặt phòng:");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel8)
                            .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 183, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(roundPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(roundPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(33, 33, 33)
                .addComponent(jLabel8)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 12, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 254, Short.MAX_VALUE)
                .addGap(9, 9, 9))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditActionPerformed
        // TODO add your handling code here:
        StringBuilder sb = new StringBuilder();
        if (txtMaphieudatphong.getText().equals("")) {
            sb.append("Mã phiếu đặt phòng không được để trống");
            txtMaphieudatphong.setBackground(Color.red);

        } else {
            txtMaphieudatphong.setBackground(Color.white);
            if (sb.length() > 0) {
                JOptionPane.showMessageDialog(this, sb);
            }
            
            if (JOptionPane.showConfirmDialog(this, "bạn có muốn sửa phiếu đặt phòng không??") == JOptionPane.YES_OPTION) {
                try {
                    String MaPhong = cmbmaphon.getSelectedItem().toString();
                    Date selectedDate = jDateChooserngaydkthue.getDate();
                    Calendar calendar = Calendar.getInstance();
                    calendar.setTime(selectedDate);

                    int year = calendar.get(Calendar.YEAR);
                    int month = calendar.get(Calendar.MONTH) + 1; // Tháng bắt đầu từ 0, nên cộng thêm 1 để có giá trị tháng thực
                    int day = calendar.get(Calendar.DAY_OF_MONTH);
                    String ngaythue = year + "-" + month + "-" + day;


                    Date selectedDate1 = jDateChooserngaydukientra.getDate();
                    Calendar calendar1 = Calendar.getInstance();
                    calendar1.setTime(selectedDate1);

                    int year1 = calendar1.get(Calendar.YEAR);
                    int month1 = calendar1.get(Calendar.MONTH) + 1; // Tháng bắt đầu từ 0, nên cộng thêm 1 để có giá trị tháng thực
                    int day1 = calendar1.get(Calendar.DAY_OF_MONTH);
                    String ngaytra = year1 + "-" + month1 + "-" + day1;

                    ModelRentv2 nv = new ModelRentv2();
                    nv.setMaPhieuDatPhong(txtMaphieudatphong.getText());
                    nv.setNgayDuKienThue(ngaythue);
                    nv.setNgayDuKienTra(ngaytra);
                    nv.setMaPhong(MaPhong);

                    ModelRent ql1 = new ModelRent();
                    ql1.update(nv);

                    JOptionPane.showMessageDialog(this, "Sửa thành công!!!");

                    loaddulieu1();
                } catch (Exception e) {
        //            JOptionPane.showMessageDialog(this, "error " + e.getMessage());
                     JOptionPane.showMessageDialog(this, "Mã phiếu đặt phòng này đã tồn tại, nếu muốn thêm với mã phiếu đặt phòng này vui lòng xóa phiếu đặt phòng trong db");
                    e.printStackTrace();
                }
            }else {
                return;
            }
            
        }
    }//GEN-LAST:event_btnEditActionPerformed

    private void btnAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddActionPerformed
        // TODO add your handling code here:
        StringBuilder sb = new StringBuilder();
        if (txtMaphieudatphong.getText().equals("")) {
            sb.append("Mã phiếu đặt phòng không được để trống");
            txtMaphieudatphong.setBackground(Color.red);

        } else {
            txtMaphieudatphong.setBackground(Color.white);
            if (sb.length() > 0) {
                JOptionPane.showMessageDialog(this, sb);
            }
            String MaKH = cmbmakh.getSelectedItem().toString();
            String MaPhong = cmbmaphon.getSelectedItem().toString();

            try {
                Date selectedDate = jDateChooserngaydkthue.getDate();
                Calendar calendar = Calendar.getInstance();
                calendar.setTime(selectedDate);

                int year = calendar.get(Calendar.YEAR);
                int month = calendar.get(Calendar.MONTH) + 1; // Tháng bắt đầu từ 0, nên cộng thêm 1 để có giá trị tháng thực
                int day = calendar.get(Calendar.DAY_OF_MONTH);
                String ngaythue = year + "-" + month + "-" + day;

                Date selectedDate1 = jDateChooserngaydukientra.getDate();
                Calendar calendar1 = Calendar.getInstance();
                calendar1.setTime(selectedDate1);

                int year1 = calendar1.get(Calendar.YEAR);
                int month1 = calendar1.get(Calendar.MONTH) + 1; // Tháng bắt đầu từ 0, nên cộng thêm 1 để có giá trị tháng thực
                int day1 = calendar1.get(Calendar.DAY_OF_MONTH);
                String ngaytra = year1 + "-" + month1 + "-" + day1;

                ModelRentv2 nv = new ModelRentv2();
                nv.setMaPhieuDatPhong(txtMaphieudatphong.getText());
                nv.setNgayDatPhong(txtNgayDatPhong.getText());
                nv.setNgayDuKienThue(ngaythue);
                nv.setNgayDuKienTra(ngaytra);
                nv.setMaPhong(MaPhong);
                nv.setMaKH(MaKH);
                nv.setMaNV(txtMaNV.getText());

                ModelRent ql1 = new ModelRent();
                ql1.insert(nv);

                JOptionPane.showMessageDialog(this, "Lưu thành công!!!");

                loaddulieu1();
            } catch (Exception e) {
                 JOptionPane.showMessageDialog(this, "Mã phiếu đặt phòng này đã tồn tại, nếu muốn thêm với mã phiếu đặt phòng này vui lòng xóa phiếu đặt phòng trong db");
                e.printStackTrace();
            }
        }
        
      
    }//GEN-LAST:event_btnAddActionPerformed

    private void bthDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bthDeleteActionPerformed
        // TODO add your handling code here:

        StringBuilder sb = new StringBuilder();
        if (txtMaphieudatphong.getText().equals("")) {
            sb.append("Mã phiếu đặt phòng không được để trống");
            txtMaphieudatphong.setBackground(Color.red);
            
        } else {
            txtMaphieudatphong.setBackground(Color.white);
            if (sb.length() > 0) {
                JOptionPane.showMessageDialog(this, sb);
            }
            if (JOptionPane.showConfirmDialog(this, "bạn có muốn xóa phiếu đặt phòng không??") == JOptionPane.YES_OPTION) {
                try {
                    ModelRentv2 nv = new ModelRentv2();
                    nv.setMaPhieuDatPhong(txtMaphieudatphong.getText());
                    ModelRent ql1 = new ModelRent();
                    ql1.deletecomeroot(nv);

                    JOptionPane.showMessageDialog(this, "Xóa thành công!!!");
                    loaddulieu1();
                } catch (Exception e) {
        //            JOptionPane.showMessageDialog(this, "error " + e.getMessage());
                     JOptionPane.showMessageDialog(this, "Mã phiếu đặt phòng này đã tồn tại, nếu muốn thêm với mã phiếu đặt phòng này vui lòng xóa phiếu đặt phòng trong db");
                    e.printStackTrace();
                }
            }else {
                return;
            }
        }
        
        
        
        
    }//GEN-LAST:event_bthDeleteActionPerformed

    private void btnRefreshActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRefreshActionPerformed
        // TODO add your handling code here:
        txtMaphieudatphong.setText("");
        txtTENkh.setText("");
        txtMaNV.setText("");
        txtTenNV.setText("");
        txtNgayDatPhong.setText("");
        txtTenPhong.setText("");
        txtloaiphong.setText("");
        txtGiaPhong.setText("");
        
    }//GEN-LAST:event_btnRefreshActionPerformed

    private void cmbmakhItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cmbmakhItemStateChanged
        // TODO add your handling code here:
        String Makh = cmbmakh.getSelectedItem().toString();
        try {
            ModelCustomers ql = new ModelCustomers();

            model.ModelCustomersv2 ttp = ql.findByID(Makh);
            if (ttp != null) {
                txtTENkh.setText(ttp.getHoKH() +" "+ ttp.getTenKH());

            } else {
                JOptionPane.showMessageDialog(this, "Không tim thấy mã khách hàng");
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "error " + e.getMessage());
            e.printStackTrace();
        }

    }//GEN-LAST:event_cmbmakhItemStateChanged

    private void cmbmaphonItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cmbmaphonItemStateChanged
        // TODO add your handling code here:
         
        String MaPhong = cmbmaphon.getSelectedItem().toString();
        try {
            ModelRoom ql = new ModelRoom();

            model.ModelRoomv2 ttp = ql.findByID(MaPhong);
            if (ttp != null) {
                txtTenPhong.setText(ttp.getTenPhong());
                txtGiaPhong.setText(ttp.getFormattedDonGia());
                txtloaiphong.setText(ttp.getTenLoaiPhong());

            } else {
                JOptionPane.showMessageDialog(this, "Không tim thấy mã phòng");
            }

        } catch (Exception e) {
//            JOptionPane.showMessageDialog(this, "error " + e.getMessage());
//            e.printStackTrace();
        }
    }//GEN-LAST:event_cmbmaphonItemStateChanged

    private void TBRentMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TBRentMouseClicked
        // TODO add your handling code here:
        int row = TBRent.getSelectedRow();

        if (row >= 0) {
            txtMaphieudatphong.setText(TBRent.getValueAt(row, 0).toString());
            
            String makh = TBRent.getValueAt(row, 1).toString();
            System.out.println("" + makh);
            cmbmakh.setSelectedItem(makh);
            
            txtTENkh.setText(TBRent.getValueAt(row, 2).toString());
            
            txtMaNV.setText(TBRent.getValueAt(row, 3).toString());
            txtTenNV.setText(TBRent.getValueAt(row, 4).toString());
            
            
            String maphong = TBRent.getValueAt(row, 5).toString();
            System.out.println("" + maphong);
            cmbmaphon.setSelectedItem(maphong);
            
            txtTenPhong.setText(TBRent.getValueAt(row, 6).toString());
            txtloaiphong.setText(TBRent.getValueAt(row, 7).toString());
            txtNgayDatPhong.setText(TBRent.getValueAt(row, 8).toString());
            
            
            String ngaydukienthue = TBRent.getValueAt(row, 9).toString();
            System.out.println(ngaydukienthue);
            try {
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd"); // Định dạng của chuỗi ngày
                Date selectedDate = dateFormat.parse(ngaydukienthue); // Phân tích chuỗi thành đối tượng Date
                jDateChooserngaydkthue.setDate(selectedDate); // Đặt giá trị ngày cho JDateChooser
            } catch (ParseException e) {
                e.printStackTrace();
            }
            
            String ngaydukientra = TBRent.getValueAt(row, 10).toString();
            System.out.println(ngaydukienthue);
            try {
                SimpleDateFormat dateFormat1 = new SimpleDateFormat("yyyy-MM-dd"); // Định dạng của chuỗi ngày
                Date selectedDate = dateFormat1.parse(ngaydukientra); // Phân tích chuỗi thành đối tượng Date
                jDateChooserngaydukientra.setDate(selectedDate); // Đặt giá trị ngày cho JDateChooser
            } catch (ParseException e) {
                e.printStackTrace();
            }
            txtGiaPhong.setText(TBRent.getValueAt(row, 11).toString());
        }

    }//GEN-LAST:event_TBRentMouseClicked

    private void txtloaiphongActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtloaiphongActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtloaiphongActionPerformed

    private void txtGiaPhongActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtGiaPhongActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtGiaPhongActionPerformed

    private void txtMaNVPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_txtMaNVPropertyChange
        // TODO add your handling code here:
        String MaNV = txtMaNV.getText();
        try {
            ModelStaff ql = new ModelStaff();

            model.ModelStaffv2 ttp = ql.findByID(MaNV);
            if (ttp != null) {
                txtTenNV.setText(ttp.getHoNV() + " "+ ttp.getTenNV());

            } else {
//                JOptionPane.showMessageDialog(this, "Không tim thấy mã nhân viên");
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "error " + e.getMessage());
            e.printStackTrace();
        }
    }//GEN-LAST:event_txtMaNVPropertyChange

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        
       
        try{
            Date selectedDate = jDateChooserngaydkthue.getDate();
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(selectedDate);

            int year = calendar.get(Calendar.YEAR);
            int month = calendar.get(Calendar.MONTH) + 1; // Tháng bắt đầu từ 0, nên cộng thêm 1 để có giá trị tháng thực
            int day = calendar.get(Calendar.DAY_OF_MONTH);
            String ngaythue = year + "-" + month + "-" + day;

            Date selectedDate1 = jDateChooserngaydukientra.getDate();
            Calendar calendar1 = Calendar.getInstance();
            calendar1.setTime(selectedDate1);

            int year1 = calendar1.get(Calendar.YEAR);
            int month1 = calendar1.get(Calendar.MONTH) + 1; // Tháng bắt đầu từ 0, nên cộng thêm 1 để có giá trị tháng thực
            int day1 = calendar1.get(Calendar.DAY_OF_MONTH);
            String ngaytra = year1 + "-" + month1 + "-" + day1;

            System.out.println(ngaythue +"   "+ngaytra);
            
            FormcheckRoom l = new FormcheckRoom(username, password, DisplayName,quyen, ngaythue, ngaytra);
            l.setVisible(true);
            l.setLocationRelativeTo(null);
//            initCombobox_maphong();
        }catch(Exception e){
            JOptionPane.showMessageDialog(this, "Vui lòng chọn ngày thuê phòng, ngày trả phòng" );
        }
        
        try {
            cmbmaphon.removeAllItems();
            txtTenPhong.setText("");
            txtloaiphong.setText("");
            txtGiaPhong.setText("");
            initCombobox_maphong();
        }catch(Exception e){
            
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void btnThemKHActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemKHActionPerformed
        // TODO add your handling code here:
        FormaddKH l = new FormaddKH(username, password, DisplayName,quyen);
        l.setVisible(true);
        l.setLocationRelativeTo(null);
    }//GEN-LAST:event_btnThemKHActionPerformed

    private void jDateChooserngaydukientraPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_jDateChooserngaydukientraPropertyChange
        // TODO add your handling code here:
         
        
    }//GEN-LAST:event_jDateChooserngaydukientraPropertyChange

    private void jDateChooserngaydkthuePropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_jDateChooserngaydkthuePropertyChange
        // TODO add your handling code here:
       
    }//GEN-LAST:event_jDateChooserngaydkthuePropertyChange

    private void cmbmaphonMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cmbmaphonMousePressed
        // TODO add your handling code here:
       
    }//GEN-LAST:event_cmbmaphonMousePressed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        FormFindRent l = new FormFindRent(username, password, DisplayName,quyen);
        l.setVisible(true);
        l.setLocationRelativeTo(null);
    }//GEN-LAST:event_jButton2ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable TBRent;
    private swing.Button bthDelete;
    private swing.Button btnAdd;
    private swing.Button btnEdit;
    private swing.Button btnRefresh;
    private javax.swing.JButton btnThemKH;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JComboBox<String> cmbmakh;
    private javax.swing.JComboBox<String> cmbmaphon;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private com.toedter.calendar.JDateChooser jDateChooserngaydkthue;
    private com.toedter.calendar.JDateChooser jDateChooserngaydukientra;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private swing.RoundPanel roundPanel5;
    private component.TextField txtGiaPhong;
    private component.TextField txtMaNV;
    private component.TextField txtMaphieudatphong;
    private component.TextField txtNgayDatPhong;
    private component.TextField txtTENkh;
    private component.TextField txtTenNV;
    private component.TextField txtTenPhong;
    private component.TextField txtloaiphong;
    // End of variables declaration//GEN-END:variables
}
