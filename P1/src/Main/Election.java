package Main;

import java.io.FileNotFoundException;

import Ballot.BallotValidation;
import Resources.FileManager;

public class Election {
	
	private static FileManager file = new FileManager();
	//private static Ballot ballot;
	private static BallotValidation ballotValidation;
	
	public static void main(String[] args) throws FileNotFoundException {
		
		//file.ballotBuilder(file.ballots);
		//System.out.println("\n");
		//file.ballotBuilder(file.ballots2);
		//file.candidateListBuilder(file.candidates);
		ballotValidation = new BallotValidation(file.ballotBuilder(file.ballots2),file.candidateListBuilder(file.candidates));
		
		ballotValidation.validation();

	}

}
