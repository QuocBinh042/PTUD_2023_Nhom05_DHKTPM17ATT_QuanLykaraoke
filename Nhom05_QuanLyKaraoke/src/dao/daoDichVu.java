package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;

import connectDB.ConnectDB;
import entity.DichVu;

public class daoDichVu {
	// Lay toan bo dich vu
	public ArrayList<DichVu> getAllDichVu() {
		ArrayList<DichVu> dsDichVu = new ArrayList<DichVu>();
		ConnectDB.getInstance();
		Connection connect = ConnectDB.getConnection();
		try {
			String sql = "select *from DichVu";
			Statement statement = connect.createStatement();
			ResultSet rs = statement.executeQuery(sql);
			while (rs.next()) {
				dsDichVu.add(new DichVu(rs.getString(1), rs.getString(2), rs.getDouble(3), rs.getDouble(4),
						rs.getString(5), rs.getInt(6), rs.getString(7)));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dsDichVu;
	}

	// Lay dich vu theo combobox
	public ArrayList<DichVu> getDichVuCB(String tinhTrang) {
		ArrayList<DichVu> dsDichVu = new ArrayList<DichVu>();
		ConnectDB.getInstance();
		Connection connect = ConnectDB.getConnection();
		String sql = "";
		if (tinhTrang.equalsIgnoreCase("Hết hàng")) {
			sql = "select *from DichVu\r\n" + "where TinhTrangDV like N'Hết hàng'";
		} else if (tinhTrang.equalsIgnoreCase("Sắp hết hàng")) {
			sql = "select *from DichVu\r\n" + "where TinhTrangDV like N'Sắp hết hàng'";
		} else if (tinhTrang.equalsIgnoreCase("Còn hàng")) {
			sql = "select *from DichVu\r\n" + "where TinhTrangDV like N'Còn hàng'";
		} else if (tinhTrang.equalsIgnoreCase("Đã xóa")) {
			sql = "select *from DichVu\r\n" + "where TinhTrangDV like N'Đã xóa'";
		} else if (tinhTrang.equalsIgnoreCase("Tất cả")) {
			sql = "select *from DichVu\r\n" + "where TinhTrangDV not like N'Đã xóa'";
		}
		try {

			Statement statement = connect.createStatement();
			ResultSet rs = statement.executeQuery(sql);
			while (rs.next()) {
				dsDichVu.add(new DichVu(rs.getString(1), rs.getString(2), rs.getDouble(3), rs.getDouble(4),
						rs.getString(5), rs.getInt(6), rs.getString(7)));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dsDichVu;
	}

	// Them dich vu
	public boolean add(DichVu dv) {
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		PreparedStatement stm = null;
		String sql = "INSERT INTO DichVu (MaDV,TenDV,DonGiaNhap,DonGiaBan,DonViTinh,SoLuongTonKho,TinhTrangDV) "
				+ "values(?,?,?,?,?,?,?)";
		try {
			stm = con.prepareStatement(sql);
			stm.setString(1, dv.getMaDichVu());
			stm.setString(2, dv.getTenDichVu());
			stm.setDouble(3, dv.getDonGiaNhap());
			stm.setDouble(4, dv.getDonGiaBan());
			stm.setString(5, dv.getDonVi());
			stm.setInt(6, dv.getSoLuong());
			stm.setString(7, dv.getTinhTrang());
			System.out.println(stm);
			stm.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	// Cap nhat dich vu
	public boolean updateGia(DichVu dv) {
		// TODO Auto-generated method stub
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		PreparedStatement stm = null;
		String sql = "update DichVu \r\n"
				+ "set TenDV = ?, DonGiaNhap = ?, DonGiaBan = ?, DonViTinh = ?, SoLuongTonKho = ?\r\n"
				+ "where MaDV = ?";
		try {
			stm = con.prepareStatement(sql);
			stm.setString(1, dv.getTenDichVu());
			stm.setDouble(2, dv.getDonGiaNhap());
			stm.setDouble(3, dv.getDonGiaBan());
			stm.setString(4, dv.getDonVi());
			stm.setInt(5, dv.getSoLuong());
			stm.setString(6, dv.getMaDichVu());

			stm.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	// Xoa dich vu
	public void delete(String maDichVu) {
		// TODO Auto-generated method stub
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		PreparedStatement stm = null;
		String sql = "DELETE from DichVu " + "where MaDV = ?";
		try {
			stm = con.prepareStatement(sql);
			stm.setString(1, maDichVu);

			stm.executeUpdate();
		} catch (SQLException e) {
			// TODO: handle exception
		}
	}
	
}
