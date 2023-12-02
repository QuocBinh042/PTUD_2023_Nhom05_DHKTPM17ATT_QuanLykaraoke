package app;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import com.toedter.calendar.JDateChooser;

import connectDB.ConnectDB;
import dao.DAODichVu;
import dao.MaTuDong;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.regex.Pattern;

public class PanelDichVu extends JPanel implements MouseListener {

	private JLabel lblTenDichVu, lblDonGia, lblDonVi, lblSoLuong, lblTinhTrang, lblLocTinhTrang, lblTimDV, lblMaDV;
	private JTextField txtTenDichVu, txtDonGia, txtDonVi, txtSoLuong, txtTimDV, txtMaDV, txtTinhTrang;
	private JButton btnThemMoi, btnCapNhat, btnXoa, btnLamMoi, btnTim, btnLuu;
	private JComboBox cbTinhTrang;
	private JTable table;
	private DefaultTableModel tableModel;
	private Box bLeft, bRight;
	private ArrayList<entity.DichVu> dsDichVu;
	private DAODichVu daoDV;
	private DecimalFormat formatter = new DecimalFormat("###");
	private static int maDVTT = 0;
	private MaTuDong maDVTD = new MaTuDong();

	public PanelDichVu() {

		try {
			ConnectDB.getInstance().connect();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		daoDV = new DAODichVu();

		createUI();

		// add event button
		layToanBoDV();
		table.addMouseListener(this);
		btnThemMoi.addActionListener(e -> xuLyThemMoi());
		btnLuu.addActionListener(e -> xuLyLuu());
		btnLamMoi.addActionListener(e -> xuLyLamMoi());
		btnXoa.addActionListener(e -> xuLyXoa());
		btnCapNhat.addActionListener(e -> xuLyCapNhat());
		btnTim.addActionListener(e -> xuLyTimKiem());
		cbTinhTrang.addActionListener(e -> xuLyCBTinhTrang());
		txtTimDV.addActionListener(e -> xuLyGoiY());
	}

	private void createUI() {
		Icon img_add = new ImageIcon("src/img/add16.png");
		Icon img_del = new ImageIcon("src/img/bin.png");
		Icon img_reset = new ImageIcon("src/img/refresh16.png");
		Icon img_edit = new ImageIcon("src/img/edit16.png");
		Icon img_out = new ImageIcon("src/img/out.png");
		Icon img_search = new ImageIcon("src/img/search.png");
		Icon img_refresh = new ImageIcon("src/img/refresh16.png");
		Border line = BorderFactory.createLineBorder(Color.BLACK);

		// Thông tin khuyến mãi
		JPanel pnlInput = new JPanel();
		pnlInput.setLayout(new GridLayout(3, 2, 40, 0));
		pnlInput.add(createPanel(lblMaDV = new JLabel("Mã dịch vụ"), txtMaDV = new JTextField()));
		pnlInput.add(createPanel(lblTenDichVu = new JLabel("Tên dịch vụ"), txtTenDichVu = new JTextField()));
		pnlInput.add(createPanel(lblDonVi = new JLabel("Đơn vị"), txtDonVi = new JTextField()));
		pnlInput.add(createPanel(lblDonGia = new JLabel("Đơn giá"), txtDonGia = new JTextField()));
		pnlInput.add(createPanel(lblSoLuong = new JLabel("Số lượng"), txtSoLuong = new JTextField()));
		pnlInput.add(createPanel(lblTinhTrang = new JLabel("Tình trạng"), txtTinhTrang = new JTextField()));

		// Nút chức năng
		JPanel pnlChucNang = new JPanel();
		pnlChucNang.setLayout(new GridLayout(3, 2, 0, 10));
		pnlChucNang.add(btnThemMoi = new ButtonGradient("Thêm mới", img_add));
		pnlChucNang.add(btnLuu = new ButtonGradient("Lưu", img_add));
		pnlChucNang.add(btnCapNhat = new ButtonGradient("Cập nhật", img_edit));
		pnlChucNang.add(btnXoa = new ButtonGradient("Xóa", img_del));
		pnlChucNang.add(btnLamMoi = new ButtonGradient("Làm mới", img_reset));

		Box bThongTinKM = Box.createHorizontalBox();
		bThongTinKM.add(pnlInput);
		bThongTinKM.add(Box.createHorizontalStrut(50));
		bThongTinKM.add(pnlChucNang);
		JPanel pnlThongTinKM = new JPanel();
		pnlThongTinKM.setBorder(BorderFactory.createTitledBorder("Thông tin dịch vụ"));
		pnlThongTinKM.add(bThongTinKM);

		// Set kích thước
		Dimension dimension = new Dimension(250, 30);
		txtTenDichVu.setPreferredSize(dimension);
		txtDonGia.setPreferredSize(dimension);
		txtSoLuong.setPreferredSize(dimension);
		txtDonVi.setPreferredSize(dimension);
		txtMaDV.setPreferredSize(dimension);
		txtTinhTrang.setPreferredSize(dimension);
		lblMaDV.setPreferredSize(lblTenDichVu.getPreferredSize());
		lblTinhTrang.setPreferredSize(lblTenDichVu.getPreferredSize());
		lblDonGia.setPreferredSize(lblTenDichVu.getPreferredSize());
		lblDonVi.setPreferredSize(lblTenDichVu.getPreferredSize());
		lblSoLuong.setPreferredSize(lblTenDichVu.getPreferredSize());
		int preferredWidth = 300;
		Dimension preferredSize = new Dimension(preferredWidth, pnlChucNang.getPreferredSize().height);
		pnlChucNang.setPreferredSize(preferredSize);

		// Tìm
		JPanel pnlLoc = new JPanel();
		String[] cbTT = { "Tất cả", "Còn hàng", "Hết hàng", "Sắp hết hàng", "Đã xóa" };
		pnlLoc.add(lblTinhTrang = new JLabel("Tình trạng"));
		pnlLoc.add(Box.createHorizontalStrut(30));
		pnlLoc.add(cbTinhTrang = new JComboBox<>(cbTT));
		cbTinhTrang.setPreferredSize(dimension);
		JPanel pnlTim = new JPanel();
		pnlTim.add(lblTenDichVu = new JLabel("Tên dịch vụ"));
		pnlTim.add(txtTimDV = new JTextField(20));
		pnlTim.add(btnTim = new ButtonGradient("Tìm", img_search));
		JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, pnlLoc, pnlTim);
		splitPane.setDividerLocation(600);

		// Table
		JPanel pnlTable = new JPanel();
		pnlTable.setBorder(BorderFactory.createTitledBorder(line, "Danh sách dịch vụ"));
		table = new JTable();
		String[] headers = "Mã dịch vụ;Tên dịch vụ;Đơn giá;Đơn vị;Số lượng;Tình trạng".split(";");
		tableModel = new DefaultTableModel(headers, 0);
		table.setModel(tableModel);
		table.setRowHeight(25);
		table.setAutoCreateRowSorter(true);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		JScrollPane scroll = new JScrollPane(table, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scroll.setViewportView(table);
		pnlTable.setLayout(new BorderLayout());
		pnlTable.add(splitPane, BorderLayout.NORTH);
		pnlTable.add(scroll, BorderLayout.CENTER);

		// Set editable
		txtMaDV.setEnabled(false);
		txtTinhTrang.setEnabled(false);

		// Add vào giao diện
		setLayout(new BorderLayout());
		add(pnlThongTinKM, BorderLayout.NORTH);
		add(pnlTable, BorderLayout.CENTER);
		pnlLoc.setBackground(Color.decode("#B099BC"));
		pnlTim.setBackground(Color.decode("#B099BC"));
		pnlTable.setBackground(Color.decode("#B099BC"));
		pnlInput.setBackground(Color.decode("#B099BC"));
		pnlChucNang.setBackground(Color.decode("#B099BC"));
		pnlThongTinKM.setBackground(Color.decode("#B099BC"));
	}

	private JPanel createPanel(JLabel label, JComponent component) {
		JPanel panel = new JPanel();
		panel.setLayout(new FlowLayout(FlowLayout.LEFT));
		panel.add(label);
		panel.add(component);
		label.setFont(new Font("Sanserif", Font.BOLD, 13));
		panel.setBackground(Color.decode("#B099BC"));
		return panel;
	}

	// Xu ly kiem tra thong tin day du
	private boolean kiemTraThongTin() {
		String tenDV = txtTenDichVu.getText();
		String donVi = txtDonVi.getText();
		String donGia = txtDonGia.getText();
		String soLuong = txtSoLuong.getText();
		if (tenDV.trim().equals("") || donVi.trim().equals("") || donGia.trim().equals("")
				|| soLuong.trim().equals("")) {
			return false;
		}
		return true;
	}

	// Kiem tra rang buoc
	private boolean validData() {
		String tenDV = txtTenDichVu.getText();
		String donVi = txtDonVi.getText();
		String donGia = txtDonGia.getText();
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
		if (!(p2.matcher(donGia).find())) {
			JOptionPane.showMessageDialog(null, "Đơn giá nhập không hợp lệ!");
			return false;
		}
		Pattern p3 = Pattern.compile("^\\d+$");
		if (!(soLuong.length() > 0 && p3.matcher(soLuong).find())) {
			JOptionPane.showMessageDialog(null, "Số lượng không hợp lệ!");
			return false;
		}
		return true;
	}

	private Object xuLyThemMoi() {
		// TODO Auto-generated method stub
		String maDV = maDVTD.formatMa(daoDV.getAllDichVu().get(daoDV.getAllDichVu().size() - 1).getMaDichVu());
		txtMaDV.setText(maDV);
		return null;
	}

	// Xu ly them moi
	private void xuLyLuu() {
		if (kiemTraThongTin()) {
			if (validData() == true) {
				String maDV = txtMaDV.getText();
				String tenDV = txtTenDichVu.getText();
				String donVi = txtDonVi.getText();
				double donGia = Double.valueOf(txtDonGia.getText());
				int soLuong = Integer.valueOf(txtSoLuong.getText());
				String tinhTrang = "";
				if (soLuong == 0) {
					tinhTrang = "Hết hàng";
				} else if (soLuong > 0 && soLuong <= 10) {
					tinhTrang = "Sắp hết hàng";
				} else if (soLuong > 10) {
					tinhTrang = "Còn hàng";
				}
				entity.DichVu dv = new entity.DichVu(maDV, tenDV, donGia, donVi, soLuong, tinhTrang);
				int i = JOptionPane.showConfirmDialog(null, "Bạn có chắc chắn muốn thêm mới dịch vụ không ?", "Chú ý!",
						JOptionPane.YES_OPTION);
				if (i == JOptionPane.YES_OPTION) {

					if (daoDV.add(dv)) {
						String[] row = { maDV, tenDV, formatter.format(donGia), donVi, soLuong + "", tinhTrang };
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
				if (validData() == true) {
					int i = JOptionPane.showConfirmDialog(null, "Bạn có chắc chắn muốn cập nhật dịch vụ không ?",
							"Chú ý!", JOptionPane.YES_NO_OPTION);
					if (i == JOptionPane.YES_OPTION) {
						String tenDV = txtTenDichVu.getText();
						String donVi = txtDonVi.getText();
						double donGia = Double.valueOf(txtDonGia.getText());
						int soLuong = Integer.valueOf(txtSoLuong.getText());
						String tinhTrang = "";
						table.setValueAt(tenDV, r, 1);
						table.setValueAt(donGia, r, 2);
						table.setValueAt(donVi, r, 3);
						table.setValueAt(soLuong, r, 4);
						entity.DichVu dv = new entity.DichVu((String) table.getValueAt(r, 0), tenDV, donGia, donVi,
								soLuong, tinhTrang);
						daoDV.update(dv);
						if (Integer.parseInt(table.getValueAt(r, 4).toString()) == 0) {
							table.setValueAt("Hết hàng", r, 5);
						} else if (Integer.parseInt(table.getValueAt(r, 4).toString()) > 10) {
							table.setValueAt("Còn hàng", r, 5);
						} else if (Integer.parseInt(table.getValueAt(r, 4).toString()) > 0
								&& Integer.parseInt(table.getValueAt(r, 4).toString()) <= 10) {
							table.setValueAt("Sắp hết hàng", r, 5);
						}
						table.setValueAt(formatter.format(donGia), r, 2);
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
		txtMaDV.setText("");
		txtTenDichVu.setText("");
		txtDonVi.setText("");
		txtDonGia.setText("");
		txtSoLuong.setText("");
		txtTimDV.setText("");
		txtTinhTrang.setText("");
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
			tableModel.addRow(new Object[] { dv.getMaDichVu(), dv.getTenDichVu(), formatter.format(dv.getDonGia()),
					dv.getDonVi(), dv.getSoLuong(), dv.getTinhTrang() });
			locDVDaXoa();
		}
	}

	// Loc dich vu trang thai da xoa
	private void locDVDaXoa() {
		for (int i = 0; i < tableModel.getRowCount(); i++) {
			String tt = table.getValueAt(i, 5).toString();
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
			tableModel.addRow(new Object[] { dv.getMaDichVu(), dv.getTenDichVu(), formatter.format(dv.getDonGia()),
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
		txtMaDV.setText(table.getValueAt(row, 0).toString());
		txtTenDichVu.setText(table.getValueAt(row, 1).toString());
		txtDonGia.setText(table.getValueAt(row, 2).toString());
		txtDonVi.setText(table.getValueAt(row, 3).toString());
		txtSoLuong.setText(table.getValueAt(row, 4).toString());
		txtTinhTrang.setText(table.getValueAt(row, 5).toString());
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
