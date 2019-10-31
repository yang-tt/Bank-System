package Control;

import java.io.File;
import java.util.List;

/**
 * This class is to check the suspend status of accounts.
 * @author YangTao
 *
 */
public class IsSuspend {
	static String path1 = "src/Account.csv";
	
	/**
	 * get the account suspend status.
	 * @param accNo		account number
	 * @return flag
	 */
	public boolean isSuspend(String accNo) {
		boolean flag = false;
		List<String> accountList = ReadWriteData.read(new File(path1));
		if(accountList!=null && !accountList.isEmpty()) {
			for(int startLine = 1; startLine < accountList.size(); startLine++ ) {
				String line = accountList.get(startLine);
				String[] lineSplit = line.split(",");

				if (lineSplit[0].equals(accNo)) {
					if (Boolean.parseBoolean(lineSplit[13])) {
						flag = true;
						System.out.println("Dong");
						break;
					}	else {
						flag = false;
						break;
					}
				}
			}
		}
		return flag;
	}
}
