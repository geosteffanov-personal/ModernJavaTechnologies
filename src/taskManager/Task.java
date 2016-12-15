package taskManager;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

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

	public Task(String[] parameters) {
		this.title = parameters[0];
		this.description = parameters[1];
		this.status = Status.valueOf(parameters[2]);
		this.priority = Integer.parseInt(parameters[3]);
	    this.deadline = LocalDate.parse(parameters[4], DateTimeFormatter.ofPattern("yyyy-MM-dd"));
		
	}

	
	@Override
	public int compareTo(Task task) {
		if (priority > task.priority)
			return 1;
		if (priority < task.priority)
			return -1;
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
			if (deadline.equals(LocalDate.now().plusDays(i)))
				return true;
		}
		return false;
	}

	public boolean isDone() {
		return status == Status.DONE;
	}

	public String formatCSV() {
		String[] parameters = {
								title,
								description,
								status.toString(),
								Integer.toString(priority),
								deadline.toString()
							};
		return String.join(",", parameters);
	}
	
	public String toString() {
		StringBuilder result = new StringBuilder();
		result.append(title).append(',').append(description).append(',')
							.append(status).append(',').append(priority).append(',').append(deadline);
		return result.toString();
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
}
