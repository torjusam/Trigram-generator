**Background**

This project is the final assignment for the Algorithms and Data Structures course. The task is to create a program that reads of text, counts three-word combinations (trigrams), and generates new text based on these combinations.

***Part 1: Counting Word Combinations***: The program reads a text file or URL, splits the content into words, and counts three-word combinations. For example:

    "det+var+i"
    "var+i+den"
    etc.


***Part 2: Generating Text***: The program generates text by allowing the user to manually select two starting words. The third word is chosen based on trigram statistics. For example, "det+var+i" (3 occurrences) and "det+var+på" (1 occurrence) will yield "i" with a 3/4 probability and "på" with 1/4.
