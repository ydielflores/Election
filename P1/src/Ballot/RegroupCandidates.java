package Ballot;

import DataStructures.LinkedList.LinkedList;

public class RegroupCandidates {
	
		int candidateID;
		LinkedList<Integer> listOfRanks;
		
	public RegroupCandidates(int candidateID, LinkedList<Integer> listOfRanks) {
		this.candidateID = candidateID;
		this.listOfRanks = listOfRanks;
	}
	
	public int getAmountOf(int rank) {
		int count = 0;
		for(int i = 0; i < getListOfRanks().size(); i++) {
			if(getListOfRanks().get(i) == i) {
				count++;
			}
		}
		return count;
	}

	public int getCandidateID() {
		return candidateID;
	}

	public LinkedList<Integer> getListOfRanks() {
		return listOfRanks;
	}
	
	
}
