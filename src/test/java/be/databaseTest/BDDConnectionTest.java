package be.databaseTest;

import static org.junit.jupiter.api.Assertions.*;
import java.sql.Connection;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import be.database.BDDConnection;

class BDDConnectionTest {
	
	@BeforeEach
	void testConnection(){
		Connection conn;
		conn = BDDConnection.getInstance();
		assertNotEquals(null, conn);
		
	}
	
	@Test
	void testConnectionIfNull(){
		
		Connection conn = null;
		conn = BDDConnection.getInstance();
		assertNotEquals(null, conn);
		
	}
	
	@Test
	void testConnectionIfNotNull(){
		
		Connection conn;
		conn = BDDConnection.getInstance();
		assertNotEquals(null, conn);
			
		}
	
	
	
}
