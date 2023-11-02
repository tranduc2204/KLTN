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
public class ModelRoomLPv2 {
    private String MaLoaiPhong, TenLoaiPhong;

    public ModelRoomLPv2() {
    }

    public ModelRoomLPv2(String MaLoaiPhong, String TenLoaiPhong) {
        this.MaLoaiPhong = MaLoaiPhong;
        this.TenLoaiPhong = TenLoaiPhong;
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
}
