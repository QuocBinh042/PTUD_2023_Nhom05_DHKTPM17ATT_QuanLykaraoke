package app;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
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
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
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
	private JTextArea txaMoTa, txaMoTa2;
	private JTextField txtMaKM, txtPhanTramGiam, txtNgayBatDau, txtNgayKetThuc, txtThongBaoLoi, txtTimKM, txtTimPhong,
			txtTrangThai;
	private JDateChooser dateBD, dateKT, dateBDTim, dateKTTim;
	private app.ButtonGradient btnThemMoi, btnCapNhat, btnXoa, btnLamMoi, btnThoat, btnTimKM;
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
		// Khai báo
		Icon img_add = new ImageIcon("src/img/add16.png");
		Icon img_del = new ImageIcon("src/img/bin.png");
		Icon img_reset = new ImageIcon("src/img/refresh16.png");
		Icon img_edit = new ImageIcon("src/img/edit16.png");
		Icon img_out = new ImageIcon("src/img/out.png");
		Icon img_search = new ImageIcon("src/img/search.png");

		Border line = BorderFactory.createLineBorder(Color.BLACK);
		Box b1, b2, b3, b4, b5;
		Box bLeft, bRight, bLoc, bTacVu;
		Dimension dimension = new Dimension(170, 25);

		JPanel pnlLeft = new JPanel();
		JPanel pnlRight = new JPanel();

		bLeft = Box.createVerticalBox();
		bRight = Box.createVerticalBox();
		pnlLeft.add(bLeft);
		pnlRight.add(bRight);

		pnlLeft.setBorder(BorderFactory.createTitledBorder(line, "Thông tin khuyến mãi"));
		bLeft.add(Box.createVerticalStrut(5));
		JPanel pnlPhanTramGiam = new JPanel();
		pnlPhanTramGiam.setBackground(Color.decode("#cccccc"));
		pnlPhanTramGiam.add(lblPhanTramGiam = new JLabel("Phần trăm giảm"));
		pnlPhanTramGiam.add(txtPhanTramGiam = new JTextField(15));
		bLeft.add(b1 = Box.createHorizontalBox());
		b1.add(pnlPhanTramGiam);
		bLeft.add(Box.createVerticalStrut(10));
		JPanel pnlNgayBatDau = new JPanel();
		pnlNgayBatDau.setBackground(Color.decode("#cccccc"));
		pnlNgayBatDau.add(lblNgayBatDau = new JLabel("Ngày bắt đầu"));
		pnlNgayBatDau.add(dateBD = new JDateChooser());
		dateBD.setPreferredSize(dimension);
		bLeft.add(b2 = Box.createHorizontalBox());
		b2.add(pnlNgayBatDau);
		bLeft.add(Box.createVerticalStrut(10));
		JPanel pnlNgayKetThuc = new JPanel();
		pnlNgayKetThuc.setBackground(Color.decode("#cccccc"));
		pnlNgayKetThuc.add(lblNgayKetThuc = new JLabel("Ngày kết thúc"));
		pnlNgayKetThuc.add(dateKT = new JDateChooser());
		dateKT.setPreferredSize(dimension);
		bLeft.add(b3 = Box.createHorizontalBox());
		b3.add(pnlNgayKetThuc);
		bLeft.add(Box.createVerticalStrut(10));
		JPanel pnlMoTa = new JPanel();
		pnlMoTa.setBackground(Color.decode("#cccccc"));
		pnlMoTa.add(lblMoTa = new JLabel("Mô tả"));
		pnlMoTa.add(txaMoTa = new JTextArea(5, 15));
		txaMoTa.setBorder(line);
		bLeft.add(b4 = Box.createHorizontalBox());
		b4.add(pnlMoTa);
		bLeft.add(Box.createVerticalStrut(10));

		lblNgayBatDau.setPreferredSize(lblPhanTramGiam.getPreferredSize());
		lblNgayKetThuc.setPreferredSize(lblPhanTramGiam.getPreferredSize());
		lblMoTa.setPreferredSize(lblPhanTramGiam.getPreferredSize());

		bLeft.add(Box.createVerticalStrut(50));
		JPanel pnlKM = new JPanel();
		pnlKM.setBackground(Color.decode("#cccccc"));
		GridLayout gridKM = new GridLayout(2, 2);
		pnlKM.setLayout(gridKM);
		gridKM.setHgap(20);
		gridKM.setVgap(20);
		pnlKM.add(btnThemMoi = new ButtonGradient("Thêm mới", img_add));
		pnlKM.add(btnCapNhat = new ButtonGradient("Cập nhật", img_edit));
		pnlKM.add(btnXoa = new ButtonGradient("Xóa", img_del));
		pnlKM.add(btnLamMoi = new ButtonGradient("Làm mới", img_reset));
		bLeft.add(pnlKM);
		bLeft.add(Box.createVerticalStrut(20));
		bLeft.add(b5 = Box.createHorizontalBox());
		b5.add(btnThoat = new ButtonGradient("Thoát", img_out));
		btnThoat.setMaximumSize(new Dimension(Integer.MAX_VALUE, btnThoat.getMinimumSize().height));

		// Set size
		bLeft.setMaximumSize(new Dimension(Integer.MAX_VALUE, bLeft.getPreferredSize().height));

		// Right box
		// Tac vu khuyen mai
		bTacVu = Box.createHorizontalBox();
		JPanel pnlLocKM = new JPanel();
		pnlLocKM.setBorder(BorderFactory.createTitledBorder(line, "Lọc theo ngày"));
		pnlLocKM.setBackground(Color.decode("#cccccc"));
		JPanel pnlTimKM = new JPanel();
		pnlTimKM.setBackground(Color.decode("#e6dbd1"));
		bTacVu.add(pnlLocKM);
		bTacVu.add(pnlTimKM);
		bRight.add(bTacVu);

		GridLayout gridLocKM = new GridLayout(1, 2);
		pnlLocKM.setLayout(gridLocKM);
		gridLocKM.setHgap(5);
		Box bNgayBD = Box.createHorizontalBox();

		bNgayBD.add(lblNgayBatDau = new JLabel("Từ ngày"));
		bNgayBD.add(Box.createHorizontalStrut(5));
		bNgayBD.add(dateBDTim = new JDateChooser());
		bNgayBD.add(Box.createHorizontalStrut(10));
		pnlLocKM.add(bNgayBD);
		Box bNgayKT = Box.createHorizontalBox();
		bNgayKT.add(lblNgayKetThuc = new JLabel("Đến"));
		bNgayKT.add(Box.createHorizontalStrut(5));
		bNgayKT.add(dateKTTim = new JDateChooser());
		pnlLocKM.add(bNgayKT);

		pnlTimKM.add(lblMaKM = new JLabel("Mã khuyến mãi"));
		pnlTimKM.add(txtTimKM = new JTextField(10));
		pnlTimKM.add(btnTimKM = new ButtonGradient("Tìm", img_search));

		// Table
		Box table1 = Box.createVerticalBox();
		table1.setBorder(BorderFactory.createTitledBorder(line, "Danh sách khuyến mãi"));
		tableModel = new DefaultTableModel(headers, 0);
		JScrollPane scroll = new JScrollPane(table, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scroll.setViewportView(table = new JTable(tableModel));
		table.setRowHeight(25);
		table.setAutoCreateRowSorter(true);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		table1.add(scroll);

		// set color
		pnlLeft.setBackground(Color.decode("#cccccc"));
		pnlRight.setBackground(Color.decode("#e6dbd1"));
		pnlRight.setLayout(new BorderLayout());
		pnlRight.add(bTacVu, BorderLayout.NORTH);
		pnlRight.add(table1, BorderLayout.CENTER);

		this.setLayout(new BorderLayout());
		this.add(pnlLeft, BorderLayout.WEST);
		this.add(pnlRight, BorderLayout.CENTER);

		// Load data
		dsKM = daoKM.layDSKhuyenMai();
		loadData();

		// sự kiện
		btnLamMoi.addActionListener(e -> xuLyLamMoiKM());
		btnThemMoi.addActionListener(e -> xuLyThemMoiKM());
		btnXoa.addActionListener(e -> xuLyXoaKM());
		btnCapNhat.addActionListener(e -> xuLyCapNhatKM());
		btnThoat.addActionListener(e -> xuLyThoat());
		btnTimKM.addActionListener(e -> xuLyTimKM());
		table.addMouseListener(this);

	}

	private Object xuLyThoat() {
		// TODO Auto-generated method stub
		if (JOptionPane.showConfirmDialog(null, "Bạn có chắc chắc muốn thoát không ?", "Chú ý!",
				JOptionPane.YES_OPTION) == JOptionPane.YES_OPTION) {
			System.exit(0);
		}

		return null;
	}

	public void loadData() {
		// delete all
		deleteAllDataJtable();
		// Load data
	
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
//		if (maKM) {
//			JOptionPane.showMessageDialog(null, "Tìm kiếm thông tin khuyến mãi thành công!");
////			table.setRowSelectionInterval(pos, pos);
//		} else
//			JOptionPane.showMessageDialog(null, "Chương trình khuyến mãi không tồn tại!");
		return null;
	}

	private Object xuLyThemMoiKM() {
		// TODO Auto-generated method stub
		int luaChon = JOptionPane.showConfirmDialog(null, "Có chắc chắn thêm thông tin khuyến mãi không?", "Chú ý",
				JOptionPane.YES_NO_OPTION);
		if (luaChon == JOptionPane.YES_OPTION) {
			String phanTramKM = txtPhanTramGiam.getText();
			String ngayBD = dateFormat.format(dateBD.getDate());
			String ngayKT = dateFormat.format(dateKT.getDate());
			String moTa = txaMoTa.getText();
			String maKM = maKhuyenMai.formatMa(daoKM.layDSKhuyenMai().get(daoKM.layDSKhuyenMai().size() - 1).getMaKM());
			entity.KhuyenMai km = new entity.KhuyenMai(maKM, Double.valueOf(txtPhanTramGiam.getText()),
					dateBD.getDate(), dateKT.getDate(), moTa, true);
			if (daoKM.themKhuyenMai(km)) {
				String[] row = { maKM, phanTramKM, ngayBD, ngayKT, moTa, "Đang hoạt động" };
				tableModel.addRow(row);
				JOptionPane.showMessageDialog(null, "Thêm mới khuyến mãi thành công!");
			}
		}
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
		txtPhanTramGiam.setText("");
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