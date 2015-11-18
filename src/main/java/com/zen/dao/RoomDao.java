package com.zen.dao;

import java.util.List;

import com.zen.beans.AbstractRoom;




public interface RoomDao {

    void create( AbstractRoom event ) throws DAOException;

    AbstractRoom findById( String id ) throws DAOException;
    
    List<AbstractRoom> findAll() throws DAOException;
    
    void update(int id, AbstractRoom abstractEvent)  throws DAOException;
    
    void delete(int id) throws DAOException;
    
    
    
}
