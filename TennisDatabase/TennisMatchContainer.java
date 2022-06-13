//cs102 Melody Denby

package TennisDatabase;

import java.util.Iterator;
import java.util.LinkedList;

/*
 * Class containing a linked list that stores all matches of tennis players and related methods
 */
class TennisMatchContainer implements TennisMatchContainerInterface {

	private LinkedList<TennisMatch> tma; // List to store tennis matches

	// Constructor
	public TennisMatchContainer() {
		tma = new LinkedList<TennisMatch>();
	}

	/*
	 * Return number of matches
	 */
	@Override
	public int getNumMatches() {
		return tma.size();
	}

	/*
	 * returns the iterator object
	 */
	@Override
	public Iterator<TennisMatch> iterator() {
		return tma.iterator();
	}

	/*
	 * Inserts the match in list sorted
	 */
	@Override
	public void insertMatch(TennisMatch m) throws TennisDatabaseException {
		if (tma.isEmpty()) {
			tma.add(m);
		} else {
			int index = binaryInsertion(m, tma.size(), 0);
			tma.add(index, m);
		}

	}

	/*
	 * Method that inserts the match in list based on date of match
	 */
	private int binaryInsertion(TennisMatch match, int upperBound, int lowerBound) {
		while (upperBound > lowerBound) {
			int midPoint = (upperBound + lowerBound) / 2;
			int compareResult = tma.get(midPoint).compareTo(match);
			// tma.get(midPoint) > match
			if (compareResult == 1) {
				upperBound = midPoint;
			} else {
				lowerBound = midPoint + 1;
			}
		}
		return lowerBound;
	}

	/*
	 * returns all matches in linked list
	 */
	@Override
	public TennisMatch[] getAllMatches() throws TennisDatabaseRuntimeException {
		TennisMatch[] allMatches = tma.toArray(new TennisMatch[tma.size()]);
		return allMatches;
	}

	// Returns all of the players matches
	@Override
	public TennisMatch[] getMatchesOfPlayer(String playerId) throws TennisDatabaseRuntimeException {
		TennisMatch[] playerMatches = new TennisMatch[tma.size()];
    if (getNumMatches() == 0) { // if no matches
      throw new TennisDatabaseRuntimeException("No Tennis Matches Available");
    } else {
		for (int i = 0; i <= tma.size()-1; i++) {
			if (tma.get(i).getIdPlayer1().equalsIgnoreCase(playerId)) {
				playerMatches[i]=tma.get(i);
			} else if (tma.get(i).getIdPlayer2().equalsIgnoreCase(playerId)) {
				playerMatches[i]=tma.get(i);
			}
       }
    return playerMatches;
    }
  }

	/*
	 * Deletes matches of player that was deleted
	 */
	@Override
	public void deleteMatchesOfPlayer(String playerId) throws TennisDatabaseRuntimeException {
		for (int i = 0; i < tma.size(); i++) {
			if (tma.get(i).getIdPlayer1().equalsIgnoreCase(playerId)) {
				tma.remove(i);
				i--;
			} else if (tma.get(i).getIdPlayer2().equalsIgnoreCase(playerId)) {
				tma.remove(i);
				i--;
			}
		}
	}

}
