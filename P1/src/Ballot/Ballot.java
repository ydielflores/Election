package Ballot;

import java.io.FileNotFoundException;
import DataStructures.LinkedList.LinkedList;



public class Ballot {
	
	String ballot;
	int ID;
	LinkedList<Candidate> castedVotes = new LinkedList<Candidate>();
	
	public Ballot(int ID, LinkedList<Candidate> castedVotes) throws FileNotFoundException {
		this.ID = ID;
		this.castedVotes = castedVotes;
		
		
	}
	
	public void printList(LinkedList<String> list) {
		
		for(String b : list) {
			System.out.println(b);
		}
	}
	
	
	public int getBallotNum() {
		return this.ID;
	}
	
	public int getRankByCandidate(int candidateId) {
		return 0;
	}
	
	public int getCandidateByRank(int rank) {
		return 0;
	}
	
	public boolean eliminate(int candidateId) {
		return false; 
	}
	
}
