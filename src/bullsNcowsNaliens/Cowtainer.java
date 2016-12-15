package bullsNcowsNaliens;


public abstract class Cowtainer<T> {
	protected Target<T> correctGuess;
	
	protected GenericFactory<T> factory;
	
	protected int guessLength;
	
	
	private void generateRandom(int length) {
		correctGuess = factory.generateTarget(length);
	}

	public abstract GuessResult<T> checkGuess(Guess<T> guess);
	
	public Cowtainer(GenericFactory<T> factory, int length){
		this.factory = factory;
		generateRandom(length);
		guessLength = length;
	}
}
