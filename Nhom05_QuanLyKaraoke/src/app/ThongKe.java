package app;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
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

public class ThongKe extends JPanel {
	private JTable table;
	private DefaultTableModel tableModel;
	private String[] headers = { "Mã hoá đơn", "Tên khách hàng", "Tên nhân viên", "Ngày thanh toán",
			"Tổng hoá đơn" };
	private JLabel lblTenNV, lblSđtKH, lblNgayBatDau, lblNgayKetThuc, lblLoaiPhong, lblPhong, lblTongDT, lblSoLuongHD;
	private JTextField txtPhong, txtTongDT, txtSoLuongHD;
	private JButton btnTim, btnLoc, btnLamMoi2, btnLamMoi, btnThoat, btnXemCT;
	private JDateChooser dateBD, dateKT;
	private JComboBox<String> cbLoaiPhong;
	private DAOHoaDon daoHD = new DAOHoaDon();
	private ArrayList<entity.HoaDon> dsHD = new ArrayList<>();
	private DecimalFormat formatter = new DecimalFormat("###,###,### VNĐ");
	private SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
	private CategoryDataset dataset;
	private Box b, bCenter, bTim, bTim1, bTim2;
	private JScrollPane scroll;

	public ThongKe() {
		// Khai báo
		Icon img_out = new ImageIcon("src/img/out16.png");
		Icon img_search = new ImageIcon("src/img/search.png");
		Icon img_check = new ImageIcon("src/img/check.png");
		Icon img_refresh = new ImageIcon("src/img/refresh16.png");
		Icon img_detail = new ImageIcon("src/img/detail16.png");

		Border line = BorderFactory.createLineBorder(Color.BLACK);
		Dimension dimension = new Dimension(110, 25);

		// Bố cục
		b = Box.createHorizontalBox();
		JPanel pnlThongKe = new JPanel();
		GridLayout gridThongKe = new GridLayout(2, 5);
		gridThongKe.setHgap(10);
		gridThongKe.setVgap(5);
		pnlThongKe.setLayout(gridThongKe);
		pnlThongKe.setBorder(BorderFactory.createTitledBorder(line, "Thống kê"));
		pnlThongKe.add(lblNgayBatDau = new JLabel("Ngày bắt đầu:"));
		pnlThongKe.add(dateBD = new JDateChooser());
		pnlThongKe.add(btnTim = new ButtonGradient("Tìm", img_search));
		pnlThongKe.add(lblNgayKetThuc = new JLabel("Ngày kết thúc:"));
		pnlThongKe.add(dateKT = new JDateChooser());
		pnlThongKe.add(btnLamMoi = new ButtonGradient("Làm mới", img_refresh));
		b.add(pnlThongKe);
		b.add(Box.createHorizontalStrut(100));

		//
		JPanel pnlKetQua = new JPanel();
		GridLayout gridKetQua = new GridLayout(2, 2);
		gridKetQua.setHgap(10);
		gridKetQua.setVgap(10);
		pnlKetQua.setLayout(gridKetQua);
		pnlKetQua.setBorder(BorderFactory.createTitledBorder(line, "Kết quả thống kê"));
		pnlKetQua.add(lblTongDT = new JLabel("Tổng doanh thu: "));
		pnlKetQua.add(txtTongDT = new JTextField(10));
		txtTongDT.setEditable(false);
		pnlKetQua.add(lblSoLuongHD = new JLabel("Số lượng hoá đơn: "));
		pnlKetQua.add(txtSoLuongHD = new JTextField(10));
		txtSoLuongHD.setEditable(false);
		b.add(pnlKetQua);
//		pnlKetQua.setBackground(Color.decode("#cccccc"));
		b.add(Box.createHorizontalStrut(100));
		b.add(btnThoat = new ButtonGradient("Thoát", img_out));
		b.add(Box.createHorizontalStrut(50));

		tableModel = new DefaultTableModel(headers, 0);
		scroll = new JScrollPane(table, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scroll.setBorder(BorderFactory.createTitledBorder(line, "Danh sách hoá đơn" + ""));
		scroll.setViewportView(table = new JTable(tableModel));
		table.setRowHeight(25);
		table.setAutoCreateRowSorter(true);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);

		// Create chart
		JFreeChart chart = ChartFactory.createBarChart("Biểu đồ doanh thu", "Tháng", "Doanh thu (VNĐ)", dataset,
				PlotOrientation.VERTICAL, true, true, false);
		ChartPanel panel = new ChartPanel(chart);
		bCenter = Box.createHorizontalBox();
		bCenter.add(scroll);
		bCenter.add(panel);
		this.setLayout(new BorderLayout());
		this.add(b, BorderLayout.NORTH);
		this.add(bCenter, BorderLayout.CENTER);

		// Sự kiện
		btnTim.addActionListener(e -> xuLyThongKe());
		btnLamMoi.addActionListener(e -> xuLyLamMoi());
		btnThoat.addActionListener(e -> System.exit(0));
	}

	
	private Object xuLyThongKe() {
		// TODO Auto-generated method stub
		deleteAllDataJtable();
		txtTongDT.setText(formatter.format(daoHD.ThongKeHoaDon(dateBD.getDate(), dateKT.getDate())));
		txtSoLuongHD.setText(String.valueOf(daoHD.ThongKeSoLuongHoaDon(dateBD.getDate(), dateKT.getDate())));
		dsHD = daoHD.layDSHoaDonTrongKhoangThoiGian(dateBD.getDate(), dateKT.getDate());
		for (entity.HoaDon hd : dsHD) {
			tableModel.addRow(new Object[] { hd.getMaHoaDon(), hd.getKh().getTenKH(), hd.getNv().getTenNV(),
					dateFormat.format(hd.getNgayThanhToan()), formatter.format(hd.getTongHoaDon()) });
		}
		dataset = createDataset();
		JFreeChart chart = ChartFactory.createBarChart("Biểu đồ doanh thu", "Tháng", "Doanh thu (VNĐ)", dataset,
				PlotOrientation.VERTICAL, true, true, false);
		ChartPanel panel = new ChartPanel(chart);

		bCenter.removeAll();
		bCenter.add(scroll);
		bCenter.add(panel);
		bCenter.repaint();
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

	private Object xuLyLamMoi() {
		// TODO Auto-generated method stub
		((JTextField) dateBD.getDateEditor().getUiComponent()).setText("");
		((JTextField) dateKT.getDateEditor().getUiComponent()).setText("");
		return null;
	}

}
