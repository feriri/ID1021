import java.util.Scanner;

/* 
░ ▒ ▓░ ▒ ▓░ ▒ ▓░ ▒ ▓░ ▒ ▓░ ▒ ▓░ ▒ ▓░ ▒ ▓░ ▒ ▓░ ▒ ▓░ ▒ ▓░ ▒ ▓░ ▒ ▓░ ▒ ▓░ ▒ ▓░ ▒ ▓
Basis for assignments 1 and 2: Implement insertionsort. Augment the sorting
process so that all the content of the array that is being sorted is printed
after each inner loop iteration. Write a unit test in main() which allows the
user to define the size of the input (N) and then input (N) integers from stdin
which is to be sorted.
Augment the above implementation so that it prints the number of swaps performed
when sorting the array.
░ ▒ ▓░ ▒ ▓░ ▒ ▓░ ▒ ▓░ ▒ ▓░ ▒ ▓░ ▒ ▓░ ▒ ▓░ ▒ ▓░ ▒ ▓░ ▒ ▓░ ▒ ▓░ ▒ ▓░ ▒ ▓░ ▒ ▓░ ▒ ▓
*/

public class Assignment1 { // inspiration from princeton
    public static void insertionSort(int arr[], int n) { // sort the array in ascending order
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
                System.out.println("\nSwap " + swap + ":");
                print(arr, n); // print after each inner loop iteration
            }
        }
        System.out.println("\nTotal swaps: " + swap);
    }

    static void print(int arr[], int n) { // print the array
        for (int i = 0; i < n; i++) {
            if (i == n - 1) {
                System.out.println("[" + arr[i] + "]");
                break;
            }
            System.out.print("[" + arr[i] + "],");
        }
    }

    public static void main(String[] args) {
        System.out.println("░░░░░░░Assignment 1░░░░░░░");
        System.out.println("\nSize of the array: ");
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int[] arr = new int[n];
        System.out.println("Numbers:");

        for (int i = 0; i < n; i++) { // read the input array
            arr[i] = in.nextInt();
        }
        in.close();

        System.out.println("\nUnsorted:");
        print(arr, n);
        insertionSort(arr, n);
        System.out.println("\nSorted:");
        print(arr, n);
    }
}
