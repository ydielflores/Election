package Ballot;

public class Candidate {

	String candidateName;
	int candidateID;
	
	public Candidate(String candidateName, int candidateID) {
		this.candidateName = candidateName;
		this.candidateID = candidateID;
	}

	public String getCandidateName() {
		return candidateName;
	}

	public void setCandidateName(String candidateName) {
		this.candidateName = candidateName;
	}

	public int getCandidateID() {
		return candidateID;
	}

	public void setCandidateID(int candidateID) {
		this.candidateID = candidateID;
	}
}
