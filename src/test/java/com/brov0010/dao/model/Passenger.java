package com.brov0010.dao.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "passenger")
public class Passenger extends Person {

	public Passenger() { }
	public Passenger(String name) {
		super(name);
	}
	public Passenger(String name, boolean hasPass) {
		super(name);
		setHasPass(hasPass);
	}
	
	@Column(name = "has_pass")
	private boolean hasPass = false; /* sets default to false */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "bus_id")
	private Bus bus;

	public void setHasPass(boolean hasPass) {
		this.hasPass = hasPass;
	}
	public boolean isHasPass() {
		return hasPass;
	}

	@Override
	public Bus getBus() {
		return bus;
	}
	@Override
	public void setBus(Bus bus) {
		this.bus = bus;
	}

}
