package be.model;

import java.util.Objects;

public class Customer {
	private int customernumber;
	private String name;
	private String firstname;
	private String tvanumber;
	public Customer(int customernumber, String name, String firstname, String tvanumber) {
		super();
		this.customernumber = customernumber;
		this.name = name;
		this.firstname = firstname;
		this.tvanumber = tvanumber;
	}
	public int getCustomernumber() {
		return customernumber;
	}
	public void setCustomernumber(int customernumber) {
		this.customernumber = customernumber;
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
	public String getTvanumber() {
		return tvanumber;
	}
	public void setTvanumber(String tvanumber) {
		this.tvanumber = tvanumber;
	}
	@Override
	public int hashCode() {
		return Objects.hash(customernumber, firstname, name, tvanumber);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Customer other = (Customer) obj;
		return customernumber == other.customernumber && Objects.equals(firstname, other.firstname)
				&& Objects.equals(name, other.name) && Objects.equals(tvanumber, other.tvanumber);
	}
	@Override
	public String toString() {
		return "Customer [customernumber=" + customernumber + ", name=" + name + ", firstname=" + firstname
				+ ", tvanumber=" + tvanumber + "]";
	}
	
	
}
