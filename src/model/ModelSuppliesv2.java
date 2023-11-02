/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.logging.Logger;

/**
 *
 * @author TeeDee
 */
public class ModelSuppliesv2 {
    String MaVatTu, TenVatTu;
    
    public ModelSuppliesv2() {
        
    }
    
    public ModelSuppliesv2(String MaVatTu, String TenVatTu) {
        this.MaVatTu = MaVatTu;
        this.TenVatTu = TenVatTu;
    }

    public String getMaVatTu() {
        return MaVatTu;
    }

    public void setMaVatTu(String MaVatTu) {
        this.MaVatTu = MaVatTu;
    }

    public String getTenVatTu() {
        return TenVatTu;
    }

    public void setTenVatTu(String TenVatTu) {
        this.TenVatTu = TenVatTu;
    }

   
}
