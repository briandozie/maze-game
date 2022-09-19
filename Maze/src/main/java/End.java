/*	
	Author: Brian Dozie Chong Ochulor
	Creation Date: 1 April 2022
	Modification Date: 14 April 2022
	Purpose: Decorates the grid square with an End point
*/

package edu.curtin.app;

public class End extends GridDecorator
{
    /* SUBMODULE: End
	   IMPORT: next (Grid)
	   EXPORT: none
	   ASSERTION: Constructor for End*/
    public End(Grid next)
    {
        super(next);
        addContent("E", "End");
    }

    /* SUBMODULE: addContent
	   IMPORT: type (String), misc (String)
	   EXPORT: none
	   ASSERTION: Calls the next linked object to add content to map */
    @Override
    public void addContent(String type, String misc)
    {
        next.addContent(type, misc);
    }
}