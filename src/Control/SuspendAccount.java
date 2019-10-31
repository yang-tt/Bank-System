package Control;

import java.io.File;
import java.util.List;

/**
 * This class is to suspend and unsuspend accounts.
 * @author YangTao
 *
 */
public class SuspendAccount {
	private String accountNo, type, PIN, name, address, birthday, reserve, reserveDate;
	private boolean isSuspend;
	private double balance, overdraft, overdraftLimit, unclearedBalance;
	private int age;
	private String input;
	static String path1 = "src/Account.csv";
	
	/**
	 * suspend account
	 * @param accNo account number
	 * @return flag
	 */
	public int suspend(String accNo) {
		int flag = -2;
		List<String> accountList = ReadWriteData.read(new File(path1));
		if(accountList!=null && !accountList.isEmpty()) {
			for(int startLine = 1; startLine < accountList.size(); startLine++ ) {
				String line = accountList.get(startLine);
				String[] lineSplit = line.split(",");
				
				if (lineSplit[0].equals(accNo)) {
					if (!Boolean.parseBoolean(lineSplit[13])) {
						flag = 1;
						accountNo = lineSplit[0];
						type = lineSplit[1];
						PIN = lineSplit[2];
						name = lineSplit[3];
						balance = Double.valueOf(lineSplit[4].toString());
						overdraft = Double.valueOf(lineSplit[5].toString());
						overdraftLimit = Double.valueOf(lineSplit[6].toString());
						address = lineSplit[7];
						birthday = lineSplit[8];
						age = Integer.valueOf(lineSplit[9].toString());
						unclearedBalance = Double.valueOf(lineSplit[10].toString());
						reserve = lineSplit[11];
						reserveDate = lineSplit[12];
						isSuspend = true;
						
						input = accNo + "," + type + "," + PIN + "," + name + "," + balance + "," + overdraft + "," + 
								overdraftLimit + "," + address + "," + birthday + "," + age + "," + unclearedBalance + "," +  
								reserve + "," + reserveDate + "," + isSuspend;
						accountList.add(input);
						accountList.remove(startLine);
						System.out.println("success");
						ReadWriteData.write(new File(path1), accountList);
						break;
					}	else {
						flag = 2;
						System.out.println("You've already suspend this account");
						break;
					}
					
				}
			}
		}
		return flag;
	}
	
	/**
	 * unsuspend account
	 * @param accNo	account number
	 * @return flag
	 */
	public int unsuspend(String accNo) {
		int flag = -2;
		List<String> accountList = ReadWriteData.read(new File(path1));
		if(accountList!=null && !accountList.isEmpty()) {
			for(int startLine = 1; startLine < accountList.size(); startLine++ ) {
				String line = accountList.get(startLine);
				String[] lineSplit = line.split(",");
				
				if (lineSplit[0].equals(accNo)) {
					if (Boolean.parseBoolean(lineSplit[13])) {
						flag = 1;
						accountNo = lineSplit[0];
						type = lineSplit[1];
						PIN = lineSplit[2];
						name = lineSplit[3];
						balance = Double.valueOf(lineSplit[4].toString());
						overdraft = Double.valueOf(lineSplit[5].toString());
						overdraftLimit = Double.valueOf(lineSplit[6].toString());
						address = lineSplit[7];
						birthday = lineSplit[8];
						age = Integer.valueOf(lineSplit[9].toString());
						unclearedBalance = Double.valueOf(lineSplit[10].toString());
						reserve = lineSplit[11];
						reserveDate = lineSplit[12];
						isSuspend = false;
						
						input = accNo + "," + type + "," + PIN + "," + name + "," + balance + "," + overdraft + "," + 
								overdraftLimit + "," + address + "," + birthday + "," + age + "," + unclearedBalance + "," +  
								reserve + "," + reserveDate + "," + isSuspend;
						accountList.add(input);
						accountList.remove(startLine);
						System.out.println("success");
						ReadWriteData.write(new File(path1), accountList);
						break;
					}	else {
						flag = 2;
						System.out.println("You've already unsuspend this account");
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
		String UserID = "123";
		SuspendAccount checkObject = new SuspendAccount();
		checkObject.unsuspend(UserID);
	}
}
