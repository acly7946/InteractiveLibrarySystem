package bcu.cmp5332.librarysystem.data;

import bcu.cmp5332.librarysystem.model.Library;
import bcu.cmp5332.librarysystem.model.Patron;
import bcu.cmp5332.librarysystem.main.LibraryException;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

/**
 * PatronDataManager class is used to {@link loadData load}
 * and {@link storeData store}
 * Patron data using the text file storage.
 *
 * <p>
 * It is an implementation of the {@link DataManager} interface. It has a
 * RESOURCE field
 * that holds the path to the text file (patrons.txt) that will be used as
 * storage for the Patron data.
 * </p>
 *
 * <p>
 * The {@link loadData loadData(Library)} method loads data from patrons.txt
 * file to the library
 * and the {@link storeData storeData(Library)} method stores the patron data
 * that exist
 * in the
 * library to patrons.txt file.
 * </p>
 *
 * @see loadData loadData(Library)
 * @see storeData storeData(Library)
 * @see RESOURCE
 * @see DataManager
 * @see Library
 * @see Patron
 */
public class PatronDataManager implements DataManager {

	private final String RESOURCE = "./resources/data/patrons.txt";

	/**
	 * Load data from the file storage to the library.
	 *
	 * <p>
	 * The method opens the file that holds the patron data using the RESOURCE field
	 * and creates a Scanner object to read it. Then it reads each line and splits
	 * it in parts using the predefined {@link DataManager#SEPARATOR} (::). The
	 * result is an
	 * array of strings with each of the strings representing a different
	 * {@link Patron}
	 * property. Then the function assigns each of the string parts to its
	 * corresponding property (the properties are {@link storeData stored} in a
	 * specific predefined
	 * order) of a {@link Patron} object. Finally the new Patron is
	 * {@link bcu.cmp5332.librarysystem.commands.AddPatron added} to the library. If
	 * the patron id can't be parsed to an integer a {@link LibraryException} is
	 * thrown.
	 * </p>
	 *
	 * @param library the library object where the data will be loaded to
	 * @throws IOException      if the file is not found or can't be read.
	 * @throws LibraryException if the id of the patron that is being read can't be
	 *                          parsed to an integer
	 * @see RESOURCE
	 * @see Scanner
	 * @see String#split(String, int)
	 * @see DataManager#SEPARATOR
	 * @see #storeData(Library)
	 * @see Library#addPatron(Patron)
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
					String name = properties[2];
					String phone = properties[3];
					String email = properties[4];
					Patron patron = new Patron(id, deleted, name, phone, email);
					library.addPatron(patron);
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
	 * The method creates a new {@link FileWriter} object using the {@link RESOURCE} field and then
	 * creates a {@link PrintWriter} that will be used to write characters to the
	 * FileWriter. The PrintWriter is initialised using the try-with-resources
	 * statement to take care of properly closing the resources. Then for each
	 * patron that is found on the library's patron list, a new line is written to
	 * the file containing the Patron properties in a specific order. After each
	 * property, the predefined {@link DataManager#SEPARATOR} is added.
	 * </p>
	 *
	 * @param library the library object whose data will be stored in the file
	 * @throws IOException if the file is corrupt or can't be written
	 * @see RESOURCE
	 * @see FileWriter
	 * @see PrintWriter
	 * @see Library#getPatrons()
	 * @see DataManager#SEPARATOR
	 */
	@Override
	public void storeData(Library library) throws IOException {
		try (PrintWriter out = new PrintWriter(new FileWriter(RESOURCE))) {
			for (Patron patron : library.getPatrons()) {
				out.print(patron.getId() + SEPARATOR);
				out.print(patron.getDeleted() + SEPARATOR);
				out.print(patron.getName() + SEPARATOR);
				out.print(patron.getPhone() + SEPARATOR);
				out.print(patron.getEmail() + SEPARATOR);
				out.println();
			}
		}
	}
}
