
package app;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import com.toedter.calendar.JDateChooser;

import connectDB.ConnectDB;
import dao.MaTuDong;
import dao.DAONhanVien;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.regex.Pattern;

public class NhanVien extends JPanel implements MouseListener {

	private JLabel lblMaNhanVien, lblTenNhanVien, lblNamSinh, lblGioiTinh, lblSoDienThoai, lblCCCD, lblChucVu,
			lblMatKhau, lblTinhTrang;
	private JTextField txtTenNhanVien, txtSoDienThoai, txtCCCD, txtMatKhau, txtTimNhanVien, txtTimSDT, txtMaNV,
			txtTinhTrang;
	private JButton btnThemMoi, btnCapNhat, btnXoa, btnLamMoiNV, btnThoat, btnTim, btnLamMoi, btnLuu;
	private JComboBox cbChucVu, cbTinhTrang, cbGioiTinh, cbTimChucVu;
	private String[] headers = { "Mã nhân viên", "Tên nhân viên", "Năm sinh", "Giới tính", "Số điện thoại", "CCCD",
			"Chức vụ", "Mật khẩu", "Tình trạng" };
	private JDateChooser dateNamSinh;
	private JTable table;
	private DefaultTableModel tableModel;
	private SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
	private DAONhanVien daoNV = new DAONhanVien();
	private ArrayList<entity.NhanVien> dsNV = new ArrayList<>();
	private MaTuDong maNhanVien = new MaTuDong();

	public NhanVien() {

		try {
			ConnectDB.getInstance().connect();
		} catch (SQLException e) {
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

		daoNV = new DAONhanVien();
		createUI();

		layToanBoNV();
		// sự kiện
		btnLamMoi.addActionListener(e -> xuLyLamMoi());
		btnThemMoi.addActionListener(e -> {
			try {
				xuLyThemMoi();
			} catch (ParseException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}
		});
		btnLuu.addActionListener(e -> xuLyLuu());
		btnXoa.addActionListener(e -> xuLyXoa());
		btnCapNhat.addActionListener(e -> {
			try {
				xuLyCapNhat();
			} catch (ParseException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		});
		btnTim.addActionListener(e -> xuLyTimKiem());
		table.addMouseListener(this);

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

		JPanel pnlInput = new JPanel();
		pnlInput.setLayout(new GridLayout(4, 2, 40, 0));
		pnlInput.add(createPanel(lblMaNhanVien = new JLabel("Mã nhân viên"), txtMaNV = new JTextField()));
		pnlInput.add(createPanel(lblTenNhanVien = new JLabel("Tên nhân viên"), txtTenNhanVien = new JTextField()));
		pnlInput.add(createPanel(lblNamSinh = new JLabel("Ngày sinh"), dateNamSinh = new JDateChooser()));
		pnlInput.add(createPanel(lblGioiTinh = new JLabel("Giới tính"), cbGioiTinh = new JComboBox<>()));
		cbGioiTinh.addItem("Nam");
		cbGioiTinh.addItem("Nữ");
		pnlInput.add(createPanel(lblSoDienThoai = new JLabel("Số điện thoại"), txtSoDienThoai = new JTextField()));
		pnlInput.add(createPanel(lblCCCD = new JLabel("CCCD"), txtCCCD = new JTextField()));
		pnlInput.add(createPanel(lblChucVu = new JLabel("Chức vụ"), cbChucVu = new JComboBox<>()));
		cbChucVu.addItem("Lễ tân");
		cbChucVu.addItem("Nhân viên quản lý");
		cbChucVu.addItem("Phục vụ");
		pnlInput.add(createPanel(lblMatKhau = new JLabel("Mật khẩu"), txtMatKhau = new JTextField()));

		// Nút chức năng
		JPanel pnlChucNang = new JPanel();
		pnlChucNang.setBackground(Color.decode("#cccccc"));
		pnlChucNang.setLayout(new GridLayout(3, 2));
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
		pnlThongTinKM.setBorder(BorderFactory.createTitledBorder(line, "Thông tin khuyến mãi"));
		pnlThongTinKM.add(bThongTinKM);

		// Set kích thước
		Dimension dimension = new Dimension(250, 30);
		txtMaNV.setPreferredSize(dimension);
		txtTenNhanVien.setPreferredSize(dimension);
		dateNamSinh.setPreferredSize(dimension);
		cbGioiTinh.setPreferredSize(dimension);
		txtSoDienThoai.setPreferredSize(dimension);
		txtCCCD.setPreferredSize(dimension);
		cbChucVu.setPreferredSize(dimension);
		txtMatKhau.setPreferredSize(dimension);
		lblMaNhanVien.setPreferredSize(lblTenNhanVien.getPreferredSize());
		lblNamSinh.setPreferredSize(lblTenNhanVien.getPreferredSize());
		lblGioiTinh.setPreferredSize(lblTenNhanVien.getPreferredSize());
		lblSoDienThoai.setPreferredSize(lblTenNhanVien.getPreferredSize());
		lblCCCD.setPreferredSize(lblTenNhanVien.getPreferredSize());
		lblChucVu.setPreferredSize(lblTenNhanVien.getPreferredSize());
		lblMatKhau.setPreferredSize(lblTenNhanVien.getPreferredSize());
		int preferredWidth = 300;
		Dimension preferredSize = new Dimension(preferredWidth, pnlChucNang.getPreferredSize().height);
		pnlChucNang.setPreferredSize(preferredSize);

		// Tìm
		Box bLoc1, bLoc2;
		JPanel pnlTim = new JPanel();
		JPanel pnlLoc = new JPanel(new GridLayout(1, 2, 20, 0));
		bLoc1 = Box.createHorizontalBox();
		bLoc1.add(Box.createHorizontalStrut(10));
		bLoc1.add(lblChucVu = new JLabel("Chức vụ:"));
		bLoc1.add(Box.createHorizontalStrut(10));
		bLoc1.add(cbTimChucVu = new JComboBox<>());
		cbTimChucVu.addItem("Tất cả");
		cbTimChucVu.addItem("Lễ tân");
		cbTimChucVu.addItem("Nhân viên quản lý");
		cbTimChucVu.addItem("Phục vụ");
		bLoc2 = Box.createHorizontalBox();
		bLoc2.add(lblTinhTrang = new JLabel("Tình trạng:"));
		bLoc2.add(Box.createHorizontalStrut(10));
		bLoc2.add(cbTinhTrang = new JComboBox<>());
		cbTinhTrang.addItem("Tất cả");
		cbTinhTrang.addItem("Đang làm");
		cbTinhTrang.addItem("Nghỉ việc");
		pnlLoc.add(bLoc1);
		pnlLoc.add(bLoc2);
//		pnlLblTenNV.add(lblTenNhanVien = new JLabel("Tên nhân viên:"));
//		pnlTxtTenNV.add(txtTimNhanVien = new JTextField(10));
		pnlTim.add(lblSoDienThoai = new JLabel("Số điện thoại:"));
		pnlTim.add(txtTimSDT = new JTextField(15));
		pnlTim.add(btnTim = new ButtonGradient("Tìm kiếm", img_search));
		JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, pnlLoc, pnlTim);
		splitPane.setDividerLocation(700);

		// Table
		JPanel pnlTable = new JPanel();
		pnlTable.setBorder(BorderFactory.createTitledBorder("Danh sách khuyến mãi"));
		table = new JTable();
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
		txtMaNV.setEnabled(false);

		// Add vào giao diện
		setLayout(new BorderLayout());
		add(pnlThongTinKM, BorderLayout.NORTH);
		add(pnlTable, BorderLayout.CENTER);
		pnlInput.setBackground(Color.decode("#e6dbd1"));
		pnlChucNang.setBackground(Color.decode("#e6dbd1"));
		pnlThongTinKM.setBackground(Color.decode("#e6dbd1"));
	}

	private JPanel createPanel(JLabel label, JComponent component) {
		JPanel panel = new JPanel();
		panel.setLayout(new FlowLayout(FlowLayout.LEFT));
		panel.add(label);
		panel.add(component);
		panel.setBackground(Color.decode("#e6dbd1"));
		return panel;
	}

	private void xuLyLuu() {
		int luaChon = JOptionPane.showConfirmDialog(null, "Có chắc chắn thêm thông tin nhân viên không?", "Chú ý",
				JOptionPane.YES_NO_OPTION);
		if (luaChon == JOptionPane.YES_OPTION) {
			if (validData() == true) {
				String tenNV = txtTenNhanVien.getText();
				String namSinh = dateFormat.format(dateNamSinh.getDate());
				String gioiTinh = cbGioiTinh.getSelectedItem().toString();
				String sdt = txtSoDienThoai.getText();
				String cccd = txtCCCD.getText();
				String chucVu = cbChucVu.getSelectedItem().toString();
				String matKhau = txtMatKhau.getText();
				String maNV = txtMaNV.getText();
				
				try {
					if (gioiTinh.equalsIgnoreCase("Nam")) {
						daoNV.add(new entity.NhanVien(maNV, tenNV, dateFormat.parse(namSinh), false, sdt, cccd, chucVu,
								matKhau, false));
					}
					else {
						daoNV.add(new entity.NhanVien(maNV, tenNV, dateFormat.parse(namSinh), true, sdt, cccd, chucVu,
								matKhau, false));
					}
				} catch (NumberFormatException e) {
					e.printStackTrace();
				} catch (ParseException e) {
					e.printStackTrace();
				}
				String[] row = { maNV, tenNV, namSinh, gioiTinh, sdt, cccd, chucVu, matKhau, "Đang làm" };
				tableModel.addRow(row);
				JOptionPane.showMessageDialog(null, "Thêm mới nhân viên thành công!");
			}
		} else {
			JOptionPane.showMessageDialog(null, "Vui lòng nhập đầy đủ thông tin nhân viên!");
		}
	}

	// Kiem tra rang buoc
	private boolean validData() {
		String tenNV = txtTenNhanVien.getText();
		String sdt = txtSoDienThoai.getText();
		String cccd = txtCCCD.getText();
		String matKhau = txtMatKhau.getText();

		Pattern p = Pattern.compile("[a-zA-Z]+");
		if (!(p.matcher(tenNV).find())) {
			JOptionPane.showMessageDialog(null, "Tên nhân viên không hợp lệ!");
			return false;
		}

		Pattern p1 = Pattern.compile("[0-9]{10}");
		if (!(p1.matcher(sdt).find())) {
			JOptionPane.showMessageDialog(null, "Số điện thoại chỉ được nhập chữ số!");
			return false;
		}

		Pattern p2 = Pattern.compile("[0-9]{12}");
		if (!(p2.matcher(cccd).find())) {
			JOptionPane.showMessageDialog(null, "Căn cước công dân chỉ được nhập chữ số!");
			return false;
		}

		Pattern p3 = Pattern.compile("(.)+");
		if (!(p3.matcher(matKhau).find())) {
			JOptionPane.showMessageDialog(null, "Mật khẩu không được để trống!");
			return false;
		}
		return true;
	}

	// Xu ly them moi
	private void xuLyThemMoi() throws ParseException {
		String maNV = maNhanVien.formatMa(dsNV.get(dsNV.size() - 1).getMaNV());
		txtMaNV.setText(maNV);
	}

	private Object xuLyXoa() {
		// TODO Auto-generated method stub
		int row = table.getSelectedRow();
		int luaChon = JOptionPane.showConfirmDialog(null, "Có chắc chắn xóa thông tin nhân viên không?", "Chú ý",
				JOptionPane.YES_NO_OPTION);
		if (row != -1) {
			if (luaChon == JOptionPane.YES_OPTION) {
				tableModel.removeRow(row);
			}
		}
		return null;
	}

	private Object xuLyCapNhat() throws ParseException {
		// TODO Auto-generated method stub
		int luaChon = JOptionPane.showConfirmDialog(null, "Có chắc chắn muốn cập nhật thông tin nhân viên không?",
				"Chú ý", JOptionPane.YES_NO_OPTION);
		if (luaChon == JOptionPane.YES_OPTION) {
			String tenNV = txtTenNhanVien.getText();
			String namSinh = dateFormat.format(dateNamSinh.getDate());
			String gioiTinh = cbGioiTinh.getSelectedItem().toString();
			String sdt = txtSoDienThoai.getText();
			String cccd = txtCCCD.getText();
			String chucVu = cbChucVu.getSelectedItem().toString();
			String matKhau = txtMatKhau.getText();
			String maNV = table.getValueAt(table.getSelectedRow(), 0).toString();
			String tt = table.getValueAt(table.getSelectedRow(), 8).toString();
			Boolean tinhTrang;
			if (tt.equals("Đang làm")){
				tinhTrang = true;
			}
			else tinhTrang = false;
			int viTri = table.getSelectedRow();
			tableModel.removeRow(viTri);
			String[] row = { maNV, tenNV, namSinh, gioiTinh, sdt, cccd, chucVu, matKhau, tt };
			try {
				if (gioiTinh.equalsIgnoreCase("Nam")) {
					daoNV.updateNhanVien(new entity.NhanVien(maNV, tenNV, dateFormat.parse(namSinh), false, sdt, cccd, chucVu,
							matKhau, tinhTrang));
				}
				else {
					daoNV.updateNhanVien(new entity.NhanVien(maNV, tenNV, dateFormat.parse(namSinh), true, sdt, cccd, chucVu,
							matKhau, tinhTrang));
				}
			} catch (NumberFormatException e) {
				e.printStackTrace();
			} catch (ParseException e) {
				e.printStackTrace();
			}
			tableModel.insertRow(viTri, row);
			JOptionPane.showMessageDialog(null, "Cập nhật nhân viên thành công!");
		} else {
			JOptionPane.showMessageDialog(null, "Vui lòng nhập đầy đủ thông tin nhân viên!");
		}
		return null;
	}

	private Object xuLyLamMoi() {
		// TODO Auto-generated method stub
		txtTenNhanVien.setText("");
		((JTextField) dateNamSinh.getDateEditor().getUiComponent()).setText("");
		txtSoDienThoai.setText("");
		txtCCCD.setText("");
		txtMatKhau.setText("");
		cbChucVu.setSelectedIndex(-1);
		cbGioiTinh.setSelectedIndex(-1);
		return null;
	}

	private Object xuLyTraCuu() {
		// TODO Auto-generated method stub
		txtTimNhanVien.setText("");
		txtTimSDT.setText("");
		return null;
	}

	private void xuLyTimKiem() {
		String tenNVTim = txtTimNhanVien.getText();
		int n = 0;
		for (int i = 0; i < table.getRowCount(); i++) {
			if (table.getValueAt(i, 1).toString().equalsIgnoreCase(tenNVTim)) {
				table.setRowSelectionInterval(i, i);
				n = 1;
			}
		}
		if (n == 1) {
			JOptionPane.showMessageDialog(null, "Tên nhân viên được tìm thấy!");
		} else {
			JOptionPane.showMessageDialog(null, "Tên nhân viên không tồn tại!");
		}
	}

	// Lay toan bo nhan vien
	private void layToanBoNV() {
		dsNV = daoNV.getAll();
		for (entity.NhanVien nv : dsNV) {
			String gioiTinh = nv.getGioiTinh() ? "Nữ" : "Nam";
			String tinhTrang = nv.getTinhTrangNV() ? "Đang làm" : "Nghỉ việc";
			tableModel.addRow(new Object[] { nv.getMaNV(), nv.getTenNV(), dateFormat.format(nv.getNamSinh()), gioiTinh,
					nv.getSdthoai(), nv.getCccd(), nv.getChucVu(), nv.getMatKhau(), tinhTrang });

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
		txtMaNV.setText(table.getValueAt(row, 0).toString());
		txtTenNhanVien.setText(table.getValueAt(row, 1).toString());
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		Date date;
		try {
			date = dateFormat.parse(table.getValueAt(row, 2).toString());
			dateNamSinh.setDate(date);
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		cbGioiTinh.setSelectedItem(table.getValueAt(row, 3).toString());
		txtSoDienThoai.setText(table.getValueAt(row, 4).toString());
		txtCCCD.setText(table.getValueAt(row, 5).toString());
		cbChucVu.setSelectedItem(table.getValueAt(row, 6).toString());
		txtMatKhau.setText(table.getValueAt(row, 7).toString());
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