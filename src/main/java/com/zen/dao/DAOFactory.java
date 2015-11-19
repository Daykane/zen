package com.zen.dao;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DAOFactory {

	//    private static final String FICHIER_PROPERTIES       = "/com/zen/dao/dao.properties";
	//    private static final String PROPERTY_URL             = "url";
	//    private static final String PROPERTY_DRIVER          = "driver";
	//    private static final String PROPERTY_NOM_UTILISATEUR = "nomutilisateur";
	//    private static final String PROPERTY_MOT_DE_PASSE    = "motdepasse";

	private String              url;
	private String              username;
	private String              password;

	DAOFactory( String url, String username, String password ) {
		this.url = url;
		this.username = username;
		this.password = password;
	}


	public static DAOFactory getInstance() throws DAOConfigurationException {
		//        Properties properties = new Properties();
		String driver = "com.mysql.jdbc.Driver";

		String url = "jdbc:mysql://" + System.getenv("OPENSHIFT_MYSQL_DB_HOST") + ":" + System.getenv("OPENSHIFT_MYSQL_DB_PORT") + "/" + System.getenv("OPENSHIFT_APP_NAME");
		String nomUtilisateur = System.getenv("OPENSHIFT_MYSQL_DB_USERNAME");
		String motDePasse = System.getenv("OPENSHIFT_MYSQL_DB_PASSWORD");
		System.out.println("1:"+url);
		System.out.println("2:"+nomUtilisateur);
		System.out.println("3:"+motDePasse);

		try {
			Class.forName( driver );
		} catch ( ClassNotFoundException e ) {
			throw new DAOConfigurationException( "Le driver est introuvable dans le classpath.", e );
		}

		DAOFactory instance = new DAOFactory( url, nomUtilisateur, motDePasse );
		return instance;
	}


	Connection getConnection() throws SQLException {
		return DriverManager.getConnection( url, username, password );
	}


	public UserDao getUserDao() {
		return new UserDaoImpl( this );
	}
	public ProductDao getProductDao() {
		return new ProductDaoImpl( this );
	}
	public CategoryDao getCategoryDao() {
		return new CategoryDaoImpl(this);
	}
	public ActivityDao getActivityDao() {
		return new ActivityDaoImpl(this);
	}
	public EventDao getEventDao() {
		return new EventDaoImpl(this);
	}
	public RoomDao getRoomDao() {
		return new RoomDaoImpl(this);
	}
	public AccessoryDao getAccessoryDao() {
		return new AccessoryDaoImpl(this);
	}
	

}