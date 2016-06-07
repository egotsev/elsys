package org.elsys.todo.impl;

import org.elsys.todo.Criteria;
import org.elsys.todo.Status;
import org.elsys.todo.Task;

public class StatusCriteria extends AbstractCriteria implements Criteria {

	private Status status;
	
	public StatusCriteria(Status status) {
		this.status = status;
	}
	
	@Override
	boolean matches(Task task) {
		return status == task.getStatus();
	}

}
