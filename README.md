# Interactive Library System
CMP5332 Coursework implementing a library system in Java


## Checklist
| **Achieving a mark of 40% to maximum of 49%**  |  |
|:-|-|
| Add new Patrons (members) to the system. System should store at least the following information for each member: ID, Name, Phone Number and List of Books Borrowed.| [X] |
| List all Books stored in the system.| [X] |
| List all Patrons stored in the system.| [X] |
| Issue Books to Patrons. When a book is issued to a Patron a Loan object must be created holding a reference to the Book being issued, to the Patron borrowing the Book and the Due Date of the Loan. This object should be added to the Book and the book should be added to the Patron’s list of borrowed Books. | [X] |
| Return issued books. When a member returns a book the status of the returned book should be updated to reflect its availability. Also, the book must be removed from the Patron’s list of borrowed Books. | [X] |
| Save the status of the system to the backend storage (i.e. text file storage) when the system is closed. The library data should be stored in three different files (books.txt, patrons.txt and loans.txt). A sample format to save the different properties for each object is given in the Sample Prototype Application section above. When the system starts it should load the status of the library from the text files to the memory. | [X] |
| **Achieving a mark of 50% to maximum of 59%**  |  |
| Add a publisher property to the Book object and make the appropriate changes to the program to ensure that this information can be captured when a new Book is created. Also ensure that this information will be stored to and correctly loaded from the file storage. | [X] |
| Add an email property to the Patron object and make the appropriate changes to the program to ensure that this information can be captured when a new Patron is created. Also ensure that this information will be stored to and correctly loaded from the file storage. | [X] |
| Implement Unit Tests to validate and demonstrate that the above changes made to the Book and Patron classes work as expected. | [X] |
| Display a popup window that will show the Patron details if a Book is on loan. | [X] |
| List all Patrons and their details including the number of books they have on loan. | [X] |
| Display a popup window that will show the Books details if a Patron has Books on loan. | [X] |
| Display a popup window when the “Add” submenu of the “Patrons” menu item is selected. The popup should display a form that allows the addition of a new Patron to the system. | [X] |
| Extend the functionality of the library system to allow for storing data to the file storage after the execution of commands that change the state of the system (e.g. “addbook”, “renewbook”). If the system fails to store the data on the file storage due to an error (e.g. file is already in used or corrupted), the program must inform the user and rollback any changes made to the system prior to the error. You can change the file permission to “read-only” in order to test this functionality. | [ ] |
| **Achieving a mark of 70% to maximum of 79%**  | |
| Remove (hide) existing books from the system. When a book is removed, it should not appear in the books view. Instead of completely deleting a book, use a Boolean property in the Book class to indicate whether the book is deleted. Change the affected functions appropriately to return only the books that are not deleted. | [X] |
| Remove (hide) existing Patrons from the system. When a patron is removed, it should not appear in the patrons view. Instead of completely deleting a patron, use a Boolean property in the Patron class to indicate that the patron is deleted. Change the affected functions appropriately to return only the books that are not deleted. | [X] |
| Impose a limit on the maximum number of books that a patron can borrow and use that during issuing books for a patron. | [X] |
| Extend the implementation for the GUI application to add the Delete functionality for both Books and Patrons using the GUI. | [ ] |
| Add Javadoc documentation for the newly created methods. | [ ] |
| **Achieving a mark of 80% and over**| |
| Keep record of all loan history for Patrons. To implement this functionality you need to introduce a Boolean property in the Loan class to indicate whether the loan is terminated and a property that will hold the date that the book was returned. You have the flexibility to decide on how to complete the implementation of this functionality. | [ ] |
| Implement the Borrow functionality to borrow a book using the GUI. | [ ] |
| Implement the Renew functionality to allow for renewing a book loan using the GUI. | [ ] |
| Implement the Return functionality to return a book using the GUI. | [ ] |
