package entity;

import java.io.Serializable;
import java.util.Objects;

public class CTDVPhong implements Serializable{
	private HoaDon hd;
	private DichVu dichVu;
	private int soLuong;

	public CTDVPhong() {
		super();
	}

	public CTDVPhong(HoaDon hd, DichVu dichVu, int soLuong) {
		super();
		this.hd = hd;
		this.dichVu = dichVu;
		this.soLuong = soLuong;
	}

	public HoaDon getHd() {
		return hd;
	}

	public void setHd(HoaDon hd) {
		this.hd = hd;
	}

	public DichVu getDichVu() {
		return dichVu;
	}

	public void setDichVu(DichVu dichVu) {
		this.dichVu = dichVu;
	}

	public int getSoLuong() {
		return soLuong;
	}

	public void setSoLuong(int soLuong) {
		this.soLuong = soLuong;
	}

	public Double tinhTienDV() {
		return dichVu.getDonGia()*soLuong;
	}
	
	@Override
	public String toString() {
		return "CTDVPhong [hd=" + hd + ", dichVu=" + dichVu + ", soLuong=" + soLuong + "]";
	}

	
}
