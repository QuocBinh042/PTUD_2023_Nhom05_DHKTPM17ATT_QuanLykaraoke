package entity;


// Include 2 class Subject

public class inforUsingService {
//	private String sTT;
	private String tenDV;
	private String donVi;
	private String soLuong;
	private String donGiaDV;
	private String thanhTienDV;
	
	public inforUsingService() {
		super();
	}
	public inforUsingService(String tenDV, String donVi, String soLuong, String donGiaDV, String thanhTienDV) {
		super();
		this.tenDV = tenDV;
		this.donVi = donVi;
		this.soLuong = soLuong;
		this.donGiaDV = donGiaDV;
		this.thanhTienDV = thanhTienDV;
	}
	public String getTenDV() {
		return tenDV;
	}
	public void setTenDV(String tenDV) {
		this.tenDV = tenDV;
	}
	public String getDonVi() {
		return donVi;
	}
	public void setDonVi(String donVi) {
		this.donVi = donVi;
	}
	public String getSoLuong() {
		return soLuong;
	}
	public void setSoLuong(String soLuong) {
		this.soLuong = soLuong;
	}
	public String getDonGiaDV() {
		return donGiaDV;
	}
	public void setDonGiaDV(String donGiaDV) {
		this.donGiaDV = donGiaDV;
	}
	public String getThanhTienDV() {
		return thanhTienDV;
	}
	public void setThanhTienDV(String thanhTienDV) {
		this.thanhTienDV = thanhTienDV;
	}
}
