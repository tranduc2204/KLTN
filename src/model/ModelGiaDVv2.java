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
public class ModelGiaDVv2 {
    String MaDonGiaDV, NgayApDung;
    BigDecimal  DonGia;

    public ModelGiaDVv2() {
    }

    public ModelGiaDVv2(String MaDonGiaDV, String NgayApDung, BigDecimal DonGia) {
        this.MaDonGiaDV = MaDonGiaDV;
        this.NgayApDung = NgayApDung;
        this.DonGia = DonGia;
    }

    public String getMaDonGiaDV() {
        return MaDonGiaDV;
    }

    public void setMaDonGiaDV(String MaDonGiaDV) {
        this.MaDonGiaDV = MaDonGiaDV;
    }

    public String getNgayApDung() {
        return NgayApDung;
    }

    public void setNgayApDung(String NgayApDung) {
        this.NgayApDung = NgayApDung;
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
