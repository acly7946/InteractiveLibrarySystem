package bcu.cmp5332.librarysystem.commands;

import bcu.cmp5332.librarysystem.main.LibraryException;
import bcu.cmp5332.librarysystem.model.Book;
import bcu.cmp5332.librarysystem.model.Library;
import bcu.cmp5332.librarysystem.model.Patron;

import java.time.LocalDate;

/**
 * Renews a book that is already on loan to a patron.
 *
 * <p>
 * It is an implementation of the {@link Command} interface and its
 * {@link execute execute(Library, LocalDate)}
 * method renews (changes) the due date of a {@link Book} that is currently
 * on loan to a {@link Patron}.
 * </p>
 *
 * <p>
 * It is created and executed by the
 * {@link bcu.cmp5332.librarysystem.main.CommandParser CommandParser} when the
 * "renewbook" command
 * is given by the user.
 * </p>
 *
 * <p>
 * The class has a {@link RenewBook#Renewbook constructor} to initialise the
 * fields needed to execute the
 * command.
 * </p>
 *
 * @see Command
 * @see execute execute(Library, LocalDate)
 * @see bcu.cmp5332.librarysystem.model.Book Book
 * @see bcu.cmp5332.librarysystem.model.Patron Patron
 * @see bcu.cmp5332.librarysystem.main.CommandParser CommandParser
 * @see RenewBook RenewBook(int, int)
 */
public class RenewBook implements Command {

	private final int patronId;
	private final int bookId;

	/**
	 * Create a new RenewBook instance.
	 *
	 * <p>
	 * The constructor initialises a RenewBook object using the fields needed for
	 * renewing the due date of a book that is on loan to a patron.
	 * </p>
	 *
	 * @param patronId the ID of the patron that has borrowed the book
	 * @param bookId   the ID of the book whose due date will be renewed
	 */
	public RenewBook(int patronId, int bookId) {
		this.patronId = patronId;
		this.bookId = bookId;
	}

	/**
	 *
	 * Renew the due date of a book that is on loan.
	 *
	 * <p>
	 * The method should calculate the new due date of the book based on the
	 * predefined loan period and the current due date and then set the new due date
	 * using the appropriate method implemented in the {@link Patron} class. After
	 * the successful renewal, the method should display a message to notify the
	 * user
	 * about the new due date.
	 * </p>
	 *
	 * <p>
	 * The method should throw an exception if there is an error during the
	 * execution or if the given book or patron do not exist in the library.
	 * </p>
	 */
	@Override
	public void execute(Library library, LocalDate currentDate) throws LibraryException {
		LocalDate dueDate = currentDate.plusDays(library.getLoanPeriod());
		Book book = library.getBookByID(bookId);
		Patron patron = library.getPatronByID(patronId);

		if (book.getDeleted()) {
			throw new LibraryException("There is no such book with that ID.");
		} else if (patron.getDeleted()) {
			throw new LibraryException("There is no such patron with that ID.");
		}

		patron.renewBook(book, dueDate);
	}
}