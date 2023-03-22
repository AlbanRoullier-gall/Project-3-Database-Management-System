package be.model;

import java.time.LocalDate;
import java.util.Objects;

public class Bill {
	private LocalDate billdate;
	private int billnumber;
	private float vattotal;
	private float vatexclude;
	private float totalPrice;
	public Bill(int billnumber, LocalDate billdate, float vattotal, float vatexclude, float totalPrice) {
		super();
		this.billnumber = billnumber;
		this.billdate = billdate;
		this.vattotal = vattotal;
		this.vatexclude = vatexclude;
		this.totalPrice = totalPrice;
	}
	
	public int getBillnumber() {
		return billnumber;
	}
	public void setBillnumber(int billnumber) {
		this.billnumber = billnumber;
	}
	public LocalDate getBilldate() {
		return billdate;
	}
	public void setBilldate(LocalDate billdate) {
		this.billdate = billdate;
	}
	public float getVattotal() {
		return vattotal;
	}
	public void setVattotal(float vattotal) {
		this.vattotal = vattotal;
	}
	public float getVatexclude() {
		return vatexclude;
	}
	public void setVatexclude(float vatexclude) {
		this.vatexclude = vatexclude;
	}
	public float getTotalPrice() {
		return totalPrice;
	}
	public void setTotalPrice(float totalPrice) {
		this.totalPrice = totalPrice;
	}

	@Override
	public int hashCode() {
		return Objects.hash(billdate, billnumber, totalPrice, vatexclude, vattotal);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Bill other = (Bill) obj;
		return Objects.equals(billdate, other.billdate) && billnumber == other.billnumber
				&& Float.floatToIntBits(totalPrice) == Float.floatToIntBits(other.totalPrice)
				&& Float.floatToIntBits(vatexclude) == Float.floatToIntBits(other.vatexclude)
				&& Float.floatToIntBits(vattotal) == Float.floatToIntBits(other.vattotal);
	}

	@Override
	public String toString() {
		return "Bill [billdate=" + billdate + ", billnumber=" + billnumber + ", vattotal=" + vattotal + ", vatexclude="
				+ vatexclude + ", totalPrice=" + totalPrice + "]";
	}
	
	
	
	
}
