package com.brov0010.dao.model;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

/**
 * Entity object to be extended. Contains all the hibernate annotations needed for creating an
 * 	entity. 
 *
 */
@MappedSuperclass
public abstract class AbstractEntity {

	@Id @GeneratedValue(strategy=GenerationType.AUTO)
	private Integer id; /* integer MUST be used because hibernate does not like the primitive int */

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getId() {
		return id;
	}
}
