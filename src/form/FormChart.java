/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package form;

import chart.ModelChart;
import connect.Connect;
import java.awt.Color;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.Icon;
import javax.swing.JOptionPane;
import model.ModelCard;
import swing.icon.GoogleMaterialDesignIcons;
import swing.icon.IconFontSwing;

/**
 *
 * @author TeeDee
 */
public class FormChart extends javax.swing.JPanel {

    Connect cn = new Connect();
    Connection conn;
    /**
     * Creates new form FormChart
     */
    public FormChart() {
        initComponents();
        setOpaque(false);
        init();
    }
    private void init(){
        
        // data progress
        int total_room;
        try{
            conn = cn.getConnection();
            String sql_login = "select count (*) as sophong from phong";
            PreparedStatement pst = conn.prepareStatement(sql_login);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                total_room = rs.getInt("sophong");

                int Booked_room;
                String sql1 = "select count (*) as sophong from phong where MaTinhtrangphong = 'TT2' ";        
                PreparedStatement pst1 = conn.prepareStatement(sql1);
                ResultSet rs1 = pst1.executeQuery();
                if (rs1.next()) {
                    Booked_room = rs1.getInt("sophong");
                    double kq = (double)((double)Booked_room /(double) total_room)*100 ;
                    double kqend = Math.ceil(kq); //làm tròn lên
                    int view = (int)kqend;
                   
                    progress1.setValue(view);
                    progress1.start();
                }
                
                
                int Rented_room;
                String sql2 = "select count (*) as sophong from phong where MaTinhtrangphong = 'TT1' ";        
                PreparedStatement pst2 = conn.prepareStatement(sql2);
                ResultSet rs2 = pst2.executeQuery();
                if (rs2.next()) {
                    Rented_room = rs2.getInt("sophong");
                    double kq1 = (double)((double)Rented_room /(double) total_room)*100 ;
                    double kqend1 = Math.ceil(kq1);//làm tròn lên
                    int view1 = (int)kqend1;
                    progress2.setValue(view1);
                    progress2.start();
                }
            } else {
                JOptionPane.showConfirmDialog(this, "");
            }
        }catch(Exception e){
            
        }
        
        try{
            conn = cn.getConnection();
            String sql = "select month (NgayLapHoaDon) as Thang , sum(((DATEDIFF(DAY, NgayDuKienThue, NgayDuKienTra)) *DonGia)  +  (((DATEDIFF(DAY, NgayDuKienThue, NgayDuKienTra)) *DonGia) * 0.1) - (((DATEDIFF(DAY, NgayDuKienThue, NgayDuKienTra)) *DonGia) *GiamGia /100)) AS GiaHD\n" +
"from HoaDonPhong hdp \n" +
"join PhieuDatPhong pdp on pdp.MaPhieuDatPhong = hdp.MaPhieuDatPhong join PHONG p on p.MaPhong = pdp.MaPhong \n" +
"join DonGiaPhong dgp on dgp.MaDonGiaPhong = p.MaDonGiaPhong\n" +
"where year(NgayLapHoaDon) = Year(GETDATE())\n" +
"group by month(NgayLapHoaDon) ";
            PreparedStatement pst = conn.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            
            // Tạo một mảng 12 phần tử để lưu giá trị của 12 tháng
            double[] giaHDByMonth = new double[12];
            while (rs.next()) {
                // Lấy giá trị từ cột Thang và GiaHD trong kết quả
                int month = rs.getInt("Thang");
                double giaHD = rs.getDouble("GiaHD");

                // Lưu giá trị vào mảng (chú ý: tháng bắt đầu từ 1, không phải 0)
                giaHDByMonth[month - 1] = giaHD;
            }
            
            String sql1 = "select month(NgayLapHD) as Thang1, sum((DonGia*SL) +  ((DonGia*SL)*0.1) - ((DonGia * SL)*GiamGia/100)) AS GiaHD1 \n" +
"from HoaDonDV hddv join NHANVIEN nv on hddv.MaNV = nv.MaNV join KHACHHANG kh on hddv.MaKH = kh.MaKH \n" +
"join DICHVU dv on dv.MaDV = hddv.MaDV join dongiadv dgdv on dv.MaDonGiaDV = dgdv.MaDonGiaDV\n" +
"where year(NgayLapHD) = Year(GETDATE())\n" +
"group by month(NgayLapHD)  ";
            PreparedStatement pst1 = conn.prepareStatement(sql1);
            ResultSet rs1 = pst1.executeQuery();
            // Tạo một mảng 12 phần tử để lưu giá trị của 12 tháng
            double[] giaHDDVByMonth = new double[12];
            while (rs1.next()) {
                // Lấy giá trị từ cột Thang và GiaHD trong kết quả
                int month = rs1.getInt("Thang1");
                double giaHD = rs1.getDouble("GiaHD1");
                // Lưu giá trị vào mảng (chú ý: tháng bắt đầu từ 1, không phải 0)
                giaHDDVByMonth[month - 1] = giaHD;
            }
            //room
            double thang1 = giaHDByMonth[0];
            double thang2 = giaHDByMonth[1];
            double thang3 = giaHDByMonth[2];
            double thang4 = giaHDByMonth[3];
            double thang5 = giaHDByMonth[4];
            double thang6 = giaHDByMonth[5];
            double thang7 = giaHDByMonth[6];
            double thang8 = giaHDByMonth[7];
            double thang9 = giaHDByMonth[8];
            double thang10 = giaHDByMonth[9];
            double thang11 = giaHDByMonth[10];
            double thang12 = giaHDByMonth[11];
            
            
            //service
            double thang1dv = giaHDDVByMonth[0];
            double thang2dv = giaHDDVByMonth[1];
            double thang3dv = giaHDDVByMonth[2];
            double thang4dv = giaHDDVByMonth[3];
            double thang5dv = giaHDDVByMonth[4];
            double thang6dv = giaHDDVByMonth[5];
            double thang7dv = giaHDDVByMonth[6];
            double thang8dv = giaHDDVByMonth[7];
            double thang9dv = giaHDDVByMonth[8];
            double thang10dv = giaHDDVByMonth[9];
            double thang11dv = giaHDDVByMonth[10];
            double thang12dv = giaHDDVByMonth[11];
            
//            chart.addData(new ModelChart("January", new double[]{thang1, 200}));

             // chart dưới
            chart.addLegend("Room Income", new Color(5, 125, 0), new Color(95, 209, 69));
            chart.addLegend("Service Income", new Color(186, 37, 37), new Color(241, 100, 120));
            chart.addData(new ModelChart("January", new double[]{thang1, thang1dv}));
            chart.addData(new ModelChart("February", new double[]{thang2, thang2dv}));
            chart.addData(new ModelChart("March", new double[]{thang3, thang3dv}));
            chart.addData(new ModelChart("April", new double[]{thang4, thang4dv}));
            chart.addData(new ModelChart("May", new double[]{thang5, thang5dv}));
            chart.addData(new ModelChart("June", new double[]{thang6, thang6dv}));
            chart.addData(new ModelChart("July", new double[]{thang7, thang7dv}));
            chart.addData(new ModelChart("August", new double[]{thang8, thang8dv}));
            chart.addData(new ModelChart("September", new double[]{thang9, thang9dv}));
            chart.addData(new ModelChart("October", new double[]{thang10, thang10dv}));
            chart.addData(new ModelChart("November", new double[]{thang11, thang11dv}));
            chart.addData(new ModelChart("December", new double[]{thang12, thang12dv}));
            chart.start();
            
            
            lineChart.addLegend("Room Income", new Color(5, 125, 0), new Color(95, 209, 69));
            lineChart.addLegend("Service Income", new Color(186, 37, 37), new Color(241, 100, 120));
            lineChart.addData(new ModelChart("January", new double[]{thang1, thang1dv}));
            lineChart.addData(new ModelChart("February", new double[]{thang2, thang2dv}));
            lineChart.addData(new ModelChart("March", new double[]{thang3, thang3dv}));
            lineChart.addData(new ModelChart("April", new double[]{thang4, thang4dv}));
            lineChart.addData(new ModelChart("May", new double[]{thang5, thang5dv}));
            lineChart.addData(new ModelChart("June", new double[]{thang6, thang6dv}));
            lineChart.addData(new ModelChart("July", new double[]{thang7, thang7dv}));
            lineChart.addData(new ModelChart("August", new double[]{thang8, thang8dv}));
            lineChart.addData(new ModelChart("September", new double[]{thang9, thang9dv}));
            lineChart.addData(new ModelChart("October", new double[]{thang10, thang10dv}));
            lineChart.addData(new ModelChart("November", new double[]{thang11, thang11dv}));
            lineChart.addData(new ModelChart("December", new double[]{thang12, thang12dv}));
            lineChart.start();
            

        }catch(Exception e){
            JOptionPane.showMessageDialog(this, "");
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

        roundPanel1 = new swing.RoundPanel();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        progress1 = new swing.Progress();
        jLabel2 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        progress2 = new swing.Progress();
        jLabel3 = new javax.swing.JLabel();
        roundPanel2 = new swing.RoundPanel();
        chart = new chart.Chart();
        roundPanel3 = new swing.RoundPanel();
        lineChart = new chart.LineChart();

        setForeground(new java.awt.Color(255, 255, 255));

        roundPanel1.setBackground(new java.awt.Color(51, 51, 51));

        jPanel1.setOpaque(false);

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Đang được sử dụng");

        progress1.setBackground(new java.awt.Color(66, 246, 84));
        progress1.setForeground(new java.awt.Color(19, 153, 32));
        progress1.setToolTipText("");
        progress1.setValue(60);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(progress1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 157, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(12, Short.MAX_VALUE)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(progress1, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 17)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(204, 204, 204));
        jLabel2.setText("Report Chart");
        jLabel2.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 10, 1, 1));

        jPanel2.setOpaque(false);

        progress2.setBackground(new java.awt.Color(132, 66, 246));
        progress2.setForeground(new java.awt.Color(64, 18, 153));
        progress2.setValue(70);

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("Bảo trì");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(progress2, javax.swing.GroupLayout.DEFAULT_SIZE, 157, Short.MAX_VALUE)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(55, 55, 55)
                .addComponent(jLabel3)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 7, Short.MAX_VALUE)
                .addComponent(progress2, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        javax.swing.GroupLayout roundPanel1Layout = new javax.swing.GroupLayout(roundPanel1);
        roundPanel1.setLayout(roundPanel1Layout);
        roundPanel1Layout.setHorizontalGroup(
            roundPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(roundPanel1Layout.createSequentialGroup()
                .addGroup(roundPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(roundPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel2))
                    .addGroup(roundPanel1Layout.createSequentialGroup()
                        .addGap(38, 38, 38)
                        .addGroup(roundPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(38, Short.MAX_VALUE))
        );
        roundPanel1Layout.setVerticalGroup(
            roundPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(roundPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        roundPanel2.setBackground(new java.awt.Color(51, 51, 51));

        chart.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout roundPanel2Layout = new javax.swing.GroupLayout(roundPanel2);
        roundPanel2.setLayout(roundPanel2Layout);
        roundPanel2Layout.setHorizontalGroup(
            roundPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(chart, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        roundPanel2Layout.setVerticalGroup(
            roundPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, roundPanel2Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(chart, javax.swing.GroupLayout.PREFERRED_SIZE, 405, Short.MAX_VALUE))
        );

        roundPanel3.setBackground(new java.awt.Color(51, 51, 51));

        javax.swing.GroupLayout roundPanel3Layout = new javax.swing.GroupLayout(roundPanel3);
        roundPanel3.setLayout(roundPanel3Layout);
        roundPanel3Layout.setHorizontalGroup(
            roundPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(roundPanel3Layout.createSequentialGroup()
                .addComponent(lineChart, javax.swing.GroupLayout.DEFAULT_SIZE, 859, Short.MAX_VALUE)
                .addContainerGap())
        );
        roundPanel3Layout.setVerticalGroup(
            roundPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(roundPanel3Layout.createSequentialGroup()
                .addComponent(lineChart, javax.swing.GroupLayout.DEFAULT_SIZE, 391, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(roundPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(roundPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(roundPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGap(0, 0, 0))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(roundPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(roundPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(roundPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private chart.Chart chart;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private chart.LineChart lineChart;
    private swing.Progress progress1;
    private swing.Progress progress2;
    private swing.RoundPanel roundPanel1;
    private swing.RoundPanel roundPanel2;
    private swing.RoundPanel roundPanel3;
    // End of variables declaration//GEN-END:variables
}
