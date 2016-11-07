package TreePackage;

/**
 * Created by Cesar on 06/11/2016.
 */
public interface BTree<T> {
   public boolean isEmpty();

   public BinaryNode<T> gRoot();

   public int Count();

   public int Size(BinaryNode<T> ATree);

   public int Height(BinaryNode<T> ATree);

   public int Size();
}
