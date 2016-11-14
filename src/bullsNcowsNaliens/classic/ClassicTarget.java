package bullsNcowsNaliens.classic;

import bullsNcowsNaliens.Target;

public class ClassicTarget extends Target<CharRepresentable> {

	public String getString() {
		StringBuilder result = new StringBuilder();
		for (int i = 0; i < targetSize; i++) {
			result.append(target.get(i).getChar());
		}
		return result.toString();
	}
}
