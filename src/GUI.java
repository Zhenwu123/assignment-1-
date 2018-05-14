import java.awt.Color;
import java.awt.ComponentOrientation;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.sql.SQLException;
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
import entity.Connection;
import entity.MiniNetManager;
import entity.Profile;
import exception.NoAvailableException;
import exception.NoParentException;
import exception.NoSuchAgeException;
import exception.NotToBeClassmatesException;
import exception.NotToBeColleaguesException;
import exception.NotToBeCoupledException;
import exception.NotToBeFriendsException;
import exception.TooYoungException;
import utlility.DatabaseUtility;
import utlility.FileUtility;

/**
 * This class is a Main Frame class which display the people in MiniNet and it allows
 *  user to interact through the button on the right side. 
 * 
 * @author  Zhen Wu
 * @version 1.1
 */

public class GUI {

	private JFrame frame;
	ArrayList<Profile> profiles;
	ArrayList<Connection> connections = new ArrayList<Connection>();
	private MiniNetManager manager;
	private JButton addPersonBtn, displaySelectedProfileBtn, deleteSelectedPersonBtn, 
	showRelationshipBtn, addRelationshipBtn, showParentsBtn, showChildBtn;
	private ArrayList<JButton> profileButton;
	private ArrayList<String> selectedProfile;
	private JPanel networkPanel, interactPanel;
	private static int MAX_VALUE = 1000;
	private boolean DBUsed = false;

	public GUI(){
		initData();
		initFrame();
	}
	
    /** initData() reads profiledata from txt file if the file exist, otherwise it will
     * read people data from the database.
     * 
     * @throws NoSuchAgeException 
     * @throws ClassNotFoundException 
     * @throws SQLException 
     */
	private void initData() {
		try {
			profiles = FileUtility.buildProfileListFromFile("file/people.txt");
		} catch (IOException e) {
			showError(e);
		}
		if(profiles == null) {
			JOptionPane.showMessageDialog(frame,
					"Getting Profiles from Embedded Database", "Getting Profiles", JOptionPane.PLAIN_MESSAGE);
			try {
				if(!DatabaseUtility.checkDBexisted()) {
					DatabaseUtility.initProfileDB();
				}
				profiles = DatabaseUtility.getProfileFromDB();
				DBUsed = true;
			} catch (ClassNotFoundException e1) {
				// TODO Auto-generated catch block
				showError(e1);
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				showError(e1);
			}
		}
		try {
			connections = FileUtility.buildConnectionListFromFile("file/relations.txt", profiles);
		} catch (IOException e) {
			showError(e);
		}
		manager = new MiniNetManager(profiles, connections);
		try {
			manager.initMiniNet();
		} catch (NoParentException e) {
			// TODO Auto-generated catch block
			showError(e);
		}
	}
	
    /** initFrame() creates the frame and initialize the components. It can
     * also interact with user through the buttons to add profile, display 
     * profile, create connection, show connection and delete profile.
     * 
     * @throws ClassNotFoundException 
     * @throws SQLException 
     * @throws NoSuchAgeException 
     * @throws NoParentException 
     * @throws TooYoungException 
     * @throws NotToBeFriendsException 
     * @throws NotToBeCoupledException 
     * @throws NotToBeColleaguesException 
     * @throws NotToBeClassmatesException 
     * @throws NoAvailableException 
     */
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
				if(name == null) {
					return;
				}
				String ageString = JOptionPane.showInputDialog("Enter the age of the profile");
				if(ageString == null) {
					return;
				}
				int age = Integer.parseInt(ageString);
				try {
					validateAge(age);
					String status = JOptionPane.showInputDialog("Enter the status of the profile");
					if(status == null) {
						return;
					}
					if(age >= 16) {
						Profile newProfile = new Adult(name, age, status, "");
						manager.addProfile(newProfile);
						if(DBUsed) {
							DatabaseUtility.addProfileIntoDB(newProfile);
						}
					}else {
						Profile newProfile = new Child(name, age, status, "");
						manager.addProfile(newProfile);
						if(DBUsed) {
							DatabaseUtility.addProfileIntoDB(newProfile);
						}
					}
					addProfileToButtonList(name);
					networkPanel.revalidate();
					networkPanel.repaint();
				} catch (NoSuchAgeException e1) {
					// TODO Auto-generated catch block
					showError(e1);
				} catch (ClassNotFoundException e1) {
					// TODO Auto-generated catch block
					showError(e1);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					showError(e1);
				}
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
							"You should select only one profile to display!",
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
					try {
						manager.deleteProfile(manager.getProfileFromName(selectedProfile.get(0)));
						removeProfileFromButtonList(selectedProfile.get(0));
						String name = selectedProfile.get(0);
						if(DBUsed) {
							DatabaseUtility.deleteProfileFromDB(manager.getProfileFromName(selectedProfile.get(0)));
						}
						selectedProfile.clear();
						networkPanel.revalidate();
						networkPanel.repaint();
						JOptionPane.showMessageDialog(frame,
								"You have successfully removed " + name + "'s Profile from MiniNet!", "Delete Profile", JOptionPane.PLAIN_MESSAGE);
					} catch (NoParentException e1) {
						// TODO Auto-generated catch block
						showError(e1);
					} catch (ClassNotFoundException e1) {
						// TODO Auto-generated catch block
						showError(e1);
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						showError(e1);
					}
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
					if(relation == null) {
						return;
					}
					try {
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
					}catch (TooYoungException e1) {
						// TODO Auto-generated catch block
						showError(e1);
					} catch (NotToBeFriendsException e1) {
						// TODO Auto-generated catch block
						showError(e1);
					} catch (NotToBeCoupledException e1) {
						// TODO Auto-generated catch block
						showError(e1);
					} catch (NotToBeColleaguesException e1) {
						// TODO Auto-generated catch block
						showError(e1);
					} catch (NotToBeClassmatesException e1) {
						// TODO Auto-generated catch block
						showError(e1);
					} catch (NoAvailableException e1) {
						// TODO Auto-generated catch block
						showError(e1);
					} catch (NoParentException e1) {
						// TODO Auto-generated catch block
						showError(e1);
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
	
	public void showError(Exception exceptionError) {
	    String errorMessage = "Message: " + exceptionError.getMessage();
	    String title = exceptionError.getClass().getName();
	    showError(errorMessage, title);
	}
	
	public void showError(String errorMessage, String title) {
	    JOptionPane.showMessageDialog(null, errorMessage, title, JOptionPane.ERROR_MESSAGE);
	}
	
	public void validateAge(int age) throws NoSuchAgeException {
		if(age < 0 || age > 150) {
			throw new NoSuchAgeException("Trying to enter a person whose age is negative or over 150!");
		}
	}
}
