import java.util.Scanner;

public class Assignment5<Data> {
	private Node<Data> front;         
    private Node<Data> rear;          
	private int n;        		        

    private static class Node<Data> {    
    	private Data data;               
        private Node next;           
        private Node prev;           
    }

    public Assignment5() {        
        front = null;
        rear = null;
        n = 0;
    }

    public boolean isEmpty() {         
        return n == 0;
    }

    /*
        Doubly linked list
    */
    public void insertionEnd(Data data) {   
        Node newNode = new Node();
        newNode.data = data;
        if (front == null) {          
            front = newNode;           
            rear = newNode;              
            front = rear;
        } else {
            rear.next = newNode;         
            newNode.prev = rear;        
            rear = newNode;             
            rear.next = null;               
        }
        n++;                    
    }

    public void insertionFront(Data data) {
        Node newNode = new Node();
        newNode.data = data;
        if (front == null) {           
            front = newNode;         
            rear = newNode;         
            front = rear;
        } else {
            Node temp = front;
            newNode.next = front;
            temp.prev = newNode;
            front = newNode;
            front.prev = null;
        }
        n++; 

    }

    public void deletionKth(int k) {  
        Node ptr = rear;              
        if (n == 1) {                 
            front = front.next;         
        } else if (front != null) {
            if (n>=k) {
                for (int i = 2; i <= k; i++) {   
                    ptr = ptr.prev;    
                }
                if (ptr == rear) {         
                    rear = rear.prev;       
                    rear.next = null;     
                } else if (ptr == front) { 
                    ptr = ptr.prev;
                    front = front.next;     
                    front.prev = null;      
                } else {                   
                    ptr.prev.next = ptr.next;      
                    ptr.next.prev = ptr.prev;      
                }
            }
            else {System.out.println("not possible");}
        } 
        n--;
    }
        
    public void print() {  
        Node element = front;
        if (isEmpty()) {
            System.out.println("The queue is empty now!");
        } else {
            System.out.println("List: ");
        while(element != null) {   
            System.out.print("( " + element.data + " )");
            element = element.next;  
        }  
        System.out.println("\n");
        }
    }

    public static void main(String[] args) {
                    
        Scanner scanner = new Scanner(System.in);
        Scanner in = new Scanner(System.in);
        System.out.println("______Assignment 5______\n");
        System.out.println("1. Insert End 2. Insert Front  3. Remove the kth element from the end 4. Exit");
        System.out.println("");
    
        boolean valid = true;

        Assignment5<String> list = new Assignment5<String>();
        while (valid) {
            switch (scanner.nextInt()) {
                case 1: {
                    System.out.println("Input: ");
                    list.insertionEnd(in.nextLine());
                    list.print();
                    break;
                }
                case 2: {
                    System.out.println("Input: ");
                    list.insertionFront(in.nextLine());
                    list.print();
                    break;
                }
                case 3: {
                    System.out.println("Index: ");
                    list.deletionKth(scanner.nextInt());
                    list.print();
                    break;
                }

                case 4: {
                    valid = false;
                }
            }
        }
        scanner.close();
        in.close();
    }

    /* Scanner scanner = new Scanner(System.in);
    Assignment5<String> list = new Assignment5<String>();
    list.insertion("a");
    list.insertion("b");
    list.insertion("c");
    list.insertion("d");
    list.insertion("e");
    list.insertion("f");
    list.insertion("g");
    list.insertion("h");
    list.insertion("i");
    list.insertion("j");
    list.insertion("l");
    list.insertion("m");
    list.insertion("n");
    list.insertion("o");
    list.insertion("p");
    list.insertion("q");
    list.insertion("r");
    list.insertion("s");
    list.print();
    System.out.println("remove");
    int kth = scanner.nextInt();
    list.deletionKth(kth);
    list.print();
    scanner.close();*/
}
