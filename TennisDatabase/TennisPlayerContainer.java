// CS102 Melody Denby 

package TennisDatabase;

/*
 * Class containing binary search tree that stores tennis players and related data 
 */
class TennisPlayerContainer implements TennisPlayerContainerInterface {
	private TennisPlayerContainerNode root; // root of tree
	private int numOfPlayers; // Number of players

	/*
	 * Gives number of players in BST
	 */
	 
	public int getNumPlayers() {
		return numOfPlayers;
	}
   public TennisPlayer[] getAllPlayers() throws TennisDatabaseRuntimeException{
      return null;
   }

	/*
	 * returns player object with id == playerId
	 */
	public TennisPlayer getPlayer(String playerId) throws TennisDatabaseRuntimeException {
		return getPlayerRec(root, playerId.toLowerCase());
	}

	/*
	 * Method that accompanies getPlayer and returns player using recursion
	 */
	private TennisPlayer getPlayerRec(TennisPlayerContainerNode currRoot, String playerId) {
		TennisPlayer player = null;
		if (currRoot == null) {
			// returns null
		} else {
			String currRootPlayerID = currRoot.getPlayer().getId().toLowerCase();
			if (playerId.compareTo(currRootPlayerID) == 0) {
				player = currRoot.getPlayer();
			} else if (playerId.compareTo(currRootPlayerID) > 0) {
				player = getPlayerRec(currRoot.getRightChild(), playerId);
			} else {
				player = getPlayerRec(currRoot.getLeftChild(), playerId);
			}
		}
		return player;
	}

	/*
	 * deletes player with id playerId in tree
	 */
	 
	public void deletePlayer(String playerId) throws TennisDatabaseRuntimeException {
		root = deletePlayerRec(root, playerId.toLowerCase());
		numOfPlayers--;
	}

	/*
	 * method that accompanies deletePlayer and deletes player maintaining the BST
	 */
	private TennisPlayerContainerNode deletePlayerRec(TennisPlayerContainerNode currRoot, String playerId) {
		if (currRoot == null) {
			throw new TennisDatabaseRuntimeException("No such item found");
		} else {
			String currRootPlayerID = currRoot.getPlayer().getId().toLowerCase();
			if (playerId.compareTo(currRootPlayerID) > 0) {
				currRoot.setRightChild(deletePlayerRec(currRoot.getRightChild(), playerId));
				return currRoot;
			} else if (playerId.compareTo(currRootPlayerID) < 0) {
				currRoot.setLeftChild(deletePlayerRec(currRoot.getLeftChild(), playerId));
				return currRoot;
			} else {
				return deleteNode(currRoot, playerId); // Returns currRoot with new right subtree.
			}
		}
	}

	/*
	 * Deletes the node and returns the children branch (if it has one)
	 */
	private TennisPlayerContainerNode deleteNode(TennisPlayerContainerNode currRoot, String playerId) {
		if (currRoot.getLeftChild() == null && currRoot.getRightChild() == null) {
			return null;
		} else if (currRoot.getLeftChild() == null) {
			return currRoot.getRightChild();
		} else if (currRoot.getRightChild() == null) {
			return currRoot.getLeftChild();
		} else {
			// Find the inorder successor of currRoot key.
			TennisPlayerContainerNode replacement = getLeftChildMostNode(currRoot.getRightChild());
			TennisPlayerContainerNode replacementItem = deleteLeftMostNode(currRoot.getRightChild());
			currRoot = replacement;
			currRoot.setRightChild(replacementItem);
			return currRoot;
		}
	}

	/*
	 * Returns the node that is the leftmost descendant of the subtree rooted at
	 * node
	 */
	private TennisPlayerContainerNode deleteLeftMostNode(TennisPlayerContainerNode node) {
		if (node.getLeftChild() == null) {
			return node.getRightChild();
		} else {
			TennisPlayerContainerNode replacementOfLeftChild = deleteLeftMostNode(node.getLeftChild());
			node.setLeftChild(replacementOfLeftChild);
			return node;
		}
	}

	/*
	 * Deletes leftmost descendant of treeNode. Returns subtree of deleted node.
	 */
	private TennisPlayerContainerNode getLeftChildMostNode(TennisPlayerContainerNode node) {
		if (node.getLeftChild() == null) {
			return node;
		} else {
			return getLeftChildMostNode(node.getLeftChild());
		}
	}

	/*
	 * Inserts player in tree
	 */
	 
	public void insertPlayer(TennisPlayer player) throws TennisDatabaseException {
		this.root = insertPlayerRec(this.root, player);
		this.numOfPlayers++;
	}

	/*
	 * Inserts tennis player in BST recursively
	 */
	private TennisPlayerContainerNode insertPlayerRec(TennisPlayerContainerNode currRoot, TennisPlayer newPlayer)
			throws TennisDatabaseException {
		if (currRoot == null) {
			TennisPlayerContainerNode newNode = new TennisPlayerContainerNode(newPlayer);
			return newNode;
		} else {
			int compareResult = currRoot.getPlayer().compareTo(newPlayer);
			if (compareResult == 0) {
				throw new TennisDatabaseException("Error: Player already exists!");
			} else if (compareResult > 0) {
				TennisPlayerContainerNode newNode = insertPlayerRec(currRoot.getLeftChild(), newPlayer);
				currRoot.setLeftChild(newNode);
				return currRoot;
			} else {
				TennisPlayerContainerNode newNode = insertPlayerRec(currRoot.getRightChild(), newPlayer);
				currRoot.setRightChild(newNode);
				return currRoot;
			}
		}
	}

	/*
	 * Insert tennis match in sorted linked list in each tennis player
	 */
	 
	public void insertMatch(TennisMatch match) throws TennisDatabaseException {
		String player1Id = match.getIdPlayer1();
		String player2Id = match.getIdPlayer2();
		getBSTNode(root, player1Id.toLowerCase()).insertMatch(match);
		getBSTNode(root, player2Id.toLowerCase()).insertMatch(match);
	}

	/*
	 * Gets the BST node of containing player with id playerId
	 * It is similar to getPlayer but returns node instead of player object
	 */
	private TennisPlayerContainerNode getBSTNode(TennisPlayerContainerNode currNode, String playerId) {
		String currRootPlayerID = currNode.getPlayer().getId().toLowerCase();
		if (currNode == null || playerId.compareTo(currRootPlayerID) == 0) {
			return currNode;
		} else if (playerId.compareTo(currRootPlayerID) > 0) {
			return getBSTNode(currNode.getRightChild(), playerId);
		} else {
			return getBSTNode(currNode.getLeftChild(), playerId);
		}
	}

	/*
	 * Not used/required
	 */
	 
	public TennisMatch[] getMatchesOfPlayer(String playerId)
			throws TennisDatabaseException, TennisDatabaseRuntimeException {
		return null;
	}

	/*
	 * Returns the iterator object of TennisPlayerContainer
	 */
	 
	public TennisPlayerContainerIterator iterator() {
		TennisPlayerContainerIterator tpcIterator = new TennisPlayerContainerIterator(root);
		return tpcIterator;
	} 
   
   //TODO  
   public void deleteMatchesOfPlayer( String playerId ) throws TennisDatabaseRuntimeException{
   
   }
}
