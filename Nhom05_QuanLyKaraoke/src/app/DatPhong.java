package app;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

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
		Box btnBox = Box.createVerticalBox();
		btnBox.add(Box.createVerticalStrut(35));
		btnBox.add(thuePBtn = new JButton("Thuê Phòng"));
		btnBox.add(Box.createVerticalStrut(35));
		btnBox.add(datPBtn = new JButton("Đặt Phòng"));
		btnBox.add(Box.createVerticalStrut(35));
		btnBox.add(chuyenPBtn = new JButton("Chuyển phòng"));
		btnBox.add(Box.createVerticalStrut(35));
		btnBox.add(chiTietPBtn = new JButton("Chi tiết phòng"));
		btnBox.add(Box.createVerticalStrut(35));
		btnBox.add(dichVuPBtn = new JButton("Dịch vụ"));
		btnBox.add(Box.createVerticalStrut(35));
		btnBox.add(tinhTienPBtn = new JButton("Tính tiền"));
		btnBox.add(Box.createVerticalStrut(35));
		btnBox.add(thoatBtn = new JButton("Thoát"));
		btnBox.add(Box.createVerticalStrut(35));
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
		
		btnPane.add(btnBox);
		btnPane.setBorder(border);
		btnPane.setBackground(Color.decode("#cccccc"));
		
		Box leftBox = Box.createVerticalBox();
		leftBox.add(clock = new DigitalClock());
		leftBox.add(Box.createVerticalStrut(10));
		leftBox.add(btnPane);
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
		
		traCuuB1.add(timKiemPBtn = new JButton("Tìm kiếm", imgSearch));
		
		JPanel paneGiaLb = new JPanel();
		paneGiaLb.setBackground(Color.decode("#cccccc"));
		paneGiaLb.add(giaPLb = new JLabel("Giá phòng"));
		traCuuB2.add(paneGiaLb);
		
		JPanel paneGiaPhongF = new JPanel();
		paneGiaPhongF.setBackground(Color.decode("#cccccc"));
		paneGiaPhongF.add(giaPhongF = new JTextField(15));
		traCuuB2.add(paneGiaPhongF);
		
		traCuuB2.add(lamMoiBtn = new JButton("Làm mới", imgReset));		
		
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
		sdtBox.add(timKiemSDTBtn = new JButton("Tìm kiếm", imgSearch));
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
		Box btnNhanHuyBox = Box.createHorizontalBox();
		btnNhanHuyBox.add(Box.createHorizontalStrut(1000));
		btnNhanHuyBox.add(huyPBtn = new JButton("Hủy", imgCancel));
		btnNhanHuyBox.add(Box.createHorizontalStrut(40));
		btnNhanHuyBox.add(nhanPBtn = new JButton("Nhận", imgCheck));
		
		JPanel paneForBtnNhanHuyBox = new JPanel();
		paneForBtnNhanHuyBox.add(btnNhanHuyBox);
		paneForBtnNhanHuyBox.setBackground(Color.decode("#cccccc"));
		panePDP.add(paneForBtnNhanHuyBox, BorderLayout.SOUTH);
		
		huyPBtn.setBackground(Color.decode("#6fa8dc"));
		nhanPBtn.setBackground(Color.decode("#6fa8dc"));
		
		Box rightBox = Box.createVerticalBox();
		rightBox.add(panePhong);
		rightBox.add(panePDP);
		
		this.setLayout(new BorderLayout());
		this.add(leftPane, BorderLayout.WEST);
		this.add(rightBox, BorderLayout.CENTER);
	}
}
