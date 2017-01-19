package snowboardingsys.paydesk;

import java.time.LocalDate;

import snowboardingsys.users.User;

public class PayDesk {
	private final static int STARTING_BALANCE = 0;
	private int capacity;
	private int remaining;
	private int balance = STARTING_BALANCE;
	private Card[] cards;

	private int issuedCards;

	public PayDesk(int capacity) {
		this.remaining = capacity;
		this.capacity = capacity;
		this.issuedCards = 0;
	}

	public int getRemaining() {
		return remaining;
	}

	public void setRemaining(int remaining) {
		this.remaining = remaining;
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

	public void issueCard(User user) {
		synchronized (this) {
			while (remaining <= 0) {
				try {
					this.wait();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			remaining -= 1;
			// user.setCard(new Card(user.getName(), LocalDate.now()));
			setBalance(getBalance() + 51);
			System.out.println("CURRENT REMAINING: " + remaining);
			System.out.println("CURRENT BALANCE: " + balance);
			issuedCards += 1;
		}
	}

	public void retrieveCard(User user) {
		synchronized (this) {
			remaining += 1;
			setBalance(getBalance() - 1);
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
