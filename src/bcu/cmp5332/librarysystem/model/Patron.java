package bcu.cmp5332.librarysystem.model;

import bcu.cmp5332.librarysystem.main.LibraryException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Patron class models a patron (member) of the library.
 *
 * The class has fields for different patron properties such as name and phone
 * number and has an id field that represents the patron's unique id in the
 * library. In addition, the class has a list of the books that a patron has
 * borrowed.
 *
 * The class also offers getter and setter methods to get and set the values of
 * the different properties and methods that allow for borrowing, returning and
 * renewing books.
 */
public class Patron {

	private int id;
	private boolean deleted;
    private String name;
    private String phone;
    private String email;
    private final List<Book> books = new ArrayList<>();
	private final List<Loan> loanHistory = new ArrayList<>();

	/**
	 * Create a new Patron instance.
	 *
	 * The constructor initialises a Patron object using the basic mandatory fields
	 * a patron should have to exist in the library.
	 *
	 * @param id      the patron's id
	 * @param deleted the patron's deleted status
	 * @param name    the patron's name
	 * @param phone   the patron's phone
	 * @param email   the patron's email address
	 */
	public Patron(int id, boolean deleted, String name, String phone, String email) {
		this.id = id;
		this.deleted = deleted;
		this.name = name;
		this.phone = phone;
		this.email = email;
	}

	/**
	 * Get the patron's id.
	 *
	 * @return the patron's id as an integer
	 */
	public int getId() {
		return this.id;
	}

	/**
	 * Set the patron's id.
	 *
	 * @param id the integer value that will be set as the patron's id
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * Get the patron's deleted status.
	 *
	 * @return the patron's deleted status as a boolean
	 */
	public boolean getDeleted() {
		return this.deleted;
	}

	/**
	 * Set the patron's deleted status.
	 *
	 * @param deleted the boolean value that will be set as the patron's deleted
	 *                status
	 */
	public void setDeleted(boolean deleted) {
		this.deleted = deleted;
	}

	/**
	 * Get the patron's name.
	 *
	 * @return the patron's name as a String
	 */
	public String getName() {
		return this.name;
	}

	/**
	 * Set the patron's name.
	 *
	 * @param name the String value that will be set as patron's name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Get the patron's phone.
	 *
	 * @return the patron's phone as String
	 */
	public String getPhone() {
		return this.phone;
	}

	/**
	 * Set the patron's phone.
	 *
	 * @param phone the String value that will be set as patron's phone
	 */
	public void setPhone(String phone) {
		this.phone = phone;
	}

	/**
	 * Get the patron's email.
	 *
	 * @return the patron's email as String
	 */
	public String getEmail() {
		return this.email;
	}

	/**
	 * Set the patron's email.
	 *
	 * @param email the String value that will be set as patron's email
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * Get a list of books that the patron has borrowed.
	 *
	 * @return an unmodifiable list of books that the patron has borrowed.
	 */
	public List<Book> getBooks() {
		return Collections.unmodifiableList(books);
	}

	public List<Loan> getLoanHistory() {
		return Collections.unmodifiableList(loanHistory);
	}

    public void borrowBook(Book book, LocalDate dueDate) throws LibraryException {
		if (book.isOnLoan()) {
			throw new LibraryException("Book is currently on loan");
		}
		Loan loan = new Loan(this, book, LocalDate.now(), dueDate, dueDate);
		book.setLoan(loan);
		this.addBook(book);
	}

	/**
	 * Renew the loan's due date for a book that is already on loan.
	 *
	 * <p>
	 * The method changes (renews) the due date of a book that is currently on loan
	 * by the patron.
	 * </p>
	 *
	 * <p>
	 * If the book to be renewed is not on loan by the patron, an exception is
	 * thrown with a message saying that the book is not on loan to this patron.
	 * </p>
	 *
	 * @param book    the book to be renewed
	 * @param dueDate the LocalDate value that will be set as the loan's new due
	 *                date
	 * @throws LibraryException if the book is not on loan to this patron
	 */
	public void renewBook(Book book, LocalDate dueDate) throws LibraryException {
		Loan bookLoan = book.getLoan();

		if (bookLoan == null) {
			throw new LibraryException("Book is not on loan");
		} else if (bookLoan.getPatron() != this) {
			throw new LibraryException("Book is not on loan by patron");
		}
		book.setDueDate(dueDate);
	}

	/**
	 * Get a short string describing the patron.
	 *
	 * @return a short string describing the patron
	 */
	public String getDetailsShort() {
		return "Patron #" + this.id + " - " + this.name;
	}

	/**
	 * Get a long string describing the patron.
	 *
	 * @return a long string describing the patron
	 */
	public String getDetailsLong() {
		String books = "";

		for (Book book : this.books) {
			books += "\n  " + book.getDetailsShort();
		}

		return "Patron #" + this.id +
				"\n Name: " + this.name +
				"\n Phone: " + this.phone +
				"\n Email: " + this.email +
				"\n Books on loan: " + books;
	}

	/**
	 * Return a book to the library.
	 *
	 * <p>
	 * The method removes the book from the patron's list of borrowed books and
	 * returns the book to the library by removing any loan information from the
	 * book.
	 * </p>
	 *
	 * <p>
	 * If the book to be renewed is not on loan by the patron, an exception is
	 * thrown with a message saying that the book is not on loan to this patron.
	 * </p>
	 *
	 * @param book the book to be returned to the library
	 * @throws LibraryException if the book is not on loan to this patron
	 */
	public void returnBook(Book book) throws LibraryException {
		if (!(this.getBooks().contains(book))) {
			throw new LibraryException("Book is not on loan to this patron");
		}
		this.books.remove(book);
		book.setReturnDate(LocalDate.now());
		this.loanHistory.add(book.getLoan());
		book.returnToLibrary();
	}

	/**
	 * Add a book to the patron's list of borrowed books.
	 *
	 * <p>
	 * This method is used to add books that are on loan by the patron when loading
	 * data from the text file storage. The book given as argument to this method
	 * should already have the loan information added to it.
	 * </p>
	 *
	 * @param book the book to be added to the patron's list of borrowed books
	 */
	public void addBook(Book book) {
		this.books.add(book);
    }

	public void addLoanHistory(Loan loan) {
		this.loanHistory.add(loan);
	}
}
