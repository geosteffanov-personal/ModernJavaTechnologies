package treeArchiver;

public class TreeIterator<T> {
	private TreeNode<T> root;
	
	
	public TreeIterator(TreeNode<T> root) {
		this.root = root;
	}
	
	public TreeIterator addChild(T data) {
		TreeNode<T> newNode = TreeNode.<T>getNode(data);
		root.addChild(newNode);
		
		return new TreeIterator(newNode);
	}
	
	public void setData(T data) {
		root.setData(data);
	}
	
	public T getData() {
		return root.getData();
	}
	
	
	public void deleteChild(int index) {
		root.getChildren().remove(index);
	}
	
	public TreeIterator getChildIterator(int index) {
		return new TreeIterator(root.getChildren().get(index));
	}

	public int childrenCount() {
		return root.getChildren().size();
	}
}
