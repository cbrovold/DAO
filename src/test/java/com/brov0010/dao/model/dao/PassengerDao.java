package com.brov0010.dao.model.dao;

import java.util.List;

import org.hibernate.criterion.Restrictions;

import com.brov0010.dao.model.Passenger;

public class PassengerDao extends PersonDao implements IPassengerDao {

	@SuppressWarnings("unchecked")
	@Override
	public List<Passenger> findAllByPass(boolean hasPass) {
		return getSession().createCriteria(Passenger.class).add(Restrictions.eq("hasPass", hasPass)).list();
	}

}
