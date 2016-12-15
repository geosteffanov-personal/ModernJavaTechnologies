package treeArchiver.test;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import treeArchiver.*;

public class TestTree {
	private TreeNode<String> root;
	
	
	@Before
	public void setUp() {
		TreeNode<String> crrNode;
		List<TreeNode<String>> nodes;
		nodes = new ArrayList<TreeNode<String>>();
		
		crrNode = TreeNode.getNode("file.txt");
		nodes.add(crrNode);
		crrNode = TreeNode.getNode("run.sh");
		nodes.add(crrNode);
		
		crrNode = TreeNode.getNode("folder");
		for (TreeNode<String> node : nodes) {
			crrNode.addChild(node);
		}
		
		root = TreeNode.getNode("root");
		root.addChild(crrNode);
		
		crrNode = TreeNode.getNode("picture.jpeg");
		nodes = new ArrayList<TreeNode<String>>();
		nodes.add(crrNode);
		crrNode = TreeNode.getNode("calendar.dat");
		nodes.add(crrNode);		
		crrNode =TreeNode.getNode("random");
		for (TreeNode<String> node: nodes) {
			crrNode.addChild(node);
		}

		root.addChild(crrNode);
	}

	
	@Test
	public void testBFS() {
		TreeNode<String> crrNode;
		assertEquals(root.getId(), 3);
		assertEquals(root.getChildren().size(), 2);
		crrNode = root.getChildren().get(0);
		assertEquals(crrNode.getData(), "folder");
		assertEquals(root.getData(), "root");
		assertEquals(crrNode.getChildren().size(), 2);
		assertEquals(crrNode.getChildren().get(0).getData(), "file.txt");
	}
}
