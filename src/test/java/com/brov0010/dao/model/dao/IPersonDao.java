package com.brov0010.dao.model.dao;

import java.util.List;

import com.brov0010.dao.IGenericDao;
import com.brov0010.dao.model.Person;

public interface IPersonDao extends IGenericDao<Person> {

	List<Person> findAllByName(String name);
}
