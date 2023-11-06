package app;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class Phong extends JPanel implements MouseListener {

	private JLabel lblTenPhong, lblLoaiPhong, lblSucChua, lblGiaPhong, lblMoTa, lblTimMaPhong, lblTinhTrang,
			lblMaLoaiPhong;
	private JTextField txtTenPhong, txtSucChua, txtGiaPhong, txtTimMaPhong, txtLoaiPhong;
	private JTextArea txtaMoTa;
	private JButton btnThemMoi, btnCapNhat, btnXoa, btnLamMoi, btnQuanLyChiTiet, btnThoat, btnTim;
	private JComboBox cbLoaiPhong, cbTinhTrang, cbMaLoaiPhong;
	private JTable table;
	private DefaultTableModel tableModel;
	private LoaiPhong quanlyLP;

	public Phong() {
		Icon img_add = new ImageIcon("src/img/add2.png");
		Icon img_del = new ImageIcon("src/img/del.png");
		Icon img_reset = new ImageIcon("src/img/refresh.png");
		Icon img_edit = new ImageIcon("src/img/edit.png");
		Icon img_out = new ImageIcon("src/img/out.png");
		Icon img_search = new ImageIcon("src/img/search.png");
		Icon img_transfer = new ImageIcon("src/img/transfer.png");

		Box b1, b2, b3, b4, b5, b6;
		Box bLeft, bRight;
		Border line = BorderFactory.createLineBorder(Color.BLACK);
		Dimension dimension = new Dimension(110, 25);

		JPanel pnlLeft = new JPanel();
		JPanel pnlRight = new JPanel();

		bLeft = Box.createVerticalBox();
		bRight = Box.createVerticalBox();
		pnlLeft.add(bLeft);
		pnlRight.add(bRight);

		bLeft.setBorder(BorderFactory.createTitledBorder(line, "Thông tin phòng"));
		bLeft.add(Box.createVerticalStrut(5));
		JPanel pnlTenPhong = new JPanel();
		pnlTenPhong.setBackground(Color.decode("#cccccc"));
		pnlTenPhong.add(lblTenPhong = new JLabel("Tên phòng"));
		pnlTenPhong.add(txtTenPhong = new JTextField(15));
		bLeft.add(b1 = Box.createHorizontalBox());
		b1.add(pnlTenPhong);
		bLeft.add(Box.createVerticalStrut(10));

		JPanel pnlMaLP = new JPanel();
		pnlMaLP.setBackground(Color.decode("#cccccc"));
		pnlMaLP.add(lblMaLoaiPhong = new JLabel("Mã loại phòng"));
		pnlMaLP.add(cbMaLoaiPhong = new JComboBox<>());
		cbMaLoaiPhong.addItem("LP001");
		cbMaLoaiPhong.addItem("LP002");
		bLeft.add(b2 = Box.createHorizontalBox());
		b2.add(pnlMaLP);
		bLeft.add(Box.createVerticalStrut(10));

		JPanel pnlLoaiPhong = new JPanel();
		pnlLoaiPhong.setBackground(Color.decode("#cccccc"));
		pnlLoaiPhong.add(lblLoaiPhong = new JLabel("Loại phòng"));
		pnlLoaiPhong.add(txtLoaiPhong = new JTextField(15));
		txtLoaiPhong.setEditable(false);
		bLeft.add(b3 = Box.createHorizontalBox());
		b3.add(pnlLoaiPhong);
		bLeft.add(Box.createVerticalStrut(10));

		JPanel pnlSucChua = new JPanel();
		pnlSucChua.setBackground(Color.decode("#cccccc"));
		pnlSucChua.add(lblSucChua = new JLabel("Sức chứa"));
		pnlSucChua.add(txtSucChua = new JTextField(15));
		txtSucChua.setEditable(false);
		bLeft.add(b4 = Box.createHorizontalBox());
		b4.add(pnlSucChua);
		bLeft.add(Box.createVerticalStrut(10));

		JPanel pnlGiaPhong = new JPanel();
		pnlGiaPhong.setBackground(Color.decode("#cccccc"));
		pnlGiaPhong.add(lblGiaPhong = new JLabel("Giá phòng"));
		pnlGiaPhong.add(txtGiaPhong = new JTextField(15));
		txtGiaPhong.setEditable(false);
		bLeft.add(b5 = Box.createHorizontalBox());
		b5.add(pnlGiaPhong);
		bLeft.add(Box.createVerticalStrut(10));

		JPanel pnlMoTa = new JPanel();
		pnlMoTa.setBackground(Color.decode("#cccccc"));
		pnlMoTa.add(lblMoTa = new JLabel("Mô tả"));
		pnlMoTa.add(txtaMoTa = new JTextArea(3, 15));
		txtGiaPhong.setEditable(false);
		bLeft.add(b6 = Box.createHorizontalBox());
		b6.add(pnlMoTa);
		txtaMoTa.setBorder(BorderFactory.createLineBorder(Color.GRAY));
		JScrollPane scroll1 = new JScrollPane(txtaMoTa);
		scroll1.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
		scroll1.setBorder(null);
		b6.add(scroll1);
		b6.add(Box.createHorizontalStrut(5));

		lblTenPhong.setPreferredSize(lblMaLoaiPhong.getPreferredSize());
		lblLoaiPhong.setPreferredSize(lblMaLoaiPhong.getPreferredSize());
		lblSucChua.setPreferredSize(lblMaLoaiPhong.getPreferredSize());
		lblGiaPhong.setPreferredSize(lblMaLoaiPhong.getPreferredSize());
		lblMoTa.setPreferredSize(lblGiaPhong.getPreferredSize());
		txtTenPhong.setPreferredSize(dimension);
		cbMaLoaiPhong.setPreferredSize(txtTenPhong.getPreferredSize());
		txtLoaiPhong.setPreferredSize(dimension);
		txtSucChua.setPreferredSize(dimension);
		txtGiaPhong.setPreferredSize(dimension);

		bLeft.add(Box.createVerticalStrut(50));
		JPanel pnlKM = new JPanel();
		pnlKM.setBackground(Color.decode("#cccccc"));
		GridLayout gridKM = new GridLayout(2, 2);
		pnlKM.setLayout(gridKM);
		gridKM.setHgap(20);
		gridKM.setVgap(20);
		pnlKM.add(btnThemMoi = new ButtonGradient("Thêm mới", img_add));
		pnlKM.add(btnCapNhat = new ButtonGradient("Cập nhật", img_edit));
		pnlKM.add(btnXoa = new ButtonGradient("     Xóa     ", img_del));
		pnlKM.add(btnLamMoi = new ButtonGradient("Làm mới", img_reset));
		bLeft.add(pnlKM);

		bLeft.add(Box.createVerticalStrut(20));
		JPanel pnlKM2 = new JPanel();
		pnlKM2.setBackground(Color.decode("#cccccc"));
		GridLayout gridKM2 = new GridLayout(2, 1);
		pnlKM2.setLayout(gridKM2);
		gridKM2.setVgap(20);
		pnlKM2.add(btnQuanLyChiTiet = new ButtonGradient("Quản lý loại phòng", img_transfer));
		pnlKM2.add(btnThoat = new ButtonGradient("Thoát", img_out));
		bLeft.add(pnlKM2);

		// Set size
		bLeft.setMaximumSize(new Dimension(Integer.MAX_VALUE, bLeft.getPreferredSize().height));

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
		GridLayout gridLoc = new GridLayout(2, 2);
		Dimension dmsBtnLoc = new Dimension(120, 25);
		gridLoc.setVgap(10);
		pnlLoc.setLayout(gridLoc);
		pnlLoc.setBorder(BorderFactory.createTitledBorder(line, "Lọc"));
		pnlLoc.add(lblTinhTrang = new JLabel("Tình trạng"));
		pnlLoc.add(cbTinhTrang = new JComboBox<>());
		cbTinhTrang.addItem("Còn trống");
		cbTinhTrang.setPreferredSize(dmsBtnLoc);
		pnlLoc.add(lblLoaiPhong = new JLabel("Loại phòng"));
		pnlLoc.add(cbLoaiPhong = new JComboBox<>());
		cbLoaiPhong.addItem("VIP");
		cbLoaiPhong.setPreferredSize(dmsBtnLoc);
		bTacVu.add(pnlLoc);
		bTacVu.add(Box.createHorizontalStrut(200));

		// Panel Tra cuu
		JPanel pnlTim = new JPanel();
		pnlTim.setBackground(Color.decode("#cccccc"));
		pnlTim.add(Box.createVerticalStrut(25));
		pnlTim.setBorder(BorderFactory.createTitledBorder(line, "Tra cứu"));
		pnlTim.add(lblTimMaPhong = new JLabel("Mã phòng"));
		pnlTim.add(Box.createHorizontalStrut(30));
		pnlTim.add(txtTimMaPhong = new JTextField(20));
		txtTimMaPhong.setPreferredSize(dimension);
		pnlTim.add(Box.createHorizontalStrut(30));
		pnlTim.add(btnTim = new JButton("Tìm", img_search));
		btnTim.setPreferredSize(new Dimension(80, 25));
		bTacVu.add(pnlTim);
		bTacVu.add(Box.createHorizontalStrut(100));

		// Table
		Box table1 = Box.createVerticalBox();
		table1.setBorder(BorderFactory.createTitledBorder(line, "Danh sách phòng"));
		String[] headers = "Mã phòng;Tên phòng;Mã loại phòng;Loại phòng;Sức chứa;Giá phòng;Tình trạng;Mô tả"
				.split(";");
		tableModel = new DefaultTableModel(headers, 0);
		JScrollPane scroll = new JScrollPane(table, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scroll.setViewportView(table = new JTable(tableModel));
		table.setRowHeight(25);
		table.setAutoCreateRowSorter(true);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		table1.add(scroll);
		String[] row = "P001;Phòng gia đình;LP001;LP001;15;200000;Còn trống;Phòng có âm thanh 5.0".split(";");
		String[] row1 = "P002;Phòng gia đình;LP002;LP002;15;200000;Còn trống;Phòng có âm thanh 5.0".split(";");
		String[] row2 = "P003;Phòng gia đình;LP003;LP002;15;200000;Còn trống;Phòng có âm thanh 5.0".split(";");
		tableModel.addRow(row);
		tableModel.addRow(row1);
		tableModel.addRow(row2);

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
		btnQuanLyChiTiet.addActionListener(e -> xuLyChuyen());
		btnLamMoi.addActionListener(e -> xuLyLamMoi());
		btnThemMoi.addActionListener(e -> xuLyThemMoi());
		btnXoa.addActionListener(e -> xuLyXoa());
		btnCapNhat.addActionListener(e -> xuLyCapNhat());
		btnThoat.addActionListener(e -> xuLyThoat());
		btnTim.addActionListener(e -> xuLyTimKiem());
		table.addMouseListener(this);

	}

	// Xu ly them moi
	private void xuLyThemMoi() {
		String tenLP = txtTenPhong.getText();
		String maLP = cbMaLoaiPhong.getSelectedItem().toString();
		String loaiP = txtLoaiPhong.getText();
		String sucChua = txtSucChua.getText();
		String giaP = txtGiaPhong.getText();
		String moTa = txtaMoTa.getText();
		String[] row3 = { "P003", tenLP, maLP, "", loaiP, sucChua, giaP, "", moTa };
		int kt = kiemTraThongTin();
		if (kt == 1) {
			int i = JOptionPane.showConfirmDialog(null, "Bạn có chắc chắn muốn thêm mới phòng?", "Chú ý!",
					JOptionPane.YES_NO_OPTION);
			if (i == JOptionPane.YES_OPTION) {
				tableModel.addRow(row3);
			}
		} else {
			JOptionPane.showMessageDialog(null, "Vui lòng nhập đầy đủ thông tin phòng!");
		}

	}

	// Xu ly cap nhat
	private void xuLyCapNhat() {
		int r = table.getSelectedRow();
		int kt = kiemTraThongTin();
		if (r != -1) {
			if (kt == 1) {
				int i = JOptionPane.showConfirmDialog(null, "Bạn có chắc chắn muốn cập nhật phòng?", "Chú ý!",
						JOptionPane.YES_NO_OPTION);
				if (i == JOptionPane.YES_OPTION) {
					String tenP = txtTenPhong.getText();
					String maLP = cbMaLoaiPhong.getSelectedItem().toString();
					String moTa = txtaMoTa.getText();
					table.setValueAt(tenP, r, 1);
					table.setValueAt(maLP, r, 2);
					table.setValueAt(moTa, r, 7);
				}
			} else {
				JOptionPane.showMessageDialog(null, "Vui lòng nhập đầy đủ thông tin phòng!");
			}
		} else {
			JOptionPane.showMessageDialog(null, "Vui lòng chọn dịch vụ cần cập nhật!");
		}
	}

	// Xu ly lam moi
	private void xuLyLamMoi() {
		txtTenPhong.setText("");
		txtLoaiPhong.setText("");
		txtGiaPhong.setText("");
		txtSucChua.setText("");
		txtaMoTa.setText("");
		cbMaLoaiPhong.setSelectedIndex(0);
	}

	// Xu ly xoa
	private void xuLyXoa() {
		int row = table.getSelectedRow();
		if (row != -1) {
			int i = JOptionPane.showConfirmDialog(null, "Bạn có chắc chắn muốn xóa phòng không?", "Chú ý!",
					JOptionPane.YES_NO_OPTION);
			if (i == JOptionPane.YES_OPTION) {
				tableModel.removeRow(row);
			}
		} else {
			JOptionPane.showMessageDialog(null, "Vui lòng chọn phòng cần xóa!");
		}
	}

	// Xu ly chuyen giao dien
	private void xuLyChuyen() {
		quanlyLP = new LoaiPhong();
		quanlyLP.setVisible(true);
		quanlyLP.setAlwaysOnTop(true);
		quanlyLP.setLocationRelativeTo(null);
	}

	// Xu ly thoat
	private void xuLyThoat() {
		int i = JOptionPane.showConfirmDialog(null, "Bạn có chắc chắn muốn thoát không?", "Chú ý!",
				JOptionPane.YES_NO_OPTION);
		if (i == JOptionPane.YES_OPTION) {
			System.exit(0);
		}
	}

	//
	private void xuLyTimKiem() {
		String maPTim = txtTimMaPhong.getText();
		int n = 0;
		for (int i = 0; i < table.getRowCount(); i++) {
			if (table.getValueAt(i, 0).equals(maPTim)) {
				table.setRowSelectionInterval(i, i);
				n = 1;
			}
		}
		if (n == 1) {
			JOptionPane.showMessageDialog(null, "Mã phòng được tìm thấy!");
		} else if (n != 1) {
			JOptionPane.showMessageDialog(null, "Mã phòng không tồn tại!");
		}
	}

	// Xu ly kiem tra day du thong tin
	private int kiemTraThongTin() {
		String tenLP = txtTenPhong.getText();
		String loaiP = txtLoaiPhong.getText();
		String sucChua = txtSucChua.getText();
		String giaP = txtGiaPhong.getText();
		String moTa = txtaMoTa.getText();
		if (tenLP.equals("") || loaiP.equals("") || sucChua.equals("") || giaP.equals("") || moTa.equals("")) {
			return -1;
		}
		return 1;
	}

	// Xu ly bo tron button
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

	// Xu ly mouseclick
	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent e) {
		int row = table.getSelectedRow();
		txtTenPhong.setText(table.getValueAt(row, 1).toString());
		cbMaLoaiPhong.setSelectedItem(table.getValueAt(row, 2).toString());
		txtLoaiPhong.setText(table.getValueAt(row, 3).toString());
		txtSucChua.setText(table.getValueAt(row, 4).toString());
		txtGiaPhong.setText(table.getValueAt(row, 5).toString());
		txtaMoTa.setText(table.getValueAt(row, 7).toString());

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
