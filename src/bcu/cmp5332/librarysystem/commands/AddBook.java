package bcu.cmp5332.librarysystem.commands;

import bcu.cmp5332.librarysystem.model.Book;
import bcu.cmp5332.librarysystem.model.Library;
import bcu.cmp5332.librarysystem.main.LibraryException;
import java.time.LocalDate;

/**
 * Adds a new book to the library.
 *
 * <p>
 * It is an implementation of the {@link Command} interface and its {@link execute execute(Library,
 * LocalDate)} method adds a new {@link Book} to the library.
 * </p>
 *
 * <p>
 * It is created and executed by the {@link CommandParser} when the "addbook" command is
 * given by the user.
 * </p>
 *
 * <p>
 * The class has a {@link constructor} to initialise the fields needed to execute the
 * command.
 * </p>
 *
 * @see Command
 * @see execute execute(Library, LocalDate)
 * @see bcu.cmp5332.librarysystem.model.Book Book
 * @see bcu.cmp5332.librarysystem.main.CommandParser CommandParser
 * @see AddBook AddBook(String, String, String)
 */
public class AddBook implements Command {

	private final String title;
	private final String author;
	private final String publisher;
	private final String publicationYear;

	/**
	 * Create a new AddBook instance.
	 *
	 * @param title           the title of the book
	 * @param author          the author of the book
	 * @param publisher       the publisher of the book
	 * @param publicationYear the publication year of the book
	 */
	public AddBook(String title, String author, String publisher, String publicationYear) {
		this.title = title;
		this.author = author;
		this.publisher = publisher;
		this.publicationYear = publicationYear;
	}

	/**
	 * Create and add a new book to the library.
	 *
	 * @param library     the library to which the book will be added
	 * @param currentDate the current date
	 * @throws LibraryException if an error occurs while adding the book to the
	 *                          library
	 */
	@Override
	public void execute(Library library, LocalDate currentDate) throws LibraryException {
		int maxId = 0;
		if (library.getBooks().size() > 0) {
			int lastIndex = library.getBooks().size() - 1;
			maxId = library.getBooks().get(lastIndex).getId();
		}
		Book book = new Book(++maxId, false, title, author, publisher, publicationYear);
		library.addBook(book);
		System.out.println("Book #" + book.getId() + " added.");
	}
}
