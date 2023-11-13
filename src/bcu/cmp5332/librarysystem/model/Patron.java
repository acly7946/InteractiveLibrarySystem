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
    private final List<Book> books = new ArrayList<>();

	public Patron(int id, String name, String phone)
	{
		this.id = id;
		this.name = name;
		this.phone = phone;
	}

	public int getID(){
		return id;
	}

	public String getName(){
		return name;
	}

	public String getPhone(){
		return phone;
	}

    public String getDetailsShort() {
        return "Patron #" + id + " - " + name;
    }

	public String getDetailsLong() {
		return "Patron #" + id +
				"\n Name: " + name +
				"\n Phone: " + phone +
				"\n Books on loan: " + getBooks();
	}

	public List<Book> getBooks() {
		return Collections.unmodifiableList(books);
	}

    public void borrowBook(Book book, LocalDate dueDate) throws LibraryException {
        // TODO: implementation here
    }

    public void renewBook(Book book, LocalDate dueDate) throws LibraryException {
        // TODO: implementation here
    }

    public void returnBook(Book book) throws LibraryException {
        // TODO: implementation here
    }

    public void addBook(Book book) {
        // TODO: implementation here
    }
}
