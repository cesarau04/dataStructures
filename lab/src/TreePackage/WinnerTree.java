package TreePackage;

/**
 * Created by Cesar on 04/11/2016.
 */

public class WinnerTree {

   /*
   n = numbers of players
   n-1 = inner nodes
   n-s = nodes in the lower level

   s = the most right node in the tree
   lowExt =
   offset =
    */

   int players[], tree[];
   int lowExt, offset;

   public void initialize(int[] p) {
      int i, s;
      int n = p.length - 1;

      if (n < 2)
         throw new IllegalArgumentException("At least 2 players!");

      this.players = p;
      this.tree = new int[n];

      //can be done with 2*logarith2(n-1)
      for (s = 1; s * 2 <= n - 1; s += s) ;
      this.lowExt = 2 * (n - s);
      this.offset = 2 * s - 1;

      for (i = 2; i <= lowExt; i += 2) {
         play((i + offset) / 2, i - 1, i);
      }


      if (n % 2 == 1) {
         play(n / 2, tree[n - 1], offset + 1);
         i = lowExt + 3;
      } else {
         i = lowExt + 2;
      }

      for (; i <= n; i += 2) {
         play((i - lowExt + n - 1) / 2, i - 1, i);
      }
   }

   public int getWinner() {
      if (tree == null)
         return 0;
      else
         return tree[1];
   }

   public void play(int p, int l, int r) {
      tree[p] = players[l] < players[r] ? l : r;
      while (p % 2 == 1) {
         tree[p / 2] = players[p - 1] < players[p] ? p - 1 : p;
         p /= 2;
      }
   }

   public void display() {
      System.out.println("offset: " + offset + " lowExt: " + lowExt);
      /*for (int c=1; c<tree.length-1; c++){
         System.out.println(tree[c]);
      }*/
   }

   public static void main(String[] args) {
      WinnerTree wt = new WinnerTree();

      int[] num = {0, 7, 8, 4, 1, 3, 9, 7};
      wt.initialize(num);

      //wt.display();
   }
}
