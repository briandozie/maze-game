/*	
	Author: Brian Dozie Chong Ochulor
	Creation Date: 1 April 2022
	Modification Date: 14 April 2022
	Purpose: A subclass implementing the strategy pattern for file read.
             This class is responsible for assigning a vertical wall to the
             maze based on the input read from file.
*/

package edu.curtin.app;

@SuppressWarnings("PMD.ConfusingTernary") // the values represent maze coordinates
public class VerticalWallReader implements Content
{
    private String[][] map;

    /* SUBMODULE: VerticalWallReader
	   IMPORT: inMap (2D ARRAY OF String), inGridMap (HashMap)
	   EXPORT: none
	   ASSERTION: Constructor for VerticalWallReader */
    public VerticalWallReader(String[][] inMap)
    {
        this.map = inMap;
    }

    /* SUBMODULE: fillContent
	   IMPORT: row (Integer), col (Integer), misc (ARRAY OF String)
	   EXPORT: none
	   ASSERTION: Fills the 2D array with a vertical wall */
    @Override
    public void fillContent(int row, int col, String[] misc)
    {
        // check if there is a pre-existing wall/door
        if(map[row][col - 2].equals(" "))
        {
            // check top wall
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

            // check bottom wall
            if(map[row + 1][col - 2].equals("\u2500"))
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
            
            map[row][col - 2] = "\u2502";
            Maze.logger.info(() -> "Added vertical wall on the left of 2D array position (" +
                row + "," + col + ")");
        }
        else // if there is pre-existing wall/door
        {
            Display.displayWarning("There is a pre-existing door/wall at 2D array coordinates (" +
                row + "," + (col - 2) + "). Unable to place another wall");
            Maze.logger.warning(() -> "Pre-existing door/wall at (" + row + "," + (col - 2) + ")");
        }
    }
}