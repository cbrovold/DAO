package com.brov0010.dao.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

@Entity
@Table(name = "person")
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Person extends AbstractEntity {

	public Person() { }
	public Person(String name) {
		setName(name);
	}
	
	@Column(name = "person")
	private String name;

	public void setName(String name) {
		this.name = name;
	}
	public String getName() {
		return name;
	}

	public abstract Bus getBus();
	public abstract void setBus(Bus bus);
}
