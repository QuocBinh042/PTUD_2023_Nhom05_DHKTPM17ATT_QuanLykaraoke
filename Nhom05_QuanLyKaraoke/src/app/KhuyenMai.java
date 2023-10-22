package app;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;

import java.awt.Graphics;
import java.awt.Insets;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.table.DefaultTableModel;

import com.toedter.calendar.JDateChooser;

import app.KhuyenMai.RoundedBorder;

public class KhuyenMai extends JPanel {
	private JTable table, table2;
	private DefaultTableModel tableModel, tableModel2;
	private String[] headers = { "Mã khuyến mãi", "Phần trăm giảm", "Ngày bắt đầu", "Ngày kết thúc",
			"Trạng thái", "Mô tả" };
	private String[] headers2 = { "Mã phòng", "Tên phòng", "Loại phòng", "Sức chứa", "Giá phòng", "Tình trạng" };
	private JLabel lblMaKM, lblPhanTramGiam, lblNgayBatDau, lblNgayKetThuc, lblTrangThai, lblMoTa;
	private JTextArea txaMoTa, txaMoTa2;
	private JTextField txtMaKM, txtPhanTramGiam, txtNgayBatDau, txtNgayKetThuc, txtThongBaoLoi, txtTimKM, txtTimPhong,
			txtMaKM2, txtPhanTramGiam2, txtNgayBatDau2, txtNgayKetThuc2, txtTrangThai;
	private JButton btnThemMoi, btnCapNhat, btnXoa, btnLamMoi, btnThoat, btnApDung, btnHuy;
	private JComboBox<String> cbTrangThai, cbLoaiPhong;
	private JDateChooser dateBD, dateKT;

	public KhuyenMai() {
		// Khai báo
		Icon img_add = new ImageIcon("src/img/add2.png");
		Icon img_del = new ImageIcon("src/img/del.png");
		Icon img_reset = new ImageIcon("src/img/refresh.png");
		Icon img_edit = new ImageIcon("src/img/edit.png");
		Icon img_out = new ImageIcon("src/img/out.png");
		Icon img_search = new ImageIcon("src/img/search.png");
		Icon img_forbidden = new ImageIcon("src/img/forbidden.png");
		Icon img_check = new ImageIcon("src/img/check.png");
		Border line = BorderFactory.createLineBorder(Color.BLACK);
		Box bb, bLeft, bRight, bTacVuKM, bLeft2, bRight2, bTacVuPhong;
		Box b1, b2, b3, b4, b5, b6, b7, b8, b9, b12, b22, b32, b42, b52, b62, b82, b92;
		JPanel pnlTop, pnlBottom;
		Dimension dimension = new Dimension(170, 25);
		// BỐ CỤC
		bb = Box.createVerticalBox();
		pnlTop = new JPanel();
		pnlBottom = new JPanel();
		pnlTop.setLayout(new BorderLayout());
		pnlBottom.setLayout(new BorderLayout());
		bb.add(pnlTop);
		bb.add(pnlBottom);
		bLeft = Box.createVerticalBox();
		bRight = Box.createVerticalBox();
		pnlTop.add(bLeft, BorderLayout.WEST);
		pnlTop.add(bRight, BorderLayout.CENTER);
		bLeft2 = Box.createVerticalBox();
		bRight2 = Box.createVerticalBox();
		pnlBottom.add(bLeft2, BorderLayout.WEST);
		pnlBottom.add(bRight2, BorderLayout.CENTER);
		this.setLayout(new BorderLayout());
		this.add(bb, BorderLayout.CENTER);

		// TOP
		bLeft.setBorder(BorderFactory.createTitledBorder(line, "Thông tin khuyến mãi"));
		bLeft.add(Box.createVerticalStrut(5));
		bLeft.add(b1 = Box.createHorizontalBox());
		JPanel pnlMaKM = new JPanel();
		pnlMaKM.add(lblMaKM = new JLabel("Mã khuyến mãi"));
		pnlMaKM.add(txtMaKM = new JTextField(15));
		b1.add(pnlMaKM);
		txtMaKM.setEditable(false);

		JPanel pnlPhanTramGiam = new JPanel();
		pnlPhanTramGiam.add(lblPhanTramGiam = new JLabel("Phần trăm giảm"));
		pnlPhanTramGiam.add(txtPhanTramGiam = new JTextField(15));
		bLeft.add(b2 = Box.createHorizontalBox());
		bLeft.add(Box.createVerticalStrut(5));
		b2.add(pnlPhanTramGiam);

		JPanel pnlNgayBatDau = new JPanel();
		pnlNgayBatDau.add(lblNgayBatDau = new JLabel("Ngày bắt đầu"));
		pnlNgayBatDau.add(dateBD = new JDateChooser());
		dateBD.setPreferredSize(dimension);
		bLeft.add(b3 = Box.createHorizontalBox());
		bLeft.add(Box.createVerticalStrut(5));
		b3.add(pnlNgayBatDau);

		JPanel pnlNgayKetThuc = new JPanel();
		pnlNgayKetThuc.add(lblNgayKetThuc = new JLabel("Ngày kết thúc"));
		pnlNgayKetThuc.add(dateKT = new JDateChooser());
		dateKT.setPreferredSize(dimension);
		bLeft.add(b4 = Box.createHorizontalBox());
		bLeft.add(Box.createVerticalStrut(5));
		b4.add(pnlNgayKetThuc);

		JPanel pnlTrangThai = new JPanel();
		pnlTrangThai.add(lblTrangThai = new JLabel("Trạng thái"));
		pnlTrangThai.add(cbTrangThai = new JComboBox<>());
		
		cbTrangThai.setPreferredSize(dimension);
		bLeft.add(b5 = Box.createHorizontalBox());
		bLeft.add(Box.createVerticalStrut(5));
		b5.add(pnlTrangThai);
		cbTrangThai.addItem("Đang học động");

		JPanel pnlMoTa = new JPanel();
		pnlMoTa.add(lblMoTa = new JLabel("Mô tả"));
		pnlMoTa.add(txaMoTa = new JTextArea(2, 15));
		txaMoTa.setBorder(line);
		bLeft.add(b6 = Box.createHorizontalBox());
		bLeft.add(Box.createVerticalStrut(5));
		b6.add(pnlMoTa);

		lblMaKM.setPreferredSize(lblPhanTramGiam.getPreferredSize());
		lblNgayBatDau.setPreferredSize(lblPhanTramGiam.getPreferredSize());
		lblNgayKetThuc.setPreferredSize(lblPhanTramGiam.getPreferredSize());
		lblTrangThai.setPreferredSize(lblPhanTramGiam.getPreferredSize());
		lblMoTa.setPreferredSize(lblPhanTramGiam.getPreferredSize());

		bLeft.add(b8 = Box.createHorizontalBox());
		b8.add(Box.createHorizontalStrut(5));
		b8.add(btnThemMoi = new JButton("Thêm mới", img_add));
		btnThemMoi.setBorder(new RoundedBorder(2));
		btnThemMoi.setBackground(Color.decode("#6fa8dc"));
		b8.add(Box.createHorizontalStrut(25));
		b8.add(btnCapNhat = new JButton("Cập nhật", img_edit));
		btnCapNhat.setBorder(new RoundedBorder(2));
		btnCapNhat.setBackground(Color.decode("#6fa8dc"));
		bLeft.add(Box.createVerticalStrut(5));
		bLeft.add(b9 = Box.createHorizontalBox());

		b9.add(Box.createHorizontalStrut(5));
		b9.add(btnXoa = new JButton("     Xóa       ", img_del));
		btnXoa.setBorder(new RoundedBorder(2));
		btnXoa.setBackground(Color.decode("#6fa8dc"));
		b9.add(Box.createHorizontalStrut(25));
		b9.add(btnLamMoi = new JButton("Làm mới", img_reset));
		btnLamMoi.setBorder(new RoundedBorder(2));
		btnLamMoi.setBackground(Color.decode("#6fa8dc"));

		bTacVuKM = Box.createHorizontalBox();
		JPanel pnlLocKM = new JPanel();
		JPanel pnlTimKM = new JPanel();
		bTacVuKM.add(pnlLocKM);
		bTacVuKM.add(pnlTimKM);
		bRight.add(bTacVuKM);

		pnlLocKM.setBorder(BorderFactory.createTitledBorder(line, "Lọc theo thời gian"));
		pnlLocKM.add(lblNgayBatDau = new JLabel("Ngày bắt đầu"));
		pnlLocKM.add(dateBD = new JDateChooser());
		dateBD.setPreferredSize(dimension);
		pnlLocKM.add(Box.createHorizontalStrut(5));
		pnlLocKM.add(dateKT = new JDateChooser());
		dateKT.setPreferredSize(dimension);
		pnlLocKM.add(new JButton("Lọc", img_search));

		pnlTimKM.setBorder(BorderFactory.createTitledBorder(line, "Tra cứu"));
		pnlTimKM.add(lblMaKM = new JLabel("Mã khuyến mãi"));
		pnlTimKM.add(txtTimKM = new JTextField(10));
		pnlTimKM.add(new JButton("Tìm", img_search));

		tableModel = new DefaultTableModel(headers, 0);
		JScrollPane scroll = new JScrollPane(table, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scroll.setBorder(BorderFactory.createTitledBorder(line, "Danh sách khuyến mãi"));
		scroll.setViewportView(table = new JTable(tableModel));
		table.setRowHeight(50);
		table.setAutoCreateRowSorter(true);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		bRight.add(scroll);

		// BOTTOM
		pnlBottom.setBorder(BorderFactory.createTitledBorder(line, "ÁP DỤNG KHUYẾN MÃI"));
		bLeft2.setBorder(BorderFactory.createTitledBorder(line, "Thông tin khuyến mãi"));
		bLeft2.add(Box.createVerticalStrut(5));
		bLeft2.add(b12 = Box.createHorizontalBox());
		JPanel pnlMaKM2 = new JPanel();
		pnlMaKM2.add(lblMaKM = new JLabel("Mã khuyến mãi"));
		pnlMaKM2.add(txtMaKM2 = new JTextField(15));
		b12.add(pnlMaKM2);
		txtMaKM2.setBorder(null);
		txtMaKM2.setEditable(false);

		JPanel pnlPhanTramGiam2 = new JPanel();
		pnlPhanTramGiam2.add(lblPhanTramGiam = new JLabel("Phần trăm giảm"));
		pnlPhanTramGiam2.add(txtPhanTramGiam2 = new JTextField(15));
		bLeft2.add(b22 = Box.createHorizontalBox());
		bLeft2.add(Box.createVerticalStrut(5));
		txtPhanTramGiam2.setBorder(null);
		txtPhanTramGiam2.setEditable(false);
		b22.add(pnlPhanTramGiam2);

		JPanel pnlNgayBatDau2 = new JPanel();
		pnlNgayBatDau2.add(lblNgayBatDau = new JLabel("Ngày bắt đầu"));
		pnlNgayBatDau2.add(txtNgayBatDau2 = new JTextField(15));
		bLeft2.add(b32 = Box.createHorizontalBox());
		bLeft2.add(Box.createVerticalStrut(5));
		txtNgayBatDau2.setBorder(null);
		txtNgayBatDau2.setEditable(false);
		b32.add(pnlNgayBatDau2);

		JPanel pnlNgayKetThuc2 = new JPanel();
		pnlNgayKetThuc2.add(lblNgayKetThuc = new JLabel("Ngày kết thúc"));
		pnlNgayKetThuc2.add(txtNgayKetThuc2 = new JTextField(15));
		bLeft2.add(b42 = Box.createHorizontalBox());
		bLeft2.add(Box.createVerticalStrut(5));
		txtNgayKetThuc2.setBorder(null);
		txtNgayKetThuc2.setEditable(false);
		b42.add(pnlNgayKetThuc2);

		JPanel pnlTrangThai2 = new JPanel();
		pnlTrangThai2.add(lblTrangThai = new JLabel("Trạng thái"));
		pnlTrangThai2.add(txtTrangThai = new JTextField(15));
		bLeft2.add(b52 = Box.createHorizontalBox());
		bLeft2.add(Box.createVerticalStrut(5));
		txtTrangThai.setBorder(null);
		txtTrangThai.setEditable(false);
		b52.add(pnlTrangThai2);

		JPanel pnlMoTa2 = new JPanel();
		pnlMoTa2.add(lblMoTa = new JLabel("Mô tả"));
		pnlMoTa2.add(txaMoTa2 = new JTextArea(2, 15));
		txaMoTa2.setBorder(null);
		txaMoTa2.setEditable(false);
		bLeft2.add(b62 = Box.createHorizontalBox());
		bLeft2.add(Box.createVerticalStrut(5));
		b62.add(pnlMoTa2);

		bTacVuPhong = Box.createHorizontalBox();
		JPanel pnlLocPhong = new JPanel();
		JPanel pnlTimPhong = new JPanel();
		bTacVuPhong.add(pnlLocPhong);
		bTacVuPhong.add(pnlTimPhong);
		bRight2.add(bTacVuPhong);

		pnlLocPhong.add(new JLabel("Loại phòng"));
		pnlLocPhong.add(cbLoaiPhong = new JComboBox<>());
		cbLoaiPhong.setPreferredSize(dimension);
		pnlTimPhong.add(new JLabel("Mã phòng"));
		pnlTimPhong.add(txtTimPhong = new JTextField(10));
		pnlTimPhong.add(new JButton("Tìm", img_search ));

		lblMaKM.setPreferredSize(lblPhanTramGiam.getPreferredSize());
		lblNgayBatDau.setPreferredSize(lblPhanTramGiam.getPreferredSize());
		lblNgayKetThuc.setPreferredSize(lblPhanTramGiam.getPreferredSize());
		lblTrangThai.setPreferredSize(lblPhanTramGiam.getPreferredSize());
		lblMoTa.setPreferredSize(lblPhanTramGiam.getPreferredSize());

		bLeft2.add(b82 = Box.createHorizontalBox());
		b82.add(Box.createHorizontalStrut(5));
		b82.add(btnApDung = new JButton("Áp dụng", img_check));
		btnApDung.setBorder(new RoundedBorder(2));
		btnApDung.setBackground(Color.decode("#6fa8dc"));
		b82.add(Box.createHorizontalStrut(25));
		b82.add(btnHuy = new JButton("Huỷ", img_forbidden));
		btnHuy.setBorder(new RoundedBorder(2));
		btnHuy.setBackground(Color.decode("#6fa8dc"));
		bLeft2.add(Box.createVerticalStrut(5));
		bLeft2.add(b92 = Box.createHorizontalBox());
		b92.add(Box.createVerticalStrut(20));
		b92.add(Box.createHorizontalStrut(3));
		b92.add(btnThoat = new JButton("Thoát", img_out));
		btnThoat.setBorder(new RoundedBorder(3));
		btnThoat.setBackground(Color.decode("#6fa8dc"));
		btnThoat.setMaximumSize(new Dimension(Integer.MAX_VALUE, btnThoat.getMinimumSize().height));

		tableModel2 = new DefaultTableModel(headers2, 0);
		JScrollPane scroll2 = new JScrollPane(table2, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scroll2.setBorder(BorderFactory.createTitledBorder("Danh sách phòng"));
		scroll2.setViewportView(table2 = new JTable(tableModel2));
		table2.setRowHeight(50);
		table2.setAutoCreateRowSorter(true);
		table2.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		bRight2.add(scroll2);

		// Set kích thước button
		Dimension maxButtonSize = new Dimension(Integer.MAX_VALUE, btnCapNhat.getPreferredSize().height);
		btnThemMoi.setMaximumSize(maxButtonSize);
		btnCapNhat.setMaximumSize(maxButtonSize);
		btnApDung.setMaximumSize(maxButtonSize);
		btnHuy.setMaximumSize(maxButtonSize);
		btnLamMoi.setMaximumSize(maxButtonSize);
		btnXoa.setMaximumSize(maxButtonSize);

	}

	public static class RoundedBorder implements Border {

		private int radius;

		RoundedBorder(int radius) {
			this.radius = radius;
		}

		public Insets getBorderInsets(Component c) {
			return new Insets(this.radius + 1, this.radius + 1, this.radius + 2, this.radius);
		}

		public boolean isBorderOpaque() {
			return true;
		}

		public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
			g.drawRoundRect(x, y, width - 1, height - 1, radius, radius);
		}
	}
}