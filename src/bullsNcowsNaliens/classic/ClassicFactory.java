package bullsNcowsNaliens.classic;

import bullsNcowsNaliens.GenericFactory;
import bullsNcowsNaliens.Target;

public class ClassicFactory extends GenericFactory<CharRepresentable> {
	@Override
	public String guessTemplate() {
		return "<a single character>^(guess_length)";
	}

	@Override
	public CharRepresentable generateRandom() {
		CharRepresentable random = new CharRepresentable();
		return random;
	}

	@Override
	public Target<CharRepresentable> generateTarget(int length) {
		Target<CharRepresentable> result = new ClassicTarget();
		for (int i = 0; i < length; i++) {
			result.addElement(generateRandom());
		}

		return result;
	}
}
