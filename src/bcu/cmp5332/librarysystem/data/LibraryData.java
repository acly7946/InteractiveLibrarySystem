package bcu.cmp5332.librarysystem.data;

import bcu.cmp5332.librarysystem.model.Library;
import bcu.cmp5332.librarysystem.main.LibraryException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * LibraryData class is responsible for loading data to and storing data from
 * the library, using the text file storage.
 *
 * <p>
 * The class has a static list of objects that implement the {@link DataManager}
 * interface. The list gets populated by a static block of code (runs only once
 * when the object is loaded to memory) that adds different implementations of
 * the DataManager interface - one for each different entity of the library
 * system (e.g. {@link BookDataManager} is an implementation that {@link load
 * loads} and {@link store stores} data
 * for {@link Book} entities using the text file storage).
 * </p>
 *
 * <p>
 * The class has the static methods {@link load load()} to load all data to the
 * library and
 * {@link store store(Library)} to store all data from the library to the text
 * file storage.
 * </p>
 *
 * @see load()
 * @see store store(Library)
 * @see DataManager
 * @see BookDataManager
 *
 */
public class LibraryData {

	private static final List<DataManager> dataManagers = new ArrayList<>();

    // runs only once when the object gets loaded to memory
    static {
        dataManagers.add(new BookDataManager());
        dataManagers.add(new PatronDataManager());
        dataManagers.add(new LoanDataManager());
		dataManagers.add(new HistoryDataManager());
    }

	/**
	 * Loads data from all data managers to the library.
	 *
	 * <p>
	 * This static method iterates through the list of data managers and runs their
	 * implementation of the DataManager.loadData(Library) method to load data for
	 * all the entities from the file storage to a Library object.
	 * </p>
	 *
	 * @return the new Library object loaded with data for all entities
	 * @throws LibraryException if an exception is thrown by the execution of any of
	 *                          the different loadData function implementations
	 * @throws IOException      if an exception is thrown by the execution of any of
	 *                          the different loadData function implementations
	 */
	public static Library load() throws LibraryException, IOException {

		Library library = new Library();
		for (DataManager dm : dataManagers) {
			dm.loadData(library);
		}
		return library;
	}

	/**
	 * Store data from the library to the file storage using all data managers.
	 *
	 * <p>
	 * This static method iterates through the list of data managers and runs their
	 * implementation of the DataManager.loadData(Library) method to load data for
	 * all the entities from the file storage to a Library object.
	 * </p>
	 *
	 * @param library the Library object whose data will be written to the text file
	 *                storage
	 * @throws IOException if an exception is thrown by the execution of any of the
	 *                     different storeData function implementations
	 */
	public static void store(Library library) throws IOException {

		for (DataManager dm : dataManagers) {
			dm.storeData(library);
		}
	}

}
