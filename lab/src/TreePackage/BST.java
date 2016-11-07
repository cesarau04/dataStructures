package TreePackage;

/**
 * Created by Cesar on 02/11/2016.
 */

public class BST<T extends Comparable<? super T>> {

   BSTNode<T> root;

   public BST() {
      this.root = null;
   }

   public BST(T value) {
      this.root = new BSTNode<T>(value);
   }


   protected BSTNode<T> getRootNode() {
      return this.root;
   }

   protected void setRootNode(BSTNode<T> rootNode) {
      this.root = rootNode;
   }


   public boolean contains(T entry) {
      return getEntry(entry) != null;
   }

   public T getEntry(T entry) {
      return findEntry(getRootNode(), entry);
   }


   private T findEntry(BSTNode<T> rootNode, T entry) {
      T result = null;

      if (root != null) {

         int value = entry.compareTo(root.getData());

         if (value == 0) {
            result = entry;
         } else if (value < 0) {
            result = findEntry(root.getLeftChild(), entry);
         } else {
            result = findEntry(root.getRightChild(), entry);
         }
      }
      return result;
   }


   public T add(T entry) {
      T result = null;

      if (isEmpty()) {
         setRootNode(new BSTNode<T>(entry));
      } else {
         addEntry(getRootNode(), entry);
      }

      return result;
   }

   private T addEntry(BSTNode<T> rootNode, T entry) {

      assert rootNode != null;

      T result = null;

      int value = entry.compareTo(rootNode.getData());

      if (value == 0) {
         result = rootNode.getData();
         rootNode.setData(entry);
      } else if (value < 0) {
         if (rootNode.hasLeftChild()) {
            result = addEntry(rootNode.getLeftChild(), entry);
         } else {
            rootNode.setLeftChild(new BSTNode<T>(entry));
         }
      } else {
         if (rootNode.hasRightChild()) {
            result = addEntry(rootNode.getRightChild(), entry);
         } else {
            rootNode.setRightChild(new BSTNode<T>(entry));
         }
      }

      return result;
   }

   public boolean isEmpty() {
      return this.root == null;
   }

   public T remove(T entry) {
      ReturnObject oldEntry = new ReturnObject(null);
      BSTNode<T> newRoot = removeEntry(getRootNode(), entry, oldEntry);

      setRootNode(newRoot);

      return oldEntry.getData();
   }

   private BSTNode<T> removeEntry(BSTNode<T> rootNode, T entry, ReturnObject oldEntry) {
      if (rootNode != null) {
         T rootData = rootNode.getData();
         int value = entry.compareTo(rootData);

         if (value == 0) {
            oldEntry.setData(rootData);
            rootNode = removeFromRoot(rootNode);
         } else if (value < 0) {
            BSTNode<T> leftChild = rootNode.getLeftChild();
            BSTNode<T> subtreeRoot = removeEntry(leftChild, entry, oldEntry);

            rootNode.setLeftChild(subtreeRoot);
         } else {
            BSTNode<T> rightChild = rootNode.getRightChild();
            rootNode.setRightChild(removeEntry(rightChild, entry, oldEntry));
         }
      }
      return rootNode;
   }

   private BSTNode<T> removeFromRoot(BSTNode<T> rootNode) {
      if (rootNode.hasLeftChild() && rootNode.hasRightChild()) {
         BSTNode<T> leftSubtreeRoot = rootNode.getLeftChild();
         BSTNode<T> largestNode = findLargest(leftSubtreeRoot);

         rootNode.setData(largestNode.getData());

         rootNode.setLeftChild(removeLargest(leftSubtreeRoot));
      } else if (rootNode.hasRightChild()) {
         rootNode = rootNode.getRightChild();
      } else {
         rootNode = rootNode.getLeftChild();
      }

      return rootNode;
   }

   private BSTNode<T> findLargest(BSTNode<T> rootNode) {
      if (rootNode.hasRightChild()) {
         rootNode = findLargest(rootNode.getRightChild());
      }

      return rootNode;
   }

   private BSTNode<T> removeLargest(BSTNode<T> rootNode) {
      if (rootNode.hasRightChild()) {
         BSTNode<T> rightChild = rootNode.getRightChild();
         BSTNode<T> root = removeLargest(rightChild);
         rootNode.setRightChild(root);
      } else {
         rootNode = rootNode.getLeftChild();
      }

      return rootNode;
   }

   private class ReturnObject {
      T data;

      /**
       * Default constructor
       **/
      public ReturnObject() {
         this(null);
      }

      /**
       * @param data Initialize a instance with a given data.
       */
      public ReturnObject(T data) {
         this.data = data;
      }

      /**
       * @return The value of data.
       */
      public T getData() {
         return this.data;
      }

      /**
       * @param newData Set the data.
       */
      public void setData(T newData) {
         this.data = newData;
      }
   }

   private static class BSTNode<T> {
      private T data;
      private BSTNode<T> leftChild;
      private BSTNode<T> rightChild;

      public BSTNode() {
         this(null);
      }

      public BSTNode(T data) {
         this(data, null, null);
      }

      public BSTNode(T data, BSTNode<T> leftChild, BSTNode<T> rightChild) {
         this.data = data;
         this.leftChild = leftChild;
         this.rightChild = rightChild;
      }

      public T getData() {
         return this.data;
      }

      public void setData(T data) {
         this.data = data;
      }

      public BSTNode<T> getLeftChild() {
         return this.leftChild;
      }

      public void setLeftChild(BSTNode<T> leftChild) {
         this.leftChild = leftChild;
      }

      public BSTNode<T> getRightChild() {
         return this.rightChild;
      }

      public void setRightChild(BSTNode<T> rightChild) {
         this.rightChild = rightChild;
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
   }

   public static void main(String[] args) {

   }

}
