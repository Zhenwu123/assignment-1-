package entity;

import java.util.ArrayList;

public class Child extends Profile {
	
	private ArrayList<Profile> parents;

	public Child(String name, int age, String status) {
		super(name, age, status);		
		// TODO Auto-generated constructor stub

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
