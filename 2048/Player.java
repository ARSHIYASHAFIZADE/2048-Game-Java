/* To keep track of a player's information */
public class Player {
  
  private int playerId;      // The player's ID
  private int score;         // The player's score
  
  /* Constructing a Player object with an assigned 
   * player ID
   * @param id - The ID given to the player */
  public Player( int id ) {
    
  this.playerId = id;
  //default score 0
  this.score = 0;
   
  }
  
  /* Get the player's value 
   * @return - The ID that belongs to this 
   *           Player object */
  public int GetId() {
    
    return playerId;
    
  }
  /* Set the player's score 
   * @param score - The new score that the player get */
  public void SetScore(int score) {
    //add the scores 
    this.score+= score;
    
  }
  /* Get the player's score
   * @return - Get the player's current score */
  public int GetScore() {
 //getter for score
    return this.score;
    
  }
}