package Ballot;

import java.io.FileNotFoundException;
import DataStructures.LinkedList.LinkedList;



public class Ballot {
	
	int ID;
	LinkedList<Ranking> castedVotes = new LinkedList<Ranking>();
	
	public Ballot(int ID, LinkedList<Ranking> castedVotes) throws FileNotFoundException {
		this.ID = ID;
		this.castedVotes = castedVotes;
	}
	
	public LinkedList<Ranking> getCastedVotes(){
		return this.castedVotes;
	}
	public int getBallotNum() {
		return this.ID;
	}
	
	public int getRankByCandidate(int candidateId) {
		
		for(Ranking c : getCastedVotes()) {
			if(c.getCandidateID() == candidateId) {
				return c.getRank();
			}
		}
		return -1;
	}
	
	public int getCandidateByRank(int rank) {
		for(Ranking c : getCastedVotes()) {
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
