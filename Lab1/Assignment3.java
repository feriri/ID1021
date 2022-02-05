import java.util.Scanner;
import java.util.Iterator;
import java.util.NoSuchElementException;

// in this lab:
// insertion: add a new element to the end of the linked list
// deletion: remove an element from the front of the linked list


public class Assignment3<Data> implements Iterable<Data> {
	private Node<Data> front;            
	//private Node rear;  		     
	private int n;        		        

    private static class Node<Data> {    
    	private Data data;              
        private Node next;             
        private Node prev;            
    }

    public Assignment3() {              
        front = null;
        //rear = null;
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
		private Node<Data> current = front;          // pointer to front of the list for iteration              
        public boolean hasNext() { return current != null; }  // returns false if next elemet doesn't exist
        public void remove() { throw new UnsupportedOperationException();}
        
        public Data next() {   // return current data and update pointer
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
        System.out.println("______Assignment 3______\n");
        System.out.println("1. Insertion 2. DeletionFront 3. Exit");
     
        boolean valid = true;

        Assignment3<String> queue = new Assignment3<String>();
        while (valid) {
            switch (scanner.nextInt()) {
                case 1: {
                    queue.insertionEnd(in.nextLine());
                    queue.print();
                    break;
            }
                case 2: {
                    queue.deletionFront();
                    queue.print();
                    break;
            }
                case 3: {
                    valid = false;
                }
            }
        }
        scanner.close();
        in.close();
    }
}