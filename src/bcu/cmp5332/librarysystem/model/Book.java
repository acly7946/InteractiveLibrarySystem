package bcu.cmp5332.librarysystem.model;

import bcu.cmp5332.librarysystem.main.LibraryException;
import java.time.LocalDate;

public class Book {

    private int id;
	private boolean deleted;
    private String title;
    private String author;
	private String publisher;
    private String publicationYear;

    private Loan loan;

    public Book(int id, String title, String author, String publisher, String publicationYear) {
        this.id = id;
		this.deleted = false;
        this.title = title;
        this.author = author;
		this.publisher = publisher;
        this.publicationYear = publicationYear;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

	public boolean getDeleted() {
		return this.deleted;
	}

	public void setDeleted(boolean deleted) {
		this.deleted = deleted;
	}

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return this.author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

	public String getPublisher() {
		return this.publisher;
	}

	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}

    public String getPublicationYear() {
        return this.publicationYear;
    }

    public void setPublicationYear(String publicationYear) {
        this.publicationYear = publicationYear;
    }

    public String getDetailsShort() {
        return "Book #" + id + " - " + title;
    }

    public String getDetailsLong() {
		return "Book #" + id +
				"\n Title: " + title +
				"\n Author: " + author +
				"\n Publisher: " + publisher +
				"\n Publication Year: " + publicationYear;
    }

    public boolean isOnLoan() {
        return (this.loan != null);
    }

    public String getStatus() {
		if (this.isOnLoan()) {
			return "On loan";
		} else {
			return "Available";
		}
    }

    public LocalDate getDueDate() {
		return this.loan.getDueDate();
    }

    public void setDueDate(LocalDate dueDate) throws LibraryException {
		this.getLoan().setDueDate(dueDate);
    }

    public Loan getLoan() {
        return this.loan;
    }

    public void setLoan(Loan loan) {
        this.loan = loan;
    }

    public void returnToLibrary() {
        this.loan = null;
    }
}
