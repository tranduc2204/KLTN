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
public class ModelBillOfServicev2 {
    String MaDV, TenDichVu, MaNV, TenNV, MaKH, TenKH, NgayLapHD;
    int SL;
    BigDecimal Tongtien, giadv;

    public ModelBillOfServicev2(String MaDV, String TenDichVu, String MaNV, String TenNV, String MaKH, String TenKH, String NgayLapHD, int SL, BigDecimal Tongtien, BigDecimal giadv) {
        this.MaDV = MaDV;
        this.TenDichVu = TenDichVu;
        this.MaNV = MaNV;
        this.TenNV = TenNV;
        this.MaKH = MaKH;
        this.TenKH = TenKH;
        this.NgayLapHD = NgayLapHD;
        this.SL = SL;
        this.Tongtien = Tongtien;
        this.giadv = giadv;
    }

    public ModelBillOfServicev2() {
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

    public String getMaNV() {
        return MaNV;
    }

    public void setMaNV(String MaNV) {
        this.MaNV = MaNV;
    }

    public String getTenNV() {
        return TenNV;
    }

    public void setTenNV(String TenNV) {
        this.TenNV = TenNV;
    }

    public String getMaKH() {
        return MaKH;
    }

    public void setMaKH(String MaKH) {
        this.MaKH = MaKH;
    }

    public String getTenKH() {
        return TenKH;
    }

    public void setTenKH(String TenKH) {
        this.TenKH = TenKH;
    }

    public String getNgayLapHD() {
        return NgayLapHD;
    }

    public void setNgayLapHD(String NgayLapHD) {
        this.NgayLapHD = NgayLapHD;
    }

    public int getSL() {
        return SL;
    }

    public void setSL(int SL) {
        this.SL = SL;
    }

    public BigDecimal getTongtien() {
        return Tongtien;
    }

    public void setTongtien(BigDecimal Tongtien) {
        this.Tongtien = Tongtien;
    }

    public BigDecimal getGiadv() {
        return giadv;
    }

    public void setGiadv(BigDecimal giadv) {
        this.giadv = giadv;
    }
    
    // hàm tính tổng tiền
    public BigDecimal calculateTotal() {
        return giadv.multiply(BigDecimal.valueOf(SL));
    }   
    
    public String getFormattedGiaDv() {
        DecimalFormat decimalFormat = new DecimalFormat("#,### VND");
        return decimalFormat.format(giadv);
    }
    
    public String getFormattedTongTien() {
        DecimalFormat decimalFormat = new DecimalFormat("#,### VND");
        return decimalFormat.format(Tongtien);
    }
    
}
