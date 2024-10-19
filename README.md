## Background

This project is the final assignment for the Algorithms and Data Structures course at USN. The task was to create a program that reads text, counts three-word combinations (trigrams), and generates text based on the probabilities of the next word in these sequences.

**Part 1: Counting Word Combinations**: The program reads a text file or URL, splits the content into words, and counts three-word combinations. For example, after processing a Wikipedia article on LOTR, snippet of the trigrams might look like this:

    [Jackson's, emphasis] -> {on=1}
    [tried, to] -> {buy=1}
    [that, they] -> {would=1, are=4, need=1, had=1}
    [hoping, they] -> {will=1}

**Part 2: Generating Text**: The program generates text by allowing the user to manually select two starting words. The third word is chosen based on trigram statistics. For instance, if "det+var+i" occurs 3 times and "det+var+på" occurs once, the word "i" will be chosen with a 3/4 probability, and "på" with a 1/4 probability.

### Notes
There is significant potential for improvement, particularly in how the program handles reading content, especially from URLs. Currently, unwanted symbols are filtered using regex, and it has only been used on Wikipedia articles. To fix this, a library should be used for web parsing, along with validation and error handling. Due to this filtering the program doesnt fully handle punctuation either. This demo serves as a proof of concept for the assignment, and the logic is somewhat intertwined with the JavaFX components, which could also be improved.