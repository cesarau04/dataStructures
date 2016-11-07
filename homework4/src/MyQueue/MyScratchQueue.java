package MyQueue;

import java.util.NoSuchElementException;

public class MyScratchQueue<T> implements QueueInterface<T> {

   private Node<T> head;
   private int size;

   public MyScratchQueue() {
      head = null;
      size = 0;
   }

   @Override
   public boolean isEmpty() {
      return head == null;
   }

   @Override
   public T front() {
      return this.getFirst();
   }

   @Override
   public T rear() {
      return this.getLast();
   }

   @Override
   public T Dequeue() {
      return this.removeFirst();
   }

   @Override
   public void Enqueue(T e) {
      this.addLast(e);
   }

   @Override
   public int size() {
      return 0;
   }

   private void addFirst(T e) {
      head = new Node<T>(e, head);
      this.size++;
   }

   private T getFirst() {
      if (head == null) throw new NoSuchElementException();
      return head.data;
   }

   private T removeFirst() {
      T tmp = getFirst();
      head = head.next;
      return tmp;
   }

   private void addLast(T e) {
      if (head == null) {
         addFirst(e);
      } else {
         Node<T> tmp = head;
         while (tmp.next != null) tmp = tmp.next;
         tmp.next = new Node<T>(e, null);
      }
   }

   private T getLast() {
      if (head == null) throw new NoSuchElementException();

      Node<T> tmp = head;
      while (tmp.next != null) tmp = tmp.next;

      return tmp.data;
   }

   public void clear() {
      head = null;
   }

   public LinkedList<T> reverse() {
      LinkedList<T> list = new LinkedList<T>();
      Node<T> tmp = head;
      while (tmp != null) {
         list.addFirst(tmp.data);
         tmp = tmp.next;
      }
      return list;
   }

   private static class Node<T> {
      private T data;
      private Node<T> next;

      public Node(T data, Node<T> next) {
         this.data = data;
         this.next = next;
      }
   }

   public String toString() {
      StringBuilder sb = new StringBuilder('[');
      while (head != null) {
         sb.append("<-");
         sb.append(head.data);
         sb.append("");
         head = head.next;
      }
      return sb.toString();
   }


   public static void main(String[] args) {
      MyScratchQueue<Integer> q = new MyScratchQueue<>();

      for (int i = 0; i < 10; i++) {
         q.Enqueue(i);
      }

      System.out.println(q.toString());

		/*while(!q.isEmpty()){
			System.out.println(q.Dequeue());
		}*/
   }

}
