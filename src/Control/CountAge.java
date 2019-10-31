package Control;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * This class is to count the user's age.
 * @author YangTao
 *
 */
public class CountAge {
	/**
	 * Get this computer's date
	 * @return date
	 */
	public static String getDate() {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        return formatter.format(new Date());
    }
	
	/**
	 * Calculate how many days between str1 and str2
	 * @param str1	day1
	 * @param str2	day2
	 * @return	the number of days
	 */
	public static long getDistanceDays(String str1, String str2){
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        Date one;
        Date two;
        long days=0;
        try {
            one = (Date) df.parse(str1);
            two = (Date) df.parse(str2);
            long time1 = one.getTime();
            long time2 = two.getTime();
            long diff ;
            if(time1<time2) {
                diff = time2 - time1;
            } else {
                diff = time1 - time2;
            }
            long trans = 1000 * 60 * 60 ;
            long trans2 = 24;
            days = diff / trans / trans2;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return days;
    }
}
