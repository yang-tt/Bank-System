package Control;

import java.io.File;
import java.util.List;

/**
 * this class for users to overdraft
 * @author YangTao
 *
 */
public class Overdraft {
	private String accountNo, type, PIN, name, address, birthday, reserve, reserveDate, isSuspend;
	private double balance, overdraft, overdraftLimit, unclearedBalance;
	private int age;
	private String input;
	static String path1 = "src/Account.csv";
	
	/**
	 * overdrft the input amount of money from this account
	 * @param accNo		account number
	 * @param amount	the amount of money
	 * @return	flag
	 */
	public int overdraft(String accNo, String amount) {
		int flag = -1;
		List<String> accountList = ReadWriteData.read(new File(path1));
		if(accountList!=null && !accountList.isEmpty()) {
			for(int startLine = 1; startLine < accountList.size(); startLine++ ) {
				String line = accountList.get(startLine);
				String[] lineSplit = line.split(",");
				if (lineSplit[0].equals(accNo)) {
					type = lineSplit[1];
					if(type.equals("Current")) {
						accountNo = lineSplit[0];
						type = lineSplit[1];
						PIN = lineSplit[2];
						name = lineSplit[3];
						balance = Double.valueOf(lineSplit[4].toString());
						overdraft = Double.valueOf(lineSplit[5].toString());
						overdraftLimit = Double.valueOf(lineSplit[6].toString());
						
						Double value = Double.valueOf(amount);
						if (value <= (overdraftLimit - overdraft)) {
							overdraft = overdraft + value;
							System.out.println(overdraft);
							flag = 1;
						}	else {
							flag = 2;
							System.out.println("no enough limit!");
							break;
						}
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
						System.out.println("hi");
						ReadWriteData.write(new File(path1), accountList);
						break;
					} else {
						flag = 3;
						System.out.println("You can't overdraft!");
						break;
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
		Overdraft checkObject = new Overdraft();
		checkObject.overdraft(UserID,"100");
	}
}
