package bcu.cmp5332.librarysystem.commands;

import bcu.cmp5332.librarysystem.model.Library;

import java.time.LocalDate;

/**
 * Displays a help message to the user.
 *
 * <p>
 * It is an implementation of the {@link Command} interface and its
 * {@link execute execute(Library, LocalDate)} method prints the
 * {@link Command#HELP_MESSAGE Command.HELP_MESSAGE} to the console.
 * </p>
 *
 * <p>
 * It is created and executed by the {@link CommandParser} when the "help" command is
 * given by the user.
 * </p>
 *
 * @see Command
 * @see execute execute(Library, LocalDate)
 * @see Command#HELP_MESSAGE Command.HELP_MESSAGE
 * @see CommandParser
 */
public class Help implements Command {

	@Override
	public void execute(Library library, LocalDate currentDate) {
		System.out.println(Command.HELP_MESSAGE);
	}
}
