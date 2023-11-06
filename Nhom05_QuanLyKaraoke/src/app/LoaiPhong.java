package app;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.table.DefaultTableModel;

public class LoaiPhong extends JFrame implements MouseListener {
	private JTextField txtSucChua, txtGiaLP;
	private JLabel lblTenLP, lblsucChua, lblGiaLP, lblLocTenLP, lblLocSC;
	private JButton btnThem, btnCapNhat, btnLamMoi, btnQuayLai;
	private JComboBox cbTenLP, cbSucChua, cbLocTP;
	private JTable table;
	private DefaultTableModel tableModel;

	public LoaiPhong() {
		setSize(780, 350);
		setTitle("Quản lý loại phòng");
		setLocationRelativeTo(null);
		setResizable(false);

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

		JPanel pnlTenLP = new JPanel();
		pnlTenLP.setBackground(Color.decode("#cccccc"));
		pnlTenLP.add(lblTenLP = new JLabel("Tên loại phòng"));
		pnlTenLP.add(cbTenLP = new JComboBox<>());
		cbTenLP.addItem("Tất cả");
		cbTenLP.addItem("Phòng thường");
		cbTenLP.addItem("Phòng VIP");
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

		lblsucChua.setPreferredSize(lblTenLP.getPreferredSize());
		lblGiaLP.setPreferredSize(lblTenLP.getPreferredSize());
		txtSucChua.setPreferredSize(dimension);
		txtGiaLP.setPreferredSize(dimension);
		cbTenLP.setPreferredSize(new Dimension(155, 25));

		// button
		bLeft.add(Box.createVerticalStrut(10));
		JPanel pnlKM = new JPanel();
		pnlKM.setBackground(Color.decode("#cccccc"));
		GridLayout gridKM = new GridLayout(2, 2);
		pnlKM.setLayout(gridKM);
		gridKM.setVgap(20);
		gridKM.setHgap(20);
		pnlKM.add(btnThem = new ButtonGradient("Thêm mới", img_add));
		pnlKM.add(btnCapNhat = new ButtonGradient("Cập nhật", img_edit));
		pnlKM.add(btnLamMoi = new ButtonGradient("Làm mới", img_reset));
		pnlKM.add(btnQuayLai = new ButtonGradient("Quay lại", img_back));
		bLeft.add(pnlKM);
		bLeft.add(Box.createVerticalStrut(35));

		// Right box
		JPanel pnlTacVu = new JPanel();
		pnlTacVu.setBackground(Color.decode("#e6dbd1"));
		GridLayout gridTV = new GridLayout(1, 2);
		pnlTacVu.setLayout(gridTV);
		// Panel Loc
		JPanel pnlLoc = new JPanel();
		pnlLoc.setBackground(Color.decode("#e6dbd1"));
		pnlLoc.add(lblLocTenLP = new JLabel("Tên loại phòng"));
		pnlLoc.add(Box.createHorizontalStrut(20));
		pnlLoc.add(cbLocTP = new JComboBox<>());
		cbLocTP.setPreferredSize(new Dimension(100, 25));
		pnlLoc.add(Box.createHorizontalStrut(40));
		pnlLoc.add(lblLocSC = new JLabel("Sức chứa"));
		pnlLoc.add(Box.createHorizontalStrut(20));
		pnlLoc.add(cbSucChua = new JComboBox<>());
		cbSucChua.setPreferredSize(new Dimension(100, 25));
		pnlTacVu.add(pnlLoc);

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
		String[] row1 = "LP001;Phòng thường;10;100000".split(";");
		String[] row2 = "LP002;Phòng thường;15;120000".split(";");
		String[] row3 = "LP003;Phòng thường;20;150000".split(";");
		String[] row4 = "LP004;Phòng thường;20;150000".split(";");
		String[] row5 = "LP005;Phòng VIP;15;200000".split(";");
		String[] row6 = "LP006;Phòng VIP;20;250000".split(";");
		tableModel.addRow(row1);
		tableModel.addRow(row2);
		tableModel.addRow(row3);
		tableModel.addRow(row4);
		tableModel.addRow(row5);
		tableModel.addRow(row6);

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

		// add event
		btnLamMoi.addActionListener(e -> xuLyLamMoi());
		btnThem.addActionListener(e -> xuLyThemMoi());
		btnQuayLai.addActionListener(e -> xuLyQuayLai());
		btnCapNhat.addActionListener(e -> xuLyCapNhat());
		table.addMouseListener(this);

	}

	// Xu ly them moi
	private void xuLyThemMoi() {
		String tenLP = cbTenLP.getSelectedItem().toString();
		String giaLP = txtGiaLP.getText();
		String sucChua = txtSucChua.getText();
		int soLP = 0;
		String maLP = "LP00" + (soLP + "");
		String[] row = { maLP, tenLP, giaLP, sucChua };
		tableModel.addRow(row);
	}

	// Xu ly cap nhat
	private void xuLyCapNhat() {
		int r = table.getSelectedRow();
		String tenLP = cbTenLP.getSelectedItem().toString();
		String giaLP = txtGiaLP.getText();
		String sucChua = txtSucChua.getText();
		table.setValueAt(tenLP, r, 1);
		table.setValueAt(sucChua, r, 2);
		table.setValueAt(giaLP, r, 3);

	}

	// Xu ly lam moi
	private void xuLyLamMoi() {
		cbTenLP.setSelectedIndex(0);
		txtGiaLP.setText("");
		txtSucChua.setText("");
	}

	// Xu ly quay lai
	private void xuLyQuayLai() {
		this.setVisible(false);
	}

	// Xu ly kiem tra thong tin
	private int kiemTraThongTin() {
		String sucChua = txtSucChua.getText();
		String giaLP = txtGiaLP.getText();
		if (sucChua.equals("") || giaLP.equals("")) {
			return -1;
		}
		return 1;
	}

	// Xu ly mouseclick
	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent e) {
		int row = table.getSelectedRow();
		cbTenLP.setSelectedItem(table.getValueAt(row, 1).toString());
		txtSucChua.setText(table.getValueAt(row, 2).toString());
		txtGiaLP.setText(table.getValueAt(row, 3).toString());

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}
}