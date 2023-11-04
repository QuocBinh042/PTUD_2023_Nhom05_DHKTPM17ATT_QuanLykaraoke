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
	private JButton btnThemMoi, btnCapNhat,  btnLamMoi, btnThoat, btnTim;
	private JComboBox cbLoaiKhachHang, cbGioiTinh;
	private String[] headers = { "Mã khách hàng", "Tên khách hàng", "Loại khách hàng", "Giới tính", "Số điện thoại", "Email",
			"Số giờ đã thuê", "Ghi chú"};
	private JTable table;
	private DefaultTableModel tableModel;

	public KhachHang() {
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
		bLeft.setBorder(BorderFactory.createTitledBorder(line, "Thông tin khách hàng"));
		bLeft.add(b1 = Box.createHorizontalBox());
		bLeft.add(Box.createVerticalStrut(15));
		b1.add(lblTenKhachHang = new JLabel("Tên khách hàng"));
		b1.add(Box.createHorizontalStrut(20));
		b1.add(txtTenKhachHang = new JTextField());

		bLeft.add(b2 = Box.createHorizontalBox());
		bLeft.add(Box.createVerticalStrut(15));
		b2.add(lblGioiTinh = new JLabel("Giới tính"));
		b2.add(Box.createHorizontalStrut(20));
		b2.add(cbGioiTinh = new JComboBox<>());
		cbGioiTinh.addItem("Nam");
		cbGioiTinh.addItem("Nữ");
	
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
		bLeft.add(Box.createVerticalStrut(50));
		b5.add(lblGhiChu = new JLabel("Ghi chú"));
		b5.add(Box.createHorizontalStrut(20));
		b5.add(txaGhiChu = new JTextArea(5, 15));
		txaGhiChu.setBorder(line);
		
		lblTenKhachHang.setPreferredSize(lblTenKhachHang.getPreferredSize());
		lblSoDienThoai.setPreferredSize(lblTenKhachHang.getPreferredSize());
		lblGioiTinh.setPreferredSize(lblTenKhachHang.getPreferredSize());
		lblEmail.setPreferredSize(lblTenKhachHang.getPreferredSize());
		lblGhiChu.setPreferredSize(lblTenKhachHang.getPreferredSize());
		
		JPanel pnlKH = new JPanel();
		GridLayout gridKH = new GridLayout(2, 2);
		pnlKH.setLayout(gridKH);
		gridKH.setHgap(30);
		gridKH.setVgap(10);
		pnlKH.add(btnThemMoi = new ButtonGradient("Thêm mới", img_add));
		pnlKH.add(btnCapNhat = new ButtonGradient("Cập nhật", img_edit));
		pnlKH.add(btnLamMoi = new ButtonGradient("Làm mới", img_reset));
		pnlKH.add(btnThoat = new ButtonGradient("Thoát", img_reset));
		pnlKH.setBackground(Color.decode("#cccccc"));
		bLeft.add(pnlKH);
		bLeft.add(Box.createVerticalStrut(340));
		
		//Loc
		bRight.add(bTacVu = Box.createHorizontalBox());
		bTacVu.add(Box.createHorizontalStrut(100));
		
		bLoc = Box.createVerticalBox();
		JPanel pnlLblLoaiKhachHang = new JPanel();	
		JPanel pnlCbLoaiKhachHang = new JPanel();
		pnlLblLoaiKhachHang.setBackground((Color.decode("#e6dbd1")));
		pnlCbLoaiKhachHang.setBackground((Color.decode("#e6dbd1")));
		
		
		pnlLblLoaiKhachHang.add(lblLoaiKhachHang = new JLabel("Loại khách hàng:"));
		pnlCbLoaiKhachHang.add(cbLoaiKhachHang = new JComboBox<>());
		cbLoaiKhachHang.addItem("Tất cả");
		cbLoaiKhachHang.addItem("Thường");
		cbLoaiKhachHang.addItem("VIP");
		
		bLoc.add(bLoc1 = Box.createHorizontalBox());
		bLoc1.add(pnlLblLoaiKhachHang);
		bLoc1.add(pnlCbLoaiKhachHang);
		
		JPanel pnlLoc = new JPanel();
		pnlLoc.add(bLoc);
	    pnlLoc.setBackground((Color.decode("#e6dbd1")));
		//pnlLoc.setBorder(BorderFactory.createTitledBorder(line, "Lọc"));
		bTacVu.add(pnlLoc);
		
		bTacVu.add(Box.createHorizontalStrut(100));
		
		//Tim
		bTim = Box.createVerticalBox();
		JPanel pnlLblTenKhachHang = new JPanel();
		JPanel pnlTxtTenKhachHang = new JPanel();
		JPanel pnlLblSoDienThoai = new JPanel();
		JPanel pnlTxtSoDienThoai = new JPanel();		
		pnlLblTenKhachHang.setBackground((Color.decode("#cccccc")));
		pnlLblSoDienThoai.setBackground((Color.decode("#cccccc")));
		pnlTxtTenKhachHang.setBackground((Color.decode("#cccccc")));
		pnlTxtSoDienThoai.setBackground((Color.decode("#cccccc")));
		pnlLblTenKhachHang.add(lblTenKhachHang = new JLabel("Tên khách hàng:"));
		pnlTxtTenKhachHang.add(txtTenKhachHang = new JTextField(10));
		pnlLblSoDienThoai.add(lblSoDienThoai = new JLabel("Số điện thoại:"));
		pnlTxtSoDienThoai.add(txtSoDienThoai = new JTextField(10));
		
		bTim.add(bTim1 = Box.createHorizontalBox());
		bTim1.add(pnlLblTenKhachHang);
		bTim1.add(pnlTxtTenKhachHang);
		bTim1.add(btnTim = new ButtonGradient("Tìm", img_search));
		bTim.add(Box.createVerticalStrut(10));
		bTim.add(bTim2 = Box.createHorizontalBox());
		bTim2.add(pnlLblSoDienThoai);
		bTim2.add(pnlTxtSoDienThoai);
		bTim2.add(btnLamMoi = new ButtonGradient("Làm mới", img_reset));
		bTacVu.add(Box.createVerticalStrut(10));
		JPanel pnlTim = new JPanel();
		pnlTim.add(bTim);
		pnlTim.setBackground((Color.decode("#cccccc")));
		pnlTim.setBorder(BorderFactory.createTitledBorder(line, "Tra cứu"));
		bTacVu.add(pnlTim);
		
		bTacVu.add(Box.createHorizontalStrut(100));
		
		//
		lblSoDienThoai.setPreferredSize(lblTenKhachHang.getPreferredSize());
		cbLoaiKhachHang.setPreferredSize(dimension);
		txtTenKhachHang.setPreferredSize(dimension);
		txtSoDienThoai.setPreferredSize(dimension);
		btnTim.setPreferredSize(dimension2);
		btnLamMoi.setPreferredSize(dimension2);

		// Table - Center
		tableModel = new DefaultTableModel(headers, 0);
		JScrollPane scroll = new JScrollPane(table, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scroll.setBorder(BorderFactory.createTitledBorder(line, "Danh sách khách hàng"));
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
