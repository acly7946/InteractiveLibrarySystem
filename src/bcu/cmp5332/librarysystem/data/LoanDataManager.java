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

/**
 * LoanDataManager class is used to {@link loadData load} and
 * {@link storeData store}
 * {@link Loan} data using the text file
 * storage.
 *
 * <p>
 * It is an implementation of the {@link DataManager} interface. It has a
 * RESOURCE field
 * that holds the path to the text file (loans.txt) that will be used as storage
 * for the Loan data.
 * </p>
 *
 * <p>
 * The {@link loadData loadData(Library)} method loads data from loans.txt file
 * to the library
 * and the {@link storeData storeData(Library)} method stores the loan data that
 * exist in
 * the
 * library to loans.txt file.
 * </p>
 *
 * @see loadData loadData(Library)
 * @see storeData storeData(Library)
 * @see RESOURCE
 * @see DataManager
 * @see Library
 * @see Loan
 */
public class LoanDataManager implements DataManager {

	/**
	 * The path to loans.txt file that is used as storage for loan data.
	 */
	public final String RESOURCE = "./resources/data/loans.txt";

	/**
	 *
	 * Load data from the file storage to the library.
	 *
	 * <p>
	 * The method opens the file that holds the loan data using the RESOURCE field
	 * and creates a Scanner object to read it. Then it reads each line and splits
	 * it in parts using the predefined {@link DataManager#SEPARATOR} (::). The
	 * result is an
	 * array of strings with each of the strings representing a different
	 * {@link Loan}
	 * property. Then the function parses each of the string parts to its
	 * corresponding property (the properties are {@link storeData stored} in a
	 * specific predefined
	 * order) of a {@link Loan} object. Finally the new Loan is added to the
	 * corresponding
	 * Book and the Book is added to the corresponding Patron's book list. If the
	 * patron id can't be parsed to an integer a LibraryException is thrown.
	 * </p>
	 *
	 * @param library the library object where the data will be loaded to
	 * @throws IOException      if the file is not found or can't be read.
	 * @throws LibraryException if the id of the loan that is being read can't be
	 *                          parsed to an integer or if any of the
	 *                          Loan.startDate or Loan.dueDate can't be parsed to a
	 *                          LocalDate
	 * @see RESOURCE
	 * @see Scanner
	 * @see String#split(String, int)
	 * @see DataManager#SEPARATOR
	 * @see storeData storeData(Library)
	 * @see Loan
	 * @see Book#setLoan(Loan)
	 * @see Patron#addBook(Book)
	 * @see LocalDate#parse(CharSequence)
	 */
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
					LocalDate returnDate = LocalDate.parse(properties[4]);
					Patron patron = library.getPatronByID(patronId);
					Book book = library.getBookByID(bookId);
					Loan loan = new Loan(patron, book, startDate, dueDate, returnDate);
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

	/**
	 * Store data from the library to the text file storage.
	 *
	 * The method creates a new FileWriter object using the RESOURCE field and then
	 * creates a PrintWriter that will be used to write characters to the
	 * FileWriter. The PrintWriter is initialised using the try-with-resources
	 * statement to take care of properly closing the resources. Then for each loan
	 * that is found on each of the patrons book list, a new line is written to the
	 * file containing the Loan properties in a specific order. After each property,
	 * the predefined DataManager.SEPARATOR is added.
	 *
	 * @param library the library object whose data will be stored in the file
	 * 			  storage
	 * @throws java.io.IOException if the file is corrupt or can't be written
	 * @see RESOURCE
	 * @see FileWriter
	 * @see PrintWriter
	 * @see Patron.getBooks()
	 * @see DataManager#SEPARATOR
	 *
	 */
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
					out.print(loan.getReturnDate().toString() + SEPARATOR);
					out.println();
				}
			}
		}
	}

}
