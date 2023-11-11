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
			String sql = "select * from Phong p inner join LoaiPhong lp on p.MaLP = lp.MaLP "
					+ "inner join KhuyenMai km on p.MaKM = km.MaKM"; 
			ResultSet rs = connect.createStatement().executeQuery(sql);
			while(rs.next()) {
				KhuyenMai km = new KhuyenMai(rs.getString(11), rs.getDouble(12), rs.getDate(13).toLocalDate(), rs.getDate(14).toLocalDate(),
						rs.getString(15), rs.getString(16));
				
				LoaiPhong lp = new LoaiPhong(rs.getString(7), rs.getString(8), rs.getInt(9), rs.getDouble(10));
				Phong phong = new Phong(rs.getString(1), rs.getString(2), km, lp, rs.getString(5), rs.getString(6));
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
					+ "inner join KhuyenMai km on p.MaKM = km.MaKhuyenMai "
					+ "Where TenLP = ?";
			statement = connect.prepareStatement(sql);
			statement.setString(1, loaiPhong);
			ResultSet rs = statement.executeQuery();
			
			while(rs.next()) {
				KhuyenMai km = new KhuyenMai(rs.getString(11), rs.getDouble(12), rs.getDate(13).toLocalDate(), rs.getDate(14).toLocalDate(),
						rs.getString(15), rs.getString(16));
				
				LoaiPhong lp = new LoaiPhong(rs.getString(7), rs.getString(8), rs.getInt(9), rs.getDouble(10));
				Phong phong = new Phong(rs.getString(1), rs.getString(2), km, lp, rs.getString(5), rs.getString(6));
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
					+ "inner join KhuyenMai km on p.MaKM = km.MaKhuyenMai "
					+ "Where TinhTrangPhong = ?";
			statement = connect.prepareStatement(sql);
			statement.setString(1, tinhTrangPhong);
			ResultSet rs = statement.executeQuery();
			
			while(rs.next()) {
				KhuyenMai km = new KhuyenMai(rs.getString(11), rs.getDouble(12), rs.getDate(13).toLocalDate(), rs.getDate(14).toLocalDate(),
						rs.getString(15), rs.getString(16));
				
				LoaiPhong lp = new LoaiPhong(rs.getString(7), rs.getString(8), rs.getInt(9), rs.getDouble(10));
				Phong phong = new Phong(rs.getString(1), rs.getString(2), km, lp, rs.getString(5), rs.getString(6));
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
					+ "inner join KhuyenMai km on p.MaKM = km.MaKhuyenMai "
					+ "Where SucChua = ?";
			statement = connect.prepareStatement(sql);
			statement.setInt(1, soNguoi);
			ResultSet rs = statement.executeQuery();
			
			while(rs.next()) {
				KhuyenMai km = new KhuyenMai(rs.getString(11), rs.getDouble(12), rs.getDate(13).toLocalDate(), rs.getDate(14).toLocalDate(),
						rs.getString(15), rs.getString(16));
				
				LoaiPhong lp = new LoaiPhong(rs.getString(7), rs.getString(8), rs.getInt(9), rs.getDouble(10));
				Phong phong = new Phong(rs.getString(1), rs.getString(2), km, lp, rs.getString(5), rs.getString(6));
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
					+ "inner join KhuyenMai km on p.MaKM = km.MaKhuyenMai "
					+ "Where TenPhong = ?";
			statement = connect.prepareStatement(sql);
			statement.setString(1, tenPhong);
			ResultSet rs = statement.executeQuery();
			
			while(rs.next()) {
				KhuyenMai km = new KhuyenMai(rs.getString(11), rs.getDouble(12), rs.getDate(13).toLocalDate(), rs.getDate(14).toLocalDate(),
						rs.getString(15), rs.getString(16));
				
				LoaiPhong lp = new LoaiPhong(rs.getString(7), rs.getString(8), rs.getInt(9), rs.getDouble(10));
				Phong phong = new Phong(rs.getString(1), rs.getString(2), km, lp, rs.getString(5), rs.getString(6));
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
					+ "inner join KhuyenMai km on p.MaKM = km.MaKhuyenMai "
					+ "Where TenPhong LIKE ?";
			statement = connect.prepareStatement(sql);
			statement.setString(1, "%" + tenPhong + "%");
			ResultSet rs = statement.executeQuery();
			
			while(rs.next()) {
				KhuyenMai km = new KhuyenMai(rs.getString(11), rs.getDouble(12), rs.getDate(13).toLocalDate(), rs.getDate(14).toLocalDate(),
						rs.getString(15), rs.getString(16));
				
				LoaiPhong lp = new LoaiPhong(rs.getString(7), rs.getString(8), rs.getInt(9), rs.getDouble(10));
				Phong phong = new Phong(rs.getString(1), rs.getString(2), km, lp, rs.getString(5), rs.getString(6));
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
