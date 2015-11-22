package com.zen.dao;

import java.sql.Timestamp;
import java.util.List;

import javax.security.auth.login.LoginException;

import com.zen.beans.AbstractEvent;
import com.zen.beans.User;

public interface UserDao {

    void create( User utilisateur ) throws DAOException, DAOExceptionMail;

    User findById( String id ) throws DAOException;
    
    List<User> findAll() throws DAOException;
    
    //User connection(String mail,String password) throws DAOException;
    
    void delete(int id) throws DAOException;

	User connection(String mail, String password, String token, Timestamp currentTimestamp) throws DAOException, LoginException;

	void update(User user);

	User findByToken(String token);
	
}
