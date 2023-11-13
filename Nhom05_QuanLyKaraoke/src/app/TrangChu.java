package app;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.ImageIcon;

import java.awt.Dimension;
import java.awt.Image;
import java.awt.Toolkit;

public class TrangChu extends JPanel {
	public TrangChu() {
		ImageIcon icon = new ImageIcon("src/img/trangChu.jpg");
		Image image = icon.getImage();
		JLabel label = new JLabel(icon);
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		Image scaledImage = image.getScaledInstance(screenSize.width, screenSize.height, Image.SCALE_SMOOTH);
		label.setIcon(new ImageIcon(scaledImage));
		add(label);

	}
}
