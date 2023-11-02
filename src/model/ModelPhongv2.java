/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author TeeDee
 */
public class ModelPhongv2 {
    private String MaPhong, TenPhong, MaTinhTrangPhong, MaLoaiPhong;
    private double tien;
   

    public ModelPhongv2() {
    }

    public ModelPhongv2(String MaPhong, String TenPhong, String MaTinhTrangPhong, String MaLoaiPhong, double tien) {
        this.MaPhong = MaPhong;
        this.TenPhong = TenPhong;
        this.MaTinhTrangPhong = MaTinhTrangPhong;
        this.MaLoaiPhong = MaLoaiPhong;
        this.tien = tien;
   
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

    public String getMaLoaiPhong() {
        return MaLoaiPhong;
    }

    public void setMaLoaiPhong(String MaLoaiPhong) {
        this.MaLoaiPhong = MaLoaiPhong;
    }

    public double getTien() {
        return tien;
    }

    public void setTien(double tien) {
        this.tien = tien;
    }


}
