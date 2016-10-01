package LinearList;

public interface QueueInterface<T> {
	public boolean isEmpty();
	public T dequeue();
	public void enqueue(T t);
	public T front();
	public T rear();
}
