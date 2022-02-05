import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.*;

/* 
░ ▒ ▓░ ▒ ▓░ ▒ ▓░ ▒ ▓░ ▒ ▓░ ▒ ▓░ ▒ ▓░ ▒ ▓░ ▒ ▓░ ▒ ▓░ ▒ ▓░ ▒ ▓░ ▒ ▓░ ▒ ▓░ ▒ ▓░ ▒ ▓
Write a program based on a Binary Search Tree which reads the first two hundred
words from the text and allows the user to select to print these words in either
alphabetic or reverse alphabetic order. The time complexity of the printing
should be O(N) where N is the number of different words in the text.
░ ▒ ▓░ ▒ ▓░ ▒ ▓░ ▒ ▓░ ▒ ▓░ ▒ ▓░ ▒ ▓░ ▒ ▓░ ▒ ▓░ ▒ ▓░ ▒ ▓░ ▒ ▓░ ▒ ▓░ ▒ ▓░ ▒ ▓░ ▒ ▓
*/

/***************************************************************
 * Binary Search Tree is a binary tree:
 * 1. The left subtree: only nodes with keys lesser than root
 * 2. The right subtree: only nodes with keys greater than root
 * 3. Teh left and right subtree each must also be a BST
 ***************************************************************/

/***************************************************************
 * Lexicographic ordering: 
 ***************************************************************/

public class Assignment7<Key extends Comparable<Key>, Value> {
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

    public Assignment7() {  // an empty ST
    }

    public boolean isEmpty() { //Returns true if this symbol table is empty.
        return size() == 0;
    }

    public int size() {  //Returns the number of key-value pairs in this symbol table.
        return size(root);
    }

    private int size(Node x) { // return number of key-value pairs in Assignment7 rooted at x
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

    public void put(Key key, Value val) {  //Inserts the specified key-value pair into the symbol table
        if (key == null) throw new IllegalArgumentException("calls put() with a null key");
        if (val == null) {
            delete(key);
            return;
        }
        root = put(root, key, val);
    }

    private Node put(Node x, Key key, Value val) {
        if (x == null) return new Node(key, val, 1);
        int cmp = key.compareTo(x.key);
        if      (cmp < 0) x.left  = put(x.left,  key, val);   // 
        else if (cmp > 0) x.right = put(x.right, key, val);
        else              x.val   = val;
        x.size = 1 + size(x.left) + size(x.right);
        return x;
    }

    public void deleteMin() {  //Removes the smallest key and associated value from the symbol table.
        if (isEmpty()) throw new NoSuchElementException("Symbol table underflow");
        root = deleteMin(root);
    }

    private Node deleteMin(Node x) {
        if (x.left == null) return x.right;
        x.left = deleteMin(x.left);
        x.size = size(x.left) + size(x.right) + 1;
        return x;
    }

    /**
     * Removes the largest key and associated value from the symbol table.
     *
     * @throws NoSuchElementException if the symbol table is empty
     */
    public void deleteMax() {
        if (isEmpty()) throw new NoSuchElementException("Symbol table underflow");
        root = deleteMax(root);
        assert check();
    }

    private Node deleteMax(Node x) {
        if (x.right == null) return x.left;
        x.right = deleteMax(x.right);
        x.size = size(x.left) + size(x.right) + 1;
        return x;
    }

    /**
     * Removes the specified key and its associated value from this symbol table     
     * (if the key is in this symbol table).    
     */
    public void delete(Key key) {
        if (key == null) throw new IllegalArgumentException("calls delete() with a null key");
        root = delete(root, key);
        assert check();
    }

    private Node delete(Node x, Key key) {
        if (x == null) return null;

        int cmp = key.compareTo(x.key);
        if      (cmp < 0) x.left  = delete(x.left,  key);
        else if (cmp > 0) x.right = delete(x.right, key);
        else { 
            if (x.right == null) return x.left;
            if (x.left  == null) return x.right;
            Node t = x;
            x = min(t.right);
            x.right = deleteMin(t.right);
            x.left = t.left;
        } 
        x.size = size(x.left) + size(x.right) + 1;
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
     *
     * @param  lo minimum endpoint
     * @param  hi maximum endpoint
     * @return all keys in the symbol table between {@code lo} 
     *         (inclusive) and {@code hi} (inclusive)
     * @throws IllegalArgumentException if either {@code lo} or {@code hi}
     *         is {@code null}
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

    public Iterable<Key> reverse(Key lo, Key hi) {
        if (lo == null) throw new IllegalArgumentException("first argument to keys() is null");
        if (hi == null) throw new IllegalArgumentException("second argument to keys() is null");

        Queue<Key> queue = new Queue<Key>();
        reverse(root, queue, lo, hi);
        return queue;
    } 
    public Iterable<Key> reverse() {
        if (isEmpty()) return new Queue<Key>();
        return reverse(min(), max());
    }

    private void reverse(Node x, Queue<Key> queue, Key lo, Key hi) { 
        if (x == null) return; 
        int cmplo = lo.compareTo(x.key); 
        int cmphi = hi.compareTo(x.key); 
        if (cmplo < 0) reverse(x.right, queue, lo, hi); 
        if (cmplo <= 0 && cmphi >= 0) queue.enqueue(x.key); 
        if (cmphi > 0) reverse(x.left, queue, lo, hi); 
    }
    
    public static void main(String[] args) throws IOException {
        File file = new File("/Users/farzaneh/Desktop/Lab3/Filtered.txt");
        Scanner in = new Scanner(file);
        Scanner scanner = new Scanner(System.in);
        Assignment7<String, Integer> st = new Assignment7<String, Integer>();
        //Stack<String> stack = new Stack<>();
        int maxlen = 200;
        int words = 0;
        while ((in.hasNext()) && ((words < maxlen))) {
            String word = in.next();
            word.toLowerCase().split("\\s+");
            words++;
            //word.toString();
            //System.out.println(word);
            if (word.isEmpty()) continue;            
           
            if (st.contains(word)) {
                st.put(word, st.get(word) + 1);
            }
            else {
                st.put(word, 1);
            }
            //String[] keys = word.toLowerCase().split("\\s+");
            //ArrayList<String> list = new ArrayList<>(Arrays.asList(keys));
         
            
        //    System.out.println(list);
        }

        System.out.println("\n░░░░░░░Assignment 7░░░░░░░\n");

        boolean valid = true;
        while(valid) {
            System.out.println("1. in alphabetic order 2. in reverse alphabetic order 3. Exit");
            switch (scanner.nextInt()) {
                case 1: {
                    for (String word : st.keys()) {
                        System.out.println(word);
                    }
                    break;
                }
                case 2: {
                    for (String word : st.reverse()) {
                        System.out.println(word);
                    }
                    /*for (String word : st.keys()) {
                        stack.push(word);
                    }
                    while (stack.size()>0) {                     
                        System.out.print(stack.pop()+"\n");  
                     }*/
                    break;
                }
                case 3: {
                    valid = false;
                }
            }
        }
    scanner.close();
    in.close();
    }
}