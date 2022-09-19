/*	
	Author: Brian Dozie Chong Ochulor
	Creation Date: 1 April 2022
	Modification Date: 14 April 2022
	Purpose: A subclass implementing the strategy pattern for file read.
             This class is responsible for assigning a vertical door to the
             maze based on the input read from file.
*/

package edu.curtin.app;

import java.util.*;

// throwing custom exception, if statements values represent maze coordinates
@SuppressWarnings({"PMD.PreserveStackTrace", "PMD.ConfusingTernary"}) 
public class VerticalDoorReader implements Content
{
    private String[][] map;
    private Map<String,Grid> gridMap;

    /* SUBMODULE: VerticalDoorReader
	   IMPORT: inMap (2D ARRAY OF String), inGridMap (HashMap)
	   EXPORT: none
	   ASSERTION: Constructor for VerticalDoorReader */
    public VerticalDoorReader(String[][] inMap, Map<String,Grid> inGridMap)
    {
        this.map = inMap;
        this.gridMap = inGridMap;
    }

    /* SUBMODULE: fillContent
	   IMPORT: row (Integer), col (Integer), misc (ARRAY OF String)
	   EXPORT: none
	   ASSERTION: Fills the 2D array and map with a vertical door */
    @Override
    public void fillContent(int row, int col, String[] misc) throws MazeGameException
    {
        if(misc != null)
        {
            try
            {
                int code = Integer.parseInt(misc[3]); // check if colour is valid

                // check if there is a pre-existing door
                if(!map[row][col - 2].contains("\u2592")) // if there is no door
                {
                    if(code >= 1 && code <= 6)
                    {
                        // check top wall
                        if(!map[row - 1][col - 2].equals("\u2510")
                        && !map[row - 1][col - 2].equals("\u250c")
                        && !map[row - 1][col - 2].equals("\u253c")
                        && !map[row - 1][col - 2].equals("\u251c")
                        && !map[row - 1][col - 2].equals("\u2524")
                        && !map[row - 1][col - 2].equals("\u252c"))
                        {
                            if(map[row - 1][col - 2].equals("\u2500"))
                            {
                                //if left and right of top wall is filled
                                if(!map[row - 1][col - 3].equals(" ")
                                && !map[row - 1][col - 1].equals(" "))
                                {
                                    map[row - 1][col - 2] = "\u252c";
                                }
                                else if(!map[row - 1][col - 3].equals(" ")) // if left of top wall is filled
                                {
                                    map[row - 1][col - 2] = "\u2510";
                                }
                                else if(!map[row - 1][col - 1].equals(" ")) // if right of top wall is filled
                                {
                                    map[row - 1][col - 2] = "\u250c";
                                } 
                            }
                            else if(map[row - 1][col - 2].equals("\u2514")) 
                            {
                                map[row - 1][col - 2] = "\u251c";
                            }
                            else if(map[row - 1][col - 2].equals("\u2518")) 
                            {
                                map[row - 1][col - 2] = "\u2524";
                            }
                            else if(map[row - 1][col - 2].equals("\u2534"))
                            {
                                map[row - 1][col - 2] = "\u253c";
                            }
                            else // | or nothing, just put normal wall |
                            {
                                map[row - 1][col - 2] = "\u2502";
                            }
                        }

                        // check bottom wall
                        if(!map[row + 1][col - 2].equals("\u2514")
                        && !map[row + 1][col - 2].equals("\u2518")
                        && !map[row + 1][col - 2].equals("\u253c")
                        && !map[row + 1][col - 2].equals("\u251c")
                        && !map[row + 1][col - 2].equals("\u2524")
                        && !map[row + 1][col - 2].equals("\u2534"))
                        {
                            if(map[row + 1][col - 2].equals("\u2500")) // -
                            {
                                // if left and right of bottom wall is filled
                                if(!map[row + 1][col - 3].equals(" ")
                                && !map[row + 1][col - 1].equals(" "))
                                {
                                    map[row + 1][col - 2] = "\u2534";
                                }
                                else if(!map[row + 1][col - 3].equals(" ")) // if left of bottom wall is filled
                                {
                                    map[row + 1][col - 2] = "\u2518";
                                }
                                else if(!map[row + 1][col - 1].equals(" ")) // if right of bottom wall is filled
                                {
                                    map[row + 1][col - 2] = "\u2514";
                                }
                            }
                            else if(map[row + 1][col - 2].equals("\u2510"))
                            {
                                map[row + 1][col - 2] = "\u2524";
                            }
                            else if(map[row + 1][col - 2].equals("\u250c"))
                            {
                                map[row + 1][col - 2] = "\u251c";
                            }
                            else if(map[row + 1][col - 2].equals("\u252c"))
                            {
                                map[row + 1][col - 2] = "\u253c";
                            }
                            else // | or nothing, just put normal wall |
                            {
                                map[row + 1][col - 2] = "\u2502";
                            }
                        }

                        map[row][col - 2] = "\033[3" + code + "m\u2592" + "\033[m";
                        gridMap.put(row + "," + (col - 2), new VerticalDoor(misc[3], new GridSquare()));

                        Maze.logger.info(() -> "Added vertical door with colour code '" + misc[3] +
                            "' on the left of 2D array position (" + row + "," + col + ")");
                    }
                    else
                    {
                        Maze.logger.severe(() -> "Invalid door colour code '" + misc[3] + "'");
                        throw new MazeGameException("Invalid door colour code '" + misc[3] + "'");
                    }
                }
                else
                {
                    Maze.logger.warning(() -> "There is a pre-existing door at 2D array coordinates (" +
                    row + "," + (col - 2) + ")");
                    Display.displayWarning("There is a pre-existing door at 2D array coordinates (" +
                        row + "," + (col - 2) + ") Unable to place another door");
                }
            }
            catch(NumberFormatException e)
            {
                Maze.logger.severe(() -> "Invalid door colour code '" + misc[3] + "'");
                throw new MazeGameException("Invalid door colour code '" + misc[3] + "'");
            }
        }
        else
        {
            Maze.logger.severe(() -> "Missing colour code for a vertical door");
            throw new MazeGameException("Missing colour code for a vertical door");
        }
        


        
    }
}