package TreePackage;

/*
 * @author: Cesar.
 * @version: 1.2.
 * @since 19-10-2016
 * 
 * Binary Search Tree created with the help of the book Data Structures and Abstractions with Java of Frank M. Carrano.
 * 
 * This BTS is able to create a tree from nothing, or given a root, it does not allow incorporate a root with 
 * other subtrees because the user can cause conflict in the BTS structure.
 * 
 * This BTS do not support collisions.
 * 
 * The methods are recursive.
 * The iterative methods are going to be put later.
 * 
 * This version lack of JavaDocs.
 * Working in that.
 * 
 * <---1.1--->
 * 		Added main with the tests.
 * 
 * <---1.2--->
 * 		Added all the JavaDocs.
 */

public class BinarySearchTree<T extends Comparable<? super T>> extends BinaryTree<T> implements BinarySearchTreeInterface<T> {

	/** Default constructor. **/
	public BinarySearchTree() {
		super();
	}
	
	
	/**
	 * @param root Initialize a BTS with given root.
	 */
	public BinarySearchTree(T root){
		super();
		setRootNode(new BinaryNode<T>(root));
	}
	
	/** Method blocked for security in BTS structure. **/
	public void setTree(T rootData){
		throw new UnsupportedOperationException("Not a BTS");
	}
	
	/** Method blocked for security in BTS structure. **/
	public void setTree(T rootData, BinaryTreeInterface<T> leftTree, BinaryTreeInterface<T> rightTree){
		throw new UnsupportedOperationException("Not a BTS");
	}
	
	////////////////////////////////////////////////////////////////////////////////////////////////////
	
	/**
	 * @param entry Search for the element entry in the BTS.
	 * @see			Calls getEntry(T entry).
	 * @return 		True if found.
	 */
	@Override
	public boolean contains(T entry) {
		return getEntry(entry)!=null;
	}

	/**
	 * @param entry The element to be searched.
	 * @see			Calls findEntry(BinaryNodeInterface<T> rootNode, T entry).
	 * @return 		Entry if found.
	 */
	@Override
	public T getEntry(T entry) {
		return findEntry(getRootNode(), entry);
	}
	
	/**
	 * @param rootNode	The root of the actual tree (could be subtree).
	 * @param entry		The element to be searched.
	 * @return			<T> entry.
	 */
	private T findEntry(BinaryNodeInterface<T> rootNode, T entry){
		T result = null;
		
		if(rootNode!=null){
			
			int value = entry.compareTo(rootNode.getData()
					);
			
			if (value == 0){
				result = entry;
			}else if (value < 0){
				result = findEntry(rootNode.getLeftChild(), entry);
			}else{
				result = findEntry(rootNode.getRightChild(), entry);
			}
		}
		return result;
	}

	////////////////////////////////////////////////////////////////////////////////////////////////////
	
	/**
	 * @param entry The element to be added. Calls addEntry.
	 * @see			Calls addEntry(BinaryNodeInterface<T> rootNode, T entry).
	 * @return 		If there are collisions return the old entry, otherwise null. 
	 */
	@Override 
	public T add(T entry) {
		T result = null;
		
		if(isEmpty()){
			setRootNode(new BinaryNode<T>(entry));
		}else{
			addEntry(getRootNode(), entry);
		}
		
		return result;
	}
	
	
	/**
	 * @param rootNode	The root of the actual tree (could be subtree).
	 * @param entry		The element to be added.
	 * @return			If there are collisions return the old entry, otherwise null.
	 */
	private T addEntry(BinaryNodeInterface<T> rootNode, T entry){
		
		assert rootNode!=null;
		
		T result = null;
		
		int value = entry.compareTo(rootNode.getData());
		
		if(value == 0){
			result = rootNode.getData();
			rootNode.setData(entry);
		}else if(value < 0){
			if(rootNode.hasLeftChild()){
				result = addEntry(rootNode.getLeftChild(), entry);
			}else{
				rootNode.setLeftChild(new BinaryNode<T>(entry));
			}
		}else{
			if(rootNode.hasRightChild()){
				result = addEntry(rootNode.getRightChild(), entry);
			}else{
				rootNode.setRightChild(new BinaryNode<T>(entry));
			}
		}
		
		return result;
	}

	////////////////////////////////////////////////////////////////////////////////////////////////////
	 
	/**
	 * @param entry The element to be removed.
	 * @see			Calls removeEntry(T entry).
	 * @return 		<T> element removed. 
	 */
	@Override
	public T remove(T entry) {
		ReturnObject oldEntry = new ReturnObject(null);
		BinaryNodeInterface<T> newRoot = removeEntry(getRootNode(), entry, oldEntry);
		
		setRootNode(newRoot);
		
		return oldEntry.getData();
	}
	
	/**
	 * @param rootNode	The root of the actual tree (could be subtree).
	 * @param entry		The element to be removed.
	 * @param oldEntry	Reference to the parent.
	 * @see				Calls removeFromRoot(BinaryNodeInterface<T> rootNode).
	 * @return			Path for the element to be removed.
	 */
	private BinaryNodeInterface<T> removeEntry(BinaryNodeInterface<T> rootNode,T entry, ReturnObject oldEntry){
		if(rootNode!=null){
			T rootData = rootNode.getData();
			int value = entry.compareTo(rootData);
			
			if(value == 0){
				oldEntry.setData(rootData);
				rootNode = removeFromRoot(rootNode);
			}else if (value < 0){
				BinaryNodeInterface<T> leftChild = rootNode.getLeftChild();
				BinaryNodeInterface<T> subtreeRoot =  removeEntry(leftChild, entry, oldEntry);
				
				rootNode.setLeftChild(subtreeRoot);
			}else{
				BinaryNodeInterface<T> rightChild = rootNode.getRightChild();
				rootNode.setRightChild(removeEntry(rightChild, entry, oldEntry));
			}
		}
		return rootNode;
	}
	
	/**
	 * @param rootNode	The root of the actual tree (could be subtree).
	 * @see				findLargest(BinaryNodeInterface<T> rootNode)
	 * @return			Path for the element to be removed.
	 */
	private BinaryNodeInterface<T> removeFromRoot(BinaryNodeInterface<T> rootNode){
		if(rootNode.hasLeftChild() && rootNode.hasRightChild()){
			BinaryNodeInterface<T> leftSubtreeRoot = rootNode.getLeftChild();
			BinaryNodeInterface<T> largestNode = findLargest(leftSubtreeRoot);
			
			rootNode.setData(largestNode.getData());
			
			rootNode.setLeftChild(removeLargest(leftSubtreeRoot));
		}else if (rootNode.hasRightChild()){
			rootNode = rootNode.getRightChild();
		}else{
			rootNode = rootNode.getLeftChild();
		}
		
		return rootNode;
	}

	/**
	 * @param rootNode	The root of the actual tree (could be subtree).
	 * @see				Calls removeLargest(BinaryNodeInterface<T> rootNode).
	 * @return			Path for the element to be removed.
	 */
	private BinaryNodeInterface<T> findLargest(BinaryNodeInterface<T> rootNode){
		if (rootNode.hasRightChild()){
			rootNode = findLargest(rootNode.getRightChild());
		}
		
		return rootNode;
	}
	
	/**
	 * @param rootNode	The root of the actual tree (could be subtree).
	 * @return			Path for the element to be removed.
	 */
	private BinaryNodeInterface<T> removeLargest(BinaryNodeInterface<T> rootNode){
		if (rootNode.hasRightChild()){
			BinaryNodeInterface<T> rightChild = rootNode.getRightChild();
			BinaryNodeInterface<T> root = removeLargest(rightChild);
			rootNode.setRightChild(root);
		}else{
			rootNode = rootNode.getLeftChild();
		}
		
		return rootNode;
	}
	
	////////////////////////////////////////////////////////////////////////////////////////////////////
	
	@SuppressWarnings("unused")
	private class ReturnObject{
		T data;
		
		/** Default constructor **/
		public ReturnObject(){
			this(null);
		}

		/**
		 * @param data Initialize a instance with a data given. 
		 */
		public ReturnObject(T data){
			this.data = data;
		}
		
		/**
		 * @return	The value of data.
		 */
		public T getData() {
			return this.data;
		}
		
		/**
		 * @param newData Set the data.
		 */
		public void setData(T newData){
			this.data = newData;
		}
	}
	
	//tests
	public static void main(String[] args) {
		BinarySearchTree<Integer> bst = new BinarySearchTree<Integer>();
		
		bst.add(7);
		bst.add(3);
		bst.add(9);
		bst.add(2);
		bst.add(5);
		bst.add(8);
		bst.add(10);
		bst.add(4);
		bst.add(6);
		bst.add(11);
		bst.remove(6);
		bst.remove(9);
		bst.remove(7);
		System.out.println(bst.contains(7));
		System.out.println(bst.contains(2));
	}
}
