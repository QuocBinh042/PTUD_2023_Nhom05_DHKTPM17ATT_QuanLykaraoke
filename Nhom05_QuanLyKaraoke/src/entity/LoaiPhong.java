package entity;

import java.util.Objects;

public class LoaiPhong {
	private String maLoaiPhong;
	private String tenLoaiPhong;
	private int sucChua;
	private double giaLoaiPhong;

	public LoaiPhong() {
		this("", "", 0, 0);
	}

	public LoaiPhong(String maLoaiPhong, String tenLoaiPhong, int sucChua, double giaLoaiPhong) {
		this.maLoaiPhong = maLoaiPhong;
		this.tenLoaiPhong = tenLoaiPhong;
		this.sucChua = sucChua;
		this.giaLoaiPhong = giaLoaiPhong;
	}

	public String getMaLoaiPhong() {
		return maLoaiPhong;
	}

	public void setMaLoaiPhong(String maLoaiPhong) {
		this.maLoaiPhong = maLoaiPhong;
	}

	public String getTenLoaiPhong() {
		return tenLoaiPhong;
	}

	public void setTenLoaiPhong(String tenLoaiPhong) {
		this.tenLoaiPhong = tenLoaiPhong;
	}

	public int getSucChua() {
		return sucChua;
	}

	public void setSucChua(int sucChua) {
		this.sucChua = sucChua;
	}

	public double getGiaLoaiPhong() {
		return giaLoaiPhong;
	}

	public void setGiaLoaiPhong(double giaLoaiPhong) {
		this.giaLoaiPhong = giaLoaiPhong;
	}

	@Override
	public int hashCode() {
		return Objects.hash(maLoaiPhong);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		LoaiPhong other = (LoaiPhong) obj;
		return Objects.equals(maLoaiPhong, other.maLoaiPhong);
	}

	@Override
	public String toString() {
		return "LoaiPhong [maLoaiPhong=" + maLoaiPhong + ", tenLoaiPhong=" + tenLoaiPhong + ", sucChua=" + sucChua
				+ ", giaLoaiPhong=" + giaLoaiPhong + "]";
	}

}
