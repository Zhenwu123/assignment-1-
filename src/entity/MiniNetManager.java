package entity;

import java.util.ArrayList;

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

	
}
