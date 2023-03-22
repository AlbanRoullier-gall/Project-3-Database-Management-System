package be.abstractDaoFactory;

import java.sql.Connection;

import be.dao.InterfaceUserDAO;
import be.factorySQL.SQLDaoFactory;


public abstract class DaoFactory {
	
	public abstract Connection getConnection();
	
	public static DaoFactory getFactory(Databases database) {
		switch (database) {
		
		case SQL:
			return new SQLDaoFactory();
			
		default:
			return null;
		}
	}
	
	public abstract InterfaceUserDAO getUserDAO();
	
	
}
