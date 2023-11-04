package app;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;	

public class ChiTietPhong extends JFrame {
	private JTextField tfMaPDP, tfTenKhach, tfNgayNhanP, tfTenNhanVien, tfSDT, tfGioNhan, tfTenPhong, tfSucChua, tfLoaiPhong, tfGiaPhong,
	tfGioThuePhong, tfGioHienTai, tfThoiGianSuDung, tfTienPhongCu, tfTienPhongHienTai, tfTongTienPhong, tfTongTienDich, tfGiamGia,
	tfTongCong, tfThueVAT, tfThanhTien;
	private JTable tableDichVu;
	private DefaultTableModel model;
	private JButton btnThemDV, btnChuyenPhong, btnQuayLai;
	
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
		
		JLabel lbMaPDP, lbTenKhach, lbNgayNhanP, lbTenNhanVien, lbSDT, lbGioNhan, lbTenPhong, lbSucChua, lbLoaiPhong, lbGiaPhong,
		lbGioThuePhong, lbGioHienTai, lbThoiGianSuDung, lbTienPhongCu, lbTienPhongHienTai, lbTongTienPhong, lbTongTienDich, lbGiamGia,
		lbTongCong, lbThueVAT, lbThanhTien, lbTuaDe;
		
		Border border = new LineBorder(Color.black);
		
		// set Top Pane
		topPane = new JPanel();
		lbTuaDe = new JLabel("CHI TIẾT PHÒNG");
		lbTuaDe.setFont(new Font("Arial", Font.BOLD, 24));
		topPane.add(lbTuaDe);
		topPane.setBackground(Color.decode("#6fa8dc"));
		
		
		//set Bottom Pane
		bottomPane = new JPanel(new BorderLayout());
		pDPPane = new JPanel();
		pDPPane.setBackground(Color.decode("#e6dbd1"));
		Box boxForPDPPane = Box.createHorizontalBox();
		
		//set up cột đầu tiên trong pane phiếu đặt phòng
		Box box1 = Box.createVerticalBox();
		Box boxForMaPDP = Box.createHorizontalBox();
		boxForMaPDP.add(lbMaPDP = new JLabel("Mã Phiếu Đặt Phòng: "));
		boxForMaPDP.add(tfMaPDP = new JTextField(15));
		box1.add(boxForMaPDP);
		box1.add(Box.createVerticalStrut(20));
		
		Box boxForTenNhanVien = Box.createHorizontalBox();
		boxForTenNhanVien.add(lbTenNhanVien = new JLabel("Tên Nhân Viên: "));
		boxForTenNhanVien.add(tfTenNhanVien = new JTextField(15));
		box1.add(boxForTenNhanVien);
		boxForPDPPane.add(box1);
		boxForPDPPane.add(Box.createHorizontalStrut(10));
		lbTenNhanVien.setPreferredSize(lbMaPDP.getPreferredSize());
		
		//set up cột thứ hai trong pane phiếu đặt phòng
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
		lbSDT.setPreferredSize(lbTenKhach.getPreferredSize());
		
		//set up cột thứ ba trong pane phiếu đặt phòng
		Box box3 = Box.createVerticalBox();
		Box boxForNgayNhan	 = Box.createHorizontalBox();
		boxForNgayNhan.add(lbNgayNhanP = new JLabel("Ngày Nhận Phòng: "));
		boxForNgayNhan.add(tfNgayNhanP = new JTextField(15));
		box3.add(boxForNgayNhan);
		box3.add(Box.createVerticalStrut(20));
			
		Box boxForGioNhan = Box.createHorizontalBox();
		boxForGioNhan.add(lbGioNhan = new JLabel("Giờ Nhận Phòng: "));
		boxForGioNhan.add(tfGioNhan = new JTextField(15));
		box3.add(boxForGioNhan);
		boxForPDPPane.add(box3);
		lbGioNhan.setPreferredSize(lbNgayNhanP.getPreferredSize());
		
		//Thong tin phong hien tai, thong tin dich vu
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
	//	boxForThoiGian.add(Box.createHorizontalStrut(5));
		boxForThoiGian.add(tfThoiGianSuDung = new JTextField(15));
		
		boxForThongTinPhong.add(Box.createVerticalStrut(20));
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
		
		lbTenPhong.setPreferredSize(lbThoiGianSuDung.getPreferredSize());
		lbSucChua.setPreferredSize(lbThoiGianSuDung.getPreferredSize());
		lbLoaiPhong.setPreferredSize(lbThoiGianSuDung.getPreferredSize());
		lbGiaPhong.setPreferredSize(lbThoiGianSuDung.getPreferredSize());
		lbGioThuePhong.setPreferredSize(lbThoiGianSuDung.getPreferredSize());
		lbGioHienTai.setPreferredSize(lbThoiGianSuDung.getPreferredSize());
		
		thongTinPhongPane.add(boxForThongTinPhong);
		thongTinPhongPane.setBackground(Color.decode("#cccccc"));
		thongTinPhongPane.setBorder(BorderFactory.createTitledBorder(border, "Thông tin phòng hiện tại"));
		pane.add(thongTinPhongPane, BorderLayout.WEST);
		
		//set thong tin dich vu
		String[] headersDichVu = {"STT", "Tên dịch vụ", "Đơn vị", "Đơn giá", "Số lượng"};
		model = new DefaultTableModel(headersDichVu, 20);
		tableDichVu = new JTable(model);
		tableDichVu.setRowHeight(25);
		JScrollPane scroll = new JScrollPane(tableDichVu, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scroll.setBackground(Color.decode("#cccccc"));
		scroll.setBorder(BorderFactory.createTitledBorder(border, "Thông tin dịch vụ"));
		pane.add(scroll, BorderLayout.CENTER);
		
		//set tổng tiền phiếu đặt phòng
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
		
		lbTienPhongHienTai.setPreferredSize(lbTienPhongCu.getPreferredSize());
		lbTongTienPhong.setPreferredSize(lbTienPhongCu.getPreferredSize());
		lbTongTienDich.setPreferredSize(lbTienPhongCu.getPreferredSize());
		
		
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
		
		lbGiamGia.setPreferredSize(lbThanhTien.getPreferredSize());
		lbTongCong.setPreferredSize(lbThanhTien.getPreferredSize());
		lbThueVAT.setPreferredSize(lbThanhTien.getPreferredSize());
		
		thongTinPDPPane.add(boxForTTPDP);
		thongTinPDPPane.setBackground(Color.decode("#cccccc"));
		thongTinPDPPane.setBorder(BorderFactory.createTitledBorder(border, "Tổng Tiền Phiếu Đặt Phòng"));
		
		JPanel pane2 = new JPanel(new BorderLayout());
		pane2.add(thongTinPDPPane, BorderLayout.WEST);
		
		// set pane button
		btnPane = new JPanel();
		Box boxForBtnPane = Box.createVerticalBox();
		boxForBtnPane.add(Box.createVerticalStrut(10));
		boxForBtnPane.add(btnThemDV = new ButtonGradient("Thêm dịch vụ", imgAdd));
		boxForBtnPane.add(Box.createVerticalStrut(20));
		boxForBtnPane.add(btnChuyenPhong = new ButtonGradient("Chuyển phòng", imgChange));
		boxForBtnPane.add(Box.createVerticalStrut(20));
		boxForBtnPane.add(btnQuayLai = new ButtonGradient("Quay lại", imgBack));
		boxForBtnPane.add(Box.createVerticalStrut(10));
		btnPane.add(boxForBtnPane);
		btnPane.setBackground(Color.decode("#e6dbd1"));
				
		btnQuayLai.setBackground(Color.decode("#6fa8dc"));
		btnChuyenPhong.setBackground(Color.decode("#6fa8dc"));
		btnThemDV.setBackground(Color.decode("#6fa8dc"));
		
		Dimension maxButtonSize = new Dimension(Integer.MAX_VALUE, btnChuyenPhong.getPreferredSize().height);
		btnThemDV.setMaximumSize(maxButtonSize);
		btnQuayLai.setMaximumSize(maxButtonSize);

		pane2.add(btnPane, BorderLayout.CENTER);
		
		//set text field của pdp pane
		tfMaPDP.setBorder(null); tfMaPDP.setEditable(false); tfMaPDP.setBackground(Color.decode("#e6dbd1"));
		tfTenNhanVien.setBorder(null); tfTenNhanVien.setEditable(false); tfTenNhanVien.setBackground(Color.decode("#e6dbd1"));
		tfTenKhach.setBorder(null); tfTenKhach.setEditable(false); tfTenKhach.setBackground(Color.decode("#e6dbd1"));
		tfSDT.setBorder(null); tfSDT.setEditable(false); tfSDT.setBackground(Color.decode("#e6dbd1"));
		tfNgayNhanP.setBorder(null); tfNgayNhanP.setEditable(false); tfNgayNhanP.setBackground(Color.decode("#e6dbd1"));
		tfGioNhan.setBorder(null); tfGioNhan.setEditable(false); tfGioNhan.setBackground(Color.decode("#e6dbd1"));
		tfTenPhong.setBorder(null); tfTenPhong.setEditable(false); tfTenPhong.setBackground(Color.decode("#cccccc"));
		tfSucChua.setBorder(null); tfSucChua.setEditable(false); tfSucChua.setBackground(Color.decode("#cccccc"));
		tfLoaiPhong.setBorder(null); tfLoaiPhong.setEditable(false); tfLoaiPhong.setBackground(Color.decode("#cccccc"));
		tfGiaPhong.setBorder(null); tfGiaPhong.setEditable(false); tfGiaPhong.setBackground(Color.decode("#cccccc"));
		tfGioThuePhong.setBorder(null); tfGioThuePhong.setEditable(false); tfGioThuePhong.setBackground(Color.decode("#cccccc"));
		tfGioHienTai.setBorder(null); tfGioHienTai.setEditable(false); tfGioHienTai.setBackground(Color.decode("#cccccc"));
		tfThoiGianSuDung.setBorder(null); tfThoiGianSuDung.setEditable(false); tfThoiGianSuDung.setBackground(Color.decode("#cccccc"));
		
		tfTienPhongCu.setBorder(null); tfTienPhongCu.setEditable(false); tfTienPhongCu.setBackground(Color.decode("#cccccc"));
		tfTienPhongHienTai.setBorder(null); tfTienPhongHienTai.setEditable(false); tfTienPhongHienTai.setBackground(Color.decode("#cccccc"));
		tfTongTienPhong.setBorder(null); tfTongTienPhong.setEditable(false); tfTongTienPhong.setBackground(Color.decode("#cccccc"));
		tfGiamGia.setBorder(null); tfGiamGia.setEditable(false); tfGiamGia.setBackground(Color.decode("#cccccc"));
		tfTongCong.setBorder(null); tfTongCong.setEditable(false); tfTongCong.setBackground(Color.decode("#cccccc"));
		tfThueVAT.setBorder(null); tfThueVAT.setEditable(false); tfThueVAT.setBackground(Color.decode("#cccccc"));
		tfThanhTien.setBorder(null); tfThanhTien.setEditable(false); tfThanhTien.setBackground(Color.decode("#cccccc"));
		tfTongTienDich.setBorder(null); tfTongTienDich.setEditable(false); tfTongTienDich.setBackground(Color.decode("#cccccc"));
		
		pDPPane.add(boxForPDPPane);
		bottomPane.add(pDPPane, BorderLayout.NORTH);
		bottomPane.add(pane, BorderLayout.CENTER);
		bottomPane.add(pane2, BorderLayout.SOUTH);
		//main Pane
		mainPane = new JPanel(new BorderLayout());
		mainPane.add(topPane, BorderLayout.NORTH);
		mainPane.add(bottomPane, BorderLayout.CENTER);
		mainPane.setBackground(Color.decode("#6fa8dc"));
		this.getContentPane().add(mainPane);
	}
}
