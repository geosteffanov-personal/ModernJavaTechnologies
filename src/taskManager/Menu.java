package taskManager;

import java.time.LocalDate;
import java.util.Scanner;

public class Menu {
	private ToDo toDoList;
	
	public Menu(ToDo toDoList) {
		this.toDoList = toDoList;
	}
	
	private String printMainMenu() {
		StringBuilder result = new StringBuilder();
		result.append("Choose an option:\n\n");
		result.append("1) Tasks ordered by priority\n");
		result.append("2) Tasks in process\n");
		result.append("3) Tasks in the upcoming 3 days\n");
		result.append("4) Exit\n");
		
		return result.toString();
	}
	
	public void start() {
		Scanner input = new Scanner(System.in);
		while(true) {
			System.out.println(printMainMenu() + "\n\n");
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
		
	 private String parseInput(final int input) throws IllegalArgumentException {
			switch(input) {
			case 1:
				return toDoList.printInPriority() + "\n\n\n";
			case 2:
				return toDoList.printInProcess() + "\n\n\n";
			case 3:
				return toDoList.printUpcoming(3) + "\n\n\n";
			case 4:
				return "";
			default:
				throw new IllegalArgumentException("Illegal argument!");
			}
	 }
	
	
	public static void main(String args[]) {
		String[] titles = { "Note 1", "Note 2", "Note 3", "Note 4", "Note 5", "Note 6", "Note 7" };

		String[] descriptions = { "Desc 1", "Desc 2", "Desc 3", "Desc 4", "Desc 5", "Desc 6", "Desc 7" };

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
