package bcu.cmp5332.librarysystem.commands;

import bcu.cmp5332.librarysystem.main.LibraryException;
import bcu.cmp5332.librarysystem.model.Book;
import bcu.cmp5332.librarysystem.model.Library;
import bcu.cmp5332.librarysystem.model.Patron;

import java.time.LocalDate;

/**
 * Executes the actions needed to lend a book to a patron.
 * <p>
 * It is an implementation of the {@link Command} interface and
 * its {@link Command#execute(Library, LocalDate) execute(Library, LocalDate)}
 * method assigns
 * a {@link Book} to a {@link Patron}
 * </p>
 * <p>
 * It is created and executed by
 * {@link bcu.cmp5332.librarysystem.main.CommandParser}
 * when the "borrowbook" command is given by the user.
 * </p>
 * <p>
 * The class has a {@link constructor} to initialise the fields needed
 * to execute the command.
 * </p>
 *
 * @see Command
 * @see execute execute(Library, LocalDate)
 * @see bcu.cmp5332.librarysystem.model.Book Book
 * @see bcu.cmp5332.librarysystem.model.Patron Patron
 * @see bcu.cmp5332.librarysystem.main.CommandParser CommandParser
 * @see BorrowBook BorrowBook(int, int)
 */
public class BorrowBook implements Command {

	private final int patronId;
	private final int bookId;

	/**
	 * Create a new BorrowBook instance.
	 *
	 * @param patronId  the id of the patron that will borrow the book
	 * @param bookId   the id of the book that will be borrowed by the patron
	 */
	public BorrowBook(int patronId, int bookId) {
		this.patronId = patronId;
		this.bookId = bookId;
	}

	/**
	 * Lend a book to a patron.
	 *
	 * <p>
	 * The method should calculate the due date of the book based on the predefined
	 * loan period and then lend the book to the patron using the appropriate
	 * methods implemented in the {@link Book} and {@link Patron} classes. After the successful
	 * lending of the book, a message should be displayed to notify the user about
	 * the due date of the loan.
	 * </p>
	 *
	 * <p>
	 * The method should throw an exception if there is an error during the
	 * execution or if the given book or patron do not exist in the library.
	 * </p>
	 *
	 * @param library     the library object
	 * @param currentDate the current date
	 * @throws LibraryException if there is an error in borrowing the book
	 */
	@Override
	public void execute(Library library, LocalDate currentDate) throws LibraryException {
		LocalDate dueDate = currentDate.plusDays(library.getLoanPeriod());
		Patron patron = library.getPatronByID(patronId);
		Book book = library.getBookByID(bookId);

		if (book.getDeleted()) {
			throw new LibraryException("There is no such book with that ID.");
		} else if (patron.getDeleted()) {
			throw new LibraryException("There is no such patron with that ID.");
		} else if (patron.getBooks().size() >= library.getMaxLoans()) {
			throw new LibraryException("Maximum number of (" + library.getMaxLoans() + ") books reached.");
		}

		patron.borrowBook(book, dueDate);
	}
}