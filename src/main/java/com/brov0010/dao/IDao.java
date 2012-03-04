package com.brov0010.dao;

import java.util.List;

import org.hibernate.Criteria;

import com.brov0010.dao.util.DaoException;
import com.brov0010.util.QueryMetaData;
import com.brov0010.util.ResultList;


public interface IDao<T> {

	T get(int id);
	void create(T t) throws DaoException;
	T update(T t) throws DaoException;
	void delete(T t) throws DaoException;
	List<T> getAll();
	List<T> list(Criteria crit);
	void flush();
	void evict(T t);
	ResultList<T> scroll(QueryMetaData metaData);
	ResultList<T> scroll(Criteria crit, QueryMetaData metaData);
}
