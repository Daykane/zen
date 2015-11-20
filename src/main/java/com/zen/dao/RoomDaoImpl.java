package com.zen.dao;

import com.zen.beans.AbstractRoom;
import com.zen.beans.Accessory;

import static com.zen.dao.DAOUtilitaire.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RoomDaoImpl implements RoomDao {

	private DAOFactory          daoFactory;

	RoomDaoImpl( DAOFactory daoFactory ) {
		this.daoFactory = daoFactory;
	}

	private static final String SQL_SELECT_BY_ID = "SELECT * FROM Room WHERE roomId = ?";
	@Override
	public
	AbstractRoom findById( String id ) throws DAOException {
		Connection connexion = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		AbstractRoom room = null;

		try {
			/* Recuperation d'une connexion depuis la Factory */
			connexion = daoFactory.getConnection();
			preparedStatement = initialisationRequetePreparee( connexion, SQL_SELECT_BY_ID, false, id );
			resultSet = preparedStatement.executeQuery();
			/* Parcours de la ligne de donnees de l'eventuel ResulSet retourne */
			if ( resultSet.next() ) {
				room = map( resultSet );
			}
		} catch ( SQLException e ) {
			throw new DAOException( e );
		} finally {
			fermeturesSilencieuses( resultSet, preparedStatement, connexion );
		}

		return room;
	}

	private static final String SQL_INSERT = "INSERT INTO Activity (`roomName`, `superficy`) VALUES (?, ?);";
	@Override
	public void create( AbstractRoom room ) throws DAOException {
		Connection connexion = null;
		PreparedStatement preparedStatement = null;

		try {

			connexion = daoFactory.getConnection();
			preparedStatement = initialisationRequetePreparee( connexion, SQL_INSERT, true, room.getRoomName(), room.getSuperficy());
			int statut = preparedStatement.executeUpdate();

			if ( statut == 0 ) {
				throw new DAOException( "echec de la creation de la room, aucune ligne ajoutee dans la table." );
			}

		} catch ( SQLException e ) {
			throw new DAOException( e );
		} finally {
			fermeturesSilencieuses(preparedStatement, connexion );
		}
	}


	/*
	 * Simple methode utilitaire permettant de faire la correspondance (le
	 * mapping) entre une ligne issue de la table des rooms (un
	 * ResultSet) et un bean room.
	 */
	private static AbstractRoom map( ResultSet resultSet ) throws SQLException {
		AbstractRoom room = new AbstractRoom();
		room.setRoomId(resultSet.getInt( "roomId" ));
		room.setRoomName(resultSet.getString("roomName"));
		room.setSuperficy(resultSet.getInt("superficy"));

		return room;
	}

	private static final String SQL_SELECT = "SELECT * FROM Room";
	@Override
	public List<AbstractRoom> findAll() throws DAOException {
		Connection connexion = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		AbstractRoom room = null;
		List<AbstractRoom> rooms = new ArrayList<AbstractRoom>();

		try {
			/* Recuperation d'une connexion depuis la Factory */
			connexion = daoFactory.getConnection();
			preparedStatement = initialisationRequetePreparee( connexion, SQL_SELECT, false );
			resultSet = preparedStatement.executeQuery();
			/* Parcours de la ligne de donnees de l'eventuel ResulSet retourne */
			while ( resultSet.next() ) {           
				room = map( resultSet );
				rooms.add(room);   
			}
		} catch ( SQLException e ) {
			throw new DAOException( e );
		} finally {
			fermeturesSilencieuses( resultSet, preparedStatement, connexion );
		}

		return rooms;
	}



	private static final String SQL_DELETE = "DELETE FROM Room WHERE roomId = ?";
	@Override
	public void delete(int id) throws DAOException {
		Connection connexion = null;
		PreparedStatement preparedStatement = null;

		try {

			connexion = daoFactory.getConnection();
			preparedStatement = initialisationRequetePreparee( connexion, SQL_DELETE, true, id);
			int statut = preparedStatement.executeUpdate();

			if ( statut == 0 ) {
				throw new DAOException( "Echec de la suppression de la room" );
			}

		} catch ( SQLException e ) {
			throw new DAOException( e );
		} finally {
			fermeturesSilencieuses(preparedStatement, connexion );
		}
	}


	@Override
	public void update(int id, AbstractRoom abstractRoom) throws DAOException {
		// TODO Auto-generated method stub

	}



	@Override
	public void deleteAccessoryFromRoom(int idRoom, Accessory accessory) {
		// TODO Auto-generated method stub

	}


	@Override
	public AbstractRoom findByIdWithAccesories(String id) throws DAOException {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public List<AbstractRoom> findAllWithAccesories() throws DAOException {
		// TODO Auto-generated method stub
		return null;
	}

	private static final String SQL_ADD_ACCESSORIES = "INSERT INTO RoomAccessory (`roomId`, `accessoryId`, `quantity`) VALUES (?, ?,?);";
	@Override
	public void addAccessoryInRoom(String idRoom, Accessory accessory) {
		Connection connexion = null;
		PreparedStatement preparedStatement = null;

		try {

			connexion = daoFactory.getConnection();
			preparedStatement = initialisationRequetePreparee( connexion, SQL_ADD_ACCESSORIES, true, idRoom , accessory.getAccessoryId(), accessory.getQuantity());
			int statut = preparedStatement.executeUpdate();

			if ( statut == 0 ) {
				throw new DAOException( "echec de l'addition des accessoires à la salle, aucune ligne ajoutee dans la table." );
			}

		} catch ( SQLException e ) {
			throw new DAOException( e );
		} finally {
			fermeturesSilencieuses(preparedStatement, connexion );

		}
	}

	private static final String SQL_UPDATE_ACCESSORIES = "UPDATE RoomAccessory SET quantity=? WHERE roomId=? and accessoryId=?;";
	@Override
	public void updateQuantityAccessoryInRoom(String idRoom, Accessory accessory) {
		Connection connexion = null;
		PreparedStatement preparedStatement = null;

		try {

			connexion = daoFactory.getConnection();
			preparedStatement = initialisationRequetePreparee( connexion, SQL_UPDATE_ACCESSORIES, true, accessory.getQuantity(), idRoom , accessory.getAccessoryId());
			int statut = preparedStatement.executeUpdate();

			if ( statut == 0 ) {
				throw new DAOException( "echec de l'addition des accessoires à la salle, aucune ligne ajoutee dans la table." );
			}

		} catch ( SQLException e ) {
			throw new DAOException( e );
		} finally {
			fermeturesSilencieuses(preparedStatement, connexion );
		}


	}

}
