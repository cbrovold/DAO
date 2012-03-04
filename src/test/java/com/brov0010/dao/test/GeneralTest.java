package com.brov0010.dao.test;

import java.util.ArrayList;
import java.util.List;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.brov0010.dao.model.Bus;
import com.brov0010.dao.model.Driver;
import com.brov0010.dao.model.Passenger;
import com.brov0010.dao.model.Person;
import com.brov0010.dao.model.dao.IBusDao;
import com.brov0010.dao.model.dao.IPersonDao;
import com.brov0010.dao.util.DaoException;
import com.brov0010.testing.AbstractTransactionalTestHelper;

public class GeneralTest extends AbstractTransactionalTestHelper {

	@Autowired
	IBusDao busDao;
	@Autowired
	IPersonDao personDao;
	
	
	
	private static final List<Person> people = new ArrayList<Person>();
	static {
		people.add(new Driver("Steve", "A1")); 
		people.add(new Driver("Bill", "A2"));
		people.add(new Passenger("Roy", false));
		people.add(new Passenger("Molly", true));
		people.add(new Passenger("Cletus", false));
		people.add(new Passenger("Beatrice", true));
		people.add(new Passenger("Janice", false));
		people.add(new Passenger("Adam", false));
		people.add(new Passenger("Micheal", true));
		people.add(new Passenger("Bill", false));
	};
	
	@Before
	public void setUp() throws DaoException{
		for(Person p : people) {
			personDao.create(p);
		}
		
		busDao.create(new Bus("B1"));
		busDao.create(new Bus("B2"));
	}

	@Test
	public void test_getPeople() {
		Assert.assertEquals(8, personDao.getAll().size());
	}
	
	@Test
	public void test_getBuses() {

		Assert.assertEquals(2, busDao.getAll().size());
	}
	
}
