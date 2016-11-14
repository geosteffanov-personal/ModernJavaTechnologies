package bullsNcowsNaliens;

import java.util.ArrayList;

import java.util.List;

public abstract class Target<T> {
	protected List<T> target;
	protected int targetSize;
			
	public void addElement (T element) {
		target.add(element);
		targetSize++;
	}
	
	public Target() {
		targetSize = 0;
		target = new ArrayList<T>();
	}
	
	public int getSize() {
		return targetSize;
	}
	
	public abstract String getString();
	
	public T get(int index) {
		return target.get(index);
	}
}
