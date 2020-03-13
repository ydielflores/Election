package Ballot;

import DataStructures.LinkedList.LinkedList;

public class RegroupCandidates {
	
		int candidateID;
		LinkedList<RankAndBallotID> rankAndBallotID;
		
		
	public RegroupCandidates(int candidateID, LinkedList<RankAndBallotID> rankAndBallotID) {
		this.candidateID = candidateID;
		this.rankAndBallotID = rankAndBallotID;
	}
	
	public int getAmountOf(int rank) {
		int count = 0;
		for(int i = 0; i < getRankAndBallotID().size(); i++) {
			if(getRankAndBallotID().get(i).getRank() == rank) {
				count++;
			}
		}
		return count;
	}

	public int getCandidateID() {
		return candidateID;
	}

	public LinkedList<RankAndBallotID> getRankAndBallotID() {
		return rankAndBallotID;
	}
	
	
}
