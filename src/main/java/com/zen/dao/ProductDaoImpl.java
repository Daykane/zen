package com.zen.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static com.zen.dao.DAOUtilitaire.*;
import com.zen.beans.Product;

public class ProductDaoImpl  implements ProductDao {
	private DAOFactory daoFactory;
	
	public ProductDaoImpl(DAOFactory daoFactory) {
		this.daoFactory = daoFactory;
	}

	private static final String SQL_INSERT = "INSERT INTO Product (`productName`, `productDescr`, `availableQuantity`, `memberReduction`) VALUES (?, ?, ?, ?);";
	@Override
	public void create(Product product) throws DAOException {
		Connection connexion = null;
        PreparedStatement preparedStatement = null;
        //ResultSet valeursAutoGenerees = null;

        try {
            connexion = daoFactory.getConnection();
            preparedStatement = initialisationRequetePreparee( connexion, SQL_INSERT, true, product.getProductName(), product.getProductDescr(), product.getAvailableQuantity(),product.getMemberReduction() );
            int statut = preparedStatement.executeUpdate();
            /* Analyse du statut retourné par la requête d'insertion */
            if ( statut == 0 ) {
                throw new DAOException( "Échec de la création de l'utilisateur, aucune ligne ajoutée dans la table." );
            }
            /*
            valeursAutoGenerees = preparedStatement.getGeneratedKeys();
            if ( valeursAutoGenerees.next() ) {
                product.setId( valeursAutoGenerees.getLong( 1 ) );
            } else {
                throw new DAOException( "Échec de la création de l'utilisateur en base, aucun ID auto-généré retourné." );
            }
            */
        } catch ( SQLException e ) {
            throw new DAOException( e );
        } finally {
            fermeturesSilencieuses(preparedStatement, connexion );
        }
		
	}

	private static final String SQL_SELECT_BY_ID = "SELECT * FROM Product WHERE productId = ?";
	@Override
	public Product find(String id) throws DAOException {
		Connection connexion = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Product product = null;

        try {
            /* Récupération d'une connexion depuis la Factory */
            connexion = daoFactory.getConnection();
            preparedStatement = initialisationRequetePreparee( connexion, SQL_SELECT_BY_ID, false, id );
            resultSet = preparedStatement.executeQuery();
            /* Parcours de la ligne de données de l'éventuel ResulSet retourné */
            if ( resultSet.next() ) {
                product = map( resultSet );
            }
        } catch ( SQLException e ) {
            throw new DAOException( e );
        } finally {
            fermeturesSilencieuses( resultSet, preparedStatement, connexion );
        }

        return product;
	}

	private static final String SQL_SELECT = "SELECT * FROM Product";
	@Override
	public List<Product> findAll() throws DAOException {
		Connection connexion = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Product product = null;
        List<Product> products = new ArrayList<Product>();

        try {
            /* Récupération d'une connexion depuis la Factory */
            connexion = daoFactory.getConnection();
            preparedStatement = initialisationRequetePreparee( connexion, SQL_SELECT, false );
            resultSet = preparedStatement.executeQuery();
            /* Parcours de la ligne de données de l'éventuel ResulSet retourné */
            while ( resultSet.next() ) {           
                product = map( resultSet );
                products.add(product);   
            }
        } catch ( SQLException e ) {
            throw new DAOException( e );
        } finally {
            fermeturesSilencieuses( resultSet, preparedStatement, connexion );
        }

        return products;
	}
	
	private static Product map( ResultSet resultSet ) throws SQLException {
        Product product = new Product();
        product.setProductId( resultSet.getInt( "productId" ) );
        product.setProductName(resultSet.getString( "productName" ));
        product.setProductDescr(resultSet.getString( "productDescr" ));
        product.setAvailableQuantity(resultSet.getInt( "availableQuantity" ));
        product.setMemberReduction(resultSet.getDouble( "memberReduction" ));
 
        return product;
    }
	
	private static final String SQL_DELETE = "DELETE FROM Product WHERE productId = ?";
	@Override
	public void delete(Product product) throws DAOException {
		Connection connexion = null;
        PreparedStatement preparedStatement = null;
        //ResultSet valeursAutoGenerees = null;

        try {
            connexion = daoFactory.getConnection();
            preparedStatement = initialisationRequetePreparee( connexion, SQL_DELETE, true, product.getProductId() );
            int statut = preparedStatement.executeUpdate();
            /* Analyse du statut retourné par la requête d'insertion */
            if ( statut == 0 ) {
                throw new DAOException( "Deleted failed" );
            }
        } catch ( SQLException e ) {
            throw new DAOException( e );
        } finally {
            fermeturesSilencieuses(preparedStatement, connexion );
        }
		
	}

	private static final String SQL_UPDATE = "UPDATE Product SET  productName = ?, productDescr = ?, availableQuantity = ?, memberReduction = ? WHERE productId = ?;";
	@Override
	public void update(int id, Product product) throws DAOException {
		Connection connexion = null;
        PreparedStatement preparedStatement = null;

        try {
            connexion = daoFactory.getConnection();
            preparedStatement = initialisationRequetePreparee( connexion, SQL_UPDATE, true,product.getProductName(), product.getProductDescr(),product.getAvailableQuantity(),product.getMemberReduction() ,id );
            System.out.println(preparedStatement);
            int statut = preparedStatement.executeUpdate();
            /* Analyse du statut retourné par la requête d'update */
            if ( statut == 0 ) {
                throw new DAOException( "Update failed" );
            }
        } catch ( SQLException e ) {
            throw new DAOException( e );
        } finally {
            fermeturesSilencieuses(preparedStatement, connexion );
        }
		
	}

}
