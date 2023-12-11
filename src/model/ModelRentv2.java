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
public class ModelRentv2 {
    String MaPhieuDatPhong, MaKH,HoKH, TenKH,MaNV,HoNV, TenNV, MaPhong, TenPhong, LoaiPhong, NgayDatPhong, NgayDuKienThue, NgayDuKienTra;
    BigDecimal Gia;
    int booked_status;

    public ModelRentv2() {
    }

    public ModelRentv2(String MaPhieuDatPhong, String MaKH, String HoKH, String TenKH, String MaNV, String HoNV, String TenNV, String MaPhong, String TenPhong, String LoaiPhong, String NgayDatPhong, String NgayDuKienThue, String NgayDuKienTra, BigDecimal Gia, int booked_status) {
        this.MaPhieuDatPhong = MaPhieuDatPhong;
        this.MaKH = MaKH;
        this.HoKH = HoKH;
        this.TenKH = TenKH;
        this.MaNV = MaNV;
        this.HoNV = HoNV;
        this.TenNV = TenNV;
        this.MaPhong = MaPhong;
        this.TenPhong = TenPhong;
        this.LoaiPhong = LoaiPhong;
        this.NgayDatPhong = NgayDatPhong;
        this.NgayDuKienThue = NgayDuKienThue;
        this.NgayDuKienTra = NgayDuKienTra;
        this.Gia = Gia;
        this.booked_status = booked_status;
    }

    public String getMaPhieuDatPhong() {
        return MaPhieuDatPhong;
    }

    public void setMaPhieuDatPhong(String MaPhieuDatPhong) {
        this.MaPhieuDatPhong = MaPhieuDatPhong;
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

    public String getMaNV() {
        return MaNV;
    }

    public void setMaNV(String MaNV) {
        this.MaNV = MaNV;
    }

    public String getHoNV() {
        return HoNV;
    }

    public void setHoNV(String HoNV) {
        this.HoNV = HoNV;
    }

    public String getTenNV() {
        return TenNV;
    }

    public void setTenNV(String TenNV) {
        this.TenNV = TenNV;
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

    public String getNgayDatPhong() {
        return NgayDatPhong;
    }

    public void setNgayDatPhong(String NgayDatPhong) {
        this.NgayDatPhong = NgayDatPhong;
    }

    public String getNgayDuKienThue() {
        return NgayDuKienThue;
    }

    public void setNgayDuKienThue(String NgayDuKienThue) {
        this.NgayDuKienThue = NgayDuKienThue;
    }

    public String getNgayDuKienTra() {
        return NgayDuKienTra;
    }

    public void setNgayDuKienTra(String NgayDuKienTra) {
        this.NgayDuKienTra = NgayDuKienTra;
    }

    public BigDecimal getGia() {
        return Gia;
    }

    public void setGia(BigDecimal Gia) {
        this.Gia = Gia;
    }

    public int getBooked_status() {
        return booked_status;
    }

    public void setBooked_status(int booked_status) {
        this.booked_status = booked_status;
    }

    

    

    
    
    public String getFormattedGia() {
        DecimalFormat decimalFormat = new DecimalFormat("#,### VND");
        return decimalFormat.format(Gia);
    }
}
