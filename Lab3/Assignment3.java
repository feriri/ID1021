import java.io.File;
import java.io.FileNotFoundException;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.NoSuchElementException;

/* 
░ ▒ ▓░ ▒ ▓░ ▒ ▓░ ▒ ▓░ ▒ ▓░ ▒ ▓░ ▒ ▓░ ▒ ▓░ ▒ ▓░ ▒ ▓░ ▒ ▓░ ▒ ▓░ ▒ ▓░ ▒ ▓░ ▒ ▓░ ▒ ▓
Use the first thousand words from the text to measure the running time of the
Binary Search Tree algorithm (Algorithm 3.3 in the book (obs not chapter 3.3))
(you need only implement the basic operations to put and get keys to/from the ST)
Use the FrequencyCounter from page 372 as test program (you may need to change
how you read the words if you do not use the libraries from Sedgewick&Wayne).
Show tables or graphs of your measurements.
░ ▒ ▓░ ▒ ▓░ ▒ ▓░ ▒ ▓░ ▒ ▓░ ▒ ▓░ ▒ ▓░ ▒ ▓░ ▒ ▓░ ▒ ▓░ ▒ ▓░ ▒ ▓░ ▒ ▓░ ▒ ▓░ ▒ ▓░ ▒ ▓
*/

/***************************************************************
 * Binary Search Tree is a binary tree:
 * 1. The left subtree: only nodes with keys lesser than root
 * 2. The right subtree: only nodes with keys greater than root
 * 3. Teh left and right subtree each must also be a BST
 ***************************************************************/

public class Assignment3<Key extends Comparable<Key>, Value> {
    private Node root;             // root of BST

    private class Node {
        private Key key;           // sorted by key
        private Value val;         // associated data
        private Node left, right;  // left and right subtrees
        private int size;          // number of nodes in subtree

        public Node(Key key, Value val, int size) {
            this.key = key;
            this.val = val;
            this.size = size;
        }
    }

    public Assignment3() {  // an empty ST
    }

    public boolean isEmpty() { //Returns true if this symbol table is empty.
        return size() == 0;
    }

    public int size() {  //Returns the number of key-value pairs in this symbol table.
        return size(root);
    }

    private int size(Node x) { // return number of key-value pairs in Assignment3 rooted at x
        if (x == null) return 0;
        else return x.size;
    }

    public boolean contains(Key key) { //Does this symbol table contain the given key? return true
        if (key == null) throw new IllegalArgumentException("argument to contains() is null");
        return get(key) != null;
    }

    public Value get(Key key) { //@return the value associated with the given key if the key is in the symbol table
        return get(root, key);
    }

    private Value get(Node x, Key key) {
        if (key == null) throw new IllegalArgumentException("calls get() with a null key");
        if (x == null) return null;
        int cmp = key.compareTo(x.key);
        if      (cmp < 0) return get(x.left, key);
        else if (cmp > 0) return get(x.right, key);
        else              return x.val;
    }

    public void put(Key key, Value val) { //Inserts the specified key-value pair into the symbol table
        if (key == null) throw new IllegalArgumentException("calls put() with a null key");
        root = put(root, key, val);
    }

    private Node put(Node x, Key key, Value val) {
        if (x == null) return new Node(key, val, 1);
        int cmp = key.compareTo(x.key);
        if      (cmp < 0) x.left  = put(x.left,  key, val);
        else if (cmp > 0) x.right = put(x.right, key, val);
        else              x.val   = val;
        x.size = 1 + size(x.left) + size(x.right);
        return x;
    }

    /**
     * Returns the smallest key in the symbol table.
     */
    public Key min() {
        if (isEmpty()) throw new NoSuchElementException("calls min() with empty symbol table");
        return min(root).key;
    } 

    private Node min(Node x) { 
        if (x.left == null) return x; 
        else                return min(x.left); 
    } 

    /**
     * Returns the largest key in the symbol table.
     */
    public Key max() {
        if (isEmpty()) throw new NoSuchElementException("calls max() with empty symbol table");
        return max(root).key;
    } 

    private Node max(Node x) {
        if (x.right == null) return x; 
        else                 return max(x.right); 
    } 

    /**
     * Returns all keys in the symbol table as an {@code Iterable}.
     * To iterate over all of the keys in the symbol table named {@code st},
     * use the foreach notation: {@code for (Key key : st.keys())}.
     *
     * @return all keys in the symbol table
     */
    public Iterable<Key> keys() {
        if (isEmpty()) return new Queue<Key>();
        return keys(min(), max());
    }

    /**
     * Returns all keys in the symbol table in the given range,
     * as an {@code Iterable}.
     */
    public Iterable<Key> keys(Key lo, Key hi) {
        if (lo == null) throw new IllegalArgumentException("first argument to keys() is null");
        if (hi == null) throw new IllegalArgumentException("second argument to keys() is null");

        Queue<Key> queue = new Queue<Key>();
        keys(root, queue, lo, hi);
        return queue;
    } 

    private void keys(Node x, Queue<Key> queue, Key lo, Key hi) { 
        if (x == null) return; 
        int cmplo = lo.compareTo(x.key); 
        int cmphi = hi.compareTo(x.key); 
        if (cmplo < 0) keys(x.left, queue, lo, hi); 
        if (cmplo <= 0 && cmphi >= 0) queue.enqueue(x.key); 
        if (cmphi > 0) keys(x.right, queue, lo, hi); 
    } 

    public static void main(String[] args) throws FileNotFoundException {
        int distinct = 0, words = 0;
        int maxWords = 1000;
        Assignment3<String, Integer> st = new Assignment3<String, Integer>();
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

        System.out.println("\n░░░░░░░Assignment 3░░░░░░░\n");
        System.out.println("Most repeated word: " + max + ", " + st.get(max) + " times");
        System.out.println("Distinct = " + distinct);
        System.out.println("Words = " + words);
        System.out.println("Running time: " + (end-start)/10000000 + " ms\n");
    }
}