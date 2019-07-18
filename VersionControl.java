package Testing;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.MergeCommand;
import org.eclipse.jgit.api.PullCommand;
import org.eclipse.jgit.api.PullResult;
import org.eclipse.jgit.api.RebaseCommand.InteractiveHandler;
import org.eclipse.jgit.api.RebaseResult;
import org.eclipse.jgit.api.ResetCommand.ResetType;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.eclipse.jgit.api.errors.NoHeadException;
import org.eclipse.jgit.api.errors.RefNotFoundException;
import org.eclipse.jgit.api.errors.WrongRepositoryStateException;
import org.eclipse.jgit.errors.IllegalTodoFileModification;
import org.eclipse.jgit.lib.ObjectId;
import org.eclipse.jgit.lib.RebaseTodoLine;
import org.eclipse.jgit.lib.RebaseTodoLine.Action;
import org.eclipse.jgit.lib.Ref;
import org.eclipse.jgit.merge.MergeResult;
import org.eclipse.jgit.merge.MergeStrategy;
import org.eclipse.jgit.transport.RefSpec;
import org.eclipse.jgit.api.RebaseCommand;

import org.eclipse.jgit.api.*;

public class VersionControl {
	
	Git git = LocalRepository.getGit();
	
	
	public void startGitWorkflow() {
	

		
		try {						/* 	Staging Area und gleichzeitiges Commiten von Files, die geändert/ gelöscht
		 								wurden sind, nicht aber neuhinzugefügt.*/
				git.commit()
					.setAll(true)
					.setMessage("Bestätigung von Änderungen (Commit) an allen Dateien")
					.call();
				
				System.out.println("Alle Änderungen für die Freischaltung zum Repository bei " + git.getRepository() + " bestätigt.");
		
			//Pushen				
				RefSpec spec = new RefSpec(""+ Branch.currentBranch);
				
				try {
					PushCommand pushCommand = git.push();
				      pushCommand.setCredentialsProvider(LocalRepository.cr);
				      pushCommand.setRemote("origin");
				      pushCommand.setRefSpecs(spec);
				      pushCommand.call();
				      System.out.println("Änderungen hochgeladen.");
                    
                    System.out.println("Pushed from repository: " + git.getRepository().getDirectory() + " to remote repository at " + LocalRepository.remote);
    				
    				
                } catch (Exception e) {
                	System.out.println("Fehler beim Pushen: " + e);
                }

               
		
			// Pullen
				
					pullFromRemote();
			
				 
				 	Ref checkout = git.checkout()
				 		.setName("master")
				 		.call();
				 	
				 	System.out.println("Du befindest dich im Branch " + checkout.getName());
				 	
				 	
				 	


		
		} catch (Exception e) {
			
			System.out.println("Fehler beim Versionieren: " + e);
		}
	}

	
	public void pullFromRemote() {
	
	    try {
	        PullResult result = git.pull()
	                .setCredentialsProvider(LocalRepository.cr)
	                .setRemote("origin")
	                .setRemoteBranchName("master")
	                .call();
	        
	        if (result.isSuccessful()) {
	            System.out.println(" Dein lokales Repo ist auf dem aktuellsten Stand. :) ");
	            
	          } else {
	             org.eclipse.jgit.api.MergeResult output = result.getMergeResult();
	             System.out.println(output);
	             
	            
	          }
	        
	        } catch (GitAPIException e) {
	          
	          System.out.println("Was auch immer GITAPIException bedeutet, es wurde ein Fehler geworfen.");
	        }
	    
	}









	
	
	
	
	
	
	
	
	
	

// -------------------------- NICHT FUNKTIONIERENDE METHODEN --------------------------------------------------
//	public void merge() {
//	
//		
//		
//		try {
//			ObjectId mergeBase = git.getRepository().resolve("isolated-test-branch");
//			
//			
//			org.eclipse.jgit.api.MergeResult merge = git.merge()
//					.include(mergeBase)
//					.setCommit(true)
//					.setFastForward(MergeCommand.FastForwardMode.NO_FF)
//					.setStrategy(MergeStrategy.RECURSIVE)
//					.setMessage("Merged changes")
//					.call();
//		
//			System.out.println("Merge-Results for id: " + mergeBase + ": " + merge);
//			
//			for (Map.Entry<String, int[][]> entry : merge.getConflicts().entrySet()) {
//			
//				System.out.println("Key: " + entry.getKey());
//				for (int[] arr : entry.getValue()) {
//				
//					System.out.println("Value: " + Arrays.toString(arr));
//				}
//			}
//			
//		
//		} catch (Exception e) {
//			
//			e.printStackTrace();
//		}
//				
//
//	}
	
/*	public void rebase() throws NoHeadException, RefNotFoundException, WrongRepositoryStateException, GitAPIException {
	
		Ref reset = git.reset().setMode(ResetType.HARD).setRef("refs/heads/master").call();
	
		System.out.println(reset);
		
	}	*/

	





































}

