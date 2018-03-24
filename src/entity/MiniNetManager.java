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

	public void updateConnection(){
		buildDirectConnection();
		buildIndirectConnection();
	}
	
	public void clearConnection(){
		for(Profile profile : profiles){
			profile.getFriends().clear();
		}
	}
	
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
	
	public void buildIndirectConnection(){
		for(Profile profile : profiles){
			ArrayList<Profile> newFriends = new ArrayList<Profile>();
			for(Profile profile2 : profile.getFriends()){
				newFriends.addAll(profile2.getFriends());
			}
			profile.combineFriends(newFriends);
		}
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
		for(int i = profiles.size() - 1; i >= 0; i--){
			if(profiles.get(i).equals(profile)){
				profiles.remove(i);
			}
		}
		removeConnections(profile);
	}
	
	public boolean isDirectFriends(Profile profile1, Profile profile2){
		for(Connection connection : connections){
			if(connection.getDirectRelationship(profile1, profile2).equals("friends")){
				return true;
			}
		}
		return false;
	}
	
	public void removeConnections(Profile profile){
		for(int i = connections.size() - 1; i >= 0; i--){
			if(connections.get(i).containProfile(profile)){
				connections.remove(i);
			}
		}
		clearConnection();
		updateConnection();
	}
	
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
