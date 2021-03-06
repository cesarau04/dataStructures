
package LinearList;

import java.util.NoSuchElementException;

import LinearList.LinearList;

import java.util.ListIterator;

public class ArrayLinearList<T> implements LinearList<T> {
	private T[] arr;
	private int size;
	public static final int defSize = 100;

	@SuppressWarnings("unchecked")
	public ArrayLinearList(int initialSize) {
	        if (initialSize < 1) {
	                throw new IllegalArgumentException();
	        }
	        this.arr = (T[]) new Object[initialSize];
	        this.size = 0;
	}

	@SuppressWarnings("unchecked")
	public ArrayLinearList(T[] givenArray) {
	        this.arr = (T[]) new Object[defSize];
	        System.arraycopy(givenArray, 0, this.arr, 0, givenArray.length);
	        this.size = givenArray.length;
	}

	public ArrayLinearList() {
	        this(defSize);
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
	        if (index < 0 || index >= this.size) throw new IndexOutOfBoundsException();
	        return this.arr[index];
	}

	@Override
	public int indexOf(T x) {
	        for (int i = 0; i < this.size; i++) {
	                if (this.arr[i].equals(x))
	                        return i;
	        }
	        return -1;
	}

	@Override
	public T remove(int index){
	        if (index < 0 || index >= this.size) throw new IndexOutOfBoundsException();
	        T removedItem = this.arr[index];
	        for (int i = index + 1; i < this.size; i++) {
	                this.arr[i - 1] = this.arr[i];
	        }
	        this.arr[--this.size] = null;
	        return removedItem;
	};


	@SuppressWarnings("unchecked")
	private void resize() {
	        T[] newArr = (T[]) new Object[this.size * 2];
	        System.arraycopy(this.arr, 0, newArr, 0, this.arr.length);
	        this.arr = newArr;
	}

	@Override
	public void add(int index, T newE) {
	        if (index < 0 || index > this.size) throw new IndexOutOfBoundsException();

	        if (this.size == this.arr.length) {
	                this.resize();
	        }

	        for (int i  = this.size - 1; i >= index; i--)
	                this.arr[i + 1] = this.arr[i];
	        this.arr[index] = newE;
	        this.size += 1;
	}

	@Override
	public void output() {
		System.out.print("[]= ");
	        for (int i = 0; i < this.size-1; i++) {
	                System.out.print(arr[i] + ", ");
	        }
	        System.out.print(arr[this.size-1]);
	}

	public ListIterator<T> iterator() {
	        return new IteratorArray();
	}

	public ListIterator<T> iterator(int index) {
	        return new IteratorArray(index);
	}

	class IteratorArray implements ListIterator<T> {
	        private T next;
	        private int nextIndex;

	        public IteratorArray() {
	                this.next = arr[0];
	                this.nextIndex = 0;
	        }
	        public IteratorArray(int index) {
	                this.next = arr[index];
	                this.nextIndex = index;
	        }

	        public void add(T el){
	                this.add(el);
	        };
	        public void set(T el){};
	        public void remove(T el){};
	        public void remove(){};
	        public int previousIndex() {
	                return this.nextIndex - 1;
	        }

	        public int nextIndex() {
	                return this.nextIndex;
	        }

	        public T previous() {
	                if (!this.hasPrevious())
	                        throw new NoSuchElementException();

	                this.nextIndex--;
	                this.next = arr[this.nextIndex];
	                return arr[nextIndex];
	        }

	        public T next() {
	                if (this.hasNext()) {
	                        this.nextIndex++;
	                        this.next = arr[nextIndex];
	                        return arr[nextIndex-1];
	                }
	                else throw new NoSuchElementException();
	        }

	        public boolean hasNext() {
	                return this.next != null;
	        }

	        public boolean hasPrevious() {
	                return this.nextIndex > 0;
	        }
	}
	
	public static ArrayLinearList<Character> toCharacterArrayLinearList(String in) {
		  if ( in == null ) {
		     return null;
		   }
		   
		   int len = in.length();
		   ArrayLinearList<Character> list = new ArrayLinearList<>(len);
		   for (int i = 0; i < len ; i++) {
		     list.add(i, new Character(in.charAt(i)));
		   }
		   return list;
		}
}
