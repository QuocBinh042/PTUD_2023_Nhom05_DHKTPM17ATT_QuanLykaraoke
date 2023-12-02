package app;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import com.toedter.calendar.JDateChooser;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.regex.Pattern;

import connectDB.ConnectDB;
import dao.DAOKhachHang;
import dao.MaTuDong;
import dao.DAODichVu;

public class PanelKhachHang extends JPanel implements MouseListener {
	private JLabel lblTenKhachHang, lblGioiTinh, lblSoDienThoai, lblEmail, lblGhiChu, lblLoaiKhachHang, lblMaKH;
	private JTextField txtTenKhachHang, txtSoDienThoai, txtEmail, txtTimSDT, txtTimTenKH, txtMaKH;
	private JTextArea txaGhiChu;
	private JButton btnThemMoi, btnCapNhat, btnLamMoi, btnThoat, btnTim, btnLamMoiKH, btnLuu;
	private JComboBox cbLoaiKhachHang, cbGioiTinh;
	private String[] headers = { "Mã khách hàng", "Tên khách hàng", "Loại khách hàng", "Giới tính", "Số điện thoại",
			"Email", "Số giờ đã thuê", "Ghi chú" };
	private JTable table;
	private DefaultTableModel tableModel;
	private DAOKhachHang daoKH = new DAOKhachHang();
	private ArrayList<entity.KhachHang> dsKH = new ArrayList<>();
	private MaTuDong maKhachHang = new MaTuDong();
	private Boolean gt;

	public PanelKhachHang() {
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
		daoKH = new DAOKhachHang();

		createUI();
		dsKH = daoKH.getAll();
		layDanhSachKH(dsKH);
		// sự kiện
		cbLoaiKhachHang.addActionListener(e -> xuLyCBLoaiKH());
		btnLamMoi.addActionListener(e -> xuLyLamMoi());
		btnThemMoi.addActionListener(e -> xuLyThemMoi());
		btnLuu.addActionListener(e -> xuLyLuu());
		btnCapNhat.addActionListener(e -> xuLyCapNhat());
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

		// Thông tin kháchh hàng
		JPanel pnlInput = new JPanel();
		pnlInput.setLayout(new GridLayout(3, 2, 40, 0));
		pnlInput.add(createPanel(lblMaKH = new JLabel("Mã khách hàng"), txtMaKH = new JTextField()));
		pnlInput.add(createPanel(lblTenKhachHang = new JLabel("Tên khách hàng"), txtTenKhachHang = new JTextField()));
		pnlInput.add(createPanel(lblGioiTinh = new JLabel("Giới tính"), cbGioiTinh = new JComboBox<>()));
		cbGioiTinh.addItem("Nam");
		cbGioiTinh.addItem("Nữ");
		pnlInput.add(createPanel(lblSoDienThoai = new JLabel("Số điện thoại"), txtSoDienThoai = new JTextField()));
		pnlInput.add(createPanel(lblEmail = new JLabel("Email"), txtEmail = new JTextField()));
		pnlInput.add(createPanel(lblGhiChu = new JLabel("Ghi chú"), txaGhiChu = new JTextArea()));

		// Nút chức năng
		JPanel pnlChucNang = new JPanel();
		pnlChucNang.setBackground(Color.decode("#cccccc"));
		pnlChucNang.setLayout(new GridLayout(3, 2));
		pnlChucNang.add(btnThemMoi = new ButtonGradient("Thêm mới", img_add));
		pnlChucNang.add(btnLuu = new ButtonGradient("Lưu", img_add));
		pnlChucNang.add(btnCapNhat = new ButtonGradient("Cập nhật", img_edit));
		pnlChucNang.add(btnLamMoi = new ButtonGradient("Làm mới", img_reset));

		Box bThongTinKM = Box.createHorizontalBox();
		bThongTinKM.add(pnlInput);
		bThongTinKM.add(Box.createHorizontalStrut(50));
		bThongTinKM.add(pnlChucNang);
		JPanel pnlThongTinKM = new JPanel();
		pnlThongTinKM.setBorder(BorderFactory.createTitledBorder("Thông tin khách hàng"));
		pnlThongTinKM.add(bThongTinKM);

		// Set kích thước
		Dimension dimension = new Dimension(250, 30);
		txtMaKH.setPreferredSize(dimension);
		txtTenKhachHang.setPreferredSize(dimension);
		cbGioiTinh.setPreferredSize(dimension);
		txtSoDienThoai.setPreferredSize(dimension);
		txtEmail.setPreferredSize(dimension);
		txaGhiChu.setPreferredSize(dimension);
		lblTenKhachHang.setPreferredSize(lblTenKhachHang.getPreferredSize());
		lblSoDienThoai.setPreferredSize(lblTenKhachHang.getPreferredSize());
		lblGioiTinh.setPreferredSize(lblTenKhachHang.getPreferredSize());
		lblEmail.setPreferredSize(lblTenKhachHang.getPreferredSize());
		lblGhiChu.setPreferredSize(lblTenKhachHang.getPreferredSize());
		int preferredWidth = 300;
		Dimension preferredSize = new Dimension(preferredWidth, pnlChucNang.getPreferredSize().height);
		pnlChucNang.setPreferredSize(preferredSize);

		// Tìm
		JPanel pnlLoc = new JPanel();
		pnlLoc.add(lblLoaiKhachHang = new JLabel("Loại khách hàng:"));
		pnlLoc.add(Box.createHorizontalStrut(10));
		pnlLoc.add(cbLoaiKhachHang = new JComboBox<>());
		cbLoaiKhachHang.setPreferredSize(dimension);
		cbLoaiKhachHang.addItem("Tất cả");
		cbLoaiKhachHang.addItem("Thường");
		cbLoaiKhachHang.addItem("VIP");
		JPanel pnlTim = new JPanel();
		pnlTim.add(lblSoDienThoai = new JLabel("Số điện thoại"));
		pnlTim.add(txtTimSDT = new JTextField(15));
		pnlTim.add(btnTim = new ButtonGradient("Tìm kiếm", img_search));
		JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, pnlLoc, pnlTim);
		splitPane.setDividerLocation(600);

		// Table
		JPanel pnlTable = new JPanel();
		pnlTable.setBorder(BorderFactory.createTitledBorder(line,"Danh sách khuyến mãi"));
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
		txtMaKH.setEnabled(false);

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

	private void xuLyLuu() {
		int luaChon = JOptionPane.showConfirmDialog(null, "Có chắc chắn thêm thông tin khách hàng không?", "Chú ý",
				JOptionPane.YES_NO_OPTION);
		if (luaChon == JOptionPane.YES_OPTION) {
			if (validData() == true) {
				String tenKH = txtTenKhachHang.getText();
				String gioiTinh = cbGioiTinh.getSelectedItem().toString();
				if (gioiTinh == "Nam") {
					gt = false;
				} else
					gt = true;
				String sdt = txtSoDienThoai.getText();
				String email = txtEmail.getText();
				String ghichu = txaGhiChu.getText();
				String maKH = txtMaKH.getText();
				String[] row = { maKH, tenKH, "Thường", gioiTinh, sdt, email, "0", ghichu };
				if (daoKH.add(new entity.KhachHang(maKH, tenKH, false, gt, sdt, email, 0, ghichu))) {
					tableModel.addRow(row);
					JOptionPane.showMessageDialog(null, "Thêm mới khách hàng thành công!");
				} else
					JOptionPane.showMessageDialog(null, "Thêm mới khách hàng không thành công!");

			}

		} else {
			JOptionPane.showMessageDialog(null, "Vui lòng nhập đầy đủ thông tin khách hàng!");
		}
	}

	// Xoa toan bo dich vu
	private void xoaToanBoKH() {
		DefaultTableModel dm = (DefaultTableModel) table.getModel();
		while (dm.getRowCount() > 0) {
			dm.removeRow(0);
		}

	}

	// Lay toan bo khach hang
	private void layDanhSachKH(ArrayList<entity.KhachHang> dsKH) {
		xoaToanBoKH();
		for (entity.KhachHang kh : dsKH) {
			String gioiTinh = kh.getGioiTinh() ? "Nữ" : "Nam";
			String loaikh = kh.getLoaiKH() ? "VIP" : "Thường";
			tableModel.addRow(new Object[] { kh.getMaKH(), kh.getTenKH(), loaikh, gioiTinh, kh.getSdthoai(),
					kh.getEmail(), kh.getSoGioDaThue(), kh.getGhiChu() });

		}
	}

	// Xu ly combobox loai khach hang
	private void xuLyCBLoaiKH() {
		if (cbLoaiKhachHang.getSelectedItem().toString().equalsIgnoreCase("VIP")) {
			dsKH = DAOKhachHang.getKhachHangCB(true);
		} else if (cbLoaiKhachHang.getSelectedItem().toString().equalsIgnoreCase("Thường")) {
			dsKH = DAOKhachHang.getKhachHangCB(false);
		} else
			dsKH = daoKH.getAll();
		layDanhSachKH(dsKH);
	}

	// Kiem tra rang buoc
	private boolean validData() {
		String tenKH = txtTenKhachHang.getText();
		String sdt = txtSoDienThoai.getText();
		String email = txtEmail.getText();

		Pattern p = Pattern.compile("[a-zA-Z]+");
		if (!(p.matcher(tenKH).find())) {
			JOptionPane.showMessageDialog(null, "Tên khách hàng không hợp lệ!");
			return false;
		}

		Pattern p1 = Pattern.compile("[0-9]{10}");
		if (!(p1.matcher(sdt).find())) {
			JOptionPane.showMessageDialog(null, "Số điện thoại chỉ được nhập chữ số!");
			return false;
		}

		Pattern p2 = Pattern.compile("^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@"
				+ "[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$");
		if (!(p2.matcher(email).find())) {
			JOptionPane.showMessageDialog(null, "Email không được để trống!");
			return false;
		}

		return true;
	}

	// Xu ly them moi
	private void xuLyThemMoi() {
		String maKH = maKhachHang.formatMa(dsKH.get(dsKH.size() - 1).getMaKH());
		txtMaKH.setText(maKH);
	}

	private Object xuLyCapNhat() {
		// TODO Auto-generated method stub
		int luaChon = JOptionPane.showConfirmDialog(null, "Có chắc chắn muốn cập nhật thông tin khách hàng không?",
				"Chú ý", JOptionPane.YES_NO_OPTION);
		if (luaChon == JOptionPane.YES_OPTION) {
			if (validData() == true) {
				String tenKH = txtTenKhachHang.getText();
				String gioiTinh = cbGioiTinh.getSelectedItem().toString();
				String sdt = txtSoDienThoai.getText();
				String email = txtEmail.getText();
				String ghichu = txaGhiChu.getText();
				int viTri = table.getSelectedRow();
				String maKH = (String) table.getValueAt(table.getSelectedRow(), 0);
				tableModel.removeRow(viTri);
				String[] row = { maKH, tenKH, "Thường", gioiTinh, sdt, email, "2", ghichu };
				daoKH.updateKhachHang(new entity.KhachHang(maKH, tenKH, false, false, sdt, email, 0, ghichu));
				tableModel.insertRow(viTri, row);
				JOptionPane.showMessageDialog(null, "Cập nhật khách hàng thành công!");
			} else {
				JOptionPane.showMessageDialog(null, "Vui lòng nhập đầy đủ thông tin khách hàng!");
			}
		}
		return null;

	}

	private Object xuLyLamMoi() {
		// TODO Auto-generated method stub
		txtMaKH.setText("");
		txtTenKhachHang.setText("");
		cbGioiTinh.setSelectedIndex(-1);
		txtSoDienThoai.setText("");
		txtEmail.setText("");
		txaGhiChu.setText("");
		return null;
	}

	private Object xuLyTraCuu() {
		// TODO Auto-generated method stub
		txtTimTenKH.setText("");
		txtTimSDT.setText("");
		return null;
	}

	private void xuLyTimKiem() {
		String SDT = txtTimSDT.getText();
		int n = 0;
		for (int i = 0; i < table.getRowCount(); i++) {
			if (table.getValueAt(i, 4).toString().equalsIgnoreCase(SDT)) {
				table.setRowSelectionInterval(i, i);
				n = 1;
			}
		}
		if (n == 1) {
			JOptionPane.showMessageDialog(null, "Tên khách hàng được tìm thấy!");
		} else {
			JOptionPane.showMessageDialog(null, "Tên khách hàng không tồn tại!");
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
		txtMaKH.setText(table.getValueAt(row, 0).toString());
		txtTenKhachHang.setText(table.getValueAt(row, 1).toString());
		cbGioiTinh.setSelectedItem(table.getValueAt(row, 3).toString());
		txtSoDienThoai.setText(table.getValueAt(row, 4).toString());
		txtEmail.setText(table.getValueAt(row, 5).toString());
		txaGhiChu.setText(table.getValueAt(row, 7).toString());
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
