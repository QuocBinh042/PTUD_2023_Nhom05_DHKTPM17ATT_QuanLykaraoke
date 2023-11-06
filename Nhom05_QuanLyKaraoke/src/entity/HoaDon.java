package entity;

import java.sql.Time;
import java.time.LocalDate;
import java.util.Date;
import java.util.Objects;

public class HoaDon {
	private String maHoaDon;
	private LocalDate ngayThanhToan;
	private Time gioThanhToan;
	private String tenNV;
	private String tenKH;
	private String sdt;
	private Double tongTien;

	public HoaDon() {
		this("Mã hoá đơn", LocalDate.now(), new Time(0),"Tên NV", "Tên KH", "Số điện thoại", 0.0);
	}
	
	public HoaDon(String maHoaDon, LocalDate ngayThanhToan, Time i, String tenNV, String tenKH, String sdt,
			Double tongTien) {
		super();
		this.maHoaDon = maHoaDon;
		this.ngayThanhToan = ngayThanhToan;
		this.gioThanhToan = i;
		this.tenNV = tenNV;
		this.tenKH = tenKH;
		this.sdt = sdt;
		this.tongTien = tongTien;
	}

	public String getMaHoaDon() {
		return maHoaDon;
	}

	public void setMaHoaDon(String maHoaDon) {
		this.maHoaDon = maHoaDon;
	}

	public LocalDate getNgayThanhToan() {
		return ngayThanhToan;
	}

	public void setNgayThanhToan(LocalDate ngayThanhToan) {
		this.ngayThanhToan = ngayThanhToan;
	}

	public Time getGioThanhToan() {
		return gioThanhToan;
	}

	public void setGioThanhToan(Time gioThanhToan) {
		this.gioThanhToan = gioThanhToan;
	}

	public String getTenNV() {
		return tenNV;
	}

	public void setTenNV(String tenNV) {
		this.tenNV = tenNV;
	}

	public String getTenKH() {
		return tenKH;
	}

	public void setTenKH(String tenKH) {
		this.tenKH = tenKH;
	}

	public String getSdt() {
		return sdt;
	}

	public void setSdt(String sdt) {
		this.sdt = sdt;
	}

	public Double getTongTien() {
		return tongTien;
	}

	public void setTongTien(Double tongTien) {
		this.tongTien = tongTien;
	}

	@Override
	public int hashCode() {
		return Objects.hash(maHoaDon);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		HoaDon other = (HoaDon) obj;
		return Objects.equals(maHoaDon, other.maHoaDon);
	}

	@Override
	public String toString() {
		return "HoaDon [maHoaDon=" + maHoaDon + ", ngayThanhToan=" + ngayThanhToan + ", gioThanhToan=" + gioThanhToan
				+ ", tenNV=" + tenNV + ", tenKH=" + tenKH + ", sdt=" + sdt + ", tongTien=" + tongTien + "]";
	}

}
