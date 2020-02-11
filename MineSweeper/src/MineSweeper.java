//////////////////// ALL ASSIGNMENTS INCLUDE THIS SECTION /////////////////////
//
// Title:           MineSweeper
// Files:           MineSweeper.java
// Course:          CS200 Fall 2017
//
// Author:          Kunlun Wang
// Email:           bchen246@wisc.edu
// Lecturer's Name: Marc Renault
//
//////////////////// PAIR PROGRAMMERS COMPLETE THIS SECTION ///////////////////
//
// Partner Name:    (name of your pair programming partner)
// Partner Email:   (email address of your programming partner)
// Lecturer's Name: (name of your partner's lecturer)
// 
// VERIFY THE FOLLOWING BY PLACING AN X NEXT TO EACH TRUE STATEMENT:
//   ___ Write-up states that pair programming is allowed for this assignment.
//   ___ We have both read and understand the course Pair Programming Policy.
//   ___ We have registered our team prior to the team registration deadline.
//
///////////////////////////// CREDIT OUTSIDE HELP /////////////////////////////
//
// Students who get help from sources other than their partner must fully 
// acknowledge and credit those sources of help here.  Instructors and TAs do 
// not need to be credited here, but tutors, friends, relatives, room mates 
// strangers, etc do.  If you received no outside help from either type of 
// source, then please explicitly indicate NONE.
//
// Persons:         (identify each person and describe their help in detail)
// Online Sources:  (identify each URL and describe their assistance in detail)
//
/////////////////////////////// 80 COLUMNS WIDE ///////////////////////////////

import java.util.Random;
import java.util.Scanner;



/**
 * This class is about a mine sweeping game. Players should check the area, sweep mines and avoid touching mines.
 * @author Bohong Chen
 *
 */
public class MineSweeper {

    /**
     * This is the main method for Mine Sweeper game!
     * This method contains the within game and play again loops and calls
     * the various supporting methods.
     *  
     * @param args (unused)
     */
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
    	
        int width = 0;
        
        int height = 0;
        
        int row = 0;
        
        int column = 0;
        
        boolean again;
        
        String promptStr = "";
        
        System.out.println("Welcome to Mine Sweeper!");
        
        do 
        {
        again = false;
        
        System.out.print("What width of map would you like (" + Config.MIN_SIZE + " - " + Config.MAX_SIZE +"): ");
        
        width = promptUser(sc,promptStr,Config.MIN_SIZE,Config.MAX_SIZE);
        
        System.out.print("What height of map would you like (" + Config.MIN_SIZE + " - " + Config.MAX_SIZE +"): ");
        
        height = promptUser(sc,promptStr,Config.MIN_SIZE,Config.MAX_SIZE);
        
        char[][] userMap= new char[height][width];
        
        eraseMap(userMap);
        
        simplePrintMap(userMap);
        
        System.out.print("row: ");
        
        row = promptUser(sc,promptStr,1,height);
        
        System.out.print("column: ");
        
        column = promptUser(sc,promptStr,1,width);
        
        userMap[row-1][column-1] = ' ';
        
        for(int i = 0; i < userMap.length; i++)
        {
        	for(int j = 0; j < userMap[i].length; j++)
        	{
        		if( j != userMap[i].length-1 )
    			{
    			    System.out.print(" "+userMap[i][j]);
    			}
    			
    			else
    			{
    				System.out.println(" "+userMap[i][j]);
    			}

        	}
        }
        
        System.out.print("Would you like to play again (y/n)?");
        
        promptStr = sc.nextLine();
        
        promptStr = promptStr.trim();
        
        if((promptStr.substring(0,1)).equalsIgnoreCase("y") == true)
        {
        	again = true;
        }
        
        }while( again == true);
        
        System.out.print("Thank you for playing Mine Sweeper!");

    }


    /**
     * This method prompts the user for a number, verifies that it is between min
     * and max, inclusive, before returning the number.  
     * 
     * If the number entered is not between min and max then the user is shown 
     * an error message and given another opportunity to enter a number.
     * If min is 1 and max is 5 the error message is:
     *      Expected a number from 1 to 5.  
     * 
     * If the user enters characters, words or anything other than a valid int then 
     * the user is shown the same message.  The entering of characters other
     * than a valid int is detected using Scanner's methods (hasNextInt) and
     * does not use exception handling.
     * 
     * Do not use constants in this method, only use the min and max passed
     * in parameters for all comparisons and messages.
     * Do not create an instance of Scanner in this method, pass the reference
     * to the Scanner in main, to this method.
     * The entire prompt should be passed in and printed out.
     *
     * @param in  The reference to the instance of Scanner created in main.
     * @param prompt  The text prompt that is shown once to the user.
     * @param min  The minimum value that the user must enter.
     * @param max  The maximum value that the user must enter.
     * @return The integer that the user entered that is between min and max, 
     *          inclusive.
     */
    public static int promptUser(Scanner in, String prompt, int min, int max) {
    	  boolean valid = false;
          
          boolean isDigit = true;
          
          int promptNum = 0;
          
          while( valid == false )
          {
          	prompt=in.nextLine();
          	
          	isDigit = true;
          	
              for(int i = 0; i < prompt.length(); i++)
              {
              	
              	if(Character.isDigit(prompt.charAt(i)) == false)
              	{
              		isDigit = false;
              		break;
              	}
              	
              }
              
              if( isDigit == true )
              {
              	promptNum = Integer.parseInt(prompt);
              
              
              
                  if( promptNum >= min && promptNum <= max )
                  {
              	    valid = true;
                  }
                  
              }
              
              if( valid == false )
              {
              	System.out.println("Expected a number from " + min + " to " + max+ ".");
              	
              
              
              
              }
          }
          
          
          return promptNum;
        
    }

    /**
     * This initializes the map char array passed in such that all
     * elements have the Config.UNSWEPT character.
     * Within this method only use the actual size of the array. Don't
     * assume the size of the array.
     * This method does not print out anything. This method does not
     * return anything.
     * 
     * @param map An allocated array. After this method call all elements
     *      in the array have the same character, Config.UNSWEPT. 
     */
    public static void eraseMap(char[][] map) {
    	for(int i = 0; i < map.length; i++)
    	{
    		
    		for(int j = 0; j < map[i].length; j++)
    		{
    			map[i][j] = '.';
    		}
    		
    	}
    	
        return; 
    }    

    /**
     * This prints out a version of the map without the row and column numbers.
     * A map with width 4 and height 6 would look like the following: 
     *  . . . .
     *  . . . .
     *  . . . .
     *  . . . .
     *  . . . .
     *  . . . .
     * For each location in the map a space is printed followed by the 
     * character in the map location.
     * @param map The map to print out.
     */
    public static void simplePrintMap(char[][] map) {
    	for(int i = 0; i < map.length; i++)
    	{
    		
    		for(int j = 0; j < map[i].length; j++)
    		{
    			
    			if( j != map[i].length-1 )
    			{
    			    System.out.print(" "+map[i][j]);
    			}
    			
    			else
    			{
    				System.out.println(" "+map[i][j]);
    			}
    			
    		}
    	}
        
    		return; 
    }

    /**
     * This prints out the map. This shows numbers of the columns
     * and rows on the top and left side, respectively. 
     * map[0][0] is row 1, column 1 when shown to the user.
     * The first column, last column and every multiple of 5 are shown.
     * 
     * To print out a 2 digit number with a leading space if the number
     * is less than 10, you may use:
     *     System.out.printf("%2d", 1); 
     * 
     * @param map The map to print out.
     */
    public static void printMap(char[][] map) {
        return; //FIXME
    }

    /**
     * This method initializes the boolean mines array passed in. A true value for
     * an element in the mines array means that location has a mine, false means
     * the location does not have a mine. The MINE_PROBABILITY is used to determine
     * whether a particular location has a mine. The randGen parameter contains the
     * reference to the instance of Random created in the main method.
     * 
     * Access the elements in the mines array with row then column (e.g., mines[row][col]).
     * 
     * Access the elements in the array solely using the actual size of the mines
     * array passed in, do not use constants. 
     * 
     * A MINE_PROBABILITY of 0.3 indicates that a particular location has a
     * 30% chance of having a mine.  For each location the result of
     *      randGen.nextFloat() < Config.MINE_PROBABILITY 
     * determines whether that location has a mine.
     * 
     * This method does not print out anything.
     *  
     * @param mines  The array of boolean that tracks the locations of the mines.
     * @param randGen The reference to the instance of the Random number generator
     *      created in the main method.
     * @return The number of mines in the mines array.
     */
    public static int placeMines(boolean[][] mines, Random randGen) {
        return -99;
    }

    /**
     * This method returns the number of mines in the 8 neighboring locations.
     * For locations along an edge of the array, neighboring locations outside of 
     * the mines array do not contain mines. This method does not print out anything.
     * 
     * If the row or col arguments are outside the mines array, then return -1.
     * This method (or any part of this program) should not use exception handling.
     * 
     * @param mines The array showing where the mines are located.
     * @param row The row, 0-based, of a location.
     * @param col The col, 0-based, of a location.
     * @return The number of mines in the 8 surrounding locations or -1 if row or col
     *      are invalid.
     */
    public static int numNearbyMines( boolean [][]mines, int row, int col) {
        return -99;
    }

    /**
     * This updates the map with each unswept mine shown with the Config.HIDDEN_MINE
     * character. Swept mines will already be mapped and so should not be changed.
     * This method does not print out anything.
     * 
     * @param map  An array containing the map. On return the map shows unswept mines.
     * @param mines An array indicating which locations have mines.  No changes
     *      are made to the mines array.
     */
    public static void showMines(char[][] map, boolean[][] mines) {
        return; //FIXME
    }

    /**
     * Returns whether all the safe (non-mine) locations have been swept. In 
     * other words, whether all unswept locations have mines. 
     * This method does not print out anything.
     * 
     * @param map The map showing touched locations that is unchanged by this method.
     * @param mines The mines array that is unchanged by this method.
     * @return whether all non-mine locations are swept.
     */
    public static boolean allSafeLocationsSwept(char[][] map, boolean[][] mines) {
        return false; //FIXME

    }

    /**
     * This method sweeps the specified row and col.
     *   - If the row and col specify a location outside the map array then 
     *     return -3 without changing the map.
     *   - If the location has already been swept then return -2 without changing
     *     the map.
     *   - If there is a mine in the location then the map for the corresponding
     *     location is updated with Config.SWEPT_MINE and return -1.
     *   - If there is not a mine then the number of nearby mines is determined 
     *     by calling the numNearbyMines method. 
     *        - If there are 1 to 8 nearby mines then the map is updated with 
     *          the characters '1'..'8' indicating the number of nearby mines.
     *        - If the location has 0 nearby mines then the map is updated with
     *          the Config.NO_NEARBY_MINE character.
     *        - Return the number of nearbyMines.
     *        
     * @param map The map showing swept locations.
     * @param mines The array showing where the mines are located.
     * @param row The row, 0-based, of a location.
     * @param col The col, 0-based, of a location.
     * @return the number of nearby mines, -1 if the location is a mine, -2 if 
     * the location has already been swept, -3 if the location is off the map.
     */
    public static int sweepLocation(char[][] map, boolean[][] mines, int row, int col) {
        return -99; //FIXME
    }

    /**
     * This method iterates through all 8 neighboring locations and calls sweepLocation
     * for each. It does not call sweepLocation for its own location, just the neighboring
     * locations.
     * @param map The map showing touched locations.
     * @param mines The array showing where the mines are located.
     * @param row The row, 0-based, of a location.
     * @param col The col, 0-based, of a location.
     */
    public static void sweepAllNeighbors(char [][]map, boolean[][] mines, int row, int col) {
        return; //FIXME
    }
}
