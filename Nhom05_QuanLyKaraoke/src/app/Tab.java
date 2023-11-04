package app;

import java.awt.Component;

import javax.swing.*;

public class Tab extends JFrame{
	JTabbedPane tp = new JTabbedPane();
	public Tab() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//		setExtendedState(JFrame.MAXIMIZED_BOTH);
		setSize(1350, 700);
		setLocationRelativeTo(null);
		
		TrangChu tc = new TrangChu();
		DatPhong dp = new DatPhong();
		Phong phong = new Phong();
		DichVu dv = new DichVu();
		NhanVien nv = new NhanVien();
		KhachHang kh = new KhachHang();
		HoaDon hd = new HoaDon();
		KhuyenMai km = new KhuyenMai();
		ThongKe tk = new ThongKe();
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
