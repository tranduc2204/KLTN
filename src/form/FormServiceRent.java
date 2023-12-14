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
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
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
import model.ModelStaff;
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

    private String username, password, quyen, DisplayName, MaNV;
    
    public FormServiceRent(String username, String password, String DisplayName, String quyen, String MaNV) {
        initComponents();
        this.username = username;
        this.password = password;
        this.DisplayName = DisplayName;
        this.quyen = quyen;
        this.MaNV = MaNV;
        
        
        initCombobox_madichvu();
        initCombobox_makhachhang();
  
        txtMaNV.setText(MaNV);

        inittable();
        loaddulieu1();
        // lấy ngày hiện tại
        LocalDate currentDate = LocalDate.now();
        // Định dạng ngày theo "yyyy-MM-dd"
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String formattedDate = currentDate.format(formatter);
        


        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd"); // Định dạng của chuỗi ngày
            Date selectedDate = dateFormat.parse(formattedDate); // Phân tích chuỗi thành đối tượng Date
            jDateChooserngaylaphoadon.setDate(selectedDate); // Đặt giá trị ngày cho JDateChooser
        } catch (ParseException e) {
            e.printStackTrace();
        }
        
    }
    
    private void inittable() {
        tbmodel = new DefaultTableModel();
        tbmodel.setColumnIdentifiers(new String[]{"Mã dịch vụ", "Tên dịch vụ", "Mã nhân viên",  "Họ tên nhân viên",
            "Mã khách hàng", "Họ tên khách hàng", "Ngày lập hóa đơn", "Đơn giá","Giảm giá",  "Số lượng","VAT", "Giá hóa đơn"});
        TBServiceRent.setModel(tbmodel);
    }
    
     public void loaddulieu1() {
        try {
            ModelServiceRent ql = new ModelServiceRent();
            ArrayList<ModelServiceRentv2> list = ql.findALL();
            tbmodel.setRowCount(0);
            for (ModelServiceRentv2 p : list) {
                tbmodel.addRow(new Object[]{
                    p.getMaDV(), p.getTenDichVu(), p.getMaNV(), p.getTenNhanVien(), p.getMaKH(),p.getHoKH()+" "+ p.getTenKH(),p.getNgayLapHD(), p.getFormattedGia(),p.getFormattedPercentage(),  p.getSL(),p.getFormattedVAT(),p.getFormattedGiahd()
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
        btnEdit = new swing.Button();
        btnAdd = new swing.Button();
        btnDelete = new swing.Button();
        btnRefresh = new swing.Button();
        jLabel11 = new javax.swing.JLabel();
        cmbmadichvu = new javax.swing.JComboBox<>();
        jLabel22 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jDateChooserngaylaphoadon = new com.toedter.calendar.JDateChooser();
        cmbmakhachhang = new javax.swing.JComboBox<>();
        btnPay = new javax.swing.JButton();
        btnReport = new javax.swing.JButton();
        txtTenDichVu = new component.TextField();
        txtTenNhanVien = new component.TextField();
        spSL = new component.Spinner();
        txtTenKH = new component.TextField();
        txtGiaDichvu = new component.TextField();
        txtGiahd = new component.TextField();
        txtVAT = new component.TextField();
        txtGiamGia = new component.TextField();
        txtMaNV = new component.TextField();
        btnThemKH = new javax.swing.JButton();
        btnThemKH1 = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jScrollPane1 = new javax.swing.JScrollPane();
        TBServiceRent = new javax.swing.JTable();

        txtloaiphong.setEnabled(false);

        roundPanel5.setBackground(new java.awt.Color(36, 87, 157));
        roundPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Thông tin thuê dịch vụ", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 24), new java.awt.Color(255, 255, 255))); // NOI18N

        btnEdit.setText("Sửa");
        btnEdit.setEnabled(false);
        btnEdit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditActionPerformed(evt);
            }
        });

        btnAdd.setText("Thêm");
        btnAdd.setEnabled(false);
        btnAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddActionPerformed(evt);
            }
        });

        btnDelete.setText("Xóa");
        btnDelete.setEnabled(false);
        btnDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteActionPerformed(evt);
            }
        });

        btnRefresh.setText("Làm mới");
        btnRefresh.setEnabled(false);
        btnRefresh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRefreshActionPerformed(evt);
            }
        });

        jLabel11.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(255, 255, 255));
        jLabel11.setText("Mã dịch vụ:");

        cmbmadichvu.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cmbmadichvuItemStateChanged(evt);
            }
        });

        jLabel22.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        jLabel22.setForeground(new java.awt.Color(255, 255, 255));
        jLabel22.setText("Mã khách hàng:");

        jLabel19.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        jLabel19.setForeground(new java.awt.Color(255, 255, 255));
        jLabel19.setText("Ngày lập hóa đơn:");

        cmbmakhachhang.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cmbmakhachhangItemStateChanged(evt);
            }
        });

        btnPay.setBackground(new java.awt.Color(255, 99, 76));
        btnPay.setText("Tính tiền");
        btnPay.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPayActionPerformed(evt);
            }
        });

        btnReport.setBackground(new java.awt.Color(255, 99, 76));
        btnReport.setText("In Report");
        btnReport.setEnabled(false);
        btnReport.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnReportActionPerformed(evt);
            }
        });

        txtTenDichVu.setEditable(false);
        txtTenDichVu.setLabelText("Tên dịch vụ");

        txtTenNhanVien.setEditable(false);
        txtTenNhanVien.setLabelText("Tên nhân viên");

        spSL.setLabelText("Số lượng");

        txtTenKH.setEditable(false);
        txtTenKH.setLabelText("Tên khách hàng");

        txtGiaDichvu.setEditable(false);
        txtGiaDichvu.setLabelText("Giá dịch vụ");

        txtGiahd.setEditable(false);
        txtGiahd.setLabelText("Giá hóa đơn");

        txtVAT.setEditable(false);
        txtVAT.setLabelText("Giá VAT");
        txtVAT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtVATActionPerformed(evt);
            }
        });

        txtGiamGia.setEditable(false);
        txtGiamGia.setLabelText("Giảm giá");
        txtGiamGia.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtGiamGiaActionPerformed(evt);
            }
        });

        txtMaNV.setEditable(false);
        txtMaNV.setLabelText("Mã nhân viên");
        txtMaNV.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                txtMaNVPropertyChange(evt);
            }
        });

        btnThemKH.setBackground(new java.awt.Color(255, 99, 76));
        btnThemKH.setText("Thêm khách hàng");
        btnThemKH.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemKHActionPerformed(evt);
            }
        });

        btnThemKH1.setBackground(new java.awt.Color(255, 99, 76));
        btnThemKH1.setText("Xem dịch vụ");
        btnThemKH1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemKH1ActionPerformed(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 51, 0));
        jLabel4.setText("*");

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 51, 0));
        jLabel5.setText("*");

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 51, 0));
        jLabel6.setText("*");

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 51, 0));
        jLabel7.setText("*");

        javax.swing.GroupLayout roundPanel5Layout = new javax.swing.GroupLayout(roundPanel5);
        roundPanel5.setLayout(roundPanel5Layout);
        roundPanel5Layout.setHorizontalGroup(
            roundPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(roundPanel5Layout.createSequentialGroup()
                .addGroup(roundPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(roundPanel5Layout.createSequentialGroup()
                        .addGap(306, 306, 306)
                        .addComponent(btnAdd, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnEdit, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(roundPanel5Layout.createSequentialGroup()
                        .addGap(50, 50, 50)
                        .addComponent(jLabel7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(roundPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtTenNhanVien, javax.swing.GroupLayout.DEFAULT_SIZE, 401, Short.MAX_VALUE)
                            .addComponent(txtMaNV, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(spSL, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(roundPanel5Layout.createSequentialGroup()
                        .addGap(48, 48, 48)
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(roundPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, roundPanel5Layout.createSequentialGroup()
                                .addComponent(txtTenDichVu, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnThemKH1))
                            .addGroup(roundPanel5Layout.createSequentialGroup()
                                .addComponent(jLabel11)
                                .addGap(35, 35, 35)
                                .addComponent(cmbmadichvu, javax.swing.GroupLayout.PREFERRED_SIZE, 274, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addGap(18, 18, 18)
                .addGroup(roundPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, roundPanel5Layout.createSequentialGroup()
                        .addGroup(roundPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(roundPanel5Layout.createSequentialGroup()
                                .addComponent(jLabel5)
                                .addGap(7, 7, 7))
                            .addGroup(roundPanel5Layout.createSequentialGroup()
                                .addComponent(jLabel6)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                        .addGroup(roundPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, roundPanel5Layout.createSequentialGroup()
                                .addComponent(jLabel22)
                                .addGap(26, 26, 26)
                                .addComponent(cmbmakhachhang, javax.swing.GroupLayout.PREFERRED_SIZE, 273, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(6, 6, 6))
                            .addGroup(roundPanel5Layout.createSequentialGroup()
                                .addGroup(roundPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtVAT, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtGiamGia, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(26, 26, 26)
                                .addGroup(roundPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(txtGiaDichvu, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(txtGiahd, javax.swing.GroupLayout.DEFAULT_SIZE, 254, Short.MAX_VALUE)))
                            .addGroup(roundPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addGroup(roundPanel5Layout.createSequentialGroup()
                                    .addComponent(txtTenKH, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(btnThemKH))
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, roundPanel5Layout.createSequentialGroup()
                                    .addComponent(jLabel19)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(jDateChooserngaylaphoadon, javax.swing.GroupLayout.PREFERRED_SIZE, 270, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                    .addGroup(roundPanel5Layout.createSequentialGroup()
                        .addComponent(btnDelete, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(32, 32, 32)
                        .addComponent(btnRefresh, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(40, 40, 40)
                        .addComponent(btnPay, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnReport, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(49, Short.MAX_VALUE))
        );
        roundPanel5Layout.setVerticalGroup(
            roundPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(roundPanel5Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(roundPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(roundPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(cmbmakhachhang, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel22)
                        .addComponent(jLabel5))
                    .addGroup(roundPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(cmbmadichvu, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel11)
                        .addComponent(jLabel4)))
                .addGap(18, 18, 18)
                .addGroup(roundPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(roundPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtTenDichVu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtTenKH, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(btnThemKH, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnThemKH1, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(roundPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(roundPanel5Layout.createSequentialGroup()
                        .addGroup(roundPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jDateChooserngaylaphoadon, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtMaNV, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, roundPanel5Layout.createSequentialGroup()
                        .addGroup(roundPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel19)
                            .addComponent(jLabel6))
                        .addGap(28, 28, 28)))
                .addGroup(roundPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtTenNhanVien, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtGiaDichvu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtGiamGia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(roundPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(roundPanel5Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(roundPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnAdd, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnEdit, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnDelete, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnRefresh, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnPay, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnReport, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap())
                    .addGroup(roundPanel5Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(roundPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(spSL, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtGiahd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtVAT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel7))
                        .addContainerGap(80, Short.MAX_VALUE))))
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

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
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
                .addGap(18, 18, 18)
                .addComponent(jLabel8)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 12, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 321, Short.MAX_VALUE)
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
        String MaNV = txtMaNV.getText();
   
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
        
        // Lấy giá trị hiện tại của JSpinner
        Object selectedValue = spSL.getValue();
        
        int spinnerValue = (int) selectedValue;
        if (spinnerValue > 0){
            try {
                Date selectedDate = jDateChooserngaylaphoadon.getDate();
                Calendar calendar = Calendar.getInstance();
                calendar.setTime(selectedDate);

                int year = calendar.get(Calendar.YEAR);
                int month = calendar.get(Calendar.MONTH) + 1; // Tháng bắt đầu từ 0, nên cộng thêm 1 để có giá trị tháng thực
                int day = calendar.get(Calendar.DAY_OF_MONTH);
                String ngayhd = year + "-" + month + "-" + day;

                String MaDichvu = cmbmadichvu.getSelectedItem().toString();

                String MaNV = txtMaNV.getText();
                String MaKH = cmbmakhachhang.getSelectedItem().toString();
                
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

    private void btnDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteActionPerformed
        // TODO add your handling code here:

        String MaDichvu = cmbmadichvu.getSelectedItem().toString();
        String MaNV = txtMaNV.getText();
       
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
        

    }//GEN-LAST:event_btnDeleteActionPerformed

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
                txtGiamGia.setText(ttp.getFormattedPercentage());

            } else {
                JOptionPane.showMessageDialog(this, "Không tim thấy dịch vụ");
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "error " + e.getMessage());
            e.printStackTrace();
        }
    }//GEN-LAST:event_cmbmadichvuItemStateChanged

    private void cmbmakhachhangItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cmbmakhachhangItemStateChanged
        // TODO add your handling code here:
        String MaKH = cmbmakhachhang.getSelectedItem().toString();
        try {
            ModelServiceRent ql = new ModelServiceRent();

            ModelServiceRentv2 ttp = ql.findByID_MAKH(MaKH);
            if (ttp != null) {

                txtTenKH.setText(ttp.getHoKH()+" "+ ttp.getTenKH());

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

//            String manv = TBServiceRent.getValueAt(row, 2).toString();
//            System.out.println("" + manv);
//            cmbMaNhanvien.setSelectedItem(manv);
            txtMaNV.setText(TBServiceRent.getValueAt(row, 2).toString());
            
            txtTenNhanVien.setText(TBServiceRent.getValueAt(row, 3).toString());
            
            String makh = TBServiceRent.getValueAt(row, 4).toString();
            System.out.println("" + makh);
            cmbmakhachhang.setSelectedItem(makh);
            
            txtTenKH.setText(TBServiceRent.getValueAt(row, 5).toString());

            String ngaylaphd = TBServiceRent.getValueAt(row, 6).toString();
            System.out.println(ngaylaphd);
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
            
            txtVAT.setText(TBServiceRent.getValueAt(row, 9).toString());
            txtGiahd.setText(TBServiceRent.getValueAt(row, 10).toString());
            

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
        
        
        String giamgia = txtGiamGia.getText();
        String giamgianotpercent = giamgia.replace("%", "");
        int giam = Integer.parseInt(giamgianotpercent); 
        
        try {
            Number giaNumber = decimalFormat.parse(tmp);
            // Lấy giá trị số từ đối tượng Number
            Float gia = giaNumber.floatValue();
            
            System.out.println(gia);
            
            Float tmp2;
            
            if (spinnerValue > 0){
                
                btnAdd.setEnabled(true);
                btnEdit.setEnabled(true);
                btnDelete.setEnabled(true);
                btnRefresh.setEnabled(true);
                btnReport.setEnabled(true);
                
                System.out.println("\nkq test");
                float giacb = (float) ((float)spinnerValue * gia);
                float vat = (float) (((float)spinnerValue * gia )* 0.1);
                float dis = (float) (giacb*giam/100) ;
//                ((DonGia*SL) +  ((DonGia*SL)*0.1) - ((DonGia * SL)*GiamGia/100))
                float kq =(float) (giacb + vat - dis);
                System.out.println("gia" + giacb);
                System.out.println("vat"+ vat);
                System.out.println("dis" + dis);
                System.out.println("kq "+ kq);
                
                BigDecimal giahd = BigDecimal.valueOf(kq);
                BigDecimal giavat = BigDecimal.valueOf(vat);
                
                ModelServiceRentv2 sr = new ModelServiceRentv2();
                sr.setGiaHD(giahd);
                sr.setVAT(giavat);
                String formattedhoadon = sr.getFormattedGiahd();
                String formattedvat = sr.getFormattedVAT();
                txtGiahd.setText(formattedhoadon);
                txtVAT.setText(formattedvat);
            }else {
                JOptionPane.showMessageDialog(this, "Vui lòng chọn số lượng dịch vụ lớn hơn 0");
            }
            
            
        } catch (ParseException ex) {
            Logger.getLogger(FormServiceRent.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnPayActionPerformed

    private void btnReportActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnReportActionPerformed
        // TODO add your handling code here:
        String MaDichvu = cmbmadichvu.getSelectedItem().toString();
        String MaNV = txtMaNV.getText();
//        String MaNV1 = cmbMaNhanvien.getSelectedItem().toString();
        String MaKH = cmbmakhachhang.getSelectedItem().toString();
        
        if (MaDichvu.equals("")==true || MaNV.equals("")==true || MaKH.equals("")==true) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn mã phiếu dịch vụ mã nhân viên và mã khách hàng");
        } else {

            //All Files	D:\TranDuc\java\nhom4_qlksnewworld_5804_Ct6\src\nhom4_qlksnewworld_5804_ct6\newReport.jrxml
            try {//All Files	D:\TranDuc\java\nhom4_qlksnewworld_5804_Ct6\src\report\rptphieuthuephong.jrxml
//                Hashtable map = new Hashtable();
                Map<String, Object> map = new HashMap<>();
                JasperReport rpt = JasperCompileManager.compileReport("src/report/rpServiceRent.jrxml");
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

    private void txtVATActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtVATActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtVATActionPerformed

    private void txtGiamGiaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtGiamGiaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtGiamGiaActionPerformed

    private void txtMaNVPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_txtMaNVPropertyChange
        // TODO add your handling code here:
        String MaNV = txtMaNV.getText();
        try {
            ModelStaff ql = new ModelStaff();

            model.ModelStaffv2 ttp = ql.findByID(MaNV);
            if (ttp != null) {
                txtTenNhanVien.setText(ttp.getHoNV() + " "+ ttp.getTenNV());

            } else {
                JOptionPane.showMessageDialog(this, "Không tim thấy mã nhân viên");
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "error " + e.getMessage());
            e.printStackTrace();
        }
    }//GEN-LAST:event_txtMaNVPropertyChange

    private void btnThemKHActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemKHActionPerformed
        // TODO add your handling code here:
        FormaddKH l = new FormaddKH(username, password, DisplayName,quyen);
        l.setVisible(true);
        l.setLocationRelativeTo(null);
    }//GEN-LAST:event_btnThemKHActionPerformed

    private void btnThemKH1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemKH1ActionPerformed
        // TODO add your handling code here:
        FormViewService l = new FormViewService(username, password, DisplayName,quyen);
        l.setVisible(true);
        l.setLocationRelativeTo(null);
    }//GEN-LAST:event_btnThemKH1ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable TBServiceRent;
    private swing.Button btnAdd;
    private swing.Button btnDelete;
    private swing.Button btnEdit;
    private javax.swing.JButton btnPay;
    private swing.Button btnRefresh;
    private javax.swing.JButton btnReport;
    private javax.swing.JButton btnThemKH;
    private javax.swing.JButton btnThemKH1;
    private javax.swing.JComboBox<String> cmbmadichvu;
    private javax.swing.JComboBox<String> cmbmakhachhang;
    private com.toedter.calendar.JDateChooser jDateChooserngaylaphoadon;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private swing.RoundPanel roundPanel5;
    private component.Spinner spSL;
    private component.TextField txtGiaDichvu;
    private component.TextField txtGiahd;
    private component.TextField txtGiamGia;
    private component.TextField txtMaNV;
    private component.TextField txtTenDichVu;
    private component.TextField txtTenKH;
    private component.TextField txtTenNhanVien;
    private component.TextField txtVAT;
    private javax.swing.JTextField txtloaiphong;
    // End of variables declaration//GEN-END:variables
}
