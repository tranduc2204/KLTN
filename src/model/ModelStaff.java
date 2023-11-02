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
public class ModelStaff {
    Connect cn = new Connect();
    Connection conn;

    public ArrayList<ModelStaffv2> findALL() throws Exception {
        String sql = "select * from nhanvien where isvisible = '1' ";
        conn = cn.getConnection();
        PreparedStatement pstmt = conn.prepareStatement(sql);
        ResultSet rs = pstmt.executeQuery();
        ArrayList<ModelStaffv2> list = new ArrayList<>();
        while (rs.next()) {
            ModelStaffv2 nv = new ModelStaffv2();
            nv.setMaNV(rs.getString("MaNV"));
            nv.setHoNV(rs.getString("HoNV"));
            nv.setTenNV(rs.getString("TenNV"));
            nv.setGioitinh(rs.getInt("GioiTinh"));
            nv.setDiaChi(rs.getString("DiaChi"));
            nv.setNgaySinh(rs.getString("NgaySinh"));
            nv.setDienThoai(rs.getString("DienThoai"));
            nv.setEmail(rs.getString("Email"));
            nv.setNoiSinh(rs.getString("NoiSinh"));
            nv.setNgayVaoLam(rs.getString("NgayVaoLam"));

            list.add(nv);
        }
        return list;
    }

    public boolean insert(ModelStaffv2 nv) throws Exception {
        String sql = "insert into nhanvien (manv, Honv,Tennv,gioitinh,DiaChi,NgaySinh,DienThoai,email,noisinh,ngayvaolam) values (?,?,?,?,?,?,?,?,?,?)";

        conn = cn.getConnection();
        PreparedStatement pstmt = conn.prepareStatement(sql);

        pstmt.setString(1, nv.getMaNV());
        pstmt.setString(2, nv.getHoNV());
        pstmt.setString(3, nv.getTenNV());
        pstmt.setInt(4, nv.getGioitinh());
        pstmt.setString(5, nv.getDiaChi());
        pstmt.setString(6, nv.getNgaySinh());
        pstmt.setString(7, nv.getDienThoai());
        pstmt.setString(8, nv.getEmail());
        pstmt.setString(9, nv.getNoiSinh());
        pstmt.setString(10, nv.getNgayVaoLam());

        return pstmt.executeUpdate() > 0;

    }

    public ModelStaffv2 findByID(String Manv) throws Exception {
        String sql = "select * from nhanvien where manv =?";

        conn = cn.getConnection();
        PreparedStatement pstmt = conn.prepareStatement(sql);

        pstmt.setString(1, Manv);
        ResultSet rs = pstmt.executeQuery();

        if (rs.next()) {
            ModelStaffv2 nv = new ModelStaffv2();
            nv.setMaNV(rs.getString("MaNV"));
            nv.setHoNV(rs.getString("HoNV"));
            nv.setTenNV(rs.getString("TenNV"));
            nv.setGioitinh(rs.getInt("gioitinh"));
            nv.setDiaChi(rs.getString("diachi"));
            nv.setNgaySinh(rs.getString("ngaysinh"));
            nv.setDienThoai(rs.getString("dienthoai"));
            nv.setEmail(rs.getString("email"));
            nv.setNoiSinh(rs.getString("noisinh"));
            nv.setNgayVaoLam(rs.getString("ngayvaolam"));
            
            

            return nv;
        }
        return null;
    }
    public boolean update(ModelStaffv2 nv) throws Exception {
        String sql = "update nhanvien set HoNV =?,TenNV=?,gioitinh=?,DiaChi=?, NgaySinh=? , DienThoai=?, email=?, noisinh=?, ngayvaolam=? where manv = ?";

        conn = cn.getConnection();
        PreparedStatement pstmt = conn.prepareStatement(sql);

        pstmt.setString(10, nv.getMaNV());
        pstmt.setString(1, nv.getHoNV());
        pstmt.setString(2, nv.getTenNV());
        pstmt.setInt(3, nv.getGioitinh());
        pstmt.setString(4, nv.getDiaChi());
        pstmt.setString(5, nv.getNgaySinh());
        pstmt.setString(6, nv.getDienThoai());
        pstmt.setString(7, nv.getEmail());
        pstmt.setString(8, nv.getNoiSinh());
        pstmt.setString(9, nv.getNgayVaoLam());

        return pstmt.executeUpdate() > 0;

    }

    public boolean delete(String MaNV) throws Exception {
        String sql = "delete from nhanvien where manv= ?";

        conn = cn.getConnection();
        PreparedStatement pstmt = conn.prepareStatement(sql);

        pstmt.setString(1, MaNV);

        return pstmt.executeUpdate() > 0;

    }
    
//    public boolean deletecomeroot (ModelStaffv2 nv) throws Exception {
//        String sql = "update nhanvien set isvisible = '0' where manv = ?";
//
//        conn = cn.getConnection();
//        PreparedStatement pstmt = conn.prepareStatement(sql);
//
//        pstmt.setString(1, nv.getMaNV());
//
//        return pstmt.executeUpdate() > 0;
//
//    }
    public boolean deletecomeroot(ModelStaffv2 nv) throws Exception {
        String sql1 = "update HoaDonDV set isvisible = '0' where manv = ? ";
        String sql2 = "update PhieuDatPhong set isvisible = '0' where manv = ? ";
        String sql = "update nhanvien set isvisible = '0' where manv = ? ";

        conn = cn.getConnection();
        conn.setAutoCommit(false); // Tắt chế độ tự động commit

        try {
            PreparedStatement pstmt1 = conn.prepareStatement(sql1);
            pstmt1.setString(1, nv.getMaNV());

            int rowsAffected1 = pstmt1.executeUpdate(); // Số dòng bị ảnh hưởng bởi lệnh sql1
            
            PreparedStatement pstmt2 = conn.prepareStatement(sql2);
            pstmt2.setString(1, nv.getMaNV());

            int rowsAffected2 = pstmt2.executeUpdate(); // Số dòng bị ảnh hưởng bởi lệnh sql1
            

            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, nv.getMaNV());

            int rowsAffected = pstmt.executeUpdate(); // Số dòng bị ảnh hưởng bởi lệnh sql

            if (rowsAffected1 > 0 || rowsAffected > 0 || rowsAffected2 > 0 ) {
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
