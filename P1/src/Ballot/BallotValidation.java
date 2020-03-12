package Ballot;

import DataStructures.LinkedList.LinkedList;

public class BallotValidation {
	
	int amountOfInvalidBallots = 0, totalBallots = 0;
	LinkedList<Ballot> ballotList = new LinkedList<Ballot>();
	LinkedList<Candidate> candidateList = new LinkedList<Candidate>();
	
	public BallotValidation(LinkedList<Ballot> ballotList,LinkedList<Candidate> candidateList) {
		this.ballotList   = ballotList;
		this.totalBallots = ballotList.size(); 
		this.candidateList = candidateList;
	}
	/*ranking repetido, votar por el mismo candidato mas de una vez, 
	 * que el ranking mayor sea mayor que la cantidad de candidatos,
	 * el ranking no puede ser menor que 1, 
	 * el size del ballo te dice hasta donde llega el ranking
	 */
	public void validation(LinkedList<Ballot> list) {
		
	}
//	public void 
//	public void validationRepeatedCandidateAndRepeatedRank(LinkedList<Ballot> list) {
//		for(Ballot b : list) {
//			for(int i = 0; i < b.getCastedVotes().size(); i++) {
//					for(int j = 0; j < b.getCastedVotes().size(); j++) {
//						if(j!=i) {
//							if(b.getCastedVotes().get(i).getCandidateID() == b.getCastedVotes().get(j).getCandidateID() || ) {
//								
//							}
//						}
//				}
//			}
//		}
//	}
//	private void validationBy
	
	
	
	
	
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
	
}
