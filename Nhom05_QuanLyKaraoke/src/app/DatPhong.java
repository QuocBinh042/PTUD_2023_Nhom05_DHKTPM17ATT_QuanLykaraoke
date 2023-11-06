package app;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

public class DatPhong extends JPanel {
	private JButton thuePBtn, datPBtn, chuyenPBtn, chiTietPBtn, dichVuPBtn, tinhTienPBtn, thoatBtn, nhanPBtn, huyPBtn, timKiemPBtn, lamMoiBtn, timKiemSDTBtn;
	private JComboBox<String> tinhTrangB, soNguoiB, loaiPhongB, tinhTrangPhieuB;
	private JTextField phongF, giaPhongF, sdtF;
	private JTable phongTable, phieuDatPTable;
	private DefaultTableModel phongModel, phieuDatPModel;
	private DigitalClock clock;
	private ThuePhong thuePhong;
	private DatTruocPhong datTruocPhong;
	private ChuyenPhong chuyenPhong;
	private ChiTietPhong chiTietPhong;
	private DichVuPhong dichVuPhong;
	private TinhTien tinhTien;
	private JLabel lbPhongTrong, lbPhongCho, lbPhongDangThue;
	public DatPhong() {
		Icon imgAdd = new ImageIcon("src/img/add2.png");
		Icon imgDel = new ImageIcon("src/img/del.png");
		Icon imgReset = new ImageIcon("src/img/refresh16.png");
		Icon imgEdit = new ImageIcon("src/img/edit.png");
		Icon imgOut = new ImageIcon("src/img/out.png");
		Icon imgSearch = new ImageIcon("src/img/search.png");
		Icon imgCheck = new ImageIcon("src/img/check16.png");
		Icon imgCancel = new ImageIcon("src/img/cancel16.png");	
		
		JLabel tinhTrangLb, soNguoiLb, loaiPLb, phongLb, giaPLb, locTinhTrangLb, sdtLb;
		JPanel mainPane, leftPane, rightPane, timePane, btnPane, panePhong, panePDP, paneBtnPhong, paneBtnPDP, paneTraCuuP;
		String[] headersTableP = {"Phòng", "Loại Phòng", "Số Người", "Tình Trạng", "Giá Phòng"};
		String[] headersTablePDP = {"Mã đặt phòng", "Tên phòng", "Ngày đặt", "Giờ đặt", "Tên khách", "SĐT khách", "Nhân viên", "Tình trạng"};
		Border border = new LineBorder(Color.black);
		Border blackLine = BorderFactory.createLineBorder(Color.black);
		leftPane = new JPanel();
		rightPane = new JPanel();
		btnPane = new JPanel();
		
		//Pane left các chức năng 
		GridLayout grid = new GridLayout(7, 0);
		grid.setVgap(15);
		JPanel btnMainPane = new JPanel(grid);
		btnMainPane.setBackground(Color.decode("#cccccc"));
		btnMainPane.add(thuePBtn = new ButtonGradient("Thuê Phòng"));
		btnMainPane.add(datPBtn = new ButtonGradient("Đặt Phòng"));
		btnMainPane.add(chuyenPBtn = new ButtonGradient("Chuyển phòng"));
		btnMainPane.add(chiTietPBtn = new ButtonGradient("Chi tiết phòng"));
		btnMainPane.add(dichVuPBtn = new ButtonGradient("Dịch vụ"));
		btnMainPane.add(tinhTienPBtn = new ButtonGradient("Tính tiền"));
		btnMainPane.add(thoatBtn = new ButtonGradient("Thoát"));
		thuePBtn.setFont(new Font("Sanserif", Font.BOLD, 20));
		thuePBtn.setBackground(Color.decode("#6fa8dc"));
		thuePBtn.setMaximumSize(new Dimension(Integer.MAX_VALUE, thuePBtn.getMinimumSize().height));
		datPBtn.setFont(new Font("Sanserif", Font.BOLD, 20));
		datPBtn.setBackground(Color.decode("#6fa8dc"));
		datPBtn.setMaximumSize(new Dimension(Integer.MAX_VALUE, datPBtn.getMinimumSize().height));
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
		thoatBtn.setFont(new Font("Sanserif", Font.BOLD, 20));
		thoatBtn.setBackground(Color.decode("#6fa8dc"));
		thoatBtn.setMaximumSize(new Dimension(Integer.MAX_VALUE, thoatBtn.getMinimumSize().height));
	
		btnPane.add(btnMainPane);
		btnPane.setBorder(border);
		btnPane.setBackground(Color.decode("#cccccc"));
		
		GridLayout gridForPane = new GridLayout(3, 0);
		gridForPane.setVgap(2);
		JPanel pane = new JPanel(gridForPane);
		pane.add(lbPhongCho = new JLabel("Phòng Chờ ()"));
		pane.add(lbPhongTrong = new JLabel("Phòng Trống ()"));
		pane.add(lbPhongDangThue = new JLabel("Phòng Đang Thuê ()"));
		pane.setBackground(Color.decode("#e6dbd1"));
		
		Box leftBox = Box.createVerticalBox();
		leftBox.add(clock = new DigitalClock());
		leftBox.add(Box.createVerticalStrut(10));
		leftBox.add(btnPane);
		leftBox.add(Box.createVerticalStrut(5));
		leftBox.add(pane);
		leftPane.add(leftBox);
		leftPane.setBackground(Color.decode("#e6dbd1"));
		
		//Pane Right
		panePhong = new JPanel();
		panePDP = new JPanel();
		panePhong.setLayout(new BorderLayout());
		panePDP.setLayout(new BorderLayout());
		paneBtnPhong = new JPanel();
		paneTraCuuP = new JPanel();
		paneBtnPDP = new JPanel();

		//Table Phong
		phongModel = new DefaultTableModel(headersTableP, 20);
		phongTable = new JTable(phongModel);
		phongTable.setAutoCreateRowSorter(true);
		phongTable.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		JScrollPane scrollPhongTable = new JScrollPane(phongTable, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		panePhong.add(scrollPhongTable, BorderLayout.CENTER);
		
		//Box các btn phòng
		Box btnPhongBox = Box.createHorizontalBox();
		
		//pane tra cứu phòng
		Box traCuuB = Box.createVerticalBox();
		Box traCuuB1 = Box.createHorizontalBox();
		Box traCuuB2 = Box.createHorizontalBox();
		
		JPanel panePhongLb = new JPanel();
		panePhongLb.setBackground(Color.decode("#cccccc"));
		panePhongLb.add(phongLb = new JLabel("Phòng"));
		traCuuB1.add(panePhongLb);
		
		JPanel panePhongF = new JPanel();
		panePhongF.setBackground(Color.decode("#cccccc"));
		panePhongF.add(phongF = new JTextField(15));
		traCuuB1.add(panePhongF);
		
		traCuuB1.add(timKiemPBtn = new ButtonGradient("Tìm kiếm", imgSearch));
		
		JPanel paneGiaLb = new JPanel();
		paneGiaLb.setBackground(Color.decode("#cccccc"));
		paneGiaLb.add(giaPLb = new JLabel("Giá phòng"));
		traCuuB2.add(paneGiaLb);
		
		JPanel paneGiaPhongF = new JPanel();
		paneGiaPhongF.setBackground(Color.decode("#cccccc"));
		paneGiaPhongF.add(giaPhongF = new JTextField(15));
		traCuuB2.add(paneGiaPhongF);
		
		traCuuB2.add(lamMoiBtn = new ButtonGradient("Làm mới", imgReset));		
		
		traCuuB.add(traCuuB1);
		traCuuB.add(Box.createVerticalStrut(10));
		traCuuB.add(traCuuB2);
		paneTraCuuP.add(traCuuB);
		paneTraCuuP.setBackground(Color.decode("#cccccc"));
		paneTraCuuP.setBorder(BorderFactory.createTitledBorder(blackLine, "Tra cứu"));
		
		lamMoiBtn.setBackground(Color.decode("#6fa8dc"));
		timKiemPBtn.setBackground(Color.decode("#6fa8dc"));
		phongLb.setPreferredSize(giaPLb.getPreferredSize());
		lamMoiBtn.setPreferredSize(timKiemPBtn.getPreferredSize());
		
		//Btn Phong
		String[] headersTinhTrang = {"Tất cả","Trống", "Đang sử dụng", "Phòng chờ"};
		String[] headersSoNguoi = {"Tất cả", "7", "10", "15"};
		String[] headersLoaiPhong = {"Tất cả", "Vip", "Thường"};
		
		//pane combobox tình trạng 
		Box btnPhongBox1 = Box.createHorizontalBox();
		btnPhongBox1.add(tinhTrangLb = new JLabel("Tình trạng phòng"));
		btnPhongBox1.add(Box.createHorizontalStrut(5));
		btnPhongBox1.add(tinhTrangB = new JComboBox<>(headersTinhTrang));
		JPanel paneCBTinhTrang = new JPanel();
		paneCBTinhTrang.setBackground(Color.decode("#cccccc"));
		paneCBTinhTrang.add(btnPhongBox1);
		btnPhongBox.add(paneCBTinhTrang);
		btnPhongBox.add(Box.createHorizontalStrut(20));
		
		//set size text field equals tinhTrangB

		
		//pane combobox số người
		Box btnPhongBox2 = Box.createHorizontalBox();
		btnPhongBox2.add(soNguoiLb = new JLabel("Số người"));
		btnPhongBox2.add(Box.createHorizontalStrut(5));
		btnPhongBox2.add(soNguoiB = new JComboBox<>(headersSoNguoi));
		JPanel paneCBSoNguoi = new JPanel();
		paneCBSoNguoi.setBackground(Color.decode("#cccccc"));
		paneCBSoNguoi.add(btnPhongBox2);
		btnPhongBox.add(paneCBSoNguoi);
		btnPhongBox.add(Box.createHorizontalStrut(20));
		
		//pane combobox loại phòng
		Box btnPhongBox3 = Box.createHorizontalBox();
		btnPhongBox3.add(loaiPLb = new JLabel("Loại phòng"));
		btnPhongBox3.add(Box.createHorizontalStrut(5));
		btnPhongBox3.add(loaiPhongB = new JComboBox<>(headersLoaiPhong));
		JPanel paneCBLoaiPhong = new JPanel();
		paneCBLoaiPhong.setBackground(Color.decode("#cccccc"));
		paneCBLoaiPhong.add(btnPhongBox3);
		btnPhongBox.add(paneCBLoaiPhong);
		btnPhongBox.add(Box.createHorizontalStrut(85));
		
		//Thêm pane tra cứu vào Box
		btnPhongBox.add(paneTraCuuP);
		
		soNguoiB.setPreferredSize(tinhTrangB.getPreferredSize());
		loaiPhongB.setPreferredSize(tinhTrangB.getPreferredSize());	
		
		paneBtnPhong.add(btnPhongBox);
		paneBtnPhong.setBackground(Color.decode("#cccccc"));
		
		panePhong.add(paneBtnPhong, BorderLayout.NORTH);
		panePhong.setBorder(BorderFactory.createTitledBorder(blackLine, "Thông tin phòng"));
		panePhong.setBackground(Color.decode("#e6dbd1"));
		
		//Table PDP 
		phieuDatPModel = new DefaultTableModel(headersTablePDP, 20);
		phieuDatPTable = new JTable(phieuDatPModel);
		phieuDatPTable.setAutoCreateRowSorter(true);
		phieuDatPTable.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		JScrollPane scrollPDPTable = new JScrollPane(phieuDatPTable, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		panePDP.add(scrollPDPTable, BorderLayout.CENTER);
		
		//Box tra cứu phiếu đặt phòng
		Box btnPDPBox = Box.createHorizontalBox();
		
		//Box Combox box lọc theo tình trạng pdp
		String[] headersTinhTrangPDP = {"Tất cả", "chưa nhận phòng", "nhận phòng"};
		Box locTinhTrangBox = Box.createHorizontalBox();
		locTinhTrangBox.add(locTinhTrangLb = new JLabel("Lọc theo tình trạng"));
		locTinhTrangBox.add(Box.createHorizontalStrut(5));
		locTinhTrangBox.add(tinhTrangPhieuB = new JComboBox<>(headersTinhTrangPDP));
		JPanel paneForLocBox = new JPanel();
		paneForLocBox.setBackground(Color.decode("#cccccc"));
		paneForLocBox.add(locTinhTrangBox);
		btnPDPBox.add(paneForLocBox);
		btnPDPBox.add(Box.createHorizontalStrut(150));
		
		//Box tìm kiếm khách theo sdt
		Box sdtBox = Box.createHorizontalBox();
		
		JPanel paneForSdtLb = new JPanel();
		paneForSdtLb.add(sdtLb = new JLabel("SĐT Khách hàng"));
		paneForSdtLb.setBackground(Color.decode("#cccccc"));
		sdtBox.add(paneForSdtLb);
		
		JPanel paneForSdtF = new JPanel();
		paneForSdtF.add(sdtF = new JTextField(15));
		paneForSdtF.setBackground(Color.decode("#cccccc"));
		sdtBox.add(paneForSdtF);
		sdtBox.add(timKiemSDTBtn = new ButtonGradient("Tìm kiếm", imgSearch));
		timKiemSDTBtn.setBackground(Color.decode("#6fa8dc"));
		
		JPanel paneForSdtBox = new JPanel();
		paneForSdtBox.setBackground(Color.decode("#cccccc"));
		paneForSdtBox.add(sdtBox);
		btnPDPBox.add(paneForSdtBox);
		paneBtnPDP.add(btnPDPBox);
		paneBtnPDP.setBackground(Color.decode("#cccccc"));
		
		panePDP.add(paneBtnPDP, BorderLayout.NORTH);
		panePDP.setBorder(BorderFactory.createTitledBorder(blackLine, "Danh sách phiếu đặt phòng"));
		panePDP.setBackground(Color.decode("#e6dbd1"));
		
		//Box nút hủy, nhận phòng
//		Box btnNhanHuyBox = Box.createHorizontalBox();
//		btnNhanHuyBox.add(Box.createHorizontalStrut(1000));
//		btnNhanHuyBox.add(huyPBtn = new JButton("Hủy", imgCancel));
//		btnNhanHuyBox.add(Box.createHorizontalStrut(40));
//		btnNhanHuyBox.add(nhanPBtn = new JButton("Nhận", imgCheck));
		
		JPanel paneForBtnNhanHuyBox = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		paneForBtnNhanHuyBox.add(huyPBtn = new ButtonGradient("Hủy", imgCancel));
		paneForBtnNhanHuyBox.add(Box.createHorizontalStrut(50));
		paneForBtnNhanHuyBox.add(nhanPBtn = new ButtonGradient("Nhận", imgCheck));
		paneForBtnNhanHuyBox.setBackground(Color.decode("#cccccc"));
		panePDP.add(paneForBtnNhanHuyBox, BorderLayout.SOUTH);
		
		huyPBtn.setBackground(Color.decode("#6fa8dc"));
		nhanPBtn.setBackground(Color.decode("#6fa8dc"));
		
		Box rightBox = Box.createVerticalBox();
		rightBox.add(panePhong);
		rightBox.add(panePDP);
		
		phongTable.setRowHeight(25);
		phieuDatPTable.setRowHeight(25);
		
		this.setLayout(new BorderLayout());
		this.add(leftPane, BorderLayout.WEST);
		this.add(rightBox, BorderLayout.CENTER);
		
		thuePBtn.addActionListener(e -> xuLyThuePhong());
		datPBtn.addActionListener(e -> xyLyDatPhong());
		chuyenPBtn.addActionListener(e -> xyLyChuyenPhong());
		chiTietPBtn.addActionListener(e -> xuLyChiTietPhong());
		dichVuPBtn.addActionListener(e -> xuLyDichVuPhong());
		tinhTienPBtn.addActionListener(e -> xuLyTinhTien());
		thoatBtn.addActionListener(e -> System.exit(0));
		timKiemPBtn.addActionListener(e -> xuLyTimKiemPhong());
		lamMoiBtn.addActionListener(e -> {
			phongF.setText("");
			giaPhongF.setText("");
			phongF.requestFocus();
		});
		timKiemSDTBtn.addActionListener(e -> xuLyTimKiemSDT());
		huyPBtn.addActionListener(e -> xyLyHuyPhongCho());
		nhanPBtn.addActionListener(e -> xuLyNhanPhong());
		tinhTrangB.addActionListener(e -> xuLyLocTheoTinhTrangPhong());
		soNguoiB.addActionListener(e -> xuLyLocTheoSoNguoi());
		loaiPhongB.addActionListener(e -> xuLyLocTheoLoaiPhong());
		tinhTrangPhieuB.addActionListener(e -> xuLyLocTheoTinhTrangPDP());
	}
	
	private void xuLyLocTheoTinhTrangPDP() {
		
	}
	
	private void xuLyLocTheoLoaiPhong() {
		
	}
	
	private void xuLyLocTheoSoNguoi() {
		
	}
	
	private void xuLyLocTheoTinhTrangPhong() {
		
	}
	
	private void xyLyHuyPhongCho() {
		
	}
	
	private void xuLyNhanPhong() {
		
	}
	
	private void xuLyTimKiemSDT () {
		
	}
	
	private void xuLyTimKiemPhong() {
		
	}
	
	private void xuLyThuePhong() {
		thuePhong = new ThuePhong();
		thuePhong.setVisible(true);
		thuePhong.setAlwaysOnTop(true);
		thuePhong.setLocationRelativeTo(null);
	}
	
	private void xyLyDatPhong() {
		datTruocPhong = new DatTruocPhong();
		datTruocPhong.setVisible(true);
		datTruocPhong.setAlwaysOnTop(true);
		datTruocPhong.setLocationRelativeTo(null);
	}
	
	private void xyLyChuyenPhong() {
		chuyenPhong = new ChuyenPhong();
		chuyenPhong.setVisible(true);
		chuyenPhong.setAlwaysOnTop(true);
		chuyenPhong.setLocationRelativeTo(null);
	}
	
	private void xuLyChiTietPhong() {
		chiTietPhong = new ChiTietPhong();
		chiTietPhong.setVisible(true);
		chiTietPhong.setAlwaysOnTop(true);
		chiTietPhong.setLocationRelativeTo(null);
	}
	
	private void xuLyDichVuPhong() {
		dichVuPhong = new DichVuPhong();
		dichVuPhong.setVisible(true);
		dichVuPhong.setAlwaysOnTop(true);
		dichVuPhong.setLocationRelativeTo(null);
	}
	
	private void xuLyTinhTien() {
		tinhTien = new TinhTien();
		tinhTien.setVisible(true);
		tinhTien.setAlwaysOnTop(true);
		tinhTien.setLocationRelativeTo(null);
	}
}
