package com.zen.dao;


import static com.zen.dao.DAOUtilitaire.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.zen.beans.AbstractEvent;
import com.zen.beans.Accessory;

public class AccessoryDaoImpl implements AccessoryDao{
	
	private DAOFactory          daoFactory;

	AccessoryDaoImpl( DAOFactory daoFactory ) {
		this.daoFactory = daoFactory;
	}

	private static final String SQL_INSERT = "INSERT INTO Accessory (`accessoryName`) VALUES (?);";
	@Override
	public void create(Accessory accessory ) throws DAOException {
		Connection connexion = null;
		PreparedStatement preparedStatement = null;

		try {

			connexion = daoFactory.getConnection();
			preparedStatement = initialisationRequetePreparee( connexion, SQL_INSERT, true, accessory.getAccessoryName());
			int statut = preparedStatement.executeUpdate();

			if ( statut == 0 ) {
				throw new DAOException( "echec de la creation de l'accessoire, aucune ligne ajoutee dans la table." );
			}

		} catch ( SQLException e ) {
			throw new DAOException( e );
		} finally {
			fermeturesSilencieuses(preparedStatement, connexion );
		}
	}


	private static final String SQL_SELECT_BY_ID = "SELECT * FROM Accessory WHERE eventId = ?";
	@Override
	public
	Accessory findById( String id ) throws DAOException {
		Connection connexion = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		Accessory accessory = null;

		try {
			/* Recuperation d'une connexion depuis la Factory */
			connexion = daoFactory.getConnection();
			preparedStatement = initialisationRequetePreparee( connexion, SQL_SELECT_BY_ID, false, id );
			resultSet = preparedStatement.executeQuery();
			/* Parcours de la ligne de donnees de l'eventuel ResulSet retourne */
			if ( resultSet.next() ) {
				accessory = map( resultSet );
			}
		} catch ( SQLException e ) {
			throw new DAOException( e );
		} finally {
			fermeturesSilencieuses( resultSet, preparedStatement, connexion );
		}

		return accessory;
	}
	
	/*
	 * Simple methode utilitaire permettant de faire la correspondance (le
	 * mapping) entre une ligne issue de la table des activités (un
	 * ResultSet) et un bean activité.
	 */
	private static Accessory map( ResultSet resultSet ) throws SQLException {
		Accessory accessory = new Accessory();
		accessory.setAccessoryId(resultSet.getInt( "accessoryId" ));
		accessory.setAccessoryName(resultSet.getString("accessoryName"));
		
		return accessory;
	}

	private static final String SQL_SELECT = "SELECT * FROM Accessory";
	@Override
	public List<Accessory> findAll() throws DAOException {
		Connection connexion = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		Accessory accessory = null;
		List<Accessory> accessories = new ArrayList<Accessory>();

		try {
			/* Recuperation d'une connexion depuis la Factory */
			connexion = daoFactory.getConnection();
			preparedStatement = initialisationRequetePreparee( connexion, SQL_SELECT, false );
			resultSet = preparedStatement.executeQuery();
			/* Parcours de la ligne de donnees de l'eventuel ResulSet retourne */
			while ( resultSet.next() ) {           
				accessory = map( resultSet );
				accessories.add(accessory);   
			}
		} catch ( SQLException e ) {
			throw new DAOException( e );
		} finally {
			fermeturesSilencieuses( resultSet, preparedStatement, connexion );
		}

		return accessories;
	}

	@Override
	public void update(int id, Accessory accessory) throws DAOException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(int id) throws DAOException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Accessory> findByRoom(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Accessory findByRoomById(String id, String idA) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void addAccessoryInRoom(int idRoom, Accessory accessory, int quantity) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteAccessoryFromRoom(int idRoom, Accessory accessory) {
		// TODO Auto-generated method stub
		
	}

}
