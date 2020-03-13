package Main;

import Ballot.Ballot;
import Ballot.Candidate;
import Ballot.RankAndBallotID;
import Ballot.RegroupCandidates;
import DataStructures.LinkedList.LinkedList;

public class VotingProcess {
	
	public LinkedList<Ballot> ballotList;
	public LinkedList<Candidate> candidateList;
	
	
	/**This class should only accept the ballotList already validated.
	 * 
	 */
	public VotingProcess(LinkedList<Ballot> ballotList, LinkedList<Candidate> candidateList) {
		this.ballotList = ballotList;
		this.candidateList = candidateList;
		start();
	}

	public void start() {
		printRegroup(regroupCandidatesMethod(getBallotList()));
	}
	
	private LinkedList<RegroupCandidates> regroupCandidatesMethod(LinkedList<Ballot> ballotList) {
		//RegroupCandidates candidateToRegroup;
		LinkedList<RegroupCandidates> regroupCandidates = new LinkedList<RegroupCandidates>();
		RegroupCandidates candidateIDandVotesReceived;
		RankAndBallotID rankAndBallotID;
		for(int i = 1; i < getCandidateList().size()+1; i++){
			LinkedList<RankAndBallotID> listOfRanksAndBallotID = new LinkedList<RankAndBallotID>();
				for(Ballot b : ballotList) {
					for(int j = 0; j < b.getCastedVotes().size(); j++) {
						if(b.getCastedVotes().get(j).getCandidateID() == i) {
							rankAndBallotID = new RankAndBallotID(b.getBallotNum(), b.getCastedVotes().get(j).getRank());
							listOfRanksAndBallotID.add(rankAndBallotID);
							break;
						}
					}
				}
				//placeHolder = listOfRanksAndBallotID;
				candidateIDandVotesReceived = new RegroupCandidates(i, listOfRanksAndBallotID);
				
				addIntoRegroupCandidates(regroupCandidates, candidateIDandVotesReceived);
				
				
		}
		
		return regroupCandidates;
	}
	
	private void addIntoRegroupCandidates(LinkedList<RegroupCandidates> regroupCandidate, RegroupCandidates candidateIDandVotesReceived) {
		regroupCandidate.add(candidateIDandVotesReceived);
	}
	
	private void printRegroup(LinkedList<RegroupCandidates> list) {
		
		for(RegroupCandidates rc : list) {
			System.out.println(rc.getCandidateID());
			printRanks(rc.getRankAndBallotID());
			System.out.println("\n");
		}
	}
	
	private void printRanks(LinkedList<RankAndBallotID> list) {
		for(RankAndBallotID rABI : list) {
			System.out.println("The ballot for this vote was: " + rABI.getBallotID() + ", And the rank was: " + rABI.getRank());
		}
	}
	
	
	
	
	
	
	
	
	
	
	public LinkedList<Ballot> getBallotList() {
		return ballotList;
	}

	public LinkedList<Candidate> getCandidateList() {
		return candidateList;
	}
	
}
