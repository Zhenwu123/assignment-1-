package entity;

import java.awt.Image;
import java.util.ArrayList;

public abstract class Profile{

	private String name;
	private int age;
	private Image image;
	private String status;
	
	private ArrayList<Profile> friends;
	
	public Profile(String name, int age, String status) {
		// TODO Auto-generated constructor stub
		setName(name);
		setAge(age);
		setStatus(status);
		
		friends = new ArrayList<Profile>();
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public Image getImage() {
		return image;
	}

	public void setImage(Image image) {
		this.image = image;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public ArrayList<Profile> getFriends() {
		return friends;
	}

	public void setFriends(ArrayList<Profile> friends) {
		this.friends = friends;
	}

	public void addFriends(Profile profile){
		if(!friends.contains(profile)){
			friends.add(profile);
		}
	}
	
	public void combineFriends(ArrayList<Profile> friendsArrayList){
		for(Profile profile : friendsArrayList){
			if(!friends.contains(profile) && !profile.getName().equals(this.name)){
				friends.add(profile);
			}
		}
	}
	
	public String toString(){
		String friendsString = "";
		for(Profile profile : friends){
			friendsString += profile.getName() + " ";
		}
		return "Profile name: " + getName() + "\n" 
					+ "Age: " + getAge() + "\n" + "Status: "
					+ getStatus() + "\n" + "Friends: " + friendsString + "\n";
	}
}
