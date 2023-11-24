/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package component;

/**
 *
 * @author TeeDee
 */
public interface TableActionEvent {
    public void onFresh(int row);

    public void onDelete(int row);

    
}
