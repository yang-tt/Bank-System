package Control;

import java.io.File;
import java.util.List;
import java.util.Random;

/**
 * This class is to control the process of add user into bank system
 * @author YangTao
 *
 */
public class AddUser {
	static String path1 = "src/BankUser.csv";
	static String path2 = "src/Credit.csv";
	private String input;
	private String creditValue;
	
	/**
	 * Check if the add user process is successful
	 * @param name	User Name
	 * @return a String contains flag and credit value.
	 */
	public String adduser(String name) {
		String back = "";
		int flag = -2;
		List<String> bankUserList = ReadWriteData.read(new File(path1));
		if(bankUserList!=null && !bankUserList.isEmpty()) {
			for(int startLine = 1; startLine < bankUserList.size(); startLine++ ){
				String line = bankUserList.get(startLine);
				String[] lineSplit = line.split(",");
				if (name.equals(lineSplit[0])) {
					flag = 1;
					back = flag + ";";
					System.out.println("You've already in our bank!");
					return back;
				}	
			}
			
			List<String> creditList = ReadWriteData.read(new File(path2));
			if(creditList!=null && !creditList.isEmpty()) {
				for(int startLine1 = 1; startLine1 < creditList.size(); startLine1++ ){
					String line1 = creditList.get(startLine1);
					String[] lineSplit1 = line1.split(",");
					if (name.equals(lineSplit1[0])) {
						flag = 2;
						creditValue = lineSplit1[1];
						back = flag + ";" + creditValue;
						input = name + "," +creditValue;
						bankUserList.add(input);
						ReadWriteData.write(new File(path1), bankUserList);
						System.out.println("success");
						return back;
					}
				}
				flag = 3;
				back = flag + ";";
				System.out.println("You are not in the credit system!");
			}
		}
		return back;
	}
	
	public static void main(String []args) {
		String name="ZLC";
		AddUser user = new AddUser();
		user.adduser(name);
	}
}
