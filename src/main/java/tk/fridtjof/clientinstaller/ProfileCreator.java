package tk.fridtjof.clientinstaller;


import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class ProfileCreator {
	
	
	public static final String PATH = Menus.DEFAULT_MINECRAFT_FOLDER;

	public static void createProfile(String clientID, String clientName, String icon) throws FileNotFoundException {
		
		//File jsonFile = new File(Menus.DEFAULT_MINECRAFT_FOLDER + "\\launcher_profiles.json");
		String line = null;
		File jsonFile = new File("test.json");
		Scanner scanner = new Scanner(jsonFile); 
		while(scanner.hasNextLine()) {
			line = scanner.nextLine();
			System.out.println(line);
		}
	}
}
