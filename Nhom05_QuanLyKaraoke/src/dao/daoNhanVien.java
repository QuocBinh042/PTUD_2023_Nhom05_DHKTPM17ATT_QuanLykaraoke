package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import entity.KhachHang;
import entity.NhanVien;
import connectDB.ConnectDB;

public class daoNhanVien{

	public ArrayList<NhanVien> getAll() {
		// TODO Auto-generated method stubArrayList<HoaDonDichVuPhong> dsDVP = new ArrayList<HoaDonDichVuPhong>();
		ArrayList<NhanVien> dsNV = new ArrayList<NhanVien>();
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		try {
			String sql = "select * from KhachHang";
			Statement statement =con.createStatement();
			ResultSet rs = statement.executeQuery(sql);
			while(rs.next()) {
				dsNV.add(new NhanVien(rs.getString(1), rs.getString(2), rs.getDate(3), rs.getInt(4),
						rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8), rs.getInt(9)));
			}
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return dsNV;
	}
		
	
	public boolean add(NhanVien nv) {
		// TODO Auto-generated method stub
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		PreparedStatement stm = null;
		String sql = "INSERT INTO NhanVien (MaNV, TenNV, NamSinh, GioiTinh, SoDienThoai, CCCD, ChucVu, MatKhau, TinhTrangNV)"
				+ "values(?,?,?,?,?,?,?,?,?)";
		try {	
	        stm = con.prepareStatement(sql);
			stm.setString(1, nv.getMaNV());
			stm.setString(2, nv.getTenNV());
			stm.setDate(3, (Date) nv.getNamSinh());
			stm.setInt(4, nv.getGioiTinh());
			stm.setString(5, nv.getSdthoai());
			stm.setString(6, nv.getCccd());
			stm.setString(7, nv.getChucVu());
			stm.setString(8, nv.getMatKhau());
			stm.setInt(9, nv.getTinhTrangNV());
			System.out.println(stm);
			stm.executeUpdate();
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
			return false;
		}
		finally {
			close(stm);
		}
		return true;
	}

	public boolean updateNhanVien(NhanVien nv) {
		// TODO Auto-generated method stub
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		PreparedStatement stm = null;
		String sql = "Update NhanVien set TenNV = ?, NamSinh = ?, GioiTinh = ?, SoDienThoai = ?,CCCD = ?, ChucVu = ?, MatKhau = ?, TinhTrangNV = ?\r\n"
				+ "where MaNV = ?";
		try {
			stm.setString(1, nv.getMaNV());
			stm.setString(2, nv.getTenNV());
			stm.setDate(3, (Date) nv.getNamSinh());
			stm.setInt(4, nv.getGioiTinh());
			stm.setString(5, nv.getSdthoai());
			stm.setString(6, nv.getCccd());
			stm.setString(7, nv.getChucVu());
			stm.setString(8, nv.getMatKhau());
			stm.setInt(9, nv.getTinhTrangNV());
			
			stm.executeUpdate();
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
			return false;
		}
		finally {
			close(stm);
		}
		return true;
	}
	

	public void delete(String tenNV) {
		// TODO Auto-generated method stub
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		PreparedStatement stm = null;
		String sql = "DELETE from NhanVien where TenNhanVien = ?";
		try {
			stm = con.prepareStatement(sql);
			stm.setString(1, tenNV);
			
			stm.executeUpdate();
		} catch (SQLException e) {
			// TODO: handle exception
		}
	}
	 
	public void close(PreparedStatement stm) {
		if(stm!=null) {
			try {
				stm.close();
			} catch (SQLException e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		}
	}
	
}
