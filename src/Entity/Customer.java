package Entity;

import java.sql.Date;

public class Customer {
	private String accName;
	private String address;
	private Date birth;
	private boolean creditStatus;
	
	public Customer() {	}
	
	public Customer(String accName, String address, Date birth) {
		this.accName = accName;
		this.address = address;
		this.birth = birth;
	}
	
	public void setAccName(String accName) {
		this.accName = accName;
	}
	public String getAccName() {
		return accName;
	}
	
	public void setAddress(String address) {
		this.address = address;
	}
	public String getAddress() {
		return address;
	}
	
	public void setBirth(Date birth) {
		this.birth = birth;
	}
	public Date getBirth() {
		return birth;
	}
	
	public void setcreditStatus(boolean creditStatus) {
		this.creditStatus = creditStatus;
	}
	public boolean getcreditStatu() {
		return creditStatus;
	}
}
