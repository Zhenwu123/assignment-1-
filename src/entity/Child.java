package entity;

import interf8ce.AddRelationship;

import java.util.ArrayList;

import sun.print.resources.serviceui;

public class Child extends Profile implements AddRelationship{
	
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
	public String toString() {
		// TODO Auto-generated method stub
		String result;
		if(getParents().size() != 0){
			result = "Parents: " + getParents().get(0).getName() + " and " + getParents().get(1).getName() + "\n";
		}else{
			result = "Parents:" + "\n";
		}
		return super.toString() + result;
	}

	@Override
	public void addParents(Profile profile) {
		// TODO Auto-generated method stub
		if(!parents.contains(profile)){
			parents.add(profile);
		}
	}

}
