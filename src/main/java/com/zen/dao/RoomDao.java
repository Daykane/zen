package com.zen.dao;

import java.util.List;

import com.zen.beans.AbstractRoom;
import com.zen.beans.Accessory;




public interface RoomDao {

    void create( AbstractRoom room ) throws DAOException;

    AbstractRoom findById( String id ) throws DAOException;
    
    List<AbstractRoom> findAll() throws DAOException;
    
    void update(AbstractRoom room)  throws DAOException;
    
    void delete(int id) throws DAOException;
    
    void addAccessoryInRoom(String idRoom, Accessory accessory);
    
    public void updateQuantityAccessoryInRoom(String idRoom, Accessory accessory);
    
    void deleteAccessoryFromRoom (int idRoom, Accessory accessory );
    
    AbstractRoom findByIdWithAccesories( String id ) throws DAOException;
    
    List<AbstractRoom> findAllWithAccesories() throws DAOException;
    
    
    
}
