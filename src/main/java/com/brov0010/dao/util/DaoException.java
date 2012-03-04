package com.brov0010.dao.util;

import org.hibernate.HibernateException;

public abstract class DaoException extends HibernateException {

	private static final long serialVersionUID = 686332252813872990L;

	public DaoException(Throwable root) {
		super(root);
	}

}
