package snowboardingsys.paydesk;

import java.util.concurrent.Semaphore;

import snowboardingsys.users.User;

public class PayDesk {
	private final static int STARTING_BALANCE = 0;
	private int capacity;
	private Semaphore availableSlots;
	private int balance = STARTING_BALANCE;
	private Card[] cards;

	private int issuedCards;

	public PayDesk(int capacity) {
		availableSlots = new Semaphore(capacity);
		this.capacity = capacity;
		this.issuedCards = 0;
	}

	public int getRemaining() {
		return availableSlots.availablePermits();
	}


	public int getBalance() {
		return balance;
	}

	public void setBalance(int balance) {
		this.balance = balance;
	}

	public Card[] getCards() {
		return cards;
	}

	public int getIssuedCards() {
		return issuedCards;
	}

	public void setCards(Card[] cards) {
		this.cards = cards;
	}

	public synchronized void issueCard(User user) throws InterruptedException {
		availableSlots.acquire();
		// user.setCard(new Card(user.getName(), LocalDate.now()));
		 setBalance(getBalance() + 51);
		 issuedCards += 1;
	}

	public void retrieveCard(User user) {
		synchronized(this) {
			setBalance(getBalance() - 1);
			availableSlots.release();
		this.notify();
		}
	}

	public void transferMoney(Transferrer employee) {
		synchronized(this) {
			while (balance <= 1000 + capacity) {
				try {
					this.wait();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			int balance = getBalance();
			int moneyToTransfer = balance - 1000;
			employee.getVault().deposit(moneyToTransfer);
			setBalance(1000);
		}
	}
}
