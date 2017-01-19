package threadfighting.main;

import threadfighting.exceptions.ExistingFighterException;
import threadfighting.threads.FightingPit;
import threadfighting.threads.ThreadFighter;
import threadfighting.threads.Timer;

public class Main {
	public static void main (String args[]) {
		FightingPit pit = new FightingPit();
		try {
			ThreadFighter fighter;
			
			fighter = new ThreadFighter("Hristozov");
			pit.addFighter(fighter);
			
			fighter = new ThreadFighter("Tony Naika");
			pit.addFighter(fighter);
			
			fighter = new ThreadFighter("Ikonata");
			pit.addFighter(fighter);

			fighter = new ThreadFighter("Sonic X");
			pit.addFighter(fighter);

			fighter = new ThreadFighter("Batman");
			pit.addFighter(fighter);

		} catch (ExistingFighterException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Timer timer = new Timer(pit, 10);
		Thread timerThread = new Thread(timer);
		timerThread.start();
		try {
			timerThread.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		pit.getResults().values()
		   .stream()
		   .sorted()
		   .forEach(t -> System.out.println(t + "\n=============\n"));
	}

}
