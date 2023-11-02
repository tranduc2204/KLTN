/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;
import com.formdev.flatlaf.FlatLightLaf;
import com.formdev.flatlaf.themes.FlatMacDarkLaf;
import com.formdev.flatlaf.themes.FlatMacLightLaf;
import form.FormLogin;
import java.awt.Color;
/**
 *
 * @author TeeDee
 */
public class Run {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        FlatLightLaf.setup(); 
        FlatMacLightLaf.setup();

        
//        Main l= new Main();
//        l.setLocationRelativeTo(null);
//        l.setVisible(true);

        FormLogin f= new FormLogin();
        f.setLocationRelativeTo(null);
        f.setVisible(true);
    }
    
}
