package org.elsys.todo.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.elsys.todo.Criteria;
import org.elsys.todo.Status;
import org.elsys.todo.TodoList;
import org.junit.Before;
import org.junit.Test;

public class TodoListImplTest {

	private TodoList todoList;
	
	@Before
	public void setUp() {
		this.todoList = TodoList.parse(
				"TODO    | Do OOP homework              | Low    | school, programming\n" + 
				"TODO    | Get 8 hours of sleep.        | Low    | health\n" + 
				"DOING   | Party hard.                  | Normal | social\n" + 
				"DONE    | Netflix and chill.           | Normal | tv shows\n" + 
				"TODO    | Find missing socks.          | Low    | meh");
	}
	
	@Test
	public void testIsCompleted() {
		fail("Not yet implemented");
	}

	@Test
	public void testPercentageCompleted() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetTasks() {
		fail("Not yet implemented");
	}

	@Test
	public void testFilter() {
		assertEquals(1,
				todoList.filter(Criteria.status(Status.DOING)).getTasks().size());
		assertEquals(1,
				todoList.filter(Criteria.status(Status.DONE)).getTasks().size());
		assertEquals(3,
				todoList.filter(Criteria.status(Status.TODO)).getTasks().size());
	}

	@Test
	public void testJoin() {
		fail("Not yet implemented");
	}

}
