package app;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.Border;
import javax.swing.table.DefaultTableModel;

import com.toedter.calendar.JDateChooser;

import dao.DAOHoaDon;

public class HoaDon extends JPanel {
	private JTable table;
	private DefaultTableModel tableModel;
	private String[] headers = { "Mã hoá đơn", "Ngày thanh toán", "Giờ thanh toán", "Tên nhân viên",
			"Tên khách hàng", "Số điện thoại khách", "Tổng hoá đơn" };
	private JLabel lblTenNV, lblSđtKH, lblNgayBatDau, lblNgayKetThuc, lblThoiGian, lblMaHD;
	private JTextField txtTimNV, txtTimKH, txtTimMaHD;
	private ButtonGradient btnTim, btnLamMoi, btnXemCT;
	private JDateChooser dateBDTim, dateKTTim;
	private DAOHoaDon daoHD = new DAOHoaDon();
	private JComboBox<String> cbLuaChon;
	private SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
	private DecimalFormat formatter = new DecimalFormat("###,###,### VNĐ");

	public HoaDon() {
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
		// Load data
		loadData(daoHD.getAllDataHD());

		// Sự kiện
		btnTim.addActionListener(e -> xuLyTimKiem());
		btnLamMoi.addActionListener(e -> xuLyLamMoi());
		cbLuaChon.addActionListener(e -> xuLyCBLuaChon());
		dateBDTim.getDateEditor().addPropertyChangeListener(new PropertyChangeListener() {
			@Override
			public void propertyChange(PropertyChangeEvent evt) {
				if (dateKTTim.getDate() != null)
					loadData(daoHD.layDSHoaDonTrongKhoangThoiGian(dateBDTim.getDate(), dateKTTim.getDate()));
			}
		});
		dateKTTim.getDateEditor().addPropertyChangeListener(new PropertyChangeListener() {
			@Override
			public void propertyChange(PropertyChangeEvent evt) {
				if (dateBDTim.getDate() != null)
					loadData(daoHD.layDSHoaDonTrongKhoangThoiGian(dateBDTim.getDate(), dateKTTim.getDate()));
			}
		});
		btnXemCT.addActionListener(e -> xuLyXemCT());

	}

	public void createUI() {
		Icon img_search = new ImageIcon("src/img/search.png");
		Icon img_refresh = new ImageIcon("src/img/refresh16.png");
		Icon img_detail = new ImageIcon("src/img/detail16.png");
		Box bThoiGian, bNgayBD, bNgayKT, bMaHD, bTenNV, bSDT, bb;
		Border line = BorderFactory.createLineBorder(Color.BLACK);
		Dimension dimension = new Dimension(180, 30);

		// NORT
		bb = Box.createHorizontalBox();
		bThoiGian = Box.createHorizontalBox();
		bNgayBD = Box.createHorizontalBox();
		bNgayKT = Box.createHorizontalBox();
		bMaHD = Box.createHorizontalBox();
		bTenNV = Box.createHorizontalBox();
		bSDT = Box.createHorizontalBox();

		bThoiGian.add(lblThoiGian = new JLabel("Thời gian"));
		bThoiGian.add(cbLuaChon = new JComboBox<>());
		cbLuaChon.addItem("Tất cả");
		cbLuaChon.addItem("Ngày hiện tại");
		cbLuaChon.addItem("Tháng hiện tại");
		cbLuaChon.addItem("Năm hiện tại");

		bNgayBD.add(lblNgayBatDau = new JLabel("Từ ngày"));
		bNgayBD.add(dateBDTim = new JDateChooser());

		bNgayKT.add(lblNgayKetThuc = new JLabel("Đến ngày"));
		bNgayKT.add(Box.createHorizontalStrut(5));
		bNgayKT.add(dateKTTim = new JDateChooser());

		bMaHD.add(lblMaHD = new JLabel("Mã hoá đơn"));
		bMaHD.add(txtTimMaHD = new JTextField(10));

		bTenNV.add(lblTenNV = new JLabel("Tên nhân viên"));
		bTenNV.add(txtTimNV = new JTextField(10));

		bSDT.add(lblSđtKH = new JLabel("Số điện thoại khách"));
		bSDT.add(Box.createHorizontalStrut(5));
		bSDT.add(txtTimKH = new JTextField(10));

		JPanel pnlChucNang = new JPanel(new GridLayout(3, 1));
		pnlChucNang.add(btnXemCT = new ButtonGradient("Xem chi tiết", img_detail));
		pnlChucNang.add(btnTim = new ButtonGradient("Tìm kiếm", img_search));
		pnlChucNang.add(btnLamMoi = new ButtonGradient("Làm mới", img_refresh));
		JPanel pnlNorth = new JPanel(new GridLayout(2, 4, 20, 10));
		pnlNorth.add(bThoiGian);
		pnlNorth.add(bNgayBD);
		pnlNorth.add(bNgayKT);
		pnlNorth.add(bMaHD);
		pnlNorth.add(bTenNV);
		pnlNorth.add(bSDT);
		pnlNorth.setBorder(BorderFactory.createTitledBorder("Tra cứu"));
		bb.add(pnlNorth);
		bb.add(Box.createHorizontalStrut(20));
		bb.add(pnlChucNang);
		bb.add(Box.createHorizontalStrut(20));

		cbLuaChon.setPreferredSize(dimension);
		dateBDTim.setPreferredSize(dimension);
		dateKTTim.setPreferredSize(dimension);
		txtTimMaHD.setPreferredSize(dimension);
		txtTimNV.setPreferredSize(dimension);
		txtTimKH.setPreferredSize(dimension);
		lblMaHD.setPreferredSize(lblSđtKH.getPreferredSize());
		lblThoiGian.setPreferredSize(lblSđtKH.getPreferredSize());
		lblNgayBatDau.setPreferredSize(lblSđtKH.getPreferredSize());
		lblNgayKetThuc.setPreferredSize(lblSđtKH.getPreferredSize());
		lblTenNV.setPreferredSize(lblSđtKH.getPreferredSize());

		// CENTER
		tableModel = new DefaultTableModel(headers, 0);
		JScrollPane scroll = new JScrollPane(table, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scroll.setBorder(BorderFactory.createTitledBorder(line, "Danh sách hoá đơn"));
		scroll.setViewportView(table = new JTable(tableModel));
		table.setRowHeight(25);
		table.setAutoCreateRowSorter(true);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);

		// Add giao diện
		this.setLayout(new BorderLayout());
		this.add(bb, BorderLayout.NORTH);
		this.add(scroll, BorderLayout.CENTER);
	}

	public void loadData(ArrayList<entity.HoaDon> dsHD) {
		// delete all
		deleteAllDataJtable();
		// Load data
		for (entity.HoaDon hd : dsHD) {
			tableModel.addRow(new Object[] { hd.getMaHoaDon(), dateFormat.format(hd.getNgayThanhToan()),
					hd.getGioThanhToan().toString(), hd.getKh().getTenKH(), hd.getNv().getTenNV(),
					hd.getKh().getSdthoai(), formatter.format(hd.getTongHoaDon()) });
		}

	}

	private void deleteAllDataJtable() {
		DefaultTableModel dm = (DefaultTableModel) table.getModel();
		while (dm.getRowCount() > 0) {
			dm.removeRow(0);
		}
	}

	private Object xuLyCBLuaChon() {
		// TODO Auto-generated method stub
		if (cbLuaChon.getSelectedItem().equals("Tất cả"))
			loadData(daoHD.getAllDataHD());
		else if (cbLuaChon.getSelectedItem().equals("Ngày hiện tại"))
			loadData(daoHD.layDSHoaDonTrongNgay());
		else if (cbLuaChon.getSelectedItem().equals("Tháng hiện tại"))
			loadData(daoHD.layDSHoaDonTheoThang());
		else if (cbLuaChon.getSelectedItem().equals("Năm hiện tại"))
			loadData(daoHD.layDSHoaDonTheoNam());
		return null;
	}

	private Object xuLyTimKiem() {
		// TODO Auto-generated method stub
		deleteAllDataJtable();
		ArrayList<entity.HoaDon> ds = daoHD.timKiemHoaDon(txtTimMaHD.getText(), txtTimNV.getText(), txtTimKH.getText());
		// Load data
		if (ds.size() > 0) {
			loadData(ds);
			JOptionPane.showMessageDialog(null, "Đã tìm thấy hoá đơn!");
		} else
			JOptionPane.showMessageDialog(null, "Không tìm thấy hoá đơn!");

		return null;
	}

	private Object xuLyXemCT() {
		// TODO Auto-generated method stub

		return null;
	}

	private Object xuLyLamMoi() {
		// TODO Auto-generated method stub
		txtTimKH.setText("");
		txtTimNV.setText("");
		txtTimMaHD.setText("");
		((JTextField) dateBDTim.getDateEditor().getUiComponent()).setText("");
		((JTextField) dateKTTim.getDateEditor().getUiComponent()).setText("");
		return null;
	}
}