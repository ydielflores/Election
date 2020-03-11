package Ballot;

public class Candidate {
	
	int candidateID, vote;
	
	public Candidate(int candidateID, int vote) {
		this.candidateID = candidateID;
		this.vote = vote;
	}

	public int getCandidateID() {
		return candidateID;
	}

	public void setCandidateID(int candidateID) {
		this.candidateID = candidateID;
	}

	public int getVote() {
		return vote;
	}

	public void setVote(int vote) {
		this.vote = vote;
	}
	
	
}
