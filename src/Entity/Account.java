package Entity;

/**
 * 
 * @author YangTao
 *
 */
public class Account {
	private String accName;
	private String type;
	private int accNo;	//unique
	private int PIN;
	private double balance;
	private double overdraftLimit;
	private boolean isSuspended;
	private boolean isActive;
	private boolean noticeNeeded;
	
	public Account(String accName, int accNo, int PIN, double balance, boolean isSuspended) {
		this.accName = accName;
		this.accNo = accNo;
		this.PIN = PIN;
		this.balance = balance;
		this.isSuspended = isSuspended;
	}
	
	public void setAccName(String accName) {
		this.accName = accName;
	}
	public String getAccName() {
		return accName;
	}
	

	public void setType(String type) {
		this.type = type;
	}	
	public String getType() {
		return type;
	}
	
	public void setAccNo(int accNo) {
		this.accNo = accNo;
	}
	public int getAccNo() {
		return accNo;
	}
	
	public void setPIN(int PIN) {
		this.PIN = PIN;
	}
	public int getPIN() {
		return PIN;
	}
	
	public void setBalance(double balance) {
		this.balance = balance;
	}
	public double getBalance() {
		return balance;
	}
	
	public void setOverdraftLimit(double overdraftLimit) {
		this.overdraftLimit = overdraftLimit;
	}
	public double getOverdraftLimit() {
		return overdraftLimit;
	}
	
	public void setSuspended(boolean isSuspended) {
		this.isSuspended = isSuspended;
	}
	public boolean isSuspended() {
		return isSuspended;
	}
	
	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}
	public boolean isActive() {
		return isActive;
	}
	
	public void setNoticeNeeded(boolean noticeNeeded) {
		this.noticeNeeded = noticeNeeded;
	}
	public boolean getNoticeNeeded() {
		return noticeNeeded;
	}
	
}

	