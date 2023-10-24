package app;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class KhachHang extends JPanel {

	private JLabel lblTenKhachHang, lblGioiTinh, lblSoDienThoai, lblEmail,lblGhiChu, lblLoaiKhachHang;
	private JTextField txtTenKhachHang, txtSoDienThoai, txtEmail;
	private JTextArea txaGhiChu;
	private JButton btnThemMoi, btnCapNhat,  btnLamMoi, btnThoat, btnTraCuu;
	private JComboBox cbLoaiKhachHang;
	private JRadioButton radNam, radNu;
	private JTable table;
	private DefaultTableModel tableModel;

	public KhachHang() {
		Icon img_add = new ImageIcon("src/img/add2.png");
		Icon img_reset = new ImageIcon("src/img/refresh.png");
		Icon img_edit = new ImageIcon("src/img/edit.png");
		Icon img_out = new ImageIcon("src/img/out.png");
		Icon img_search = new ImageIcon("src/img/search2.png");
	
		
		JPanel pnlLeft = new JPanel(new BorderLayout());
		JPanel pnlRight = new JPanel(new BorderLayout());

		Box bLeft = Box.createVerticalBox(); 
		Box bRight = Box.createVerticalBox();
		Box b1, b2, b3, b4, b5, b6, b7, b8, b9, b10;

		Border line = BorderFactory.createLineBorder(Color.BLACK);
		bLeft.setBorder(BorderFactory.createTitledBorder(line, "Thông tin khách hàng", TitledBorder.LEFT, TitledBorder.TOP, new Font("Arial", Font.PLAIN, 13), Color.BLACK));
		bLeft.add(b1 = Box.createHorizontalBox());
		bLeft.add(Box.createVerticalStrut(15));
		b1.add(lblTenKhachHang = new JLabel("Tên khách hàng"));
		b1.add(Box.createHorizontalStrut(20));
		b1.add(txtTenKhachHang = new JTextField());

		bLeft.add(b2 = Box.createHorizontalBox());
		bLeft.add(Box.createVerticalStrut(15));
		b2.add(lblGioiTinh = new JLabel("Giới tính"));
		b2.add(Box.createHorizontalStrut(60));
		ButtonGroup bg = new ButtonGroup();
		radNam = new JRadioButton("Nam");
		radNu = new JRadioButton("Nữ");
		b2.add(radNam);
		b2.add(radNu);
		b2.add(radNam);
		b2.add(Box.createHorizontalStrut(80));
		b2.add(radNu);
	
		bLeft.add(b3 = Box.createHorizontalBox());
		bLeft.add(Box.createVerticalStrut(15));
		b3.add(lblSoDienThoai = new JLabel("Số điện thoại"));
		b3.add(Box.createHorizontalStrut(20));
		b3.add(txtSoDienThoai = new JTextField());

		bLeft.add(b4 = Box.createHorizontalBox());
		bLeft.add(Box.createVerticalStrut(15));
		b4.add(lblEmail = new JLabel("Email"));
		b4.add(Box.createHorizontalStrut(20));
		b4.add(txtEmail = new JTextField());

		bLeft.add(b5 = Box.createHorizontalBox());
		bLeft.add(Box.createVerticalStrut(15));
		b5.add(lblGhiChu = new JLabel("Ghi chú"));
		b5.add(Box.createHorizontalStrut(20));
		b5.add(txaGhiChu = new JTextArea(5, 15));
		txaGhiChu.setBorder(line);
		
		lblTenKhachHang.setPreferredSize(lblTenKhachHang.getPreferredSize());
		lblSoDienThoai.setPreferredSize(lblTenKhachHang.getPreferredSize());
		lblEmail.setPreferredSize(lblTenKhachHang.getPreferredSize());
		lblGhiChu.setPreferredSize(lblTenKhachHang.getPreferredSize());
		
		
		bLeft.add(b6 = Box.createHorizontalBox());
		b6.add(Box.createHorizontalStrut(5));
		b6.add(btnThemMoi = new JButton("Thêm mới", img_add));
		btnThemMoi.setBorder(new RoundedBorder(7));
		btnThemMoi.setBackground(Color.decode("#6fa8dc"));
		btnThemMoi.setMaximumSize(new Dimension(Integer.MAX_VALUE, btnThemMoi.getMinimumSize().height));
		bLeft.add(Box.createVerticalStrut(5));
		
		b6.add(Box.createHorizontalStrut(20));
		b6.add(btnCapNhat = new JButton("Cập nhật", img_edit));
		btnCapNhat.setBorder(new RoundedBorder(7));
		btnCapNhat.setBackground(Color.decode("#6fa8dc"));
		btnCapNhat.setMaximumSize(new Dimension(Integer.MAX_VALUE, btnCapNhat.getMinimumSize().height));
		bLeft.add(Box.createVerticalStrut(5));

	
		bLeft.add(b7 = Box.createHorizontalBox());
		b7.add(Box.createHorizontalStrut(5));
		b7.add(btnLamMoi = new JButton("Làm mới", img_reset));
		btnLamMoi.setBorder(new RoundedBorder(7));
		btnLamMoi.setBackground(Color.decode("#6fa8dc"));
		btnLamMoi.setMaximumSize(new Dimension(Integer.MAX_VALUE, btnLamMoi.getMinimumSize().height));
		bLeft.add(Box.createVerticalStrut(5));
		
		b7.add(Box.createHorizontalStrut(20));
		b7.add(btnThoat = new JButton("Thoát", img_out));
		btnThoat.setBorder(new RoundedBorder(7));
		btnThoat.setBackground(Color.decode("#6fa8dc"));
		btnThoat.setMaximumSize(new Dimension(Integer.MAX_VALUE, btnCapNhat.getMinimumSize().height));
		bLeft.add(Box.createVerticalStrut(400)); 


		Box aNorth = Box.createHorizontalBox(), aCenter = Box.createVerticalBox();
		Box a, a1, a2;

		aNorth.add(a = Box.createHorizontalBox());
		aNorth.setMaximumSize(new Dimension(Integer.MAX_VALUE, aNorth.getMinimumSize().height));
		aNorth.add(Box.createHorizontalStrut(200));

		aNorth.add(a1 = Box.createVerticalBox());
		
		aNorth.add(Box.createVerticalStrut(20));
		a1.add(b8 = Box.createHorizontalBox());
		
		b8.add(Box.createVerticalStrut(20));
		b8.add(lblLoaiKhachHang = new JLabel("Loại khách hàng"));
		b8.add(Box.createHorizontalStrut(10));
		b8.add(cbLoaiKhachHang = new JComboBox<>());
		a1.add(Box.createVerticalStrut(10));
		a1.setMaximumSize(new Dimension(Integer.MAX_VALUE, a1.getMinimumSize().height));
		aNorth.add(Box.createHorizontalStrut(300));
		
		
        aNorth.add(a2 = Box.createVerticalBox());
		
		a2.setBorder(BorderFactory.createTitledBorder(line, "Tra cứu", TitledBorder.LEFT, TitledBorder.TOP, new Font("Arial", Font.PLAIN, 13), Color.BLACK));
		aNorth.add(Box.createVerticalStrut(30));
		a2.add(b9 = Box.createHorizontalBox());
		
		b9.add(Box.createVerticalStrut(20));
		b9.add(lblTenKhachHang = new JLabel("Tên khách hàng"));
		b9.add(Box.createHorizontalStrut(20));
		b9.add(txtTenKhachHang = new JTextField());
		b9.add(Box.createHorizontalStrut(30));
		b9.add(btnTraCuu = new JButton("  Tìm  ", img_search));
		btnTraCuu.setBorder(new RoundedBorder(1));
	    btnTraCuu.setBackground(Color.decode("#6fa8dc"));
		a2.add(Box.createVerticalStrut(22));
		lblTenKhachHang.setPreferredSize(lblTenKhachHang.getPreferredSize());
		
		a2.add(b10 = Box.createHorizontalBox());
		b10.add(Box.createVerticalStrut(20));
		b10.add(lblSoDienThoai = new JLabel("Số điện thoại"));
		b10.add(Box.createHorizontalStrut(20));
		b10.add(txtSoDienThoai = new JTextField());
		b10.add(Box.createHorizontalStrut(30));
		b10.add(btnTraCuu = new JButton("Làm mới", img_reset));
		btnTraCuu.setBorder(new RoundedBorder(1));
	    btnTraCuu.setBackground(Color.decode("#6fa8dc"));
	    a2.setMaximumSize(new Dimension(Integer.MAX_VALUE, a2.getMinimumSize().height));
	    lblSoDienThoai.setPreferredSize(lblTenKhachHang.getPreferredSize());
	    aNorth.add(Box.createHorizontalStrut(150));

	
		
	    bRight.add(aNorth);
		aCenter.setBorder(BorderFactory.createTitledBorder(line, "Danh sách khách hàng", TitledBorder.LEFT, TitledBorder.TOP, new Font("Arial", Font.PLAIN, 13), Color.BLACK));
		String[] headers = "Mã khách hàng;Tên khách hàng;Loại khách hàng;Giới tính;Số điện thoại;Email;Số giờ đã thuê;Ghi chú"
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
