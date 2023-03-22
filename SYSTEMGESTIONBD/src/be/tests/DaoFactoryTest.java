package be.tests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import be.abstractDaoFactory.DaoFactory;
import be.abstractDaoFactory.Databases;
import be.factorySQL.SQLDaoFactory;


public class DaoFactoryTest {
	@Test
	void testDatabaseSelection() {
		
		DaoFactory databaseSelection;

		databaseSelection = DaoFactory.getFactory(Databases.SQL);
		
		assertTrue(databaseSelection instanceof SQLDaoFactory);
		
	}
	
	@Test
	void testDatabaseSelectionNull() {
		
		DaoFactory databaseSelection;

		databaseSelection = DaoFactory.getFactory(Databases.H2);
		
		assertEquals(null, databaseSelection);
	}
}
