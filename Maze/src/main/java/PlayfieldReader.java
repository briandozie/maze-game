/*	
	Author: Brian Dozie Chong Ochulor
	Creation Date: 1 April 2022
	Modification Date: 14 April 2022
	Purpose: Contains methods to read and import maze data from a text file
*/

package edu.curtin.app;

import java.io.*;
import java.util.*;

// throwing custom exception, simplified logging statements used
@SuppressWarnings({"PMD.PreserveStackTrace", "PMD.GuardLogStatement"}) 
public class PlayfieldReader
{
    private String[][] map;
    private boolean[] hasStart;
    private boolean[] hasEnd;
    private Map<String,Content> content;

    /* SUBMODULE: PlayfieldReader
	   IMPORT: none
	   EXPORT: none
	   ASSERTION: Constructor for PlayfieldReader */
    public PlayfieldReader()
    {
        map = null;
        hasEnd = new boolean[]{false};
        hasStart = new boolean[]{false};
        content = new HashMap<>();
    }
    
    /* SUBMODULE: importMapFromFile
	   IMPORT: filename (String), inGridMap (HashMap), player (Player)
	   EXPORT: map (2D ARRAY OF String)
	   ASSERTION: Creates the maze map in the 2D array and map container from the input file */
    public String[][] importMapFromFile(String filename, Map<String,Grid> inGridMap, Player player) throws MazeGameException
    {
        try(BufferedReader reader = new BufferedReader(new FileReader(filename)))
        {
            String[] parts;
            int row, col;
            String line;
            
            line = reader.readLine();

            // check if file is empty
            if(line == null)
            {
                Maze.logger.severe(() -> "Input file '" + filename + "' is empty");
                throw new MazeGameException("File is empty");
            }

            // create the map 2d array
            createMap(line);
            addContentOptions(inGridMap, player);
            fillMapBorder();

            line = reader.readLine();

            while(line != null)
            {
                parts = line.split(" ");

                if(parts.length < 3)
                {
                    Maze.logger.severe("Invalid coordinates for '" + line + "'");
                    throw new MazeGameException("Invalid coordinates for '" + line + "'");
                }

                // check if row and column is valid
                if(validLocation(parts[1], parts[2]))
                {
                    row = getArrayRow(Integer.parseInt(parts[1]));
                    col = getArrayCol(Integer.parseInt(parts[2]));
                    
                    if(content.get(parts[0]) != null)
                    {
                        if(parts.length > 3)
                        {
                            content.get(parts[0]).fillContent(row, col, parts);
                        }
                        else
                        {
                            content.get(parts[0]).fillContent(row, col, null);
                        }
                    }
                    else
                    {
                        Maze.logger.severe("Invalid map code in line '" + line + "'");
                        throw new MazeGameException("Invalid map code in line '" + line + "'");
                    }
                }
                else
                {
                    Maze.logger.warning("Invalid coordinates for '" + line + "'");
                    Display.displayWarning("Invalid coordinates for '" + line + "'. Ignored the line.");
                }
                
                line = reader.readLine();
            }

            checkForStart(); // check for at start point
            checkForEnd(); // check for end point

            Maze.logger.info(() -> "Import maze from file complete");

            return map;
        }
        catch(IOException e)
        {
            Maze.logger.severe(() -> e.getMessage());
            throw new MazeGameException("Error during file reading - " + e.getMessage());
        }
    }

    /* SUBMODULE: createMap
	   IMPORT: line (String)
	   EXPORT: none
	   ASSERTION: Creates the 2D array for the maze */
    private void createMap(String line) throws MazeGameException
    {
        String[] parts = line.split(" ");
        try
        {
            int row, col;

            row = Integer.parseInt(parts[0]);
            col = Integer.parseInt(parts[1]);

            if(row > 0 && col > 0)
            {
                map = new String[(2 * row) + 1][(4 * col) + 1];

                // fill the map with blank spaces
                for(int i = 0; i < map.length; i ++)
                {
                    for(int j = 0; j < map[0].length; j++)
                    {
                        map[i][j] = " ";
                    }
                }
            }
            else
            {
                Maze.logger.severe(() -> "Invalid maze size '" + line + "'");
                throw new MazeGameException("Invalid maze size '" + line + "'");
            }   
        }
        catch(NumberFormatException | ArrayIndexOutOfBoundsException e)
        {
            Maze.logger.severe(() -> e.getMessage());
            throw new MazeGameException("Invalid maze size '" + line + "'");
        }
    }

    /* SUBMODULE: fillMapBorder
	   IMPORT: none
	   EXPORT: none
	   ASSERTION: Fills the walls on the border of the maze */
    private void fillMapBorder()
    {
        // fill the corners of the map
        map[0][0] = "\u250c"; // top left
        map[0][map[0].length - 1] = "\u2510"; // top right
        map[map.length - 1][0] = "\u2514"; // bottom left
        map[map.length - 1][map[0].length - 1] = "\u2518"; // bottom right
        
        // fill the top and bottom borders
        for(int i = 1; i < map[0].length - 1; i++)
        {
            map[0][i] = "\u2500";
            map[map.length - 1][i] = "\u2500";
        }

        // fill the left and right borders
        for(int i = 1; i < map.length - 1; i++)
        {
            map[i][0] = "\u2502";
            map[i][map[0].length - 1] = "\u2502";
        }
    }

    /* SUBMODULE: getArrayRow
	   IMPORT: row (Integer)
	   EXPORT: newRow (Integer)
	   ASSERTION: Convert the given row value to coordinates of 2D array */
    private int getArrayRow(int row)
    { 
        return ((2 * row) + 1);
    }

    /* SUBMODULE: getArrayCol
	   IMPORT: col (Integer)
	   EXPORT: newCOl (Integer)
	   ASSERTION: Convert the given column value to coordinates of 2D array */
    private int getArrayCol(int col)
    {
        int newCol = col + 2;
        for(int i = 0; i < col; i++)
        {
            newCol += 3;
        }

        return newCol;
    }

    /* SUBMODULE: addContentOptions
	   IMPORT: gridMap (HashMap)
	   EXPORT: player (Player)
	   ASSERTION: Puts the various strategy pattern subclasses into a map */
    private void addContentOptions(Map<String,Grid> gridMap, Player player)
    {
        content.put("WV", new VerticalWallReader(map));
        content.put("WH", new HorizontalWallReader(map));
        content.put("S", new StartReader(map, player, hasStart));
        content.put("M", new MessageReader(gridMap));
        content.put("E", new EndReader(map, gridMap, hasEnd));
        content.put("DV", new VerticalDoorReader(map, gridMap));
        content.put("DH", new HorizontalDoorReader(map, gridMap));
        content.put("K", new KeyReader(map, gridMap));
    }

    /* SUBMODULE: checkForStart
	   IMPORT: none
	   EXPORT: none
	   ASSERTION: Checks whether a start location was read from file, adds one if not */
    private void checkForStart() throws MazeGameException
    {
        // set the default start location to (0,0), if no start is read
        if(!hasStart[0])
        {
            content.get("S").fillContent(getArrayRow(0), getArrayCol(0), null);
            Display.displayWarning("No start location read. Start location is set to default");

            Maze.logger.warning(() -> "Start location missing from input file\n\t" + 
                "start position in 2D array set to default (" + getArrayRow(0) + "," +
                getArrayCol(0) + ")");
        }
    }

    /* SUBMODULE: checkForEnd
	   IMPORT: none
	   EXPORT: none
	   ASSERTION: Checks whether an end location was read from file, adds one if not */
    private void checkForEnd() throws MazeGameException
    {
        // set the default end location to the most bottom right grid, if no end is read
        if(!hasEnd[0])
        {
            content.get("E").fillContent(map.length - 2, map[0].length - 3, null);
            Display.displayWarning("No end location read. End location is set to default");

            Maze.logger.warning(() -> "End location missing from input file\n\t" + 
                "end position in 2D array set to default (" + (map.length - 2) + "," +
                (map[0].length - 3) + ")");
        }
    }

    /* SUBMODULE: validLocation
	   IMPORT: inRow (String), inCol (String)
	   EXPORT: valid (Boolean)
	   ASSERTION: Returns true if imported row and col are within maze bounds, false otherwise */
    private boolean validLocation(String inRow, String inCol) throws MazeGameException
    {
        boolean valid;
        
        try
        {
            int row = getArrayRow(Integer.parseInt(inRow));
            int col = getArrayCol(Integer.parseInt(inCol));
        
            if((row >= 0 && row < map.length) && (col >= 0 && col < map[0].length))
            {
                valid = true;
            }
            else
            {
                valid = false;
            }
        }
        catch(NumberFormatException e)
        {
            Maze.logger.severe(() -> "Invalid coordinates read from file (" + inRow + "," + inCol +")");
            throw new MazeGameException("Invalid coordinates read from file (" + inRow + "," + inCol +")");
        }

        return valid; 
    }
    
}