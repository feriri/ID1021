/**
 * https://algs4.cs.princeton.edu/code/edu/princeton/cs/algs4/SequentialSearchST.java.html
 * 
 *  @author Robert Sedgewick
 *  @author Kevin Wayne
 */

public class SequentialSearchST<Key, Value> {
    private int n;           // number of key-value pairs
    private Node first;      // the linked list of key-value pairs

    // a helper linked list data type
    private class Node {
        private Key key;
        private Value val;
        private Node next;

        public Node(Key key, Value val, Node next)  {
            this.key  = key;
            this.val  = val;
            this.next = next;
        }
    }

    public SequentialSearchST() {  //an empty symbol table.
    }

    public int size() { // Returns the number of key-value pairs in this symbol table.
        return n;
    }

    /*
    public boolean isEmpty() { // Returns true if this symbol table is empty.
        return size() == 0;
    }*/

    public boolean contains(Key key) { // Returns true if this symbol table contains the specified key.
        if (key == null) throw new IllegalArgumentException("null");
        return get(key) != null;
    }

    public Value get(Key key) {  // Returns the value associated with the given key in this symbol table.
        if (key == null) throw new IllegalArgumentException("null"); 
        for (Node x = first; x != null; x = x.next) {
            if (key.equals(x.key)) return x.val;
        }
        return null;
    }

    public void put(Key key, Value val) {
        if (key == null) throw new IllegalArgumentException("null"); 

        for (Node x = first; x != null; x = x.next) {
            if (key.equals(x.key)) {
                x.val = val;
                return;
            }
        }
        first = new Node(key, val, first);
        n++;
    }

    public Iterable<Key> keys()  {
        Queue<Key> queue = new Queue<Key>();
        for (Node x = first; x != null; x = x.next)
            queue.enqueue(x.key);
        return queue;                               // use the foreach notation: {@code for (Key key : st.keys())
    }
}