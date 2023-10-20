package app;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DichVu extends JPanel {

	private JLabel lblMaDichVu, lblTenDichVu, lblDonGiaNhap, lblDonGiaBan, lblDonViTinh, lblSoLuong, lblTinhTrang,
			lblLocTinhTrang, lblTimDV;
	private JTextField txtMaDichVu, txtTenDichVu, txtDonGiaNhap, txtDonGiaBan, txtDonViTinh, txtSoLuong, txtTimDV,
			txtThongBaoLoi;
	private JButton btnThemMoi, btnCapNhat, btnXoa, btnLamMoi, btnThoat, btnTim;
	private JComboBox cbTinhTrang;
	private JTable table;
	private DefaultTableModel tableModel;
	private Box bLeft, bRight;

	public DichVu() {

		JPanel pnlLeft = new JPanel(new BorderLayout());
		JPanel pnlRight = new JPanel(new BorderLayout());

		// WEST
		bLeft = Box.createVerticalBox();
		bRight = Box.createVerticalBox();

		Box b1, b2, b3, b4, b5, b6, b7, b8, b9, b10, b11, b12, bDVT;

//		bLeft.setBorder(BorderFactory.createTitledBorder("Thông tin dịch vụ"));

		bLeft.add(Box.createVerticalStrut(10));
		bLeft.add(b1 = Box.createHorizontalBox());
		bLeft.add(Box.createVerticalStrut(15));
		b1.add(lblMaDichVu = new JLabel("Mã dịch vụ"));
		b1.add(txtMaDichVu = new JTextField());
		txtMaDichVu.setEditable(false);

		bLeft.add(b2 = Box.createHorizontalBox());
		bLeft.add(Box.createVerticalStrut(15));
		b2.add(lblTenDichVu = new JLabel("Tên dịch vụ"));
		b2.add(txtTenDichVu = new JTextField());

		bLeft.add(b3 = Box.createHorizontalBox());
		bLeft.add(Box.createVerticalStrut(15));
		b3.add(lblDonGiaNhap = new JLabel("Đơn giá nhập"));
		b3.add(txtDonGiaNhap = new JTextField());

		bLeft.add(b4 = Box.createHorizontalBox());
		bLeft.add(Box.createVerticalStrut(15));
		b4.add(lblDonGiaBan = new JLabel("Đơn giá bán"));
		b4.add(txtDonGiaBan = new JTextField());

		bLeft.add(b5 = Box.createHorizontalBox());
		bLeft.add(Box.createVerticalStrut(15));
		b5.add(lblSoLuong = new JLabel("Số lượng"));
		b5.add(txtSoLuong = new JTextField());

		bLeft.add(b6 = Box.createHorizontalBox());
		bLeft.add(Box.createVerticalStrut(15));
		b6.add(lblTinhTrang = new JLabel("Tình trạng"));
		b6.add(cbTinhTrang = new JComboBox<>());
		cbTinhTrang.addItem("Còn trống"); // add vao combobox

		bLeft.add(bDVT = Box.createHorizontalBox());
		bLeft.add(Box.createVerticalStrut(15));
		bDVT.add(lblDonViTinh = new JLabel("Đơn vị tính"));
		bDVT.add(txtDonViTinh = new JTextField());

		bLeft.add(b7 = Box.createHorizontalBox());
		bLeft.add(Box.createVerticalStrut(15));
		b7.add(txtThongBaoLoi = new JTextField());
		txtThongBaoLoi.setEditable(false);
		txtThongBaoLoi.setBorder(null);
		txtThongBaoLoi.setBackground(Color.decode("#cccccc"));
		txtThongBaoLoi.setForeground(Color.red);
		txtThongBaoLoi.setFont(new Font("Times new roman", ABORT, 13));

		lblMaDichVu.setPreferredSize(lblDonGiaNhap.getPreferredSize());
		lblTenDichVu.setPreferredSize(lblDonGiaNhap.getPreferredSize());
		lblDonGiaBan.setPreferredSize(lblDonGiaNhap.getPreferredSize());
		lblSoLuong.setPreferredSize(lblDonGiaNhap.getPreferredSize());
		lblTinhTrang.setPreferredSize(lblDonGiaNhap.getPreferredSize());
		lblDonViTinh.setPreferredSize(lblDonGiaNhap.getPreferredSize());

		bLeft.add(b8 = Box.createHorizontalBox());
		b8.add(Box.createHorizontalStrut(5));
		b8.add(btnThemMoi = new JButton("Thêm mới"));
		btnThemMoi.setBackground(Color.decode("#6fa8dc"));
		btnThemMoi.setMaximumSize(new Dimension(Integer.MAX_VALUE, btnThemMoi.getMinimumSize().height));
		b8.add(Box.createHorizontalStrut(25));
		b8.add(btnCapNhat = new JButton("Cập nhật"));
		btnCapNhat.setBackground(Color.decode("#6fa8dc"));
		btnCapNhat.setMaximumSize(new Dimension(Integer.MAX_VALUE, btnCapNhat.getMinimumSize().height));

		bLeft.add(b9 = Box.createHorizontalBox());
		b9.add(Box.createVerticalStrut(70));
		b9.add(Box.createHorizontalStrut(5));
		b9.add(btnXoa = new JButton("     Xóa       "));
		btnXoa.setBackground(Color.decode("#6fa8dc"));
		btnXoa.setMaximumSize(new Dimension(Integer.MAX_VALUE, btnXoa.getMinimumSize().height));
		b9.add(Box.createHorizontalStrut(25));
		b9.add(btnLamMoi = new JButton("Làm mới"));
		btnLamMoi.setBackground(Color.decode("#6fa8dc"));
		btnLamMoi.setMaximumSize(new Dimension(Integer.MAX_VALUE, btnLamMoi.getMinimumSize().height));

		bLeft.add(b10 = Box.createHorizontalBox());
		b10.add(Box.createVerticalStrut(20));
		b10.add(Box.createHorizontalStrut(5));
		b10.add(btnThoat = new JButton("Thoát"));
		btnThoat.setBackground(Color.decode("#6fa8dc"));
		btnThoat.setMaximumSize(new Dimension(Integer.MAX_VALUE, btnThoat.getMinimumSize().height));

		bLeft.add(Box.createVerticalStrut(250));
		bLeft.add(Box.createHorizontalStrut(220));

		pnlRight.add(bRight);
		Box aNorth = Box.createHorizontalBox(), aCenter = Box.createVerticalBox();
		Box a, a1, a2;

//		aNorth.setBorder(BorderFactory.createTitledBorder(""));
		aNorth.setMaximumSize(new Dimension(Integer.MAX_VALUE, aNorth.getMinimumSize().height));

		aNorth.add(Box.createVerticalStrut(80));
		aNorth.add(a = Box.createVerticalBox());
		a.add(Box.createHorizontalStrut(200));
		aNorth.add(a1 = Box.createVerticalBox());
		a1.setBorder(BorderFactory.createTitledBorder("Lọc"));
		a1.add(Box.createVerticalStrut(10));
		a1.add(b12 = Box.createHorizontalBox());
		b12.add(lblTinhTrang = new JLabel("Tình trạng"));
		b12.add(Box.createHorizontalStrut(30));
		b12.add(cbTinhTrang = new JComboBox<>());
		a1.add(Box.createVerticalStrut(10));
		a1.setMaximumSize(new Dimension(Integer.MAX_VALUE, a1.getMinimumSize().height));
		aNorth.add(Box.createHorizontalStrut(100));

		aNorth.add(a2 = Box.createVerticalBox());
		a2.setBorder(BorderFactory.createTitledBorder(""));
		a2.add(lblTimDV = new JLabel("Tên dịch vụ"));
		a2.add(Box.createVerticalStrut(10));
		aNorth.add(Box.createHorizontalStrut(100));
		a2.add(b12 = Box.createHorizontalBox());
		b12.add(txtTimDV = new JTextField());
		b12.add(Box.createHorizontalStrut(30));
		b12.add(btnTim = new JButton("Tìm"));
		btnTim.setBackground(Color.decode("#6fa8dc"));
		a2.setMaximumSize(new Dimension(Integer.MAX_VALUE, a2.getMinimumSize().height));

		// add bang aNorth vao bRight
		bRight.add(aNorth);

		aCenter.setBorder(BorderFactory.createTitledBorder("Danh sách dịch vụ"));
		String[] headers = "Mã dịch vụ;Tên dịch vụ;Đơn giá nhập;Đơn giá bán;Đơn vị tính;Số lượng;Tình trạng".split(";");
		tableModel = new DefaultTableModel(headers, 0);
		JScrollPane scroll = new JScrollPane(table, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scroll.setViewportView(table = new JTable(tableModel));
		table.setRowHeight(25);
		table.setAutoCreateRowSorter(true);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		aCenter.add(scroll);

		// add bang aCenter vao bRight
		bRight.add(aCenter);

		//
		pnlLeft.setBorder(BorderFactory.createTitledBorder("Thông tin dịch vụ"));
		pnlLeft.setBackground(Color.decode("#cccccc"));
		pnlRight.setBackground(Color.decode("#e6dbd1"));

		pnlLeft.add(bLeft, BorderLayout.WEST);
		pnlRight.add(bRight, BorderLayout.CENTER);

		this.setLayout(new BorderLayout());
		add(pnlLeft, BorderLayout.WEST);
		add(pnlRight, BorderLayout.CENTER);

	}
}
