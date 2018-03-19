package entity;

import java.util.ArrayList;

public class Child extends Profile {
	
	private ArrayList<Profile> friends;
	private ArrayList<Profile> parents;

	public Child(String name, int age, String status) {
		// TODO Auto-generated constructor stub
		super(name, age, status);
		friends = new ArrayList<Profile>();
		parents = new ArrayList<Profile>(2);
	}
	
	public ArrayList<Profile> getParents() {
		return parents;
	}

	public void setParents(ArrayList<Profile> parents) {
		this.parents = parents;
	}

	@Override
	public void print2Screen() {
		// TODO Auto-generated method stub
		
	}
}
