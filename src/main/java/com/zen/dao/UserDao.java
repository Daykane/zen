package com.zen.dao;

import java.util.Calendar;
import java.util.List;

import com.zen.beans.User;

public interface UserDao {

    void create( User utilisateur ) throws DAOException, DAOExceptionMail;

    User findById( String id ) throws DAOException;
    
    List<User> findAll() throws DAOException;
    
    //User connection(String mail,String password) throws DAOException;
    
    void delete(int id) throws DAOException;

	User connection(String mail, String password, String token, Calendar calendar);
}
