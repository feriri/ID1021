import java.util.Scanner;
import java.util.NoSuchElementException;

public class Assignment7 <Data>{ 
   private Node top;          
   private int s;           

   private class Node {
      private Data data;                
      private Node nextNode;             
   }

   public Assignment7() {     
      top = null;
      s = 0;
   }

   public boolean isEmpty() {   
      return top == null;
   }

   public int size() {
      return s;
   }
   
   public void push(Data d) {   
      Node node = new Node();  
      node.data = d;            
      node.nextNode = top;      
                                 
      top = node;                
      s++;                       
   }

   public Data pop() {
      if(isEmpty()) throw new NoSuchElementException("Stack is empty");
      Data data = top.data;
      top = top.nextNode;
      s--;
      return data;
   }

   public void print() {
      if(isEmpty()) {
         System.out.println("Balanced :)");
      } else {
         System.out.println("Not balanced :(");
      }
   }

   public Data peek() { 
      if(isEmpty()) throw new NoSuchElementException("Stack is empty");
      return top.data;        // returns the most recently added data to the stack
   }

   public static void main(String args[]) {
      Scanner scanner = new Scanner(System.in);
      System.out.println("______Assignment 7______\n");
      System.out.println("Enter brackets ");
      String str = scanner.nextLine();

      scanner.close();
      
      Assignment7<Character> stack = new Assignment7<>();   // create stack

      for(int i = 0; i < str.length(); i++) {
         char bracket = str.charAt(i);
         if(bracket == '(' || bracket == '[' || bracket == '{') { // if opening bracket
               stack.push(bracket);                               // then push
         } else if (!stack.isEmpty() && ((stack.peek() == '(' && bracket == ')') || 
                     (stack.peek() == '{' && bracket == '}') ||   // if closing bracket match
                     (stack.peek() == '[' && bracket == ']'))) {  
               stack.pop();                                       // then pop
          } else {
               stack.push(bracket);                            
          }
      }
      stack.print();          
   }      
}

// ([()]{}{[()()]()})   balanced
// ({}{}{]})            not balanced