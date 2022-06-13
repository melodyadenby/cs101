
//CS-102 - Kettering University - 10/14/2020
//Melody Denby

import TennisDatabase.*;
import java.io.*;
import java.io.FileNotFoundException;
import java.util.*;

public class Assignment2 {

  // menuPrint() method to print the menu
  private static void menuPrint() {
    System.out.println(
        "\n---------------------------------------------------\n"
        +"Select an option my entering the option's number:\n"
         +"1. Print all Players\n"
         +"2. Print a Player\n"
         +"3. Print Matches\n"
         +"4. Print All Matches For A Player\n"
         +"5. Add a Player\n"
         +"6. Add a Match\n"
         +"7. Delete a Player\n"
         +"8. RESET Database\n"
         +"9. Save to file\n"
         +"\n0. Exit\n"
         +"Your choice? ");
  }

  public static void main(String args[]) {
    System.out.println(
        "\n------------------------------------------\n"
        +"Welcome to your personal Tennis Manager c:\n"
        +"------------------------------------------");

    // executing flag that shows the program status
    boolean executing = true;

    // A new instance of the tennis database used for the majority of operations
    TennisDatabase tdb = new TennisDatabase();

    // Get file input and load data
    System.out.println("Please enter an input file name\nEx- inputs.txt");
    try{
     Scanner userInput= new Scanner(System.in);
     String fileName= userInput.nextLine();
      tdb.loadFromFile(fileName);
    } catch(TennisDatabaseException err){
      System.out.println(err);
    }

    while (executing) {
    // A new scanner that allows the user to input values as prompted in the menu
    Scanner user = new Scanner(System.in); // Scans console for user input
      menuPrint();
      String input = user.nextLine();
      switch (input) {
      case "1": {
        System.out.println("All players:");
        for(int i=0; i<=tdb.getAllPlayers().length-1; i++){
            System.out.println(i+1+": "+tdb.getAllPlayers()[i].toString());
        }
        break;
      }
      case "2": {//print A player
        System.out.println("Enter a Player ID");
        String playerId = user.nextLine();
        System.out.println("All about player, "+playerId+":");
        System.out.println(tdb.getPlayer(playerId).playerToString());
      break;
      }
      case "3":
        System.out.println("All Tennis matches: ");
        for(int i=0; i<=tdb.getAllMatches().length-1; i++){
         System.out.println(tdb.getAllMatches()[i].toString());
        }
        break;
      case "4":
        System.out.println("Enter a Player ID");
        String playerId = user.nextLine();
        try{
        System.out.println("All Matches of "+playerId+":");
        for(int i=0; i<tdb.numMatches(); i++){
         if(tdb.getMatchesOfPlayer(playerId)[i]!=null){
            System.out.println(tdb.getMatchesOfPlayer(playerId)[i].toString()); // Print Matches of Player Inputed
            }
         }
        } catch(TennisDatabaseException err){
         System.out.println("FAIL! " +err);
         }
        break;
      case "5": // Asks user to fill in all data to add a player
        System.out.println("Let's add a player!");
        System.out.println("Enter the Player ID");
        String id = user.nextLine().toUpperCase();
        System.out.println("Enter First Name of "+id);
        String firstName = user.nextLine().toUpperCase();
        System.out.println("Enter "+firstName+"'s's Last Name");
        String lastName = user.nextLine().toUpperCase();
        System.out.println("Enter "+firstName+"'s Country");
        String country = user.nextLine().toUpperCase();
        System.out.println("Enter "+firstName+"'s Birth Year");
        Integer year = user.nextInt();
        try{
        tdb.insertPlayer(id, firstName, lastName, year, country); // Adds player
        } catch(TennisDatabaseException err){
         System.out.println(err);
         }
        break;
      case "6": // Asks user to fill in all data to add a match
        System.out.println("Let's add a match!\n");
        System.out.println("Enter the First Player's ID");
        String idPlayer1 = user.nextLine().toUpperCase();
        System.out.println("Enter the Second Player's ID");
        String idPlayer2 = user.nextLine().toUpperCase();
        System.out.println("Enter the Name of the Tournament");
        String tournamentInput = user.nextLine().toUpperCase();
        System.out.println("Enter the Score\nExample: 6-4,5-7,6-4,6-4");
        String scoreInput = user.nextLine().toUpperCase();
        System.out.println("Enter the Day of the Match");
        Integer dayInput = user.nextInt();
        System.out.println("Enter the Month of the Match");
        Integer monthInput = user.nextInt();
        System.out.println("Enter the Year of the Match");
        Integer yearInput = user.nextInt();
        try{
        tdb.insertMatch(idPlayer1, idPlayer2, yearInput, monthInput, dayInput, tournamentInput, scoreInput); // Adds match
        } catch(TennisDatabaseException err){
         System.out.println(err);
         }
        break;
      case "7"://delete a player
         System.out.println("Enter Player ID to delete:");
         String delId = user.nextLine();
         System.out.println("Are you sure you want to delete "+delId+"? [Y/N]");
         String answer=user.nextLine();
         if(answer.equalsIgnoreCase("Y")){
            tdb.deletePlayer(delId);
            System.out.println("Success!");
            break;
         } else if(answer.equalsIgnoreCase("N")){
            System.out.print("Cancelling...");
            break;
         } else{
            System.out.println("Could not understand input. Cancelling...");
            break;
         }
      case "8": 
         System.out.println("Are you sure you want to reset the database? [Y/N]");
         String resAnswer=user.nextLine();
         if(resAnswer.equalsIgnoreCase("Y")){
            tdb.reset();
            System.out.println("Success!");
            break;
         } else if(resAnswer.equalsIgnoreCase("N")){
            System.out.print("Cancelling...");
            break;
         } else{
            System.out.println("Could not understand input. Cancelling...");
            break;
         }
      case "9":
         System.out.println("What file would you like to save to?");
         String saveFile= user.nextLine();
         try{
            tdb.saveToFile(saveFile);
            System.out.println("Saved to "+saveFile+"!");
            break;
         } catch(TennisDatabaseException e){
            System.out.println("Failed to save!");
         break;
        }
      case "0":
        executing = false; // Stops the program
        break;
      default:
        System.out.println("Please choose an option that was listed..."); // If user selected an option not shown
        break;
      }
    }
    ;
  }

}
