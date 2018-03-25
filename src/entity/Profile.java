package entity;

import java.awt.Image;
import java.util.ArrayList;
/**
 * This class is a base-class Profile which defines profile 
 * name, age, image and status, and each profile has a ArrayList
 * of Profile as friends.
 * 
 * @author Zhen Wu 
 * @version 1.0
 */

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

    /** addFriends(Profile profile) is to add a single Profile
     *  to his friends' ArrayList.
     * 
     * @param profile A Profile to be added to the friends ArrayList
     */
	public void addFriends(Profile profile){
		if(!friends.contains(profile)){
			friends.add(profile);
		}
	}
	
    /** combineFriends(ArrayList<Profile> friendsArrayList) is 
     * to add a List of Profile to the friends ArrayList
     *
     * @param friendsArrayList A List of Profile to be added to friends ArrayList
     */
	public void combineFriends(ArrayList<Profile> friendsArrayList){
		for(Profile profile : friendsArrayList){
			if(!friends.contains(profile) && !profile.getName().equals(this.name)){
				friends.add(profile);
			}
		}
	}
	
	/** toString() build an String of all the information
	 *  of a Profile.
     * 
     * @return  String  An object which holds the all the 
     * information of a Profile.
     */
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
