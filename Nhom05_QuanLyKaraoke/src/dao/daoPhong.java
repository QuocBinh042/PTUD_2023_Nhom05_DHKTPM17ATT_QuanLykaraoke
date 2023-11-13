package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import connectDB.ConnectDB;
import entity.KhuyenMai;
import entity.LoaiPhong;
import entity.Phong;

public class daoPhong {
	public daoPhong() {}
	
	public ArrayList<Phong> getAllPhong() {
	//Lay toan bo phong
		ArrayList<Phong> dsPhong = new ArrayList<>();
		try {
			ConnectDB.getInstance();
			Connection connect = ConnectDB.getConnection();
			String sql = "select * from Phong p inner join LoaiPhong lp on p.MaLP = lp.MaLP "; 
			ResultSet rs = connect.createStatement().executeQuery(sql);
			while(rs.next()) {				
				LoaiPhong lp = new LoaiPhong(rs.getString(6), rs.getString(7), rs.getInt(8), rs.getDouble(9));
				Phong phong = new Phong(rs.getString(1), rs.getString(2), lp, rs.getString(4), rs.getString(5));
				dsPhong.add(phong);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return dsPhong;
	}
	
	// Lay loai phong theo combobox tinh trang
		public ArrayList<Phong> getCBTT(String tinhTrang) {
			ArrayList<Phong> dsPhong = new ArrayList<Phong>();
			ConnectDB.getInstance();
			Connection connect = ConnectDB.getConnection();
			String sql = "";
			if (tinhTrang.equalsIgnoreCase("Tất cả")) {
				sql = "select * from Phong p inner join LoaiPhong lp on p.MaLP = lp.MaLP \r\n"
						+ "where p.TinhTrangPhong not like N'Đã xóa'";
			} else if (tinhTrang.equalsIgnoreCase("Còn trống")) {
				sql = "select * from Phong p inner join LoaiPhong lp on p.MaLP = lp.MaLP \r\n"
						+ "where p.TinhTrangPhong = N'Còn trống'";
			} else if (tinhTrang.equalsIgnoreCase("Đã đặt trước")) {
				sql = "select * from Phong p inner join LoaiPhong lp on p.MaLP = lp.MaLP \r\n"
						+ "where p.TinhTrangPhong = N'Đã đặt trước'";
			} else if (tinhTrang.equalsIgnoreCase("Đang thuê")) {
				sql = "select * from Phong p inner join LoaiPhong lp on p.MaLP = lp.MaLP \r\n"
						+ "where p.TinhTrangPhong = N'Đang thuê'";
			} else if (tinhTrang.equalsIgnoreCase("Đã xóa")) {
				sql = "select * from Phong p inner join LoaiPhong lp on p.MaLP = lp.MaLP \r\n"
						+ "where p.TinhTrangPhong = N'Đã xóa'";
			}
			try {
				Statement statement = connect.createStatement();
				ResultSet rs = statement.executeQuery(sql);
				while (rs.next()) {
					LoaiPhong lp = new LoaiPhong(rs.getString(6), rs.getString(7), rs.getInt(8), rs.getDouble(9));
					Phong phong = new Phong(rs.getString(1), rs.getString(2), lp, rs.getString(4), rs.getString(5));
					dsPhong.add(phong);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			return dsPhong;
		}

		// Lay loai phong theo combobox loai phong
		public ArrayList<Phong> getCBLP(String tinhTrang) {
			ArrayList<Phong> dsPhong = new ArrayList<Phong>();
			ConnectDB.getInstance();
			Connection connect = ConnectDB.getConnection();
			String sql = "";
			if (tinhTrang.equalsIgnoreCase("Tất cả")) {
				sql = "select * from Phong p inner join LoaiPhong lp on p.MaLP = lp.MaLP \r\n"
						+ "where p.TinhTrangPhong not like N'Đã xóa'";
			} else if (tinhTrang.equalsIgnoreCase("Thường")) {
				sql = "select * from Phong p inner join LoaiPhong lp on p.MaLP = lp.MaLP \r\n"
						+ "where lp.TenLP = N'Thường'";
			} else if (tinhTrang.equalsIgnoreCase("VIP")) {
				sql = "select * from Phong p inner join LoaiPhong lp on p.MaLP = lp.MaLP \r\n" + "where lp.TenLP = N'VIP'";
			}
			try {
				Statement statement = connect.createStatement();
				ResultSet rs = statement.executeQuery(sql);
				while (rs.next()) {
					LoaiPhong lp = new LoaiPhong(rs.getString(6), rs.getString(7), rs.getInt(8), rs.getDouble(9));
					Phong phong = new Phong(rs.getString(1), rs.getString(2), lp, rs.getString(4), rs.getString(5));
					dsPhong.add(phong);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			return dsPhong;
		}

		// Them dich vu
		public boolean add(Phong p) {
			ConnectDB.getInstance();
			Connection con = ConnectDB.getConnection();
			PreparedStatement stm = null;
			String sql = "INSERT INTO Phong (MaPhong, TenPhong, MaLP, TinhTrangPhong, MoTa) " + "values(?,?,?,?,?)";
			try {
				stm = con.prepareStatement(sql);
				stm.setString(1, p.getMaPhong());
				stm.setString(2, p.getTenPhong());
				stm.setString(3, p.getLoaiPhong().getMaLoaiPhong());
				stm.setString(4, p.getTinhTrangPhong());
				stm.setString(5, p.getMoTa());
				System.out.println(stm);
				stm.executeUpdate();
			} catch (Exception e) {
				e.printStackTrace();
				return false;
			}
			return true;
		}

		// Cap nhat phong
		public boolean update(Phong p) {
			ConnectDB.getInstance();
			Connection con = ConnectDB.getConnection();
			PreparedStatement stm = null;
			String sql = "update Phong \r\n" + "set TenPhong = ?, MaLP = ?, TinhTrangPhong = ?, MoTa = ?\r\n"
					+ "where MaPhong = ?";
			try {
				stm = con.prepareStatement(sql);
				stm.setString(1, p.getTenPhong());
				stm.setString(2, p.getLoaiPhong().getMaLoaiPhong());
				stm.setString(3, p.getTinhTrangPhong());
				stm.setString(4, p.getMoTa());
				stm.setString(5, p.getMaPhong());
				stm.executeUpdate();
			} catch (SQLException e) {
				e.printStackTrace();
				return false;
			}
			return true;
		}

		// Xoa dich vu
		public void delete(String maPhong) {
			// TODO Auto-generated method stub
			ConnectDB.getInstance();
			Connection con = ConnectDB.getConnection();
			PreparedStatement stm = null;
			String sql = "DELETE from Phong " + "where MaPhong = ?";
			try {
				stm = con.prepareStatement(sql);
				stm.setString(1, maPhong);

				stm.executeUpdate();
			} catch (SQLException e) {
				// TODO: handle exception
			}
		}
		
		public boolean capNhatPhongTheoTinhTrang(String tinhTrang, String maPhong) {
			// TODO Auto-generated method stub
			ConnectDB.getInstance();
			Connection con = ConnectDB.getConnection();
			PreparedStatement stm = null;
			String sql = "update Phong set TinhTrangPhong = ? "
					+ "where MaPhong = ? ";

			try {
				stm = con.prepareStatement(sql);
				stm.setString(1, tinhTrang);
				stm.setString(2, maPhong);
				stm.executeUpdate();
			} catch (SQLException e) {
				e.printStackTrace();
				return false;
			}
			return true;
		}
		
		public boolean capNhatTinhTrangPhongTheoTenPhong(String tinhTrang, String tenPhong) {
			// TODO Auto-generated method stub
			ConnectDB.getInstance();
			Connection con = ConnectDB.getConnection();
			PreparedStatement stm = null;
			String sql = "update Phong set TinhTrangPhong = ? "
					+ "where TenPhong = ? ";

			try {
				stm = con.prepareStatement(sql);
				stm.setString(1, tinhTrang);
				stm.setString(2, tenPhong);
				stm.executeUpdate();
			} catch (SQLException e) {
				e.printStackTrace();
				return false;
			}
			return true;
		}
		
		public ArrayList<Phong> getAllDataForTableDatPhong() {
			ArrayList<Phong> dsPhong = new ArrayList<>();
			try {
				ConnectDB.getInstance();
				Connection connect = ConnectDB.getConnection();
				String sql = "select * from Phong p inner join LoaiPhong lp on p.MaLP = lp.MaLP"; 
				ResultSet rs = connect.createStatement().executeQuery(sql);
				while(rs.next()) {
					LoaiPhong lp = new LoaiPhong(rs.getString(6), rs.getString(7), rs.getInt(8), rs.getDouble(9));
					Phong phong = new Phong(rs.getString(1), rs.getString(2), lp, rs.getString(4), rs.getString(5));
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
						+ "Where TenLP = ?";
				statement = connect.prepareStatement(sql);
				statement.setString(1, loaiPhong);
				ResultSet rs = statement.executeQuery();
				
				while(rs.next()) {
					LoaiPhong lp = new LoaiPhong(rs.getString(6), rs.getString(7), rs.getInt(8), rs.getDouble(9));
					Phong phong = new Phong(rs.getString(1), rs.getString(2), lp, rs.getString(4), rs.getString(5));
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
						+ "Where TinhTrangPhong = ?";
				statement = connect.prepareStatement(sql);
				statement.setString(1, tinhTrangPhong);
				ResultSet rs = statement.executeQuery();
				
				while(rs.next()) {

					LoaiPhong lp = new LoaiPhong(rs.getString(6), rs.getString(7), rs.getInt(8), rs.getDouble(9));
					Phong phong = new Phong(rs.getString(1), rs.getString(2), lp, rs.getString(4), rs.getString(5));
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
						+ "Where SucChua = ?";
				statement = connect.prepareStatement(sql);
				statement.setInt(1, soNguoi);
				ResultSet rs = statement.executeQuery();
				
				while(rs.next()) {
					LoaiPhong lp = new LoaiPhong(rs.getString(6), rs.getString(7), rs.getInt(8), rs.getDouble(9));
					Phong phong = new Phong(rs.getString(1), rs.getString(2), lp, rs.getString(4), rs.getString(5));
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
						+ "Where TenPhong = ?";
				statement = connect.prepareStatement(sql);
				statement.setString(1, tenPhong);
				ResultSet rs = statement.executeQuery();
				
				while(rs.next()) {
					LoaiPhong lp = new LoaiPhong(rs.getString(6), rs.getString(7), rs.getInt(8), rs.getDouble(9));
					Phong phong = new Phong(rs.getString(1), rs.getString(2), lp, rs.getString(4), rs.getString(5));
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
//				tenPhong = tenPhong.replace("%", "!%");
				String sql = "select * from Phong p inner join LoaiPhong lp on p.MaLP = lp.MaLP "
						+ "Where TenPhong LIKE ?";
				statement = connect.prepareStatement(sql);
				statement.setString(1, "%" + tenPhong + "%");
				ResultSet rs = statement.executeQuery();
				
				while(rs.next()) {
					LoaiPhong lp = new LoaiPhong(rs.getString(6), rs.getString(7), rs.getInt(8), rs.getDouble(9));
					Phong phong = new Phong(rs.getString(1), rs.getString(2), lp, rs.getString(4), rs.getString(5));
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
