package LinearList;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.concurrent.ThreadLocalRandom;

public class AverageTimeChainArray {
	
	public static void main(String[] args) {
		
	    int[] cosas = new int[] {1, 10, 100, 1000, 10000, 39000};
	    long[] exTimes = new long[cosas.length];
	    try {
	      ArrayLinearList<Integer> al = new ArrayLinearList<Integer>();
	      ChainLinearList<Integer> ch = new ChainLinearList<Integer>();

	      BufferedWriter writer = new BufferedWriter(new FileWriter("AverageTimeChainArray.txt"));



	      writer.write("Execution time for ArrayLinearList:");
	      writer.newLine();
	      writer.write("====================================");
	      writer.newLine();
	      for (int i = 0; i < cosas.length; i++) {

	        // add to ArrayLinearList
	        start(i, exTimes);
	        for (int j = 0; j < cosas[i]; j++) {
	          al.add(ThreadLocalRandom.current().nextInt(al.size()+1), ThreadLocalRandom.current().nextInt());
	        }
	        stop(i, exTimes);

	        writer.write("added " + cosas[i] + " elements:\t" + exTimes[i] + " nanoseconds");
	        writer.newLine();


	        // remove from ArrayLinearList
	        start(i, exTimes);
	        for (int j = 0; j < cosas[i]; j++) {
	          al.remove(ThreadLocalRandom.current().nextInt(al.size()));
	        }
	        stop(i, exTimes);

	        writer.write("removed " + cosas[i] + " elements:\t" + exTimes[i]/1000000 + " nanoseconds");
	        writer.newLine();
	        writer.write("----------------------------------");
	        writer.newLine();

	      }

	      writer.newLine();
	      writer.write("Execution time for ChainLinearList:");
	      writer.newLine();
	      writer.write("====================================");
	      writer.newLine();
	      for (int i = 0; i < cosas.length; i++) {

	        // add to chain
	        start(i, exTimes);
	        for (int j = 0; j < cosas[i]; j++) {
	          ch.add(ThreadLocalRandom.current().nextInt(al.size()+1), ThreadLocalRandom.current().nextInt());
	        }
	        stop(i, exTimes);

	        writer.write("added " + cosas[i] + " elements:\t" + exTimes[i] + " nanoseconds");
	        writer.newLine();


	        // remove from chain
	        start(i, exTimes);
	        for (int j = 0; j < cosas[i]; j++) {
	          ch.remove(ThreadLocalRandom.current().nextInt(ch.size()));
	        }
	        stop(i, exTimes);

	        writer.write("removed " + cosas[i] + " elements:\t" + exTimes[i] + " nanoseconds");
	        writer.newLine();
	        writer.write("----------------------------------");
	        writer.newLine();
	      }
	      writer.close();
	    } catch (IOException e) {
	      System.out.println(e);
	    }
	  }

	  private static void start(int i, long[] exTimes) {
	    exTimes[i] = System.nanoTime();
	  }

	  private static void stop(int i, long[] exTimes) {
	    exTimes[i] = System.nanoTime() - exTimes[i];
	  }

}
