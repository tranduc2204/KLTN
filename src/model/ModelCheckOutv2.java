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
public class ModelCheckOutv2 {
    String MaHoaDonPhong ,MaPhieuThuePhong, NgayThuePhong, NgayDatPhong, MaPhong,TenPhong, LoaiPhong, NgayLapHoaDon;
    BigDecimal Gia, GiaHD;

    public ModelCheckOutv2() {
    }

    public String getMaHoaDonPhong() {
        return MaHoaDonPhong;
    }

    public void setMaHoaDonPhong(String MaHoaDonPhong) {
        this.MaHoaDonPhong = MaHoaDonPhong;
    }

    public String getMaPhieuThuePhong() {
        return MaPhieuThuePhong;
    }

    public void setMaPhieuThuePhong(String MaPhieuThuePhong) {
        this.MaPhieuThuePhong = MaPhieuThuePhong;
    }

    public String getNgayThuePhong() {
        return NgayThuePhong;
    }

    public void setNgayThuePhong(String NgayThuePhong) {
        this.NgayThuePhong = NgayThuePhong;
    }

    public String getNgayDatPhong() {
        return NgayDatPhong;
    }

    public void setNgayDatPhong(String NgayDatPhong) {
        this.NgayDatPhong = NgayDatPhong;
    }

    public String getMaPhong() {
        return MaPhong;
    }

    public void setMaPhong(String MaPhong) {
        this.MaPhong = MaPhong;
    }

    public String getTenPhong() {
        return TenPhong;
    }

    public void setTenPhong(String TenPhong) {
        this.TenPhong = TenPhong;
    }

    public String getLoaiPhong() {
        return LoaiPhong;
    }

    public void setLoaiPhong(String LoaiPhong) {
        this.LoaiPhong = LoaiPhong;
    }

    public String getNgayLapHoaDon() {
        return NgayLapHoaDon;
    }

    public void setNgayLapHoaDon(String NgayLapHoaDon) {
        this.NgayLapHoaDon = NgayLapHoaDon;
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

    public ModelCheckOutv2(String MaHoaDonPhong, String MaPhieuThuePhong, String NgayThuePhong, String NgayDatPhong, String MaPhong, String TenPhong, String LoaiPhong, String NgayLapHoaDon, BigDecimal Gia, BigDecimal GiaHD) {
        this.MaHoaDonPhong = MaHoaDonPhong;
        this.MaPhieuThuePhong = MaPhieuThuePhong;
        this.NgayThuePhong = NgayThuePhong;
        this.NgayDatPhong = NgayDatPhong;
        this.MaPhong = MaPhong;
        this.TenPhong = TenPhong;
        this.LoaiPhong = LoaiPhong;
        this.NgayLapHoaDon = NgayLapHoaDon;
        this.Gia = Gia;
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
