/*	
	Author: Brian Dozie Chong Ochulor
	Creation Date: 1 April 2022
	Modification Date: 14 April 2022
	Purpose: Interface for the strategy pattern used during file read
*/

package edu.curtin.app;

public interface Content
{
    /* SUBMODULE: fillContent
	   IMPORT: row (Integer), col (Integer), misc (ARRAY OF String)
	   EXPORT: none
	   ASSERTION: Fills the array and map container with maze objects,
                  to be overriden by subclasses */
    void fillContent(int row, int col, String[] misc) throws MazeGameException;
}