package com.brov0010.dao;

import java.util.List;


public abstract class AbstractGenericDao<T> extends AbstractDao<T> implements IGenericDao<T> {

	/**
	 * Used to get a polymorphic list
	 */
	@SuppressWarnings({ "unchecked", "hiding" })
	public <T> List<T> getAllByType(Class<T> clazz) {
		return getSession().createCriteria(clazz).list();
	}
}
