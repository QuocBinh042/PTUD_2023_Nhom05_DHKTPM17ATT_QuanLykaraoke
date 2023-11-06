package entity;

import java.util.Objects;

public class CTDVPhong {
	private PhieuDatPhong phieuDatPhong;
	private DichVu dichVu;
	private int soLuong;
	
	public CTDVPhong(PhieuDatPhong phieuDatPhong, DichVu dichVu, int soLuong) {
		this.phieuDatPhong = phieuDatPhong;
		this.dichVu = dichVu;
		this.soLuong = soLuong;
	}
	
	public PhieuDatPhong getPhieuDatPhong() {
		return phieuDatPhong;
	}
	public void setPhieuDatPhong(PhieuDatPhong phieuDatPhong) {
		this.phieuDatPhong = phieuDatPhong;
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
	
	@Override
	public int hashCode() {
		return Objects.hash(phieuDatPhong);
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CTDVPhong other = (CTDVPhong) obj;
		return Objects.equals(phieuDatPhong, other.phieuDatPhong);
	}
	
	@Override
	public String toString() {
		return "CTDVPhong [phieuDatPhong=" + phieuDatPhong + ", dichVu=" + dichVu + ", soLuong=" + soLuong + "]";
	}
}
