package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;

import connectDB.ConnectDB;
import entity.CTDVPhong;
import entity.ChiTietHoaDon;
import entity.HoaDon;
import entity.KhachHang;
import entity.KhuyenMai;
import entity.LoaiPhong;
import entity.NhanVien;
import entity.PhieuDatPhong;
import entity.Phong;

public class DAOCTHD {
	public DAOCTHD() {

	}

	public boolean createCTPhong(ChiTietHoaDon cthd) {
		ConnectDB.getInstance();
		Connection connect = ConnectDB.getConnection();
		PreparedStatement pre = null;
		int n = 0;
		try {
			pre = connect.prepareStatement("insert into " + "ChiTietHoaDon values(?,?,?,?)");
			Timestamp timeStanThoiGianNhanPhong = new Timestamp(cthd.getThoiGianNhanPhong().getTime());
			Timestamp timeStanThoiGianTraPhong = new Timestamp(cthd.getThoiGianNhanPhong().getTime());
			pre.setString(1, cthd.getPhong().getMaPhong());
			pre.setString(2, cthd.getHd().getMaHoaDon());
			pre.setTimestamp(3, timeStanThoiGianNhanPhong);
			pre.setTimestamp(4, timeStanThoiGianTraPhong);
			n = pre.executeUpdate();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return n > 0;
	}

	public String timKiemCTHDTheoMaPhong(String tenPhong) {
		String str = "";
		try {
			ConnectDB.getInstance();
			Connection connect = ConnectDB.getConnection();
			PreparedStatement statement = null;
			String sql = "select * from ChiTietHoaDon cthd " + "inner join Phong p on cthd.MaPhong = p.MaPhong "
					+ "inner join HoaDon hd on cthd.MaHD = hd.MaHD " + "inner join KhachHang kh on kh.MaKH = hd.MaKH "
					+ "where cthd.MaPhong = ?";
			statement = connect.prepareStatement(sql);
			statement.setString(1, tenPhong);
			ResultSet rs = statement.executeQuery();
			while (rs.next()) {
				str = rs.getString(6) + "," + rs.getString(18) + "," + rs.getTimestamp(3);
			}

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			e.printStackTrace();
		}
		return str;
	}

	public ArrayList<ChiTietHoaDon> timKiemCTHDTheoTenPhong(String tenPhong) {
		ArrayList<ChiTietHoaDon> dsCTHD = new ArrayList<>();
		try {
			ConnectDB.getInstance();
			Connection connect = ConnectDB.getConnection();
			PreparedStatement statement = null;
			String sql = "select * from ChiTietHoaDon cthd " + "inner join Phong p on cthd.MaPhong = p.MaPhong "
					+ "inner join LoaiPhong lp on p.MaLP = lp.MaLP " + "where TenPhong = ?";
			statement = connect.prepareStatement(sql);
			statement.setString(1, tenPhong);
			ResultSet rs = statement.executeQuery();

			while (rs.next()) {
				LoaiPhong lp = new LoaiPhong(rs.getString(10), rs.getString(11), rs.getInt(12), rs.getDouble(13));
				Phong p = new Phong(rs.getString(5), rs.getString(6), lp, rs.getString(8), rs.getString(9));
				ChiTietHoaDon cthd = new ChiTietHoaDon(p, new HoaDon(rs.getString(2)), rs.getTimestamp(3),
						rs.getTimestamp(4));
				dsCTHD.add(cthd);
			}

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			e.printStackTrace();
		}
		return dsCTHD;
	}

	public ArrayList<ChiTietHoaDon> timKiemCTHDTheoMaHD(String maHD) {
		ArrayList<ChiTietHoaDon> dsCTHD = new ArrayList<>();
		try {
			ConnectDB.getInstance();
			Connection connect = ConnectDB.getConnection();
			PreparedStatement statement = null;
			String sql = "select * from ChiTietHoaDon cthd " + "inner join Phong p on cthd.MaPhong = p.MaPhong "
					+ "inner join LoaiPhong lp on p.MaLP = lp.MaLP " + "where MaHD = ?";
			statement = connect.prepareStatement(sql);
			statement.setString(1, maHD);
			ResultSet rs = statement.executeQuery();

			while (rs.next()) {
				LoaiPhong lp = new LoaiPhong(rs.getString(10), rs.getString(11), rs.getInt(12), rs.getDouble(13));
				Phong p = new Phong(rs.getString(5), rs.getString(6), lp, rs.getString(8), rs.getString(9));
				ChiTietHoaDon cthd = new ChiTietHoaDon(p, new HoaDon(rs.getString(2)), rs.getTimestamp(3),
						rs.getTimestamp(4));
				dsCTHD.add(cthd);
			}

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			e.printStackTrace();
		}
		return dsCTHD;
	}

	public boolean capNhatGioTraPhong(ChiTietHoaDon chiTietHoaDon) {
		ConnectDB.getInstance();
		Connection connect = ConnectDB.getConnection();
		PreparedStatement statement = null;
		int n = 0;
		try {
			String sql = "update ChiTietHoaDon " + "set ThoiGianTraPhong = ? " + "where MaPhong = ?;";
			statement = connect.prepareStatement(sql);
			statement.setTimestamp(1, new Timestamp(chiTietHoaDon.getThoiGianTraPhong().getTime()));
			statement.setString(2, chiTietHoaDon.getPhong().getMaPhong());
			n = statement.executeUpdate();

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return n > 0;
	}

	public Double ThongKeTienPhongTheoThang(java.util.Date date) {
		if (date == null) {
			return 0.0;
		}
		Double totalCost = 0.0;
		try {
			ConnectDB.getInstance();
			Connection connect = ConnectDB.getConnection();
			LocalDate localDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
			java.sql.Date sqlNgay = java.sql.Date.valueOf(localDate);

			String sql = "SELECT SUM(DATEDIFF(HOUR, cthd.ThoiGianTraPhong, cthd.ThoiGianNhanPhong)*lp.GiaPhong ) AS TotalCount "
					+ "	FROM ChiTietHoaDon cthd " + "	INNER JOIN Phong p ON cthd.MaPhong = p.MaPhong "
					+ "	INNER JOIN LoaiPhong lp ON p.MaLP = lp.MaLP "
					+ "	WHERE MONTH(cthd.ThoiGianTraPhong) = MONTH(?)";

			PreparedStatement preparedStatement = connect.prepareStatement(sql);
			preparedStatement.setDate(1, sqlNgay);
			ResultSet rs = preparedStatement.executeQuery();

			if (rs.next()) {
				totalCost = rs.getDouble("TotalCount");
			}
		} catch (SQLException | DateTimeParseException e) {
			e.printStackTrace();
		}
		return totalCost;
	}

	public Double ThongKeTienPhongTheoNgay(java.util.Date date) {
		if (date == null) {
			return 0.0;
		}

		Double totalCost = 0.0;

		try {
			ConnectDB.getInstance();
			Connection connect = ConnectDB.getConnection();
			LocalDate localDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
			java.sql.Date sqlNgay = java.sql.Date.valueOf(localDate);

			String sql = "SELECT SUM(DATEDIFF(HOUR, cthd.ThoiGianTraPhong, cthd.ThoiGianNhanPhong) * lp.GiaPhong) AS TotalCount "
					+ " FROM ChiTietHoaDon cthd " + " INNER JOIN Phong p ON cthd.MaPhong = p.MaPhong "
					+ " INNER JOIN LoaiPhong lp ON p.MaLP = lp.MaLP "
					+ " WHERE CONVERT(DATE, cthd.ThoiGianTraPhong) = ?";

			PreparedStatement preparedStatement = connect.prepareStatement(sql);
			preparedStatement.setDate(1, sqlNgay);
			ResultSet rs = preparedStatement.executeQuery();

			if (rs.next()) {
				totalCost = rs.getDouble("TotalCount");
			}
		} catch (SQLException | DateTimeParseException e) {
			e.printStackTrace();
		}

		return totalCost;
	}

	public Double ThongKeTienPhongTheoNam(String yearString) {
		if (yearString == null || yearString.isEmpty()) {
			return 0.0;
		}

		Double totalCost = 0.0;

		try {
			ConnectDB.getInstance();
			Connection connect = ConnectDB.getConnection();
			int year = Integer.parseInt(yearString);
			LocalDate localDate = LocalDate.of(year, 1, 1);
			java.sql.Date sqlNgay = java.sql.Date.valueOf(localDate);

			String sql = "SELECT SUM(DATEDIFF(HOUR, cthd.ThoiGianTraPhong, cthd.ThoiGianNhanPhong) * lp.GiaPhong) AS TotalCount "
					+ " FROM ChiTietHoaDon cthd " + " INNER JOIN Phong p ON cthd.MaPhong = p.MaPhong "
					+ " INNER JOIN LoaiPhong lp ON p.MaLP = lp.MaLP " + " WHERE YEAR(cthd.ThoiGianTraPhong) = YEAR(?)";

			PreparedStatement preparedStatement = connect.prepareStatement(sql);
			preparedStatement.setDate(1, sqlNgay);
			ResultSet rs = preparedStatement.executeQuery();

			if (rs.next()) {
				totalCost = rs.getDouble("TotalCount");
			}
		} catch (SQLException | DateTimeParseException | NumberFormatException e) {
			e.printStackTrace(); // Handle this more appropriately in a production environment
		}

		return totalCost;
	}

}
