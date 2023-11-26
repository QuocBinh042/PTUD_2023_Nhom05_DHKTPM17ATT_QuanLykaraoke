package app;

import java.awt.BorderLayout;
import java.awt.Color;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;

import dao.DAONhanVien;

public class ForgotPassword extends JFrame {
	private static String ACCOUNT_SID = "AC4d68f5344f866140494be07649cb767c"; // Thay bằng ACCOUNT_SID
	private static String AUTH_TOKEN = "5208b7198b910b83bb55748eed4f8b35"; // Thay bằng AUTH_TOKEN
	private static String TWILIO_PHONE_NUMBER = "+19185057094"; // Thay bằng số điện thoại Twilio
	private JLabel lblSoDienThoai, lblMatKhau, lblMaXacNhan;
	private JTextField txtSoDienThoai, txtMaXacNhan;
	private JPasswordField txtMatKhau;
	private JButton btnGuiMa, btnXacNhan, btnQuayLai;
	private static String generatedCode;
	private DAONhanVien daoNV;

	public ForgotPassword() {
		setSize(310, 360);
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
		lblSoDienThoai.setBounds(20, 20, 150, 30);
		pnlBorder.add(lblSoDienThoai);

		txtSoDienThoai = new JTextField(20);
		txtSoDienThoai.setBounds(20, 50, 250, 35);
		pnlBorder.add(txtSoDienThoai);

		btnGuiMa = new ButtonGradient("Gửi mã");
		btnGuiMa.setBounds(185, 87, 85, 30);
		pnlBorder.add(btnGuiMa);

		lblMaXacNhan = new JLabel("Mã xác nhận");
		lblMaXacNhan.setBounds(20, 90, 150, 30);
		pnlBorder.add(lblMaXacNhan);

		txtMaXacNhan = new JTextField(20);
		txtMaXacNhan.setBounds(20, 120, 250, 35);
		pnlBorder.add(txtMaXacNhan);

		lblMatKhau = new JLabel("Mật khẩu mới");
		lblMatKhau.setBounds(20, 160, 150, 30);
		pnlBorder.add(lblMatKhau);

		txtMatKhau = new JPasswordField(20);
		txtMatKhau.setBounds(20, 190, 250, 35);
		pnlBorder.add(txtMatKhau);

		btnXacNhan = new ButtonGradient("Xác nhận");
		btnXacNhan.setBounds(20, 245, 105, 40);
		pnlBorder.add(btnXacNhan);

		btnQuayLai = new ButtonGradient("Quay lại");
		btnQuayLai.setBounds(165, 245, 105, 40);
		pnlBorder.add(btnQuayLai);

		// Event
		btnQuayLai.addActionListener(e -> this.dispose());
		btnGuiMa.addActionListener(e -> xuLyGuiMa());
		btnXacNhan.addActionListener(e -> xuLyXacNhan());
	}

	// Xu ly gui ma xac nhan
	public void xuLyGuiMa() {
		String soDienThoai = "";
		String sDT = txtSoDienThoai.getText();
		soDienThoai = "+84" + sDT.substring(1, 10);
		if (!soDienThoai.isEmpty()) {
			generatedCode = taoMaNgauNhien();
			guiMaXacNhan(soDienThoai, generatedCode);
			JOptionPane.showMessageDialog(null, "Mã xác nhận đã được gửi đến số điện thoại của bạn.");
		} else {
			JOptionPane.showMessageDialog(null, "Vui lòng nhập số điện thoại để xác nhận mã!", "Lỗi",
					JOptionPane.ERROR_MESSAGE);
		}
	}

	// Xu ly xac nhan reset mat khau
	public void xuLyXacNhan() {
		String maXacNhan = txtMaXacNhan.getText();
		String matKhau = new String(txtMatKhau.getPassword());
		String soDienThoai = txtSoDienThoai.getText();

		if (maXacNhan.isEmpty()) {
			JOptionPane.showMessageDialog(null, "Vui lòng nhập mã xác nhận", "Lỗi!", JOptionPane.ERROR_MESSAGE);
		} else {
			// Kiểm tra mã xác nhận có trùng khớp không
			if (kiemTraTrungKhop(maXacNhan, generatedCode)) {
				daoNV.updateMatKhau(matKhau, soDienThoai);
				JOptionPane.showMessageDialog(null, "Reset mật khẩu thành công.");
				this.dispose();
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
