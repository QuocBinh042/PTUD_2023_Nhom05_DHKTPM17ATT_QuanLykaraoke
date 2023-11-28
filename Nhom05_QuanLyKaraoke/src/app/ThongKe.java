package app;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Component;
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
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.table.DefaultTableModel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;

import com.toedter.calendar.JDateChooser;

import dao.DAOHoaDon;
import entity.HoaDon;

public class ThongKe extends JPanel {
	private JTable table;
	private DefaultTableModel tableModel;
	private String[] headers = { "Mã hoá đơn", "Tên khách hàng", "Tên nhân viên", "Ngày thanh toán",
			"Tổng hoá đơn" };
	private JLabel lblLuaChonTG, lblDoanhThu, lblSoLuongHD, lblDoanhThuTB, lblDolar, lblDoanhThuValue,
			lblSoLuongHDValue, lblDoanhThuTBValue;
	private JTextField txtDoanhThu, txtSoLuongHD, txtDoanhThuTB;
	private JButton btnChart, btnTable;
	private JDateChooser dateBD, dateKT, dcChonNgay, dcChonThang;
	private JComboBox<String> cbLuaChonTG;
	private JComboBox<Integer> cbChonNam;
	private DAOHoaDon daoHD = new DAOHoaDon();
	private ArrayList<entity.HoaDon> dsHD = new ArrayList<>();
	private DecimalFormat formatter = new DecimalFormat("###,###,### VNĐ");
	private SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
	private CategoryDataset dataset;
	private JScrollPane scroll;
	private CardLayout cardLayout;
	private JPanel cardPanel;
	private JPanel pnlLuaChonTK = new JPanel(new FlowLayout(FlowLayout.LEFT));
	private JPanel pnlThoiGian = new JPanel();

	public ThongKe() {
		createUI();
		thongKeTheoNgay();
		// Sự kiện
		cbLuaChonTG.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
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
			}
		});
		dcChonThang.getDateEditor().addPropertyChangeListener(new PropertyChangeListener() {
			@Override
			public void propertyChange(PropertyChangeEvent evt) {
				thongKeTheoThang();
			}
		});
		cbChonNam.addActionListener(e -> thongKeTheoNam());
		btnTable.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				cardLayout.show(cardPanel, "TablePanel");

			}
		});
		btnChart.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				cardLayout.show(cardPanel, "ChartPanel");
			}
		});

	}

	private void createUI() {
		// Khai báo
		Icon img_search = new ImageIcon("src/img/search.png");
		Icon img_check = new ImageIcon("src/img/check.png");
		Icon img_dollar = new ImageIcon("src/img/dollar.png");
		lblDolar = new JLabel(img_dollar);

		Border line = BorderFactory.createLineBorder(Color.BLACK);
		Dimension dimension = new Dimension(300, 40);
		pnlLuaChonTK.add(lblLuaChonTG = new JLabel("Thời gian thống kê"));
		lblLuaChonTG.setFont(new Font("Arial", Font.BOLD, 13));
		pnlLuaChonTK.add(cbLuaChonTG = new JComboBox<>());
		cbLuaChonTG.addItem("Ngày");
		cbLuaChonTG.addItem("Tháng");
		cbLuaChonTG.addItem("Năm");
		pnlLuaChonTK.add(pnlThoiGian);
		pnlThoiGian.add(dcChonNgay = new JDateChooser());
//		pnlThoiGian.add(dcChonNgay = new JDateChooser((Date) null, "dd/MM/yyyy"));
		dcChonNgay.setDate(new Date());
		cbChonNam = new JComboBox<Integer>();
		List<Integer> dsNam = daoHD.layNamLapHoaDon();
		for (Integer string : dsNam) {
			cbChonNam.addItem(string);
		}
		dcChonThang = new JDateChooser((Date) null, "MM/yyyy");

		cbLuaChonTG.setPreferredSize(new Dimension(150, 40));
		dcChonNgay.setPreferredSize(dimension);
		dcChonThang.setPreferredSize(dimension);
		cbChonNam.setPreferredSize(dimension);

		JPanel pnlKetQua = createKetQuaPanel();
		pnlKetQua.setBorder(line);
		JPanel pnlCenter = new JPanel(new BorderLayout());
		cardLayout = new CardLayout();
		cardPanel = new JPanel(cardLayout);
		cardPanel.add(createChartPanel(), "ChartPanel");
		cardPanel.add(createTablePanel(), "TablePanel");
		pnlCenter.add(cardPanel, BorderLayout.CENTER);
		JPanel pnlLuaChonKQ = new JPanel(new FlowLayout(FlowLayout.LEFT));
		pnlLuaChonKQ.add(btnChart = new JButton("Biểu đồ"));
		pnlLuaChonKQ.add(btnTable = new JButton("Bảng thống kê"));
		pnlCenter.add(pnlLuaChonKQ, BorderLayout.NORTH);

		// Create the main panel
		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
		mainPanel.add(pnlLuaChonTK);
		mainPanel.add(pnlKetQua); // Add pnlKetQua here
		mainPanel.add(pnlCenter);
		this.setLayout(new BorderLayout());
		this.add(mainPanel, BorderLayout.CENTER);
	}

	private JPanel createKetQuaPanel() {
		JPanel pnlKetQua = new JPanel(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		JPanel b1 = createPanelWithLabelAndTextField(lblDoanhThu = new JLabel("Tổng doanh thu"),
				lblDoanhThuValue = new JLabel(""), Color.RED);
		JPanel b2 = createPanelWithLabelAndTextField(lblSoLuongHD = new JLabel("Số lượng hoá đơn"),
				lblSoLuongHDValue = new JLabel(""), Color.GREEN);
		JPanel b3 = createPanelWithLabelAndTextField(lblDoanhThuTB = new JLabel("Doanh thu trung bình"),
				lblDoanhThuTBValue = new JLabel(""), Color.BLUE);
		Dimension componentSize = new Dimension(250, 40);
		lblDoanhThuValue.setPreferredSize(componentSize);
		lblSoLuongHDValue.setPreferredSize(componentSize);
		lblDoanhThuTBValue.setPreferredSize(componentSize);
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.insets = new Insets(0, 25, 0, 25); // Adjust the insets as needed
		pnlKetQua.add(b1, gbc);
		gbc.gridx = 1;
		pnlKetQua.add(b2, gbc);
		gbc.gridx = 2;
		pnlKetQua.add(b3, gbc);
		return pnlKetQua;
	}

	private JPanel createPanelWithLabelAndTextField(JLabel label, JLabel valueLabel, Color color) {
		Icon img_dollar = new ImageIcon("src/img/dollar.png");
		JPanel panel = new JPanel(new BorderLayout());
		panel.setBackground(color); // Set background color for the JPanel
		label.setFont(new Font("Arial", Font.BOLD, 13));
		panel.add(new JLabel(img_dollar), BorderLayout.WEST);

		JPanel textPanel = new JPanel();
		textPanel.setLayout(new BoxLayout(textPanel, BoxLayout.Y_AXIS));
		textPanel.add(Box.createVerticalStrut(10));
		textPanel.add(label);
		textPanel.add(valueLabel);
		textPanel.setBackground(color);
		valueLabel.setFont(new Font("Arial", Font.BOLD, 25));
		panel.add(textPanel, BorderLayout.CENTER);

		return panel;
	}

	private JPanel createTablePanel() {
		JPanel pnlTable = new JPanel(new BorderLayout());
		tableModel = new DefaultTableModel(headers, 0);
		scroll = new JScrollPane(table, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scroll.setViewportView(table = new JTable(tableModel));
		table.setRowHeight(25);
		table.setAutoCreateRowSorter(true);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		pnlTable.add(scroll, BorderLayout.CENTER);
		return pnlTable;
	}

	private JPanel createChartPanel() {
		JPanel pnlChart = new JPanel(new BorderLayout());
		JFreeChart chart = ChartFactory.createBarChart("Biểu đồ doanh thu", "Tháng", "Doanh thu (VNĐ)", dataset,
				PlotOrientation.VERTICAL, true, true, false);
		ChartPanel chartPanelComponent = new ChartPanel(chart);
		pnlChart.add(chartPanelComponent, BorderLayout.CENTER);
		return pnlChart;
	}

	private void thongKeTheoNgay() {
		Double dt = daoHD.ThongKeHoaDonTheoNgay(dcChonNgay.getDate());
		Integer slhd = daoHD.ThongKeSoLuongHoaDonTheoNgay(dcChonNgay.getDate());
		Double dttb = dt/slhd;
		addKetQua(dt, slhd, dttb);
	}

	private void thongKeTheoThang() {
		Double dt = daoHD.ThongKeHoaDonTheoThang(dcChonThang.getDate());
		Integer slhd = daoHD.ThongKeSoLuongHoaDonTheoThang(dcChonThang.getDate());
		Double dttb = dt/slhd;
		addKetQua(dt, slhd, dttb);
	}

	private void thongKeTheoNam() {
		Double dt = daoHD.ThongKeHoaDonTheoNam(cbChonNam.getSelectedItem().toString());
		Integer slhd = daoHD.ThongKeSoLuongHoaDonTheoNam(cbChonNam.getSelectedItem().toString());
		Double dttb = dt/slhd;
		addKetQua(dt, slhd, dttb);
	}

	private void addKetQua(Double dt, Integer slhd, Double dttb) {
		lblDoanhThuValue.setText(formatter.format(dt));
		lblSoLuongHDValue.setText(slhd.toString());
		lblDoanhThuTBValue.setText(formatter.format(dttb));
	}
	private Object xuLyThongKe() {
		// TODO Auto-generated method stub
		deleteAllDataJtable();
		txtDoanhThu.setText(formatter.format(daoHD.ThongKeHoaDon(dateBD.getDate(), dateKT.getDate())));
		txtSoLuongHD.setText(String.valueOf(daoHD.ThongKeSoLuongHoaDon(dateBD.getDate(), dateKT.getDate())));
		dsHD = daoHD.layDSHoaDonTrongKhoangThoiGian(dateBD.getDate(), dateKT.getDate());
		loadData(dsHD);
		dataset = createDataset();
		JFreeChart chart = ChartFactory.createBarChart("Biểu đồ doanh thu", "Tháng", "Doanh thu (VNĐ)", dataset,
				PlotOrientation.VERTICAL, true, true, false);
		ChartPanel panel = new ChartPanel(chart);

//		bCenter.removeAll();
//		bCenter.add(scroll);
//		bCenter.add(panel);
//		bCenter.repaint();
		return null;
	}

	private CategoryDataset createDataset() {
		DefaultCategoryDataset dataset = new DefaultCategoryDataset();
		Map<String, Double> dsThongKe = new HashMap<>();
		dsThongKe = daoHD.ThongKeHoaDonThang(dateBD.getDate(), dateKT.getDate());
		for (Map.Entry<String, Double> entry : dsThongKe.entrySet()) {
			String month = entry.getKey();
			Double totalCount = entry.getValue();
			dataset.addValue(totalCount, "Doanh thu trong tháng", month);
		}
		return dataset;
	}

	private void deleteAllDataJtable() {
		DefaultTableModel dm = (DefaultTableModel) table.getModel();
		while (dm.getRowCount() > 0) {
			dm.removeRow(0);
		}

	}

	private void loadData(ArrayList<HoaDon> ds) {
		for (entity.HoaDon hd : ds) {
			tableModel.addRow(new Object[] { hd.getMaHoaDon(), hd.getKh().getTenKH(), hd.getNv().getTenNV(),
					dateFormat.format(hd.getNgayThanhToan()), formatter.format(hd.getTongHoaDon()) });
		}
	}

	private Object xuLyLamMoi() {
		// TODO Auto-generated method stub
		((JTextField) dateBD.getDateEditor().getUiComponent()).setText("");
		((JTextField) dateKT.getDateEditor().getUiComponent()).setText("");
		return null;
	}

}
