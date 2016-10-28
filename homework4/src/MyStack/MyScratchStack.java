package MyStack;

import java.util.Arrays;
import java.util.Iterator;

public class MyScratchStack<T> implements StackInterface<T>{

	private T[] stack;
	private int topIndex;
	private static final int INIT_CAP = 10;
	
	public MyScratchStack() {
		this(INIT_CAP);
	}
	
	public MyScratchStack(int initCap) {
		@SuppressWarnings("unchecked")
		T[] tempStack = (T[])new Object[initCap];
		stack = tempStack;
		topIndex = -1;
	}

	@Override
	public void push(T element) {
		ensureCapacity();
		topIndex++;
		stack[topIndex] = element;
	}
	
	private void ensureCapacity(){
		if (topIndex == stack.length - 1)
			stack = Arrays.copyOf(stack, 2 * stack.length);
	}

	@Override
	public T pop() {
		T top = null;
		if (!isEmpty()){
			top = stack[topIndex];
			stack[topIndex] = null;
			topIndex--;
		}
		return top;
	}

	@Override
	public T peek() {
		T top = null;
		if (!isEmpty())
			top = stack[topIndex];
		return top;
	}

	@Override
	public int size() {
		return topIndex+1;
	}

	@Override
	public boolean isEmpty() {
		return topIndex < 0;
	}

	@Override
	public Iterator<T> getIterator() {
		throw new UnsupportedOperationException();
	}
	
	public static void main(String[] args) {
		MyScratchStack<Integer> stack = new MyScratchStack<Integer>();
		stack.push(12);
		stack.push(11);
		stack.push(10);
		stack.push(9);
		stack.push(8);
		stack.push(7);
		stack.push(6);
		stack.push(5);
		stack.push(4);
		stack.push(3);
		stack.push(2);
		stack.push(1);
		
		while(!stack.isEmpty()){
			System.out.println(stack.pop());
		}
	}
}
