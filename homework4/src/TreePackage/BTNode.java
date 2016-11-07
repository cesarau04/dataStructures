package TreePackage;

/**
 * Created by Cesar on 06/11/2016.
 */
public interface BTNode<T> {
   public T gKey();

   public BTNode<T> gLeft();

   public BTNode<T> gRight();

   public void sKey(T AValue);

   public void sLeft(BTNode<T> AValue);

   public void sRight(BTNode<T> AValue);
}
