package app;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.JobAttributes;

import javax.swing.Box;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

public class TinhTien extends JFrame{
	private JTextField tfMaHD, tfTenKH, tfNgayThanhToan, tfTenNhanVien, tfSDTKhach, tfGioThanhToan, tfTienNhan, tfTienThua,
	tfTienPhong, tfTienDichVu, tfGiamGia, tfTongCong, tfThue, tfThanhTien;
	private JButton btnQuayLai, btnInHD, btnThanhToan;
	private JTable tablePhong, tableDichVu;
	private DefaultTableModel modelPhong, modelDichVu;
	
	public TinhTien() {
		// TODO Auto-generated constructor stub
		setSize(900, 800);
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
		
		JLabel lbMaHD, lbTenKH, lbNgayThanhToan, lbTenNhanVien, lbSDTKhach, lbGioThanhToan, lbTienNhan, lbTienThua,
		lbTienPhong, lbTienDichVu, lbGiamGia, lbTongCong, lbThue, lbThanhTien, lbTieuDe, lbTablePhong, lbTableDichVu;
		
		JPanel mainPane, topPane, bottomPane, thongTinHDPane, tablePhongPane, tableDVPane, leftBottomPane, rightBottomPane;
		Font font = new Font("Arial", Font.BOLD, 24);
		Border border = new LineBorder(Color.black);
		
		//Tieu De
		mainPane = new JPanel(new BorderLayout());
		topPane = new JPanel();
		topPane.setBackground(Color.decode("#6fa8dc"));
		lbTieuDe = new JLabel("Tính Tiền");
		lbTieuDe.setFont(font);
		topPane.add(lbTieuDe);
		
		bottomPane = new JPanel(new BorderLayout());
		//Thông tin hóa đơn
		thongTinHDPane = new JPanel();
		Box box = Box.createVerticalBox();
		Box boxForThongTinHD = Box.createHorizontalBox();
		
		Box boxForThongTinHDLeft = Box.createVerticalBox();
		Box boxForMaHD = Box.createHorizontalBox();
		boxForMaHD.add(lbMaHD = new JLabel("Mã Hóa Đơn:"));
		boxForMaHD.add(tfMaHD = new JTextField(15));
		Box boxForTenKH = Box.createHorizontalBox();
		boxForTenKH.add(lbTenKH = new JLabel("Tên Khách Hàng:"));
		boxForTenKH.add(tfTenKH = new JTextField(15));
		Box boxForNgayThanhToan = Box.createHorizontalBox();
		boxForNgayThanhToan.add(lbNgayThanhToan = new JLabel("Ngày Thanh Toán:"));
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
		boxForTenNV.add(lbTenNhanVien = new JLabel("Tên Nhân Viên:"));
		boxForTenNV.add(tfTenNhanVien = new JTextField(15));
		Box boxForSDTK = Box.createHorizontalBox();
		boxForSDTK.add(lbSDTKhach = new JLabel("Số Điện Thoại Khách: "));
		boxForSDTK.add(tfSDTKhach = new JTextField(15));
		Box boxForGioThanhToan = Box.createHorizontalBox();
		boxForGioThanhToan.add(lbGioThanhToan = new JLabel("Giờ Thanh Toán:"));
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
		
		tfMaHD.setBorder(null); tfMaHD.setEditable(false); tfMaHD.setBackground(Color.white);
		tfTenKH.setBorder(null); tfTenKH.setEditable(false); tfTenKH.setBackground(Color.white);
		tfNgayThanhToan.setBorder(null); tfNgayThanhToan.setEditable(false); tfNgayThanhToan.setBackground(Color.white);
		tfTenNhanVien.setBorder(null); tfTenNhanVien.setEditable(false); tfTenNhanVien.setBackground(Color.white);
		tfSDTKhach.setBorder(null); tfSDTKhach.setEditable(false); tfSDTKhach.setBackground(Color.white);
		tfGioThanhToan.setBorder(null); tfGioThanhToan.setEditable(false); tfGioThanhToan.setBackground(Color.white);
		
		boxForThongTinHD.add(boxForThongTinHDLeft);
		boxForThongTinHD.add(Box.createHorizontalStrut(40));
		boxForThongTinHD.add(boxForThongTinHDRight);
		thongTinHDPane.setBackground(Color.white);
		thongTinHDPane.add(boxForThongTinHD);
		
		//Thông tin Phòng
		tablePhongPane = new JPanel(new BorderLayout());
		JPanel titleTablePhongPane = new JPanel();
		titleTablePhongPane.setBackground(Color.white);
		lbTablePhong = new JLabel("Thông tin chi tiết sử dụng phòng");
		lbTablePhong.setFont(new Font("sanserif", Font.PLAIN, 18));
		titleTablePhongPane.add(lbTablePhong);
		String[] headerTablePhong = {"STT", "Tên Phòng", "Loại Phòng", "Giờ Nhận", "Giờ Trả", "Thời Lượng (phút)", "Giá Phòng", "Thành Tiền (VND)"};
		modelPhong = new DefaultTableModel(headerTablePhong, 0);
		tablePhong = new JTable(modelPhong);
		tablePhong.setRowHeight(25);
		JScrollPane scrollTablePhong = new JScrollPane(tablePhong, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		tablePhongPane.add(scrollTablePhong, BorderLayout.CENTER);
		tablePhongPane.add(titleTablePhongPane, BorderLayout.NORTH);
		
		//Thông tin dịch vụ
		tableDVPane = new JPanel(new BorderLayout());
		JPanel titleTableDVPane = new JPanel();
		titleTableDVPane.setBackground(Color.white);
		lbTableDichVu = new JLabel("Thông tin chi tiết dịch vụ");
		lbTableDichVu.setFont(new Font("sanserif", Font.PLAIN, 18));
		titleTableDVPane.add(lbTableDichVu);
		String[] headerTableDV = {"STT", "Tên Phòng", "Đơn Vị", "Số Lượng", "Đơn Giá", "Thành Tiền (VND)"};
		modelDichVu = new DefaultTableModel(headerTableDV, 0);
		tableDichVu = new JTable(modelDichVu);
		tableDichVu.setRowHeight(25);
		JScrollPane scrollTableDichVu = new JScrollPane(tableDichVu, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		tableDVPane.add(scrollTableDichVu, BorderLayout.CENTER);
		tableDVPane.add(titleTableDVPane, BorderLayout.NORTH);
		
		box.add(thongTinHDPane);
		box.add(tablePhongPane);
		box.add(tableDVPane);
		bottomPane.add(box, BorderLayout.CENTER);
		
		//Thông tin tiền 
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
		paneForBtnBack.add(btnQuayLai = new JButton("Quay lại", imgBack));
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
		
		Box boxForTienDichVu =  Box.createHorizontalBox();
		boxForTienDichVu.add(lbTienDichVu = new JLabel("Tiền Dịch Vụ: "));
		boxForTienDichVu.add(tfTienDichVu = new JTextField(15));
		
		Box boxForGiamGia =  Box.createHorizontalBox();
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
		
		tfTienPhong.setBorder(null); tfTienPhong.setEditable(false); tfTienPhong.setBackground(Color.white);
		tfTienDichVu.setBorder(null); tfTienDichVu.setEditable(false); tfTienDichVu.setBackground(Color.white);
		tfGiamGia.setBorder(null); tfGiamGia.setEditable(false); tfGiamGia.setBackground(Color.white);
		tfTongCong.setBorder(null); tfTongCong.setEditable(false); tfTongCong.setBackground(Color.white);
		tfThue.setBorder(null); tfThue.setEditable(false); tfThue.setBackground(Color.white);
		tfThanhTien.setBorder(null); tfThanhTien.setEditable(false); tfThanhTien.setBackground(Color.white);
		
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
		paneForBtnThanhToan.add(btnInHD = new JButton("In Hóa Đơn"));
		btnInHD.setBackground(Color.decode("#6fa8dc"));
		paneForBtnThanhToan.add(Box.createHorizontalStrut(20));
		paneForBtnThanhToan.add(btnThanhToan = new JButton("Thanh Toán"));
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
	}
}
