package MyStack;

import java.util.Iterator;

import MyStack.ChainLinearList;

public class MyDerivedStack<T> implements StackInterface<T>{

	ChainLinearList<T> chainList = new ChainLinearList<T>();
	
	@Override
	public void push(T element) {
		chainList.add(element);
	}

	@Override
	public T pop() {
		return chainList.remove(chainList.size-1);
	}

	@Override
	public T peek() {
		return chainList.get(0);
	}

	@Override
	public int size() {
		return chainList.size();
	}

	@Override
	public boolean isEmpty() {
		return chainList.isEmpty();
	}
	
	@Override
	public Iterator<T> getIterator() {
		throw new UnsupportedOperationException();
	}
	
	public static void main(String[] args) {
		MyDerivedStack<Integer> stack = new MyDerivedStack<Integer>();
		
		stack.push(5);
		stack.push(4);
		stack.push(3);
		stack.push(2);
		stack.push(1);
		
		while (!stack.isEmpty()) {
			System.out.println(stack.pop());
		}

	}

	
}
