// https://algs4.cs.princeton.edu/code/edu/princeton/cs/algs4/BreadthFirstPaths.java.html

public class BreadthFirstPaths {
    private boolean[] marked;  // has bfs() has been called for this vertex?
    private int[] edgeTo;      // saves knhow paths to each vertex (contatins all the vertices connected to s)
    private final int s;       // source vertex

    public BreadthFirstPaths(Graph G, int s) {  // Computes a path between s and every other vertex in graph
        this.s = s;
        marked = new boolean[G.V()];
        edgeTo = new int[G.V()];
        validateVertex(s);
        bfs(G, s);
    }

    private void bfs(Graph G, int s) {    
        Queue<Integer> q = new Queue<Integer>();
        marked[s] = true;       // mark the current node as visited
        q.enqueue(s);           // and add it to the queue
        while (!q.isEmpty()) {
            int v = q.dequeue();       // pop the front node from the queue
            for (int w : G.adj(v)) {   // for every neighboring node of v
                if (!marked[w]) {      // if a vertex has not been marked
                    edgeTo[w] = v;     // edgeTo[w] = v means that v-w was the edge used to access w for the first time
                    marked[w] = true;  // mark it as visited
                    q.enqueue(w);      // and push it into the queue
                }
            }
        }
    }
    /**
     * node 0 in connected to node 8, node 3 and node 1
     * node 0 is enqueued 
     * enqueue the unvisited neighbors nodes: 8, 3, 1
     * visit the first node in the queue, node 1

     * 
     */

    public boolean hasPathTo(int v) {   // is there a path between s and v?
        validateVertex(v);
        return marked[v];
    }

    public Iterable<Integer> pathTo(int v) {    // path from s to v
        validateVertex(v);
        if (!hasPathTo(v)) return null;
        Stack<Integer> path = new Stack<Integer>();
        for (int x = v; x != s; x = edgeTo[x])
            path.push(x);
        path.push(s);
        return path;
    }

    private void validateVertex(int v) {
        int V = marked.length;
        if (v < 0 || v >= V)
            throw new IllegalArgumentException("vertex " + v + " is not between 0 and " + (V-1));
    }
}