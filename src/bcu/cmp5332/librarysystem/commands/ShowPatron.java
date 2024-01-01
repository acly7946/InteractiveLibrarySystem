package bcu.cmp5332.librarysystem.commands;

import bcu.cmp5332.librarysystem.model.Library;
import bcu.cmp5332.librarysystem.model.Patron;
import bcu.cmp5332.librarysystem.main.LibraryException;

import java.time.LocalDate;

/**
 * Shows detailed information about a specific patron.
 *
 * <p>
 * It is an implementation of the {@link Command} interface and its
 * {@link execute execute(Library, LocalDate)} method displays detailed
 * information about a {@link Patron} to the user.
 * If the patron has books on loan, the method should display information about
 * these books as well as the due date for returning each one of them. In
 * addition, the method should return the total number of books that the patron
 * has on loan.
 * </p>
 *
 * <p>
 * It is created and executed by the
 * {@link bcu.cmp5332.librarysystem.main.CommandParser CommandParser} when the
 * "showpatron" command
 * is given by the user.
 * </p>
 *
 * <p>
 * The class has a {@link ShowPatron#ShowPatron constructor} to initialise the
 * fields needed to execute the
 * command.
 * </p>
 *
 * @see Command
 * @see execute execute(Library, LocalDate)
 * @see bcu.cmp5332.librarysystem.model.Patron Patron
 * @see bcu.cmp5332.librarysystem.main.CommandParser CommandParser
 * @see ShowBook ShowBook(int)
 */
public class ShowPatron implements Command {

	private final int patronId;

	/**
	 * Create a new ShowPatron instance.
	 *
	 * <p>
	 * The constructor initialises a ShowPatron object using the fields needed to
	 * find a patron that exists in the library.
	 * </p>
	 *
	 * @param patronId the id of the patron whose details will be displayed
	 */
	public ShowPatron(int patronId) {
		this.patronId = patronId;
	}

	@Override
	public void execute(Library library, LocalDate currentDate) throws LibraryException {
		Patron patron = library.getPatronByID(patronId);
		if (patron.getDeleted()) {
			throw new LibraryException("There is no such patron with that ID.");
		}
		System.out.println(patron.getDetailsLong());
	}
}