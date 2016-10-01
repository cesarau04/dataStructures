package DLinkedList;

/*
 * When creating a class inside a class there's two ways of doing it.
 * 
 * 1. Inner Class - public class @name, can use fields and methods from the parent class, also 
 * if it is generic it's receives the generic class from its father.
 * 
 * 2. Inside Class - public static class @name, cannot use fields and methods from parent class and if generic are used then it should be
 * tell that way.
 */

import java.util.ListIterator;
import java.util.NoSuchElementException;


class DLinkedList<T> implements List<T>  {
	 int size;
	 DNode<T> first;
	 DNode<T> last;

 	public DLinkedList() { //default DLinkList --> Begins with 0 and null's pointer
		this.first = null;
		this.last = null;
		this.size = 0;
 	}

 	public ListIterator<T> getIterator() { //return the iterator of the DLinkList
 		return new LstItr();
 	}

 	private void checkIndex(int index) { //Check the index is between bounds
 		if (index < 0 || index > this.size) {
 			throw new IndexOutOfBoundsException();
 		}
 	}

 	public ListIterator<T> getIterator(int index) { //return the iterator but index is given
 		checkIndex(index);
 		return new LstItr(index);
 	}

 	public boolean isEmpty() { //check whether DLinkList is empty (return false if not)
 		return this.size == 0;
 	}

 	public int size() { //return this.size
 		return this.size;
 	}

 	public T get(int index) { //return the "T value" of an given index
 		checkIndex(index);
 		return getNode(index).value;
 	}

 	private DNode<T> getNode(int index) { //return the DNode<T> Object with its methods and fields.
 		DNode<T> n = this.first;
 		while (index > 0) {
 			n = n.next;
 			index--;
 		}
 		return n;
 	}

 	private void addFirst(T value) { //add a newNode at the start of the DLinkList
 		DNode<T> newNode = new DNode<T>(null, value, this.first);
 		if (!this.isEmpty()) {
 			this.first.prev = newNode;
 		} else {
 			this.last = newNode;
 		}
 		this.first = newNode;
 		this.size++;
 	}
 
 	private void addLast(T value) { //add a newNode at the end of the DLinkList
 		DNode<T> newNode = new DNode<T>(this.last, value, null);
 		if (this.isEmpty()) {
 			this.first = newNode;
 		} else {
 			this.last.next = newNode;
 		}
 		this.last = newNode;
 		this.size++;
 	}

 	public int indexOf(T x) { //return indexOf a given T
 		ListIterator<T> iterator = this.getIterator();
 		while (iterator.hasNext()) {
 			T current = iterator.next();
 			if (current == x) {
 				return iterator.previousIndex();
 			}
 		}
 		return -1;
 	}
 	
 	public T remove(int index) { //remove the element at "index"
 		checkIndex(index);
 		DNode<T> toRemove = getNode(index);
 		toRemove.prev.next = toRemove.next;
 		toRemove.next.prev = toRemove.prev;
 		size--;
 		T r = toRemove.value;
 		toRemove = null;
 		return r;
 	}
 	
 	public void add(T element, int index) { //add the T element at index position
 		checkIndex(index);
 		DNode<T> nextNode = getNode(index);
 		DNode<T> newNode = new DNode<T>(nextNode.prev, element, nextNode);
 		nextNode.prev.next = newNode;
 		nextNode.prev = newNode;
 		size++;
 	}
 	
 	public String toString() { //return a string of the elements of DLinkList
 		StringBuilder sb = new StringBuilder('[');
 		DNode<T> node = this.first;
 		while (node != null) {
 			sb.append(" [");
 			sb.append(node.value);
 			sb.append("] ");
 			node = node.next;
 		}
 		return sb.toString();
 	}
 	
 	/*
 	 * Class made for save the next and prev information for each node that form the DLinkList
 	 */
 	
 	private static class DNode<T> {
 		T value;
 		DNode<T> next;
 		DNode<T> prev;
 		
 		public DNode(DNode<T> prev, T value, DNode<T> next) {
 			this.value = value;
 			this.prev = prev;
 			this.next = next;
 		}
 	}
 	
 	/*
 	 * Iterator class
 	 * Allows to search through the DLinkList
 	 */
 	
 	class LstItr implements ListIterator<T> {
 		DNode<T> next;
 		DNode<T> ultimoVisitado = null;
 		int nextIndex;
 		
 		public LstItr() {
 			this.next = first;
 			this.nextIndex = 0;
 		}
 		
 		public LstItr(int index) {
 			if (index == size) {
 				this.next = null;
 			} else {
 				this.next = getNode(index);
 			}
 			this.nextIndex = index;
 		}
 		
 		
 		public int previousIndex() { //return previous index
 			return this.nextIndex - 1;
 		}
 		
 		public int nextIndex() { //return next index
 			return this.nextIndex;
 		}
 		
 		public T previous() { //previos element
 			if (!this.hasPrevious())
 				throw new NoSuchElementException();
 			if (this.next == null)
 				this.next = last;
 			else
 				this.next = this.next.prev;
 			
 			this.ultimoVisitado = this.next;
 			this.nextIndex--;
 			return ultimoVisitado.value;
 		}
 		
 		public T next() { //next element
 			if (this.hasNext()) {
 				ultimoVisitado = this.next;
 				this.nextIndex++;
 				this.next = this.next.next;
 				return this.ultimoVisitado.value;
 			}
 			else throw new NoSuchElementException();
 		}
 		
 		public boolean hasNext() { //has next?
 			return this.next != null;
 		}
 		
 		public boolean hasPrevious() { //has previous?
 			return this.nextIndex > 0;
 		}
 		
 		//unused methods due to unnecessary
 		public void add(T el){}
 		public void set(T el){}
 		public void remove(T el){}
 		public void remove(){}
 		
 	}
 	
 	
 	public static void main(String[] args) { 		
 		try {
 			DLinkedList<Integer> dl = new DLinkedList<Integer>();
 	 		dl.addFirst(1);
 	 		dl.addLast(4);
 	 		dl.addLast(8);
 	 		dl.addLast(12);
 	 		dl.addFirst(-5);
 	 		dl.addFirst(-6);
 			System.out.println(dl);
 	 		System.out.println("this is your index " + dl.indexOf(4));
 	 		System.out.println(dl.getNode(2).value);
 	 		System.out.println();
 	 		
 	 		ListIterator<Integer> it = dl.getIterator();
 	 		for (int i = 0; i < dl.size(); i++) {
 	 			System.out.println(it.next());
			}
 	 		
 	 		System.out.println();
 	 		System.out.println(it.previous());
 	 		System.out.println(it.previous());
 	 		System.out.println(it.previous());
 	 		System.out.println(it.previous());
 	 		System.out.println(it.previous());
 	 		System.out.println(it.previous());
 	 		
 	 		dl.remove(4);
 	 		System.out.println(dl);
 	 		dl.add(7, 4);
 	 		System.out.println(dl);
 	 		dl.addFirst(-10);
 	 		dl.addLast(23);
 	 		System.out.println(dl);
 	 		
 	 		System.out.println(dl.isEmpty());
 	 		System.out.println(dl.get(3));
 	 		dl.checkIndex(18);
 	 		
 	 		
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
 		
 	}
}	