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
import java.time.format.DateTimeFormatter;
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
					int bookId = Integer.parseInt(properties[1]);
					int patronId = Integer.parseInt(properties[2]);
					//DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
					LocalDate startDate = LocalDate.parse(properties[3]);
					LocalDate dueDate = LocalDate.parse(properties[4]);
					Patron patron = library.getPatronByID(patronId);
					Book book = library.getBookByID(bookId);
					Loan loan = new Loan(patron, book, startDate, dueDate);
					book.setLoan(loan);
				} catch (NumberFormatException ex) {
					throw new LibraryException("Unable to parse book id " + properties[0] + " on line " + line_idx
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
                out.print(book.getId() + SEPARATOR);
                out.print(book.getTitle() + SEPARATOR);
                out.print(book.getAuthor() + SEPARATOR);
                out.print(book.getPublisher() + SEPARATOR);
                out.print(book.getPublicationYear() + SEPARATOR);
                out.println();
            }
        }
	}

}
