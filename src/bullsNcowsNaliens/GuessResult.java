package bullsNcowsNaliens;

public class GuessResult<T> {
	protected Guess<T> guess;
	protected Target<T> target;
	
	public GuessResult(Guess<T> guess, Target<T> target) {
		this.guess = guess;
		this.target = target;
	}
}
