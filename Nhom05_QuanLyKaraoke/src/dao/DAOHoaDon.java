package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import connectDB.ConnectDB;
import entity.HoaDon;
import entity.KhachHang;
import entity.KhuyenMai;
import entity.NhanVien;
import entity.PhieuDatPhong;

public class DAOHoaDon {
	public boolean createHD(HoaDon hoaDon) {
		ConnectDB.getInstance();
		Connection connect = ConnectDB.getConnection();
		PreparedStatement pre = null;
		int n = 0;
		try {
			Date sqlNgayThanhToan = new Date(hoaDon.getNgayThanhToan().getTime());
			pre = connect.prepareStatement("insert into " + "HoaDon values(?,?,?,?,?,?,?)");
			pre.setString(1, hoaDon.getMaHoaDon());
			pre.setTime(2, Time.valueOf(hoaDon.getGioThanhToan()));
			pre.setDate(3, sqlNgayThanhToan);
			pre.setString(4, hoaDon.getNv().getMaNV());
			pre.setString(5, hoaDon.getKh().getMaKH());
			pre.setString(6, hoaDon.getKm().getMaKM());
			pre.setDouble(7, hoaDon.getTongHoaDon());
			n = pre.executeUpdate();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return n > 0;
	}

	public ArrayList<HoaDon> getAllDataHD() {
		ArrayList<HoaDon> dsHD = new ArrayList<>();
		try {
			ConnectDB.getInstance();
			Connection connect = ConnectDB.getConnection();
			String sql = "select * from HoaDon hd inner join NhanVien nv on hd.MaNV = nv.MaNV "
					+ "inner join KhachHang kh on hd.MaKH = kh.MaKH " + "inner join KhuyenMai km on hd.MaKM = km.MaKM";
			ResultSet rs = connect.createStatement().executeQuery(sql);
			while (rs.next()) {
				NhanVien nv = new NhanVien(rs.getString(8), rs.getString(9), rs.getDate(10), rs.getBoolean(11),
						rs.getString(12), rs.getString(13), rs.getString(14), rs.getString(15), rs.getBoolean(16));

				KhachHang kh = new KhachHang(rs.getString(17), rs.getString(18), rs.getBoolean(19), rs.getBoolean(20),
						rs.getString(21), rs.getString(22), rs.getInt(23), rs.getString(24));
				KhuyenMai km = new KhuyenMai(rs.getString(25), rs.getDouble(26), rs.getDate(27), rs.getDate(28),
						rs.getString(29), rs.getBoolean(30));

				HoaDon hd = new HoaDon(rs.getString(1), rs.getTime(2).toLocalTime(), rs.getDate(3), nv, kh, km,
						rs.getDouble(7));
				dsHD.add(hd);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return dsHD;
	}

	public ArrayList<HoaDon> layDSHoaDonTrongKhoangThoiGian(java.util.Date date, java.util.Date date2) {
		ArrayList<HoaDon> dsHoaDon = new ArrayList<HoaDon>();
		ConnectDB.getInstance();
		Connection connect = ConnectDB.getConnection();
		try {
			String sql = "select * from HoaDon hd inner join NhanVien nv on hd.MaNV = nv.MaNV "
					+ "inner join KhachHang kh on hd.MaKH = kh.MaKH " + "inner join KhuyenMai km on hd.MaKM = km.MaKM "
					+ "WHERE NgayThanhToan BETWEEN ? AND ?";

			PreparedStatement preparedStatement = connect.prepareStatement(sql);
			java.sql.Date sqlNgayBD = new java.sql.Date(date.getTime());
			java.sql.Date sqlNgayKT = new java.sql.Date(date2.getTime());
			preparedStatement.setDate(1, sqlNgayBD);
			preparedStatement.setDate(2, sqlNgayKT);
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				NhanVien nv = new NhanVien(rs.getString(8), rs.getString(9), rs.getDate(10), rs.getBoolean(11),
						rs.getString(12), rs.getString(13), rs.getString(14), rs.getString(15), rs.getBoolean(16));

				KhachHang kh = new KhachHang(rs.getString(17), rs.getString(18), rs.getBoolean(19), rs.getBoolean(20),
						rs.getString(21), rs.getString(22), rs.getInt(23), rs.getString(24));
				KhuyenMai km = new KhuyenMai(rs.getString(25), rs.getDouble(26), rs.getDate(27), rs.getDate(28),
						rs.getString(29), rs.getBoolean(30));

				HoaDon hd = new HoaDon(rs.getString(1), rs.getTime(2).toLocalTime(), rs.getDate(3), nv, kh, km,
						rs.getDouble(7));
				dsHoaDon.add(hd);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dsHoaDon;
	}

	public ArrayList<HoaDon> layDSHoaDonKhiThongKe(java.util.Date date, java.util.Date date2) {
		ArrayList<HoaDon> dsHoaDon = new ArrayList<HoaDon>();
		ConnectDB.getInstance();
		Connection connect = ConnectDB.getConnection();
		try {
			String sql = "select * from HoaDon hd inner join NhanVien nv on hd.MaNV = nv.MaNV "
					+ "inner join KhachHang kh on hd.MaKH = kh.MaKH " + "inner join KhuyenMai km on hd.MaKM = km.MaKM "
					+ "WHERE NgayThanhToan BETWEEN ? AND ?";

			PreparedStatement preparedStatement = connect.prepareStatement(sql);
			java.sql.Date sqlNgayBD = new java.sql.Date(date.getTime());
			java.sql.Date sqlNgayKT = new java.sql.Date(date2.getTime());
			preparedStatement.setDate(1, sqlNgayBD);
			preparedStatement.setDate(2, sqlNgayKT);
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				NhanVien nv = new NhanVien(rs.getString(8), rs.getString(9), rs.getDate(10), rs.getBoolean(11),
						rs.getString(12), rs.getString(13), rs.getString(14), rs.getString(15), rs.getBoolean(16));

				KhachHang kh = new KhachHang(rs.getString(17), rs.getString(18), rs.getBoolean(19), rs.getBoolean(20),
						rs.getString(21), rs.getString(22), rs.getInt(23), rs.getString(24));
				KhuyenMai km = new KhuyenMai(rs.getString(25), rs.getDouble(26), rs.getDate(27), rs.getDate(28),
						rs.getString(29), rs.getBoolean(30));

				HoaDon hd = new HoaDon(rs.getString(1), rs.getTime(2).toLocalTime(), rs.getDate(3), nv, kh, km,
						rs.getDouble(7));
				dsHoaDon.add(hd);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dsHoaDon;
	}

	public Double ThongKeHoaDon(java.util.Date date, java.util.Date date2) {
		Double totalCount = 0.0;
		try {
			ConnectDB.getInstance();
			Connection connect = ConnectDB.getConnection();
			String sql = "SELECT SUM(TongHoaDon) AS TotalCount FROM HoaDon WHERE NgayThanhToan BETWEEN ? AND ?";
			PreparedStatement preparedStatement = connect.prepareStatement(sql);
			java.sql.Date sqlNgayBD = new java.sql.Date(date.getTime());
			java.sql.Date sqlNgayKT = new java.sql.Date(date2.getTime());
			preparedStatement.setDate(1, sqlNgayBD);
			preparedStatement.setDate(2, sqlNgayKT);
			ResultSet rs = preparedStatement.executeQuery();

			if (rs.next()) {
				totalCount = rs.getDouble("TotalCount");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return totalCount;
	}

	public int ThongKeSoLuongHoaDon(java.util.Date date, java.util.Date date2) {
		int totalCount = 0;
		try {
			ConnectDB.getInstance();
			Connection connect = ConnectDB.getConnection();
			String sql = "SELECT Count(*) AS TotalCount FROM HoaDon WHERE NgayThanhToan BETWEEN ? AND ?";
			PreparedStatement preparedStatement = connect.prepareStatement(sql);
			java.sql.Date sqlNgayBD = new java.sql.Date(date.getTime());
			java.sql.Date sqlNgayKT = new java.sql.Date(date2.getTime());
			preparedStatement.setDate(1, sqlNgayBD);
			preparedStatement.setDate(2, sqlNgayKT);
			ResultSet rs = preparedStatement.executeQuery();

			if (rs.next()) {
				totalCount = rs.getInt("TotalCount");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return totalCount;
	}

	public Map<String, Double> ThongKeHoaDonThang(java.util.Date date, java.util.Date date2) {
		Map<String, Double> monthlyTotal = new HashMap<>();
		try {
			ConnectDB.getInstance();
			Connection connect = ConnectDB.getConnection();
			if (date != null && date2 != null) {
				String sql = "SELECT CONVERT(VARCHAR(7), NgayThanhToan, 120) AS Month, SUM(TongHoaDon) AS TotalCount FROM HoaDon WHERE NgayThanhToan BETWEEN ? AND ? GROUP BY CONVERT(VARCHAR(7), NgayThanhToan, 120)";
				PreparedStatement preparedStatement = connect.prepareStatement(sql);
				java.sql.Date sqlNgayBD = new java.sql.Date(date.getTime());
				java.sql.Date sqlNgayKT = new java.sql.Date(date2.getTime());
				preparedStatement.setDate(1, sqlNgayBD);
				preparedStatement.setDate(2, sqlNgayKT);
				ResultSet rs = preparedStatement.executeQuery();

				while (rs.next()) {
					String month = rs.getString("Month");
					Double totalCount = rs.getDouble("TotalCount");
					monthlyTotal.put(month, totalCount);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return monthlyTotal;
	}
}
