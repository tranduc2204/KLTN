/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package form;

import connect.Connect;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.prefs.Preferences;
import javax.swing.JOptionPane;
import main.MainHome;
import org.mindrot.jbcrypt.BCrypt;

/**
 *
 * @author TeeDee
 */
public class FormLogin extends javax.swing.JFrame {

    /**
     * Creates new form FormLogin
     */
    
    Connect cn = new Connect();
    Connection conn;
    
    public FormLogin() {
        initComponents();
        
        loadSavedLoginInfo();
    }
    
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_ENTER) {
            System.out.println("");
        }
    }

    public static boolean checkPassword(String candidatePassword, String hashedPassword) {
        // Kiểm tra mật khẩu nhập vào có khớp với mật khẩu đã lưu không
        return BCrypt.checkpw(candidatePassword, hashedPassword);
    }
    
    private void login() {

        conn = cn.getConnection();

        String UserName = txtTENDN.getText().trim();
        String PassWorrd = passwordField2.getText().trim();

        String quyen, ten;

        StringBuffer sb = new StringBuffer();

        if (UserName.equals("")) {
            sb.append("tài khoản không được để trống: \n");
        }
        if (PassWorrd.equals("")) {
            sb.append("mật khẩu không được để trống\n");
        }
        if (sb.length() > 0) {
            JOptionPane.showMessageDialog(this, sb.toString());
            return;
        }
        String sql_login = "select * from  Account where UserName = ? ";

        try {
            PreparedStatement pst = conn.prepareStatement(sql_login);
            pst.setString(1, UserName);
//            pst.setString(2, PassWorrd);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                String hashedPasswordInDatabase = rs.getString("PassWorrd");
                if (PasswordHashingExample.checkPassword(PassWorrd, hashedPasswordInDatabase)) {
                    ten = rs.getString("DisplayName");
                    quyen = rs.getString("Typpe");
                    if (ckLuuMK.isSelected() == true){
                        // lưu thông tin đăng nhập
                        saveLoginInfo(UserName, PassWorrd);
                    }
                    

                    MainHome l = new MainHome(UserName, PassWorrd, ten, quyen);
                    l.setVisible(true);
                    l.setLocationRelativeTo(null);
                    this.setVisible(false);
                }else {
                    JOptionPane.showMessageDialog(this, "Đăng nhập thất bại. Sai mật khẩu.");

                }
            } else {
                JOptionPane.showConfirmDialog(this, "Đăng nhập thất bai, sai tài khoản hoặc mật khẩu");

            }
        } catch (Exception e) {
        }
    }
    
    private void saveLoginInfo(String username, String password) {
        // Lưu thông tin đăng nhập vào Java Preferences API
        Preferences prefs = Preferences.userNodeForPackage(getClass());
        prefs.put("username", username);
        prefs.put("password", password);
    }
    
     private void loadSavedLoginInfo() {
        // Load saved login info from Java Preferences API
        Preferences prefs = Preferences.userNodeForPackage(getClass());
        String savedUsername = prefs.get("username", "");
        String savedPassword = prefs.get("password", "");

        if (!savedUsername.isEmpty()) {
            txtTENDN.setText(savedUsername);
        }

        if (!savedPassword.isEmpty()) {
            passwordField2.setText(savedPassword);
        }
    }
    
    // Hàm này simulates việc lấy hashed password từ cơ sở dữ liệu
    private static String getHashedPasswordFromDatabase(String userName) {
        // Thực tế, bạn sẽ truy vấn cơ sở dữ liệu để lấy hashed password dựa trên tên người dùng
        // Trong ví dụ này, chúng ta sẽ trả về một giá trị mẫu
        if ("user123".equals(userName)) {
            return "$2a$12$exampleHashedPassword"; // Đây là một giá trị thực từ cơ sở dữ liệu
        } else {
            return null; // Trả về null nếu không tìm thấy người dùng
        }
    }


    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        roundPanel1 = new swing.RoundPanel();
        jLabel7 = new javax.swing.JLabel();
        jSeparator2 = new javax.swing.JSeparator();
        jLabel8 = new javax.swing.JLabel();
        button3 = new swing.Button();
        button4 = new swing.Button();
        btnLogin = new swing.Button();
        ckLuuMK = new javax.swing.JCheckBox();
        txtTENDN = new component.TextField();
        passwordField2 = new component.PasswordField();
        login = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(26, 48, 116));
        jPanel1.setForeground(java.awt.SystemColor.activeCaptionBorder);

        roundPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jLabel7.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        jLabel7.setText("HỆ THỐNG QUẢN LÝ NEW WORLD HOTEL");

        jLabel8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/res/New_world.png"))); // NOI18N

        button3.setBackground(new java.awt.Color(204, 204, 204));
        button3.setText("Đổi mật khẩu");
        button3.setMaximumSize(new java.awt.Dimension(39, 25));
        button3.setMinimumSize(new java.awt.Dimension(39, 25));
        button3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button3ActionPerformed(evt);
            }
        });

        button4.setBackground(new java.awt.Color(204, 204, 204));
        button4.setText("Thoát");
        button4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button4ActionPerformed(evt);
            }
        });

        btnLogin.setBackground(new java.awt.Color(102, 102, 255));
        btnLogin.setText("Đăng Nhập");
        btnLogin.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        btnLogin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLoginActionPerformed(evt);
            }
        });

        ckLuuMK.setText("Ghi nhớ tài khoản");

        txtTENDN.setLabelText("Tên đăng nhập");

        passwordField2.setText("passwordField1");
        passwordField2.setLabelText("Mật khẩu");
        passwordField2.setShowAndHide(true);
        passwordField2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                passwordField2KeyPressed(evt);
            }
        });

        javax.swing.GroupLayout roundPanel1Layout = new javax.swing.GroupLayout(roundPanel1);
        roundPanel1.setLayout(roundPanel1Layout);
        roundPanel1Layout.setHorizontalGroup(
            roundPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, roundPanel1Layout.createSequentialGroup()
                .addContainerGap(225, Short.MAX_VALUE)
                .addComponent(jLabel7)
                .addGap(254, 254, 254))
            .addGroup(roundPanel1Layout.createSequentialGroup()
                .addGroup(roundPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(roundPanel1Layout.createSequentialGroup()
                        .addGap(91, 91, 91)
                        .addGroup(roundPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(roundPanel1Layout.createSequentialGroup()
                                .addComponent(btnLogin, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(33, 33, 33)
                                .addComponent(button3, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(26, 26, 26)
                                .addComponent(button4, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(35, 35, 35))
                            .addComponent(ckLuuMK)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, roundPanel1Layout.createSequentialGroup()
                        .addGap(72, 72, 72)
                        .addGroup(roundPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtTENDN, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 432, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(passwordField2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 432, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel8)
                .addGap(124, 124, 124))
            .addGroup(roundPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jSeparator2)
                .addContainerGap())
        );
        roundPanel1Layout.setVerticalGroup(
            roundPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(roundPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txtTENDN, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(roundPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(roundPanel1Layout.createSequentialGroup()
                        .addComponent(passwordField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(ckLuuMK))
                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(15, 15, 15)
                .addGroup(roundPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnLogin, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(button4, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(button3, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(36, 36, 36))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addComponent(roundPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(roundPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(15, Short.MAX_VALUE))
        );

        login.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Res/login.jpg"))); // NOI18N
        login.setToolTipText("");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(login, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(login, javax.swing.GroupLayout.PREFERRED_SIZE, 401, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void button3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button3ActionPerformed
        // TODO add your handling code here:
        FormChangePass l = new FormChangePass();
        l.setVisible(true);
        l.setLocationRelativeTo(null);
    }//GEN-LAST:event_button3ActionPerformed

    private void button4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button4ActionPerformed
        // TODO add your handling code here:
        int dk = JOptionPane.showConfirmDialog(this, "Bạn có muốn thoát chương trình", "Confirm", JOptionPane.YES_NO_OPTION);
        if (dk != JOptionPane.NO_OPTION) {
            System.exit(0);
        } else {
            return;
        }
    }//GEN-LAST:event_button4ActionPerformed

    private void btnLoginActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLoginActionPerformed
        // TODO add your handling code here:
        login();
    }//GEN-LAST:event_btnLoginActionPerformed

    private void passwordField2KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_passwordField2KeyPressed
        // TODO add your handling code here:
        if (evt.getKeyCode() == KeyEvent.VK_ENTER){
            btnLogin.requestFocus();
            btnLogin.doClick();
        }
    }//GEN-LAST:event_passwordField2KeyPressed

    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(FormLogin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FormLogin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FormLogin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FormLogin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FormLogin().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private swing.Button btnLogin;
    private swing.Button button3;
    private swing.Button button4;
    private javax.swing.JCheckBox ckLuuMK;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JLabel login;
    private component.PasswordField passwordField2;
    private swing.RoundPanel roundPanel1;
    private component.TextField txtTENDN;
    // End of variables declaration//GEN-END:variables
}
