
package app;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import com.toedter.calendar.JDateChooser;

import connectDB.ConnectDB;
import dao.MaTuDong;
import dao.daoNhanVien;

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

	private JLabel lblTenNhanVien, lblNamSinh, lblGioiTinh, lblSoDienThoai, lblCCCD, lblChucVu, lblMatKhau,
			lblTinhTrang;
	private JTextField txtTenNhanVien, txtSoDienThoai, txtCCCD, txtMatKhau, txtTimNhanVien, txtTimSDT;
	private JButton btnThemMoi, btnCapNhat, btnXoa, btnLamMoiNV, btnThoat, btnTim, btnLamMoi;
	private JComboBox cbChucVu, cbTinhTrang, cbGioiTinh, cbTimChucVu;
	private String[] headers = { "Mã nhân viên", "Tên nhân viên", "Năm sinh", "Giới tính", "Số điện thoại", "CCCD",
			"Chức vụ", "Mật khẩu", "Tình trạng" };
	private JDateChooser dateNamSinh;
	private JTable table;
	private DefaultTableModel tableModel;
	private SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
	private daoNhanVien daoNV = new daoNhanVien();
	private ArrayList<entity.NhanVien> dsNV = new ArrayList<>();
	private MaTuDong maNhanVien = new MaTuDong();


	public NhanVien() {

		try {
			ConnectDB.getInstance().connect();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		daoNV = new daoNhanVien();
		
		Icon img_add = new ImageIcon("src/img/add2.png");
		Icon img_del = new ImageIcon("src/img/del.png");
		Icon img_reset = new ImageIcon("src/img/refresh16.png");
		Icon img_edit = new ImageIcon("src/img/edit.png");
		Icon img_out = new ImageIcon("src/img/out.png");
		Icon img_search = new ImageIcon("src/img/search.png");

		JPanel pnlLeft = new JPanel(new BorderLayout());
		JPanel pnlRight = new JPanel(new BorderLayout());

		Box bLeft = Box.createVerticalBox();
		Box bRight = Box.createVerticalBox();
		Box b1, b2, b3, b4, b5, b6, b7, b8, b9, b10, b11, b12, b13, b14, b15, b16, b17, b92, bLoc, bTacVu, bTim, bTim1,
				bTim2, bLoc1, bLoc2;
		Dimension dimension = new Dimension(170, 25);
		Dimension dimension2 = new Dimension(120, 20);
		Border line = BorderFactory.createLineBorder(Color.BLACK);
		bLeft.setBorder(BorderFactory.createTitledBorder(line, "Thông tin nhân viên"));
		bLeft.add(b1 = Box.createHorizontalBox());
		bLeft.add(Box.createVerticalStrut(15));
		b1.add(lblTenNhanVien = new JLabel("Tên nhân viên"));
		b1.add(Box.createHorizontalStrut(20));
		b1.add(txtTenNhanVien = new JTextField());

		bLeft.add(b2 = Box.createHorizontalBox());
		bLeft.add(Box.createVerticalStrut(15));
		b2.add(lblNamSinh = new JLabel("Năm sinh"));
		b2.add(Box.createHorizontalStrut(20));
		b2.add(dateNamSinh = new JDateChooser());
		dateNamSinh.setPreferredSize(dimension);

		bLeft.add(b3 = Box.createHorizontalBox());
		bLeft.add(Box.createVerticalStrut(15));
		b3.add(lblGioiTinh = new JLabel("Giới tính"));
		b3.add(Box.createHorizontalStrut(20));
		b3.add(cbGioiTinh = new JComboBox<>());
		cbGioiTinh.addItem("Nam");
		cbGioiTinh.addItem("Nữ");

		bLeft.add(b4 = Box.createHorizontalBox());
		bLeft.add(Box.createVerticalStrut(15));
		b4.add(lblSoDienThoai = new JLabel("Số điện thoại"));
		b4.add(Box.createHorizontalStrut(20));
		b4.add(txtSoDienThoai = new JTextField());

		bLeft.add(b5 = Box.createHorizontalBox());
		bLeft.add(Box.createVerticalStrut(15));
		b5.add(lblCCCD = new JLabel("CCCD"));
		b5.add(Box.createHorizontalStrut(20));
		b5.add(txtCCCD = new JTextField());

		bLeft.add(b6 = Box.createHorizontalBox());
		bLeft.add(Box.createVerticalStrut(15));
		b6.add(lblChucVu = new JLabel("Chức vụ"));
		b6.add(Box.createHorizontalStrut(20));
		b6.add(cbChucVu = new JComboBox<>());
		cbChucVu.addItem("Lễ tân");
		cbChucVu.addItem("Nhân viên quản lý");
		cbChucVu.addItem("Phục vụ");

		bLeft.add(b7 = Box.createHorizontalBox());
		bLeft.add(Box.createVerticalStrut(40));
		b7.add(lblMatKhau = new JLabel("Mật khẩu"));
		b7.add(Box.createHorizontalStrut(20));
		b7.add(txtMatKhau = new JTextField());
		txtMatKhau.setBorder(line);

		lblNamSinh.setPreferredSize(lblTenNhanVien.getPreferredSize());
		lblSoDienThoai.setPreferredSize(lblTenNhanVien.getPreferredSize());
		lblCCCD.setPreferredSize(lblTenNhanVien.getPreferredSize());
		lblGioiTinh.setPreferredSize(lblTenNhanVien.getPreferredSize());
		lblChucVu.setPreferredSize(lblTenNhanVien.getPreferredSize());
		lblMatKhau.setPreferredSize(lblTenNhanVien.getPreferredSize());

		JPanel pnlNV = new JPanel();
		GridLayout gridNV = new GridLayout(2, 2);
		pnlNV.setLayout(gridNV);
		gridNV.setHgap(30);
		gridNV.setVgap(10);
		pnlNV.add(btnThemMoi = new ButtonGradient("Thêm mới", img_add));
		pnlNV.add(btnCapNhat = new ButtonGradient("Cập nhật", img_edit));
		pnlNV.add(btnXoa = new ButtonGradient("Xóa", img_del));
		pnlNV.add(btnLamMoiNV = new ButtonGradient("Làm mới", img_reset));
		pnlNV.setBackground(Color.decode("#cccccc"));
		bLeft.add(pnlNV);
		bLeft.add(Box.createVerticalStrut(20));
		bLeft.add(b92 = Box.createHorizontalBox());
		b92.add(Box.createVerticalStrut(20));
		b92.add(Box.createHorizontalStrut(3));
		b92.add(btnThoat = new ButtonGradient("Thoát", img_out));
		btnThoat.setMaximumSize(new Dimension(Integer.MAX_VALUE, btnThoat.getMinimumSize().height));
		bLeft.add(Box.createVerticalStrut(270));

		// Loc
		bRight.add(bTacVu = Box.createHorizontalBox());
		bTacVu.add(Box.createHorizontalStrut(100));

		bLoc = Box.createVerticalBox();
		JPanel pnlLblChucVu = new JPanel();
		JPanel pnlCbChucVu = new JPanel();
		JPanel pnlLblTinhTrang = new JPanel();
		JPanel pnlCbTinhTrang = new JPanel();
		pnlLblChucVu.setBackground((Color.decode("#cccccc")));
		pnlCbChucVu.setBackground((Color.decode("#cccccc")));
		pnlLblTinhTrang.setBackground((Color.decode("#cccccc")));
		pnlCbTinhTrang.setBackground((Color.decode("#cccccc")));

		pnlLblChucVu.add(lblChucVu = new JLabel("Chức vụ:"));
		pnlCbChucVu.add(cbTimChucVu = new JComboBox<>());
		pnlLblTinhTrang.add(lblTinhTrang = new JLabel("Tình trạng:"));
		pnlCbTinhTrang.add(cbTinhTrang = new JComboBox<>());
		cbTinhTrang.addItem("Tất cả");
		cbTinhTrang.addItem("Đang làm");
		cbTinhTrang.addItem("Nghỉ việc");

		bLoc.add(bLoc1 = Box.createHorizontalBox());
		bLoc.add(bLoc2 = Box.createHorizontalBox());
		bLoc1.add(pnlLblChucVu);
		bLoc1.add(pnlCbChucVu);
		bLoc2.add(pnlLblTinhTrang);
		bLoc2.add(pnlCbTinhTrang);

		JPanel pnlLoc = new JPanel();
		pnlLoc.add(bLoc);
		pnlLoc.setBackground((Color.decode("#cccccc")));
		pnlLoc.setBorder(BorderFactory.createTitledBorder(line, "Lọc"));
		bTacVu.add(pnlLoc);

		bTacVu.add(Box.createHorizontalStrut(100));

		// Tim
		bTim = Box.createVerticalBox();
		JPanel pnlLblTenNV = new JPanel();
		JPanel pnlTxtTenNV = new JPanel();
		JPanel pnlLblSdt = new JPanel();
		JPanel pnlTxtSdt = new JPanel();
		pnlLblTenNV.setBackground((Color.decode("#cccccc")));
		pnlLblSdt.setBackground((Color.decode("#cccccc")));
		pnlTxtSdt.setBackground((Color.decode("#cccccc")));
		pnlTxtTenNV.setBackground((Color.decode("#cccccc")));
		pnlLblTenNV.add(lblTenNhanVien = new JLabel("Tên nhân viên:"));
		pnlTxtTenNV.add(txtTimNhanVien = new JTextField(10));
		pnlLblSdt.add(lblSoDienThoai = new JLabel("Số điện thoại:"));
		pnlTxtSdt.add(txtTimSDT = new JTextField(10));

		bTim.add(bTim1 = Box.createHorizontalBox());
		bTim1.add(pnlLblTenNV);
		bTim1.add(pnlTxtTenNV);
		bTim1.add(btnTim = new ButtonGradient("Tìm", img_search));
		bTim.add(Box.createVerticalStrut(10));
		bTim.add(bTim2 = Box.createHorizontalBox());
		bTim2.add(pnlLblSdt);
		bTim2.add(pnlTxtSdt);
		bTim2.add(btnLamMoi = new ButtonGradient("Làm mới", img_reset));
		bTacVu.add(Box.createVerticalStrut(10));
		JPanel pnlTim = new JPanel();
		pnlTim.add(bTim);
		pnlTim.setBackground((Color.decode("#cccccc")));
		pnlTim.setBorder(BorderFactory.createTitledBorder(line, "Tra cứu"));
		bTacVu.add(pnlTim);

		bTacVu.add(Box.createHorizontalStrut(100));

		//
		lblChucVu.setPreferredSize(lblTinhTrang.getPreferredSize());
		lblSoDienThoai.setPreferredSize(lblTenNhanVien.getPreferredSize());
		cbChucVu.setPreferredSize(dimension);
		cbTinhTrang.setPreferredSize(dimension);
		cbTimChucVu.setPreferredSize(dimension);
		txtTenNhanVien.setPreferredSize(dimension);
		txtSoDienThoai.setPreferredSize(dimension);
		btnTim.setPreferredSize(dimension2);
		btnLamMoi.setPreferredSize(dimension2);

		// Table - Center
		tableModel = new DefaultTableModel(headers, 0);
		JScrollPane scroll = new JScrollPane(table, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scroll.setBorder(BorderFactory.createTitledBorder(line, "Danh sách nhân viên"));
		scroll.setViewportView(table = new JTable(tableModel));
		table.setRowHeight(25);
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
		
		layToanBoNV();
		btnLamMoiNV.addActionListener(e -> xuLyLamMoi());
		btnThemMoi.addActionListener(e -> {
			try {
				xuLyThemMoi();
			} catch (ParseException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		});
		btnXoa.addActionListener(e -> xuLyXoa());
		btnCapNhat.addActionListener(e -> {
			try {
				xuLyCapNhat();
			} catch (ParseException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		});
		btnThoat.addActionListener(e -> System.exit(0));
		btnTim.addActionListener(e -> xuLyTimKiem());
		btnLamMoi.addActionListener(e -> xuLyTraCuu());
		table.addMouseListener((MouseListener) this);

		//String[] row = { "NV001", "tenNV", "06/11/2023", "", "sdt", "cccd", "", "matkhau", "" };
		//tableModel.addRow(row);
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
			String maNV = maNhanVien.formatMa(dsNV.get(dsNV.size()-1).getMaNV());
			try {
				dsNV.add(new entity.NhanVien(maNV, tenNV, dateFormat.parse(namSinh), false, sdt, cccd, chucVu, matKhau, false));
			} catch (NumberFormatException e) {
				e.printStackTrace();
			} catch (ParseException e) {
				e.printStackTrace();
			}
			String[] row = { maNV, tenNV, namSinh, gioiTinh, sdt, cccd, chucVu, matKhau, "Đang làm" };
			tableModel.addRow(row);
			JOptionPane.showMessageDialog(null, "Thêm mới nhân viên thành công!");
		}
		}else {
			JOptionPane.showMessageDialog(null, "Vui lòng nhập đầy đủ thông tin nhân viên!");
		}
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
			String maNV = (String) table.getValueAt(table.getSelectedRow(),0);
			int viTri = table.getSelectedRow();
			tableModel.removeRow(viTri);
			String[] row = { maNV, tenNV, namSinh, gioiTinh, sdt, cccd, chucVu, matKhau, "Đang làm" };
			try {
			daoNV.updateNhanVien(new entity.NhanVien(maNV, tenNV, dateFormat.parse(namSinh), false, sdt, cccd, chucVu, matKhau, false));
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
					tableModel.addRow(new Object[] { nv.getMaNV(), nv.getTenNV(), nv.getNamSinh(), gioiTinh,
							nv.getSdthoai(), nv.getCccd(), nv.getChucVu(), nv.getMatKhau(), tinhTrang});

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
		txtTenNhanVien.setText(table.getValueAt(row, 1).toString());
		Date date = null;
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