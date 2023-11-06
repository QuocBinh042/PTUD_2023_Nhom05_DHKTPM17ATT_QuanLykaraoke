package entity;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Objects;

public class PhieuDatPhong {
	private String maPDP;
	private LocalDate ngayDatPhong;
	private LocalTime gioDatPhong;
	private NhanVien nhanVien;
	private KhachHang khachHang;
	private int soLuongKhach;
	private Boolean tinhTrangPDP;
	private String moTa;
	
	
	
	public PhieuDatPhong() {
		setMaPDP("");
		setNgayDatPhong(LocalDate.now());
		setGioDatPhong(LocalTime.now());
		setNhanVien(new NhanVien());
		setKhachHang(new KhachHang());
		setSoLuongKhach(1);
		setTinhTrangPDP(true);
		setMoTa("");
	}

	public PhieuDatPhong(String maPDP, LocalDate ngayDatPhong, LocalTime gioDatPhong, NhanVien nhanVien,
			KhachHang khachHang, int soLuongKhach, Boolean tinhTrangPDP, String moTa) {
		this.maPDP = maPDP;
		this.ngayDatPhong = ngayDatPhong;
		this.gioDatPhong = gioDatPhong;
		this.nhanVien = nhanVien;
		this.khachHang = khachHang;
		this.soLuongKhach = soLuongKhach;
		this.tinhTrangPDP = tinhTrangPDP;
		this.moTa = moTa;
	}
	
	public String getMaPDP() {
		return maPDP;
	}
	private void setMaPDP(String maPDP) {
		this.maPDP = maPDP;
	}
	public LocalDate getNgayDatPhong() {
		return ngayDatPhong;
	}
	public void setNgayDatPhong(LocalDate ngayDatPhong) {
		this.ngayDatPhong = ngayDatPhong;
	}
	public LocalTime getGioDatPhong() {
		return gioDatPhong;
	}
	public void setGioDatPhong(LocalTime gioDatPhong) {
		this.gioDatPhong = gioDatPhong;
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
	public Boolean getTinhTrangPDP() {
		return tinhTrangPDP;
	}
	public void setTinhTrangPDP(Boolean tinhTrangPDP) {
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
		return "PhieuDatPhong [maPDP=" + maPDP + ", ngayDatPhong=" + ngayDatPhong + ", gioDatPhong=" + gioDatPhong
				+ ", nhanVien=" + nhanVien + ", khachHang=" + khachHang + ", soLuongKhach=" + soLuongKhach
				+ ", tinhTrangPDP=" + tinhTrangPDP + ", moTa=" + moTa + "]";
	}
}
