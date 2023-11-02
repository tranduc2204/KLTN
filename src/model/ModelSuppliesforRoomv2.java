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
public class ModelSuppliesforRoomv2 {
    String MaVatTu, TenVatTu, MaLoaiPhong,TenLoaiPhong;
    int SoLuong;
    public ModelSuppliesforRoomv2() {
    }

    public ModelSuppliesforRoomv2(String MaVatTu, String TenVatTu, String MaLoaiPhong, String TenLoaiPhong, int SoLuong) {
        this.MaVatTu = MaVatTu;
        this.TenVatTu = TenVatTu;
        this.MaLoaiPhong = MaLoaiPhong;
        this.TenLoaiPhong = TenLoaiPhong;
        this.SoLuong = SoLuong;
    }

    public String getMaVatTu() {
        return MaVatTu;
    }

    public void setMaVatTu(String MaVatTu) {
        this.MaVatTu = MaVatTu;
    }

    public String getTenVatTu() {
        return TenVatTu;
    }

    public void setTenVatTu(String TenVatTu) {
        this.TenVatTu = TenVatTu;
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

    public int getSoLuong() {
        return SoLuong;
    }

    public void setSoLuong(int SoLuong) {
        this.SoLuong = SoLuong;
    }
    
}
