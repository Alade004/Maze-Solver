## Overview
This is a java program called the maze solving system. The code will load a maze for the file and solve the maze. The program utilizes a maze solving system based on the Stack data structure.

## Usage
To use the Maze Solver, follow these steps:
1. Compile the java program using the following command:
    javac *.java
2. Run the program with the desired maze file you want 
    ex: java Maze maze.txt
3. To display the solution include:
    --solution

## Example 
1. java Maze maze.txt

# Ouput: 

+-----+-----+-----+
|                 |
|              F  |
|                 |
+     +-----+-----+
|                 |
|        S        |
|                 |
+-----+-----+-----+

solution displayed:

+-----+-----+-----+
|                 |
|  *     *     F  |
|                 |
+     +-----+-----+
|                 |
|  *     S        |
|                 |
+-----+-----+-----+


## Prompt
Using a Stack is an appropriate ADT to use for the solver as it makes it the best approach to go through the maze. Stacks provide a depth-first search strategy, which makes them the most suitable for modeling a person navigating a maze. 
