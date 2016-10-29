package taskManager;

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

}
