package bcu.cmp5332.librarysystem.commands;

import bcu.cmp5332.librarysystem.model.Book;
import bcu.cmp5332.librarysystem.model.Library;
import bcu.cmp5332.librarysystem.main.LibraryException;

import java.time.LocalDate;
import java.util.List;

/**
 * Shows a list of all library books to the user.
 *
 * <p>
 * It is an implementation of the {@link Command} interface and its
 * {@link execute execute(Library, LocalDate)} method displays a list of the
 * library books to the user. At the
 * end of the list, the total amount of books in the library is also displayed
 * to the user.
 * </p>
 *
 * <p>
 * It is created and executed by the {@link bcu.cmp5332.librarysystem.main.CommandParser CommandParser} when the "listbooks" command
 * is given by the user.
 * </p>
 *
 * @see Command
 * @see execute execute(Library, LocalDate)
 * @see bcu.cmp5332.librarysystem.main.CommandParser CommandParser
 */
public class ListBooks implements Command {

	@Override
	public void execute(Library library, LocalDate currentDate) throws LibraryException {
		List<Book> books = library.getBooks();
		int deletedBooks = 0;
		for (Book book : books) {
			if (book.getDeleted()) {
				deletedBooks++;
				continue;
			}
			System.out.println(book.getDetailsShort());
		}
		System.out.println(books.size() - deletedBooks + " book(s)");
	}
}
