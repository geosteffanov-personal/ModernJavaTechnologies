package taskManager;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class ToDo {
	private List<Task> tasks;

	public ToDo() {
		tasks = new ArrayList<Task>();
	}
	
	public ToDo(List<Task> tasks) {
		this.tasks = tasks;
	}

	public String printAll() {
		StringBuilder result = new StringBuilder();

		for (int i = 0; i < tasks.size(); i++) {
			result.append(tasks.get(i).printTaskInfo() + "\n\n");
		}
		return result.toString();
	}

	public String printInPriority() {
		StringBuilder result = new StringBuilder();

		ArrayList<Task> sortedTasks = new ArrayList<Task>(tasks);
		Collections.sort(sortedTasks);

		for (int i = 0; i < sortedTasks.size(); i++) {
			result.append(sortedTasks.get(i).printTaskInfo() + "\n\n");
		}

		return result.toString();
	}

	public String printInProcess() {
		StringBuilder result = new StringBuilder();

		ArrayList<Task> sortedTasks = new ArrayList<Task>(tasks);
		Collections.sort(sortedTasks);

		for (int i = 0; i < sortedTasks.size(); i++) {
			if (sortedTasks.get(i).getStatus() == Status.IN_PROCESS) {
				result.append(sortedTasks.get(i).printTaskInfo() + "\n\n");
			}
		}
		return result.toString();
	}

	public String printUpcoming(int days) {
		StringBuilder result = new StringBuilder();
		for (int i = 0; i < tasks.size(); i++) {
			boolean upcoming = tasks.get(i).occursInNextDays(days);
			boolean isNotDone = !(tasks.get(i).isDone());

			if (upcoming && isNotDone)
				result.append(tasks.get(i).printTaskInfo() + "\n\n");
		}

		return result.toString();
	}

	public void importFromFile(File file) {
		tasks = new ArrayList<Task>();
		try(BufferedReader importer = new BufferedReader(new FileReader(file))) {
			String line;
			while((line = importer.readLine()) != null) {
				String[] parameters = line.split(",");
				Task newTask = new Task(parameters);
				tasks.add(newTask);
			}
		} catch (IOException e) {
			System.out.println("IO EXPCETION");
			e.printStackTrace();
		}
	}
	
	public void exportToFile(File file) {
		try(BufferedWriter importer = new BufferedWriter(new FileWriter(file))) {
			for(Task task : tasks) {
				importer.write(task.formatCSV());
				importer.newLine();
			}
		} catch (IOException e) {
			System.out.println("IO EXPCETION");
			e.printStackTrace();
		}
	}

	public int taskNumber() {
		return tasks.size();
	}

}
