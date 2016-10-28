package MyStack;

import java.util.Iterator;

public interface StackInterface<T> {
	public void push(T element);
	public T pop();
	public T peek();
	public int size();
	public boolean isEmpty();
	public Iterator<T> getIterator();
}
