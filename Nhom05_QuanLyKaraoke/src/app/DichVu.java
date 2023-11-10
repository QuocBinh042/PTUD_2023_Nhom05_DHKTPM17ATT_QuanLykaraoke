package app;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import connectDB.ConnectDB;
import dao.daoDichVu;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.regex.Pattern;

public class DichVu extends JPanel implements MouseListener {

	private JLabel lblTenDichVu, lblDonGiaNhap, lblDonGiaBan, lblDonVi, lblSoLuong, lblTinhTrang, lblLocTinhTrang,
			lblTimDV;
	private JTextField txtTenDichVu, txtDonGiaNhap, txtDonGiaBan, txtDonVi, txtSoLuong, txtTimDV;
	private JButton btnThemMoi, btnCapNhat, btnXoa, btnLamMoi, btnThoat, btnTim;
	private JComboBox cbTinhTrang;
	private JTable table;
	private DefaultTableModel tableModel;
	private Box bLeft, bRight;
	private ArrayList<entity.DichVu> dsDichVu;
	private daoDichVu daoDV;
	private DecimalFormat formatter = new DecimalFormat("###,###,###");
	private static int maDVTT = 0;

	public DichVu() {

		try {
			ConnectDB.getInstance().connect();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		daoDV = new daoDichVu();

		Icon img_add = new ImageIcon("src/img/add2.png");
		Icon img_del = new ImageIcon("src/img/del.png");
		Icon img_reset = new ImageIcon("src/img/refresh.png");
		Icon img_edit = new ImageIcon("src/img/edit.png");
		Icon img_out = new ImageIcon("src/img/out.png");
		Icon img_search = new ImageIcon("src/img/search.png");

		Box b1, b2, b3, b4, b5;
		Box bLeft, bRight;
		Border line = BorderFactory.createLineBorder(Color.BLACK);
		Dimension dimension = new Dimension(110, 25);

		JPanel pnlLeft = new JPanel();
		JPanel pnlRight = new JPanel();

		bLeft = Box.createVerticalBox();
		bRight = Box.createVerticalBox();
		pnlLeft.add(bLeft);
		pnlRight.add(bRight);

		bLeft.setBorder(BorderFactory.createTitledBorder(line, "Thông tin dịch vụ"));
		bLeft.add(Box.createVerticalStrut(5));
		JPanel pnlTenDV = new JPanel();
		pnlTenDV.setBackground(Color.decode("#cccccc"));
		pnlTenDV.add(lblTenDichVu = new JLabel("Tên dịch vụ"));
		pnlTenDV.add(txtTenDichVu = new JTextField(15));
		bLeft.add(b1 = Box.createHorizontalBox());
		b1.add(pnlTenDV);
		bLeft.add(Box.createVerticalStrut(10));

		JPanel pnlDonVi = new JPanel();
		pnlDonVi.setBackground(Color.decode("#cccccc"));
		pnlDonVi.add(lblDonVi = new JLabel("Đơn vị"));
		pnlDonVi.add(txtDonVi = new JTextField(15));
		bLeft.add(b2 = Box.createHorizontalBox());
		b2.add(pnlDonVi);
		bLeft.add(Box.createVerticalStrut(10));

		JPanel pnlDonGiaNhap = new JPanel();
		pnlDonGiaNhap.setBackground(Color.decode("#cccccc"));
		pnlDonGiaNhap.add(lblDonGiaNhap = new JLabel("Đơn giá nhập"));
		pnlDonGiaNhap.add(txtDonGiaNhap = new JTextField(15));
		bLeft.add(b3 = Box.createHorizontalBox());
		b3.add(pnlDonGiaNhap);
		bLeft.add(Box.createVerticalStrut(10));

		JPanel pnlDonGiaBan = new JPanel();
		pnlDonGiaBan.setBackground(Color.decode("#cccccc"));
		pnlDonGiaBan.add(lblDonGiaBan = new JLabel("Đơn giá bán"));
		pnlDonGiaBan.add(txtDonGiaBan = new JTextField(15));
		bLeft.add(b4 = Box.createHorizontalBox());
		b4.add(pnlDonGiaBan);
		bLeft.add(Box.createVerticalStrut(10));

		JPanel pnlSoLuong = new JPanel();
		pnlSoLuong.setBackground(Color.decode("#cccccc"));
		pnlSoLuong.add(lblSoLuong = new JLabel("Số lượng"));
		pnlSoLuong.add(txtSoLuong = new JTextField(15));
		bLeft.add(b5 = Box.createHorizontalBox());
		b5.add(pnlSoLuong);
		bLeft.add(Box.createVerticalStrut(10));

		lblTenDichVu.setPreferredSize(lblDonGiaNhap.getPreferredSize());
		lblDonGiaBan.setPreferredSize(lblDonGiaNhap.getPreferredSize());
		lblDonVi.setPreferredSize(lblDonGiaNhap.getPreferredSize());
		lblSoLuong.setPreferredSize(lblDonGiaNhap.getPreferredSize());
		txtTenDichVu.setPreferredSize(dimension);
		txtDonGiaNhap.setPreferredSize(dimension);
		txtDonGiaBan.setPreferredSize(dimension);
		txtSoLuong.setPreferredSize(dimension);
		txtDonVi.setPreferredSize(dimension);

		bLeft.add(Box.createVerticalStrut(50));

		JPanel pnlKM = new JPanel();
		pnlKM.setBackground(Color.decode("#cccccc"));
		GridLayout gridKM = new GridLayout(5, 1);
		pnlKM.setLayout(gridKM);
		gridKM.setVgap(20);
		pnlKM.add(btnThemMoi = new ButtonGradient("Thêm mới", img_add));
		pnlKM.add(btnCapNhat = new ButtonGradient("Cập nhật", img_edit));
		pnlKM.add(btnXoa = new ButtonGradient("     Xóa    ", img_del));
		pnlKM.add(btnLamMoi = new ButtonGradient("Làm mới", img_reset));
		pnlKM.add(btnThoat = new ButtonGradient(" Thoát ", img_out));
		bLeft.add(pnlKM);

		// Right box
		Box bTacVu = Box.createHorizontalBox();
		JPanel pnlTacVu = new JPanel();
		pnlTacVu.add(bTacVu);
		bTacVu.setOpaque(true);
		bTacVu.setBackground(Color.decode("#e6dbd1"));
		bTacVu.add(Box.createHorizontalStrut(200));
		// Panel Loc
		JPanel pnlLoc = new JPanel();
		String[] cbTT = { "Tất cả", "Còn hàng", "Hết hàng", "Sắp hết hàng", "Đã xóa" };
		pnlLoc.setBackground(Color.decode("#cccccc"));
		pnlLoc.setBorder(BorderFactory.createTitledBorder(line, "Lọc"));
		pnlLoc.add(lblTinhTrang = new JLabel("Tình trạng"));
		pnlLoc.add(Box.createHorizontalStrut(30));
		pnlLoc.add(cbTinhTrang = new JComboBox<>(cbTT));
		cbTinhTrang.setPreferredSize(new Dimension(150, 25));
		bTacVu.add(pnlLoc);
		bTacVu.add(Box.createHorizontalStrut(150));

		// Panel Tra cuu
		JPanel pnlTim = new JPanel();
		pnlTim.setBackground(Color.decode("#cccccc"));
		pnlTim.add(Box.createVerticalStrut(25));
		pnlTim.setBorder(BorderFactory.createTitledBorder(line, "Tra cứu"));
		pnlTim.add(lblTenDichVu = new JLabel("Tên dịch vụ"));
		pnlTim.add(Box.createHorizontalStrut(30));
		pnlTim.add(txtTimDV = new JTextField(20));
		txtTimDV.setPreferredSize(dimension);
		pnlTim.add(Box.createHorizontalStrut(30));
		pnlTim.add(btnTim = new JButton("Tìm", img_search));
		btnTim.setPreferredSize(new Dimension(80, 25));
		bTacVu.add(pnlTim);
		bTacVu.add(Box.createHorizontalStrut(100));

		// Table
		Box table1 = Box.createVerticalBox();
		table1.setBorder(BorderFactory.createTitledBorder(line, "Danh sách dịch vụ"));
		String[] headers = "Mã dịch vụ;Tên dịch vụ;Đơn giá nhập;Đơn giá bán;Đơn vị;Số lượng;Tình trạng".split(";");
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

		// add event button
		layToanBoDV();
		table.addMouseListener(this);
		btnThemMoi.addActionListener(e -> xuLyThemMoi());
		btnLamMoi.addActionListener(e -> xuLyLamMoi());
		btnXoa.addActionListener(e -> xuLyXoa());
		btnCapNhat.addActionListener(e -> xuLyCapNhat());
		btnThoat.addActionListener(e -> xuLyThoat());
		btnTim.addActionListener(e -> xuLyTimKiem());
		cbTinhTrang.addActionListener(e -> xuLyCBTinhTrang());
		txtTimDV.addActionListener(e -> xuLyGoiY());
	}

	// Xu ly kiem tra thong tin day du
	private boolean kiemTraThongTin() {
		String tenDV = txtTenDichVu.getText();
		String donVi = txtDonVi.getText();
		String donGiaNhap = txtDonGiaNhap.getText();
		String donGiaBan = txtDonGiaBan.getText();
		String soLuong = txtSoLuong.getText();
		if (tenDV.trim().equals("") || donVi.trim().equals("") || donGiaNhap.trim().equals("")
				|| donGiaBan.trim().equals("") || soLuong.trim().equals("")) {
			return false;
		}
		return true;
	}

	// Kiem tra rang buoc
	private boolean validData() {
		String tenDV = txtTenDichVu.getText();
		String donVi = txtDonVi.getText();
		String donGiaNhap = txtDonGiaNhap.getText();
		String donGiaBan = txtDonGiaBan.getText();
		String soLuong = txtSoLuong.getText();

		Pattern p = Pattern.compile("[a-zA-Z_0-9]");
		if (!(p.matcher(tenDV).find())) {
			JOptionPane.showMessageDialog(null, "Tên dịch vụ không hợp lệ!");
			return false;
		}
		Pattern p1 = Pattern.compile("[a-zA-Z]+$");
		if (!(p1.matcher(donVi).find())) {
			JOptionPane.showMessageDialog(null, "Đơn vị chỉ bao gồm chữ cái!");
			return false;
		}
		Pattern p2 = Pattern.compile("^\\d+$");
		if (!(p2.matcher(donGiaNhap).find())) {
			JOptionPane.showMessageDialog(null, "Đơn giá nhập không hợp lệ!");
			return false;
		}
		Pattern p3 = Pattern.compile("^\\d+$");
		if (!(p3.matcher(donGiaBan).find())) {
			JOptionPane.showMessageDialog(null, "Đơn giá bán không hợp lệ!");
			return false;
		}
		Pattern p4 = Pattern.compile("^\\d+$");
		if (!(soLuong.length() > 0 && p4.matcher(soLuong).find())) {
			JOptionPane.showMessageDialog(null, "Số lượng không hợp lệ!");
			return false;
		}
		return true;
	}

	// Xu ly them moi
	private void xuLyThemMoi() {

		if (kiemTraThongTin()) {
			if (validData() == true) {
				maDVTT = daoDV.getAllDichVu().size() + 1;
				String maDV = "DV0" + maDVTT;
				String tenDV = txtTenDichVu.getText();
				String donVi = txtDonVi.getText();
				double donGiaNhap = Double.valueOf(txtDonGiaNhap.getText());
				double donGiaBan = Double.valueOf(txtDonGiaBan.getText());
				int soLuong = Integer.valueOf(txtSoLuong.getText());
				String tinhTrang = "";
				if (soLuong == 0) {
					tinhTrang = "Hết hàng";
				} else if (soLuong > 0 && soLuong <= 10) {
					tinhTrang = "Sắp hết hàng";
				} else if (soLuong > 10) {
					tinhTrang = "Còn hàng";
				}
				entity.DichVu dv = new entity.DichVu(maDV, tenDV, donGiaNhap, donGiaBan, donVi, soLuong, tinhTrang);
				int i = JOptionPane.showConfirmDialog(null, "Bạn có chắc chắn muốn thêm mới dịch vụ không ?", "Chú ý!",
						JOptionPane.YES_OPTION);
				if (i == JOptionPane.YES_OPTION) {

					if (daoDV.add(dv)) {
						String[] row = { maDV, tenDV, formatter.format(donGiaNhap) + "VNĐ", formatter.format(donGiaBan)+ "VNĐ", donVi, soLuong + "",
								tinhTrang};
						tableModel.addRow(row);
						JOptionPane.showMessageDialog(null, "Thêm mới dịch vụ thành công!");
					}
				}
			}

		} else {
			JOptionPane.showMessageDialog(null, "Vui lòng nhập đầy đủ thông tin dịch vụ!");
		}
	}

	// Xu ly cap nhat(0: het, 1: sap het; 2: con; 3 da xoa)
	private void xuLyCapNhat() {
		int r = table.getSelectedRow();
		if (r != -1) {
			if (kiemTraThongTin()) {
				if(validData()==true) {
					int i = JOptionPane.showConfirmDialog(null, "Bạn có chắc chắn muốn cập nhật dịch vụ không ?", "Chú ý!",
							JOptionPane.YES_NO_OPTION);
					if (i == JOptionPane.YES_OPTION) {
						String tenDV = txtTenDichVu.getText();
						String donVi = txtDonVi.getText();
						double donGiaNhap = Double.valueOf(txtDonGiaNhap.getText());
						double donGiaBan = Double.valueOf(txtDonGiaBan.getText());
						int soLuong = Integer.valueOf(txtSoLuong.getText());
						String tinhTrang = "";
						table.setValueAt(tenDV, r, 1);
						table.setValueAt(donGiaNhap, r, 2);
						table.setValueAt(donGiaBan, r, 3);
						table.setValueAt(donVi, r, 4);
						table.setValueAt(soLuong, r, 5);
						entity.DichVu dv = new entity.DichVu((String) table.getValueAt(r, 0), tenDV, donGiaNhap, donGiaBan,
								donVi, soLuong, tinhTrang);
						daoDV.updateGia(dv);
						if (Integer.parseInt(table.getValueAt(r, 5).toString()) == 0) {
							table.setValueAt("Hết hàng", r, 6);
						} else if (Integer.parseInt(table.getValueAt(r, 5).toString()) > 10) {
							table.setValueAt("Còn hàng", r, 6);
						} else if (Integer.parseInt(table.getValueAt(r, 5).toString()) > 0 &&Integer.parseInt(table.getValueAt(r, 5).toString()) <= 10) {
							table.setValueAt("Sắp hết hàng", r, 6);
						}
						table.setValueAt(formatter.format(donGiaNhap)+"VNĐ", r, 2);
						table.setValueAt(formatter.format(donGiaBan)+"VNĐ", r, 3);
						JOptionPane.showMessageDialog(null, "Cập nhật thông tin dịch vụ thành công!");
					}
				}
				
			} else {
				JOptionPane.showMessageDialog(null, "Vui lòng nhập đầy đủ thông tin dịch vụ!");
			}

		} else {
			JOptionPane.showMessageDialog(null, "Vui lòng chọn dịch vụ cần cập nhật!");
		}
	}

	// Xu ly lam moi
	private void xuLyLamMoi() {
		txtTenDichVu.setText("");
		txtDonVi.setText("");
		txtDonGiaNhap.setText("");
		txtDonGiaBan.setText("");
		txtSoLuong.setText("");
		txtTimDV.setText("");
		cbTinhTrang.setSelectedIndex(0);
	}

	// Xu ly xoa
	private void xuLyXoa() {
		int row = table.getSelectedRow();
		if (row != -1) {
			int i = JOptionPane.showConfirmDialog(null, "Bạn có chắc chắn muốn xóa dịch vụ này không ?", "Chú ý!",
					JOptionPane.YES_NO_OPTION);
			if (i == JOptionPane.YES_OPTION) {
				daoDV.delete(table.getValueAt(row, 0).toString());
				tableModel.removeRow(row);
				JOptionPane.showMessageDialog(null, "Xóa dịch vụ thành công!");
			}
		} else {
			JOptionPane.showMessageDialog(null, "Vui lòng chọn dịch vụ cần xóa!");
		}
	}

	// Xu ly thoat
	private void xuLyThoat() {
		int i = JOptionPane.showConfirmDialog(null, "Bạn có chắc chắc muốn thoát không ?", "Chú ý!",
				JOptionPane.YES_OPTION);
		if (i == JOptionPane.YES_OPTION) {
			System.exit(0);
		}
	}

	// Xu ly tim kiem
	private void xuLyTimKiem() {
		String maDVTim = txtTimDV.getText();
		int n = 0;
		for (int i = 0; i < table.getRowCount(); i++) {
			if (table.getValueAt(i, 1).toString().equalsIgnoreCase(maDVTim)) {
				table.setRowSelectionInterval(i, i);
				n = 1;
			}
		}
		if (n == 1) {
			JOptionPane.showMessageDialog(null, "Dịch vụ được tìm thấy!");
		} else {
			JOptionPane.showMessageDialog(null, "Dịch vụ không tồn tại!");
		}
	}

	// Lay toan bo dich vu
	private void layToanBoDV() {
		dsDichVu = daoDV.getAllDichVu();
		for (entity.DichVu dv : dsDichVu) {
			tableModel.addRow(new Object[] { dv.getMaDichVu(), dv.getTenDichVu(),
					formatter.format(dv.getDonGiaNhap()) + " VNĐ", formatter.format(dv.getDonGiaBan()) + " VNĐ",
					dv.getDonVi(), dv.getSoLuong(), dv.getTinhTrang() });
			locDVDaXoa();
		}
	}

	// Loc dich vu trang thai da xoa
	private void locDVDaXoa() {
		for (int i = 0; i < tableModel.getRowCount(); i++) {
			String tt = table.getValueAt(i, 6).toString();
			if (tt.equalsIgnoreCase("Đã xóa")) {
				tableModel.removeRow(i);
			}
		}
	}

	// Xoa toan bo dich vu
	private void xoaToanBoDV() {
		DefaultTableModel dm = (DefaultTableModel) table.getModel();
		while (dm.getRowCount() > 0) {
			dm.removeRow(0);
		}

	}

	// Xu ly combo tinh trang
	private void xuLyCBTinhTrang() {
		xoaToanBoDV();
		String tt = cbTinhTrang.getSelectedItem().toString();
		dsDichVu = daoDV.getDichVuCB(tt);
		for (entity.DichVu dv : dsDichVu) {
			tableModel.addRow(new Object[] { dv.getMaDichVu(), dv.getTenDichVu(),
					formatter.format(dv.getDonGiaNhap()) + " VNĐ", formatter.format(dv.getDonGiaBan()) + " VNĐ",
					dv.getDonVi(), dv.getSoLuong(), dv.getTinhTrang() });
		}

	}

	// Xu ly goi y
	private void xuLyGoiY() {
		dsDichVu = daoDV.getAllDichVu();
		String tenDV = "";
		for (entity.DichVu dv : dsDichVu) {
			tenDV += dv.getTenDichVu().toString() + ";";
		}
		String[] data = tenDV.split(";");
		String searchTerm = txtTimDV.getText().toLowerCase();
		txtTimDV.setText(""); // Xóa gợi ý trước đó
		for (String suggestion : data) {
			if (suggestion.toLowerCase().contains(searchTerm)) {
				txtTimDV.setText(suggestion);
				break; // Dừng sau khi tìm thấy một dòng gợi ý
			}
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
		txtTenDichVu.setText(table.getValueAt(row, 1).toString());
		txtDonGiaNhap.setText(table.getValueAt(row, 2).toString());
		txtDonGiaBan.setText(table.getValueAt(row, 3).toString());
		txtDonVi.setText(table.getValueAt(row, 4).toString());
		txtSoLuong.setText(table.getValueAt(row, 5).toString());
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
