package entity;

import java.util.ArrayList;

import utlility.IOUtility;

public class MiniNetManager {
	
	private ArrayList<Profile> profiles;
	private ArrayList<Connection> connections;

	public MiniNetManager(ArrayList<Profile> profiles, ArrayList<Connection> connections) {
		// TODO Auto-generated constructor stub
		this.profiles = profiles;
		this.connections = connections;
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

	public void updateRelationship(){
		
	}
	
	public Profile createProfile(String name, int age, String status){
		if(age >= 16){
			return new Adult(name, age, status);
		}else{
			return new Child(name, age, status);
		}
	}
	
	public void addProfile(Profile newProfile){
		profiles.add(newProfile);
	}
	
	public void listAllProfils(){
		IOUtility.printArrayList(profiles);
	}
	
	public Profile getProfileFromName(String name){
		for(Profile profile : profiles){
			if(name.equals(profile.getName())){
				return profile;
			}
		}
		return null;
	}
	
	public void displayProfile(Profile profile){
		System.out.println(profile.toString());
	}
	
	public void updateProfile(Profile profile, String name, int age, String status){
		profile.setName(name);
		profile.setAge(age);
		profile.setStatus(status);
	}
	
	public void deleteProfile(Profile profile){
		for(int i = profiles.size() - 1; i > 0; i--){
			if(profiles.get(i).equals(profile)){
				profiles.remove(profile);
			}
		}
		removeConnections(profile);
		
	}
	
	public boolean isDirectFriends(Profile profile1, Profile profile2){
		for(Connection connection : connections){
			if(connection.getDirectRelationship(profile1, profile2) != null && 
					connection.getDirectRelationship(profile1, profile2).equals("friends")){
				return true;
			}
		}
		return false;
	}
	
	public void removeConnections(Profile profile){
		/*for(Connection connection : connections){
			if(connection.containProfile(profile)){
				connections.remove(connection);
			}
		}*/
		updateRelationship();
	}
	
	public boolean canCreateConnection(Profile profile1, Profile profile2, String relationship){
		//constrain
		if(true){
			connections.add(new Connection(profile1, profile2, relationship));
			updateRelationship();
			return true;
		}
		return false;
		
	}
	
	public void getParentsOrChild(String name, String relationship){
		Profile profile = getProfileFromName(name);
		if(relationship.equals("parents")){
			if(((Child)profile).getParents().size() != 0){
				System.out.print("parents of " + name + "are: ");
				IOUtility.printArrayList(((Child)profile).getParents());
			}
		}else if(relationship.equals("child")){
			if(((Adult)profile).getChild().size() != 0){
				System.out.print("child of " + name + "is(are): ");
				IOUtility.printArrayList(((Adult)profile).getChild());
			}
		}
	}

}
