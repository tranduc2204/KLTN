/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package form;

import com.sun.corba.se.impl.protocol.giopmsgheaders.Message;
import connect.Connect;
import java.awt.Color;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.Icon;
import javax.swing.JOptionPane;
import main.MainHome;
import model.ModelCard;
import swing.icon.GoogleMaterialDesignIcons;
import swing.icon.IconFontSwing;
import swing.noticeboard.ModelNoticeBoard;

/**
 *
 * @author TeeDee
 */
public class FormHome extends javax.swing.JPanel {

    Connect cn = new Connect();
    Connection conn;
    /**
     * Creates new form Form_Home
     */
    public FormHome() {
        initComponents();
        setOpaque(false);
        initData();
    }

    private void initData() {
        initCardData();
        initNoticeBoard();
//        initTableData();
    }
    
    private void initCardData() {
        int sl;
        try{
            conn = cn.getConnection();
            String sql_login = "select count (*) as SL from KHACHHANG";
            PreparedStatement pst = conn.prepareStatement(sql_login);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {;
                sl = rs.getInt("SL");

                Icon icon1 = IconFontSwing.buildIcon(GoogleMaterialDesignIcons.PEOPLE, 60, new Color(255, 255, 255, 100), new Color(255, 255, 255, 15));
                card1.setData(new ModelCard("Khách hàng", sl, 100, icon1));// 5100 là tổng 

            } else {
                JOptionPane.showConfirmDialog(this, "Đăng nhập thất bai, sai tài khoản hoặc mật khẩu");

            }
        }catch(Exception e){
            
        }
        
        int doanhthu;
        try{
            conn = cn.getConnection();
            String sql_login = "select sum(DonGia) as doanhthu from HoaDonPhong hdp \n" +
"join PhieuDatPhong pdp on pdp.MaPhieuDatPhong = hdp.MaPhieuDatPhong join PHONG p on p.MaPhong = pdp.MaPhong \n" +
"join DonGiaPhong dgp on dgp.MaDonGiaPhong = p.MaDonGiaPhong\n" +
"where month(NgayLapHoaDon) =  MONTH(GETDATE()) and year(NgayLapHoaDon) = Year(GETDATE())";
            PreparedStatement pst = conn.prepareStatement(sql_login);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {

                doanhthu = rs.getInt("doanhthu");
                int target = doanhthu;
                int kq = 30000000;
                float nopercent =(float)((float) target / (float) kq);
                float percent =(float) nopercent * 100;
                int kqq = (int)percent;
                Icon icon2 = IconFontSwing.buildIcon(GoogleMaterialDesignIcons.MONETIZATION_ON, 60, new Color(255, 255, 255, 100), new Color(255, 255, 255, 15));
                card2.setData(new ModelCard("Doanh thu phòng", doanhthu,kqq , icon2));//2000 60
            } else {
                JOptionPane.showConfirmDialog(this, "");

            }
        }catch(Exception e){
            
        }
        
        int doanhthu_service;
        try{
            conn = cn.getConnection();
            String sql_login = "select sum(SL* DonGia) as GiaHD from HoaDonDV hddv join DICHVU dv on hddv.MaDV = dv.MaDV \n" +
"join DonGiaDV dgdv on dv.MaDonGiaDV = dgdv.MaDonGiaDV\n" +
"where month(NgayLapHD) =  MONTH(GETDATE()) and year(NgayLapHD) = Year(GETDATE())";
            PreparedStatement pst = conn.prepareStatement(sql_login);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {;
                doanhthu_service = rs.getInt("GiaHD");
                
                int target = doanhthu_service;
                int kq = 15000000;
                float nopercent =(float)((float) target / (float) kq);
                float percent =(float) nopercent * 100;
                int kqq = (int)percent;
                
                Icon icon3 = IconFontSwing.buildIcon(GoogleMaterialDesignIcons.SHOPPING_BASKET, 60, new Color(255, 255, 255, 100), new Color(255, 255, 255, 15));
                card3.setData(new ModelCard("Doanh thu dịch vụ", doanhthu_service, kqq, icon3)); //3000

            } else {
                JOptionPane.showConfirmDialog(this, "");

            }
        }catch(Exception e){
            
        }
        
        int countStaff;
        try{
            conn = cn.getConnection();
            String sql_login = "select count (*) as SL from Nhanvien";
            PreparedStatement pst = conn.prepareStatement(sql_login);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {;
                sl = rs.getInt("SL");

                Icon icon4 = IconFontSwing.buildIcon(GoogleMaterialDesignIcons.BUSINESS_CENTER, 60, new Color(255, 255, 255, 100), new Color(255, 255, 255, 15));
                card4.setData(new ModelCard("Nhân viên", sl, 100, icon4)); //550
            } else {
                JOptionPane.showConfirmDialog(this, "");

            }
        }catch(Exception e){
            
        }
        
   
    }
    
    private void initNoticeBoard() {
        noticeBoard.addDate("24/12/2023");
        noticeBoard.addNoticeBoard(new ModelNoticeBoard(new Color(94, 49, 238), "Nghỉ lễ", "Now", "Ngày 31/12/2023 - 01/01/2024 nghỉ tết dương lịch."));
        noticeBoard.addDate("11/11/2023");
        noticeBoard.addNoticeBoard(new ModelNoticeBoard(new Color(218, 49, 238), "Team building", "9:00 AM", "29/11/2023 - 05/12/2023 Team building tại Đà Lạt."));
        noticeBoard.addDate("21/10/2023");
        noticeBoard.addNoticeBoard(new ModelNoticeBoard(new Color(27, 188, 204), "Kiểm tra ISO", "9:00 AM", "Ngày 16/11/2023 kiểm tra ISO định kỳ."));
        noticeBoard.addNoticeBoard(new ModelNoticeBoard(new Color(238, 46, 57), "Cập nhật hệ thống", "17:15 AM", "Ngày 25/10/2023 cập nhật phiên bản mới cho toàn bộ hệ thống khách sạn."));
        noticeBoard.addDate("7/10/2023");
        noticeBoard.addNoticeBoard(new ModelNoticeBoard(new Color(94, 49, 238), "Ngày phụ nữ Việt Nam", "09:15 AM", "Ngày 20/10/2023 liên hoan khách sạn, nhân ngày phụ nữ Việt Nam."));
        noticeBoard.scrollToTop();
    }

//    private boolean showMessage(String message) {
//        Message obj = new Message(MainHome.getFrames()[0], true);
//        obj.showMessage(message);
//        return obj.isOk();
//    }
    
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jProgressBar1 = new javax.swing.JProgressBar();
        card1 = new component.Card();
        jLabel1 = new javax.swing.JLabel();
        card3 = new component.Card();
        card4 = new component.Card();
        card2 = new component.Card();
        jPanel1 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        noticeBoard = new swing.noticeboard.NoticeBoard();
        jPanel2 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();

        setBackground(new java.awt.Color(255, 255, 255));

        card1.setBackground(new java.awt.Color(112, 67, 205));
        card1.setColorGradient(new java.awt.Color(255, 59, 108));
        card1.setPreferredSize(new java.awt.Dimension(235, 119));

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(102, 102, 255));
        jLabel1.setText("DashBoard");

        card3.setBackground(new java.awt.Color(112, 0, 255));
        card3.setColorGradient(new java.awt.Color(255, 132, 39));
        card3.setPreferredSize(new java.awt.Dimension(260, 119));

        card4.setBackground(new java.awt.Color(0, 48, 215));
        card4.setColorGradient(new java.awt.Color(255, 177, 16));
        card4.setPreferredSize(new java.awt.Dimension(235, 119));

        card2.setBackground(new java.awt.Color(100, 87, 94));
        card2.setColorGradient(new java.awt.Color(113, 117, 61));
        card2.setPreferredSize(new java.awt.Dimension(245, 119));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jLabel3.setFont(new java.awt.Font("sansserif", 1, 12)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(105, 105, 105));
        jLabel3.setText("New World Hotel");
        jLabel3.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 10, 1, 1));

        jLabel2.setFont(new java.awt.Font("sansserif", 1, 15)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(76, 76, 76));
        jLabel2.setText("Bảng thông tin");
        jLabel2.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 10, 1, 1));

        jLabel4.setOpaque(true);

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 135, Short.MAX_VALUE)
        );

        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/res/New_world.png"))); // NOI18N

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(noticeBoard, javax.swing.GroupLayout.PREFERRED_SIZE, 629, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(58, 58, 58)
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(jLabel3))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(1, 1, 1)
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 7, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(26, 26, 26)
                                .addComponent(noticeBoard, javax.swing.GroupLayout.DEFAULT_SIZE, 408, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(64, 64, 64)))
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 234, Short.MAX_VALUE)
                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(130, 130, 130))))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel1))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addComponent(card1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(25, 25, 25)
                        .addComponent(card2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(25, 25, 25)
                        .addComponent(card3, javax.swing.GroupLayout.PREFERRED_SIZE, 255, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(25, 25, 25)
                .addComponent(card4, javax.swing.GroupLayout.PREFERRED_SIZE, 233, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(card2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(card1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(card3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(card4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private component.Card card1;
    private component.Card card2;
    private component.Card card3;
    private component.Card card4;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JProgressBar jProgressBar1;
    private swing.noticeboard.NoticeBoard noticeBoard;
    // End of variables declaration//GEN-END:variables
}
