package Control;

import java.io.File;
import java.util.List;

/**
 * This class is for users to deposit funds
 * @author YangTao
 *
 */
public class DepositFunds {
	private String accountNo, type, PIN, name, address, birthday, reserve, reserveDate, isSuspend;
	private double balance, overdraft, overdraftLimit, unclearedBalance;
	private int age;
	private String input;
	static String path1 = "src/Account.csv";
	
	/**
	 * add the input amount of money into this account 
	 * @param accNo		account number
	 * @param amount	the amount of money
	 * @param isClear	the type of money
	 * @return	flag
	 */
	public int addMoney(String accNo, String amount, Boolean isClear) {
		int flag = -2;
		List<String> accountList = ReadWriteData.read(new File(path1));
		if(accountList!=null && !accountList.isEmpty()) {
			for(int startLine = 1; startLine < accountList.size(); startLine++ ) {
				String line = accountList.get(startLine);
				String[] lineSplit = line.split(",");
				if (lineSplit[0].equals(accNo)) {
					accountNo = lineSplit[0];
					type = lineSplit[1];
					PIN = lineSplit[2];
					name = lineSplit[3];
					double value = Double.valueOf(amount.toString());
					double save = Double.valueOf(lineSplit[4].toString());
					overdraft = Double.valueOf(lineSplit[5].toString());
					if (isClear) {
						balance = save + value;
						overdraft = Double.valueOf(lineSplit[5].toString());
						unclearedBalance = Double.valueOf(lineSplit[10].toString());
						flag = 1;
					} else {
						balance = Double.valueOf(lineSplit[4].toString());
						overdraft = Double.valueOf(lineSplit[5].toString());
						unclearedBalance = Double.valueOf(lineSplit[10].toString()) + value;
						System.out.println("unclearedBalance");
						flag = 2;
					}
					
					overdraftLimit = Double.valueOf(lineSplit[6].toString());
					address = lineSplit[7];
					birthday = lineSplit[8];
					age = Integer.valueOf(lineSplit[9].toString());
					
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
				}
			}
		}
		return flag;
	}
	public static void main(String []args)
	{
		String UserID = "980";
		DepositFunds checkObject = new DepositFunds();
		checkObject.addMoney(UserID,"500", true);
	}
}
