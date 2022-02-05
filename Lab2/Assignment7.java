import java.util.Scanner;
/* 
░ ▒ ▓░ ▒ ▓░ ▒ ▓░ ▒ ▓░ ▒ ▓░ ▒ ▓░ ▒ ▓░ ▒ ▓░ ▒ ▓░ ▒ ▓░ ▒ ▓░ ▒ ▓░ ▒ ▓░ ▒ ▓░ ▒ ▓░ ▒ ▓░ ▒ ▓
Augment the test code from assignment 1&2 so that the array is sorted in descending
order instead of ascending order (you may add O(N) operations) Clarification: You
should not change (not alter/modify any code in) the sorting method, nor should you
sort the array an extra time. You may traverse the array once before sorting and once
after sorting. During these traversals you may not move (re-order) any elements.
(Hint: you need not and should not use any extra memory)
░ ▒ ▓░ ▒ ▓░ ▒ ▓░ ▒ ▓░ ▒ ▓░ ▒ ▓░ ▒ ▓░ ▒ ▓░ ▒ ▓░ ▒ ▓░ ▒ ▓░ ▒ ▓░ ▒ ▓░ ▒ ▓░ ▒ ▓░ ▒ ▓░ ▒ ▓
*/

public class Assignment7 {
    public static void insertionSort(int arr[], int n) {  
        int swap = 0;
        for(int i = 1; i <= n-1; i++) {       
            for(int j = i; j > 0; j--) {
                if(arr[j] < arr[j-1]) {    
                    int temp = arr[j];          
                    arr[j] = arr[j-1];    
                    arr[j-1] = temp;            
                    swap++;                  
                } else break;
                System.out.println("\nSwap " + swap + ":");
                print(arr, n);                // print after each inner loop iteration 
            }
        }
        System.out.println("\nTotal swaps: " + swap);
    }

    public static void inversionCount(int arr[], int n) { 
        int inversion = 0;
        for(int i = 0; i < n; i++) {               
            for(int j = i+1; j < n; j++) {    
                if(arr[i] > arr[j]){            
                    System.out.println("a[" + i + "] = " + arr[i] + ", " + "a[" + j + "] = " + arr[j] + "");
                    inversion++;
                }                               
            }                                  
        }  
        System.out.println("\nTotal inversions: " + inversion);
    }

    static void print(int arr[], int n) {       // print the array
        for(int i = 0; i < n; i++) {
                if(i == n-1) {
                    System.out.println("["+ arr[i] +"]");
                    break;
                }
            System.out.print("["+ arr[i] +"],");
        }  
    } 

    static void traverseArray(int arr[], int n) {
        for(int i = 0; i < n; i++) {
            arr[i] *= -1;
        }
    }

    public static void main(String[] args) {
        System.out.println("░░░░░░░Assignment 7░░░░░░░");
        System.out.println("Size of the array: ");
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();                  // the size of the array (the # of elements)
        int[] arr = new int[n];                // creats an array
        System.out.println("Numbers: ");

        for(int i = 0; i < n; i++) {           // read the input array     
            arr[i] = in.nextInt();
        }
        in.close();    
        
        System.out.println("\nInput:");
        print(arr, n);
        System.out.println("\nInversions:");
        inversionCount(arr, n);
        
        traverseArray(arr, n);      // element * -1
        insertionSort(arr, n);      // sortera
        traverseArray(arr, n);      // element * -1

        System.out.println("\nArray in descending order");
        print(arr, n);
    }
}


