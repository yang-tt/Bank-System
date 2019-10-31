package Control;

import java.io.File;
import java.util.List;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * this class for users to make a notice
 * @author YangTao
 *
 */
public class MakeNotice {
	private String accountNo, type, PIN, name, address, birthday, reserve, reserveDate, isSuspend;
	private double balance, overdraft, overdraftLimit, unclearedBalance;
	private int age;
	private String input;
	static String path1 = "src/Account.csv";
	
	/**
	 * Check if this account should make a notice and the process of reservation is successful
	 * @param accNo		account number
	 * @return a String contains flag and date of reservation
	 */
	public String reserve(String accNo) {
		String back = "";
		int flag = -2;
		List<String> accountList = ReadWriteData.read(new File(path1));
		if(accountList != null && !accountList.isEmpty()) {
			for(int startLine = 1; startLine < accountList.size(); startLine++ ) {
				String line = accountList.get(startLine);
				String[] lineSplit = line.split(",");
				if (lineSplit[0].equals(accNo)) {
						if (lineSplit[1].equals("Saver")) {
							if (!Boolean.parseBoolean(lineSplit[11])) {

								String[] strNow = new SimpleDateFormat("yyyy-MM-dd").format(new Date()).toString().split("-");
								Integer year = Integer.parseInt(strNow[0]);
								Integer month = Integer.parseInt(strNow[1]);
								Integer day = Integer.parseInt(strNow[2]) + 2;
								String reservateDate = strNow[0] + "-" + strNow[1] + "-" + day;
								
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
								reserve = "true";
								reserveDate = reservateDate;
								isSuspend = lineSplit[13];
								
								flag = 1;
								back = flag + ";" + reserveDate;
								
								input = accNo + "," + type + "," + PIN + "," + name + "," + balance + "," + overdraft + "," + 
										overdraftLimit + "," + address + "," + birthday + "," + age + "," + unclearedBalance + "," +  
										reserve + "," + reserveDate + "," + isSuspend;
								accountList.add(input);
								accountList.remove(startLine);
								System.out.println("hi");
								ReadWriteData.write(new File(path1), accountList);
								break;
							}	else {
								flag = 2;
								back = flag + ";" + lineSplit[12];
								System.out.println("You've already make a notice, and you can withdraw in or after " + lineSplit[12]);
								break;
							}
							
						}	else {
							flag = 3;
							back = flag + ";" + "No";
							System.out.println("You don't need reservation.");
							break;
						}
				}
			}
		}
		return back;
	}
	
	public static void main(String []args)
	{
		String UserID = "123";
		MakeNotice checkObject = new MakeNotice();
		checkObject.reserve(UserID);
	}
}
