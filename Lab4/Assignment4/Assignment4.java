import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

/*
** Directed graph
░ ▒ ▓░ ▒ ▓░ ▒ ▓░ ▒ ▓░ ▒ ▓░ ▒ ▓░ ▒ ▓░ ▒ ▓░ ▒ ▓░ ▒ ▓░ ▒ ▓░ ▒ ▓░ ▒ ▓░ ▒ ▓░ ▒ ▓░ ▒ ▓░ ▒ ▓░ ▒
Write a program that can answer if there is a path between any to vertices.
░ ▒ ▓░ ▒ ▓░ ▒ ▓░ ▒ ▓░ ▒ ▓░ ▒ ▓░ ▒ ▓░ ▒ ▓░ ▒ ▓░ ▒ ▓░ ▒ ▓░ ▒ ▓░ ▒ ▓░ ▒ ▓░ ▒ ▓░ ▒ ▓░ ▒ ▓░ ▒
 */

 public class Assignment4 {
    public static void main(String[] args) throws FileNotFoundException {
        SymbolDiGraph sg = new SymbolDiGraph("/Users/farzaneh/Desktop/Lab4/thedatabase.txt");
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter X: ");
        String f = scanner.nextLine().toUpperCase();   // input src
        int s = sg.index(f);                           // the integer associated with src
        System.out.println("Enter Y: ");
        String t = scanner.nextLine().toUpperCase();  // input dst
        int v = sg.index(t);                          // the integer associated with dst

        //Graph G = new Graph(5);
        //int s = Integer.parseInt(args[1]);
     
        DepthFirstDirectedPaths dfs = new DepthFirstDirectedPaths(sg.G(), s);
        System.out.println("\n░░░░░░░Assignment 4░░░░░░░\n");
        //for (int v = 0; v < G.V(); v++) 
        if (dfs.hasPathTo(v)) {
            System.out.println("There is a path between " + sg.name(s) + " and " + sg.name(v));
            for (int x : dfs.pathTo(v)) {
                if (x == s) System.out.print(sg.name(x).toLowerCase());
                else System.out.print("-" + sg.name(x).toLowerCase());
            }
            System.out.println();
        } else {
            System.out.println("There is no path between " + sg.name(s) + " and " + sg.name(v));
        }
        scanner.close();
    }
 }