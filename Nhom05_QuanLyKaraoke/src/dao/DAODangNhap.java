package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import connectDB.ConnectDB;
import entity.NhanVien;

public class DAODangNhap {
	public NhanVien kiemTraTK(String maNV, String matKhau) {
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		PreparedStatement preparedStatement = null;
		ResultSet rs = null;

		try {
			String sql = "SELECT * FROM NhanVien WHERE maNV = ? and MatKhau = ?";
			preparedStatement = con.prepareStatement(sql);
			preparedStatement.setString(1, maNV);
			preparedStatement.setString(2, matKhau);
			rs = preparedStatement.executeQuery();
			while (rs.next()) {
				NhanVien nv = new NhanVien(rs.getString(1), rs.getString(2), rs.getDate(3), rs.getBoolean(4),
						rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8), rs.getBoolean(9));
				return nv;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			// Đóng ResultSet, PreparedStatement và Connection ở đây
			try {
				if (rs != null) {
					rs.close();
				}
				if (preparedStatement != null) {
					preparedStatement.close();
				}
				if (con != null) {
					con.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return null; // Trả về null nếu không tìm thấy dữ liệu
	}
}
