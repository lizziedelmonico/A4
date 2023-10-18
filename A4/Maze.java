import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.ArrayList;

/* The main class for the Maze that contains general information for the maze and reads files. */
public class Maze implements DisplayableMaze{
  /* An ArrayList containing an ArrayList of Characters which when put together is the maze */
  public ArrayList<ArrayList <Character>> maze;
  /* The contents of the maze */
  public MazeContents[][] mazeContents;
  /* The starting location of the maze */
  public MazeLocation Start;
  /* The location in the maze marking the end */
  public MazeLocation Finish;
  public int height;
  public int width;
  
  /* Creates a new maze by reading the files and converting the symbols to MazeContents*/
  public Maze(){
    String[] mazes = {"maze1", "maze2"};
    Scanner file = null;
    ArrayList<String> fileContents = new ArrayList<String>();

    try{
      file = new Scanner(new File(mazes[0]));
    } catch(FileNotFoundException x){
      System.err.println("Cannot locate file.");
      System.exit(-1);
    }

    while(file.hasNextLine()){
      String line = file.nextLine();
      fileContents.add(line);
    }

    int height = fileContents.size();
    int width = fileContents.get(0).length();

    MazeContents[][] mazeContents = new MazeContents[height][width];

    for(int i = 0; i < fileContents.size(); i++){
      String[] fileLine = fileContents.get(i).split("");
      for(int j = 0; j < fileLine.length; j++){
        if(fileLine[j].equals("#")){
          mazeContents[i][j] = MazeContents.WALL;
        } else if(fileLine[j].equals(".")){
          mazeContents[i][j] = MazeContents.OPEN;
        } else if(fileLine[j].equals("S")){
          mazeContents[i][j] = MazeContents.OPEN;
        } else if(fileLine[j].equals("F")){
          mazeContents[i][j] = MazeContents.OPEN;
          MazeLocation finishPoint = new MazeLocation(i, j);
        }
      }
    }

  }

  /** @return height of maze grid */
  public int getHeight(){
    return this.height;
  }

  /** @return width of maze grid */
  public int getWidth(){
    return this.width;
  }

  //** converts contents of maze to a 2D array of MazeContents */
  public void convert(){
    for(int i = 0; i < maze.size(); i++){
      ArrayList<Character> row = maze.get(i);
      this.mazeContents[i] = row.toArray(new MazeContents[row.size()]);
    }
  }

  /** @return contents of maze grid at row i, column j */
  public MazeContents getContents(int i, int j){
    if(i > mazeContents.length-1 && i < 0){
        return MazeContents.WALL;
    } else if(j > mazeContents.length-1 && j <0){
        return MazeContents.WALL;
    }
    return mazeContents[i][j];
  }

  /** @return location of maze start point */
  public MazeLocation getStart(){
    return Start;
  }

  /** @return location of maze finish point */
  public MazeLocation getFinish(){
    return Finish;
  }

  /**
   * Solves the maze using resursive methods
   * @param maze  The maze that is being solved
   * @param pos   The current position within the maze
   * @return      Whether or not the position can be moved forward
   */
  public static boolean mazeSolver(Maze maze, MazeLocation pos){
    /* the contents of the current position */
    MazeContents pos_info = maze.mazeContents[pos.getRow()][pos.getCol()];
  
    /* if current position is a wall, this is a problem. you shouldn't be able to go there -_- */
    if(pos_info == MazeContents.WALL){
      return false;
      /* if current position is visited, this is a problem. why are you back there? */
    } if(pos_info == MazeContents.VISITED){
      return false;
      /* if pos is finish line.. YIPPIEEEEEE */
    } if(pos.equals(maze.getFinish())){
      return true;
      /* if any neighbors are paths, YAY!! GO THERE!! */
    } else if(maze.mazeContents[pos.neighbor(MazeDirection.NORTH).getRow()][pos.neighbor(MazeDirection.NORTH).getCol()] == MazeContents.PATH || maze.mazeContents[pos.neighbor(MazeDirection.SOUTH).getRow()][pos.neighbor(MazeDirection.SOUTH).getCol()] == MazeContents.PATH || maze.mazeContents[pos.neighbor(MazeDirection.EAST).getRow()][pos.neighbor(MazeDirection.EAST).getCol()] == MazeContents.PATH || maze.mazeContents[pos.neighbor(MazeDirection.WEST).getRow()][pos.neighbor(MazeDirection.WEST).getCol()] == MazeContents.PATH) {
      return true;
    } else if(maze.mazeContents[pos.neighbor(MazeDirection.NORTH).getRow()][pos.neighbor(MazeDirection.NORTH).getCol()] == MazeContents.DEAD_END || maze.mazeContents[pos.neighbor(MazeDirection.SOUTH).getRow()][pos.neighbor(MazeDirection.SOUTH).getCol()] == MazeContents.DEAD_END || maze.mazeContents[pos.neighbor(MazeDirection.EAST).getRow()][pos.neighbor(MazeDirection.EAST).getCol()] == MazeContents.DEAD_END || maze.mazeContents[pos.neighbor(MazeDirection.WEST).getRow()][pos.neighbor(MazeDirection.WEST).getCol()] == MazeContents.DEAD_END){
     return false;
    } else{
      return false;
    }
    }
  }


