package com.zen.dao;

import com.zen.beans.AbstractEvent;
import static com.zen.dao.DAOUtilitaire.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EventDaoImpl implements EventDao {

	private DAOFactory          daoFactory;

	EventDaoImpl( DAOFactory daoFactory ) {
		this.daoFactory = daoFactory;
	}

	private static final String SQL_SELECT_BY_ID = "SELECT * FROM Event WHERE eventId = ?";
	@Override
	public
	AbstractEvent findById( String id ) throws DAOException {
		Connection connexion = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		AbstractEvent event = null;

		try {
			/* Recuperation d'une connexion depuis la Factory */
			connexion = daoFactory.getConnection();
			preparedStatement = initialisationRequetePreparee( connexion, SQL_SELECT_BY_ID, false, id );
			resultSet = preparedStatement.executeQuery();
			/* Parcours de la ligne de donnees de l'eventuel ResulSet retourne */
			if ( resultSet.next() ) {
				event = map( resultSet );
			}
		} catch ( SQLException e ) {
			throw new DAOException( e );
		} finally {
			fermeturesSilencieuses( resultSet, preparedStatement, connexion );
		}

		return event;
	}

	private static final String SQL_INSERT = "INSERT INTO Event (`eventName`, `eventPrice`, `maxNbr`, `durationHours`) VALUES (?, ?, ?,?);";
	@Override
	public void create(AbstractEvent event ) throws DAOException {
		Connection connexion = null;
		PreparedStatement preparedStatement = null;

		try {

			connexion = daoFactory.getConnection();
			preparedStatement = initialisationRequetePreparee( connexion, SQL_INSERT, true, event.getEventName(), event.getEventPrice() ,event.getMaxNubr(), event.getDurationHours());
			int statut = preparedStatement.executeUpdate();

			if ( statut == 0 ) {
				throw new DAOException( "echec de la creation de l'event, aucune ligne ajoutee dans la table." );
			}

		} catch ( SQLException e ) {
			throw new DAOException( e );
		} finally {
			fermeturesSilencieuses(preparedStatement, connexion );
		}
	}


	/*
	 * Simple methode utilitaire permettant de faire la correspondance (le
	 * mapping) entre une ligne issue de la table des activités (un
	 * ResultSet) et un bean activité.
	 */
	private static AbstractEvent map( ResultSet resultSet ) throws SQLException {
		AbstractEvent event = new AbstractEvent();
		event.setEventId(resultSet.getInt( "eventId" ));
		event.setEventName(resultSet.getString("eventName"));
		event.setEventPrice(resultSet.getDouble("eventPrice"));
		event.setMaxNubr(resultSet.getInt("maxNbr"));
		event.setDurationHours(resultSet.getDouble("durationHours"));
		
		return event;
	}

	private static final String SQL_SELECT = "SELECT * FROM Event";
	@Override
	public List<AbstractEvent> findAll() throws DAOException {
		Connection connexion = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		AbstractEvent event = null;
		List<AbstractEvent> events = new ArrayList<AbstractEvent>();

		try {
			/* Recuperation d'une connexion depuis la Factory */
			connexion = daoFactory.getConnection();
			preparedStatement = initialisationRequetePreparee( connexion, SQL_SELECT, false );
			resultSet = preparedStatement.executeQuery();
			/* Parcours de la ligne de donnees de l'eventuel ResulSet retourne */
			while ( resultSet.next() ) {           
				event = map( resultSet );
				events.add(event);   
			}
		} catch ( SQLException e ) {
			throw new DAOException( e );
		} finally {
			fermeturesSilencieuses( resultSet, preparedStatement, connexion );
		}

		return events;
	}



	private static final String SQL_DELETE = "DELETE FROM Event WHERE eventId = ?";
	@Override
	public void delete(int id) throws DAOException {
		Connection connexion = null;
		PreparedStatement preparedStatement = null;

		try {

			connexion = daoFactory.getConnection();
			preparedStatement = initialisationRequetePreparee( connexion, SQL_DELETE, true, id);
			int statut = preparedStatement.executeUpdate();

			if ( statut == 0 ) {
				throw new DAOException( "Echec de la suppression de l'event." );
			}

		} catch ( SQLException e ) {
			throw new DAOException( e );
		} finally {
			fermeturesSilencieuses(preparedStatement, connexion );
		}
	}

}
