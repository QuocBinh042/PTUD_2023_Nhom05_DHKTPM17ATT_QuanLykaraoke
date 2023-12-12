package app;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import com.toedter.calendar.JDateChooser;

import app.LoaiPhong;
import connectDB.ConnectDB;
import dao.DAOLoaiPhong;
import dao.DAOPhong;
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

public class PanelPhong extends JPanel implements MouseListener {

	private JLabel lblTenPhong, lblLoaiPhong, lblSucChua, lblGiaPhong, lblMoTa, lblTimMaPhong, lblTinhTrang,
			lblMaLoaiPhong, lblMaPhong;
	private JTextField txtTenPhong, txtSucChua, txtGiaPhong, txtTimMaPhong, txtLoaiPhong, txtMaPhong, txtTinhTrang;
	private JTextArea txaMoTa;
	private JButton btnThemMoi, btnCapNhat, btnXoa, btnLamMoi, btnQLLoaiPhong, btnThoat, btnTim, btnLuu;
	private JComboBox cbLoaiPhong, cbTinhTrang, cbMaLoaiPhong;
	private JTable table;
	private DefaultTableModel tableModel;
	private LoaiPhong quanlyLP;
	private DAOPhong daoPhong;
	private ArrayList<entity.Phong> dsPhong;
	private ArrayList<entity.LoaiPhong> dsLP;
	private DAOLoaiPhong daoLP;
	private MaTuDong maPTD = new MaTuDong();
	private DecimalFormat formatter = new DecimalFormat("###");

	public PanelPhong() {
		try {
			ConnectDB.getInstance().connect();

		} catch (Exception e) {
			e.printStackTrace();
		}

		try {
			UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (InstantiationException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IllegalAccessException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (UnsupportedLookAndFeelException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		daoPhong = new DAOPhong();
		daoLP = new DAOLoaiPhong();

		createUI();
//		// add event button
		layToanBoPhong();
		cbMaLoaiPhong.addActionListener(e -> capNhatTTLP());
		cbTinhTrang.addActionListener(e -> xuLyLocCBTT());
		cbLoaiPhong.addActionListener(e -> xuLyLocCBLP());
		txtTimMaPhong.addActionListener(e -> xuLyGoiY());
		btnQLLoaiPhong.addActionListener(e -> xuLyChuyen());
		btnLamMoi.addActionListener(e -> xuLyLamMoi());
		btnThemMoi.addActionListener(e -> xuLyThemMoi());
		btnLuu.addActionListener(e -> xuLyLuu());
		btnXoa.addActionListener(e -> xuLyXoa());
		btnCapNhat.addActionListener(e -> xuLyCapNhat());
		btnTim.addActionListener(e -> xuLyTimKiem());
		table.addMouseListener(this);

	}

	private void createUI() {
		// TODO Auto-generated method stub
		Icon img_add = new ImageIcon("src/img/add16.png");
		Icon img_del = new ImageIcon("src/img/bin.png");
		Icon img_reset = new ImageIcon("src/img/refresh16.png");
		Icon img_edit = new ImageIcon("src/img/edit.png");
		Icon img_out = new ImageIcon("src/img/out.png");
		Icon img_search = new ImageIcon("src/img/search.png");
		Icon img_transfer = new ImageIcon("src/img/change16.png");
		Border line = BorderFactory.createLineBorder(Color.BLACK);

		// Thông tin phòng
		JPanel pnlInput = new JPanel();
		pnlInput.setLayout(new GridLayout(4, 2, 30, 0));
		pnlInput.add(createPanel(lblMaPhong = new JLabel("Mã phòng"), txtMaPhong = new JTextField()));

//		 Lay danh sach MaLP do vao comboBox
		String ds = "";
		dsLP = daoLP.getAllLoaiPhong();
		for (entity.LoaiPhong lp : dsLP) {
			ds += lp.getMaLoaiPhong().toString() + ";";
		}
		String[] cb = ds.split(";");
		pnlInput.add(createPanel(lblMaLoaiPhong = new JLabel("Mã loại phòng"), cbMaLoaiPhong = new JComboBox<>(cb)));
		pnlInput.add(createPanel(lblTenPhong = new JLabel("Tên phòng"), txtTenPhong = new JTextField()));
		pnlInput.add(createPanel(lblLoaiPhong = new JLabel("Loại phòng"), txtLoaiPhong = new JTextField()));
		pnlInput.add(createPanel(lblMoTa = new JLabel("Mô tả"), txaMoTa = new JTextArea()));
		pnlInput.add(createPanel(lblGiaPhong = new JLabel("Giá phòng"), txtGiaPhong = new JTextField()));
		pnlInput.add(createPanel(lblTinhTrang = new JLabel("Tình trạng"), txtTinhTrang = new JTextField()));
		pnlInput.add(createPanel(lblSucChua = new JLabel("Sức chứa"), txtSucChua = new JTextField()));

		// Nút chức năng
		JPanel pnlChucNang = new JPanel();
		pnlChucNang.setLayout(new GridLayout(3, 2, 10, 10));
		pnlChucNang.add(btnThemMoi = new ButtonGradient("Thêm mới", img_add));
		pnlChucNang.add(btnLuu = new ButtonGradient("Lưu", img_add));
		pnlChucNang.add(btnCapNhat = new ButtonGradient("Cập nhật", img_edit));
		pnlChucNang.add(btnXoa = new ButtonGradient("Xóa", img_del));
		pnlChucNang.add(btnLamMoi = new ButtonGradient("Làm mới", img_reset));
		pnlChucNang.add(btnQLLoaiPhong = new ButtonGradient("Quản lý loại phòng", img_transfer));

		Box bThongTinPhong = Box.createHorizontalBox();
		bThongTinPhong.add(pnlInput);
		bThongTinPhong.add(Box.createHorizontalStrut(20));
		bThongTinPhong.add(pnlChucNang);
		JPanel pnlThongTinPhong = new JPanel();
		pnlThongTinPhong.setBorder(BorderFactory.createTitledBorder("Thông tin phòng"));
		pnlThongTinPhong.add(bThongTinPhong);

		Dimension dimension = new Dimension(240, 30);
		lblMaPhong.setPreferredSize(lblMaLoaiPhong.getPreferredSize());
		lblTenPhong.setPreferredSize(lblMaLoaiPhong.getPreferredSize());
		lblTinhTrang.setPreferredSize(lblMaLoaiPhong.getPreferredSize());
		lblLoaiPhong.setPreferredSize(lblMaLoaiPhong.getPreferredSize());
		lblSucChua.setPreferredSize(lblMaLoaiPhong.getPreferredSize());
		lblGiaPhong.setPreferredSize(lblMaLoaiPhong.getPreferredSize());
		lblMoTa.setPreferredSize(lblMaLoaiPhong.getPreferredSize());
		txtTenPhong.setPreferredSize(dimension);
		cbMaLoaiPhong.setPreferredSize(txtTenPhong.getPreferredSize());
		txtMaPhong.setPreferredSize(dimension);
		txtLoaiPhong.setPreferredSize(dimension);
		txtSucChua.setPreferredSize(dimension);
		txtGiaPhong.setPreferredSize(dimension);
		txtTinhTrang.setPreferredSize(dimension);
		txaMoTa.setPreferredSize(dimension);
		int preferredWidth = 400;
		Dimension preferredSize = new Dimension(preferredWidth, pnlChucNang.getPreferredSize().height);
		pnlChucNang.setPreferredSize(preferredSize);

		// Tìm
		String cbtt[] = { "Tất cả", "Còn trống", "Đã đặt trước", "Đang thuê", "Đã xóa" };
		String cblp[] = { "Tất cả", "Thường", "VIP" };
		Box bLoc1, bLoc2;
		JPanel pnlLoc = new JPanel(new GridLayout(1, 2, 0, 0));
		bLoc1 = Box.createHorizontalBox();
		bLoc1.add(Box.createHorizontalStrut(20));
		bLoc1.add(lblTinhTrang = new JLabel("Tình trạng"));
		bLoc1.add(Box.createHorizontalStrut(10));
		bLoc1.add(cbTinhTrang = new JComboBox<>(cbtt));
		bLoc2 = Box.createHorizontalBox();
		bLoc2.add(Box.createHorizontalStrut(20));
		bLoc2.add(lblLoaiPhong = new JLabel("Loại phòng"));
		bLoc2.add(Box.createHorizontalStrut(10));
		bLoc2.add(cbLoaiPhong = new JComboBox<>(cblp));
		pnlLoc.add(bLoc1);
		pnlLoc.add(bLoc2);
		JPanel pnlTim = new JPanel();
		pnlTim.add(Box.createVerticalStrut(25));
		pnlTim.add(lblTimMaPhong = new JLabel("Tìm theo mã phòng"));
		pnlTim.add(txtTimMaPhong = new JTextField(20));
		txtTimMaPhong.setPreferredSize(dimension);
		pnlTim.add(btnTim = new ButtonGradient("Tìm kiếm", img_search));

		JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, pnlLoc, pnlTim);
		splitPane.setDividerLocation(600);

		JPanel pnlTable = new JPanel();
		pnlTable.setBorder(BorderFactory.createTitledBorder(line, "Danh sách phòng"));
		table = new JTable();
		String[] headers = "Mã phòng;Tên phòng;Mã loại phòng;Loại phòng;Sức chứa;Giá phòng;Tình trạng;Mô tả"
				.split(";");
		tableModel = new DefaultTableModel(headers, 0);
		table.setModel(tableModel);
		table.setRowHeight(30);
		table.setAutoCreateRowSorter(true);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		JScrollPane scroll = new JScrollPane(table, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scroll.setViewportView(table);
		pnlTable.setLayout(new BorderLayout());
		pnlTable.add(splitPane, BorderLayout.NORTH);
		pnlTable.add(scroll, BorderLayout.CENTER);

//		// Set editable
		txtMaPhong.setEnabled(false);
		txtTinhTrang.setEnabled(false);

		// Add vào giao diện
		setLayout(new BorderLayout());
		add(pnlThongTinPhong, BorderLayout.NORTH);
		add(pnlTable, BorderLayout.CENTER);
		pnlLoc.setBackground(Color.decode("#D0BAFB"));
		pnlTim.setBackground(Color.decode("#D0BAFB"));
		pnlTable.setBackground(Color.decode("#D0BAFB"));
		pnlInput.setBackground(Color.decode("#D0BAFB"));
		pnlChucNang.setBackground(Color.decode("#D0BAFB"));
		pnlThongTinPhong.setBackground(Color.decode("#D0BAFB"));
	}

	private JPanel createPanel(JLabel label, JComponent component) {
		JPanel panel = new JPanel();
		panel.setLayout(new FlowLayout(FlowLayout.LEFT));
		panel.add(label);
		panel.add(component);
		label.setFont(new Font("Sanserif", Font.BOLD, 13));
		panel.setBackground(Color.decode("#D0BAFB"));
		return panel;
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
		String maP = maPTD.formatMa(daoPhong.getAllPhong().get(daoPhong.getAllPhong().size() - 1).getMaPhong());
		txtMaPhong.setText(maP);

	}

	private void xuLyLuu() {
		if (kiemTraThongTin()) {
			if (validData() == true) {
				String maP = txtMaPhong.getText();
				String tenP = txtTenPhong.getText();
				String maLP = cbMaLoaiPhong.getSelectedItem().toString();
				String loaiP = txtLoaiPhong.getText();
				int sucChua = Integer.parseInt(txtSucChua.getText());
				double giaP = Double.parseDouble(txtGiaPhong.getText());
				String moTa = txaMoTa.getText();
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
					String moTa = txaMoTa.getText();
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
			JOptionPane.showMessageDialog(null, "Vui lòng chọn phòng cần cập nhật!");
		}
	}

	// Xu ly lam moi
	private void xuLyLamMoi() {
		cbMaLoaiPhong.setSelectedIndex(0);
		cbLoaiPhong.setSelectedIndex(0);
		cbTinhTrang.setSelectedIndex(0);
		txtMaPhong.setText("");
		txtTenPhong.setText("");
		txtLoaiPhong.setText("");
		txtGiaPhong.setText("");
		txtSucChua.setText("");
		txtTinhTrang.setText("");
		txaMoTa.setText("");
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
		String moTa = txaMoTa.getText();

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
		txtMaPhong.setText(table.getValueAt(row, 0).toString());
		txtTenPhong.setText(table.getValueAt(row, 1).toString());
		cbMaLoaiPhong.setSelectedItem(table.getValueAt(row, 2).toString());
		capNhatTTLP();
		txtTinhTrang.setText(table.getValueAt(row, 6).toString());
		txaMoTa.setText(table.getValueAt(row, 7).toString());

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
