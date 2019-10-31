package Control;

import java.io.File;
import java.util.List;

/**
 * This class is to check the credit status of users.
 * @author YangTao
 *
 */
public class IsCredit {
	static String path2 = "src/Credit.csv";
	private int creditValue;
	
	/**
	 * get the credit status and determine the user can create account or not.
	 * @param name 	user name
	 * @return flag
	 */
	public int credit(String name) {
		int flag = -2;
		List<String> creditList = ReadWriteData.read(new File(path2));
		if(creditList!=null && !creditList.isEmpty()) {
			for(int startLine = 1; startLine < creditList.size(); startLine++ ){
				String line = creditList.get(startLine);
				String[] lineSplit = line.split(",");
				if (name.equals(lineSplit[0])) {
					creditValue = Integer.parseInt(lineSplit[1]);
					if (creditValue >= 50) {
						flag = 1;
						System.out.println("success");
						return flag;
					}	else {
						flag = 2;
						System.out.println("Credit is too low!");
						return flag;
					}
				}
			}
			flag = 3;
			System.out.println("You should join our bank first!");
		}
		return flag;
	}
	public static void main(String []args) {
		String name="YangTao";
		IsCredit user = new IsCredit();
		user.credit(name);
	}
	
}
