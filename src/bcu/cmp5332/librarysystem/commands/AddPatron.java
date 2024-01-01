package bcu.cmp5332.librarysystem.commands;

import bcu.cmp5332.librarysystem.main.LibraryException;
import bcu.cmp5332.librarysystem.model.Library;
import bcu.cmp5332.librarysystem.model.Patron;

import java.time.LocalDate;

/**
 * Adds a new patron to the library.
 *
 * <p>
 * It is an implementation of the {@link Command} interface and its
 * {@link execute execute(Library, LocalDate)} method adds a new {@link Patron}
 * to the
 * library.
 * </p>
 *
 * <p>
 * It is created and executed by the {@link CommandParser} when the "addpatron"
 * command
 * is given by the user.
 * </p>
 *
 * <p>
 * The class has a {@link AddPatron#AddPatron constructor} to initialise the
 * fields needed to execute the
 * command.
 * </p>
 *
 * @see Command
 * @see execute execute(Library, LocalDate)
 * @see Patron
 * @see bcu.cmp5332.librarysystem.main.CommandParser CommandParser
 * @see AddPatron AddPatron(String, String)
 */
public class AddPatron implements Command {

	private final String name;
	private final String phone;
	private final String email;

	/**
	 * Create a new AddPatron instance.
	 *
	 * <p>
	 * The constructor initialises an AddPatron object using the necessary fields
	 * needed for adding a new patron to the library.
	 * </p>
	 *
	 * @param name  the name of the new patron
	 * @param phone the phone number of the new patron
	 * @param email the email address of the new patron
	 */
	public AddPatron(String name, String phone, String email) {
		this.name = name;
		this.phone = phone;
		this.email = email;
	}

	/**
	 * Create and add a new Patron to the library.
	 *
	 * <p>
	 * The method should create a new unique id for the new patron and then create a
	 * new Patron object using this id and the information given by the user (patron
	 * name and phone number) via the command line. Finally, the method adds this
	 * patron to the library and prints a notification to the user that the patron
	 * was successfully added.
	 * </p>
	 *
	 * @param library     the library system
	 * @param currentDate the current date
	 * @throws LibraryException if an error occurs while adding the patron
	 */
	@Override
	public void execute(Library library, LocalDate currentDate) throws LibraryException {
		int maxId = 0;
		if (library.getPatrons().size() > 0) {
			int lastIndex = library.getPatrons().size() - 1;
			maxId = library.getPatrons().get(lastIndex).getId();
		}
		Patron patron = new Patron(++maxId, false, name, phone, email);
		library.addPatron(patron);
		System.out.println("Patron #" + patron.getId() + " added.");
	}
}
