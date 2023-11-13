package bcu.cmp5332.librarysystem.commands;

import bcu.cmp5332.librarysystem.model.Book;
import bcu.cmp5332.librarysystem.model.Library;
import bcu.cmp5332.librarysystem.model.Patron;
import bcu.cmp5332.librarysystem.main.LibraryException;

import java.time.LocalDate;
import java.util.List;

public class ShowBook implements Command {

	private final int bookID;

	public ShowBook(int bookID) {
		this.bookID = bookID;
	}

    @Override
    public void execute(Library library, LocalDate localDate) throws LibraryException {
		Book book = library.getBookByID(bookID);

		System.out.println(book.getDetailsLong());
		if(book.isOnLoan()) {
			Patron patron = book.getLoan().getPatron();
			LocalDate dueDate = book.getLoan().getDueDate();
			System.out.println("This book is on loan to " + patron.getName() + " and is due back on " + dueDate);
		}

    }
}
