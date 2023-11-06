/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import connect.Connect;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 *
 * @author TeeDee
 */
public class ModelCheckOut {
    Connect cn = new Connect();
    Connection conn;
    
    public ArrayList<ModelCheckOutv2> findALL() throws Exception {
        String sql = "select MaHoaDonPhong, ptp.MaPhieuThuePhong, NgayThuePhong, NgayDatPhong, TenPhong, lp.TenLoaiPhong,"
                + " Tien, ((DATEDIFF(DAY, NgayDatPhong, NgayThuePhong)) *Tien) AS GiaHD,NgayLapHoaDon "
                + "from HoaDonPhong hdp join PhieuThuePhong ptp on hdp.MaPhieuThuePhong = ptp.MaPhieuThuePhong "
                + "join PhieuDatPhong pdp on pdp.MaPhieuDatPhong = ptp.MaPhieuDatPhong join phong p "
                + "on p.MaPhong = pdp.MaPhong join LOAIPHONG lp on p.MaLoaiPhong = lp.MaLoaiPhong where hdp.isvisible = '1' ";
        conn = cn.getConnection();//where ptp.isvisible = '1' 
        PreparedStatement pstmt = conn.prepareStatement(sql);
        ResultSet rs = pstmt.executeQuery();
        ArrayList<ModelCheckOutv2> list = new ArrayList<>();
        while (rs.next()) {
            ModelCheckOutv2 co = new ModelCheckOutv2();
            co.setMaHoaDonPhong(rs.getString("MaHoaDonPhong"));
            co.setMaPhieuThuePhong(rs.getString("MaPhieuThuePhong"));
            co.setNgayThuePhong(rs.getString("NgayThuePhong"));
            co.setNgayDatPhong(rs.getString("NgayDatPhong"));
            co.setTenPhong(rs.getString("TenPhong"));
            co.setLoaiPhong(rs.getString("TenLoaiPhong"));
    
            co.setGia(rs.getBigDecimal("Tien"));
            co.setGiaHD(rs.getBigDecimal("GiaHD"));
            co.setNgayLapHoaDon(rs.getString("NgayLapHoaDon"));
            list.add(co);
        }
        return list;
    }
    
    public ModelCheckOutv2 findByID(String Maphieuthuephong) throws Exception {
        String sql = "select * from PhieuThuePhong where MaPhieuThuePhong =?";

        conn = cn.getConnection();
        PreparedStatement pstmt = conn.prepareStatement(sql);

        pstmt.setString(1, Maphieuthuephong);
        ResultSet rs = pstmt.executeQuery();

        if (rs.next()) {
            ModelCheckOutv2 tp = new ModelCheckOutv2();
            tp.setMaPhieuThuePhong(rs.getString("MaPhieuThuePhong"));
            tp.setNgayThuePhong(rs.getString("NgayThuePhong"));
           

            return tp;
        }
        return null;
    }
//    
    public boolean insert(ModelCheckOutv2 rt) throws Exception {
        String sql = "insert into HoaDonPhong (MaHoaDonPhong, NgayLapHoaDon, MaPhieuThuePhong) values (?,?,?)";

        conn = cn.getConnection();
        PreparedStatement pstmt = conn.prepareStatement(sql);
        
        pstmt.setString(1, rt.getMaHoaDonPhong());
        pstmt.setString(2, rt.getNgayLapHoaDon());
        pstmt.setString(3, rt.getMaPhieuThuePhong());

        return pstmt.executeUpdate() > 0;

    }
    public boolean update(ModelCheckOutv2 rt) throws Exception {
        String sql = "update HoaDonPhong set NgayLapHoaDon = ? , MaPhieuThuePhong= ?  where MaHoaDonPhong = ? ";

        conn = cn.getConnection();
        PreparedStatement pstmt = conn.prepareStatement(sql);

        
        pstmt.setString(3, rt.getMaHoaDonPhong());
        pstmt.setString(1, rt.getNgayLapHoaDon());
        pstmt.setString(2, rt.getMaPhieuThuePhong());

        return pstmt.executeUpdate() > 0;
  
    }
    
    public boolean deletecomeroot(ModelCheckOutv2 rt) throws Exception {
       
        String sql = "update HoaDonPhong set isvisible = '0' where MaHoaDonPhong = ? ";
        

        conn = cn.getConnection();
        conn.setAutoCommit(false); // Tắt chế độ tự động commit

        try {

            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, rt.getMaHoaDonPhong());

            int rowsAffected = pstmt.executeUpdate(); // Số dòng bị ảnh hưởng bởi lệnh sql

            if (rowsAffected > 0) {
                conn.commit(); // Commit thay đổi nếu cả hai lệnh thành công
                System.out.println("ok");
                return true;
                
            } else {
                conn.rollback(); // Rollback nếu có lỗi
                System.out.println("no");
                return false;
            }
        } catch (Exception e) {
            conn.rollback(); // Rollback nếu có lỗi
            throw e;
        } finally {
            conn.setAutoCommit(true); // Bật chế độ tự động commit trở lại
        }
    }
}
