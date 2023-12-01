package app;

import java.awt.Component;

import javax.swing.*;

public class Tab extends JFrame{
	JTabbedPane tp = new JTabbedPane();
	public Tab() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//		setExtendedState(JFrame.MAXIMIZED_BOTH);
		setSize(1370, 720);
		setLocationRelativeTo(null);
		
		TrangChu tc = new TrangChu();
		PanelDatPhong dp = new PanelDatPhong();
		PanelPhong phong = new PanelPhong();
		PanelDichVu dv = new PanelDichVu();
		PanelNhanVien nv = new PanelNhanVien();
		PanelKhachHang kh = new PanelKhachHang();
		PanelHoaDon hd = new PanelHoaDon();
		PanelKhuyenMai km = new PanelKhuyenMai();
		PanelThongKe tk = new PanelThongKe();
		TroGiup tg = new TroGiup();
		
		tp.add("Trang Chủ", tc);
		tp.add("Đặt Phòng", dp);
		tp.add("Phòng",phong);
		tp.add("Dịch Vụ", dv);
		tp.add("Khách Hàng", kh);
		tp.add("Nhân Viên", nv);
		tp.add("Hóa Đơn", hd);
		tp.add("Khuyến Mãi",km);
		tp.add("Thống Kê", tk);
		tp.add("Trợ Giúp", tg);
		
		this.getContentPane().add(tp);
	}
}
