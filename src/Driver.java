import utlility.IOUtility;
import entity.MiniNetManager;
import entity.Profile;
import exception.NoParentException;
/**
 * This class is a driver class which display a simple numbered menu system 
 * and enable command-line interaction with user.
 * 
 * @author  Zhen Wu
 * @version 1.0
 */

public class Driver {

    /** MiniNetManager manager is used to manipulate the profiles and connections.
     * 
     */
	private MiniNetManager manager;
	
    /** boolean flag is used to stop the menu loop if user selects exit.
     * 
     */
	private static boolean flag = true;

	public Driver(MiniNetManager manager){
		this.manager = manager;
	}

    /** showMenu() shows the main menu for interaction
     * and receive user input as an integer.
     * 
     * @return  Integer  An Integer which holds the user selection
     */
	public int showMenu(){
		System.out.println("MiniNet Menu");
		System.out.println("===================================");
		System.out.println("1. List everyone");
		System.out.println("2. Add a person");
		System.out.println("3. Select a person");
		System.out.println("4. Are these two directly connected?");
		System.out.println("5. Connect two persons in a meaningful way e.g. friend, parent");
		System.out.println("6. Find out the name(s) of a person¡¯s child(ren) or the names of the parents");
		System.out.println("7. Exit?\n");
		System.out.print("Enter an option: ");
		return IOUtility.getInteger();	
	}

    /** showSubMenu3() shows the sub menu of the third of the main menu
     *  for interaction and receive user input as an integer.
     * 
     * @return  Integer  An Integer which holds the user selection
     */
	public int showSubMenu3(){
		System.out.println("1. Display the profile of the selected person");
		System.out.println("2. Update the profile information of the selected person");
		System.out.println("3. Delete the selected person");
		System.out.println("4. Back to previous option");
		System.out.print("Enter an option: ");
		return IOUtility.getInteger();	
	}

    /** run() is the main loop of showing the main menu and 
     *  list all selections for user to select
     * @throws NoParentException 
     *
     */
	public void run() throws NoParentException{
		while(flag){
			switch (showMenu()) {
			//1. List everyone
			case 1:
				manager.listAllProfils();
				break;
			//2. Add a person
			case 2: 
				String name2 = IOUtility.getString("Please enter the name: ");
				Integer age = IOUtility.getInteger("Please enter the age: ");
				String status = IOUtility.getString("Please enter the status: ");
				Profile tempProfile = manager.createProfile(name2, age, status);
				manager.addProfile(tempProfile);
				System.out.println("Profile is added!\n");
				break;
			//3. Select a person
			case 3:
				String name3 = IOUtility.getString("Please enter the name you want to select: ");
				Profile profile = manager.getProfileFromName(name3);
				if(profile != null){
					System.out.println("You have selected: " + name3);
					System.out.println("===================================");
					switch (showSubMenu3()) {
					//3-1. Display the profile of the selected person
					case 1: 
						System.out.println(profile.toString());
						break;
					//3-2. Update the profile information of the selected person
					case 2: 
						Integer age1 = IOUtility.getInteger("Please enter the new age: ");
						String status1 = IOUtility.getString("Please enter the new status: ");
						manager.updateProfile(profile, name3, age1, status1);
						System.out.println(name3 +"'s profile is updated!\n");
						break;
					//3-3. Delete the selected person
					case 3: 
						manager.deleteProfile(profile);
						System.out.println(name3 +"'s profile is deleted!\n"); 
						break;
					//3-4. Continue
					case 4:
						break;
					}
				}else{
					System.out.println(name3 + " is not in MiniNet!");
				}
				break;
			//4. Are these two directly connected?
			case 4:
				String name41 = IOUtility.getString("Please enter the first profile name: ");
				Profile profile1 = manager.getProfileFromName(name41);
				String name42 = IOUtility.getString("Please enter the second profile name: ");
				Profile profile2 = manager.getProfileFromName(name42);
				String relation = manager.getDirectlyConnection(profile1, profile2);
				if(!relation.equals("")){
					System.out.println("the relationship between" + name41 + " and " + name42 + " is " + relation + "!\n");
				}else{
					System.out.println(name41 + " and " + name42 + " is not direct friends!\n");
				}
				break;
			//5. Connect two persons in a meaningful way e.g. friend, parent"
			case 5:
				String name51 = IOUtility.getString("Please enter the first profile name: ");
				Profile profile3 = manager.getProfileFromName(name51);
				String name52 = IOUtility.getString("Please enter the second profile name: ");
				Profile profile4 = manager.getProfileFromName(name52);
				String connection = IOUtility.getString("Please enter the connection (eg. friends, parents): ");
				/*if(manager.canCreateConnection(profile3, profile4, connection)){
					System.out.println("new connection "+ connection + " between " + name51 + " and " + name52 + " is created!\n");
				}else{
					System.out.println("new connection "+ connection + " between " + name51 + " and " + name52 + " cannot be created!\n");
				}*/
				break;
			//6. Find out the name(s) of a person¡¯s child(ren) or the names of the parents
			case 6:
				String name6 =  IOUtility.getString("Please enter the profile name: ");
				String connection1 = IOUtility.getString("Are you looking forward the name of child or the name of parents?");
				System.out.println(manager.getParentsOrChild(name6, connection1));
				break;
			//7. Exit?
			case 7:
				flag = false;
				System.err.println("You have successfully exit!");
				break;
			// Remind user the input is out of range.
			default:
				System.err.println("The input is outside the range!\n");
				break;
			}
		}
	}
}
