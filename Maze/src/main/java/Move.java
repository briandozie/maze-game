/*	
	Author: Brian Dozie Chong Ochulor
	Creation Date: 1 April 2022
	Modification Date: 14 April 2022
	Purpose: Interface for the strategy pattern used for player movement
*/

package edu.curtin.app;

public interface Move
{
    /* SUBMODULE: movePlayer
	   IMPORT: none
	   EXPORT: none
	   ASSERTION: Move the player in the maze, to be overriden by subclasses */
    void movePlayer();
}