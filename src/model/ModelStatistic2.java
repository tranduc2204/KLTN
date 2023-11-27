/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.math.BigDecimal;
import java.text.DecimalFormat;

/**
 *
 * @author TeeDee
 */
public class ModelStatistic2 {
    private BigDecimal tien;

    public BigDecimal getTien() {
        return tien;
    }

    public void setTien(BigDecimal tien) {
        this.tien = tien;
    }
    
    public String getFormattedDonGia() {
        DecimalFormat decimalFormat = new DecimalFormat("#,### VND");
        return decimalFormat.format(tien);
    }
}
