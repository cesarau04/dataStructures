package MyQueue;

import java.util.ArrayList;

public class MyRadixSort {

   public static ArrayList<Integer> RadixSort(ArrayList<Integer> List, int k) {
      int Radix = 10;
      int power = 1;
      int digit;

      QueueInterface<Integer>[] digitQueue = new MyScratchQueue[Radix];

      for (int i = 0; i < digitQueue.length; i++) {
         digitQueue[i] = new MyScratchQueue<Integer>();
      }

      for (int i = 0; i < k; i++) {
         for (int j = 0; j < List.size(); j++) {
            digit = List.remove(0);
            digitQueue[(digit / power) % 10].Enqueue(digit);
         }
      }

      for (int j = 0; j < Radix; j++) {
         while (!digitQueue[j].isEmpty()) {
            Integer a = digitQueue[j].Dequeue();
            List.add(a);
         }
      }

      power *= 10;
      return List;
   }

   public static void main(String[] args) {
      ArrayList<Integer> input = new ArrayList<>(10);

      input.add(91); input.add(06); input.add(85); input.add(15); input.add(92); input.add(35); input.add(30);
      input.add(22); input.add(39);

      System.out.println(input);
      ArrayList<Integer> output = RadixSort(input, 10);
      System.out.println(output);
   }
}
