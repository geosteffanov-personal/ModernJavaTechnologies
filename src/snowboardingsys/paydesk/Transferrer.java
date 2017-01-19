package snowboardingsys.paydesk;

public class Transferrer implements Runnable {
	private PayDesk desk;
	private Vault vault;
	
	public Transferrer(PayDesk desk, Vault vault) {
		this.desk = desk;
		this.vault = vault;
	}
	
	@Override
	public void run() {
		while(true) {
			desk.transferMoney(this);
		}
	}
	
	public Vault getVault() {
		return vault;
	}

}
