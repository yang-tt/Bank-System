package Control;

import java.io.File;
import java.util.List;

/**
 * This class is for users to withdraw funds
 * @author YangTao
 *
 */
public class WidhdrawFunds {
	private String accountNo, type, PIN, name, address, birthday, reserve, reserveDate, isSuspend;
	private double balance, overdraft, overdraftLimit, unclearedBalance;
	private int age;
	private String input;
	static String path1 = "src/Account.csv";
	
	/**
	 * get the input amount of money from this account
	 * @param accNo
	 * @param amount
	 * @return
	 */
	public int getMoney(String accNo, String amount) {
		int flag = -2;
		List<String> accountList = ReadWriteData.read(new File(path1));
		if(accountList!=null && !accountList.isEmpty()) {
			for(int startLine = 1; startLine < accountList.size(); startLine++ ) {
				String line = accountList.get(startLine);
				String[] lineSplit = line.split(",");
				if (lineSplit[0].equals(accNo)) {
						if (lineSplit[1].equals("Saver")) {
							if (!Boolean.parseBoolean(lineSplit[11])) {
								
								System.out.println("You should reserve before make withdraw.");
								flag = 3;
								break;
							}	else {
								reserveDate = lineSplit[12];
								String[] dateSplit = reserveDate.split("-");
								String dateString = dateSplit[0] + dateSplit[1] + dateSplit[2];
								int date = Integer.valueOf(dateString.toString());
								String todayString = CountAge.getDate();
								String[] todaySplit = todayString.split("-");
								String todaystring = todaySplit[0] + todaySplit[1] +todaySplit[2];
								int today = Integer.valueOf(todaystring.toString());
								if (today >= date) {
									accountNo = lineSplit[0];
									type = lineSplit[1];
									PIN = lineSplit[2];
									name = lineSplit[3];
									double value = Double.valueOf(amount.toString());
									double save = Double.valueOf(lineSplit[4].toString());
									overdraft = Double.valueOf(lineSplit[5].toString());
									if (value > save) {
										flag = 1;
										System.out.println("no enough money!");
										break;
										
									} else {
										balance = save - value;
										
										System.out.println("okk");
										flag = 2;
									}
									overdraft = Double.valueOf(lineSplit[5].toString());
									overdraftLimit = Double.valueOf(lineSplit[6].toString());
									address = lineSplit[7];
									birthday = lineSplit[8];
									age = Integer.valueOf(lineSplit[9].toString());
									unclearedBalance = Double.valueOf(lineSplit[10].toString());
									reserve = "false";
									reserveDate = "null";
									isSuspend = lineSplit[13];
									
									
									input = accNo + "," + type + "," + PIN + "," + name + "," + balance + "," + overdraft + "," + 
											overdraftLimit + "," + address + "," + birthday + "," + age + "," + unclearedBalance + "," +  
											reserve + "," + reserveDate + "," + isSuspend;
									accountList.add(input);
									accountList.remove(startLine);
									System.out.println("hi");
									ReadWriteData.write(new File(path1), accountList);
									break;
								}	else {
									flag = 4;
									System.out.println("please wait");
									break;
								}
								
							}
						}
						
						accountNo = lineSplit[0];
						type = lineSplit[1];
						PIN = lineSplit[2];
						name = lineSplit[3];
						double value = Double.valueOf(amount.toString());
						double save = Double.valueOf(lineSplit[4].toString());
						overdraft = Double.valueOf(lineSplit[5].toString());
						if (value > save) {
							flag = 1;
							System.out.println("no enough money!");
							break;
							
						} else {
							balance = save - value;
							
							System.out.println("okk");
							flag = 2;
						}
						overdraft = Double.valueOf(lineSplit[5].toString());
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
		//String password= "88131";
		String UserID = "123";
		WidhdrawFunds checkObject = new WidhdrawFunds();
		checkObject.getMoney(UserID,"1");
	}
}
