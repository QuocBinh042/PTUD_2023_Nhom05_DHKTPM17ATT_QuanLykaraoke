package entity;

import java.util.Objects;

public class Phong {
	private String maPhong;
	private String tenPhong;
	private String maLP;
	private String loaiPhong;
	private int sucChua;
	private double giaPhong;
	private String tinhTrang; // (0: con trong, 1: dang thue, 2: da dat truoc, 3: da xoa)
	private String moTa;

	public Phong() {
		this("", "", "", "", 0, 0, "", "");
	}

	public Phong(String maPhong, String tenPhong, String maLP, String loaiPhong, int sucChua, double giaPhong,
			String tinhTrang, String moTa) {
		this.maPhong = maPhong;
		this.tenPhong = tenPhong;
		this.maLP = maLP;
		this.loaiPhong = loaiPhong;
		this.sucChua = sucChua;
		this.giaPhong = giaPhong;
		this.tinhTrang = tinhTrang;
		this.moTa = moTa;
	}

	public String getMaPhong() {
		return maPhong;
	}

	public void setMaPhong(String maPhong) {
		this.maPhong = maPhong;
	}

	public String getTenPhong() {
		return tenPhong;
	}

	public void setTenPhong(String tenPhong) {
		this.tenPhong = tenPhong;
	}

	public String getMaLP() {
		return maLP;
	}

	public void setMaLP(String maLP) {
		this.maLP = maLP;
	}

	public String getLoaiPhong() {
		return loaiPhong;
	}

	public void setLoaiPhong(String loaiPhong) {
		this.loaiPhong = loaiPhong;
	}

	public int getSucChua() {
		return sucChua;
	}

	public void setSucChua(int sucChua) {
		this.sucChua = sucChua;
	}

	public double getGiaPhong() {
		return giaPhong;
	}

	public void setGiaPhong(double giaPhong) {
		this.giaPhong = giaPhong;
	}

	public String getTinhTrang() {
		return tinhTrang;
	}

	public void setTinhTrang(String tinhTrang) {
		this.tinhTrang = tinhTrang;
	}

	public String getMoTa() {
		return moTa;
	}

	public void setMoTa(String moTa) {
		this.moTa = moTa;
	}

	@Override
	public int hashCode() {
		return Objects.hash(maPhong);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Phong other = (Phong) obj;
		return Objects.equals(maPhong, other.maPhong);
	}

	@Override
	public String toString() {
		return "Phong [maPhong=" + maPhong + ", tenPhong=" + tenPhong + ", maLP=" + maLP + ", loaiPhong=" + loaiPhong
				+ ", sucChua=" + sucChua + ", giaPhong=" + giaPhong + ", tinhTrang=" + tinhTrang + ", moTa=" + moTa
				+ "]";
	}

}
