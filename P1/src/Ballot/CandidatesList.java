package Ballot;

import DataStructures.LinkedList.LinkedList;
/**
 * 
 *This class saves Candidates.
 *
 */
public class CandidatesList {
	
	LinkedList<String> candidateList = new LinkedList<String>();
	
	public CandidatesList(LinkedList<String> candidatesList) {
		this.candidateList = candidatesList;
	}

	public LinkedList<String> getCandidateList() {
		return candidateList;
	}

	public void setCandidateList(LinkedList<String> candidateList) {
		this.candidateList = candidateList;
	}
}
