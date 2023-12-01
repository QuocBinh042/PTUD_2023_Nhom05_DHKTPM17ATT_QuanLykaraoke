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

public class DAONhanVien{

	public ArrayList<NhanVien> getAll() {
		// TODO Auto-generated method stubArrayList<HoaDonDichVuPhong> dsDVP = new ArrayList<HoaDonDichVuPhong>();
		ArrayList<NhanVien> dsNV = new ArrayList<NhanVien>();
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		try {
			String sql = "select * from NhanVien";
			Statement statement =con.createStatement();
			ResultSet rs = statement.executeQuery(sql);
			while(rs.next()) {
				dsNV.add(new NhanVien(rs.getString(1), rs.getString(2), rs.getDate(3), rs.getBoolean(4),
						rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8), rs.getBoolean(9)));
			}
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return dsNV;
	}
	
	public ArrayList<NhanVien> timKiemNhanVienTheoMa(String maNV) {
		// TODO Auto-generated method stubArrayList<HoaDonDichVuPhong> dsDVP = new ArrayList<HoaDonDichVuPhong>();
		ArrayList<NhanVien> dsNV = new ArrayList<NhanVien>();
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		try {
			PreparedStatement statement = null;
			String sql = "select * from NhanVien where MaNV = ?";
			statement = con.prepareStatement(sql);
			statement.setString(1, maNV);
			ResultSet rs = statement.executeQuery();
			while(rs.next()) {
				dsNV.add(new NhanVien(rs.getString(1), rs.getString(2), rs.getDate(3), rs.getBoolean(4),
						rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8), rs.getBoolean(9)));
			}
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return dsNV;
	}
	
	// Lay chuc vu theo combobox
		public static ArrayList<NhanVien> getChucVuCB(String chucVu) {
			ArrayList<NhanVien> dsNV = new ArrayList<NhanVien>();
			ConnectDB.getInstance();
			Connection connect = ConnectDB.getConnection();
			String sql = "";
			if (chucVu.equalsIgnoreCase("Lễ tân")) {
				sql = "select *from NhanVien\r\n" + "where ChucVu like N'Lễ tân'";
			} else if (chucVu.equalsIgnoreCase("Nhân viên quản lý")) {
				sql = "select *from NhanVien\r\n" + "where ChucVu like N'Nhân viên quản lý'";
			} else if (chucVu.equalsIgnoreCase("Phục vụ")) {
				sql = "select *from NhanVien\r\n" + "where ChucVu like N'Phục vụ'";
			} else if (chucVu.equalsIgnoreCase("Tất cả")) {
				sql = "select *from NhanVien\r\n" + "where ChucVu";
			}
			try {

				Statement statement = connect.createStatement();
				ResultSet rs = statement.executeQuery(sql);
				while (rs.next()) {
					dsNV.add(new NhanVien(rs.getString(1), rs.getString(2), rs.getDate(3), rs.getBoolean(4),
							rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8), rs.getBoolean(9)));
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			return dsNV;
		}
		
		// Lay tinh trang theo combobox
		public static ArrayList<NhanVien> getTinhTrangCB(Boolean tinhTrangNV) {
			ArrayList<NhanVien> dsNV = new ArrayList<NhanVien>();
			ConnectDB.getInstance();
			Connection connect = ConnectDB.getConnection();
			String sql = "";
			if (tinhTrangNV) {
				sql = "SELECT * FROM NhanVien WHERE tinhTrangNV = ?";
			} else if (!tinhTrangNV) {
				sql = "SELECT * FROM NhanVien WHERE tinhTrangNV = ?";
			}
			try {
				PreparedStatement preparedStatement = connect.prepareStatement(sql);
				preparedStatement.setBoolean(1, tinhTrangNV);

				ResultSet rs = preparedStatement.executeQuery();
				while (rs.next()) {
					dsNV.add(new NhanVien(rs.getString(1), rs.getString(2), rs.getDate(3), rs.getBoolean(4),
							rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8), rs.getBoolean(9)));
				}
			} catch (Exception e) {
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
			java.sql.Date namSinh = new java.sql.Date(nv.getNamSinh().getTime());
			stm.setDate(3, namSinh);
			stm.setBoolean(4, nv.getGioiTinh());
			stm.setString(5, nv.getSdthoai());
			stm.setString(6, nv.getCccd());
			stm.setString(7, nv.getChucVu());
			stm.setString(8, nv.getMatKhau());
			stm.setBoolean(9, nv.getTinhTrangNV());
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
			stm = con.prepareStatement(sql);
			stm.setString(1, nv.getTenNV());
			java.sql.Date namSinh = new java.sql.Date(nv.getNamSinh().getTime());
			stm.setDate(2, namSinh);
			stm.setBoolean(3, nv.getGioiTinh());
			stm.setString(4, nv.getSdthoai());
			stm.setString(5, nv.getCccd());
			stm.setString(6, nv.getChucVu());
			stm.setString(7, nv.getMatKhau());
			stm.setBoolean(8, nv.getTinhTrangNV());
			stm.setString(9, nv.getMaNV());
			
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
	
	public void updateMatKhau(String matKhau,String soDienThoai) {
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		PreparedStatement stm = null;
		String sql = "Update NhanVien set MatKhau = ? where SoDienThoai = ?";
		try {
			stm = con.prepareStatement(sql);
			stm.setString(1, matKhau);
			stm.setString(2, soDienThoai);
			stm.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
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