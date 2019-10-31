package Control;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;

import Entity.Account;
import Entity.Customer;
import Entity.Deposit;

/**
 * this class for read and write data
 * @author YangTao
 *
 */
public class ReadWriteData {
	static String path1 = "src/Account.csv";
	
	/**
	 * read data
	 * @param file
	 * @return
	 */
	public static List<String> read(File file) {
		List<String> dataList = new ArrayList<String>();
		BufferedReader reader = null;
		try {
			reader = new BufferedReader(new FileReader(file));
			String line = "";
			while ((line = reader.readLine()) != null) {
				dataList.add(line);
			}
		}	catch (Exception e) {
			}	finally {
				if (reader != null){
					try {
						reader.close();
						reader = null;
					}	catch (IOException e) {
						e.printStackTrace();
						}
				}
			}
		return dataList;
	}
	
	/**
	 * write data
	 * @param file
	 * @param dataList
	 * @return
	 */
	public static boolean write(File file, List<String> dataList){
		boolean isSucess = false;
		FileOutputStream out = null;
		OutputStreamWriter osw = null;
		BufferedWriter bw = null;
		try {
				out = new FileOutputStream(file);
				osw = new OutputStreamWriter(out);
				bw = new BufferedWriter(osw);
				bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file)));
				if(dataList!=null && !dataList.isEmpty()){
					for(String data : dataList){
						bw.append(data).append("\r");
					}
				}
				isSucess=true;
		} catch (Exception e) {
			isSucess=false;
		}
		finally{
			if(bw!=null){
				try {
					bw.close();
					bw=null;
				} catch (IOException e) {
						e.printStackTrace();
						}
				}
			if(osw!=null){
				try {
					osw.close();
					osw=null;
				} catch (IOException e) {
					e.printStackTrace();
					}
				}
			if(out!=null){
				try {
					out.close();
					out=null;
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return isSucess;
	}
}
