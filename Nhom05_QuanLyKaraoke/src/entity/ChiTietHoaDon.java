package entity;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;

public class ChiTietHoaDon implements Serializable {
	private Phong phong;
	private HoaDon hd;
	private Date thoiGianNhanPhong;
	private Date thoiGianTraPhong;

	public ChiTietHoaDon() {
		super();
	}

	public ChiTietHoaDon(Phong phong, HoaDon hd, Date thoiGianNhanPhong, Date thoiGianTraPhong) {
		super();
		this.phong = phong;
		this.hd = hd;
		this.thoiGianNhanPhong = thoiGianNhanPhong;
		this.thoiGianTraPhong = thoiGianTraPhong;
	}

	public Phong getPhong() {
		return phong;
	}

	public void setPhong(Phong phong) {
		this.phong = phong;
	}

	public HoaDon getHd() {
		return hd;
	}

	public void setHd(HoaDon hd) {
		this.hd = hd;
	}

	public Date getThoiGianNhanPhong() {
		return thoiGianNhanPhong;
	}

	public void setThoiGianNhanPhong(Date thoiGianNhanPhong) {
		this.thoiGianNhanPhong = thoiGianNhanPhong;
	}

	public Date getThoiGianTraPhong() {
		return thoiGianTraPhong;
	}

	public void setThoiGianTraPhong(Date thoiGianTraPhong) {
		this.thoiGianTraPhong = thoiGianTraPhong;
	}

	// Tính toán
	public long tinhThoiLuong() {
		return (thoiGianTraPhong.getTime() - thoiGianNhanPhong.getTime()) / 60000;
	}

	public Double tinhTienPhong() {
		Double tien;
		tien = phong.getLoaiPhong().getGiaLoaiPhong() * (tinhThoiLuong() / 60);
		long TGdu = tinhThoiLuong() % 60;
		if (TGdu > 0 && TGdu < 15) {
			tien += phong.getLoaiPhong().getGiaLoaiPhong() * 0.25;
		} else if (TGdu >= 15 && TGdu < 30) {
			tien += phong.getLoaiPhong().getGiaLoaiPhong() * 0.5;
		} else if (TGdu >= 30 && TGdu < 45) {
			tien += phong.getLoaiPhong().getGiaLoaiPhong() * 0.75;
		} else {
			tien += phong.getLoaiPhong().getGiaLoaiPhong();
		}
		return tien;
	}

	@Override
	public String toString() {
		return "ChiTietHoaDon [phong=" + phong + ", hd=" + hd + ", thoiGianNhanPhong=" + thoiGianNhanPhong
				+ ", thoiGianTraPhong=" + thoiGianTraPhong + "]";
	}

}
