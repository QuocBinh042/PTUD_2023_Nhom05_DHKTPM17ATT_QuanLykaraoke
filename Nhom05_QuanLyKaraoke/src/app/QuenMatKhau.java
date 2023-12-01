package app;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;

import dao.DAONhanVien;

public class QuenMatKhau extends JFrame {
	private static String ACCOUNT_SID = "AC4d68f5344f866140494be07649cb767c"; // Thay bằng ACCOUNT_SID
	private static String AUTH_TOKEN = "5c6f9e637b1129231ef148b3cd7e3ec6"; // Thay bằng AUTH_TOKEN
	private static String TWILIO_PHONE_NUMBER = "+19185057094"; // Thay bằng số điện thoại Twilio
	private JLabel lblSoDienThoai, lblMatKhau, lblMaXacNhan;
	private JTextField txtSoDienThoai, txtMaXacNhan;
	private JPasswordField txtMatKhau;
	private JButton btnGuiMa, btnXacNhan, btnQuayLai, btnShowPass, btnHidePass;
	private static String generatedCode;
	private DAONhanVien daoNV;

	public QuenMatKhau() {
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
		setSize(320, 360);
		setTitle("Quên mật khẩu");
		setLocationRelativeTo(null);
		setResizable(false);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		daoNV = new DAONhanVien();

		JPanel pnlBorder = new JPanel();
		pnlBorder.setBackground(Color.decode("#e6dbd1"));
		pnlBorder.setLayout(null);
		add(pnlBorder, BorderLayout.CENTER);

		lblSoDienThoai = new JLabel("Số điện thoại");
		lblSoDienThoai.setFont(new Font("Times new roman", ALLBITS, 16));
		lblSoDienThoai.setBounds(20, 20, 150, 30);
		pnlBorder.add(lblSoDienThoai);

		txtSoDienThoai = new JTextField(20);
		txtSoDienThoai.setBounds(20, 50, 250, 35);
		pnlBorder.add(txtSoDienThoai);

		btnGuiMa = new ButtonGradient("Gửi mã");
		btnGuiMa.setFont(new Font("Times new roman", ALLBITS, 14));
		btnGuiMa.setBounds(190, 87, 87, 32);
		btnGuiMa.setForeground(Color.WHITE);
		pnlBorder.add(btnGuiMa);

		lblMaXacNhan = new JLabel("Mã xác nhận");
		lblMaXacNhan.setFont(new Font("Times new roman", ALLBITS, 16));
		lblMaXacNhan.setBounds(20, 90, 150, 30);
		pnlBorder.add(lblMaXacNhan);

		txtMaXacNhan = new JTextField(20);
		txtMaXacNhan.setBounds(20, 120, 250, 35);
		pnlBorder.add(txtMaXacNhan);

		lblMatKhau = new JLabel("Mật khẩu mới");
		lblMatKhau.setFont(new Font("Times new roman", ALLBITS, 16));
		lblMatKhau.setBounds(20, 160, 150, 30);
		pnlBorder.add(lblMatKhau);

		txtMatKhau = new JPasswordField(20);
		txtMatKhau.setBounds(20, 190, 250, 35);
		pnlBorder.add(txtMatKhau);

		// Show password
		ImageIcon icon_showPass = new ImageIcon("src/IMG/hide.png");
		btnShowPass = new JButton(icon_showPass);
		btnShowPass.setBorder(null);
		btnShowPass.setBackground(Color.decode("#e6dbd1"));
		btnShowPass.setBounds(272, 192, 30, 30);
		pnlBorder.add(btnShowPass);

		// Hide password
		ImageIcon icon_hidePass = new ImageIcon("src/IMG/visible.png");
		btnHidePass = new JButton(icon_hidePass);
		btnHidePass.setBorder(null);
		btnHidePass.setBackground(Color.decode("#e6dbd1"));
		btnHidePass.setBounds(272, 192, 30, 30);
		pnlBorder.add(btnHidePass);

		btnXacNhan = new ButtonGradient("Xác nhận");
		btnXacNhan.setBounds(10, 245, 125, 50);
		btnXacNhan.setFont(new Font("Times new roman", ALLBITS, 18));
		btnXacNhan.setForeground(Color.WHITE);
		pnlBorder.add(btnXacNhan);

		btnQuayLai = new ButtonGradient("Quay lại");
		btnQuayLai.setBounds(155, 245, 125, 50);
		btnQuayLai.setFont(new Font("Times new roman", ALLBITS, 18));
		btnQuayLai.setForeground(Color.WHITE);
		pnlBorder.add(btnQuayLai);

		// Event
		btnQuayLai.addActionListener(e -> this.dispose());
		btnGuiMa.addActionListener(e -> xuLyGuiMa());
		btnXacNhan.addActionListener(e -> xuLyXacNhan());
		btnShowPass.addActionListener(e -> xuLyHienMK());
		btnHidePass.addActionListener(e -> xuLyAnMK());
	}

	//
	public void xuLyHienMK() {
		btnHidePass.setVisible(true);
		btnShowPass.setVisible(false);
		txtMatKhau.setEchoChar((char) 0);
	}

	//
	public void xuLyAnMK() {
		btnHidePass.setVisible(false);
		btnShowPass.setVisible(true);
		txtMatKhau.setEchoChar('*');
	}

	// Xu ly gui ma xac nhan
	private void xuLyGuiMa() {
		String sDT = txtSoDienThoai.getText();
		if (sDT.equals("")) {
			JOptionPane.showMessageDialog(null, "Vui lòng nhập số điện thoại để xác nhận mã!", "Lỗi",
					JOptionPane.ERROR_MESSAGE);
		} else {
			String soDienThoai = "+84" + sDT.substring(1, 10);
			generatedCode = taoMaNgauNhien();
			guiMaXacNhan(soDienThoai, generatedCode);
			JOptionPane.showMessageDialog(null, "Mã xác nhận đã được gửi đến số điện thoại của bạn.");
		}
	}

	// Xu ly xac nhan reset mat khau
	private void xuLyXacNhan() {
		String maXacNhan = txtMaXacNhan.getText();
		String matKhau = new String(txtMatKhau.getPassword());
		String soDienThoai = txtSoDienThoai.getText();
		if (maXacNhan.isEmpty()) {
			JOptionPane.showMessageDialog(null, "Vui lòng nhập mã xác nhận", "Lỗi!", JOptionPane.ERROR_MESSAGE);
		} else {
			// Kiểm tra mã xác nhận có trùng khớp không
			if (kiemTraTrungKhop(maXacNhan, generatedCode)) {
				if (!matKhau.isEmpty()) {
					daoNV.updateMatKhau(matKhau, soDienThoai);
					JOptionPane.showMessageDialog(null, "Thay đổi mật khẩu thành công.");
					this.dispose();
				} else {
					JOptionPane.showMessageDialog(null, "Vui lòng nhập mật khẩu mới!", "Lỗi!",
							JOptionPane.ERROR_MESSAGE);
				}
			} else {
				JOptionPane.showMessageDialog(null, "Mã xác nhận không chính xác!", "Lỗi!", JOptionPane.ERROR_MESSAGE);
			}
		}

	}

	private static void guiMaXacNhan(String soDienThoai, String maXacNhan) {
		// Thực hiện gửi mã xác nhận qua SMS
		Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
		Message message = Message.creator(new com.twilio.type.PhoneNumber("+" + soDienThoai),
				new com.twilio.type.PhoneNumber("+" + TWILIO_PHONE_NUMBER), "Mã xác nhận của bạn là: " + maXacNhan)
				.create();
		System.out.println("Gửi mã xác nhận thành công! " + "SID của tin nhắn: " + message.getSid());
	}

	private static String taoMaNgauNhien() {
		// Tạo mã xác nhận ngẫu nhiên
		Random random = new Random();
		int code = 100000 + random.nextInt(900000);
		return String.valueOf(code);
	}

	private static boolean kiemTraTrungKhop(String enteredCode, String generatedCode) {
		// Kiểm tra xem mã xác nhận có trùng khớp không
		return enteredCode.equals(generatedCode);
	}

}
