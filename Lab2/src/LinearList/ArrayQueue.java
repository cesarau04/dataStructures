package LinearList;

import java.util.Arrays;
import java.util.NoSuchElementException;

public class ArrayQueue<T> implements QueueInterface<T>{
	int capacity, size;
	NQueue<T> front, rear;
	@SuppressWarnings("rawtypes")
	NQueue[] arrayNQueue;

	public ArrayQueue(){
		this.capacity = 8;
		this.size=0;
		 arrayNQueue = new NQueue[this.capacity];
	}

	public ArrayQueue(int capacity){
		this.capacity=capacity;
		this.size=0;
		arrayNQueue = new NQueue[this.capacity];
	}

	@Override
	public boolean isEmpty() {
		return size==0;
	}

	@SuppressWarnings("unchecked")
	@Override
	public T dequeue() {
		if(this.isEmpty()){
			throw new NoSuchElementException("Queue is empty, cannot dequeue");
		}else{
			NQueue<T> removedElement = new NQueue<T>(this.front.value, null, this.front.position);
			arrayNQueue[removedElement.position] = null;
			this.front = arrayNQueue[removedElement.position+1];
			this.size--;
			return removedElement.value;

		}
	}

	@Override
	public void enqueue(T t) {
		if(t==null){
			throw new NullPointerException("This queue does not allow null entries");
		}


		if(this.isFull()){
			this.duplica();
			NQueue<T> newNode = new NQueue<T>(t, this.rear, this.rear.position+1);
			this.rear = newNode;
			arrayNQueue[newNode.position] = newNode;
			this.size++;
		}else if(this.isEmpty()){
			NQueue<T> newNode = new NQueue<T>(t, null, 0);
			this.front = newNode;
			this.rear = newNode;
			arrayNQueue[newNode.position] =  newNode;
			this.size++;
		}else{
			if(this.rear.position==capacity-1){
				NQueue<T> newNode = new NQueue<T>(t, this.rear, 0);
				this.rear = newNode;
				arrayNQueue[newNode.position] = newNode;
				this.size++;
			}else{
				NQueue<T> newNode = new NQueue<T>(t, this.rear, this.rear.position+1);
				this.rear = newNode;
				arrayNQueue[newNode.position] = newNode;
				this.size++;
			}
		}
	}

	@Override
	public T front() {
		return this.front.value;
	}

	@Override
	public T rear() {
		return this.rear.value;
	}

	private void duplica(){
		@SuppressWarnings("rawtypes")
		NQueue[] arrayNQueue;
		arrayNQueue = Arrays.copyOf(this.arrayNQueue, this.capacity*2);
		this.arrayNQueue = arrayNQueue;
		this.capacity=this.capacity*2;
	}

	private boolean isFull(){
		return this.size==this.capacity;
	}


	private static class NQueue<T>{
		T value;
		int position;
		@SuppressWarnings("unused")
		NQueue<T> previous;

		public NQueue(T value, NQueue<T> previous, int position){
			this.value=value;
			this.previous=previous;
			this.position=position;
		}
	}

	public static void main(String[] args) {
		ArrayQueue<Integer> queue = new ArrayQueue<Integer>(4);
		queue.enqueue(1); //size=1 [0]
		queue.enqueue(2); //size=2 [1]

		queue.dequeue();
		queue.dequeue();
		System.out.println(Arrays.toString(queue.arrayNQueue));
		try {
			System.out.println("array length: " + queue.arrayNQueue.length);
			System.out.println("size: " + queue.size);
			System.out.println("front: " + queue.arrayNQueue[queue.front.position].value); //return the rear
			System.out.println("rear: " + queue.arrayNQueue[queue.rear.position].value); //return the rear
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
}
