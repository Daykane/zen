package com.zen.dao;

import com.zen.beans.Activity;
import com.zen.beans.User;
import static com.zen.dao.DAOUtilitaire.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ActivityDaoImpl implements ActivityDao {
	
	private DAOFactory          daoFactory;

    ActivityDaoImpl( DAOFactory daoFactory ) {
        this.daoFactory = daoFactory;
    }
    
    private static final String SQL_SELECT_BY_ID = "SELECT * FROM Activity WHERE id = ?";
    @Override
	public
    Activity findById( String id ) throws DAOException {
        Connection connexion = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Activity activity = null;

        try {
            /* Recuperation d'une connexion depuis la Factory */
            connexion = daoFactory.getConnection();
            preparedStatement = initialisationRequetePreparee( connexion, SQL_SELECT_BY_ID, false, id );
            resultSet = preparedStatement.executeQuery();
            /* Parcours de la ligne de donnees de l'eventuel ResulSet retourne */
            if ( resultSet.next() ) {
                activity = map( resultSet );
            }
        } catch ( SQLException e ) {
            throw new DAOException( e );
        } finally {
            fermeturesSilencieuses( resultSet, preparedStatement, connexion );
        }

        return activity;
    }
    
    private static final String SQL_INSERT = "INSERT INTO Activity (`activityName`, `activityShortDesc`, `activityLongDesc`) VALUES (?, ?, ?);";
    @Override
    public void create( Activity activity ) throws DAOException {
        Connection connexion = null;
        PreparedStatement preparedStatement = null;

        try {
 
            connexion = daoFactory.getConnection();
            preparedStatement = initialisationRequetePreparee( connexion, SQL_INSERT, true, activity.getActivityName(), activity.getActivityShortDescr(),activity.getActivityLongDesc());
            int statut = preparedStatement.executeUpdate();
           
            if ( statut == 0 ) {
                throw new DAOException( "echec de la creation de l'activité, aucune ligne ajoutee dans la table." );
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
    private static Activity map( ResultSet resultSet ) throws SQLException {
        Activity activity = new Activity();
        activity.setActivityId(resultSet.getInt( "id" ));
        activity.setActivityName(resultSet.getString("activityName"));
        activity.setActivityShortDescr(resultSet.getString("activityShortDesc"));
        activity.setActivityLongDescr(resultSet.getString("activityLongDesc"));
        
     
        return activity;
    }
    
    private static final String SQL_SELECT = "SELECT * FROM Activity";
	@Override
	public List<Activity> findAll() throws DAOException {
		Connection connexion = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Activity activity = null;
        List<Activity> activities = new ArrayList<Activity>();

        try {
            /* Recuperation d'une connexion depuis la Factory */
            connexion = daoFactory.getConnection();
            preparedStatement = initialisationRequetePreparee( connexion, SQL_SELECT, false );
            resultSet = preparedStatement.executeQuery();
            /* Parcours de la ligne de donnees de l'eventuel ResulSet retourne */
            while ( resultSet.next() ) {           
                activity = map( resultSet );
                activities.add(activity);   
            }
        } catch ( SQLException e ) {
            throw new DAOException( e );
        } finally {
            fermeturesSilencieuses( resultSet, preparedStatement, connexion );
        }

        return activities;
	}

	

	private static final String SQL_DELETE = "DELETE FROM Activity WHERE id = ?";
	@Override
	public void delete(int id) throws DAOException {
		 Connection connexion = null;
	     PreparedStatement preparedStatement = null;

	        try {
	 
	            connexion = daoFactory.getConnection();
	            preparedStatement = initialisationRequetePreparee( connexion, SQL_DELETE, true, id);
	            int statut = preparedStatement.executeUpdate();
	           
	            if ( statut == 0 ) {
	                throw new DAOException( "Echec de la suppression de l'activité." );
	            }
	      
	        } catch ( SQLException e ) {
	            throw new DAOException( e );
	        } finally {
	            fermeturesSilencieuses(preparedStatement, connexion );
	        }
	}

}