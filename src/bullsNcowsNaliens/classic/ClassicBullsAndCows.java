package bullsNcowsNaliens.classic;

import java.util.Scanner;

import bullsNcowsNaliens.Cowtainer;
import bullsNcowsNaliens.GenericFactory;

public class ClassicBullsAndCows {

	public static void main(String args[]) {
		GenericFactory<CharRepresentable> testFactory = new ClassicFactory();
		Cowtainer testCowtainer = new ClassicCowtainer(testFactory, 4);

		Scanner scanner = new Scanner(System.in);
		while (true) {
			System.out.println("Enter number: ");
			String guessString = scanner.nextLine();
			ClassicGuess guess = new ClassicGuess(guessString);
			ClassicResult result = (ClassicResult) testCowtainer.checkGuess(guess);
			if (result.hasGuessedCorrectly()) {
				System.out.println("You win!");
				break;
			} else {
				System.out.println(result.guessResult() + "\n");
			}
		}
		scanner.close();
	}
}
