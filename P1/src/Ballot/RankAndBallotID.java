package Ballot;

	/**This class creates an object that is able to save the combination
	 * of ballotID and a rank 'x' candidate received in a 'y' ballot;
	 */
public class RankAndBallotID {
	
	int ballotID;
	int rank;
	
	public RankAndBallotID(int ballotID, int rank) {
		this.ballotID = ballotID;
		this.rank = rank;
	}

	public int getBallotID() {
		return ballotID;
	}

	public void setBallotID(int ballotID) {
		this.ballotID = ballotID;
	}

	public int getRank() {
		return rank;
	}

	public void setRank(int rank) {
		this.rank = rank;
	}
	
	
	
	
}
