package TreePackage;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Stack;


public class BinaryTree<T> implements BinaryTreeInterface<T> {

	private BinaryNodeInterface<T> root;
	
	
	public BinaryTree(){
		root = null;
	}
	
	public BinaryTree(T rootData){
		this.root = new BinaryNode<T>(rootData);
	}
	
	public BinaryTree(T rootData, BinaryTree<T> leftTree, BinaryTree<T> rightTree){
		throw new UnsupportedOperationException();
		//privateSetTree(rootData, leftTree, rightTree);
	}
	
	public void setTree(T rootData){
		this.root = new BinaryNode<T>(rootData);
	}

	@Override
	public void setTree(T rootData, BinaryTreeInterface<T> leftTree, BinaryTreeInterface<T> rightTree) {
		throw new UnsupportedOperationException();
		//privateSetTree(rootData, (BinaryTree<T>)leftTree, (BinaryTree<T>)rightTree);
	}
	
	public void setTree(T rootData, BinaryTree<T> leftTree, BinaryTree<T> rightTree){
		throw new UnsupportedOperationException();
		//privateSetTree(rootData, leftTree, rightTree);
	}
		
	/*private void privateSetTree(T root, BinaryTree<T> left, BinaryTree<T> right) {
		
	}*/
	
	
	@Override
	public T getRootData() {
		return this.root.getData();
	}

	
	@Override
	public int getHeight() {
		return this.root.getHeight();
	}

	
	@Override
	public int getNumberOfNodes() {
		return this.root.getNumberOfNodes();
	}

	
	@Override
	public boolean isEmpty() {
		return this.root == null;
	}

	
	@Override
	public void clear() {
		this.root = null;
	}

	protected void setRootData(T rootData){
		this.root.setData(rootData);
	}
	
	protected void setRootNode(BinaryNodeInterface<T> rootNode){
		this.root = rootNode;
	}
	
	protected BinaryNodeInterface<T> getRootNode(){
		return this.root;
	}
	
	@Override
	public Iterator<T> getPreorderIterator() {
		// TODO Auto-generated method stub
		return null;
	}

	
	@Override
	public Iterator<T> getPostorderIterator() {
		// TODO Auto-generated method stub
		return null;
	}

	
	@Override
	public Iterator<T> getInorderIterator() {
		inorderTransversal(this.root); //recursive
		inorderTransversal(); //stack solution
		new inorderIterator(); //iterative way
		return null;
	}

	//recursive
	private void inorderTransversal(BinaryNodeInterface<T> node) {
		if(node!=null){
			inorderTransversal(node.getLeftChild());
			System.out.println(node.getData());
			inorderTransversal(node.getRightChild());
		}
		
	}
	
	//stack way
	private void inorderTransversal(){
		Stack<BinaryNodeInterface<T>> nodeStack = new Stack<BinaryNodeInterface<T>>();
		BinaryNodeInterface<T> currentNode = this.root;
		while (!nodeStack.isEmpty() || (currentNode != null)){
			while (currentNode != null){
				nodeStack.push(currentNode);
				currentNode = currentNode.getLeftChild();
			}
			if (!nodeStack.isEmpty()){
				BinaryNodeInterface<T> nextNode = nodeStack.pop();
				assert nextNode != null;
				System.out.println(nextNode.getData());
				currentNode = nextNode.getRightChild();
			}
		}
	}
	
	//iterative
	private class inorderIterator implements Iterator<T>{

		private Stack<BinaryNodeInterface<T>> nodeStack;
		private BinaryNodeInterface<T> currentNode;
		
		public inorderIterator(){
			nodeStack = new Stack<BinaryNodeInterface<T>>();
			currentNode = root;
		}
		
		
		@Override
		public boolean hasNext() {
			return !nodeStack.isEmpty() || (currentNode != null);
		}

		@Override
		public T next() {
			BinaryNodeInterface<T> nextNode = null;
			
			while (currentNode != null){
				nodeStack.push(currentNode);
				currentNode = currentNode.getLeftChild();
			}
			if (!nodeStack.isEmpty()){
				nextNode = nodeStack.pop();
				assert nextNode != null;
				currentNode = nextNode.getRightChild();
			}else{
				throw new NoSuchElementException();
			}
			return nextNode.getData();
		}
		
		public void remove(){
			throw new UnsupportedOperationException();
		}
		
	}

	@Override
	public Iterator<T> getLevelOrderIterator() {
		// TODO Auto-generated method stub
		return null;
	}

   public static void main(String[] args) {

   }

	
}
