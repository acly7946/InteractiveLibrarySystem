package bcu.cmp5332.librarysystem.model;

import bcu.cmp5332.librarysystem.main.LibraryException;
import java.util.*;

/**
 * Library class models the whole library.
 *
 * It has a collection of all books and all members (patrons) and can also hold
 * other global library properties such as the maximum loan period in days.
 *
 * When the application starts, the {@link Main} class of the app loads the
 * stored data
 * from the file storage using the {@link LibraryData} class and populates the
 * collections holding the patrons and books.
 *
 * The class offers methods to get all the books and patrons as a list and to
 * get a specific book or patron using their id.
 *
 * @see Main
 * @see LibraryData
 */
public class Library {

	private final int loanPeriod = 7;
	private final int maxLoans = 5;
	private final Map<Integer, Patron> patrons = new TreeMap<>();
	private final Map<Integer, Book> books = new TreeMap<>();

	/**
	 * Get the predefined maximum period to loan a book in days.
	 *
	 * @return the loan period in days as an integer
	 */
	public int getLoanPeriod() {
		return this.loanPeriod;
	}

	/**
	 * Get the maximum number of books a patron can loan at a time.
	 *
	 * @return the maximum number of books a patron can loan at a time as an integer
	 */
	public int getMaxLoans() {
		return this.maxLoans;
	}

	/**
	 * Get a list of all patrons (members) of the library.
	 *
	 * @return an unmodifiable list of all patrons in the library
	 */
	public List<Patron> getPatrons() {
		List<Patron> out = new ArrayList<>(patrons.values());
		return Collections.unmodifiableList(out);
	}

	/**
	 * Get a list of all books that exist in the library.
	 *
	 * @return an unmodifiable List of all books in the library
	 */
	public List<Book> getBooks() {
		List<Book> out = new ArrayList<>(books.values());

		return Collections.unmodifiableList(out);
	}

	/**
	 * Get a book from the library based on its id.
	 *
	 * @param id the id of the book to be found
	 * @return the {@link Book} with id matching the one given as argument to the
	 *         function
	 * @throws LibraryException if a book with that id does not exist in the library
	 */
	public Book getBookByID(int id) throws LibraryException {
		if (!books.containsKey(id)) {
			throw new LibraryException("There is no such book with that ID.");
		}
		return books.get(id);
	}

	/**
	 * Get a patron (member) from the library based on their id.
	 *
	 * <p>
	 * The function looks in the collection of all the patrons and returns the
	 * patron that its id matches the given one. If a patron with that id does not
	 * exist, the function throws a {@link LibraryException} with a message
	 * indicating that
	 * there is no patron with that id.
	 * </p>
	 *
	 * @param id the id of the patron to be found
	 * @return the {@link Patron} with id matching the one given as argument to the
	 *         function
	 * @throws LibraryException if a patron with that id does not exist in the
	 *                          library
	 */
	public Patron getPatronByID(int id) throws LibraryException {
		if (!patrons.containsKey(id)) {
			throw new LibraryException("There is no such patron with that ID.");
		}
		return patrons.get(id);
	}

	/**
	 * Add a book to the library.
	 *
	 * <p>
	 * The function adds a book to the collection of all books of the library. If a
	 * book with that id already exists in the library, the function throws an
	 * IllegalArgumentException with a message indicating that there is already a
	 * book with that id in the library.
	 * </p>
	 *
	 * @param book the {@link Book} to be added to the library
	 */
	public void addBook(Book book) {
		if (books.containsKey(book.getId())) {
			throw new IllegalArgumentException("Duplicate book ID.");
		}
		books.put(book.getId(), book);
	}

	/**
	 * Add a patron to the library.
	 *
	 * <p>
	 * The function adds a patron to the collection of all patrons of the library.
	 * If a patron with that id already exists in the library, the function throws
	 * an IllegalArgumentException with a message indicating that there is already a
	 * patron with that id in the library.
	 * </p>
	 *
	 * @param patron the {@link Patron} to be added to the library
	 */
	public void addPatron(Patron patron) {
		if (patrons.containsKey(patron.getId())) {
			throw new IllegalArgumentException("Duplicate book ID.");
		}
		patrons.put(patron.getId(), patron);
	}

	/**
	 * Remove a book from the library.
	 *
	 * @param id the id of the book to be removed
	 * @throws LibraryException if a book with that id does not exist in the library
	 */
	public void removeBook(int id) throws LibraryException {
		if (!books.containsKey(id)) {
			throw new LibraryException("There is no such book with that ID.");
		}
		books.get(id).setDeleted(true);
	}

	/**
	 * Remove a patron from the library.
	 *
	 * @param id the id of the patron to be removed
	 * @throws LibraryException if a patron with that id does not exist in the
	 *                          library
	 */
	public void removePatron(int id) throws LibraryException {
		if (!patrons.containsKey(id)) {
			throw new LibraryException("There is no such patron with that ID.");
		}
		patrons.get(id).setDeleted(true);
	}
}
