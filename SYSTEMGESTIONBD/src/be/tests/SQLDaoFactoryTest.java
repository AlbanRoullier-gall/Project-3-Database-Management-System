package be.tests;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import be.dao.UserSQL;
import be.factorySQL.SQLDaoFactory;

public class SQLDaoFactoryTest {
	
	@Test
	void testgetUserDAO(){
		SQLDaoFactory sqlDaoFactory = new SQLDaoFactory();
		assertTrue(sqlDaoFactory.getUserDAO() instanceof UserSQL);
		}
}
