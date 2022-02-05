#include <stdio.h>
#include <ctype.h>
/* 
░ ▒ ▓░ ▒ ▓░ ▒ ▓░ ▒ ▓░ ▒ ▓░ ▒ ▓░ ▒ ▓░ ▒ ▓░ ▒ ▓░ ▒ ▓░ ▒ ▓░ ▒ ▓░ ▒ ▓░ ▒ ▓░ ▒ ▓░ ▒ ▓
Write a simple filter to clean a text, i.e. to remove all characters that are not
alphabetic, blank or newline - replacing every such character by a blank to keep
the number of characters constant to the original text. Hint: this is easy to do
in C using the "isalpha()" function (to find out more about it on a unix/linux
system type: man isalpha as a command to the shell) This could be useful for the
next assignments. A filter is a program which reads its input from stdin,
processes it and the prints the result to stdout (i.e. reads and writes from/to
the terminal).
░ ▒ ▓░ ▒ ▓░ ▒ ▓░ ▒ ▓░ ▒ ▓░ ▒ ▓░ ▒ ▓░ ▒ ▓░ ▒ ▓░ ▒ ▓░ ▒ ▓░ ▒ ▓░ ▒ ▓░ ▒ ▓░ ▒ ▓░ ▒ ▓
*/

// isalpha() checks whether a char is an alpahbet (a-z and A-Z) or not
// isalpha() returns Zero (0) if the parameter is not an alphabet
// & Non zero number if the parameter is an alpahbet

int main() {
    printf("░░░░░░░Assignment 1░░░░░░░\n");
	FILE *re;
	if ((re = fopen("/Users/farzaneh/Desktop/Lab3/ATaleofTwoCities.txt", "r")) == NULL) {  // read the file
		printf("Error");    // om filen inte finns = print "Error"
	}
	char ch;

	while ((ch = getc(re))!= -1) {	 // så länge vi inte nått EOF (end of file)
		if (isalpha(ch) == 0 && ch != 32 && ch != 10) {  // if not alphabetic, blank or newline
			ch = 32;									 // replace by a blank
		}
		putchar(ch);									 // otherwise, write the char to stdout
	}
	fclose(re);

    return 0;
}