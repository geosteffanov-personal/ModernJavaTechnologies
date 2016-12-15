package taskManager;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.zip.ZipException;
import java.util.zip.ZipFile;
import java.util.zip.ZipOutputStream;

import javax.naming.directory.BasicAttribute;
import javax.naming.directory.BasicAttributes;

public class Menu {
	private static final int THREE_DAYS = 3;
	private ToDo toDoList;
	static boolean b;
	
	private void importFile() {
		System.out.println(printImportMenu());
		Scanner input = new Scanner(System.in);
		String path = input.nextLine();	
		
		File file = new File("tests/taskManager/" + path);
		toDoList.importFromFile(file);
	}

	private void exportFile() {
		System.out.println(printExportMenu());
		Scanner input = new Scanner(System.in);
		String path = input.nextLine();
		
		File file = new File("tests/taskManager/" + path);
		if (file.exists() && !file.isDirectory()) {
			file = new File(path + "_copy");
		}
		
		toDoList.exportToFile(file);
	}
	 
	private String printMainMenu() {
		StringBuilder result = new StringBuilder();
		result.append("\n\n---------------------\n");
		result.append("Choose an option:\n\n");
		result.append("1) Tasks ordered by priority\n");
		result.append("2) Tasks in process\n");
		result.append("3) Tasks in the upcoming 3 days\n");
		result.append("4) Import file: \n");
		result.append("5) Export file: \n");
		result.append("6) Exit\n");
		result.append("\n\nInput a number(1-6):");

		return result.toString();
	}

	private String printImportMenu() {
		return "Enter the full pathname to the file you wish to import\n\n";
	}
	
	private String printExportMenu() {
		return "Enter the full pathname to the file you wish to export to\n\n";
	}
	
	private void archiveFile(File file) {
		
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
			importFile();
			return "#noaction";
		case 5:
			exportFile();
			return "#noaction";
		case 6:
			return "#exit";
		default:
			throw new IllegalArgumentException("Illegal argument: " + input);

		}
	}

	public Menu(ToDo toDoList) {
		this.toDoList = toDoList;
		File backupDir = new File("tests/taskManager/Backup");
		if (!backupDir.exists()) {
			backupDir.mkdir();
		}
	}

	public void start() {
		Scanner input = new Scanner(System.in);
		while (true) {
			System.out.println(printMainMenu());
			int userInput = input.nextInt();

			try {
				String output = parseInput(userInput);
				if (output.equals("#exit")) {
					input.close();
					return;
				}
				if (!output.equals("#noaction")) {
					System.out.println(output);
				}
			} catch (IllegalArgumentException e) {
				System.out.println("\n\n\n" + e.getMessage());
			}
		}
	}

	public static void main(String args[]) {
		ToDo toDoList = new ToDo();
		Menu menu = new Menu(toDoList);
		menu.start();
	}

}
