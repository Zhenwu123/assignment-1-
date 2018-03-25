package entity;
/**
 * This class defines the connection between two profile,
 * the first profile is the source profile and the second
 * one is the target profile, relationship is the connection
 * between these profiles.
 * 
 * @author  
 * @version 1.0
 */

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

	/** containProfile(Profile profile) returns true if either
	 * the source profile or the target profile contains the profile.
	 * 
	 * @param   profile  A Profile which represent the profile 
	 * to the searched
	 * @return  True if either the source profile or the target profile contains the profile.
	 */
	public boolean containProfile(Profile profile){
		if(sourceProfile.equals(profile) || targetProfile.equals(profile)){
			return true;
		}
		return false;
	}
	
	/** getAnotherProfile(Profile profile) returns the other profile
	 * if one of the profile is matched with the given profile. 
	 * 
	 * @param   profile  A Profile which represent the profile to 
	 * be matched
	 * @return  Profile returns the other profile if one of the 
	 * profile is matched with the given profile.
	 */
	public Profile getAnotherProfile(Profile profile){
		if(sourceProfile.equals(profile)){
			return targetProfile;
		}else{
			return sourceProfile;
		}
	}
	
	/** containBothProfile(Profile profile1, Profile profile2) returns
	 *  true if the connection contains both of the profile1 and profile2.
	 * 
	 * @param   profile1  A Profile which represent the profile to be matched
	 * @param   profile2  A Profile which represent the profile to be matched
	 * @return  boolean   If the connection contains both of the profile1 and profile2.
	 */
	public boolean containBothProfile(Profile profile1, Profile profile2){
		if((this.sourceProfile.equals(profile1) && this.targetProfile.equals(profile2)) || 
				(this.sourceProfile.equals(profile2) && this.targetProfile.equals(profile1))){
			return true;
		}
		return false;
	}
	
	/** getDirectRelationship(Profile profile1, Profile profile2) returns
	 *  the connection between the profile1 and profile2, if there is 
	 *  no connection, return null.
	 * 
	 * @param   profile1  A Profile which represent the first profile
	 * @param   profile2  A Profile which represent the second profile
	 * @return  String    The connection between profile1 and profile2.
	 */
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
