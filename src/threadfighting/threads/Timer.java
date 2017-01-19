package threadfighting.threads;

public class Timer implements Runnable {
	
	private int seconds;
	private FightingPit pit;
	
	
	public Timer(FightingPit pit, int seconds) {
		this.pit = pit;
		this.seconds = seconds;
	}
	
	
	@Override
	public void run() {
		pit.start();
		try {
			Thread.sleep(seconds * 1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			pit.stop();
		}
	}
	

}
