package org.elsys.todo.impl;

import org.elsys.todo.Criteria;
import org.elsys.todo.Task;

public class NotCriteria extends AbstractCriteria implements Criteria {

	private AbstractCriteria criteria;
	
	public NotCriteria(AbstractCriteria abstractCriteria) {
		this.criteria = abstractCriteria;
	}

	@Override
	boolean matches(Task task) {
		return !criteria.matches(task);
	}

}
