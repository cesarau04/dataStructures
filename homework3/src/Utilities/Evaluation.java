package Utilities;

import java.util.Stack;

import LinearList.ChainLinearList;

import java.util.ListIterator;


@SuppressWarnings("rawtypes")
public class Evaluation {
	public static final String OPERATORS = "+-*/^%";
	static int index=0;
	
	public static ChainLinearList<Character> Recursive(ChainLinearList<Character> prefix){
		
		ListIterator<Character> itr = prefix.getIterator();
		if(itr.hasNext()){
			Character actual = prefix.get(index);
			index++;
			if(actual=='+' || actual == '-'){
				
			}
		}
		return null;
	}

	
	@SuppressWarnings("unchecked")
	public static void main(String[] args) {
		ChainLinearList prefix = new ChainLinearList();
		prefix.add('+');prefix.add('+');prefix.add('A');prefix.add('B');prefix.add('Z');prefix.add('-');prefix.add('D');
		Recursive(prefix);
	}
	public static double Iterative(ChainLinearList<Character> prefix) {
		ListIterator<Character> it = prefix.getIterator();
		Stack<String> stack = new Stack<>();
		while (it.hasNext()) {
			char c = it.next();
			if (OPERATORS.indexOf(c) != -1) {
				stack.push(Character.toString(c));
			} else {
				String last = stack.peek();
				stack.push(Character.toString(c));
				while (OPERATORS.indexOf(last) == -1) {
					Double n2 = Double.parseDouble(stack.pop());
					Double n1 = Double.parseDouble(stack.pop());
					char op = stack.pop().charAt(0);
					double result = evalExp(n1, n2, op);
					try { last = stack.peek(); } catch (Exception e) { return result; }
					stack.push(Double.toString(result));
				}
			}
		}
		return Double.parseDouble(stack.pop());
	}
	
	private static double evalExp(double n1, double n2, char op) {
	    	switch (op) {
	    	case '-':
	    		return n1 - n2;
	    	case '*':
	    		return n1 * n2;
	    	case '/':
	    		return n1 / n2;
	    	case '%':
	    		return n1 % n2;
	    	case '^':
	    		return Math.pow(n1, n2);
	    	default:
	    		return n1 + n2;
	    	}
	}
}
