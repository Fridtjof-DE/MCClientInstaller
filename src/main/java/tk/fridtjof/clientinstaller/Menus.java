package tk.fridtjof.clientinstaller;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

public class Menus {

	public static final String CLIENT_ID = "Pudding-1.0.0";
	public static final String CLIENT_NAME = "Pudding Client";
	public static final String INSTALLER_TITLE = CLIENT_NAME + " - Installer";
	
	public static final String APPDATA = System.getenv("APPDATA");
	public static final String DEFAULT_MINECRAFT_FOLDER = APPDATA + "\\" + ".minecraft";

	//Change these:
	public static final String JAR_LINK = "https://www.fridtjof.tk/Pudding.jar";
	public static final String JSON_LINK = "https://www.fridtjof.tk/Pudding.json";
	
	public static String minecraftFolder = DEFAULT_MINECRAFT_FOLDER;
	public static String versionsFolder = minecraftFolder + "\\" + "versions";	
	public static String clientFolder = versionsFolder + "\\" + CLIENT_ID;
	public static String jsonFile = clientFolder + "\\" + CLIENT_ID + ".json";
	public static String jarFile = clientFolder + "\\" + CLIENT_ID + ".jar";
	public static boolean createProfile = true;
	public static boolean profileCreated = false;
	public static boolean jsonInstalled = false;
	public static boolean jarInstalled = false;
	
	public static void main(String[] args) {
		System.out.println("Menu 1");
		int input = JOptionPane.showConfirmDialog(null, "This programm will install the " + CLIENT_NAME + " to the selected folder.", INSTALLER_TITLE, JOptionPane.YES_NO_OPTION);
		System.out.println("Input: " + input);
		if(input == 0) {
			menu2();
		}
	}

	private static void menu2() {
		System.out.println("Menu 2");
		int input = JOptionPane.showConfirmDialog(null, "Install in default directory?", INSTALLER_TITLE, JOptionPane.YES_NO_CANCEL_OPTION);
		System.out.println("Input: " + input);
		if(input == 1) {
			JFileChooser chooser = new JFileChooser();
			chooser.setCurrentDirectory(new java.io.File(DEFAULT_MINECRAFT_FOLDER));
			chooser.setDialogTitle(INSTALLER_TITLE);
			chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
			if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
				File choosenFile = chooser.getSelectedFile();
				System.out.println("selected Directory: " + choosenFile);
				minecraftFolder = choosenFile + "";
			}
		} else {
			minecraftFolder = DEFAULT_MINECRAFT_FOLDER;
		}
		versionsFolder = minecraftFolder + "\\" + "versions";	
		clientFolder = versionsFolder + "\\" + CLIENT_ID;
		jsonFile = clientFolder + "\\" + CLIENT_ID + ".json";
		jarFile = clientFolder + "\\" + CLIENT_ID + ".jar";
		menu3();
	}

	private static void menu3() {
		System.out.println("Menu 3");
		int input = JOptionPane.showConfirmDialog(null, "Create a profile in the launcher?", INSTALLER_TITLE, JOptionPane.YES_NO_CANCEL_OPTION);
		System.out.println("Input: " + input);
		if(input == 1) {
			createProfile = false;
		}
		menu4();
	}

	private static void menu4() {
		System.out.println("Menu 4");
		int input = JOptionPane.showConfirmDialog(null, "Install now?", INSTALLER_TITLE, JOptionPane.YES_NO_OPTION);
		System.out.println("Input: " + input);
		if(input == 0) {
			
			Installer.createDirectory(versionsFolder);
			Installer.createDirectory(clientFolder);
			
			System.out.println("Installing client JSON...");
			try {
				Installer.saveFile(JSON_LINK, jsonFile);
				jarInstalled = true;
				System.out.println("JSON installed!");
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			System.out.println("Installing client JAR...");
			try {
				Installer.saveFile(JAR_LINK, jarFile);
				jsonInstalled = true;
				System.out.println("JAR installed!");
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			if(createProfile) {
				System.out.println("Creating launcher profile...");
				try {
					ProfileCreator.createProfile(CLIENT_ID, CLIENT_NAME, "");
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				}
				profileCreated = true;
				System.out.println("Launcher profile created!");
			}
			
			if(!jsonInstalled && !jarInstalled) {
				bothFailed();
			} else if(!jsonInstalled && jarInstalled) {
				jsonFailed();
			} else if(jsonInstalled && !jarInstalled) {
				jarFailed();
			} else if(createProfile && !profileCreated) {
				profileFailed();
			} else {
				menu5();
			}
		}
	}

	private static void menu5() {
		System.out.println("Menu 5");
		JOptionPane.showMessageDialog(null, "Installation successful!", INSTALLER_TITLE, JOptionPane.INFORMATION_MESSAGE);
	}

	private static void bothFailed() {
		JOptionPane.showMessageDialog(null, "Installation failed! - Error #1", INSTALLER_TITLE, JOptionPane.ERROR_MESSAGE);
	}
	
	private static void jsonFailed() {
		JOptionPane.showMessageDialog(null, "Installation failed! - Error #2", INSTALLER_TITLE, JOptionPane.ERROR_MESSAGE);
	}
	
	private static void jarFailed() {
		JOptionPane.showMessageDialog(null, "Installation failed! - Error #3", INSTALLER_TITLE, JOptionPane.ERROR_MESSAGE);
	}
	
	private static void profileFailed() {
		JOptionPane.showMessageDialog(null, "Profile creation failed! - Error #4", INSTALLER_TITLE, JOptionPane.ERROR_MESSAGE);
	}
}