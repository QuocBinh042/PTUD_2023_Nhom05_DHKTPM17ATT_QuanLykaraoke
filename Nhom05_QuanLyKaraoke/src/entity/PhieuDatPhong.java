package entity;

import java.util.Date;
import java.time.LocalDate;
import java.util.Objects;

public class PhieuDatPhong {
	private String maPDP;
	private KhachHang khachHang;
	private NhanVien nhanVien;
	private Phong phong;
	private Date thoiGianDatPhong;
	private Date thoiGianNhanPhong;
	private int soLuongKhach;
	private int tinhTrangPDP;
	private String moTa;

	public PhieuDatPhong() {
		setMaPDP("");
		setThoiGianDatPhong(new Date());
		setThoiGianNhanPhong(new Date());
		setNhanVien(new NhanVien());
		setKhachHang(new KhachHang());
		setSoLuongKhach(1);
		setTinhTrangPDP(1);
		setMoTa("");
	}

	public PhieuDatPhong(String maPDP, KhachHang khachHang, NhanVien nhanVien, Phong phong, Date thoiGianDatPhong,
			Date thoiGianNhanPhong, int soLuongKhach, int tinhTrangPDP, String moTa) {
		super();
		this.maPDP = maPDP;
		this.khachHang = khachHang;
		this.nhanVien = nhanVien;
		this.phong = phong;
		this.thoiGianDatPhong = thoiGianDatPhong;
		this.thoiGianNhanPhong = thoiGianNhanPhong;
		this.soLuongKhach = soLuongKhach;
		this.tinhTrangPDP = tinhTrangPDP;
		this.moTa = moTa;
	}

	public Phong getPhong() {
		return phong;
	}

	public void setPhong(Phong phong) {
		this.phong = phong;
	}

	public Date getThoiGianDatPhong() {
		return thoiGianDatPhong;
	}

	public void setThoiGianDatPhong(Date thoiGianDatPhong) {
		this.thoiGianDatPhong = thoiGianDatPhong;
	}

	public Date getThoiGianNhanPhong() {
		return thoiGianNhanPhong;
	}

	public void setThoiGianNhanPhong(Date thoiGianNhanPhong) {
		this.thoiGianNhanPhong = thoiGianNhanPhong;
	}

	public String getMaPDP() {
		return maPDP;
	}

	private void setMaPDP(String maPDP) {
		this.maPDP = maPDP;
	}

	public NhanVien getNhanVien() {
		return nhanVien;
	}

	public void setNhanVien(NhanVien nhanVien) {
		this.nhanVien = nhanVien;
	}

	public KhachHang getKhachHang() {
		return khachHang;
	}

	public void setKhachHang(KhachHang khachHang) {
		this.khachHang = khachHang;
	}

	public int getSoLuongKhach() {
		return soLuongKhach;
	}

	public void setSoLuongKhach(int soLuongKhach) {
		this.soLuongKhach = soLuongKhach;
	}

	public int getTinhTrangPDP() {
		return tinhTrangPDP;
	}

	public void setTinhTrangPDP(int tinhTrangPDP) {
		this.tinhTrangPDP = tinhTrangPDP;
	}

	public String getMoTa() {
		return moTa;
	}

	public void setMoTa(String moTa) {
		this.moTa = moTa;
	}

	@Override
	public int hashCode() {
		return Objects.hash(maPDP);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PhieuDatPhong other = (PhieuDatPhong) obj;
		return Objects.equals(maPDP, other.maPDP);
	}

	@Override
	public String toString() {
		return "PhieuDatPhong [maPDP=" + maPDP + ", ngayDatPhong=" + thoiGianDatPhong + ", gioDatPhong="
				+ thoiGianNhanPhong + ", nhanVien=" + nhanVien + ", khachHang=" + khachHang + ", soLuongKhach="
				+ soLuongKhach + ", tinhTrangPDP=" + tinhTrangPDP + ", moTa=" + moTa + "]";
	}
}
