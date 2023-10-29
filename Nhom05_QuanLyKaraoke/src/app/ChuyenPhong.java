package app;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

public class ChuyenPhong extends JFrame {
	private JLabel lbTenPhong, lbTenKhach, lbNgayThue, lbGioThue, lbGioHienTai, lbThoiGianSuDung, lbPhong, lbGiaPhong, lbLoaiPhong, lbSucChua;
	private JTextField tfTenPhong, tfTenKhach, tfNgayThue, tfGioThue, tfGioHienTai, tfThoiGianSuDung, tfPhong, tfGiaPhong;
	private JComboBox<String> cbLoaiPhong, cbSucChua;
	private JButton btnTimKiem, btnLamMoi, btnQuayLai, btnChuyenPhong;
	private JTable tablePhongTrong;
	private DefaultTableModel modelPhongTrong;
	
	public ChuyenPhong() {
		// TODO Auto-generated constructor stub
		setSize(1000, 450);
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
		String[] headersSoNguoi = {"Tất cả", "7", "10", "15"};
		String[] headersLoaiPhong = {"Tất cả", "Vip", "Thường"};
		String[] headersTable = {"Mã Phòng", "Tên Phòng", "Loại Phòng", "Sức Chứa", "Giá Phòng"};
		Font font = new Font("Arial", Font.BOLD, 14);
		
		mainPane = new JPanel(new BorderLayout()); 
		
		//set top pane
		topPane = new JPanel();
		topPane.setBackground(Color.decode("#6fa8dc"));
		topPane.add(lbTieuDe = new JLabel("CHUYỂN PHÒNG"));
		lbTieuDe.setFont(new Font("Arial", Font.BOLD, 24));
		
		
		//set left pane
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
		//set pane right
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
		
		//pane tra cuu
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
		
		traCuuB1.add(btnTimKiem = new JButton("Tìm kiếm", imgSearch));
		
		JPanel paneGiaLb = new JPanel();
		paneGiaLb.setBackground(Color.decode("#cccccc"));
		paneGiaLb.add(lbGiaPhong = new JLabel("Giá phòng"));
		traCuuB2.add(paneGiaLb);
		
		JPanel paneGiaPhongF = new JPanel();
		paneGiaPhongF.setBackground(Color.decode("#cccccc"));
		paneGiaPhongF.add(tfGiaPhong = new JTextField(15));
		traCuuB2.add(paneGiaPhongF);
		
		traCuuB2.add(btnLamMoi = new JButton("Làm mới", imgReset));		
		
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
		
		
		//Table
		modelPhongTrong = new DefaultTableModel(headersTable, 20);
		tablePhongTrong = new JTable(modelPhongTrong);
		tablePhongTrong.setRowHeight(25);
		JScrollPane scroll = new JScrollPane(tablePhongTrong, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		rightPane.add(scroll, BorderLayout.CENTER);
		
		//Bottom Pane
		bottomPane = new JPanel(new GridLayout(1, 2));
		bottomPane.setBackground(Color.decode("#cccccc"));
		
		bottomPaneRight = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		bottomPaneRight.setBackground(Color.decode("#cccccc"));
		bottomPaneRight.add(btnChuyenPhong = new JButton("Chuyển phòng", imgChange));
		btnChuyenPhong.setBackground(Color.decode("#6fa8dc"));
		
		bottomPaneLeft = new JPanel(new FlowLayout(FlowLayout.LEFT));
		bottomPaneLeft.setBackground(Color.decode("#cccccc"));
		bottomPaneLeft.add(btnQuayLai = new JButton("Quay lại", imgBack));
		btnQuayLai.setBackground(Color.decode("#6fa8dc"));
		
		bottomPane.add(bottomPaneLeft);
		bottomPane.add(bottomPaneRight);
		
		mainPane.add(topPane, BorderLayout.NORTH);
		mainPane.add(leftPane, BorderLayout.WEST);
		mainPane.add(rightPane, BorderLayout.CENTER);
		mainPane.add(bottomPane, BorderLayout.SOUTH);
		
		this.getContentPane().add(mainPane);
	}
}
