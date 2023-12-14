package app;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.TimeZone;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.TableColumnModelEvent;
import javax.swing.event.TableColumnModelListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;

import com.github.lgooddatepicker.components.TimePicker;
import com.toedter.calendar.JDateChooser;

import connectDB.ConnectDB;
import dao.DAOCTDVPhong;
import dao.DAOCTHD;
import dao.DAOHoaDon;
import dao.DAOPhieuDatPhong;
import dao.MaTuDong;
import dao.DAODichVu;
import dao.DAOKhachHang;
import dao.DAONhanVien;
import dao.DAOPhong;
import entity.CTDVPhong;
import entity.ChiTietHoaDon;
import entity.DichVu;
import entity.HoaDon;
import entity.inforUsingService;
import entity.KhachHang;
import entity.KhuyenMai;
import entity.NhanVien;
import entity.PhieuDatPhong;
import entity.Phong;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.view.JasperViewer;

public class PanelDatPhong extends JPanel {
	private JButton thuePBtn, phieuDatPhongBtn, datPBtn, chuyenPBtn, chiTietPBtn, dichVuPBtn, tinhTienPBtn, timKiemPBtn,
			lamMoiBtn;
	private JComboBox<String> tinhTrangB, soNguoiB, loaiPhongB;
	private JTextField phongF, giaPhongF;
	private JTable phongTable;
	private DefaultTableModel phongModel;
	private DigitalClock clock;
	private ThuePhong thuePhong;
	private ChuyenPhong chuyenPhong;
	private ChiTietPhong chiTietPhong;
	private DichVuPhong dichVuPhong;
	private TinhTien tinhTien;
	private PhongCho phieuDatPhong;
	private JLabel lbPhongTrong, lbPhongCho, lbPhongDangThue;
	private DAOPhong phongDao = new DAOPhong();
	private DAOCTHD cthdDao = new DAOCTHD();
	private DAOHoaDon hdDAO = new DAOHoaDon();
	private DAODichVu dvDAO = new DAODichVu();
	private DAOCTDVPhong ctdvPhongDAO = new DAOCTDVPhong();
	private DecimalFormat formatter = new DecimalFormat("###,###,### VNĐ");
	private String manNV;

	public String getManNV() {
		return manNV;
	}

	public void setManNV(String manNV) {
		this.manNV = manNV;
	}

	public PanelDatPhong(String maNV) {
		setManNV(maNV);
		try {
			ConnectDB.getInstance().connect();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

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

		Icon imgAdd = new ImageIcon("src/img/add2.png");
		Icon imgDel = new ImageIcon("src/img/del.png");
		Icon imgReset = new ImageIcon("src/img/refresh16.png");
		Icon imgEdit = new ImageIcon("src/img/edit.png");
		Icon imgOut = new ImageIcon("src/img/out.png");
		Icon imgSearch = new ImageIcon("src/img/search.png");
		Icon imgCheck = new ImageIcon("src/img/check16.png");
		Icon imgCancel = new ImageIcon("src/img/cancel16.png");

		JLabel tinhTrangLb, soNguoiLb, loaiPLb, phongLb, giaPLb, locTinhTrangLb, sdtLb;
		JPanel mainPane, leftPane, rightPane, timePane, btnPane, panePhong, panePDP, paneBtnPhong, paneBtnPDP,
				paneTraCuuP;
		String[] headersTableP = { "Phòng", "Loại Phòng", "Số Người", "Tình Trạng", "Giá Phòng" };
		Border border = new LineBorder(Color.black);
		Border blackLine = BorderFactory.createLineBorder(Color.black);
		leftPane = new JPanel();
		rightPane = new JPanel();
		btnPane = new JPanel();

		// Pane left các chức năng
		GridLayout grid = new GridLayout(7, 0);
		grid.setVgap(20);
		JPanel btnMainPane = new JPanel(grid);
		btnMainPane.setBackground(Color.decode("#D0BAFB"));
		btnMainPane.add(thuePBtn = new ButtonGradient("Thuê Phòng"));
//		btnMainPane.add(datPBtn = new ButtonGradient("Đặt Phòng"));
		btnMainPane.add(phieuDatPhongBtn = new ButtonGradient("Phòng Chờ"));
		btnMainPane.add(chuyenPBtn = new ButtonGradient("Chuyển phòng"));
		btnMainPane.add(chiTietPBtn = new ButtonGradient("Chi tiết"));
		btnMainPane.add(dichVuPBtn = new ButtonGradient("Dịch vụ"));
		btnMainPane.add(tinhTienPBtn = new ButtonGradient("Thanh Toán"));
		thuePBtn.setFont(new Font("Sanserif", Font.BOLD, 20));
		thuePBtn.setBackground(Color.decode("#6fa8dc"));
		thuePBtn.setMaximumSize(new Dimension(Integer.MAX_VALUE, thuePBtn.getMinimumSize().height));

//		datPBtn.setFont(new Font("Sanserif", Font.BOLD, 20));
//		datPBtn.setBackground(Color.decode("#6fa8dc"));
//		datPBtn.setMaximumSize(new Dimension(Integer.MAX_VALUE, datPBtn.getMinimumSize().height));

		phieuDatPhongBtn.setFont(new Font("Sanserif", Font.BOLD, 20));
		phieuDatPhongBtn.setBackground(Color.decode("#6fa8dc"));
		phieuDatPhongBtn.setMaximumSize(new Dimension(Integer.MAX_VALUE, phieuDatPhongBtn.getMinimumSize().height));
		chuyenPBtn.setFont(new Font("Sanserif", Font.BOLD, 20));
		chuyenPBtn.setBackground(Color.decode("#6fa8dc"));
		chuyenPBtn.setMaximumSize(new Dimension(Integer.MAX_VALUE, chuyenPBtn.getMinimumSize().height));
		chiTietPBtn.setBackground(Color.decode("#6fa8dc"));
		chiTietPBtn.setFont(new Font("Sanserif", Font.BOLD, 20));
		chiTietPBtn.setMaximumSize(new Dimension(Integer.MAX_VALUE, chiTietPBtn.getMinimumSize().height));
		dichVuPBtn.setFont(new Font("Sanserif", Font.BOLD, 20));
		dichVuPBtn.setBackground(Color.decode("#6fa8dc"));
		dichVuPBtn.setMaximumSize(new Dimension(Integer.MAX_VALUE, dichVuPBtn.getMinimumSize().height));
		tinhTienPBtn.setFont(new Font("Sanserif", Font.BOLD, 20));
		tinhTienPBtn.setBackground(Color.decode("#6fa8dc"));
		tinhTienPBtn.setMaximumSize(new Dimension(Integer.MAX_VALUE, tinhTienPBtn.getMinimumSize().height));

		btnPane.add(btnMainPane);
//		btnPane.setBorder(border);
		btnPane.setBackground(Color.decode("#D0BAFB"));

		GridLayout gridForPane = new GridLayout(3, 0);
		gridForPane.setVgap(2);
		JPanel pane = new JPanel(gridForPane);
		pane.add(lbPhongCho = new JLabel("Phòng Chờ ()"));
		pane.add(lbPhongTrong = new JLabel("Phòng Trống ()"));
		pane.add(lbPhongDangThue = new JLabel("Phòng Đang Thuê ()"));
		pane.setBackground(Color.decode("#D0BAFB"));

		Box leftBox = Box.createVerticalBox();
//		leftBox.add(clock = new DigitalClock());
		leftBox.add(Box.createVerticalStrut(10));
		leftBox.add(btnPane);
		leftBox.add(Box.createVerticalStrut(5));
		leftBox.add(pane);
		leftPane.add(leftBox);
		leftPane.setBackground(Color.decode("#D0BAFB"));

		// Pane Right
		panePhong = new JPanel();
		panePhong.setLayout(new BorderLayout());
		paneBtnPhong = new JPanel(new BorderLayout());
		paneTraCuuP = new JPanel();

		// Table Phong
		phongModel = new DefaultTableModel(headersTableP, 0);
		phongTable = new JTable(phongModel) {
			@Override
			public Class<?> getColumnClass(int column) {
				if (convertColumnIndexToModel(column) == 3)
					return Double.class;
				return super.getColumnClass(column);
			}
		};
		phongTable.setAutoCreateRowSorter(true);
		phongTable.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		phongTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		phongTable.setRowHeight(40);
		phongTable.setFont(new Font("serif", Font.PLAIN, 17));
		phongTable.setDefaultRenderer(Double.class, new DefaultTableCellRenderer() {
			@Override
			public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected,
					boolean hasFocus, int row, int column) {
				Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
				c.setForeground(((String) value).equalsIgnoreCase("Đang thuê") ? Color.RED
						: ((String) value).equalsIgnoreCase("Còn trống") ? Color.green : Color.BLUE);
				return c;
			}
		});

//		DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
//		centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
//		phongTable.setDefaultRenderer(String.class, centerRenderer);

		JScrollPane scrollPhongTable = new JScrollPane(phongTable, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scrollPhongTable.setBorder(BorderFactory.createTitledBorder(blackLine, "Danh sách thông tin phòng"));
		scrollPhongTable.setBackground(Color.decode("#D0BAFB"));
		JPanel pnlForTablePhong = new JPanel(new BorderLayout());
		pnlForTablePhong.add(scrollPhongTable, BorderLayout.CENTER);
		pnlForTablePhong.add(leftPane, BorderLayout.EAST);
		panePhong.add(pnlForTablePhong, BorderLayout.CENTER);

//		//Btn for phong
//		GridLayout grid2 = new GridLayout(5, 0);
//		grid2.setVgap(5);
//		Box boxForBtnPhong = Box.createVerticalBox();
//		boxForBtnPhong.add(thuePBtn);
//		boxForBtnPhong.add(chuyenPBtn);
//		boxForBtnPhong.add(chiTietPBtn);
//		boxForBtnPhong.add(dichVuPBtn);
//		boxForBtnPhong.add(tinhTienPBtn);
//		JPanel pnlForBoxForBtnPhong = new JPanel();
//		pnlForBoxForBtnPhong.add(boxForBtnPhong);
//		
//		panePhong.add(pnlForBoxForBtnPhong, BorderLayout.EAST);

		// Box các btn phòng
		Box btnPhongBox = Box.createHorizontalBox();

		// pane tra cứu phòng
		Box traCuuB = Box.createVerticalBox();
		Box traCuuB1 = Box.createHorizontalBox();
		Box traCuuB2 = Box.createHorizontalBox();

		JPanel panePhongLb = new JPanel();
		panePhongLb.setBackground(Color.decode("#D0BAFB"));
		panePhongLb.add(phongLb = new JLabel("Phòng"));
		traCuuB1.add(panePhongLb);

		JPanel panePhongF = new JPanel();
		panePhongF.setBackground(Color.decode("#D0BAFB"));
		panePhongF.add(phongF = new JTextField(10));
		phongF.setFont(new Font("Arial", Font.PLAIN, 16));
		traCuuB1.add(panePhongF);

		traCuuB1.add(timKiemPBtn = new ButtonGradient("Tìm kiếm", imgSearch));

		JPanel paneGiaLb = new JPanel();
		paneGiaLb.setBackground(Color.decode("#D0BAFB"));
		paneGiaLb.add(giaPLb = new JLabel("Giá phòng"));
		traCuuB2.add(paneGiaLb);

		JPanel paneGiaPhongF = new JPanel();
		paneGiaPhongF.setBackground(Color.decode("#D0BAFB"));
		paneGiaPhongF.add(giaPhongF = new JTextField(10));
		giaPhongF.setFont(new Font("Arial", Font.PLAIN, 16));
		traCuuB2.add(paneGiaPhongF);

		traCuuB2.add(lamMoiBtn = new ButtonGradient("Làm mới", imgReset));

		traCuuB.add(traCuuB1);
		traCuuB.add(Box.createVerticalStrut(10));
		traCuuB.add(traCuuB2);
		paneTraCuuP.add(traCuuB);
		paneTraCuuP.setBackground(Color.decode("#D0BAFB"));
		paneTraCuuP.setBorder(BorderFactory.createTitledBorder(blackLine, "Tra cứu"));

		phongLb.setPreferredSize(giaPLb.getPreferredSize());
		lamMoiBtn.setPreferredSize(timKiemPBtn.getPreferredSize());

		// Btn Phong
		String[] headersTinhTrang = { "Còn trống", "Tất cả", "Đang thuê", "Đã đặt trước" };
		String[] headersSoNguoi = { "Tất cả", "10", "15", "20" };
		String[] headersLoaiPhong = { "Tất cả", "Vip", "Thường" };

		// pane combobox tình trạng
		Box btnPhongBox1 = Box.createHorizontalBox();
		btnPhongBox1.add(tinhTrangLb = new JLabel("Tình trạng phòng "));
		btnPhongBox1.add(tinhTrangB = new JComboBox<>(headersTinhTrang));
		JPanel paneCBTinhTrang = new JPanel();
		paneCBTinhTrang.setBackground(Color.decode("#D0BAFB"));
		paneCBTinhTrang.add(btnPhongBox1);
		btnPhongBox.add(paneCBTinhTrang);
		btnPhongBox.add(Box.createHorizontalStrut(5));

		// pane combobox số người
		Box btnPhongBox2 = Box.createHorizontalBox();
		btnPhongBox2.add(soNguoiLb = new JLabel("Số người "));
		btnPhongBox2.add(soNguoiB = new JComboBox<>(headersSoNguoi));
		JPanel paneCBSoNguoi = new JPanel();
		paneCBSoNguoi.setBackground(Color.decode("#D0BAFB"));
		paneCBSoNguoi.add(btnPhongBox2);
		btnPhongBox.add(paneCBSoNguoi);
		btnPhongBox.add(Box.createHorizontalStrut(5));

		// pane combobox loại phòng
		Box btnPhongBox3 = Box.createHorizontalBox();
		btnPhongBox3.add(loaiPLb = new JLabel("Loại phòng "));
		btnPhongBox3.add(loaiPhongB = new JComboBox<>(headersLoaiPhong));
		JPanel paneCBLoaiPhong = new JPanel();
		paneCBLoaiPhong.setBackground(Color.decode("#D0BAFB"));
		paneCBLoaiPhong.add(btnPhongBox3);
		btnPhongBox.add(paneCBLoaiPhong);
		btnPhongBox.add(Box.createHorizontalStrut(5));

		// Thêm pane tra cứu vào Box
		btnPhongBox.add(paneTraCuuP);

		soNguoiB.setPreferredSize(tinhTrangB.getPreferredSize());
		loaiPhongB.setPreferredSize(tinhTrangB.getPreferredSize());

		paneBtnPhong.add(clock = new DigitalClock(), BorderLayout.WEST);
		paneBtnPhong.add(btnPhongBox, BorderLayout.CENTER);
		paneBtnPhong.setBackground(Color.decode("#D0BAFB"));

		panePhong.add(paneBtnPhong, BorderLayout.NORTH);
//		panePhong.setBorder(BorderFactory.createTitledBorder(blackLine, "Thông tin phòng"));
		panePhong.setBackground(Color.decode("#D0BAFB"));

//		Box rightBox = Box.createVerticalBox();
//		rightBox.add(panePhong);
//		rightBox.add(panePDP);

		this.setLayout(new BorderLayout());
//		this.add(leftPane, BorderLayout.EAST);
		this.add(panePhong, BorderLayout.CENTER);

		readDataToTablePhong();

		thuePBtn.setMnemonic(KeyEvent.VK_F1);
		thuePBtn.setToolTipText("Click ALT F1");
		chuyenPBtn.setMnemonic(KeyEvent.VK_F2);
		chuyenPBtn.setToolTipText("Click ALT F2");
		chiTietPBtn.setMnemonic(KeyEvent.VK_F3);
		chiTietPBtn.setToolTipText("Click ALT F3");
		dichVuPBtn.setMnemonic(KeyEvent.VK_F4);
		dichVuPBtn.setToolTipText("Click ALT F4");
		tinhTienPBtn.setMnemonic(KeyEvent.VK_F5);
		tinhTienPBtn.setToolTipText("Click ALT F5");
		
		thuePBtn.addActionListener(e -> xuLyThuePhong());
		chuyenPBtn.addActionListener(e -> xuLyChuyenPhong());
		chiTietPBtn.addActionListener(e -> xuLyChiTietPhong());
		dichVuPBtn.addActionListener(e -> xuLyDichVuPhong());
		tinhTienPBtn.addActionListener(e -> xuLyTinhTien());
		timKiemPBtn.addActionListener(e -> xuLyTimKiemPhong());
		lamMoiBtn.addActionListener(e -> {
			phongF.setText("");
			giaPhongF.setText("");
			phongF.requestFocus();
		});
		tinhTrangB.addActionListener(e -> xuLyLocTheoTinhTrangPhong());
		soNguoiB.addActionListener(e -> xuLyLocTheoSoNguoi());
		loaiPhongB.addActionListener(e -> xuLyLocTheoLoaiPhong());
		phieuDatPhongBtn.addActionListener(e -> xuLyNhanPhongCho());

	}

	private void readAllDateToTablePhong() {
		phongModel.setRowCount(0);
		List<entity.Phong> dsP = phongDao.getAllDataForTableDatPhong().stream()
				.sorted((p1, p2) -> p1.getTenPhong().compareTo(p2.getTenPhong())).collect(Collectors.toList());
		for (Phong p : dsP) {
			if (!p.getTinhTrangPhong().trim().equalsIgnoreCase("Đã xóa"))
				phongModel.addRow(new Object[] { p.getTenPhong(), p.getLoaiPhong().getTenLoaiPhong(),
						p.getLoaiPhong().getSucChua(), p.getTinhTrangPhong(),
						formatter.format(p.getLoaiPhong().getGiaLoaiPhong()) });
		}
	}

	private void readAllDateToTablePhong2() {
		phongModel.setRowCount(0);
		List<entity.Phong> dsP = phongDao.getAllDataForTableDatPhong().stream()
				.sorted((p1, p2) -> p1.getTenPhong().compareTo(p2.getTenPhong())).collect(Collectors.toList());
		for (Phong p : dsP) {
			if (p.getTinhTrangPhong().trim().equalsIgnoreCase("Còn trống"))
				phongModel.addRow(new Object[] { p.getTenPhong(), p.getLoaiPhong().getTenLoaiPhong(),
						p.getLoaiPhong().getSucChua(), p.getTinhTrangPhong(),
						formatter.format(p.getLoaiPhong().getGiaLoaiPhong()) });
		}
	}

	private void readAllDateToTablePhong3() {
		phongModel.setRowCount(0);
		List<entity.Phong> dsP = phongDao.getAllDataForTableDatPhong().stream()
				.sorted((p1, p2) -> p1.getTenPhong().compareTo(p2.getTenPhong())).collect(Collectors.toList());
		for (Phong p : dsP) {
			if (p.getTinhTrangPhong().trim().equalsIgnoreCase("Đang thuê"))
				phongModel.addRow(new Object[] { p.getTenPhong(), p.getLoaiPhong().getTenLoaiPhong(),
						p.getLoaiPhong().getSucChua(), p.getTinhTrangPhong(),
						formatter.format(p.getLoaiPhong().getGiaLoaiPhong()) });
		}
	}

	private void xuLyLocTheoLoaiPhong() {
		phongModel.setRowCount(0);
		List<Phong> dsP = phongDao.timKiemPhongTheoLoaiPhong((String) loaiPhongB.getSelectedItem());
		if (((String) loaiPhongB.getSelectedItem()).equalsIgnoreCase("Tất cả")) {
			readAllDateToTablePhong();
		}
		for (Phong p : dsP) {
			phongModel.addRow(
					new Object[] { p.getTenPhong(), p.getLoaiPhong().getTenLoaiPhong(), p.getLoaiPhong().getSucChua(),
							p.getTinhTrangPhong(), formatter.format(p.getLoaiPhong().getGiaLoaiPhong()) });
		}
	}

	private void xuLyLocTheoSoNguoi() {
		phongModel.setRowCount(0);
		List<Phong> dsP = phongDao.timKiemPhongTheoSoNguoi(Integer.valueOf((String) soNguoiB.getSelectedItem()));
		if (((String) soNguoiB.getSelectedItem()).equalsIgnoreCase("Tất cả")) {
			readAllDateToTablePhong();
		}
		for (Phong p : dsP) {
			phongModel.addRow(
					new Object[] { p.getTenPhong(), p.getLoaiPhong().getTenLoaiPhong(), p.getLoaiPhong().getSucChua(),
							p.getTinhTrangPhong(), formatter.format(p.getLoaiPhong().getGiaLoaiPhong()) });
		}
	}

	public void xuLyLocTheoTinhTrangPhong() {
		phongModel.setRowCount(0);
		List<Phong> dsP = phongDao.timKiemPhongTheoTinhTrangPhong((String) tinhTrangB.getSelectedItem());
		if (((String) tinhTrangB.getSelectedItem()).equalsIgnoreCase("Tất cả")) {
			readAllDateToTablePhong();
		}
		for (Phong p : dsP) {
			phongModel.addRow(
					new Object[] { p.getTenPhong(), p.getLoaiPhong().getTenLoaiPhong(), p.getLoaiPhong().getSucChua(),
							p.getTinhTrangPhong(), formatter.format(p.getLoaiPhong().getGiaLoaiPhong()) });
		}
	}

	private void xuLyTimKiemPhong() {
		if (phongF.getText().equals("") && giaPhongF.getText().equals("")) {

		} else {
			phongModel.setRowCount(0);
			List<Phong> dsP = phongDao.getAllDataForTableDatPhong();
			if (!phongF.getText().trim().equals("")) {
				dsP = phongDao.timKiemPhongTheoTenPhong(phongF.getText().trim());
			}

			if (!giaPhongF.getText().trim().equals("")) {
				dsP = dsP.stream()
						.filter(p -> p.getLoaiPhong().getGiaLoaiPhong() == Double.valueOf(giaPhongF.getText().trim()))
						.collect(Collectors.toList());
			}

			for (Phong p : dsP) {
				System.out.println();
				phongModel.addRow(new Object[] { p.getTenPhong(), p.getLoaiPhong().getTenLoaiPhong(),
						p.getLoaiPhong().getSucChua(), p.getTinhTrangPhong(),
						formatter.format(p.getLoaiPhong().getGiaLoaiPhong()) });
			}
		}
	}

	private void xuLyNhanPhongCho() {
		phieuDatPhong = new PhongCho();
		phieuDatPhong.setVisible(true);
		phieuDatPhong.setLocationRelativeTo(null);
	}

	private void xuLyThuePhong() {
		if (phongTable.getSelectedRow() != -1) {
			if (((String) phongModel.getValueAt(phongTable.getSelectedRow(), 3)).equalsIgnoreCase("Còn trống")) {
				thuePhong = new ThuePhong();
				thuePhong.setVisible(true);
				thuePhong.setAlwaysOnTop(true);
				thuePhong.setLocationRelativeTo(null);
			} else {
				JOptionPane.showMessageDialog(this, "Bạn chỉ được thuê phòng đang trống!!");
			}
		} else {
			JOptionPane.showMessageDialog(this, "Hãy chọn phòng bạn muốn đặt!!");
		}
	}

	private void xuLyChuyenPhong() {
		if (phongTable.getSelectedRow() != -1) {
			if (((String) phongModel.getValueAt(phongTable.getSelectedRow(), 3)).equalsIgnoreCase("Đang thuê")) {
				chuyenPhong = new ChuyenPhong((String) phongModel.getValueAt(phongTable.getSelectedRow(), 0));
				chuyenPhong.setVisible(true);
				chuyenPhong.setAlwaysOnTop(true);
				chuyenPhong.setLocationRelativeTo(null);
			} else {
				JOptionPane.showMessageDialog(this, "Bạn chỉ được chuyển phòng đang thuê!!");
			}
		} else {
			JOptionPane.showMessageDialog(this, "Hãy chọn phòng bạn muốn chuyển!!");
		}
	}

	private void xuLyChiTietPhong() {
		if(phongTable.getSelectedRow() != -1) {
			if (((String) phongModel.getValueAt(phongTable.getSelectedRow(), 3)).equalsIgnoreCase("Đang thuê")) {
				chiTietPhong = new ChiTietPhong();
				chiTietPhong.setVisible(true);
				chiTietPhong.setAlwaysOnTop(true);
				chiTietPhong.setLocationRelativeTo(null);
			}
			else {
				JOptionPane.showMessageDialog(this, "Bạn chỉ được xem chi tiết phòng đang thuê!!");
			}
		} else {
			JOptionPane.showMessageDialog(this, "Hãy chọn phòng bạn muốn xem chi tiết!!");
		}
	}

	private void xuLyDichVuPhong() {
		if (phongTable.getSelectedRow() != -1) {
			if (((String) phongModel.getValueAt(phongTable.getSelectedRow(), 3)).equalsIgnoreCase("Đang thuê")) {
				dichVuPhong = new DichVuPhong();
				dichVuPhong.setVisible(true);
				dichVuPhong.setAlwaysOnTop(true);
				dichVuPhong.setLocationRelativeTo(null);
			} else {
				JOptionPane.showMessageDialog(this, "Bạn chỉ được thêm dịch vụ vào phòng đang thuê!!");
			}
		} else {
			JOptionPane.showMessageDialog(this, "Hãy chọn phòng bạn muốn thêm dịch vụ!!");
		}
	}

	private void xuLyTinhTien() {
		if (phongTable.getSelectedRow() != -1) {
			if (((String) phongModel.getValueAt(phongTable.getSelectedRow(), 3)).equalsIgnoreCase("Đang thuê")) {
				tinhTien = new TinhTien();
				tinhTien.setVisible(true);
				tinhTien.setAlwaysOnTop(true);
				tinhTien.setLocationRelativeTo(null);
			} else {
				JOptionPane.showMessageDialog(this, "Bạn chỉ được thanh toán phòng đang thuê!!");
			}
		} else {
			JOptionPane.showMessageDialog(this, "Hãy chọn phòng bạn muốn thanh toán!!");
		}

	}

	private void readDataToTablePhong() {
		List<entity.Phong> dsP = phongDao.getAllDataForTableDatPhong().stream()
				.sorted((p1, p2) -> p1.getTenPhong().compareTo(p2.getTenPhong())).collect(Collectors.toList());

		long soPhongCho = dsP.stream().filter(p -> p.getTinhTrangPhong().equalsIgnoreCase("Đã đặt trước")).count();
		long soPhongThue = dsP.stream().filter(p -> p.getTinhTrangPhong().equalsIgnoreCase("Đang Thuê")).count();
		long soPhongTrong = dsP.stream().filter(p -> p.getTinhTrangPhong().equalsIgnoreCase("Còn trống")).count();
		lbPhongTrong.setText("Phòng Trống ( " + soPhongTrong + " )");
		lbPhongDangThue.setText("Phòng Thuê ( " + soPhongThue + " )");
		lbPhongCho.setText("Phòng Còn Trống ( " + soPhongCho + " )");
		lbPhongTrong.setFont(new Font("sanserif", Font.BOLD, 14));
		lbPhongCho.setFont(new Font("sanserif", Font.BOLD, 14));
		lbPhongDangThue.setFont(new Font("sanserif", Font.BOLD, 14));

		for (Phong p : dsP) {
			if (p.getTinhTrangPhong().equalsIgnoreCase("Còn Trống"))
				phongModel.addRow(new Object[] { p.getTenPhong(), p.getLoaiPhong().getTenLoaiPhong(),
						p.getLoaiPhong().getSucChua(), p.getTinhTrangPhong(), formatter.format(p.getLoaiPhong().getGiaLoaiPhong()) });
		}
	}

	public class ThuePhong extends JFrame {
		private JTextField tenPhongF, loaiPhongF, giaPhongF, sucChuaF, tinhTrangF, sdtKhachF, tenKhachF, soLuongF;
		private JTextArea ghiChuA;
		private JDateChooser ngayNhanCD;
		private TimePicker gioNhanTP;
		private JButton kiemTraBtn, quayLaiBtn, thuePhongBtn;
		private JComboBox<String> cbLuaChon;
		private String tenP;
		private DAOPhong pdao;
		private DAOKhachHang kdao;
		private DAONhanVien nvDAO = new DAONhanVien();
		private DAOPhieuDatPhong pdpDao;
		private DAOHoaDon hdDao;
		private DAOCTHD cthdDao;
		private MaTuDong maTuDong = new MaTuDong();
		private String maKH = "", tenKH = "", sdtKH = "";
		SimpleDateFormat dayFormat = new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat gioFormat = new SimpleDateFormat("hh:mm:ss");
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		SimpleDateFormat dayFormat2 = new SimpleDateFormat("dd/MM/yyyy");

		public ThuePhong() {
			setSize(700, 550);
			hdDao = new DAOHoaDon();
			pdao = new DAOPhong();
			kdao = new DAOKhachHang();
			pdpDao = new DAOPhieuDatPhong();
			cthdDao = new DAOCTHD();
			try {
				ConnectDB.getInstance().connect();
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}

			Icon imgDel = new ImageIcon("src/img/del.png");
			Icon imgReset = new ImageIcon("src/img/refresh16.png");
			Icon imgEdit = new ImageIcon("src/img/edit.png");
			Icon imgOut = new ImageIcon("src/img/out.png");
			Icon imgSearch = new ImageIcon("src/img/search.png");
			Icon imgCheck = new ImageIcon("src/img/check16.png");
			Icon imgCancel = new ImageIcon("src/img/cancel16.png");
			Icon imgBack = new ImageIcon("src/img/back16.png");
			Icon imgAdd = new ImageIcon("src/img/add16.png");

			JLabel luaChonLb, soLuongLb, tenPhongLb, loaiPhongLb, giaPhongLb, sucChuaLb, tinhTrangLb, sdtKhachLb,
					tenKhachLb, ngayNhanPhongLb, gioNhanPhongLb, ghiChuLb, tieuDeLb;
			JPanel titlePanel, mainPanel, leftPanel, rightPanel, bottomPanelRight, bottomPanelLeft, bottomPanel;
			Font font = new Font("Arial", Font.BOLD, 24);
			Border border = new LineBorder(Color.black);
			mainPanel = new JPanel(new BorderLayout());
			titlePanel = new JPanel();
			leftPanel = new JPanel();
			rightPanel = new JPanel();
			bottomPanelRight = new JPanel(new FlowLayout(FlowLayout.RIGHT));
			bottomPanelLeft = new JPanel(new FlowLayout(FlowLayout.LEFT));
			bottomPanel = new JPanel(new GridLayout(1, 2));

			String[] headersLuaChon = { "Đặt Trước", "Thuê Liền" };
			cbLuaChon = new JComboBox<>(headersLuaChon);
			// set thông tin phòng
			leftPanel.setBackground(Color.decode("#cccccc"));
			leftPanel.setBorder(BorderFactory.createTitledBorder(border, "Thông tin phòng"));

			Box thongTinPhongBox = Box.createVerticalBox();

			Box tenPhongBox = Box.createHorizontalBox();
			tenPhongBox.add(tenPhongLb = new JLabel("Tên phòng:"));
			tenPhongBox.add(Box.createHorizontalStrut(5));
			tenPhongBox.add(tenPhongF = new JTextField(10));
			tenPhongF.setFont(new Font("Arial", Font.PLAIN, 16));
			tenPhongF.setBorder(null);
			tenPhongF.setEditable(false);
			tenPhongLb.setFont(new Font("Arial", Font.BOLD, 16));

			Box loaiPhongBox = Box.createHorizontalBox();
			loaiPhongBox.add(loaiPhongLb = new JLabel("Loại phòng:"));
			loaiPhongBox.add(Box.createHorizontalStrut(5));
			loaiPhongBox.add(loaiPhongF = new JTextField(10));
			loaiPhongF.setFont(new Font("Arial", Font.PLAIN, 16));
			loaiPhongF.setBorder(null);
			loaiPhongF.setEditable(false);
			loaiPhongLb.setFont(new Font("Arial", Font.BOLD, 16));

			Box giaPhongBox = Box.createHorizontalBox();
			giaPhongBox.add(giaPhongLb = new JLabel("Giá phòng:"));
			giaPhongBox.add(Box.createHorizontalStrut(5));
			giaPhongBox.add(giaPhongF = new JTextField(10));
			giaPhongF.setFont(new Font("Arial", Font.PLAIN, 16));
			giaPhongF.setBorder(null);
			giaPhongF.setEditable(false);
			giaPhongLb.setFont(new Font("Arial", Font.BOLD, 16));

			Box sucChuaBox = Box.createHorizontalBox();
			sucChuaBox.add(sucChuaLb = new JLabel("Sức chứa:"));
			sucChuaBox.add(Box.createHorizontalStrut(5));
			sucChuaBox.add(sucChuaF = new JTextField(10));
			sucChuaF.setFont(new Font("Arial", Font.PLAIN, 16));
			sucChuaF.setBorder(null);
			sucChuaF.setEditable(false);
			sucChuaLb.setFont(new Font("Arial", Font.BOLD, 16));

			Box tinhTrangBox = Box.createHorizontalBox();
			tinhTrangBox.add(tinhTrangLb = new JLabel("Tình trạng:"));
			tinhTrangBox.add(Box.createHorizontalStrut(5));
			tinhTrangBox.add(tinhTrangF = new JTextField(10));
			tinhTrangF.setFont(new Font("Arial", Font.PLAIN, 16));
			tinhTrangF.setBorder(null);
			tinhTrangF.setEditable(false);
			tinhTrangLb.setFont(new Font("Arial", Font.BOLD, 16));

			tenPhongF.setBackground(Color.decode("#cccccc"));
			loaiPhongF.setBackground(Color.decode("#cccccc"));
			giaPhongF.setBackground(Color.decode("#cccccc"));
			sucChuaF.setBackground(Color.decode("#cccccc"));
			tinhTrangF.setBackground(Color.decode("#cccccc"));

			thongTinPhongBox.add(tenPhongBox);
			thongTinPhongBox.add(Box.createVerticalStrut(25));
			thongTinPhongBox.add(loaiPhongBox);
			thongTinPhongBox.add(Box.createVerticalStrut(25));
			thongTinPhongBox.add(giaPhongBox);
			thongTinPhongBox.add(Box.createVerticalStrut(25));
			thongTinPhongBox.add(sucChuaBox);
			thongTinPhongBox.add(Box.createVerticalStrut(25));
			thongTinPhongBox.add(tinhTrangBox);
			leftPanel.add(thongTinPhongBox);

			tenPhongLb.setPreferredSize(loaiPhongLb.getPreferredSize());
			tinhTrangLb.setPreferredSize(loaiPhongLb.getPreferredSize());
			giaPhongLb.setPreferredSize(loaiPhongLb.getPreferredSize());
			sucChuaLb.setPreferredSize(loaiPhongLb.getPreferredSize());

			// set thông tin thuê phòng
			rightPanel = new JPanel();
			rightPanel.setBorder(BorderFactory.createTitledBorder(border, "Thông tin thuê phòng"));
			rightPanel.setBackground(Color.decode("#cccccc"));
			Box thongTinThuePhongBox = Box.createVerticalBox();
			Box boxForLuaChon = Box.createHorizontalBox();
			boxForLuaChon.add(luaChonLb = new JLabel("Lựa Chọn:"));
			boxForLuaChon.add(Box.createHorizontalStrut(5));
			boxForLuaChon.add(cbLuaChon);
			luaChonLb.setFont(new Font("Arial", Font.BOLD, 14));

			Box sdtKhachBox = Box.createHorizontalBox();
			sdtKhachBox.add(sdtKhachLb = new JLabel("SĐT Khách:"));
			JPanel sdtKhachPane = new JPanel();
			sdtKhachPane.add(sdtKhachF = new JTextField(15));
			sdtKhachPane.setBackground(Color.decode("#cccccc"));
			sdtKhachBox.add(sdtKhachPane);
			sdtKhachBox.add(Box.createHorizontalStrut(5));
			sdtKhachBox.add(kiemTraBtn = new ButtonGradient("Kiểm tra", imgCheck));
			kiemTraBtn.setBackground(Color.decode("#6fa8dc"));
			sdtKhachLb.setFont(new Font("Arial", Font.BOLD, 14));

			Box tenKhachBox = Box.createHorizontalBox();
			tenKhachBox.add(tenKhachLb = new JLabel("Tên khách:"));
			tenKhachBox.add(Box.createHorizontalStrut(5));
			tenKhachBox.add(tenKhachF = new JTextField(15));
			tenKhachLb.setFont(new Font("Arial", Font.BOLD, 14));

			Box soLuongBox = Box.createHorizontalBox();
			soLuongBox.add(soLuongLb = new JLabel("Số lượng khách:"));
			soLuongBox.add(Box.createHorizontalStrut(5));
			soLuongBox.add(soLuongF = new JTextField(15));
			soLuongLb.setFont(new Font("Arial", Font.BOLD, 14));

			Box ngayNhanBox = Box.createHorizontalBox();
			ngayNhanBox.add(ngayNhanPhongLb = new JLabel("Ngày nhận phòng:"));
			ngayNhanBox.add(Box.createHorizontalStrut(5));
			ngayNhanBox.add(ngayNhanCD = new JDateChooser());
			ngayNhanPhongLb.setFont(new Font("Arial", Font.BOLD, 14));

			Box gioNhanBox = Box.createHorizontalBox();
			gioNhanBox.add(gioNhanPhongLb = new JLabel("Giờ nhận phòng:"));
			gioNhanBox.add(Box.createHorizontalStrut(5));
			gioNhanBox.add(gioNhanTP = new TimePicker());
			gioNhanPhongLb.setFont(new Font("Arial", Font.BOLD, 14));

			Box ghiChuBox = Box.createHorizontalBox();
			ghiChuBox.add(ghiChuLb = new JLabel("Ghi chú:"));
			ghiChuBox.add(Box.createHorizontalStrut(5));
			ghiChuBox.add(ghiChuA = new JTextArea(3, 15));
			ghiChuLb.setFont(new Font("Arial", Font.BOLD, 14)); 

			thongTinThuePhongBox.add(boxForLuaChon);
			thongTinThuePhongBox.add(Box.createVerticalStrut(20));
			thongTinThuePhongBox.add(sdtKhachBox);
			thongTinThuePhongBox.add(Box.createVerticalStrut(20));
			thongTinThuePhongBox.add(tenKhachBox);
			thongTinThuePhongBox.add(Box.createVerticalStrut(20));
			thongTinThuePhongBox.add(soLuongBox);
			thongTinThuePhongBox.add(Box.createVerticalStrut(20));
			thongTinThuePhongBox.add(ngayNhanBox);
			thongTinThuePhongBox.add(Box.createVerticalStrut(20));
			thongTinThuePhongBox.add(gioNhanBox);
			thongTinThuePhongBox.add(Box.createVerticalStrut(20));
			thongTinThuePhongBox.add(ghiChuBox);
			rightPanel.add(thongTinThuePhongBox);

			tenKhachLb.setPreferredSize(ngayNhanPhongLb.getPreferredSize());
			gioNhanPhongLb.setPreferredSize(ngayNhanPhongLb.getPreferredSize());
			sdtKhachLb.setPreferredSize(ngayNhanPhongLb.getPreferredSize());
			ghiChuLb.setPreferredSize(ngayNhanPhongLb.getPreferredSize());
			luaChonLb.setPreferredSize(ngayNhanPhongLb.getPreferredSize());
			soLuongLb.setPreferredSize(ngayNhanPhongLb.getPreferredSize());

//			set Bottom pane

			bottomPanelLeft.add(quayLaiBtn = new ButtonGradient("Quay lại", imgBack));
			bottomPanelRight.add(thuePhongBtn = new ButtonGradient("Xác nhận", imgCheck));
			bottomPanelRight.setBackground(Color.decode("#D0BAFB"));
			bottomPanelLeft.setBackground(Color.decode("#D0BAFB"));
			bottomPanel.add(bottomPanelLeft);
			bottomPanel.add(bottomPanelRight);

			quayLaiBtn.setHorizontalAlignment(SwingConstants.RIGHT);
			quayLaiBtn.setBackground(Color.decode("#6fa8dc"));
			thuePhongBtn.setBackground(Color.decode("#6fa8dc"));

			// set tiêu đề
			titlePanel.setBackground(Color.decode("#990447"));
			titlePanel.add(tieuDeLb = new JLabel("THUÊ PHÒNG"));
			tieuDeLb.setFont(font);

			mainPanel.add(titlePanel, BorderLayout.NORTH);
			mainPanel.add(leftPanel, BorderLayout.WEST);
			mainPanel.add(rightPanel, BorderLayout.CENTER);
			mainPanel.add(bottomPanel, BorderLayout.SOUTH);
			this.getContentPane().add(mainPanel);
			this.setBackground(Color.decode("#e6dbd1"));

			readDataToFieldsTTPhong();

		
			kiemTraBtn.addActionListener(e -> xuLyKiemTraSDT());
			quayLaiBtn.addActionListener(e -> this.dispose());
			cbLuaChon.addActionListener(e -> xuLyThoiGian());
			thuePhongBtn.addActionListener(e -> {
				try {
					xuLyThuePhong();
				} catch (NumberFormatException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (ParseException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			});
		}

		private void readDataToFieldsTTPhong() {
			Phong phong = pdao.timKiemPhongTheoTenPhong((String) phongModel.getValueAt(phongTable.getSelectedRow(), 0))
					.get(0);
			tenPhongF.setText(phong.getTenPhong());
			loaiPhongF.setText(phong.getLoaiPhong().getTenLoaiPhong());
			giaPhongF.setText(formatter.format(phong.getLoaiPhong().getGiaLoaiPhong()) + "");
			sucChuaF.setText(phong.getLoaiPhong().getSucChua() + "");
			tinhTrangF.setText(phong.getTinhTrangPhong());
		}

		private void xuLyThoiGian() {
			if (((String) cbLuaChon.getSelectedItem()).equalsIgnoreCase("Thuê liền")) {
				Date date = new Date();
				ngayNhanCD.setDate(date);
				gioNhanTP.setTime(LocalTime.now());

			}
		}

		private void xuLyKiemTraSDT() {
			Pattern pattern = Pattern.compile("^\\d{10}$");
		    Matcher matcher = pattern.matcher(sdtKhachF.getText().trim());
			if(matcher.matches()) {
				tenKH = tenKhachF.getText().trim();
				sdtKH = sdtKhachF.getText().trim();
				List<KhachHang> dsKH = kdao.timKiemKHTheoSDT(sdtKhachF.getText());
				if (dsKH.size() > 0) {
					tenKhachF.setText(dsKH.get(0).getTenKH());
					maKH = kdao.timKiemKHTheoSDT(sdtKH).get(0).getMaKH();
				} else {
					JOptionPane.showMessageDialog(this, "Khách hàng mới");
					List<KhachHang> dsKH2 = kdao.getAllDataKH().stream()
							.sorted((kh1, kh2) -> kh1.getMaKH().compareTo(kh2.getMaKH())).toList();
					String str = dsKH2.get(dsKH2.size() - 1).getMaKH();
					maKH = maTuDong.formatMa(str);

				}
			}
			else {
				sdtKhachF.selectAll();
		    	sdtKhachF.requestFocus();
		    	JOptionPane.showMessageDialog(this, "Số điện thoại phải gồm 10 chữ số");
			}
		}
		
		private boolean matchPhone() {
			Pattern pattern = Pattern.compile("^\\d{10}$");
		    Matcher matcher = pattern.matcher(sdtKhachF.getText().trim());
		    if(!matcher.matches()) {
		    	sdtKhachF.selectAll();
		    	sdtKhachF.requestFocus();
		    	JOptionPane.showMessageDialog(this, "Số điện thoại phải gồm 10 chữ số");
		    }
		    return matcher.matches();
		}
		
		private boolean matchSoNguoi() {
			Phong phong = pdao.timKiemPhongTheoTenPhong((String) phongModel.getValueAt(phongTable.getSelectedRow(), 0))
					.get(0);
			Pattern p = Pattern.compile("^\\d+$");
		    Matcher matcher = p.matcher(soLuongF.getText().trim());
		    if(!matcher.matches()) {
		    	soLuongF.selectAll();
		    	soLuongF.requestFocus();
		    	JOptionPane.showMessageDialog(this, "Số người phải là số!!");
		    }
		    else {
		    	if(Integer.valueOf(soLuongF.getText()) > phong.getLoaiPhong().getGiaLoaiPhong()) {
		    		soLuongF.selectAll();
		    		soLuongF.requestFocus();
			    	JOptionPane.showMessageDialog(this, "Số người không phù hợp với sức chứa của phòng!!");
			    	return false;
			    	
		    	}
		    }
		    return matcher.matches();
		}
		
		private boolean matchNgayDatPhong() {
			String s = ((JTextField)ngayNhanCD.getDateEditor().getUiComponent()).getText();
			String[] s1 = s.split(" ");
			if(Integer.valueOf(s1[0]) < LocalDate.now().getDayOfMonth()) {
				JOptionPane.showMessageDialog(this, "Ngày đặt phòng không phù hợp!!");
				return false;
			}
			if(Integer.valueOf(s1[0]) - LocalDate.now().getDayOfMonth() > 1) {
				JOptionPane.showMessageDialog(this, "Chỉ được đặt phòng trong hôm nay hoặc ngày mai!!!");
				return false;
			}
			return true;
		}
		
		private boolean matchGioNhanPhong() {
			String[] s = gioNhanTP.getText().split(":");
			int hour = Integer.valueOf(s[0]);
			String str = String.valueOf( gioNhanTP.getText().charAt(gioNhanTP.getText().length() - 2));
			if(str.equalsIgnoreCase("p") && hour != 12)
				hour = hour + 12;
			
			if(((String)cbLuaChon.getSelectedItem()).equalsIgnoreCase("Thuê liền")) {
				if(hour < LocalTime.now().getHour() || hour > 22 || hour < 9) {
					JOptionPane.showMessageDialog(this, "giờ thuê phòng không hợp lệ!!!");
					return false;
				}
			}
			else if(((String)cbLuaChon.getSelectedItem()).equalsIgnoreCase("Đặt trước")){
				if(hour > 21) {
					JOptionPane.showMessageDialog(this, "Không được đặt phòng sau 9g tối!!!");
					return false;
				}
				if(hour < 9) {
					JOptionPane.showMessageDialog(this, "Không được đặt phòng trước 9g sáng!!!");
					return false;
				}
			}
			return true;
		}

		private void xuLyThuePhong() throws NumberFormatException, ParseException {
			if(matchPhone() && matchSoNguoi() && matchNgayDatPhong() && matchGioNhanPhong()) {
				String hour = gioNhanTP.getTime().toString().substring(0, 2);
				String minute = gioNhanTP.getTime().toString().substring(3, 5);
				String time = hour + ":" + minute + ":" + "00";
				String date = dayFormat.format(ngayNhanCD.getDate());
				tenKH = tenKhachF.getText().trim();
				sdtKH = sdtKhachF.getText().trim();

				List<KhachHang> dsKH = kdao.timKiemKHTheoSDT(sdtKhachF.getText());
				if (dsKH.size() == 0) {
					KhachHang kh = new KhachHang(maKH, sdtKH, tenKH);
					kdao.createKhach(kh);
				}
				if (((String) cbLuaChon.getSelectedItem()).equalsIgnoreCase("Đặt trước")) {
					List<PhieuDatPhong> dsPDP = pdpDao.getAllDataPDP().stream()
							.sorted((pdp1, pdp2) -> pdp1.getMaPDP().compareTo(pdp2.getMaPDP())).toList();
					String s = dsPDP.get(dsPDP.size() - 1).getMaPDP();
					String maKM = maTuDong.formatMa(s);
					Phong phong = pdao.timKiemPhongChinhXacTheoTenPhong(tenPhongF.getText().trim()).get(0);
					PhieuDatPhong pdp = new PhieuDatPhong(maKM, new KhachHang(maKH, sdtKH, tenKH),
							nvDAO.timKiemNhanVienTheoMa(manNV).get(0), phong, dateFormat.parse(date + " " + time),
							dateFormat.parse(date + " " + time), Integer.valueOf(soLuongF.getText()), 1, ghiChuA.getText());
					try {
						if (pdpDao.createPDP(pdp) && pdao.capNhatPhongTheoTinhTrang("Đã đặt trước", phong.getMaPhong())) {
							JOptionPane.showMessageDialog(this, "Đặt phòng thành công!!!");
							this.dispose();
						}
					} catch (Exception e) {
						// TODO: handle exception
						e.printStackTrace();

					}
				}

				else {
					List<HoaDon> dsHD = hdDao.getAllDataHD().stream()
							.sorted((hd1, hd2) -> hd1.getMaHoaDon().compareTo(hd2.getMaHoaDon())).toList();
					String s = "";
					if (dsHD.size() != 0) {
						s = dsHD.get(dsHD.size() - 1).getMaHoaDon();
					} else {
						s = "HD0001";
					}
					String maHD = maTuDong.formatMa(s);
					HoaDon hd = new HoaDon(maHD, gioNhanTP.getTime(), dateFormat.parse(date + " " + time),
							nvDAO.timKiemNhanVienTheoMa(manNV).get(0), new KhachHang(maKH, sdtKH, tenKH),
							new KhuyenMai("KM0001"));
					Phong phong = pdao.timKiemPhongChinhXacTheoTenPhong(tenPhongF.getText().trim()).get(0);
					ChiTietHoaDon cthd = new ChiTietHoaDon(phong, hd, new Date(), new Date());
					try {
						if (hdDao.createHD(hd) && cthdDao.createCTPhong(cthd)
								&& pdao.capNhatPhongTheoTinhTrang("Đang thuê", phong.getMaPhong())) {
							JOptionPane.showMessageDialog(this, "Thuê phòng thành công!!!");
							readAllDateToTablePhong2();
							this.dispose();
						}
					} catch (Exception e) {
						// TODO: handle exception
						e.printStackTrace();
					}
				}
			}
			else {
			}
		}
	}

	public class ChuyenPhong extends JFrame {
		private JLabel lbTenPhong, lbTenKhach, lbNgayThue, lbGioThue, lbGioHienTai, lbThoiGianSuDung, lbPhong,
				lbGiaPhong, lbLoaiPhong, lbSucChua;
		private JTextField tfTenPhong, tfTenKhach, tfNgayThue, tfGioThue, tfGioHienTai, tfThoiGianSuDung, tfPhong,
				tfGiaPhong;
		private JComboBox<String> cbLoaiPhong, cbSucChua;
		private JButton btnTimKiem, btnLamMoi, btnQuayLai, btnChuyenPhong;
		private JTable tablePhongTrong;
		private DefaultTableModel modelPhongTrong;
		private String tenPhong;
		private DAOPhong pdao;
		private DAOKhachHang kdao;
		private DAOPhieuDatPhong pdpDao;
		private DAOHoaDon hdDao;
		private DAOCTHD cthdDao;
		private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
		private DecimalFormat tienFormat = new DecimalFormat("###,###,### VNĐ");
		private SimpleDateFormat dayFormat2 = new SimpleDateFormat("dd/MM/yyyy");

		public String getTenPhong() {
			return tenPhong;
		}

		public void setTenPhong(String tenPhong) {
			this.tenPhong = tenPhong;
		}

		public ChuyenPhong(String tenPhong) {
			hdDao = new DAOHoaDon();
			pdao = new DAOPhong();
			kdao = new DAOKhachHang();
			pdpDao = new DAOPhieuDatPhong();
			cthdDao = new DAOCTHD();
			try {
				ConnectDB.getInstance().connect();
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
			setTenPhong(tenPhong);
			setSize(1050, 500);
			setLocationRelativeTo(null);
			Icon imgDel = new ImageIcon("src/img/del.png");
			Icon imgReset = new ImageIcon("src/img/refresh16.png");
			Icon imgEdit = new ImageIcon("src/img/edit.png");
			Icon imgOut = new ImageIcon("src/img/out.png");
			Icon imgSearch = new ImageIcon("src/img/search.png");
			Icon imgCheck = new ImageIcon("src/img/check16.png");
			Icon imgCancel = new ImageIcon("src/img/cancel16.png");
			Icon imgBack = new ImageIcon("src/img/back16.png");
			Icon imgAdd = new ImageIcon("src/img/add16.png");
			Icon imgChange = new ImageIcon("src/img/change16.png");

			JPanel mainPane, topPane, leftPane, rightPane, bottomPane, bottomPaneRight, bottomPaneLeft;
			JLabel lbTieuDe;
			String[] headersSoNguoi = { "Tất cả", "7", "10", "15" };
			String[] headersLoaiPhong = { "Tất cả", "Vip", "Thường" };
			String[] headersTable = { "Mã Phòng", "Tên Phòng", "Loại Phòng", "Sức Chứa", "Giá Phòng" };
			Font font = new Font("Arial", Font.BOLD, 14);

			mainPane = new JPanel(new BorderLayout());

			// set top pane
			topPane = new JPanel();
			topPane.setBackground(Color.decode("#990447"));
			topPane.add(lbTieuDe = new JLabel("CHUYỂN PHÒNG"));
			lbTieuDe.setFont(new Font("Arial", Font.BOLD, 24));

			// set left pane
			leftPane = new JPanel();
			leftPane.setBackground(Color.decode("#cccccc"));
			leftPane.setBorder(BorderFactory.createTitledBorder(new LineBorder(Color.black), "Phòng đang sử dụng"));
			Box box = Box.createVerticalBox();
			Box boxPhong = Box.createHorizontalBox();
			boxPhong.add(lbTenPhong = new JLabel("Tên Phòng:"));
			boxPhong.add(Box.createHorizontalStrut(5));
			boxPhong.add(tfTenPhong = new JTextField(15));
			tfTenPhong.setBorder(null);
			tfTenPhong.setEditable(false);
			tfTenPhong.setBackground(Color.decode("#cccccc"));
			lbTenPhong.setFont(font);
			box.add(boxPhong);
			box.add(Box.createVerticalStrut(15));

			Box boxKhach = Box.createHorizontalBox();
			boxKhach.add(lbTenKhach = new JLabel("Tên Khách Hàng:"));
			boxKhach.add(Box.createHorizontalStrut(5));
			boxKhach.add(tfTenKhach = new JTextField(15));
			tfTenKhach.setBorder(null);
			tfTenKhach.setEditable(false);
			tfTenKhach.setBackground(Color.decode("#cccccc"));
			lbTenKhach.setFont(font);
			box.add(boxKhach);
			box.add(Box.createVerticalStrut(15));

			Box boxNgayThue = Box.createHorizontalBox();
			boxNgayThue.add(lbNgayThue = new JLabel("Ngày Thuê:"));
			boxNgayThue.add(Box.createHorizontalStrut(5));
			boxNgayThue.add(tfNgayThue = new JTextField(15));
			tfNgayThue.setBorder(null);
			tfNgayThue.setEditable(false);
			tfNgayThue.setBackground(Color.decode("#cccccc"));
			lbNgayThue.setFont(font);
			box.add(boxNgayThue);
			box.add(Box.createVerticalStrut(15));

			Box boxGioThue = Box.createHorizontalBox();
			boxGioThue.add(lbGioThue = new JLabel("Giờ Thuê Phòng:"));
			boxGioThue.add(Box.createHorizontalStrut(5));
			boxGioThue.add(tfGioThue = new JTextField(15));
			tfGioThue.setBorder(null);
			tfGioThue.setEditable(false);
			tfGioThue.setBackground(Color.decode("#cccccc"));
			lbGioThue.setFont(font);
			box.add(boxGioThue);
			box.add(Box.createVerticalStrut(15));

			Box boxGioHienTai = Box.createHorizontalBox();
			boxGioHienTai.add(lbGioHienTai = new JLabel("Giờ Hiện Tại:"));
			boxGioHienTai.add(Box.createHorizontalStrut(5));
			boxGioHienTai.add(tfGioHienTai = new JTextField(15));
			tfGioHienTai.setBorder(null);
			tfGioHienTai.setEditable(false);
			tfGioHienTai.setBackground(Color.decode("#cccccc"));
			lbGioHienTai.setFont(font);
			box.add(boxGioHienTai);
			box.add(Box.createVerticalStrut(15));

			Box boxThoiGianSuDung = Box.createHorizontalBox();
			boxThoiGianSuDung.add(lbThoiGianSuDung = new JLabel("Thời Gian Sử Dụng:"));
			boxThoiGianSuDung.add(Box.createHorizontalStrut(5));
			boxThoiGianSuDung.add(tfThoiGianSuDung = new JTextField(15));
			tfThoiGianSuDung.setBorder(null);
			tfThoiGianSuDung.setEditable(false);
			tfThoiGianSuDung.setBackground(Color.decode("#cccccc"));
			lbThoiGianSuDung.setFont(font);
			box.add(boxThoiGianSuDung);
			box.add(Box.createVerticalStrut(15));

			leftPane.add(box);
			// set pane right
			rightPane = new JPanel(new BorderLayout());
			rightPane.setBorder(BorderFactory.createTitledBorder(new LineBorder(Color.black), "Danh sách phòng trống"));
			Box boxBtn = Box.createHorizontalBox();
			Box boxLoaiPhong = Box.createVerticalBox();
			boxLoaiPhong.add(lbLoaiPhong = new JLabel("Loại Phòng"));
			boxLoaiPhong.add(Box.createVerticalStrut(5));
			boxLoaiPhong.add(cbLoaiPhong = new JComboBox<String>(headersLoaiPhong));
			JPanel paneForLoaiPhong = new JPanel();
			paneForLoaiPhong.setBackground(Color.decode("#cccccc"));
			paneForLoaiPhong.add(boxLoaiPhong);
			boxBtn.add(paneForLoaiPhong);
			boxBtn.add(Box.createHorizontalStrut(40));

			Box boxSucChua = Box.createVerticalBox();
			boxSucChua.add(lbSucChua = new JLabel("Sức Chứa"));
			boxSucChua.add(Box.createVerticalStrut(5));
			boxSucChua.add(cbSucChua = new JComboBox<String>(headersSoNguoi));
			boxBtn.add(boxSucChua);
			JPanel paneForSucChua = new JPanel();
			paneForSucChua.setBackground(Color.decode("#cccccc"));
			paneForSucChua.add(boxSucChua);
			boxBtn.add(paneForSucChua);
			boxBtn.add(Box.createHorizontalStrut(40));

			// pane tra cuu
			JPanel paneTraCuuP = new JPanel();
			Box traCuuB = Box.createVerticalBox();
			Box traCuuB1 = Box.createHorizontalBox();
			Box traCuuB2 = Box.createHorizontalBox();

			JPanel panePhongLb = new JPanel();
			panePhongLb.setBackground(Color.decode("#cccccc"));
			panePhongLb.add(lbPhong = new JLabel("Phòng"));
			traCuuB1.add(panePhongLb);

			JPanel panePhongF = new JPanel();
			panePhongF.setBackground(Color.decode("#cccccc"));
			panePhongF.add(tfPhong = new JTextField(15));
			traCuuB1.add(panePhongF);

			traCuuB1.add(btnTimKiem = new ButtonGradient("Tìm kiếm", imgSearch));

			JPanel paneGiaLb = new JPanel();
			paneGiaLb.setBackground(Color.decode("#cccccc"));
			paneGiaLb.add(lbGiaPhong = new JLabel("Giá phòng"));
			traCuuB2.add(paneGiaLb);

			JPanel paneGiaPhongF = new JPanel();
			paneGiaPhongF.setBackground(Color.decode("#cccccc"));
			paneGiaPhongF.add(tfGiaPhong = new JTextField(15));
			traCuuB2.add(paneGiaPhongF);

			traCuuB2.add(btnLamMoi = new ButtonGradient("Làm mới", imgReset));

			traCuuB.add(traCuuB1);
			traCuuB.add(Box.createVerticalStrut(10));
			traCuuB.add(traCuuB2);
			paneTraCuuP.add(traCuuB);
			paneTraCuuP.setBackground(Color.decode("#cccccc"));
			paneTraCuuP.setBorder(BorderFactory.createTitledBorder(new LineBorder(Color.black), "Tra cứu"));

			btnLamMoi.setBackground(Color.decode("#6fa8dc"));
			btnTimKiem.setBackground(Color.decode("#6fa8dc"));
			lbPhong.setPreferredSize(lbGiaPhong.getPreferredSize());
			btnLamMoi.setPreferredSize(btnTimKiem.getPreferredSize());

			boxBtn.add(paneTraCuuP);
			JPanel paneForBoxBtn = new JPanel();
			paneForBoxBtn.setBackground(Color.decode("#cccccc"));
			paneForBoxBtn.add(boxBtn);
			rightPane.add(paneForBoxBtn, BorderLayout.NORTH);

			// Table
			modelPhongTrong = new DefaultTableModel(headersTable, 0);
			tablePhongTrong = new JTable(modelPhongTrong);
			tablePhongTrong.setRowHeight(30);
			tablePhongTrong.setFont(new Font("serif", Font.PLAIN, 14));
			JScrollPane scroll = new JScrollPane(tablePhongTrong, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
					JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
			rightPane.add(scroll, BorderLayout.CENTER);

			// Bottom Pane
			bottomPane = new JPanel(new GridLayout(1, 2));
			bottomPane.setBackground(Color.decode("#cccccc"));

			bottomPaneRight = new JPanel(new FlowLayout(FlowLayout.RIGHT));
			bottomPaneRight.setBackground(Color.decode("#D0BAFB"));
			bottomPaneRight.add(btnChuyenPhong = new ButtonGradient("Chuyển phòng", imgChange));
			btnChuyenPhong.setBackground(Color.decode("#6fa8dc"));

			bottomPaneLeft = new JPanel(new FlowLayout(FlowLayout.LEFT));
			bottomPaneLeft.setBackground(Color.decode("#D0BAFB"));
			bottomPaneLeft.add(btnQuayLai = new ButtonGradient("Quay lại", imgBack));
			btnQuayLai.setBackground(Color.decode("#6fa8dc"));

			bottomPane.add(bottomPaneLeft);
			bottomPane.add(bottomPaneRight);

			mainPane.add(topPane, BorderLayout.NORTH);
			mainPane.add(leftPane, BorderLayout.WEST);
			mainPane.add(rightPane, BorderLayout.CENTER);
			mainPane.add(bottomPane, BorderLayout.SOUTH);

			tfTenPhong.setFont(new Font("Arial", Font.PLAIN, 16));
			tfTenPhong.setBorder(null);
			tfTenPhong.setEditable(false);

			tfTenKhach.setFont(new Font("Arial", Font.PLAIN, 16));
			tfTenKhach.setBorder(null);
			tfTenKhach.setEditable(false);

			tfNgayThue.setFont(new Font("Arial", Font.PLAIN, 16));
			tfNgayThue.setBorder(null);
			tfNgayThue.setEditable(false);

			tfGioThue.setFont(new Font("Arial", Font.PLAIN, 16));
			tfGioThue.setBorder(null);
			tfGioThue.setEditable(false);

			tfGioHienTai.setFont(new Font("Arial", Font.PLAIN, 16));
			tfGioHienTai.setBorder(null);
			tfGioHienTai.setEditable(false);

			tfThoiGianSuDung.setFont(new Font("Arial", Font.PLAIN, 16));
			tfThoiGianSuDung.setBorder(null);
			tfThoiGianSuDung.setEditable(false);

			this.getContentPane().add(mainPane);

			readDataToFieldsTTPhongDangThue();
			readDataToTablePhong();

			btnQuayLai.addActionListener(e -> this.dispose());
			btnLamMoi.addActionListener(e -> {
				tfPhong.setText("");
				tfTenPhong.setText("");
				tfPhong.requestFocus();
			});
			btnTimKiem.addActionListener(e -> xuLyTimKiem());
			btnChuyenPhong.addActionListener(e -> xuLyChuyenPhong());
			cbLoaiPhong.addActionListener(e -> xuLyLocTheoLoaiPhong());
			cbSucChua.addActionListener(e -> xuLyLocTheoSucChua());
		}

		private void xuLyTimKiem() {

		}

		private void xuLyChuyenPhong() {
			Phong p = pdao.timKiemPhongChinhXacTheoTenPhong(
					(String) modelPhongTrong.getValueAt(tablePhongTrong.getSelectedRow(), 1)).get(0);
			
			ChiTietHoaDon cthd = cthdDao.timKiemCTHDTheoTenPhong(tenPhong).stream()
																		  .sorted((maHD1, maHD2) -> maHD2.getHd().getMaHoaDon().compareTo(maHD1.getHd().getMaHoaDon()))
																		  .toList()
																		  .get(0);
			System.out.println(cthd.getHd().getMaHoaDon());
			HoaDon hd = hdDao.timKiemHoaDonTheoMaHD(cthd.getHd().getMaHoaDon()).get(0);
			ChiTietHoaDon chiTietHD = new ChiTietHoaDon(p, hd, new Date(),
					new Date());
			cthd.setThoiGianTraPhong(new Date());
			try {
				if (cthdDao.createCTPhong(chiTietHD) && cthdDao.capNhatGioTraPhong(cthd)) {
					if (pdao.capNhatTinhTrangPhongTheoTenPhong("Còn trống", tenPhong)
							&& pdao.capNhatTinhTrangPhongTheoTenPhong("Đang thuê", p.getTenPhong()))
						JOptionPane.showMessageDialog(this, "Chuyển phòng thành công!!");
					readAllDateToTablePhong3();
				}
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		}

		private void xuLyLocTheoLoaiPhong() {

		}

		private void xuLyLocTheoSucChua() {

		}

		private void readDataToFieldsTTPhongDangThue() {
			Phong phong = pdao.timKiemPhongTheoTenPhong(tenPhong).get(0);
			String s = cthdDao.timKiemCTHDTheoMaPhong(phong.getMaPhong());
			String[] str = s.split(",");
			String tenKH = str[1];
			String thoiGian = str[2];
			String[] str2 = thoiGian.split(" ");
			String gio = str2[1];
			String[] time = gio.split(":");
			String hour = time[0];
			String minute = time[1];
			int h = Integer.valueOf(hour);
			int m = Integer.valueOf(minute);
			LocalTime tgNhanPhong = LocalTime.of(h, m);
			LocalTime tgHienTai = LocalTime.now();

			List<ChiTietHoaDon> dsCTHD = cthdDao.timKiemCTHDTheoTenPhong(tenPhong);
			ChiTietHoaDon cthd2 = dsCTHD.get(dsCTHD.size() - 1);
			cthd2.setThoiGianTraPhong(new Date());
			cthdDao.capNhatGioTraPhong(cthd2);
			tfThoiGianSuDung.setText(cthd2.tinhThoiLuong() + " Phút");
			
			tfTenPhong.setText(tenPhong.trim());
			tfTenKhach.setText(tenKH);
			tfNgayThue.setText(dayFormat2.format(cthd2.getThoiGianNhanPhong()));
			tfGioThue.setText(tgNhanPhong.format(formatter));
			tfGioHienTai.setText(tgHienTai.format(formatter));
		}

		private void readDataToTablePhong() {
			List<Phong> dsP = pdao.getAllDataForTableDatPhong().stream()
					.sorted((p1, p2) -> p1.getTenPhong().compareTo(p2.getTenPhong())).collect(Collectors.toList());
			for (Phong p : dsP) {
				if (p.getTinhTrangPhong().equalsIgnoreCase("Còn Trống"))
					modelPhongTrong
							.addRow(new Object[] { p.getMaPhong(), p.getTenPhong(), p.getLoaiPhong().getTenLoaiPhong(),
									p.getLoaiPhong().getSucChua(), tienFormat.format(p.getLoaiPhong().getGiaLoaiPhong())});
			}
		}
	}

	public class PhongCho extends JFrame {
		private JButton nhanPBtn, huyPBtn, timKiemSDTBtn;
		private JTable phieuDatPTable;
		private DefaultTableModel phieuDatPModel;
		private JTextField sdtF;
		private JComboBox<String> tinhTrangPhieuB;
		private DAOPhong pdao;
		private DAOKhachHang kdao;
		private DAOPhieuDatPhong pdpDao;
		private DAOHoaDon hdDao;
		private DAOCTHD cthdDao;
		private SimpleDateFormat formatDate = new SimpleDateFormat("yyyy-MM-dd");
		private SimpleDateFormat formatTime;
		private SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		private MaTuDong maTuDong = new MaTuDong();

		public PhongCho() {
			formatTime = new SimpleDateFormat("hh:mm:ss");
//			formatTime.setTimeZone(TimeZone.getTimeZone("Asia/VietNam"));

			setSize(1200, 500);
			setLocationRelativeTo(null);
			pdpDao = new DAOPhieuDatPhong();
			hdDao = new DAOHoaDon();
			cthdDao = new DAOCTHD();
			pdao = new DAOPhong();

			try {
				ConnectDB.getInstance().connect();
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}

			Icon imgAdd = new ImageIcon("src/img/add2.png");
			Icon imgDel = new ImageIcon("src/img/del.png");
			Icon imgReset = new ImageIcon("src/img/refresh16.png");
			Icon imgEdit = new ImageIcon("src/img/edit.png");
			Icon imgOut = new ImageIcon("src/img/out.png");
			Icon imgSearch = new ImageIcon("src/img/search.png");
			Icon imgCheck = new ImageIcon("src/img/check16.png");
			Icon imgCancel = new ImageIcon("src/img/cancel16.png");

			Border blackLine = BorderFactory.createLineBorder(Color.black);

			JPanel panePDP = new JPanel(new BorderLayout());
			JPanel paneBtnPDP = new JPanel();
			JPanel titlePanel = new JPanel();
			JLabel locTinhTrangLb, sdtLb, tieuDeLb;
			String[] headersTablePDP = { "Mã đặt phòng", "Tên phòng", "Ngày đặt", "Giờ đặt", "Tên khách", "SĐT khách",
					"Nhân viên", "Tình trạng" };

			// set tiêu đề
			Font font = new Font("Arial", Font.BOLD, 24);
			titlePanel.setBackground(Color.decode("#990447"));
			titlePanel.add(tieuDeLb = new JLabel("PHÒNG CHỜ"));
			tieuDeLb.setFont(font);

			// Table PDP
			phieuDatPModel = new DefaultTableModel(headersTablePDP, 0);
			phieuDatPTable = new JTable(phieuDatPModel);
			phieuDatPTable.setRowHeight(30);
			phieuDatPTable.setFont(new Font("serif", Font.PLAIN, 15));
			phieuDatPTable.setAutoCreateRowSorter(true);
			phieuDatPTable.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
			phieuDatPTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			JScrollPane scrollPDPTable = new JScrollPane(phieuDatPTable, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
					JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
			panePDP.add(scrollPDPTable, BorderLayout.CENTER);
			phieuDatPTable.setRowHeight(25);

			// Box tra cứu phiếu đặt phòng
			Box btnPDPBox = Box.createHorizontalBox();

			// Box Combox box lọc theo tình trạng pdp
			String[] headersTinhTrangPDP = { "Tất cả", "chưa nhận phòng", "nhận phòng" };
			Box locTinhTrangBox = Box.createHorizontalBox();
			locTinhTrangBox.add(locTinhTrangLb = new JLabel("Lọc theo tình trạng"));
			locTinhTrangBox.add(Box.createHorizontalStrut(5));
			locTinhTrangBox.add(tinhTrangPhieuB = new JComboBox<>(headersTinhTrangPDP));
			JPanel paneForLocBox = new JPanel();
			paneForLocBox.setBackground(Color.decode("#D0BAFB"));
			paneForLocBox.add(locTinhTrangBox);
			btnPDPBox.add(paneForLocBox);
			btnPDPBox.add(Box.createHorizontalStrut(150));

			// Box tìm kiếm khách theo sdt
			Box sdtBox = Box.createHorizontalBox();

			JPanel paneForSdtLb = new JPanel();
			paneForSdtLb.add(sdtLb = new JLabel("SĐT Khách hàng"));
			paneForSdtLb.setBackground(Color.decode("#D0BAFB"));
			sdtBox.add(paneForSdtLb);

			JPanel paneForSdtF = new JPanel();
			paneForSdtF.add(sdtF = new JTextField(15));
			paneForSdtF.setBackground(Color.decode("#D0BAFB"));
			sdtBox.add(paneForSdtF);
			sdtBox.add(timKiemSDTBtn = new ButtonGradient("Tìm kiếm", imgSearch));
			timKiemSDTBtn.setBackground(Color.decode("#D0BAFB"));

			JPanel paneForSdtBox = new JPanel();
			paneForSdtBox.setBackground(Color.decode("#D0BAFB"));
			paneForSdtBox.add(sdtBox);
			btnPDPBox.add(paneForSdtBox);
			paneBtnPDP.add(btnPDPBox);
			paneBtnPDP.setBackground(Color.decode("#D0BAFB"));

			panePDP.add(paneBtnPDP, BorderLayout.NORTH);
			panePDP.setBorder(BorderFactory.createTitledBorder(blackLine, "Danh sách phiếu đặt phòng"));
			panePDP.setBackground(Color.decode("#D0BAFB"));

			// Box nút hủy, nhận phòng
//			Box btnNhanHuyBox = Box.createHorizontalBox();
//			btnNhanHuyBox.add(Box.createHorizontalStrut(1000));
//			btnNhanHuyBox.add(huyPBtn = new JButton("Hủy", imgCancel));
//			btnNhanHuyBox.add(Box.createHorizontalStrut(40));
//			btnNhanHuyBox.add(nhanPBtn = new JButton("Nhận", imgCheck));

			JPanel paneForBtnNhanHuyBox = new JPanel(new FlowLayout(FlowLayout.RIGHT));
			paneForBtnNhanHuyBox.add(huyPBtn = new ButtonGradient("Hủy", imgCancel));
			paneForBtnNhanHuyBox.add(Box.createHorizontalStrut(50));
			paneForBtnNhanHuyBox.add(nhanPBtn = new ButtonGradient("Nhận", imgCheck));
			paneForBtnNhanHuyBox.setBackground(Color.decode("#D0BAFB"));
			panePDP.add(paneForBtnNhanHuyBox, BorderLayout.SOUTH);

			JPanel pnlMain = new JPanel(new BorderLayout());
			pnlMain.add(titlePanel, BorderLayout.NORTH);
			pnlMain.add(panePDP, BorderLayout.CENTER);
			this.getContentPane().add(pnlMain);

			huyPBtn.setBackground(Color.decode("#D0BAFB"));
			nhanPBtn.setBackground(Color.decode("#D0BAFB"));

			readDateToTablePhongCho();

			huyPBtn.addActionListener(e -> xuLyHuyPhongCho());
			nhanPBtn.addActionListener(e -> xuLyNhanPhong());
			tinhTrangPhieuB.addActionListener(e -> xuLyLocTheoTinhTrangPDP());
			timKiemSDTBtn.addActionListener(e -> xuLyTimKiemSDT());
		}

		private void readDateToTablePhongCho() {
			phieuDatPModel.setRowCount(0);
			List<PhieuDatPhong> dsPDP = pdpDao.getAllDataPDP();
			for (PhieuDatPhong pdp : dsPDP) {
				String date = formatDate.format(pdp.getThoiGianDatPhong());
				String time = formatTime.format(pdp.getThoiGianDatPhong());
				String tinhTrang = "";
				if (pdp.getTinhTrangPDP() == 0)
					tinhTrang = "Chưa nhận";
				else
					tinhTrang = "Nhận rồi";
				if (tinhTrang.equalsIgnoreCase("Chưa nhận")) {
					String[] s = pdp.getThoiGianDatPhong().toString().split(" ");
					String str = s[1];
					String[] t = str.split(":");

					phieuDatPModel.addRow(new Object[] { pdp.getMaPDP(), pdp.getPhong().getTenPhong(), date,
							t[0] + ":" + t[1], pdp.getKhachHang().getTenKH(), pdp.getKhachHang().getSdthoai(),
							pdp.getNhanVien().getTenNV(), tinhTrang });
				}
			}
		}

		private void xuLyLocTheoTinhTrangPDP() {

		}

		private void xuLyHuyPhongCho() {
			try {
				PhieuDatPhong pdp = pdpDao
						.timKiemPDPTheoMa((String) phieuDatPModel.getValueAt(phieuDatPTable.getSelectedRow(), 0))
						.get(0);
				if(JOptionPane.showConfirmDialog(this, "Bạn có chắc muốn hủy phòng chờ ?") == 0)
					if(pdpDao.delete(pdp.getMaPDP())) {
						JOptionPane.showMessageDialog(this, "Hủy Phòng Chờ thành công!!!");
						readDateToTablePhongCho();
						readAllDateToTablePhong2();
					}
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		}

		private void xuLyNhanPhong() {
			try {
				PhieuDatPhong pdp = pdpDao
						.timKiemPDPTheoMa((String) phieuDatPModel.getValueAt(phieuDatPTable.getSelectedRow(), 0))
						.get(0);
				System.out.println(pdp.getPhong().getTenPhong());
				List<HoaDon> dsHD = hdDao.getAllDataHD().stream()
						.sorted((hd1, hd2) -> hd1.getMaHoaDon().compareTo(hd2.getMaHoaDon())).toList();
				String s = dsHD.get(dsHD.size() - 1).getMaHoaDon();
				String maHD = maTuDong.formatMa(s);
				HoaDon hd = new HoaDon(maHD, LocalTime.now(), new Date(), pdp.getNhanVien(), pdp.getKhachHang(),
						new KhuyenMai("KM0001"));
				ChiTietHoaDon chiTietHoaDon = new ChiTietHoaDon(pdp.getPhong(), hd, new Date(), new Date());
				if (hdDao.createHD(hd) && cthdDao.createCTPhong(chiTietHoaDon)
						&& pdpDao.capNhatTinhTrangPDP(0, pdp.getMaPDP())
						&& pdao.capNhatTinhTrangPhongTheoTenPhong("Đang thuê", pdp.getPhong().getTenPhong())) {
					JOptionPane.showMessageDialog(this, "Nhận phòng thành công!");
					readDateToTablePhongCho();
					readAllDateToTablePhong2();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		private void xuLyTimKiemSDT() {

		}
	}

	public class DichVuPhong extends JFrame {
		private JTextField tfMaPDP, tfTenKH, tfSDT, tfTenPhong, tfTimKiemDV1, tfTimKiemDV2;
		private JButton btnQuayLai, btnThem, btnCapNhat, btnXoa;
		private JTable tableDVKho, tableDVPhong;
		private DefaultTableModel model1, model2;

		public DichVuPhong() {
			// TODO Auto-generated constructor stub
			setSize(1200, 600);
			setLocationRelativeTo(null);

			Icon imgDel = new ImageIcon("src/img/del.png");
			Icon imgReset = new ImageIcon("src/img/refresh16.png");
			Icon imgEdit = new ImageIcon("src/img/edit.png");
			Icon imgOut = new ImageIcon("src/img/out.png");
			Icon imgSearch = new ImageIcon("src/img/search.png");
			Icon imgCheck = new ImageIcon("src/img/check16.png");
			Icon imgCancel = new ImageIcon("src/img/cancel16.png");
			Icon imgBack = new ImageIcon("src/img/back16.png");
			Icon imgAdd = new ImageIcon("src/img/add16.png");
			Icon imgChange = new ImageIcon("src/img/change16.png");

			JPanel mainPane, topPane, bottomPane, thongTinPane;
			JPanel paneForTableDV, paneForTableDVPhong;
			Box boxForTable;
			JLabel lbMaPDP, lbTenKH, lbSDT, lbTenPhong, lbTieuDe, lbTimKiemDV1, lbTimKiemDV2;
			Font font = new Font("Arial", Font.BOLD, 24);
			Border border = new LineBorder(Color.black);

			// set top Pane
			lbTieuDe = new JLabel("DỊCH VỤ PHÒNG");
			lbTieuDe.setFont(font);
			topPane = new JPanel();
			topPane.setBackground(Color.decode("#990447"));
			topPane.add(lbTieuDe);

			// Thông tin phiếu đặt phòng
			bottomPane = new JPanel(new BorderLayout());
			thongTinPane = new JPanel(new BorderLayout());
			Box boxForThongTin = Box.createHorizontalBox();

			Box box1 = Box.createVerticalBox();
			Box boxForMaPDP = Box.createHorizontalBox();
			boxForMaPDP.add(lbMaPDP = new JLabel("Mã Hóa Đơn: "));
			boxForMaPDP.add(tfMaPDP = new JTextField(15));
			Box boxForTenPhong = Box.createHorizontalBox();
			boxForTenPhong.add(lbTenPhong = new JLabel("Tên Phòng:"));
			boxForTenPhong.add(tfTenPhong = new JTextField(15));
			box1.add(Box.createVerticalStrut(10));
			box1.add(boxForMaPDP);
			box1.add(Box.createVerticalStrut(20));
			box1.add(boxForTenPhong);
			box1.add(Box.createVerticalStrut(10));

			Box box2 = Box.createVerticalBox();
			Box boxForTenKH = Box.createHorizontalBox();
			boxForTenKH.add(lbTenKH = new JLabel("Tên Khách Hàng:  "));
			boxForTenKH.add(tfTenKH = new JTextField(15));
			Box boxForSDT = Box.createHorizontalBox();
			boxForSDT.add(lbSDT = new JLabel("Số Điện Thoại:"));
			boxForSDT.add(tfSDT = new JTextField(15));
			box2.add(Box.createVerticalStrut(10));
			box2.add(boxForTenKH);
			box2.add(Box.createVerticalStrut(20));
			box2.add(boxForSDT);
			box2.add(Box.createVerticalStrut(10));

			tfMaPDP.setBackground(Color.decode("#D0BAFB"));
			tfMaPDP.setBorder(null);
			tfMaPDP.setEditable(false);
			tfTenPhong.setBackground(Color.decode("#D0BAFB"));
			tfTenPhong.setBorder(null);
			tfTenPhong.setEditable(false);
			tfTenKH.setBackground(Color.decode("#D0BAFB"));
			tfTenKH.setBorder(null);
			tfTenKH.setEditable(false);
			tfSDT.setBackground(Color.decode("#D0BAFB"));
			tfSDT.setBorder(null);
			tfSDT.setEditable(false);

			boxForThongTin.add(Box.createHorizontalStrut(10));
			boxForThongTin.add(box1);
			boxForThongTin.add(Box.createHorizontalStrut(50));
			boxForThongTin.add(box2);
			boxForThongTin.add(Box.createHorizontalStrut(50));

			JPanel paneForBackBtn = new JPanel();
			paneForBackBtn.setBackground(Color.decode("#D0BAFB"));
			paneForBackBtn.add(btnQuayLai = new ButtonGradient("Quay Lại", imgBack));
			btnQuayLai.setBackground(Color.decode("#6fa8dc"));

			thongTinPane.add(boxForThongTin, BorderLayout.CENTER);
			thongTinPane.add(paneForBackBtn, BorderLayout.EAST);
			thongTinPane.setBackground(Color.decode("#D0BAFB"));
			bottomPane.add(thongTinPane, BorderLayout.NORTH);

			// Pane for table
			boxForTable = Box.createHorizontalBox();

			// Table dịch vụ kho
			paneForTableDV = new JPanel(new BorderLayout());
			paneForTableDV.setBorder(BorderFactory.createTitledBorder(border, "Danh Sách Dịch Vụ"));
			paneForTableDV.setBackground(Color.decode("#D0BAFB"));
			JPanel paneForBtnTableDV = new JPanel();
			Box boxForPaneForBtnTableDV = Box.createHorizontalBox();
			JPanel paneForTimKiemDV1 = new JPanel();
			paneForTimKiemDV1.setBackground(Color.decode("#D0BAFB"));
			paneForTimKiemDV1.add(lbTimKiemDV1 = new JLabel("Tìm Kiếm Dịch Vụ: "));
			paneForTimKiemDV1.add(tfTimKiemDV1 = new JTextField(15));
			boxForPaneForBtnTableDV.add(paneForTimKiemDV1);
			boxForPaneForBtnTableDV.add(Box.createHorizontalStrut(100));
			boxForPaneForBtnTableDV.add(btnThem = new ButtonGradient("Thêm", imgAdd));
			btnThem.setBackground(Color.decode("#6fa8dc"));
			paneForBtnTableDV.add(boxForPaneForBtnTableDV);
			paneForBtnTableDV.setBackground(Color.decode("#D0BAFB"));

			String[] headers1 = { "STT", "Tên Dịch Vụ", "Đơn Giá", "Đơn Vị", "Số Lượng Tồn" };
			model1 = new DefaultTableModel(headers1, 0);
			tableDVKho = new JTable(model1);
			tableDVKho.setRowHeight(25);
			tableDVKho.getColumnModel().getColumn(1).setPreferredWidth(200);

			JScrollPane scroll1 = new JScrollPane(tableDVKho, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
					JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
			paneForTableDV.add(paneForBtnTableDV, BorderLayout.NORTH);
			paneForTableDV.add(scroll1, BorderLayout.CENTER);

			// Table dịch vụ phòng
			paneForTableDVPhong = new JPanel(new BorderLayout());
			paneForTableDVPhong.setBorder(BorderFactory.createTitledBorder(border, "Dịch Vụ Đã Thêm"));
			paneForTableDVPhong.setBackground(Color.decode("#D0BAFB"));
			JPanel paneForBtnTableDVP = new JPanel();
			Box boxForPaneForBtnTableDVP = Box.createHorizontalBox();
			JPanel paneForTimKiemDV2 = new JPanel();
			paneForTimKiemDV2.add(lbTimKiemDV2 = new JLabel("Tìm Kiếm Dịch Vụ: "));
			paneForTimKiemDV2.add(tfTimKiemDV2 = new JTextField(15));
			paneForTimKiemDV2.setBackground(Color.decode("#D0BAFB"));
			boxForPaneForBtnTableDVP.add(paneForTimKiemDV2);
			boxForPaneForBtnTableDVP.add(Box.createHorizontalStrut(100));
			boxForPaneForBtnTableDVP.add(btnXoa = new ButtonGradient("Xóa", imgDel));
			btnXoa.setBackground(Color.decode("#6fa8dc"));
			paneForBtnTableDVP.add(boxForPaneForBtnTableDVP);
			paneForBtnTableDVP.setBackground(Color.decode("#D0BAFB"));

			String[] headers2 = { "STT", "Tên Dịch Vụ", "Đơn Giá", "Số Lượng", "Thành Tiền" };
			model2 = new DefaultTableModel(headers2, 0);
			tableDVPhong = new JTable(model2);
			tableDVPhong.setRowHeight(25);

			tableDVPhong.getColumnModel().getColumn(1).setPreferredWidth(200);

			TableColumnModel colModelDVPhong = tableDVPhong.getColumnModel();
			TableColumn colTableDVPhong = colModelDVPhong.getColumn(3);
			colTableDVPhong.setCellEditor(new MySpinnerEditor());

			JScrollPane scroll2 = new JScrollPane(tableDVPhong, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
					JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
			paneForTableDVPhong.add(paneForBtnTableDVP, BorderLayout.NORTH);
			paneForTableDVPhong.add(scroll2, BorderLayout.CENTER);

			boxForTable.add(paneForTableDV);
			boxForTable.add(paneForTableDVPhong);
			bottomPane.add(boxForTable);

			mainPane = new JPanel(new BorderLayout());
			mainPane.add(topPane, BorderLayout.NORTH);
			mainPane.add(bottomPane, BorderLayout.CENTER);
			this.getContentPane().add(mainPane);

			tfTenPhong.setFont(new Font("sanserif", Font.PLAIN, 15));
			tfTenKH.setFont(new Font("sanserif", Font.PLAIN, 15));
			tfMaPDP.setFont(new Font("sanserif", Font.PLAIN, 15));
			tfSDT.setFont(new Font("sanserif", Font.PLAIN, 15));
			lbMaPDP.setFont(new Font("sanserif", Font.BOLD, 15));
			lbTenKH.setFont(new Font("sanserif", Font.BOLD, 15));
			lbSDT.setFont(new Font("sanserif", Font.BOLD, 15));
			lbTenPhong.setFont(new Font("sanserif", Font.BOLD, 15));

			lbTenPhong.setPreferredSize(lbTenKH.getPreferredSize());
			lbMaPDP.setPreferredSize(lbTenKH.getPreferredSize());
			lbSDT.setPreferredSize(lbTenKH.getPreferredSize());

			readDataToFieldThongTin();
			readDataToTableDichVu();
			readDataToTableDichVuPhong();

			tableDVPhong.addMouseListener(new MouseListener() {

				@Override
				public void mouseReleased(MouseEvent e) {
					// TODO Auto-generated method stub

				}

				@Override
				public void mousePressed(MouseEvent e) {
					// TODO Auto-generated method stub
				}

				@Override
				public void mouseExited(MouseEvent e) {
					// TODO Auto-generated method stub
				}

				@Override
				public void mouseEntered(MouseEvent e) {
					// TODO Auto-generated method stub
				}

				@Override
				public void mouseClicked(MouseEvent e) {
					// TODO Auto-generated method stub
					xuLyTangGiamSoLuongDichVuKho();
				}
			});

			btnQuayLai.addActionListener(e -> this.dispose());
			btnThem.addActionListener(e -> xuLyThemDVPhong());
			btnXoa.addActionListener(e -> xuLyXoaDVPhong());
		}

		private void xuLyThemDVPhong() {
			if (tableDVKho.getSelectedRow() != -1) {
				DichVu dv = dvDAO.timKiemDichVuTheoTenDV((String) model1.getValueAt(tableDVKho.getSelectedRow(), 1))
						.get(0);
				ctdvPhongDAO.capNhatSoLuongDichVu(dv.getSoLuong() - 1,
						(String) model1.getValueAt(tableDVKho.getSelectedRow(), 1));
				try {
					if (ctdvPhongDAO.createCTDVPhong(new CTDVPhong(new HoaDon(tfMaPDP.getText()), dv, 1))) {
						readDataToTableDichVuPhong();
						readDataToTableDichVu();
						JOptionPane.showMessageDialog(this, "Thêm dịch vụ thành công!!!");
					}
				} catch (Exception e) {
					// TODO: handle exception
					JOptionPane.showMessageDialog(this, "Thêm dịch vụ không thành công!!!");
				}
			} else {
				JOptionPane.showMessageDialog(this, "Hãy chọn dịch vụ bạn muốn thêm vào phòng!!!");
			}
		}

		private void xuLyXoaDVPhong() {
			if (tableDVPhong.getSelectedRow() != -1) {
				DichVu dv = dvDAO.timKiemDichVuTheoTenDV((String) model2.getValueAt(tableDVPhong.getSelectedRow(), 1))
						.get(0);
				CTDVPhong ctdvPhong = ctdvPhongDAO.timKiemCTDVPhongTheoTenDV(tfMaPDP.getText(), dv.getMaDichVu())
						.get(0);
				try {
					int soLuong = (int) model2.getValueAt(tableDVPhong.getSelectedRow(), 3);
					int soLuongBanDau = dv.getSoLuong();

					if (ctdvPhongDAO.capNhatSoLuongDichVu(soLuong + soLuongBanDau,
							(String) model2.getValueAt(tableDVPhong.getSelectedRow(), 1))
							&& ctdvPhongDAO.delete(ctdvPhong.getDichVu().getMaDichVu())) {
						readDataToTableDichVuPhong();
						readDataToTableDichVu();
						JOptionPane.showMessageDialog(this, "Xóa dịch vụ phòng thành công!!!");
					}
				} catch (Exception e) {
					// TODO: handle exception
					JOptionPane.showMessageDialog(this, "Xóa dịch vụ phòng không thành công!!!");
					e.printStackTrace();
				}
			}
		}

		private void readDataToFieldThongTin() {
			Phong p = phongDao
					.timKiemPhongChinhXacTheoTenPhong((String) phongModel.getValueAt(phongTable.getSelectedRow(), 0))
					.get(0);
			List<ChiTietHoaDon> dsCTHD = cthdDao
					.timKiemCTHDTheoTenPhong((String) phongModel.getValueAt(phongTable.getSelectedRow(), 0));
			ChiTietHoaDon cthd = dsCTHD.get(dsCTHD.size() - 1);
			List<HoaDon> dsHD = hdDAO.timKiemHoaDonTheoMaHD(cthd.getHd().getMaHoaDon());
			HoaDon hd = dsHD.get(dsHD.size() - 1);
			tfMaPDP.setText(cthd.getHd().getMaHoaDon());
			tfTenPhong.setText(p.getTenPhong());
			tfTenKH.setText(hd.getKh().getTenKH());
			tfSDT.setText(hd.getKh().getSdthoai());
		}

		private void readDataToTableDichVu() {
			model1.setRowCount(0);
			List<entity.DichVu> dsDV = dvDAO.getAllDichVu();
			int i = 0;
			for (entity.DichVu dv : dsDV) {
				if (dv.getSoLuong() > 0)
					model1.addRow(
							new Object[] { ++i, dv.getTenDichVu(), dv.getDonGia(), dv.getDonVi(), dv.getSoLuong() });
			}
		}

		private void readDataToTableDichVuPhong() {
			model2.setRowCount(0);
			List<CTDVPhong> dsCTDVPhong = ctdvPhongDAO.timKiemCTDVPhongTheoMaHD(tfMaPDP.getText());
			int i = 0;
			for (CTDVPhong ct : dsCTDVPhong) {
				model2.addRow(new Object[] { ++i, ct.getDichVu().getTenDichVu(), ct.getDichVu().getDonGia(),
						ct.getSoLuong(), ct.tinhTienDV() });
			}
		}

		private void xuLyTangGiamSoLuongDichVuKho() {
			DichVu dv = dvDAO.timKiemDichVuTheoTenDV((String) model2.getValueAt(tableDVPhong.getSelectedRow(), 1))
					.get(0);
			CTDVPhong ctdvPhong = ctdvPhongDAO.timKiemCTDVPhongTheoTenDV(tfMaPDP.getText().trim(), dv.getMaDichVu())
					.get(0);
			int soLuongBanDau = ctdvPhong.getSoLuong();
			int soLuongCapNhat = (int) model2.getValueAt(tableDVPhong.getSelectedRow(), 3);
			int soLuongTinhToan = dv.getSoLuong() + (soLuongBanDau - soLuongCapNhat);

			String maHD = tfMaPDP.getText().trim();
			String maDV = dvDAO.timKiemDichVuTheoTenDV((String) model2.getValueAt(tableDVPhong.getSelectedRow(), 1))
					.get(0).getMaDichVu();

			if (soLuongCapNhat > 0) {
				if (soLuongTinhToan >= 0) {
					try {
						ctdvPhongDAO.capNhatSoLuongDichVu(soLuongTinhToan, dv.getTenDichVu());
						ctdvPhongDAO.capNhatSoLuongCTDVPhong(soLuongCapNhat, maDV, maHD);
						readDataToTableDichVu();
						readDataToTableDichVuPhong();

					} catch (Exception e) {
						// TODO: handle exception
						e.printStackTrace();
					}
				} else {
					JOptionPane.showMessageDialog(this, "Số lượng dịch vụ trong kho không đủ!!!");
					model2.setValueAt(ctdvPhong.getSoLuong(), tableDVPhong.getSelectedRow(), 3);
				}
			} else {
				JOptionPane.showMessageDialog(this, "Số lượng phải lớn hơn 0 !!!");
				model2.setValueAt(ctdvPhong.getSoLuong(), tableDVPhong.getSelectedRow(), 3);
			}
		}

//		private void xuLyTangGiamSoLuongDichVu() {
//			int soLuong = (int) model2.getValueAt(tableDVPhong.getSelectedRow(), 3);
//			if(soLuong <= 0) {
//				JOptionPane.showMessageDialog(this, "Số lượng phải lớn hơn 0 !!!");
//				model2.setValueAt(1, tableDVPhong.getSelectedRow(), 3);
//			}
//			else {
//				String maHD = tfMaPDP.getText().trim();
//				String maDV = dvDAO.timKiemDichVuTheoTenDV((String) model2.getValueAt(tableDVPhong.getSelectedRow(), 1)).get(0).getMaDichVu();
//				try {
//					ctdvPhongDAO.capNhatSoLuongCTDVPhong(soLuong, maDV, maHD);
//				} catch (Exception e) {
//					// TODO: handle exception
//					e.printStackTrace();
//				}
//			}
//		}

	}

	public class TinhTien extends JFrame {
		private JTextField tfMaHD, tfTenKH, tfNgayThanhToan, tfTenNhanVien, tfSDTKhach, tfGioThanhToan, tfTienNhan,
				tfTienThua, tfTienPhong, tfTienDichVu, tfGiamGia, tfTongCong, tfThue, tfThanhTien;
		private JButton btnQuayLai, btnInHD, btnThanhToan;
		private JTable tablePhong, tableDichVu;
		private DefaultTableModel modelPhong, modelDichVu;
		private SimpleDateFormat gioFormat = new SimpleDateFormat("hh:mm");
		private DateTimeFormatter gioFormatForDate = DateTimeFormatter.ofPattern("HH:mm");
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy hh:mm");

		public TinhTien() {
			// TODO Auto-generated constructor stub
			setSize(650, 700);
			setLocationRelativeTo(null);
			dateFormat.setTimeZone(TimeZone.getTimeZone("Asia/Ho_Chi_Minh"));
			Icon imgDel = new ImageIcon("src/img/del.png");
			Icon imgReset = new ImageIcon("src/img/refresh16.png");
			Icon imgEdit = new ImageIcon("src/img/edit.png");
			Icon imgOut = new ImageIcon("src/img/out.png");
			Icon imgSearch = new ImageIcon("src/img/search.png");
			Icon imgCheck = new ImageIcon("src/img/check16.png");
			Icon imgCancel = new ImageIcon("src/img/cancel16.png");
			Icon imgBack = new ImageIcon("src/img/back16.png");
			Icon imgAdd = new ImageIcon("src/img/add16.png");
			Icon imgChange = new ImageIcon("src/img/change16.png");

			JLabel lbMaHD, lbTenKH, lbNgayThanhToan, lbTenNhanVien, lbSDTKhach, lbGioThanhToan, lbTienNhan, lbTienThua,
					lbTienPhong, lbTienDichVu, lbGiamGia, lbTongCong, lbThue, lbThanhTien, lbTieuDe, lbTablePhong,
					lbTableDichVu;

			JPanel mainPane, topPane, bottomPane, thongTinHDPane, tablePhongPane, tableDVPane, leftBottomPane,
					rightBottomPane;
			Font font = new Font("Arial", Font.BOLD, 24);
			Border border = new LineBorder(Color.black);

			// Tieu De
			mainPane = new JPanel(new BorderLayout());
			topPane = new JPanel();
			topPane.setBackground(Color.decode("#990447"));
			lbTieuDe = new JLabel("Tính Tiền");
			lbTieuDe.setFont(font);
			topPane.add(lbTieuDe);

			bottomPane = new JPanel(new BorderLayout());
			// Thông tin hóa đơn
			thongTinHDPane = new JPanel();
			Box box = Box.createVerticalBox();
			JPanel paneForThongTinHD = new JPanel(new FlowLayout(FlowLayout.RIGHT));

			Box boxForThongTinHDLeft = Box.createVerticalBox();
			Box boxForMaHD = Box.createHorizontalBox();
			boxForMaHD.add(lbMaHD = new JLabel("Mã Hóa Đơn: "));
			boxForMaHD.add(tfMaHD = new JTextField(15));
			Box boxForTenKH = Box.createHorizontalBox();
			boxForTenKH.add(lbTenKH = new JLabel("Tên Khách Hàng: "));
			boxForTenKH.add(tfTenKH = new JTextField(15));
			Box boxForNgayThanhToan = Box.createHorizontalBox();
			boxForNgayThanhToan.add(lbNgayThanhToan = new JLabel("Ngày Thanh Toán: "));
			boxForNgayThanhToan.add(tfNgayThanhToan = new JTextField(15));

			boxForThongTinHDLeft.add(Box.createVerticalStrut(10));
			boxForThongTinHDLeft.add(boxForMaHD);
			boxForThongTinHDLeft.add(Box.createVerticalStrut(10));
			boxForThongTinHDLeft.add(boxForTenKH);
			boxForThongTinHDLeft.add(Box.createVerticalStrut(10));
			boxForThongTinHDLeft.add(boxForNgayThanhToan);
			boxForThongTinHDLeft.add(Box.createVerticalStrut(10));

			Box boxForThongTinHDRight = Box.createVerticalBox();
			Box boxForTenNV = Box.createHorizontalBox();
			boxForTenNV.add(lbTenNhanVien = new JLabel("Tên Nhân Viên: "));
			boxForTenNV.add(tfTenNhanVien = new JTextField(15));
			Box boxForSDTK = Box.createHorizontalBox();
			boxForSDTK.add(lbSDTKhach = new JLabel("Số Điện Thoại Khách: "));
			boxForSDTK.add(tfSDTKhach = new JTextField(15));
			Box boxForGioThanhToan = Box.createHorizontalBox();
			boxForGioThanhToan.add(lbGioThanhToan = new JLabel("Giờ Thanh Toán: "));
			boxForGioThanhToan.add(tfGioThanhToan = new JTextField(15));

			boxForThongTinHDRight.add(Box.createVerticalStrut(10));
			boxForThongTinHDRight.add(boxForTenNV);
			boxForThongTinHDRight.add(Box.createVerticalStrut(10));
			boxForThongTinHDRight.add(boxForSDTK);
			boxForThongTinHDRight.add(Box.createVerticalStrut(10));
			boxForThongTinHDRight.add(boxForGioThanhToan);
			boxForThongTinHDRight.add(Box.createVerticalStrut(10));

			lbTenNhanVien.setPreferredSize(lbSDTKhach.getPreferredSize());
			lbGioThanhToan.setPreferredSize(lbSDTKhach.getPreferredSize());
			lbMaHD.setPreferredSize(lbSDTKhach.getPreferredSize());
			lbTenKH.setPreferredSize(lbSDTKhach.getPreferredSize());
			lbNgayThanhToan.setPreferredSize(lbSDTKhach.getPreferredSize());

			tfMaHD.setBorder(null);
			tfMaHD.setEditable(false);
			tfMaHD.setBackground(Color.white);
			tfTenKH.setBorder(null);
			tfTenKH.setEditable(false);
			tfTenKH.setBackground(Color.white);
			tfNgayThanhToan.setBorder(null);
			tfNgayThanhToan.setEditable(false);
			tfNgayThanhToan.setBackground(Color.white);
			tfTenNhanVien.setBorder(null);
			tfTenNhanVien.setEditable(false);
			tfTenNhanVien.setBackground(Color.white);
			tfSDTKhach.setBorder(null);
			tfSDTKhach.setEditable(false);
			tfSDTKhach.setBackground(Color.white);
			tfGioThanhToan.setBorder(null);
			tfGioThanhToan.setEditable(false);
			tfGioThanhToan.setBackground(Color.white);
			tfMaHD.setFont(new Font("sanserif", Font.PLAIN, 13));
			tfTenKH.setFont(new Font("sanserif", Font.PLAIN, 13));
			tfNgayThanhToan.setFont(new Font("sanserif", Font.PLAIN, 13));
			tfTenNhanVien.setFont(new Font("sanserif", Font.PLAIN, 13));
			tfSDTKhach.setFont(new Font("sanserif", Font.PLAIN, 13));
			tfGioThanhToan.setFont(new Font("sanserif", Font.PLAIN, 13));
			lbMaHD.setFont(new Font("sanserif", Font.BOLD, 13));
			lbTenKH.setFont(new Font("sanserif", Font.BOLD, 13));
			lbNgayThanhToan.setFont(new Font("sanserif", Font.BOLD, 13));
			lbTenNhanVien.setFont(new Font("sanserif", Font.BOLD, 13));
			lbSDTKhach.setFont(new Font("sanserif", Font.BOLD, 13));
			lbGioThanhToan.setFont(new Font("sanserif", Font.BOLD, 13));

			paneForThongTinHD.setBackground(Color.white);
			paneForThongTinHD.add(boxForThongTinHDLeft);
			paneForThongTinHD.add(boxForThongTinHDRight);
			thongTinHDPane.setBackground(Color.white);
			thongTinHDPane.add(paneForThongTinHD);

			// Thông tin Phòng
			tablePhongPane = new JPanel(new BorderLayout());
			JPanel titleTablePhongPane = new JPanel();
			titleTablePhongPane.setBackground(Color.white);
			lbTablePhong = new JLabel("Thông tin chi tiết sử dụng phòng");
			lbTablePhong.setFont(new Font("sanserif", Font.PLAIN, 18));
			titleTablePhongPane.add(lbTablePhong);
			String[] headerTablePhong = { "Tên Phòng", "Loại Phòng", "Giờ Nhận", "Giờ Trả", "Thời Lượng(p)",
					"Giá Phòng", "Thành Tiền" };
			modelPhong = new DefaultTableModel(headerTablePhong, 0);
			tablePhong = new JTable(modelPhong);
			tablePhong.setRowHeight(25);
			tablePhong.getColumnModel().getColumn(6).setPreferredWidth(120);

			JScrollPane scrollTablePhong = new JScrollPane(tablePhong, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
					JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
			tablePhongPane.add(scrollTablePhong, BorderLayout.CENTER);
			tablePhongPane.add(titleTablePhongPane, BorderLayout.NORTH);

			// Thông tin dịch vụ
			tableDVPane = new JPanel(new BorderLayout());
			JPanel titleTableDVPane = new JPanel();
			titleTableDVPane.setBackground(Color.white);
			lbTableDichVu = new JLabel("Thông tin chi tiết dịch vụ");
			lbTableDichVu.setFont(new Font("sanserif", Font.PLAIN, 18));
			titleTableDVPane.add(lbTableDichVu);
			String[] headerTableDV = { "STT", "Tên Dịch Vụ", "Đơn Vị", "Số Lượng", "Đơn Giá", "Thành Tiền" };
			modelDichVu = new DefaultTableModel(headerTableDV, 0);
			tableDichVu = new JTable(modelDichVu);
			tableDichVu.setRowHeight(25);
			tableDichVu.getColumnModel().getColumn(1).setPreferredWidth(220);
			tableDichVu.getColumnModel().getColumn(5).setPreferredWidth(120);
			JScrollPane scrollTableDichVu = new JScrollPane(tableDichVu, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
					JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
			tableDVPane.add(scrollTableDichVu, BorderLayout.CENTER);
			tableDVPane.add(titleTableDVPane, BorderLayout.NORTH);

			box.add(thongTinHDPane);
			box.add(tablePhongPane);
			box.add(tableDVPane);
			bottomPane.add(box, BorderLayout.CENTER);

			// Thông tin tiền
			Box box2 = Box.createHorizontalBox();
			leftBottomPane = new JPanel(new BorderLayout());
			Box boxForTienNhan = Box.createHorizontalBox();
			boxForTienNhan.add(lbTienNhan = new JLabel("Tiền Nhận:   "));
			boxForTienNhan.add(tfTienNhan = new JTextField(15));

			JPanel paneForTienNhan = new JPanel();
			paneForTienNhan.add(boxForTienNhan);
			paneForTienNhan.setBackground(Color.white);

			Box boxForTienThua = Box.createHorizontalBox();
			boxForTienThua.add(lbTienThua = new JLabel("Tiền Thừa: "));
			boxForTienThua.add(tfTienThua = new JTextField(15));

			JPanel paneForTienThua = new JPanel();
			paneForTienThua.add(boxForTienThua);
			paneForTienThua.setBackground(Color.white);

			Box boxForTienNhanThua = Box.createVerticalBox();
			boxForTienNhanThua.add(Box.createVerticalStrut(50));
			boxForTienNhanThua.add(paneForTienNhan);
			boxForTienNhanThua.add(Box.createVerticalStrut(20));
			boxForTienNhanThua.add(paneForTienThua);
			boxForTienNhanThua.setBackground(Color.white);

			tfTienThua.setEditable(false);
			lbTienThua.setPreferredSize(lbTienNhan.getPreferredSize());

			JPanel paneForBtnBack = new JPanel(new FlowLayout(FlowLayout.LEFT));
			paneForBtnBack.setBackground(Color.white);
			paneForBtnBack.add(btnQuayLai = new ButtonGradient("Quay lại", imgBack));
			btnQuayLai.setBackground(Color.decode("#6fa8dc"));

			JPanel paneForBoxForTienNhanThua = new JPanel();
			paneForBoxForTienNhanThua.setBackground(Color.white);
			paneForBoxForTienNhanThua.add(boxForTienNhanThua);
			leftBottomPane.add(paneForBoxForTienNhanThua, BorderLayout.NORTH);
			leftBottomPane.add(paneForBtnBack, BorderLayout.SOUTH);
			box2.add(leftBottomPane);

			//
			rightBottomPane = new JPanel(new BorderLayout());
			rightBottomPane.setBackground(Color.white);

			Box boxForTienPhong = Box.createHorizontalBox();
			boxForTienPhong.add(lbTienPhong = new JLabel("Tiền Phòng: "));
			boxForTienPhong.add(tfTienPhong = new JTextField(15));

			Box boxForTienDichVu = Box.createHorizontalBox();
			boxForTienDichVu.add(lbTienDichVu = new JLabel("Tiền Dịch Vụ: "));
			boxForTienDichVu.add(tfTienDichVu = new JTextField(15));

			Box boxForGiamGia = Box.createHorizontalBox();
			boxForGiamGia.add(lbGiamGia = new JLabel("Giảm Giá: "));
			boxForGiamGia.add(tfGiamGia = new JTextField(15));

			Box boxForTongCong = Box.createHorizontalBox();
			boxForTongCong.add(lbTongCong = new JLabel("Tổng Cộng: "));
			boxForTongCong.add(tfTongCong = new JTextField(15));

			Box boxForThue = Box.createHorizontalBox();
			boxForThue.add(lbThue = new JLabel("Thuế VAT: "));
			boxForThue.add(tfThue = new JTextField(15));

			Box boxForThanhTien = Box.createHorizontalBox();
			boxForThanhTien.add(lbThanhTien = new JLabel("Thành Tiền: "));
			boxForThanhTien.add(tfThanhTien = new JTextField(15));

			lbTienPhong.setPreferredSize(lbTienDichVu.getPreferredSize());
			lbGiamGia.setPreferredSize(lbTienDichVu.getPreferredSize());
			lbTongCong.setPreferredSize(lbTienDichVu.getPreferredSize());
			lbTongCong.setPreferredSize(lbTienDichVu.getPreferredSize());
			lbThue.setPreferredSize(lbTienDichVu.getPreferredSize());
			lbThanhTien.setPreferredSize(lbTienDichVu.getPreferredSize());

			tfTienPhong.setBorder(null);
			tfTienPhong.setEditable(false);
			tfTienPhong.setBackground(Color.white);
			tfTienDichVu.setBorder(null);
			tfTienDichVu.setEditable(false);
			tfTienDichVu.setBackground(Color.white);
			tfGiamGia.setBorder(null);
			tfGiamGia.setEditable(false);
			tfGiamGia.setBackground(Color.white);
			tfTongCong.setBorder(null);
			tfTongCong.setEditable(false);
			tfTongCong.setBackground(Color.white);
			tfThue.setBorder(null);
			tfThue.setEditable(false);
			tfThue.setBackground(Color.white);
			tfThanhTien.setBorder(null);
			tfThanhTien.setEditable(false);
			tfThanhTien.setBackground(Color.white);

			Box boxForRight = Box.createVerticalBox();
			boxForRight.add(boxForTienPhong);
			boxForRight.add(Box.createVerticalStrut(10));
			boxForRight.add(boxForTienDichVu);
			boxForRight.add(Box.createVerticalStrut(10));
			boxForRight.add(boxForGiamGia);
			boxForRight.add(Box.createVerticalStrut(10));
			boxForRight.add(boxForTongCong);
			boxForRight.add(Box.createVerticalStrut(10));
			boxForRight.add(boxForThue);
			boxForRight.add(Box.createVerticalStrut(10));
			boxForRight.add(boxForThanhTien);

			JPanel paneForBtnThanhToan = new JPanel(new FlowLayout(FlowLayout.RIGHT));
			paneForBtnThanhToan.setBackground(Color.white);
			paneForBtnThanhToan.add(btnInHD = new ButtonGradient("In Hóa Đơn"));
			btnInHD.setBackground(Color.decode("#6fa8dc"));
			paneForBtnThanhToan.add(Box.createHorizontalStrut(20));
			paneForBtnThanhToan.add(btnThanhToan = new ButtonGradient("Thanh Toán"));
			btnThanhToan.setBackground(Color.decode("#6fa8dc"));

			JPanel paneForBoxForRight = new JPanel();
			paneForBoxForRight.add(boxForRight);
			rightBottomPane.add(paneForBoxForRight, BorderLayout.CENTER);
			paneForBoxForRight.setBackground(Color.white);
			rightBottomPane.add(paneForBtnThanhToan, BorderLayout.SOUTH);

			box2.add(rightBottomPane);

			bottomPane.add(box2, BorderLayout.SOUTH);
			box2.setBackground(Color.white);

			mainPane.add(topPane, BorderLayout.NORTH);
			mainPane.add(bottomPane, BorderLayout.CENTER);
			this.getContentPane().add(mainPane);

			readDataToFieldThongTin();
			readDataToTablePhong();
			readDataToTableDichVu();
			xuLyTinhTienDienVaoThongTin();
			btnQuayLai.addActionListener(e -> this.dispose());
			btnThanhToan.addActionListener(e -> xuLyThanhToan());
			btnInHD.addActionListener(e -> xuLyInHD());
			tfTienNhan.addActionListener(e -> xuLyTienThua());

		}
		
		private void xuLyTienThua() {
			List<ChiTietHoaDon> dsCTHD = cthdDao.timKiemCTHDTheoMaHD(tfMaHD.getText().trim());
			double tongTienPhong = dsCTHD.stream().mapToDouble(cthd -> cthd.tinhTienPhong()).sum();
			List<CTDVPhong> dsCTDVP = ctdvPhongDAO.timKiemCTDVPhongTheoMaHD(tfMaHD.getText().trim());
			double tongTienDV = dsCTDVP.stream().mapToDouble(ctdv -> ctdv.tinhTienDV()).sum();
			double tong = tongTienPhong + tongTienDV;
			double thanhTien = tong + tong * 0.01;
			
			double tienNhan = Double.valueOf(tfTienNhan.getText().trim());
			double tienThua = tienNhan - thanhTien;	
			tfTienThua.setText(formatter.format(tienThua));
		}

		private void xuLyThanhToan() {
			List<ChiTietHoaDon> dsCTHD2 = cthdDao.timKiemCTHDTheoMaHD(tfMaHD.getText().trim());
			double tongTienPhong = dsCTHD2.stream().mapToDouble(cthd -> cthd.tinhTienPhong()).sum();
			List<CTDVPhong> dsCTDVP = ctdvPhongDAO.timKiemCTDVPhongTheoMaHD(tfMaHD.getText().trim());
			double tongTienDV = dsCTDVP.stream().mapToDouble(ctdv -> ctdv.tinhTienDV()).sum();
			double tong = tongTienPhong + tongTienDV;
			double thanhTien = tong + tong * 0.01;
			
			Phong p = phongDao
					.timKiemPhongChinhXacTheoTenPhong((String) phongModel.getValueAt(phongTable.getSelectedRow(), 0))
					.get(0);
			List<ChiTietHoaDon> dsCTHD = cthdDao
					.timKiemCTHDTheoTenPhong((String) phongModel.getValueAt(phongTable.getSelectedRow(), 0));
			ChiTietHoaDon cthd = dsCTHD.get(dsCTHD.size() - 1);
			List<HoaDon> dsHD = hdDAO.timKiemHoaDonTheoMaHD(cthd.getHd().getMaHoaDon());
			HoaDon hd = dsHD.get(dsHD.size() - 1);
			hd.setGioThanhToan(LocalTime.now());
			hd.setTongHoaDon(Double.valueOf(thanhTien));
			try {
				if (hdDAO.capNhatHoaDonSauKhiThanhToan(hd)) {
					if (phongDao.capNhatPhongTheoTinhTrang("Còn trống", p.getMaPhong())) {
						JOptionPane.showMessageDialog(this, "Thanh toán thành công");
						readAllDateToTablePhong3();
						tinhTien.dispose();
					}
				} else {
					JOptionPane.showMessageDialog(this, "Thanh toán không thành công");
				}
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		}
		
		private ArrayList<inforUsingService>listSerVice(){
			ArrayList<inforUsingService>list = new ArrayList<inforUsingService>();
			modelDichVu = (DefaultTableModel) tableDichVu.getModel();
			int n = tableDichVu.getRowCount();
			for(int i = 0; i < n; i++) {
				list.add(new inforUsingService(modelDichVu.getValueAt(i, 1).toString(),
						modelDichVu.getValueAt(i, 2).toString(),
						modelDichVu.getValueAt(i, 3).toString(),
						modelDichVu.getValueAt(i, 4).toString(),
						modelDichVu.getValueAt(i, 5).toString()));
			}
			return list;
		}
		
		private void xuLyInHD() {
			try {
				String filePath = "src\\resources\\inHD.jrxml";
				
				List<inforUsingService> list2 = new ArrayList<>();
				list2 = listSerVice();
				
//				infor
				
				
//				JRBeanCollectionDataSource dataSource1 = new JRBeanCollectionDataSource(list1);
				JRBeanCollectionDataSource dataSource2 = new JRBeanCollectionDataSource(list2);
				Map<String, Object> parameters = new HashMap<>();
				parameters.put("maHD", tfMaHD.getText());
				parameters.put("tenKH", tfTenKH.getText());
				parameters.put("ngayThanhToan", tfNgayThanhToan.getText());
				parameters.put("tenNV", tfTenNhanVien.getText());
				parameters.put("soDienThoaiKhach", tfSDTKhach.getText());
				parameters.put("gioThanhToan", tfGioThanhToan.getText());
				parameters.put("tienPhong", tfTienPhong.getText());
				parameters.put("tienDV", tfTienDichVu.getText());
				parameters.put("tongCong", tfTongCong.getText());
				parameters.put("thueVAT", tfThue.getText());
				parameters.put("thanhTien", tfThanhTien.getText());
				parameters.put("tienNhan", tfTienNhan.getText());
				parameters.put("tienThua", tfTienThua.getText());
//				parameters.put("thongTinSuDung", dataSource1);
				parameters.put("thongTinDichVu", dataSource2);
				
				JasperReport report = JasperCompileManager.compileReport(filePath);
				
				JasperPrint print = JasperFillManager.fillReport(report, parameters,dataSource2);
				JasperViewer view = new JasperViewer(print, false);
				view.setVisible(true);
				
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		}

		private void readDataToFieldThongTin() {
			List<ChiTietHoaDon> dsCTHD = cthdDao
					.timKiemCTHDTheoTenPhong((String) phongModel.getValueAt(phongTable.getSelectedRow(), 0))
					.stream().sorted((maHD1, maHD2) -> maHD1.getHd().getMaHoaDon().compareTo(maHD2.getHd().getMaHoaDon()))
					.toList();
			ChiTietHoaDon cthd = dsCTHD.get(dsCTHD.size() - 1);
			List<HoaDon> dsHD = hdDAO.timKiemHoaDonTheoMaHD(cthd.getHd().getMaHoaDon());
			HoaDon hd = dsHD.get(dsHD.size() - 1);
			tfMaHD.setText(hd.getMaHoaDon());
			tfTenNhanVien.setText(hd.getNv().getTenNV());
			tfTenKH.setText(hd.getKh().getTenKH());
			tfSDTKhach.setText(hd.getKh().getSdthoai());
			tfNgayThanhToan.setText(dateFormat.format(hd.getNgayThanhToan()).split(" ")[0]);
			tfGioThanhToan.setText(LocalTime.now().format(gioFormatForDate));
		}

		private void readDataToTablePhong() {
			modelDichVu.setRowCount(0);
			List<ChiTietHoaDon> dsCTHD = cthdDao.timKiemCTHDTheoMaHD(tfMaHD.getText().trim()).stream()
					.sorted((cthd1, cthd2) -> cthd1.getThoiGianNhanPhong().compareTo(cthd2.getThoiGianNhanPhong()))
					.toList();
			ChiTietHoaDon cthdSetDate = dsCTHD.get(dsCTHD.size() - 1);
			cthdSetDate.setThoiGianTraPhong(new Date());
			System.out.println(cthdSetDate.getThoiGianTraPhong());
			cthdDao.capNhatGioTraPhong(cthdSetDate);
			try {
				for (ChiTietHoaDon cthd : dsCTHD) {
					Phong phong = phongDao.timKiemPhongTheoTenPhong(cthd.getPhong().getTenPhong()).get(0);
					String s = cthdDao.timKiemCTHDTheoMaPhong(phong.getMaPhong());
					String[] str = s.split(",");
					String thoiGian = str[2];
					String[] str2 = thoiGian.split(" ");
					String gio = str2[1];
					String[] time = gio.split(":");
					String hour = time[0];
					String minute = time[1];
					int h = Integer.valueOf(hour);
					int m = Integer.valueOf(minute);
					LocalTime tgNhanPhong = LocalTime.of(h, m);
					LocalTime tgHienTai = LocalTime.now();

					modelPhong.addRow(new Object[] { cthd.getPhong().getTenPhong(),
							cthd.getPhong().getLoaiPhong().getTenLoaiPhong(), tgNhanPhong,
							dateFormat.format(cthd.getThoiGianTraPhong()).split(" ")[1], cthd.tinhThoiLuong(),
							formatter.format(cthd.getPhong().getLoaiPhong().getGiaLoaiPhong()), formatter.format(cthd.tinhTienPhong()) });
				}
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		}

		private void xuLyTinhTienDienVaoThongTin() {
			tfThue.setText("10%");
			List<ChiTietHoaDon> dsCTHD = cthdDao.timKiemCTHDTheoMaHD(tfMaHD.getText().trim());
			double tongTienPhong = dsCTHD.stream().mapToDouble(cthd -> cthd.tinhTienPhong()).sum();
			tfTienPhong.setText(formatter.format(tongTienPhong) + "");
			List<CTDVPhong> dsCTDVP = ctdvPhongDAO.timKiemCTDVPhongTheoMaHD(tfMaHD.getText().trim());
			double tongTienDV = dsCTDVP.stream().mapToDouble(ctdv -> ctdv.tinhTienDV()).sum();
			tfTienDichVu.setText(formatter.format(tongTienDV) + "");
			double tong = tongTienPhong + tongTienDV;
			double thanhTien = tong + tong * 0.01;
			tfTongCong.setText(formatter.format(tong) + "");
			tfThanhTien.setText(formatter.format(thanhTien) + "");
		}

		private void readDataToTableDichVu() {
			modelDichVu.setRowCount(0);
			List<CTDVPhong> dsCTDVPhong = ctdvPhongDAO.timKiemCTDVPhongTheoMaHD(tfMaHD.getText());
			int i = 0;
			for (CTDVPhong ct : dsCTDVPhong) {
				modelDichVu.addRow(new Object[] { ++i, ct.getDichVu().getTenDichVu(), ct.getDichVu().getDonVi(),
						ct.getSoLuong(), formatter.format(ct.getDichVu().getDonGia()), formatter.format(ct.tinhTienDV()) });
			}
		}

	}

	public class ChiTietPhong extends JFrame {
		private JTextField tfMaPDP, tfTenKhach, tfNgayNhanP, tfTenNhanVien, tfSDT, tfGioNhan, tfTenPhong, tfSucChua,
				tfLoaiPhong, tfGiaPhong, tfGioThuePhong, tfGioHienTai, tfThoiGianSuDung, tfTienPhongCu,
				tfTienPhongHienTai, tfTongTienPhong, tfTongTienDich, tfGiamGia, tfTongCong, tfThueVAT, tfThanhTien;
		private JTable tableDichVu;
		private DefaultTableModel model;
		private JButton btnThemDV, btnChuyenPhong, btnQuayLai;
		private DateTimeFormatter gioFormatForDate = DateTimeFormatter.ofPattern("HH:mm");

		public ChiTietPhong() {
			setSize(1000, 600);
			setLocationRelativeTo(null);

			Icon imgDel = new ImageIcon("src/img/del.png");
			Icon imgReset = new ImageIcon("src/img/refresh16.png");
			Icon imgEdit = new ImageIcon("src/img/edit.png");
			Icon imgOut = new ImageIcon("src/img/out.png");
			Icon imgSearch = new ImageIcon("src/img/search.png");
			Icon imgCheck = new ImageIcon("src/img/check16.png");
			Icon imgCancel = new ImageIcon("src/img/cancel16.png");
			Icon imgBack = new ImageIcon("src/img/back16.png");
			Icon imgAdd = new ImageIcon("src/img/add16.png");
			Icon imgChange = new ImageIcon("src/img/change16.png");

			JPanel mainPane, topPane, bottomPane, pDPPane, thongTinPhongPane, thongTinDVPane, thongTinPDPPane, btnPane;

			JLabel lbMaPDP, lbTenKhach, lbNgayNhanP, lbTenNhanVien, lbSDT, lbGioNhan, lbTenPhong, lbSucChua,
					lbLoaiPhong, lbGiaPhong, lbGioThuePhong, lbGioHienTai, lbThoiGianSuDung, lbTienPhongCu,
					lbTienPhongHienTai, lbTongTienPhong, lbTongTienDich, lbGiamGia, lbTongCong, lbThueVAT, lbThanhTien,
					lbTuaDe;

			Border border = new LineBorder(Color.black);

			// set Top Pane
			topPane = new JPanel();
			lbTuaDe = new JLabel("CHI TIẾT PHÒNG");
			lbTuaDe.setFont(new Font("Arial", Font.BOLD, 24));
			topPane.add(lbTuaDe);
			topPane.setBackground(Color.decode("#990447"));

			// set Bottom Pane
			bottomPane = new JPanel(new BorderLayout());
			pDPPane = new JPanel();
			pDPPane.setBackground(Color.decode("#e6dbd1"));
			Box boxForPDPPane = Box.createHorizontalBox();

			// set up cột đầu tiên trong pane phiếu đặt phòng
			Box box1 = Box.createVerticalBox();
			Box boxForMaPDP = Box.createHorizontalBox();
			boxForMaPDP.add(lbMaPDP = new JLabel("Mã Hóa Đơn: "));
			boxForMaPDP.add(tfMaPDP = new JTextField(15));
			box1.add(boxForMaPDP);
			box1.add(Box.createVerticalStrut(20));

			Box boxForTenNhanVien = Box.createHorizontalBox();
			boxForTenNhanVien.add(lbTenNhanVien = new JLabel("Tên Nhân Viên: "));
			boxForTenNhanVien.add(tfTenNhanVien = new JTextField(15));
			box1.add(boxForTenNhanVien);
			boxForPDPPane.add(box1);
			boxForPDPPane.add(Box.createHorizontalStrut(10));

			// set up cột thứ hai trong pane phiếu đặt phòng
			Box box2 = Box.createVerticalBox();
			Box boxForTenKhach = Box.createHorizontalBox();
			boxForTenKhach.add(lbTenKhach = new JLabel("Tên Khách Hàng: "));
			boxForTenKhach.add(tfTenKhach = new JTextField(15));
			box2.add(boxForTenKhach);
			box2.add(Box.createVerticalStrut(20));

			Box boxForSDT = Box.createHorizontalBox();
			boxForSDT.add(lbSDT = new JLabel("Số Điện Thoại: "));
			boxForSDT.add(tfSDT = new JTextField(15));
			box2.add(boxForSDT);
			boxForPDPPane.add(box2);
			boxForPDPPane.add(Box.createHorizontalStrut(10));

			// set up cột thứ ba trong pane phiếu đặt phòng
			Box box3 = Box.createVerticalBox();
			Box boxForNgayNhan = Box.createHorizontalBox();
			boxForNgayNhan.add(lbNgayNhanP = new JLabel("Ngày Nhận Phòng: "));
			boxForNgayNhan.add(tfNgayNhanP = new JTextField(15));
			box3.add(boxForNgayNhan);
			box3.add(Box.createVerticalStrut(20));

			Box boxForGioNhan = Box.createHorizontalBox();
			boxForGioNhan.add(lbGioNhan = new JLabel("Giờ Nhận Phòng: "));
			boxForGioNhan.add(tfGioNhan = new JTextField(15));
			box3.add(boxForGioNhan);
			boxForPDPPane.add(box3);

			// Thong tin phong hien tai, thong tin dich vu
			JPanel pane = new JPanel(new BorderLayout());
			thongTinPhongPane = new JPanel();
			Box boxForThongTinPhong = Box.createVerticalBox();

			Box boxForTenPhong = Box.createHorizontalBox();
			boxForTenPhong.add(lbTenPhong = new JLabel("Tên Phòng:"));
			boxForTenPhong.add(Box.createHorizontalStrut(5));
			boxForTenPhong.add(tfTenPhong = new JTextField(15));

			Box boxForSucChua = Box.createHorizontalBox();
			boxForSucChua.add(lbSucChua = new JLabel("Sức Chứa:"));
			boxForSucChua.add(Box.createHorizontalStrut(5));
			boxForSucChua.add(tfSucChua = new JTextField(15));

			Box boxForLoaiPhong = Box.createHorizontalBox();
			boxForLoaiPhong.add(lbLoaiPhong = new JLabel("Loại Phòng:"));
			boxForLoaiPhong.add(Box.createHorizontalStrut(5));
			boxForLoaiPhong.add(tfLoaiPhong = new JTextField(15));

			Box boxForGiaPhong = Box.createHorizontalBox();
			boxForGiaPhong.add(lbGiaPhong = new JLabel("Giá Phòng:"));
			boxForGiaPhong.add(Box.createHorizontalStrut(5));
			boxForGiaPhong.add(tfGiaPhong = new JTextField(15));

			Box boxForGioThuePhong = Box.createHorizontalBox();
			boxForGioThuePhong.add(lbGioThuePhong = new JLabel("Giờ Thuê Phòng:"));
			boxForGioThuePhong.add(Box.createHorizontalStrut(5));
			boxForGioThuePhong.add(tfGioThuePhong = new JTextField(15));

			Box boxForGioHienTai = Box.createHorizontalBox();
			boxForGioHienTai.add(lbGioHienTai = new JLabel("Giờ Hiện Tại:"));
			boxForGioHienTai.add(Box.createHorizontalStrut(5));
			boxForGioHienTai.add(tfGioHienTai = new JTextField(15));

			Box boxForThoiGian = Box.createHorizontalBox();
			boxForThoiGian.add(lbThoiGianSuDung = new JLabel("Thời Gian Sử Dụng: "));
			// boxForThoiGian.add(Box.createHorizontalStrut(5));
			boxForThoiGian.add(tfThoiGianSuDung = new JTextField(15));

			boxForThongTinPhong.add(Box.createVerticalStrut(5));
			boxForThongTinPhong.add(boxForTenPhong);
			boxForThongTinPhong.add(Box.createVerticalStrut(20));
			boxForThongTinPhong.add(boxForSucChua);
			boxForThongTinPhong.add(Box.createVerticalStrut(20));
			boxForThongTinPhong.add(boxForLoaiPhong);
			boxForThongTinPhong.add(Box.createVerticalStrut(20));
			boxForThongTinPhong.add(boxForGiaPhong);
			boxForThongTinPhong.add(Box.createVerticalStrut(20));
			boxForThongTinPhong.add(boxForGioThuePhong);
			boxForThongTinPhong.add(Box.createVerticalStrut(20));
			boxForThongTinPhong.add(boxForGioHienTai);
			boxForThongTinPhong.add(Box.createVerticalStrut(20));
			boxForThongTinPhong.add(boxForThoiGian);
			boxForThongTinPhong.add(Box.createVerticalStrut(20));

			thongTinPhongPane.add(boxForThongTinPhong);
			thongTinPhongPane.setBackground(Color.decode("#cccccc"));
			thongTinPhongPane.setBorder(BorderFactory.createTitledBorder(border, "Thông tin phòng hiện tại"));
			pane.add(thongTinPhongPane, BorderLayout.WEST);

			// set thong tin dich vu
			String[] headersDichVu = { "STT", "Tên dịch vụ", "Đơn vị", "Số lượng", "Đơn giá" };
			model = new DefaultTableModel(headersDichVu, 20);
			tableDichVu = new JTable(model);
			tableDichVu.setRowHeight(25);
			JScrollPane scroll = new JScrollPane(tableDichVu, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
					JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
			scroll.setBackground(Color.decode("#cccccc"));
			scroll.setBorder(BorderFactory.createTitledBorder(border, "Thông tin dịch vụ"));
			pane.add(scroll, BorderLayout.CENTER);

			// set tổng tiền phiếu đặt phòng
			thongTinPDPPane = new JPanel();
			btnPane = new JPanel();

			Box boxForTTPDP = Box.createHorizontalBox();
			Box boxForCot1 = Box.createVerticalBox();
			Box boxForCot2 = Box.createVerticalBox();

			Box boxForTienPhongCu = Box.createHorizontalBox();
			boxForTienPhongCu.add(lbTienPhongCu = new JLabel("Tên Phòng Cũ (nếu có): "));
			boxForTienPhongCu.add(tfTienPhongCu = new JTextField(15));

			Box boxForTienPhongHienTai = Box.createHorizontalBox();
			boxForTienPhongHienTai.add(lbTienPhongHienTai = new JLabel("Tiền Phòng Hiện Tại:"));
			boxForTienPhongHienTai.add(Box.createHorizontalStrut(5));
			boxForTienPhongHienTai.add(tfTienPhongHienTai = new JTextField(15));

			Box boxForTongTienPhong = Box.createHorizontalBox();
			boxForTongTienPhong.add(lbTongTienPhong = new JLabel("Tổng Tiền Phòng:"));
			boxForTongTienPhong.add(Box.createHorizontalStrut(5));
			boxForTongTienPhong.add(tfTongTienPhong = new JTextField(15));

			Box boxForTongTienDich = Box.createHorizontalBox();
			boxForTongTienDich.add(lbTongTienDich = new JLabel("Tổng Tiền Dịch:"));
			boxForTongTienDich.add(Box.createHorizontalStrut(5));
			boxForTongTienDich.add(tfTongTienDich = new JTextField(15));

			boxForCot1.add(boxForTienPhongCu);
			boxForCot1.add(Box.createVerticalStrut(10));
			boxForCot1.add(boxForTienPhongHienTai);
			boxForCot1.add(Box.createVerticalStrut(10));
			boxForCot1.add(boxForTongTienPhong);
			boxForCot1.add(Box.createVerticalStrut(10));
			boxForCot1.add(boxForTongTienDich);

			boxForTTPDP.add(Box.createHorizontalStrut(20));
			boxForTTPDP.add(boxForCot1);
			boxForTTPDP.add(Box.createHorizontalStrut(100));

			Box boxForGiamGia = Box.createHorizontalBox();
			boxForGiamGia.add(lbGiamGia = new JLabel("Giảm Giá:"));
			boxForGiamGia.add(Box.createHorizontalStrut(5));
			boxForGiamGia.add(tfGiamGia = new JTextField(15));

			Box boxForTongCong = Box.createHorizontalBox();
			boxForTongCong.add(lbTongCong = new JLabel("Tổng Cộng:"));
			boxForTongCong.add(Box.createHorizontalStrut(5));
			boxForTongCong.add(tfTongCong = new JTextField(15));

			Box boxForThue = Box.createHorizontalBox();
			boxForThue.add(lbThueVAT = new JLabel("Thuế VAT:"));
			boxForThue.add(Box.createHorizontalStrut(5));
			boxForThue.add(tfThueVAT = new JTextField(15));

			Box boxForThanhTien = Box.createHorizontalBox();
			boxForThanhTien.add(lbThanhTien = new JLabel("Thành Tiền: "));
			boxForThanhTien.add(tfThanhTien = new JTextField(15));

			boxForCot2.add(boxForGiamGia);
			boxForCot2.add(Box.createVerticalStrut(10));
			boxForCot2.add(boxForTongCong);
			boxForCot2.add(Box.createVerticalStrut(10));
			boxForCot2.add(boxForThue);
			boxForCot2.add(Box.createVerticalStrut(10));
			boxForCot2.add(boxForThanhTien);

			boxForTTPDP.add(boxForCot2);
			boxForTTPDP.add(Box.createHorizontalStrut(20));

			thongTinPDPPane.add(boxForTTPDP);
			thongTinPDPPane.setBackground(Color.decode("#cccccc"));
			thongTinPDPPane.setBorder(BorderFactory.createTitledBorder(border, "Tổng Tiền Phiếu Đặt Phòng"));

			JPanel pane2 = new JPanel(new BorderLayout());
			pane2.add(thongTinPDPPane, BorderLayout.WEST);

			// set pane button
			btnPane = new JPanel();
			Box boxForBtnPane = Box.createVerticalBox();
			boxForBtnPane.add(Box.createVerticalStrut(80));
			boxForBtnPane.add(btnQuayLai = new ButtonGradient("Quay lại", imgBack));
			boxForBtnPane.add(Box.createVerticalStrut(10));
			btnPane.add(boxForBtnPane);
			btnPane.setBackground(Color.decode("#e6dbd1"));

			btnQuayLai.setBackground(Color.decode("#6fa8dc"));
			pane2.add(btnPane, BorderLayout.CENTER);

			// set text field của pdp pane
			tfMaPDP.setBorder(null);
			tfMaPDP.setEditable(false);
			tfMaPDP.setBackground(Color.decode("#e6dbd1"));
			tfTenNhanVien.setBorder(null);
			tfTenNhanVien.setEditable(false);
			tfTenNhanVien.setBackground(Color.decode("#e6dbd1"));
			tfTenKhach.setBorder(null);
			tfTenKhach.setEditable(false);
			tfTenKhach.setBackground(Color.decode("#e6dbd1"));
			tfSDT.setBorder(null);
			tfSDT.setEditable(false);
			tfSDT.setBackground(Color.decode("#e6dbd1"));
			tfNgayNhanP.setBorder(null);
			tfNgayNhanP.setEditable(false);
			tfNgayNhanP.setBackground(Color.decode("#e6dbd1"));
			tfGioNhan.setBorder(null);
			tfGioNhan.setEditable(false);
			tfGioNhan.setBackground(Color.decode("#e6dbd1"));
			tfTenPhong.setBorder(null);
			tfTenPhong.setEditable(false);
			tfTenPhong.setBackground(Color.decode("#cccccc"));
			tfSucChua.setBorder(null);
			tfSucChua.setEditable(false);
			tfSucChua.setBackground(Color.decode("#cccccc"));
			tfLoaiPhong.setBorder(null);
			tfLoaiPhong.setEditable(false);
			tfLoaiPhong.setBackground(Color.decode("#cccccc"));
			tfGiaPhong.setBorder(null);
			tfGiaPhong.setEditable(false);
			tfGiaPhong.setBackground(Color.decode("#cccccc"));
			tfGioThuePhong.setBorder(null);
			tfGioThuePhong.setEditable(false);
			tfGioThuePhong.setBackground(Color.decode("#cccccc"));
			tfGioHienTai.setBorder(null);
			tfGioHienTai.setEditable(false);
			tfGioHienTai.setBackground(Color.decode("#cccccc"));
			tfThoiGianSuDung.setBorder(null);
			tfThoiGianSuDung.setEditable(false);
			tfThoiGianSuDung.setBackground(Color.decode("#cccccc"));

			tfTienPhongCu.setBorder(null);
			tfTienPhongCu.setEditable(false);
			tfTienPhongCu.setBackground(Color.decode("#cccccc"));
			tfTienPhongHienTai.setBorder(null);
			tfTienPhongHienTai.setEditable(false);
			tfTienPhongHienTai.setBackground(Color.decode("#cccccc"));
			tfTongTienPhong.setBorder(null);
			tfTongTienPhong.setEditable(false);
			tfTongTienPhong.setBackground(Color.decode("#cccccc"));
			tfGiamGia.setBorder(null);
			tfGiamGia.setEditable(false);
			tfGiamGia.setBackground(Color.decode("#cccccc"));
			tfTongCong.setBorder(null);
			tfTongCong.setEditable(false);
			tfTongCong.setBackground(Color.decode("#cccccc"));
			tfThueVAT.setBorder(null);
			tfThueVAT.setEditable(false);
			tfThueVAT.setBackground(Color.decode("#cccccc"));
			tfThanhTien.setBorder(null);
			tfThanhTien.setEditable(false);
			tfThanhTien.setBackground(Color.decode("#cccccc"));
			tfTongTienDich.setBorder(null);
			tfTongTienDich.setEditable(false);
			tfTongTienDich.setBackground(Color.decode("#cccccc"));

			pDPPane.add(boxForPDPPane);
			bottomPane.add(pDPPane, BorderLayout.NORTH);
			bottomPane.add(pane, BorderLayout.CENTER);
			bottomPane.add(pane2, BorderLayout.SOUTH);
			// main Pane
			mainPane = new JPanel(new BorderLayout());
			mainPane.add(topPane, BorderLayout.NORTH);
			mainPane.add(bottomPane, BorderLayout.CENTER);
			mainPane.setBackground(Color.decode("#6fa8dc"));
			this.getContentPane().add(mainPane);

			btnQuayLai.addActionListener(e -> this.dispose());

			tfTenNhanVien.setFont(new Font("sanserif", Font.PLAIN, 14));
			tfMaPDP.setFont(new Font("sanserif", Font.PLAIN, 14));
			tfTenKhach.setFont(new Font("sanserif", Font.PLAIN, 14));
			tfSDT.setFont(new Font("sanserif", Font.PLAIN, 14));
			tfNgayNhanP.setFont(new Font("sanserif", Font.PLAIN, 14));
			tfGioNhan.setFont(new Font("sanserif", Font.PLAIN, 14));
			lbMaPDP.setFont(new Font("sanserif", Font.BOLD, 14));
			lbTenNhanVien.setFont(new Font("sanserif", Font.BOLD, 14));
			lbTenKhach.setFont(new Font("sanserif", Font.BOLD, 14));
			lbSDT.setFont(new Font("sanserif", Font.BOLD, 14));
			lbNgayNhanP.setFont(new Font("sanserif", Font.BOLD, 14));
			lbGioNhan.setFont(new Font("sanserif", Font.BOLD, 14));
			lbGioNhan.setPreferredSize(lbNgayNhanP.getPreferredSize());
			lbSDT.setPreferredSize(lbTenKhach.getPreferredSize());
			lbMaPDP.setPreferredSize(lbTenNhanVien.getPreferredSize());

			tfTenPhong.setFont(new Font("sanserif", Font.PLAIN, 13));
			tfSucChua.setFont(new Font("sanserif", Font.PLAIN, 13));
			tfLoaiPhong.setFont(new Font("sanserif", Font.PLAIN, 13));
			tfGiaPhong.setFont(new Font("sanserif", Font.PLAIN, 13));
			tfGioThuePhong.setFont(new Font("sanserif", Font.PLAIN, 13));
			tfGioHienTai.setFont(new Font("sanserif", Font.PLAIN, 13));
			tfThoiGianSuDung.setFont(new Font("sanserif", Font.PLAIN, 13));
			lbTenPhong.setFont(new Font("sanserif", Font.BOLD, 13));
			lbSucChua.setFont(new Font("sanserif", Font.BOLD, 13));
			lbLoaiPhong.setFont(new Font("sanserif", Font.BOLD, 13));
			lbGiaPhong.setFont(new Font("sanserif", Font.BOLD, 13));
			lbGioThuePhong.setFont(new Font("sanserif", Font.BOLD, 13));
			lbGioHienTai.setFont(new Font("sanserif", Font.BOLD, 13));
			lbThoiGianSuDung.setFont(new Font("sanserif", Font.BOLD, 13));

			lbTenPhong.setPreferredSize(lbThoiGianSuDung.getPreferredSize());
			lbSucChua.setPreferredSize(lbThoiGianSuDung.getPreferredSize());
			lbLoaiPhong.setPreferredSize(lbThoiGianSuDung.getPreferredSize());
			lbGiaPhong.setPreferredSize(lbThoiGianSuDung.getPreferredSize());
			lbGioThuePhong.setPreferredSize(lbThoiGianSuDung.getPreferredSize());
			lbGioHienTai.setPreferredSize(lbThoiGianSuDung.getPreferredSize());

			tfThueVAT.setFont(new Font("sanserif", Font.PLAIN, 14));
			tfTongTienPhong.setFont(new Font("sanserif", Font.PLAIN, 14));
			tfTongTienDich.setFont(new Font("sanserif", Font.PLAIN, 14));
			tfTienPhongHienTai.setFont(new Font("sanserif", Font.PLAIN, 14));
			tfTienPhongCu.setFont(new Font("sanserif", Font.PLAIN, 14));
			tfTongCong.setFont(new Font("sanserif", Font.PLAIN, 14));
			tfThanhTien.setFont(new Font("sanserif", Font.PLAIN, 14));

			lbThueVAT.setFont(new Font("sanserif", Font.BOLD, 14));
			lbTongTienPhong.setFont(new Font("sanserif", Font.BOLD, 14));
			lbTongTienDich.setFont(new Font("sanserif", Font.BOLD, 14));
			lbTienPhongHienTai.setFont(new Font("sanserif", Font.BOLD, 14));
			lbTienPhongCu.setFont(new Font("sanserif", Font.BOLD, 14));
			lbTongCong.setFont(new Font("sanserif", Font.BOLD, 14));
			lbThanhTien.setFont(new Font("sanserif", Font.BOLD, 14));
			lbGiamGia.setFont(new Font("sanserif", Font.BOLD, 14));

			lbTienPhongHienTai.setPreferredSize(lbTienPhongCu.getPreferredSize());
			lbTongTienPhong.setPreferredSize(lbTienPhongCu.getPreferredSize());
			lbTongTienDich.setPreferredSize(lbTienPhongCu.getPreferredSize());
			lbGiamGia.setPreferredSize(lbThanhTien.getPreferredSize());
			lbTongCong.setPreferredSize(lbThanhTien.getPreferredSize());
			lbThueVAT.setPreferredSize(lbThanhTien.getPreferredSize());

			readDataToFieldThongTin();
			readDataToTableDichVu();
			readDataToFieldThanhToan();
		}

		private void readDataToFieldThongTin() {
			List<ChiTietHoaDon> dsCTHD = cthdDao
					.timKiemCTHDTheoTenPhong((String) phongModel.getValueAt(phongTable.getSelectedRow(), 0));
			ChiTietHoaDon cthd = dsCTHD.get(dsCTHD.size() - 1);
			List<HoaDon> dsHD = hdDAO.timKiemHoaDonTheoMaHD(cthd.getHd().getMaHoaDon());
			HoaDon hd = dsHD.get(dsHD.size() - 1);
			tfTenNhanVien.setText(hd.getNv().getTenNV());
			tfMaPDP.setText(hd.getMaHoaDon());
			tfTenKhach.setText(hd.getKh().getTenKH());
			tfSDT.setText(hd.getKh().getSdthoai());
			tfNgayNhanP.setText(hd.getNgayThanhToan().toString());

			Phong phong = phongDao.timKiemPhongTheoTenPhong(cthd.getPhong().getTenPhong()).get(0);
			String s = cthdDao.timKiemCTHDTheoMaPhong(phong.getMaPhong());
			String[] str = s.split(",");
			String thoiGian = str[2];
			String[] str2 = thoiGian.split(" ");
			String gio = str2[1];
			String[] time = gio.split(":");
			String hour = time[0];
			String minute = time[1];
			int h = Integer.valueOf(hour);
			int m = Integer.valueOf(minute);
			LocalTime tgNhanPhong = LocalTime.of(h, m);
			tfGioNhan.setText(tgNhanPhong.toString());

			tfTenPhong.setText(cthd.getPhong().getTenPhong());
			tfSucChua.setText(cthd.getPhong().getLoaiPhong().getSucChua() + "");
			tfLoaiPhong.setText(cthd.getPhong().getLoaiPhong().getTenLoaiPhong());
			tfGiaPhong.setText(cthd.getPhong().getLoaiPhong().getGiaLoaiPhong() + "");
			tfGioThuePhong.setText(tgNhanPhong.toString());
			tfGioHienTai.setText(LocalTime.now().format(gioFormatForDate));
			tfThoiGianSuDung.setText(cthd.tinhThoiLuong() + "");

		}

		private void readDataToTableDichVu() {
			model.setRowCount(0);
			List<CTDVPhong> dsCTDVPhong = ctdvPhongDAO.timKiemCTDVPhongTheoMaHD(tfMaPDP.getText());
			int i = 0;
			for (CTDVPhong ct : dsCTDVPhong) {
				model.addRow(new Object[] { ++i, ct.getDichVu().getTenDichVu(), ct.getDichVu().getDonVi(),
						ct.getSoLuong(), ct.getDichVu().getDonGia(), formatter.format(ct.tinhTienDV()) });
			}
		}

		private void readDataToFieldThanhToan() {
			tfThueVAT.setText("10%");
			List<ChiTietHoaDon> dsCTHD = cthdDao.timKiemCTHDTheoMaHD(tfMaPDP.getText().trim());
			double tongTienPhong = dsCTHD.stream().mapToDouble(cthd -> cthd.tinhTienPhong()).sum();
			double tienPhongHienTai = dsCTHD.get(dsCTHD.size() - 1).tinhTienPhong();
			double tienPhongCu = 0;
			if(dsCTHD.size() > 1)
				tienPhongCu = dsCTHD.get(dsCTHD.size() - 2).tinhTienPhong();
			tfTongTienPhong.setText(formatter.format(tongTienPhong) + "");
			List<CTDVPhong> dsCTDVP = ctdvPhongDAO.timKiemCTDVPhongTheoMaHD(tfMaPDP.getText().trim());
			double tongTienDV = dsCTDVP.stream().mapToDouble(ctdv -> ctdv.tinhTienDV()).sum();
			tfTongTienDich.setText(formatter.format(tongTienDV) + "");
			double tong = tongTienPhong + tongTienDV;
			double thanhTien = tong + tong * 0.01;
			tfTienPhongHienTai.setText(formatter.format(tienPhongHienTai) + "");
			tfTienPhongCu.setText(formatter.format(tienPhongCu) + "");
			tfTongCong.setText(formatter.format(tong) + "");
			tfThanhTien.setText(formatter.format(thanhTien) + "");
		}

		private void xuLyChuyenPhong() {

		}

		private void xuLyThemDVPhong() {

		}
	}
}
