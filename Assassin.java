/* Raymond Luu
 * John Mayor
 * TCSS143
 */

import java.util.*;

public class Assassin {

   public static void main(String[] args) {
  
      //Put some names into the kill ring
      ArrayList<String> names = new ArrayList<String>();
      names.add("Matt");
      names.add("Kyle");
      names.add("Sara");
      names.add("Rick");
      names.add("Rachel");
      //  Collections.shuffle(names); //randomize the order of the names in the kill ring
      AssassinManager manager = new AssassinManager(names.toArray(new String[0]));

      Scanner console = new Scanner(System.in);
    
      //Repeat this loop until the game is over
      while (!manager.isGameOver()) {
         //print the kill ring
         System.out.println("Current kill ring:");
         manager.printKillRing();
      
         //print the graveyard
         System.out.println("Current graveyard:");
         manager.printGraveyard();
         System.out.println();
      
         //prompt for the next victim
         System.out.print("Next victim? ");
         String name = console.nextLine().trim();
         if (manager.graveyardContains(name)) {
            System.out.println(name + " is already dead.");
         } else if (!manager.killRingContains(name)) {
            System.out.println("Unknown person.");
         } else {
            manager.kill(name);
         }
         System.out.println();
      }
    
      //announce the final results
      System.out.println("Game was won by " + manager.getWinner() + ".");
      System.out.println("Final graveyard:");
      manager.printGraveyard();
   }
}