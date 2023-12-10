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
    String MaDV, TenDichVu ,MaNV, TenNhanVien, MaKH,HoKH, TenKH, NgayLapHD;
    int SL, GiamGia;
    BigDecimal Gia, GiaHD, VAT;

    public ModelServiceRentv2() {
    }

    public ModelServiceRentv2(String MaDV, String TenDichVu, String MaNV, String TenNhanVien, String MaKH, String HoKH, String TenKH, String NgayLapHD, int SL, int GiamGia, BigDecimal Gia, BigDecimal GiaHD, BigDecimal VAT) {
        this.MaDV = MaDV;
        this.TenDichVu = TenDichVu;
        this.MaNV = MaNV;
        this.TenNhanVien = TenNhanVien;
        this.MaKH = MaKH;
        this.HoKH = HoKH;
        this.TenKH = TenKH;
        this.NgayLapHD = NgayLapHD;
        this.SL = SL;
        this.GiamGia = GiamGia;
        this.Gia = Gia;
        this.GiaHD = GiaHD;
        this.VAT = VAT;
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

    public String getHoKH() {
        return HoKH;
    }

    public void setHoKH(String HoKH) {
        this.HoKH = HoKH;
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

    public int getGiamGia() {
        return GiamGia;
    }

    public void setGiamGia(int GiamGia) {
        this.GiamGia = GiamGia;
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

    public BigDecimal getVAT() {
        return VAT;
    }

    public void setVAT(BigDecimal VAT) {
        this.VAT = VAT;
    }

    

    public String getFormattedPercentage(){
        DecimalFormat decimalFormat = new DecimalFormat("0%");
        return decimalFormat.format((double) GiamGia / 100); // Chia cho 100 để đưa về dạng phần trăm
    }
    
    public String getFormattedGia() {
        DecimalFormat decimalFormat = new DecimalFormat("#,### VND");
        return decimalFormat.format(Gia);
    }
    
    public String getFormattedGiahd() {
        DecimalFormat decimalFormat = new DecimalFormat("#,### VND");
        return decimalFormat.format(GiaHD);
    }
    public String getFormattedVAT() {
        DecimalFormat decimalFormat = new DecimalFormat("#,### VND");
        return decimalFormat.format(VAT);
    }
   
}
