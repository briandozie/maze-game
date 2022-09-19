/*	
	Author: Brian Dozie Chong Ochulor
	Creation Date: 1 April 2022
	Modification Date: 14 April 2022
	Purpose: Represents a grid square in the maze, to be decorated
             using the decorator pattern
*/

package edu.curtin.app;

import java.util.*;

public class GridSquare implements Grid
{
    private Map<String,List<String>> map;

    /* SUBMODULE: GridSquare
	   IMPORT: none
	   EXPORT: none
	   ASSERTION: Constructor for GridSquare */
    public GridSquare()
    {
        map = new HashMap<>();
    }

    /* SUBMODULE: addContent
	   IMPORT: type (String), misc (String)
	   EXPORT: none
	   ASSERTION: Adds the content of the grid square to a map container */
    @Override
    public void addContent(String type, String misc)
    {  
        if(map.containsKey(type))
        {
            List<String> prevValue = map.get(type);
            map.remove(type);

            prevValue.add(misc);
            map.put(type, prevValue);
        }
        else
        {
            map.put(type, new LinkedList<String>(List.of(misc)));
        }

        Maze.logger.info(() -> "Added item type '" + type + "' with value '" +
            misc + "' into map container in gridSquare");
    }

    /* SUBMODULE: getContent
	   IMPORT: map (HashMap)
	   EXPORT: none
	   ASSERTION: Returns the map container containing the contents of the grid square */
    @Override
    public Map<String,List<String>> getContent()
    {
        return map;
    }

    /* SUBMODULE: removeKey
	   IMPORT: none
	   EXPORT: none
	   ASSERTION: Removes all keys from the map container */
    @Override
    public void removeKey()
    {
        map.remove("K");
        Maze.logger.info(() -> "Removed all keys from map container in gridSquare");
    }
}