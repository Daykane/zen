package com.zen.dao;

import java.util.List;

import com.zen.beans.Product;

public interface ProductDao {

    void create( Product product ) throws DAOException;
    
    void delete(Product product) throws DAOException;
    
    void update(int id, Product product)  throws DAOException;

    Product find( String id ) throws DAOException;
    
    List<Product> findAll() throws DAOException;

    List<Product> findByCategory(String id);

	Product findByCategoryById(String id, String idP);

}