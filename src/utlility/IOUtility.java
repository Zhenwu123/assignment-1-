package utlility;

import java.util.ArrayList;
import java.util.Scanner;

import data.MiniNetData;
import entity.Connection;
import entity.Profile;

/**
 * This class is a utility class which defines methods to read different forms of user input, 
 * read data from outside class.
 * 
 * @author  Zhen Wu
 * @version 1.0
 */

public class IOUtility
{
    private static Scanner in = new Scanner(System.in);
    
    /** getString(String prompt) prompts the user for an input, 
     * reads it as a String object and returns it.
     * 
     * @param   prompt  A String which promts for user input
     * @return  String  An object which holds the user input
     */
    public static String getString(String prompt)
    {
        System.out.print(prompt + " ");
        return in.nextLine();
    }

   
    /** getInteger() promts the user for an integer input and 
     * parses the input to an Integer object.
     * 
     * @return Integer  An object which holds the user input
     */
    public static Integer getInteger()
    {
        Integer i = 0;
        while(true)
        {
            try
            {
                i = Integer.parseInt(in.nextLine());
                break;
            }
            catch(Exception e)
            {
                System.err.print("Not an Integer! Enter an option: ");
            }
        }
        return i;  
    }
    
    /** getInteger(String prompt) prompts the user for an input and
     * parses the input to an Integer object.
     * 
     * @param   prompt  A String which promts for user input
     * @return  Integer  An object which holds the user input
     */
    public static Integer getInteger(String prompt)
    {
        Integer i = 0;
        while(true)
        {
            try
            {
            	System.out.print(prompt + " ");
            	i = Integer.parseInt(in.nextLine());
                break;
            }
            catch(Exception e)
            {
                System.err.print("Not an Integer! Enter an option: ");
            }
        }
        return i;  
    }
    
    /** readProfiles() reads sample profile data from MiniNetData class and 
     * return as an array list Profile object.
     * 
     * @return  ArrayList<Profile>  An ArrayList object which holds the
     *  sample Profile list
     */
    public static ArrayList<Profile> readProfiles(){
    	return MiniNetData.createProfile();
    }
    
    /** readConnections() reads sample connection data from MiniNetData class
     * and return as an array list Connection object.
     * 
     * @return  ArrayList<Connection>  An ArrayList object which holds the
     *  sample Connection list
     */
    public static ArrayList<Connection> readConnections(){
    	return MiniNetData.createConnection();
    }
    
    /** printArrayList() prints profile name from Profile list one by one.
     *  
     */
    public static void printArrayList(ArrayList<Profile> profileList){
		for(Profile profile : profileList){
			System.out.print(profile.getName() + " ");
		}
		System.out.println("\n");
	}
}


