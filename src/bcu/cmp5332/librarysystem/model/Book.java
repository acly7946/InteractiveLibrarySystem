package bcu.cmp5332.librarysystem.model;

import bcu.cmp5332.librarysystem.main.LibraryException;
import java.time.LocalDate;

/**
 * Book class models a book in the library.
 *
 * <p>
 * The book has fields for different book properties such as title and author
 * and also has an id field that represents the book's unique id in the library
 * system. In addition, the class has a loan field of type {@link Loan} that
 * holds
 * information about a book that is on loan.
 * </p>
 *
 * <p>
 * The class also offers getter and setter methods to get and set the values of
 * the different properties and methods that change the status of the book when
 * it gets borrowed or returned to the library.
 * </p>
 *
 * @see Loan
 */
public class Book {

	private int id;
	private boolean deleted;
	private String title;
	private String author;
	private String publisher;
	private String publicationYear;

	private Loan loan;

	/**
	 * Create a new book instance
	 *
	 * <p>
	 * The constructor initialises a Book object using the basic mandatory fields a
	 * book should have to exist in the library. Additional fields can be set after
	 * initialisation using the class's setter methods (e.g. {@link setLoan
	 * setLoan(Loan)}) .
	 * </p>
	 *
	 * @param id              the book's id
	 * @param deleted         the book's deleted status
	 * @param title           the book's title
	 * @param author          the book's author
	 * @param publisher       the book's publisher
	 * @param publicationYear the book's publication year
	 */
	public Book(int id, boolean deleted, String title, String author, String publisher, String publicationYear) {
		this.id = id;
		this.deleted = deleted;
		this.title = title;
		this.author = author;
		this.publisher = publisher;
		this.publicationYear = publicationYear;
	}

	/**
	 * Get the book's id
	 *
	 * @return the id of the book as an integer
	 */
	public int getId() {
		return this.id;
	}

	/**
	 * Set the book's id
	 *
	 * @param id the integer value that will be set as the book id
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * Get the book's deleted status
	 *
	 * @return true if the book is deleted, false otherwise
	 */
	public boolean getDeleted() {
		return this.deleted;
	}

	/**
	 * Set the book's deleted status
	 *
	 * @param deleted boolean value for books deletion status
	 */
	public void setDeleted(boolean deleted) {
		this.deleted = deleted;
	}

	/**
	 * Get the book's title
	 *
	 * @return the book's title as a string
	 */
	public String getTitle() {
		return this.title;
	}

	/**
	 * Set the book's title
	 *
	 * @param the string value that will be set as the book's title
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * Get the book's author
	 *
	 * @return the book's author as a string
	 */
	public String getAuthor() {
		return this.author;
	}

	/**
	 * Set the book's author
	 *
	 * @param author the string value that will be set as the book's author
	 */
	public void setAuthor(String author) {
		this.author = author;
	}

	/**
	 * Get the book's publisher
	 *
	 * @return the book's publisher as a string
	 */
	public String getPublisher() {
		return this.publisher;
	}

	/**
	 * Set the book's publisher
	 *
	 * @param publisher the string value that will be set as the book's publisher
	 */
	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}

	/**
	 * Get the book's publication year
	 *
	 * @return the book's publication year as a string
	 */
	public String getPublicationYear() {
		return this.publicationYear;
	}

	/**
	 * Set the book's publication year
	 *
	 * @param publicationYear the string value that will be set as the book's
	 *                        publication year
	 */
	public void setPublicationYear(String publicationYear) {
		this.publicationYear = publicationYear;
	}

	/**
	 * Get short description for the book
	 *
	 * <p>
	 * The function returns a string containing the book's id and title. in the
	 * following format:
	 * "Book #43 - The War of the Worlds"
	 * </p>
	 *
	 * @return the book's short description as a string
	 */
	public String getDetailsShort() {
		return "Book #" + id + " - " + title;
	}

	/**
	 * Get detailed info for the book
	 *
	 * <p>
	 * The function returns a String containing all the book's properties in the
	 * following format:
	 *
	 * Book #43
	 * Title: The War of The Worlds
	 * Author: H.G. Wells
	 * Publication year: 1898
	 *
	 * In addition, if the book is on loan, the returned String should contain the
	 * name of the patron that has borrowed the book and the due date for the loan.
	 * </p>
	 *
	 * @return detailed information about the book as a string
	 */
	public String getDetailsLong() {
		return "Book #" + id +
				"\n Title: " + title +
				"\n Author: " + author +
				"\n Publisher: " + publisher +
				"\n Publication Year: " + publicationYear;
	}

	/**
	 * Check if the book is on loan
	 *
	 * <p>
	 * The function checks if the book has a Loan associated with it and returns the
	 * results.
	 * </p>
	 *
	 * @return true if the book is on loan and false if the book is available
	 */
	public boolean isOnLoan() {
		return (this.loan != null);
	}

	/**
	 * Get the book's status
	 *
	 * <p>
	 * The function returns "On loan" if the book is on loan and "Available" if the
	 * book is available.
	 * </p>
	 *
	 * @return the book status as String
	 */
	public String getStatus() {
		if (this.isOnLoan()) {
			return "On loan";
		} else {
			return "Available";
		}
	}

	/**
	 * Get the due date of a book that is on loan
	 *
	 * @return the due date of a book if the book is on loan
	 */
	public LocalDate getDueDate() {
		return this.loan.getDueDate();
	}

	/**
	 * Set the due date of a book
	 *
	 * <p>
	 * The function sets (changes) the due date of a book that is currently on loan.
	 * If the book is not on loan, an exception should be thrown, notifying the user
	 * that the due date can't be set because the book is not on loan.
	 * </p>
	 *
	 * @param dueDate the LocalDate value that will be set as the book's due date
	 * @throws LibraryException if the book is not on loan
	 */
	public void setDueDate(LocalDate dueDate) throws LibraryException {
		this.getLoan().setDueDate(dueDate);
	}

	public LocalDate getReturnDate() {
		return this.loan.getReturnDate();
	}

	public void setReturnDate(LocalDate returnDate) throws LibraryException {
		this.getLoan().setReturnDate(returnDate);
	}

    public Loan getLoan() {
        return this.loan;
    }

	/**
	 * Get the book's {@link Loan} object that holds information about a book that
	 * is on loan
	 *
	 * @param loan the Loan object to be set on the book
	 */
	public void setLoan(Loan loan) {
		this.loan = loan;
	}

	/**
	 * Return the book to the library
	 *
	 * <p>
	 * This function is used to unset any loan information that exists for the book.
	 * </p>
	 */
	public void returnToLibrary() {
		this.loan = null;
	}
}
