package hashtable;

import java.util.Iterator;

public interface HashTable<Key, Val>  {
    public Val getValue(Key k);
    public boolean contains(Key k);
    public Val add(Key k, Val v);
    public Val remove(Key k);
    public boolean isEmpty();
    public int getSize();
    public Iterator<Key> getKeyIterator();
    public Iterator<Val> getValueIterator();
    public void clear();
}
