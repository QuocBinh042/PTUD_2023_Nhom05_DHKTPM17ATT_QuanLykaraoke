package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import connectDB.ConnectDB;
import entity.KhuyenMai;
import entity.LoaiPhong;
import entity.Phong;

public class daoPhong {
	public daoPhong() {}
	
	public ArrayList<Phong> getAllDataForTableDatPhong() {
		ArrayList<Phong> dsPhong = new ArrayList<>();
		try {
			ConnectDB.getInstance();
			Connection connect = ConnectDB.getConnection();
			String sql = "select * from Phong p inner join LoaiPhong lp on p.MaLP = lp.MaLP "; 
			ResultSet rs = connect.createStatement().executeQuery(sql);
			while(rs.next()) {				
				LoaiPhong lp = new LoaiPhong(rs.getString(6), rs.getString(7), rs.getInt(8), rs.getDouble(9));
				Phong phong = new Phong(rs.getString(1), rs.getString(2), lp, rs.getString(4), rs.getString(5));
				dsPhong.add(phong);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return dsPhong;
	}
	
	public ArrayList<Phong> timKiemPhongTheoLoaiPhong(String loaiPhong) {
		ArrayList<Phong> dsPhong = new ArrayList<>();
		try {
			ConnectDB.getInstance();
			Connection connect = ConnectDB.getConnection();
			PreparedStatement statement = null;
			String sql = "select * from Phong p inner join LoaiPhong lp on p.MaLP = lp.MaLP "
					+ "Where TenLP = ?";
			statement = connect.prepareStatement(sql);
			statement.setString(1, loaiPhong);
			ResultSet rs = statement.executeQuery();
			
			while(rs.next()) {
				
				LoaiPhong lp = new LoaiPhong(rs.getString(6), rs.getString(7), rs.getInt(8), rs.getDouble(9));
				Phong phong = new Phong(rs.getString(1), rs.getString(2), lp, rs.getString(4), rs.getString(5));
				dsPhong.add(phong);
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			e.printStackTrace();
		}
		return dsPhong;
	}
	
	public ArrayList<Phong> timKiemPhongTheoTinhTrangPhong(String tinhTrangPhong) {
		ArrayList<Phong> dsPhong = new ArrayList<>();
		try {
			ConnectDB.getInstance();
			Connection connect = ConnectDB.getConnection();
			PreparedStatement statement = null;
			String sql = "select * from Phong p inner join LoaiPhong lp on p.MaLP = lp.MaLP "
					+ "Where TinhTrangPhong = ?";
			statement = connect.prepareStatement(sql);
			statement.setString(1, tinhTrangPhong);
			ResultSet rs = statement.executeQuery();
			
			while(rs.next()) {				
				LoaiPhong lp = new LoaiPhong(rs.getString(6), rs.getString(7), rs.getInt(8), rs.getDouble(9));
				Phong phong = new Phong(rs.getString(1), rs.getString(2), lp, rs.getString(4), rs.getString(5));
				dsPhong.add(phong);
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			e.printStackTrace();
		}
		return dsPhong;
	}
	
	public ArrayList<Phong> timKiemPhongTheoSoNguoi(int soNguoi) {
		ArrayList<Phong> dsPhong = new ArrayList<>();
		try {
			ConnectDB.getInstance();
			Connection connect = ConnectDB.getConnection();
			PreparedStatement statement = null;
			String sql = "select * from Phong p inner join LoaiPhong lp on p.MaLP = lp.MaLP "
					+ "Where SucChua = ?";
			statement = connect.prepareStatement(sql);
			statement.setInt(1, soNguoi);
			ResultSet rs = statement.executeQuery();
			
			while(rs.next()) {
				LoaiPhong lp = new LoaiPhong(rs.getString(6), rs.getString(7), rs.getInt(8), rs.getDouble(9));
				Phong phong = new Phong(rs.getString(1), rs.getString(2), lp, rs.getString(4), rs.getString(5));
				dsPhong.add(phong);
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			e.printStackTrace();
		}
		return dsPhong;
	}
	
	public ArrayList<Phong> timKiemPhongChinhXacTheoTenPhong(String tenPhong) {
		ArrayList<Phong> dsPhong = new ArrayList<>();
		try {
			ConnectDB.getInstance();
			Connection connect = ConnectDB.getConnection();
			PreparedStatement statement = null;
			String sql = "select * from Phong p inner join LoaiPhong lp on p.MaLP = lp.MaLP "
					+ "Where TenPhong = ?";
			statement = connect.prepareStatement(sql);
			statement.setString(1, tenPhong);
			ResultSet rs = statement.executeQuery();
			
			while(rs.next()) {
				
				LoaiPhong lp = new LoaiPhong(rs.getString(6), rs.getString(7), rs.getInt(8), rs.getDouble(9));
				Phong phong = new Phong(rs.getString(1), rs.getString(2), lp, rs.getString(4), rs.getString(5));
				dsPhong.add(phong);
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			e.printStackTrace();
		}
		return dsPhong;
	}
	
	public ArrayList<Phong> timKiemPhongTheoTenPhong(String tenPhong) {
		ArrayList<Phong> dsPhong = new ArrayList<>();
		try {
			ConnectDB.getInstance();
			Connection connect = ConnectDB.getConnection();
			PreparedStatement statement = null;
//			tenPhong = tenPhong.replace("%", "!%");
			String sql = "select * from Phong p inner join LoaiPhong lp on p.MaLP = lp.MaLP "
					+ "Where TenPhong LIKE ?";
			statement = connect.prepareStatement(sql);
			statement.setString(1, "%" + tenPhong + "%");
			ResultSet rs = statement.executeQuery();
			
			while(rs.next()) {
				LoaiPhong lp = new LoaiPhong(rs.getString(6), rs.getString(7), rs.getInt(8), rs.getDouble(9));
				Phong phong = new Phong(rs.getString(1), rs.getString(2), lp, rs.getString(4), rs.getString(5));
				dsPhong.add(phong);
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			e.printStackTrace();
		}
		return dsPhong;
	}
}
