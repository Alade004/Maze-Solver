import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.Stack;

/*
 * General public class for our Maze.
 */
public class Maze {
    // Number of Rows in the maze.
    private int numRows;
    // Number of Columns in the Maze.
    private int numColumns;
    // Grid coordinates for the starting maze square.
    private int startRow;
    private int startColumn;
    // Grid coordinates for the final maze square.
    private int finishRow;
    private int finishColumn;
    // A double array of the MazeSquares in the maze.
    private MazeSquare[][] squares;

    /* 
     * Creates an empty maze with no squares.
     */
    public Maze() {
    }

      /**
     * Loads the maze from a file.
     * 
     * @param fileName The name of the file containing the maze data.
     * @return True if the maze is successfully loaded, false if not.
     */
    public boolean load(String fileName) {
        File inputFile = new File(fileName);
        Scanner scanner = null;
        try {
            scanner = new Scanner(inputFile);
            numRows = scanner.nextInt();
            numColumns = scanner.nextInt();
            squares = new MazeSquare[numRows][numColumns];
            startRow = scanner.nextInt();
            startColumn = scanner.nextInt();
            finishRow = scanner.nextInt();
            finishColumn = scanner.nextInt();
    
            for (int i = 0; i < numRows; i++) {
                String linecode = scanner.next();
                for (int j = 0; j < numColumns; j++) {
                    if (linecode.length() >= numColumns) {
                        String code = linecode.substring(j, j + 1);
                        if (code.equals("7")) {
                            squares[i][j] = new MazeSquare(true, true, false, false, i, j);
                        } else if (code.equals("|")) {
                            squares[i][j] = new MazeSquare(false, true, false, false, i, j);
                        } else if (code.equals("_")) {
                            squares[i][j] = new MazeSquare(true, false, false, false, i, j);
                        } else if (code.equals("*")) {
                            squares[i][j] = new MazeSquare(false, false, false, false, i, j);
                        } else {
                            return false;
                        }
                    }
                }
            }
            scanner.close();
            return true;
        } catch (FileNotFoundException e) {
            System.err.println(e);
            return false;
        }
    }
    
  
    /**
     * Checks if a number is within a specified range.
     * 
     * @param number The number to check.
     * @param lowerBound The lower bound of the range.
     * @param upperBound The upper bound of the range.
     * @return True if the number is within the specified range, false if not.
     */
    private static boolean isInRange(int number, int lowerBound, int upperBound) {
        return number < upperBound && number >= lowerBound;
    }

    /**
    * Prints the solution path on the maze.
    *
    * @param solution The stack containing the solution path.
    */
    public void print(boolean showSolution) {
        // We'll print off each row of squares in a turn.
        for (int row = 0; row < numRows; row++) {
            for (int charInRow = 0; charInRow < 4; charInRow++) {
                if (charInRow == 0) {
                    System.out.print("+");
                } else {
                    System.out.print("|");
                }
                for (int col = 0; col < numColumns; col++) {
                    MazeSquare curSquare = this.getMazeSquare(row, col);
                    if (charInRow == 0) {
                        // We're in the first row of characters for this square - need to print
                        // top wall if necessary.
                        if (curSquare.hasTopWall()) {
                            System.out.print(getTopWallString());
                        } else {
                            System.out.print(getTopOpenString());
                        }
                    } else if (charInRow == 1 || charInRow == 3) {
                        // These are the interior of the square and are unaffected by
                        // the start/final state.
                        if (curSquare.hasRightWall()) {
                            System.out.print(getRightWallString());
                        } else {
                            System.out.print(getOpenWallString());
                        }
                    } else {
                        // We must be in the second row of characters.
                        // This is the row where start/finish should be displayed if relevant
                        if (startRow == row && startColumn == col) {
                            System.out.print("  S  ");
                        } else if (finishRow == row && finishColumn == col) {
                            System.out.print("  F  ");
                        } else if (showSolution && curSquare.getSolutionPiece()) {
                            System.out.print("  *  ");
                        } else {
                            System.out.print("     ");
                        }
                        if (curSquare.hasRightWall()) {
                            System.out.print("|");
                        } else {
                            System.out.print(" ");
                        }
                    }
                }
                System.out.print("\n");
            }
        }
        // Finally, print off the bottom of the maze, since that's not explicitly represented
        // by the squares. Printing off the bottom separately means we can think of each row as
        // consisting of four lines of text.
        printFullHorizontalRow(numColumns);
    }
    /**
     * Prints the very bottom row of characters for the bottom row of maze squares (which is always walls).
     * @param numColumns is the number of columns of bottom wall to print.
     */
    private static void printFullHorizontalRow(int numColumns) {
        System.out.print("+");
        for (int row = 0; row < numColumns; row++) {
            System.out.print(getTopWallString());
        }
        System.out.print("\n");
    }

      
    /**
     * Returns a String representing the bottom of a horizontal wall.
     */
    private static String getTopWallString() {
        return "-----+";
    }

    /**
     * Returns a String representing the bottom of a square without a
     * horizontal wall.
    */
    private static String getTopOpenString() {
        return "     +";
    }

    /**
     * Returns a String representing a left wall (for the interior of the row).
    */
    private static String getRightWallString() {
        return "     |";
    }

    /**
     * Returns a String representing no left wall (for the interior of the row).
    */
    private static String getOpenWallString() {
        return "      ";
    }

    /**
     * Gets the MazeSquare at the specified coordinates.
     * 
     * @param row The row index.
     * @param col The column index.
     * @return The MazeSquare at the specified coordinates.
     */
    public MazeSquare getMazeSquare(int row, int col) {
        return squares[row][col];
    }

    /**
     * private class for MazeSquare to go right.
     * @param row
     * @param column
     * @return
     */
    private MazeSquare goRight(int row, int column) {
        MazeSquare currentSquare = squares[row][column];
        MazeSquare toReturn = null;
        column++;
        if (isInRange(column, 0, numColumns) && !currentSquare.hasRightWall()) {
            currentSquare = squares[row][column];
            if (!currentSquare.getVisited()) {
                currentSquare.setVisited();
                currentSquare.setSolutionPiece();
                toReturn = currentSquare;
            }
        }
        return toReturn;
    }
    
    /**
     * private class for mazeSquare to go left.
     * @param row
     * @param column
     * @return
     */
    private MazeSquare goLeft(int row, int column) {
        MazeSquare currentSquare = squares[row][column];
        MazeSquare toReturn = null;
        column--;
        if (isInRange(column, 0, numColumns) && !currentSquare.hasLeftWall()) {
            currentSquare = squares[row][column];
            if (!currentSquare.getVisited()) {
                currentSquare.setVisited();
                currentSquare.setSolutionPiece();
                toReturn = currentSquare;
            }
        }
        return toReturn;
    }
    
    /**
     * private class to check a square at the top.
     * @param row
     * @param column
     * @return
     */
private MazeSquare goTop(int row, int column) {
    MazeSquare currentSquare = squares[row][column];
    row--; // Decrement row before checking bounds
    if (isInRange(row, 0, numRows) && !currentSquare.hasTopWall()) {
        currentSquare = squares[row][column];
        if (!currentSquare.getVisited()) {
            currentSquare.setVisited();
            currentSquare.setSolutionPiece();
            return currentSquare;
        }
    }
    return null;
}
    /**
     * private class to check a square at the bottom.
     * @param row
     * @param column
     * @return
     */
    private MazeSquare goBottom(int row, int column) {
        MazeSquare currentSquare = squares[row][column];
        row++; // Increment row before checking bounds
        if (isInRange(row, 0, numRows) && !currentSquare.hasBottomWall()) {
            currentSquare = squares[row][column];
            if (!currentSquare.getVisited()) {
                currentSquare.setVisited();
                currentSquare.setSolutionPiece();
                return currentSquare;
            }
        }
        return null;
    }
    /*
    * public stack class MazeSquare.
    * Returns a Stack representing the solution to the maze.
    * If no solution exists, an empty stack is returned.
    */
    public Stack<MazeSquare> getSolution() {
        Stack<MazeSquare> solution = new Stack<>();
        int row = startRow;
        int column = startColumn;
        MazeSquare currentSquare = squares[row][column];
        solution.push(currentSquare);
        currentSquare.setVisited();
        currentSquare.setSolutionPiece();

        while (!solution.isEmpty()) {
            if (solution.peek() == squares[finishRow][finishColumn]) {
                return solution;
            }
            currentSquare = solution.peek();
            row = currentSquare.getRow();
            column = currentSquare.getColumn();
            currentSquare = goTop(row, column);
            if (currentSquare != null) {
                solution.push(currentSquare);
                continue;
            }
            currentSquare = goBottom(row, column);
            if (currentSquare != null) {
                solution.push(currentSquare);
                continue;
            }
            currentSquare = goLeft(row, column);
            if (currentSquare != null) {
                solution.push(currentSquare);
                continue;
            }
            currentSquare = goRight(row, column);
            if (currentSquare != null) {
                solution.push(currentSquare);
                continue;
            }
            // If no valid moves are possible, return an empty stack
            solution.pop();
        }
        return solution;
    }
    /**
     * Main Method for testing the Maze Class.
     * Prints wether the maze is solvable or unsolvable.
     * Takes usage command args.
     * @param args
     */
    public static void main(String[] args) {
        if (args.length < 1 || args.length > 2) {
            System.out.println("Usage: java Maze mazefile [--solution]");
            return;
        }
        
        String mazeFile = args[0];
        boolean showSolution = false;
        
        if (args.length == 2 && args[1].equals("--solution")) {
            showSolution = true;
        }
    
        Maze maze = new Maze();
        if (maze.load(mazeFile)) {
            maze.print(false);
            if (showSolution) {
                Stack<MazeSquare> solutionStack = maze.getSolution();
                if (solutionStack.isEmpty()) {
                    System.out.println("Maze is unsolvable.");
                } else {
                    maze.print(true);
                    for (MazeSquare sqaure : solutionStack) {
                        System.out.println("(" + sqaure.getRow()+ "," + sqaure.getColumn() + ")");
                    }
                }
            }
        } else {
            System.out.println("Failed to load the maze.");
        }
    }
}