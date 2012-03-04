package com.brov0010.dao.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "driver")
public class Driver extends Person {

	public Driver() { }
	public Driver(String name) {
		super(name);
	}
	public Driver(String name, String license) {
		super(name);
		setLicense(license);
	}
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "bus_id")
	private Bus bus;

	@Column(name = "license")
	private String license;

	public void setLicense(String license) {
		this.license = license;
	}
	public String getLicense() {
		return license;
	}
}
