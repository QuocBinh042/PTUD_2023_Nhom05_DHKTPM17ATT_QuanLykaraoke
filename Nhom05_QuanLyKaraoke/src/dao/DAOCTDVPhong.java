package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;

import connectDB.ConnectDB;
import entity.CTDVPhong;
import entity.ChiTietHoaDon;
import entity.DichVu;
import entity.HoaDon;
import entity.KhachHang;
import entity.KhuyenMai;
import entity.NhanVien;

public class DAOCTDVPhong {
	public DAOCTDVPhong() {
		
	}
	
	public boolean createCTDVPhong(CTDVPhong ctdvPhong) {
		ConnectDB.getInstance();
		Connection connect = ConnectDB.getConnection();
		PreparedStatement pre = null;
		int n = 0;
		try {
			pre = connect.prepareStatement("insert into CTDVPhong values(?,?,?)");
			pre.setString(1, ctdvPhong.getHd().getMaHoaDon());
			pre.setString(2, ctdvPhong.getDichVu().getMaDichVu());
			pre.setInt(3, ctdvPhong.getSoLuong());
			n = pre.executeUpdate();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return n > 0;
	}
	
	public ArrayList<CTDVPhong> getAllCTDVPhong() {
		ArrayList<CTDVPhong> dsCTDVPhong = new ArrayList<>();
		try {
			ConnectDB.getInstance();
			Connection connect = ConnectDB.getConnection();
			String sql = "select * from CTDVPhong";
			ResultSet rs = connect.createStatement().executeQuery(sql);
			while(rs.next()) {
				HoaDon hd = new HoaDon(rs.getString(1));
				DichVu dv = new DichVu(rs.getString(2));
				int soLuong = rs.getInt(3);
				CTDVPhong ctdvPhong = new CTDVPhong(hd, dv, soLuong);
				dsCTDVPhong.add(ctdvPhong);
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return dsCTDVPhong;
	}
	
	public ArrayList<CTDVPhong> timKiemCTDVPhongTheoMaHD(String maHD) {
		ArrayList<CTDVPhong> dsCTDVPhong = new ArrayList<>();
		try {
			ConnectDB.getInstance();
			Connection connect = ConnectDB.getConnection();
			PreparedStatement statement = null;
			String sql = "select * from CTDVPhong ctdvp inner join DichVu dv on ctdvp.MaDV = dv.MaDV where MaHD = ?";
			statement = connect.prepareStatement(sql);
			statement.setString(1, maHD);
			ResultSet rs = statement.executeQuery();
			while (rs.next()) {
				HoaDon hd = new HoaDon(rs.getString(1));
				DichVu dv = new DichVu(rs.getString(4), rs.getString(5), rs.getDouble(6), rs.getString(7), rs.getInt(8), rs.getString(9));
				int soLuong = rs.getInt(3);
				CTDVPhong ctdvPhong = new CTDVPhong(hd, dv, soLuong);
				dsCTDVPhong.add(ctdvPhong);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return dsCTDVPhong;
	}
	
	public ArrayList<CTDVPhong> timKiemCTDVPhongTheoTenDV(String maHD, String maDV) {
		ArrayList<CTDVPhong> dsCTDVPhong = new ArrayList<>();
		try {
			ConnectDB.getInstance();
			Connection connect = ConnectDB.getConnection();
			PreparedStatement statement = null;
			String sql = "select * from CTDVPhong ctdvp inner join DichVu dv on ctdvp.MaDV = dv.MaDV where MaHD = ? and dv.MaDV = ?";
			statement = connect.prepareStatement(sql);
			statement.setString(1, maHD);
			statement.setString(2, maDV);
			ResultSet rs = statement.executeQuery();
			while (rs.next()) {
				HoaDon hd = new HoaDon(rs.getString(1));
				DichVu dv = new DichVu(rs.getString(4), rs.getString(5), rs.getDouble(6), rs.getString(7), rs.getInt(8), rs.getString(9));
				int soLuong = rs.getInt(3);
				CTDVPhong ctdvPhong = new CTDVPhong(hd, dv, soLuong);
				dsCTDVPhong.add(ctdvPhong);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return dsCTDVPhong;
	}
	
	public boolean capNhatSoLuongCTDVPhong(int soLuong, String maDV, String maHD) {
		// TODO Auto-generated method stub
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		PreparedStatement stm = null;
		String sql = "update CTDVPhong set SoLuong = ? "
				+ "where MaHD = ? and MaDV = ?";

		try {
			stm = con.prepareStatement(sql);
			stm.setInt(1, soLuong);
			stm.setString(2, maHD);
			stm.setString(3, maDV);
			stm.executeUpdate();
			
		} catch (SQLException e) { 	
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	public boolean capNhatSoLuongDichVu(int soLuong, String tenDV) {
		// TODO Auto-generated method stub
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		PreparedStatement stm = null;
		String sql = "update DichVu set SoLuongTonKho = ?"
				+ " where TenDV = ?";

		try {
			stm = con.prepareStatement(sql);
			stm.setInt(1, soLuong);
			stm.setString(2, tenDV);
			stm.executeUpdate();
			
		} catch (SQLException e) { 	
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	public boolean delete(String maDV) {
		// TODO Auto-generated method stub
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		PreparedStatement stm = null;
		String sql = "delete from CTDVPhong where MaDV = ?";
		try {
			stm = con.prepareStatement(sql);
			stm.setString(1, maDV);
			stm.executeUpdate();
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
			return false;
		}
		return true;
	}
}
