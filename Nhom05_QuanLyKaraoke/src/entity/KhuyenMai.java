package entity;

import java.io.Serializable;
import java.util.Date;

public class KhuyenMai implements Serializable{
	private String maKM;
	private Double phanTramGiam;
	private Date ngayBD;
	private Date ngayKT;
	private String moTa;
	private Boolean trangThai;

	public KhuyenMai(String maKM, Double phanTramGiam, Date ngayBD, Date ngayKT, String moTa, Boolean trangThai) {
		super();
		this.maKM = maKM;
		this.phanTramGiam = phanTramGiam;
		this.ngayBD = ngayBD;
		this.ngayKT = ngayKT;
		this.moTa = moTa;
		this.trangThai = trangThai;
	}
	
	public KhuyenMai(String maKM) {
		this.maKM = maKM;
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

	public Date getNgayBD() {
		return ngayBD;
	}

	public void setNgayBD(Date ngayBD) {
		this.ngayBD = ngayBD;
	}

	public Date getNgayKT() {
		return ngayKT;
	}

	public void setNgayKT(Date ngayKT) {
		this.ngayKT = ngayKT;
	}

	public String getMoTa() {
		return moTa;
	}

	public void setMoTa(String moTa) {
		this.moTa = moTa;
	}

	public Boolean getTrangThai() {
		return trangThai;
	}

	public void setTrangThai(Boolean trangThai) {
		this.trangThai = trangThai;
	}

	@Override
	public String toString() {
		return "KhuyenMai [maKM=" + maKM + ", phanTramGiam=" + phanTramGiam + ", ngayBD=" + ngayBD + ", ngayKT="
				+ ngayKT + ", moTa=" + moTa + ", trangThai=" + trangThai + "]";
	}

}
