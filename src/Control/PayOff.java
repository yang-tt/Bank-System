package Control;

import java.io.File;
import java.util.List;

/**
 * this class for users to pay off overdraft
 * @author YangTao
 *
 */
public class PayOff {
	private String accountNo, type, PIN, name, address, birthday, reserve, reserveDate, isSuspend;
	private double balance, overdraft, overdraftLimit, unclearedBalance;
	private int age;
	private String input;
	static String path1 = "src/Account.csv";
	
	/**
	 * pay off overdrft in this account
	 * @param accNo		account number
	 * @return flag
	 */
	public int pay(String accNo) {
		int flag = -2;
		List<String> accountList = ReadWriteData.read(new File(path1));
		if(accountList!=null && !accountList.isEmpty()) {
			for(int startLine = 1; startLine < accountList.size(); startLine++ ) {
				String line = accountList.get(startLine);
				String[] lineSplit = line.split(",");
				
				if (lineSplit[0].equals(accNo)) {
					balance = Double.valueOf(lineSplit[4].toString());
					overdraft = Double.valueOf(lineSplit[5].toString());
					
					if (lineSplit[1].equals("Current")) {
						if (overdraft == 0.0) {
							flag = 0;
							System.out.println("Your overdraft is 0!");	
							return flag;
						}	else {
							if ((balance - overdraft) < 0.0) {
								flag = 1;
								System.out.println("You don't have enough balance to pay off overdraft!");
								return flag;
							}	else if ((balance - overdraft) >= 0.0) {
								flag = 2;
								accountNo = lineSplit[0];
								type = lineSplit[1];
								PIN = lineSplit[2];
								name = lineSplit[3];
								balance = balance - overdraft;
								overdraft = 0.0;
								overdraftLimit = Double.valueOf(lineSplit[6].toString());
								address = lineSplit[7];
								birthday = lineSplit[8];
								age = Integer.valueOf(lineSplit[9].toString());
								unclearedBalance = Double.valueOf(lineSplit[10].toString());
								reserve = lineSplit[11];
								reserveDate = lineSplit[12];
								isSuspend = lineSplit[13];
								
								input = accNo + "," + type + "," + PIN + "," + name + "," + balance + "," + overdraft + "," + 
										overdraftLimit + "," + address + "," + birthday + "," + age + "," + unclearedBalance + "," +  
										reserve + "," + reserveDate + "," + isSuspend;
								accountList.add(input);
								accountList.remove(startLine);
								System.out.println("Success");
								ReadWriteData.write(new File(path1), accountList);
								return flag;
							}
						}
					}	else {
						flag = 3;
						System.out.println("You don't have this option!");
						return flag;
					}
				}
			}
		}
		return flag;
	}
	public static void main(String []args)
	{
		//String password= "88131";
		String UserID = "980";
		PayOff money = new PayOff();
		money.pay(UserID);
	}
}
