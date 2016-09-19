package org.elsys.todo.impl;

import org.elsys.todo.Criteria;
import org.elsys.todo.Priority;
import org.elsys.todo.Task;

public class PriorityCriteria extends AbstractCriteria
		implements Criteria {

	private Priority priority;
	
	public PriorityCriteria(Priority priority) {
		this.priority = priority;
	}
	
	boolean matches(Task task) {
		return priority == task.getPriority();
	}
}
