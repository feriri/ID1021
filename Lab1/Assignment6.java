import java.util.Scanner;

public class Assignment6<Data> {
	private Node<Data> front;            
    private Node<Data> rear;          
    int n;    		       

    private static class Node<Data> {    
    	private int data;               
        private Node next;              
        private Node prev;             
    }

    public Assignment6() {              
        front = null;
        rear = null;
        n = 0;
    }

    public boolean isEmpty() {           
        return front == null;
    }

    /*
        Doubly linked list
    */
    public void insertion(int data) {   
        Node newNode = new Node();
		newNode.data = data;

        if (!isEmpty()) {
            if (front.data >= newNode.data) {        
                newNode.next = front;
                front.prev = newNode; 
                front = newNode;
                front.prev = null;
            } else if (newNode.data >= rear.data) {  
                newNode.prev = rear;
                rear.next = newNode;
                rear = newNode;
                rear.next = null;
            } else {                              
                Node ptr = front;                      
                do {
                    ptr = ptr.next;                 
                    if(ptr.next == null) break;
                } while (ptr.data < newNode.data);  
                newNode.next = ptr;                 
                newNode.prev = ptr.prev;
                ptr.prev.next = newNode; 
                ptr.prev = newNode;
            }
        } else {
            front = newNode;                       
            rear = front;
        }
        n++;
    }

    public void deletion() {
        front = front.next;
        System.out.println("empty");
    }

    public void print() {  
        Node element = front;
        while(element != null) {   
            System.out.print("(" + element.data + ")");
            element = element.next;  
        }  
        System.out.println("");
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.println("______Assignment 6______\n");
        System.out.println("1. Insert 2. Delete 3. Exit ");
        System.out.println("");
    
        boolean valid = true;

        Assignment6<Integer> list = new Assignment6<Integer>();
        while (valid) {
            switch (in.nextInt()) {
                case 1: {
                    list.insertion(in.nextInt());
                    list.print();
                    break;
                }
                case 2: {
                    list.deletion();
                    list.print();
                    break;
                }

                case 3: {
                    valid = false;
                }
            }
        }
        in.close();
    }
}
