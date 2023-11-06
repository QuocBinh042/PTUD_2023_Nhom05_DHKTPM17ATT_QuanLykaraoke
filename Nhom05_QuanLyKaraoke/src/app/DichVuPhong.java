package app;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;

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
		boxForMaPDP.add(lbMaPDP = new JLabel("Mã Phiếu Đặt Phòng: "));
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
		boxForTenKH.add(lbTenKH = new JLabel("Tên Khách Hàng:"));
		boxForTenKH.add(tfTenKH = new JTextField(15));
		Box boxForSDT = Box.createHorizontalBox();
		boxForSDT.add(lbSDT = new JLabel("Số Điện Thoại:"));
		boxForSDT.add(tfSDT = new JTextField(15));
		box2.add(Box.createVerticalStrut(10));
		box2.add(boxForTenKH);
		box2.add(Box.createVerticalStrut(20));
		box2.add(boxForSDT);
		box2.add(Box.createVerticalStrut(10));
		
		lbTenPhong.setPreferredSize(lbMaPDP.getPreferredSize());
		lbTenKH.setPreferredSize(lbMaPDP.getPreferredSize());
		lbSDT.setPreferredSize(lbMaPDP.getPreferredSize());
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
		model2.addRow(new Object[] {1, 1, 1, 1, 1});
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
		
		btnQuayLai.addActionListener(e -> this.dispose());
		btnThem.addActionListener(e -> xuLyThemDVPhong());
		btnXoa.addActionListener(e -> xuLyXoaDVPhong());
	}
	
	private void xuLyThemDVPhong() {
		
	}
	
	private void xuLyXoaDVPhong() {
		
	}
 	
	
}
