package be.model;

import java.time.LocalDate;
import java.util.Objects;

public class User {
	private String username;
	private String password;
	private String name;
	private String firstname;
	private String gender;
	private LocalDate birthdate;
	
	public User(String username, String password, String name, String firstname, String gender, LocalDate birthdate) {
		super();
		this.username = username;
		this.password = password;
		this.name = name;
		this.firstname = firstname;
		this.gender = gender;
		this.birthdate = birthdate;
	}
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getFirstname() {
		return firstname;
	}
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public LocalDate getBirthdate() {
		return birthdate;
	}
	public void setBirthdate(LocalDate birthdate) {
		this.birthdate = birthdate;
	}
	
	@Override
	public String toString() {
		return "User [username=" + username + ", password=" + password + ", name=" + name + ", firstname=" + firstname
				+ ", gender=" + gender + ", birthdate=" + birthdate + "]";
	}
	@Override
	public int hashCode() {
		return Objects.hash(birthdate, firstname, gender, name, password, username);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		return Objects.equals(birthdate, other.birthdate) && Objects.equals(firstname, other.firstname)
				&& Objects.equals(gender, other.gender) && Objects.equals(name, other.name)
				&& Objects.equals(password, other.password)
				&& Objects.equals(username, other.username);
	}
	
}
