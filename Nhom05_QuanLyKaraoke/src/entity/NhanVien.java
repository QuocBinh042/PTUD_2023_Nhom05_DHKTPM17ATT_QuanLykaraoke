package entity;

import java.io.Serializable;
import java.util.Objects;

public class NhanVien implements Serializable {
	private String tenNV;
	private String namSinh;
	private String gioiTinh;
	private String sdthoai;
	private String cccd;
	private String chucVu;
	private String matKhau;

	public NhanVien(String tenNV, String namSinh, String gioiTinh, String sdthoai, String cccd, 
			String chucVu, String matKhau) {
		super();
		this.tenNV = tenNV;
		this.namSinh = namSinh;
		this.gioiTinh = gioiTinh;
		this.sdthoai = sdthoai;
		this.cccd = cccd;
		this.chucVu = chucVu;
		this.matKhau = matKhau;
	
	}

	public NhanVien() {
		this("", "", "", "", "", "", "");
	}

	@Override
	public int hashCode() {
		return Objects.hash(tenNV);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		NhanVien other = (NhanVien) obj;
		return Objects.equals(tenNV, other.tenNV);
	}

	public String getTenNV() {
		return tenNV;
	}

	public void setTenNV(String tenNV) {
		this.tenNV = tenNV;
	}

	public String getNamSinh() {
		return namSinh;
	}

	public void setNamSinh(String namSinh) {
		this.namSinh = namSinh;
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

	public String getCccd() {
		return cccd;
	}

	public void setCccd(String cccd) {
		this.cccd = cccd;
	}

	public String getChucVu() {
		return chucVu;
	}

	public void setChucVu(String chucVu) {
		this.chucVu = chucVu;
	}

	public String getMatKhau() {
		return matKhau;
	}

	public void setMatKhau(String matKhau) {
		this.matKhau = matKhau;
	}

	@Override
	public String toString() {
		return "NhanVien [tenNV=" + tenNV + ", namSinh=" + namSinh + ", gioiTinh=" + gioiTinh + ", sdthoai=" + sdthoai
				+ ", cccd=" + cccd + ", chucVu=" + chucVu + ", matKhau=" + matKhau + "]";
	}
	
}
