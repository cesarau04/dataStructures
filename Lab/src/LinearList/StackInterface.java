package LinearList;

public interface StackInterface<T> {
	public T peek();
	public T pop();
	public void push(T t);
	public boolean isEmpty();
}
