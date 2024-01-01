package bcu.cmp5332.librarysystem.commands;

import bcu.cmp5332.librarysystem.model.Library;
import bcu.cmp5332.librarysystem.model.Patron;
import bcu.cmp5332.librarysystem.main.LibraryException;

import java.time.LocalDate;
import java.util.List;

/**
 * Shows a list of all patrons of the library to the user.
 *
 * <p>
 * It is an implementation of the {@link Command} interface and its
 * {@link execute execute(Library, LocalDate)} method displays a list of the
 * library patrons to the user. At the
 * end of the list, the total amount of patrons in the library is also displayed
 * to the user.
 * </p>
 *
 * <p>
 * It is created and executed by the
 * {@link bcu.cmp5332.librarysystem.main.CommandParser CommandParser} when the
 * "listpatrons"
 * command is given by the user.
 * </p>
 *
 * @see Command
 * @see execute execute(Library, LocalDate)
 * @see bcu.cmp5332.librarysystem.main.CommandParser CommandParser
 */
public class ListPatrons implements Command {

	/**
	 *
	 * @param library     the library system
	 * @param currentDate the current date
	 * @throws LibraryException if there is an error executing the command
	 */
	@Override
	public void execute(Library library, LocalDate currentDate) throws LibraryException {
		List<Patron> patrons = library.getPatrons();
		int deletedPatrons = 0;
		for (Patron patron : patrons) {
			if (patron.getDeleted()) {
				deletedPatrons++;
				continue;
			}
			System.out.println(patron.getDetailsShort());
		}
		System.out.println(patrons.size() - deletedPatrons + " patron(s)");
	}
}
