package com.zen.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static com.zen.dao.DAOUtilitaire.*;

import com.zen.beans.Category;
import com.zen.beans.Product;

public class CategoryDaoImpl  implements CategoryDao {
	private DAOFactory daoFactory;
	
	public CategoryDaoImpl(DAOFactory daoFactory) {
		this.daoFactory = daoFactory;
	}

	private static final String SQL_INSERT = "INSERT INTO Category (`categoryName`, `categoryDesc`, `validationDate`) VALUES (?, ?, ?);";
	@Override
	public void create(Category category) throws DAOException {
		// TODO Auto-generated method stub
		Connection connexion = null;
        PreparedStatement preparedStatement = null;
        //ResultSet valeursAutoGenerees = null;

        try {
            connexion = daoFactory.getConnection();
            preparedStatement = initialisationRequetePreparee( connexion, SQL_INSERT, true, category.getCategoryName(),category.getCategoryDesc(),category.getValidationDate() );
            int statut = preparedStatement.executeUpdate();
            /* Analyse du statut retourne par la requete d'insertion */
            if ( statut == 0 ) {
                throw new DAOException( "Echec de la creation de l'utilisateur, aucune ligne ajoutEe dans la table." );
            }
        } catch ( SQLException e ) {
            throw new DAOException( e );
        } finally {
            fermeturesSilencieuses(preparedStatement, connexion );
        }
		
	}

	@Override
	public void delete(Category category) throws DAOException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(int id, Category category) throws DAOException {
		// TODO Auto-generated method stub
		
	}

	private static final String SQL_SELECT_BY_ID = "SELECT * FROM Category WHERE categoryId = ?";
	@Override
	public Category find(String id) throws DAOException {
		Connection connexion = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Category category = null;

        try {
            /* Recuperation d'une connexion depuis la Factory */
            connexion = daoFactory.getConnection();
            preparedStatement = initialisationRequetePreparee( connexion, SQL_SELECT_BY_ID, false, id );
            resultSet = preparedStatement.executeQuery();
            /* Parcours de la ligne de donnees de l'eventuel ResulSet retourne */
            if ( resultSet.next() ) {
                category = map( resultSet );
            }
        } catch ( SQLException e ) {
            throw new DAOException( e );
        } finally {
            fermeturesSilencieuses( resultSet, preparedStatement, connexion );
        }

        return category;
	}
	
	private static final String SQL_SELECT = "SELECT * FROM Category";
	@Override
	public List<Category> findAll() throws DAOException {
		// TODO Auto-generated method stub
		return null;
	}
	
	private static Category map( ResultSet resultSet ) throws SQLException {
        Category category = new Category();
        category.setCategoryId( resultSet.getInt( "categoryId" ) );
        category.setCategoryName(resultSet.getString( "categoryName" ));
        category.setCategoryDescr(resultSet.getString( "categoryDesc" ));

        return category;
    }

	

}
