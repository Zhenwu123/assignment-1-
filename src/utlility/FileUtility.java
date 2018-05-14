package utlility;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import entity.Adult;
import entity.Child;
import entity.Connection;
import entity.Profile;


/**
 * This class is a File Utility class which defines methods to read data from txt file.
 * 
 * @author  Zhen Wu
 * @version 1.1
 */

public class FileUtility {
	/**
	 * Reads data from file returning the lines as a list, or null if error
	 * 
	 * @param String fileName the file name
	 * @return ArrayList<String>
	 * @throws IOException 
	 */
	public static ArrayList<String> readFromFile(String fileName) throws IOException
    {
    	ArrayList<String> contentList = new ArrayList<String>();
    	BufferedReader br;
    	br = new BufferedReader(new FileReader(fileName));
    	String lineString;
    	while((lineString = br.readLine()) != null) 
    	{
    		if (lineString.startsWith("//") || lineString.isEmpty())
    		{
    			continue;
    		}
    		contentList.add(lineString.trim());//delete the spaces
    	}
    	br.close();
    	return contentList;
    }
	
	/**
	 * A method to build ArrayList of Profile
	 * 
	 * @param String fileName
	 * @return ArrayList<Profile>
	 * @throws IOException 
	 */
	public static ArrayList<Profile> buildProfileListFromFile(String fileName) throws IOException
	{
		if(readFromFile(fileName) != null)
		{
			return buildProfileListFromStringList(readFromFile(fileName));
		}
		else
		{
			return null;
		}
	}
	
	/**
	 * A method to build ArrayList of Connection
	 * 
	 * @param String fileName
	 * @return ArrayList<Connection>
	 * @throws IOException 
	 */
	public static ArrayList<Connection> buildConnectionListFromFile(String fileName, ArrayList<Profile> profiles) throws IOException
	{
		if(readFromFile(fileName) != null)
		{
			return buildConnectionListFromStringList(readFromFile(fileName), profiles);
		}
		else
		{
			return null;
		}
	}
	
	
	/**
	 * A method to build the Profile list from String list which is read from file
	 * 
	 * @param ArrayList<String> contentList
	 * @return ArrayList<Profile>
	 */
	public static ArrayList<Profile> buildProfileListFromStringList(ArrayList<String> contentList) 
	{
		ArrayList<Profile> profileList = new ArrayList<Profile>();
		for (String content : contentList)
		{
			String name = content.split(",")[0].trim();
			String image = content.split(",")[1].trim();
			String status = content.split(",")[2].trim();
			String gender = content.split(",")[3].trim();
			int age = Integer.parseInt(content.split(",")[4].trim());
			String state = content.split(",")[5].trim();
			if(age < 16) {
				Profile newprofile = new Child(name, age, status, image);
				profileList.add(newprofile);
			}else {
				Profile newprofile = new Adult(name, age, status, image);
				profileList.add(newprofile);
			}
		}
		return profileList;
	}

	/**
	 * A method to build the Connection list from String list which is read from file
	 * 
	 * @param ArrayList<String> contentList
	 * @return ArrayList<>
	 */
	public static ArrayList<Connection> buildConnectionListFromStringList(ArrayList<String> contentList, ArrayList<Profile> profiles) 
	{
		ArrayList<Connection> connectionList = new ArrayList<Connection>();
		for (String content : contentList) 
		{
			String sourceProfile = content.split(",")[0].trim();
			String targetProfile = content.split(",")[1].trim();
			String relationship = content.split(",")[2].trim();
			Profile source = getProfileFromName(sourceProfile, profiles);
			Profile target = getProfileFromName(targetProfile, profiles);
			Connection newConnection = new Connection(source, target, relationship);
			connectionList.add(newConnection);
		}
		return connectionList;
	}
	
	public static Profile getProfileFromName(String name, ArrayList<Profile> profiles){
		for(Profile profile : profiles){
			if(name.equals(profile.getName())){
				return profile;
			}
		}
		return null;
	}
}
