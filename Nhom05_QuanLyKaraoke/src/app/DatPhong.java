package app;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
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
import dao.daoDichVu;
import dao.daoKhachHang;
import dao.daoPhong;
import entity.CTDVPhong;
import entity.ChiTietHoaDon;
import entity.DichVu;
import entity.HoaDon;
import entity.KhachHang;
import entity.KhuyenMai;
import entity.NhanVien;
import entity.PhieuDatPhong;
import entity.Phong;

public class DatPhong extends JPanel {
	private JButton thuePBtn, phieuDatPhongBtn, datPBtn, chuyenPBtn, chiTietPBtn, dichVuPBtn, tinhTienPBtn, timKiemPBtn, lamMoiBtn;
	private JComboBox<String> tinhTrangB, soNguoiB, loaiPhongB;
	private JTextField phongF, giaPhongF;
	private JTable phongTable;
	private DefaultTableModel phongModel;
	private DigitalClock clock;
	private ThuePhong thuePhong;
	private DatTruocPhong datTruocPhong;
	private ChuyenPhong chuyenPhong;
	private ChiTietPhong chiTietPhong;
	private DichVuPhong dichVuPhong;
	private TinhTien tinhTien;
	private PhongCho phieuDatPhong;
	private JLabel lbPhongTrong, lbPhongCho, lbPhongDangThue;
	private daoPhong phongDao = new daoPhong();
	private DAOCTHD cthdDao = new DAOCTHD();
	private DAOHoaDon hdDAO = new DAOHoaDon();
	private daoDichVu dvDAO = new daoDichVu();
	private DAOCTDVPhong ctdvPhongDAO = new DAOCTDVPhong();

	public DatPhong() {
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
		JPanel mainPane, leftPane, rightPane, timePane, btnPane, panePhong, panePDP, paneBtnPhong, paneBtnPDP, paneTraCuuP;
		String[] headersTableP = {"Phòng", "Loại Phòng", "Số Người", "Tình Trạng", "Giá Phòng"};
		Border border = new LineBorder(Color.black);
		Border blackLine = BorderFactory.createLineBorder(Color.black);
		leftPane = new JPanel();
		rightPane = new JPanel();
		btnPane = new JPanel();
		
		//Pane left các chức năng 
		GridLayout grid = new GridLayout(7, 0);
		grid.setVgap(20);
		JPanel btnMainPane = new JPanel(grid);
		btnMainPane.setBackground(Color.decode("#efcf7a"));
		btnMainPane.add(thuePBtn = new ButtonGradient("Thuê Phòng"));
//		btnMainPane.add(datPBtn = new ButtonGradient("Đặt Phòng"));
		btnMainPane.add(phieuDatPhongBtn = new ButtonGradient("Phòng Chờ"));
		btnMainPane.add(chuyenPBtn = new ButtonGradient("Chuyển phòng"));
		btnMainPane.add(chiTietPBtn = new ButtonGradient("Chi tiết"));
		btnMainPane.add(dichVuPBtn = new ButtonGradient("Dịch vụ"));
		btnMainPane.add(tinhTienPBtn = new ButtonGradient("Tính tiền"));
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
		btnPane.setBackground(Color.decode("#efcf7a"));
		
		GridLayout gridForPane = new GridLayout(3, 0);
		gridForPane.setVgap(2);
		JPanel pane = new JPanel(gridForPane);
		pane.add(lbPhongCho = new JLabel("Phòng Chờ ()"));
		pane.add(lbPhongTrong = new JLabel("Phòng Trống ()"));
		pane.add(lbPhongDangThue = new JLabel("Phòng Đang Thuê ()"));
		pane.setBackground(Color.decode("#efcf7a"));
		
		Box leftBox = Box.createVerticalBox();
//		leftBox.add(clock = new DigitalClock());
		leftBox.add(Box.createVerticalStrut(10));
		leftBox.add(btnPane);
		leftBox.add(Box.createVerticalStrut(5));
		leftBox.add(pane);
		leftPane.add(leftBox);
		leftPane.setBackground(Color.decode("#efcf7a"));
		
		//Pane Right
		panePhong = new JPanel();
		panePhong.setLayout(new BorderLayout());
		paneBtnPhong = new JPanel(new BorderLayout());
		paneTraCuuP = new JPanel();

		//Table Phong
		phongModel = new DefaultTableModel(headersTableP, 0);
		phongTable = new JTable(phongModel) {
			@Override
            public Class<?> getColumnClass(int column) {
                if(convertColumnIndexToModel(column)==3) return Double.class;
                return super.getColumnClass(column);
            }
		};	
		phongTable.setAutoCreateRowSorter(true);
		phongTable.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		phongTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		phongTable.setRowHeight(40);
		phongTable.setFont(new Font("serif", Font.PLAIN, 17));
		phongTable.setDefaultRenderer(Double.class, new DefaultTableCellRenderer(){
            @Override
            public Component getTableCellRendererComponent(JTable table,Object value,boolean isSelected,boolean hasFocus,int row,int column) {
                Component c = super.getTableCellRendererComponent(table,value,isSelected,hasFocus,row,column);
                c.setForeground(((String) value).equalsIgnoreCase("Đang thuê") ? Color.RED :
                	((String) value).equalsIgnoreCase("Còn trống") ? Color.green : Color.BLUE);
                return c;
			}
		});
		
//		DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
//		centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
//		phongTable.setDefaultRenderer(String.class, centerRenderer);
		
		JScrollPane scrollPhongTable = new JScrollPane(phongTable, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scrollPhongTable.setBorder(BorderFactory.createTitledBorder(blackLine, "Danh sách thông tin phòng"));
		scrollPhongTable.setBackground(Color.decode("#efcf7a"));
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
		
		//Box các btn phòng
		Box btnPhongBox = Box.createHorizontalBox();
		
		//pane tra cứu phòng
		Box traCuuB = Box.createVerticalBox();
		Box traCuuB1 = Box.createHorizontalBox();
		Box traCuuB2 = Box.createHorizontalBox();
		
		JPanel panePhongLb = new JPanel();
		panePhongLb.setBackground(Color.decode("#efcf7a"));
		panePhongLb.add(phongLb = new JLabel("Phòng"));
		traCuuB1.add(panePhongLb);
		
		JPanel panePhongF = new JPanel();
		panePhongF.setBackground(Color.decode("#efcf7a"));
		panePhongF.add(phongF = new JTextField(15));
		phongF.setFont(new Font("Arial", Font.PLAIN, 16));
		traCuuB1.add(panePhongF);
		
		traCuuB1.add(timKiemPBtn = new ButtonGradient("Tìm kiếm", imgSearch));
		
		JPanel paneGiaLb = new JPanel();
		paneGiaLb.setBackground(Color.decode("#efcf7a"));
		paneGiaLb.add(giaPLb = new JLabel("Giá phòng"));
		traCuuB2.add(paneGiaLb);
		
		JPanel paneGiaPhongF = new JPanel();
		paneGiaPhongF.setBackground(Color.decode("#efcf7a"));
		paneGiaPhongF.add(giaPhongF = new JTextField(15));
		giaPhongF.setFont(new Font("Arial", Font.PLAIN, 16));
		traCuuB2.add(paneGiaPhongF);
		
		traCuuB2.add(lamMoiBtn = new ButtonGradient("Làm mới", imgReset));		
		
		traCuuB.add(traCuuB1);
		traCuuB.add(Box.createVerticalStrut(10));
		traCuuB.add(traCuuB2);
		paneTraCuuP.add(traCuuB);
		paneTraCuuP.setBackground(Color.decode("#efcf7a"));
		paneTraCuuP.setBorder(BorderFactory.createTitledBorder(blackLine, "Tra cứu"));
		
		phongLb.setPreferredSize(giaPLb.getPreferredSize());
		lamMoiBtn.setPreferredSize(timKiemPBtn.getPreferredSize());
		
		//Btn Phong
		String[] headersTinhTrang = {"Tất cả","Còn trống", "Đang thuê", "Đã đặt trước"};
		String[] headersSoNguoi = {"Tất cả", "7", "10", "15"};
		String[] headersLoaiPhong = {"Tất cả", "Vip", "Thường"};
		
		//pane combobox tình trạng 
		Box btnPhongBox1 = Box.createHorizontalBox();
		btnPhongBox1.add(tinhTrangLb = new JLabel("Tình trạng phòng "));
		btnPhongBox1.add(tinhTrangB = new JComboBox<>(headersTinhTrang));
		JPanel paneCBTinhTrang = new JPanel();
		paneCBTinhTrang.setBackground(Color.decode("#efcf7a"));
		paneCBTinhTrang.add(btnPhongBox1);
		btnPhongBox.add(paneCBTinhTrang);
		btnPhongBox.add(Box.createHorizontalStrut(5));

		
		//pane combobox số người
		Box btnPhongBox2 = Box.createHorizontalBox();
		btnPhongBox2.add(soNguoiLb = new JLabel("Số người "));
		btnPhongBox2.add(soNguoiB = new JComboBox<>(headersSoNguoi));
		JPanel paneCBSoNguoi = new JPanel();
		paneCBSoNguoi.setBackground(Color.decode("#efcf7a"));
		paneCBSoNguoi.add(btnPhongBox2);
		btnPhongBox.add(paneCBSoNguoi);
		btnPhongBox.add(Box.createHorizontalStrut(5));
		
		//pane combobox loại phòng
		Box btnPhongBox3 = Box.createHorizontalBox();
		btnPhongBox3.add(loaiPLb = new JLabel("Loại phòng "));
		btnPhongBox3.add(loaiPhongB = new JComboBox<>(headersLoaiPhong));
		JPanel paneCBLoaiPhong = new JPanel();
		paneCBLoaiPhong.setBackground(Color.decode("#efcf7a"));
		paneCBLoaiPhong.add(btnPhongBox3);
		btnPhongBox.add(paneCBLoaiPhong);
		btnPhongBox.add(Box.createHorizontalStrut(5));
		
		//Thêm pane tra cứu vào Box
		btnPhongBox.add(paneTraCuuP);
		
		soNguoiB.setPreferredSize(tinhTrangB.getPreferredSize());
		loaiPhongB.setPreferredSize(tinhTrangB.getPreferredSize());	
		
		paneBtnPhong.add(clock = new DigitalClock(), BorderLayout.WEST);
		paneBtnPhong.add(btnPhongBox, BorderLayout.CENTER);
		paneBtnPhong.setBackground(Color.decode("#efcf7a"));
		
		panePhong.add(paneBtnPhong, BorderLayout.NORTH);
//		panePhong.setBorder(BorderFactory.createTitledBorder(blackLine, "Thông tin phòng"));
		panePhong.setBackground(Color.decode("#efcf7a"));
		
		
		
//		Box rightBox = Box.createVerticalBox();
//		rightBox.add(panePhong);
//		rightBox.add(panePDP);
		
		
		this.setLayout(new BorderLayout());
//		this.add(leftPane, BorderLayout.EAST);
		this.add(panePhong, BorderLayout.CENTER);
		
		readDataToTablePhong();
		
		thuePBtn.addActionListener(e -> xuLyThuePhong());
//		datPBtn.addActionListener(e -> xuLyDatPhong());
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
				.sorted((p1 , p2) -> p1.getTenPhong().compareTo(p2.getTenPhong()))
				.collect(Collectors.toList());
		for(Phong p : dsP) {
			if(!p.getTinhTrangPhong().trim().equalsIgnoreCase("Đã xóa"))
				phongModel.addRow(new Object[] {p.getTenPhong(), p.getLoaiPhong().getTenLoaiPhong(), p.getLoaiPhong().getSucChua(),
					p.getTinhTrangPhong(), p.getLoaiPhong().getGiaLoaiPhong()});	
		}
	}
	
	
	private void readAllDateToTablePhong2() {
		phongModel.setRowCount(0);
		List<entity.Phong> dsP = phongDao.getAllDataForTableDatPhong().stream()
				.sorted((p1 , p2) -> p1.getTenPhong().compareTo(p2.getTenPhong()))
				.collect(Collectors.toList());
		for(Phong p : dsP) {
			if(p.getTinhTrangPhong().trim().equalsIgnoreCase("Còn trống"))
				phongModel.addRow(new Object[] {p.getTenPhong(), p.getLoaiPhong().getTenLoaiPhong(), p.getLoaiPhong().getSucChua(),
					p.getTinhTrangPhong(), p.getLoaiPhong().getGiaLoaiPhong()});	
		}
	}
	
	private void readAllDateToTablePhong3() {
		phongModel.setRowCount(0);
		List<entity.Phong> dsP = phongDao.getAllDataForTableDatPhong().stream()
				.sorted((p1 , p2) -> p1.getTenPhong().compareTo(p2.getTenPhong()))
				.collect(Collectors.toList());
		for(Phong p : dsP) {
			if(p.getTinhTrangPhong().trim().equalsIgnoreCase("Đang thuê"))
				phongModel.addRow(new Object[] {p.getTenPhong(), p.getLoaiPhong().getTenLoaiPhong(), p.getLoaiPhong().getSucChua(),
					p.getTinhTrangPhong(), p.getLoaiPhong().getGiaLoaiPhong()});	
		}
	}
	
	private void xuLyLocTheoLoaiPhong() {
		phongModel.setRowCount(0);
		List<Phong> dsP = phongDao.timKiemPhongTheoLoaiPhong((String) loaiPhongB.getSelectedItem());
		if(((String)loaiPhongB.getSelectedItem()).equalsIgnoreCase("Tất cả")) {
			readAllDateToTablePhong();
		}
		for(Phong p : dsP) {			
			phongModel.addRow(new Object[] {p.getTenPhong(), p.getLoaiPhong().getTenLoaiPhong(), p.getLoaiPhong().getSucChua(),
					p.getTinhTrangPhong(), p.getLoaiPhong().getGiaLoaiPhong()});
		}
	}
	
	private void xuLyLocTheoSoNguoi() {
		phongModel.setRowCount(0);
		List<Phong> dsP = phongDao.timKiemPhongTheoSoNguoi(Integer.valueOf((String)soNguoiB.getSelectedItem()));
		if(((String)soNguoiB.getSelectedItem()).equalsIgnoreCase("Tất cả")) {
			readAllDateToTablePhong();
		}
		for(Phong p : dsP) {			
			phongModel.addRow(new Object[] {p.getTenPhong(), p.getLoaiPhong().getTenLoaiPhong(), p.getLoaiPhong().getSucChua(),
					p.getTinhTrangPhong(), p.getLoaiPhong().getGiaLoaiPhong()});
		}
	}
	
	public void xuLyLocTheoTinhTrangPhong() {
		phongModel.setRowCount(0);
		List<Phong> dsP = phongDao.timKiemPhongTheoTinhTrangPhong((String) tinhTrangB.getSelectedItem());
		if(((String)tinhTrangB.getSelectedItem()).equalsIgnoreCase("Tất cả")) {
			readAllDateToTablePhong();
		}
		for(Phong p : dsP) {			
			phongModel.addRow(new Object[] {p.getTenPhong(), p.getLoaiPhong().getTenLoaiPhong(), p.getLoaiPhong().getSucChua(),
					p.getTinhTrangPhong(), p.getLoaiPhong().getGiaLoaiPhong()});
		}
	}
	
	private void xuLyTimKiemPhong() {
		if(phongF.getText().equals("") && giaPhongF.getText().equals("")) {
		
		}
		else {
			phongModel.setRowCount(0);
			List<Phong> dsP = phongDao.getAllDataForTableDatPhong();
			if(!phongF.getText().trim().equals("")) {
				dsP = phongDao.timKiemPhongTheoTenPhong(phongF.getText().trim());
			}
				
			if(!giaPhongF.getText().trim().equals("")) {
				dsP = dsP.stream().filter(p -> p.getLoaiPhong().getGiaLoaiPhong() == Double.valueOf(giaPhongF.getText().trim()))
								  .collect(Collectors.toList());
			}
				
			for(Phong p : dsP) {	
					System.out.println();
					phongModel.addRow(new Object[] {p.getTenPhong(), p.getLoaiPhong().getTenLoaiPhong(), p.getLoaiPhong().getSucChua(),
							p.getTinhTrangPhong(), p.getLoaiPhong().getGiaLoaiPhong()});
			}
		}
	}
	
	private void xuLyNhanPhongCho() {
		phieuDatPhong = new PhongCho();
		phieuDatPhong.setVisible(true);
		phieuDatPhong.setLocationRelativeTo(null);
	}
	
	private void xuLyThuePhong() {
		if(phongTable.getSelectedRow() != -1) {
			if(((String)phongModel.getValueAt(phongTable.getSelectedRow(), 3)).equalsIgnoreCase("Còn trống")) {
				thuePhong = new ThuePhong();
				thuePhong.setVisible(true);
				thuePhong.setAlwaysOnTop(true);
				thuePhong.setLocationRelativeTo(null);
			}
			else {
				JOptionPane.showMessageDialog(this, "Bạn chỉ được thuê phòng đang trống!!");
			}
		}
		else {
			JOptionPane.showMessageDialog(this, "Hãy chọn phòng bạn muốn đặt!!");
		}
	}
	
	private void xuLyChuyenPhong() {
		if(phongTable.getSelectedRow() != -1) {
			if(((String)phongModel.getValueAt(phongTable.getSelectedRow(), 3)).equalsIgnoreCase("Đang thuê")) {
				chuyenPhong = new ChuyenPhong((String)phongModel.getValueAt(phongTable.getSelectedRow(), 0));
				chuyenPhong.setVisible(true);
				chuyenPhong.setAlwaysOnTop(true);
				chuyenPhong.setLocationRelativeTo(null);
			}
			else {
				JOptionPane.showMessageDialog(this, "Bạn chỉ được chuyển phòng đang thuê!!");
			}
		}
		else {
			JOptionPane.showMessageDialog(this, "Hãy chọn phòng bạn muốn chuyển!!");
		}
	}
	
	private void xuLyChiTietPhong() {
		chiTietPhong = new ChiTietPhong();
		chiTietPhong.setVisible(true);
		chiTietPhong.setAlwaysOnTop(true);
		chiTietPhong.setLocationRelativeTo(null);
	}
	
	private void xuLyDichVuPhong() {
		if(phongTable.getSelectedRow() != -1) {
			if(((String) phongModel.getValueAt(phongTable.getSelectedRow(), 3)).equalsIgnoreCase("Đang thuê")) {
				dichVuPhong = new DichVuPhong();
				dichVuPhong.setVisible(true);
				dichVuPhong.setAlwaysOnTop(true);
				dichVuPhong.setLocationRelativeTo(null);
			}
			else {
				JOptionPane.showMessageDialog(this, "Bạn chỉ được thêm dịch vụ vào phòng đang thuê!!");
			}
		}
		else {
			JOptionPane.showMessageDialog(this, "Hãy chọn phòng bạn muốn thêm dịch vụ!!");
		}
	}
	
	private void xuLyTinhTien() {
		tinhTien = new TinhTien();
		tinhTien.setVisible(true);
		tinhTien.setAlwaysOnTop(true);
		tinhTien.setLocationRelativeTo(null);
	}
	
	private void readDataToTablePhong() {
		List<entity.Phong> dsP = phongDao.getAllDataForTableDatPhong().stream()
										.sorted((p1 , p2) -> p1.getTenPhong().compareTo(p2.getTenPhong()))
										.collect(Collectors.toList());
		
		long soPhongCho = dsP.stream().filter(p -> p.getTinhTrangPhong().equalsIgnoreCase("Đã đặt trước")).count();
		long soPhongThue = dsP.stream().filter(p -> p.getTinhTrangPhong().equalsIgnoreCase("Đang Thuê")).count();
		lbPhongTrong.setText("Phòng Trống ( " + soPhongCho +" )");
		lbPhongDangThue.setText("Phòng Thuê ( " + soPhongThue +" )");
		lbPhongTrong.setFont(new Font("sanserif", Font.BOLD, 14));
		lbPhongCho.setFont(new Font("sanserif", Font.BOLD, 14));
		lbPhongDangThue.setFont(new Font("sanserif", Font.BOLD, 14));
		
		for(Phong p : dsP) {
			if(p.getTinhTrangPhong().equalsIgnoreCase("Còn Trống"))
				phongModel.addRow(new Object[] {p.getTenPhong(), p.getLoaiPhong().getTenLoaiPhong(), p.getLoaiPhong().getSucChua(),
					p.getTinhTrangPhong(), p.getLoaiPhong().getGiaLoaiPhong()});
		}
	}
	
	
	public class ThuePhong extends JFrame{
		private JTextField tenPhongF, loaiPhongF, giaPhongF, sucChuaF, tinhTrangF, sdtKhachF, tenKhachF, soLuongF;
		private JTextArea ghiChuA;
		private JDateChooser ngayNhanCD;
		private TimePicker gioNhanTP;
		private JButton kiemTraBtn, quayLaiBtn, thuePhongBtn;
		private JComboBox<String> cbLuaChon;
		private String tenP;
		private daoPhong pdao;
		private daoKhachHang kdao;
		private DAOPhieuDatPhong pdpDao;
		private DAOHoaDon hdDao;
		private DAOCTHD cthdDao;
		private MaTuDong maTuDong = new MaTuDong();
		private String maKH = "", tenKH = "", sdtKH = "";
		SimpleDateFormat dayFormat = new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat gioFormat = new SimpleDateFormat("hh:mm:ss");
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");

		public ThuePhong() {
			setSize(650, 500);
			hdDao = new DAOHoaDon();
			pdao = new daoPhong();
			kdao = new daoKhachHang();
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
			
			JLabel luaChonLb, soLuongLb, tenPhongLb, loaiPhongLb, giaPhongLb, sucChuaLb, tinhTrangLb, sdtKhachLb, tenKhachLb, ngayNhanPhongLb, gioNhanPhongLb, ghiChuLb, tieuDeLb;
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
			
			String[] headersLuaChon = {"Đặt Trước", "Thuê Liền"};
			cbLuaChon = new JComboBox<>(headersLuaChon);
			//set thông tin phòng 
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
			
			Box loaiPhongBox = Box.createHorizontalBox();
			loaiPhongBox.add(loaiPhongLb = new JLabel("Loại phòng:"));
			loaiPhongBox.add(Box.createHorizontalStrut(5));
			loaiPhongBox.add(loaiPhongF = new JTextField(10));
			loaiPhongF.setFont(new Font("Arial", Font.PLAIN, 16));
			loaiPhongF.setBorder(null);
			loaiPhongF.setEditable(false);
			
			Box giaPhongBox = Box.createHorizontalBox();
			giaPhongBox.add(giaPhongLb = new JLabel("Giá phòng:"));
			giaPhongBox.add(Box.createHorizontalStrut(5));
			giaPhongBox.add(giaPhongF = new JTextField(10));
			giaPhongF.setFont(new Font("Arial", Font.PLAIN, 16));
			giaPhongF.setBorder(null);
			giaPhongF.setEditable(false);
			
			Box sucChuaBox = Box.createHorizontalBox();
			sucChuaBox.add(sucChuaLb = new JLabel("Sức chứa:"));
			sucChuaBox.add(Box.createHorizontalStrut(5));
			sucChuaBox.add(sucChuaF = new JTextField(10));
			sucChuaF.setFont(new Font("Arial", Font.PLAIN, 16));
			sucChuaF.setBorder(null);
			sucChuaF.setEditable(false);
			
			Box tinhTrangBox = Box.createHorizontalBox();
			tinhTrangBox.add(tinhTrangLb = new JLabel("Tình trạng:"));
			tinhTrangBox.add(Box.createHorizontalStrut(5));
			tinhTrangBox.add(tinhTrangF = new JTextField(10));
			tinhTrangF.setFont(new Font("Arial", Font.PLAIN, 16));
			tinhTrangF.setBorder(null);
			tinhTrangF.setEditable(false);
			
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
			
			//set thông tin thuê phòng
			rightPanel = new JPanel();
			rightPanel.setBorder(BorderFactory.createTitledBorder(border, "Thông tin thuê phòng"));
			rightPanel.setBackground(Color.decode("#cccccc"));
			Box thongTinThuePhongBox = Box.createVerticalBox();
			Box boxForLuaChon = Box.createHorizontalBox();
			boxForLuaChon.add(luaChonLb = new JLabel("Lựa Chọn:"));
			boxForLuaChon.add(Box.createHorizontalStrut(5));
			boxForLuaChon.add(cbLuaChon);
					
			Box sdtKhachBox = Box.createHorizontalBox();
			sdtKhachBox.add(sdtKhachLb = new JLabel("SĐT Khách:"));
			JPanel sdtKhachPane = new JPanel();
			sdtKhachPane.add(sdtKhachF = new JTextField(15));
			sdtKhachPane.setBackground(Color.decode("#cccccc"));
			sdtKhachBox.add(sdtKhachPane);
			sdtKhachBox.add(Box.createHorizontalStrut(5));
			sdtKhachBox.add(kiemTraBtn = new ButtonGradient("Kiểm tra", imgCheck));
			kiemTraBtn.setBackground(Color.decode("#6fa8dc"));
			
			Box tenKhachBox = Box.createHorizontalBox();
			tenKhachBox.add(tenKhachLb = new JLabel("Tên khách:"));
			tenKhachBox.add(Box.createHorizontalStrut(5));
			tenKhachBox.add(tenKhachF = new JTextField(15));
			
			Box soLuongBox = Box.createHorizontalBox();
			soLuongBox.add(soLuongLb = new JLabel("Số lượng khách:"));
			soLuongBox.add(Box.createHorizontalStrut(5));
			soLuongBox.add(soLuongF = new JTextField(15));
			
			Box ngayNhanBox = Box.createHorizontalBox();
			ngayNhanBox.add(ngayNhanPhongLb = new JLabel("Ngày nhận phòng:"));
			ngayNhanBox.add(Box.createHorizontalStrut(5));
			ngayNhanBox.add(ngayNhanCD = new JDateChooser());
			
			Box gioNhanBox = Box.createHorizontalBox();
			gioNhanBox.add(gioNhanPhongLb = new JLabel("Giờ nhận phòng:"));
			gioNhanBox.add(Box.createHorizontalStrut(5));
			gioNhanBox.add(gioNhanTP = new TimePicker());
			
			Box ghiChuBox = Box.createHorizontalBox();
			ghiChuBox.add(ghiChuLb = new JLabel("Ghi chú:"));
			ghiChuBox.add(Box.createHorizontalStrut(5));
			ghiChuBox.add(ghiChuA = new JTextArea(3, 15));
			
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
			bottomPanelRight.add(thuePhongBtn = new ButtonGradient("Thuê phòng", imgCheck));
			bottomPanelRight.setBackground(Color.decode("#e6dbd1"));
			bottomPanelLeft.setBackground(Color.decode("#e6dbd1"));
			bottomPanel.add(bottomPanelLeft);
			bottomPanel.add(bottomPanelRight);
			
			quayLaiBtn.setHorizontalAlignment(SwingConstants.RIGHT);
			quayLaiBtn.setBackground(Color.decode("#6fa8dc"));
			thuePhongBtn.setBackground(Color.decode("#6fa8dc"));

			//set tiêu đề
			titlePanel.setBackground(Color.decode("#6fa8dc"));
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
			Phong phong = pdao.timKiemPhongTheoTenPhong((String)phongModel.getValueAt(phongTable.getSelectedRow(), 0)).get(0);
			tenPhongF.setText(phong.getTenPhong());
			loaiPhongF.setText(phong.getLoaiPhong().getTenLoaiPhong());
			giaPhongF.setText(phong.getLoaiPhong().getGiaLoaiPhong() + "");
			sucChuaF.setText(phong.getLoaiPhong().getSucChua() + "");
			tinhTrangF.setText(phong.getTinhTrangPhong());
		}
		
		private void xuLyThoiGian() {
			if(((String) cbLuaChon.getSelectedItem()).equalsIgnoreCase("Thuê liền")) {
				Date date = new Date();
				ngayNhanCD.setDate(date);
				gioNhanTP.setTime(LocalTime.now());
				
			}
		}
		
		private void xuLyKiemTraSDT() {
			tenKH = tenKhachF.getText().trim();
			sdtKH = sdtKhachF.getText().trim();
			List<KhachHang> dsKH = kdao.timKiemKHTheoSDT(sdtKhachF.getText());
			if(dsKH.size() > 0) {
				tenKhachF.setText(dsKH.get(0).getTenKH());
				maKH = kdao.timKiemKHTheoSDT(sdtKH).get(0).getMaKH();
			}
			else {
				JOptionPane.showMessageDialog(this, "Khách hàng mới");
				List<KhachHang> dsKH2 = kdao.getAllDataKH().stream()
														  .sorted((kh1, kh2) -> kh1.getMaKH().compareTo(kh2.getMaKH())).toList();
				String str = dsKH2.get(dsKH2.size() -1).getMaKH();
				maKH = maTuDong.formatMa(str);
				
			}
		}
		
		private void xuLyThuePhong() throws NumberFormatException, ParseException {
			String hour = gioNhanTP.getTime().toString().substring(0, 2);
			String minute = gioNhanTP.getTime().toString().substring(3, 5);
			String time = hour + ":" + minute + ":" + "00";
			String date = dayFormat.format(ngayNhanCD.getDate());
			tenKH = tenKhachF.getText().trim();
			sdtKH = sdtKhachF.getText().trim();
			
			List<KhachHang> dsKH = kdao.timKiemKHTheoSDT(sdtKhachF.getText());
			if(dsKH.size() == 0) {
				KhachHang kh = new KhachHang(maKH, sdtKH, tenKH);
				kdao.createKhach(kh);
			}
			
			if(((String)cbLuaChon.getSelectedItem()).equalsIgnoreCase("Đặt trước")) {
				List<PhieuDatPhong> dsPDP = pdpDao.getAllDataPDP().stream()
						  .sorted((pdp1, pdp2) -> pdp1.getMaPDP().compareTo(pdp2.getMaPDP())).toList();
				String s = dsPDP.get(dsPDP.size() - 1).getMaPDP();
				String maKM = maTuDong.formatMa(s);
				Phong phong = pdao.timKiemPhongChinhXacTheoTenPhong(tenPhongF.getText().trim()).get(0);
				PhieuDatPhong pdp = new PhieuDatPhong(maKM, new KhachHang(maKH, sdtKH, tenKH), 
						new NhanVien("NV0001"), phong, dateFormat.parse(date + " " + time), dateFormat.parse(date + " " + time),
						Integer.valueOf(soLuongF.getText()), 1, ghiChuA.getText());
				try {
					if(pdpDao.createPDP(pdp)) {
						JOptionPane.showMessageDialog(this, "Đặt phòng thành công!!!");
					}
				} catch (Exception e) {
					// TODO: handle exception
					e.printStackTrace();
					
				}
			}
			
			else {
				List<HoaDon> dsHD = hdDao.getAllDataHD().stream().
						sorted((hd1, hd2) -> hd1.getMaHoaDon().compareTo(hd2.getMaHoaDon())).toList();
				String s = dsHD.get(dsHD.size() - 1).getMaHoaDon();
				String maHD = maTuDong.formatMa(s);
				HoaDon hd = new HoaDon(maHD, gioNhanTP.getTime(), dateFormat.parse(date + " " + time), new NhanVien("NV0001"),
						new KhachHang(maKH, sdtKH, tenKH), new KhuyenMai("KM0001"));
				Phong phong = pdao.timKiemPhongChinhXacTheoTenPhong(tenPhongF.getText().trim()).get(0);
				ChiTietHoaDon cthd = new ChiTietHoaDon(phong, hd, new Date(),
						new Date());
				try {
					if(hdDao.createHD(hd) && cthdDao.createCTPhong(cthd) && pdao.capNhatPhongTheoTinhTrang("Đang thuê", phong.getMaPhong())) {
						JOptionPane.showMessageDialog(this, "Thuê phòng thành công!!!");
						readAllDateToTablePhong2();
					}
				} catch (Exception e) {
					// TODO: handle exception
					e.printStackTrace();
				}			
			}
		}
	}
	
	
	public class ChuyenPhong extends JFrame {
		private JLabel lbTenPhong, lbTenKhach, lbNgayThue, lbGioThue, lbGioHienTai, lbThoiGianSuDung, lbPhong, lbGiaPhong,
				lbLoaiPhong, lbSucChua;
		private JTextField tfTenPhong, tfTenKhach, tfNgayThue, tfGioThue, tfGioHienTai, tfThoiGianSuDung, tfPhong,
				tfGiaPhong;
		private JComboBox<String> cbLoaiPhong, cbSucChua;
		private JButton btnTimKiem, btnLamMoi, btnQuayLai, btnChuyenPhong;
		private JTable tablePhongTrong;
		private DefaultTableModel modelPhongTrong;
		private String tenPhong;
		private daoPhong pdao;
		private daoKhachHang kdao;
		private DAOPhieuDatPhong pdpDao;
		private DAOHoaDon hdDao;
		private DAOCTHD cthdDao;
		private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");

		public String getTenPhong() {
			return tenPhong;
		}

		public void setTenPhong(String tenPhong) {
			this.tenPhong = tenPhong;
		}

		public ChuyenPhong(String tenPhong) {
			hdDao = new DAOHoaDon();
			pdao = new daoPhong();
			kdao = new daoKhachHang();
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
			topPane.setBackground(Color.decode("#6fa8dc"));
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
			bottomPaneRight.setBackground(Color.decode("#cccccc"));
			bottomPaneRight.add(btnChuyenPhong = new ButtonGradient("Chuyển phòng", imgChange));
			btnChuyenPhong.setBackground(Color.decode("#6fa8dc"));

			bottomPaneLeft = new JPanel(new FlowLayout(FlowLayout.LEFT));
			bottomPaneLeft.setBackground(Color.decode("#cccccc"));
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
			Phong p = pdao.timKiemPhongChinhXacTheoTenPhong((String)modelPhongTrong.getValueAt(tablePhongTrong.getSelectedRow(), 1)).get(0);
			ChiTietHoaDon cthd = cthdDao.timKiemCTHDTheoTenPhong(tenPhong).get(0);	
			System.out.println(cthd.getPhong().getTenPhong());
			ChiTietHoaDon chiTietHD = new ChiTietHoaDon(p, new HoaDon(cthd.getHd().getMaHoaDon()), new Date(), new Date());
			cthd.setThoiGianTraPhong(new Date());
			try {
				if(cthdDao.createCTPhong(chiTietHD) && cthdDao.capNhatGioTraPhong(cthd)) {
					if(pdao.capNhatTinhTrangPhongTheoTenPhong("Còn trống", tenPhong) && pdao.capNhatTinhTrangPhongTheoTenPhong("Đang thuê", p.getTenPhong()))
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
			String ngay = str2[0];
			String gio = str2[1];
			String[] time = gio.split(":");
			String hour = time[0];
			String minute = time[1];
			int h = Integer.valueOf(hour);
			int m = Integer.valueOf(minute);
			LocalTime tgNhanPhong = LocalTime.of(h, m);
			LocalTime tgHienTai = LocalTime.now();
			
			tfTenPhong.setText(tenPhong.trim());
			tfTenKhach.setText(tenKH);
			tfNgayThue.setText(ngay);
			tfGioThue.setText(tgNhanPhong.format(formatter));
			tfGioHienTai.setText(tgHienTai.format(formatter));
			
			int thoiGianSuDung =(tgHienTai.getHour()*60 + tgHienTai.getMinute()) -  (h*60 + m);
			tfThoiGianSuDung.setText(thoiGianSuDung + " Phút");
		}
		
		private void readDataToTablePhong() {
			List<Phong> dsP = pdao.getAllDataForTableDatPhong().stream()
											.sorted((p1 , p2) -> p1.getTenPhong().compareTo(p2.getTenPhong()))
											.collect(Collectors.toList());
			for(Phong p : dsP) {
				if(p.getTinhTrangPhong().equalsIgnoreCase("Còn Trống"))
					modelPhongTrong.addRow(new Object[] {p.getMaPhong(), p.getTenPhong(), p.getLoaiPhong().getTenLoaiPhong(),
						p.getLoaiPhong().getSucChua(), p.getLoaiPhong().getGiaLoaiPhong()});
			}
		}
	}
	
	public class PhongCho extends JFrame{
		private JButton nhanPBtn, huyPBtn, timKiemSDTBtn;
		private JTable phieuDatPTable;
		private  DefaultTableModel phieuDatPModel;
		private JTextField sdtF;
		private JComboBox<String> tinhTrangPhieuB;
		private daoPhong pdao;
		private daoKhachHang kdao;
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
			pdao = new daoPhong();
			
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
			String[] headersTablePDP = {"Mã đặt phòng", "Tên phòng", "Ngày đặt", "Giờ đặt", "Tên khách", "SĐT khách", "Nhân viên", "Tình trạng"};
			
			//set tiêu đề
			Font font = new Font("Arial", Font.BOLD, 24);
			titlePanel.setBackground(Color.decode("#6fa8dc"));
			titlePanel.add(tieuDeLb = new JLabel("PHÒNG CHỜ"));
			tieuDeLb.setFont(font);
			
			//Table PDP 
			phieuDatPModel = new DefaultTableModel(headersTablePDP, 0);
			phieuDatPTable = new JTable(phieuDatPModel);
			phieuDatPTable.setAutoCreateRowSorter(true);
			phieuDatPTable.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
			phieuDatPTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			JScrollPane scrollPDPTable = new JScrollPane(phieuDatPTable, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
			panePDP.add(scrollPDPTable, BorderLayout.CENTER);
			phieuDatPTable.setRowHeight(25);
					
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
//			Box btnNhanHuyBox = Box.createHorizontalBox();
//			btnNhanHuyBox.add(Box.createHorizontalStrut(1000));
//			btnNhanHuyBox.add(huyPBtn = new JButton("Hủy", imgCancel));
//			btnNhanHuyBox.add(Box.createHorizontalStrut(40));
//			btnNhanHuyBox.add(nhanPBtn = new JButton("Nhận", imgCheck));
					
			JPanel paneForBtnNhanHuyBox = new JPanel(new FlowLayout(FlowLayout.RIGHT));
			paneForBtnNhanHuyBox.add(huyPBtn = new ButtonGradient("Hủy", imgCancel));
			paneForBtnNhanHuyBox.add(Box.createHorizontalStrut(50));
			paneForBtnNhanHuyBox.add(nhanPBtn = new ButtonGradient("Nhận", imgCheck));
			paneForBtnNhanHuyBox.setBackground(Color.decode("#cccccc"));
			panePDP.add(paneForBtnNhanHuyBox, BorderLayout.SOUTH);
			
			JPanel pnlMain = new JPanel(new BorderLayout());
			pnlMain.add(titlePanel, BorderLayout.NORTH);
			pnlMain.add(panePDP, BorderLayout.CENTER);
			this.getContentPane().add(pnlMain);
					
			huyPBtn.setBackground(Color.decode("#6fa8dc"));
			nhanPBtn.setBackground(Color.decode("#6fa8dc"));	
			
			readDateToTablePhongCho();
			
			huyPBtn.addActionListener(e -> xuLyHuyPhongCho());
			nhanPBtn.addActionListener(e -> xuLyNhanPhong());
			tinhTrangPhieuB.addActionListener(e -> xuLyLocTheoTinhTrangPDP());
			timKiemSDTBtn.addActionListener(e -> xuLyTimKiemSDT());
		}
		
		private void readDateToTablePhongCho() {
			phieuDatPModel.setRowCount(0);
			List<PhieuDatPhong> dsPDP = pdpDao.getAllDataPDP();
			for(PhieuDatPhong pdp : dsPDP) {
				String date = formatDate.format(pdp.getThoiGianDatPhong());
				String time = formatTime.format(pdp.getThoiGianDatPhong());
				String tinhTrang = "";
				if(pdp.getTinhTrangPDP() == 1)
					tinhTrang = "Chưa nhận";
				else 
					tinhTrang = "Nhận rồi";
				if(tinhTrang.equalsIgnoreCase("Chưa nhận")) {	
					String[] s = pdp.getThoiGianDatPhong().toString().split(" ");
					String str = s[1];
					String[] t = str.split(":");
			
					phieuDatPModel.addRow(new Object[] {pdp.getMaPDP(), pdp.getPhong().getTenPhong(), 
							date, t[0] + ":" + t[1], pdp.getKhachHang().getTenKH(), pdp.getKhachHang().getSdthoai(),
							pdp.getNhanVien().getTenNV(), tinhTrang});
				}
			}
		}
		
		private void xuLyLocTheoTinhTrangPDP() {
			
		}
		
		private void xuLyHuyPhongCho() {
			
		}
		
		private void xuLyNhanPhong() {
			try {
				PhieuDatPhong pdp = pdpDao.timKiemPDPTheoMa((String) phieuDatPModel.getValueAt(phieuDatPTable.getSelectedRow(), 0)).get(0);
				System.out.println(pdp.getPhong().getTenPhong());
				List<HoaDon> dsHD = hdDao.getAllDataHD().stream().
						sorted((hd1, hd2) -> hd1.getMaHoaDon().compareTo(hd2.getMaHoaDon())).toList();
				String s = dsHD.get(dsHD.size() - 1).getMaHoaDon();
				String maHD = maTuDong.formatMa(s);
				HoaDon hd = new HoaDon(maHD, LocalTime.now(), new Date(), pdp.getNhanVien(),
						pdp.getKhachHang(), new KhuyenMai("KM0001"));
				ChiTietHoaDon chiTietHoaDon = new ChiTietHoaDon(pdp.getPhong(), hd, new Date(), new Date());
				if(hdDao.createHD(hd) && cthdDao.createCTPhong(chiTietHoaDon) 
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
		
		private void xuLyTimKiemSDT () {
			
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
			
			//set top Pane
			lbTieuDe = new JLabel("DỊCH VỤ PHÒNG");
			lbTieuDe.setFont(font);
			topPane = new JPanel();
			topPane.setBackground(Color.decode("#6fa8dc"));
			topPane.add(lbTieuDe);
			
			//Thông tin phiếu đặt phòng
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
		
			tfMaPDP.setBackground(Color.decode("#cccccc")); tfMaPDP.setBorder(null); tfMaPDP.setEditable(false);
			tfTenPhong.setBackground(Color.decode("#cccccc")); tfTenPhong.setBorder(null); tfTenPhong.setEditable(false);
			tfTenKH.setBackground(Color.decode("#cccccc")); tfTenKH.setBorder(null); tfTenKH.setEditable(false);
			tfSDT.setBackground(Color.decode("#cccccc")); tfSDT.setBorder(null); tfSDT.setEditable(false);

			boxForThongTin.add(Box.createHorizontalStrut(10));
			boxForThongTin.add(box1);
			boxForThongTin.add(Box.createHorizontalStrut(50));
			boxForThongTin.add(box2);
			boxForThongTin.add(Box.createHorizontalStrut(50));
			
			JPanel paneForBackBtn = new JPanel();
			paneForBackBtn.setBackground(Color.decode("#cccccc"));
			paneForBackBtn.add(btnQuayLai = new ButtonGradient("Quay Lại", imgBack));
			btnQuayLai.setBackground(Color.decode("#6fa8dc"));
			
			thongTinPane.add(boxForThongTin, BorderLayout.CENTER);
			thongTinPane.add(paneForBackBtn, BorderLayout.EAST);
			thongTinPane.setBackground(Color.decode("#cccccc"));
			bottomPane.add(thongTinPane, BorderLayout.NORTH);
			
			//Pane for table
			boxForTable = Box.createHorizontalBox();
			
			//Table dịch vụ kho
			paneForTableDV = new JPanel(new BorderLayout());
			paneForTableDV.setBorder(BorderFactory.createTitledBorder(border, "Danh Sách Dịch Vụ"));
			paneForTableDV.setBackground(Color.decode("#cccccc"));
			JPanel paneForBtnTableDV = new JPanel();
			Box boxForPaneForBtnTableDV = Box.createHorizontalBox();
			JPanel paneForTimKiemDV1 = new JPanel();
			paneForTimKiemDV1.setBackground(Color.decode("#cccccc"));
			paneForTimKiemDV1.add(lbTimKiemDV1 = new JLabel("Tìm Kiếm Dịch Vụ: "));
			paneForTimKiemDV1.add(tfTimKiemDV1 = new JTextField(15));
			boxForPaneForBtnTableDV.add(paneForTimKiemDV1);
			boxForPaneForBtnTableDV.add(Box.createHorizontalStrut(100));
			boxForPaneForBtnTableDV.add(btnThem = new ButtonGradient("Thêm", imgAdd));
			btnThem.setBackground(Color.decode("#6fa8dc"));
			paneForBtnTableDV.add(boxForPaneForBtnTableDV);
			paneForBtnTableDV.setBackground(Color.decode("#cccccc"));
			
			String[] headers1 = {"STT", "Tên Dịch Vụ", "Đơn Giá", "Đơn Vị", "Số Lượng Tồn"};
			model1 = new DefaultTableModel(headers1, 0);	
			tableDVKho = new JTable(model1);
			tableDVKho.setRowHeight(25);
			tableDVKho.getColumnModel().getColumn(1).setPreferredWidth(200);
			
			JScrollPane scroll1 = new JScrollPane(tableDVKho, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
			paneForTableDV.add(paneForBtnTableDV, BorderLayout.NORTH);
			paneForTableDV.add(scroll1, BorderLayout.CENTER);
			
			//Table dịch vụ phòng
			paneForTableDVPhong = new JPanel(new BorderLayout());
			paneForTableDVPhong.setBorder(BorderFactory.createTitledBorder(border, "Dịch Vụ Đã Thêm"));
			paneForTableDVPhong.setBackground(Color.decode("#cccccc"));
			JPanel paneForBtnTableDVP = new JPanel();
			Box boxForPaneForBtnTableDVP = Box.createHorizontalBox();
			JPanel paneForTimKiemDV2 = new JPanel();
			paneForTimKiemDV2.add(lbTimKiemDV2 = new JLabel("Tìm Kiếm Dịch Vụ: "));
			paneForTimKiemDV2.add(tfTimKiemDV2 = new JTextField(15));
			paneForTimKiemDV2.setBackground(Color.decode("#cccccc"));
			boxForPaneForBtnTableDVP.add(paneForTimKiemDV2);
			boxForPaneForBtnTableDVP.add(Box.createHorizontalStrut(100));
			boxForPaneForBtnTableDVP.add(btnXoa = new ButtonGradient("Xóa", imgDel));
			btnXoa.setBackground(Color.decode("#6fa8dc"));
			paneForBtnTableDVP.add(boxForPaneForBtnTableDVP);
			paneForBtnTableDVP.setBackground(Color.decode("#cccccc"));
			
			String[] headers2 = {"STT", "Tên Dịch Vụ", "Đơn Giá", "Số Lượng", "Thành Tiền"};
			model2 = new DefaultTableModel(headers2, 0);
			tableDVPhong = new JTable(model2);
			tableDVPhong.setRowHeight(25);
			TableColumnModel colModelDVPhong = tableDVPhong.getColumnModel();
			TableColumn colTableDVPhong = colModelDVPhong.getColumn(3);
			colTableDVPhong.setCellEditor(new MySpinnerEditor());
			
			JScrollPane scroll2 = new JScrollPane(tableDVPhong, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
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
					xuLyTangGiamSoLuongDichVuKho();
					xuLyTangGiamSoLuongDichVu();
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
					
				}
			});

			
			btnQuayLai.addActionListener(e -> this.dispose());
			btnThem.addActionListener(e -> xuLyThemDVPhong());
			btnXoa.addActionListener(e -> xuLyXoaDVPhong());
		}
		
		private void xuLyThemDVPhong() {
			if(tableDVKho.getSelectedRow() != -1) {
				DichVu dv = dvDAO.timKiemDichVuTheoTenDV((String)model1.getValueAt(tableDVKho.getSelectedRow(), 1)).get(0);
				try {
					if(ctdvPhongDAO.createCTDVPhong(new CTDVPhong(new HoaDon(tfMaPDP.getText()), dv, 1))) {
						readDataToTableDichVuPhong();
						JOptionPane.showMessageDialog(this, "Thêm dịch vụ thành công!!!");
					}
				} catch (Exception e) {
					// TODO: handle exception
					JOptionPane.showMessageDialog(this, "Thêm dịch vụ không thành công!!!");
				}
			}
			else {
				JOptionPane.showMessageDialog(this, "Hãy chọn dịch vụ bạn muốn thêm vào phòng!!!");
			}
		}
		
		private void xuLyXoaDVPhong() {
			if(tableDVPhong.getSelectedRow() != -1) {
				DichVu dv = dvDAO.timKiemDichVuTheoTenDV((String) model2.getValueAt(tableDVPhong.getSelectedRow(), 1)).get(0);
				CTDVPhong ctdvPhong = ctdvPhongDAO.timKiemCTDVPhongTheoTenDV(tfMaPDP.getText(), dv.getMaDichVu()).get(0);
				try {
					int soLuong = (int) model2.getValueAt(tableDVPhong.getSelectedRow(), 3);
					int soLuongBanDau = dv.getSoLuong();
					
					if(ctdvPhongDAO.capNhatSoLuongDichVu(soLuong + soLuongBanDau, (String) model2.getValueAt(tableDVPhong.getSelectedRow(), 1)) &&
							ctdvPhongDAO.delete(ctdvPhong.getDichVu().getMaDichVu())) {
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
			Phong p = phongDao.timKiemPhongChinhXacTheoTenPhong((String)phongModel.getValueAt(phongTable.getSelectedRow(), 0)).get(0);
			List<ChiTietHoaDon> dsCTHD = cthdDao.timKiemCTHDTheoTenPhong((String)phongModel.getValueAt(phongTable.getSelectedRow(), 0));
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
			for(entity.DichVu dv : dsDV) {
				if(dv.getSoLuong() > 0)
					model1.addRow(new Object[] {++i, dv.getTenDichVu(), dv.getDonGia(), dv.getDonVi(), dv.getSoLuong()});
			}
		}
		
		private void readDataToTableDichVuPhong() {
			model2.setRowCount(0);
			List<CTDVPhong> dsCTDVPhong = ctdvPhongDAO.timKiemCTDVPhongTheoMaHD(tfMaPDP.getText());
			int i = 0;
			for(CTDVPhong ct : dsCTDVPhong) {
				model2.addRow(new Object[] {++i, ct.getDichVu().getTenDichVu(), ct.getDichVu().getDonGia(), ct.getSoLuong(), ct.tinhTienDV()});
			}
		}
		
		private void xuLyTangGiamSoLuongDichVuKho() {
			DichVu dv = dvDAO.timKiemDichVuTheoTenDV((String) model2.getValueAt(tableDVPhong.getSelectedRow(), 1)).get(0);
			System.out.println(dv.getMaDichVu());
			CTDVPhong ctdvPhong = ctdvPhongDAO.timKiemCTDVPhongTheoTenDV(tfMaPDP.getText().trim(), dv.getMaDichVu()).get(0);
			int soLuongBanDau = ctdvPhong.getSoLuong();
			int soLuongCapNhat = (int) model2.getValueAt(tableDVPhong.getSelectedRow(), 3);
			int soLuongTinhToan = dv.getSoLuong() + (soLuongBanDau - soLuongCapNhat);
			try {
				ctdvPhongDAO.capNhatSoLuongDichVu(soLuongTinhToan, dv.getTenDichVu());
				System.out.println("Tinh Toan Thanh cong");
				readDataToTableDichVu();
				
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		}
		
		private void xuLyTangGiamSoLuongDichVu() {
			int soLuong = (int) model2.getValueAt(tableDVPhong.getSelectedRow(), 3);
			String maHD = tfMaPDP.getText().trim();
			String maDV = dvDAO.timKiemDichVuTheoTenDV((String) model2.getValueAt(tableDVPhong.getSelectedRow(), 1)).get(0).getMaDichVu();
			try {
				ctdvPhongDAO.capNhatSoLuongCTDVPhong(soLuong, maDV, maHD);
				System.out.println("Thanh cong");
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		}
	}
}
