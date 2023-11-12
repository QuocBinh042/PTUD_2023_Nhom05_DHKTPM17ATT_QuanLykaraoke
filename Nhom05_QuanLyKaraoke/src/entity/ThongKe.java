package entity;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;

public class ThongKe implements Serializable{
	private String tenPhong;
	private String loaiPhong;
	private LocalDate ngayThanhToan;
	private Double tienPhong;
	private Double tienDichVu;
	private Double tongTien;

	public ThongKe() {
		this("Tên phòng", "Loại phòng", LocalDate.now(), 0.0, 0.0, 0.0);
	}

	public ThongKe(String tenPhong, String loaiPhong, LocalDate ngayThanhToan, Double tienPhong, Double tienDichVu,
			Double tongTien) {
		super();
		this.tenPhong = tenPhong;
		this.loaiPhong = loaiPhong;
		this.ngayThanhToan = ngayThanhToan;
		this.tienPhong = tienPhong;
		this.tienDichVu = tienDichVu;
		this.tongTien = tongTien;
	}

	public String getTenPhong() {
		return tenPhong;
	}

	public void setTenPhong(String tenPhong) {
		this.tenPhong = tenPhong;
	}

	public String getLoaiPhong() {
		return loaiPhong;
	}

	public void setLoaiPhong(String loaiPhong) {
		this.loaiPhong = loaiPhong;
	}

	public LocalDate getNgayThanhToan() {
		return ngayThanhToan;
	}

	public void setNgayThanhToan(LocalDate ngayThanhToan) {
		this.ngayThanhToan = ngayThanhToan;
	}

	public Double getTienPhong() {
		return tienPhong;
	}

	public void setTienPhong(Double tienPhong) {
		this.tienPhong = tienPhong;
	}

	public Double getTienDichVu() {
		return tienDichVu;
	}

	public void setTienDichVu(Double tienDichVu) {
		this.tienDichVu = tienDichVu;
	}

	public Double getTongTien() {
		return tongTien;
	}

	public void setTongTien(Double tongTien) {
		this.tongTien = tongTien;
	}

	@Override
	public String toString() {
		return "ThongKe [tenPhong=" + tenPhong + ", loaiPhong=" + loaiPhong + ", ngayThanhToan=" + ngayThanhToan
				+ ", tienPhong=" + tienPhong + ", tienDichVu=" + tienDichVu + ", tongTien=" + tongTien + "]";
	}

}
