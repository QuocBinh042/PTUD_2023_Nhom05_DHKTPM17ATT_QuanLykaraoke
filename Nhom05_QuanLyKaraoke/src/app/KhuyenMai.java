package app;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.Border;
import javax.swing.table.DefaultTableModel;

import com.toedter.calendar.JDateChooser;

import connectDB.ConnectDB;
import dao.DAOKhuyenMai;
import dao.MaTuDong;

public class KhuyenMai extends JPanel implements MouseListener {
	private JTable table;
	private DefaultTableModel tableModel;
	private String[] headers = { "Mã khuyến mãi", "Phần trăm giảm", "Ngày bắt đầu", "Ngày kết thúc", "Mô tả",
			"Trạng thái" };
	private JLabel lblMaKM, lblPhanTramGiam, lblNgayBatDau, lblNgayKetThuc, lblTrangThai, lblMoTa;
	private JTextArea txaMoTa;
	private JTextField txtMaKM, txtPhanTramGiam, txtTimKM, txtTrangThai;
	private JDateChooser dateBD, dateKT, dateBDTim, dateKTTim;
	private app.ButtonGradient btnThemMoi, btnCapNhat, btnXoa, btnLamMoi, btnTimKM, btnLuu;
	private SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
	private DAOKhuyenMai daoKM = new DAOKhuyenMai();
	private ArrayList<entity.KhuyenMai> dsKM = new ArrayList<>();
	private MaTuDong maKhuyenMai = new MaTuDong();

	public KhuyenMai() {
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

		createUI();
		loadData();
		// sự kiện
		btnLamMoi.addActionListener(e -> xuLyLamMoiKM());
		btnThemMoi.addActionListener(e -> xuLyThemMoiKM());
		btnLuu.addActionListener(e -> xuLyLuu());
		btnXoa.addActionListener(e -> xuLyXoaKM());
		btnCapNhat.addActionListener(e -> xuLyCapNhatKM());
		btnTimKM.addActionListener(e -> xuLyTimKM());
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

		// Thông tin khuyến mãi
		JPanel pnlInput = new JPanel();
		pnlInput.setLayout(new GridLayout(3, 2, 40, 0));
		pnlInput.add(createPanel(lblMaKM = new JLabel("Mã khuyến mãi"), txtMaKM = new JTextField()));
		pnlInput.add(createPanel(lblPhanTramGiam = new JLabel("Phần trăm giảm"), txtPhanTramGiam = new JTextField()));
		pnlInput.add(createPanel(lblNgayBatDau = new JLabel("Ngày bắt đầu"), dateBD = new JDateChooser()));
		pnlInput.add(createPanel(lblTrangThai = new JLabel("Trạng thái"), txtTrangThai = new JTextField()));
		pnlInput.add(createPanel(lblNgayKetThuc = new JLabel("Ngày kết thúc"), dateKT = new JDateChooser()));
		pnlInput.add(createPanel(lblMoTa = new JLabel("Mô tả"), txaMoTa = new JTextArea()));

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
		txtMaKM.setPreferredSize(dimension);
		txtPhanTramGiam.setPreferredSize(dimension);
		dateBD.setPreferredSize(dimension);
		dateKT.setPreferredSize(dimension);
		txtTrangThai.setPreferredSize(dimension);
		txaMoTa.setPreferredSize(dimension);
		lblMaKM.setPreferredSize(lblPhanTramGiam.getPreferredSize());
		lblTrangThai.setPreferredSize(lblPhanTramGiam.getPreferredSize());
		lblNgayBatDau.setPreferredSize(lblPhanTramGiam.getPreferredSize());
		lblNgayKetThuc.setPreferredSize(lblPhanTramGiam.getPreferredSize());
		lblMoTa.setPreferredSize(lblPhanTramGiam.getPreferredSize());
		int preferredWidth = 300;
		Dimension preferredSize = new Dimension(preferredWidth, pnlChucNang.getPreferredSize().height);
		pnlChucNang.setPreferredSize(preferredSize);

		// Tìm
		Box bLoc1, bLoc2;
		JPanel pnlTimKM = new JPanel();
		JPanel pnlLoc = new JPanel(new GridLayout(1, 2, 20, 0));
		bLoc1 = Box.createHorizontalBox();
		bLoc1.add(lblNgayBatDau = new JLabel("Từ ngày"));
		bLoc1.add(Box.createHorizontalStrut(10));
		bLoc1.add(dateBDTim = new JDateChooser());
		bLoc2 = Box.createHorizontalBox();
		bLoc2.add(lblNgayKetThuc = new JLabel("Đến ngày"));
		bLoc2.add(Box.createHorizontalStrut(10));
		bLoc2.add(dateKTTim = new JDateChooser());
		pnlLoc.add(bLoc1);
		pnlLoc.add(bLoc2);
		pnlTimKM.add(lblMaKM = new JLabel("Mã khuyến mãi"));
		pnlTimKM.add(txtTimKM = new JTextField(15));
		pnlTimKM.add(btnTimKM = new ButtonGradient("Tìm kiếm", img_search));
		JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, pnlLoc, pnlTimKM);
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
		txtMaKM.setEnabled(false);
		txtTrangThai.setEnabled(false);

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

	private void LocKMTheoNgay() {
		// TODO Auto-generated method stub
		if (dateKTTim.getDate() != null) {
			deleteAllDataJtable();
			dsKM = daoKM.layDSKhuyenMaiTrongKhoangThoiGian(dateBDTim.getDate(), dateKTTim.getDate());
			for (entity.KhuyenMai km : dsKM) {
				String trangThai = km.getTrangThai() ? "Đang hoạt động" : "Đã kết thúc";
				tableModel.addRow(new Object[] { km.getMaKM(), km.getPhanTramGiam(), dateFormat.format(km.getNgayBD()),
						dateFormat.format(km.getNgayKT()), km.getMoTa(), trangThai });
			}
		}
	}

	public void loadData() {
		deleteAllDataJtable();
		dsKM = daoKM.layDSKhuyenMai();
		for (entity.KhuyenMai km : dsKM) {
			String trangThai = km.getTrangThai() ? "Đang hoạt động" : "Đã kết thúc";
			tableModel.addRow(new Object[] { km.getMaKM(), km.getPhanTramGiam(), dateFormat.format(km.getNgayBD()),
					dateFormat.format(km.getNgayKT()), km.getMoTa(), trangThai });
		}
	}

	private void deleteAllDataJtable() {
		DefaultTableModel dm = (DefaultTableModel) table.getModel();
		while (dm.getRowCount() > 0) {
			dm.removeRow(0);
		}
	}

	private boolean kiemTraDuLieu() {
		return false;
	}

	private Object xuLyTimKM() {
		// TODO Auto-generated method stub
		String maKM = txtTimKM.getText();
		int rowIndex = -1;
		for (int i = 0; i < table.getRowCount(); i++) {
			if (table.getValueAt(i, 0).equals(maKM)) {
				rowIndex = i;
				break;
			}
		}
		if (rowIndex != -1) {
			table.setRowSelectionInterval(rowIndex, rowIndex);
			JOptionPane.showMessageDialog(null, "Tìm kiếm thông tin khuyến mãi thành công!");
		} else {
			JOptionPane.showMessageDialog(null, "Chương trình khuyến mãi không tồn tại!");
		}
//		if (daoKM.layKhuyenMaiTheoMa(maKM)!=null) {
//		JOptionPane.showMessageDialog(null, "Tìm kiếm thông tin khuyến mãi thành công!");
////		table.setRowSelectionInterval(pos, pos);
//	} else
//		JOptionPane.showMessageDialog(null, "Chương trình khuyến mãi không tồn tại!");
		return null;
	}

	private Object xuLyLuu() {
		// TODO Auto-generated method stub
		int luaChon = JOptionPane.showConfirmDialog(null, "Có chắc chắn thêm thông tin khuyến mãi không?", "Chú ý",
				JOptionPane.YES_NO_OPTION);
		if (luaChon == JOptionPane.YES_OPTION) {
			String phanTramKM = txtPhanTramGiam.getText();
			String ngayBD = dateFormat.format(dateBD.getDate());
			String ngayKT = dateFormat.format(dateKT.getDate());
			String moTa = txaMoTa.getText();
			String maKM = txtMaKM.getText();
			entity.KhuyenMai km = new entity.KhuyenMai(maKM, Double.valueOf(txtPhanTramGiam.getText()),
					dateBD.getDate(), dateKT.getDate(), moTa, true);
			if (daoKM.themKhuyenMai(km)) {
				String[] row = { maKM, phanTramKM, ngayBD, ngayKT, moTa, "Đang hoạt động" };
				tableModel.addRow(row);
				JOptionPane.showMessageDialog(null, "Thêm mới khuyến mãi thành công!");
			}
		}
		xuLyLamMoiKM();
		return null;
	}

	private Object xuLyThemMoiKM() {
		// TODO Auto-generated method stub
		String maKM = maKhuyenMai.formatMa(daoKM.layDSKhuyenMai().get(daoKM.layDSKhuyenMai().size() - 1).getMaKM());
		txtMaKM.setText(maKM);
		return null;
	}

	private Object xuLyCapNhatKM() {
		// TODO Auto-generated method stub
		int luaChon = JOptionPane.showConfirmDialog(null, "Có chắc chắn cập nhật thông tin khuyến mãi không?",
				"Chú ý", JOptionPane.YES_NO_OPTION);
		if (luaChon == JOptionPane.YES_OPTION) {
			String phanTramKM = txtPhanTramGiam.getText();
			String ngayBD = dateFormat.format(dateBD.getDate());
			String ngayKT = dateFormat.format(dateKT.getDate());
			String moTa = txaMoTa.getText();
			String maKM = table.getValueAt(table.getSelectedRow(), 0).toString();
			Boolean trangThai = dateKT.getDate().before(new Date()) ? true : false;
			entity.KhuyenMai km = new entity.KhuyenMai(maKM, Double.valueOf(txtPhanTramGiam.getText()),
					dateBD.getDate(), dateKT.getDate(), moTa, true);
			if (daoKM.capNhat(km)) {
				int viTri = table.getSelectedRow();
				tableModel.removeRow(viTri);
				String tt = dateKT.getDate().before(new Date()) ? "Đang hoạt động" : "Đã kết thúc";
				String[] row = { maKM, phanTramKM, ngayBD, ngayKT, moTa, tt };
				tableModel.insertRow(viTri, row);
				JOptionPane.showMessageDialog(null, "Cập nhật khuyến mãi thành công!");
			} else {
				JOptionPane.showMessageDialog(null, "Cập nhật khuyến mãi không thành công!");
			}
		}
		return null;
	}

	private Object xuLyXoaKM() {
		// TODO Auto-generated method stub
		int row = table.getSelectedRow();
		int i = JOptionPane.showConfirmDialog(null, "Có chắc chắn xóa thông tin khuyến mãi không?", "Chú ý",
				JOptionPane.YES_NO_OPTION);
		if (row != -1) {
			if (i == JOptionPane.YES_OPTION) {
				daoKM.xoaKM(table.getValueAt(row, 0).toString());
				tableModel.removeRow(row);
				JOptionPane.showMessageDialog(null, "Xóa khuyến mãi thành công!");
			}
		} else {
			JOptionPane.showMessageDialog(null, "Vui lòng chọn khuyến mãi cần xóa!");
		}
		return null;
	}

	private Object xuLyLamMoiKM() {
		// TODO Auto-generated method stub
		txtMaKM.setText("");
		txtPhanTramGiam.setText("");
		txtTrangThai.setText("");
		((JTextField) dateBD.getDateEditor().getUiComponent()).setText("");
		((JTextField) dateKT.getDateEditor().getUiComponent()).setText("");
		txaMoTa.setText("");
		return null;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		int row = table.getSelectedRow();
		txtMaKM.setText(table.getValueAt(row, 0).toString());
		txtPhanTramGiam.setText(table.getValueAt(row, 1).toString());
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		Date date1, date2;
		try {
			date1 = dateFormat.parse(table.getValueAt(row, 2).toString());
			dateBD.setDate(date1);
			date2 = dateFormat.parse(table.getValueAt(row, 3).toString());
			dateKT.setDate(date2);
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		txaMoTa.setText(table.getValueAt(row, 4).toString());
		txtTrangThai.setText(table.getValueAt(row, 5).toString());

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