package bcu.cmp5332.librarysystem.commands;

import bcu.cmp5332.librarysystem.model.Book;
import bcu.cmp5332.librarysystem.model.Library;
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

		if (book.getDeleted()) {
			throw new LibraryException("There is no such book with that ID.");
		}

		System.out.println(book.getDetailsLong());
		System.out.println(" Status: " + book.getStatus());

    }
}
