package Main;

import Ballot.Ballot;
import Ballot.Candidate;
import Ballot.RegroupCandidates;
import DataStructures.LinkedList.LinkedList;

public class VotingProcess {
	
	LinkedList<Ballot> ballotList;
	LinkedList<Candidate> candidateList;
	LinkedList<RegroupCandidates> regroupCandidates = new LinkedList<RegroupCandidates>();
	
	
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
		LinkedList<Integer> listOfRanks = new LinkedList<Integer>();
		for(int i = 1; i < getCandidateList().size()+1; i++){
			
				for(Ballot b : ballotList) {
					for(int j = 0; j < b.getCastedVotes().size(); j++) {
						if(b.getCastedVotes().get(j).getCandidateID() == i) {
							listOfRanks.add(b.getCastedVotes().get(j).getRank());
							break;
						}
					}
				}
				//candidateToRegroup = new RegroupCandidates(i,listOfRanks);
				regroupCandidates.add(new RegroupCandidates(i, listOfRanks));
				
		}
	}
	
	private void printRegroup(LinkedList<RegroupCandidates> list) {
		
		for(RegroupCandidates rc : list) {
			System.out.println(rc.getCandidateID());
			printRanks(rc.getListOfRanks());
			System.out.println("\n");
		}
	}
	
	private void printRanks(LinkedList<Integer> list) {
		for(Integer i : list) {
			System.out.println(i);
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
