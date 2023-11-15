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

    // TODO: implementation of Getter and Setter methods
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
}
