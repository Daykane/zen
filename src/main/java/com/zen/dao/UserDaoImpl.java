package com.zen.dao;

import com.zen.beans.User;
import static com.zen.dao.DAOUtilitaire.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.security.auth.login.LoginException;


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
            /* Recuperation d'une connexion depuis la Factory */
            connexion = daoFactory.getConnection();
            preparedStatement = initialisationRequetePreparee( connexion, SQL_SELECT_BY_ID, false, id );
            resultSet = preparedStatement.executeQuery();
            /* Parcours de la ligne de donnees de l'eventuel ResulSet retourne */
            if ( resultSet.next() ) {
                user = map( resultSet );
                user.setPassword( resultSet.getString("password"));
            }
        } catch ( SQLException e ) {
            throw new DAOException( e );
        } finally {
            fermeturesSilencieuses( resultSet, preparedStatement, connexion );
        }

        return user;
    }
    
    private static final String SQL_INSERT = "INSERT INTO User (`password`, `lastName`, `firstName`, `adr1`, `adr2`, `pc`, `town`, `phone`, `mail`) VALUES (?, ?, ?, ?,?, ?, ?, ?, ?);";
    private static final String SQL_CHECK_MAIL ="SELECT * FROM user where mail=?";
    @SuppressWarnings("resource")
	@Override
    public void create( User user ) throws DAOException, DAOExceptionMail {
        Connection connexion = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
 
            connexion = daoFactory.getConnection();
            preparedStatement = initialisationRequetePreparee( connexion, SQL_INSERT, true, user.getPassword(), user.getLastName(), user.getFirstName(), user.getAdr1(),user.getAdr2(),user.getPc(),user.getTown(),user.getPhone(),user.getMail());
            int statut = preparedStatement.executeUpdate();
           
            if ( statut == 0 ) {
            	
                throw new DAOException( "echec de la creation de l'utilisateur, aucune ligne ajoutee dans la table." );
            }

      
        } 
        catch (SQLIntegrityConstraintViolationException e) {
        	 try {
				preparedStatement = initialisationRequetePreparee( connexion, SQL_CHECK_MAIL, true,user.getMail());
				resultSet = preparedStatement.executeQuery();
				//System.out.println("id du result set :" + resultSet.getInt( "id" ));
				if(resultSet.getInt( "id" ) != 0){
					throw new DAOExceptionMail(e);
				}
			} catch (SQLException e1) {
				throw new DAOException( e1 );
			}
             
        	 throw new DAOException( e );
        }
        
        catch ( SQLException e ) {
        	e.printStackTrace();
            //throw new DAOException(e);
        } finally {
            fermeturesSilencieuses(preparedStatement, connexion );
        }
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
            /* Recuperation d'une connexion depuis la Factory */
            connexion = daoFactory.getConnection();
            preparedStatement = initialisationRequetePreparee( connexion, SQL_SELECT, false );
            resultSet = preparedStatement.executeQuery();
            /* Parcours de la ligne de donnees de l'eventuel ResulSet retourne */
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
	private static final String SQL_INSERT_TOKEN_TIMESTAMP = "UPDATE user SET token=?,timetamps=? WHERE id=?;";
	@SuppressWarnings("resource")
	@Override
	public User connection(String mail, String password,String token, Timestamp currentTimestamp) throws DAOException, LoginException {
		Connection connexion = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        User user = null;
        

        try {
            /* Recuperation d'une connexion depuis la Factory */
            connexion = daoFactory.getConnection();
            preparedStatement = initialisationRequetePreparee( connexion, SQL_CONNECTION, false, mail,password );
            resultSet = preparedStatement.executeQuery();
            /* Parcours de la ligne de donnees de l'eventuel ResulSet retourne */
            if ( resultSet.next() ) {
                user = map( resultSet );
            }
            if(user == null){
            	throw new LoginException("User not registrate");
            }
            preparedStatement = initialisationRequetePreparee( connexion, SQL_INSERT_TOKEN_TIMESTAMP, false, token,currentTimestamp,user.getId() );
            preparedStatement.executeUpdate();
            
            user.setToken( token);
            user.setTimetamps(currentTimestamp);
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
	                throw new DAOException( "Echec de la suppression de l'utilisateur." );
	            }
	      
	        } catch ( SQLException e ) {
	            throw new DAOException( e );
	        } finally {
	            fermeturesSilencieuses(preparedStatement, connexion );
	        }
	}
	
	
	private static final String SQL_UPDATE = "UPDATE User SET password=?,lastName=?,firstName=?,adr1=?,adr2=?,pc=?,town=?,phone=?,mail=? WHERE id=?;";
	@Override
	public void update(User user) {
		Connection connexion = null;
	     PreparedStatement preparedStatement = null;

	        try {
	 
	            connexion = daoFactory.getConnection();
	            preparedStatement = initialisationRequetePreparee( connexion, SQL_UPDATE, true,user.getPassword(), user.getLastName(), user.getFirstName(), user.getAdr1(),user.getAdr2(),user.getPc(),user.getTown(),user.getPhone(),user.getMail(),user.getId() );
	            int statut = preparedStatement.executeUpdate();
	           
	            if ( statut == 0 ) {
	                throw new DAOException( "Update Fail" );
	            }	      
	        } catch ( SQLException e ) {
	            throw new DAOException( e );
	        } finally {
	            fermeturesSilencieuses(preparedStatement, connexion );
	        }
		
	}
	
	 private static final String SQL_SELECT_BY_TOKEN = "SELECT * FROM User WHERE token=?";
	    @Override
		public
	    User findByToken( String token ) throws DAOException {
	        Connection connexion = null;
	        PreparedStatement preparedStatement = null;
	        ResultSet resultSet = null;
	        User user = null;

	        try {
	            /* Recuperation d'une connexion depuis la Factory */
	            connexion = daoFactory.getConnection();
	            preparedStatement = initialisationRequetePreparee( connexion, SQL_SELECT_BY_TOKEN, false, token );
	            resultSet = preparedStatement.executeQuery();
	            /* Parcours de la ligne de donnees de l'eventuel ResulSet retourne */
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
	   
    /*
	* For fill user with resulSet result 
    */
    private static User map( ResultSet resultSet ) throws SQLException {
        User user = new User();
        user.setId( resultSet.getInt( "id" ) );
        //user.setPassword( resultSet.getString("password"));
        user.setLastName( resultSet.getString( "lastName" ) );
        user.setFirstName( resultSet.getString( "firstName" ) );
        user.setAdr1( resultSet.getString( "adr1" ) );
        user.setAdr2( resultSet.getString( "adr2" ) );
        user.setPc( resultSet.getString( "pc" ) );
        user.setTown( resultSet.getString( "town" ) );
        user.setPhone( resultSet.getString( "phone" ) );
        user.setMail( resultSet.getString( "mail" ) );
        //Remove token et timestamps , juste pour les tests d auhtentifications !!*
        user.setToken(resultSet.getString( "token" ));
        user.setTimetamps(resultSet.getTimestamp("timetamps"));
        return user;
    }

	


	


}