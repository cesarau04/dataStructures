package TreePackage;

import java.util.Iterator;

public interface BinarySearchTreeInterface<T extends Comparable<? super T>> extends TreeInterface<T>{

	public boolean contains(T newEntry);
	public T getEntry(T newEntry);
	public T add(T newEntry);
	public T remove(T newEntry);
	public Iterator<T> getInorderIterator();
}
