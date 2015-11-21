package com.zen.dao;

import java.util.List;

import com.zen.beans.AbstractEvent;
import com.zen.beans.Product;;



public interface EventDao {

    void create( AbstractEvent event ) throws DAOException;

    AbstractEvent findById( String id ) throws DAOException;
    
    List<AbstractEvent> findAll() throws DAOException;
    
    void update(int id, AbstractEvent abstractEvent)  throws DAOException;
    
    void delete(int id) throws DAOException;
    
    List<AbstractEvent> findByActivity(String id);

    AbstractEvent findByActivityById(String id, String idE);
    
    void subscribeEvent(String idE, String idU);
    
    void unsubscribeEvent(String idE, String idU);
    
    public List<AbstractEvent> findAllEvent(String id) throws DAOException;
    
    
    
}