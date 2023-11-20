package bcu.cmp5332.librarysystem.data;

import bcu.cmp5332.librarysystem.main.LibraryException;
import bcu.cmp5332.librarysystem.model.Book;
import bcu.cmp5332.librarysystem.model.Library;
import bcu.cmp5332.librarysystem.model.Loan;
import bcu.cmp5332.librarysystem.model.Patron;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.util.Scanner;

public class LoanDataManager implements DataManager {

	public final String RESOURCE = "./resources/data/loans.txt";

	@Override
	public void loadData(Library library) throws IOException, LibraryException {
		try (Scanner sc = new Scanner(new File(RESOURCE))) {
			int line_idx = 1;
			while (sc.hasNextLine()) {
				String line = sc.nextLine();
				String[] properties = line.split(SEPARATOR, -1);
				try {
					int bookId = Integer.parseInt(properties[0]);
					int patronId = Integer.parseInt(properties[1]);
					LocalDate startDate = LocalDate.parse(properties[2]);
					LocalDate dueDate = LocalDate.parse(properties[3]);
					Patron patron = library.getPatronByID(patronId);
					Book book = library.getBookByID(bookId);
					Loan loan = new Loan(patron, book, startDate, dueDate);
					book.setLoan(loan);
					patron.addBook(book);
				} catch (NumberFormatException ex) {
					throw new LibraryException("Unable to parse loan on line " + line_idx
						+ "\nError: " + ex);
				}
				line_idx++;
			}
		}
	}

	@Override
	public void storeData(Library library) throws IOException {
        try (PrintWriter out = new PrintWriter(new FileWriter(RESOURCE))) {
            for (Book book : library.getBooks()) {
				if (book.isOnLoan()) {
					Loan loan = book.getLoan();
					out.print(book.getId() + SEPARATOR);
					out.print(loan.getPatron().getId() + SEPARATOR);
					out.print(loan.getStartDate().toString() + SEPARATOR);
					out.print(loan.getDueDate().toString() + SEPARATOR);
				}
            }
        }
	}

}
