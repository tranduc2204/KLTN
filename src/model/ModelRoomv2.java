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
public class ModelRoomv2 {
    
    private String MaPhong, TenPhong, MaTinhTrangPhong,tinhtrangphong, MaLoaiPhong,TenLoaiPhong, MaDonGiaPhong;
    private BigDecimal tien;
    private int isvisible, GiamGia;

    public ModelRoomv2() {
    }

    public ModelRoomv2(String MaPhong, String TenPhong, String MaTinhTrangPhong, String tinhtrangphong, String MaLoaiPhong, String TenLoaiPhong, String MaDonGiaPhong, BigDecimal tien, int isvisible, int GiamGia) {
        this.MaPhong = MaPhong;
        this.TenPhong = TenPhong;
        this.MaTinhTrangPhong = MaTinhTrangPhong;
        this.tinhtrangphong = tinhtrangphong;
        this.MaLoaiPhong = MaLoaiPhong;
        this.TenLoaiPhong = TenLoaiPhong;
        this.MaDonGiaPhong = MaDonGiaPhong;
        this.tien = tien;
        this.isvisible = isvisible;
        this.GiamGia = GiamGia;
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

    public String getMaTinhTrangPhong() {
        return MaTinhTrangPhong;
    }

    public void setMaTinhTrangPhong(String MaTinhTrangPhong) {
        this.MaTinhTrangPhong = MaTinhTrangPhong;
    }

    public String getTinhtrangphong() {
        return tinhtrangphong;
    }

    public void setTinhtrangphong(String tinhtrangphong) {
        this.tinhtrangphong = tinhtrangphong;
    }

    public String getMaLoaiPhong() {
        return MaLoaiPhong;
    }

    public void setMaLoaiPhong(String MaLoaiPhong) {
        this.MaLoaiPhong = MaLoaiPhong;
    }

    public String getTenLoaiPhong() {
        return TenLoaiPhong;
    }

    public void setTenLoaiPhong(String TenLoaiPhong) {
        this.TenLoaiPhong = TenLoaiPhong;
    }

    public String getMaDonGiaPhong() {
        return MaDonGiaPhong;
    }

    public void setMaDonGiaPhong(String MaDonGiaPhong) {
        this.MaDonGiaPhong = MaDonGiaPhong;
    }

    public BigDecimal getTien() {
        return tien;
    }

    public void setTien(BigDecimal tien) {
        this.tien = tien;
    }

    public int getIsvisible() {
        return isvisible;
    }

    public void setIsvisible(int isvisible) {
        this.isvisible = isvisible;
    }

    public int getGiamGia() {
        return GiamGia;
    }

    public void setGiamGia(int GiamGia) {
        this.GiamGia = GiamGia;
    }

   
    

    public String getFormattedDonGia() {
        DecimalFormat decimalFormat = new DecimalFormat("#,### VND");
        return decimalFormat.format(tien);
    }
    
    public String getFormattedPercentage(){
        DecimalFormat decimalFormat = new DecimalFormat("0%");
        return decimalFormat.format((double) GiamGia / 100); // Chia cho 100 để đưa về dạng phần trăm
    }
    
}
