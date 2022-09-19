/*	
	Author: Brian Dozie Chong Ochulor
	Creation Date: 1 April 2022
	Modification Date: 14 April 2022
	Purpose: A subclass implementing the strategy pattern for file read.
             This class is responsible for assigning a key to the
             maze based on the input read from file.
*/

package edu.curtin.app;

import java.util.*;

// throwing custom exception
// gridSquare is a reference
@SuppressWarnings({"PMD.PreserveStackTrace", "PMD.UnusedAssignment"})
public class KeyReader implements Content
{
    
    private String[][] map; // NOPMD
    private Map<String,Grid> gridMap;

    /* SUBMODULE: KeyReader
	   IMPORT: inMap (2D ARRAY OF String), inGridMap (HashMap)
	   EXPORT: none
	   ASSERTION: Constructor for KeyReader */
    public KeyReader(String[][] inMap, Map<String,Grid> inGridMap)
    {
        this.map = inMap;
        this.gridMap = inGridMap;
    }

    /* SUBMODULE: fillContent
	   IMPORT: row (Integer), col (Integer), misc (ARRAY OF String)
	   EXPORT: none
	   ASSERTION: Fills the 2D array and map with a key */
    @Override
    public void fillContent(int row, int col, String[] misc) throws MazeGameException
    {
        if(misc != null)
        {
            try
            {
                int code = Integer.parseInt(misc[3]);

                // check if colour is valid
                if(code >= 1 && code <= 6)
                {
                    map[row][col] = "\033[3" + code + "m\u2555" + "\033[m"; // set symbol in 2D array

                    // decorate the grid square with a key
                    if(gridMap.containsKey(String.valueOf(row) + "," + String.valueOf(col)))
                    {
                        Grid gridSquare;
                        gridSquare = gridMap.get(String.valueOf(row) + "," + String.valueOf(col)); 
                        gridSquare = new Key(misc[3], gridSquare); 
                    }
                    else
                    {
                        gridMap.put(row + "," + col, new Key(misc[3], new GridSquare()));
                    }

                    Maze.logger.info(() -> "Added key with colour code '" + misc[3] +
                        "' to 2D array position (" + row + "," + col);
                }
                else
                {
                    Maze.logger.severe(() -> "Invalid key colour code'" + misc[3] + "'");
                    throw new MazeGameException("Invalid key colour code '" + misc[3] + "'");
                }
            }
            catch(NumberFormatException e)
            {
                Maze.logger.severe(() -> "Invalid key colour code'" + misc[3] + "'");
                throw new MazeGameException("Invalid key colour code '" + misc[3] + "'");
            }
        }
        else
        {
            Maze.logger.severe(() -> "Missing colour code for a key");
            throw new MazeGameException("Missing colour code for a key");
        }


        
    }
}