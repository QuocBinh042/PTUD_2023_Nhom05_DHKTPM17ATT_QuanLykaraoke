
package app;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import com.toedter.calendar.JDateChooser;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class NhanVien extends JPanel {

	private JLabel lblTenNhanVien, lblNamSinh, lblGioiTinh, lblSoDienThoai, lblCCCD, lblChucVu, lblMatKhau,
			lblTinhTrang;
	private JTextField txtTenNhanVien, txtNamSinh, txtSoDienThoai, txtCCCD, txtMatKhau, txtTimNhanVien, txtTimSDT;
	private JButton btnThemMoi, btnCapNhat, btnXoa, btnLamMoi, btnThoat, btnTim;
	private JComboBox cbChucVu, cbTinhTrang, cbGioiTinh;
	private String[] headers = { "Mã nhân viên", "Tên nhân viên", "Năm sinh", "Giới tính", "Số điện thoại", "CCCD",
			"Chức vụ", "Mật khẩu", "Tình trạng" };
	private JDateChooser dateNamSinh;
	private JTable table;
	private DefaultTableModel tableModel;

	public NhanVien() {
		Icon img_add = new ImageIcon("src/img/add2.png");
		Icon img_del = new ImageIcon("src/img/del.png");
		Icon img_reset = new ImageIcon("src/img/refresh.png");
		Icon img_edit = new ImageIcon("src/img/edit.png");
		Icon img_out = new ImageIcon("src/img/out.png");
		Icon img_search = new ImageIcon("src/img/search2.png");

		JPanel pnlLeft = new JPanel(new BorderLayout());
		JPanel pnlRight = new JPanel(new BorderLayout());

		Box bLeft = Box.createVerticalBox();
		Box bRight = Box.createVerticalBox();
		Box b1, b2, b3, b4, b5, b6, b7, b8, b9, b10, b11, b12, b13, b14, b15, b16, b17, b92, bLoc, bTacVu, bTim, bTim1,
				bTim2, bLoc1, bLoc2;
		Dimension dimension = new Dimension(170, 25);
		Dimension dimension2 = new Dimension(120, 20);
		Border line = BorderFactory.createLineBorder(Color.BLACK);
		bLeft.setBorder(BorderFactory.createTitledBorder(line, "Thông tin nhân viên"));
		bLeft.add(b1 = Box.createHorizontalBox());
		bLeft.add(Box.createVerticalStrut(15));
		b1.add(lblTenNhanVien = new JLabel("Tên nhân viên"));
		b1.add(Box.createHorizontalStrut(20));
		b1.add(txtTenNhanVien = new JTextField());

		bLeft.add(b2 = Box.createHorizontalBox());
		bLeft.add(Box.createVerticalStrut(15));
		b2.add(lblNamSinh = new JLabel("Năm sinh"));
		b2.add(Box.createHorizontalStrut(20));
		b2.add(dateNamSinh = new JDateChooser());
		dateNamSinh.setPreferredSize(dimension);

		bLeft.add(b3 = Box.createHorizontalBox());
		bLeft.add(Box.createVerticalStrut(15));
		b3.add(lblGioiTinh = new JLabel("Giới tính"));
		b3.add(Box.createHorizontalStrut(20));
		b3.add(cbGioiTinh = new JComboBox<>());
		cbGioiTinh.addItem("Nam");
		cbGioiTinh.addItem("Nữ");

		bLeft.add(b4 = Box.createHorizontalBox());
		bLeft.add(Box.createVerticalStrut(15));
		b4.add(lblSoDienThoai = new JLabel("Số điện thoại"));
		b4.add(Box.createHorizontalStrut(20));
		b4.add(txtSoDienThoai = new JTextField());

		bLeft.add(b5 = Box.createHorizontalBox());
		bLeft.add(Box.createVerticalStrut(15));
		b5.add(lblCCCD = new JLabel("CCCD"));
		b5.add(Box.createHorizontalStrut(20));
		b5.add(txtCCCD = new JTextField());

		bLeft.add(b6 = Box.createHorizontalBox());
		bLeft.add(Box.createVerticalStrut(15));
		b6.add(lblChucVu = new JLabel("Chức vụ"));
		b6.add(Box.createHorizontalStrut(20));
		b6.add(cbChucVu = new JComboBox<>());
		cbChucVu.addItem("Lễ tân");
		cbChucVu.addItem("Nhân viên quản lý");
		cbChucVu.addItem("Phục vụ");

		bLeft.add(b7 = Box.createHorizontalBox());
		bLeft.add(Box.createVerticalStrut(40));
		b7.add(lblMatKhau = new JLabel("Mật khẩu"));
		b7.add(Box.createHorizontalStrut(20));
		b7.add(txtMatKhau = new JTextField());
		txtMatKhau.setBorder(line);

		lblNamSinh.setPreferredSize(lblTenNhanVien.getPreferredSize());
		lblSoDienThoai.setPreferredSize(lblTenNhanVien.getPreferredSize());
		lblCCCD.setPreferredSize(lblTenNhanVien.getPreferredSize());
		lblGioiTinh.setPreferredSize(lblTenNhanVien.getPreferredSize());
		lblChucVu.setPreferredSize(lblTenNhanVien.getPreferredSize());
		lblMatKhau.setPreferredSize(lblTenNhanVien.getPreferredSize());

		JPanel pnlNV = new JPanel();
		GridLayout gridNV = new GridLayout(2, 2);
		pnlNV.setLayout(gridNV);
		gridNV.setHgap(30);
		gridNV.setVgap(10);
		pnlNV.add(btnThemMoi = new ButtonGradient("Thêm mới", img_add));
		pnlNV.add(btnCapNhat = new ButtonGradient("Cập nhật", img_edit));
		pnlNV.add(btnXoa = new ButtonGradient("Xóa", img_del));
		pnlNV.add(btnLamMoi = new ButtonGradient("Làm mới", img_reset));
		pnlNV.setBackground(Color.decode("#cccccc"));
		bLeft.add(pnlNV);
		bLeft.add(Box.createVerticalStrut(20));
		bLeft.add(b92 = Box.createHorizontalBox());
		b92.add(Box.createVerticalStrut(20));
		b92.add(Box.createHorizontalStrut(3));
		b92.add(btnThoat = new ButtonGradient("Thoát", img_out));
		btnThoat.setMaximumSize(new Dimension(Integer.MAX_VALUE, btnThoat.getMinimumSize().height));
		bLeft.add(Box.createVerticalStrut(270));

		//Loc
		bRight.add(bTacVu = Box.createHorizontalBox());
		bTacVu.add(Box.createHorizontalStrut(100));
		
		bLoc = Box.createVerticalBox();
		JPanel pnlLblChucVu = new JPanel();
		JPanel pnlCbChucVu = new JPanel();
		JPanel pnlLblTinhTrang = new JPanel();
		JPanel pnlCbTinhTrang = new JPanel();		
		pnlLblChucVu.setBackground((Color.decode("#cccccc")));
		pnlCbChucVu.setBackground((Color.decode("#cccccc")));
		pnlLblTinhTrang.setBackground((Color.decode("#cccccc")));
		pnlCbTinhTrang.setBackground((Color.decode("#cccccc")));
		
		pnlLblChucVu.add(lblChucVu = new JLabel("Chức vụ:"));
		pnlCbChucVu.add(cbChucVu = new JComboBox<>());
		pnlLblTinhTrang.add(lblTinhTrang = new JLabel("Tình trạng:"));
		pnlCbTinhTrang.add(cbTinhTrang = new JComboBox<>());
		cbTinhTrang.addItem("Tất cả");
		cbTinhTrang.addItem("Đang làm");
		cbTinhTrang.addItem("Nghỉ việc");
		
		bLoc.add(bLoc1 = Box.createHorizontalBox());
		bLoc.add(bLoc2 = Box.createHorizontalBox());
		bLoc1.add(pnlLblChucVu);
		bLoc1.add(pnlCbChucVu);
		bLoc2.add(pnlLblTinhTrang);
		bLoc2.add(pnlCbTinhTrang);
		
		JPanel pnlLoc = new JPanel();
		pnlLoc.add(bLoc);
		pnlLoc.setBackground((Color.decode("#cccccc")));
		pnlLoc.setBorder(BorderFactory.createTitledBorder(line, "Lọc"));
		bTacVu.add(pnlLoc);
		
		bTacVu.add(Box.createHorizontalStrut(100));
		
		//Tim
		bTim = Box.createVerticalBox();
		JPanel pnlLblTenNV = new JPanel();
		JPanel pnlTxtTenNV = new JPanel();
		JPanel pnlLblSdt = new JPanel();
		JPanel pnlTxtSdt = new JPanel();		
		pnlLblTenNV.setBackground((Color.decode("#cccccc")));
		pnlLblSdt.setBackground((Color.decode("#cccccc")));
		pnlTxtSdt.setBackground((Color.decode("#cccccc")));
		pnlTxtTenNV.setBackground((Color.decode("#cccccc")));
		pnlLblTenNV.add(lblTenNhanVien = new JLabel("Tên nhân viên:"));
		pnlTxtTenNV.add(txtTenNhanVien = new JTextField(10));
		pnlLblSdt.add(lblSoDienThoai = new JLabel("Số điện thoại:"));
		pnlTxtSdt.add(txtSoDienThoai = new JTextField(10));
		
		bTim.add(bTim1 = Box.createHorizontalBox());
		bTim1.add(pnlLblTenNV);
		bTim1.add(pnlTxtTenNV);
		bTim1.add(btnTim = new ButtonGradient("Tìm", img_search));
		bTim.add(Box.createVerticalStrut(10));
		bTim.add(bTim2 = Box.createHorizontalBox());
		bTim2.add(pnlLblSdt);
		bTim2.add(pnlTxtSdt);
		bTim2.add(btnLamMoi = new ButtonGradient("Làm mới", img_reset));
		bTacVu.add(Box.createVerticalStrut(10));
		JPanel pnlTim = new JPanel();
		pnlTim.add(bTim);
		pnlTim.setBackground((Color.decode("#cccccc")));
		pnlTim.setBorder(BorderFactory.createTitledBorder(line, "Tra cứu"));
		bTacVu.add(pnlTim);
		
		bTacVu.add(Box.createHorizontalStrut(100));
		
		//
		lblChucVu.setPreferredSize(lblTinhTrang.getPreferredSize());
		lblSoDienThoai.setPreferredSize(lblTenNhanVien.getPreferredSize());
		cbChucVu.setPreferredSize(dimension);
		cbTinhTrang.setPreferredSize(dimension);
		txtTenNhanVien.setPreferredSize(dimension);
		txtSoDienThoai.setPreferredSize(dimension);
		btnTim.setPreferredSize(dimension2);
		btnLamMoi.setPreferredSize(dimension2);

		// Table - Center
		tableModel = new DefaultTableModel(headers, 0);
		JScrollPane scroll = new JScrollPane(table, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scroll.setBorder(BorderFactory.createTitledBorder(line, "Danh sách nhân viên"));
		scroll.setViewportView(table = new JTable(tableModel));
		table.setRowHeight(50);
		table.setAutoCreateRowSorter(true);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		bRight.add(scroll);

		//
		pnlLeft.setBackground(Color.decode("#cccccc"));
		pnlRight.setBackground(Color.decode("#e6dbd1"));

		pnlRight.setLayout(new BorderLayout());
		pnlRight.add(bTacVu, BorderLayout.NORTH);
		pnlLeft.add(bLeft, BorderLayout.WEST);
		pnlRight.add(bRight, BorderLayout.CENTER);
		this.setLayout(new BorderLayout());
		add(pnlLeft, BorderLayout.WEST);
		add(pnlRight, BorderLayout.CENTER);

	}

}