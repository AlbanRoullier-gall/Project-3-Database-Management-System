package be.tests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import java.sql.Connection;
import java.time.LocalDate;
import org.junit.jupiter.api.Test;

import be.dao.UserSQL;
import be.model.User;
import be.singleton.BDDConnection;

public class UserSQLTest {
	
	
	
	@Test
	void getUserFromIDTest() {
		Connection conn;
		conn = BDDConnection.getInstance();
		UserSQL usersql = new UserSQL(conn);
		assertEquals(true, usersql.getFromID(3).isPresent());		
	}
	
	@Test
	void getListeWithRegExprTest() {
		Connection conn;
		conn = BDDConnection.getInstance();
		UserSQL usersql = new UserSQL(conn);
		assertEquals(false, usersql.getListe("^A").isEmpty());
	}
	
	@Test
	void insertTest() throws Exception{
		Connection conn;
		conn = BDDConnection.getInstance();
		UserSQL usersql = new UserSQL(conn);
		LocalDate birthdate = LocalDate.of(1991, 05, 10);
		User user = new User("Aliar", "JOC", "MELIUR", "Paul", "M", birthdate);
		assertEquals(true, usersql.insert(user));
	}
	
	@Test
	void deleteTest() throws Exception{
		Connection conn;
		conn = BDDConnection.getInstance();
		UserSQL usersql = new UserSQL(conn);
		LocalDate birthdate = LocalDate.of(1991, 05, 10);
		User user = new User("Aliar", "JOC", "MELIUR", "Paul", "M", birthdate);
		assertEquals(true, usersql.delete(user));
	}
	
	@Test
	void updateTest() throws Exception{
		Connection conn;
		conn = BDDConnection.getInstance();
		UserSQL usersql = new UserSQL(conn);
		LocalDate birthdate = LocalDate.of(1991, 05, 10);
		User user1 = new User("Aliar", "JOC", "MELIUR", "Paul", "M", birthdate);
		usersql.insert(user1);
		User user = new User("Aliar", "JOC", "MOLIER", "JEAN-JACQUE", "F", birthdate);
		assertEquals(true, usersql.update(user));
	}
	
	@Test
	void countTest() throws Exception{
		Connection conn;
		conn = BDDConnection.getInstance();
		UserSQL usersql = new UserSQL(conn);
		assert (usersql.count() > 0);
	}
	
	@Test
	void getListeFromBirthdateTest() throws Exception{
		Connection conn;
		conn = BDDConnection.getInstance();
		UserSQL usersql = new UserSQL(conn);
		assertEquals(false, usersql.getUserFromBirthdate(LocalDate.of(1991, 05, 10)).isEmpty());
	}
	
	
	
	
}
