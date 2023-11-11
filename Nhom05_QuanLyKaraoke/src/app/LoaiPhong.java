package app;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.text.DecimalFormat;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.table.DefaultTableModel;

import connectDB.ConnectDB;
import dao.daoLoaiPhong;

public class LoaiPhong extends JFrame implements MouseListener {
	private JTextField txtSucChua, txtGiaLP;
	private JLabel lblTenLP, lblsucChua, lblGiaLP, lblLocTenLP, lblLocSC;
	private JButton btnThem, btnCapNhat, btnLamMoi, btnQuayLai;
	private JComboBox cbTenLP, cbLocSC, cbLocTP;
	private JTable table;
	private DefaultTableModel tableModel;
	private ArrayList<entity.LoaiPhong> dsLP;
	private daoLoaiPhong daoLP;
	private DecimalFormat formatter = new DecimalFormat("###,###,###");

	public LoaiPhong() {
		
		try {
			ConnectDB.getInstance().connect();;
		} catch (Exception e) {
			e.printStackTrace();
		}
		daoLP = new daoLoaiPhong();
		
		setSize(780, 350);
		setTitle("Quản lý loại phòng");
		setLocationRelativeTo(null);
		setResizable(false);

		Icon img_add = new ImageIcon("src/img/add16.png");
		Icon img_back = new ImageIcon("src/img/back16.png");
		Icon img_reset = new ImageIcon("src/img/refresh16.png");
		Icon img_edit = new ImageIcon("src/img/edit16.png");

		Box b1, b2, b3, b4;
		Box bLeft, bRight;
		Border line = BorderFactory.createLineBorder(Color.BLACK);
		Dimension dimension = new Dimension(120, 25);

		JPanel pnlLeft = new JPanel();
		JPanel pnlRight = new JPanel();

		bLeft = Box.createVerticalBox();
		bRight = Box.createVerticalBox();
		pnlLeft.add(bLeft);
		pnlRight.add(bRight);

		bLeft.setBorder(BorderFactory.createTitledBorder(line, "Thông tin loại phòng"));
		bLeft.add(Box.createVerticalStrut(5));

		JPanel pnlTenLP = new JPanel();
		String cbLP[] = { "Tất cả", "Thường", "VIP" };
		pnlTenLP.setBackground(Color.decode("#cccccc"));
		pnlTenLP.add(lblTenLP = new JLabel("Tên loại phòng"));
		pnlTenLP.add(cbTenLP = new JComboBox<>());
		cbTenLP.addItem("Thường");
		cbTenLP.addItem("VIP");
		bLeft.add(b2 = Box.createHorizontalBox());
		b2.add(pnlTenLP);
		bLeft.add(Box.createVerticalStrut(10));

		JPanel pnlSucChua = new JPanel();
		pnlSucChua.setBackground(Color.decode("#cccccc"));
		pnlSucChua.add(lblsucChua = new JLabel("Sức chứa"));
		pnlSucChua.add(txtSucChua = new JTextField(15));
		bLeft.add(b3 = Box.createHorizontalBox());
		b3.add(pnlSucChua);
		bLeft.add(Box.createVerticalStrut(10));

		JPanel pnlGiaLP = new JPanel();
		pnlGiaLP.setBackground(Color.decode("#cccccc"));
		pnlGiaLP.add(lblGiaLP = new JLabel("Giá loại phòng"));
		pnlGiaLP.add(txtGiaLP = new JTextField(15));
		bLeft.add(b4 = Box.createHorizontalBox());
		b4.add(pnlGiaLP);
		bLeft.add(Box.createVerticalStrut(10));

		lblsucChua.setPreferredSize(lblTenLP.getPreferredSize());
		lblGiaLP.setPreferredSize(lblTenLP.getPreferredSize());
		txtSucChua.setPreferredSize(dimension);
		txtGiaLP.setPreferredSize(dimension);
		cbTenLP.setPreferredSize(new Dimension(155, 25));

		// button
		bLeft.add(Box.createVerticalStrut(10));
		JPanel pnlKM = new JPanel();
		pnlKM.setBackground(Color.decode("#cccccc"));
		GridLayout gridKM = new GridLayout(2, 2);
		pnlKM.setLayout(gridKM);
		gridKM.setVgap(20);
		gridKM.setHgap(20);
		pnlKM.add(btnThem = new ButtonGradient("Thêm mới", img_add));
		pnlKM.add(btnCapNhat = new ButtonGradient("Cập nhật", img_edit));
		pnlKM.add(btnLamMoi = new ButtonGradient("Làm mới", img_reset));
		pnlKM.add(btnQuayLai = new ButtonGradient("Quay lại", img_back));
		bLeft.add(pnlKM);
		bLeft.add(Box.createVerticalStrut(35));

		// Right box
		JPanel pnlTacVu = new JPanel();
		pnlTacVu.setBackground(Color.decode("#e6dbd1"));
		GridLayout gridTV = new GridLayout(1, 2);
		pnlTacVu.setLayout(gridTV);
		// Panel Loc
		JPanel pnlLoc = new JPanel();
		String cbSC[] = { "Tất cả", "10", "15", "20" };
		pnlLoc.setBackground(Color.decode("#e6dbd1"));
		pnlLoc.add(lblLocTenLP = new JLabel("Tên loại phòng"));
		pnlLoc.add(Box.createHorizontalStrut(20));
		pnlLoc.add(cbLocTP = new JComboBox<>(cbLP));
		cbLocTP.setPreferredSize(new Dimension(100, 25));
		pnlLoc.add(Box.createHorizontalStrut(40));
		pnlLoc.add(lblLocSC = new JLabel("Sức chứa"));
		pnlLoc.add(Box.createHorizontalStrut(20));
		pnlLoc.add(cbLocSC = new JComboBox<>(cbSC));
		cbLocSC.setPreferredSize(new Dimension(100, 25));
		pnlTacVu.add(pnlLoc);

		// Table
		Box table1 = Box.createVerticalBox();
		table1.setBorder(BorderFactory.createTitledBorder(line, "Danh sách loại phòng"));
		String[] headers = "Mã loại phòng;Tên loại phòng;Sức chứa;Giá loại phòng".split(";");
		tableModel = new DefaultTableModel(headers, 0);
		JScrollPane scroll = new JScrollPane(table, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scroll.setViewportView(table = new JTable(tableModel));
		table.setRowHeight(25);
		table.setAutoCreateRowSorter(true);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		table1.add(scroll);

		// set color
		pnlTacVu.setBackground(Color.decode("#e6dbd1"));
		pnlLeft.setBackground(Color.decode("#cccccc"));
		pnlRight.setBackground(Color.decode("#e6dbd1"));
		pnlRight.setLayout(new BorderLayout());
		pnlRight.add(pnlTacVu, BorderLayout.NORTH);
		pnlRight.add(table1, BorderLayout.CENTER);

		this.setLayout(new BorderLayout());
		this.add(pnlLeft, BorderLayout.WEST);
		this.add(pnlRight, BorderLayout.CENTER);

		// add event
		layToanBoLP();
		cbLocTP.addActionListener(e -> xuLyLocCBTP());
		cbLocSC.addActionListener(e -> xuLyLocCBSC());
		btnLamMoi.addActionListener(e -> xuLyLamMoi());
		btnThem.addActionListener(e -> xuLyThemMoi());
		btnQuayLai.addActionListener(e -> xuLyQuayLai());
		btnCapNhat.addActionListener(e -> xuLyCapNhat());
		table.addMouseListener(this);

	}
	// Lay toan bo loai phong
		private void layToanBoLP() {
			dsLP = daoLP.getAllLoaiPhong();
			for (entity.LoaiPhong lp : dsLP) {
				tableModel.addRow(new Object[] { lp.getMaLoaiPhong(), lp.getTenLoaiPhong(), lp.getSucChua(),
						formatter.format(lp.getGiaLoaiPhong()) + " VNĐ" });
			}
		}

		// Xu ly them moi
		private void xuLyThemMoi() {
			String tenLP = cbTenLP.getSelectedItem().toString();
			String giaLP = txtGiaLP.getText();
			String sucChua = txtSucChua.getText();
			int soLP = 0;
			String maLP = "LP00" + (soLP + "");
			String[] row = { maLP, tenLP, giaLP, sucChua };
			tableModel.addRow(row);
		}

		// Xu ly cap nhat
		private void xuLyCapNhat() {
			int r = table.getSelectedRow();
			String tenLP = cbTenLP.getSelectedItem().toString();
			String giaLP = txtGiaLP.getText();
			String sucChua = txtSucChua.getText();
			table.setValueAt(tenLP, r, 1);
			table.setValueAt(sucChua, r, 2);
			table.setValueAt(giaLP, r, 3);

		}

		// Xu ly lam moi
		private void xuLyLamMoi() {
			cbTenLP.setSelectedIndex(0);
			txtGiaLP.setText("");
			txtSucChua.setText("");
			cbLocTP.setSelectedIndex(0);
			cbLocSC.setSelectedIndex(0);
		}

		// Xu ly quay lai
		private void xuLyQuayLai() {
			this.setVisible(false);
		}

		// Xu ly kiem tra thong tin
		private int kiemTraThongTin() {
			String sucChua = txtSucChua.getText();
			String giaLP = txtGiaLP.getText();
			if (sucChua.equals("") || giaLP.equals("")) {
				return -1;
			}
			return 1;
		}

		// Xu ly loc ten loai phong xuLyLocCBSC
		private void xuLyLocCBTP() {
			String lc = cbLocTP.getSelectedItem().toString();
			if (lc.equalsIgnoreCase("Tất cả")) {
				xoaToanBoLP();
				layToanBoLP();
			} else if (lc.equalsIgnoreCase("Thường")) {
				xoaToanBoLP();
				layToanBoLP();
				for (int i = 0; i < table.getRowCount(); i++) {
					String tenLP = table.getValueAt(i, 1).toString();
					if (tenLP.equalsIgnoreCase("VIP")) {
						tableModel.removeRow(i);
					}
				}
			} else if (lc.equalsIgnoreCase("VIP")) {
				xoaToanBoLP();
				layToanBoLP();
				for (int i = 0; i < table.getRowCount(); i++) {
					String tenLP = table.getValueAt(i, 1).toString();
					if (tenLP.equalsIgnoreCase("Thường")) {
						tableModel.removeRow(i);
					}
				}

			}
		}

		// Xu ly loc theo suc chua
		private void xuLyLocCBSC() {
			xoaToanBoLP();
			String lc = cbLocSC.getSelectedItem().toString();
			dsLP = daoLP.getCBSC(lc);
			for(entity.LoaiPhong lp : dsLP) {
				tableModel.addRow(new Object[] {lp.getMaLoaiPhong(), lp.getTenLoaiPhong(), lp.getSucChua(), formatter.format(lp.getGiaLoaiPhong())+" VNĐ"});
			}
		}

		// Xoa toan bo loai phong
		private void xoaToanBoLP() {
			DefaultTableModel dm = (DefaultTableModel) table.getModel();
			while (dm.getRowCount() > 0) {
				dm.removeRow(0);
			}

		}

	// Xu ly mouseclick
	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent e) {
		int row = table.getSelectedRow();
		cbTenLP.setSelectedItem(table.getValueAt(row, 1).toString());
		txtSucChua.setText(table.getValueAt(row, 2).toString());
		txtGiaLP.setText(table.getValueAt(row, 3).toString());

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}
}