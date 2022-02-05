import java.util.Scanner;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class Assignment4<Data> implements Iterable<Data> {
	private Node<Data> front;           
	private int n;        		       

    private static class Node<Data> {    
    	private Data data;              
        private Node next;              
        private Node prev;             
    }

    public Assignment4() {              
        front = null;
        n = 0;
    }

    public boolean isEmpty() {          
		return front == null;
    }

    public void insertionEnd(Data data) {   
        Node newNode = new Node();
        newNode.data = data;
        if (front == null) {         
            front = newNode;           
            front.next = front;          
            front.prev = front;                            
        } else {
           Node tempNode = front.prev;                    
           tempNode.next = newNode;                
           newNode.prev = tempNode;         
           front.prev = newNode;                       
           newNode.next = front;       
        } 
        n++;
    }

    public void insertionFront(Data data) {
        Node newNode = new Node();
        newNode.data = data;
        if (front == null) {            
            front = newNode;                
            front.next = front;          
            front.prev = front;           
        } else {
            Node last = front.prev;   
            front.prev = newNode;        
            last.next = newNode;         
            newNode.next = front;        
            newNode.prev = last;        
            front = newNode;           
        }
        n++;
    }

    public void deletionEnd() {
        if (!isEmpty()) {                                            
            if (front.next==front) {                            
                front.next = null;             
                front.prev = null;                   
                front = null;
                System.out.println("The queue is empty now");                
           } else {
               Node newLast = front.prev.prev;
               front.prev = newLast;
               newLast.next = front;
               front.prev = newLast;
            }
            n--;
        }
    }

    public void deletionFront() {             		
        if (!isEmpty()) {                                               
            if (front.next==front) {                             
                front.next = null;             
                front.prev = null;                   
                front = null;
                System.out.println("The queue is empty now");                
           } else {
                Node newFront = front.next;    
                newFront.prev = front.prev;                   
                front.prev.next = newFront;                        
                front = newFront;                            
            }
            n--;
        }
        else throw new NoSuchElementException("Queue underflow"); 
    }

    public void print() {
		StringBuilder str = new StringBuilder();
		int count = 1;
		for(Data data : this) {
            if (str!=null) {
			    if(count != n) {
				    str.append("( " + data + " ) , ");
				    count++;
			    }
                else {
                    str.append("( " + data + " )");
			        break;
                }
		    }
        }
        System.out.println(str);	
	}

    public Iterator<Data> iterator() {
        return new LinkedIterator();
    }

    private class LinkedIterator implements Iterator<Data> {
		private Node<Data> current = front;                      
        public boolean hasNext() { return current != null; }  
        public void remove() { throw new UnsupportedOperationException();}
        
        public Data next() {  
            if (!hasNext()) { throw new NoSuchElementException();   
            }
            Data data = current.data;
            current = current.next;
            return data;
        }
    }
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Scanner in = new Scanner(System.in);
        System.out.println("______Assignment 4______\n");
        System.out.println("1. Insertion Front 2. Insertion End 3. Deletion Front 4. Deletion End 5. Exit");
     
        boolean valid = true;

        Assignment4<String> list = new Assignment4<String>();
        while (valid) {
            switch (scanner.nextInt()) {
                case 1: {
                    list.insertionFront(in.nextLine());
                    list.print();
                    break;
                }
                case 2: {
                    list.insertionEnd(in.nextLine());
                    list.print();
                    break;
                }

                case 3: {
                    list.deletionFront();
                    list.print();
                    break;   
                }

                case 4: {
                    list.deletionEnd();
                    list.print();
                    break;
                }

                case 5: {
                    valid = false;
                }
            }
        }
        scanner.close();
        in.close();
    }
}
