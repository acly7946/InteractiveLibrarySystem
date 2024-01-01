package bcu.cmp5332.librarysystem.data;

import bcu.cmp5332.librarysystem.model.Book;
import bcu.cmp5332.librarysystem.model.Library;
import bcu.cmp5332.librarysystem.main.LibraryException;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

/**
 * BookDataManager class is used to {@link loadData load}
 * and {@link storeData store} {@link Book} data
 * using the text file
 * storage.
 *
 * <p>
 * It is an implementation of the {@link DataManager} interface. It has a
 * RESOURCE field
 * that holds the path to the text file (books.txt) that will be used as storage
 * for the Book data.
 * </p>
 *
 * <p>
 * The {@link loadData loadData(Library)} method loads data from books.txt file
 * to the library
 * and the {@link storeData storeData(Library)} method stores the book data that
 * exist in the
 * library to books.txt file.
 * </p>
 *
 * @see loadData loadData(Library)
 * @see storeData storeData(Library)
 * @see RESOURCE
 * @see DataManager
 * @see Library
 * @see Book
 */
public class BookDataManager implements DataManager {

	private final String RESOURCE = "./resources/data/books.txt";

	/**
	 * Load data from the file storage to the library.
	 *
	 * <p>
	 * The method opens the file that holds the book data using the RESOURCE field
	 * and creates a Scanner object to read it. Then it reads each line and splits
	 * it in parts using the predefined {@link DataManager#SEPARATOR
	 * DataManager.SEPARATOR} (::). The
	 * result is an
	 * array of strings with each of the strings representing a different
	 * {@link Book}
	 * property. Then the function assigns each of the string parts to its
	 * corresponding property (the properties are {@link storeData stored} in a
	 * specific predefined
	 * order) of a {@link Book} object. Finally the new Book is
	 * {@link bcu.cmp5332.librarysystem.commands.AddBook
	 * added} to the library. If the
	 * book id can't be parsed to an integer a {@link LibraryException} is thrown.
	 * </p>
	 *
	 * @param library the library object where the data will be loaded to
	 *
	 * @throws IOException      if the file is not found or can't be read.
	 * @throws LibraryException if the id of the book that is being read can't be
	 *                          parsed to an integer
	 *
	 * @see RESOURCE
	 * @see Scanner
	 * @see String#split(String, int)
	 * @see DataManager#SEPARATOR
	 * @see storeData storeData(Library)
	 * @see Library#addBook(Book)
	 */
	@Override
	public void loadData(Library library) throws IOException, LibraryException {
		try (Scanner sc = new Scanner(new File(RESOURCE))) {
			int line_idx = 1;
			while (sc.hasNextLine()) {
				String line = sc.nextLine();
				String[] properties = line.split(SEPARATOR, -1);
				try {
					int id = Integer.parseInt(properties[0]);
					boolean deleted = Boolean.parseBoolean(properties[1]);
					String title = properties[2];
					String author = properties[3];
					String publisher = properties[4];
					String publicationYear = properties[5];
					Book book = new Book(id, deleted, title, author, publisher, publicationYear);
					library.addBook(book);
				} catch (NumberFormatException ex) {
					throw new LibraryException("Unable to parse book id " + properties[0] + " on line " + line_idx
							+ "\nError: " + ex);
				}
				line_idx++;
			}
		}
	}

	/**
	 * Store data from the library to the text file storage.
	 *
	 * <p>
	 * The method creates a new {@link FileWriter} object using the {@link RESOURCE}
	 * field and then
	 * creates a {@link PrintWriter} that will be used to write characters to the
	 * FileWriter. The PrintWriter is initialised using the try-with-resources
	 * statement to take care of properly closing the resources. Then for each book
	 * that is found on the library's book {@link Library.getBooks list}, a new
	 * line is written to the file
	 * containing the Book properties in a specific order. After each property, the
	 * predefined {@link DataManager#SEPARATOR} is added.
	 * </p>
	 *
	 * @param library the library object whose data will be stored in the file
	 *                storage
	 * @throws java.io.IOException if the file is corrupt or can't be written
	 * @see RESOURCE
	 * @see FileWriter
	 * @see PrintWriter
	 * @see Library.getBooks()
	 * @see DataManager#SEPARATOR
	 */
	@Override
	public void storeData(Library library) throws IOException {
		try (PrintWriter out = new PrintWriter(new FileWriter(RESOURCE))) {
			for (Book book : library.getBooks()) {
				out.print(book.getId() + SEPARATOR);
				out.print(book.getDeleted() + SEPARATOR);
				out.print(book.getTitle() + SEPARATOR);
				out.print(book.getAuthor() + SEPARATOR);
				out.print(book.getPublisher() + SEPARATOR);
				out.print(book.getPublicationYear() + SEPARATOR);
				out.println();
			}
		}
	}
}
