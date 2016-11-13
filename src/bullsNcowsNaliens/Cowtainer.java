package bullsNcowsNaliens;

import java.util.List;

import bullsNcowsNaliens.exception.InvalidGuessException;

public class Cowtainer<T> {
	private Target<T> correctGuess;
	private GenericFactory<T> factory;
	private int guessLength;
	
	private void generateRandom(int length) {
		correctGuess = factory.generateTarget(length);
	}

	private boolean checkGuess(Guess<T> guess) {
		return false;
	}
	
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
