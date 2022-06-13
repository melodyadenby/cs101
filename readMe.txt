Melody Denby

Assignment 2 

   Assignment 2 is an extension of CS-102: Assignment 1 with 
added features. It maintains a tennis database containing 
tennis matches and tennis players. All data is stored sorted 
and the program allows user to manipulate data from a user 
interface. User can add, delete and even export data.

Explanation of JCF Class Chosen for TennisMatchesContainer Implementation:
   
   In the assignment, to store TennisMatches, I used a LinkedList instead 
of an Arraylist because manipulating a Linked list can be more efficient. 
In Assignment 2, data is frequently manipulated in TennisMatchContainer to 
add or delete match info. Deletion is more reliable in a linked list as, 
one player could result in either deleting no matches or several matches.