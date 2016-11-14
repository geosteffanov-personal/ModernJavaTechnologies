package bullsNcowsNaliens.classic;

import bullsNcowsNaliens.Cowtainer;
import bullsNcowsNaliens.GenericFactory;
import bullsNcowsNaliens.Guess;
import bullsNcowsNaliens.GuessResult;

public class ClassicCowtainer extends Cowtainer<CharRepresentable> {

	public ClassicCowtainer(GenericFactory<CharRepresentable> factory, int length) {
		super(factory, length);
	}

	@Override
	public GuessResult<CharRepresentable> checkGuess(Guess<CharRepresentable> guess) {
		return new ClassicResult(guess, correctGuess);
	}

}
