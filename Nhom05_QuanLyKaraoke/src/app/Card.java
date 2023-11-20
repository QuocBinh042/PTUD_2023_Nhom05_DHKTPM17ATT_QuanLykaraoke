package app;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
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
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.border.Border;

public class Card extends JFrame{
	private JButton btnTrangChu, btnPhongHat, btnPhong, btnDichVu, btnKhachHang, btnNhanVien, btnHoaDon, btnKhuyenMai,
	btnThongKe, btnTroGiup;
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
	public Card() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//		setExtendedState(JFrame.MAXIMIZED_BOTH);
		setSize(1400, 720);
		setLocationRelativeTo(null);
		this.setLayout(new BorderLayout());
		JPanel pane = new JPanel(new BorderLayout());
		JPanel pnlLeft = new JPanel(new BorderLayout());
		JPanel pnlLogo = new JPanel();
		ImageIcon icon = new ImageIcon("src/img/logo2.jpg");
		JLabel label = new JLabel(icon);
		pnlLogo.add(Box.createVerticalStrut(50));
		pnlLogo.add(label);
		
		JPanel pnlButton = new JPanel();
		
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
		
//		ImageIcon icon = new ImageIcon("src/img/logo2.jpg");
//		JLabel label = new JLabel(icon);
//		pnlLogo.add(label);
//		
//		pnlLeft.add(pnlLogo, BorderLayout.NORTH);
		//
		Box boxForButton = Box.createVerticalBox();
		boxForButton.add(Box.createVerticalStrut(100));
		boxForButton.add(btnTrangChu = new JButton("TRANG CHỦ"));
		btnTrangChu.setPreferredSize(new Dimension(200, 20));
		boxForButton.add(Box.createVerticalStrut(20));
		boxForButton.add(btnPhongHat = new JButton("PHÒNG HÁT"));
		boxForButton.add(Box.createVerticalStrut(20));
		boxForButton.add(btnPhong = new JButton("PHÒNG"));
		boxForButton.add(Box.createVerticalStrut(20));
		boxForButton.add(btnDichVu = new JButton("DỊCH VỤ"));
		boxForButton.add(Box.createVerticalStrut(20));
		boxForButton.add(btnKhachHang = new JButton("KHÁCH HÀNG"));
		boxForButton.add(Box.createVerticalStrut(20));
		boxForButton.add(btnNhanVien = new JButton("NHÂN VIÊN"));
		boxForButton.add(Box.createVerticalStrut(20));
		boxForButton.add(btnHoaDon = new JButton("HÓA ĐƠN"));
		boxForButton.add(Box.createVerticalStrut(20));
		boxForButton.add(btnKhuyenMai = new JButton("KHUYẾN MÃI"));
		boxForButton.add(Box.createVerticalStrut(20));
		boxForButton.add(btnThongKe = new JButton("THỐNG KÊ"));
		boxForButton.add(Box.createVerticalStrut(20));
		boxForButton.add(btnTroGiup = new JButton("TRỢ GIÚP"));
		boxForButton.add(Box.createVerticalStrut(20));
		pnlButton.add(boxForButton);
		pnlButton.setPreferredSize(new Dimension(200, 720));
		
		List<JButton> dsBtn = Arrays.asList(btnTrangChu, btnPhongHat, btnPhong, btnDichVu, btnKhachHang, btnNhanVien, btnHoaDon, btnKhuyenMai,
				btnThongKe, btnTroGiup);
		
		for(int i = 0; i < dsBtn.size(); i++) {
			dsBtn.get(i).setMaximumSize(new Dimension(Integer.MAX_VALUE, Integer.MAX_VALUE));
			dsBtn.get(i).setFont(new Font("Sanserif", Font.BOLD, 14));
			dsBtn.get(i).setForeground(Color.black);
			dsBtn.get(i).setBackground(Color.decode("#e6dbd1"));
			dsBtn.get(i).setBorder(BorderFactory.createLineBorder(Color.decode("#e6dbd1")));
		}
		
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
	
//		btnTrangChu.setMaximumSize(new Dimension(Integer.MAX_VALUE, btnTrangChu.getMinimumSize().height));
//		btnTrangChu.setFont(new Font("Sanserif", Font.BOLD, 25));
//		btnTrangChu.setForeground(Color.decode("#c0baa2"));
//		btnPhongHat.setMaximumSize(new Dimension(Integer.MAX_VALUE, btnPhongHat.getMinimumSize().height));
//		btnPhongHat.setFont(new Font("Sanserif", Font.BOLD, 25));
//		btnPhongHat.setForeground(Color.decode("#c0baa2"));
//		btnPhong.setMaximumSize(new Dimension(Integer.MAX_VALUE,  btnPhong.getMinimumSize().height));
//		btnPhong.setFont(new Font("Sanserif", Font.BOLD, 25));
//		btnPhong.setForeground(Color.decode("#c0baa2"));
//		btnDichVu.setMaximumSize(new Dimension(Integer.MAX_VALUE, btnDichVu.getMinimumSize().height));
//		btnDichVu.setFont(new Font("Sanserif", Font.BOLD, 25));
//		btnDichVu.setForeground(Color.decode("#c0baa2"));
//		btnKhachHang.setMaximumSize(new Dimension(Integer.MAX_VALUE, btnKhachHang.getMinimumSize().height));
//		btnKhachHang.setFont(new Font("Sanserif", Font.BOLD, 25));
//		btnKhachHang.setForeground(Color.decode("#c0baa2"));
//		btnHoaDon.setMaximumSize(new Dimension(Integer.MAX_VALUE, btnHoaDon.getMinimumSize().height));
//		btnHoaDon.setFont(new Font("Sanserif", Font.BOLD, 25));
//		btnHoaDon.setForeground(Color.decode("#c0baa2"));
//		btnNhanVien.setMaximumSize(new Dimension(Integer.MAX_VALUE, btnNhanVien.getMinimumSize().height));
//		btnNhanVien.setFont(new Font("Sanserif", Font.BOLD, 25));
//		btnNhanVien.setForeground(Color.decode("#c0baa2"));
//		btnKhuyenMai.setMaximumSize(new Dimension(Integer.MAX_VALUE, btnKhuyenMai.getMinimumSize().height));
//		btnKhuyenMai.setFont(new Font("Sanserif", Font.BOLD, 25));
//		btnKhuyenMai.setForeground(Color.decode("#c0baa2"));
//		btnThongKe.setMaximumSize(new Dimension(Integer.MAX_VALUE, btnThongKe.getMinimumSize().height));
//		btnThongKe.setFont(new Font("Sanserif", Font.BOLD, 25));
//		btnThongKe.setForeground(Color.decode("#c0baa2"));
//		btnTroGiup.setMaximumSize(new Dimension(Integer.MAX_VALUE, btnTroGiup.getMinimumSize().height));
//		btnTroGiup.setFont(new Font("Sanserif", Font.BOLD, 25));
//		btnTroGiup.setForeground(Color.decode("#c0baa2"));
//		
		pnlButton.setBackground(Color.decode("#e6dbd1"));
		
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
