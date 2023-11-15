package bcu.cmp5332.librarysystem.model;

import bcu.cmp5332.librarysystem.main.LibraryException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Patron {

    private int id;
    private String name;
    private String phone;
    private String email;
    private final List<Book> books = new ArrayList<>();

	public Patron(int id, String name, String phone, String email) {
		this.id = id;
		this.name = name;
		this.phone = phone;
		this.email = email;
	}

	public int getID() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public String getPhone() {
		return phone;
	}

	public String getEmail() {
		return email;
	}

    public String getDetailsShort() {
        return "Patron #" + id + " - " + name;
    }

	public String getDetailsLong() {
		String books = "";

		for(Book book : this.books) {
			books += "\n  " + book.getDetailsShort();
		}

		return "Patron #" + id +
				"\n Name: " + name +
				"\n Phone: " + phone +
				"\n Email: " + email +
				"\n Books on loan: " + books;
	}

	public List<Book> getBooks() {
		return Collections.unmodifiableList(books);
	}

    public void borrowBook(Book book, LocalDate dueDate) throws LibraryException {
		if(book.isOnLoan()) {
			throw new LibraryException("Book is currently on loan");
		}
		Loan loan = new Loan(this, book, LocalDate.now(), dueDate);
		book.setLoan(loan);
		this.addBook(book);
    }

    public void renewBook(Book book, LocalDate dueDate) throws LibraryException {
		Loan bookLoan = book.getLoan();

		if(bookLoan == null) {
			throw new LibraryException("Book is not on loan");
		} else if(bookLoan.getPatron() != this) {
			throw new LibraryException("Book is not on loan by patron");
		}
		book.setDueDate(dueDate);
    }

    public void returnBook(Book book) throws LibraryException {
		if(!(this.getBooks().contains(book))) {
			throw new LibraryException("Book is not on loan to this patron");
		}
		this.books.remove(book);
		book.returnToLibrary();
    }

    public void addBook(Book book) {
		this.books.add(book);
    }
}
