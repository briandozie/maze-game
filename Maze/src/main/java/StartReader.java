/*	
	Author: Brian Dozie Chong Ochulor
	Creation Date: 1 April 2022
	Modification Date: 14 April 2022
	Purpose: A subclass implementing the strategy pattern for file read.
             This class is responsible for assignning a start point to the
             maze based on the input read from file.
*/

package edu.curtin.app;

public class StartReader implements Content
{
    // variable map is accessed by fillContent method
    private String[][] map; // NOPMD
    private Player player;
    private boolean[] hasStart;

    /* SUBMODULE: StartReader
	   IMPORT: inMap (2D ARRAY OF String), inPlayer (Player), inHasStart (ARRAY OF Boolean)
	   EXPORT: none
	   ASSERTION: Constructor for StartReader */
    public StartReader(String[][] inMap, Player inPlayer, boolean[] inHasStart)
    {
        this.map = inMap;
        this.player = inPlayer;
        this.hasStart = inHasStart;
    }

    /* SUBMODULE: fillContent
	   IMPORT: row (Integer), col (Integer), misc (ARRAY OF String)
	   EXPORT: none
	   ASSERTION: Fills the 2D array and map with a start point */
    @Override
    public void fillContent(int row, int col, String[] misc)
    {
        if(!hasStart[0]) // NOPMD
        {
            map[row][col] = "P"; // set symbol in 2D array

            // set the player's start position
            player.setRow(row);
            player.setCol(col);

            hasStart[0] = true;

            Maze.logger.info(() -> "Start position in 2D array set to (" + row + "," + col + ")");
        }
        else
        {
            Display.displayWarning("Multiple start locations read. " + 
                "Ignored additional start location at 2D array coordinates (" + row + "," + col + ")");
            Maze.logger.warning(() -> "Ignored additional start location at 2D array coordinates (" +
                row + "," + col + ")");
        }
        
    }
}