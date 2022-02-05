#include <stdio.h>

void recursive() {
    char ch;                        
    if ((ch=getchar()) != '\n') {   
        recursive();
    }
    putchar(ch);            
}

void iterative() {
    char ch[10];        
    int counter = 0;    
    char input;

    while ((input=getchar()) != '\n') { 
        ch[counter] = input;
        counter++;
    }
    
    for (int i = counter-1; i>=0 ;i--) {
        putchar(ch[i]);
    }
}

int main() {
    printf("______Assignment 1______ \n");
    printf("Enter characters to be reversed (recursive version):  ");

    recursive();
    putchar('\n');

    printf("Enter characters to be reversed: (iterative version):  ");
    iterative();
    putchar('\n');
    return 0;
}