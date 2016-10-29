package taskManager;

import java.time.LocalDate;

public class Task implements Comparable<Task> {
	private String title;
	private String description;
	private Status status;
	private int priority;
	private LocalDate deadline;

	public Task(String title, String description, Status status, int priority, LocalDate deadline) {
		this.title = title;
		this.description = description;
		this.status = status;
		this.priority = priority;
		this.deadline = deadline;
	}

	@Override
	public int compareTo(Task task) {
		if (priority > task.priority)
			return 1;
		if (priority < task.priority)
			return 	-1;
		return 0;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public int getPriority() {
		return priority;
	}

	public void setPriority(int priority) {
		this.priority = priority;
	}

	public LocalDate getDeadline() {
		return deadline;
	}

	public void setDeadline(LocalDate deadline) {
		this.deadline = deadline;
	}

	public boolean occursOnDate(LocalDate date) {
		if (this.deadline.equals(date))
			return true;
		return false;
	}
	
	public boolean occursInNextDays(int days) {
		for (int i = 0; i <= days; i++) {
			if(deadline.equals(LocalDate.now().plusDays(i)))
				return true;
		}
		return false;
	}
	
	public boolean isDone() {
		return status == Status.DONE;
	}
	
	public String printTaskInfo() {
		StringBuilder result = new StringBuilder();
		result.append(title);
		result.append("\n\n");
		result.append("Status: " + status);
		result.append("\nPriority: " + priority);
		result.append("\nDeadline: " + deadline);
		
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
		
		for (int i = 0; i < 7 ; i++) {
			System.out.println(tasks[i].occursInNextDays(3));
		}

	}
}
