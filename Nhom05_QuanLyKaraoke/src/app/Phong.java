package app;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import app.LoaiPhong;
import connectDB.ConnectDB;
import dao.daoLoaiPhong;
import dao.daoPhong;
import dao.MaTuDong;
import entity.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.regex.Pattern;

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
	private daoPhong daoPhong;
	private ArrayList<entity.Phong> dsPhong;
	private ArrayList<entity.LoaiPhong> dsLP;
	private daoLoaiPhong daoLP;
	private MaTuDong maPTD = new MaTuDong();
	private DecimalFormat formatter = new DecimalFormat("###");

	public Phong() {

		try {
			ConnectDB.getInstance().connect();
			;
		} catch (Exception e) {
			e.printStackTrace();
		}
		daoPhong = new daoPhong();
		daoLP = new daoLoaiPhong();

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
		// Lay danh sach MaLP do vao comboBox
		String ds = "";
		dsLP = daoLP.getAllLoaiPhong();
		for (entity.LoaiPhong lp : dsLP) {
			ds += lp.getMaLoaiPhong().toString() + ";";
		}
		String[] cb = ds.split(";");
		pnlMaLP.setBackground(Color.decode("#cccccc"));
		pnlMaLP.add(lblMaLoaiPhong = new JLabel("Mã loại phòng"));
		pnlMaLP.add(cbMaLoaiPhong = new JComboBox<>(cb));
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
		lblMoTa.setPreferredSize(lblMaLoaiPhong.getPreferredSize());
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
		bLeft.add(Box.createVerticalStrut(60));

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
		String cbtt[] = {"Tất cả","Còn trống","Đã đặt trước","Đang thuê", "Đã xóa"};
		String cblp[] = {"Tất cả","Thường","VIP"};
		pnlLoc.setBackground(Color.decode("#cccccc"));
		GridLayout gridLoc = new GridLayout(2, 2);
		Dimension dmsBtnLoc = new Dimension(120, 25);
		gridLoc.setVgap(10);
		pnlLoc.setLayout(gridLoc);
		pnlLoc.setBorder(BorderFactory.createTitledBorder(line, "Lọc"));
		pnlLoc.add(lblTinhTrang = new JLabel("Tình trạng"));
		pnlLoc.add(cbTinhTrang = new JComboBox<>(cbtt));
		cbTinhTrang.setPreferredSize(dmsBtnLoc);
		pnlLoc.add(lblLoaiPhong = new JLabel("Loại phòng"));
		pnlLoc.add(cbLoaiPhong = new JComboBox<>(cblp));
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
		btnTim.setPreferredSize(new Dimension(100, 25));
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
		layToanBoPhong();
		cbMaLoaiPhong.addActionListener(e -> capNhatTTLP());
		cbTinhTrang.addActionListener(e -> xuLyLocCBTT());
		cbLoaiPhong.addActionListener(e -> xuLyLocCBLP());
		txtTimMaPhong.addActionListener(e -> xuLyGoiY());
		btnQuanLyChiTiet.addActionListener(e -> xuLyChuyen());
		btnLamMoi.addActionListener(e -> xuLyLamMoi());
		btnThemMoi.addActionListener(e -> xuLyThemMoi());
		btnXoa.addActionListener(e -> xuLyXoa());
		btnCapNhat.addActionListener(e -> xuLyCapNhat());
		btnThoat.addActionListener(e -> xuLyThoat());
		btnTim.addActionListener(e -> xuLyTimKiem());
		table.addMouseListener(this);

	}

	
	// Loc danh sach dich vu co trang thai: da xoa
		private void locPhongDaXoa() {
			for (int i = 0; i < tableModel.getRowCount(); i++) {
				String tt = table.getValueAt(i, 6).toString();
				if (tt.equalsIgnoreCase("Đã xóa")) {
					tableModel.removeRow(i);
				}
			}
		}

		// Lay toan bo danh sach phong
		private void layToanBoPhong() {
			dsPhong = daoPhong.getAllPhong();
			for (entity.Phong p : dsPhong) {
				tableModel.addRow(new Object[] { p.getMaPhong(), p.getTenPhong(), p.getLoaiPhong().getMaLoaiPhong(),
						p.getLoaiPhong().getTenLoaiPhong(), p.getLoaiPhong().getSucChua(),
						formatter.format(p.getLoaiPhong().getGiaLoaiPhong()), p.getTinhTrangPhong(), p.getMoTa() });
			}
			locPhongDaXoa();
		}

		// Xu ly them moi phong
		private void xuLyThemMoi() {
			if (kiemTraThongTin()) {
				if (validData() == true) {
					String maP = maPTD.formatMa(daoPhong.getAllPhong().get(daoPhong.getAllPhong().size() - 1).getMaPhong());
					String tenP = txtTenPhong.getText();
					String maLP = cbMaLoaiPhong.getSelectedItem().toString();
					String loaiP = txtLoaiPhong.getText();
					int sucChua = Integer.parseInt(txtSucChua.getText());
					double giaP = Double.parseDouble(txtGiaPhong.getText());
					String moTa = txtaMoTa.getText();
					entity.LoaiPhong lp = new entity.LoaiPhong(maLP, loaiP, sucChua, giaP);
					entity.Phong p = new entity.Phong(maP, tenP, lp, "Còn trống", moTa);
					int i = JOptionPane.showConfirmDialog(null, "Bạn có chắc chắn muốn thêm mới phòng không ?", "Chú ý!",
							JOptionPane.YES_OPTION);
					if (i == JOptionPane.YES_OPTION) {

						if (daoPhong.add(p)) {
							String[] row = { maP, tenP, maLP, loaiP, sucChua + "", formatter.format(giaP), "Còn trống",
									moTa };
							tableModel.addRow(row);
							JOptionPane.showMessageDialog(null, "Thêm mới phòng thành công!");
						}
					}
				}

			} else {
				JOptionPane.showMessageDialog(null, "Vui lòng nhập đầy đủ thông tin phòng!");
			}

		}

		// Xu ly cap nhat thong tin phong
		private void xuLyCapNhat() {
			int r = table.getSelectedRow();
			if (r != -1) {
				if (kiemTraThongTin()) {
					int i = JOptionPane.showConfirmDialog(null, "Bạn có chắc chắn muốn cập nhật phòng?", "Chú ý!",
							JOptionPane.YES_NO_OPTION);
					if (i == JOptionPane.YES_OPTION) {
						String tenP = txtTenPhong.getText();
						String maLP = cbMaLoaiPhong.getSelectedItem().toString();
						String loaiP = txtLoaiPhong.getText();
						int sucChua = Integer.parseInt(txtSucChua.getText());
						double giaP = Double.parseDouble(txtGiaPhong.getText());
						String moTa = txtaMoTa.getText();
						String tinhTrang = table.getValueAt(r, 6).toString();
						table.setValueAt(tenP, r, 1);
						table.setValueAt(maLP, r, 2);
						table.setValueAt(loaiP, r, 3);
						table.setValueAt(sucChua, r, 4);
						table.setValueAt(formatter.format(giaP), r, 5);
						table.setValueAt(tinhTrang, r, 6);
						table.setValueAt(moTa, r, 7);
						entity.LoaiPhong lp = new entity.LoaiPhong(maLP, loaiP, sucChua, giaP);
						entity.Phong p = new entity.Phong(table.getValueAt(r, 0).toString(), tenP, lp, tinhTrang, moTa);
						daoPhong.update(p);
						JOptionPane.showMessageDialog(null, "Cập nhật thông tin phòng thành công!");
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
			cbMaLoaiPhong.setSelectedIndex(0);
			cbLoaiPhong.setSelectedIndex(0);
			cbTinhTrang.setSelectedIndex(0);
			txtTenPhong.setText("");
			txtLoaiPhong.setText("");
			txtGiaPhong.setText("");
			txtSucChua.setText("");
			txtaMoTa.setText("");
		}

		// Xu ly xoa phong
		private void xuLyXoa() {
			int row = table.getSelectedRow();
			if (row != -1) {
				int i = JOptionPane.showConfirmDialog(null, "Bạn có chắc chắn muốn xóa phòng không?", "Chú ý!",
						JOptionPane.YES_NO_OPTION);
				if (i == JOptionPane.YES_OPTION) {
					daoPhong.delete(table.getValueAt(row, 0).toString());
					tableModel.removeRow(row);
					JOptionPane.showMessageDialog(null, "Xóa phòng thành công!");
				}
			} else {
				JOptionPane.showMessageDialog(null, "Vui lòng chọn phòng cần xóa!");
			}
		}

		// Kiem tra du lieu nhap vao
		private boolean validData() {
			String tenP = txtTenPhong.getText();
			String moTa = txtaMoTa.getText();

			Pattern p = Pattern.compile("^[0-9]{3}$");
			if (!(p.matcher(tenP).find())) {
				JOptionPane.showMessageDialog(null, "Tên phòng không hợp lệ");
				return false;
			}
//			Pattern p1 = Pattern.compile("^[a-zA-Z0-9 ]+$");
//			if (!(p1.matcher(moTa).find())) {
//				JOptionPane.showMessageDialog(null, "Mô tả không hợp lệ!");
//				return false;
//			}
			return true;
		}

		// Xu ly khi chuyen sang giao dien loai phong
		private void xuLyChuyen() {
			quanlyLP = new LoaiPhong();
			quanlyLP.setVisible(true);
//			quanlyLP.setAlwaysOnTop(true);
			quanlyLP.setLocationRelativeTo(null);
		}

		// Xu ly khi thoat
		private void xuLyThoat() {
			int i = JOptionPane.showConfirmDialog(null, "Bạn có chắc chắn muốn thoát không?", "Chú ý!",
					JOptionPane.YES_NO_OPTION);
			if (i == JOptionPane.YES_OPTION) {
				System.exit(0);
			}
		}

		// Xu ly tim kiem phong
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

		// Xu ly kiem tra nhap day du thong tin
		private boolean kiemTraThongTin() {
			String tenP = txtTenPhong.getText();
			String loaiP = txtLoaiPhong.getText();
			String sucChua = txtSucChua.getText();
			String giaP = txtGiaPhong.getText();
			if (tenP.equals("") || loaiP.equals("") || sucChua.equals("") || giaP.equals("")) {
				return false;
			}
			return true;
		}

		// Xu ly goi y khi tim phong
		private void xuLyGoiY() {
			dsPhong = daoPhong.getAllPhong();
			String maP = "";
			for (entity.Phong p : dsPhong) {
				maP += p.getMaPhong().toString() + ";";
			}
			String[] data = maP.split(";");
			String searchTerm = txtTimMaPhong.getText().toLowerCase();
			txtTimMaPhong.setText(""); // Xóa gợi ý trước đó
			for (String suggestion : data) {
				if (suggestion.toLowerCase().contains(searchTerm)) {
					txtTimMaPhong.setText(suggestion);
					break; // Dừng sau khi tìm thấy một dòng gợi ý
				}
			}
		}

		// Xoa toan bo loai phong
		private void xoaToanBoPhong() {
			DefaultTableModel dm = (DefaultTableModel) table.getModel();
			while (dm.getRowCount() > 0) {
				dm.removeRow(0);
			}
		}

		// Xu ly combobox loc theo tinh trang
		private void xuLyLocCBTT() {
			xoaToanBoPhong();
			String lc = cbTinhTrang.getSelectedItem().toString();
			dsPhong = daoPhong.getCBTT(lc);
			for (entity.Phong p : dsPhong) {
				tableModel.addRow(new Object[] { p.getMaPhong(), p.getTenPhong(), p.getLoaiPhong().getMaLoaiPhong(),
						p.getLoaiPhong().getTenLoaiPhong(), p.getLoaiPhong().getSucChua(),
						formatter.format(p.getLoaiPhong().getGiaLoaiPhong()), p.getTinhTrangPhong(), p.getMoTa() });
			}
		}

		// Xu ly combobox loc theo loai phong
		private void xuLyLocCBLP() {
			xoaToanBoPhong();
			String lc = cbLoaiPhong.getSelectedItem().toString();
			dsPhong = daoPhong.getCBLP(lc);
			for (entity.Phong p : dsPhong) {
				tableModel.addRow(new Object[] { p.getMaPhong(), p.getTenPhong(), p.getLoaiPhong().getMaLoaiPhong(),
						p.getLoaiPhong().getTenLoaiPhong(), p.getLoaiPhong().getSucChua(),
						formatter.format(p.getLoaiPhong().getGiaLoaiPhong()), p.getTinhTrangPhong(), p.getMoTa() });
			}
		}

		// xu ly cap nhat thong tin loai phong khi chon combobox maLP
		private void capNhatTTLP() {
			String maLP = cbMaLoaiPhong.getSelectedItem().toString();
			dsLP = daoLP.getAllLoaiPhong();
			for (entity.LoaiPhong lp : dsLP) {
				if (lp.getMaLoaiPhong().equalsIgnoreCase(maLP)) {
					txtLoaiPhong.setText(lp.getTenLoaiPhong());
					txtSucChua.setText(formatter.format(lp.getSucChua()));
					txtGiaPhong.setText(formatter.format(lp.getGiaLoaiPhong()));
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
		txtTenPhong.setText(table.getValueAt(row, 1).toString());
		cbMaLoaiPhong.setSelectedItem(table.getValueAt(row, 2).toString());
		capNhatTTLP();
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
