package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import connectDB.ConnectDB;
import entity.LoaiPhong;
import entity.Phong;

public class daoPhong {
	public ArrayList<Phong> getAllPhong() {
		ArrayList<Phong> dsPhong = new ArrayList<Phong>();
		ConnectDB.getInstance();
		Connection connect = ConnectDB.getConnection();
		try {
			String sql = "select p.MaPhong, TenPhong, lp.MaLP, lp.TenLP, lp.SucChua, lp.GiaPhong, p.TinhTrangPhong,p.MoTa from Phong p\r\n"
					+ "inner join LoaiPhong lp on p.MaLP = lp.MaLP";
			Statement statement = connect.createStatement();
			ResultSet rs = statement.executeQuery(sql);
			while (rs.next()) {
				String maPhong = rs.getString(1);
				String tenPhong = rs.getString(2);
				String maLP = rs.getString(3);
				String tenLP = rs.getString(4);
				int sucChua = rs.getInt(5);
				double giaP = rs.getDouble(6);
				String tinhTrang = rs.getString(7);
				String moTa = rs.getString(8);
				dsPhong.add(new Phong(maPhong, tenPhong, maLP, tenLP, sucChua, giaP, tinhTrang, moTa));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dsPhong;
	}
}
