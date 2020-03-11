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
	public LinkedList<Candidate> listOfCandidates;
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
	public void ballotBuilder(File file) throws FileNotFoundException {

		Scanner sc = new Scanner(file);

		String editor;
		int ballotID;

		while(sc.hasNextLine()) {
			editor = sc.nextLine();
			//ballotID = breakID(editor);
			//System.out.println(ballotID);
			//ballot = new Ballot(sc.nextLine());
			if(hasVotes(editor) > 0) {
				new Ballot(breakID(editor),
						breakIntoCandidates(editor.substring(hasVotes(editor) + 1, 
								editor.length())));
			}else {
				new Ballot(breakID(editor), null);
			}
		}
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
			return breakIntoCandidatesHelper(s);
		}
		return null;
	}
	private LinkedList<Candidate> breakIntoCandidatesHelper(String s) {
		if(s.length() == 0) {
			return listOfCandidates;
		}else {
			candidate = new Candidate(breakIntoCandidateID(s), breakIntoCandidateRank(s));
			listOfCandidates.add(candidate);
			return breakIntoCandidatesHelper(s.substring(hasVotes(s),s.length()));
		}
	}

	


}
