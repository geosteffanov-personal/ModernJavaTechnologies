package snowboardingsys.users;

import java.util.concurrent.CountDownLatch;

import snowboardingsys.paydesk.Card;
import snowboardingsys.paydesk.PayDesk;

public class User implements Runnable {
	private String name;
	private Card card;
	private PayDesk desk;
	private CountDownLatch latch;
	public User(String name, PayDesk desk, CountDownLatch latch) {
		this.name = name;
		this.card = null;
		this.desk = desk;
		this.latch = latch;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Card getCard() {
		return card;
	}

	public void setCard(Card card) {
		this.card = card;
	}

	
	
	@Override
	public void run() {
		desk.issueCard(this);
		try {
			Thread.sleep(250);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		desk.retrieveCard(this);
		latch.countDown();
	}

}
