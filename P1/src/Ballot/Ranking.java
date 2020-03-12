package Ballot;

public class Ranking {
	
	int candidateID, rank;
	
	public Ranking(int candidateID, int rank) {
		this.candidateID = candidateID;
		this.rank = rank;
	}

	public int getCandidateID() {
		return candidateID;
	}

	public void setCandidateID(int candidateID) {
		this.candidateID = candidateID;
	}

	public int getRank() {
		return rank;
	}

	public void setVote(int rank) {
		this.rank = rank;
	}
	
	
}
