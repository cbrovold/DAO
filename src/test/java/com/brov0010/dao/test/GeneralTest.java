package com.brov0010.dao.test;

import java.util.ArrayList;
import java.util.List;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.brov0010.dao.model.Bus;
import com.brov0010.dao.model.Driver;
import com.brov0010.dao.model.Passenger;
import com.brov0010.dao.model.Person;
import com.brov0010.dao.model.dao.IBusDao;
import com.brov0010.dao.model.dao.IPassengerDao;
import com.brov0010.dao.model.dao.IPersonDao;
import com.brov0010.dao.util.DaoException;
import com.brov0010.testing.AbstractTransactionalTestHelper;

/**
 * By extending the {@link AbstractTransactionalTestHelper}, the data is automatically rolled back. However,
 * 	since the data.hibernate.properties[hibernate.hbm2ddl.auto] is set to just create, the schema will be persisted
 * 	so you can see how hibernate generates the schema. 
 * 
 * @author chadbrovold
 *
 */
public class GeneralTest extends AbstractTransactionalTestHelper {

	@Autowired
	IBusDao busDao;
	@Autowired
	IPersonDao personDao;
	@Autowired
	IPassengerDao passengerDao;
	
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
	// Mostly creating the separate arrays for test helpers
	private static List<Passenger> passengers = new ArrayList<Passenger>();
	private static List<Driver> drivers = new ArrayList<Driver>();

	@BeforeClass
	public static void init() {
		
		for(Person p : people) {
			
			if(p instanceof Passenger) {
				passengers.add((Passenger)p);
			} else if (p instanceof Driver) {
				drivers.add((Driver)p);
			}
		}
	}
	
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
		Assert.assertEquals(people.size(), personDao.getAll().size());
	}
	
	@Test
	public void test_getBuses() {

		Assert.assertEquals(2, busDao.getAll().size());
	}

	@Test
	public void test_getDrivers() {
		
		Assert.assertEquals(drivers.size(), personDao.getAllByType(Driver.class).size());
	}

	@Test
	public void test_getPassengers() {
		
		Assert.assertEquals(passengers.size(), personDao.getAllByType(Passenger.class).size());
	}

	@Test
	public void test_findPassengersWithPass() {

		// Just dynamically getting the number of Passenger(s) with/without a pass
		int noPass = 0;
		int pass = 0;
		for(Passenger p : passengers) {
			
			if(p.isHasPass()) {
				pass++;
			} else {
				noPass++;
			}
		}

		Assert.assertEquals(pass, passengerDao.findAllByPass(true).size());
		Assert.assertEquals(noPass, passengerDao.findAllByPass(false).size());
	}
}
