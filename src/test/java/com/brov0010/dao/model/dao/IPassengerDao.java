package com.brov0010.dao.model.dao;

import java.util.List;

import com.brov0010.dao.model.Passenger;


public interface IPassengerDao extends IPersonDao {
	List<Passenger> findAllByPass(boolean hasPass);
}
