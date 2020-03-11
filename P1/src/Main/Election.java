package Main;

import java.io.FileNotFoundException;

import Resources.FileManager;

public class Election {
	
	private static FileManager file = new FileManager();
	//private static Ballot ballot;
	
	
	public static void main(String[] args) throws FileNotFoundException {
		
//		file.readBallot(file.ballots);
		file.ballotBuilder(file.ballots);
//		System.out.println("\n");
//		System.out.println("Next Ballot: ");
//		file.readBallot(file.ballots2);
//		file.ballotBuilder(file.ballots2);

	}

}
