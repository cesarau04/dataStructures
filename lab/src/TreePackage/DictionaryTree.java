package TreePackage;

import java.util.Iterator;
import TreePackage.*;

import DictionaryPackage.DictionaryInterface;

public class DictionaryTree<K extends Comparable<? super K>, V> implements DictionaryInterface<K,V> {

	private BinarySearchTree<Entry<K, V>> BST;

	@Override
	public V add(K key, V value) {
		Entry<K,V> newEntry = new Entry<K,V>(key, value);
		Entry<K,V> returnedEntry = BST.add(newEntry);
		
		V result = null;
		if(returnedEntry != null){
			result = returnedEntry.getValue();
		}
		return result;
	}
	@Override
	public V remove(K key) {
		Entry<K,V> temporalEntry = new Entry<K,V>(key, null);
		Entry<K,V> returnedEntry = BST.remove(temporalEntry);
		
		V result = null;
		if (returnedEntry != null){
			result = returnedEntry.getValue();
		}
		return result;
	}
	@Override
	public V getValue(K key) {
		Entry<K,V> temporalEntry = new Entry<K,V>(key, null);
		Entry<K,V> returnedEntry = BST.getEntry(temporalEntry);
		return returnedEntry.value;
	}
	@Override
	public boolean contains(K key) {
		return getValue(key)!=null;
	}


	@Override
	public Iterator<K> getKeyIterator() {
		return new KeyIterator();
	}
	
	private class KeyIterator implements Iterator<K>{

		Iterator<Entry<K,V>> localIterator;
		
		public KeyIterator(){
			localIterator = BST.getInorderIterator();
		}
		
		@Override
		public boolean hasNext() {
			return localIterator.hasNext();
		}

		@Override
		public K next() {
			Entry<K, V> nextEntry = localIterator.next();
			return nextEntry.getKey();
		}
		
		public void remove(){
			throw new UnsupportedOperationException();
		}		
	}
	

	@Override
	public Iterator<V> getValueIterator() {
		return new ValueIterator();
	}
	
	private class ValueIterator implements Iterator<V>{

		Iterator<Entry<K,V>> localIterator;
		
		public ValueIterator(){
			localIterator = BST.getInorderIterator();
		}
		
		@Override
		public boolean hasNext() {
			return localIterator.hasNext();
		}

		@Override
		public V next() {
			Entry<K, V> nextEntry = localIterator.next();
			return nextEntry.getValue();
		}
		
		public void remove(){
			throw new UnsupportedOperationException();
		}	
	}

	
	@Override
	public boolean isEmpty() {
		return BST.isEmpty();
	}
	@Override
	public int getSize() {
		return BST.getNumberOfNodes();
	}
	@Override
	public void clear() {
		BST.clear();
	}
	
	
	@SuppressWarnings("hiding")
	private class Entry<S extends Comparable<? super K>,V> implements Comparable<Entry<K,V>>{

		private K key;
		private V value;
		
		private Entry(K key, V value){
			this.key = key;
			this.value = value;
		}
		
		public int compareTo(Entry<K,V> xEntry) {
			return key.compareTo(xEntry.key);
		}
		
		@SuppressWarnings("unused")
		public boolean equals(Entry<K,V> entryOne, Entry<K,V> entryTwo){
			if(entryOne.key.compareTo(entryTwo.key) == 0){
				return true;
			}
			return false;
		}
		
		public K getKey(){
			return this.key;
		}
		
		public V getValue(){
			return this.value;
		}
		
		@SuppressWarnings("unused")
		public void setValue(V value){
			this.value = value;
		}
	}
	
	public static void main(String[] args) {
		DictionaryTree<String, Integer> DT = new DictionaryTree<String, Integer>();
		try {
			DT.add("Cesar", 18);
			DT.add("Katy", 16);
			DT.add("Lolis", 23);
			DT.add("Samanta", 8);
			DT.add("Doni", 56);
			DT.add("Carlos", 17);
			DT.add("Marti", 51);
			DT.add("Diego", 15);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
		
		System.out.println(DT.contains("Cesar"));
	}
}
