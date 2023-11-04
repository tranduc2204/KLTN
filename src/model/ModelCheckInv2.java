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
public class ModelCheckInv2 {
    String MaPhieuThuePhong, MaPhieuDatPhong, NgayDatPhong, MaKH, TenKH, MaPhong, TenPhong, LoaiPhong, NgayThuePhong;
    BigDecimal Gia;

    public ModelCheckInv2() {
    }

    public ModelCheckInv2(String MaPhieuThuePhong, String MaPhieuDatPhong, String NgayDatPhong, String MaKH, String TenKH, String MaPhong, String TenPhong, String LoaiPhong, String NgayThuePhong, BigDecimal Gia) {
        this.MaPhieuThuePhong = MaPhieuThuePhong;
        this.MaPhieuDatPhong = MaPhieuDatPhong;
        this.NgayDatPhong = NgayDatPhong;
        this.MaKH = MaKH;
        this.TenKH = TenKH;
        this.MaPhong = MaPhong;
        this.TenPhong = TenPhong;
        this.LoaiPhong = LoaiPhong;
        this.NgayThuePhong = NgayThuePhong;
        this.Gia = Gia;
    }

    public String getMaPhieuThuePhong() {
        return MaPhieuThuePhong;
    }

    public void setMaPhieuThuePhong(String MaPhieuThuePhong) {
        this.MaPhieuThuePhong = MaPhieuThuePhong;
    }

    public String getMaPhieuDatPhong() {
        return MaPhieuDatPhong;
    }

    public void setMaPhieuDatPhong(String MaPhieuDatPhong) {
        this.MaPhieuDatPhong = MaPhieuDatPhong;
    }

    public String getNgayDatPhong() {
        return NgayDatPhong;
    }

    public void setNgayDatPhong(String NgayDatPhong) {
        this.NgayDatPhong = NgayDatPhong;
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

    public String getNgayThuePhong() {
        return NgayThuePhong;
    }

    public void setNgayThuePhong(String NgayThuePhong) {
        this.NgayThuePhong = NgayThuePhong;
    }

    public BigDecimal getGia() {
        return Gia;
    }

    public void setGia(BigDecimal Gia) {
        this.Gia = Gia;
    }
    
    public String getFormattedGia() {
        DecimalFormat decimalFormat = new DecimalFormat("#,### VND");
        return decimalFormat.format(Gia);
    }
}
