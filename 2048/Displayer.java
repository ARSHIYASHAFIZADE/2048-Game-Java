/* A class that handles all the printing of
 * the game. */
public class Displayer {
  
  public Displayer() {
    
  }
  
  /* Prints the grid and its content on the screen
   * @param grid - The Block object 2D array. */
  public void PrintGrid( Block[][] grid ) {
    
    
    String horizontalLine = "-----";
    
    for (int row = 0; row < grid.length; row++) {
      
      for (int col = 0; col < grid[row].length; col++) {
        System.out.print(horizontalLine);
        
      }
      
      System.out.println("-"); // print the end of row border
      
      for (int col = 0; col < grid[row].length; col++) {
        System.out.print("|");
        
        if (grid[row][col] != null&&grid[row][col].GetVal()!=0 ) {
          System.out.printf("%4d", grid[row][col].GetVal());
        }
        
        else {
          System.out.print("    ");
          
        }
        
      }
      
      System.out.println("|"); // end of block contents
      
    }
    
    for (int col = 0; col < grid[0].length; col++) {
      System.out.print(horizontalLine);
      
    }
    
    System.out.println("-"); // print the end of grid border
  }
  
  /* Prints the scores of the two players
   * @param p1 - First player's Player object
   * @param p2 - Second player's Player object */
  public void PrintScores( Player p1, Player p2 ) {
 
    System.out.println("================ SCORES ===============");
    System.out.println("|Player 0 score: " + p1.GetScore()+ " | "+ "Player 1 score: " + p2.GetScore()+"|");
    System.out.println("=======================================");
    
  }
}