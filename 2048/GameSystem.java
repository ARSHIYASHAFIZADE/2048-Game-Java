import java.util.Random;

/* Manages the game and all of its attributes */
public class GameSystem {
  public final static int DEFAULT_GRID_HEIGHT =    4;
  public final static int DEFAULT_GRID_WIDTH  =    4;
  public final static int DEFAULT_WINNING_VAL = 2048;
  public final static int DEFAULT_WINNING_BKL = 2048;
  
  public final static int LEFT                =    0;
  public final static int UP                  =    1;
  public final static int RIGHT               =    2;
  public final static int DOWN                =    3;
  
  private Block[][] grid;                              // The grid of the game
  private Player[] allPlayer;                          // To keep track of the two players
  
  private int winningVal;                              // Value that must be reached to win the game.
  private int winningBlk;
  private Player currPlayer;                           // Keeps track of the current player
  
  //=================== CONSTRUCTOR =====================//
  /* The constructor to initialize the grid. */
  public GameSystem() {
    
    // make the grid with default size //
    grid = new Block [DEFAULT_GRID_HEIGHT][ DEFAULT_GRID_WIDTH];
     winningVal = DEFAULT_WINNING_VAL;
     winningBlk = DEFAULT_WINNING_BKL;
    allPlayer = new Player[2];
    allPlayer[0] = new Player(0);
    allPlayer[1] = new Player(1);
  }
  
  /* The constructor to customize the winning value.
   * @param winningBlk - The block that the player must reach 
   *                     to win the game.
   * @param winningVal - The value that the player must reach
   *                     to win the game.
   * @param height     - Height of the grid
   * @param width      - Width of the grid */
  public GameSystem(int winningBlk, int winningVal, int height, int width) {
    
      // let user to choose the grid //
      grid = new Block[height][width];
      
      // let user to choose the winning blocks //
      this.winningBlk = winningBlk;
      
      // let user to choose the winning value //
      this.winningVal = winningVal;
      allPlayer = new Player[2];
      allPlayer[0] = new Player(0);
      allPlayer[1] = new Player(1);
    
    if(height<4 && width<4){
      // if height or width is smaller than 4
      System.out.println("Invalid height or width");
      
    }
    
  }
  
  //================== PRIVATE METHODS ====================//
  
  
  /* Helper method for moving blocks to the left
   * @return the total points earned from combined blocks*/
  private int moveLeft() {
    
    int pointsEarned = 0; // variable to keep track of points earned from combined blocks

    // iterate over each row of the grid
    for (int i = 0; i < grid.length; i++) {
        int combinpos = 0; // position of the last combined block in the row
        // iterate over each column of the grid, starting from the second column
        for (int j = 1; j < grid[0].length; j++) {
            // if the current block is not null and its value is not 0
            if (grid[i][j] != null && grid[i][j].GetVal() != 0) {
                // move the block as far left as possible
                for (int k = j; k > combinpos; k--) {
                    // if the block to the left is null, move the current block there
                    if (grid[i][k - 1] == null) {
                        grid[i][k - 1] = new Block(0);
                        grid[i][k - 1].SetVal(grid[i][k].GetVal());
                        grid[i][k] = null;
                    }
                    // if the block to the left has the same value as the current block and hasn't been combin yet
                    else if (grid[i][k - 1].GetVal() == grid[i][k].GetVal() && combinpos != k - 1) {
                        // combin the blocks and update the points earned
                        grid[i][k - 1].SetVal(grid[i][k - 1].GetVal() * 2);
                        grid[i][k].SetVal(0);
                        pointsEarned += grid[i][k - 1].GetVal();
                        combinpos = k - 1;
                    }
                    // if the block to the left is not null and not combinable, stop moving left
                    else {
                        break;
                    }
                }
            }
        }
    }

    RandBlock(); // generate a new random block on the grid
    return (pointsEarned/2); // return the total points earned from combined blocks
}
  /* Helper method for moving blocks to the Right
   * @return the total points earned from combined blocks*/
  private int moveRight() {
    
    int pointsEarned = 0; // variable to keep track of points earned from merged blocks
    
    // iterate over each row of the grid
    for (int row = 0; row < grid.length; row++) {
      int combinPos = grid[0].length; // position of the last combined block in the row
      // iterate over each column of the grid, starting from the second to last column
      for (int col = grid[0].length - 2; col >= 0; col--) {
        // if the current block is not null and its value is not 0
        if (grid[row][col] != null && grid[row][col].GetVal() != 0) {
          // move the block as far right as possible
          for (int k = col; k < combinPos - 1; k++) {
            // if the block to the right is null, move the current block there
            if (grid[row][k + 1] == null) {
              grid[row][k + 1] = new Block(0);
              grid[row][k + 1].SetVal(grid[row][k].GetVal());
              grid[row][k] = null;
            }
            // if the block to the right is 0, move the current block there
            else if (grid[row][k + 1].GetVal() == 0) {
              grid[row][k + 1].SetVal(grid[row][k].GetVal());
              grid[row][k] = null;
            }
            // if the block to the right has the same value as the current block and hasn't been combined yet
            else if (grid[row][k + 1].GetVal() == grid[row][k].GetVal() && combinPos != k + 1) {
              // combin the blocks and update the points earned
              grid[row][k + 1].SetVal(grid[row][k + 1].GetVal() * 2);
              grid[row][k].SetVal(0);
              pointsEarned += grid[row][k + 1].GetVal();
              combinPos = k + 1;
            }
            // if the block to the right is not null and not combinable, stop moving right
            else {
              break;
            }
          }
        }
      }
    }
    
    RandBlock(); // generate a new random block on the grid
    return (pointsEarned/2); // return the total points earned from combined blocks
  }
  /* Helper method for moving blocks to the left
 * @return the total points earned from combined blocks*/
private int moveUp() {
    int pointsEarned = 0;
    
    // Loop through all columns
    for (int j = 0; j < grid[0].length; j++) {
        int combinpos = -1;
        
        // Loop through all rows, starting from the second row
        for (int i = 1; i < grid.length; i++) {
          
            // If the current block is not null and has a non-zero value
            if (grid[i][j] != null && grid[i][j].GetVal() != 0) {
              
                // Move the block up until it hits the top or another block
                for (int k = i; k > 0; k--) {
                    if (grid[k - 1][j] == null) {
                      
                        // If the space above is empty, move the block
                        grid[k - 1][j] = new Block(0);
                        grid[k - 1][j].SetVal(grid[k][j].GetVal());
                        grid[k][j] = null;
                    } else if (grid[k - 1][j].GetVal() == 0) {
                      
                        // If the block above has a value of 0, move the current block there
                        grid[k - 1][j].SetVal(grid[k][j].GetVal());
                        grid[k][j] = null;
                    } else if (grid[k - 1][j].GetVal() == grid[k][j].GetVal() && combinpos != k - 1) {
                      
                        // If the block above has the same value, combine the two blocks
                        grid[k - 1][j].SetVal(grid[k - 1][j].GetVal() * 2);
                        grid[k][j] = null;
                        pointsEarned += grid[k - 1][j].GetVal();
                        combinpos = k - 1;
                    } else {
                      
                        // If the block above is different, stop moving the current block
                        break;
                    }
                }
            }
        }
    }
    
    // Add a new random block to grid
    RandBlock();
    
    // Return the points earned from combined blocks
    return (pointsEarned/2);
}
 /* 
Helper method for moving blocks down
Returns the number of points earned in the move
*/
private int moveDown() {
  int pointsEarned = 0;
  for (int j = 0; j < grid[0].length; j++) {
    int combinPos = -1; // Move declaration inside column loop
    
    // Iterate over each row from the second to last to the first
    for (int i = grid.length - 2; i >= 0; i--) {
      
      // If a block is found in this cell, try to move it down
      if (grid[i][j] != null && grid[i][j].GetVal() != 0) {
        for (int k = i; k < grid.length - 1; k++) {
          
          // If the cell below is empty, move the block down
          if (grid[k + 1][j] == null) {
            grid[k + 1][j] = new Block(0);
            grid[k + 1][j].SetVal(grid[k][j].GetVal());
            grid[k][j] = null;
            
            // If the cell below has the same value as the current block, combin them
          } else if (grid[k + 1][j].GetVal() == grid[k][j].GetVal() && combinPos != k + 1) {
            grid[k + 1][j].SetVal(grid[k + 1][j].GetVal() * 2);
            grid[k][j] = null;
            pointsEarned += grid[k + 1][j].GetVal();
            combinPos = k + 1;
            
            // If the cell below is not empty and does not have the same value as the current block, stop trying to move the block down
          } else {
            break;
          }
        }
      }
    }
  }
  
  // Add a new block to the grid and return the number of points earned in the move
  RandBlock();
  return (pointsEarned/2);
}
//================== PUBLIC METHODS ====================//
/* Get the whole grid of the game
 * @return - The Block object 2D array */
public Block[][] GetGrid() {
  
  return grid;
}
/* Randomize a block and its value (between 2 or 4) and place it 
 * in the grid. Note: Must not randomize a position that already 
 * has a block. */
public void RandBlock() {
  
  // Create a new Random object
  Random r = new Random();
    
    // Set the initial value of num to 2
    int num = 2;
    
    // Randomly set num to 4 with 50% probability
    if (Math.random() < 0.5)
    {
        num = 4;
    }
    
    // Initialize a count variable to keep track of the number of empty spaces on the grid
    int Count = 0;
   
    // Loop through the grid and count the number of empty spaces
    for (int i = 0; i < grid.length; i++) {
        for (int j = 0; j < grid[i].length; j++) {
            if (grid[i][j] == null) {
                Count++;
            }
        }
    }
    
    // If there is at least one empty space on the grid
    if ( Count > 0) {
        // Choose a random empty space on the grid
        int emptyIndex = r.nextInt(Count);
        int currIndex = 0;
        // Loop through the grid again to find the chosen empty space
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                if (grid[i][j] == null) {
                    // If the current empty space is the chosen one, place a new block with value num
                    if (currIndex == emptyIndex) {
                        grid[i][j] = new Block(num);
                        return;
                    }
                    currIndex++;
                }
            }
        }
    }
}

/* Move all the blocks to the specified direction. If the direction is
   * invalid, it will not move any blocks.
   * @param dir - The direction that the user wants to move to.
   *              Refer to GameSystem global variables for direction
   *              values.
   * @return    - Returns points earned from this round of move. 
   *              Returns -1 if the direction is invalid, and blocks
   *              will not be moved. */
  public int Move(int dir) {
   
    // move blocks to the left//
    if (dir == 1) {
      currPlayer.SetScore(moveLeft());
      return 1;
    } 
    // move blocks to the rigth//
    else if (dir == 3) {
      currPlayer.SetScore( moveRight());
      return 1;
    }
    // move blocks to up//
    else if (dir == 2) {
      currPlayer.SetScore(moveUp());
      return 1;
    }
    // move blocks to down//
    else if (dir == 4) {
      currPlayer.SetScore(moveDown());
      return 1;
    }
    //if non of above statements then return -1  invalid direction//
      return -1; 
  }
  /* Set who will be the player turn
   * @param playerId - The ID of the player */
  public void SetCurrPlayer(int playerId ) {
    
    // set the current player inside the array for players//
    currPlayer = allPlayer[playerId];
    
  }
  /* Get the Player who is currently his/her turn
   * @return - The Player object of the player who is currently
   *           his/her turn */
  public Player GetCurrPlayer() {
    
    //return the current player getter method for private variable//
    return currPlayer;
    
  }
  /* Get the Player with the indicated player ID
   * @param playerId - The ID of the player 
   * @return         - The Player object of the player */
  public Player GetPlayer(int playerId ) {
    
    for (int i = 0; i < allPlayer.length; i++) {
      
      //check the id//
        if (allPlayer[i] != null && allPlayer[i].GetId() == playerId) {
          
          //return the players obj//
            return allPlayer[i];
        }
    }
    
    // just in case if player with the specified ID not found
    return null; 
}
  /* Switch player's turn. If it is player 0 turn, then it will
   * switch to player 1, and vice versa */
  public void SwitchPlayer() {
    
    //if player 0 then switch to 1//
    if (currPlayer.GetId() == 0) {
      SetCurrPlayer(1);
    } 
    
    //if player 1 then switch to 0//
    else {
      SetCurrPlayer(0);
    }
  }
  /* Check if the player wins or not by reaching the 
   * specified winning value.
   * @return - True if at least one of the blocks is equals to the wining value. 
   *           False otherwise. */
  public boolean CheckWinner() {
    //if the grid is winning value then finish the game in main method//
     for (int i = 0; i < grid.length; i++) {
        for (int j = 0; j < grid[0].length; j++) {
            if (grid[i][j] != null && grid[i][j].GetVal() == winningVal) {
                return true;
            }
        }
    }
    
    return false;
}
  /* Check if the grid is full or not
   * @return - True if the grid has no more empty blocks
   *           False otherwise. */
  public boolean IsGridFull() {
    
    //if all the grid is full and there is no null then gameover//
    for (int i = 0; i < DEFAULT_GRID_HEIGHT; i++) {
      for (int j = 0; j < DEFAULT_GRID_WIDTH; j++) {
        if (grid[i][j] == null || grid[i][j].GetVal() == 0) {
          return false;
        }
      }
    }
    return true;
  }
}