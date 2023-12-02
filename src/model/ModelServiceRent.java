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
public class ModelServiceRent {
    Connect cn = new Connect();
    Connection conn;
    
    public ArrayList<ModelServiceRentv2> findALL() throws Exception {
        String sql = "select dv.MaDV, TenDichVu, nv.MaNV, TenNV, kh.MaKH, TenKH,NgayLapHD, DonGia, SL, ((DonGia*SL)*0.1) as VAT,((DonGia*SL) +  ((DonGia*SL)*0.1)) AS GiaHD  \n" +
"                from HoaDonDV hddv join NHANVIEN nv on hddv.MaNV = nv.MaNV join KHACHHANG kh on hddv.MaKH = kh.MaKH  \n" +
"                join DICHVU dv on dv.MaDV = hddv.MaDV where hddv.isvisible = '1' ";
        conn = cn.getConnection();//where ptp.isvisible = '1' 
        PreparedStatement pstmt = conn.prepareStatement(sql);
        ResultSet rs = pstmt.executeQuery();
        ArrayList<ModelServiceRentv2> list = new ArrayList<>();
        while (rs.next()) {
            ModelServiceRentv2 co = new ModelServiceRentv2();
            co.setMaDV(rs.getString("MaDV"));
            co.setTenDichVu(rs.getString("TenDichVu"));
            co.setMaNV(rs.getString("MaNV"));
            co.setTenNhanVien(rs.getString("TenNV"));
            co.setMaKH(rs.getString("MaKH"));
            co.setTenKH(rs.getString("TenKH"));
            co.setNgayLapHD(rs.getString("NgayLapHD"));
            co.setGia(rs.getBigDecimal("DonGia"));
            co.setSL(rs.getInt("SL"));
            co.setVAT(rs.getBigDecimal("VAT"));
            co.setGiaHD(rs.getBigDecimal("GiaHD"));
           
            list.add(co);
        }
        return list;
    }
    
    public ModelServiceRentv2 findByID_MADV(String MaDV) throws Exception {
//        String sql = "select * from PhieuDatPhong where MaPhieuDatPhong =?";

        String sql = "select TenDichVu, DonGia from DICHVU where MaDV =? ";  
        
        conn = cn.getConnection();
        PreparedStatement pstmt = conn.prepareStatement(sql);

        pstmt.setString(1, MaDV);
        ResultSet rs = pstmt.executeQuery();

        if (rs.next()) {
            ModelServiceRentv2 tp = new ModelServiceRentv2();

            tp.setTenDichVu(rs.getString("TenDichVu"));
            tp.setGia(rs.getBigDecimal("DonGia"));
            
            return tp;
        }
        return null;
    }
    
    public ModelServiceRentv2 findByID_MANV(String MaNV) throws Exception {
//        String sql = "select * from PhieuDatPhong where MaPhieuDatPhong =?";

        String sql = "select TenNV from NhanVien where MaNV =? ";  
        
        conn = cn.getConnection();
        PreparedStatement pstmt = conn.prepareStatement(sql);

        pstmt.setString(1, MaNV);
        ResultSet rs = pstmt.executeQuery();

        if (rs.next()) {
            ModelServiceRentv2 tp = new ModelServiceRentv2();

            tp.setTenNhanVien(rs.getString("TenNV"));
            
            return tp;
        }
        return null;
    }
    
    public ModelServiceRentv2 findByID_MAKH(String MaKH) throws Exception {
//        String sql = "select * from PhieuDatPhong where MaPhieuDatPhong =?";

        String sql = "select TenKH from KhachHang where MaKH =? ";  
        
        conn = cn.getConnection();
        PreparedStatement pstmt = conn.prepareStatement(sql);

        pstmt.setString(1, MaKH);
        ResultSet rs = pstmt.executeQuery();

        if (rs.next()) {
            ModelServiceRentv2 tp = new ModelServiceRentv2();

            tp.setTenKH(rs.getString("TenKH"));
            
            return tp;
        }
        return null;
    }

//    
    public boolean insert(ModelServiceRentv2 rt) throws Exception {
        String sql = "insert into HoaDonDV (MaDV, MaNV, MaKH, NgayLapHD, SL) values (?,?,?,?,?)";

        conn = cn.getConnection();
        PreparedStatement pstmt = conn.prepareStatement(sql);
        
        pstmt.setString(1, rt.getMaDV());
        pstmt.setString(2, rt.getMaNV());
        pstmt.setString(3, rt.getMaKH());
        pstmt.setString(4, rt.getNgayLapHD());
        pstmt.setInt(5, rt.getSL());

        return pstmt.executeUpdate() > 0;

    }
    public boolean update(ModelServiceRentv2 rt) throws Exception {
        String sql = "update HoaDonDV set NgayLapHD = ? , SL = ?  where MaDV = ? and MaNV = ? and MaKH = ? ";

        conn = cn.getConnection();
        PreparedStatement pstmt = conn.prepareStatement(sql);

        pstmt.setString(1, rt.getNgayLapHD());
        pstmt.setInt(2, rt.getSL());
        pstmt.setString(3, rt.getMaDV());
        pstmt.setString(4, rt.getMaNV());
        pstmt.setString(5, rt.getMaKH());
        

        return pstmt.executeUpdate() > 0;
  
    }
    
    public boolean deletecomeroot(ModelServiceRentv2 rt) throws Exception {
       
        String sql = "update HoaDonDV set isvisible = '0' where MaDV = ? and MaNV = ? and MaKH = ? ";
        

        conn = cn.getConnection();
        conn.setAutoCommit(false); // Tắt chế độ tự động commit

        try {

            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, rt.getMaDV());
            pstmt.setString(2, rt.getMaNV());
            pstmt.setString(3, rt.getMaKH());

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
