import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

/*
** Undirected graph
░ ▒ ▓░ ▒ ▓░ ▒ ▓░ ▒ ▓░ ▒ ▓░ ▒ ▓░ ▒ ▓░ ▒ ▓░ ▒ ▓░ ▒ ▓░ ▒ ▓░ ▒ ▓░ ▒ ▓░ ▒ ▓░ ▒ ▓░ ▒ ▓░ ▒ ▓░ ▒
Write a program based on BFS which can answer questions of the type: "Find the a path
from X to Y" Which should result in a list of vertices traversed from X to Y if there is
a path.
░ ▒ ▓░ ▒ ▓░ ▒ ▓░ ▒ ▓░ ▒ ▓░ ▒ ▓░ ▒ ▓░ ▒ ▓░ ▒ ▓░ ▒ ▓░ ▒ ▓░ ▒ ▓░ ▒ ▓░ ▒ ▓░ ▒ ▓░ ▒ ▓░ ▒ ▓░ ▒
 */

 public class Assignment2 {
    public static void main(String[] args) throws FileNotFoundException {
        SymbolGraph sg = new SymbolGraph("/Users/farzaneh/Desktop/Lab4/thedatabase.txt"); // Symbol Graph
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter X: ");
        String f = scanner.nextLine().toUpperCase();   // input source
        int s = sg.index(f);                           // the integer associated with source vertex
        System.out.println("Enter Y: ");               
        String t = scanner.nextLine().toUpperCase();   // input destination
        int v = sg.index(t);                           // the integer associated with destination vertex

        //Graph G = new Graph(5);
        //int s = Integer.parseInt(args[1]);
        
        BreadthFirstPaths bfs = new BreadthFirstPaths(sg.G(), s);    // BFS
        System.out.println("\n░░░░░░░Assignment 2░░░░░░░\n");
        //for (int v = 0; v < G.V(); v++) 
        if (bfs.hasPathTo(v)) {
            System.out.print("Path from " + sg.name(s) + " to " + sg.name(v) + " : ");
            for (int x : bfs.pathTo(v)) {
                if (x == s) System.out.print(sg.name(x));
                else System.out.print(" ⋙ " + sg.name(x));
            }
            System.out.println();
        } else {
            System.out.println("not connected\n");
        }
        scanner.close();
    }
 }