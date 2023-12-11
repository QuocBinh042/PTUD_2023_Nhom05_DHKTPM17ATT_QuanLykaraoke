package app;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.Timer;
import javax.swing.border.Border;
import javax.swing.table.DefaultTableModel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;

import com.toedter.calendar.JDateChooser;

import dao.DAOCTDVPhong;
import dao.DAOCTHD;
import dao.DAOHoaDon;
import entity.HoaDon;

public class PanelThongKe extends JPanel {
	private JTable tableTG, tableKH, tableP, tableDV;
	private DefaultTableModel tableModelTG, tableModelKH, tableModelP, tableModelDV;
	private String[] headersTG = { "Mã hoá đơn", "Tên khách hàng", "Tên nhân viên", "Ngày thanh toán",
			"Tổng hoá đơn" };
	private String[] headersKH = { "Mã khách hàng", "Tên khách hàng", "Số điện thoại", "Tổng hoá đơn" };
	private String[] headersP = { "Mã phòng", "Tên phòng", "Loại phòng", "Số giờ sử dụng",
			"Tổng tiền phòng" };
	private String[] headersDV = { "Mã dịch vụ", "Tên dịch vụ", "Số lượng bán", "Tổng tiền dịch vụ" };
	private JLabel lblLuaChonTG, lblDoanhThu, lblSoLuongHD, lblDoanhThuTB, lblDolar, lblDoanhThuValue,
			lblSoLuongHDValue, lblDoanhThuTBValue, lblThoiGianBD, lblThoiGianKT, lblTienPhong, lblTienDV,
			lblTienPhongValue, lblTienDVValue;
	private JTextField txtDoanhThu, txtSoLuongHD, txtDoanhThuTB;
	private JButton btnChart, btnTable, btnThoiGian, btnPhong, btnKhachHang, btnDichVu;
	private JDateChooser dateBD, dateKT, dcChonNgay, dcChonThang;
	private JComboBox<String> cbLuaChonTG;
	private JComboBox<Integer> cbChonNam;
	private DAOHoaDon daoHD = new DAOHoaDon();
	private DAOCTHD daoCTHD = new DAOCTHD();
	private DAOCTDVPhong daoCTDVPhong = new DAOCTDVPhong();
	private ArrayList<entity.HoaDon> dsHD = new ArrayList<>();
	private DecimalFormat formatter = new DecimalFormat("###,###,### VNĐ");
	private SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
	private CategoryDataset dataset;
	private JScrollPane scroll;
	private CardLayout cardLayout;
	private CardLayout cardLayoutMenu = new CardLayout();
	private JPanel pnlCardTG;
	private JPanel pnlLuaChonTK = new JPanel(new FlowLayout(FlowLayout.LEFT));
	private JPanel pnlThoiGian = new JPanel();
	private JPanel pnlMenu = new JPanel(new FlowLayout(FlowLayout.LEFT));
	private JPanel pnlCardMain;

	public PanelThongKe() {
		setLayout(new BorderLayout());
		add(createUIThongKeTheoTG(), BorderLayout.CENTER);

		thongKeTheoNgay();
		cbLuaChonTG.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				btnChart.enable(true);
				Component selectedComponent = null;
				pnlThoiGian.removeAll();
				if (cbLuaChonTG.getSelectedItem().equals("Ngày")) {
					selectedComponent = dcChonNgay;
				} else if (cbLuaChonTG.getSelectedItem().equals("Tháng")) {
					selectedComponent = dcChonThang;
				} else {
					selectedComponent = cbChonNam;
				}
				pnlThoiGian.add(selectedComponent);
			}
		});

		dcChonNgay.getDateEditor().addPropertyChangeListener(new PropertyChangeListener() {
			@Override
			public void propertyChange(PropertyChangeEvent evt) {
				thongKeTheoNgay();
				btnChart.enable(false);
			}
		});
		dcChonThang.getDateEditor().addPropertyChangeListener(new PropertyChangeListener() {
			@Override
			public void propertyChange(PropertyChangeEvent evt) {
				btnChart.enable(true);
				thongKeTheoThang();
			}
		});
		cbChonNam.addPropertyChangeListener(new PropertyChangeListener() {
			@Override
			public void propertyChange(PropertyChangeEvent evt) {
				btnChart.enable(true);
				thongKeTheoNam();
				cbChonNam.addActionListener(e -> thongKeTheoNam());
			}
		});
		btnTable.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				cardLayout.show(pnlCardTG, "TablePanel");
				btnTable.setBackground(Color.GRAY);
				btnChart.setBackground(getBackground());
			}
		});
		btnChart.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				cardLayout.show(pnlCardTG, "ChartPanel");
				btnChart.setBackground(Color.GRAY);
				btnTable.setBackground(getBackground());
			}
		});
	}

	private JPanel createUIThongKeTheoTG() {
		// Khai báo
		Icon img_search = new ImageIcon("src/img/search.png");
		Icon img_check = new ImageIcon("src/img/check.png");
		Icon img_dollar = new ImageIcon("src/img/dollar.png");
		lblDolar = new JLabel(img_dollar);
		Border line = BorderFactory.createLineBorder(Color.BLACK);
		Dimension dimension = new Dimension(300, 40);

		pnlLuaChonTK.add(lblLuaChonTG = new JLabel("Loại thời gian"));
		lblLuaChonTG.setFont(new Font("Arial", Font.BOLD, 13));
		pnlLuaChonTK.add(cbLuaChonTG = new JComboBox<>());
		cbLuaChonTG.addItem("Ngày");
		cbLuaChonTG.addItem("Tháng");
		cbLuaChonTG.addItem("Năm");
		pnlLuaChonTK.add(pnlThoiGian);
		pnlThoiGian.add(dcChonNgay = new JDateChooser());
//		pnlThoiGian.add(dcChonNgay = new JDateChooser((Date) null, "dd/MM/yyyy"));
		dcChonNgay.setDate(new Date());
		dcChonThang = new JDateChooser((Date) null, "MM/yyyy");
		dcChonThang.setDate(new Date());
		cbChonNam = new JComboBox<Integer>();
		List<Integer> dsNam = daoHD.layNamLapHoaDon();
		for (Integer string : dsNam) {
			cbChonNam.addItem(string);
		}
		pnlThoiGian.setBackground(Color.decode("#D0BAFB"));
		pnlLuaChonTK.setBackground(Color.decode("#D0BAFB"));
		dcChonNgay.setBackground(Color.decode("#D0BAFB"));
		dcChonThang.setBackground(Color.decode("#D0BAFB"));
		cbLuaChonTG.setPreferredSize(new Dimension(150, 40));
		dcChonNgay.setPreferredSize(dimension);
		dcChonThang.setPreferredSize(dimension);
		cbChonNam.setPreferredSize(dimension);
		JPanel pnlKetQua = createKetQuaPanel();
		pnlKetQua.setBorder(line);
		createTableTG();
		JPanel pnlCenter = new JPanel(new BorderLayout());
		cardLayout = new CardLayout();
		pnlCardTG = new JPanel(cardLayout);
		pnlCardTG.setBackground(Color.decode("#D0BAFB"));
		pnlCardTG.add(createChartPanel(null, ""), "ChartPanel");
		pnlCardTG.add(createTablePanel(tableTG), "TablePanel");
		pnlCenter.add(pnlCardTG, BorderLayout.CENTER);
		JPanel pnlLuaChonKQ = new JPanel(new FlowLayout(FlowLayout.LEFT));
		pnlLuaChonKQ.add(btnChart = new JButton("Biểu đồ"));
		pnlLuaChonKQ.add(btnTable = new JButton("Bảng thống kê"));
		pnlCenter.add(pnlLuaChonKQ, BorderLayout.NORTH);
		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
		mainPanel.add(pnlLuaChonTK);
		mainPanel.add(pnlKetQua);
		mainPanel.add(pnlCenter);
		return mainPanel;
	}

	private JPanel createKetQuaPanel() {
		JPanel pnlKetQua = new JPanel(new GridBagLayout());
		pnlKetQua.setBackground(Color.decode("#D0BAFB"));
		GridBagConstraints gbc = new GridBagConstraints();
		JPanel b1 = createPanelWithLabelAndTextField(lblDoanhThu = new JLabel("Tổng doanh thu"),
				lblDoanhThuValue = new JLabel(""), Color.decode("#FF005D"));
		JPanel b2 = createPanelWithLabelAndTextField(lblSoLuongHD = new JLabel("Số lượng hoá đơn"),
				lblSoLuongHDValue = new JLabel(""), Color.decode("#F4D624"));
		JPanel b3 = createPanelWithLabelAndTextField(lblTienPhong = new JLabel("Tổng tiền phòng"),
				lblTienPhongValue = new JLabel(""), Color.decode("#49D874"));
		JPanel b4 = createPanelWithLabelAndTextField(lblTienDV = new JLabel("Tổng tiền dịch vụ"),
				lblTienDVValue = new JLabel(""), Color.decode("#03A9F4"));
		Dimension componentSize = new Dimension(200, 40);
		lblDoanhThuValue.setPreferredSize(componentSize);
		lblSoLuongHDValue.setPreferredSize(componentSize);
		lblTienPhongValue.setPreferredSize(componentSize);
		lblTienDVValue.setPreferredSize(componentSize);
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.insets = new Insets(-10, 10, -10, 10);
		pnlKetQua.add(b1, gbc);
		gbc.gridx = 1;
		pnlKetQua.add(b2, gbc);
		gbc.gridx = 2;
		pnlKetQua.add(b3, gbc);
		gbc.gridx = 3;
		pnlKetQua.add(b4, gbc);
		return pnlKetQua;
	}

	private JPanel createPanelWithLabelAndTextField(JLabel label, JLabel valueLabel, Color color) {
		Icon img_dollar = new ImageIcon("src/img/dollar.png");
		JPanel panel = new JPanel(new BorderLayout());
		panel.setBackground(color);
		label.setFont(new Font("Arial", Font.BOLD, 13));
		panel.add(new JLabel(img_dollar), BorderLayout.WEST);
		JPanel textPanel = new JPanel();
		textPanel.setLayout(new BoxLayout(textPanel, BoxLayout.Y_AXIS));
		textPanel.add(Box.createVerticalStrut(10));
		textPanel.add(label);
		textPanel.add(valueLabel);
		textPanel.setBackground(color);
		valueLabel.setFont(new Font("Arial", Font.BOLD, 26));
		panel.add(textPanel, BorderLayout.CENTER);
		return panel;
	}

	private void createTableTG() {
		tableModelTG = new DefaultTableModel(headersTG, 0);
		tableTG = new JTable(tableModelTG);
		tableTG.setRowHeight(25);
		tableTG.setAutoCreateRowSorter(true);
		tableTG.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
	}

	private void createTableP() {
		tableModelP = new DefaultTableModel(headersP, 0);
		tableP = new JTable(tableModelP);
		tableP.setRowHeight(25);
		tableP.setAutoCreateRowSorter(true);
		tableP.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
	}

	private void createTableDV() {
		tableModelDV = new DefaultTableModel(headersDV, 0);
		tableDV = new JTable(tableModelDV);
		tableDV.setRowHeight(25);
		tableDV.setAutoCreateRowSorter(true);
		tableDV.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
	}

	private void createTableKH() {
		tableModelKH = new DefaultTableModel(headersKH, 0);
		tableKH = new JTable(tableModelKH);
		tableKH.setRowHeight(25);
		tableKH.setAutoCreateRowSorter(true);
		tableKH.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
	}

	private JPanel createTablePanel(JTable table) {
		JPanel pnlTable = new JPanel(new BorderLayout());
		scroll = new JScrollPane(table, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scroll.setViewportView(table);
		pnlTable.add(scroll, BorderLayout.CENTER);
		return pnlTable;
	}

	private JPanel createChartPanel(CategoryDataset dataset, String txt) {
		JPanel pnlChart = new JPanel(new BorderLayout());
		JFreeChart chart = ChartFactory.createBarChart("Biểu đồ doanh thu", txt, "Doanh thu (VNĐ)", dataset,
				PlotOrientation.VERTICAL, true, true, false);
		ChartPanel chartPanelComponent = new ChartPanel(chart);
		pnlChart.add(chartPanelComponent, BorderLayout.CENTER);
		return pnlChart;
	}

	private void thongKeTheoNgay() {
		Date date = dcChonNgay.getDate();
		Double dt = daoHD.ThongKeHoaDonTheoNgay(date);
		Integer slhd = daoHD.ThongKeSoLuongHoaDonTheoNgay(date);
		Double tienPhong = daoCTHD.ThongKeTienPhongTheoNgay(date) * 0.9;
		Double tienDV = daoCTDVPhong.ThongKeTienDVTheoNgay(date) * 0.9;
		Double dttb = dt / slhd;
		if (slhd == 0)
			dttb = 0.0;
		loadData(daoHD.layDSHoaDonTheoNgay(date), tableTG, tableModelTG);
		addKetQua(dt, slhd, tienPhong, tienDV);
		cardLayout.show(pnlCardTG, "TablePanel");
	}

	private void thongKeTheoThang() {
		Date date = dcChonThang.getDate();
		Double dt = daoHD.ThongKeHoaDonTheoThang(date);
		Integer slhd = daoHD.ThongKeSoLuongHoaDonTheoThang(date);
		Double tienPhong = daoCTHD.ThongKeTienPhongTheoThang(date) * 0.9;
		Double tienDV = daoCTDVPhong.ThongKeTienDVTheoThang(date) * 0.9;
		loadData(daoHD.layDSHoaDonTheoThang(date), tableTG, tableModelTG);
		addKetQua(dt, slhd, tienPhong, tienDV);
		pnlCardTG.add(createChartPanel(createDatasetMonth(date), "Ngày"), "ChartPanel");
		cardLayout.show(pnlCardTG, "ChartPanel");
	}

	private void thongKeTheoNam() {
		String year = cbChonNam.getSelectedItem().toString();
		Double dt = daoHD.ThongKeHoaDonTheoNam(year);
		Integer slhd = daoHD.ThongKeSoLuongHoaDonTheoNam(year);
		Double tienPhong = daoCTHD.ThongKeTienPhongTheoNam(year) * 0.9;
		Double tienDV = daoCTDVPhong.ThongKeTienDVTheoNam(year) * 0.9;
		loadData(dsHD = daoHD.layDSHoaDonTheoNam(Integer.valueOf(year)), tableTG, tableModelTG);
		addKetQua(dt, slhd, tienPhong, tienDV);
		pnlCardTG.add(createChartPanel(createDatasetYear(Integer.valueOf(year)), "Tháng"), "ChartPanel");
		cardLayout.show(pnlCardTG, "ChartPanel");
	}

	private CategoryDataset createDatasetMonth(Date date) {
		// TODO Auto-generated method stub
		DefaultCategoryDataset dataset = new DefaultCategoryDataset();
		Map<Integer, Double> dsThongKe = new HashMap<>();
		dsThongKe = daoHD.ThongKeHoaDonThang(date);
		for (Entry<Integer, Double> entry : dsThongKe.entrySet()) {
			Integer day = entry.getKey();
			Double totalCount = entry.getValue();
			dataset.addValue(totalCount, "Doanh thu trong ngày", day);
		}
		return dataset;
	}

	private void addKetQua(Double dt, Integer slhd, Double tienPhong, Double tienDV) {
		lblDoanhThuValue.setText(formatter.format(dt));
		lblSoLuongHDValue.setText(slhd.toString());
		lblTienPhongValue.setText(formatter.format(tienPhong));
		lblTienDVValue.setText(formatter.format(tienDV));
	}

	private CategoryDataset createDatasetYear(Integer year) {
		DefaultCategoryDataset dataset = new DefaultCategoryDataset();
		Map<Integer, Double> dsThongKe = new HashMap<>();
		dsThongKe = daoHD.ThongKeHoaDonNam(year);
		for (Entry<Integer, Double> entry : dsThongKe.entrySet()) {
			Integer month = entry.getKey();
			Double totalCount = entry.getValue();
			dataset.addValue(totalCount, "Doanh thu trong tháng", month);
		}
		return dataset;
	}

	private void deleteAllDataJtable(JTable table) {
		DefaultTableModel dm = (DefaultTableModel) table.getModel();
		while (dm.getRowCount() > 0) {
			dm.removeRow(0);
		}
	}

	private void loadData(ArrayList<HoaDon> ds, JTable table, DefaultTableModel tableModel) {
		deleteAllDataJtable(table);
		Collections.sort(ds, Comparator.comparing(HoaDon::getTongHoaDon, Comparator.reverseOrder()));
		for (entity.HoaDon hd : ds) {
			tableModel.addRow(new Object[] { hd.getMaHoaDon(), hd.getKh().getTenKH(), hd.getNv().getTenNV(),
					dateFormat.format(hd.getNgayThanhToan()), formatter.format(hd.getTongHoaDon()) });
		}
	}

}
