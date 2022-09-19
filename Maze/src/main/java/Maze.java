/*	
	Author: Brian Dozie Chong Ochulor
	Creation Date: 1 April 2022
	Modification Date: 14 April 2022
	Purpose: Main driver class for maze game. Contains main method and makes
             method calls to other classes to execute the maze game.
*/

package edu.curtin.app;

import java.util.*;
import java.util.logging.Logger;
//import java.util.logging.Level;

// class field is used for logging, simplified logging statements are used
@SuppressWarnings({"PMD.FieldNamingConventions", "PMD.GuardLogStatement"})
public class Maze
{
    // logger variable
    public static final Logger logger =
        Logger.getLogger(Maze.class.getName());

    public static void main(String[] args)
    {
        // declaring variables
        PlayfieldReader playfield = new PlayfieldReader();
        Player player = new Player();
        Map<String,Grid> gridMap = new HashMap<>();
        char userInput;
        String filename;

        // Get input filename from user input
        filename = Input.stringInput("Enter input file name: ");
        logger.info(() -> "Receive name of input file from user: " + filename);
        
        try
        {
            // load maze data from file
            String[][] map = playfield.importMapFromFile(filename, gridMap, player);
            Input.waitForEnter();
            Display.displayMaze(map); // display maze

            // setup game controller
            GameController game = new GameController(map, gridMap, player);

            while(!GameController.checkForEnd(gridMap, player))
            {
                // receive user movement input
                userInput = Input.charInput("\nEnter 'n', 's', 'e', 'w' to move: ");
                logger.info("Receive user input for player movement: " + userInput);

                game.movePlayer(userInput); // move the player
            }

            Display.displayWin(); // display win message
        }
        catch(MazeGameException e)
        {
            logger.severe(() -> e.getMessage());
            Display.displayFatalError(e.getMessage() + "\n\nProgram terminated");
        }
    }
}
