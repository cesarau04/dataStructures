package TreePackage;

/**
 * Created by Cesar on 06/11/2016.
 */
public class BinaryNode<T> implements BTNode<T> {
   private T data;
   private BinaryNode<T> leftChild;
   private BinaryNode<T> rightChild;

   public BinaryNode() {
      this(null);
   }

   public BinaryNode(T data) {
      this(data, null, null);
   }

   public BinaryNode(T data, BinaryNode<T> leftChild, BinaryNode<T> rightChild) {
      this.data = data;
      this.leftChild = leftChild;
      this.rightChild = rightChild;
   }

   public T gKey() {
      return this.data;
   }

   public void sKey(T newData) {
      this.data = newData;
   }

   public BTNode<T> gLeft() {
      return this.leftChild;
   }

   public BTNode<T> gRight() {
      return this.rightChild;
   }

   public void sLeft(BTNode<T> newLeftChild) {
      this.leftChild = (BinaryNode<T>) newLeftChild;
   }

   public void sRight(BTNode<T> newRightChild) {
      this.rightChild = (BinaryNode<T>) newRightChild;
   }

   public boolean hasLeftChild() {
      return this.leftChild != null;
   }

   public boolean hasRightChild() {
      return this.rightChild != null;
   }

   public boolean isLeaf() {
      return (this.leftChild == null) && (this.rightChild == null);
   }

   public int getNumberOfNodes() {
      int leftC = 0;
      int rightC = 0;

      if (this.leftChild != null) {
         leftC = this.leftChild.getNumberOfNodes();
      }
      if (this.rightChild != null) {
         rightC = this.rightChild.getNumberOfNodes();
      }

      return 1 + leftC + rightC;
   }

   public int getHeight() {
      return getHeight(this);
   }

   private int getHeight(BinaryNode<T> node) {
      int height = 0;

      if (node != null) {
         height = 1 + Math.max(getHeight(node.leftChild), getHeight(node.rightChild));
      }

      return height;
   }

   public BinaryNode<T> copy() {
      throw new UnsupportedOperationException();
   }

}
