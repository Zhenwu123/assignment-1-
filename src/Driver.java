import utlility.IOUtility;
import entity.MiniNetManager;
import entity.Profile;

public class Driver {

	private MiniNetManager manager;
	private static boolean flag = true;

	public Driver(MiniNetManager manager){
		this.manager = manager;
	}

	public int showMenu(){
		System.out.println("MiniNet Menu");
		System.out.println("===================================");
		System.out.println("1. List everyone");
		System.out.println("2. Add a person");
		System.out.println("3. Select a person");
		System.out.println("4. Are these two direct friends?");
		System.out.println("5. Connect two persons in a meaningful way e.g. friend, parent");
		System.out.println("6. Find out the name(s) of a person¡¯s child(ren) or the names of the parents");
		System.out.println("7. Exit?\n");
		System.out.print("Enter an option: ");
		return IOUtility.getInteger();	
	}

	public int showSubMenu3(){
		System.out.println("1. Display the profile of the selected person");
		System.out.println("2. Update the profile information of the selected person");
		System.out.println("3. Delete the selected person");
		System.out.println("4. Back to previous option");
		System.out.print("Enter an option: ");
		return IOUtility.getInteger();	
	}

	public void run(){
		while(flag){
			switch (showMenu()) {
			case 1://pass
				manager.listAllProfils();
				break;
			case 2: //pass
				manager.addProfile(receiveProfileInfo());
				System.out.println("Profile is added!\n");
				break;
			case 3:
				String name = IOUtility.getString("Please enter the name you want to select: ");
				Profile profile = manager.getProfileFromName(name);
				if(profile != null){
					System.out.println("You have selected: " + name);
					System.out.println("===================================");
					switch (showSubMenu3()) {
					case 1: //pass
						System.out.println(profile.toString());
						break;
					case 2: //pass
						Integer age1 = IOUtility.getInteger("Please enter the new age: ");
						String status1 = IOUtility.getString("Please enter the new status: ");
						manager.updateProfile(profile, name, age1, status1);
						System.out.println(name +"'s profile is updated!\n");
						break;
					case 3: //pass
						manager.deleteProfile(profile);
						System.out.println(name +"'s profile is deleted!\n"); 
						break;
					case 4:
						break;
					}
				}else{
					System.out.println(name + " is not in MiniNet!");
				}
				break;
			case 4://pass
				String name1 = IOUtility.getString("Please enter the first profile name: ");
				Profile profile1 = manager.getProfileFromName(name1);
				String name2 = IOUtility.getString("Please enter the second profile name: ");
				Profile profile2 = manager.getProfileFromName(name2);
				if(manager.isDirectFriends(profile1, profile2)){
					System.out.println(name1 + " and " + name2 + " is direct friends!\n");
				}else{
					System.out.println(name1 + " and " + name2 + " is not direct friends!\n");
				}
				break;
			case 5:
				//pass
				String name3 = IOUtility.getString("Please enter the first profile name: ");
				Profile profile3 = manager.getProfileFromName(name3);
				String name4 = IOUtility.getString("Please enter the second profile name: ");
				Profile profile4 = manager.getProfileFromName(name4);
				String connection = IOUtility.getString("Please enter the connection (eg. friends, parents): ");
				if(manager.canCreateConnection(profile3, profile4, connection)){
					System.out.println("new connection "+ connection + " between " + name3 + " and " + name4 + " is created!\n");
				}else{
					System.out.println("new connection "+ connection + " between " + name3 + " and " + name4 + " cannot be created!\n");
				}
				break;
			case 6://pass
				String name5 =  IOUtility.getString("Please enter the profile name: ");
				String connection1 = IOUtility.getString("Are you looking forward the name of child or the name of parents?");
				manager.getParentsOrChild(name5, connection1);
				break;
			case 7://pass
				flag = false;
				System.err.println("You have successfully exit!");
				break;
			default:
				System.err.println("The input is outside the range!\n");
				break;
			}
		}
	}

	public Profile receiveProfileInfo(){
		String name = IOUtility.getString("Please enter the name: ");
		Integer age = IOUtility.getInteger("Please enter the age: ");
		String status = IOUtility.getString("Please enter the status: ");
		return manager.createProfile(name, age, status);
	}
}
