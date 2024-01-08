package bcu.cmp5332.librarysystem.model;

import java.time.LocalDate;

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

	public Patron getPatron() {
		return this.patron;
	}

	public void setPatron(Patron patron) {
		this.patron = patron;
	}

	public Book getBook() {
		return this.book;
	}

	public void setBook(Book book) {
		this.book = book;
	}

	public LocalDate getStartDate() {
		return this.startDate;
	}

	public void setStartDate(LocalDate startDate) {
		this.startDate = startDate;
	}

	public LocalDate getDueDate() {
		return this.dueDate;
	}

	public void setDueDate(LocalDate dueDate) {
		this.dueDate = dueDate;
	}

	public LocalDate getReturnDate() {
		return this.returnDate;
	}

	public void setReturnDate(LocalDate returnDate) {
		this.returnDate = returnDate;
	}

}
