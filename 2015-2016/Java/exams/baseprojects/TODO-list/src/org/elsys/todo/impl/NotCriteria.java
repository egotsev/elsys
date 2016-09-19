package org.elsys.todo.impl;

import org.elsys.todo.Criteria;
import org.elsys.todo.Task;

class NotCriteria extends AbstractCriteria implements Criteria {

	private AbstractCriteria criteria;

	NotCriteria(AbstractCriteria criteria) {
		this.criteria = criteria;
	}

	@Override
	boolean matches(Task task) {
		return !criteria.matches(task);
	}

}