package snowboardingsys.paydesk;

public class Vault {
	private int balance;
	public Vault(){
		this.balance = 0;
	}

	public void deposit(int amount) {
		synchronized(this) {
			balance += amount;
			System.out.println("BALANCE IS: " + balance);
		}
	}
	public int getBalance() {
		return balance;
	}

}
