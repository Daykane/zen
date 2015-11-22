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

	private static final String SQL_INSERT = "INSERT INTO Product (`productName`,`categoryId`, `productDescr`, `availableQuantity`, `memberReduction`) VALUES (?,?, ?, ?, ?);";
	@Override
	public void create(Product product) throws DAOException {
		Connection connexion = null;
        PreparedStatement preparedStatement = null;
        //ResultSet valeursAutoGenerees = null;

        try {
            connexion = daoFactory.getConnection();
            preparedStatement = initialisationRequetePreparee( connexion, SQL_INSERT, true, product.getProductName(),product.getCategoryProduct(), product.getProductDescr(), product.getAvailableQuantity(),product.getMemberReduction() );
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

	private static final String SQL_SELECT_BY_ID = "SELECT * FROM Product WHERE productId = ?";
	@Override
	public Product find(String id) throws DAOException {
		Connection connexion = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Product product = null;

        try {
            /* Recuperation d'une connexion depuis la Factory */
            connexion = daoFactory.getConnection();
            preparedStatement = initialisationRequetePreparee( connexion, SQL_SELECT_BY_ID, false, id );
            resultSet = preparedStatement.executeQuery();
            /* Parcours de la ligne de donnees de l'eventuel ResulSet retourne */
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
            /* Recuperation d'une connexion depuis la Factory */
            connexion = daoFactory.getConnection();
            preparedStatement = initialisationRequetePreparee( connexion, SQL_SELECT, false );
            resultSet = preparedStatement.executeQuery();
            /* Parcours de la ligne de donnees de l'eventuel ResulSet retourne */
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
        product.setCategoryProduct(resultSet.getInt( "categoryId" ));
        product.setPrice(resultSet.getDouble("price"));
 
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
            /* Analyse du statut retourne par la requete d'insertion */
            if ( statut == 0 ) {
                throw new DAOException( "Deleted failed" );
            }
        } catch ( SQLException e ) {
            throw new DAOException( e );
        } finally {
            fermeturesSilencieuses(preparedStatement, connexion );
        }
		
	}

	private static final String SQL_UPDATE = "UPDATE Product SET  productName = ?, productDescr = ?, availableQuantity = ?, memberReduction = ?, price = ? WHERE productId = ?;";
	@Override
	public void update(int id, Product product) throws DAOException {
		Connection connexion = null;
        PreparedStatement preparedStatement = null;

        try {
            connexion = daoFactory.getConnection();
            preparedStatement = initialisationRequetePreparee( connexion, SQL_UPDATE, true,product.getProductName(), product.getProductDescr(),product.getAvailableQuantity(),product.getMemberReduction(),product.getPrice() ,id );
            System.out.println(preparedStatement);
            int statut = preparedStatement.executeUpdate();
            /* Analyse du statut retourne par la requete d'update */
            if ( statut == 0 ) {
                throw new DAOException( "Update failed" );
            }
        } catch ( SQLException e ) {
            throw new DAOException( e );
        } finally {
            fermeturesSilencieuses(preparedStatement, connexion );
        }
		
	}

	private static final String SQL_SELECT_BY_CATEGORY = "SELECT * FROM Product WHERE categoryId = ?";
	@Override
	public List<Product> findByCategory(String id) {
		Connection connexion = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Product product = null;
        List<Product> products = new ArrayList<Product>();

        try {
            /* Recuperation d'une connexion depuis la Factory */
            connexion = daoFactory.getConnection();
            preparedStatement = initialisationRequetePreparee( connexion, SQL_SELECT_BY_CATEGORY, false,id );
            resultSet = preparedStatement.executeQuery();
            /* Parcours de la ligne de donnees de l'eventuel ResulSet retourne */
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

	private static final String SQL_SELECT_BY_CAT_BY_ID = "SELECT * FROM Product WHERE categoryId = ? AND productId = ?";
	@Override
	public Product findByCategoryById(String id, String idP) {
		Connection connexion = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Product product = null;

        try {
            /* Recuperation d'une connexion depuis la Factory */
            connexion = daoFactory.getConnection();
            preparedStatement = initialisationRequetePreparee( connexion, SQL_SELECT_BY_CAT_BY_ID, false, id,idP);
            resultSet = preparedStatement.executeQuery();
            /* Parcours de la ligne de donnees de l'eventuel ResulSet retourne */
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

}
