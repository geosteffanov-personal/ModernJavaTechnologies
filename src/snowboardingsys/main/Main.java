package snowboardingsys.main;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import snowboardingsys.paydesk.PayDesk;
import snowboardingsys.paydesk.Transferrer;
import snowboardingsys.paydesk.Vault;
import snowboardingsys.users.User;

public class Main {
	private static final int CAPACITY = 30;
	private static final int USER_COUNT = 100;
	
	public static void main(String[] args) {
		PayDesk desk = new PayDesk(CAPACITY);
		User[] users = new User[USER_COUNT];
		Vault vault = new Vault();
		Transferrer[] transferrers = new Transferrer[10];
//		Thread[] threads = new Thread[USER_COUNT];

//		
//		for (int i = 0; i < USER_COUNT; i++) {
//			Thread crr = new Thread(users[i]);
//			threads[i] = crr;
//			crr.start();
//		}
//		for (int i = 0; i < USER_COUNT; i++) {
//			try {
//				threads[i].join();
//			} catch (InterruptedException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//		}

		CountDownLatch latch = new CountDownLatch(USER_COUNT);
		for (int i = 0; i < USER_COUNT; i++) {
			users[i] = new User("User" + i, desk, latch);
		}
		for (int i = 0; i < 10; i++) {
			transferrers[i] = new Transferrer(desk, vault);
		}
		
		ExecutorService threadPool = Executors.newFixedThreadPool(CAPACITY);
		for (int i = 0; i < USER_COUNT; i++) {
			threadPool.execute(users[i]);
		}
		
		for (int i = 0; i < 10; i++) {
			Thread crr = new Thread(transferrers[i]);
			crr.setDaemon(true);
			crr.start();
		}
		
		try {
			latch.await();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		System.out.println(desk.getBalance());
		System.out.println(desk.getIssuedCards());
		System.out.println("FINAL BALANCE " + vault.getBalance());
		threadPool.shutdown();
		
	}
}
