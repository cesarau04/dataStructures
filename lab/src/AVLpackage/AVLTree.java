package AVLpackage;

/**
 * @param <E> Any kind of Object.
 * @author Cesar Augusto
 * @see               this AVLTree do not support delete/erase nodes from it, only accept insertions/add
 */
public class AVLTree<E extends Comparable<E>> {

   private Node root; //root
   private static final int ALLOWED_IMBALANCE = 1; //max imbalance allow

   /**
    * @return An empty AVLTree.
    */
   public AVLTree() {

   }

   /**
    * @param e element to be insert
    * @return the AVL with the element inside and balanced
    */
   public Node insert(E e) {
      return this.root = this.insert(e, root);
   }


   /**
    * @param e element to be insert
    * @param n root of the actual tree(could be subtree)
    * @return the AVL with the element inside and balanced
    */
   private Node insert(E e, Node n) {
      //check if its null, 2 cases: when first entry or when reaching a leave
      if (n == null)
         return new Node(e, null, null);

      //decide if this right or left child
      int compareResult = e.compareTo(n.element);

      //recursive way to find the correct position
      if (compareResult < 0)
         n.leftChild = insert(e, n.leftChild);
      else if (compareResult > 0)
         n.rightChild = insert(e, n.rightChild);
      else ;

      //balance the actual tree
      return balance(n);
   }


   public Node remove(Node element) {
      return null;
   }

   /**
    * @param n A node
    * @return node's height or 0 if it is null
    */
   private int height(Node n) {
      if (n == null)
         return 0;
      return n.height;
   }

   /**
    * @param n A node
    * @return return the AVL balanced
    */
   private Node balance(Node n) {
      //check if it is null
      if (n == null)
         return n;

      //(left child - right child) > 1?
      if (height(n.leftChild) - height(n.rightChild) > ALLOWED_IMBALANCE)
         //if left.left >= left.right -------> simple rotation to left
         if (height(n.leftChild.leftChild) >= height(n.leftChild.rightChild))
            n = rotateWithLeftChild(n);
         else
            //else: double rotation left
            n = doubleWithLeftChild(n);
      else
         //(right child - left child) > 1?
         if ((height(n.rightChild) - height(n.leftChild) > ALLOWED_IMBALANCE))
            //if left.left >= left.right -------> simple rotation to right
            if (height(n.rightChild.rightChild) >= height(n.rightChild.leftChild))
               n = rotateWithRightChild(n);
            else
               //else: double rotation right
               n = doubleWithRightChild(n);

      //difference between children is < 1
      n.height = Math.max(height(n.leftChild), height(n.rightChild)) + 1;
      return n;
   }


   /**
    * @param n Node to be rotated
    * @return Node balanced according to a double right rotation
    */
   private Node doubleWithRightChild(Node n) {
      n.rightChild = rotateWithLeftChild(n.rightChild);
      return rotateWithRightChild(n);
   }

   /**
    * @param n Node to be rotated
    * @return Node balanced according a simple right rotation
    */
   private Node rotateWithRightChild(Node n) {
      Node newNode = n.rightChild; //make a copy of the right child of n
      n.rightChild = newNode.leftChild; //point left child of newNode.leftChild to n.rightChild letting alone newNode
      newNode.leftChild = n;//point newNode as a father of n
      n.height = Math.max(height(n.leftChild), height(n.rightChild)) + 1; //update n(newNode child) height
      newNode.height = Math.max(height(newNode.leftChild), n.height) + 1; //update newNode(new father of n) height
      return newNode;
   }

   /**
    * @param n Node to be rotated
    * @return Node balanced according to a double left rotation
    */
   private Node doubleWithLeftChild(Node n) {
      n.leftChild = rotateWithRightChild(n.leftChild);
      return rotateWithLeftChild(n);
   }

   /**
    * @param n Node to be rotated
    * @return Node balanced according a simple left rotation
    */
   private Node rotateWithLeftChild(Node n) {
      Node newNode = n.leftChild; //make a copy of the left child of n
      n.leftChild = newNode.rightChild; //point left child of newNode.rightChild to n.leftChild letting alone newNode
      newNode.rightChild = n; //point newNode as a father of n
      n.height = Math.max(height(n.leftChild), height(n.rightChild)) + 1; //update n(newNode child) height
      newNode.height = Math.max(height(newNode.leftChild), n.height) + 1; //update newNode(new father of n) height
      return newNode;
   }

   /**
    * @return The AVL's nodes in inOrder format: [node - height]
    */
   public void inOrder() {
      inorderTransversal(this.root); //recursive
   }

   /**
    * @param node Node to begin the traverse.
    */
   private void inorderTransversal(Node node) {
      if (node != null) {
         inorderTransversal(node.leftChild); //check if it have a leftChild
         System.out.print(node.toString()); //print node
         inorderTransversal(node.rightChild); // check if it have a rightChild
      }

   }

   //Node class, each AVL save a Node inside.
   private class Node {
      E element;
      Node leftChild, rightChild;
      Integer height;


      /**
       * @param element    Element to be stored
       * @param leftChild  Points to its left child
       * @param rightChild Points to its right child
       */
      public Node(E element, Node leftChild, Node rightChild) {
         this.element = element;
         this.leftChild = leftChild;
         this.rightChild = rightChild;
         this.height = 1;
      }

      /**
       * @return A string in [Node - height] format
       */
      public String toString() {
         String b = "[ " + this.element.toString() + " - " + this.height.toString() + " ]";
         return b;
      }
   }

   public static void main(String[] args) {
      AVLTree<Integer> avl = new AVLTree<>();

      avl.insert(84);
      avl.insert(10);
      avl.insert(8);
      avl.insert(92);
      avl.insert(66);
      avl.insert(88);
      avl.insert(29);
      avl.insert(27);
      avl.insert(75);
      avl.insert(72);
      avl.insert(68);
      avl.insert(62);
      avl.insert(18);
      avl.insert(80);
      avl.insert(36);
      avl.insert(1);
      avl.insert(40);
      System.out.println();
      avl.inOrder();
   }
}
