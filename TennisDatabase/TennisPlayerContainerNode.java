//Melody Denby 

package TennisDatabase;

/*
 * Class for implementing nodes in TennisPlayerContainer Binary Search Tree
 */
class TennisPlayerContainerNode implements TennisPlayerContainerNodeInterface {

	private TennisPlayerContainerNode right; // Right Child
	private TennisPlayerContainerNode left; // Left Child
	private TennisPlayer player; // Tennis Player
	private SortedLinkedList<TennisMatch> playerMatches; // SortedLinkedList containing all tennis matches

	// Constructor
	public TennisPlayerContainerNode(TennisPlayer inputPlayer) {
		this.left = null;
		this.right = null;
		this.player = inputPlayer;
		this.playerMatches = new SortedLinkedList<TennisMatch>();
	}
   
   
   public SortedLinkedList<TennisMatch> getMatchList(){
      return playerMatches;
   }
   
   public void setMatchList( SortedLinkedList<TennisMatch> ml ){
      playerMatches=ml;
   }

	/*
	 * returns right node
	 */
	public TennisPlayerContainerNode getRightChild() {
		return right;
	}

	/*
	 * sets right node
	 */
	public void setRightChild(TennisPlayerContainerNode right) {
		this.right = right;
	}

	/*
	 * returns left node
	 */
	public TennisPlayerContainerNode getLeftChild() {
		return left;
	}

	/*
	 * sets left node
	 */
	public void setLeftChild(TennisPlayerContainerNode left) {
		this.left = left;
	}

	/*
	 * returns the player
	 */
	public TennisPlayer getPlayer() {
		return player;
	}

	/*
	 * sets the player
	 */
	public void setPlayer(TennisPlayer player) {
		this.player = player;
	}

	/*
	 * Inserts TennisMatch in playerMatches SortedLinkedList
	 */
	@Override
	public void insertMatch(TennisMatch m) throws TennisDatabaseException {
		try {
			playerMatches.insert(m);
		} catch (Exception e) {
		}
	}

	/*
	 * Gets all matches from playerMatches SortedLinkedList
	 */
	@Override
	public TennisMatch[] getMatches() throws TennisDatabaseRuntimeException {
		// TODO Auto-generated method stub
		TennisMatch[] allMatches = new TennisMatch[playerMatches.size()];
		for (int i = 0; i < allMatches.length; i++) {
			allMatches[i] = playerMatches.get(i);
		}
		return allMatches;
	}
   public void deleteMatchesOfPlayer( String playerId ) throws TennisDatabaseRuntimeException{
   
   }

}
