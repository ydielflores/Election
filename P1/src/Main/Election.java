package Main;

/**
 * Main class
 * 
 * 
 * 
 * @author Ydiel Z. Flores Torres - 802 14 2452
 */



import java.io.FileNotFoundException;
import java.io.IOException;

import Ballot.Ballot;
import Ballot.BallotValidation;
import DataStructures.LinkedList.LinkedList;
import Resources.FileManager;

public class Election {
	
	private static FileManager file = new FileManager();
	//private static Ballot ballot;
	private static BallotValidation ballotValidation;
	private static VotingProcess votingProcess;
	
	public static void main(String[] args) throws IOException {
		
		ballotValidation = new BallotValidation(file.ballotBuilder(file.ballots2),file.candidateListBuilder(file.candidates));
		
		votingProcess = new VotingProcess(ballotValidation.getBallotList(), ballotValidation.getCandidateList());
		votingProcess.start(ballotValidation.getBlanckBallots(), ballotValidation.getAmountOfInvalidBallots(), ballotValidation.getTotalBallots());
	}

}
