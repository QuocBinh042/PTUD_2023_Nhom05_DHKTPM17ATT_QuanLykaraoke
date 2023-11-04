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

	private JLabel lblTenNhanVien, lblNamSinh, lblGioiTinh, lblSoDienThoai, lblCCCD, lblChucVu, lblMatKhau, lblTinhTrang;
	private JTextField txtTenNhanVien, txtNamSinh, txtSoDienThoai, txtCCCD, txtMatKhau, txtTimNhanVien, txtTimSDT;
	private JButton btnThemMoi, btnCapNhat, btnXoa, btnLamMoi, btnThoat, btnTim;
	private JComboBox cbChucVu, cbTinhTrang;
	private String[] headers = { "Mã nhân viên", "Tên nhân viên", "Năm sinh", "Giới tính", "Số điện thoại", 
			"CCCD", "Chức vụ", "Mật khẩu"};
	private JRadioButton radNam, radNu;
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
		Box b1, b2, b3, b4, b5, b6, b7, b8, b9, b10, b11, b12, b13, b14, b15, b16, b17, b92, b;
		Dimension dimension = new Dimension(170, 25);

		Border line = BorderFactory.createLineBorder(Color.BLACK);
		bLeft.setBorder(BorderFactory.createTitledBorder(line, "Thông tin nhân viên", TitledBorder.LEFT, TitledBorder.TOP, new Font("Arial", Font.PLAIN, 13), Color.BLACK));
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
		b3.add(Box.createHorizontalStrut(50));
		ButtonGroup bg = new ButtonGroup();
		radNam = new JRadioButton("Nam");
		radNu = new JRadioButton("Nữ");
		b3.add(radNam);
		b3.add(radNu);
		b3.add(radNam);
		b3.add(Box.createHorizontalStrut(80));
		b3.add(radNu);
	
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
		
		
		//lblTenNhanVien.setPreferredSize(lblTenNhanVien.getPreferredSize());
		lblNamSinh.setPreferredSize(lblTenNhanVien.getPreferredSize());
		lblSoDienThoai.setPreferredSize(lblTenNhanVien.getPreferredSize());
		lblCCCD.setPreferredSize(lblTenNhanVien.getPreferredSize());
		lblChucVu.setPreferredSize(lblTenNhanVien.getPreferredSize());
		lblMatKhau.setPreferredSize(lblTenNhanVien.getPreferredSize());
		
		
		JPanel pnlKM = new JPanel();
		GridLayout gridKM = new GridLayout(2, 2);
		pnlKM.setLayout(gridKM);
		gridKM.setHgap(30);
		gridKM.setVgap(10);
		pnlKM.add(btnThemMoi = new ButtonGradient("Thêm mới", img_add));
		pnlKM.add(btnCapNhat = new ButtonGradient("Cập nhật", img_edit));
		pnlKM.add(btnXoa = new ButtonGradient("Xóa", img_del));
		pnlKM.add(btnLamMoi = new ButtonGradient("Làm mới", img_reset));
		bLeft.add(pnlKM);
		bLeft.add(Box.createVerticalStrut(20));
		bLeft.add(b92 = Box.createHorizontalBox());
		b92.add(Box.createVerticalStrut(20));
		b92.add(Box.createHorizontalStrut(3));
		b92.add(btnThoat = new ButtonGradient("Thoát", img_out));		
		btnThoat.setMaximumSize(new Dimension(Integer.MAX_VALUE, btnThoat.getMinimumSize().height));


	
		bLeft.add(Box.createVerticalStrut(200));


		//b = Box.createHorizontalBox();
		//b.add(Box.createVerticalStrut(50));
		JPanel pnlTraCuu = new JPanel();
		GridLayout gridTraCuu = new GridLayout(2, 5);
		gridTraCuu.setHgap(10);
		gridTraCuu.setVgap(5);
		pnlTraCuu.setLayout(gridTraCuu);
		pnlTraCuu.setBorder(BorderFactory.createTitledBorder(line, "Tra cứu"));
		pnlTraCuu.add(lblChucVu = new JLabel("Chức vụ:"));
		pnlTraCuu.add(cbChucVu = new JComboBox<>());
		pnlTraCuu.add(lblTenNhanVien = new JLabel("Tên nhân viên:"));
		pnlTraCuu.add(txtTenNhanVien = new JTextField(10));
		pnlTraCuu.add(btnTim = new ButtonGradient("Tìm", img_search));
		pnlTraCuu.add(lblTinhTrang = new JLabel("Tình trạng:"));
		pnlTraCuu.add(cbTinhTrang = new JComboBox<>());
		pnlTraCuu.add(lblSoDienThoai = new JLabel("Số điện thoại:"));
		pnlTraCuu.add(txtSoDienThoai = new JTextField(10));
		pnlTraCuu.add(btnLamMoi = new ButtonGradient("Làm mới", img_reset));
		bRight.add(pnlTraCuu);
	
		tableModel = new DefaultTableModel(headers, 0);
		JScrollPane scroll = new JScrollPane(table, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scroll.setBorder(BorderFactory.createTitledBorder(line, "Danh sách nhân viên"));
		scroll.setViewportView(table = new JTable(tableModel));
		table.setRowHeight(50);
		table.setAutoCreateRowSorter(true);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		bRight.add(scroll);

		
		//pnlLeft.setBackground(Color.decode("#cccccc"));
		//pnlRight.setBackground(Color.decode("#e6dbd1"));
		pnlLeft.add(bLeft, BorderLayout.WEST);
		pnlRight.add(bRight, BorderLayout.CENTER);

		this.setLayout(new BorderLayout());
		add(pnlLeft, BorderLayout.WEST);
		add(pnlRight, BorderLayout.CENTER);

	}
	private static class RoundedBorder implements Border {

	    private int radius;


	    RoundedBorder(int radius) {
	        this.radius = radius;
	    }


	    public Insets getBorderInsets(Component c) {
	        return new Insets(this.radius+1, this.radius+1, this.radius+2, this.radius);
	    }


	    public boolean isBorderOpaque() {
	        return true;
	    }


	    public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
	        g.drawRoundRect(x, y, width-1, height-1, radius, radius);
	    }
	}

}
