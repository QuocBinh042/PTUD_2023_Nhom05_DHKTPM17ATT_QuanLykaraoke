package entity;

import java.io.Serializable;
import java.util.Objects;

public class Phong implements Serializable{
	private String maPhong;
	private String tenPhong;
	private LoaiPhong loaiPhong;
	private String tinhTrangPhong;
	private String moTa;

	public Phong(String maPhong, String tenPhong, LoaiPhong loaiPhong, String tinhTrangPhong,
			String moTa) {
		this.maPhong = maPhong;
		this.tenPhong = tenPhong;
		this.loaiPhong = loaiPhong;
		this.tinhTrangPhong = tinhTrangPhong;
		this.moTa = moTa;
	}

	public String getMoTa() {
		return moTa;
	}

	public void setMoTa(String moTa) {
		this.moTa = moTa;
	}


	public String getMaPhong() {
		return maPhong;
	}

	private void setMaPhong(String maPhong) {
		this.maPhong = maPhong;
	}

	public String getTenPhong() {
		return tenPhong;
	}

	public void setTenPhong(String tenPhong) {
		this.tenPhong = tenPhong;
	}

	public LoaiPhong getLoaiPhong() {
		return loaiPhong;
	}

	public void setLoaiPhong(LoaiPhong loaiPhong) {
		this.loaiPhong = loaiPhong;
	}

	public String getTinhTrangPhong() {
		return tinhTrangPhong;
	}

	public void setTinhTrangPhong(String tinhTrangPhong) {
		this.tinhTrangPhong = tinhTrangPhong;
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
		return "Phong [maPhong=" + maPhong + ", tenPhong=" + tenPhong + ", loaiPhong=" + loaiPhong + ", tinhTrangPhong="
				+ tinhTrangPhong + ", moTa=" + moTa + "]";
	}
}
