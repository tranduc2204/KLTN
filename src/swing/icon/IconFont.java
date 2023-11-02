/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package swing.icon;

import java.io.InputStream;

/**
 *
 * @author TeeDee
 */
public interface  IconFont {
    
    String getFontFamily();

 
    InputStream getFontInputStream();
}
