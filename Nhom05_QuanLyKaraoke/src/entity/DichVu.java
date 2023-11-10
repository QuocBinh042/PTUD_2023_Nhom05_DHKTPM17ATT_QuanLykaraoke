package entity;

import java.util.Objects;

public class DichVu {
	private String maDichVu;
	private String tenDichVu;
	private double donGiaNhap;
	private double donGiaBan;
	private String donVi;
	private int soLuong;
	private String tinhTrang; // (0: het, 1: sap het; 2: con; 3 da xoa)

	public DichVu() {
		this("", "", 0, 0, "", 0, "");
	}

	public DichVu(String maDichVu, String tenDichVu, double donGiaNhap, double donGiaBan, String donVi, int soLuong,
			String tinhTrang) {
		this.maDichVu = maDichVu;
		this.tenDichVu = tenDichVu;
		this.donGiaNhap = donGiaNhap;
		this.donGiaBan = donGiaBan;
		this.donVi = donVi;
		this.soLuong = soLuong;
		this.tinhTrang = tinhTrang;
	}

	public String getMaDichVu() {
		return maDichVu;
	}

	public void setMaDichVu(String maDichVu) {
		this.maDichVu = maDichVu;
	}

	public String getTenDichVu() {
		return tenDichVu;
	}

	public void setTenDichVu(String tenDichVu) {
		this.tenDichVu = tenDichVu;
	}

	public double getDonGiaNhap() {
		return donGiaNhap;
	}

	public void setDonGiaNhap(double donGiaNhap) {
		this.donGiaNhap = donGiaNhap;
	}

	public double getDonGiaBan() {
		return donGiaBan;
	}

	public void setDonGiaBan(double donGiaBan) {
		this.donGiaBan = donGiaBan;
	}

	public String getDonVi() {
		return donVi;
	}

	public void setDonVi(String donVi) {
		this.donVi = donVi;
	}

	public int getSoLuong() {
		return soLuong;
	}

	public void setSoLuong(int soLuong) {
		this.soLuong = soLuong;
	}

	public String getTinhTrang() {
		return tinhTrang;
	}

	public void setTinhTrang(String tinhTrang) {
		this.tinhTrang = tinhTrang;
	}

	@Override
	public int hashCode() {
		return Objects.hash(maDichVu);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		DichVu other = (DichVu) obj;
		return Objects.equals(maDichVu, other.maDichVu);
	}

	@Override
	public String toString() {
		return "DichVu [maDichVu=" + maDichVu + ", tenDichVu=" + tenDichVu + ", donGiaNhap=" + donGiaNhap
				+ ", donGiaBan=" + donGiaBan + ", donVi=" + donVi + ", soLuong=" + soLuong + ", tinhTrang=" + tinhTrang
				+ "]";
	}
}
