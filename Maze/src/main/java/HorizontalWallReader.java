/*	
	Author: Brian Dozie Chong Ochulor
	Creation Date: 1 April 2022
	Modification Date: 14 April 2022
	Purpose: A subclass implementing the strategy pattern for file read.
             This class is responsible for assigning an horizontal wall
             to the maze based on the input read from file.
*/

package edu.curtin.app;

@SuppressWarnings("PMD.ConfusingTernary") // the values represent maze coordinates
public class HorizontalWallReader implements Content
{
    private String[][] map;

    /* SUBMODULE: HorizontalWallReader
	   IMPORT: inMap (2D ARRAY OF String)
	   EXPORT: none
	   ASSERTION: Constructor for HorizontalWallReader */
    public HorizontalWallReader(String[][] inMap)
    {
        this.map = inMap;
    }

    /* SUBMODULE: fillContent
	   IMPORT: row (Integer), col (Integer), misc (ARRAY OF String)
	   EXPORT: none
	   ASSERTION: Fills the 2D array with a horizontal wall */
    @Override
    public void fillContent(int row, int col, String[] misc)
    {
        // check if there is a pre-exisiting wall/door
        if(map[row - 1][col].equals(" ")) // if not filled
        {
            // check top right
            if(!map[row - 1][col + 2].equals(" ")) // filled
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
            else // not filled, just place normal wall -
            {
                map[row - 1][col + 2] = "\u2500";
            }
            
            // check top left
            if(!map[row - 1][col - 2].equals(" ")) // filled
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
            else // if top left is not filled, place normal wall -
            {
                map[row - 1][col - 2] = "\u2500";
            }

            map[row - 1][col] = "\u2500";
            map[row - 1][col - 1] = "\u2500";
            map[row - 1][col + 1] = "\u2500";  

            Maze.logger.info(() -> "Added horizontal wall above 2D array position (" + row + "," + col + ")");
        } 
        else // there is a pre-existing door/wall
        {
            Display.displayWarning("There is a pre-existing door/wall at 2D array coordinates (" +
                row + "," + (col - 2) + "). Unable to place another wall");
            Maze.logger.warning(() -> "Pre-existing door/wall at (" + (row - 1) + "," + col + ")");
        }
    }
}