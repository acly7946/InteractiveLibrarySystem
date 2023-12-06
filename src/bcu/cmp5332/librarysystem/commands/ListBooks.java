package bcu.cmp5332.librarysystem.commands;

import bcu.cmp5332.librarysystem.model.Book;
import bcu.cmp5332.librarysystem.model.Library;
import bcu.cmp5332.librarysystem.main.LibraryException;

import java.time.LocalDate;
import java.util.List;

public class ListBooks implements Command {

    @Override
    public void execute(Library library, LocalDate currentDate) throws LibraryException {
        List<Book> books = library.getBooks();
		int deletedBooks = 0;
        for (Book book : books) {
			if (book.getDeleted()) {
				deletedBooks++;
				continue;
			}
            System.out.println(book.getDetailsShort());
        }
        System.out.println(books.size()-deletedBooks + " book(s)");
    }
}
