package Resources;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import Ballot.Ballot;
import Ballot.Candidate;
import DataStructures.LinkedList.LinkedList;

public class FileManager {

	public  File ballots;
	public  File ballots2;
	public  File candidates;
	public Ballot ballot;
	public LinkedList<Candidate> listOfCandidates = new LinkedList<Candidate>();
	public LinkedList<Candidate> listToPrint = new LinkedList<Candidate>();
	public Candidate candidate;

	public FileManager () {

		ballots  = new File("res/Ballots/ballots.csv");
		ballots2 = new File("res/Ballots/ballots2.csv");
		candidates = new File("res/Candidates/candidates.csv");

	}
	public void readBallot(File file) throws FileNotFoundException  {
		Scanner sc = new Scanner(file);

		while(sc.hasNextLine()) {
			System.out.println(sc.nextLine());
		}
	}
	
	
	//This method builds every ballot in the file.
	@SuppressWarnings("resource")
	public LinkedList<Ballot> ballotBuilder(File file) throws FileNotFoundException {
		
		LinkedList<Ballot> ballotList = new LinkedList<Ballot>();
		Scanner sc = new Scanner(file);
		String editor;

		while(sc.hasNextLine()) {
			editor = sc.nextLine();
			listOfCandidates.clear();
			if(hasVotes(editor) > 0) {
				System.out.println("Here comes a new Ballot! \n");
				
				ballot = new Ballot(breakID(editor),
						breakIntoCandidates(editor.substring(hasVotes(editor) + 1, 
								editor.length())));
				ballotList.add(ballot);
			}else {
				ballot = new Ballot(breakID(editor), null);
				ballotList.add(ballot);
			}
		}
		printBallotList(ballotList);
		return ballotList;
	}
	
	//This method finds the ID of the ballot.
	private int breakID(String s) {
		Integer toInt;
		if( hasVotes(s) < 0) {
			toInt = new Integer (s);
			return toInt.intValue();
		}
		toInt = new Integer(s.substring(0, hasVotes(s)));
		return  toInt.intValue();
	}

	//This method returns -1 if there are no votes and > 0 if it has votes.
	private int hasVotes(String s) {
		return s.indexOf(",");
	}
	//This method helps breaking down the ballot string by finding the candidate ID.
	private int breakIntoCandidateID(String s) {
		Integer toInt = new Integer (s.substring(0, 1));
		return toInt.intValue();
	}
	//This method helps breaking down the ballot string by finding the vote that the candidate received.
	private int breakIntoCandidateRank(String s) {
		Integer toInt = new Integer (s.substring(2,3));
		return toInt.intValue();
	}
	//This method builds a list of the Candidates in the ballot.
	private LinkedList<Candidate> breakIntoCandidates(String s){
		if(hasVotes(s) > 0) {
			listToPrint = breakIntoCandidatesHelper(s);
			printList(listToPrint);
			return listToPrint;
		}
		return null;
	}
	private LinkedList<Candidate> breakIntoCandidatesHelper(String s) {
		
		if(hasVotes(s) < 0) {
			candidate = new Candidate(breakIntoCandidateID(s), breakIntoCandidateRank(s));
			listOfCandidates.add(candidate);
			return listOfCandidates;
		}else {
			candidate = new Candidate(breakIntoCandidateID(s), breakIntoCandidateRank(s));
			listOfCandidates.add(candidate);
			return breakIntoCandidatesHelper(s.substring(hasVotes(s) + 1,s.length()));
		}
	}
	
	private void printList(LinkedList<Candidate> list) {
		for(Candidate c : list) {
			System.out.println("The candidate ID is: " + c.getCandidateID() + " and the casted vote in this ballot was: " + c.getRank());
		}
	}
	
	public void printBallotList(LinkedList<Ballot> list) {
		for(Ballot b : list) {
			System.out.println("Ballots ID in this list: " + b.getBallotNum());
		}
	}

}
