/*	
	Author: Brian Dozie Chong Ochulor
	Creation Date: 1 April 2022
	Modification Date: 14 April 2022
	Purpose: A subclass that implements the strategy pattern for
             moving the player southwards
*/

package edu.curtin.app;

import java.util.*;

public class MoveSouth implements Move
{
    private String[][] map;
    private Map<String,Grid> gridMap;
    private Player player;
    
    /* SUBMODULE: MoveSouth
	   IMPORT: inMap (2D ARRAY OF String), inGridMap (HashMap), inPlayer (Player)
	   EXPORT: none
	   ASSERTION: Constructor for MoveSouth */
    public MoveSouth(String[][] inMap, Map<String,Grid> inGridMap, Player inPlayer)
    {
        this.map = inMap;
        this.gridMap = inGridMap;
        this.player = inPlayer;
    }

    /* SUBMODULE: movePlayer
	   IMPORT: none
	   EXPORT: none
	   ASSERTION: Checks if there is an obstacle. move player if possible */
    @Override
    public void movePlayer()
    {
        // check if there is an obstacle below
        if(map[player.getRow() + 1][player.getCol()].equals(" ")
        || map[player.getRow() + 1][player.getCol()].contains("\u2592")) // if no obstacle or is wall
        {
            String doorColour = "";
            
            if(map[player.getRow() + 1][player.getCol()].contains("\u2592"))
            {
                doorColour = gridMap.get(String.valueOf(player.getRow() + 1) + "," +
                String.valueOf(player.getCol())).getContent().get("DH").get(0);
            }

            if(map[player.getRow() + 1][player.getCol()].equals(" ")
            || player.hasKey(doorColour))
            {
                // clear symbol at original location
                map[player.getRow()][player.getCol()] = " "; 

                // move symbol to new location
                map[player.getRow() + 2][player.getCol()] = "P"; 

                Display.displayMaze(map);
                
                // check what is in the grid square below
                if(gridMap.containsKey(String.valueOf(player.getRow() + 2) + "," +
                String.valueOf(player.getCol())))
                {
                    Grid gridSquare;
                    gridSquare = gridMap.get(String.valueOf(player.getRow() + 2) + "," +
                        String.valueOf(player.getCol()));

                    GameController.checkForKey(gridSquare, player);
                    GameController.checkForMessage(gridSquare);     
                }
                else
                {
                    // there is nothing in the cell below, player can just move there
                    Display.displayKey(player);
                }

                // update player position
                player.setRow(player.getRow() + 2);

                Maze.logger.info(() -> "Player moved down a grid square.\n" +
                    "Current player position in 2D array: (" + player.getRow() +
                    "," + player.getCol() + ")");
            }
            else
            {
                Display.displayError("Can't move there! There's a door in the way!");
            }
        }
        else // its a wall
        {
            Display.displayError("Can't move there! There's a wall in the way!");
        }
    }
}