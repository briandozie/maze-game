/*	
	Author: Brian Dozie Chong Ochulor
	Creation Date: 1 April 2022
	Modification Date: 14 April 2022
	Purpose: A subclass implementing the strategy pattern for file read.
             This class is responsible for assigning an end point to the
             maze based on the input read from file.
*/

package edu.curtin.app;

import java.util.*;

// variable map and hasEnd are accessed by fillContent method
// gridSquare is a reference
@SuppressWarnings({"PMD.UnusedPrivateField", "PMD.UnusedAssignment"})
public class EndReader implements Content
{ 
    private String[][] map;
    private Map<String,Grid> gridMap;
    private boolean[] hasEnd;

    /* SUBMODULE: EndReader
	   IMPORT: inMap (2D ARRAY OF String), inGridMap (HashMap), inHasEnd (ARRAY OF Boolean)
	   EXPORT: none
	   ASSERTION: Constructor for EndReader */
    public EndReader(String[][] inMap, Map<String,Grid> inGridMap, boolean[] inHasEnd)
    {
        this.map = inMap;
        this.gridMap = inGridMap;
        this.hasEnd = inHasEnd;
    }

    /* SUBMODULE: fillContent
	   IMPORT: row (Integer), col (Integer), misc (ARRAY OF String)
	   EXPORT: none
	   ASSERTION: Fills the 2D array and map with an end point */
    @Override
    public void fillContent(int row, int col, String[] misc)
    {
        map[row][col] = "E"; // set symbol in 2D array

        // decorate the grid square with an end point
        if(gridMap.containsKey(String.valueOf(row) + "," + String.valueOf(col)))
        {
            Grid gridSquare;
            gridSquare = gridMap.get(String.valueOf(row) + "," + String.valueOf(col)); // NOPMD
            gridSquare = new End(gridSquare); // gridSquare is passed as parameter here
        }
        else
        {
            gridMap.put(row + "," + col, new End(new GridSquare()));
        }

        hasEnd[0] = true;

        Maze.logger.info(() -> "End position in 2D array set to (" + row + "," + col + ")");
    }
}