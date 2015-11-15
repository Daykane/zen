package com.zen.dao;

import java.util.List;

import com.zen.beans.AbstractEvent;;



public interface EventDao {

    void create( AbstractEvent event ) throws DAOException;

    AbstractEvent findById( String id ) throws DAOException;
    
    List<AbstractEvent> findAll() throws DAOException;
    
    void delete(int id) throws DAOException;
    
}