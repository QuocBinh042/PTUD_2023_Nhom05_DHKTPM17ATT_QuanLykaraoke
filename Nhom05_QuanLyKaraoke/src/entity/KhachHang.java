package entity;

import java.io.Serializable;
import java.util.Objects;

public class KhachHang implements Serializable {
	private String tenKH;
	private String gioiTinh;
	private String sdthoai;
	private String email;
	private String ghiChu;
	
	
	public KhachHang(String tenKH, String gioiTinh, String sdthoai, String email, 
			String ghiChu) {
		super();
		this.tenKH = tenKH;
		this.gioiTinh = gioiTinh;
		this.sdthoai = sdthoai;
		this.email = email;
		this.ghiChu = ghiChu;

	}

	public KhachHang() {
		this("Tên khách hàng", "Giới tính", "Số điện thoại", "Email", "Ghi chú");
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

	public String getTenKH() {
		return tenKH;
	}

	public void setTenKH(String tenKH) {
		this.tenKH = tenKH;
	}

	public String getGioiTinh() {
		return gioiTinh;
	}

	public void setGioiTinh(String gioiTinh) {
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
