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
public class ModelServiceRentv2 {
    String MaDV, TenDichVu ,MaNV, TenNhanVien, MaKH, TenKH, NgayLapHD;
    int SL;
    BigDecimal Gia, GiaHD;

    public ModelServiceRentv2(String MaDV, String TenDichVu, String MaNV, String TenNhanVien, String MaKH, String TenKH, String NgayLapHD, int SL, BigDecimal Gia, BigDecimal GiaHD) {
        this.MaDV = MaDV;
        this.TenDichVu = TenDichVu;
        this.MaNV = MaNV;
        this.TenNhanVien = TenNhanVien;
        this.MaKH = MaKH;
        this.TenKH = TenKH;
        this.NgayLapHD = NgayLapHD;
        this.SL = SL;
        this.Gia = Gia;
        this.GiaHD = GiaHD;
    }

    public ModelServiceRentv2() {
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

    public String getTenNhanVien() {
        return TenNhanVien;
    }

    public void setTenNhanVien(String TenNhanVien) {
        this.TenNhanVien = TenNhanVien;
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

    public BigDecimal getGia() {
        return Gia;
    }

    public void setGia(BigDecimal Gia) {
        this.Gia = Gia;
    }

    public BigDecimal getGiaHD() {
        return GiaHD;
    }

    public void setGiaHD(BigDecimal GiaHD) {
        this.GiaHD = GiaHD;
    }
    
    public String getFormattedGia() {
        DecimalFormat decimalFormat = new DecimalFormat("#,### VND");
        return decimalFormat.format(Gia);
    }
    
    public String getFormattedGiahd() {
        DecimalFormat decimalFormat = new DecimalFormat("#,### VND");
        return decimalFormat.format(GiaHD);
    }
}
