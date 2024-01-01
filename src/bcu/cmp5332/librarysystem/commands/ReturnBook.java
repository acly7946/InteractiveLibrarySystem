package bcu.cmp5332.librarysystem.commands;

import bcu.cmp5332.librarysystem.main.LibraryException;
import bcu.cmp5332.librarysystem.model.Book;
import bcu.cmp5332.librarysystem.model.Library;
import bcu.cmp5332.librarysystem.model.Patron;

import java.time.LocalDate;

/**
 * Returns a book to the library.
 *
 * <p>
 * It is an implementation of the {@link Command} interface and its
 * {@link execute execute(Library, LocalDate)}
 * method returns a {@link Book} (that is currently on loan to a {@link Patron})
 * back
 * to the library.
 * </p>
 *
 * <p>
 * It is created and executed by the
 * {@link bcu.cmp5332.librarysystem.main.CommandParser CommandParser} when the
 * "returnbook" command
 * is given by the user.
 * </p>
 *
 * <p>
 * The class has a {@link ReturnBook#ReturnBook constructor} to initialise the
 * fields needed to execute the
 * command.
 * </p>
 *
 * @see Command
 * @see execute execute(Library, LocalDate)
 * @see bcu.cmp5332.librarysystem.model.Book Book
 * @see bcu.cmp5332.librarysystem.model.Patron Patron
 * @see bcu.cmp5332.librarysystem.main.CommandParser CommandParser
 * @see ReturnBook ReturnBook(int, int)
 */
public class ReturnBook implements Command {

	private final int patronId;
	private final int bookId;

	/**
	 * Create a new ReturnBook instance.
	 *
	 * <p>
	 * The constructor initialises a ReturnBook object using the fields needed for
	 * returning a book to the library.
	 * </p>
	 *
	 * @param patronId the ID of the patron that has borrowed the book
	 * @param bookId   the ID of the book to be returned in the library
	 */
	public ReturnBook(int patronId, int bookId) {
		this.patronId = patronId;
		this.bookId = bookId;
	}

	/**
	 * Return a book that is on loan back to the library.
	 *
	 * <p>
	 * The method should execute the tasks needed to return a book to the library.
	 * After successful return, the method should display a message to the user
	 * saying that the book was returned. The method should also check the due date
	 * of the book and notify the user if the current date is past the due date by
	 * displaying a message showing the number of days that the book is overdue.
	 * </p>
	 *
	 * <p>
	 * The method should throw an exception if there is an error during the
	 * execution or if the given book or patron do not exist in the library.
	 * </p>
	 */
	@Override
	public void execute(Library library, LocalDate currentDate) throws LibraryException {
		Patron patron = library.getPatronByID(patronId);
		Book book = library.getBookByID(bookId);

		if (book.getDeleted()) {
			throw new LibraryException("There is no such book with that ID.");
		} else if (patron.getDeleted()) {
			throw new LibraryException("There is no such patron with that ID.");
		}

		patron.returnBook(book);
		System.out.println("Book " + bookId + " returned by " + patronId);
	}
}