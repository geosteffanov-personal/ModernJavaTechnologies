package bullsNcowsNaliens.classic;

import java.util.Random;

public class CharRepresentable {
	private Character ch;

	public CharRepresentable() {
		randomize();
	}

	public CharRepresentable(Character ch) {
		this.ch = ch;
	}

	public void randomize() {
		Random randomGenerator = new Random();
//		int random = '!' + randomGenerator.nextInt('~' - '!' + 1);
		int random = randomGenerator.nextInt(10);
		this.ch = (char) (random + 48);
	}

	public void setChar(Character ch) {
		this.ch = ch;
	}

	public Character getChar() {
		return ch;
	}

	@Override
	public boolean equals(Object other) {
		if (!(other instanceof CharRepresentable)) {
			return false;
		}
		return ch.charValue() == ((CharRepresentable) other).getChar().charValue();
	}
	
	@Override
	public int hashCode() {
		return ch.charValue();
	}

	public static void main(String args[]) {
		System.out.println('~' - '!');
	}
	

}
