package Utilities;

import LinearList.ChainLinearList;
import java.util.ListIterator;
import java.util.Stack;

public class Check {
  public static boolean Iterative(ChainLinearList<Integer> binary) {
    Stack<Integer> count = new Stack<>();
    ListIterator<Integer> iter = binary.getIterator();
    int push = binary.get(0);
    while (iter.hasNext()) {
      if (count.empty()) {
        push = iter.next();
        count.push(push);
      } else {
        int n = iter.next();
        if (n == push)
          count.push(n);
        else
          count.pop();
      }
    }
    return count.empty();
  }
}
