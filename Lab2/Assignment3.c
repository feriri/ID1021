#include <stdio.h>
/* 
░ ▒ ▓░ ▒ ▓░ ▒ ▓░ ▒ ▓░ ▒ ▓░ ▒ ▓░ ▒ ▓░ ▒ ▓░ ▒ ▓░ ▒ ▓░ ▒ ▓░ ▒ ▓░ ▒ ▓░ ▒ ▓░ ▒ ▓░ ▒ ▓
Implement a function in C which takes an array of integers (both positive and
negative) and orders the elements in the array so that all negative elements come
before the positive. You are not allowed to sort the array (i.e. by any of the
sorting methods) - only collect all negative values first. The algorithm should
only use O(1) extra memory (i.e. be in-place Wikipedia: In-place algorithm)
░ ▒ ▓░ ▒ ▓░ ▒ ▓░ ▒ ▓░ ▒ ▓░ ▒ ▓░ ▒ ▓░ ▒ ▓░ ▒ ▓░ ▒ ▓░ ▒ ▓░ ▒ ▓░ ▒ ▓░ ▒ ▓░ ▒ ▓░ ▒ ▓
*/

void swap(int a[], int l, int k) {
	int temp = a[l];
	a[l] = a[k];				// byt plats på elementen
	a[k] = temp;
}

void negativeFirst(int a[], int n) {
	
	for(int i = 0; i < n; i++) {
		for(int j = i+1; j < n; j++) {
			if(a[i] > 0 && a[j] < 0) { swap(a, i, j); }
		}
	}
}

int main() {
    printf("░░░░░░░Assignment 3░░░░░░░\n");
	int n;
	printf("The size of the array: ");
	scanf("%d", &n);
	
	int a[n];
	printf("Numbers (negative & positive): \n");
	for(int i = 0; i < n; i++) {      // read the input array
		scanf("%d", &a[i]);
	}

	negativeFirst(a,n);
	for(int i = 0; i < n; i++) {     // print the array
		if(i == n-1) {
			printf("[%d]\n", a[i]);
			break;
		}
		printf("[%d],", a[i]);
	}
    return 0;
}
