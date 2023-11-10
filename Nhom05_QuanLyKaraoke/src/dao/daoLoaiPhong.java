package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import connectDB.ConnectDB;
import entity.DichVu;
import entity.LoaiPhong;

public class daoLoaiPhong {
	public ArrayList<LoaiPhong> getAllLoaiPhong() {
		ArrayList<LoaiPhong> dsLoaiPhong = new ArrayList<LoaiPhong>();
		ConnectDB.getInstance();
		Connection connect = ConnectDB.getConnection();
		try {
			String sql = "select *from LoaiPhong";
			Statement statement = connect.createStatement();
			ResultSet rs = statement.executeQuery(sql);
			while (rs.next()) {
				dsLoaiPhong.add(new LoaiPhong(rs.getString(1), rs.getString(2), rs.getInt(3), rs.getDouble(4)));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dsLoaiPhong;
	}
}
