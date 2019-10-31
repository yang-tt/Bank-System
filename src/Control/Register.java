package Control;

import java.io.File;
import java.util.List;
import java.util.Random;

/**
 * this class is to control the register process.
 * @author YangTao
 *
 */
public class Register {
	private double balance = 0.0;
	private double overdraft = 0.0;
	private double overdraftLimit;
	private double unclearedBalance = 0.0;
	private boolean isReserve = false;
	private boolean isSuspend = false;
	private String reserveDate = null;
	private String input;
	static String path1 = "src/Account.csv";
	
	/**
	 * check if register succeddfully
	 * @param accNo		account Number
	 * @param type		account type
	 * @param name		user name
	 * @param address	user address
	 * @param birthday	user birthday
	 * @return	flag
	 */
	public String addAccount(String accNo, String type, String name, String address, String birthday) {
		String back = "";
		int flag = 0;
		
		String pin = generatePIN();
		
		List<String> accountList = ReadWriteData.read(new File(path1));
		if(accountList!=null && !accountList.isEmpty()) {
			for(int startLine = 1; startLine < accountList.size(); startLine++ ){
				String line = accountList.get(startLine);
				String[] lineSplit = line.split(",");
				if (accNo.equals(lineSplit[0])) {
					System.out.println("Same account number!");
					back = flag + ";";
					return back;
					//return flag;
				}
				while (pin.equals(lineSplit[2])) {
					System.out.println("Same PIN!");
					pin = generatePIN();
				}
			}
			long days = CountAge.getDistanceDays(birthday, CountAge.getDate());
			int age = Math.round(days / 365);
			if (type.equals("Junior") && age > 16) {
				flag = -1;
				System.out.println("Age!");
				back = flag + ";" ;
				return back;
			}
			
			if (type.equals("Current")) {
				overdraftLimit = 500.0;
			}
			
			
			input = accNo + "," + type + "," + pin + "," + name + "," + balance + "," + overdraft + "," + 
					overdraftLimit + "," + address + "," + birthday + "," + age + "," + unclearedBalance + "," +  
					isReserve + "," + reserveDate + "," + isSuspend;
			accountList.add(input);
	 		ReadWriteData.write(new File(path1), accountList);
	 		flag = 1;
	 		back = flag + ";" + pin;
			return back;
			
		} else {
			flag = -2;
			back = flag + ";";
			return back;
		}
	}
	
	/**
	 * generate a random PIN
	 * @return a random PIN
	 */
	public String generatePIN() {
		Random rand = new Random();
		String section1;
		String section2;
		section1 = String.valueOf(rand.nextInt(999));
		section2 = String.valueOf(rand.nextInt(999));
		String pin = section1 + section2;
		return pin;
	}
	/*public static void main(String []args)
	{
		String accNo = "998";
		String type = "Junior";
		String name = "999";
		String address = "999";
		String birthday = "2000-08-20";
		Register user = new Register();
		user.addAccount(accNo, type, name, address, birthday);
	}*/
}
