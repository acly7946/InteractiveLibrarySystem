package bcu.cmp5332.librarysystem.commands;

import bcu.cmp5332.librarysystem.main.LibraryException;
import bcu.cmp5332.librarysystem.model.Library;

import java.time.LocalDate;

/**
 *
 * The Command interface models an action that occurs as a result of a command
 * given by the user.
 *
 * <p>
 * The interface provides the {@link execute execute(Library, LocalDate)} function which must be
 * implemented by all the objects that implement the Command interface.
 * </p>
 *
 * <p>
 * In addition, all classes that implement this interface, can have a
 * constructor to initialise additional objects (if needed) for the execution of
 * the command. See {@link AddBook} as an example of a class that needs a constructor
 * and {@link ListBooks} as an example of one that doesn't.
 * </p>
 *
 * @see execute execute(Library, LocalDate)
 * @see AddBook
 * @see ListBooks
 */
public interface Command {

	/**
	 * The message to be shown to the user when the help command is given.
	 * <p>
	 * See {@link Help} command for more details.
	 * </p>
	 *
	 * @see Help
	 */
	public static final String HELP_MESSAGE = "Commands:\n"
			+ "\tlistbooks                       print all books\n"
			+ "\tlistpatrons                     print all patrons\n"
			+ "\taddbook                         add a new book\n"
			+ "\taddpatron                       add a new patron\n"
			+ "\tshowbook [book id]              show book details\n"
			+ "\tshowpatron [patron id]          show patron details\n"
			+ "\tborrow [patron id] [book id]    borrow a book\n"
			+ "\tremovebook [book id]            remove a book\n"
			+ "\tremovepatron [patron id]        remove a patron\n"
			+ "\trenew [patron id] [book id]     renew a book\n"
			+ "\treturn [patron id] [book id]    return a book\n"
			+ "\tloadgui                         loads the GUI version of the app\n"
			+ "\thelp                            prints this help message\n"
			+ "\texit                            exits the program";

	/**
	 * The function that executes the Command given by the user.
	 *
	 * <p>
	 * All classes that implement the Command interface must provide an
	 * implementation of this method. the function takes as argument a
	 * {@link Library}
	 * object containing all the books and patrons that exist on the library system
	 * and uses it to make the changes instructed from the command given by the
	 * user.
	 * </p>
	 *
	 * <p>
	 * The function also takes a {@link LocalDate} object with the current date to
	 * be used
	 * by commands that need this information when executed (e.g. when a book is
	 * {@link BorrowBook borrowed} or {@link ReturnBook returned} ).
	 * </p>
	 *
	 * @param library     the library object to perform actions based on the command
	 *                    given
	 * @param currentDate an object holding the current date
	 * @throws LibraryException if there is an error during the execution
	 *
	 * @see Library
	 * @see LocalDate
	 * @see BorrowBook
	 * @see ReturnBook
	 */
	public void execute(Library library, LocalDate currentDate) throws LibraryException;

}
