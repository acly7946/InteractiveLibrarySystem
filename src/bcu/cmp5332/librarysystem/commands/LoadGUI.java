package bcu.cmp5332.librarysystem.commands;

import bcu.cmp5332.librarysystem.gui.MainWindow;
import bcu.cmp5332.librarysystem.model.Library;
import bcu.cmp5332.librarysystem.main.LibraryException;
import java.time.LocalDate;

/**
 * Starts the GUI of the application.
 *
 * <p>
 * It is an implementation of the {@link Command} interface and its
 * {@link execute execute(Library, LocalDate)} method creates a new
 * {@link MainWindow}
 * object that launches the GUI of
 * the application.
 * </p>
 *
 * <p>
 * It is created and executed by the
 * {@link bcu.cmp5332.librarysystem.main.CommandParser CommandParser} when the
 * "loadgui" command is
 * given by the user.
 * </p>
 */
public class LoadGUI implements Command {

	@Override
	public void execute(Library library, LocalDate currentDate) throws LibraryException {
		new MainWindow(library);
	}

}
