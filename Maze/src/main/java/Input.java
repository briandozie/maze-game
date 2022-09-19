/*	
	Author: Brian Dozie Chong Ochulor
	Creation Date: 1 April 2022
	Modification Date: 14 April 2022
	Purpose: Contains method to receive user input from terminal
*/

package edu.curtin.app;

import java.util.*;

@SuppressWarnings("PMD.CloseResource") // closing scanner would cause following inputs to not work
public class Input
{
    /* SUBMODULE: stringInput
       IMPORT: promptMessage (String)
       EXPORT: userInput (String)
       ASSERTION: "To receive and validate the user's string input." */
       public static String stringInput(String prompt)
       {
           String userInput; // declaring variable
           Scanner input = new Scanner(System.in);
   
           do
           {
               System.out.print(prompt); // display prompt message
               userInput = input.nextLine(); // receive user string input

               if (userInput == null || userInput.equals(""))
               {
                   // Invalid input message
                   System.out.println("Invalid input! Input cannot be blank! " +
                       "Please try again."); 
                   System.out.println();
               } 
           } while (userInput == null || userInput.equals(""));
           // ASSERTION: ((userInput != null) AND !(userInput.equals(""))

           return userInput;
       }

       /* SUBMODULE: charInput
	   IMPORT: prompt (String)
	   EXPORT: userInput (Character)
	   ASSERTION: Receive a character input from the user */
       public static char charInput(String prompt)
       {
            char userInput; // declaring variable
            Scanner input = new Scanner(System.in);

            do
            {
                System.out.print(prompt); // display prompt message
                userInput = input.next().charAt(0); // receive user string input

                if (userInput == ' ')
                {
                    // Invalid input message
                    System.out.println("Invalid input! Input cannot be blank! " +
                        "Please try again."); 
                    System.out.println();
                } 
            } while (userInput == ' ');
            // ASSERTION: ((userInput != null) AND !(userInput.equals(""))

            return userInput;
       }

       /* SUBMODULE: waitForEnter
	   IMPORT: none
	   EXPORT: none
	   ASSERTION: Waits for the user to press enter to continue executing the program */
       public static void waitForEnter()
       {
           Scanner input = new Scanner(System.in);
           System.out.print("\n\033[32mFile read complete.\033[m Press enter to start maze... ");
           input.nextLine();
       }
}