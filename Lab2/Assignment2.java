import java.util.Scanner;

/* 
░ ▒ ▓░ ▒ ▓░ ▒ ▓░ ▒ ▓░ ▒ ▓░ ▒ ▓░ ▒ ▓░ ▒ ▓░ ▒ ▓░ ▒ ▓░ ▒ ▓░ ▒ ▓░ ▒ ▓░ ▒ ▓░ ▒ ▓░ ▒ ▓░ ▒ ▓
Add a method to Assignment1 which counts the number of inversions in the input
array and prints a list of all inversions on the format [i,a[i]], [j, a[j]] where
i and j are indices and a[i], a[j] are the values of the elements. Call the method
from main() before the array is sorted. Calculate the time complexity for the algorithm.
░ ▒ ▓░ ▒ ▓░ ▒ ▓░ ▒ ▓░ ▒ ▓░ ▒ ▓░ ▒ ▓░ ▒ ▓░ ▒ ▓░ ▒ ▓░ ▒ ▓░ ▒ ▓░ ▒ ▓░ ▒ ▓░ ▒ ▓░ ▒ ▓░ ▒ ▓
*/

// inversion: if i < j and a[i] > a[j] then the pair (i,j) is an inversion of a[]
// if the array is already sorted -> inversions = 0
// if the array is sorted in reverse order -> inversions = max

public class Assignment2 {
    public static void insertionSort(int arr[], int n) {
        int swap = 0;
        for (int i = 1; i <= n - 1; i++) {
            for (int j = i; j > 0; j--) {
                if (arr[j] < arr[j - 1]) {
                    int temp = arr[j];
                    arr[j] = arr[j - 1];
                    arr[j - 1] = temp;
                    swap++;
                } else
                    break;
                print(arr, n);
            }
        }
        System.out.println("\nTotal swaps: " + swap);
    }

    public static void inversionCount(int arr[], int n) { // brute force, inspiration from princeton
        int inversion = 0;

        for (int i = 0; i < n; i++) { // ⟏ ⟏ ⟏ ⟏ ⟏ ⟏ ⟏ ⟏ ⟏ ⟏ ⟏ ⟏
            for (int j = i + 1; j < n; j++) { // i=0 j=1 i=1 j=2 i=3 j=3
                if (arr[j] < arr[i]) {
                    System.out.println("a[" + i + "] = " + arr[i] + ", " + "a[" + j + "] = " + arr[j] + "");
                    inversion++;
                } // if i < j and a[i] > a[j] then the pair (i,j) is an inversion
            }
        }
        System.out.println("\nTotal inversions: " + inversion);
    }

    static void print(int arr[], int n) {
        for (int i = 0; i < n; i++) {
            if (i == n - 1) {
                System.out.println("[" + arr[i] + "]");
                break;
            }
            System.out.print("[" + arr[i] + "],");
        }
    }

    public static void main(String[] args) {
        System.out.println("░░░░░░░Assignment 2░░░░░░░");
        System.out.println("Size of the array: ");
        Scanner in = new Scanner(System.in);
        int n = in.nextInt(); // the size of the array (the # of elements)
        int[] arr = new int[n]; // creats an array
        System.out.println("Numbers: ");

        for (int i = 0; i < n; i++) { // read the input array
            arr[i] = in.nextInt();
        }
        in.close();

        System.out.println("\nInput:");
        print(arr, n);
        System.out.println("\nInversions:");
        inversionCount(arr, n);
        System.out.println("\nSwaps:");
        insertionSort(arr, n);
    }
}
