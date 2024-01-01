package bcu.cmp5332.librarysystem.data;

import bcu.cmp5332.librarysystem.model.Library;
import bcu.cmp5332.librarysystem.main.LibraryException;
import java.io.IOException;

/**
 * An object that loads and stores data to the text file storage.
 *
 * <p>
 * The interface has a field that holds the value of a predefined
 * {@link DataManager#SEPARATOR sparator} that
 * will be used to separate different entities' properties when saved as a line
 * of text. It also provides two methods, one that can be used to
 * {@link loadData load} data from
 * the text file storage to a {@link Library} object and one that can be used to
 * {@link storeData store}
 * data from a {@link Library} object to the text file system.
 * </p>
 *
 * <p>
 * Different implementations can exist for this interface and each one can be
 * used to load and store data for different entities (e.g
 * {@link BookDataManager} can
 * be used to load and store book data, {@link PatronDataManager} for patron
 * data,
 * etc.).
 * </p>
 *
 * @see SEPARATOR
 * @see loadData loadData(Library)
 * @see storeData storeData(Library)
 * @see BookDataManager
 */
public interface DataManager {

	/**
	 * The separator that will be used to separate the values of the different
	 * object properties when saved in a single line.
	 */
	public static final String SEPARATOR = "::";

	/**
	 * Load data from the file storage to the library.
	 *
	 * @param library the library object where the data will be loaded to
	 * @throws IOException      if something goes wrong while reading from the file
	 * @throws LibraryException implementing functions can throw this to send
	 *                          notifications to the user
	 */
	public void loadData(Library library) throws IOException, LibraryException;

	/**
	 * Store data from the library to the text file storage.
	 *
	 * @param library the library object whose data will be stored in the file
	 * @throws IOException if something goes wrong while writing to the file
	 */
	public void storeData(Library library) throws IOException;
}
