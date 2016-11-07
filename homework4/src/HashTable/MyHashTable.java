package HashTable;

import java.math.BigInteger;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.concurrent.ThreadLocalRandom;


public class MyHashTable<K, V> implements HashTableInterface<K, V> {
   private final static int DEF_SIZE = 128;
   public static final float DEF_LOAD = 0.75f;
   private int m;
   Entry<K, V>[] hashTable;
   private int n; // amount of values in the table
   private long a, b, p;
   private float loadFactor;
   private int threshold;


   @SuppressWarnings("unchecked")
   public MyHashTable(int initialSize, float loadFactor) {
      this.m = initialSize;
      this.n = 0;
      this.loadFactor = loadFactor;
      this.threshold = (int) (initialSize * loadFactor);
      hashTable = (Entry<K, V>[]) new Entry[m];
      a = new BigInteger(Integer.toString(ThreadLocalRandom.current().nextInt(1, 1001))).nextProbablePrime().longValue();
      b = new BigInteger(Integer.toString(ThreadLocalRandom.current().nextInt(0, 1001))).nextProbablePrime().longValue();
      p = new BigInteger(Integer.toString(m)).nextProbablePrime().longValue();
   }

   public MyHashTable(int initialSize) {
      this(initialSize, DEF_LOAD);
   }

   public MyHashTable() {
      this(DEF_SIZE, DEF_LOAD);
   }


   public V getValue(K key) {
      int h = hash(key);
      while (hashTable[h] != null && !hashTable[h].key.equals(key)) {
         h = (h + 1) % m;
      }
      if (hashTable[h] == null)
         throw new NoSuchElementException();
      else
         return hashTable[h].value;
   }

   public boolean contains(K key) {
      int h = hash(key);
      int initialHash = h;

      if (hashTable[h] == null) return false;
      while (!hashTable[h].key.equals(key)) {
         h = (h + 1) % m;
         if (hashTable[h] == null || h == initialHash)
            return false;
      }
      return true;
   }

   public V add(K key, V value) {
      if (n >= threshold) resize();
      int h = hash(key);
      int initialHash = -1;
      while (initialHash != h && hashTable[h] != null && !hashTable[h].key.equals(key)) {
         if (initialHash == -1) {
            initialHash = h;
         }
         h = (h + 1) % m;
      }
      if (hashTable[h] == null) {
         hashTable[h] = new Entry<K, V>(key, value);
         n++;
         return null;
      } else if (initialHash != h)
         if (hashTable[h] != null && hashTable[h].key.equals(key)) {
            V prev = hashTable[h].value;
            hashTable[h].value = value;
            n++;
            return prev;
         } else {
            hashTable[h] = new Entry<K, V>(key, value);
            n++;
         }
      return null;
   }

   public V remove(K key) {
      int h = hash(key);
      int initialHash = -1;

      if (hashTable[h] == null) {
         return null;
      }
      while (!hashTable[h].key.equals(key)) {
         h = (h + 1) % m;
         if (hashTable[h] == null || h == initialHash) {
            return null;
         }
      }

      V r = hashTable[h].value;
      hashTable[h] = null;
      while (hashTable[++h] != null) {
         Entry<K, V> e = hashTable[h];
         hashTable[h] = null;
         n--;
         this.add(e.key, e.value);
      }
      n--;
      return r;
   }

   public boolean isEmpty() {
      return n == 0;
   }

   public int getSize() {
      return n;
   }

   public Iterator<V> getValueIterator() {
      return new ValuesIterator();
   }

   public Iterator<K> getKeyIterator() {
      return new KeysIterator();
   }

   class KeysIterator implements Iterator<K> {
      int nextIndex = 0;
      int counter = 0;

      public KeysIterator() {
      }

      public K next() {
         while (this.hasNext()) {
            if (hashTable[nextIndex++] != null) {
               counter++;
               return hashTable[nextIndex - 1].key;
            }
         }
         throw new NoSuchElementException();
      }

      public boolean hasNext() {
         return counter < n;
      }
   }

   class ValuesIterator implements Iterator<V> {
      int nextIndex = 0;
      int counter = 0;

      public ValuesIterator() {
      }


      public V next() {
         while (this.hasNext()) {
            if (hashTable[nextIndex++] != null) {
               counter++;
               return hashTable[nextIndex - 1].value;
            }
         }
         throw new NoSuchElementException();
      }

      public boolean hasNext() {
         return counter < n;
      }
   }

   public String toString() {
      StringBuilder sb = new StringBuilder("{ ");
      for (Entry<K, V> e : hashTable) {
         if (e != null)
            sb.append(e.key).append(": ").append(e.value).append(", ");
      }
      return sb.append('}').toString();
   }

   private static class Entry<K, V> {
      K key;
      V value;

      Entry(K k, V v) {
         key = k;
         value = v;
      }
   }


   @SuppressWarnings("unchecked")
   private void resize() {
      Entry<K, V>[] oldHashTable = (Entry<K, V>[]) new Entry[m];
      System.arraycopy(this.hashTable, 0, oldHashTable, 0, m);
      n = 0;
      m *= 2;
      p = new BigInteger(Integer.toString(m)).nextProbablePrime().longValue();
      this.hashTable = (Entry<K, V>[]) new Entry[m];
      threshold = (int) (m * loadFactor);

      for (Entry<K, V> e : oldHashTable) {
         if (e != null) {
            this.add(e.key, e.value);
         }
      }
      oldHashTable = null;
   }

   private int hash(K key) {
      return (int) ((a * (key.hashCode() & 0x7FFFFFF) + b) % p) % m;
   }

   @SuppressWarnings("unchecked")
   public void clear() {
      hashTable = (Entry<K, V>[]) new Entry[m];
      n = 0;
   }

   public static void main(String[] args) {
      MyHashTable<Integer, String> hs = new MyHashTable<>();

      hs.add(18, "Cesar");
      hs.add(24, "Lolis");

      System.out.println(hs.contains(18));
      System.out.println(hs.getSize());
      System.out.println(hs.remove(18));
      System.out.println(hs.contains(18));
      System.out.println(hs.getSize());
   }


}