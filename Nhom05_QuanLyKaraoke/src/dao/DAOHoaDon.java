package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.YearMonth;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import connectDB.ConnectDB;
import entity.ChiTietHoaDon;
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

	public boolean capNhatHoaDonSauKhiThanhToan(HoaDon hd) {
		ConnectDB.getInstance();
		Connection connect = ConnectDB.getConnection();
		PreparedStatement statement = null;
		int n = 0;
		try {
			String sql = "update HoaDon set GioThanhToan = ?, NgayThanhToan = ?, TongHoaDon = ? where MaHD = ?";
			statement = connect.prepareStatement(sql);
			statement.setTime(1, Time.valueOf(hd.getGioThanhToan()));
			statement.setTimestamp(2, new Timestamp(hd.getNgayThanhToan().getTime()));
			statement.setDouble(3, hd.getTongHoaDon());
			statement.setString(4, hd.getMaHoaDon());
			n = statement.executeUpdate();

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

	public ArrayList<HoaDon> layDSHoaDonTheoNgay(Date date) {
		ArrayList<HoaDon> dsHoaDon = new ArrayList<>();
		ConnectDB.getInstance();
		Connection connect = ConnectDB.getConnection();

		try {
			// Truncate the timestamp to the day level
			LocalDate localDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
			java.sql.Date sqlNgay = java.sql.Date.valueOf(localDate);

			String sql = "SELECT * FROM HoaDon hd " + "INNER JOIN NhanVien nv ON hd.MaNV = nv.MaNV "
					+ "INNER JOIN KhachHang kh ON hd.MaKH = kh.MaKH " + "INNER JOIN KhuyenMai km ON hd.MaKM = km.MaKM "
					+ "WHERE CAST(NgayThanhToan AS DATE) = CAST(? AS DATE)";

			PreparedStatement preparedStatement = connect.prepareStatement(sql);
			preparedStatement.setDate(1, sqlNgay);

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

	public ArrayList<HoaDon> layDSHoaDonTheoNgay(java.util.Date date) {
		ArrayList<HoaDon> dsHoaDon = new ArrayList<>();
		ConnectDB.getInstance();
		Connection connect = ConnectDB.getConnection();

		try {
			LocalDate localDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
			java.sql.Date sqlNgay = java.sql.Date.valueOf(localDate);

			String sql = "SELECT * FROM HoaDon hd " + "INNER JOIN NhanVien nv ON hd.MaNV = nv.MaNV "
					+ "INNER JOIN KhachHang kh ON hd.MaKH = kh.MaKH " + "INNER JOIN KhuyenMai km ON hd.MaKM = km.MaKM "
					+ "WHERE CAST(NgayThanhToan AS DATE) = CAST(? AS DATE)";

			PreparedStatement preparedStatement = connect.prepareStatement(sql);
			preparedStatement.setDate(1, sqlNgay);

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

	public ArrayList<HoaDon> layDSHoaDonTheoThang(java.util.Date date) {
		ArrayList<HoaDon> dsHoaDon = new ArrayList<>();
		ConnectDB.getInstance();
		Connection connect = ConnectDB.getConnection();

		try {
			LocalDate localDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
			java.sql.Date sqlNgay = java.sql.Date.valueOf(localDate);

			String sql = "SELECT * FROM HoaDon hd " + "INNER JOIN NhanVien nv ON hd.MaNV = nv.MaNV "
					+ "INNER JOIN KhachHang kh ON hd.MaKH = kh.MaKH " + "INNER JOIN KhuyenMai km ON hd.MaKM = km.MaKM "
					+ "WHERE MONTH(NgayThanhToan) = MONTH(?) AND hd.TongHoaDon != 0 ";

			PreparedStatement preparedStatement = connect.prepareStatement(sql);
			preparedStatement.setDate(1, sqlNgay);

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

	public ArrayList<HoaDon> layDSHoaDonTheoNam(int nam) {
		ArrayList<HoaDon> dsHoaDon = new ArrayList<>();
		ConnectDB.getInstance();
		Connection connect = ConnectDB.getConnection();

		try {
			String sql = "SELECT * FROM HoaDon hd " + "INNER JOIN NhanVien nv ON hd.MaNV = nv.MaNV "
					+ "INNER JOIN KhachHang kh ON hd.MaKH = kh.MaKH " + "INNER JOIN KhuyenMai km ON hd.MaKM = km.MaKM "
					+ "WHERE YEAR(NgayThanhToan) = ? AND hd.TongHoaDon != 0";

			PreparedStatement preparedStatement = connect.prepareStatement(sql);
			preparedStatement.setInt(1, nam);

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

	public ArrayList<HoaDon> layDSHoaDonTrongNgay() {
		LocalDate today = LocalDate.now();
		java.util.Date date = java.util.Date.from(today.atStartOfDay(ZoneId.systemDefault()).toInstant());
		return layDSHoaDonKhiThongKe(date, date);
	}

	public ArrayList<HoaDon> layDSHoaDonTheoThang() {
		LocalDate currentDate = LocalDate.now();
		int currentYear = currentDate.getYear();
		int currentMonth = currentDate.getMonthValue();

		LocalDate startDate = LocalDate.of(currentYear, currentMonth, 1);
		LocalDate endDate = LocalDate.of(currentYear, currentMonth, startDate.lengthOfMonth());

		java.util.Date startDateUtil = java.util.Date.from(startDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
		java.util.Date endDateUtil = java.util.Date.from(endDate.atStartOfDay(ZoneId.systemDefault()).toInstant());

		return layDSHoaDonKhiThongKe(startDateUtil, endDateUtil);
	}

	public ArrayList<HoaDon> layDSHoaDonTheoNam() {
		LocalDate currentDate = LocalDate.now();
		int currentYear = currentDate.getYear();

		LocalDate startDate = LocalDate.of(currentYear, 1, 1);
		LocalDate endDate = LocalDate.of(currentYear, 12, 31);

		java.util.Date startDateUtil = java.util.Date.from(startDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
		java.util.Date endDateUtil = java.util.Date.from(endDate.atStartOfDay(ZoneId.systemDefault()).toInstant());

		return layDSHoaDonKhiThongKe(startDateUtil, endDateUtil);
	}

	public ArrayList<HoaDon> timKiemHoaDon(String maHD, String tenNV, String sdtKhach) {
		ArrayList<HoaDon> dsHoaDon = new ArrayList<>();
		ConnectDB.getInstance();
		Connection connect = ConnectDB.getConnection();
		try {
			StringBuilder sqlBuilder = new StringBuilder();
			sqlBuilder.append("SELECT * FROM HoaDon hd ");
			sqlBuilder.append("INNER JOIN NhanVien nv ON hd.MaNV = nv.MaNV ");
			sqlBuilder.append("INNER JOIN KhachHang kh ON hd.MaKH = kh.MaKH ");
			sqlBuilder.append("INNER JOIN KhuyenMai km ON hd.MaKM = km.MaKM ");

			if (maHD != null && !maHD.isEmpty()) {
				sqlBuilder.append("AND hd.MaHD = ? ");
			}
			if (tenNV != null && !tenNV.isEmpty()) {
				sqlBuilder.append("AND nv.TenNV LIKE ? ");
			}
			if (sdtKhach != null && !sdtKhach.isEmpty()) {
				sqlBuilder.append("AND kh.SoDienThoai = ? ");
			}

			PreparedStatement preparedStatement = connect.prepareStatement(sqlBuilder.toString());

			int paramIndex = 1;
			if (maHD != null && !maHD.isEmpty()) {
				preparedStatement.setString(paramIndex++, maHD);
			}

			if (tenNV != null && !tenNV.isEmpty()) {
				preparedStatement.setString(paramIndex++, "%" + tenNV + "%");
			}

			if (sdtKhach != null && !sdtKhach.isEmpty()) {
				preparedStatement.setString(paramIndex++, sdtKhach);
			}

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

	public ArrayList<HoaDon> timKiemHoaDonTheoMaHD(String maHD) {
		ArrayList<HoaDon> dsHD = new ArrayList<>();
		try {
			ConnectDB.getInstance();
			Connection connect = ConnectDB.getConnection();
			PreparedStatement statement = null;
			String sql = "select * from HoaDon hd inner join NhanVien nv on hd.MaNV = nv.MaNV "
					+ "inner join KhachHang kh on hd.MaKH = kh.MaKH " + "inner join KhuyenMai km on hd.MaKM = km.MaKM "
					+ "where MaHD = ?";
			statement = connect.prepareStatement(sql);
			statement.setString(1, maHD);
			ResultSet rs = statement.executeQuery();
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

	public ArrayList<HoaDon> layDSHoaDonKhiThongKe(java.util.Date date, java.util.Date date2) {
		ArrayList<HoaDon> dsHoaDon = new ArrayList<HoaDon>();
		ConnectDB.getInstance();
		Connection connect = ConnectDB.getConnection();
		try {
			String sql = "select * from HoaDon hd inner join NhanVien nv on hd.MaNV = nv.MaNV "
					+ "inner join KhachHang kh on hd.MaKH = kh.MaKH " + "inner join KhuyenMai km on hd.MaKM = km.MaKM "
					+ "WHERE hd.TongHoaDon != 0 AND NgayThanhToan BETWEEN ? AND ?";

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

	public Double ThongKeHoaDonTheoNgay(java.util.Date date) {
		if (date == null) {
			return 0.0;
		}

		Double totalCount = 0.0;
		try {
			ConnectDB.getInstance();
			Connection connect = ConnectDB.getConnection();
			LocalDate localDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
			java.sql.Date sqlNgay = java.sql.Date.valueOf(localDate);

			String sql = "SELECT SUM(TongHoaDon) AS TotalCount FROM HoaDon WHERE TongHoaDon != 0 AND DAY(NgayThanhToan) = DAY(?)";
			PreparedStatement preparedStatement = connect.prepareStatement(sql);
			preparedStatement.setDate(1, sqlNgay);
			ResultSet rs = preparedStatement.executeQuery();

			if (rs.next()) {
				totalCount = rs.getDouble("TotalCount");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return totalCount;
	}

	public Double ThongKeHoaDonTheoThang(java.util.Date date) {
		if (date == null) {
			return 0.0;
		}

		Double totalCount = 0.0;
		try {
			ConnectDB.getInstance();
			Connection connect = ConnectDB.getConnection();
			LocalDate localDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
			java.sql.Date sqlNgay = java.sql.Date.valueOf(localDate);

			String sql = "SELECT SUM(TongHoaDon) AS TotalCount FROM HoaDon WHERE TongHoaDon != 0 AND MONTH(NgayThanhToan) = MONTH(?)";
			PreparedStatement preparedStatement = connect.prepareStatement(sql);
			preparedStatement.setDate(1, sqlNgay);
			ResultSet rs = preparedStatement.executeQuery();

			if (rs.next()) {
				totalCount = rs.getDouble("TotalCount");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return totalCount;
	}

	public Double ThongKeHoaDonTheoNam(String yearString) {
		if (yearString == null || yearString.isEmpty()) {
			return 0.0;
		}

		Double totalCount = 0.0;
		try {
			ConnectDB.getInstance();
			Connection connect = ConnectDB.getConnection();
			int year = Integer.parseInt(yearString);
			LocalDate localDate = LocalDate.of(year, 1, 1);
			java.sql.Date sqlNgay = java.sql.Date.valueOf(localDate);
			String sql = "SELECT SUM(TongHoaDon) AS TotalCount FROM HoaDon WHERE TongHoaDon != 0 AND YEAR(NgayThanhToan) = YEAR(?)";
			try (PreparedStatement preparedStatement = connect.prepareStatement(sql)) {
				preparedStatement.setDate(1, sqlNgay);
				try (ResultSet rs = preparedStatement.executeQuery()) {
					if (rs.next()) {
						totalCount = rs.getDouble("TotalCount");
					}
				}
			}
		} catch (SQLException | NumberFormatException e) {
			e.printStackTrace();
		}
		return totalCount;
	}

	public Double ThongKeHoaDon(java.util.Date date, java.util.Date date2) {
		Double totalCount = 0.0;
		try {
			ConnectDB.getInstance();
			Connection connect = ConnectDB.getConnection();
			String sql = "SELECT SUM(TongHoaDon) AS TotalCount FROM HoaDon WHERE TongHoaDon != 0 AND NgayThanhToan BETWEEN ? AND ?";
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
			String sql = "SELECT Count(*) AS TotalCount FROM HoaDon WHERE hd.TongHoaDon != 0 AND NgayThanhToan BETWEEN ? AND ?";
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

	public int ThongKeSoLuongHoaDonTheoNgay(java.util.Date date) {
		if (date == null) {
			return 0;
		}
		int totalCount = 0;
		try {
			ConnectDB.getInstance();
			Connection connect = ConnectDB.getConnection();
			LocalDate localDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
			java.sql.Date sqlNgay = java.sql.Date.valueOf(localDate);

			String sql = "SELECT Count(*) AS TotalCount FROM HoaDon WHERE TongHoaDon!=0 AND DAY(NgayThanhToan) = DAY(?) AND MONTH(NgayThanhToan) = MONTH(?) AND YEAR(NgayThanhToan) = YEAR(?)";
			PreparedStatement preparedStatement = connect.prepareStatement(sql);
			preparedStatement.setDate(1, sqlNgay);
			preparedStatement.setDate(2, sqlNgay);
			preparedStatement.setDate(3, sqlNgay);
			ResultSet rs = preparedStatement.executeQuery();

			if (rs.next()) {
				totalCount = rs.getInt("TotalCount");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return totalCount;
	}

	public int ThongKeSoLuongHoaDonTheoThang(java.util.Date date) {
		if (date == null) {
			return 0;
		}
		int totalCount = 0;
		try {
			ConnectDB.getInstance();
			Connection connect = ConnectDB.getConnection();
			LocalDate localDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
			java.sql.Date sqlNgay = java.sql.Date.valueOf(localDate);

			String sql = "SELECT Count(*) AS TotalCount FROM HoaDon WHERE TongHoaDon!=0 AND MONTH(NgayThanhToan) = MONTH(?) AND YEAR(NgayThanhToan) = YEAR(?)";
			PreparedStatement preparedStatement = connect.prepareStatement(sql);
			preparedStatement.setDate(1, sqlNgay);
			preparedStatement.setDate(2, sqlNgay);
			ResultSet rs = preparedStatement.executeQuery();

			if (rs.next()) {
				totalCount = rs.getInt("TotalCount");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return totalCount;
	}

	public int ThongKeSoLuongHoaDonTheoNam(String yearString) {
		if (yearString == null || yearString.isEmpty()) {
			return 0;
		}
		int totalCount = 0;
		try {
			ConnectDB.getInstance();
			Connection connect = ConnectDB.getConnection();
			int year = Integer.parseInt(yearString);
			LocalDate localDate = LocalDate.of(year, 1, 1);
			java.sql.Date sqlNgay = java.sql.Date.valueOf(localDate);

			String sql = "SELECT Count(*) AS TotalCount FROM HoaDon WHERE TongHoaDon!=0 AND DATEPART(YEAR, NgayThanhToan) = DATEPART(YEAR, ?)";
			PreparedStatement preparedStatement = connect.prepareStatement(sql);
			preparedStatement.setDate(1, sqlNgay);
			ResultSet rs = preparedStatement.executeQuery();

			if (rs.next()) {
				totalCount = rs.getInt("TotalCount");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return totalCount;
	}

	public List<Integer> layNamLapHoaDon() {
		List<Integer> years = new ArrayList<>();
		try {
			ConnectDB.getInstance();
			Connection connect = ConnectDB.getConnection();

			String sql = "SELECT DISTINCT YEAR(NgayThanhToan) AS InvoiceYear FROM HoaDon";
			PreparedStatement preparedStatement = connect.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				int year = rs.getInt("InvoiceYear");
				years.add(year);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return years;
	}

	public Map<String, Double> ThongKeHoaDonThang(java.util.Date date, java.util.Date date2) {
		Map<String, Double> monthlyTotal = new HashMap<>();
		try {
			ConnectDB.getInstance();
			Connection connect = ConnectDB.getConnection();
			if (date != null && date2 != null) {
				String sql = "SELECT CONVERT(VARCHAR(7), NgayThanhToan, 120) AS Month, SUM(TongHoaDon) AS TotalCount FROM HoaDon WHERE TongHoaDon!=0 AND NgayThanhToan BETWEEN ? AND ? GROUP BY CONVERT(VARCHAR(7), NgayThanhToan, 120)";
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

	public Map<Integer, Double> ThongKeHoaDonNam(Integer year) {
		Map<Integer, Double> monthlyTotal = new HashMap<>();
		try {
			ConnectDB.getInstance();
			Connection connect = ConnectDB.getConnection();
			if (year != null) {
				// Loop through all 12 months
				for (int month = 1; month <= 12; month++) {
					String sql = "SELECT SUM(TongHoaDon) AS TotalCount FROM HoaDon WHERE TongHoaDon!=0 AND YEAR(NgayThanhToan) = ? AND MONTH(NgayThanhToan) = ?";
					PreparedStatement preparedStatement = connect.prepareStatement(sql);
					preparedStatement.setInt(1, year);
					preparedStatement.setInt(2, month);
					ResultSet rs = preparedStatement.executeQuery();

					if (rs.next()) {
						Double totalCount = rs.getDouble("TotalCount");
						monthlyTotal.put(month, totalCount);
					} else {
						// If there is no revenue for the month, set it to 0
						monthlyTotal.put(month, 0.0);
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return monthlyTotal;
	}

	public Map<Integer, Double> ThongKeHoaDonThang(java.util.Date month) {
		Map<Integer, Double> monthlyTotal = new TreeMap<>();

		try {
			if (month != null) {
				ConnectDB.getInstance();
				Connection connect = ConnectDB.getConnection();

				LocalDate localDate = month.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
				YearMonth yearMonth = YearMonth.from(localDate);

				int year = yearMonth.getYear();
				int monthValue = yearMonth.getMonthValue();

				int daysInMonth = yearMonth.lengthOfMonth();

				for (int day = 1; day <= daysInMonth; day++) {
					String sql = "SELECT SUM(TongHoaDon) AS TotalCount FROM HoaDon WHERE TongHoaDon!=0 AND YEAR(ngayThanhToan) = ? AND MONTH(ngayThanhToan) = ? AND DAY(ngayThanhToan) = ?";
					PreparedStatement preparedStatement = connect.prepareStatement(sql);
					preparedStatement.setInt(1, year);
					preparedStatement.setInt(2, monthValue);
					preparedStatement.setInt(3, day);
					ResultSet rs = preparedStatement.executeQuery();

					if (rs.next()) {
						double totalCount = rs.getDouble("TotalCount");
						monthlyTotal.put(day, totalCount);
					} else {
						// If there is no revenue for the day, set it to 0
						monthlyTotal.put(day, 0.0);
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return monthlyTotal;
	}

}
