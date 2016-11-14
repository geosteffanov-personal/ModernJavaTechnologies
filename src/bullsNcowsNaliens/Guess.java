package bullsNcowsNaliens;

import java.util.List;

import bullsNcowsNaliens.exception.InvalidGuessException;

public abstract class Guess<T> {
	
	protected List<T> guess;
	protected int guessSize;
	protected String guessString;
	
	protected abstract List<T> parseGuess(String guess);
	
	public Guess(String guessString) {
		this.guessString = guessString;
		
		guess = parseGuess(guessString);

		guessSize = guess.size();
	}
	
	public int getSize() {
		return guessSize;
	}
	
	public T get(int index) {
		return guess.get(index);
	}
}
