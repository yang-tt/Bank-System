package Control;
import java.io.File;
import java.util.List;

/**
 * This class is to close account.
 * @author YangTao
 *
 */
public class CloseAccount {
	private String accountNo, type, PIN, name, address, birthday, reserve, reserveDate, isSuspend;
	private double balance, overdraft, overdraftLimit, unclearedBalance;
	private int age;
	private String input;
	static String path1 = "src/Account.csv";
	
	/**
	 * check if close account successfully.
	 * @param accNo
	 * @return a flag
	 */
	public int closeAccount(String accNo) {
		int flag = -2;
		List<String> accountList = ReadWriteData.read(new File(path1));
		if(accountList!=null && !accountList.isEmpty()) {
			for(int startLine = 1; startLine < accountList.size(); startLine++ ) {
				String line = accountList.get(startLine);
				String[] lineSplit = line.split(",");
				if (lineSplit[0].equals(accNo)) {
					balance = Double.valueOf(lineSplit[4].toString());
					overdraft = Double.valueOf(lineSplit[5].toString());
					unclearedBalance = Double.valueOf(lineSplit[10].toString());
					if ((balance == 0.0) && (overdraft == 0.0) && (unclearedBalance == 0.0)) {
						flag = 1;
						System.out.println("success");
						accountList.remove(line);
						ReadWriteData.write(new File(path1), accountList);
						break;
					}	else {
						flag = 2;
						System.out.println("You should empty balance and overdraft.");
						break;
					}
				}
			}
		}
		return flag;
	}
	
	public static void main(String []args)
	{
		String UserID = "000";
		CloseAccount account = new CloseAccount();
		account.closeAccount(UserID);
	}
}
