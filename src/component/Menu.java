/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package component;

import event.EventMenu;
import event.EventMenuSelected;
import event.EventShowPopupMenu;
import java.awt.Color;
import java.awt.Component;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.event.MouseInputAdapter;
import model.ModelMenu;
import net.miginfocom.swing.MigLayout;
import swing.MenuAnimation;
import swing.MenuItem;
import swing.ScrollBarCustom;

/**
 *
 * @author TeeDee
 */
public class Menu extends javax.swing.JPanel {

    /**
     * Creates new form menu
     */
    
    public boolean isShowMenu() {
        return showMenu;
    }

    public void addEvent(EventMenuSelected event) {
        this.event = event;
    }

    public void setEnableMenu(boolean enableMenu) {
        this.enableMenu = enableMenu;
    }

    public void setShowMenu(boolean showMenu) {
        this.showMenu = showMenu;
    }

    public void addEventShowPopup(EventShowPopupMenu eventShowPopup) {
        this.eventShowPopup = eventShowPopup;
    }

    private final MigLayout layout;
    private EventMenuSelected event;
    private EventShowPopupMenu eventShowPopup;
    private boolean enableMenu = true;
    private boolean showMenu = true;
    
    
    
    public Menu() {
        initComponents();
        setOpaque(false);
        sp.getViewport().setOpaque(false);
        sp.setVerticalScrollBar(new ScrollBarCustom());
        layout = new MigLayout("wrap, fillx, insets 0", "[fill]", "[]0[]");
        panel.setLayout(layout);
    }
    
    public void clearMenu() {
        panel.removeAll();
        panel.revalidate();
        panel.repaint();
    }
    
    private void addEnpty(){
        panel.add(new JLabel(), "push");
    }
    //menu root
    public void initMenuItemroot() {
        addMenu(new ModelMenu(new ImageIcon(getClass().getResource("/res/1.png")), "Home"));
        addMenu(new ModelMenu(new ImageIcon(getClass().getResource("/res/data-recovery.png")), "Backup Restore"));
        addMenu(new ModelMenu(new ImageIcon(getClass().getResource("/res/delete_2.png")), "System Trash"));
        addMenu(new ModelMenu(new ImageIcon(getClass().getResource("/res/question.png")), "Help"));
        addMenu(new ModelMenu(new ImageIcon(getClass().getResource("/res/settings.png")), "Setting"));
        addMenu(new ModelMenu(new ImageIcon(getClass().getResource("/res/padlock.png")), "Change Password"));
        addMenu(new ModelMenu(new ImageIcon(getClass().getResource("/res/translation.png")),"Change Language","Vietnamese","English"));
        addEnpty();

        addMenu(new ModelMenu(new ImageIcon(getClass().getResource("/res/logout.png")), "Log out"));
        addMenu(new ModelMenu(new ImageIcon(getClass().getResource("/res/power-off.png")), "Exit"));
    }
    
    public void initMenuItemrootVietNam() {
        addMenu(new ModelMenu(new ImageIcon(getClass().getResource("/res/1.png")), "Trang chủ"));
        addMenu(new ModelMenu(new ImageIcon(getClass().getResource("/res/data-recovery.png")), "Phục hồi dữ liệu"));
        addMenu(new ModelMenu(new ImageIcon(getClass().getResource("/res/delete_2.png")), "Thùng rác hệ thống"));
        addMenu(new ModelMenu(new ImageIcon(getClass().getResource("/res/question.png")), "Trợ giúp"));
        addMenu(new ModelMenu(new ImageIcon(getClass().getResource("/res/settings.png")), "Cài đặt"));
        addMenu(new ModelMenu(new ImageIcon(getClass().getResource("/res/padlock.png")), "Đổi mật khẩu"));
        addMenu(new ModelMenu(new ImageIcon(getClass().getResource("/res/translation.png")), "Đổi ngôn ngữ","Việt Nam","Tiếng anh"));
        addEnpty();

        addMenu(new ModelMenu(new ImageIcon(getClass().getResource("/res/logout.png")), "Đăng xuất"));
        addMenu(new ModelMenu(new ImageIcon(getClass().getResource("/res/power-off.png")), "Thoát"));
    }
    
    // menu admin
    public void initMenuItem() {
        
        addMenu(new ModelMenu(new ImageIcon(getClass().getResource("/res/1.png")), "Home"));
        addMenu(new ModelMenu(new ImageIcon(getClass().getResource("/res/2.png")), "Dashboard"));
        addMenu(new ModelMenu(new ImageIcon(getClass().getResource("/res/user.png")), "Admin Management", "Accounts Management",
                "Customers Management", "Bills Management","Staffs Management","Services Management","Rooms Management",
                "Supplies Management","Room Supplies Management", "Service Bill Management"));
        addMenu(new ModelMenu(new ImageIcon(getClass().getResource("/res/pie-chart.png")), "Statistic", "Statistic 001", "Statistic 002"));
        addMenu(new ModelMenu(new ImageIcon(getClass().getResource("/res/question.png")), "Help"));
        addMenu(new ModelMenu(new ImageIcon(getClass().getResource("/res/settings.png")), "Setting"));
        addMenu(new ModelMenu(new ImageIcon(getClass().getResource("/res/padlock.png")), "Change Password"));
        addMenu(new ModelMenu(new ImageIcon(getClass().getResource("/res/translation.png")), "Change Language","Vietnamese","English"));
        addEnpty();

        addMenu(new ModelMenu(new ImageIcon(getClass().getResource("/res/logout.png")), "Log out"));
        addMenu(new ModelMenu(new ImageIcon(getClass().getResource("/res/power-off.png")), "Exit"));
    }
    
    public void initMenuItemVietNam() {
        addMenu(new ModelMenu(new ImageIcon(getClass().getResource("/res/1.png")), "Trang chủ"));
        addMenu(new ModelMenu(new ImageIcon(getClass().getResource("/res/2.png")), "Biểu đồ"));
        addMenu(new ModelMenu(new ImageIcon(getClass().getResource("/res/user.png")), "Quản lý", "Quản lý tài khoản", 
                "Quản lý khách hàng", "Quản lý hóa đơn","Quản lý nhân viên","Quản lý dịch vụ","Quản lý phòng","Quản lý vật tư", "Quản lý vật tư phòng"
                ,"Quản lý hóa đơn dịch vụ"));
        addMenu(new ModelMenu(new ImageIcon(getClass().getResource("/res/pie-chart.png")), "Thống kê", "Thống kê 001", "Thống kê 002"));
        addMenu(new ModelMenu(new ImageIcon(getClass().getResource("/res/question.png")), "Trợ giúp"));
        addMenu(new ModelMenu(new ImageIcon(getClass().getResource("/res/settings.png")), "Cài đặt"));
        addMenu(new ModelMenu(new ImageIcon(getClass().getResource("/res/padlock.png")), "Đổi mật khẩu"));
        addMenu(new ModelMenu(new ImageIcon(getClass().getResource("/res/translation.png")), "Đổi ngôn ngữ","Việt Nam","Tiếng anh"));
        addEnpty();
        addMenu(new ModelMenu(new ImageIcon(getClass().getResource("/res/logout.png")), "Đăng xuất"));
        addMenu(new ModelMenu(new ImageIcon(getClass().getResource("/res/power-off.png")), "Thoát"));
    }
    
    // menu user
    public void initMenuItem1() {
        addMenu(new ModelMenu(new ImageIcon(getClass().getResource("/res/1.png")), "Home"));
        addMenu(new ModelMenu(new ImageIcon(getClass().getResource("/res/booking.png")), "Rent"));
        addMenu(new ModelMenu(new ImageIcon(getClass().getResource("/res/8.png")), "Check in"));
        addMenu(new ModelMenu(new ImageIcon(getClass().getResource("/res/9.png")), "Check out"));
        addMenu(new ModelMenu(new ImageIcon(getClass().getResource("/res/11.png")), "Service"));
        addMenu(new ModelMenu(new ImageIcon(getClass().getResource("/res/question.png")), "Help"));
        addMenu(new ModelMenu(new ImageIcon(getClass().getResource("/res/settings.png")), "Setting"));
        addMenu(new ModelMenu(new ImageIcon(getClass().getResource("/res/padlock.png")), "Change Password"));
        addMenu(new ModelMenu(new ImageIcon(getClass().getResource("/res/translation.png")), "Change Language","Vietnamese","English"));
        addEnpty();
        addMenu(new ModelMenu(new ImageIcon(getClass().getResource("/res/logout.png")), "Log out"));
        addMenu(new ModelMenu(new ImageIcon(getClass().getResource("/res/power-off.png")), "Exit"));
        
    }
    
    public void initMenuItem1VietNam() {
        addMenu(new ModelMenu(new ImageIcon(getClass().getResource("/res/1.png")), "Trang chủ"));
        addMenu(new ModelMenu(new ImageIcon(getClass().getResource("/res/booking.png")), "Đặt phòng"));
        addMenu(new ModelMenu(new ImageIcon(getClass().getResource("/res/8.png")), "Thuê phòng"));
        addMenu(new ModelMenu(new ImageIcon(getClass().getResource("/res/9.png")), "Trả phòng"));
        addMenu(new ModelMenu(new ImageIcon(getClass().getResource("/res/11.png")), "Dịch vụ"));
        addMenu(new ModelMenu(new ImageIcon(getClass().getResource("/res/question.png")), "Trợ giúp"));
        addMenu(new ModelMenu(new ImageIcon(getClass().getResource("/res/settings.png")), "Cài đặt"));
        addMenu(new ModelMenu(new ImageIcon(getClass().getResource("/res/padlock.png")), "Đổi mật khẩu"));
        addMenu(new ModelMenu(new ImageIcon(getClass().getResource("/res/translation.png")), "Đổi ngôn ngữ","Việt Nam","Tiếng anh"));
        addEnpty();
        addMenu(new ModelMenu(new ImageIcon(getClass().getResource("/res/logout.png")), "Đăng xuất"));
        addMenu(new ModelMenu(new ImageIcon(getClass().getResource("/res/power-off.png")), "Thoát"));
        
    }
    
    private void addMenu(ModelMenu menu) {
        panel.add(new MenuItem(menu, getEventMenu(), event, panel.getComponentCount()), "h 40!");
    }

    private EventMenu getEventMenu() {
        return new EventMenu() {
            @Override
            public boolean menuPressed(Component com, boolean open) {
                if (enableMenu) {
                    if (isShowMenu()) {
                        if (open) {
                            new MenuAnimation(layout, com).openMenu();
                        } else {
                            new MenuAnimation(layout, com).closeMenu();
                        }
                        return true;
                    } else {
                        eventShowPopup.showPopup(com);
                    }
                }
                return false;
            }
            
        };
           
    }
    
     public void hideallMenu() {
        for (Component com : panel.getComponents()) {
            MenuItem item = (MenuItem) com;
            if (item.isOpen()) {
                new MenuAnimation(layout, com, 500).closeMenu();
                item.setOpen(false);
            }
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

        sp = new javax.swing.JScrollPane();
        panel = new javax.swing.JPanel();
        profile1 = new component.Profile();

        sp.setBorder(null);
        sp.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        panel.setOpaque(false);

        javax.swing.GroupLayout panelLayout = new javax.swing.GroupLayout(panel);
        panel.setLayout(panelLayout);
        panelLayout.setHorizontalGroup(
            panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 308, Short.MAX_VALUE)
        );
        panelLayout.setVerticalGroup(
            panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 574, Short.MAX_VALUE)
        );

        sp.setViewportView(panel);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(sp, javax.swing.GroupLayout.DEFAULT_SIZE, 265, Short.MAX_VALUE)
            .addComponent(profile1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(profile1, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(sp, javax.swing.GroupLayout.DEFAULT_SIZE, 378, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    @Override
    protected void paintComponent(Graphics grphcs) {
        Graphics2D g2 = (Graphics2D) grphcs;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
//        GradientPaint gra = new GradientPaint(0, 0, new Color(33, 105, 249), getWidth(), 0, new Color(93, 58, 196));
//        GradientPaint gra = new GradientPaint(0, 0, new Color(112,104,108), getWidth(), 0, new Color(113,117,61)); //[113,117,61] màu rêu cũ
        GradientPaint gra = new GradientPaint(0, 0, new Color(20,28,116), getWidth(), 0, new Color(20,140,219)); // chốt
        
        g2.setPaint(gra);
        g2.fillRect(0, 0, getWidth(), getHeight());//colorGradient	[113,117,61]
        super.paintComponent(grphcs);//[145,160,49] [112,104,108] 112,104,108 [100,87,94]
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel panel;
    private component.Profile profile1;
    private javax.swing.JScrollPane sp;
    // End of variables declaration//GEN-END:variables
}
