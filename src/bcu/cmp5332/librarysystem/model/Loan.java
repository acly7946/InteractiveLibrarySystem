package bcu.cmp5332.librarysystem.model;

import java.time.LocalDate;

public class Loan {

    private Patron patron;
    private Book book;
    private LocalDate startDate;
    private LocalDate dueDate;

    public Loan(Patron patron, Book book, LocalDate startDate, LocalDate dueDate) {
		this.patron = patron;
		this.book = book;
		this.startDate = startDate;
		this.dueDate = dueDate;
    }

	public LocalDate getDueDate() {
		return dueDate;
	}

	public LocalDate getStartDate() {
		return startDate;
	}

	public Book getBook() {
		return book;
	}

	public Patron getPatron() {
		return patron;
	}

	public void setBook(Book book) {
		this.book = book;
	}

	public void setPatron(Patron patron) {
		this.patron = patron;
	}

	public void setDueDate(LocalDate dueDate) {
		this.dueDate = dueDate;
	}

	public void setStartDate(LocalDate startDate) {
		this.startDate = startDate;
	}
}
