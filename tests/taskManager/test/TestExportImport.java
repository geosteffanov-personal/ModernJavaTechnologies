package taskManager.test;

import static org.junit.Assert.assertEquals;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import taskManager.Task;
import taskManager.ToDo;


public class TestExportImport {
	private static final String FILE_PATH =  "tests/taskManager/files/tasks.txt";
	ToDo toDoList;
	
	@Before
	public void setUp() {
		toDoList = new ToDo();
	}
	
	@Test
	public void testImport() {
		toDoList.importFromFile(new File(FILE_PATH));
		assertEquals(toDoList.taskNumber(), 7);
		
		List<Task> tasks = new ArrayList<Task>(); 
		try (BufferedReader fileReader = new BufferedReader(new FileReader(FILE_PATH))) {
			String line;
			while ((line = fileReader.readLine()) != null) {
				String[] parameters = line.split(",");
				Task newTask = new Task(parameters);
				tasks.add(newTask);
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		ToDo expectedToDo = new ToDo(tasks);
		
		assertEquals(toDoList.printInPriority(), expectedToDo.printInPriority());
		assertEquals(toDoList.printInProcess(), expectedToDo.printInProcess());
		assertEquals(toDoList.printUpcoming(3), expectedToDo.printUpcoming(3));
	}
	
	@Test
	public void testExport() {
		List<Task> tasks = new ArrayList<Task>(); 
		try (BufferedReader fileReader = new BufferedReader(new FileReader(FILE_PATH))) {
			String line;
			while ((line = fileReader.readLine()) != null) {
				String[] parameters = line.split(",");
				Task newTask = new Task(parameters);
				tasks.add(newTask);
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		ToDo otherToDo = new ToDo(tasks);
		toDoList.importFromFile(new File(FILE_PATH));
		
		otherToDo.exportToFile(new File("tests/taskManager/files/EXPORT1TEST"));
		toDoList.exportToFile(new File("tests/taskManager/files/EXPORT2TEST"));
		
		List<Task> firstFile = new ArrayList<Task>();
		List<Task> secondFile = new ArrayList<Task>();
		
		try (BufferedReader fileReader = new BufferedReader(new FileReader("tests/taskManager/files/EXPORT1TEST"))) {
			String line;
			while ((line = fileReader.readLine()) != null) {
				String[] parameters = line.split(",");
				Task newTask = new Task(parameters);
				firstFile.add(newTask);
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try (BufferedReader fileReader = new BufferedReader(new FileReader("tests/taskManager/files/EXPORT2TEST"))) {
			String line;
			while ((line = fileReader.readLine()) != null) {
				String[] parameters = line.split(",");
				Task newTask = new Task(parameters);
				secondFile.add(newTask);
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		assertEquals(firstFile.size(), secondFile.size());
		for (int i = 0; i < firstFile.size(); i++) {
			assertEquals(firstFile.get(i), secondFile.get(i));
		}
	}
}
