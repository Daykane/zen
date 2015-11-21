package com.zen.dao;

import com.zen.beans.AbstractEvent;


import static com.zen.dao.DAOUtilitaire.*;

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
		event.setActivityId(resultSet.getInt("activityId"));
		event.setContributor(resultSet.getInt("contributorId"));
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


	@Override
	public void update(int id, AbstractEvent abstractEvent) throws DAOException {
		// TODO Auto-generated method stub
		
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

	private static final String SQL_INSERT_Inscription = "INSERT INTO InscriptionEvent (`userId`, `eventId`, `inscriptionDate`) VALUES (?, ?, ?);";
	@Override
	public void subscribeEvent(String idE, String idU) {
		Connection connexion = null;
		PreparedStatement preparedStatement = null;

		try {
			Date date = new Date();
			connexion = daoFactory.getConnection();
			preparedStatement = initialisationRequetePreparee( connexion, SQL_INSERT_Inscription, true, idE, idU ,new Timestamp(date.getTime()));
			int statut = preparedStatement.executeUpdate();

			if ( statut == 0 ) {
				throw new DAOException( "echec de l'inscription à l'event, aucune ligne ajoutee dans la table." );
			}

		} catch ( SQLException e ) {
			throw new DAOException( e );
		} finally {
			fermeturesSilencieuses(preparedStatement, connexion );
		}
		
	}

	private static final String SQL_DELETE_Inscription = "INSERT FROM InscriptionEvent where userId = ? and eventId = ? `inscriptionDate`);";
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
	
	private static final String SQL_SELECT_EVENT_USER = "SELECT event.* FROM Event, InscriptionEvent WHERE InscriptionEvent.userId = ? and InscriptionEvent.eventId = Event.eventId";
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
			preparedStatement = initialisationRequetePreparee( connexion, SQL_SELECT, false );
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
