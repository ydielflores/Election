package Main;

import Ballot.Ballot;
import Ballot.Candidate;
import Ballot.RankAndBallotID;
import Ballot.RegroupCandidates;
import DataStructures.LinkedList.LinkedList;

public class VotingProcess {
	
	public LinkedList<Ballot> ballotList;
	public LinkedList<Candidate> candidateList;
	private LinkedList<RegroupCandidates> regroupCandidates = new LinkedList<RegroupCandidates>();
	private LinkedList<RankAndBallotID> listOfRanksAndBallotID = new LinkedList<RankAndBallotID>();
	
	
	public VotingProcess(LinkedList<Ballot> ballotList, LinkedList<Candidate> candidateList) {
		this.ballotList = ballotList;
		this.candidateList = candidateList;
		
		start();
	}

	public void start() {
		regroupCandidates(getBallotList());
		printRegroup(regroupCandidates);
	}
	
	private void regroupCandidates(LinkedList<Ballot> ballotList) {
		//RegroupCandidates candidateToRegroup;
		
		RankAndBallotID rankAndBallotID;
		for(int i = 1; i < getCandidateList().size()+1; i++){
			listOfRanksAndBallotID.clear();
				for(Ballot b : ballotList) {
					for(int j = 0; j < b.getCastedVotes().size(); j++) {
						if(b.getCastedVotes().get(j).getCandidateID() == i) {
							rankAndBallotID = new RankAndBallotID(b.getBallotNum(), b.getCastedVotes().get(j).getRank());
							listOfRanksAndBallotID.add(rankAndBallotID);
							break;
						}
					}
				}
				regroupCandidates.add(new RegroupCandidates(i, listOfRanksAndBallotID));
				
		}
	}
	
	private void addToListOfRanksAndBallotID(RankAndBallotID obj) {
		
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
	
	
	
	
	
	
	
	
	
	public LinkedList<RegroupCandidates> getRegroupCandidates(){
		return regroupCandidates;
	}
	
	public LinkedList<Ballot> getBallotList() {
		return ballotList;
	}

	public LinkedList<Candidate> getCandidateList() {
		return candidateList;
	}
	
}
