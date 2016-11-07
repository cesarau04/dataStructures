package HashTable;

import java.util.Iterator;

/**
 * Created by Cesar on 06/11/2016.
 */

public interface HashTableInterface<K, V> {
   public V getValue(K k);

   public boolean contains(K k);

   public V add(K k, V v);

   public V remove(K k);

   public boolean isEmpty();

   public int getSize();

   public Iterator<K> getKeyIterator();

   public Iterator<V> getValueIterator();

   public void clear();

}
