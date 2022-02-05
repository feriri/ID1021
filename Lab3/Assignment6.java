import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
/*
░ ▒ ▓░ ▒ ▓░ ▒ ▓░ ▒ ▓░ ▒ ▓░ ▒ ▓░ ▒ ▓░ ▒ ▓░ ▒ ▓░ ▒ ▓░ ▒ ▓░ ▒ ▓░ ▒ ▓░ ▒ ▓░ ▒ ▓░ ▒ ▓░ ▒ ▓
Write a program which allows the user to repeatedly ask questions (without re-reading
the input or re-building the data structures used) how many times a word occurs in the 
input. Base the implementation on a hash table which uses separate chaining.
░ ▒ ▓░ ▒ ▓░ ▒ ▓░ ▒ ▓░ ▒ ▓░ ▒ ▓░ ▒ ▓░ ▒ ▓░ ▒ ▓░ ▒ ▓░ ▒ ▓░ ▒ ▓░ ▒ ▓░ ▒ ▓░ ▒ ▓░ ▒ ▓░ ▒ ▓
*/

public class Assignment6 {
    
    private final static int TABLE_SIZE = 16384;
    public static class SeparateChainingHashST<Key, Value> {

      //  private int n;                                // number of key-value pairs (words)
        private int m;                                 //  hash table size (array size)
        private SequentialSearchST<Key, Value>[] arr;   //   array of linked-list symbol tables

        public SeparateChainingHashST(int m) {   // an empty ST
            this.m = m;                          // nr of chains
            arr = (SequentialSearchST<Key, Value>[]) new SequentialSearchST[m];
            for (int i = 0; i < m; i++)
                arr[i] = new SequentialSearchST();
        } 

        private int hash(Key key) {  //hash func for keys, returns value between 0 and m-1
            return (key.hashCode() & 0x7fffffff) % m;  // make it positive by ending off the sign bit and then mark with M to get 
        }

        public int size() { // Returns the number of key-value pairs in this symbol table.
            return n;
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

        // return keys in symbol table as an Iterable
        public Iterable<Key> keys() {
            Queue<Key> queue = new Queue<Key>();
            for (int i = 0; i < m; i++) {
                for (Key key : arr[i].keys())
                    queue.enqueue(key);
            }
            return queue;
        }

        public static void main(String[] args) throws IOException {
            File file = new File("/Users/farzaneh/Desktop/Lab3/Filtered.txt");
            Scanner in = new Scanner(file);  
            SeparateChainingHashST<String, Integer> st = new SeparateChainingHashST(TABLE_SIZE);

            while (in.hasNext()) {
                String word = in.next();
                word.toLowerCase().split("\\s+");
                
                if (st.contains(word)) {
                    st.put(word, st.get(word) + 1);
                } else {
                    st.put(word, 1);
                }
            }

            boolean valid = true;
            Scanner sc = new Scanner(System.in);
            String input;

            System.out.println("\n░░░░░░░Assignment 6░░░░░░░\n");
            while(valid){
                System.out.print("Exit = 0, Word = ");
                input = sc.nextLine();
    
                if(st.contains(input)){
                    if (st.get(input) == 1) System.out.println("\n" + input +": " + st.get(input) + " time\n");
                    else System.out.println("\n" + input +": " + st.get(input) + " times\n");
                } else if (input.equals("0")) {
                    valid = false;
                } else System.out.println("\nThe word does not exist");
            }
            sc.close();
            in.close();
        }
    }
}
