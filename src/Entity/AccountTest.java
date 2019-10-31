package Entity;

import static org.junit.Assert.*;

import org.junit.Test;

public class AccountTest {

	@Test
	public void test() {
		Account account = new Account("ZLC", 111, 111, 0.0, false);
		assertEquals("ZLC", account.getAccName());
		assertEquals(111, account.getAccNo());
		assertEquals(111, account.getPIN());
		//assertEquals(0.0, account.getBalance());
		assertEquals(false, account.isSuspended());
	}

}
