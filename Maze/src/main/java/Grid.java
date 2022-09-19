/*	
	Author: Brian Dozie Chong Ochulor
	Creation Date: 1 April 2022
	Modification Date: 14 April 2022
	Purpose: Interface for the decorator pattern
*/

package edu.curtin.app;

import java.util.*;

public interface Grid
{
    /* SUBMODULE: addContent
	   IMPORT: type (String), misc (String)
	   EXPORT: none
	   ASSERTION: Adds an item to the map container in the grid square class,
                  to be overriden by subclasses */
    void addContent(String type, String misc);

    /* SUBMODULE: getContent
	   IMPORT: none
	   EXPORT: (HashMap)
	   ASSERTION: Returns the map container containing the content of a grid square,
                  to be overriden by subclasses */
    Map<String,List<String>> getContent();

    /* SUBMODULE: removeKey
	   IMPORT: none
	   EXPORT: none
	   ASSERTION: Removes all the keys from the grid square,
                  to be overriden by subclasses */
    void removeKey();
}