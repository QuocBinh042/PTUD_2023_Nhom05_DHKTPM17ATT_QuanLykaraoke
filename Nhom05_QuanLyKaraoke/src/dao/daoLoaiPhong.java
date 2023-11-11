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
	public ArrayList<LoaiPhong> getCBSC(String sucChua) {
		ArrayList<LoaiPhong> dsLoaiPhong = new ArrayList<LoaiPhong>();
		ConnectDB.getInstance();
		Connection connect = ConnectDB.getConnection();
		String sql = "";
		if(sucChua.equalsIgnoreCase("Tất cả")) {
			sql = "select *from LoaiPhong";
		}else if(sucChua.equalsIgnoreCase("10")) {
			sql = "select *from LoaiPhong where SucChua = 10";
		}else if(sucChua.equalsIgnoreCase("15")) {
			sql = "select *from LoaiPhong where SucChua = 15";
		}else if(sucChua.equalsIgnoreCase("20")) {
			sql = "select *from LoaiPhong where SucChua = 20";
		}
		try {
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
