package app;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;

public class DigitalClock extends JPanel{
	private JLabel timeLabel;
	private JLabel dateLabel;
	public DigitalClock() {
		Border border = new LineBorder(Color.black);
		JPanel pane = new JPanel();
		pane.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
		pane.setLayout(new BorderLayout());
		
		timeLabel = new JLabel();
		timeLabel.setFont(new Font("Arial", Font.PLAIN, 40));
		timeLabel.setHorizontalAlignment(SwingConstants.CENTER);
		timeLabel.setVerticalAlignment(SwingConstants.CENTER);
		timeLabel.setForeground(Color.black);
		
		dateLabel = new JLabel();
		dateLabel.setFont(new Font("Arial", Font.PLAIN, 20));
		dateLabel.setHorizontalAlignment(SwingConstants.CENTER);
		dateLabel.setVerticalAlignment(SwingConstants.CENTER);
		dateLabel.setForeground(Color.black);
		
		pane.add(timeLabel, BorderLayout.CENTER);
		pane.add(dateLabel, BorderLayout.SOUTH);
		pane.setBackground(Color.decode("#e6dbd1"));
		
		Timer timer = new Timer(1000, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				updateTimeAndDate();
			}
		});
		timer.start();
		this.add(pane);
		this.setBackground(Color.decode("#e6dbd1"));
		this.setBorder(border);
	}
	
	private void updateTimeAndDate() {
		Calendar calendar = Calendar.getInstance();
		SimpleDateFormat timeFormatter = new SimpleDateFormat("HH:mm:ss");
		String time = timeFormatter.format(calendar.getTime());
		
		SimpleDateFormat dateFormatter = new SimpleDateFormat("EEE, MMM dd, yyyy");
		String date = dateFormatter.format(calendar.getTime());
		timeLabel.setText(time);
		dateLabel.setText(date);
	}
}
