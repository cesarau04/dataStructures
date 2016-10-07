package LinearList;

import java.util.ListIterator;
import java.util.NoSuchElementException;


public class ChainLinearList<T> implements LinearList<T>  {
	 int size;
	 CNode<T> first;

 	public ChainLinearList() { 
		this.first = null;
		this.size = 0;
 	}
 	
 	public ChainLinearList(T[] array) {
 	    this();
 	    for (T el : array) {
 	      this.add(el);
 	    }
 	}

 	public ListIterator<T> getIterator() {
 		return new ChainItr();
 	}

 	private void checkIndex(int index) { 
 		if (index < 0 || index > this.size) {
 			throw new IndexOutOfBoundsException();
 		}
 	}

 	public ListIterator<T> getIterator(int index) { 
 		checkIndex(index);
 		return new ChainItr(index);
 	}

 	@Override
 	public boolean isEmpty() { 
 		return this.size == 0;
 	}
 	
 	@Override
 	public int size() { 
 		return this.size;
 	}
 	
 	@Override
 	public T get(int index) {
 		checkIndex(index);
 		return getNode(index).value;
 	}

 	private CNode<T> getNode(int index) { 
 		CNode<T> n = this.first;
 		while (index > 0) {
 			n = n.next;
 			index--;
 		}
 		return n;
 	}
 
 	@Override
 	public int indexOf(T x) { 
 		ListIterator<T> iterator = this.getIterator();
 		while (iterator.hasNext()) {
 			T current = iterator.next();
 			if (current == x) {
 				return iterator.previousIndex();
 			}
 		}
 		return -1;
 	}
 	
 	@Override
 	public T remove(int index) {
 		checkIndex(index);
 		
 		if(index == 0){
 			CNode<T> toRemove = getNode(index);
 			this.first = toRemove.next;
 			this.size--;
 			return toRemove.value;
 		}
 		CNode<T> toRemove = getNode(index);
 		CNode<T> previous = getNode(index-1);
 		previous.next = toRemove.next;
 		this.size--;
 		return toRemove.value;
 	}
 	
 	
 	public void add(T element) {
 	    this.add(this.size, element);
 	}
 	
	@Override
 	public void add(int index, T element) { 
 		checkIndex(index);
 		if(index==0){
 			CNode<T> oldFirst = getNode(index);
 			CNode<T> newFirst = new CNode<T>(element);
 			this.first = newFirst;
 			newFirst.next = oldFirst;
 			this.size++;
 		}else if(index>0 && index<size-1){
 			CNode<T> newNode = new CNode<T>(element);
 			CNode<T> previousNode = getNode(index);
 			CNode<T> nextNode = getNode(index+1);
 			previousNode.next = newNode;
 			newNode.next = nextNode;
 			this.size++;
 		}else{
 			CNode<T> newNode = new CNode<T>(element);
 			CNode<T> previousNode = getNode(size-1);
 			previousNode.next = newNode;
 			this.size++;
 		}
 	}

	@Override
	public void output() {
		if (this.first == null)  {
		      System.out.println("[ ]");
		      return;
		    }
		    StringBuilder sb = new StringBuilder("[ ");
		    CNode<T> actualN = this.first;
		    while (actualN.next != null) {
		      sb.append(actualN.value + ",");
		      actualN = actualN.next;
		    } sb.append(actualN.value + " ]");
		    System.out.println(sb);
	}
	
 	
 	private static class CNode<T> {
 		T value;
 		CNode<T> next;
 		
 		public CNode(T value){
 			this.value=value;
 		}

		@SuppressWarnings("unused")
		public CNode(T value, CNode<T> next) {
 			this.value = value;
 			this.next = next;
 		}
 	}
 
 	
 	class ChainItr implements ListIterator<T> {
 		CNode<T> next;
 		CNode<T> ultimoVisitado = null;
 		int nextIndex;
 		
 		public ChainItr() {
 			this.next = first;
 			this.nextIndex = 0;
 		}
 		
 		public ChainItr(int index) {
 			if (index == size) {
 				this.next = null;
 			} else {
 				this.next = getNode(index);
 			}
 			this.nextIndex = index;
 		}
 		
 		@Override
 		public int previousIndex() { 
 			return this.nextIndex - 1;
 		}
 		@Override
 		public int nextIndex() {
 			return this.nextIndex;
 		}
 		
 		@Override
 		public T next() { 
 			if (this.hasNext()) {
 				ultimoVisitado = this.next;
 				this.nextIndex++;
 				this.next = this.next.next;
 				return this.ultimoVisitado.value;
 			}
 			else throw new NoSuchElementException();
 		}
 		@Override
 		public boolean hasNext() { 
 			return this.next != null;
 		}
 		
 		//unused methods due to unnecessary
 		@Override public void add(T e){}
 		@Override public void set(T e){}
 		@Override public void remove(){}
 		@Override public boolean hasPrevious() {return false;}
 		@Override public T previous() {return null;}
 		
 	}
}	