package Entity;

import java.sql.Date;

public class AccountSaver extends Account{
	private Date noticeDate;
	private double noticeAmount;
	
	public AccountSaver(String accName, int accNo, int PIN, double balance, boolean isSuspended) {
		super(accName, accNo, PIN, balance, isSuspended);
		// TODO Auto-generated constructor stub
	}

}
