package com.zen.dao;

import java.util.List;

import com.zen.beans.Category;

public interface CategoryDao {

    void create( Category category ) throws DAOException;
    
    void delete(Category category) throws DAOException;
    
    void update(int id, Category category)  throws DAOException;

    Category find( String id ) throws DAOException;
    
    List<Category> findAll() throws DAOException;

}