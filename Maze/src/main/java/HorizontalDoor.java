/*	
	Author: Brian Dozie Chong Ochulor
	Creation Date: 1 April 2022
	Modification Date: 14 April 2022
	Purpose: Decorates the grid square with an horizontal door above
*/

package edu.curtin.app;

public class HorizontalDoor extends GridDecorator
{
    /* SUBMODULE: HorizontalDoor
	   IMPORT: colour (String), next (Grid)
	   EXPORT: none
	   ASSERTION: Constructor for HorizontalDoor */
    public HorizontalDoor(String colour, Grid next)
    {
        super(next);
        addContent("DH", colour);
    }

    /* SUBMODULE: addContent
	   IMPORT: type (String), colour (String)
	   EXPORT: none
	   ASSERTION: Calls the next linked object to add content to map */
    @Override
    public void addContent(String type, String colour)
    {
        next.addContent(type, colour);
    }
}