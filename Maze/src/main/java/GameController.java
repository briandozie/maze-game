/*	
	Author: Brian Dozie Chong Ochulor
	Creation Date: 1 April 2022
	Modification Date: 14 April 2022
	Purpose: Receives user input and moves the player. This class is also
             responsible for handling game logic such as checking for keys,
             messages, and win condition.
*/

package edu.curtin.app;

import java.util.*;

public class GameController
{
    private Map<Character,Move> move;

    /* SUBMODULE: GameController
	   IMPORT: map (2D ARRAY OF String), gridMap (HashMap), player (Player)
	   EXPORT: none
	   ASSERTION: Constructor for this class. Also does initial check for win condition/grid items */
    public GameController(String[][] map, Map<String,Grid> gridMap, Player player)
    {
        move = new HashMap<>();

        move.put('n', new MoveNorth(map, gridMap, player));
        move.put('s', new MoveSouth(map, gridMap, player));
        move.put('e', new MoveEast(map, gridMap, player));
        move.put('w', new MoveWest(map, gridMap, player));

        // initial check to see if player's grid has any items
        Grid gridSquare;
        gridSquare = gridMap.get(String.valueOf(player.getRow()) + "," +
            String.valueOf(player.getCol()));

        if(gridSquare != null)
        {
            checkForKey(gridSquare, player);
            checkForMessage(gridSquare);
        }

        Maze.logger.info(() -> "Initial setup for game controller complete");
    }

    /* SUBMODULE: movePlayer
	   IMPORT: userInput (Character)
	   EXPORT: none
	   ASSERTION: Receive user input for player movement and calls the corresponding
                  strategy method for file read if applicable */
    public void movePlayer(char userInput)
    {
        if(move.get(Character.toLowerCase(userInput)) != null)
        {
            move.get(Character.toLowerCase(userInput)).movePlayer();
        }
        else
        {
            Maze.logger.warning(() -> "Invalid player movement input: " + userInput);
            Display.displayError("Use keys 'n', 's', 'e', 'w' to move");
        }
    }

    /* SUBMODULE: checkForKey
	   IMPORT: gridSquare (Grid), player (Player)
	   EXPORT: none
	   ASSERTION: checks whether the given grid square has key(s), collects all
                  the keys if so. */
    public static void checkForKey(Grid gridSquare, Player player)
    {   
        if(gridSquare.getContent() != null)
        {
            Map<String,List<String>> gridContent = gridSquare.getContent();

            if(gridContent.containsKey("K")) // check if grid has key(s)
            {
                for(String s: gridContent.get("K"))
                {
                    player.collectKey(Integer.parseInt(s)); // player collects the key
                }

                gridSquare.removeKey(); // remove all keys from grid square

                Maze.logger.info(() -> "Removed all keys in 2D array position (" +
                    player.getRow() + "," + player.getCol() + ")");
            }
        }

        Display.displayKey(player);
    }

    /* SUBMODULE: checkForMessage
	   IMPORT: gridSquare (Grid)
	   EXPORT: none
	   ASSERTION: Checks the given grid square for messages, displays them if so. */
    public static void checkForMessage(Grid gridSquare)
    {
        if(gridSquare.getContent() != null)
        {
            Map<String,List<String>> gridContent = gridSquare.getContent();
            
            if(gridContent.containsKey("M")) // check if grid has message(s)
            {
                for(String s: gridContent.get("M"))
                {
                    Display.displayMessage(s);

                    Maze.logger.info(() -> "Player has reached a grid square with message: '" +
                        s + "'.");
                }
            }
        }
    }

    /* SUBMODULE: checkForEnd
	   IMPORT: gridMap (HashMap), player (Player)
	   EXPORT: end (Boolean)
	   ASSERTION: Returns true if current player grid square is and end point, false otherwise */
    public static boolean checkForEnd(Map<String,Grid> gridMap, Player player)
    {
        boolean end = false;

        if(gridMap.containsKey(String.valueOf(player.getRow()) + "," +
        String.valueOf(player.getCol())))
        {
            if(gridMap.get(String.valueOf(player.getRow()) + "," +
                String.valueOf(player.getCol())).getContent().containsKey("E"))
            {
                end = true;
                Maze.logger.info(() -> "Player has reached an end grid square (" +
                    player.getRow() + "," + player.getCol() + ").");
            }
        }

        return end;
    }
}