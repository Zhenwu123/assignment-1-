import java.awt.Container;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

import entity.Profile;

/**
 * This class is a Frame class which display the detailed information of 
 * selected people in the main frame.
 * 
 * @author  Zhen Wu
 * @version 1.1
 */

@SuppressWarnings("serial")
public class DisplayFrame extends JFrame{
	
	public DisplayFrame(Profile profile) {
		// TODO Auto-generated constructor stub
		setTitle("MiniNet - Display Profile");
		
		int width = 600;
		int height = 280;
		setSize(width, height);
		int w = (Toolkit.getDefaultToolkit().getScreenSize().width - width) / 2;
		int h = (Toolkit.getDefaultToolkit().getScreenSize().height - height) / 2;
		setLocation(w, h);
		setVisible(true);
		
		Container contentPane = getContentPane();
		contentPane.setLayout(null);
		
		Image image = new ImageIcon(profile.getImage()).getImage();
		System.out.println(profile.getImage());
		ImageIcon icon = new ImageIcon(image);
		JLabel imageLabel = new JLabel();
		imageLabel.setIcon(icon);
		imageLabel.setSize(268, 268);
		imageLabel.setLocation(0, 0);
		
		JLabel nameLabel = new JLabel("Name:   " + profile.getName());
		nameLabel.setSize(300, 15);
		nameLabel.setLocation(290, 20);
		JLabel ageLabel = new JLabel("Age:   " + String.valueOf(profile.getAge()));
		ageLabel.setSize(300, 15);
		ageLabel.setLocation(290, 60);
		JLabel statusLabel = new JLabel("Status:   " + profile.getStatus());
		statusLabel.setSize(300, 15);
		statusLabel.setLocation(290, 100);
		
		String friendList = "";
		for(Profile profile2 : profile.getFriends()) {
			friendList += profile2.getName() + " ";
		}
		JLabel friendsLabel = new JLabel("Friends:   " + friendList);
		friendsLabel.setSize(300, 15);
		friendsLabel.setLocation(290, 140);
		
		contentPane.add(imageLabel);
		contentPane.add(nameLabel);
		contentPane.add(ageLabel);
		contentPane.add(statusLabel);
		contentPane.add(friendsLabel);
	}
	
	
}
