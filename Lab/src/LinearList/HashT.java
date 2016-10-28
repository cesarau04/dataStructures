package LinearList;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.NoSuchElementException;
import java.util.Queue;



/**
 * @author Cesar
 *
 * @param <K> is the key
 * @param <V> is the value
 */

public class HashT<K,V> {
	private int capacity;
	private int size; //elements in table
	private int maxCap;
	private static final int INI_CAP=13;
	private static float carga = .75f;
	private Node<K,V>[] table;

	public HashT(){
		this(INI_CAP, carga);
	}

	/**
	 * @param capacity Initialize the table with the given capacity
	 */
	@SuppressWarnings("unchecked")
	public HashT(int capacity, float carga){
		this.capacity=capacity;
		this.size=0;
		HashT.carga=carga;
		this.maxCap = (int) (this.capacity*HashT.carga);
		table = new Node[this.capacity];
	}

	public Iterator<K> getIteratorKey(){
		return new keyIterator<K>();
	}

	public Iterator<V> getIteratorValue(){
		return new valueIterator<V>();
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

	/**
	 * @param key The key value
	 * @param value The value to be stored
	 * @return
	 */
	
	public V put(K key, V value) {
		if (key==null||value==null) {
			throw new IllegalArgumentException("Neither null-keys nor null-values are accepted");
		}
		if (this.size>=this.maxCap){
			this.rehashing();
		}
		int pos=this.hash(key);
		for (Node<K,V> x = table[pos]; x!=null; x=x.next) {
			if (x.key.equals(key)){
				V toBeRemoved = x.value;
				x.value = value;
				return toBeRemoved;
			}
		}
		Node<K, V> first = new Node<>(key, value, this.table[pos]);
		this.table[pos]=first;
		this.size++;
		return null;
	}

	@SuppressWarnings("unchecked")
	public void clean(){
		this.table= new Node[this.capacity];
		this.size=0;
	}

	public K remove(K key){
		if (key==null) {
			throw new IllegalArgumentException("Neither null-keys nor null-values are accepted");
		}
		int pos=this.hash(key);

		for (Node<K,V> x = table[pos]; x != null;) {
	        if (x.next.key.equals(key)) {
	          K k = x.next.key;
	          size--;
	          x.next = null;
	          return k;
	        }
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	public void rehashing (){
		Node<K,V>[] copyTable = new Node[this.capacity];
		System.arraycopy(this.table, 0, copyTable, 0, this.capacity);
		this.capacity = (this.capacity*2)+1;
		this.size=0;
		this.table = new Node[this.capacity];
		this.maxCap = (int) (this.capacity * HashT.carga);
	    for (Node<K, V> x : copyTable) {
	      for (; x != null; x = x.next) {
	        put(x.key, x.value);
	      }
	    }
	    copyTable = null;
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

	private abstract class HashIterator<E> implements Iterator<E>{
		Node<K,V> next;
		int index;

		public HashIterator(){
			for (int i = 0; i < capacity; i++) {
				if(table[i]!=null){
					next=table[i];
					index=i;
					return;
				}
			}
		}

		public boolean hasNext(){
			return next!=null;
		}

		
		public Node<K,V> nextNode(){
			Node<K,V> toBeRemoved = next;

			if(toBeRemoved == null){
				throw new NoSuchElementException();
			}

			this.next=toBeRemoved.next;
			if(next==null){
				this.index++;
				while(index<capacity && table[index]==null){
					this.index++;
				}
				if(index<capacity){
					this.next=table[index];
				}
			}
			return toBeRemoved;
		}

	}

	@SuppressWarnings("hiding")
	private class valueIterator<V> extends HashIterator<V>{

		@SuppressWarnings("unchecked")
		@Override
		public V next() {
			if(hasNext()){
				return (V) nextNode().value;
			}
			return null;
		}

	}

	@SuppressWarnings("hiding")
	private class keyIterator<K> extends HashIterator<K> {

		@SuppressWarnings("unchecked")
		@Override
		public K next() {
			if(hasNext()){
				return (K) nextNode().key;
			}
			return null;
		}

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

	public static void main(String[] args) {
		HashT<String, Integer> hashTable =  new HashT<String, Integer>();
		System.out.println(hashTable.capacity);
		System.out.println(hashTable.size);
		System.out.println(hashTable.isEmpty());
		System.out.println(hashTable.maxCap);

		hashTable.put("Cesar", 18);
		hashTable.put("Maria", 24);
		hashTable.put("Cesar", 18);
		hashTable.put("Donato", 56);
		hashTable.put("Marti", 51);
		hashTable.put("Juan", 18);
		hashTable.put("Carlos", 19);
		hashTable.put("Rube", 21);
		hashTable.put("El bolas", 19);
		hashTable.put("Karla", 19);
		System.out.println(hashTable.size);

		hashTable.put("Whitney Houston", 99);

		System.out.println(hashTable.maxCap);
		System.out.println(hashTable.size);
		System.out.println(hashTable.isEmpty());
		System.out.println(hashTable.contains("Karla"));
		System.out.println(hashTable.get("Donato"));

		System.out.println(hashTable.remove("Cesar"));
		System.out.println(hashTable.contains("Cesar"));
		hashTable.remove("asdfasdf");
		System.out.println(hashTable.size);
	}
}
