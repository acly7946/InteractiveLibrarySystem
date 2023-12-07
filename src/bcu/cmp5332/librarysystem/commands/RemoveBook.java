package bcu.cmp5332.librarysystem.commands;

import bcu.cmp5332.librarysystem.model.Library;
import bcu.cmp5332.librarysystem.main.LibraryException;

import java.time.LocalDate;

public class RemoveBook implements Command {

	private final int bookId;

	public RemoveBook(int bookId) {
		this.bookId = bookId;
	}

    @Override
    public void execute(Library library, LocalDate currentDate) throws LibraryException {
		library.removeBook(bookId);
		System.out.println("Book " + bookId + " removed");
    }
}
