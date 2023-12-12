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
    String MaHoaDonPhong ,MaPhieuDatPhong, NgayThuePhong, NgayTraPhong, MaPhong,TenPhong, LoaiPhong, NgayLapHoaDon;
    BigDecimal Gia, GiaHD, VAT;
    int GiamGia;

    public ModelCheckOutv2() {
    }

    public ModelCheckOutv2(String MaHoaDonPhong, String MaPhieuDatPhong, String NgayThuePhong, String NgayTraPhong, String MaPhong, String TenPhong, String LoaiPhong, String NgayLapHoaDon, BigDecimal Gia, BigDecimal GiaHD, BigDecimal VAT, int GiamGia) {
        this.MaHoaDonPhong = MaHoaDonPhong;
        this.MaPhieuDatPhong = MaPhieuDatPhong;
        this.NgayThuePhong = NgayThuePhong;
        this.NgayTraPhong = NgayTraPhong;
        this.MaPhong = MaPhong;
        this.TenPhong = TenPhong;
        this.LoaiPhong = LoaiPhong;
        this.NgayLapHoaDon = NgayLapHoaDon;
        this.Gia = Gia;
        this.GiaHD = GiaHD;
        this.VAT = VAT;
        this.GiamGia = GiamGia;
    }

    public String getMaHoaDonPhong() {
        return MaHoaDonPhong;
    }

    public void setMaHoaDonPhong(String MaHoaDonPhong) {
        this.MaHoaDonPhong = MaHoaDonPhong;
    }

    public String getMaPhieuDatPhong() {
        return MaPhieuDatPhong;
    }

    public void setMaPhieuDatPhong(String MaPhieuDatPhong) {
        this.MaPhieuDatPhong = MaPhieuDatPhong;
    }

    public String getNgayThuePhong() {
        return NgayThuePhong;
    }

    public void setNgayThuePhong(String NgayThuePhong) {
        this.NgayThuePhong = NgayThuePhong;
    }

    public String getNgayTraPhong() {
        return NgayTraPhong;
    }

    public void setNgayTraPhong(String NgayTraPhong) {
        this.NgayTraPhong = NgayTraPhong;
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

    public BigDecimal getVAT() {
        return VAT;
    }

    public void setVAT(BigDecimal VAT) {
        this.VAT = VAT;
    }

    

    public void setGiamGia(int GiamGia) {
        this.GiamGia = GiamGia;
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
