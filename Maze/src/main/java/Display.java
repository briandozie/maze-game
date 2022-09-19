/*	
	Author: Brian Dozie Chong Ochulor
	Creation Date: 1 April 2022
	Modification Date: 14 April 2022
	Purpose: Contains method to display to the terminal
*/

package edu.curtin.app;

public class Display
{
    /* SUBMODULE: displayMaze
	   IMPORT: map (2D ARRAY OF String)
	   EXPORT: none
	   ASSERTION: Displays the maze */
    public static void displayMaze(String[][] map)
    {
        System.out.println("\033[2J"); // clear screen

        // loop each element of 2d array to display maze
        for(int i = 0; i < map.length; i ++) // NOPMD
        {
            for(int j = 0; j < map[0].length; j++)
            {
                System.out.print(map[i][j]);
            }

            System.out.println();
        }
        
        System.out.println();
        Maze.logger.info(() -> "Refreshed maze display");
    }

    /* SUBMODULE: displayError
	   IMPORT: prompt (String)
	   EXPORT: none
	   ASSERTION: Displays an error message*/
    public static void displayError(String prompt)
    {
        System.out.println("\033[31mError:\033[m " + prompt);
    }

    /* SUBMODULE: displayFatalError
	   IMPORT: prompt (String)
	   EXPORT: none
	   ASSERTION: Displays a fatal error message */
    public static void displayFatalError(String prompt)
    {
        System.out.println("\033[31mFATAL ERROR:\033[m " + prompt);
    }

    /* SUBMODULE: displayKey
	   IMPORT: player (Player)
	   EXPORT: none
	   ASSERTION: Displays the keys picked up by player */
    public static void displayKey(Player player)
    {
        if(player.hasKeys())
        {
            System.out.println("Keys picked up: " +  player.keysToString());
        }
    }

    /* SUBMODULE: displayMessage
	   IMPORT: prompt (String)
	   EXPORT: none
	   ASSERTION: Displays the grid message */
    public static void displayMessage(String prompt)
    {
        System.out.println("Grid message: " + prompt);
    }

    /* SUBMODULE: displayWarning
	   IMPORT: prompt (String)
	   EXPORT: none
	   ASSERTION: Displays a warning message */
    public static void displayWarning(String prompt)
    {
        System.out.println("\033[33mWarning:\033[m " + prompt);
    }

    /* SUBMODULE: displayWin
	   IMPORT: none
	   EXPORT: none
	   ASSERTION: Displays the congratulation message */
    public static void displayWin()
    {
        System.out.println("\n\033[32mCongratulations!\033[m You have completed the maze successfully :)");
    }
}