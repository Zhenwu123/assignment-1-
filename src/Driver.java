import utlility.IOUtility;
import entity.MiniNetManager;

public class Driver {

	private MiniNetManager manager;
	private static boolean flag = true;

	public Driver(MiniNetManager manager){
		this.manager = manager;
	}

	public static int showMenu(){
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

	public static int showSubMenu3(){
		System.out.println("1. Display the profile of the selected person");
		System.out.println("2. Update the profile information of the selected person");
		System.out.println("3. Delete the selected person");
		System.out.println("4. Back to previous option");
		return IOUtility.getInteger();	
	}

	public void run(){
		while(flag){
			switch (Driver.showMenu()) {
			case 1:
				manager.listAllProfils();
				break;
			case 2:
				
				break;
			case 3:

				break;
			case 4:

				break;
			case 5:

				break;
			case 6:

				break;
			case 7:
				flag = false;
				System.err.println("You have successfully exit!");
				break;
			default:
				System.err.println("The input is outside the range!\n");
				break;
			}
		}
	}



}
