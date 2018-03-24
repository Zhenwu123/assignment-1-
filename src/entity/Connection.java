package entity;

public class Connection {

	private Profile sourceProfile;
	private Profile targetProfile;
	private String relationship;
	
	public Connection(Profile profile1, Profile profile2, String relationship) {
		// TODO Auto-generated constructor stub
		this.sourceProfile = profile1;
		this.targetProfile = profile2;
		this.relationship = relationship;
	}

	public boolean containProfile(Profile profile){
		if(sourceProfile.equals(profile) || targetProfile.equals(profile)){
			return true;
		}
		return false;
	}
	
	public Profile getAnotherProfile(Profile profile){
		if(sourceProfile.equals(profile)){
			return targetProfile;
		}else{
			return sourceProfile;
		}
	}
	
	public boolean containBothProfile(Profile profile1, Profile profile2){
		if((this.sourceProfile.equals(profile1) && this.targetProfile.equals(profile2)) || 
				(this.sourceProfile.equals(profile2) && this.targetProfile.equals(profile1))){
			return true;
		}
		return false;
	}
	
	public String getDirectRelationship(Profile profile1, Profile profile2){
		if(containBothProfile(profile1, profile2)){
			return relationship;
		}
		return null;
	}

	public String getRelationship() {
		return relationship;
	}

	public void setRelationship(String relationship) {
		this.relationship = relationship;
	}

	public Profile getSourceProfile() {
		return sourceProfile;
	}

	public void setSourceProfile(Profile sourceProfile) {
		this.sourceProfile = sourceProfile;
	}

	public Profile getTargetProfile() {
		return targetProfile;
	}

	public void setTargetProfile(Profile targetProfile) {
		this.targetProfile = targetProfile;
	}

	
	
}
