package com.brov0010.dao.model.dao;

import java.util.List;

import org.hibernate.criterion.Restrictions;

import com.brov0010.dao.AbstractGenericDao;
import com.brov0010.dao.model.Person;

public class PersonDao extends AbstractGenericDao<Person> implements IPersonDao {

	@SuppressWarnings("unchecked")
	@Override
	public List<Person> findAllByName(String name) {
		return getCriteria().add(Restrictions.eq("name", name)).list();
	}

}
