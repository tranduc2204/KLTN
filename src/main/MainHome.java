/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;
import component.Header;
import component.Menu;
import connect.Connect;
import event.EventMenuSelected;
import event.EventShowPopupMenu;
import form.FormBackupRestore;
import form.FormBill;
import form.FormChart;
import form.FormCheckIn;
import form.FormCheckOut;
import form.FormHome;
import form.FormLogin;
import form.FormQLAccount;
import form.FormQLBillOfService;
import form.FormQLCustomers;
import form.FormQLRoom;
import form.FormQLService;
import form.FormQLStaff;
import form.FormQLSupplies;
import form.FormQLSuppliesForRoom;
import form.FormRecycle_Bin;
import form.FormRent;
import form.FormServiceRent;
import form.FormSetting;
import form.FormStatistic1;
import form.FormTrash;

import form.MainForm;
import form.test;
import java.awt.Color;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JOptionPane;
import net.miginfocom.swing.MigLayout;
import org.jdesktop.animation.timing.Animator;
import org.jdesktop.animation.timing.TimingTarget;
import org.jdesktop.animation.timing.TimingTargetAdapter;
import swing.MenuItem;
import swing.PopupMenu;
import swing.icon.GoogleMaterialDesignIcons;
import swing.icon.IconFontSwing;
import theme.ThemeColor;
import theme.ThemeColorChange;
/**
 *
 * @author TeeDee
 */
public class MainHome extends javax.swing.JFrame {

    /**
     * Creates new form main
    
    */
    
    private MigLayout layout;
    private Menu menu;
    private Header header;
    private MainForm main;
    private Animator animator;
    
    private FormLogin log;
 
    Connect cn = new Connect();
    Connection conn;
    
    private String username, password, quyen, DisplayName;
    
    public MainHome(){
        initComponents();
    }
    
    public MainHome(String username, String password, String DisplayName, String quyen) {
        initComponents();
        setBackground(new Color(0, 0, 0, 0));



//        init();

        
        this.username = username;
        this.password = password;
        this.DisplayName = DisplayName;
        this.quyen = quyen;
        

        layout = new MigLayout("fill", "0[]0[100%, fill]0", "0[fill, top]0");
        bg.setLayout(layout);
        menu = new Menu();
//        header = new Header();
        header = new Header(username, password, DisplayName,quyen);
        main = new MainForm();
        
        log = new FormLogin();
        
        menu.addEvent(new EventMenuSelected(){
        @Override
        public void menuSelected(int menuIndex, int subMenuIndex) {
            System.out.println("Menu Index : " + menuIndex + " SubMenu Index " + subMenuIndex);
            if (menuIndex == 0) {
                main.showForm(new FormHome());
            }else if (menuIndex ==1 && quyen.equals("Admin")){
                main.showForm(new FormChart());
            }else if (menuIndex ==1 && quyen.equals("root")){
                main.showForm(new FormBackupRestore());
            }else if (menuIndex ==2 && quyen.equals("root")){
                main.showForm(new FormTrash());
            }else if (menuIndex ==2 && quyen.equals("Admin")){
                if (subMenuIndex == 0){
                    main.showForm(new FormQLAccount(username, password, DisplayName,quyen));
                }else if (subMenuIndex == 1){
                    main.showForm(new FormQLCustomers(username, password, DisplayName,quyen));
                }else if (subMenuIndex == 2){
                    main.showForm(new FormBill(username, password, DisplayName,quyen));
                }
                else if (subMenuIndex == 3){
                    main.showForm(new FormQLStaff(username, password, DisplayName,quyen));
                }else if (subMenuIndex == 4){
                    main.showForm(new FormQLService(username, password, DisplayName,quyen));
                }else if (subMenuIndex == 5){
                    main.showForm(new FormQLRoom(username, password, DisplayName,quyen));
                }else if (subMenuIndex == 6){
                    main.showForm(new FormQLSupplies(username, password, DisplayName,quyen));
                }else if (subMenuIndex == 7){
                    main.showForm(new FormQLSuppliesForRoom(username, password, DisplayName,quyen));
                }else if (subMenuIndex == 8){
                    main.showForm(new FormQLBillOfService(username, password, DisplayName,quyen));
                }
                

            }else if (menuIndex ==3 && quyen.equals("Admin")){

                main.showForm(new FormRent(username, password, DisplayName,quyen));
            }else if (menuIndex ==4 && quyen.equals("Admin")){
                main.showForm(new FormCheckIn(username, password, DisplayName,quyen));
            }else if (menuIndex ==5 && quyen.equals("Admin")){
                main.showForm(new FormCheckOut(username, password, DisplayName,quyen));
            }else if (menuIndex ==6 && quyen.equals("Admin")){
                if (subMenuIndex == 0){
                    main.showForm(new FormStatistic1(username, password, DisplayName,quyen));
                }
                
            }else if (menuIndex ==7 && quyen.equals("Admin")){
                main.showForm(new FormServiceRent(username, password, DisplayName,quyen));
            }else if (menuIndex ==8 && quyen.equals("Admin")){
                Runtime rt = Runtime.getRuntime();
                try {
                    String filePath = "src\\form\\THNN_TranDuc.chm";  //Đường dẫn và tên file cần mở
                    rt.exec("hh.exe " + filePath);

                } catch (Exception ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(main, "Cannot load help file!", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
            else if (menuIndex == 9 && quyen.equals("Admin")){
                main.showForm(new FormSetting());
                
            }else if(menuIndex == 10 && quyen.equals("Admin") ){
                if (subMenuIndex == 0){
                    System.out.println("hihi");
                    menu.clearMenu();
                    menu.initMenuItemVietNam();
                }else if(subMenuIndex == 1){
                    menu.clearMenu();
                    menu.initMenuItem();
                }
            }else if (menuIndex == 12){
                int dk = JOptionPane.showConfirmDialog(main, "Bạn có muốn đăng xuất khỏi chương trình", "Confirm", JOptionPane.YES_NO_OPTION);
                if (dk != JOptionPane.NO_OPTION) {
                    main.hide();
                    header.hide();
                    menu.hide();
                    bg.hide();
                    log.setLocationRelativeTo(null);
                    log.setVisible(true);
                    
                    
                   
                } else {
                    return;
                }
            }else if (menuIndex == 13){
                int dk = JOptionPane.showConfirmDialog(main, "Bạn có muốn thoát khỏi chương trình", "Confirm", JOptionPane.YES_NO_OPTION);
                if (dk != JOptionPane.NO_OPTION) {
                    System.exit(0);
                } else {
                    return;
                }
            }
            ///////////////////////user//////////////////
            else if (menuIndex == 8 && quyen.equals("User")){
                int dk = JOptionPane.showConfirmDialog(main, "Bạn có muốn đăng xuất khỏi chương trình", "Confirm", JOptionPane.YES_NO_OPTION);
                if (dk != JOptionPane.NO_OPTION) {
                    main.hide();
                    header.hide();
                    menu.hide();
                    bg.hide();
                    log.setLocationRelativeTo(null);
                    log.setVisible(true);
                } else {
                    return;
                }
            }else if (menuIndex == 9 && quyen.equals("User")){
                int dk = JOptionPane.showConfirmDialog(main, "Bạn có muốn thoát khỏi chương trình", "Confirm", JOptionPane.YES_NO_OPTION);
                if (dk != JOptionPane.NO_OPTION) {
                    System.exit(0);
                } else {
                    return;
                }
            }else if(menuIndex == 6 && quyen.equals("User") ){
                if (subMenuIndex == 0){
                    menu.clearMenu();
                    menu.initMenuItem1VietNam();
                }else if(subMenuIndex == 1){
                    menu.clearMenu();
                    menu.initMenuItem1();
                }
            }else if (menuIndex == 5 && quyen.equals("User")){
                main.showForm(new FormSetting());
                
            }
        }

            });
        if (quyen.equals("Admin")){
            menu.initMenuItem();
            
        }else if (quyen.equals("User")){
            menu.initMenuItem1();
            
        }else if (quyen.equals("root")){
            menu.initMenuItemroot();
            
        }
        

        
        // show menu icon when menu is 
        menu.addEventShowPopup(new EventShowPopupMenu() {
            @Override
            public void showPopup(Component com) {
                MenuItem item = (MenuItem) com;
                PopupMenu popup = new PopupMenu(MainHome.this, item.getIndex(), item.getEventSelected(), item.getMenu().getSubMenu());
                int x = MainHome.this.getX() + 52;
                int y = MainHome.this.getY() + com.getY() + 86;
                popup.setLocation(x, y);
                popup.setVisible(true);
            }
        });
        bg.add(menu, "w 230!, spany 2");    // Span Y 2cell
        bg.add(header, "h 50!, wrap");
        bg.add(main, "w 100%, h 100%");
        TimingTarget target = new TimingTargetAdapter(){
            @Override
            public void timingEvent(float fraction) {
                double width;
                if (menu.isShowMenu()) {
                    width = 60 + (170 * (1f - fraction));
                } else {
                    width = 60 + (170 * fraction);
                }
                layout.setComponentConstraints(menu, "w " + width + "!, spany2");
                menu.revalidate();
            }

            @Override
            public void end() {
                menu.setShowMenu(!menu.isShowMenu());
                menu.setEnableMenu(true);
            }
            
        };
        animator = new Animator(500, target);
        animator.setResolution(0);
        animator.setDeceleration(0.5f);
        animator.setAcceleration(0.5f);
        header.addMenuEvent(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!animator.isRunning()) {
                    animator.start();
                }
                menu.setEnableMenu(false);
                if (menu.isShowMenu()) {
                    menu.hideallMenu();
                }
            }
            
        });
        
         ThemeColorChange.getInstance().addThemes(new ThemeColor(new Color(34, 34, 34), Color.WHITE) {
            @Override
            public void onColorChange(Color color) {
                bg.setBackground(color);
            }
        });
//        ThemeColorChange.getInstance().addThemes(new ThemeColor(Color.WHITE, new Color(80, 80, 80)) {
//            @Override
//            public void onColorChange(Color color) {
//                mainBody.changeColor(color);
//            }
//        });
        
        
        // init gg icon font
        IconFontSwing.register(GoogleMaterialDesignIcons.getIconFont());
        //auto show form home when run project
        main.showForm(new FormHome());
        
        
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        bg = new javax.swing.JLayeredPane();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);

        bg.setBackground(new java.awt.Color(245, 245, 245));
        bg.setOpaque(true);

        javax.swing.GroupLayout bgLayout = new javax.swing.GroupLayout(bg);
        bg.setLayout(bgLayout);
        bgLayout.setHorizontalGroup(
            bgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1361, Short.MAX_VALUE)
        );
        bgLayout.setVerticalGroup(
            bgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 862, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(bg)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(bg)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */
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
            java.util.logging.Logger.getLogger(MainHome.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MainHome.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MainHome.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainHome.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MainHome().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLayeredPane bg;
    // End of variables declaration//GEN-END:variables
}
