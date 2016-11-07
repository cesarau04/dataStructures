package MyQueue;

public interface QueueInterface<T> {
   public boolean isEmpty();

   public T front();

   public T rear();

   public T Dequeue();

   public void Enqueue(T e);

   public int size();
}
