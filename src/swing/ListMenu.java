/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package swing;


import java.awt.Component;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.DefaultListCellRenderer;
import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.ListCellRenderer;
import javax.swing.SwingUtilities;
import model.ModelMenu;
/**
 *
 * @author TeeDee
 */
public class ListMenu <E extends Object> extends JList<E>{
    
    private final DefaultListModel model;
    private int selectedIndex = -1;
    
    public ListMenu() {
        model = new DefaultListModel();
        setModel(model);
        addMouseListener(new MouseAdapter() {
           
        });

    }

   
  
    
}
