import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
/*
 *       ST<String,Integer> st       String[] keys                      Graph G
 *           ╭─────┬───╮            (inverted index)              (undirected graph)
 *           │ JFK │ 0 │                ┌───────                      int V = 4
 *           ╰─────┴───╯              0 │ JFK                Bag[] adj     
 *           ╭─────┬───╮              1 │ MCO                   0 ⟏ -----> linked list 2 -> 7 -> 1
 *           │ MCO │ 1 │              2 │ ORD                   1 ⟏ -----> linked list 4 -> 7 -> 0
 *           ╰─────┴───╯              3 │ DEN                   2 ⟏ -----> linked list 7 -> 0 -> 6 -> 5 -> 4 -> 3
 *           ╭─────┬───╮                                        3 ⟏ -----> linked list 9 -> 6 -> 2
 *           │ ORD │ 2 │                                          
 *           ╰─────┴───╯
 *           ╭─────┬───╮              
 *           │ DEN │ 3 │
 *           ╰──┬──┴─┬─╯ 
 *             key value
 */

public class SymbolDiGraph {
    private ST<String, Integer> st;  // string -> index
    private String[] keys;           // index  -> string
    private DiGraph graph;           // the graph with integer vertex names

    public SymbolDiGraph(String filename) throws FileNotFoundException {
        st = new ST<String, Integer>();                            
        Scanner in = new Scanner(new FileReader(filename));   // first path
        while (in.hasNextLine()) {                            // builds the index
            String[] a = in.nextLine().split("\\s+");         // by reading Strings
            for (int i = 0; i < a.length; i++) {              // to associate each
                if (!st.contains(a[i]))                       // distinct String
                    st.put(a[i], st.size());                  // with an index
            }
        }

        keys = new String[st.size()];       // inverted index
        for (String name : st.keys()) {     // to get string keys
            keys[st.get(name)] = name;      // in an array
        }

        graph = new DiGraph(st.size());                 
        in = new Scanner(new FileReader(filename));     // second pass
        while (in.hasNextLine()) {                      // builds the graph
            String[] a = in.nextLine().split("\\s+");   // by connecting the
            int v = st.get(a[0]);                       // first index
            for (int i = 1; i < a.length; i++) {        // on each line                
                graph.addEdge(v, st.get(a[i]));         // to all the others
            }
        }
    }

    public boolean contains(String s) {   return st.contains(s);   }  // does the graph contain the vertex s

    public int index(String s) {   return st.get(s);   }    // the integer associated with the vertex s

    public String name(int v) {   return keys[v];   }       // the name of the vertex associated with the integer

    public DiGraph G() {   return graph;   }  // the graph associated with symbol graph
}
