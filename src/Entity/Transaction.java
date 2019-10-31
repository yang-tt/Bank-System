package Entity;

import java.util.Date; 
import java.util.Calendar;
import java.text.SimpleDateFormat;

public class Transaction {
	private double amount;
	private String accNo;
	private String time;
	
	public Transaction(double amount) { 
		this.amount = amount;
		Date now = new Date(); 
		SimpleDateFormat dateFormat = new SimpleDateFormat();// 格式化时间 
		dateFormat.applyPattern("yyyy-MM-dd HH:mm:ss a");// a为am/pm的标记  
        this.time = dateFormat.format(now);
	}
	
	public void setAmount(double amount) {
		this.amount = amount;
	}
	public double getAmount() {
		return amount;
	}
	
	public void setAccNo(String accNo) {
		this.accNo = accNo;
	}
	public String getAccNo() {
		return accNo;
	}
	
	public void setTime(String time) {
		this.time = time;
	}
	public String getTime() {
		return time;
	}
	
	public String toString() {
		return "Transaction amount: " + amount +"\n" + "Transaction time:" + time;
	}
}
