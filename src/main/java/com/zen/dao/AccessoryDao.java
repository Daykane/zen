package com.zen.dao;

import java.util.List;

import com.zen.beans.Accessory;;




public interface AccessoryDao {

    void create(Accessory accessory ) throws DAOException;

    Accessory findById( String id ) throws DAOException;
    
    List<Accessory> findAll() throws DAOException;
    
    void update(int id, Accessory accessory)  throws DAOException;
    
    void delete(int id) throws DAOException;
    
    List<Accessory> findByRoom(String id);

    Accessory findByRoomById(String id, String idA);
    
   
}