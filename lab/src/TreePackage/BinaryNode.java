package TreePackage;

public class BinaryNode<T> implements BinaryNodeInterface<T>{

	private T data;
	private BinaryNode<T> leftChild;
	private BinaryNode<T> rightChild;
	
	public BinaryNode(){
		this(null);
	}
	
	public BinaryNode(T data) {
		this(data, null, null);
	}
	
	public BinaryNode(T data, BinaryNode<T> leftChild, BinaryNode<T> rightChild){
		this.data = data;
		this.leftChild = leftChild;
		this.rightChild = rightChild;
	}

	@Override
	public T getData() {
		return this.data;
	}

	@Override
	public void setData(T newData) {
		this.data = newData;
	}

	@Override
	public BinaryNodeInterface<T> getLeftChild() {
		return this.leftChild;
	}

	@Override
	public BinaryNodeInterface<T> getRightChild() {
		return this.rightChild;
	}

	@Override
	public void setLeftChild(BinaryNodeInterface<T> newLeftChild) {
		this.leftChild = (BinaryNode<T>) newLeftChild;
	}

	@Override
	public void setRightChild(BinaryNodeInterface<T> newRightChild) {
		this.rightChild = (BinaryNode<T>) newRightChild;
	}

	@Override
	public boolean hasLeftChild() {
		return this.leftChild!=null;
	}

	@Override
	public boolean hasRightChild() {
		return this.rightChild!=null;
	}

	@Override
	public boolean isLeaf() {
		return (this.leftChild==null) && (this.rightChild==null);
	}

	@Override
	public int getNumberOfNodes() {
		int leftC = 0;
		int rightC = 0;
		
		if (this.leftChild!=null) {
			leftC = this.leftChild.getNumberOfNodes();
		}
		if (this.rightChild!=null) {
			rightC = this.rightChild.getNumberOfNodes();
		}
		
		return 1 + leftC + rightC;
	}
	
	@Override
	public int getHeight() {
		return getHeight(this);
	}
	
	private int getHeight(BinaryNode<T> node){
		int height = 0;
		
		if(node!=null){
			height = 1 + Math.max(getHeight(node.leftChild), getHeight(node.rightChild));
		}
		
		return height; 
	}

	//Not implemented due to unnecessary!
	@Override
	public BinaryNodeInterface<T> copy() {
		return null;
	}

}
