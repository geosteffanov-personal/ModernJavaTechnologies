package bullsNcowsNaliens.classic;

import java.util.ArrayList;
import java.util.List;

import bullsNcowsNaliens.Guess;

public class ClassicGuess extends Guess<CharRepresentable> {

	public ClassicGuess(String guessString) {
		super(guessString);
	}

	@Override
	protected List<CharRepresentable> parseGuess(String guess) {
		List<CharRepresentable> result = new ArrayList<CharRepresentable>();
		for (int i = 0; i < guess.length(); i++) {
			result.add(new CharRepresentable(guess.charAt(i)));
		}
		return result;
	}
}
