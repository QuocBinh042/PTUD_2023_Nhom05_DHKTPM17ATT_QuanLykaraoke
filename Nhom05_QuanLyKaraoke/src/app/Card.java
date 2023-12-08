package app;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Arrays;
import java.util.List;
import javax.swing.*;

import dao.DAONhanVien;

public class Card extends JFrame {
	private JButton btnTrangChu, btnPhongHat, btnPhong, btnDichVu, btnKhachHang, btnNhanVien, btnHoaDon, btnKhuyenMai,
			btnThongKe, btnTroGiup, btnDangXuat;
	private JLabel lbTenNV;
	private JPanel pnl = new JPanel(new CardLayout());
	private TrangChu tc = new TrangChu();
	private PanelDatPhong dp;
	private PanelPhong phong = new PanelPhong();
	private PanelDichVu dv = new PanelDichVu();
	private PanelNhanVien nv = new PanelNhanVien();
	private PanelKhachHang kh = new PanelKhachHang();
	private PanelHoaDon hd = new PanelHoaDon();
	private PanelKhuyenMai km = new PanelKhuyenMai();
	private PanelThongKe tk = new PanelThongKe();
	private TroGiup tg = new TroGiup();
	private JPanel pnlButton = new JPanel();
	private String maNV;
	private DAONhanVien daoNV = new DAONhanVien();

	public String getMaNV() {
		return maNV;
	}

	public void setMaNV(String maNV) {
		this.maNV = maNV;
	}

	public Card(String maNV) {
		this.maNV = maNV;
		dp = new PanelDatPhong(maNV);
		try {
			UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (InstantiationException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IllegalAccessException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (UnsupportedLookAndFeelException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		createUI();
		addEventListeners();
	}

	private void createUI() {
		// Set frame properties
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(1400, 720);
		setLocationRelativeTo(null);
		setLayout(new BorderLayout());

		// Create components
		btnTrangChu = createButton(" TRANG CHỦ ", "src/img/home.png");
		btnPhongHat = createButton(" PHÒNG HÁT ", "src/img/karaoke.png");
		btnPhong = createButton(" PHÒNG          ", "src/img/room.png");
		btnDichVu = createButton(" DỊCH VỤ         ", "src/img/service.png");
		btnKhachHang = createButton(" KHÁCH HÀNG", "src/img/client.png");
		btnNhanVien = createButton(" NHÂN VIÊN ", "src/img/staff.png");
		btnHoaDon = createButton(" HÓA ĐƠN   ", "src/img/invoice.png");
		btnKhuyenMai = createButton(" KHUYẾN MÃI", "src/img/discount.png");
		btnThongKe = createButton(" THỐNG KÊ  ", "src/img/statistical.png");
		btnTroGiup = createButton(" TRỢ GIÚP  ", "src/img/help.png");
		btnDangXuat = createButton(" ĐĂNG XUẤT ", "src/img/out.png");
		createPanelLayout();

		// Add components to the frame
		add(createLeftPanel(), BorderLayout.WEST);
		add(pnl, BorderLayout.CENTER);
//		add(createNVPanel(), BorderLayout.SOUTH);
	}

	private JButton createButton(String text, String iconPath) {
		ImageIcon icon = new ImageIcon(iconPath);
		JButton button = new JButton(text, icon);
		button.setFont(new Font("Arial", Font.BOLD, 12));
//		button.setHorizontalAlignment(SwingConstants.LEFT);
		button.setBackground(Color.decode("#e6dbd1"));
		button.setForeground(Color.BLACK);
		button.setFocusPainted(false);
		button.setBorderPainted(false);
		button.setCursor(new Cursor(Cursor.HAND_CURSOR));
		button.setPreferredSize(new Dimension(180, 40));
		pnlButton.add(button);
		return button;
	}

	private void createPanelLayout() {
		pnl.add(tc, "tc");
		pnl.add(dp, "dp");
		pnl.add(dv, "dv");
		pnl.add(phong, "phong");
		pnl.add(nv, "nv");
		pnl.add(kh, "kh");
		pnl.add(hd, "hd");
		pnl.add(km, "km");
		pnl.add(tk, "tk");
		pnl.add(tg, "tg");
		pnl.add(tg, "dx");
	}

	private JPanel createLeftPanel() {
		JPanel pnlLeft = new JPanel(new BorderLayout());
		pnlLeft.add(createLogoPanel(), BorderLayout.NORTH);
		pnlLeft.add(pnlButton, BorderLayout.CENTER);
		pnlLeft.add(createNVPanel(), BorderLayout.SOUTH);
//		pnlLeft.setBorder(BorderFactory.createLineBorder(Color.black));
		pnlButton.setPreferredSize(new Dimension(200, 700));
		return pnlLeft;
	}

	private JPanel createLogoPanel() {
		JPanel pnlLogo = new JPanel();
		ImageIcon icon = new ImageIcon("src/img/Logo.png");
		JLabel label = new JLabel(icon);
		pnlLogo.add(label);
		pnlLogo.setBackground(Color.decode("#990447"));
		pnlButton.setBackground(Color.decode("#990447"));
		return pnlLogo;
	}

	private JPanel createNVPanel() {
		entity.NhanVien nv = daoNV.timKiemNhanVienTheoMa(maNV).get(0);
		JPanel pnlNV = new JPanel();
		lbTenNV = new JLabel();
		lbTenNV.setText(nv.getTenNV().trim());
		JPanel pnlMain = new JPanel();
		ImageIcon icon = new ImageIcon("src/img/logo2.png");
		JLabel label = new JLabel(icon);
		lbTenNV.setFont(new Font("Sanserif", Font.BOLD, 14));
		lbTenNV.setForeground(Color.white);
		pnlNV.setLayout(new BorderLayout());
		pnlNV.add(label, BorderLayout.WEST);
		pnlNV.add(lbTenNV, BorderLayout.CENTER);
		pnlNV.setBackground(Color.decode("#990447"));
		pnlMain.add(pnlNV);
		pnlMain.setBackground(Color.decode("#990447"));
		return pnlMain;
	}

	private void addEventListeners() {
		List<JButton> buttons = Arrays.asList(btnTrangChu, btnPhongHat, btnPhong, btnDichVu, btnKhachHang, btnNhanVien,
				btnHoaDon, btnKhuyenMai, btnThongKe, btnTroGiup, btnDangXuat);
		buttons.forEach(button -> button.addMouseListener(new MouseListener() {
			@Override
			public void mouseReleased(MouseEvent e) {
			}

			@Override
			public void mousePressed(MouseEvent e) {
			}

			@Override
			public void mouseExited(MouseEvent e) {
				button.setBackground(Color.decode("#e6dbd1"));
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				button.setBackground(Color.decode("#B5B5B5"));
			}

			@Override
			public void mouseClicked(MouseEvent e) {
			}
		}));
		btnPhong.addActionListener(e -> xuLyPhong());
		btnPhongHat.addActionListener(e -> xuLyPhongHat());
		btnKhachHang.addActionListener(e -> xuLyKhachHang());
		btnNhanVien.addActionListener(e -> xuLyNhanVien());
		btnKhuyenMai.addActionListener(e -> xuLyKhuyenMai());
		btnHoaDon.addActionListener(e -> xuLyHoaDon());
		btnDichVu.addActionListener(e -> xuLyDichVu());
		btnThongKe.addActionListener(e -> xuLyThongKe());
		btnTroGiup.addActionListener(e -> xuLyTroGiup());
		btnTrangChu.addActionListener(e -> xuLyTrangChu());
		btnDangXuat.addActionListener(e -> xuLyDangXuat());
	}

	private void xuLyPhong() {
		CardLayout card = (CardLayout) pnl.getLayout();
		card.show(pnl, "phong");
	}

	private void xuLyPhongHat() {
		CardLayout card = (CardLayout) pnl.getLayout();
		card.show(pnl, "dp");
	}

	private void xuLyDichVu() {
		CardLayout card = (CardLayout) pnl.getLayout();
		card.show(pnl, "dv");
	}

	private void xuLyNhanVien() {
		CardLayout card = (CardLayout) pnl.getLayout();
		card.show(pnl, "nv");
	}

	private void xuLyKhachHang() {
		CardLayout card = (CardLayout) pnl.getLayout();
		card.show(pnl, "kh");
	}

	private void xuLyHoaDon() {
		CardLayout card = (CardLayout) pnl.getLayout();
		card.show(pnl, "hd");
	}

	private void xuLyThongKe() {
		CardLayout card = (CardLayout) pnl.getLayout();
		card.show(pnl, "tk");
	}

	private void xuLyTroGiup() {
		CardLayout card = (CardLayout) pnl.getLayout();
		card.show(pnl, "tg");
	}

	private void xuLyKhuyenMai() {
		CardLayout card = (CardLayout) pnl.getLayout();
		card.show(pnl, "km");
	}

	private void xuLyTrangChu() {
		CardLayout card = (CardLayout) pnl.getLayout();
		card.show(pnl, "tc");
	}

	private void xuLyDangXuat() {
		int result = JOptionPane.showConfirmDialog(null, "Bạn có chắc chắn muốn đăng xuất khỏi hệ thống không?",
				"Chú ý", JOptionPane.YES_NO_OPTION);
		if (result == JOptionPane.YES_OPTION) {
			dispose();
			new app.DangNhap().setVisible(true);
		}
	}
}
