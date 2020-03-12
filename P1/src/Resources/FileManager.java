package Resources;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import Ballot.Ballot;
import Ballot.Candidate;
import Ballot.Ranking;
import DataStructures.LinkedList.LinkedList;

public class FileManager {

	public  File ballots;
	public  File ballots2;
	public  File candidates;
	
	public Ballot ballot;
	public Ranking ranking;
	
	public LinkedList<Ranking> listOfRanking = new LinkedList<Ranking>();
	public LinkedList<Ranking> listToPrint = new LinkedList<Ranking>();
	
	public FileManager () {

		ballots  = new File("res/Ballots/ballots.csv");
		ballots2 = new File("res/Ballots/ballots2.csv");
		candidates = new File("res/Candidates/candidates.csv");

	}

	
	
	/*This method builds every ballot in the file.
	 *This method has a print, remember to delete.  
	 */
	@SuppressWarnings("resource")
	public LinkedList<Ballot> ballotBuilder(File file) throws FileNotFoundException {
		
		LinkedList<Ballot> ballotList = new LinkedList<Ballot>();
		Scanner sc = new Scanner(file);
		String editor;

		while(sc.hasNextLine()) {
			editor = sc.nextLine();
			listOfRanking.clear();
			if(nextComma(editor) > 0) {
				System.out.println("Here comes a new Ballot! \n");
				
				ballot = new Ballot(breakID(editor),
						breakIntoCandidates(editor.substring(nextComma(editor) + 1, 
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
	/*This method finds the ID of the ballot.*/
	private int breakID(String s) {
		Integer toInt;
		if( nextComma(s) < 0) {
			toInt = new Integer (s);
			return toInt.intValue();
		}
		toInt = new Integer(s.substring(0, nextComma(s)));
		return  toInt.intValue();
	}
	/*This method returns -1 if there are no commas and > 0 if it has commas.
	 * By finding the next comma we can determine whether if the ballot is empty,
	 * we can find the next pair of ID:RANK given in the ballot and we can find
	 * the division between the candidate NAME and candidate ID.
	 */ 
	private int nextComma(String s) {
		return s.indexOf(",");
	}
	/*This method helps breaking down the ballot string by finding the candidate ID.*/
	private int breakIntoCandidateID(String s) {
		Integer toInt = new Integer (s.substring(0, 1));
		return toInt.intValue();
	}
	/*This method helps breaking down the ballot string by finding the vote that the candidate received.*/
	private int breakIntoCandidateRank(String s) {
		Integer toInt = new Integer (s.substring(2,3));
		return toInt.intValue();
	}
	/*This method builds a list of the Candidates in the ballot. 
	 * This method has a print, remember to delete.*/ 
	private LinkedList<Ranking> breakIntoCandidates(String s){
		if(nextComma(s) > 0) {
			listToPrint = breakIntoCandidatesHelper(s);
			printList(listToPrint);
			return listToPrint;
		}
		return null;
	}
	/*This method helps to build the list of Candidates recursively.*/ 
	private LinkedList<Ranking> breakIntoCandidatesHelper(String s) {

		
		if(nextComma(s) < 0) {
			ranking = new Ranking(breakIntoCandidateID(s), breakIntoCandidateRank(s));
			listOfRanking.add(ranking);
			return listOfRanking;
		}else {
			ranking = new Ranking(breakIntoCandidateID(s), breakIntoCandidateRank(s));
			listOfRanking.add(ranking);
			return breakIntoCandidatesHelper(s.substring(nextComma(s) + 1,s.length()));
		}
	}
	/*This method builds the candidate list for the people running.*/ 
	public LinkedList<Candidate> candidateListBuilder(File file) throws FileNotFoundException{
		
		LinkedList<Candidate> listOfCandidates = new LinkedList<Candidate>();
		Scanner sc = new Scanner(file);
		String editor;
		Candidate candidate;
		while(sc.hasNextLine()) {
			editor = sc.nextLine();
			candidate = new Candidate(findCandidateName(editor), findCandidateID(editor));
			listOfCandidates.add(candidate);
		}
		printCandidateList(listOfCandidates);
		return listOfCandidates;
	}
	/*This method finds the Name in a candidate file.*/
	private String findCandidateName(String s) {
		return s.substring(0, nextComma(s));
	}
	/*This method finds the ID in a candidate file.*/
	private int findCandidateID(String s) {
		Integer toInt = new Integer(s.substring(nextComma(s)+1, s.length()));
		return toInt.intValue();
	}
	
	
	//These are print methods to test the program, remember to delete.
	private void readBallot(File file) throws FileNotFoundException  {
		Scanner sc = new Scanner(file);

		while(sc.hasNextLine()) {
			System.out.println(sc.nextLine());
		}
	}
	private void printList(LinkedList<Ranking> list) {
		for(Ranking c : list) {
			System.out.println("The candidate ID is: " + c.getCandidateID() + " and the casted vote in this ballot was: " + c.getRank());
		}
	}
	private void printBallotList(LinkedList<Ballot> list) {
		for(Ballot b : list) {
			System.out.println("Ballots ID in this list: " + b.getBallotNum());
		}
	}
	private void printCandidateList(LinkedList<Candidate> list) {
		for(Candidate c : list) {
			System.out.println("Candidate Name: " + c.getCandidateName() + " Candidate ID: "+ c.getCandidateID());
		}
	}
}
