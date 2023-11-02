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
public class ModelServicev2 {
    private String MaDV, TenDichVu;
    private BigDecimal  DonGia;

    public ModelServicev2() {
    }

    public ModelServicev2(String MaDV, String TenDichVu, BigDecimal DonGia) {
        this.MaDV = MaDV;
        this.TenDichVu = TenDichVu;
        this.DonGia = DonGia;
    }

    public String getMaDichVu() {
        return MaDV;
    }

    public void setMaDichVu(String MaDichVu) {
        this.MaDV = MaDichVu;
    }

    public String getTenDichVu() {
        return TenDichVu;
    }

    public void setTenDichVu(String TenDichVu) {
        this.TenDichVu = TenDichVu;
    }

    public BigDecimal getDonGia() {
        return DonGia;
    }

    public void setDonGia(BigDecimal DonGia) {
        this.DonGia = DonGia;
    }

    public String getFormattedDonGia() {
        DecimalFormat decimalFormat = new DecimalFormat("#,### VND");
        return decimalFormat.format(DonGia);
    }
}
