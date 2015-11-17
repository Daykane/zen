package com.zen.dao;

public class DAOException extends RuntimeException {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/*
     * Constructeurs
     */
    public DAOException( String message ) {
        super( message );
    	//System.out.println("erroraazaz");
    }

    public DAOException( String message, Throwable cause ) {
        super( message, cause );
    	//System.out.println("errorazazaz");
    }

    public DAOException( Throwable cause ) {
        super( cause );
    	//System.out.println("errorazazaz");
    }
}