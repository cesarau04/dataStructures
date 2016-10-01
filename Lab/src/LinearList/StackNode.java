package LinearList;

import java.util.EmptyStackException;

public class StackNode<T> implements StackInterface<T> {
	int size;
	SNode<T> last;
	
	
	public StackNode(){
		this.size = 0;
		this.last = null;
	}

	@Override
	public T peek() {
		if(isEmpty())
			throw new EmptyStackException();
		
		return this.last.value;
	}

	@Override
	public T pop() {
		if(isEmpty())
			throw new EmptyStackException();
		
		if(this.last.previous!=null){
			SNode<T> temp = new SNode<T>(this.last.value);
			this.last = this.last.previous;
			this.size--;
			return temp.value;
		}else{
			SNode<T> temp = new SNode<T>(this.last.value);
			this.last = null;
			this.size--;
			return temp.value;
		}
	}

	@Override
	public void push(T t) {
		if(this.isEmpty()){
			SNode<T> newT = new SNode<T>(t, null);
			this.last = newT;
			
		}else{
			SNode<T> temp = new SNode<T>(this.last.value);
			temp = this.last;
			SNode<T> newT = new SNode<T>(t, temp);
			this.last = newT;
		}
		this.size++;
	}
	
	@Override
	public boolean isEmpty() {
		return this.size==0;
	}
	
	private static class SNode<T>{
		T value;
		SNode<T> previous;
		
		public SNode(T value){
			this.value = value;
		}
		public SNode(T value, SNode<T> previous){
			this.value = value;
			this.previous = previous;
		}
	}
}
