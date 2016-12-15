package bullsNcowsNaliens.classic;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import bullsNcowsNaliens.Guess;
import bullsNcowsNaliens.GuessResult;
import bullsNcowsNaliens.Target;

public class ClassicResult extends GuessResult<CharRepresentable> {
	private int cows;
	private int bulls;

	private void setCowsAndBulls() {
		cows = 0;
		bulls = 0;

		Map<CharRepresentable, Integer> targetArity = new HashMap<CharRepresentable, Integer>();
		Map<CharRepresentable, Integer> guessArity = new HashMap<CharRepresentable, Integer>();

		for (int i = 0; i < target.getSize(); i++) {
			CharRepresentable current = target.get(i);
			if (targetArity.containsKey(current)) {
				Integer currentValue = targetArity.get(current);
				targetArity.replace(current, currentValue, currentValue + 1);
			} else {
				targetArity.put(current, 1);
			}
		}

		for (int i = 0; i < guess.getSize(); i++) {
			CharRepresentable current = guess.get(i);
			if (guessArity.containsKey(current)) {
				Integer currentValue = guessArity.get(current);
				guessArity.replace(current, currentValue, currentValue + 1);
			} else {
				guessArity.put(current, 1);
			}
		}

		Set<CharRepresentable> keys = targetArity.keySet();
		Iterator<CharRepresentable> keyIterator = keys.iterator();

		while (keyIterator.hasNext()) {
			CharRepresentable current = keyIterator.next();

			int targetArityOccurences = targetArity.get(current);
			int guessArityOccurences = 0;

			if (guessArity.get(current) != null) {
				guessArityOccurences = guessArity.get(current);
			}

			cows += Math.min(targetArityOccurences, guessArityOccurences);
		}

		for (int i = 0; i < target.getSize(); i++) {
			if (target.get(i).equals(guess.get(i))) {
				bulls++;
			}
		}

		cows = cows - bulls;
	}

	public ClassicResult(Guess<CharRepresentable> guess, Target<CharRepresentable> target) {
		super(guess, target);
		setCowsAndBulls();
	}

	public String guessResult() {
		StringBuilder result = new StringBuilder();
		result.append("cows: " + cows + ", bulls: " + bulls);
		return result.toString();
	}

	public boolean hasGuessedCorrectly() {
		return (bulls == target.getSize());
	}
}
