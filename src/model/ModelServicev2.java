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
    private String MaDV, TenDichVu, MaDonGiaDV;
    private BigDecimal DonGia;
    private int GiamGia;
    public ModelServicev2() {
    }

    public ModelServicev2(String MaDV, String TenDichVu, String MaDonGiaDV, BigDecimal DonGia, int GiamGia) {
        this.MaDV = MaDV;
        this.TenDichVu = TenDichVu;
        this.MaDonGiaDV = MaDonGiaDV;
        this.DonGia = DonGia;
        this.GiamGia = GiamGia;
    }

    public String getMaDV() {
        return MaDV;
    }

    public void setMaDV(String MaDV) {
        this.MaDV = MaDV;
    }

    public String getTenDichVu() {
        return TenDichVu;
    }

    public void setTenDichVu(String TenDichVu) {
        this.TenDichVu = TenDichVu;
    }

    public String getMaDonGiaDV() {
        return MaDonGiaDV;
    }

    public void setMaDonGiaDV(String MaDonGiaDV) {
        this.MaDonGiaDV = MaDonGiaDV;
    }

    public BigDecimal getDonGia() {
        return DonGia;
    }

    public void setDonGia(BigDecimal DonGia) {
        this.DonGia = DonGia;
    }

    public int getGiamGia() {
        return GiamGia;
    }

    public void setGiamGia(int GiamGia) {
        this.GiamGia = GiamGia;
    }

    

    public String getFormattedDonGia() {
        DecimalFormat decimalFormat = new DecimalFormat("#,### VND");
        return decimalFormat.format(DonGia);
    }
    
    public String getFormattedPercentage(){
        DecimalFormat decimalFormat = new DecimalFormat("0%");
        return decimalFormat.format((double) GiamGia / 100); // Chia cho 100 để đưa về dạng phần trăm
    }
}
