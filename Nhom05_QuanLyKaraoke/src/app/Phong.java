package app;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Phong extends JPanel {

	private JLabel lblMaPhong, lblTenPhong, lblLoaiPhong, lblSucChua, lblGiaPhong, lblMoTa, lblTimMaPhong, lblTinhTrang,
			lblMaLoaiPhong;
	private JTextField txtMaPhong, txtTenPhong, txtSucChua, txtGiaPhong, txtTimMaPhong, txtThongBaoLoi, txtLoaiPhong;
	private JTextArea txtMoTa;
	private JButton btnThemMoi, btnCapNhat, btnXoa, btnLamMoi, btnQuanLyChiTiet, btnThoat, btnLoc;
	private JComboBox cbLoaiPhong, cbTinhTrang, cbMaLoaiPhong;
	private JTable table;
	private DefaultTableModel tableModel;

	public Phong() {
		Icon img_add = new ImageIcon("src/img/add2.png");
		Icon img_del = new ImageIcon("src/img/del.png");
		Icon img_reset = new ImageIcon("src/img/refresh.png");
		Icon img_edit = new ImageIcon("src/img/edit.png");
		Icon img_out = new ImageIcon("src/img/out.png");
		Icon img_search = new ImageIcon("src/img/search2.png");
		Icon img_transfer = new ImageIcon("src/img/transfer.png");
		
		JPanel pnlLeft = new JPanel(new BorderLayout());
		JPanel pnlRight = new JPanel(new BorderLayout());

		// WEST
		Box bLeft = Box.createVerticalBox(), bRight = Box.createVerticalBox();
		Box b1, b2, b3, b4, b5, b6, b7, b8, b9, b10, b11, b12, b13, b14, b15, b16, b17;

		Border line = BorderFactory.createLineBorder(Color.BLACK);
		bLeft.setBorder(BorderFactory.createTitledBorder(line, "Thông tin phòng", TitledBorder.LEFT, TitledBorder.TOP, new Font("Arial", Font.PLAIN, 13), Color.BLACK));
		bLeft.add(b1 = Box.createHorizontalBox());
		bLeft.add(Box.createVerticalStrut(15));
		b1.add(lblMaPhong = new JLabel("Mã phòng"));
		b1.add(Box.createHorizontalStrut(20));
		b1.add(txtMaPhong = new JTextField());

		bLeft.add(b2 = Box.createHorizontalBox());
		bLeft.add(Box.createVerticalStrut(15));
		b2.add(lblTenPhong = new JLabel("Tên phòng"));
		b2.add(Box.createHorizontalStrut(20));
		b2.add(txtTenPhong = new JTextField());

		bLeft.add(b3 = Box.createHorizontalBox());
		bLeft.add(Box.createVerticalStrut(15));
		b3.add(lblMaLoaiPhong = new JLabel("Mã loại phòng"));
		b3.add(Box.createHorizontalStrut(20));
		b3.add(cbMaLoaiPhong = new JComboBox<>());
		cbMaLoaiPhong.addItem("LP001"); // add vao combobox
		cbMaLoaiPhong.addItem("LP002");
		cbMaLoaiPhong.addItem("LP003");

		bLeft.add(b4 = Box.createHorizontalBox());
		bLeft.add(Box.createVerticalStrut(15));
		b4.add(lblLoaiPhong = new JLabel("Loại phòng"));
		b4.add(Box.createHorizontalStrut(20));
		b4.add(txtLoaiPhong = new JTextField());
		txtLoaiPhong.setEditable(false);

		bLeft.add(b5 = Box.createHorizontalBox());
		bLeft.add(Box.createVerticalStrut(15));
		b5.add(lblSucChua = new JLabel("Sức chứa"));
		b5.add(Box.createHorizontalStrut(20));
		b5.add(txtSucChua = new JTextField());
		txtSucChua.setEditable(false);

		bLeft.add(b15 = Box.createHorizontalBox());
		bLeft.add(Box.createVerticalStrut(15));
		b15.add(lblGiaPhong = new JLabel("Giá phòng"));
		b15.add(Box.createHorizontalStrut(20));
		b15.add(txtGiaPhong = new JTextField());
		txtGiaPhong.setEditable(false);

		bLeft.add(b16 = Box.createHorizontalBox());
		bLeft.add(Box.createVerticalStrut(15));
		b16.add(lblMoTa = new JLabel("Mô tả"));
		b16.add(Box.createHorizontalStrut(20));
		b16.add(txtMoTa = new JTextArea(5, 15));
		txtMoTa.setBorder(BorderFactory.createLineBorder(Color.GRAY));
		JScrollPane scroll1 = new JScrollPane(txtMoTa);
		scroll1.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
		scroll1.setBorder(null);
		b16.add(scroll1);

		bLeft.add(b7 = Box.createHorizontalBox());
		bLeft.add(Box.createVerticalStrut(15));
		b7.add(txtThongBaoLoi = new JTextField());
		txtThongBaoLoi.setEditable(false);
		txtThongBaoLoi.setBorder(null);
		txtThongBaoLoi.setBackground(Color.decode("#cccccc"));
		txtThongBaoLoi.setForeground(Color.red);
		txtThongBaoLoi.setFont(new Font("Times new roman", ABORT, 12));

		lblMaPhong.setPreferredSize(lblMaLoaiPhong.getPreferredSize());
		lblTenPhong.setPreferredSize(lblMaLoaiPhong.getPreferredSize());
		lblMoTa.setPreferredSize(lblMaLoaiPhong.getPreferredSize());
		lblGiaPhong.setPreferredSize(lblMaLoaiPhong.getPreferredSize());
		lblLoaiPhong.setPreferredSize(lblMaLoaiPhong.getPreferredSize());
		lblSucChua.setPreferredSize(lblMaLoaiPhong.getPreferredSize());

		bLeft.add(b8 = Box.createHorizontalBox());
		b8.add(Box.createHorizontalStrut(5));
		b8.add(btnThemMoi = new JButton("Thêm mới", img_add));
		btnThemMoi.setBorder(new RoundedBorder(7));
		btnThemMoi.setBackground(Color.decode("#6fa8dc"));
		btnThemMoi.setMaximumSize(new Dimension(Integer.MAX_VALUE, btnThemMoi.getMinimumSize().height));
		bLeft.add(Box.createVerticalStrut(5));
		
		b8.add(Box.createHorizontalStrut(20));
		b8.add(btnCapNhat = new JButton("Cập nhật", img_edit));
		btnCapNhat.setBorder(new RoundedBorder(7));
		btnCapNhat.setBackground(Color.decode("#6fa8dc"));
		btnCapNhat.setMaximumSize(new Dimension(Integer.MAX_VALUE, btnCapNhat.getMinimumSize().height));
		bLeft.add(Box.createVerticalStrut(5));

		bLeft.add(b9 = Box.createHorizontalBox());
		b9.add(Box.createVerticalStrut(70));
		b9.add(Box.createHorizontalStrut(5));
		b9.add(btnXoa = new JButton("      Xóa      ", img_del));
		btnXoa.setBorder(new RoundedBorder(7));
		btnXoa.setBackground(Color.decode("#6fa8dc"));
		btnXoa.setMaximumSize(new Dimension(Integer.MAX_VALUE, btnXoa.getMinimumSize().height));
		bLeft.add(Box.createVerticalStrut(5));
		
		b9.add(Box.createHorizontalStrut(25));
		b9.add(btnLamMoi = new JButton("Làm mới", img_reset));
		btnLamMoi.setBorder(new RoundedBorder(7));
		btnLamMoi.setBackground(Color.decode("#6fa8dc"));
		btnLamMoi.setMaximumSize(new Dimension(Integer.MAX_VALUE, btnLamMoi.getMinimumSize().height));
		bLeft.add(Box.createVerticalStrut(5));

		bLeft.add(b10 = Box.createHorizontalBox());
		b10.add(Box.createVerticalStrut(20));
		b10.add(Box.createHorizontalStrut(7));
		b10.add(btnQuanLyChiTiet = new JButton("Quản lý loại phòng", img_transfer));
		btnQuanLyChiTiet.setBorder(new RoundedBorder(5));
		btnQuanLyChiTiet.setBackground(Color.decode("#6fa8dc"));
		btnQuanLyChiTiet.setMaximumSize(new Dimension(Integer.MAX_VALUE, btnQuanLyChiTiet.getMinimumSize().height));
		bLeft.add(Box.createVerticalStrut(5));
		bLeft.add(Box.createVerticalStrut(10));

		bLeft.add(b11 = Box.createHorizontalBox());
		b11.add(Box.createVerticalStrut(50));
		b11.add(Box.createHorizontalStrut(7));
		b11.add(btnThoat = new JButton("Thoát", img_out));
		btnThoat.setBorder(new RoundedBorder(5));
		btnThoat.setBackground(Color.decode("#6fa8dc"));
		btnThoat.setMaximumSize(new Dimension(Integer.MAX_VALUE, btnThoat.getMinimumSize().height));
		bLeft.add(Box.createVerticalStrut(300));

		Box aNorth = Box.createHorizontalBox(), aCenter = Box.createVerticalBox();
		Box a, a1, a2;

		aNorth.add(a = Box.createHorizontalBox());
		aNorth.setMaximumSize(new Dimension(Integer.MAX_VALUE, aNorth.getMinimumSize().height));
		aNorth.add(Box.createHorizontalStrut(400));

		aNorth.add(a1 = Box.createVerticalBox());
		
		a1.setBorder(BorderFactory.createTitledBorder(line, "Lọc", TitledBorder.LEFT, TitledBorder.TOP, new Font("Arial", Font.PLAIN, 13), Color.BLACK));
		aNorth.add(Box.createVerticalStrut(20));
		a1.add(b12 = Box.createHorizontalBox());
		
		b12.add(Box.createVerticalStrut(20));
		b12.add(lblTinhTrang = new JLabel("Tình trạng"));
		b12.add(Box.createHorizontalStrut(50));
		b12.add(cbTinhTrang = new JComboBox<>());
		a1.add(Box.createVerticalStrut(20));
		
		a1.add(b13 = Box.createHorizontalBox());
		b13.add(Box.createVerticalStrut(10));
		b13.add(lblLoaiPhong = new JLabel("Loại phòng"));
		b13.add(Box.createHorizontalStrut(40));
		b13.add(cbLoaiPhong = new JComboBox<>());
		a1.add(Box.createVerticalStrut(20));
		a1.setMaximumSize(new Dimension(Integer.MAX_VALUE, a1.getMinimumSize().height));
		aNorth.add(Box.createHorizontalStrut(350));
		
		aNorth.add(a2 = Box.createVerticalBox());
		a2.add(b17 = Box.createHorizontalBox());
		
		b17.add(lblTimMaPhong = new JLabel("Mã phòng"));
		b17.add(Box.createVerticalStrut(30));
		
		a2.add(b14 = Box.createHorizontalBox());
		
		b14.add(txtTimMaPhong = new JTextField());
		b14.add(Box.createHorizontalStrut(30));
		b14.add(btnLoc = new JButton("  Tìm  ", img_search));
		btnLoc.setBorder(new RoundedBorder(3));
		btnLoc.setBackground(Color.decode("#6fa8dc"));
		a2.setMaximumSize(new Dimension(Integer.MAX_VALUE, a2.getMinimumSize().height));
		aNorth.add(Box.createHorizontalStrut(100));

		// add box a vao bang
		bRight.add(aNorth);

		aCenter.setBorder(BorderFactory.createTitledBorder(line, "Danh sách phòng", TitledBorder.LEFT, TitledBorder.TOP, new Font("Arial", Font.PLAIN, 13), Color.BLACK));
		String[] headers = "Mã phòng;Tên phòng;Mã loại phòng;Mã giá phòng;Loại phòng;Sức chứa;Giá phòng;Tình trạng;Mô tả"
				.split(";");
		tableModel = new DefaultTableModel(headers, 0);
		JScrollPane scroll = new JScrollPane(table, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scroll.setViewportView(table = new JTable(tableModel));
		table.setRowHeight(25);
		table.setAutoCreateRowSorter(true);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		aCenter.add(scroll);

		// add box aa vao bang
		bRight.add(aCenter);

		//
		pnlLeft.setBackground(Color.decode("#cccccc"));
		pnlRight.setBackground(Color.decode("#e6dbd1"));
		pnlLeft.add(bLeft, BorderLayout.WEST);
		pnlRight.add(bRight, BorderLayout.CENTER);

		this.setLayout(new BorderLayout());
		add(pnlLeft, BorderLayout.WEST);
		add(pnlRight, BorderLayout.CENTER);

	}
	private static class RoundedBorder implements Border {

	    private int radius;


	    RoundedBorder(int radius) {
	        this.radius = radius;
	    }


	    public Insets getBorderInsets(Component c) {
	        return new Insets(this.radius+1, this.radius+1, this.radius+2, this.radius);
	    }


	    public boolean isBorderOpaque() {
	        return true;
	    }


	    public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
	        g.drawRoundRect(x, y, width-1, height-1, radius, radius);
	    }
	}

}
