package Entity;

public class Deposit extends Transaction{
	private boolean cleared;
	
	public Deposit(double amount, boolean cleared) {
		super(amount);
		this.cleared = cleared;
	}
	
	public void setCleared(boolean cleared) {
		this.cleared = cleared;
	}
	public boolean isCleared() {
		return cleared;
	}

}
