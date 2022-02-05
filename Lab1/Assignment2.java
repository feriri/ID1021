import java.util.Scanner;
import java.util.NoSuchElementException;

public class Assignment2 <Data>{ 
   private Node top;             
   private int s;            

   private class Node {         
      private Data data;                 
      private Node nextNode;        
   }

   public Assignment2() {      
      top = null;
      s = 0;
   }

   public boolean isEmpty() {    
      return top == null;
   }

   public int size() {
      return s;
   }
   
   public void push (Data d) {   // func to add an element (data) to the stack
      Node node = new Node();    
      node.data = d;            
      node.nextNode = top;     
                                 
      top = node;                
      s++;                       
    
      /*Node oldfirst = top;     // bokens kod
      top = new Node();          
      top.data = d;
      top.nextNode = oldfirst;    
      s++;*/
   }
   public Data pop() {
      if(isEmpty()) throw new NoSuchElementException("Stack is empty");
      Data data = top.data;
      top = top.nextNode;
      s--;
      return data;
   }
    /*if(isEmpty()) throw new NoSuchElementException("Stack is empty");
      return top.data; */

   public static void recursive(String s) {         
      if(!(s.isEmpty())) {                                                             
         System.out.println(s.charAt(s.length()-1));
         recursive(s.substring(0, s.length()-1));    
      }
      //return;
   }

      /*public static void recursive(String s) {    // input : blue
      if(s.isEmpty()) {                             // "e" + blu
        return;                                     // "e" + "u" + bl
      }                                             // "e" + "u" + "l" + b
      System.out.println(s.charAt(s.length()-1));  
      recursive(s.substring(0, s.length()-1));    
    }*/

   public static void main(String args[]) {
      Scanner scanner = new Scanner(System.in);
      System.out.println("______Assignment 2______\n");
      System.out.println("Enter a sentence to be reversed: ");
      String str = scanner.nextLine();
      scanner.close();
      System.out.println("Recursive version: ");
      recursive(str);
      Assignment2<Character> stack = new Assignment2<>();   // create stack
      System.out.println("Iterative version: ");
      // push all the characters in the stack and then pop them out

      for(int i = 0; i < str.length(); i++) {       
         stack.push(str.charAt(i));                // store char in stack
      }
      while (stack.size()>0) {                     // print char from stack
         System.out.print(stack.pop()+"\n");  
      }
      /*for(int j = stack.size(); j > 0; j-- ) {
         System.out.print(stack.pop());
      }*/
    }
}
