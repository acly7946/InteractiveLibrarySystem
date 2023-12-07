package bcu.cmp5332.librarysystem.commands;

import bcu.cmp5332.librarysystem.main.LibraryException;
import bcu.cmp5332.librarysystem.model.Book;
import bcu.cmp5332.librarysystem.model.Library;
import bcu.cmp5332.librarysystem.model.Patron;

import java.time.LocalDate;

public class RenewBook implements Command {

	private final int patronId;
	private final int bookId;

    public RenewBook(int patronId, int bookId) {
		this.patronId = patronId;
		this.bookId = bookId;
    }

    @Override
    public void execute(Library library, LocalDate currentDate) throws LibraryException {
		LocalDate dueDate = currentDate.plusDays(library.getLoanPeriod());
		Book book = library.getBookByID(bookId);
		Patron patron = library.getPatronByID(patronId);

		if (book.getDeleted()) {
			throw new LibraryException("There is no such book with that ID.");
		}

		patron.renewBook(book, dueDate);
    }
}