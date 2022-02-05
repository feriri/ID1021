import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
/*
░ ▒ ▓░ ▒ ▓░ ▒ ▓░ ▒ ▓░ ▒ ▓░ ▒ ▓░ ▒ ▓░ ▒ ▓░ ▒ ▓░ ▒ ▓░ ▒ ▓░ ▒ ▓░ ▒ ▓░ ▒ ▓░ ▒ ▓░ ▒ ▓░ ▒ ▓
Write a program that shows how evenly the built-in hashcode() function for strings in
Java distributes the hashcodes for the words found in the text. (Hint it may be hard
to use the hashcodes directly without manipulating them...)
░ ▒ ▓░ ▒ ▓░ ▒ ▓░ ▒ ▓░ ▒ ▓░ ▒ ▓░ ▒ ▓░ ▒ ▓░ ▒ ▓░ ▒ ▓░ ▒ ▓░ ▒ ▓░ ▒ ▓░ ▒ ▓░ ▒ ▓░ ▒ ▓░ ▒ ▓
*/


/**
 * hascode() for Strings spread equally hashcodes for the words in the text
 * a hashcode is an integer value
 */

/*
 * if two objects are equal according to the equals() method
 * hashcode() returns the same hash code value
 * but if it is called on two unequal objects
 * it will not necessarily return different values
 * 
 * hashcode for string:
 * s[0]*31^(n-1) + s[1]*31^(n-2) + ... + s[n-1] (the max length a string can have is 2^31 - 1)
 * 
 * s[0] is the first element of the string n is the length of the string
 * 
 * hash value of the empty string = zero
 */ 

public class Assignment5 {
    
    private final static int TABLE_SIZE = 10000;    // kan ändras
    public static class SeparateChainingHashST<Key, Value> {

      //  private int n;                                // number of key-value pairs (words)
        private int m;                                  // hash table size (array size)
        private SequentialSearchST<Key, Value>[] arr;   // array of linked-list symbol tables

        public SeparateChainingHashST(int m) {   // an empty ST
            this.m = m;                          // nr of chains
            arr = (SequentialSearchST<Key, Value>[]) new SequentialSearchST[m];
            for (int i = 0; i < m; i++)
                arr[i] = new SequentialSearchST();
        } 

        // Hash code: an int between -2^31 and 2^31-1
        // Hash function: an int between 0 and m-1 (m = array.length)
        private int hash(Key key) {  //hash func for keys, returns value between 0 and m-1
            return (key.hashCode() & 0x7fffffff) % m;  // make it positive first
        }

        public Value get(Key key) {  // returns the value corresponding to the key if the key exists in HT
            if (key == null) throw new IllegalArgumentException("null");
            return arr[hash(key)].get(key);
        } 

        public boolean isEmpty() {  return size() == 0; } // true if ST is empty
    

        public boolean contains(Key key) {   // true if ST exists
            if (key == null) throw new IllegalArgumentException("null");
            return get(key) != null;
        } 

        public void put(Key key, Value val) {
            if (key == null) throw new IllegalArgumentException("null");
            if (val == null) {
                delete(key);  
                return;        
            }
            arr[hash(key)].put(key, val);  //inserts the specified key-value pair in the ST
        } 

        public void delete(Key key) {  // Removes the specified key and its associated value from ST    
            if (key == null) throw new IllegalArgumentException("argument to delete() is null");
            arr[hash(key)].delete(key);
        } 
        
        public static void main(String[] args) throws FileNotFoundException {
        
            SeparateChainingHashST<String, Integer> st = new SeparateChainingHashST(TABLE_SIZE);

            int count = 0;
            int minlen = 1;

            File file = new File("/Users/farzaneh/Desktop/Lab3/Filtered.txt");
            Scanner in = new Scanner(file);
   
            while (in.hasNext()) {
                String word = in.next();
                if (word.length() < minlen) continue;
                word = word.toLowerCase();
                count++;
                if (st.contains(word)) {
                    st.put(word, st.get(word) + 1);
                }
                else {
                    st.put(word, 1);
                }
            }
            System.out.println("\n░░░░░░░Assignment 5░░░░░░░\n");
            for (int i = 0; i < st.arr.length; i++) {  // traverse through the hashtable
                if (st.arr[i].size()>0) {
                    System.out.println(i + " Collision " + st.arr[i].size());
                } else {
                    System.out.println(i + ": " + st.arr[i].size());
                }
                i++;
            }
            
            /*for(SequentialSearchST key : st.s) {
            }*/
            
            in.close();
            
            //System.out.println("words " + count);
        }
    }
}