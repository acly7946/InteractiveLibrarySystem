package bcu.cmp5332.librarysystem.commands;

import bcu.cmp5332.librarysystem.model.Library;
import bcu.cmp5332.librarysystem.main.LibraryException;

import java.time.LocalDate;

/**
 * Removes a patron from the library.
 *
 * <p>
 * It is an implementation of the {@link Command} interface and its
 * {@link execute execute(Library, LocalDate)} method removes a {@link Patron}
 * from the library.
 * </p>
 *
 * <p>
 * It is created and executed by the
 * {@link bcu.cmp5332.librarysystem.main.CommandParser CommandParser} when the
 * "removepatron"
 * command is given by the user.
 * </p>
 *
 * <p>
 * The class has a {@link RemovePatron#RemovePatron constructor} to initialise
 * the
 * fields needed to execute the command
 * </p>
 *
 * @see Command
 * @see execute execute(Library, LocalDate)
 * @see bcu.cmp5332.librarysystem.model.Patron Patron
 * @see bcu.cmp5332.librarysystem.main.CommandParser CommandParser
 * @see RemoveBook RemovePatron(String, String)
 */
public class RemovePatron implements Command {

	private final int patronId;

	/**
	 * Create a new RemovePatron instance.
	 *
	 * <p>
	 * The constructor initialises a RemovePatron object using the necessary fields
	 * to remove a book from the library.
	 * </p>
	 *
	 * @param patronId the ID of the patron to be removed
	 */
	public RemovePatron(int patronId) {
		this.patronId = patronId;
	}

	/**
	 * Remove a patron from the library
	 *
	 * @param library     the library from which the patron is to be removed
	 * @param currentDate the current date
	 * @throws LibraryException if an error occurs while removing the patron
	 */
	@Override
	public void execute(Library library, LocalDate currentDate) throws LibraryException {
		library.removePatron(patronId);
		System.out.println("Patron " + patronId + " removed");
	}
}
