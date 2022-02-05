/**
 * https://algs4.cs.princeton.edu/code/edu/princeton/cs/algs4/DepthFirstPaths.java.html
 *  
 * uses less memory than BFS
 */

public class DepthFirstPaths {
    private boolean[] marked;    // has dfs() been called for this vertex?
    private int[] edgeTo;        // saves known paths to each vertex (contains all the vertices connected to s)
    private final int s;         // source vertex

    public DepthFirstPaths(Graph G, int s) {  // Computes a path between s and every other vertex in graph
        this.s = s;          // source vertex
        edgeTo = new int[G.V()];
        marked = new boolean[G.V()];   
        validateVertex(s);    // 0 <= s <= V-1
        dfs(G, s);
    }

    private void dfs(Graph G, int v) {   // dfs from v
        marked[v] = true;                // visit v
        for (int w : G.adj(v)) {         // for each neighbor w of v
            if (!marked[w]) {
                edgeTo[w] = v;          
                dfs(G, w);               // dfs(w);     recursive
            }
        }
    }

    public boolean hasPathTo(int v) {  // is there a path between s and v?
        validateVertex(v);       // 0 <= v <= V-1
        return marked[v];
    }

    private void validateVertex(int v) {
        int V = marked.length;
        if (v < 0 || v >= V)
            throw new IllegalArgumentException("vertex " + v + " is not between 0 and " + (V-1));
    }

    public Iterable<Integer> pathTo(int v) {   // path from s to v
        validateVertex(v);     // 0 <= v <= V-1
        if (!hasPathTo(v)) return null;   // if no such path == null
        Stack<Integer> stack = new Stack<Integer>();
        for (int x = v; x != s; x = edgeTo[x]) stack.push(x);
        stack.push(s);
        return stack;
    }
}