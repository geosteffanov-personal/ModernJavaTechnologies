package treeArchiver;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class TreeFactory<T> {
	public static TreeNode<String> getFromCrownFile(String path) throws Exception {
		try(BufferedReader reader = new BufferedReader(new FileReader(path))) {
			String line;
			line = reader.readLine();
			if (line == null) {
				return null;
			}
			line = line.trim();
			String[] data = line.split("\\s");
			if (!data[0].equals("ST") || !data[1].equals("0")) {
				throw new Exception("ILLEGAL FORMAT");
			}
			TreeNode<String> root = TreeNode.<String>getNode(data[2]);
			TreeIterator<String> iterator = new TreeIterator<String>(root);
			
			while((line = reader.readLine()) != null) {
//				data = line.split(regex)
			}
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
		return null;
	}

}


/* CROWN format
 *     
 *    T 0 {#ROOT DATA}
 *    ST 0 {#ST0 DATA}
 *    ST 1 {#ST1 DATA}
 *    ST 2 {#ST2 DATA}
 *    ST 3 {#ST3 DATA}
 *    ST 0 ST 0 {#ST0ST0 DATA}
 *   .....
 * 
 * 
 * 
 * 
 */
