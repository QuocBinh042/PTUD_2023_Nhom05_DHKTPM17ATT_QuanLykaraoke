package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import connectDB.ConnectDB;
import entity.KhuyenMai;

public class DAOKhuyenMai {
	public ArrayList<KhuyenMai> layDSKhuyenMai() {
		ArrayList<KhuyenMai> dsKhuyenMai = new ArrayList<KhuyenMai>();
		ConnectDB.getInstance();
		Connection connect = ConnectDB.getConnection();
		try {
			String sql = "select *from KhuyenMai";
			Statement statement = connect.createStatement();
			ResultSet rs = statement.executeQuery(sql);
			while (rs.next()) {
				dsKhuyenMai.add(new KhuyenMai(rs.getString(1), rs.getDouble(2), rs.getDate(3), rs.getDate(4),
						rs.getString(5), rs.getBoolean(6)));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dsKhuyenMai;
	}

	public KhuyenMai layKhuyenMaiTheoMa(String maKhuyenMai) {
	    KhuyenMai khuyenMai = null;
	    ConnectDB.getInstance();
	    Connection connect = ConnectDB.getConnection();
	    try {
	        String sql = "SELECT * FROM KhuyenMai WHERE MaKM=?";
	        PreparedStatement preparedStatement = connect.prepareStatement(sql);
	        preparedStatement.setString(1, maKhuyenMai);
	        ResultSet rs = preparedStatement.executeQuery();
	        if (rs.next()) {
	            khuyenMai = new KhuyenMai(rs.getString(1), rs.getDouble(2), rs.getDate(3), rs.getDate(4),
	                    rs.getString(5), rs.getBoolean(6));
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	    return khuyenMai;
	}

	public ArrayList<KhuyenMai> layDSKhuyenMaiTrongKhoangThoiGian(java.util.Date date, java.util.Date date2) {
	    ArrayList<KhuyenMai> dsKhuyenMai = new ArrayList<KhuyenMai>();
	    ConnectDB.getInstance();
	    Connection connect = ConnectDB.getConnection();
	    try {
	        String sql = "SELECT * FROM KhuyenMai WHERE NgayBatDau >= ? AND NgayKetThuc <= ?";
	        PreparedStatement preparedStatement = connect.prepareStatement(sql);
	        java.sql.Date sqlNgayBD = new java.sql.Date(date.getTime());
			java.sql.Date sqlNgayKT = new java.sql.Date(date2.getTime());
	        preparedStatement.setDate(1, sqlNgayBD);
	        preparedStatement.setDate(2, sqlNgayKT);
	        ResultSet rs = preparedStatement.executeQuery();
	        while (rs.next()) {
	            dsKhuyenMai.add(new KhuyenMai(rs.getString(1), rs.getDouble(2), rs.getDate(3), rs.getDate(4),
	                    rs.getString(5), rs.getBoolean(6)));
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	    return dsKhuyenMai;
	}

	public boolean themKhuyenMai(KhuyenMai km) {
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		PreparedStatement stm = null;
		String sql = "INSERT INTO KhuyenMai(MaKM, PhanTramGiam, NgayBatDau, NgayKetThuc, MoTa, TrangThaiKM)"
				+ "values(?,?,?,?,?,?)";
		try {
			stm = con.prepareStatement(sql);
			java.sql.Date sqlNgayBD = new java.sql.Date(km.getNgayBD().getTime());
			java.sql.Date sqlNgayKT = new java.sql.Date(km.getNgayKT().getTime());
			stm.setString(1, km.getMaKM());
			stm.setDouble(2, km.getPhanTramGiam());
			stm.setDate(3, sqlNgayBD);
			stm.setDate(4, sqlNgayKT);
			stm.setString(5, km.getMoTa());
			stm.setBoolean(6, km.getTrangThai());
			System.out.println(stm);
			stm.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	public boolean capNhat(KhuyenMai km) {
		// TODO Auto-generated method stub
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		PreparedStatement stm = null;
		String sql = "update KhuyenMai \r\n"
				+ "set PhanTramGiam = ?, NgayBatDau = ?, NgayKetThuc = ?, MoTa = ?, TrangThaiKM = ?\r\n"
				+ "where MaKM = ?";

		try {
			stm = con.prepareStatement(sql);
			java.sql.Date sqlNgayBD = new java.sql.Date(km.getNgayBD().getTime());
			java.sql.Date sqlNgayKT = new java.sql.Date(km.getNgayKT().getTime());
			stm.setDouble(1, km.getPhanTramGiam());
			stm.setDate(2, sqlNgayBD);
			stm.setDate(3, sqlNgayKT);
			stm.setString(4, km.getMoTa());
			stm.setBoolean(5, km.getTrangThai());
			stm.setString(6, km.getMaKM());
			stm.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	public void xoaKM(String maKM) {
		// TODO Auto-generated method stub
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		PreparedStatement stm = null;
		String sql = "DELETE from KhuyenMai " + "where MaKM = ?";
		try {
			stm = con.prepareStatement(sql);
			stm.setString(1, maKM);

			stm.executeUpdate();
		} catch (SQLException e) {
			// TODO: handle exception
		}
	}

	public void close(PreparedStatement stm) {
		if (stm != null) {
			try {
				stm.close();
			} catch (SQLException e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		}
	}
}
