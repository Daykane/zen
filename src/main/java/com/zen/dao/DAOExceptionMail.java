package com.zen.dao;

import java.sql.SQLIntegrityConstraintViolationException;

public class DAOExceptionMail extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	

	public DAOExceptionMail(SQLIntegrityConstraintViolationException e) {
		super(e);
	}


	public DAOExceptionMail() {
		super();
	}

}
