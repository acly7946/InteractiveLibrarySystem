package bcu.cmp5332.librarysystem.model;

import bcu.cmp5332.librarysystem.main.LibraryException;
import java.time.LocalDate;

public class Book {

    private int id;
    private String title;
    private String author;
	private String publisher;
    private String publicationYear;

    private Loan loan;

    public Book(int id, String title, String author, String publisher, String publicationYear) {
        this.id = id;
        this.title = title;
        this.author = author;
		this.publisher = publisher;
        this.publicationYear = publicationYear;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

	public String getPublisher() {
		return publisher;
	}

    public String getPublicationYear() {
        return publicationYear;
    }

	public void setPublisher(String publisher) {
		this.publisher = publisher;
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
        return (loan != null);
    }

    public String getStatus() {
		if(this.isOnLoan()) {
			return "On loan";
		} else {
			return "Available";
		}
    }

    public LocalDate getDueDate() {
		return loan.getDueDate();
    }

    public void setDueDate(LocalDate dueDate) throws LibraryException {
		this.getLoan().setDueDate(dueDate);
    }

    public Loan getLoan() {
        return loan;
    }

    public void setLoan(Loan loan) {
        this.loan = loan;
    }

    public void returnToLibrary() {
        loan = null;
    }
}
