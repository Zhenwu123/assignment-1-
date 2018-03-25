package entity;

import java.util.ArrayList;

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
		updateConnection();
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
				}else if(connection.getRelationship().equals("parents")){
					if(connection.getSourceProfile().equals(profile)){
						((Child)profile).addParents(connection.getAnotherProfile(profile));
					}
				}				
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
	 */
	public void deleteProfile(Profile profile){
		for(int i = profiles.size() - 1; i >= 0; i--){
			if(profiles.get(i).equals(profile)){
				profiles.remove(i);
			}
		}
		removeConnections(profile);
	}
	
	/** isDirectFriends(Profile profile1, Profile profile2) returns
	 *  true if profile1 and profile2 are direct friends.
	 * 
	 * @param  profile1  A Profile 
	 * @param  profile2  A Profile
	 * @return boolean   return true if profile1 and profile2 are direct friends.
	 */
	public boolean isDirectFriends(Profile profile1, Profile profile2){
		for(Connection connection : connections){
			if(connection.getDirectRelationship(profile1, profile2).equals("friends")){
				return true;
			}
		}
		return false;
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
	 */
	public boolean canCreateConnection(Profile profile1, Profile profile2, String relationship){
		//constrain
		if(relationship.equals("friends")){
			if(profile1.getAge() <= 16 && profile2.getAge()<= 16 
					&& profile1.getAge() >= 2 && profile2.getAge() >= 2 
					&& Math.abs(profile1.getAge() - profile2.getAge()) <= 3){
				connections.add(new Connection(profile1, profile2, relationship));
				updateConnection();
				return true;
			}
		}else if(relationship.equals("parents")){
			if(profile1.getAge() <= 16){
				connections.add(new Connection(profile1, profile2, relationship));
				updateConnection();
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
	public void getParentsOrChild(String name, String relationship){
		Profile profile = getProfileFromName(name);
		if(relationship.equals("parents")){
			System.out.print("parents of " + name + " are: ");
			for(Connection connection : connections){
				if(connection.getSourceProfile().equals(profile)){
					System.out.print(connection.getTargetProfile().getName() + " ");
				}
			}
		}else if(relationship.equals("child")){
			System.out.print("child of " + name + " is(are): ");
			for(Connection connection : connections){
				if(connection.getTargetProfile().equals(profile)){
					System.out.print(connection.getSourceProfile().getName());
				}
			}
		}
		System.out.println("\n");
	}

}
