package Ballot;

import DataStructures.LinkedList.LinkedList;

/**
 * This class creates and object that saves the Candidate ID 
 * and a list that contains all the ranks this candidate
 * received during the campaign.
 *
 */
public class RegroupCandidates {
	
		int candidateID;
		LinkedList<RankAndBallotID> rankAndBallotID;
		
		
	public RegroupCandidates(int candidateID, LinkedList<RankAndBallotID> rankAndBallotID) {
		this.candidateID = candidateID;
		this.rankAndBallotID = rankAndBallotID;
	}
	
	/**This method counts the amount of ranks a candidate received in the entire campaign.
	 * 
	 * @param rank - the rank you wish to count.
	 * @return int - the amount of times the candidate received the rank.
 	 */
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
