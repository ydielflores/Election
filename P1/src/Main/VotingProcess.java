package Main;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;


import Ballot.Ballot;

import Ballot.Candidate;
import Ballot.RankAndBallotID;
import Ballot.Ranking;
import Ballot.RegroupCandidates;
import DataStructures.LinkedList.LinkedList;



/**
 * Voting Process class starts the voting count. 
 * This class will help determine the winner of the campaign.
 */
public class VotingProcess {

	public LinkedList<Ballot> ballotList;
	public LinkedList<Candidate> candidateList;
	LinkedList<RegroupCandidates> candidatesAndVotes;
	LinkedList<RegroupCandidates> eliminatedList = new LinkedList<RegroupCandidates>();
	LinkedList<RegroupCandidates> openList;
	LinkedList<Integer> ones = new LinkedList<Integer>();
	

	public VotingProcess(LinkedList<Ballot> ballotList, LinkedList<Candidate> candidateList) {
		this.ballotList = ballotList;
		this.candidateList = candidateList;

	}

	public void start(int blanckBallots, int invalidBallots, int totalBallots) throws IOException {
		openList = regroupCandidatesMethod(getBallotList());
		countVote(sort(openList));

		write(eliminatedList,getCandidateList(),blanckBallots,invalidBallots,totalBallots);
		
	}

	/**
	 * The method that writes the result.txt.
	 * @param eliminatedList
	 * @param candidates
	 * @param bB
	 * @param iB
	 * @param tb
	 * @throws IOException 
	 */
	public void write(LinkedList<RegroupCandidates>eliminatedList,LinkedList<Candidate> candidates, int bB, int iB, int tb) throws IOException{

		File file = new File("result.txt");
		FileWriter fw = new FileWriter(file);
		PrintWriter pw = new PrintWriter(fw);

		int rounC = 1;
		int moveL = 0;
		pw.write("Number of Ballots: " + tb);
		pw.write("\n");
		pw.write("Number of Blank Ballots: " + bB);
		pw.write("\n");
		pw.write("Number of Invalid Ballots :" + iB);
		pw.write("\n");

		for(RegroupCandidates rc : eliminatedList) {

			for(Candidate c : candidates) {
				
				if(rc.getCandidateID() == c.getCandidateID()) {
					
					pw.write("In round " + rounC +", "+ c.getCandidateName()+ " was eliminated with " + ones.get(moveL));
					pw.write("\n");
					rounC++;
				}
			}
		}
		
		for(Candidate c : candidates) {
			if(c.getCandidateID() == openList.get(0).getCandidateID()) {
				pw.write("Winner is " + c.getCandidateName() + " with " + openList.get(0).getAmountOf(1));
			}
		}
		
		pw.close();
	}
	/**
	 * This method organizes the candidates into a list with their respective votes and ballot IDs they were voted on. 
	 * @param ballotList
	 * @return LinkedList:RegroupCandidates: - This is a list of all the candidates 
	 * 			paired with their ranks and the ballot IDs they were voted on.
	 */
	private LinkedList<RegroupCandidates> regroupCandidatesMethod(LinkedList<Ballot> ballotList) {
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

			candidateIDandVotesReceived = new RegroupCandidates(i, listOfRanksAndBallotID);

			regroupCandidates.add(candidateIDandVotesReceived);
		}

		return regroupCandidates;
	}


	/**
	 * This method sorts the list to always put the one with less zeros 1's in front.
	 * @param list
	 * @return
	 */
	private LinkedList<RegroupCandidates> sort(LinkedList<RegroupCandidates> list){
		RegroupCandidates temp;
		for(int i = 0; i < list.size(); i++) {
			for(int j = 1; j < list.size() - i; j++) {
				if(list.get(j-1).getAmountOf(1) > list.get(j).getAmountOf(1)){
					temp =  list.get(j-1);
					list.set(j-1, list.get(j));
					list.set(j, temp);
				}
			}
		}
		return list;
	}
	/**
	 * this method starts counting using the grouped candidates with their votes.
	 * @param list
	 */
	private void countVote(LinkedList<RegroupCandidates> list) {
		while(list.size()>1) {
			if(list.get(0).getAmountOf(1) == list.get(1).getAmountOf(1)) {
				int choose = tieBraker(list);
				if(choose == 0) {
					if(list.get(0).getCandidateID() > list.get(1).getCandidateID()) {
						ones.add(list.get(0).getAmountOf(1));
						eliminatedList.add(list.get(0));
						eliminate(ballotList, list.get(0).getCandidateID());
						list.remove(0);
					}else {
						ones.add(list.get(1).getAmountOf(1));
						eliminatedList.add(list.get(1));
						eliminate(ballotList, list.get(1).getCandidateID());
						list.remove(1);
					}
				}
				if(choose > 0) {
					ones.add(list.get(1).getAmountOf(1));
					eliminatedList.add(list.get(1));
					eliminate(ballotList, list.get(1).getCandidateID());
					list.remove(1);
				}else {
					ones.add(list.get(0).getAmountOf(1));
					eliminatedList.add(list.get(0));
					list.remove(0);
				}

			}else if(list.get(0).getAmountOf(1) < list.get(1).getAmountOf(1)){
				ones.add(list.get(0).getAmountOf(1));
				eliminatedList.add(list.get(0));
				list.remove(0);
			}else {
				ones.add(list.get(1).getAmountOf(1));
				eliminatedList.add(list.get(1));
				list.remove(1);
			}
		}
	}

	/**
	 * This method is meant for breaking a tie.
	 */
	private int tieBraker(LinkedList<RegroupCandidates> list) {
		int decider = 0;
		int rankSearcher = 2;
		int tieEnder = 2;
		while(decider == 0) {
			tieEnder++;
			if(tieEnder >= candidateList.size()) {
				return 0;
			}
			if(list.get(0).getAmountOf(rankSearcher) > list.get(1).getAmountOf(rankSearcher)) {
				decider = 1;
			}else if(list.get(0).getAmountOf(rankSearcher) < list.get(1).getAmountOf(rankSearcher)){
				decider = -1;
			}
		}
		return decider;
	}

	/**
	 * This method eliminates one member.
	 */
	private void eliminate(LinkedList<Ballot> ballotList, int candidateID) {
		for(Ballot b : ballotList) {
			for(Ranking c : b.getCastedVotes()) {
				if(c.getCandidateID() == candidateID) {
					if(c.getRank() == 1) {
						update(b, candidateID);
						b.eliminate(candidateID);
					}
				}
			}
		}
	}

	/**
	 * This method updates the ranks in a specific ballot.
	 */
	private void update(Ballot b, int candidateID) {
		
		for(RegroupCandidates r : getOpenList()) {
			for(RankAndBallotID rAbId : r.getRankAndBallotID()) {
				
				if(rAbId.getBallotID() == b.getBallotNum()) {
					rAbId.setRank(rAbId.getRank()-1);
					
				}
			}
		}
	}



	public LinkedList<Ballot> getBallotList() {
		return ballotList;
	}

	public LinkedList<Candidate> getCandidateList() {
		return candidateList;
	}
	public LinkedList<RegroupCandidates> getOpenList(){
		return openList;
	}


}
