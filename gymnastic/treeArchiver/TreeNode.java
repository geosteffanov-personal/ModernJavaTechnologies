package treeArchiver;

import java.util.ArrayList;
import java.util.List;

public class TreeNode<Type> {
	private static int maxId = -1;
	
	private List<TreeNode<Type>> children;
	private int id;
	private Type data;
	
	private TreeNode(int id, Type data){
		children = new ArrayList<TreeNode<Type>>();
		this.id = id;
		this.data = data;
	}
	
	public static <Type> TreeNode<Type> getNode(Type data) {
		maxId++;
		TreeNode<Type> result = new TreeNode<Type>(maxId, data);
		return result;
	}
	
	public void addChild(TreeNode<Type> child) {
		children.add(child);
	}

	public void setData(Type data) {
		this.data = data;
	}
	
	public Type getData() {
		return data;
	}
	
	public int getId() {
		return id;
	}
	
	public List<TreeNode<Type>> getChildren() {
		return children;
	}
	
	public String toString() {
		StringBuilder str = new StringBuilder();
		str.append("node#"+id);
		str.append("data::"+data);
		str.append("child::");
		for (TreeNode<Type> child : children) {
			str.append("#"+child.getId());
		}
		
		return str.toString();
	}
}
