package tk.fridtjof.clientinstaller;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ProfileCreator {
	
	public static final String PATH = Menus.DEFAULT_MINECRAFT_FOLDER;
	
	public static boolean profileExist = false;
	public static boolean replaced = false;

	@SuppressWarnings("resource")
	public static void createProfile(String clientID, String clientName, String icon) throws IOException {
		
		File jsonFile = new File(Menus.DEFAULT_MINECRAFT_FOLDER + "\\launcher_profiles.json");
		
		String line = null;
		Scanner scanner1 = new Scanner(jsonFile); 
		while(scanner1.hasNextLine()) {
			line = scanner1.nextLine();
			System.out.println(line);
			if(line.matches(".*\"" + clientID + "\" : .*")) {
				profileExist = true;
				break;
			}
		}
		
		if(!profileExist) {
			line = null;
			List<String> list = new ArrayList<String>();
			
			Scanner scanner2 = new Scanner(jsonFile); 
			while(scanner2.hasNextLine()) {
				line = scanner2.nextLine();
				System.out.println(line);
				if(line.matches("  \"profiles\" : .*") && !replaced) {
					line = "\"profiles\" : {\r\n    \"" + clientID + "\" : {\r\n      \"created\" : \"\",\r\n      \"icon\" : \"" + icon + "\",\r\n      \"lastUsed\" : \"\",\r\n      \"lastVersionId\" : \"" + clientID + "\",\r\n      \"name\" : \"" + clientName + "\",\r\n      \"type\" : \"custom\"\r\n    },";
					replaced = true;
				}
				list.add(line);
			}
			String file = jsonFile + "";
			File testFile = new File(file);
			FileWriter writer = new FileWriter(testFile);
			
			for(String line1:list) {
	            System.out.println(line1);
	            writer.write(line1);
	        }
			
			writer.close();
		}
	}
}
