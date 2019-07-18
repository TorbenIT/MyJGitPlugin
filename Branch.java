package Testing;

import java.io.File;
import java.io.IOException;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.ListBranchCommand;
import org.eclipse.jgit.api.ListBranchCommand.ListMode;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.eclipse.jgit.api.errors.RefAlreadyExistsException;
import org.eclipse.jgit.lib.Ref;
import org.eclipse.jgit.lib.Repository;
import org.slf4j.LoggerFactory;

public class Branch {
	
	static UI objectManager = new UI();

	Repository repository = LocalRepository.getGit().getRepository();
	
	Git git = new Git(repository);
	
	private static final String yourBranch = objectManager.getBranch();		// ggf. prüfen, ob null weil catch clause eintreten könnte
	
	static String currentBranch = yourBranch;
	
	
	List<Ref> call;
	
		public void showBranches() {
		
			
			System.out.println("Lokal befindliche Branches:");
			
			
			try {
				
				call = git.branchList().call();
				
				System.out.println("Nur zum Test, Ausgabe von currentBranch: " + currentBranch);
				
				for (Ref ref : call) {
					String r = ref.getName();
					String a = r.replace("refs/heads/", "");
					System.out.println(a);
					if (a == currentBranch) {
						
						String highlighted = a.toUpperCase();
						System.out.println(highlighted);	
					}
							
					
				}
			
				
			
			} catch (Exception e) {
				
				System.out.println("Fehler in bei der Methode showBranches(): " + e);
			}
		}
		
		
	


		public String inDevBranchWechseln() {
		
			
			
			try {
				
				if (call.contains(currentBranch)) {
					System.out.println("Du befindest dich im Branch: " + currentBranch);
					return currentBranch;
				}
				else {
					
					git.checkout()
					.setCreateBranch(true)
					.setName(currentBranch)
					.call();
					return currentBranch;
				}
					
				
				

			
			} catch (RefAlreadyExistsException e) {
			
				System.out.println("Es wurde in den Nebenbranch '" + currentBranch + "' gewechselt. Du kannst nun in diesem Branch entwickeln.");
				return currentBranch;
			
			} catch (Exception e) {
				
				System.out.println("Fehler beim Wechseln in einen anderen Branch: " + e);
				return null;
			} 

		
		}


		public void pushFromBranch() {
		
			
		}

}







