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
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;

import com.github.lgooddatepicker.components.TimePicker;
import com.toedter.calendar.JDateChooser;

public class DatTruocPhong extends JFrame{
	private JTextField tenPhongF, loaiPhongF, giaPhongF, sucChuaF, tinhTrangF, sdtKhachF, tenKhachF;
	private JTextArea ghiChuA;
	private JButton kiemTraBtn, quayLaiBtn, thuePhongBtn;
	private JDateChooser ngayDatF;
	private TimePicker gioNhanF;
	public DatTruocPhong() {
		setSize(600, 400);
		
		Icon imgDel = new ImageIcon("src/img/del.png");
		Icon imgReset = new ImageIcon("src/img/refresh16.png");
		Icon imgEdit = new ImageIcon("src/img/edit.png");
		Icon imgOut = new ImageIcon("src/img/out.png");
		Icon imgSearch = new ImageIcon("src/img/search.png");
		Icon imgCheck = new ImageIcon("src/img/check16.png");
		Icon imgCancel = new ImageIcon("src/img/cancel16.png");	
		Icon imgBack = new ImageIcon("src/img/back16.png");
		Icon imgAdd = new ImageIcon("src/img/add16.png");
		
		JLabel tenPhongLb, loaiPhongLb, giaPhongLb, sucChuaLb, tinhTrangLb, sdtKhachLb, tenKhachLb, ngayNhanPhongLb, gioNhanPhongLb, ghiChuLb, tieuDeLb;
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
		
		//set thông tin phòng 
		leftPanel.setBackground(Color.decode("#cccccc"));
		leftPanel.setBorder(BorderFactory.createTitledBorder(border, "Thông tin phòng"));
		
		Box thongTinPhongBox = Box.createVerticalBox();
		
		Box tenPhongBox = Box.createHorizontalBox();
		tenPhongBox.add(tenPhongLb = new JLabel("Tên phòng:"));
		tenPhongBox.add(Box.createHorizontalStrut(5));
		tenPhongBox.add(tenPhongF = new JTextField(10));
		tenPhongF.setBorder(null);
		tenPhongF.setEditable(false);
		
		Box loaiPhongBox = Box.createHorizontalBox();
		loaiPhongBox.add(loaiPhongLb = new JLabel("Loại phòng:"));
		loaiPhongBox.add(Box.createHorizontalStrut(5));
		loaiPhongBox.add(loaiPhongF = new JTextField(10));
		loaiPhongF.setBorder(null);
		loaiPhongF.setEditable(false);
		
		Box giaPhongBox = Box.createHorizontalBox();
		giaPhongBox.add(giaPhongLb = new JLabel("Giá phòng:"));
		giaPhongBox.add(Box.createHorizontalStrut(5));
		giaPhongBox.add(giaPhongF = new JTextField(10));
		giaPhongF.setBorder(null);
		giaPhongF.setEditable(false);
		
		Box sucChuaBox = Box.createHorizontalBox();
		sucChuaBox.add(sucChuaLb = new JLabel("Sức chứa:"));
		sucChuaBox.add(Box.createHorizontalStrut(5));
		sucChuaBox.add(sucChuaF = new JTextField(10));
		sucChuaF.setBorder(null);
		sucChuaF.setEditable(false);
		
		Box tinhTrangBox = Box.createHorizontalBox();
		tinhTrangBox.add(tinhTrangLb = new JLabel("Tình trạng:"));
		tinhTrangBox.add(Box.createHorizontalStrut(5));
		tinhTrangBox.add(tinhTrangF = new JTextField(10));
		tinhTrangF.setBorder(null);
		tinhTrangF.setEditable(false);
		
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
		
		//set thông tin thuê phòng
		rightPanel = new JPanel();
		rightPanel.setBorder(BorderFactory.createTitledBorder(border, "Thông tin đặt phòng"));
		rightPanel.setBackground(Color.decode("#cccccc"));
		Box thongTinThuePhongBox = Box.createVerticalBox();
		Box sdtKhachBox = Box.createHorizontalBox();
		sdtKhachBox.add(sdtKhachLb = new JLabel("SĐT Khách:"));
		JPanel sdtKhachPane = new JPanel();
		sdtKhachPane.add(sdtKhachF = new JTextField(15));
		sdtKhachPane.setBackground(Color.decode("#cccccc"));
		sdtKhachBox.add(sdtKhachPane);
		sdtKhachBox.add(Box.createHorizontalStrut(5));
		sdtKhachBox.add(kiemTraBtn = new JButton("Kiểm tra", imgCheck));
		kiemTraBtn.setBackground(Color.decode("#6fa8dc"));
		
		Box tenKhachBox = Box.createHorizontalBox();
		tenKhachBox.add(tenKhachLb = new JLabel("Tên khách:"));
		tenKhachBox.add(Box.createHorizontalStrut(5));
		tenKhachBox.add(tenKhachF = new JTextField(15));
		
		Box ngayNhanBox = Box.createHorizontalBox();
		ngayNhanBox.add(ngayNhanPhongLb = new JLabel("Ngày nhận phòng:"));
		ngayNhanBox.add(Box.createHorizontalStrut(5));
		ngayNhanBox.add(ngayDatF = new JDateChooser());
		
		Box gioNhanBox = Box.createHorizontalBox();
		gioNhanBox.add(gioNhanPhongLb = new JLabel("Giờ nhận phòng:"));
		gioNhanBox.add(Box.createHorizontalStrut(5));
		gioNhanBox.add(gioNhanF = new TimePicker());
		
		Box ghiChuBox = Box.createHorizontalBox();
		ghiChuBox.add(ghiChuLb = new JLabel("Ghi chú:"));
		ghiChuBox.add(Box.createHorizontalStrut(5));
		ghiChuBox.add(ghiChuA = new JTextArea(3, 15));
		
		
		thongTinThuePhongBox.add(sdtKhachBox);
		thongTinThuePhongBox.add(Box.createVerticalStrut(20));
		thongTinThuePhongBox.add(tenKhachBox);
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
		
//		set Bottom pane
		
		bottomPanelLeft.add(quayLaiBtn = new JButton("Quay lại", imgBack));
		bottomPanelRight.add(thuePhongBtn = new JButton("Đặt phòng", imgAdd));
		bottomPanelRight.setBackground(Color.decode("#e6dbd1"));
		bottomPanelLeft.setBackground(Color.decode("#e6dbd1"));
		bottomPanel.add(bottomPanelLeft);
		bottomPanel.add(bottomPanelRight);
		
		quayLaiBtn.setHorizontalAlignment(SwingConstants.RIGHT);
		quayLaiBtn.setBackground(Color.decode("#6fa8dc"));
		thuePhongBtn.setBackground(Color.decode("#6fa8dc"));

		//set tiêu đề
		titlePanel.setBackground(Color.decode("#6fa8dc"));
		titlePanel.add(tieuDeLb = new JLabel("ĐẶT PHÒNG"));
		tieuDeLb.setFont(font);
		
		mainPanel.add(titlePanel, BorderLayout.NORTH);
		mainPanel.add(leftPanel, BorderLayout.WEST);
		mainPanel.add(rightPanel, BorderLayout.CENTER);
		mainPanel.add(bottomPanel, BorderLayout.SOUTH);
		this.getContentPane().add(mainPanel);
		this.setBackground(Color.decode("#e6dbd1"));
	}
}
