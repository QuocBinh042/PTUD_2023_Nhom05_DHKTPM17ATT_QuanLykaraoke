package app;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Arrays;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.border.Border;

public class Card extends JFrame{
	private JButton btnTrangChu, btnPhongHat, btnPhong, btnDichVu, btnKhachHang, btnNhanVien, btnHoaDon, btnKhuyenMai,
	btnThongKe, btnTroGiup, btnDangXuat;
	private JPanel pnl = new JPanel(new CardLayout());
	private TrangChu tc = new TrangChu();
	private DatPhong dp = new DatPhong();
	private Phong phong = new Phong();
	private DichVu dv = new DichVu();
	private NhanVien nv = new NhanVien();
	private KhachHang kh = new KhachHang();
	private HoaDon hd = new HoaDon();
	private KhuyenMai km = new KhuyenMai();
	private ThongKe tk = new ThongKe();
	private TroGiup tg = new TroGiup();
	private JPanel pnlButton = new JPanel();
	
	
	public Card() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(1400, 720);
		setLocationRelativeTo(null);
		this.setLayout(new BorderLayout());
		JPanel pane = new JPanel(new BorderLayout());
		JPanel pnlLeft = new JPanel(new BorderLayout());
		JPanel pnlLogo = new JPanel();
		ImageIcon icon = new ImageIcon("src/img/One.png");
		JLabel label = new JLabel(icon);
		pnlLogo.add(Box.createVerticalStrut(50));
		pnlLogo.add(label);		
		pnlLogo.setBackground(Color.decode("#990447"));
		pnlButton.setBackground(Color.decode("#990447"));
		pnl.add(tc, "tc");
		pnl.add(dp, "dp");
		pnl.add(dv, "dv");
		pnl.add(phong, "phong");
		pnl.add(nv, "nv");
		pnl.add(kh, "kh");
		pnl.add(hd, "hd");
		pnl.add(km, "km");
		pnl.add(tk, "tk");
		pnl.add(tg, "tg");
		pnl.add(tg, "dx");
		
        addButton(btnTrangChu = new JButton("TRANG CHỦ"));
        addButton(btnPhongHat = new JButton("PHÒNG HÁT"));
        addButton(btnPhong = new JButton("PHÒNG"));
        addButton(btnDichVu = new JButton("DỊCH VỤ"));
        addButton(btnKhachHang = new JButton("KHÁCH HÀNG"));
        addButton(btnNhanVien = new JButton("NHÂN VIÊN"));
        addButton(btnHoaDon = new JButton("HÓA ĐƠN"));
        addButton(btnKhuyenMai = new JButton("KHUYẾN MÃI"));
        addButton(btnThongKe = new JButton("THỐNG KÊ DOANH THU"));
        addButton(btnTroGiup = new JButton("TRỢ GIÚP"));
        addButton(btnDangXuat = new JButton("ĐĂNG XUẤT"));
        
		pnlButton.setPreferredSize(new Dimension(200, 720));		
		btnTrangChu.addMouseListener(new MouseListener() {			
			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				btnTrangChu.setBackground(Color.decode("#e6dbd1"));
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				btnTrangChu.setBackground(Color.decode("#B5B5B5"));
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
			}
		});
		
		btnPhongHat.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				btnPhongHat.setBackground(Color.decode("#e6dbd1"));
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				btnPhongHat.setBackground(Color.decode("#B5B5B5"));
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
			}
		});
		
		btnPhong.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				btnPhong.setBackground(Color.decode("#e6dbd1"));
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				btnPhong.setBackground(Color.decode("#B5B5B5"));
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
			}
		});
		
		btnDichVu.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				btnDichVu.setBackground(Color.decode("#e6dbd1"));
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				btnDichVu.setBackground(Color.decode("#B5B5B5"));
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
			}
		});
		
		btnHoaDon.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				btnHoaDon.setBackground(Color.decode("#e6dbd1"));
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				btnHoaDon.setBackground(Color.decode("#B5B5B5"));
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
			}
		});
			
		btnNhanVien.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				btnNhanVien.setBackground(Color.decode("#e6dbd1"));
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				btnNhanVien.setBackground(Color.decode("#B5B5B5"));
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
			}
		});
		
		btnKhachHang.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				btnKhachHang.setBackground(Color.decode("#e6dbd1"));
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				btnKhachHang.setBackground(Color.decode("#B5B5B5"));
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
			}
		});
		
		btnThongKe.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				btnThongKe.setBackground(Color.decode("#e6dbd1"));
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				btnThongKe.setBackground(Color.decode("#B5B5B5"));
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
			}
		});
		
	
		btnTroGiup.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				btnTroGiup.setBackground(Color.decode("#e6dbd1"));
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				btnTroGiup.setBackground(Color.decode("#B5B5B5"));
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
			}
		});
		
		btnKhuyenMai.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				btnKhuyenMai.setBackground(Color.decode("#e6dbd1"));
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				btnKhuyenMai.setBackground(Color.decode("#B5B5B5"));
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
			}
		});
	
		
		
		pnlLeft.add(pnlLogo, BorderLayout.NORTH);
		pnlLeft.add(pnlButton, BorderLayout.CENTER);
		pnlLeft.setBorder(BorderFactory.createLineBorder(Color.black));
		
		this.add(pnlLeft, BorderLayout.WEST);
		this.add(pnl, BorderLayout.CENTER);
		
		btnPhong.addActionListener(e -> xuLyPhong());
		btnPhongHat.addActionListener(e -> xuLyPhongHat());
		btnKhachHang.addActionListener(e -> xuLyKhachHang());
		btnNhanVien.addActionListener(e -> xuLyNhanVien());
		btnKhuyenMai.addActionListener(e -> xuLyKhuyenMai());
		btnHoaDon.addActionListener(e -> xuLyHoaDon());
		btnDichVu.addActionListener(e -> xuLyDichVu());
		btnThongKe.addActionListener(e -> xuLyThongKe());
		btnTroGiup.addActionListener(e -> xuLyTroGiup());
		btnTrangChu.addActionListener(e -> xuLyTrangChu());
		btnDangXuat.addActionListener(e ->{
			int i = JOptionPane.showConfirmDialog(null, "Bạn có chắc chắn muốn đăng xuất khỏi hệ thống không?", "Chú ý",
					JOptionPane.YES_NO_OPTION);
			if (i == JOptionPane.YES_OPTION) {
				dispose();
				new app.Login().setVisible(true);
			}
			else {
				
			}
		});
	}
	
	private void addButton(JButton button) {
		button.setAlignmentX(Component.LEFT_ALIGNMENT);
	    button.setBackground(Color.decode("#e6dbd1"));
	    button.setForeground(Color.BLACK);
	    button.setFocusPainted(false);
	    button.setBorderPainted(false);
	    button.setCursor(new Cursor(Cursor.HAND_CURSOR));
	    button.setPreferredSize(new Dimension(180, 40));
//	    pnlButton.add(Box.createVerticalStrut(20));
	    pnlButton.add(button);
    }

	private void xuLyPhong() {
		CardLayout card = (CardLayout) pnl.getLayout();
		card.show(pnl, "phong");
	}
	
	private void xuLyPhongHat() {
		CardLayout card = (CardLayout) pnl.getLayout();
		card.show(pnl, "dp");
	}
	
	private void xuLyDichVu() {
		CardLayout card = (CardLayout) pnl.getLayout();
		card.show(pnl, "dv");
	}
	
	private void xuLyNhanVien() {
		CardLayout card = (CardLayout) pnl.getLayout();
		card.show(pnl, "nv");
	}
	
	private void xuLyKhachHang() {
		CardLayout card = (CardLayout) pnl.getLayout();
		card.show(pnl, "kh");
	}

	private void xuLyHoaDon() {
		CardLayout card = (CardLayout) pnl.getLayout();
		card.show(pnl, "hd");
	}
	
	private void xuLyThongKe() {
		CardLayout card = (CardLayout) pnl.getLayout();
		card.show(pnl, "tk");
	}
	
	private void xuLyTroGiup() {
		CardLayout card = (CardLayout) pnl.getLayout();
		card.show(pnl, "tg");
	}
	
	private void xuLyKhuyenMai() {
		CardLayout card = (CardLayout) pnl.getLayout();
		card.show(pnl, "km");
	}
	
	private void xuLyTrangChu() {
		CardLayout card = (CardLayout) pnl.getLayout();
		card.show(pnl, "tc");
	}
}
