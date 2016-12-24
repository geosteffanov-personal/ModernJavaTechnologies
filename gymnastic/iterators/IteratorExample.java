package iterators;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;



public class IteratorExample {
	private static List<String> names = new ArrayList<String>();
	
	
	private static void iterateList() {
	   names.add("Pesho");
	   names.add("Gosho");
	   names.add("Niki");
	   
	   Iterator<String> iterator = names.iterator();
	   while(iterator.hasNext()) {
		   String crr = iterator.next();
		   System.out.println(crr);
	   }
	   
	   ListIterator<String> lIterator = names.listIterator(0);
	   while(lIterator.hasNext()) {
		   String crr = lIterator.next();
		   lIterator.add(crr + ", hi!");
		   System.out.println(crr);
		   System.out.println(lIterator.previous());
		   lIterator.next();
	   }
	}
	
	
	public static void main(String args[]) {
		iterateList();
	}

}
