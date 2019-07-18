package Testing;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Formatter;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

import javax.security.auth.login.CredentialException;

import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.Status;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.eclipse.jgit.lib.IndexDiff.StageState;
import org.eclipse.jgit.lib.Ref;
import org.eclipse.jgit.lib.Repository;
import org.eclipse.jgit.lib.RepositoryCache;
import org.eclipse.jgit.storage.file.FileRepositoryBuilder;
import org.eclipse.jgit.transport.CredentialsProvider;
import org.eclipse.jgit.transport.UsernamePasswordCredentialsProvider;
import org.eclipse.jgit.util.FS;


public class LocalRepository {
 	
	static UI objectManager = new UI();
	
	private static final File localRepo = objectManager.getDirectory();
	
	public static final String remote = "https://github.com/TorbenIT/RepositoryForVersionControl.git";
	
	public final static CredentialsProvider cr = new UsernamePasswordCredentialsProvider("", "");
	
	static Branch branchManager = new Branch();
	
	IsolationDevelopment isolation = new IsolationDevelopment();


	

	
	public static Git getGit() {

		try {
//			Git git = Git.open(localRepo, FS.DETECTED);
//		
//			git.cloneRepository();
			
			System.out.println("Methode wegen Fehler auskommentiert");
		
		
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		return null;
		
		

		
	}
	



	public static Git cloneRepo() {
		
		try {
			Git git = Git.cloneRepository()
					.setDirectory(localRepo)
					.setRemote(remote)
					.call();
			
			return git;
			
			
		} catch (Exception e) {
			
			System.out.println("Fehler beim Klonen eines .git Repositories: " + e);
			return null;
			
		}
	

	}









	public Repository openGitRepo() {
		// funktioniert und wird aufgerufen
	
		try {			
				Repository repository = getGit().getRepository();
				
				
				branchManager.showBranches();
				
				branchManager.inDevBranchWechseln();
				System.out.println("Git Repository vorbereitet und bereit auf Änderungen:");
				
				
				
				return repository;
	
		
		} catch (Exception e) {
			
			System.out.println("Repo konnt nicht geöffnet werden: " + e);
			return null;
		}

	}
	
	

	
	
	public void startWorking() {
		// Startmethode in der main Methode
		
		try {
			Repository repo = openGitRepo();
			
			isolation.develop(repo);
			
			Git git = new Git(repo);
			
			Status status = git.status().call();
			
			Set<String> newFiles = status.getModified();
			
			if (newFiles != null && !newFiles.isEmpty()) {
			
				VersionControl v = new VersionControl();
				for (String newEdit : newFiles) {
				
					System.out.println("Geändert: " + newEdit);
				}
				v.startGitWorkflow();
				
			} else if (newFiles != null && newFiles.isEmpty()) {
				
				System.out.println("Du hast nichts geändert. Es ist alles up-to-date!");
				
			} else {
				// wenn null ist
				System.out.println("Fehler in der Methode getUntracked(). ");
			}
			
//			isolation.readChangesFromAFile(repo);
		
		} catch (Exception e){
			System.out.println("Folgender Fehler in der Funktion startWorking(): " + e);
		}
	}









	
	
	// getter setter
//	public static File getLocalrepo() {
//		return localRepo;
//	}
	
/*	
		try {
			
//	         	Git git1 = Git.init().setDirectory(localRepo).call();
	            

			
			
			if (Git git != null) {
			
				git = Git.open(localRepo);
				return git;			
			
			} else {
				
				System.out.println("Es wird von " + remote + " geklont...");
				
				git = cloneRepo();
				
				
				
				return git;
				
			}
				
		} catch (Exception e) {
			System.out.println("Fehler bei Methode getGit() : " + e);
			return null;
		}
 * 
 * 
 * 
 * */
	
	
}
