package bcu.cmp5332.librarysystem.model;

import bcu.cmp5332.librarysystem.main.LibraryException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Patron {

    private int id;
	private boolean deleted;
    private String name;
    private String phone;
    private String email;
    private final List<Book> books = new ArrayList<>();
	private final List<Loan> loanHistory = new ArrayList<>();

	public Patron(int id, boolean deleted, String name, String phone, String email) {
		this.id = id;
		this.deleted = deleted;
		this.name = name;
		this.phone = phone;
		this.email = email;
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

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhone() {
		return this.phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

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

	public void renewBook(Book book, LocalDate dueDate) throws LibraryException {
		Loan bookLoan = book.getLoan();

		if (bookLoan == null) {
			throw new LibraryException("Book is not on loan");
		} else if (bookLoan.getPatron() != this) {
			throw new LibraryException("Book is not on loan by patron");
		}
		book.setDueDate(dueDate);
    }

    public String getDetailsShort() {
        return "Patron #" + this.id + " - " + this.name;
    }

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

    public void returnBook(Book book) throws LibraryException {
		if (!(this.getBooks().contains(book))) {
			throw new LibraryException("Book is not on loan to this patron");
		}
		this.books.remove(book);
		book.setReturnDate(LocalDate.now());
		this.loanHistory.add(book.getLoan());
		book.returnToLibrary();
    }

    public void addBook(Book book) {
		this.books.add(book);
    }

	public void addLoanHistory(Loan loan) {
		this.loanHistory.add(loan);
	}
}
