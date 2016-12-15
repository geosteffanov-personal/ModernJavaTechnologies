package treeArchiver.test;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import treeArchiver.TreeIterator;
import treeArchiver.TreeNode;

public class TestTreeIterator {
	TreeIterator<String> iterator;
	
	
	@Before
	public void setUp() {
		TreeNode<String> root = TreeNode.<String>getNode("root");
		iterator = new TreeIterator<>(root);
		
		iterator.setData("/root");
		
		iterator.addChild("folder").addChild("file.txt");
		
		TreeIterator<String> temp = iterator.addChild("nextFolder");
		temp.addChild("subFolder");
		temp.addChild("subFolder(2)");
		temp.getChildIterator(1).addChild("picture.txt");
	}
	@Test
	public void testBFS() {
		Queue<TreeIterator<String>> bfs = new LinkedList<TreeIterator<String>>();
		List<String> result = new ArrayList<String>();
		
		
		bfs.add(iterator);
		
		while(!bfs.isEmpty()) {
			TreeIterator<String> crr = bfs.remove();
			result.add(crr.getData());
			
			for (int i = 0; i < crr.childrenCount(); i++) {
				bfs.add(crr.getChildIterator(i));
			}
		}
		
		List<String> expected = new ArrayList<String>();
		expected.add("/root");
		expected.add("folder");
		expected.add("nextFolder");
		expected.add("file.txt");
		expected.add("subFolder");
		expected.add("subFolder(2)");
		expected.add("picture.txt");

		for (int i = 0; i < result.size(); i++)
			assertEquals(result.get(i), expected.get(i));
	}	
}
