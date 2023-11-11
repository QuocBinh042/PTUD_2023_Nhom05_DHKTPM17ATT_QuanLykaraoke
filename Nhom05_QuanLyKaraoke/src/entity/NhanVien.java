package entity;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;
import java.util.Objects;

public class NhanVien implements Serializable {
	private String maNV;
	private String tenNV;
	private Date namSinh;
	private int gioiTinh;
	private String sdthoai;
	private String cccd;
	private String chucVu;
	private String matKhau;
	private int tinhTrangNV;

	public NhanVien(String maNV, String tenNV, Date namSinh, int gioiTinh, String sdthoai, String cccd,
			String chucVu, String matKhau, int tinhTrangNV) {
		this.maNV = maNV;
		this.tenNV = tenNV;
		this.namSinh = namSinh;
		this.gioiTinh = gioiTinh;
		this.sdthoai = sdthoai;
		this.cccd = cccd;
		this.chucVu = chucVu;
		this.matKhau = matKhau;
		this.tinhTrangNV = tinhTrangNV;
	}

	public NhanVien() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public int hashCode() {
		return Objects.hash(maNV);
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
		return Objects.equals(maNV, other.maNV);
	}

	public String getMaNV() {
		return maNV;
	}

	public void setMaNV(String maNV) {
		this.maNV = maNV;
	}

	public String getTenNV() {
		return tenNV;
	}

	public void setTenNV(String tenNV) {
		this.tenNV = tenNV;
	}

	public Date getNamSinh() {
		return namSinh;
	}

	public void setNamSinh(Date namSinh) {
		this.namSinh = namSinh;
	}

	public int getGioiTinh() {
		return gioiTinh;
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

	public int getTinhTrangNV() {
		return tinhTrangNV;
	}

	public void setTinhTrangNV(int tinhTrangNV) {
		this.tinhTrangNV = tinhTrangNV;
	}

	@Override
	public String toString() {
		return "NhanVien [maNV=" + maNV + ", tenNV=" + tenNV + ", namSinh=" + namSinh + ", gioiTinh=" + gioiTinh
				+ ", sdthoai=" + sdthoai + ", cccd=" + cccd + ", chucVu=" + chucVu + ", matKhau=" + matKhau
				+ ", tinhTrangNV=" + tinhTrangNV + "]";
	}
	
	
}
