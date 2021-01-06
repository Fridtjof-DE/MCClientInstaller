package tk.fridtjof.clientinstaller;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

public class Installer {
	
	public static void createDirectory(String path) {
		File file = new File(path);
		if(!file.exists()){
			System.out.println("Creating directory " + path + "...");
			file.mkdir();
		}
	}
	
	public static void saveFile(String url, String file) throws MalformedURLException, IOException, FileNotFoundException {
		
		System.out.println("Installing " + url + " to " + file + "...");
		URL urle = new URL(url);
		
		System.out.println("Opening Stream...");
	    InputStream in = urle.openStream();
	    
	    System.out.println("Setting Output Stream...");
	    FileOutputStream fos = new FileOutputStream(new File(file));
	    
	    int length = -1;
	    byte[] buffer = new byte[1024];
	    
	    System.out.println("Reading bytes...");
	    while ((length = in.read(buffer)) > -1) {
	    	fos.write(buffer, 0, length);
	    }
	    
	    System.out.println("Closing Streams...");
	    fos.close();
		in.close();
	}
}
