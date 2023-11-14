package bcu.cmp5332.librarysystem.commands;

import bcu.cmp5332.librarysystem.main.LibraryException;
import bcu.cmp5332.librarysystem.model.Library;
import bcu.cmp5332.librarysystem.model.Patron;

import java.time.LocalDate;

public class BorrowBook implements Command {

	private final int patronId;
	private final int bookId;

    public BorrowBook(int patronId, int bookId) {
		this.patronId = patronId;
		this.bookId = bookId;
    }

    @Override
    public void execute(Library library, LocalDate currenDate) throws LibraryException {
    }
}