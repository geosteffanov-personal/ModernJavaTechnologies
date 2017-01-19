package threadfighting.threads;

import threadfighting.exceptions.ExistingFighterException;

public class ThreadFighter implements Runnable , Comparable{
	
	private String name;
	private int health;
	private int damage;
	private int level;
	private double experience;
	private int points;
	private FightingPit pit;
	
	public ThreadFighter(String name) {
		this.name = name;
		health = 20;
		damage = 1;
		level = 1;
		experience = 0;
		points = 0;
		pit = null;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getDamage() {
		return damage;
	}

	public void setDamage(int damage) {
		this.damage = damage;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public double getExperience() {
		return experience;
	}

	public void setExperience(double experience) {
		this.experience = experience;
	}

	public void setPit(FightingPit pit) {
		this.pit = pit;
	}
	
	public FightingPit getPit() {
		return pit;
	}
	
	public void setPoints(int points) {
		this.points = points;
	}
	
	public int getPoints() {
		return points;
	}
	
	@Override
	public void run() {
		while(pit.getStatus()) {
			ThreadFighter other = pit.pickFighterDifferentThan(this);
			other.health -= damage;
			if (other.health < 0) {
				experience += 0.1;
				other.health = 100 + 2 * (other.level - 1);
				points += other.level;
			}
			if (experience >= 1) {
				level++;
				experience = 0;
				health = 100 + 2 * level;
				damage += 1;
			}
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}


	@Override
	public int compareTo(Object arg0) {
		if (!(arg0 instanceof ThreadFighter)) {
			return 0;
		}
		ThreadFighter otherFighter = (ThreadFighter) arg0;
		int levelDifference = level - otherFighter.level;
		if (levelDifference != 0)
			return levelDifference;
		int pointsDifference = points - otherFighter.points;
		if (pointsDifference != 0)
			return pointsDifference;
		double xpDifference = experience - otherFighter.experience;
		if (xpDifference != 0) {
			if (xpDifference < 0)
				return -1;
			else return 1;
		}
		return (name.compareTo(otherFighter.name));
	}

	public String toString() {
		StringBuilder result = new StringBuilder();
		result.append("Name: " + name + '\n')
		      .append("Level: " + level + '\n')
		      .append("Points: " + points);
		return result.toString();
	}
}
