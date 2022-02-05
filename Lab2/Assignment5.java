import java.util.*;
/* 
// some codes are taken from princeton
░ ▒ ▓░ ▒ ▓░ ▒ ▓░ ▒ ▓░ ▒ ▓░ ▒ ▓░ ▒ ▓░ ▒ ▓░ ▒ ▓░ ▒ ▓░ ▒ ▓░ ▒ ▓░ ▒ ▓░ ▒ ▓░ ▒ ▓░ ▒ ▓░ ▒ ▓
Experiment with the cut-off to insertionsort in mergesort. How is the execution time
affected by different values for the cut-off? A suitable range for cut-off values to
test with could be [0-30]. Upload code, tests and a graphs.
░ ▒ ▓░ ▒ ▓░ ▒ ▓░ ▒ ▓░ ▒ ▓░ ▒ ▓░ ▒ ▓░ ▒ ▓░ ▒ ▓░ ▒ ▓░ ▒ ▓░ ▒ ▓░ ▒ ▓░ ▒ ▓░ ▒ ▓░ ▒ ▓░ ▒ ▓
*/

public class Assignment5 {
    //static final int CUTOFF = 30;
    
    static void insertionSort(int arr[], int beg, int end) {
        for(int i = beg; i <= end; i++) {
            for(int j = i; j > beg; j--) {
                if(arr[j] < arr[j-1]) {
                    int temp = arr[j];
                    arr[j] = arr[j-1];
                    arr[j-1] = temp;
                }
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
     
                                                                
    static void mergeSort(int arr[], int temp[], int beg, int end, int CUTOFF) {                                 
        if(end-beg <= CUTOFF) {                
            insertionSort(arr, beg, end);
            return;
        } 
        int mid = beg + (end - beg) / 2;  
        mergeSort(arr, temp, beg, mid, CUTOFF); 
        mergeSort(arr, temp, mid+1, end, CUTOFF);  
        merge(arr, temp, beg, mid, end);
    }
   /* static void randomArray(int arr[], int n) {
        for(int i = 0; i < n; i++) {
            int random = (int)(Math.random()*n*10) + 0;  // random numbers between 0 - n*10
            arr[i] = random;
        }
    }

    static void copyArray(int arr[], int n, int temp[]) {   // kopierar random array
        for(int i = 0; i < n; i++) {  
            temp[i] = arr[i];
        }
    }*/

    static void random(int arr[], int n, int seed) {
        Random rnd = new Random(seed);
        for(int i = 0; i < n; i++) {
            arr[i] = rnd.nextInt(n);
        }
    }

    static void print(int arr[], int n) {       // print the array
        for(int i = 0; i < n; i++) {
                System.out.print(arr[i]+ " ");
        }  
        System.out.println();
    } 
    
    public static void main(String[] args) {
        System.out.println("░░░░░░░Assignment 5░░░░░░░");
        Scanner in = new Scanner(System.in);
        boolean valid = true;
        System.out.println("Size of the array: ");
        int n = in.nextInt(); 
        int[] arr = new int[n];
        int seed = new Random().nextInt(10000);
        while(valid) {
        System.out.println("CUTOFF: ");
        int CUTOFF = in.nextInt();
        if(CUTOFF==30) {valid =false;}           
        int[] tempMerge = new int[n];
        random(arr, n, seed);
        //print(arr, n);
        long start = System.nanoTime();
        mergeSort(arr, tempMerge, 0, n-1, CUTOFF);
        long end = System.nanoTime();
       // print(arr, n);

        System.out.println("Merge Sort with cut-off " + CUTOFF + " runtime: " + (end - start)/1000000 + " ms\n"); 
        }
        in.close();
    }
}
