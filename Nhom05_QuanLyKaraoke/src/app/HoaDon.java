package app;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.table.DefaultTableModel;

import com.toedter.calendar.JDateChooser;

public class HoaDon extends JPanel {
	private JTable table;
	private DefaultTableModel tableModel;
	private String[] headers = { "Mã hoá đơn", "Ngày thanh toán", "Giờ thanh toán", "Tên nhân viên",
			"Tên khách hàng", "Số điện thoại khách", "Tổng hoá đơn" };
	private JLabel lblTenNV, lblSđtKH, lblNgayBatDau, lblNgayKetThuc;
	private JTextField txtTimNV, txtTimKH;
	private JButton btnTim, btnLoc, btnLamMoi2, btnLamMoi, btnThoat, btnXemCT;
	private JDateChooser dateBD, dateKT;

	public HoaDon() {
		// Khai báo
		Icon img_out = new ImageIcon("src/img/out16.png");
		Icon img_search = new ImageIcon("src/img/search.png");
		Icon img_check = new ImageIcon("src/img/check.png");
		Icon img_refresh = new ImageIcon("src/img/refresh16.png");
		Icon img_detail = new ImageIcon("src/img/detail16.png");
		Box b, bNV, bLoc, bLoc1, bLoc2, bTim, bTim1, bTim2, bChiTiet;
		Border line = BorderFactory.createLineBorder(Color.BLACK);
		Dimension dimension = new Dimension(110, 25);

		// Bố cục
		b = Box.createHorizontalBox();
		b.add(Box.createHorizontalStrut(50));
		b.add(bLoc = Box.createVerticalBox());
		b.add(Box.createHorizontalStrut(100));
		b.add(bTim = Box.createVerticalBox());
		b.add(Box.createHorizontalStrut(100));
		b.add(bChiTiet = Box.createVerticalBox());
		b.add(Box.createHorizontalStrut(100));
		bLoc.add(bLoc1 = Box.createHorizontalBox());
		bLoc.add(Box.createVerticalStrut(5));
		bLoc.add(bLoc2 = Box.createHorizontalBox());
		bTim.add(bTim1 = Box.createHorizontalBox());
		bTim.add(Box.createVerticalStrut(5));
		bTim.add(bTim2 = Box.createHorizontalBox());

		// Lọc
		bLoc.setBorder(BorderFactory.createTitledBorder(line, "Lọc theo ngày"));
		bLoc1.add(lblNgayBatDau = new JLabel("Ngày bắt đầu"));
		bLoc1.add(Box.createHorizontalStrut(10));
		bLoc1.add(dateBD = new JDateChooser());
		bLoc1.add(Box.createHorizontalStrut(10));
		bLoc1.add(btnLoc = new JButton("Lọc", img_search));
		bLoc2.add(lblNgayKetThuc = new JLabel("Ngày kết thúc"));
		bLoc2.add(Box.createHorizontalStrut(10));
		bLoc2.add(dateKT = new JDateChooser());
		bLoc2.add(Box.createHorizontalStrut(10));
		bLoc2.add(btnLamMoi = new JButton("Làm mới", img_refresh));

		// Tra cứu
		bTim.setBorder(BorderFactory.createTitledBorder(line, "Tra cứu"));
		bTim1.add(lblTenNV = new JLabel("Tên nhân viên"));
		bTim1.add(Box.createHorizontalStrut(10));
		bTim1.add(txtTimNV = new JTextField(15));
		bTim1.add(Box.createHorizontalStrut(10));
		bTim1.add(btnTim = new JButton("Tìm", img_search));
		bTim2.add(lblSđtKH = new JLabel("Số điện thoại khách"));
		bTim2.add(Box.createHorizontalStrut(10));
		bTim2.add(txtTimKH = new JTextField(15));
		bTim2.add(Box.createHorizontalStrut(10));
		bTim2.add(btnLamMoi2 = new JButton("Làm mới", img_refresh));

		//
		bChiTiet.setBorder(BorderFactory.createTitledBorder(" "));
		bChiTiet.add(btnThoat = new JButton("Thoát", img_out));
		bChiTiet.add(Box.createVerticalStrut(5));
		bChiTiet.add(btnXemCT = new JButton("Xem chi tiết", img_detail));

		// setSize button
		btnLoc.setPreferredSize(dimension);
		btnLamMoi.setPreferredSize(dimension);
		btnTim.setPreferredSize(dimension);
		btnLamMoi2.setPreferredSize(dimension);

		lblNgayBatDau.setPreferredSize(lblNgayKetThuc.getPreferredSize());
		lblTenNV.setPreferredSize(lblSđtKH.getPreferredSize());

		tableModel = new DefaultTableModel(headers, 0);
		JScrollPane scroll = new JScrollPane(table, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scroll.setBorder(BorderFactory.createTitledBorder(line, "Danh sách phòng"));
		scroll.setViewportView(table = new JTable(tableModel));
		table.setRowHeight(50);
		table.setAutoCreateRowSorter(true);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		this.setLayout(new BorderLayout());
		this.add(b, BorderLayout.NORTH);
		this.add(scroll, BorderLayout.CENTER);
	}
}
