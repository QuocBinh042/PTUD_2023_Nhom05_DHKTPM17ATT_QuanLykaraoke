package entity;

import java.time.LocalDate;

public class KhuyenMai {
	private String maKM;
	private Double phanTramGiam;
	private LocalDate ngayBD;
	private LocalDate ngayKT;
	private String trangThai;
	private String moTa;

	public KhuyenMai(String maKM, Double phanTramGiam, LocalDate ngayBD, LocalDate ngayKT, String trangThai,
			String moTa) {
		super();
		this.maKM = maKM;
		this.phanTramGiam = phanTramGiam;
		this.ngayBD = ngayBD;
		this.ngayKT = ngayKT;
		this.trangThai = trangThai;
		this.moTa = moTa;
	}

	public String getMaKM() {
		return maKM;
	}

	public void setMaKM(String maKM) {
		this.maKM = maKM;
	}

	public Double getPhanTramGiam() {
		return phanTramGiam;
	}

	public void setPhanTramGiam(Double phanTramGiam) {
		this.phanTramGiam = phanTramGiam;
	}

	public LocalDate getNgayBD() {
		return ngayBD;
	}

	public void setNgayBD(LocalDate ngayBD) {
		this.ngayBD = ngayBD;
	}

	public LocalDate getNgayKT() {
		return ngayKT;
	}

	public void setNgayKT(LocalDate ngayKT) {
		this.ngayKT = ngayKT;
	}

	public String getTrangThai() {
		return trangThai;
	}

	public void setTrangThai(String trangThai) {
		this.trangThai = trangThai;
	}

	public String getMoTa() {
		return moTa;
	}

	public void setMoTa(String moTa) {
		this.moTa = moTa;
	}

	@Override
	public String toString() {
		return "KhuyenMai [maKM=" + maKM + ", phanTramGiam=" + phanTramGiam + ", ngayBD=" + ngayBD + ", ngayKT="
				+ ngayKT + ", trangThai=" + trangThai + ", moTa=" + moTa + "]";
	}

}
