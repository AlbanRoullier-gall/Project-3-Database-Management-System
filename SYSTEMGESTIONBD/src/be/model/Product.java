package be.model;

import java.util.Objects;

public class Product {
	private String productName;
	private float unitprice;
	public Product(String productName, float unitprice) {
		super();
		this.productName = productName;
		this.unitprice = unitprice;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public float getUnitprice() {
		return unitprice;
	}
	public void setUnitprice(float unitprice) {
		this.unitprice = unitprice;
	}
	@Override
	public int hashCode() {
		return Objects.hash(productName, unitprice);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Product other = (Product) obj;
		return Objects.equals(productName, other.productName)
				&& Float.floatToIntBits(unitprice) == Float.floatToIntBits(other.unitprice);
	}
	@Override
	public String toString() {
		return "Product [productName=" + productName + ", unitprice=" + unitprice + "]";
	}
	
	
}
