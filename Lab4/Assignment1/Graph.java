// https://algs4.cs.princeton.edu/code/edu/princeton/cs/algs4/Graph.java.html

import java.util.NoSuchElementException;
import java.io.File;

/*    
 *        int V = 4
 *        Bag[] adj     
 *   0 ⟏ -----> linked list 2 -> 7 -> 1
 *   1 ⟏ -----> linked list 4 -> 7 -> 0
 *   2 ⟏ -----> linked list 7 -> 0 -> 6 -> 5 -> 4 -> 3
 *   3 ⟏ -----> linked list 9 -> 6 -> 2        
 */

public class Graph {

    private final int V;   // nr of Vertices
    private int E;         // nr of Edges
    private Bag<Integer>[] adj;
    

    public Graph(int V) {      // an empty graph with V vertices and 0 edges
        if (V < 0) throw new IllegalArgumentException("Number of vertices must be non-negative");
        this.V = V;
        this.E = 0; 
        adj = (Bag<Integer>[]) new Bag[V];
        for (int v = 0; v < V; v++) {
            adj[v] = new Bag<Integer>();
        }
    }

    public int V() {        // returns vertices
        return V;
    }

    public int E() {        // returns edges
        return E;
    }

    private void validateVertex(int v) {
        if (v < 0 || v >= V)
            throw new IllegalArgumentException("vertex " + v + " is not between 0 and " + (V-1));
    }

    public void addEdge(int v, int w) {    // adds the undirected edge v-w to this graph
        validateVertex(v);                 // if it does not already exist
        validateVertex(w);
        E++;
        // if (!adjMatrix[v][w]) E++
        adj[v].add(w);
        adj[w].add(v);
    }

    public Iterable<Integer> adj(int v) { 
        validateVertex(v);
        return adj[v];
    }

   /* public String toString() {
        StringBuilder s = new StringBuilder();
        s.append(V + " vertices, " + E + " edges " + "\n");
        for (int v = 0; v < V; v++) {
            s.append(v + ": ");
            for (int w : adj[v]) {
                s.append(w + " ");
            }
            s.append("\n");
        }
        return s.toString();
    }*/
}