package com.brov0010.dao;

import java.util.List;


public interface IGenericDao<T> extends IDao<T> {
	@SuppressWarnings("hiding")
	<T> List<T> getAllByType(Class<T> clazz);
}
