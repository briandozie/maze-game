/*	
	Author: Brian Dozie Chong Ochulor
	Creation Date: 1 April 2022
	Modification Date: 14 April 2022
	Purpose: Decorates the grid square with a key
*/

package edu.curtin.app;

public class Key extends GridDecorator
{    
    /* SUBMODULE: Key
	   IMPORT: next (Grid)
	   EXPORT: none
	   ASSERTION: Constructor for Key */
    public Key(String colour, Grid next)
    {
        super(next);
        addContent("K", colour);
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