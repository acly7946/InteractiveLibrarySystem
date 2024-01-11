package bcu.cmp5332.librarysystem.model;

import java.time.LocalDate;

/**
 * Loan class models an object that holds information about a book that is on
 * loan.
 *
 * The class has as fields a {@link Patron} object which represents the patron
 * that has
 * borrowed the book, a {@link Book} object that represents the book that is on
 * loan and
 * two LocalDate objects that represent the date the loan started and its due
 * date.
 *
 * The class also offers the basic getter and setter methods to get and set the
 * class's different properties.
 *
 * @see Patron
 * @see Book
 */
public class Loan {

    private Patron patron;
    private Book book;
    private LocalDate startDate;
    private LocalDate dueDate;
	private LocalDate returnDate;

    public Loan(Patron patron, Book book, LocalDate startDate, LocalDate dueDate, LocalDate returnDate) {
		this.patron = patron;
		this.book = book;
		this.startDate = startDate;
		this.dueDate = dueDate;
		this.returnDate = returnDate;
    }

	/**
	 * Get the loan's patron object.
	 *
	 * @return the loan's patron object
	 */
	public Patron getPatron() {
		return this.patron;
	}

	/**
	 * Set the loan's patron object.
	 *
	 * @param patron the {@link Patron} object that will be set as the loan's patron
	 */
	public void setPatron(Patron patron) {
		this.patron = patron;
	}

	/**
	 * Get the loan's book object.
	 *
	 * <p>
	 * Get the book associated with the loan.
	 * </p>
	 *
	 * @return the loan's book
	 */
	public Book getBook() {
		return this.book;
	}

	/**
	 * Set the loan's book object.
	 *
	 * @param book the {@link Book} object that will be set as the loan's book
	 */
	public void setBook(Book book) {
		this.book = book;
	}

	/**
	 * Get the loan's start date.
	 *
	 * @return the loan's start date as LocalDate
	 */
	public LocalDate getStartDate() {
		return this.startDate;
	}

	/**
	 * Set the loan's start date.
	 *
	 * @param startDate the {@link LocalDate} object that will be set as the loan's
	 *                  start date
	 */
	public void setStartDate(LocalDate startDate) {
		this.startDate = startDate;
	}

	/**
	 * Get the loan's due date.
	 *
	 * @return the loan's due date as LocalDate
	 */
	public LocalDate getDueDate() {
		return this.dueDate;
	}

	/**
	 * Set the loan's due date.
	 *
	 * @param dueDate the LocalDate value that will be set as the loan's due date
	 */
	public void setDueDate(LocalDate dueDate) {
		this.dueDate = dueDate;
	}

	/**
	 * Get the loan's return date.
	 *
	 * @return the loan's return date as LocalDate
	 */
	public LocalDate getReturnDate() {
		return this.returnDate;
	}

	/**
	 * Set the loan's return date.
	 *
	 * @param returnDate the LocalDate value that will be set as the loan's return date
	 */
	public void setReturnDate(LocalDate returnDate) {
		this.returnDate = returnDate;
	}

}
