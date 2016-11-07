package TreePackage;

/**
 * Created by Cesar on 06/11/2016.
 */
public class BinaryTree<T> implements BTree<T> {
   private BinaryNode<T> root;
   private int size;


   @Override
   public boolean isEmpty() {
      return this.root == null;
   }

   @Override
   public BinaryNode<T> gRoot() {
      return this.root;
   }

   @Override
   public int Count() {
      return this.root.getNumberOfNodes();
   }

   @Override
   public int Size(BinaryNode<T> ATree) {
      return ATree.getNumberOfNodes();
   }

   @Override
   public int Height(BinaryNode<T> ATree) {
      return ATree.getHeight();
   }

   @Override
   public int Size() {
      return this.Count();
   }
}
