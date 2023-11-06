package entity;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Objects;

public class CTPhong {
	private Phong phong;
	private PhieuDatPhong phieuDatPhong;
	private LocalDate ngayNhanPhong;
	private LocalTime gioNhanPhong;
	private LocalTime gioTraPhong;
	
	public CTPhong(Phong phong, PhieuDatPhong phieuDatPhong, LocalDate ngayNhanPhong, LocalTime gioNhanPhong,
			LocalTime gioTraPhong) {
		super();
		this.phong = phong;
		this.phieuDatPhong = phieuDatPhong;
		this.ngayNhanPhong = ngayNhanPhong;
		this.gioNhanPhong = gioNhanPhong;
		this.gioTraPhong = gioTraPhong;
	}

	public Phong getPhong() {
		return phong;
	}

	public void setPhong(Phong phong) {
		this.phong = phong;
	}

	public PhieuDatPhong getPhieuDatPhong() {
		return phieuDatPhong;
	}

	public void setPhieuDatPhong(PhieuDatPhong phieuDatPhong) {
		this.phieuDatPhong = phieuDatPhong;
	}

	public LocalDate getNgayNhanPhong() {
		return ngayNhanPhong;
	}

	public void setNgayNhanPhong(LocalDate ngayNhanPhong) {
		this.ngayNhanPhong = ngayNhanPhong;
	}

	public LocalTime getGioNhanPhong() {
		return gioNhanPhong;
	}

	public void setGioNhanPhong(LocalTime gioNhanPhong) {
		this.gioNhanPhong = gioNhanPhong;
	}

	public LocalTime getGioTraPhong() {
		return gioTraPhong;
	}

	public void setGioTraPhong(LocalTime gioTraPhong) {
		this.gioTraPhong = gioTraPhong;
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
		CTPhong other = (CTPhong) obj;
		return Objects.equals(phieuDatPhong, other.phieuDatPhong);
	}

	@Override
	public String toString() {
		return "CTPhong [phong=" + phong + ", phieuDatPhong=" + phieuDatPhong + ", ngayNhanPhong=" + ngayNhanPhong
				+ ", gioNhanPhong=" + gioNhanPhong + ", gioTraPhong=" + gioTraPhong + "]";
	}
}
