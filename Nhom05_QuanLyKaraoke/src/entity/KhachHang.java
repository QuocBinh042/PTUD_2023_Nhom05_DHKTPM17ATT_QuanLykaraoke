package entity;

import java.io.Serializable;
import java.util.Objects;

public class KhachHang implements Serializable {
	private String maKH;
	private String tenKH;
	private int loaiKH;
	private int gioiTinh;
	private String sdthoai;
	private String email;
	private int soGioDaThue;
	private String ghiChu;
	
	
	public int getSoGioDaThue() {
		return soGioDaThue;
	}

	public void setSoGioDaThue(int soGioDaThue) {
		this.soGioDaThue = soGioDaThue;
	}
	
	public KhachHang(String SDT, String TenKH) {
		setTenKH(TenKH);
		setSdthoai(SDT);
	}

	public KhachHang(String maKH, String tenKH, int loaiKH, int gioiTinh, String sdthoai, String email, int soGioDaThue,
			String ghiChu) {
		this.maKH = maKH;
		this.tenKH = tenKH;
		this.loaiKH = loaiKH;
		this.gioiTinh = gioiTinh;
		this.sdthoai = sdthoai;
		this.email = email;
		this.soGioDaThue = soGioDaThue;
		this.ghiChu = ghiChu;
	}

	public KhachHang() {
		this("Ma Khach Hang", "Tên khách hàng", 1, 1, "Số điện thoại", "Email", 1, "Ghi chú");
	}

	@Override
	public int hashCode() {
		return Objects.hash(tenKH);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		KhachHang other = (KhachHang) obj;
		return Objects.equals(tenKH, other.tenKH);
	}

	public String getMaKH() {
		return maKH;
	}

	private void setMaKH(String maKH) {
		this.maKH = maKH;
	}

	public String getTenKH() {
		return tenKH;
	}

	public void setTenKH(String tenKH) {
		this.tenKH = tenKH;
	}

	public int getGioiTinh() {
		return gioiTinh;
	}

	public int getLoaiKH() {
		return loaiKH;
	}

	public void setLoaiKH(int loaiKH) {
		this.loaiKH = loaiKH;
	}

	public void setGioiTinh(int gioiTinh) {
		this.gioiTinh = gioiTinh;
	}

	public String getSdthoai() {
		return sdthoai;
	}

	public void setSdthoai(String sdthoai) {
		this.sdthoai = sdthoai;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getGhiChu() {
		return ghiChu;
	}

	public void setGhiChu(String ghiChu) {
		this.ghiChu = ghiChu;
	}

	@Override
	public String toString() {
		return "KhachHang [tenKH=" + tenKH + ", gioiTinh=" + gioiTinh + ", sdthoai=" + sdthoai + ", email=" + email
				+ ", ghiChu=" + ghiChu + "]";
	}
	
}
