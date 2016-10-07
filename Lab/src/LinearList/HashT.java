package LinearList;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author Cesar
 *
 * @param <K> is the key
 * @param <V> is the value
 */

public class HashT<K,V> {
	private int capacity;
	private int size;
	private static final int INI_CAP=13;
	private Node<K,V>[] table;

	@SuppressWarnings("unchecked")
	public HashT(){
		this.capacity=HashT.INI_CAP;
		this.size=0;
		table = new Node[this.capacity];
	}

	/**
	 * @param capacity Initialize the table with the given capacity
	 */
	@SuppressWarnings("unchecked")
	public HashT(int capacity){
		this.capacity=capacity;
		this.size=0;
		table = new Node[this.capacity];
	}

	/**
	 * @param key The key value to be hashed
	 * @return Unsigned integer
	 */
	public int hash(K key){
		return key.hashCode()&0x7FFFFFF%this.capacity;
	}

	/**
	 * @return size of table
	 */
	public int size(){
		return this.size;
	}

	/**
	 * @return true whenever table is empty
	 */
	public boolean isEmpty(){
		return this.size()==0;
	}

	/**
	 * @param key The key value
	 * @return The value of the key given or null if it doesn't exist
	 */
	public V get(K key) {
		if (key==null) {
			throw new IllegalArgumentException("No null keys are accepted");
		}
		int pos = this.hash(key);
		for (Node<K,V> x = table[pos]; x!=null; x=x.next) {
			if (x.key.equals(key)) {
				return x.value;
			}
		}
		return null;
	}


	/**
	 * @param key The key value
	 * @return Whenever key exits in table
	 */
	public boolean contains(K key){
		return get(key)!=null;
	}



	public Iterable<K> key(){
		Queue<K> queue = new LinkedList<K>();
		for (int i = 0; i < this.capacity; i++) {
			for (Node<K,V> x = table[i]; x != null; x=x.next) {
				queue.add((K) x.key);
			}

		}
		//for (String s : k) {}
		return queue;
	}

	/**
	 * @param key The key value
	 * @param value The value to be stored
	 * @return
	 */
	@SuppressWarnings("unused")
	public void put(K key, V value) {
		if (key==null||value==null) {
			throw new IllegalArgumentException("Neither null-keys nor null-values are accepted");
		}
		int pos=this.hash(key);
		for (Node<K,V> x = table[pos]; x!=null; x=x.next) {
			if (x.next.key.equals(key)){
				x.value=value;
			}
			return;
		}
		//Node<K,V> = newNode;
		this.size++;
	}

	public V remove(K key){
		if (key==null) {
			throw new IllegalArgumentException("Neither null-keys nor null-values are accepted");
		}
		int pos=this.hash(key);
		for (Node<K,V> x = table[pos]; x!=null; x=x.next) {
			if (x.key.equals(key)){
				@SuppressWarnings("unused")
				Node<K,V> newNode = new Node<K,V>(x.key, x.value, table[pos]);
			}
		}
		return null;
	}

	private static class Node<K,V>{
		protected K key;
		protected V value;
		Node<K,V> next;

		public Node(K key, V value, Node<K,V> next){
			this.key=key;
			this.value=value;
			this.next=next;
		}
	}
}
