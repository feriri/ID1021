public class DepthFirstDirectedPaths {
    private boolean[] marked;  // has dfs() been called for this vertex
    private int[] edgeTo;      // edgeTo[v] = last edge on path from s to v
    private final int s;       // source vertex

    public DepthFirstDirectedPaths(DiGraph G, int s) {
        marked = new boolean[G.V()];         
        edgeTo = new int[G.V()];
        this.s = s;
        validateVertex(s);
        dfs(G, s);
    }

    private void dfs(DiGraph G, int v) {    // dfs from v
        marked[v] = true;                   // visit v
        for (int w : G.adj(v)) {            // for each neighbor w of v
            if (!marked[w]) {
                edgeTo[w] = v;
                dfs(G, w);                 // dfs(w) recursive
            }
        }
    }

    /*private void bfs(DiGraph G, int s) {    
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
    }*/
    
    public boolean hasPathTo(int v) {     // is there a path between s and v
        validateVertex(v);
        return marked[v];
    }

    public Iterable<Integer> pathTo(int v) {   // path from s to v
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