package utlility;

import java.util.ArrayList;
import java.util.Scanner;

import data.MiniNetData;
import entity.Connection;
import entity.Profile;

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
    
    public static ArrayList<Profile> readProfiles(){
    	return MiniNetData.createProfile();
    }
    
    public static ArrayList<Connection> readConnections(){
    	return MiniNetData.createConnection();
    }
    
    public static void printArrayList(ArrayList<Profile> profileList){
		for(Profile profile : profileList){
			System.out.print(profile.getName() + " ");
		}
		System.out.println("\n");
	}
}


