package bcu.cmp5332.librarysystem.commands;

import bcu.cmp5332.librarysystem.model.Book;
import bcu.cmp5332.librarysystem.model.Library;
import bcu.cmp5332.librarysystem.model.Patron;
import bcu.cmp5332.librarysystem.main.LibraryException;

import java.time.LocalDate;

public class ShowBook implements Command {

	private final int bookId;

	public ShowBook(int bookId) {
		this.bookId = bookId;
	}

    @Override
    public void execute(Library library, LocalDate currentDate) throws LibraryException {
		Book book = library.getBookByID(bookId);

		System.out.println(book.getDetailsLong());
		if(book.isOnLoan()) {
			Patron patron = book.getLoan().getPatron();
			LocalDate dueDate = book.getLoan().getDueDate();
			System.out.println("Loaned to " + patron.getName() + ", due back on " + dueDate);
		}

    }
}
