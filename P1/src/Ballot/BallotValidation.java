package Ballot;

import DataStructures.LinkedList.LinkedList;

/**
 * BallotValidation receives two lists, one of them is the candidateList 
 * and the other is the ballotList. The BallotValidation class validates every
 * every ballot in the ballot list and removes the ones that are invalid. 
 * 
 */
public class BallotValidation {

	int amountOfInvalidBallots = 0, totalBallots = 0, blanckBallots = 0;
	LinkedList<Ballot> ballotList = new LinkedList<Ballot>();
	LinkedList<Candidate> candidateList = new LinkedList<Candidate>();
	LinkedList<Ballot> ballotsToRemove = new LinkedList<Ballot>();
	

	
	public BallotValidation(LinkedList<Ballot> ballotList,LinkedList<Candidate> candidateList) {
		this.ballotList   = ballotList;
		this.totalBallots = ballotList.size(); 
		this.candidateList = candidateList;
		
		validation();
	}
	
	//This is where validation starts.
	private LinkedList<Ballot> validation() {
		validateDuplicateCandidatesAndDuplicateRanks();
		remove();
		validateEmptyAndAmountOfVotes();
		remove();
		validateSkippedRank();
		remove();
		
		return getBallotList();
	}

	



	/**
	 * This method checks for repeated candidates and ranks.
	 */
	private void validateDuplicateCandidatesAndDuplicateRanks(){
		for(Ballot b : getBallotList()) {
			for(int i = 0; i < b.getCastedVotes().size(); i++) {
				for(int j = 0; j < b.getCastedVotes().size(); j++) {
					if(i!=j) {
						if(b.getCastedVotes().get(i).getCandidateID() == b.getCastedVotes().get(j).getCandidateID() || b.getCastedVotes().get(i).getRank() == b.getCastedVotes().get(j).getRank()) {
							if(!ballotsToRemove.contains(b)) {
								ballotsToRemove.add(b);
							}

						}
					}
				}
			}
		}
	}
	/**
	 * This method checks for empty ballots and if the ballot contains more votes than candidates. AKA more votes than the ones allowed
	 */
	private void validateEmptyAndAmountOfVotes(){

		//With this first loop I tackle the ballots where no ranking was casted AKA an empty ballot.
		for(Ballot b : getBallotList()) {
			if(b.getCastedVotes().size() == 0) {
				getBallotList().remove(b);
				blanckBallots++;
				continue;
				/*With this if, if the size of the casted votes is bigger than the size of the list of 
				 * candidates then this means that the voter voted for more candidates than the ones that are available
				 */	

			}else if(b.getCastedVotes().size() > getCandidateList().size()) {
				ballotsToRemove.add(b);
				continue;
			}
		}
	}
	/*
	 * This method looks for skipped ranks.
	 */
	private void validateSkippedRank() {
		int rankOrderCheck = 0;
		for(Ballot b : getBallotList()) {

			for(int i = 0; i < b.getCastedVotes().size(); i++) {
				rankOrderCheck++;
				if(b.getCastedVotes().get(i).getRank() != rankOrderCheck) {
					ballotsToRemove.add(b);
					break;
				}
			}
			rankOrderCheck = 0;

		}
	}

	//The method that removes invalid ballots and counts them.
	private void remove() {

		for(Ballot b : ballotsToRemove) {
			getBallotList().remove(b);
			amountOfInvalidBallots++;
		}
		ballotsToRemove.clear();
	}

	/*This next method gets you the candidateList*/
	public LinkedList<Candidate> getCandidateList() {
		return candidateList;
	}
	/*This method lets you set the candidateList*/
	public void setCandidateList(LinkedList<Candidate> candidateList) {
		this.candidateList = candidateList;
	}
	/*This method gets you the amount of total ballots in this batch.*/
	public int getTotalBallots() {
		return totalBallots;
	}
	/*This method gets you the amount of invalid ballots in this batch.*/
	public int getAmountOfInvalidBallots() {
		return amountOfInvalidBallots;
	}
	/*This method returns all the ballots in this batch.*/
	public LinkedList<Ballot> getBallotList() {
		return ballotList;
	}
	/*In case of need, you can also change the ballot batch with this method.*/
	public void setBallotList(LinkedList<Ballot> ballotList) {
		this.ballotList = ballotList;
	}
	public int getBlanckBallots() {
		return blanckBallots;
	}
}
