package paths;

import java.nio.file.Path;
import java.nio.file.Paths;

public class PathsTesing {
	public static void first() {
		Path currentPath = Paths.get("temp", "file.txt");
		System.out.println(currentPath.getName(1));
		
	}
	public static void main (String args[]) {
		first();
	}

}
