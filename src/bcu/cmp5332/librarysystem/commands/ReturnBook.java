package bcu.cmp5332.librarysystem.commands;

import bcu.cmp5332.librarysystem.main.LibraryException;
import bcu.cmp5332.librarysystem.model.Book;
import bcu.cmp5332.librarysystem.model.Library;
import bcu.cmp5332.librarysystem.model.Patron;

import java.time.LocalDate;

public class ReturnBook implements Command {

	private final int patronId;
	private final int bookId;

    public ReturnBook(int patronId, int bookId) {
		this.patronId = patronId;
		this.bookId = bookId;
    }

    @Override
    public void execute(Library library, LocalDate currentDate) throws LibraryException {
		Patron patron = library.getPatronByID(patronId);
		Book book = library.getBookByID(bookId);

		patron.returnBook(book);
		System.out.println("Book " + bookId + " returned by " + patronId);
    }
}