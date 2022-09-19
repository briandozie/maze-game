/*	
	Author: Brian Dozie Chong Ochulor
	Creation Date: 1 April 2022
	Modification Date: 14 April 2022
	Purpose: A subclass implementing the strategy pattern for file read.
             This class is responsible for assigning a horizontal door to
             the maze based on the input read from file.
*/

package edu.curtin.app;

import java.util.*;

 // the values represent maze coordinates
@SuppressWarnings("PMD.ConfusingTernary")
public class HorizontalDoorReader implements Content
{
    private String[][] map;
    private Map<String,Grid> gridMap;

    /* SUBMODULE: HorizontalDoorReader
	   IMPORT: inMap (2D ARRAY OF String), inGridMap (HashMap)
	   EXPORT: none
	   ASSERTION: Constructor for HorizontalDoorReader */
    public HorizontalDoorReader(String[][] inMap, Map<String,Grid> inGridMap)
    {
        this.map = inMap;
        this.gridMap = inGridMap;
    }

    /* SUBMODULE: fillContent
	   IMPORT: row (Integer), col (Integer), misc (ARRAY OF String)
	   EXPORT: none
	   ASSERTION: Fills the 2D array and map with a horizontal door */
    @Override
    public void fillContent(int row, int col, String[] misc) throws MazeGameException
    {
        if(misc != null)
        {
            try
            {
                int code = Integer.parseInt(misc[3]); // check if colour is valid

                // check if there is a pre-existing door
                if(!map[row - 1][col].contains("\u2592")) // if not filled
                {      
                    if(code >= 1 && code <= 6)
                    {    
                        // check top right
                        if(!map[row - 1][col + 2].equals(" ")) // filled
                        {
                            if(!map[row - 1][col + 2].equals("\u253c")
                            && !map[row - 1][col + 2].equals("\u2524")
                            && !map[row - 1][col - 2].equals("\u2510")
                            && !map[row - 1][col - 2].equals("\u2518")
                            && !map[row - 1][col - 2].equals("\u2534")
                            && !map[row - 1][col - 2].equals("\u252c"))
                            {
                                if(map[row - 1][col + 2].equals("\u2502")) // if top right is |
                                {
                                    // if above and below top right is filled
                                    if(!map[row - 2][col + 2].equals(" ")
                                    && !map[row][col + 2].equals(" "))
                                    {
                                        map[row - 1][col + 2] = "\u2524";
                                    }
                                    else if(!map[row - 2][col + 2].equals(" ")) // if above top right is filled
                                    {
                                        map[row - 1][col + 2] = "\u2518";
                                    }
                                    else if(!map[row][col + 2].equals(" ")) // if below top right is filled
                                    {
                                        map[row - 1][col + 2] = "\u2510";
                                    }
                                }
                                else if(map[row - 1][col + 2].equals("\u2514"))
                                {
                                    map[row - 1][col + 2] = "\u2534";
                                }
                                else if(map[row - 1][col + 2].equals("\u250c"))
                                {
                                    map[row - 1][col + 2] = "\u252c";
                                }
                                else if(map[row - 1][col + 2].equals("\u251c"))
                                {
                                    map[row - 1][col + 2] = "\u253c";
                                }
                                else // just place a normal wall -
                                {
                                    map[row - 1][col + 2] = "\u2500";
                                } 
                            }
                        }
                        else // if top right is not filled, place normal wall -
                        {
                            map[row - 1][col + 2] = "\u2500";
                        }
                            
                        
                        // check top left
                        if(!map[row - 1][col - 2].equals(" ")) // filled
                        {                        
                            if(!map[row - 1][col - 2].equals("\u253c")
                            && !map[row - 1][col - 2].equals("\u251c")
                            && !map[row - 1][col - 2].equals("\u250c")
                            && !map[row - 1][col - 2].equals("\u2514")
                            && !map[row - 1][col - 2].equals("\u2534")
                            && !map[row - 1][col - 2].equals("\u252c"))
                            {
                                if(map[row - 1][col - 2].equals("\u2502")) // if top left is |
                                {
                                    // if above and below top left is filled
                                    if(!map[row - 2][col - 2].equals(" ")
                                    && !map[row][col - 2].equals(" "))
                                    {
                                        map[row - 1][col - 2] = "\u251c";
                                    }
                                    else if(!map[row - 2][col - 2].equals(" ")) // if above top left is filled
                                    {
                                        map[row - 1][col - 2] = "\u2514";
                                    }
                                    else if(!map[row][col - 2].equals(" ")) // if below top left is filled
                                    {
                                        map[row - 1][col - 2] = "\u250c";
                                    }
                                }
                                else if(map[row - 1][col - 2].equals("\u2518"))
                                {
                                    map[row - 1][col - 2] = "\u2534";
                                }
                                else if(map[row - 1][col - 2].equals("\u2510"))
                                {
                                    map[row - 1][col - 2] = "\u252c";
                                }
                                else if(map[row - 1][col - 2].equals("\u2524"))
                                {
                                    map[row - 1][col - 2] = "\u253c";
                                }
                                else // place a normal wall -
                                {
                                    map[row - 1][col - 2] = "\u2500";
                                }
                            }
                        }
                        else // if top left is not filled, place normal wall -
                        {
                            map[row - 1][col - 2] = "\u2500";
                        }                    
                        
                        map[row - 1][col] = "\033[3" + code + "m\u2592" + "\033[m";
                        map[row - 1][col - 1] = "\033[3" + code + "m\u2592" + "\033[m";
                        map[row - 1][col + 1] = "\033[3" + code + "m\u2592" + "\033[m";

                        gridMap.put((row - 1) + "," + col, new HorizontalDoor(misc[3], new GridSquare()));
                        Maze.logger.info(() -> "Added horizontal door with colour code '" + misc[3] +
                            "' above the 2D array position (" + row + "," + col + ")");
                    }
                    else
                    {
                        Maze.logger.severe(() -> "Invalid door colour code '" + misc[3] + "'");
                        throw new MazeGameException("Invalid door color '" + misc[3] + "'");
                    }  
                }
                else
                {
                    Maze.logger.warning(() -> "There is a pre-existing door at 2D array coordinates (" +
                    (row - 1) + "," + col + ")");
                    Display.displayWarning("There is a pre-existing door at 2D array coordinates (" +
                        (row - 1) + "," + col + ") Unable to place another door");
                }
            }
            catch(NumberFormatException e)
            {
                // throwing custom exception
                throw new MazeGameException("Invalid door color '" + misc[3] + "'"); // NOPMD
            }
        }
        else
        {
            Maze.logger.severe(() -> "Missing colour code for a horizontal door");
            throw new MazeGameException("Missing colour code for a horizontal door");
        }
    }
}