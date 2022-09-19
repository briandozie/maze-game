/*	
	Author: Brian Dozie Chong Ochulor
	Creation Date: 1 April 2022
	Modification Date: 14 April 2022
	Purpose: A subclass that implements the strategy pattern for
             moving the player eastwards
*/

package edu.curtin.app;

import java.util.*;

public class MoveEast implements Move
{
    private String[][] map;
    private Map<String,Grid> gridMap;
    private Player player;
    
    /* SUBMODULE: MoveEast
	   IMPORT: inMap (2D ARRAY OF String), inGridMap (HashMap), inPlayer (Player)
	   EXPORT: none
	   ASSERTION: Constructor for MoveEast */
    public MoveEast(String[][] inMap, Map<String,Grid> inGridMap, Player inPlayer)
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
        // check if there is an obstacle on the right
        if(map[player.getRow()][player.getCol() + 2].equals(" ")
        || map[player.getRow()][player.getCol() + 2].contains("\u2592")) // if no obstacle or is door
        {
            String doorColour = "";
            
            if(map[player.getRow()][player.getCol() + 2].contains("\u2592"))
            {
                doorColour = gridMap.get(String.valueOf(player.getRow()) + "," +
                    String.valueOf(player.getCol() + 2)).getContent().get("DV").get(0);
            }

            if(map[player.getRow()][player.getCol() + 2].equals(" ")
            || player.hasKey(doorColour))
            {
                // clear symbol at original location
                map[player.getRow()][player.getCol()] = " "; 
        
                // move symbol to new location
                map[player.getRow()][player.getCol() + 4] = "P"; 

                Display.displayMaze(map);

                // check what is in the grid square to the right
                if(gridMap.containsKey(String.valueOf(player.getRow()) + "," +
                String.valueOf(player.getCol() + 4)))
                {
                    Grid gridSquare;
                    gridSquare = gridMap.get(String.valueOf(player.getRow()) + "," +
                        String.valueOf(player.getCol() + 4));

                    GameController.checkForKey(gridSquare, player);
                    GameController.checkForMessage(gridSquare);
                }
                else
                {
                    // there is nothing in the cell to the right, player can just move there
                    Display.displayKey(player);
                }

                // update player position
                player.setCol(player.getCol() + 4);

                Maze.logger.info(() -> "Player moved right a grid square.\n" +
                    "Current player position in 2D array: (" + player.getRow() +
                    "," + player.getCol() + ")");
            }
            else
            {
                Display.displayError("Can't move there! There's a door in the way!");
            }
        }
        else // it is a wall
        {
            Display.displayError("Can't move there! There's a wall in the way!");
        }
    }
}