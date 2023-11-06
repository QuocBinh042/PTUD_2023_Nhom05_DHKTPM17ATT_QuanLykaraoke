package app;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.MouseListener;
import java.util.Date;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.table.DefaultTableModel;

import com.toedter.calendar.JDateChooser;

public class HoaDon extends JPanel {
	private JTable table;
	private DefaultTableModel tableModel;
	private String[] headers = { "Mã hoá đơn", "Ngày thanh toán", "Giờ thanh toán", "Tên nhân viên",
			"Tên khách hàng", "Số điện thoại khách", "Tổng hoá đơn" };
	private JLabel lblTenNV, lblSđtKH, lblNgayBatDau, lblNgayKetThuc;
	private JTextField txtTimNV, txtTimKH;
	private JButton btnTim, btnLamMoi, btnThoat, btnXemCT;
	private JDateChooser dateBD, dateKT;
	private MouseListener mouseListener;

	public HoaDon() {
		// Khai báo
		Icon img_out = new ImageIcon("src/img/out16.png");
		Icon img_search = new ImageIcon("src/img/search.png");
		Icon img_refresh = new ImageIcon("src/img/refresh16.png");
		Icon img_detail = new ImageIcon("src/img/detail16.png");
		Box b, bTim, bTim1, bTim2;
		Border line = BorderFactory.createLineBorder(Color.BLACK);
		Dimension dimension = new Dimension(110, 25);

		// NORT
		b = Box.createHorizontalBox();
		b.add(bTim = Box.createVerticalBox());
		bTim.add(bTim1 = Box.createHorizontalBox());
		bTim.setBorder(BorderFactory.createTitledBorder(line, "Tra cứu"));
		bTim1.add(Box.createHorizontalStrut(10));
		bTim1.add(lblNgayBatDau = new JLabel("Ngày bắt đầu"));
		bTim1.add(Box.createHorizontalStrut(15));
		bTim1.add(dateBD = new JDateChooser());
		bTim1.add(Box.createHorizontalStrut(50));
		bTim1.add(lblTenNV = new JLabel("Tên nhân viên"));
		bTim1.add(Box.createHorizontalStrut(15));
		bTim1.add(txtTimNV = new JTextField(10));
		bTim1.add(Box.createHorizontalStrut(50));
		bTim1.add(btnTim = new ButtonGradient("Tìm", img_search));
		bTim1.add(Box.createHorizontalStrut(5));

		bTim.add(Box.createVerticalStrut(5));
		bTim.add(bTim2 = Box.createHorizontalBox());
		bTim2.add(Box.createHorizontalStrut(10));
		bTim2.add(lblNgayKetThuc = new JLabel("Ngày kết thúc"));
		bTim2.add(Box.createHorizontalStrut(15));
		bTim2.add(dateKT = new JDateChooser());
		bTim2.add(Box.createHorizontalStrut(50));
		bTim2.add(lblSđtKH = new JLabel("Số điện thoại khách"));
		bTim2.add(Box.createHorizontalStrut(15));
		bTim2.add(txtTimKH = new JTextField(10));
		bTim2.add(Box.createHorizontalStrut(50));
		bTim2.add(btnLamMoi = new ButtonGradient("Làm mới", img_refresh));
		bTim2.add(Box.createHorizontalStrut(5));

		b.add(Box.createHorizontalStrut(50));
		b.add(btnXemCT = new ButtonGradient("Xem chi tiết", img_detail));
		b.add(Box.createHorizontalStrut(50));
		b.add(btnThoat = new ButtonGradient("Thoát", img_out));
		b.add(Box.createHorizontalStrut(50));

		btnTim.setPreferredSize(btnLamMoi.getMaximumSize());
		lblNgayBatDau.setPreferredSize(lblNgayKetThuc.getPreferredSize());
		lblTenNV.setPreferredSize(lblSđtKH.getPreferredSize());

		// CENTER
		tableModel = new DefaultTableModel(headers, 0);
		JScrollPane scroll = new JScrollPane(table, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scroll.setBorder(BorderFactory.createTitledBorder(line, "Danh sách phòng"));
		scroll.setViewportView(table = new JTable(tableModel));
		table.setRowHeight(25);
		table.setAutoCreateRowSorter(true);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);

		// Add giao diện
		this.setLayout(new BorderLayout());
		this.add(b, BorderLayout.NORTH);
//		this.setBackground(Color.decode("#cccccc"));
		this.add(scroll, BorderLayout.CENTER);

		// Load data
		loadData();

		// Sự kiện
		String[] row = "HDOO1; 2022/11/21; 13:34:26; Trần Lê Quốc Bình; Quốc Quốc; 0394109819; 150000; 100000; 250000"
				.split(";");
		tableModel.addRow(row);
		btnTim.addActionListener(e -> xuLyTimKiem());
		btnLamMoi.addActionListener(e -> xuLyLamMoi());
		btnThoat.addActionListener(e -> System.exit(0));
		btnXemCT.addActionListener(e -> xuLyXemCT());

	}

	public void loadData() {
		// delete all
		deleteAllDataJtable();
		// Load data

	}

	private void deleteAllDataJtable() {
		DefaultTableModel dm = (DefaultTableModel) table.getModel();
		while (dm.getRowCount() > 0) {
			dm.removeRow(0);
		}

	}

	private Object xuLyTimKiem() {
		// TODO Auto-generated method stub
		int pos = 0;

		if (pos != -1) {
			JOptionPane.showMessageDialog(null, "Tìm kiếm thông tin hóa đơn thành công!");
			table.setRowSelectionInterval(pos, pos);
		} else
			JOptionPane.showMessageDialog(null, "Hóa đơn không tồn tại!");

		return null;
	}

	private Object xuLyXemCT() {
		// TODO Auto-generated method stub

		return null;
	}

	private Object xuLyLamMoi() {
		// TODO Auto-generated method stub
		txtTimKH.setText("");
		txtTimNV.setText("");
		((JTextField) dateBD.getDateEditor().getUiComponent()).setText("");
		((JTextField) dateKT.getDateEditor().getUiComponent()).setText("");
		return null;
	}
}