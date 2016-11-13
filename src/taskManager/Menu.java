package taskManager;

import java.time.LocalDate;
import java.util.Scanner;

public class Menu {
	private static final int THREE_DAYS = 3;
	private ToDo toDoList;
	 static boolean b;

	private String printMainMenu() {
		StringBuilder result = new StringBuilder();
		result.append("\n\n---------------------\n");
		result.append("Choose an option:\n\n");
		result.append("1) Tasks ordered by priority\n");
		result.append("2) Tasks in process\n");
		result.append("3) Tasks in the upcoming 3 days\n");
		result.append("4) Exit\n");
		result.append("\n\nInput a number(1-4):");

		return result.toString();
	}

	private String parseInput(final int input) throws IllegalArgumentException {

		switch (input) {
		case 1:
			return "\n\n" + toDoList.printInPriority() + "\n\n";
		case 2:
			return "\n\n" + toDoList.printInProcess() + "\n\n";
		case 3:
			return "\n\n" + toDoList.printUpcoming(THREE_DAYS) + "\n\n";
		case 4:
			return "";
		default:
			throw new IllegalArgumentException("Illegal argument: " + input);

		}
	}

	public Menu(ToDo toDoList) {
		this.toDoList = toDoList;
	}

	public void start() {
		Scanner input = new Scanner(System.in);
		while (true) {
			System.out.println(printMainMenu());
			int userInput = input.nextInt();

			try {
				String output = parseInput(userInput);
				if (output.length() == 0) {
					input.close();
					return;
				}
				System.out.println(output);
			} catch (IllegalArgumentException e) {
				System.out.println("\n\n\n" + e.getMessage());
			}
		}
	}

	public static void main(String args[]) {
		String[] titles = { "Walk the dog", "Register at the local event", "Write homework", "Learn Java",
				"Watch movies", "Study math", "Phone mom" };

		String[] descriptions = { "Take the dog outside in the park", "There may be free food", "Page 384, ex. 1-7",
				"Threads and Networking", "Doctor Strange or maybe Bourne?", "Cardinals and the axiom of choice", "" };

		Status[] statuses = { Status.INITIAL, Status.DONE, Status.INITIAL, Status.IN_PROCESS, Status.IN_PROCESS,
				Status.DONE, Status.INITIAL };

		int[] priorities = { 1, 4, 2, 5, 3, 1, 4 };

		LocalDate[] deadlines = { LocalDate.of(2016, 04, 20), LocalDate.of(2016, 05, 11), LocalDate.of(2016, 05, 12),
				LocalDate.of(2016, 11, 1), LocalDate.of(2016, 06, 21), LocalDate.of(2016, 10, 30),
				LocalDate.of(2016, 10, 29), };

		Task[] tasks = new Task[7];

		for (int i = 0; i < 7; i++) {
			tasks[i] = new Task(titles[i], descriptions[i], statuses[i], priorities[i], deadlines[i]);
		}

		ToDo toDoList = new ToDo(tasks);
		Menu menu = new Menu(toDoList);
		menu.start();
	}

}
