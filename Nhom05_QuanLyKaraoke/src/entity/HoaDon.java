package entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.Objects;

public class HoaDon implements Serializable{
	private String maHoaDon;
	private Date ngayThanhToan;
	private Date gioThanhToan;
	private NhanVien nv;
	private KhachHang kh;
	private KhuyenMai km;
	private Double tongHoaDon;
	private Double tienKhachTra;
	private ArrayList<ChiTietHoaDon> CTHD;
	private ArrayList<CTDVPhong> CTDVP;

	public HoaDon() {
		super();
		// TODO Auto-generated constructor stub
	}

	public HoaDon(String maHoaDon, Date ngayThanhToan, Date gioThanhToan, NhanVien nv, KhachHang kh, KhuyenMai km,
			Double tongHoaDon, Double tienKhachTra) {
		super();
		this.maHoaDon = maHoaDon;
		this.ngayThanhToan = ngayThanhToan;
		this.gioThanhToan = gioThanhToan;
		this.nv = nv;
		this.kh = kh;
		this.km = km;
		this.tongHoaDon = tongHoaDon;
		this.tienKhachTra = tienKhachTra;
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

	public Date getGioThanhToan() {
		return gioThanhToan;
	}

	public void setGioThanhToan(Date gioThanhToan) {
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

	public Double getTienKhachTra() {
		return tienKhachTra;
	}

	public void setTienKhachTra(Double tienKhachTra) {
		this.tienKhachTra = tienKhachTra;
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

	@Override
	public String toString() {
		return "HoaDon [maHoaDon=" + maHoaDon + ", ngayThanhToan=" + ngayThanhToan + ", gioThanhToan=" + gioThanhToan
				+ ", nv=" + nv + ", kh=" + kh + ", km=" + km + ", tongHoaDon=" + tongHoaDon + ", tienKhachTra="
				+ tienKhachTra + ", CTHD=" + CTHD + ", CTDVP=" + CTDVP + "]";
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
	
	public Double tinhTienTraKhach() {
		return tienKhachTra - tinhTongHD();
	}
}
