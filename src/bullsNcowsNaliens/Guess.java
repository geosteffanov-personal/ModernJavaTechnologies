package bullsNcowsNaliens;

import java.util.List;

import bullsNcowsNaliens.exception.InvalidGuessException;

public abstract class Guess<T> {
	
	private List<T> guess;
	private int guessSize;
	private String guessString;
	
	protected abstract List<T> parseGuess(String guess) throws InvalidGuessException;
	
	public Guess(String guessString) throws InvalidGuessException {
		this.guessString = guessString;
		guess = parseGuess(guessString);
		guessSize = guess.size();
	}
	
	
}
