package entity;

import java.util.ArrayList;

import javax.net.ssl.SSLEngine;

import exception.NoAvailableException;
import exception.NoParentException;
import exception.NotToBeClassmatesException;
import exception.NotToBeColleaguesException;
import exception.NotToBeCoupledException;
import exception.NotToBeFriendsException;
import exception.TooYoungException;
import sun.text.resources.cldr.om.FormatData_om;
import utlility.IOUtility;
/**
 * This class is to manipulate the profiles and connections
 * according to the user request.
 * 
 * @author  
 * @version 1.0
 */

public class MiniNetManager {
	
	private ArrayList<Profile> profiles;
	private ArrayList<Connection> connections;

	public MiniNetManager(ArrayList<Profile> profiles, ArrayList<Connection> connections) {
		// TODO Auto-generated constructor stub
		this.profiles = profiles;
		this.connections = connections;
	}
	
	public void initMiniNet() throws NoParentException {
		buildDirectConnection();
		validateConnection();
		buildIndirectConnection();
	}

	public ArrayList<Profile> getProfiles() {
		return profiles;
	}

	public void setProfiles(ArrayList<Profile> profiles) {
		this.profiles = profiles;
	}

	public ArrayList<Connection> getConnections() {
		return connections;
	}

	public void setConnections(ArrayList<Connection> connections) {
		this.connections = connections;
	}

	 /** updateConnection() builds up the friends list 
	  * from the list of connections.
      * 
      */
	public void updateConnection(){
		buildDirectConnection();
		buildIndirectConnection();
	}
	
	/** buildDirectConnection() builds up the direct friends 
	 * list from the connections. 
     * 
     */
	public void buildDirectConnection(){
		for(Profile profile : profiles){
			for(Connection connection : connections){
				if(connection.getRelationship().equals("friends")){
					if(connection.containProfile(profile)){
						profile.addFriends(connection.getAnotherProfile(profile));
					}
				}else if(connection.getRelationship().equals("parent")){
					if(connection.getSourceProfile() instanceof Child) {
						((Child)connection.getSourceProfile()).addParents(connection.getTargetProfile());
					}else {
						((Child)connection.getTargetProfile()).addParents(connection.getSourceProfile());
					}
				}				
			}
		}
	}
	
	public void validateConnection() throws NoParentException{
		for(int i = profiles.size() - 1; i >= 0; i--){
			if(profiles.get(i) instanceof Child && ((Child) profiles.get(i)).getParents().size() != 2) {
				Profile profile = profiles.get(i);
				profiles.remove(profile);
				removeConnections(profile);
				throw new NoParentException("Removing from MiniNet as " + profile.getName()
						+ " has no parent or has only one parent!");
			}
		}
	}
	
	/** buildIndirectConnection() builds up the indirect friends 
	 * list from the direct friend list the previously built. 
     * 
     */
	public void buildIndirectConnection(){
		for(Profile profile : profiles){
			ArrayList<Profile> newFriends = new ArrayList<Profile>();
			for(Profile profile2 : profile.getFriends()){
				newFriends.addAll(profile2.getFriends());
			}
			profile.combineFriends(newFriends);
		}
	}
	
	/** clearConnection() clear up all the friends list.
	 * 
	 */
		public void clearConnection(){
			for(Profile profile : profiles){
				profile.getFriends().clear();
			}
		}
		
	/** createProfile(String name, int age, String status) is used 
	 * when creates a new profile with a name, an age and a status.
	 * 
	 * @param   name  A String which represent the profile name
	 * @param   age  An Integer which represents the profile age
	 * @param   status  A String which represents the profile status
	 * @return  Profile  An Profile which holds the profile information
	 */
	public Profile createProfile(String name, int age, String status){
		if(age >= 16){
			return new Adult(name, age, status);
		}else{
			return new Child(name, age, status);
		}
	}
	
	/** addProfile(Profile newProfile) adds a new profile to the 
	 * profile list. 
     * 
     * @param   newProfile  A profile which to be added into the profile list.
     */
	public void addProfile(Profile newProfile){
		profiles.add(newProfile);
	}
	
	public void listAllProfils(){
		IOUtility.printArrayList(profiles);
	}
	
	/** getProfileFromName(String name) is to return the 
	 * profile according to its name.
	 * 
	 * @param   name  A String which represent the profile name
	 * @return  profile  An Profile which matches the profile name
	 */
	public Profile getProfileFromName(String name){
		for(Profile profile : profiles){
			if(name.equals(profile.getName())){
				return profile;
			}
		}
		return null;
	}
	
	/** displayProfile(Profile profile) is to print profile 
	 * information to the command line.
	 * 
	 * @param  profile  A Profile which needs to be printed
	 *  to the command line
	 */
	public void displayProfile(Profile profile){
		System.out.println(profile.toString());
	}
	
	/** updateProfile(Profile profile, String name, int age, String status)
	 *  is to update the profile with the new name, new age or new status.
	 * 
	 * @param  profile  A Profile which needs to be updated
	 * @param  name     A String which represents the new profile name
	 * @param  age      An Integer which represents the new profile age
	 * @param  status   A String which represents the new profile status
	 */
	public void updateProfile(Profile profile, String name, int age, String status){
		profile.setName(name);
		profile.setAge(age);
		profile.setStatus(status);
	}
	
	/** deleteProfile(Profile profile) is to delete the profile 
	 * from the profile list, after that, all the connections 
	 * should be updated.
	 * 
	 * @param  profile  A Profile which needs to be deleted
	 * @throws NoParentException 
	 */
	public void deleteProfile(Profile profile) throws NoParentException{
		for(int i = profiles.size() - 1; i >= 0; i--){
			if(!canMakeCouple(profile)) {
				throw new NoParentException("Trying to delete an adult who has at least one dependent!");
			}else if(profiles.get(i).equals(profile)){
				profiles.remove(i);
			}
		}
		removeConnections(profile);
	}
	

	public String getDirectlyConnection(Profile profile1, Profile profile2){
		for(Connection connection : connections){
			if(connection.containBothProfile(profile1, profile2)){
				return connection.getDirectRelationship(profile1, profile2);
			}
		}
		return "";
	}
	
	/** removeConnections(Profile profile) remove connections 
	 * if the according profile is deleted.
	 * 
	 * @param  profile  A Profile that should be deleted in all connections.
	 */
	public void removeConnections(Profile profile){
		for(int i = connections.size() - 1; i >= 0; i--){
			if(connections.get(i).containProfile(profile)){
				connections.remove(i);
			}
		}
		clearConnection();
		updateConnection();
	}
	
	/** canCreateConnection(Profile profile1, Profile profile2, String relationship)
	 *  returns true if the connection can be created between profile1 and profile2.
	 * 
	 * @param  profile1      A Profile 
	 * @param  profile2      A Profile 
	 * @param  relationship  An Integer which represents the new profile age
	 * @return boolean       A boolean which represents the relationship can be created or not.
	 * @throws TooYoungException 
	 * @throws NotToBeFriendsException 
	 * @throws NotToBeCoupledException 
	 * @throws NotToBeColleaguesException 
	 * @throws NotToBeClassmatesException 
	 * @throws NoAvailableException 
	 * @throws NoParentException 
	 */
	public boolean canCreateConnection(Profile profile1, Profile profile2, String relationship) throws 
	TooYoungException, NotToBeFriendsException, NotToBeCoupledException, NotToBeColleaguesException, NotToBeClassmatesException, NoAvailableException, NoParentException{
		//constrain
		if(relationship.equals("friends")) {
			if(profile1.getAge() < 2 || profile1.getAge() < 2) {
				throw new TooYoungException("Trying to make friend with a young child!");
			}else if(profile1.getAge() <= 16 && profile2.getAge()<= 16 && Math.abs(profile1.getAge() - profile2.getAge()) > 3){
				throw new NotToBeFriendsException("Tring to connect two children with an age gap larger than 3!");
			}else if(profile1 instanceof Child && profile2 instanceof Adult || profile2 instanceof Child && profile1 instanceof Adult) {
				throw new NotToBeFriendsException("Trying to make an adult and a child friend!");
			}else {
				connections.add(new Connection(profile1, profile2, relationship));
				updateConnection();
				return true;
			}
		}else if(relationship.equals("parents")){
			if(profile1 instanceof Child && (((Child) profile1).getParents().size() == 0 || ((Child) profile1).getParents().size() == 1) || 
					profile2 instanceof Child && (((Child) profile2).getParents().size() == 0) || ((Child) profile2).getParents().size() == 1) {
				throw new NoParentException("A child or young child has no parent or has only one parent!");
			}/*else {
				connections.add(new Connection(profile1, profile2, relationship));
				updateConnection();
				return true;
			}*/
		}else if(relationship.equals("couple")) {
			if(profile1 instanceof Child || profile2 instanceof Child) {
				throw new NotToBeCoupledException("Trying to make a couple when at least one member is not an adult!");
			}else if(!canMakeCouple(profile1) || !canMakeCouple(profile2)) {
				throw new NoAvailableException("Trying to make two adults a couple and at least one of them is\r\n" + 
						"already connected with another adult as a couple");
			}else {
				connections.add(new Connection(profile1, profile2, relationship));
				return true;
			}
		}else if(relationship.equals("colleagues")) {
			if(profile1 instanceof Child || profile2 instanceof Child) {
				throw new NotToBeColleaguesException("Trying to connect a child in a colleague relation!");
			}else {
				connections.add(new Connection(profile1, profile2, relationship));
				return true;
			}
		}else if(relationship.equals("classmates")) {
			if(profile1.getAge() < 2 || profile1.getAge() < 2) {
				throw new NotToBeClassmatesException("Trying to make a young child in a classmate relation!");
			}else {
				connections.add(new Connection(profile1, profile2, relationship));
				return true;
			}
		}
		return false;
		
	}
	
	
	/** getParentsOrChild(String name, String relationship) prints
	 *  the parents or child of a profile from connection to the command line.
	 * 
	 * @param  name          A String which represents the profile name
	 * @param  relationship  A String which represents the connection
	 */
	public String getParentsOrChild(String name, String relationship){
		Profile profile = getProfileFromName(name);
		if(relationship.equals("parents")){
			if(profile instanceof Adult) {
				return name + "has no parents";
			}
			return "parents of " + name + " are: " + ((Child)profile).printParents();
		}else if(relationship.equals("child")){
			if(profile instanceof Child) {
				return name + "has no children";
			}
			for(Connection connection : connections){
				if(connection.getRelationship().equals("parent")) {
					if(connection.containProfile(profile)) {
						if(connection.getSourceProfile().equals(profile)) {
							return "child of " + name + " is: " + connection.getTargetProfile().getName();
						}else {
							return "child of " + name + " is: " + connection.getSourceProfile().getName();
						}
					}
				}
			}
		}
		return name + "has no children";
	}

	
	public boolean canMakeCouple(Profile profile) {
		for(Connection connection : connections) {
			if(connection.getRelationship().equals("couple")) {
				if(connection.containProfile(profile)) {
					return false;
				}
			}
		}
		return true;
	}
}
