package app;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;

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

public class ThongKe extends JPanel {
	private JTable table;
	private DefaultTableModel tableModel;
	private String[] headers = { "Tên phòng", "Loại phòng", "Ngày thanh toán", "Tiền phòng", "Tiền dịch vụ",
			"Tổng hoá đơn" };
	private JLabel lblTenNV, lblSđtKH, lblNgayBatDau, lblNgayKetThuc, lblLoaiPhong, lblPhong, lblTongDT, lblSoLuotSD;
	private JTextField txtPhong, txtTongDT, txtSoLuotSD;
	private JButton btnTim, btnLoc, btnLamMoi2, btnLamMoi, btnThoat, btnXemCT;
	private JDateChooser dateBD, dateKT;
	private JComboBox<String> cbLoaiPhong;

	public ThongKe() {
		// Khai báo
		Icon img_out = new ImageIcon("src/img/out16.png");
		Icon img_search = new ImageIcon("src/img/search.png");
		Icon img_check = new ImageIcon("src/img/check.png");
		Icon img_refresh = new ImageIcon("src/img/refresh16.png");
		Icon img_detail = new ImageIcon("src/img/detail16.png");
		Box b, bCenter, bTim, bTim1, bTim2;
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
		pnlThongKe.add(lblLoaiPhong = new JLabel("Loại phòng:"));
		pnlThongKe.add(cbLoaiPhong = new JComboBox<>());
		pnlThongKe.add(btnTim = new ButtonGradient("Tìm", img_search));
		pnlThongKe.add(lblNgayKetThuc = new JLabel("Ngày kết thúc:"));
		pnlThongKe.add(dateKT = new JDateChooser());
		pnlThongKe.add(lblPhong = new JLabel("Phòng:"));
		pnlThongKe.add(txtPhong = new JTextField(10));
		pnlThongKe.add(btnLamMoi = new ButtonGradient("Làm mới", img_refresh));
		b.add(pnlThongKe);
//		pnlThongKe.setBackground(Color.decode("#cccccc"));
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
		pnlKetQua.add(lblSoLuotSD = new JLabel("Số lượt sử dụng phòng: "));
		pnlKetQua.add(txtSoLuotSD = new JTextField(10));
		b.add(pnlKetQua);
//		pnlKetQua.setBackground(Color.decode("#cccccc"));
		b.add(Box.createHorizontalStrut(100));
		b.add(btnThoat = new ButtonGradient("Thoát", img_out));
		b.add(Box.createHorizontalStrut(50));

		
		tableModel = new DefaultTableModel(headers, 0);
		JScrollPane scroll = new JScrollPane(table, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scroll.setBorder(BorderFactory.createTitledBorder(line, "Danh sách phòng"));
		scroll.setViewportView(table = new JTable(tableModel));
		table.setRowHeight(50);
		table.setAutoCreateRowSorter(true);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		
		
		CategoryDataset dataset = createDataset();

		// Create chart
		JFreeChart chart = ChartFactory.createBarChart("Biểu đồ doanh thu", // Chart Title
				"Tháng", // Category axis
				"Doanh thu (VNĐ)", // Value axis
				dataset, PlotOrientation.VERTICAL, true, true, false);
		ChartPanel panel = new ChartPanel(chart);		
		bCenter = Box.createHorizontalBox();
		bCenter.add(scroll);
		bCenter.add(panel);		
		this.setLayout(new BorderLayout());
		this.add(b, BorderLayout.NORTH);		
		this.add(bCenter, BorderLayout.CENTER);
//		this.setBackground(Color.decode("#e6dbd1"));		
	}
	
	private CategoryDataset createDataset() {
		DefaultCategoryDataset dataset = new DefaultCategoryDataset();	
		
		dataset.addValue(10000, "Tiền phòng", "Tháng 1");
		dataset.addValue(15000, "Tiền dịch vụ", "Tháng 1");
		dataset.addValue(25000, "Tổng hoá đơn", "Tháng 1");
		
		dataset.addValue(15000, "Tiền phòng", "Tháng 2");
		dataset.addValue(20000, "Tiền dịch vụ", "Tháng 2");
		dataset.addValue(35000, "Tổng hoá đơn", "Tháng 2");
		
		dataset.addValue(20000, "Tiền phòng", "Tháng 3");
		dataset.addValue(25000, "Tiền dịch vụ", "Tháng 3");
		dataset.addValue(45000, "Tổng hoá đơn", "Tháng 3");

		return dataset;
	}
}
