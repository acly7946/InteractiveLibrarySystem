package bcu.cmp5332.librarysystem.commands;

import bcu.cmp5332.librarysystem.model.Book;
import bcu.cmp5332.librarysystem.model.Library;
import bcu.cmp5332.librarysystem.main.LibraryException;

import java.time.LocalDate;

/**
 * Shows detailed information about a specific book.
 *
 * <p>
 * It is an implementation of the {@link Command} interface and its
 * {@link execute execute(Library, LocalDate)} method displays detailed
 * information about a {@link Book} to the user. If
 * the book is on loan, the method should display information about the patron
 * that has borrowed the book as well as the due date of the loan.
 * </p>
 *
 * <p>
 * It is created and executed by the
 * {@link bcu.cmp5332.librarysystem.main.CommandParser CommandParser} when the
 * "showbook" command
 * is given by the user.
 * </p>
 *
 * <p>
 * The class has a {@link ShowBook#ShowBook constructor} to initialise the
 * fields needed to execute the
 * command.
 * </p>
 *
 * @see Command
 * @see execute execute(Library, LocalDate)
 * @see bcu.cmp5332.librarysystem.model.Book Book
 * @see bcu.cmp5332.librarysystem.main.CommandParser CommandParser
 * @see ShowBook ShowBook(int)
 */
public class ShowBook implements Command {

	private final int bookId;

	/**
	 * Create a new ShowBook instance.
	 *
	 * <p>
	 * The constructor initialises a ShowBook object using the fields needed to find
	 * a book that exists in the library.
	 * </p>
	 *
	 * @param bookId the id of the book whose details will be displayed
	 */
	public ShowBook(int bookId) {
		this.bookId = bookId;
	}

	@Override
	public void execute(Library library, LocalDate currentDate) throws LibraryException {
		Book book = library.getBookByID(bookId);

		if (book.getDeleted()) {
			throw new LibraryException("There is no such book with that ID.");
		}

		System.out.println(book.getDetailsLong());
		System.out.println(" Status: " + book.getStatus());

	}
}
