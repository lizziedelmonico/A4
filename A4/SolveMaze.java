/* Solves the maze using recursive methods */
class SolveMaze {
  /* The maze being used in the MazeSolver class */
  Maze maze;

  /* Constructor for the MazeSolver class that creates the maze used */
  public SolveMaze(Maze maze){
    this.maze = maze;
  }
  


  /* Creates a displayable maze and prints it out to the terminal */
  public static void main(String[] args) {
    Maze maze = new Maze();
    MazeViewer viewer = new MazeViewer(maze);
    SolveMaze solver = new SolveMaze(maze);
    Maze.mazeSolver(maze, maze.getStart());



  }
}