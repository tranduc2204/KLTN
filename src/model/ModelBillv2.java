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
public class ModelBillv2 {
    private String mahd, machd , manv, tennv, makh, tenkh, ngaythue, ngaytra,  dichvu;
    private double giaphong, giadichvu;

    public ModelBillv2(String mahd, String machd, String manv, String tennv, String makh, String tenkh, String ngaythue, String ngaytra, String dichvu, double giaphong, double giadichvu) {
        this.mahd = mahd;
        this.machd = machd;
        this.manv = manv;
        this.tennv = tennv;
        this.makh = makh;
        this.tenkh = tenkh;
        this.ngaythue = ngaythue;
        this.ngaytra = ngaytra;
        this.dichvu = dichvu;
        this.giaphong = giaphong;
        this.giadichvu = giadichvu;
    }

    public ModelBillv2() {
    }

    public String getMahd() {
        return mahd;
    }

    public void setMahd(String mahd) {
        this.mahd = mahd;
    }

    public String getMachd() {
        return machd;
    }

    public void setMachd(String machd) {
        this.machd = machd;
    }

    public String getManv() {
        return manv;
    }

    public void setManv(String manv) {
        this.manv = manv;
    }

    public String getTennv() {
        return tennv;
    }

    public void setTennv(String tennv) {
        this.tennv = tennv;
    }

    public String getMakh() {
        return makh;
    }

    public void setMakh(String makh) {
        this.makh = makh;
    }

    public String getTenkh() {
        return tenkh;
    }

    public void setTenkh(String tenkh) {
        this.tenkh = tenkh;
    }

    public String getNgaythue() {
        return ngaythue;
    }

    public void setNgaythue(String ngaythue) {
        this.ngaythue = ngaythue;
    }

    public String getNgaytra() {
        return ngaytra;
    }

    public void setNgaytra(String ngaytra) {
        this.ngaytra = ngaytra;
    }

    public String getDichvu() {
        return dichvu;
    }

    public void setDichvu(String dichvu) {
        this.dichvu = dichvu;
    }

    public double getGiaphong() {
        return giaphong;
    }

    public void setGiaphong(double giaphong) {
        this.giaphong = giaphong;
    }

    public double getGiadichvu() {
        return giadichvu;
    }

    public void setGiadichvu(double giadichvu) {
        this.giadichvu = giadichvu;
    }

}
