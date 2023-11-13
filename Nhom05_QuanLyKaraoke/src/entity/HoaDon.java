package entity;

import java.io.Serializable;
import java.sql.Time;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.Objects;

public class HoaDon implements Serializable{
	private String maHoaDon;
	private Date ngayThanhToan;
	private LocalTime gioThanhToan;
	private NhanVien nv;
	private KhachHang kh;
	private KhuyenMai km;
	private Double tongHoaDon;
	private ArrayList<ChiTietHoaDon> CTHD;
	private ArrayList<CTDVPhong> CTDVP;

	public HoaDon(String maHD) {
		setMaHoaDon(maHD);
	}

	public HoaDon(String maHoaDon, LocalTime gioThanhToan, Date ngayThanhToan, NhanVien nv, KhachHang kh, KhuyenMai km) {
		super();
		this.maHoaDon = maHoaDon;
		this.ngayThanhToan = ngayThanhToan;
		this.gioThanhToan = gioThanhToan;
		this.nv = nv;
		this.kh = kh;
		this.km = km;
		this.tongHoaDon = 0.00;
	}
	
	public HoaDon(String maHoaDon, LocalTime gioThanhToan, Date ngayThanhToan, NhanVien nv, KhachHang kh, KhuyenMai km, Double tongHoaDon) {
		super();
		this.maHoaDon = maHoaDon;
		this.ngayThanhToan = ngayThanhToan;
		this.gioThanhToan = gioThanhToan;
		this.nv = nv;
		this.kh = kh;
		this.km = km;
		this.tongHoaDon = tongHoaDon;
	}

	public String getMaHoaDon() {
		return maHoaDon;
	}

	public void setMaHoaDon(String maHoaDon) {
		this.maHoaDon = maHoaDon;
	}

	public Date getNgayThanhToan() {
		return ngayThanhToan;
	}

	public void setNgayThanhToan(Date ngayThanhToan) {
		this.ngayThanhToan = ngayThanhToan;
	}

	public LocalTime getGioThanhToan() {
		return gioThanhToan;
	}

	public void setGioThanhToan(LocalTime gioThanhToan) {
		this.gioThanhToan = gioThanhToan;
	}

	public NhanVien getNv() {
		return nv;
	}

	public void setNv(NhanVien nv) {
		this.nv = nv;
	}

	public KhachHang getKh() {
		return kh;
	}

	public void setKh(KhachHang kh) {
		this.kh = kh;
	}

	public KhuyenMai getKm() {
		return km;
	}

	public void setKm(KhuyenMai km) {
		this.km = km;
	}

	public Double getTongHoaDon() {
		return tongHoaDon;
	}

	public void setTongHoaDon(Double tongHoaDon) {
		this.tongHoaDon = tongHoaDon;
	}

	public ArrayList<ChiTietHoaDon> getCTHD() {
		return CTHD;
	}

	public void setCTHD(ArrayList<ChiTietHoaDon> cTHD) {
		CTHD = cTHD;
	}

	public ArrayList<CTDVPhong> getCTDVP() {
		return CTDVP;
	}

	public void setCTDVP(ArrayList<CTDVPhong> cTDVP) {
		CTDVP = cTDVP;
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

	// tinh tien
	public Double tinhTongHD() {
		Double tongTien = 0.0;
		for (CTDVPhong chiTietDVPhong : getCTDVP()) {
			tongTien += chiTietDVPhong.tinhTienDV();
		}

		for (ChiTietHoaDon cthd : getCTHD()) {
			tongTien += cthd.tinhTienPhong();
		}

		if (km.getTrangThai()) {
			tongTien = tongTien - tongTien * km.getPhanTramGiam();
		}

		return tongTien;
	}
}
