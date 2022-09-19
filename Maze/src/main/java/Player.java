/*	
	Author: Brian Dozie Chong Ochulor
	Creation Date: 1 April 2022
	Modification Date: 14 April 2022
	Purpose: A class representing the player, stores player's position and keys
*/

package edu.curtin.app;

import java.util.*;

public class Player
{
    private int row;
    private int col;
    private Map<Integer,Integer> keys;

    /* SUBMODULE: Player
	   IMPORT: none
	   EXPORT: none
	   ASSERTION: Constructor for Player */
    public Player()
    {
        row = 0;
        col = 0;
        keys = new HashMap<>();
    }

    /* SUBMODULE: setRow
	   IMPORT: inRow (Integer)
	   EXPORT: none
	   ASSERTION: Updates the value of row with inRow */
    public void setRow(int inRow)
    {
        row = inRow;
        Maze.logger.info(() -> "Player row in 2D array set to: " + inRow);
    }

    /* SUBMODULE: setCol
	   IMPORT: inCow (Integer)
	   EXPORT: none
	   ASSERTION: Updates the value of col with inCol */
    public void setCol(int inCol)
    {
        col = inCol;
        Maze.logger.info(() -> "Player column in 2D array set to: " + inCol);
    }

    /* SUBMODULE: getRow
	   IMPORT: none
	   EXPORT: none
	   ASSERTION: Returns the value of row */
    public int getRow()
    {
        return row;
    }

    /* SUBMODULE: getCol
	   IMPORT: none
	   EXPORT: none
	   ASSERTION: Returns the value of col */
    public int getCol()
    {
        return col;
    }

    /* SUBMODULE: collectKey
	   IMPORT: inKeyColour (Integer)
	   EXPORT: none
	   ASSERTION: Add the key collected to the keys map */
    public void collectKey(int inKeyColour)
    {
        if(keys.containsKey(inKeyColour))
        {
            int prevCount = keys.get(inKeyColour);
            keys.remove(inKeyColour);
            keys.put(inKeyColour, prevCount + 1);
        }
        else
        {
            keys.put(inKeyColour, 1);
        }

        Maze.logger.info(() -> "Player collected key with colour code: " + inKeyColour);
    }

    /* SUBMODULE: hasKey
	   IMPORT: inKey (String)
	   EXPORT: Boolean
	   ASSERTION: Returns true if player has the key, false otherwise */
    public boolean hasKey(String inKey)
    {
        return keys.containsKey(Integer.valueOf(inKey));
    }

    /* SUBMODULE: hasKeys
	   IMPORT: none
	   EXPORT: Boolean
	   ASSERTION: Returns true if player has any keys, false otherwise */
    public boolean hasKeys()
    {
        boolean result;

        if(keys.size() == 0)
        {
            result = false;
        }
        else
        {
            result = true;
        }

        return result;
    }

    /* SUBMODULE: keysToString
	   IMPORT: none
	   EXPORT: output (String)
	   ASSERTION: Displays the keys collected by the player */
    public String keysToString()
    {
        String output = "";
        
        for(Map.Entry<Integer,Integer> entry: keys.entrySet())
        {
            output += "\033[3" + entry.getKey() + "m\u2555" + "\033[m ";
            output += "x" + entry.getValue() + "  ";
        }

        return output;
    }
}