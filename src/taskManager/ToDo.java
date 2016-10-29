package taskManager;

import java.time.LocalDate;
import java.util.Arrays;

public class ToDo {
	private Task[] tasks;

	public ToDo(Task[] tasks) {
		this.tasks = tasks;
	}

	public String printAll() {
		StringBuilder result = new StringBuilder();
		
		for (int i = 0; i < tasks.length; i++) {
			result.append(tasks[i].printTaskInfo() + "\n\n");
		}
		return result.toString();
	}
	
	public String printInPriority() {
		StringBuilder result = new StringBuilder();

		Task[] sortedTasks = Arrays.copyOf(tasks, tasks.length);
		Arrays.sort(sortedTasks);

		for (int i = 0; i < sortedTasks.length; i++) {
			result.append(sortedTasks[i].printTaskInfo() + "\n\n");
		}

		return result.toString();
	}

	public String printInProcess() {
		StringBuilder result = new StringBuilder();

		Task[] sortedTasks = Arrays.copyOf(tasks, tasks.length);
		Arrays.sort(sortedTasks);

		for (int i = 0; i < sortedTasks.length; i++) {
			if (sortedTasks[i].getStatus() == Status.IN_PROCESS) {
				result.append(sortedTasks[i].printTaskInfo() + "\n\n");
			}
		}
		return result.toString();
	}

	public String printUpcoming(int days) {
		StringBuilder result = new StringBuilder();
		for (int i = 0; i < tasks.length; i++) {
			boolean upcoming = tasks[i].occursInNextDays(days);
			boolean isNotDone = !(tasks[i].isDone());
			
			if (upcoming && isNotDone)
				result.append(tasks[i].printTaskInfo() + "\n\n");
		}
		
		return result.toString();
	}

	public static void main(String args[]) {
		String[] titles = { "Note 1", "Note 2", "Note 3", "Note 4", "Note 5", "Note 6", "Note 7" };

		String[] descriptions = { "Desc 1", "Desc 2", "Desc 3", "Desc 4", "Desc 5", "Desc 6", "Desc 7" };

		Status[] statuses = { Status.INITIAL, Status.DONE, Status.INITIAL, Status.IN_PROCESS, Status.IN_PROCESS,
				Status.DONE, Status.INITIAL };

		int[] priorities = { 1, 4, 2, 5, 3, 1, 4 };

		LocalDate[] deadlines = { LocalDate.of(2016, 04, 20), LocalDate.of(2016, 05, 11), LocalDate.of(2016, 05, 12),
				LocalDate.of(2016, 05, 12), LocalDate.of(2016, 06, 21), LocalDate.of(2016, 02, 24),
				LocalDate.of(2016, 11, 28), };

		Task[] tasks = new Task[7];

		for (int i = 0; i < 7; i++) {
			tasks[i] = new Task(titles[i], descriptions[i], statuses[i], priorities[i], deadlines[i]);
		}

		ToDo toDoList = new ToDo(tasks);
		System.out.println(toDoList.printUpcoming(3));
//		 System.out.println(toDoList.printInPriority());
//		System.out.println(toDoList.printInProcess());
//		System.out.println(toDoList.printAll());

	}

}
