package Utilities;

import java.util.Stack;
import LinearList.ArrayLinearList;

public class Conversion {
	
  public static final String OPERATORS = "+-*/^%";
  
  public static String RecursiveTwo(ArrayLinearList<Character> prefix, int idx) {
    char first = prefix.get(idx); 
    
    if (OPERATORS.indexOf(first) != -1) { 
      StringBuilder sb = new StringBuilder(RecursiveTwo(prefix, ++idx)); 
      return sb.append(RecursiveTwo(prefix, idx+=sb.length())).append(first).toString(); 
    } else {
      return String.valueOf(first); 
    }
  }

  public static ArrayLinearList<Character> Recursive(ArrayLinearList<Character> prefix, ArrayLinearList<Character> infix) {
    return ArrayLinearList.toCharacterArrayLinearList(RecursiveTwo(prefix, 0));
  }

  public static ArrayLinearList<Character> Iterative(ArrayLinearList<Character> prefix, ArrayLinearList<Character> infix) {
    Stack<String> stack = new Stack<>();
    for (int i = 0; i < prefix.size(); i++) { 
      char first = prefix.get(i);
      if (OPERATORS.indexOf(first) != -1) { 
        stack.push(Character.toString(first)); 
      } else {  
        String last = stack.peek(); 
        stack.push(Character.toString(first));  
        while (OPERATORS.indexOf(last) == -1) { 
          String second = stack.pop(); 
          String result = (new StringBuilder(stack.pop())).append(second).append(stack.pop()).toString(); 
          try { last = stack.peek(); } catch (Exception e) { return ArrayLinearList.toCharacterArrayLinearList(result); } 
          stack.push(result); 
        }
      }
    }
    return ArrayLinearList.toCharacterArrayLinearList(stack.pop());
  }

}
