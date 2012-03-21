package com.brov0010.dao.model.dao;

import java.util.List;

import org.hibernate.criterion.Restrictions;

import com.brov0010.dao.AbstractGenericDao;
import com.brov0010.dao.model.Driver;
import com.brov0010.dao.model.Passenger;
import com.brov0010.dao.model.Person;

/**
 * BE CAREFUL: extending this class CAN result in unexpected queries. If you want to extend, and get expected results
 * 				you might have to override the implemented method. 
 * 
 * For instance: getAll() will return a list of ALL Person. If you extend this dao, you might then need to override the 
 * 				 getAll() method to return the type of object you want (like {@link Passenger} or {@link Driver})
 * 
 * @author chadbrovold
 *
 */
public class PersonDao extends AbstractGenericDao<Person> implements IPersonDao {

	@SuppressWarnings("unchecked")
	@Override
	public List<Person> findAllByName(String name) {
		return getCriteria().add(Restrictions.eq("name", name)).list();
	}
}
