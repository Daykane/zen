package com.zen.dao;

import com.zen.beans.AbstractEvent;


import static com.zen.dao.DAOUtilitaire.*;
import java.sql.SQLIntegrityConstraintViolationException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
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

	private static final String SQL_INSERT = "INSERT INTO Event (`eventName`, `eventPrice`, `maxNbr`, `durationHours`, `activityId`, `contributorId`, `eventDay`,`startTime`,`endTime`) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?);";
	@Override
	public void create(AbstractEvent event ) throws DAOException {
		Connection connexion = null;
		PreparedStatement preparedStatement = null;

		try {

			connexion = daoFactory.getConnection();
			preparedStatement = initialisationRequetePreparee( connexion, SQL_INSERT, true, event.getEventName(), event.getEventPrice() ,event.getMaxNubr(), event.getDurationHours(), event.getActivityId(), 1, event.getEventDay(), event.getStartTime(), event.getEndTime());
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
	 * mapping) entre une ligne issue de la table des events (un
	 * ResultSet) et un bean evnet.
	 */
	private static AbstractEvent map( ResultSet resultSet ) throws SQLException {
		AbstractEvent event = new AbstractEvent();
		event.setEventId(resultSet.getInt( "eventId" ));
		event.setEventName(resultSet.getString("eventName"));
		event.setEventPrice(resultSet.getDouble("eventPrice"));
		event.setMaxNubr(resultSet.getInt("maxNbr"));
		event.setDurationHours(resultSet.getDouble("durationHours"));
		event.setActivityId(resultSet.getInt("activityId"));
		event.setContributor(resultSet.getInt("contributorId"));
		event.setStartTime(resultSet.getString("startTime"));
		event.setEndTime(resultSet.getString("endTime"));
		event.setEventDay(resultSet.getString("eventDay"));
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

	private static final String SQL_UPDATE_EVENT = "UPDATE Event SET eventName=?, eventPrice=?, maxNbr=?, durationHours=?, activityId=?, contributorId=?, eventDay=?, startTime=?, endTime=? WHERE eventId=?;";
	@Override
	public void update(AbstractEvent event) throws DAOException {
		Connection connexion = null;
		PreparedStatement preparedStatement = null;
		try {
			connexion = daoFactory.getConnection();
			preparedStatement = initialisationRequetePreparee( connexion, SQL_UPDATE_EVENT, true, event.getEventName(), event.getEventPrice(), event.getMaxNubr(), event.getDurationHours(), event.getActivityId(), event.getContributor(), event.getEventDay(), event.getStartTime(), event.getEndTime(), event.getEventId() );
			int statut = preparedStatement.executeUpdate();

			if ( statut == 0 ) {
				throw new DAOException( "Event Update Fail" );
			}	      
		} catch ( SQLException e ) {
			throw new DAOException( e );
		} finally {
			fermeturesSilencieuses(preparedStatement, connexion );
		}	
	}

	private static final String SQL_SELECT_BY_ACTIVITY = "SELECT * FROM Event WHERE activityId = ?";
	@Override
	public List<AbstractEvent> findByActivity(String id) {
		Connection connexion = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		AbstractEvent event = null;
		List<AbstractEvent> events = new ArrayList<AbstractEvent>();

		try {
			/* Recuperation d'une connexion depuis la Factory */
			connexion = daoFactory.getConnection();
			preparedStatement = initialisationRequetePreparee( connexion, SQL_SELECT_BY_ACTIVITY, false,id );
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

	private static final String SQL_SELECT_BY_ACT_BY_ID = "SELECT * FROM Event WHERE activityId = ? AND eventId = ?";
	@Override
	public AbstractEvent findByActivityById(String id, String idE) {
		Connection connexion = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		AbstractEvent event = null;

		try {
			/* Recuperation d'une connexion depuis la Factory */
			connexion = daoFactory.getConnection();
			preparedStatement = initialisationRequetePreparee( connexion, SQL_SELECT_BY_ACT_BY_ID, false, id,idE);
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
	
	private static final String SQL_SELECT_USER_INS = "select * from User where id =?;";
	private static final String SQL_SELECT_Inscription = "select * FROM InscriptionEvent where userId = ? and eventId = ?";
	private static final String SQL_INSERT_Inscription = "INSERT INTO InscriptionEvent (`userId`, `eventId`, `inscriptionDate`) VALUES (?, ?, ?);";
	@Override
	public void subscribeEvent(String idE, String idU) throws DuplicateEntryException, ForeignKeyException {
		Connection connexion = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet=null;

		try {
			Date date = new Date();
			connexion = daoFactory.getConnection();
			preparedStatement = initialisationRequetePreparee( connexion, SQL_INSERT_Inscription, true, idU, idE ,new Timestamp(date.getTime()));
			int statut = preparedStatement.executeUpdate();

			if ( statut == 0 ) {
				throw new DAOException( "echec de l'inscription à l'event, aucune ligne ajoutee dans la table." );
			}
			

		}
		catch (SQLIntegrityConstraintViolationException e1 ) {
			//deja inscrit
			try {
				preparedStatement = initialisationRequetePreparee( connexion, SQL_SELECT_Inscription, true, idU, idE);
				resultSet = preparedStatement.executeQuery();
				if (resultSet.next()){
					if (resultSet.getInt("eventId") != 0){
						throw new DuplicateEntryException();
					}
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			//user n'existe pas 
			try {
				preparedStatement = initialisationRequetePreparee( connexion, SQL_SELECT_USER_INS, true, idU);
				resultSet = preparedStatement.executeQuery();
				int idControl= -1;
				if (resultSet.next()){
					idControl = resultSet.getInt("id");
					
				}
				if(idControl ==-1){throw new ForeignKeyException();}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			//event n'existe pas
			
			
		} 
		catch ( SQLException e ) {
			throw new DAOException( e );
		}
		finally {
			fermeturesSilencieuses(preparedStatement, connexion );
		}

	}

	private static final String SQL_DELETE_Inscription = "DELETE FROM InscriptionEvent where userId = ? and eventId = ?;";
	@Override
	public void unsubscribeEvent(String idE, String idU) {
		Connection connexion = null;
		PreparedStatement preparedStatement = null;

		try {
			connexion = daoFactory.getConnection();
			preparedStatement = initialisationRequetePreparee( connexion, SQL_DELETE_Inscription, true, idU, idE );
			int statut = preparedStatement.executeUpdate();

			if ( statut == 0 ) {
				throw new DAOException( "echec de la desinscription à l'event, aucune ligne supprimee dans la table." );
			}

		} catch ( SQLException e ) {
			throw new DAOException( e );
		} finally {
			fermeturesSilencieuses(preparedStatement, connexion );
		}
	}

	private static final String SQL_SELECT_EVENT_USER = "SELECT Event.* FROM Event, InscriptionEvent WHERE InscriptionEvent.userId = ? and InscriptionEvent.eventId = Event.eventId";
	@Override
	public List<AbstractEvent> findAllEvent(String id) throws DAOException {
		Connection connexion = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		AbstractEvent event = null;
		List<AbstractEvent> events = new ArrayList<AbstractEvent>();

		try {
			/* Recuperation d'une connexion depuis la Factory */
			connexion = daoFactory.getConnection();
			preparedStatement = initialisationRequetePreparee( connexion, SQL_SELECT_EVENT_USER, false,id );
			resultSet = preparedStatement.executeQuery();
			/* Parcours de la ligne de donnees de l'eventuel ResulSet retourne */
			while ( resultSet.next() ) {           
				event = EventDaoImpl.map( resultSet );
				events.add(event);   
			}
		} catch ( SQLException e ) {
			throw new DAOException( e );
		} finally {
			fermeturesSilencieuses( resultSet, preparedStatement, connexion );
		}

		return events;
	}

}
