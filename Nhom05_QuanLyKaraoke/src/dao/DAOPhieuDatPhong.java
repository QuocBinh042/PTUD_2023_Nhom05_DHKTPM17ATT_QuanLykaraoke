package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import connectDB.ConnectDB;
import entity.KhachHang;
import entity.LoaiPhong;
import entity.NhanVien;
import entity.PhieuDatPhong;
import entity.Phong;


public class DAOPhieuDatPhong {
	public DAOPhieuDatPhong() {
		
	}
	
	public boolean createPDP(PhieuDatPhong pdp) {
		ConnectDB.getInstance();
		Connection connect = ConnectDB.getConnection();
		PreparedStatement pre = null;
		int n = 0;
		try { 
			java.sql.Timestamp timeStamp = new Timestamp(pdp.getThoiGianDatPhong().getTime());
			java.sql.Timestamp timeStamp2 = new Timestamp(pdp.getThoiGianNhanPhong().getTime());
			pre = connect.prepareStatement("insert into "
					+ "PhieuDatPhong values(?,?,?,?,?,?,?,?,?)");
			pre.setString(1, pdp.getMaPDP());
			pre.setString(2, pdp.getNhanVien().getMaNV());
			pre.setString(3, pdp.getKhachHang().getMaKH());
			pre.setString(4, pdp.getPhong().getMaPhong());
			pre.setTimestamp(4, timeStamp);
			pre.setTimestamp(5, timeStamp2);
			pre.setInt(6, pdp.getSoLuongKhach());
			pre.setInt(7, pdp.getTinhTrangPDP());
			pre.setString(8, pdp.getMoTa());
			n = pre.executeUpdate();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return n > 0;
	}
	
	public ArrayList<PhieuDatPhong> getAllDataPDP() {
		ArrayList<PhieuDatPhong> dsPDP = new ArrayList<>();
		try {
			ConnectDB.getInstance();
			Connection connect = ConnectDB.getConnection();
			String sql = "select * from PhieuDatPhong pdp "
					+ "inner join KhachHang kh on pdp.MaKH = kh.MaKH "
					+ "inner join NhanVien nv on pdp.MaNV = nv.MaNV "
					+ "inner join Phong p on pdp.MaPhong = p.MaPhong "
					+ "inner join LoaiPhong lp on p.MaLP = lp.MaLP";
			ResultSet rs = connect.createStatement().executeQuery(sql);
			while(rs.next()) {
				
				NhanVien nv = new entity.NhanVien(rs.getString(18), rs.getString(19), rs.getDate(20),
						rs.getBoolean(21), rs.getString(22), rs.getString(23), rs.getString(24), rs.getString(25), rs.getBoolean(26));
				
				KhachHang kh = new KhachHang(rs.getString(10), rs.getString(11), rs.getBoolean(12), rs.getBoolean(13),
						rs.getString(14), rs.getString(15), rs.getInt(16), rs.getString(17));
				
				LoaiPhong lp = new LoaiPhong(rs.getString(32), rs.getString(33), rs.getInt(34), rs.getDouble(35));
				
				Phong phong = new Phong(rs.getString(27), rs.getString(28), lp, rs.getString(30), rs.getString(31));
				
				
						
				PhieuDatPhong pdp = new PhieuDatPhong(rs.getString(1), kh, nv, phong, rs.getTimestamp(5),
						rs.getTimestamp(6), rs.getInt(7), rs.getInt(8), rs.getString(9));
				
				dsPDP.add(pdp);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return dsPDP;
	}
	
	public ArrayList<PhieuDatPhong> timKiemPDPTheoMa(String maPDP) {
		ArrayList<PhieuDatPhong> dsPDP = new ArrayList<>();
		try {
			ConnectDB.getInstance();
			Connection connect = ConnectDB.getConnection();
			PreparedStatement statement = null;
			String sql = "select * from PhieuDatPhong pdp "
					+ "inner join KhachHang kh on pdp.MaKH = kh.MaKH "
					+ "inner join NhanVien nv on pdp.MaNV = nv.MaNV "
					+ "inner join Phong p on pdp.MaPhong = p.MaPhong "
					+ "inner join LoaiPhong lp on p.MaLP = lp.MaLP "
					+ "where MaPDP = ?";
			
			statement = connect.prepareStatement(sql);
			statement.setString(1, maPDP);
			ResultSet rs = statement.executeQuery();
			while(rs.next()) {
				
				NhanVien nv = new entity.NhanVien(rs.getString(18), rs.getString(19), rs.getDate(20),
						rs.getBoolean(21), rs.getString(22), rs.getString(23), rs.getString(24), rs.getString(25), rs.getBoolean(26));
				
				KhachHang kh = new KhachHang(rs.getString(10), rs.getString(11), rs.getBoolean(12), rs.getBoolean(13),
						rs.getString(14), rs.getString(15), rs.getInt(16), rs.getString(17));
				
				LoaiPhong lp = new LoaiPhong(rs.getString(32), rs.getString(33), rs.getInt(34), rs.getDouble(35));
				
				Phong phong = new Phong(rs.getString(27), rs.getString(28), lp, rs.getString(30), rs.getString(31));
				
				PhieuDatPhong pdp = new PhieuDatPhong(rs.getString(1), kh, nv, phong, rs.getTimestamp(5),
						rs.getTimestamp(6), rs.getInt(7), rs.getInt(8), rs.getString(9));
				
				dsPDP.add(pdp);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return dsPDP;
	}
	
	public boolean capNhatTinhTrangPDP(int tinhTrang, String maPDP) {
		// TODO Auto-generated method stub
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		PreparedStatement stm = null;
		String sql = "update PhieuDatPhong set TinhTrangPDP = ? "
				+ "where MaPDP = ? ";

		try {
			stm = con.prepareStatement(sql);
			stm.setInt(1, tinhTrang);
			stm.setString(2, maPDP);
			stm.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
}
