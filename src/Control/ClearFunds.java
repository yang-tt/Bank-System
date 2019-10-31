package Control;

import java.io.File;
import java.util.List;
import java.util.TimerTask;

/**
 * This class is to clear uncleared funds 
 * @author YangTao
 *
 */
public class ClearFunds extends TimerTask {
	private String accountNo, type, PIN, name, address, birthday, reserve, reserveDate, isSuspend;
	private double balance, overdraft, overdraftLimit, unclearedBalance;
	private int age;
	private String input;
	static String path1 = "src/Account.csv";
	
	public void run() {
		List<String> accountList = ReadWriteData.read(new File(path1));
		if(accountList != null && !accountList.isEmpty()) {
			for(int startLine = 1; startLine < accountList.size(); startLine++ ) {
				String line = accountList.get(1);
				String[] lineSplit = line.split(",");
				balance = Double.valueOf(lineSplit[4].toString());
				unclearedBalance = Double.valueOf(lineSplit[10].toString());

				balance = balance + unclearedBalance;
				System.out.println(balance+"  "+unclearedBalance);
				unclearedBalance = 0.0;
		
				accountNo = lineSplit[0];
				type = lineSplit[1];
				PIN = lineSplit[2];
				name = lineSplit[3];
				overdraft = Double.valueOf(lineSplit[5].toString());
				overdraftLimit = Double.valueOf(lineSplit[6].toString());
				address = lineSplit[7];
				birthday = lineSplit[8];
				age = Integer.valueOf(lineSplit[9].toString());
				reserve = lineSplit[11];
				reserveDate = lineSplit[12];
				isSuspend = lineSplit[13];
				input = accountNo + "," + type + "," + PIN + "," + name + "," + balance + "," + overdraft + "," + 
						overdraftLimit + "," + address + "," + birthday + "," + age + "," + unclearedBalance + "," +  
						reserve + "," + reserveDate + "," + isSuspend;
				accountList.add(input);
				accountList.remove(1);
				ReadWriteData.write(new File(path1), accountList);
				System.out.println("Success");
			}
		}
	}
}

