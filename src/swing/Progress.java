/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package swing;

import java.awt.Color;
import javax.swing.JProgressBar;
import javax.swing.UIManager;

/**
 *
 * @author TeeDee
 */
public class Progress extends JProgressBar{
    private final ProgressCircleUI ui;

    public Progress() {
        setOpaque(false);
        setStringPainted(true);
        
        
        
        ui = new ProgressCircleUI();
        setUI(ui);
    }

    @Override
    public String getString() {
        return ((int) (getValue() * ui.getAnimate())) + "%";
       
    }

    public void start() {
        ui.start();
    }
}
