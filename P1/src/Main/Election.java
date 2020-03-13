package Main;

import java.io.FileNotFoundException;

import Ballot.Ballot;
import Ballot.BallotValidation;
import Ballot.RegroupCandidates;
import DataStructures.LinkedList.LinkedList;
import Resources.FileManager;

public class Election {
	
	private static FileManager file = new FileManager();
	//private static Ballot ballot;
	private static BallotValidation ballotValidation;
	private static LinkedList<Ballot> toPrint = new LinkedList<Ballot>();
	private static VotingProcess votingProcess;
	
	public static void main(String[] args) throws FileNotFoundException {
		
		//file.ballotBuilder(file.ballots);
		//System.out.println("\n");
		//file.ballotBuilder(file.ballots2);
		//file.candidateListBuilder(file.candidates);
		ballotValidation = new BallotValidation(file.ballotBuilder(file.ballots2),file.candidateListBuilder(file.candidates));
		
		votingProcess = new VotingProcess(ballotValidation.getBallotList(), ballotValidation.getCandidateList());
		
		//printRegrouped(votingProcess.getRegroupCandidates());
		toPrint = ballotValidation.getBallotList();
		//printList(toPrint);
	}
//	public static void printRegrouped(LinkedList<RegroupCandidates> toPrint) {
//		for(RegroupCandidates rc : toPrint) {
//			System.out.println(rc.getCandidateID());
//			printRankList(rc.getListOfRanks());
//			System.out.println("\n");
//		}
//	}
	public static void printRankList(LinkedList<Integer> toPrint) {
		for(Integer i : toPrint) {
			System.out.println(i);
		}
	}
	public static void printList(LinkedList<Ballot> toPrint) {
		System.out.println("\n");
		System.out.println("Resulting Ballots after validation: ");
		System.out.println("\n");
		for(Ballot b : toPrint) {
			System.out.println("Ballot ID: " + b.getBallotNum());
		}
	}
}
