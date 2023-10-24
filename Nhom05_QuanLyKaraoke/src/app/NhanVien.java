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
	private JTextField txtTenNhanVien, txtNamSinh, txtSoDienThoai, txtCCCD, txtMatKhau;
	private JButton btnThemMoi, btnCapNhat, btnXoa, btnLamMoi, btnThoat, btnTraCuu;
	private JComboBox cbChucVu, cbTinhTrang;
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
		Box b1, b2, b3, b4, b5, b6, b7, b8, b9, b10, b11, b12, b13, b14, b15, b16, b17;
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
		bLeft.add(Box.createVerticalStrut(15));
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
		
		
		bLeft.add(b8 = Box.createHorizontalBox());
		b8.add(Box.createHorizontalStrut(5));
		b8.add(btnThemMoi = new JButton("Thêm mới", img_add));
		btnThemMoi.setBorder(new RoundedBorder(7));
		btnThemMoi.setBackground(Color.decode("#6fa8dc"));
		btnThemMoi.setMaximumSize(new Dimension(Integer.MAX_VALUE, btnThemMoi.getMinimumSize().height));
		bLeft.add(Box.createVerticalStrut(5));
		
		b8.add(Box.createHorizontalStrut(20));
		b8.add(btnCapNhat = new JButton("Cập nhật", img_edit));
		btnCapNhat.setBorder(new RoundedBorder(7));
		btnCapNhat.setBackground(Color.decode("#6fa8dc"));
		btnCapNhat.setMaximumSize(new Dimension(Integer.MAX_VALUE, btnCapNhat.getMinimumSize().height));
		bLeft.add(Box.createVerticalStrut(5));

		bLeft.add(b9 = Box.createHorizontalBox());
		b9.add(Box.createVerticalStrut(70));
		b9.add(Box.createHorizontalStrut(5));
		b9.add(btnXoa = new JButton("      Xóa      ", img_del));
		btnXoa.setBorder(new RoundedBorder(7));
		btnXoa.setBackground(Color.decode("#6fa8dc"));
		btnXoa.setMaximumSize(new Dimension(Integer.MAX_VALUE, btnXoa.getMinimumSize().height));
		bLeft.add(Box.createVerticalStrut(5));
		
		b9.add(Box.createHorizontalStrut(25));
		b9.add(btnLamMoi = new JButton("Làm mới", img_reset));
		btnLamMoi.setBorder(new RoundedBorder(7));
		btnLamMoi.setBackground(Color.decode("#6fa8dc"));
		btnLamMoi.setMaximumSize(new Dimension(Integer.MAX_VALUE, btnLamMoi.getMinimumSize().height));
		bLeft.add(Box.createVerticalStrut(5));

		bLeft.add(b10 = Box.createHorizontalBox());
		b10.add(Box.createVerticalStrut(50));
		b10.add(Box.createHorizontalStrut(7));
		b10.add(btnThoat = new JButton("Thoát", img_out));
		btnThoat.setBorder(new RoundedBorder(5));
		btnThoat.setBackground(Color.decode("#6fa8dc"));
		btnThoat.setMaximumSize(new Dimension(Integer.MAX_VALUE, btnThoat.getMinimumSize().height));
		bLeft.add(Box.createVerticalStrut(300));


		Box aNorth = Box.createHorizontalBox(), aCenter = Box.createVerticalBox();
		Box a, a1, a2;

		aNorth.add(a = Box.createHorizontalBox());
		aNorth.setMaximumSize(new Dimension(Integer.MAX_VALUE, aNorth.getMinimumSize().height));
		aNorth.add(Box.createHorizontalStrut(200));

		aNorth.add(a1 = Box.createVerticalBox());
		
		aNorth.add(Box.createVerticalStrut(20));
		a1.add(b11 = Box.createHorizontalBox());
		
		b11.add(Box.createVerticalStrut(20));
		b11.add(lblChucVu = new JLabel("Chức vụ"));
		b11.add(Box.createHorizontalStrut(10));
		b11.add(cbChucVu = new JComboBox<>());
		a1.add(Box.createVerticalStrut(10));
		a1.setMaximumSize(new Dimension(Integer.MAX_VALUE, a1.getMinimumSize().height));
		lblChucVu.setPreferredSize(lblTenNhanVien.getPreferredSize());
		
		
		a1.add(b12 = Box.createHorizontalBox());
		b12.add(Box.createVerticalStrut(20));
		b12.add(lblTinhTrang = new JLabel("Tình trạng"));
		b12.add(Box.createHorizontalStrut(10));
		b12.add(cbTinhTrang = new JComboBox<>());
		a1.add(Box.createVerticalStrut(10));
		a1.setMaximumSize(new Dimension(Integer.MAX_VALUE, a1.getMinimumSize().height));
		lblTinhTrang.setPreferredSize(lblTenNhanVien.getPreferredSize());
		a1.add(Box.createVerticalStrut(22));
		
		
		aNorth.add(Box.createHorizontalStrut(300));
		
		
        aNorth.add(a2 = Box.createVerticalBox());
		
		a2.setBorder(BorderFactory.createTitledBorder(line, "Tra cứu", TitledBorder.LEFT, TitledBorder.TOP, new Font("Arial", Font.PLAIN, 13), Color.BLACK));
		aNorth.add(Box.createVerticalStrut(30));
		a2.add(b12 = Box.createHorizontalBox());
		
		b12.add(Box.createVerticalStrut(20));
		b12.add(lblTenNhanVien = new JLabel("Tên nhân viên"));
		b12.add(Box.createHorizontalStrut(20));
		b12.add(txtTenNhanVien = new JTextField());
		b12.add(Box.createHorizontalStrut(30));
		b12.add(btnTraCuu = new JButton("  Tìm  ", img_search));
		btnTraCuu.setBorder(new RoundedBorder(1));
	    btnTraCuu.setBackground(Color.decode("#6fa8dc"));
		a2.add(Box.createVerticalStrut(22));
		//lblTenNhanVien.setPreferredSize(lblTenNhanVien.getPreferredSize());
		
		a2.add(b13 = Box.createHorizontalBox());
		b13.add(Box.createVerticalStrut(20));
		b13.add(lblSoDienThoai = new JLabel("Số điện thoại"));
		b13.add(Box.createHorizontalStrut(20));
		b13.add(txtSoDienThoai = new JTextField());
		b13.add(Box.createHorizontalStrut(30));
		b13.add(btnTraCuu = new JButton("Làm mới", img_reset));
		btnTraCuu.setBorder(new RoundedBorder(1));
	    btnTraCuu.setBackground(Color.decode("#6fa8dc"));
	    a2.setMaximumSize(new Dimension(Integer.MAX_VALUE, a2.getMinimumSize().height));
	    lblSoDienThoai.setPreferredSize(lblTenNhanVien.getPreferredSize());
	    aNorth.add(Box.createHorizontalStrut(150));

	
		
	    bRight.add(aNorth);
		aCenter.setBorder(BorderFactory.createTitledBorder(line, "Danh sách nhân viên", TitledBorder.LEFT, TitledBorder.TOP, new Font("Arial", Font.PLAIN, 13), Color.BLACK));
		String[] headers = "Mã nhân viên;Tên nhân viên;Năm sinh;Giới tính;Số điện thoại;CCCD;Chức vụ;Mật khẩu"
				.split(";");
		tableModel = new DefaultTableModel(headers, 0);
		JScrollPane scroll = new JScrollPane(table, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scroll.setViewportView(table = new JTable(tableModel));
		table.setRowHeight(25);
		table.setAutoCreateRowSorter(true);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		aCenter.add(scroll);
		bRight.add(aCenter);

		
		pnlLeft.setBackground(Color.decode("#cccccc"));
		pnlRight.setBackground(Color.decode("#e6dbd1"));
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
