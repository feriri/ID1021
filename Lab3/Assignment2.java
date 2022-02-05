import java.io.File;
import java.io.FileNotFoundException;
import java.util.NoSuchElementException;
import java.util.Scanner;
/* 
Princeton:

https://algs4.cs.princeton.edu/code/edu/princeton/cs/algs4/BinarySearchST.java.html
https://algs4.cs.princeton.edu/code/edu/princeton/cs/algs4/FrequencyCounter.java.html

░ ▒ ▓░ ▒ ▓░ ▒ ▓░ ▒ ▓░ ▒ ▓░ ▒ ▓░ ▒ ▓░ ▒ ▓░ ▒ ▓░ ▒ ▓░ ▒ ▓░ ▒ ▓░ ▒ ▓░ ▒ ▓░ ▒ ▓░ ▒ ▓
Use the first thousand words from the text to measure the running time of the
ordered array ST (also known as binary search symbol table, see algorithm 3.2 in
the book (obs not chapter 3.2)). Use the FrequencyCounter from page 372 as test
program (you may need to change how you read the words if you do not use the
libraries from Sedgewick&Wayne). Show tables or graphs of your measurements.
░ ▒ ▓░ ▒ ▓░ ▒ ▓░ ▒ ▓░ ▒ ▓░ ▒ ▓░ ▒ ▓░ ▒ ▓░ ▒ ▓░ ▒ ▓░ ▒ ▓░ ▒ ▓░ ▒ ▓░ ▒ ▓░ ▒ ▓░ ▒ ▓
*/

public class Assignment2<Key extends Comparable<Key>, Value> {
    private static final int INIT_CAPACITY = 2;
    private Key[] keys;
    private Value[] vals;
    private int n = 0;

    public Assignment2() {    // an empty ST
        this(INIT_CAPACITY);
    }

    public Assignment2(int capacity) { // an empty ST with the max capacity
        keys = (Key[]) new Comparable[capacity]; 
        vals = (Value[]) new Object[capacity]; 
    }   

    private void resize(int capacity) { // resize the underlying arrays
        assert capacity >= n;
        Key[]   tempk = (Key[])   new Comparable[capacity];
        Value[] tempv = (Value[]) new Object[capacity];
        for (int i = 0; i < n; i++) {
            tempk[i] = keys[i];
            tempv[i] = vals[i];
        }
        vals = tempv;
        keys = tempk;
    }

    public int size() {  return n; } //Returns the number of key-value pairs in this symbol table.

    public boolean isEmpty() {  return size() == 0; } //Returns true if this symbol table is empty.
 
    public boolean contains(Key key) {  // Does this symbol table contain the given key? return true
        if (key == null) throw new IllegalArgumentException("argument to contains() is null");
        return get(key) != null;
    }

    public Value get(Key key) { // Returns the value associated with the given key in this symbol table.
        if (key == null) throw new IllegalArgumentException("argument to get() is null"); 
        if (isEmpty()) return null;
        int i = rank(key); 
        if (i < n && keys[i].compareTo(key) == 0) return vals[i];
        else return null;
    }

    /**
      ■ If key is in the table, rank() returns its index in the table, which is the same as the number of keys in the table that are smaller than key.
      ■ If key is not in the table, rank() also returns the number of keys in the table that are smaller than key.
     */
    public int rank(Key key) {  // Returns the number of keys in this symbol table strictly less than {@code key}.
        if (key == null) throw new IllegalArgumentException("argument to rank() is null"); 

        int lo = 0, hi = n-1; 
        while (lo <= hi) { 
            int mid = lo + (hi - lo) / 2; 
            int cmp = key.compareTo(keys[mid]);  // comapre the search key against the key in the middle of the subarray
            if      (cmp < 0) hi = mid - 1;      // if the search key is less than the middle, search in the left half
            else if (cmp > 0) lo = mid + 1;      // if greater, search in the right half
            else return mid;                     // otherwise, the key in the middle = search key
        } 
        return lo;      // if hi < lo
    } 

    public void put(Key key, Value val)  { //Inserts the specified key-value pair into the symbol tabl
        if (key == null) throw new IllegalArgumentException("first argument to put() is null"); 

        if (val == null) {
            delete(key);
            return;
        }

        int i = rank(key);

        // key is already in table
        if (i < n && keys[i].compareTo(key) == 0) {
            vals[i] = val;
            return;
        }

        // insert new key-value pair
        if (n == keys.length) resize(2*keys.length);

        for (int j = n; j > i; j--)  {
            keys[j] = keys[j-1];
            vals[j] = vals[j-1];
        }
        keys[i] = key;
        vals[i] = val;
        n++;
    } 

    public void delete(Key key) {   // Removes the specified key and associated value from this symbol table
        if (key == null) throw new IllegalArgumentException("argument to delete() is null"); 
        if (isEmpty()) return;

        // compute rank
        int i = rank(key);

        // key not in table
        if (i == n || keys[i].compareTo(key) != 0) {
            return;
        }

        for (int j = i; j < n-1; j++)  {
            keys[j] = keys[j+1];
            vals[j] = vals[j+1];
        }

        n--;
        keys[n] = null;  // to avoid loitering
        vals[n] = null;

        // resize if 1/4 full
        if (n > 0 && n == keys.length/4) resize(keys.length/2);
    } 

    public void deleteMin() {  //Removes the smallest key and associated value from this symbol table.
        if (isEmpty()) throw new NoSuchElementException("Symbol table underflow error");
        delete(min());
    }

    public void deleteMax() {  //Removes the largest key and associated value from this symbol table.
        if (isEmpty()) throw new NoSuchElementException("Symbol table underflow error");
        delete(max());
    }

   /***************************************************************************
    *  Ordered symbol table methods.
    ***************************************************************************/
    public Key min() {  //Returns the smallest key in this symbol table.
        if (isEmpty()) throw new NoSuchElementException("called min() with empty symbol table");
        return keys[0]; 
    }

    public Key max() {  //Returns the largest key in this symbol table.
        if (isEmpty()) throw new NoSuchElementException("called max() with empty symbol table");
        return keys[n-1];
    }
    
    public Iterable<Key> keys() {  //To iterate over all of the keys in the symbol table named {@code st},
        return keys(min(), max());  //use the foreach notation: {@code for (Key key : st.keys())}.
    }

    public Iterable<Key> keys(Key lo, Key hi) { // all keys in this symbol table between lo and hi
        if (lo == null) throw new IllegalArgumentException("first argument to keys() is null"); 
        if (hi == null) throw new IllegalArgumentException("second argument to keys() is null"); 

        Queue<Key> queue = new Queue<Key>(); 
        if (lo.compareTo(hi) > 0) return queue;
        for (int i = rank(lo); i < rank(hi); i++) 
            queue.enqueue(keys[i]);
        if (contains(hi)) queue.enqueue(keys[rank(hi)]);
        return queue; 
    }
    public static void main(String[] args) throws FileNotFoundException {
        int distinct = 0, words = 0;
        int maxWords = 1000;
        Assignment2<String, Integer> st = new Assignment2<String, Integer>();
        File file = new File("/Users/farzaneh/Desktop/Lab3/Filtered.txt");
        Scanner in = new Scanner(file);

        long start = System.nanoTime();
       
        while ((in.hasNext()) && (words < maxWords)) {
            String key = in.next();
            if (key.isEmpty()) continue;
            words++;
            if (st.contains(key)) {
                st.put(key, st.get(key) + 1);
            }
            else {
                st.put(key, 1);
                distinct++;
            }
        }
        in.close();

        // find a key with the highest frequency count
        String max = "";
        st.put(max, 0);
        for (String word : st.keys()) {
            if (st.get(word) > st.get(max))
                max = word;
        }
        long end = System.nanoTime();

        System.out.println("\n░░░░░░░Assignment 2░░░░░░░\n");
        System.out.println("Most repeated word: " + max + ", " + st.get(max) + " times");
        System.out.println("Distinct = " + distinct);
        System.out.println("Words = " + words);
        System.out.println("Running time: " + (end-start)/10000000 + " ms\n");
    }
}