package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


import entity.KhachHang;
import connectDB.ConnectDB;

public class daoKhachHang{

	public ArrayList<KhachHang> getAll() {
		// TODO Auto-generated method stubArrayList<HoaDonDichVuPhong> dsDVP = new ArrayList<HoaDonDichVuPhong>();
		ArrayList<KhachHang> dsKH = new ArrayList<KhachHang>();
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		try {
			String sql = "select * from KhachHang";
			Statement statement =con.createStatement();
			ResultSet rs = statement.executeQuery(sql);
			while(rs.next()) {
				dsKH.add(new KhachHang(rs.getString(1), rs.getString(2), rs.getBoolean(3), rs.getBoolean(4),
						rs.getString(5), rs.getString(6), rs.getInt(7), rs.getString(8)));
			}
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return dsKH;
	}
		
	
	public boolean add(KhachHang kh) {
		// TODO Auto-generated method stub
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		PreparedStatement stm = null;
		String sql = "INSERT INTO KhachHang (MaKH, TenKH, LoaiKH, GioiTinh, SoDienThoai, Email, SoGioDaThue, GhiChu)"
				+ "values(?,?,?,?,?,?,?,?)";
		try {
			stm = con.prepareStatement(sql);
			stm.setString(1, kh.getMaKH());
			stm.setString(2, kh.getTenKH());
			stm.setBoolean(3, kh.getLoaiKH());
			stm.setBoolean(4, kh.getGioiTinh());
			stm.setString(5, kh.getSdthoai());
			stm.setString(6, kh.getEmail());
			stm.setInt(7, kh.getSoGioDaThue());
			stm.setString(8, kh.getGhiChu());
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

	public boolean updateKhachHang(KhachHang kh) {
		// TODO Auto-generated method stub
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		PreparedStatement stm = null;
		String sql = "Update KhachHang set TenKH = ?, LoaiKH = ?, GioiTinh = ?, SoDienThoai = ?, Email = ?, SoGioDaThue = ?, GhiChu = ?\r\n"
				+ "where MaKH = ?";
		try {
			stm = con.prepareStatement(sql);		
			stm.setString(1, kh.getTenKH());
			stm.setBoolean(2, kh.getLoaiKH());
			stm.setBoolean(3, kh.getGioiTinh());
			stm.setString(4, kh.getSdthoai());
			stm.setString(5, kh.getEmail());
			stm.setInt(6, kh.getSoGioDaThue());
			stm.setString(7, kh.getGhiChu());
			stm.setString(8, kh.getMaKH());
			
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
	

	public void delete(String tenKH) {
		// TODO Auto-generated method stub
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		PreparedStatement stm = null;
		String sql = "DELETE from KhachHang where TenKhachHang = ?";
		try {
			stm = con.prepareStatement(sql);
			stm.setString(1, tenKH);
			
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
