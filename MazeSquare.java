/**
* MazeSquare represents a single square within a Maze.
* @author Anna Rafferty
* @author Anya Vostinar
*/ 
public class MazeSquare {
    //Wall variables
    private boolean hasTopWall = false;
    private boolean hasRightWall = false;
    private boolean hasLeftWall = false;
    private boolean hasBottomWall = false;

    //Other variables
    private boolean visited = false;
    private boolean solutionPiece = false;
		
    //Location of this square in a larger maze.
    private int row;
    private int col;
		
    public MazeSquare(boolean top, boolean right, boolean left, boolean bottom, int r, int c) {
      hasTopWall = top;
      hasRightWall = right;
      hasLeftWall = left;
      hasBottomWall = bottom;
      row = r;
      col = c;
    }
		
    /**
     * Check if the square has a top wall.
     */
    public boolean hasTopWall() {
        return hasTopWall;
    }
		
    /**
     * check if the square has a right wall.
     */
    public boolean hasRightWall() {
        return hasRightWall;
    }
    		
    /**
     * check if the square has a left wall.
     */
    public boolean hasLeftWall() {
        return hasLeftWall;
    }

		
    /**
     * check if the square has a bottom wall.
     */
    public boolean hasBottomWall() {
        return hasBottomWall;
    }
		
    /**
     * Get the row of the square.
     */
    public int getRow() {
        return row;
    }
		
    /**
     * Get the column of the square.
     */
    public int getColumn() {
        return col;
    }

    /**
     * Mark the square as visited.
     * @param b 
     */
    public void setVisited() {
      visited = true;
    }

    /**
     * Mark the square as unvisited.
     */
    public void setUnvisited() {
      visited = false;
    }

    /**
     * Check if the square has been visited.
     */
    public boolean getVisited() {
      return visited;
    }

    /**
     * Check if the square is part of the solution path.
     */
    public boolean getSolutionPiece() {
      return solutionPiece;
    }

   /**
     * Set the square as part of the solution path.
     * 
     */
    public void setSolutionPiece() {
      solutionPiece = true;
    }

    /**
     * Reset the solution piece of the status of the game.
     */
    public void resetSolutionPiece() {
      solutionPiece = false;
    }
  }