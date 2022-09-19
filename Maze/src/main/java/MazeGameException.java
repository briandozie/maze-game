/*	
	Author: Brian Dozie Chong Ochulor
	Creation Date: 1 April 2022
	Modification Date: 14 April 2022
	Purpose: Custom exception for the Maze application
*/

package edu.curtin.app;

public class MazeGameException extends Exception
{
    public MazeGameException(String message)
    {
        super(message);
    }
}