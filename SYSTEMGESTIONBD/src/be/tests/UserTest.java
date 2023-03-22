package be.tests;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.junit.jupiter.api.Test;

import be.model.User;

public class UserTest {
	@Test
	void TestUserConstructor(){
		
		String date= "28-04-1992";
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
		LocalDate localDate =  LocalDate.parse(date, formatter);
		User user = new User("AlbanRoullier-gall", "1AJ4", "Roullier", "Alban", "M", LocalDate.parse("28-04-1992", formatter));
		assertEquals(localDate, user.getBirthdate());
	}
}
