package Testing;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

import org.eclipse.jgit.lib.Repository;

public class IsolationDevelopment {

	private Formatter toFileWriter;
	
	Scanner fileReader, scanner, fileWriter;
	
	UI o = new UI();
	
	static StringBuffer stringBufferOfData = new StringBuffer();
	
	
	public void develop(Repository repo) {
		
		
			try {
				String fileName = o.chooseAFile();
				
				File file = new File(repo.getDirectory().getParent(), fileName);
				
				fileReader = new Scanner(file);
				
				scanner = new Scanner(System.in);
				
				
				if(file.exists()){
	
					System.out.println("Diese App ist schon verfügbar. (1) für read (2) für write");
					int selection = scanner.nextInt();
					
					switch (selection) {
					case 1:
						readChanges(repo);
						break;
					case 2:
						prepareFileWrite(file);
						writeToFile(file);
						break;
					default:
						System.out.println("Bitte wähle aus, ob die Datei zu lesen ist oder beschrieben werden soll.");
						break;
					}
					scanner.close();
				
				} else {
					
					toFileWriter = new Formatter(file);
					
					toFileWriter.format("%s %s %s", "20 ", "Torben ", "Kanji");
					
					toFileWriter.close();			
				}
						
			} catch (Exception e) {
				
				System.out.println("You got the following error " + e);
			}
			
		}


	private void prepareFileWrite(File file) {
		
	      try {
	            BufferedWriter bufwriter = new BufferedWriter(new FileWriter(file));
	            bufwriter.write(stringBufferOfData.toString());//writes the edited string buffer to the new file
	            bufwriter.close();//closes the file
	        
	      } catch (Exception e) {//if an exception occurs
	            System.out.println("Error occured while attempting to write to file: " + e.getMessage());
	        }
	}


	private void writeToFile(File file) {
			
		scanner = new Scanner(System.in);
		
		System.out.println("Bitte gib deinen Inhalt für die ausgewählte Datei an: ");
		String content = scanner.nextLine();
		
		try {
			BufferedWriter writer = new BufferedWriter(new FileWriter(file, true));
			
			writer.append('\n');
			System.out.println("Folgender Inhalt wird der Datei " + file + " angefügt:\n" + content);
			writer.append(content);
			
			writer.close();					// flushes Text in die Datei
			System.out.println("Done.");
		
		} catch (Exception e) {
			
			System.out.println("Failed to write your content to " + file + ": " + e);
		}
		
		
	}


	private void readChanges(Repository repo) {
		
		try {
		
			while (fileReader.hasNext()) {
                if(fileReader.hasNextInt()) {
                    System.out.println(fileReader.nextInt());
                } else if(fileReader.hasNextDouble()) {
                    System.out.println(fileReader.nextDouble());
                } else if(fileReader.hasNextLine()) {
                    System.out.println(fileReader.nextLine());
                }
            }
			
			fileReader.close();
			
		}
		catch (Exception e){
			System.out.println("Error at " +  e);
		}
	}

	
	
}
