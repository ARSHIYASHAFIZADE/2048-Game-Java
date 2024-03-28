/* Represents each Block in the grid */
public class Block {
   private int val;        // The value of the Block
   private boolean isNew;  // If the Block is newly genereated or old
   
   /* Constructing a Block object with a specified
    * value.
    * @param val - The value of the block */
   public Block(int val) {
     
      
      this.val = val;
    
    // The Block is newly created by default
     this.isNew = true; 
     
   }
   
   /* Get the value of the Block
    * @return - The value of the Block it contains */
   public int GetVal() {
     
      return this.val;
    
   }
   
   /* Set the value of the Block
    * @param val - The new value that the Block will
    *              use. */
   public void SetVal(int val) {
     
    this.val = val;
    
   
     
   }
   
   /* Get if the Block is newly created or not
    * @return - True if the Block is newly generated
    *           False otherwise. */   
   public boolean GetIsNew() {
     
      return this.isNew;
   }
   
   /* Set if the Block is newly created or not
    * @param val - The value you want the Block to be 
    *              Set to true to indicate that the Block is new
    *              Set to false to indicate that the Block is old */   
   public void SetIsNew(boolean val){
     
      this.isNew = val;
   }
}