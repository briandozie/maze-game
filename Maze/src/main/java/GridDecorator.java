/*	
	Author: Brian Dozie Chong Ochulor
	Creation Date: 1 April 2022
	Modification Date: 14 April 2022
	Purpose: Abstract class that implements the Grid interface
             used for the decorator pattern.
*/

package edu.curtin.app;

import java.util.*;

public abstract class GridDecorator implements Grid
{
    protected Grid next;

    /* SUBMODULE: GridDecorator
	   IMPORT: next (Grid)
	   EXPORT: none
	   ASSERTION: Constructor for GridDecorator */
    public GridDecorator(Grid next)
    {
        this.next = next;
    }

    /* SUBMODULE: getContent
	   IMPORT: none
	   EXPORT: (HashMap)
	   ASSERTION: Calls the getContent() method of the next linked object */
    @Override
    public Map<String,List<String>> getContent()
    {
        return next.getContent();
    }

    /* SUBMODULE: removeKey
	   IMPORT: none
	   EXPORT: none
	   ASSERTION: Calls the removeKey() method of the next linked object */
    @Override
    public void removeKey()
    {
        next.removeKey();
    }

    /* SUBMODULE: addContent
	   IMPORT: type (String), code (String)
	   EXPORT: none
	   ASSERTION: To be overriden by subclasses */
    @Override
    public abstract void addContent(String type, String code);
}