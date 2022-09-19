/*	
	Author: Brian Dozie Chong Ochulor
	Creation Date: 1 April 2022
	Modification Date: 14 April 2022
	Purpose: A subclass implementing the strategy pattern for file read.
             This class is responsible for assigning a message to the
             maze based on the input read from file.
*/

package edu.curtin.app;

import java.util.*;

public class MessageReader implements Content
{
    private Map<String,Grid> gridMap;

    /* SUBMODULE: MessageReader
	   IMPORT: inGridMap (HashMap)
	   EXPORT: none
	   ASSERTION: Constructor for MessageReader */
    public MessageReader(Map<String,Grid> inGridMap)
    {
        this.gridMap = inGridMap;
    }

    /* SUBMODULE: fillContent
	   IMPORT: row (Integer), col (Integer), misc (ARRAY OF String)
	   EXPORT: none
	   ASSERTION: Fills the 2D array and map with a message */
    @Override
    public void fillContent(int row, int col, String[] misc) throws MazeGameException
    {
        if(misc != null)
        {
            if(misc[3] != null)
            {
                String message = "";

                for(int i = 3; i < misc.length; i++)
                {
                    message += misc[i] + " ";
                }

                // remove last " "
                message = message.substring(0, message.length() - 1);
                
                // decorate the grid square with an end point
                if(gridMap.containsKey(String.valueOf(row) + "," + String.valueOf(col)))
                {
                    Grid gridSquare;
                    gridSquare = gridMap.get(String.valueOf(row) + "," + String.valueOf(col));
                    gridSquare = new Message(message, gridSquare); // NOPMD
                                                                // gridSquare is a reference
                }
                else
                {   
                    gridMap.put(row + "," + col, new Message(message, new GridSquare()));
                }
            }
            else
            {
                throw new MazeGameException("Message is null");
            }
        }
        else
        {
            Maze.logger.severe(() -> "Missing message details for a message");
            throw new MazeGameException("Missing message details for a message");
        }
    }
}