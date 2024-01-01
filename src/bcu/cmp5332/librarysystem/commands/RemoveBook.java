package bcu.cmp5332.librarysystem.commands;

import bcu.cmp5332.librarysystem.model.Library;
import bcu.cmp5332.librarysystem.main.LibraryException;

import java.time.LocalDate;

/**
 * Removes a book from the library.
 *
 * <p>
 * It is an implementation of the {@link Command} interface and its
 * {@link execute execute(Library, LocalDate)} method removes a {@link Book}
 * from the library.
 * </p>
 *
 * <p>
 * It is created and executed by the
 * {@link bcu.cmp5332.librarysystem.main.CommandParser CommandParser} when the
 * "removebook"
 * command is given by the user.
 * </p>
 *
 * <p>
 * The class has a {@link RemoveBook#RemoveBook constructor} to initialise the
 * fields needed to execute the command
 * </p>
 *
 * @see Command
 * @see execute execute(Library, LocalDate)
 * @see bcu.cmp5332.librarysystem.model.Book Book
 * @see bcu.cmp5332.librarysystem.main.CommandParser CommandParser
 * @see RemoveBook RemoveBook(String, String)
 */
public class RemoveBook implements Command {

	private final int bookId;

	/**
	 * Create a new RemoveBook instance.
	 *
	 * <p>
	 * The constructor initialises a RemoveBook object using the necessary fields
	 * to remove a book from the library.
	 * </p>
	 *
	 * @param bookId the ID of the book to be removed
	 */
	public RemoveBook(int bookId) {
		this.bookId = bookId;
	}

	/**
	 * Remove a book from the library
	 *
	 * @param library     the library from which the book is to be removed
	 * @param currentDate the current date
	 * @throws LibraryException if an error occurs while removing the book
	 */
	@Override
	public void execute(Library library, LocalDate currentDate) throws LibraryException {
		library.removeBook(bookId);
		System.out.println("Book " + bookId + " removed");
	}
}
