package Control;

import java.util.Calendar;
import java.util.Date;
import java.util.Timer;
 
/**
 * this class is to manage the running time of Clear Funds
 * @author YangTao
 *
 */
public class TimerManager {
 
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		new TimerManager();  
	}
 
	private static final long PERIOD_DAY = 24 * 60 * 60 * 1000;
	public TimerManager() {
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.HOUR_OF_DAY, 10); 
		calendar.set(Calendar.MINUTE, 03);
		calendar.set(Calendar.SECOND, 0);
		Date date=calendar.getTime(); 

		if (date.before(new Date())) {
			date = this.addDay(date, 1);
		}
		Timer timer = new Timer();
		ClearFunds task = new ClearFunds();
		timer.schedule(task,date,PERIOD_DAY);  
	}

	/**
	 * add or reduce days
	 * @param date
	 * @param num
	 * @return
	 */
	public Date addDay(Date date, int num) {
		Calendar startDT = Calendar.getInstance();
		startDT.setTime(date);
		startDT.add(Calendar.DAY_OF_MONTH, num);
		return startDT.getTime();
	}
 
}


