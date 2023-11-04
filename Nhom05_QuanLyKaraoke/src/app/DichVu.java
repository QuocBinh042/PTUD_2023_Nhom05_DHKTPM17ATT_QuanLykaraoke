package app;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DichVu extends JPanel {

	private JLabel lblTenDichVu, lblDonGiaNhap, lblDonGiaBan, lblDonVi, lblSoLuong, lblTinhTrang, lblLocTinhTrang,
			lblTimDV;
	private JTextField txtTenDichVu, txtDonGiaNhap, txtDonGiaBan, txtDonVi, txtSoLuong, txtTimDV, txtThongBaoLoi;
	private JButton btnThemMoi, btnCapNhat, btnXoa, btnLamMoi, btnThoat, btnTim;
	private JComboBox cbTinhTrang;
	private JTable table;
	private DefaultTableModel tableModel;
	private Box bLeft, bRight;

	public DichVu() {

		Icon img_add = new ImageIcon("src/img/add2.png");
		Icon img_del = new ImageIcon("src/img/del.png");
		Icon img_reset = new ImageIcon("src/img/refresh.png");
		Icon img_edit = new ImageIcon("src/img/edit.png");
		Icon img_out = new ImageIcon("src/img/out.png");
		Icon img_search = new ImageIcon("src/img/search.png");

		Box b1, b2, b3, b4, b5;
		Box bLeft, bRight;
		Border line = BorderFactory.createLineBorder(Color.BLACK);
		Dimension dimension = new Dimension(110, 25);

		JPanel pnlLeft = new JPanel();
		JPanel pnlRight = new JPanel();

		bLeft = Box.createVerticalBox();
		bRight = Box.createVerticalBox();
		pnlLeft.add(bLeft);
		pnlRight.add(bRight);

		bLeft.setBorder(BorderFactory.createTitledBorder(line, "Thông tin dịch vụ"));
		bLeft.add(Box.createVerticalStrut(5));
		JPanel pnlTenDV = new JPanel();
		pnlTenDV.setBackground(Color.decode("#cccccc"));
		pnlTenDV.add(lblTenDichVu = new JLabel("Tên dịch vụ"));
		pnlTenDV.add(txtTenDichVu = new JTextField(15));
		bLeft.add(b1 = Box.createHorizontalBox());
		b1.add(pnlTenDV);
		bLeft.add(Box.createVerticalStrut(10));

		JPanel pnlDonVi = new JPanel();
		pnlDonVi.setBackground(Color.decode("#cccccc"));
		pnlDonVi.add(lblDonVi = new JLabel("Đơn vị"));
		pnlDonVi.add(txtDonVi = new JTextField(15));
		bLeft.add(b2 = Box.createHorizontalBox());
		b2.add(pnlDonVi);
		bLeft.add(Box.createVerticalStrut(10));

		JPanel pnlDonGiaNhap = new JPanel();
		pnlDonGiaNhap.setBackground(Color.decode("#cccccc"));
		pnlDonGiaNhap.add(lblDonGiaNhap = new JLabel("Đơn giá nhập"));
		pnlDonGiaNhap.add(txtDonGiaNhap = new JTextField(15));
		bLeft.add(b3 = Box.createHorizontalBox());
		b3.add(pnlDonGiaNhap);
		bLeft.add(Box.createVerticalStrut(10));

		JPanel pnlDonGiaBan = new JPanel();
		pnlDonGiaBan.setBackground(Color.decode("#cccccc"));
		pnlDonGiaBan.add(lblDonGiaBan = new JLabel("Đơn giá bán"));
		pnlDonGiaBan.add(txtDonGiaBan = new JTextField(15));
		bLeft.add(b4 = Box.createHorizontalBox());
		b4.add(pnlDonGiaBan);
		bLeft.add(Box.createVerticalStrut(10));

		JPanel pnlSoLuong = new JPanel();
		pnlSoLuong.setBackground(Color.decode("#cccccc"));
		pnlSoLuong.add(lblSoLuong = new JLabel("Số lượng"));
		pnlSoLuong.add(txtSoLuong = new JTextField(15));
		bLeft.add(b5 = Box.createHorizontalBox());
		b5.add(pnlSoLuong);
		bLeft.add(Box.createVerticalStrut(10));

		lblTenDichVu.setPreferredSize(lblDonGiaNhap.getPreferredSize());
		lblDonGiaBan.setPreferredSize(lblDonGiaNhap.getPreferredSize());
		lblDonVi.setPreferredSize(lblDonGiaNhap.getPreferredSize());
		lblSoLuong.setPreferredSize(lblDonGiaNhap.getPreferredSize());
		txtTenDichVu.setPreferredSize(dimension);
		txtDonGiaNhap.setPreferredSize(dimension);
		txtDonGiaBan.setPreferredSize(dimension);
		txtSoLuong.setPreferredSize(dimension);
		txtDonVi.setPreferredSize(dimension);

		bLeft.add(Box.createVerticalStrut(50));

		JPanel pnlKM = new JPanel();
		pnlKM.setBackground(Color.decode("#cccccc"));
		GridLayout gridKM = new GridLayout(5, 1);
		pnlKM.setLayout(gridKM);
		gridKM.setVgap(20);
		pnlKM.add(btnThemMoi = new ButtonGradient("Thêm mới", img_add));
		pnlKM.add(btnCapNhat = new ButtonGradient("Cập nhật", img_edit));
		pnlKM.add(btnXoa = new ButtonGradient("     Xóa    ", img_del));
		pnlKM.add(btnLamMoi = new ButtonGradient("Làm mới", img_reset));
		pnlKM.add(btnThoat = new ButtonGradient(" Thoát ", img_out));
		bLeft.add(pnlKM);

		// Right box
		Box bTacVu = Box.createHorizontalBox();
		JPanel pnlTacVu = new JPanel();
		pnlTacVu.add(bTacVu);
		bTacVu.setOpaque(true);
		bTacVu.setBackground(Color.decode("#e6dbd1"));
		bTacVu.add(Box.createHorizontalStrut(200));
		// Panel Loc
		JPanel pnlLoc = new JPanel();
		pnlLoc.setBackground(Color.decode("#cccccc"));
		pnlLoc.setBorder(BorderFactory.createTitledBorder(line, "Lọc"));
		pnlLoc.add(lblTinhTrang = new JLabel("Tình trạng"));
		pnlLoc.add(Box.createHorizontalStrut(30));
		pnlLoc.add(cbTinhTrang = new JComboBox<>());
		cbTinhTrang.setPreferredSize(new Dimension(150, 25));
		bTacVu.add(pnlLoc);
		bTacVu.add(Box.createHorizontalStrut(150));

		// Panel Tra cuu
		JPanel pnlTim = new JPanel();
		pnlTim.setBackground(Color.decode("#cccccc"));
		pnlTim.add(Box.createVerticalStrut(25));
		pnlTim.setBorder(BorderFactory.createTitledBorder(line, "Tra cứu"));
		pnlTim.add(lblTenDichVu = new JLabel("Tên dịch vụ"));
		pnlTim.add(Box.createHorizontalStrut(30));
		pnlTim.add(txtTimDV = new JTextField(20));
		txtTimDV.setPreferredSize(dimension);
		pnlTim.add(Box.createHorizontalStrut(30));
		pnlTim.add(btnTim = new JButton("Tìm", img_search));
		btnTim.setPreferredSize(new Dimension(80, 25));
		bTacVu.add(pnlTim);
		bTacVu.add(Box.createHorizontalStrut(100));

		// Table
		Box table1 = Box.createVerticalBox();
		table1.setBorder(BorderFactory.createTitledBorder(line, "Danh sách dịch vụ"));
		String[] headers = "Mã dịch vụ;Tên dịch vụ;Đơn giá nhập;Đơn giá bán;Đơn vị;Số lượng;Tình trạng".split(";");
		tableModel = new DefaultTableModel(headers, 0);
		JScrollPane scroll = new JScrollPane(table, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scroll.setViewportView(table = new JTable(tableModel));
		table.setRowHeight(25);
		table.setAutoCreateRowSorter(true);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		table1.add(scroll);

		// set color
		pnlTacVu.setBackground(Color.decode("#e6dbd1"));
		pnlLeft.setBackground(Color.decode("#cccccc"));
		pnlRight.setBackground(Color.decode("#e6dbd1"));
		pnlRight.setLayout(new BorderLayout());
		pnlRight.add(pnlTacVu, BorderLayout.NORTH);
		pnlRight.add(table1, BorderLayout.CENTER);

		this.setLayout(new BorderLayout());
		this.add(pnlLeft, BorderLayout.WEST);
		this.add(pnlRight, BorderLayout.CENTER);

		// add event button
		btnLamMoi.addActionListener(e -> xuLyLamMoi());
	}

	private void xuLyLamMoi() {
		txtTenDichVu.setText("");
		txtDonVi.setText("");
		txtDonGiaNhap.setText("");
		txtDonGiaBan.setText("");
		txtSoLuong.setText("");
	}

	private static class RoundedBorder implements Border {

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
