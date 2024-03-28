   import java.util.Scanner;
   
   public class Main2048 {
     
     public static void main(String[] args){
       
       Scanner input = new Scanner(System.in);
       
       int option;
       String dir;
       int translate=0;
       int GridHeight,GridWidth,WinningVal,WinningBlock;
       System.out.println("Which game to play?");
       System.out.println("1. Default Game (2048 Winning value and 4 * 4 grid )");
       System.out.println("2. Custom Game");
       System.out.print("Your Option:");
       option = input.nextInt();
       input.nextLine(); // Consume the newline character left by nextInt()
       GameSystem game = new GameSystem();
       if(option == 2){
         
         System.out.print("Winning Block: ");
         WinningBlock = input.nextInt();
         System.out.print("Winning Val : ");
         WinningVal= input.nextInt();
         System.out.print("Grid Height: ");
         GridHeight= input.nextInt();
         System.out.print("Grid Width : ");
         GridWidth= input.nextInt();
         game = new GameSystem(WinningBlock, WinningVal, GridHeight, GridWidth);
        
       }
       System.out.println("Direction guide:");
       System.out.println("a - left");
       System.out.println("w - up");
       System.out.println("d - right");
       System.out.println("s - down");
       
       Displayer D = new Displayer();
       
       game.RandBlock();
       game.SetCurrPlayer(0);
       
       while (!game.IsGridFull() && !game.CheckWinner()) {
         // Display the game board and scores
         D.PrintGrid(game.GetGrid());
         D.PrintScores(game.GetPlayer(0), game.GetPlayer(1));
         
         // Loop until a valid direction is entered
         while (true) {
           System.out.print("Player " + game.GetCurrPlayer().GetId() + " turn: ");
           
           dir = input.nextLine();
           boolean validDir = true;
           if (dir.equals("a")) {
             translate = 1;
           } else if (dir.equals("w")) {
             translate = 2;
           } else if (dir.equals("d")) {
             translate = 3;
           } else if (dir.equals("s")) {
             translate = 4;
           }
           else {
             
             System.out.println("WARNING! Invalid direction");
             validDir = false;
           }
           
           if (validDir) {
             game.Move(translate);
             game.SwitchPlayer();
             break; // Exit the loop if a valid direction is entered
           }
         }
         
   // Reset translate to 0 after each input
         translate = 0;
         
         if (game.IsGridFull() && !game.CheckWinner()) {
            D.PrintGrid(game.GetGrid());
           System.out.println("Game over! The grid is full and there are no more moves.");
           break;
         } 
         else if (game.CheckWinner()) {
            D.PrintGrid(game.GetGrid());
           System.out.println("Congratulations! You won!");
           break;
         }
         else {
         System.out.println("ops invalid option");
         
         }
       }
     }
   }
