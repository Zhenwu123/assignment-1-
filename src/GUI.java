import java.awt.Color;
import java.awt.ComponentOrientation;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.SwingConstants;

import entity.Adult;
import entity.Child;
import entity.MiniNetManager;
import entity.Profile;

public class GUI {

	private JFrame frame;
	private MiniNetManager manager;
	private JButton addPersonBtn, displaySelectedProfileBtn, deleteSelectedPersonBtn, 
	showRelationshipBtn, addRelationshipBtn, showParentsBtn, showChildBtn;
	private ArrayList<JButton> profileButton;
	private ArrayList<String> selectedProfile;
	private JPanel networkPanel, interactPanel;
	private static int MAX_VALUE = 1000;

	public GUI(MiniNetManager manager){
		this.manager = manager;
		initFrame();
	}
	
	private void initFrame() {
		frame = new JFrame("MiniNet");
		
		int width = 800;
		int height = 450;
		frame.setSize(width, height);
		int w = (Toolkit.getDefaultToolkit().getScreenSize().width - width) / 2;
		int h = (Toolkit.getDefaultToolkit().getScreenSize().height - height) / 2;
		frame.setLocation(w, h);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		Container contentPane = frame.getContentPane();
		
		
		JLabel lable = new JLabel("designed by Wu Zhen");
		lable.setHorizontalAlignment(SwingConstants.RIGHT);
		contentPane.add("South", lable);
		
		networkPanel = new JPanel();
		networkPanel.setSize(570, 420);
		networkPanel.setLayout(new GridLayout(0, 3, 12, 12));
		networkPanel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
		networkPanel.setBackground(Color.LIGHT_GRAY);
		
		initProfileButton();
		
		interactPanel = new JPanel();
		interactPanel.setSize(210, 420);
		interactPanel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
		FlowLayout flowLayout = new FlowLayout(FlowLayout.CENTER, 1, 15);
		interactPanel.setLayout(flowLayout);
		interactPanel.setBackground(Color.LIGHT_GRAY);
		
		addPersonBtn = new JButton("Add a person");
		addPersonBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String name = JOptionPane.showInputDialog("Enter the name of the profile");
				String ageString = JOptionPane.showInputDialog("Enter the age of the profile");
				int age = Integer.parseInt(ageString);
				String status = JOptionPane.showInputDialog("Enter the status of the profile");
				if(age >= 16) {
					Profile newProfile = new Adult(name, age, status);
					manager.addProfile(newProfile);
				}else {
					Profile newProfile = new Child(name, age, status);
					manager.addProfile(newProfile);
				}
				addProfileToButtonList(name);
				networkPanel.revalidate();
				networkPanel.repaint();
			}
		});
		interactPanel.add(addPersonBtn);
		
		displaySelectedProfileBtn = new JButton("Display Selected Profile");
		displaySelectedProfileBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(selectedProfile.size() == 1) {
					Profile profile = manager.getProfileFromName(selectedProfile.get(0));
					new DisplayFrame(profile);
					
				}else {
					JOptionPane.showMessageDialog(frame,
							"You should select only one profile to delete!",
							"Display Profile",
						    JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		interactPanel.add(displaySelectedProfileBtn);
		
		deleteSelectedPersonBtn = new JButton("Delete Selected Person");
		deleteSelectedPersonBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO need test
				if(selectedProfile.size() == 1) {
					removeProfileFromButtonList(selectedProfile.get(0));
					manager.deleteProfile(manager.getProfileFromName(selectedProfile.get(0)));
					String name = selectedProfile.get(0);
					selectedProfile.clear();
					networkPanel.revalidate();
					networkPanel.repaint();
					JOptionPane.showMessageDialog(frame,
							"You have successfully removed " + name + "'s Profile from MiniNet!", "Delete Profile", JOptionPane.PLAIN_MESSAGE);
				}else{
					JOptionPane.showMessageDialog(frame,
							"You should select only one profile to delete!",
							"Delete Profile",
						    JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		interactPanel.add(deleteSelectedPersonBtn);
		
		showRelationshipBtn = new JButton("Show Relationship");
		showRelationshipBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(selectedProfile.size() == 2) {
					Profile profile1 = manager.getProfileFromName(selectedProfile.get(0));
					Profile profile2 = manager.getProfileFromName(selectedProfile.get(1));
					String relation = manager.getDirectlyConnection(profile1, profile2);
					if(!relation.equals("")){
						JOptionPane.showMessageDialog(frame,
								"the relationship between" + profile1.getName() + " and " + profile2.getName() + 
								" is " + relation, "Relation", JOptionPane.PLAIN_MESSAGE);
					}else{
						JOptionPane.showMessageDialog(frame,
								profile1.getName() + " and " + profile2.getName() + " is not directly connected!",
							    "Relation",
							    JOptionPane.WARNING_MESSAGE);
					}
				}else {
					JOptionPane.showMessageDialog(frame,
							"You should select two profiles!",
						    "Relation",
						    JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		interactPanel.add(showRelationshipBtn);
		
		addRelationshipBtn = new JButton("Add Relationship");
		addRelationshipBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO need test
				if(selectedProfile.size() == 2) {
					Profile profile1 = manager.getProfileFromName(selectedProfile.get(0));
					Profile profile2 = manager.getProfileFromName(selectedProfile.get(1));
					String relation = JOptionPane.showInputDialog("Enter the relationship you want to add");
					if(manager.canCreateConnection(profile1, profile2, relation)){
						JOptionPane.showMessageDialog(frame,
								"new connection "+ relation + " between " + profile1.getName() + 
								" and " + profile2.getName() + " is created!", "Add Relation", JOptionPane.PLAIN_MESSAGE);
					}else{
						JOptionPane.showMessageDialog(frame,
								"new connection cannot be created",
							    "Add Relation",
							    JOptionPane.ERROR_MESSAGE);
					}
					
				}
			}
		});
		interactPanel.add(addRelationshipBtn);
		
		showParentsBtn = new JButton("Show Parents");
		showParentsBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(selectedProfile.size() == 1) {
					JOptionPane.showMessageDialog(frame,
							manager.getParentsOrChild(selectedProfile.get(0) ,"parents"),
							"Show Parents", JOptionPane.PLAIN_MESSAGE);
				}else {
					JOptionPane.showMessageDialog(frame,
							"You should select only one profile to show parents!",
							"Show Parents",
						    JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		interactPanel.add(showParentsBtn);
		
		showChildBtn = new JButton("Show Child");
		showChildBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(selectedProfile.size() == 1) {
					JOptionPane.showMessageDialog(frame,
							manager.getParentsOrChild(selectedProfile.get(0),"child"),
							"Show Child", JOptionPane.PLAIN_MESSAGE);
				}else {
					JOptionPane.showMessageDialog(frame,
							"You should select only one profile to show child!",
							"Show Child",
						    JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		interactPanel.add(showChildBtn);

		interactPanel.setComponentOrientation(
	                ComponentOrientation.LEFT_TO_RIGHT);
	
		JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, networkPanel, interactPanel);
		splitPane.setOneTouchExpandable(false);
		splitPane.setDividerLocation(580);
		

		contentPane.add(splitPane);
		
		//Display the window
		frame.setVisible(true);
		frame.setResizable(false);
	}
	
	protected void addProfileToButtonList(String name) {
		final JButton btn = new JButton(name);
		btn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(selectedProfile.contains(btn.getActionCommand())) {
					selectedProfile.remove(btn.getActionCommand());
					btn.setBorder(BorderFactory.createEmptyBorder());
				}else {
					selectedProfile.add(btn.getActionCommand());
					btn.setBorder(BorderFactory.createLineBorder(Color.RED, 3));
				}
			}
			
		});
		profileButton.add(btn);	
		networkPanel.add(btn);
	}

	public void initProfileButton() {
		int profileCount = manager.getProfiles().size();
		profileButton = new ArrayList<JButton>(MAX_VALUE);
		selectedProfile = new ArrayList<String>(MAX_VALUE);
		for(int i = 0; i< profileCount; i++) {
			final JButton btn = new JButton(manager.getProfiles().get(i).getName());
			btn.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					if(selectedProfile.contains(btn.getActionCommand())) {
						selectedProfile.remove(btn.getActionCommand());
						btn.setBorder(BorderFactory.createEmptyBorder());
					}else {
						selectedProfile.add(btn.getActionCommand());
						btn.setBorder(BorderFactory.createLineBorder(Color.RED, 3));
					}
				}
				
			});
			profileButton.add(btn);
		}
		
		for(JButton btn : profileButton) {
			networkPanel.add(btn);
		}
	}
	
	public void removeProfileFromButtonList(String name) {
		for(int i = profileButton.size()-1; i>=0; i--) {
			if(profileButton.get(i).getActionCommand().equals(name)) {
				networkPanel.remove(profileButton.get(i));
				profileButton.remove(profileButton.get(i));
			}
		}
	}
}
