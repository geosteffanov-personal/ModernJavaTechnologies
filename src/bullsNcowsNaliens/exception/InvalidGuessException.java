package bullsNcowsNaliens.exception;

public class InvalidGuessException extends Exception {
	private String expectedGuess;
	private String guess;
	
	public InvalidGuessException(String expectedGuess, String guess) {
		super("invalid format of the guess");
		
		this.expectedGuess = expectedGuess;
		this.guess = guess;
	}
	
	public String toString() {
		StringBuilder result = new StringBuilder();
		result.append("the guess was invalid--\n");
		result.append("expected: " + expectedGuess + '\n');
		result.append("actual: " + guess + '\n');
		
		return result.toString();
	}
}
