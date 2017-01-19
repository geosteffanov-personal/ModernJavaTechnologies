package threadfighting.threads;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import threadfighting.exceptions.ExistingFighterException;

public class FightingPit {
	private Map<String, ThreadFighter> pit;
	private boolean ongoingFight = false; 
	
	public FightingPit() {
		pit = new ConcurrentHashMap<>();
	}
	
	public void setFight(boolean status) {
		ongoingFight = status;
	}
	
	public boolean getStatus() {
		return ongoingFight;
	}
	public void start() {
		setFight(true);
		Collection<ThreadFighter> allFighters = pit.values();
		allFighters.forEach(fighter-> new Thread(fighter).start());
	}
	
	public void addFighter(ThreadFighter newFighter) throws ExistingFighterException {
		if (pit.containsKey(newFighter.getName()))
			throw new ExistingFighterException();
		pit.put(newFighter.getName(), newFighter);
		newFighter.setPit(this);
	}

	public ThreadFighter pickFighterDifferentThan(ThreadFighter exception) {
		Set<String> allFighters = pit.keySet();
		Set<String> restOfFighters = new HashSet<>();
		Iterator<String> it = allFighters.iterator();
		while (it.hasNext()) {
			String newStr = new String(it.next());
			restOfFighters.add(newStr);
		}
		/* TODO : if there is no such fighter */
		restOfFighters.remove(exception.getName());
		Object[] allButOne = restOfFighters.toArray();
		Random generator = new Random();
		return  pit.get(allButOne[generator.nextInt(allButOne.length)]);
	}

	
	public void stop() {
		ongoingFight = false;
	}
	
	public Map<String, ThreadFighter> getResults() {
		return pit;
	}

	public static void main (String args[]) {
		Map<String, String> id = new HashMap<>();
		id.put("1", "1");
		Set<String> str = id.keySet();
		Set<String> newSet = new HashSet<>();
		Iterator<String> it = str.iterator();
		while (it.hasNext()) {
			String newStr = new String(it.next());
			newSet.add(newStr);
		}
		newSet.remove("1");
		System.out.println(id.size());
	}
}
