package Control;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * this class is to control the login process.
 * @author YangTao
 *
 */
public class Login {
	static String path = "src/Account.csv";
	
	/**
	 * Check if the login process is successful
	 * @param AccNo		account number
	 * @param PIN		personal identification number
	 * @return flag
	 */
	public int ckeckLogin(String AccNo, String PIN) {
		int flag = -2;
		List<String> loginList = ReadWriteData.read(new File(path));
		if(loginList!=null && !loginList.isEmpty()) {
			for(int startLine = 1; startLine < loginList.size(); startLine++ ) {
					String line = loginList.get(startLine);
					String[] lineSplit = line.split(",");

					while(lineSplit[0].equals(AccNo)) {
						if (PIN.equals(lineSplit[2])) {
							flag = 1;
							System.out.println(flag);
							return flag;
							
						} else {
							flag = 0;
							System.out.println(flag);
							return flag;
						}
					} 

			}
			flag = -1;
			System.out.println(flag);
			return flag;
			}
		System.out.println(flag);
		return flag;
		
	}
	/*public static void main(String []args)
	{
		String password= "111";
		String UserID = "111";
		Login checkObject = new Login();
		checkObject.ckeckLogin(UserID,password);
	}*/
}
