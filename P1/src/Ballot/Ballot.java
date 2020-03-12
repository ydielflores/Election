package Ballot;

import java.io.FileNotFoundException;
import DataStructures.LinkedList.LinkedList;



public class Ballot {
	
	int ID;
	LinkedList<Candidate> castedVotes = new LinkedList<Candidate>();
	
	public Ballot(int ID, LinkedList<Candidate> castedVotes) throws FileNotFoundException {
		this.ID = ID;
		this.castedVotes = castedVotes;
	}
	
	public LinkedList<Candidate> getCastedVotes(){
		return this.castedVotes;
	}
	public int getBallotNum() {
		return this.ID;
	}
	
	public int getRankByCandidate(int candidateId) {
		
		for(Candidate c : getCastedVotes()) {
			if(c.getCandidateID() == candidateId) {
				return c.getRank();
			}
		}
		return -1;
	}
	
	public int getCandidateByRank(int rank) {
		for(Candidate c : getCastedVotes()) {
			if(c.getRank() == rank) {
				return c.getCandidateID();
			}
		}
		return -1;
	}
	
	public boolean eliminate(int candidateId) {
		return false; 
	}
	
	
}
