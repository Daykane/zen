package com.zen.dao;

import com.zen.beans.User;
import static com.zen.dao.DAOUtilitaire.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDaoImpl implements UserDao {
	
	private DAOFactory          daoFactory;

    UserDaoImpl( DAOFactory daoFactory ) {
        this.daoFactory = daoFactory;
    }
    
    private static final String SQL_SELECT_BY_ID = "SELECT * FROM User WHERE id = ?";
    @Override
	public
    User findById( String id ) throws DAOException {
        Connection connexion = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        User user = null;

        try {
            /* Récupération d'une connexion depuis la Factory */
            connexion = daoFactory.getConnection();
            preparedStatement = initialisationRequetePreparee( connexion, SQL_SELECT_BY_ID, false, id );
            resultSet = preparedStatement.executeQuery();
            /* Parcours de la ligne de données de l'éventuel ResulSet retourné */
            if ( resultSet.next() ) {
                user = map( resultSet );
            }
        } catch ( SQLException e ) {
            throw new DAOException( e );
        } finally {
            fermeturesSilencieuses( resultSet, preparedStatement, connexion );
        }

        return user;
    }
    
    private static final String SQL_INSERT = "INSERT INTO User (`password`, `lastName`, `firstName`, `adr1`, `adr2`, `pc`, `town`, `phone`, `mail`) VALUES (?, ?, ?, ?,?, ?, ?, ?, ?);";
    @Override
    public void create( User user ) throws DAOException {
        Connection connexion = null;
        PreparedStatement preparedStatement = null;

        try {
 
            connexion = daoFactory.getConnection();
            preparedStatement = initialisationRequetePreparee( connexion, SQL_INSERT, true, user.getPassword(), user.getLastName(), user.getFirstName(), user.getAdr1(),user.getAdr2(),user.getPc(),user.getTown(),user.getPhone(),user.getMail());
            int statut = preparedStatement.executeUpdate();
           
            if ( statut == 0 ) {
                throw new DAOException( "Échec de la création de l'utilisateur, aucune ligne ajoutée dans la table." );
            }
      
        } catch ( SQLException e ) {
            throw new DAOException( e );
        } finally {
            fermeturesSilencieuses(preparedStatement, connexion );
        }
    }
    
    
    /*
     * Simple méthode utilitaire permettant de faire la correspondance (le
     * mapping) entre une ligne issue de la table des utilisateurs (un
     * ResultSet) et un bean Utilisateur.
     */
    private static User map( ResultSet resultSet ) throws SQLException {
        User user = new User();
        user.setId( resultSet.getInt( "id" ) );
        user.setPassword( resultSet.getString("password"));
        user.setLastName( resultSet.getString( "lastName" ) );
        user.setFirstName( resultSet.getString( "firstName" ) );
        user.setAdr1( resultSet.getString( "adr1" ) );
        user.setAdr2( resultSet.getString( "adr2" ) );
        user.setPc( resultSet.getString( "pc" ) );
        user.setTown( resultSet.getString( "town" ) );
        user.setPhone( resultSet.getString( "phone" ) );
        user.setMail( resultSet.getString( "mail" ) );
     
        return user;
    }
    
    private static final String SQL_SELECT = "SELECT * FROM User";
	@Override
	public List<User> findAll() throws DAOException {
		Connection connexion = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        User user = null;
        List<User> users = new ArrayList<User>();

        try {
            /* Récupération d'une connexion depuis la Factory */
            connexion = daoFactory.getConnection();
            preparedStatement = initialisationRequetePreparee( connexion, SQL_SELECT, false );
            resultSet = preparedStatement.executeQuery();
            /* Parcours de la ligne de données de l'éventuel ResulSet retourné */
            while ( resultSet.next() ) {           
                user = map( resultSet );
                users.add(user);   
            }
        } catch ( SQLException e ) {
            throw new DAOException( e );
        } finally {
            fermeturesSilencieuses( resultSet, preparedStatement, connexion );
        }

        return users;
	}

	private static final String SQL_CONNECTION = "SELECT * FROM User WHERE mail = ? AND password = ?";
	@Override
	public User connection(String mail, String password) throws DAOException {
		Connection connexion = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        User user = null;

        try {
            /* Récupération d'une connexion depuis la Factory */
            connexion = daoFactory.getConnection();
            preparedStatement = initialisationRequetePreparee( connexion, SQL_CONNECTION, false, mail,password );
            resultSet = preparedStatement.executeQuery();
            /* Parcours de la ligne de données de l'éventuel ResulSet retourné */
            if ( resultSet.next() ) {
                user = map( resultSet );
            }
        } catch ( SQLException e ) {
            throw new DAOException( e );
        } finally {
            fermeturesSilencieuses( resultSet, preparedStatement, connexion );
        }

        return user;
	}

	private static final String SQL_DELETE = "DELETE FROM User WHERE id = ?";
	@Override
	public void delete(int id) throws DAOException {
		 Connection connexion = null;
	     PreparedStatement preparedStatement = null;

	        try {
	 
	            connexion = daoFactory.getConnection();
	            preparedStatement = initialisationRequetePreparee( connexion, SQL_DELETE, true, id);
	            int statut = preparedStatement.executeUpdate();
	           
	            if ( statut == 0 ) {
	                throw new DAOException( "Échec de la suppression de l'utilisateur." );
	            }
	      
	        } catch ( SQLException e ) {
	            throw new DAOException( e );
	        } finally {
	            fermeturesSilencieuses(preparedStatement, connexion );
	        }
	}

}