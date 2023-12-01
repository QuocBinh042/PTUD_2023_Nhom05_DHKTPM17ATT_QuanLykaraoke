package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import connectDB.ConnectDB;
import entity.DichVu;
import entity.LoaiPhong;

public class DAOLoaiPhong {
	// lay toan bo loai phong
	public ArrayList<LoaiPhong> getAllLoaiPhong() {
		ArrayList<LoaiPhong> dsLoaiPhong = new ArrayList<LoaiPhong>();
		ConnectDB.getInstance();
		Connection connect = ConnectDB.getConnection();
		try {
			String sql = "select *from LoaiPhong";
			Statement statement = connect.createStatement();
			ResultSet rs = statement.executeQuery(sql);
			while (rs.next()) {
				dsLoaiPhong.add(new LoaiPhong(rs.getString(1), rs.getString(2), rs.getInt(3), rs.getDouble(4)));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dsLoaiPhong;
	}

	// Lay loai phong theo combobox suc chua
	public ArrayList<LoaiPhong> getCBSC(String sucChua) {
		ArrayList<LoaiPhong> dsLoaiPhong = new ArrayList<LoaiPhong>();
		ConnectDB.getInstance();
		Connection connect = ConnectDB.getConnection();
		String sql = "";
		if (sucChua.equalsIgnoreCase("Tất cả")) {
			sql = "select *from LoaiPhong";
		} else if (sucChua.equalsIgnoreCase("10")) {
			sql = "select *from LoaiPhong where SucChua = 10";
		} else if (sucChua.equalsIgnoreCase("15")) {
			sql = "select *from LoaiPhong where SucChua = 15";
		} else if (sucChua.equalsIgnoreCase("20")) {
			sql = "select *from LoaiPhong where SucChua = 20";
		}
		try {
			Statement statement = connect.createStatement();
			ResultSet rs = statement.executeQuery(sql);
			while (rs.next()) {
				dsLoaiPhong.add(new LoaiPhong(rs.getString(1), rs.getString(2), rs.getInt(3), rs.getDouble(4)));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dsLoaiPhong;
	}

	// Lay loai phong theo combobox loai phong
	public ArrayList<LoaiPhong> getCBLP(String loaiPhong) {
		ArrayList<LoaiPhong> dsLoaiPhong = new ArrayList<LoaiPhong>();
		ConnectDB.getInstance();
		Connection connect = ConnectDB.getConnection();
		String sql = "";
		if (loaiPhong.equalsIgnoreCase("Tất cả")) {
			sql = "select *from LoaiPhong";
		} else if (loaiPhong.equalsIgnoreCase("Thường")) {
			sql = "select *from LoaiPhong where TenLP = N'Thường'";
		} else if (loaiPhong.equalsIgnoreCase("VIP")) {
			sql = "select *from LoaiPhong where TenLP = N'VIP'";
		}
		try {
			Statement statement = connect.createStatement();
			ResultSet rs = statement.executeQuery(sql);
			while (rs.next()) {
				dsLoaiPhong.add(new LoaiPhong(rs.getString(1), rs.getString(2), rs.getInt(3), rs.getDouble(4)));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dsLoaiPhong;
	}

	// Them loai phong
	public boolean add(LoaiPhong lp) {
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		PreparedStatement stm = null;
		String sql = "INSERT INTO LoaiPhong (MaLP, TenLP, SucChua, GiaPhong) " + "values(?,?,?,?)";
		try {
			stm = con.prepareStatement(sql);
			stm.setString(1, lp.getMaLoaiPhong());
			stm.setString(2, lp.getTenLoaiPhong());
			stm.setInt(3, lp.getSucChua());
			stm.setDouble(4, lp.getGiaLoaiPhong());
			System.out.println(stm);
			stm.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	// Cap nhat dich vu
	public boolean update(LoaiPhong lp) {
		// TODO Auto-generated method stub
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		PreparedStatement stm = null;
		String sql = "update LoaiPhong\r\n" + "set TenLP = ?, SucChua = ?, GiaPhong = ?\r\n" + "where MaLP = ?";

		try {
			stm = con.prepareStatement(sql);
			stm.setString(1, lp.getTenLoaiPhong());
			stm.setInt(2, lp.getSucChua());
			stm.setDouble(3, lp.getGiaLoaiPhong());
			stm.setString(4, lp.getMaLoaiPhong());
			stm.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
}
