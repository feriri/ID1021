import java.util.*;
/* 
░ ▒ ▓░ ▒ ▓░ ▒ ▓░ ▒ ▓░ ▒ ▓░ ▒ ▓░ ▒ ▓░ ▒ ▓░ ▒ ▓░ ▒ ▓░ ▒ ▓░ ▒ ▓░ ▒ ▓░ ▒ ▓░ ▒ ▓░ ▒ ▓░ ▒ ▓
Compare the execution times for sorting large arrays of integers with insertionsort,
merge sort and quicksort. When should one select one method over the others? Upload
code, tests and a graphs depicting the execution times as a function of input (what
parameters in the input could be relevant?). (you need to test for a range of input
sizes)
░ ▒ ▓░ ▒ ▓░ ▒ ▓░ ▒ ▓░ ▒ ▓░ ▒ ▓░ ▒ ▓░ ▒ ▓░ ▒ ▓░ ▒ ▓░ ▒ ▓░ ▒ ▓░ ▒ ▓░ ▒ ▓░ ▒ ▓░ ▒ ▓░ ▒ ▓
*/

public class Assignment4 {
    public static void insertionSort(int arr[], int n) {
        for(int i = 1; i < n; i++) {          
            for(int j = i; j > 0; j--) {
                if(arr[j] < arr[j-1]) {      
                    int temp = arr[j];          
                    arr[j] = arr[j-1];        
                    arr[j-1] = temp;            
                } else break;
            }
        }
    }

    static void merge(int arr[], int temp[], int beg, int mid, int end) {  
        for(int k = beg; k <= end; k++) {  
            temp[k] = arr[k];
        }           
        int i= beg, j = mid+1;   

        for(int k = beg; k <= end; k++) {     
            if(i > mid) {                    
                arr[k] = temp[j++];         
            } else if(j > end) {         
                arr[k] = temp[i++];          
            } else if(temp[j]<temp[i]) {     
                arr[k] = temp[j++];          
            } else {                        
                arr[k] = temp[i++];
            }
        }
    }

    static void mergeSort(int arr[], int temp[], int beg, int end) {
        if(beg < end) {
            int mid = beg + (end - beg) / 2;                                
            mergeSort(arr, temp, beg, mid);          
            mergeSort(arr, temp, mid+1, end);         
            merge(arr, temp, beg, mid, end);          
        }
    }

    public static int partition(int[] arr, int beg, int end) {  
        int pivot = arr[end];  
        int k = beg;          
        for(int j = beg; j < end; j++) {                                                                 
            if(arr[j] < pivot) {   
                int temp = arr[k];  
                arr[k] = arr[j];    
                arr[j] = temp;      
                k++;               
            }                     
        }                          
        arr[end] = arr[k];             
        arr[k] = pivot;                         
        return k;  
    }

    public static void quickSort(int arr[], int beg, int end) {                                  
        if(beg < end) {                                                       
            int pivPos = partition(arr, beg, end); 
            quickSort(arr,beg,pivPos-1);           
            quickSort(arr,pivPos+1,end);           
        }
    }

    static void print(int arr[], int n) {       
        for(int i = 0; i < n; i++) {
                System.out.print(arr[i]+ " ");
        }  
        System.out.println();
    }

    static void random(int arr[], int n, int seed) {
        Random rnd = new Random(seed);
        for(int i = 0; i < n; i++) {
            arr[i] = rnd.nextInt(n);
        }
    }

    static void printMenue() {
        System.out.println("\n1. Random array"); 
        System.out.println("2. Quick Sort");
        System.out.println("3. Merge Sort");
        System.out.println("4. Insertion Sort");
        System.out.println("5. Print the array");
        System.out.println("6. Exit");
    }
    
    public static void main(String[] args) {
        System.out.println("░░░░░░░Assignment 4░░░░░░░");
        System.out.println("Size of the array: ");
        Scanner scanner = new Scanner(System.in);
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();                 
        int[] arr = new int[n];               
        int[] tempMerge = new int[n];
        int seed = new Random().nextInt(10000);

        random(arr, n, seed);
        printMenue();

        boolean valid = true;

        while(valid) {
            switch (scanner.nextInt()) {
                case 1: {
                    random(arr, n, seed);
                   // print(arr, n);
                    printMenue();           
                    break;
                }
                case 2: {
                    long start = System.nanoTime();
                    quickSort(arr, 0, n - 1);  
                    long end = System.nanoTime();
                    System.out.println("\nQuick Sort runtime: " + (end - start)/1000000 + " ms");
                   // print(arr, n);
                    printMenue();
                    break;
                }
                case 3: {
                    long start = System.nanoTime();
                    mergeSort(arr,tempMerge, 0, n - 1);             
                    long end = System.nanoTime();
                    System.out.println("\nMerge Sort runtime: " + (end - start)/1000000 + " ms");
                  //  print(arr, n);
                    printMenue();
                    break;
                }
                case 4: {
                    long start = System.nanoTime();
                    insertionSort(arr, n);
                    long end = System.nanoTime();
                    System.out.println("\nInsertion Sort runtime: " + (end - start)/1000000 + " ms");
                 //   print(arr, n);
                    printMenue();
                    break;
                }
                case 5: {
                    print(arr, n);
                    printMenue();
                    break;
                }
                case 6: {
                    valid = false;
                }
            }
        }
    scanner.close();
    in.close();
    }
}