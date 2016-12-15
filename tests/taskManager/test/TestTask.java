package taskManager.test;

import static org.junit.Assert.assertEquals;

import java.time.LocalDate;

import org.junit.Before;
import org.junit.Test;

import taskManager.Status;
import taskManager.Task;

public class TestTask {
	Task task;
	@Before
	public void setUp() {
		task = new Task("Finish impl","some description",Status.DONE,3,LocalDate.of(2016, 12, 3));
	}

	@Test
	public void testToString() {
		String expectedString = "Finish impl,some description,DONE,3,2016-12-03";
		assertEquals(expectedString, task.toString());
	}
	
	@Test
	public void compareTo() {
		Task secondTask = new Task("Buy cake","chocolate",Status.IN_PROCESS,1,LocalDate.of(2017,01,01));
		assertEquals(1, task.compareTo(secondTask));
	}
}
