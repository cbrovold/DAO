package com.brov0010.dao.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "bus")
public class Bus extends AbstractEntity {

	public Bus() { }
	public Bus(String license) {
		setLicense(license);
	}
	
	@Column(name = "license")
	private String license;
	@OneToOne(cascade = CascadeType.ALL)
	private Driver driver;
	@OneToMany(fetch = FetchType.LAZY)
	@JoinColumn(name = "bus_id")
	private List<Passenger> passengers;

	public void setDriver(Driver driver) {
		this.driver = driver;
	}
	public Driver getDriver() {
		return driver;
	}

	public void setPassengers(List<Passenger> passengers) {
		this.passengers = passengers;
	}
	public List<Passenger> getPassengers() {
		return passengers;
	}

	public void setLicense(String license) {
		this.license = license;
	}
	public String getLicense() {
		return license;
	}
}
