package Testing;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;


import org.eclipse.jgit.lib.Repository;

public class UI {
	
	Scanner scanner = new Scanner(System.in);
	
	
	
	public File getDirectory () {
		
		try {
			System.out.println("Please insert the path of your local repository here: ");
			String dir = scanner.nextLine();
			
			File directory = new File(dir);
			
			return directory;
		
		} catch (Exception e) {
			System.out.println("Fehler in UI Klasse, getDirectory() Methode: " + e);
			return null;
		}
	}


	public String getBranch () {
		
		try {
		
			System.out.println("Please insert your own feature branch: ");
			String br = scanner.nextLine();
			
			return br;
		} catch (Exception e) {
		
			System.out.println("Fehler beim Scannen des Userinputs!");
			
			return null; 
		}
		
	}
	
	
	public Boolean writeFile () {
		Boolean success = false;
		
		System.out.println("Bitte gebe hier die Bezeichnung deiner Datei an + Dateiendung: ");
		String file = scanner.nextLine();
		
		try {
			Formatter toFileWriter = new Formatter(file);
			success = true;
			
		
		} catch (Exception e) {
			
			System.out.println(e);
		}
		
		return success;
	
		
	}
		// Steffen
	
	public String chooseAFile() {
	
		System.out.println("Bitte gebe hier die Bezeichnung deiner Datei an + Dateiendung: ");
		String file = scanner.nextLine();
		

		
		
		
		return file;
	}


}
