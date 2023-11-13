package app;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.Border;

import connectDB.ConnectDB;
import dao.DAODangNhap;

public class Login extends JFrame implements ActionListener {
	private JLabel lblUser, lblPass, lblPicture;
	private JTextField txtUser;
	private JPasswordField txtPass;
	private JButton btnLogin, btnExit, btnShowPass, btnHidePass, btnForgetPass;
	private DAODangNhap daoDN = new DAODangNhap();

	public Login() {
		try {
			ConnectDB.getInstance().connect();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		createGUI();
	}

	public static void main(String[] args) {
		new Login().setVisible(true);
	}

	public void createGUI() {

		Image img_logo = new ImageIcon("src/IMG/login.png").getImage(); // Image Logo
		setTitle("ĐĂNG NHẬP");
		setSize(550, 250);
		setIconImage(img_logo);
		setLocationRelativeTo(null);
		setResizable(false);
		setDefaultCloseOperation(EXIT_ON_CLOSE);

		JPanel panel = new JPanel();
		panel.setBackground(Color.decode("#e6dbd1"));
		panel.setLayout(null);
		add(panel, BorderLayout.CENTER);

		// label User
		ImageIcon icon_user = new ImageIcon("src/IMG/user.png");
		lblUser = new JLabel("Tài khoản", icon_user, HEIGHT);
		lblUser.setFont(new Font("Times new roman", ABORT, 16));
		lblUser.setBounds(20, 20, 190, 40);
		panel.add(lblUser);

		// textfield User
		txtUser = new JTextField();
		txtUser.setSize(200, 30);
		txtUser.setBounds(140, 20, 320, 40);
		panel.add(txtUser);

		// label Password
		ImageIcon icon_pass = new ImageIcon("src/IMG/pass.png");
		lblPass = new JLabel("Mật khẩu", icon_pass, HEIGHT);
		lblPass.setFont(new Font("Times new roman", ABORT, 16));
		lblPass.setBounds(20, 80, 100, 40);
		panel.add(lblPass);

		// Textfield Password
		txtPass = new JPasswordField();
		txtPass.setSize(200, 30);
		txtPass.setBounds(140, 80, 320, 40);
		panel.add(txtPass);

		// Forgot Password
		btnForgetPass = new JButton("Quên mật khẩu ?");
		btnForgetPass.setFont(new Font("Times new roman", ABORT, 15));
		btnForgetPass.setBorder(null);
		btnForgetPass.setBackground(Color.decode("#e6dbd1"));
		btnForgetPass.setBounds(335, 120, 150, 35);
		panel.add(btnForgetPass);

		// Show password
		ImageIcon icon_showPass = new ImageIcon("src/IMG/hide.png");
		btnShowPass = new JButton(icon_showPass);
		btnShowPass.setBorder(null);
		btnShowPass.setBackground(Color.decode("#e6dbd1"));
		btnShowPass.setBounds(480, 85, 30, 30);
		panel.add(btnShowPass);

		// Hide password
		ImageIcon icon_hidePass = new ImageIcon("src/IMG/visible.png");
		btnHidePass = new JButton(icon_hidePass);
		btnHidePass.setBorder(null);
		btnHidePass.setBackground(Color.decode("#e6dbd1"));
		btnHidePass.setBounds(480, 85, 30, 30);
		panel.add(btnHidePass);

		// Button LOGIN and EXIT 6aa84f
		btnLogin = new JButton("Đăng nhập");
		btnLogin.setForeground(Color.WHITE);
		btnLogin.setBackground(Color.decode("#6aa84f"));
		btnLogin.setBounds(200, 160, 120, 40);
		panel.add(btnLogin);
		btnExit = new JButton("Thoát");
		btnExit.setForeground(Color.WHITE);
		btnExit.setBackground(Color.decode("#cc0000"));
		btnExit.setBounds(340, 160, 120, 40);
		panel.add(btnExit);

		// add event
		lblPass.setPreferredSize(lblUser.getPreferredSize());
		btnExit.addActionListener(this);
		btnLogin.addActionListener(this);
		btnShowPass.addActionListener(this);
		btnHidePass.addActionListener(this);
		btnForgetPass.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		Object o = e.getSource();
		// Event Exit
		if (o.equals(btnExit)) {
			System.exit(0);
		}
		// Event Login
		if (o.equals(btnLogin)) {
			String username = txtUser.getText();
			String pass = new String(txtPass.getPassword());
			if (username.equals("") || pass.equals("")) {
				JOptionPane.showMessageDialog(null, "Vui lòng nhập thông tin đăng nhập!");
			} else if (daoDN.kiemTraTK(username, pass) != null) {
				JOptionPane.showMessageDialog(null, "Đăng nhập thành công!");
				new Tab().setVisible(true);
				 dispose();
			}
			
		}
		// Event show pass
		if (o.equals(btnShowPass)) {
			btnHidePass.setVisible(true);
			btnShowPass.setVisible(false);
			txtPass.setEchoChar((char) 0);
		}
		// Event hide pass
		if (o.equals(btnHidePass)) {
			btnHidePass.setVisible(false);
			btnShowPass.setVisible(true);
			txtPass.setEchoChar('*');
		}
		if (o.equals(btnForgetPass)) {
			JOptionPane.showConfirmDialog(null, "Lấy lại mật khẩu", "Vui lòng xác nhận", JOptionPane.YES_NO_OPTION);
		}
	}

}
