package app;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.table.DefaultTableModel;

public class LoaiPhong extends JFrame {
	private JTextField txtMaLP, txtSucChua, txtGiaLP;
	private JLabel lblmaLP, lblTenLP, lblsucChua, lblGiaLP, lblLocTenLP, lblLocSC;
	private JButton btnThem, btnCapNhat, btnLamMoi, btnQuayLai;
	private JComboBox cbTenLP, cbSucChua, cbLocTP;
	private JTable table;
	private DefaultTableModel tableModel;

	public LoaiPhong() {
		setSize(800, 450);
		setTitle("Quản lý loại phòng");
		setLocationRelativeTo(null);

		Icon img_add = new ImageIcon("src/img/add16.png");
		Icon img_back = new ImageIcon("src/img/back16.png");
		Icon img_reset = new ImageIcon("src/img/refresh16.png");
		Icon img_edit = new ImageIcon("src/img/edit16.png");

		Box b1, b2, b3, b4;
		Box bLeft, bRight;
		Border line = BorderFactory.createLineBorder(Color.BLACK);
		Dimension dimension = new Dimension(120, 25);

		JPanel pnlLeft = new JPanel();
		JPanel pnlRight = new JPanel();

		bLeft = Box.createVerticalBox();
		bRight = Box.createVerticalBox();
		pnlLeft.add(bLeft);
		pnlRight.add(bRight);

		bLeft.setBorder(BorderFactory.createTitledBorder(line, "Thông tin loại phòng"));
		bLeft.add(Box.createVerticalStrut(5));

		JPanel pnlMaLP = new JPanel();
		pnlMaLP.setBackground(Color.decode("#cccccc"));
		pnlMaLP.add(lblmaLP = new JLabel("Mã loại phòng"));
		pnlMaLP.add(txtMaLP = new JTextField(15));
		txtMaLP.setEditable(false);
		bLeft.add(b1 = Box.createHorizontalBox());
		b1.add(pnlMaLP);
		bLeft.add(Box.createVerticalStrut(10));

		JPanel pnlTenLP = new JPanel();
		pnlTenLP.setBackground(Color.decode("#cccccc"));
		pnlTenLP.add(lblTenLP = new JLabel("Tên loại phòng"));
		pnlTenLP.add(cbTenLP = new JComboBox<>());
		cbTenLP.addItem("Tất cả");
		bLeft.add(b2 = Box.createHorizontalBox());
		b2.add(pnlTenLP);
		bLeft.add(Box.createVerticalStrut(10));

		JPanel pnlSucChua = new JPanel();
		pnlSucChua.setBackground(Color.decode("#cccccc"));
		pnlSucChua.add(lblsucChua = new JLabel("Sức chứa"));
		pnlSucChua.add(txtSucChua = new JTextField(15));
		bLeft.add(b3 = Box.createHorizontalBox());
		b3.add(pnlSucChua);
		bLeft.add(Box.createVerticalStrut(10));

		JPanel pnlGiaLP = new JPanel();
		pnlGiaLP.setBackground(Color.decode("#cccccc"));
		pnlGiaLP.add(lblGiaLP = new JLabel("Giá loại phòng"));
		pnlGiaLP.add(txtGiaLP = new JTextField(15));
		bLeft.add(b4 = Box.createHorizontalBox());
		b4.add(pnlGiaLP);
		bLeft.add(Box.createVerticalStrut(10));

		lblmaLP.setPreferredSize(lblTenLP.getPreferredSize());
		lblsucChua.setPreferredSize(lblTenLP.getPreferredSize());
		lblGiaLP.setPreferredSize(lblTenLP.getPreferredSize());
		txtMaLP.setPreferredSize(dimension);
		txtSucChua.setPreferredSize(dimension);
		txtGiaLP.setPreferredSize(dimension);
		cbTenLP.setPreferredSize(new Dimension(125, 25));
		// button
		bLeft.add(Box.createVerticalStrut(50));
		JPanel pnlKM = new JPanel();
		pnlKM.setBackground(Color.decode("#cccccc"));
		GridLayout gridKM = new GridLayout(4, 1);
		pnlKM.setLayout(gridKM);
		gridKM.setVgap(25);
		pnlKM.add(btnThem = new ButtonGradient("Thêm mới", img_add));
		pnlKM.add(btnCapNhat = new ButtonGradient("Cập nhật", img_edit));
		pnlKM.add(btnLamMoi = new ButtonGradient("Làm mới", img_reset));
		pnlKM.add(btnQuayLai = new ButtonGradient("Quay lại", img_back));
		bLeft.add(pnlKM);

		// Right box
		Box bTacVu = Box.createHorizontalBox();
		JPanel pnlTacVu = new JPanel();
		pnlTacVu.add(bTacVu);
		bTacVu.setOpaque(true);
		bTacVu.setBackground(Color.decode("#e6dbd1"));
		// Panel Loc
		JPanel pnlLoc = new JPanel();
		pnlLoc.setBackground(Color.decode("#cccccc"));
		pnlLoc.setBorder(BorderFactory.createTitledBorder(line, "Lọc"));
		pnlLoc.add(lblLocTenLP = new JLabel("Tên loại phòng"));
		pnlLoc.add(Box.createHorizontalStrut(30));
		pnlLoc.add(cbLocTP = new JComboBox<>());
		cbLocTP.setPreferredSize(new Dimension(80, 25));
		pnlLoc.add(Box.createHorizontalStrut(100));
		pnlLoc.add(lblLocSC = new JLabel("Sức chứa"));
		pnlLoc.add(Box.createHorizontalStrut(30));
		pnlLoc.add(cbSucChua = new JComboBox<>());
		cbSucChua.setPreferredSize(new Dimension(80, 25));

		bTacVu.add(pnlLoc);

		// Table
		Box table1 = Box.createVerticalBox();
		table1.setBorder(BorderFactory.createTitledBorder(line, "Danh sách loại phòng"));
		String[] headers = "Mã loại phòng;Tên loại phòng;Sức chứa;Giá loại phòng".split(";");
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

	}
}