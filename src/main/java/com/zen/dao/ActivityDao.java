package com.zen.dao;

import java.util.List;

import com.zen.beans.Activity;



public interface ActivityDao {

    void create( Activity activity ) throws DAOException;

    Activity findById( String id ) throws DAOException;
    
    List<Activity> findAll() throws DAOException;
    
    void delete(int id) throws DAOException;
    
    void updateActivity(Activity activity, int id);
    
}