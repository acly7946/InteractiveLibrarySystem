package bcu.cmp5332.librarysystem.main;

import bcu.cmp5332.librarysystem.commands.LoadGUI;
import bcu.cmp5332.librarysystem.commands.RemoveBook;
import bcu.cmp5332.librarysystem.commands.RemovePatron;
import bcu.cmp5332.librarysystem.commands.ReturnBook;
import bcu.cmp5332.librarysystem.commands.RenewBook;
import bcu.cmp5332.librarysystem.commands.ShowBook;
import bcu.cmp5332.librarysystem.commands.ShowPatron;
import bcu.cmp5332.librarysystem.commands.ListBooks;
import bcu.cmp5332.librarysystem.commands.ListPatrons;
import bcu.cmp5332.librarysystem.commands.AddBook;
import bcu.cmp5332.librarysystem.commands.AddPatron;
import bcu.cmp5332.librarysystem.commands.BorrowBook;
import bcu.cmp5332.librarysystem.commands.Command;
import bcu.cmp5332.librarysystem.commands.Help;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * CommandParser class is used to parse the input given by the user through the
 * application's command line interface. The class has a static method
 * {@link parse(String) parse(String)} that accepts the input as String and
 * returns the appropriate
 * {@link Command} implementation that is meant to execute the command.
 *
 * @see parse(String)
 * @see Command
 */
public class CommandParser {

	/**
	 * Get the user input as {@link String} and returns the corresponding
	 * implementation of
	 * the {@link Command} interface based on that input. The input represents a
	 * line and
	 * contains the command together with the possible arguments that were given by
	 * the user.
	 *
	 * @param line the command given by the user as String
	 * @return the appropriate implementation of the Command interface
	 * @throws IOException      if something goes wrong when reading additional
	 *                          input
	 * @throws LibraryException if the command given by the user is invalid or does
	 *                          not exist
	 */
	public static Command parse(String line) throws IOException, LibraryException {
		try {
			String[] parts = line.split(" ", 3);
			String cmd = parts[0];

			if (cmd.equals("addbook")) {
				BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
				System.out.print("Title: ");
				String title = br.readLine();
				System.out.print("Author: ");
				String author = br.readLine();
				System.out.print("Publisher: ");
				String publisher = br.readLine();
				System.out.print("Publication Year: ");
				String publicationYear = br.readLine();

				return new AddBook(title, author, publisher, publicationYear);
			} else if (cmd.equals("addpatron")) {
				BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
				System.out.print("Name: ");
				String name = br.readLine();
				System.out.print("Phone: ");
				String phone = br.readLine();
				System.out.print("Email: ");
				String email = br.readLine();

				return new AddPatron(name, phone, email);
			} else if (cmd.equals("loadgui")) {
				return new LoadGUI();
			} else if (parts.length == 1) {
				if (line.equals("listbooks")) {
					return new ListBooks();
				} else if (line.equals("listpatrons")) {
					return new ListPatrons();
				} else if (line.equals("help")) {
					return new Help();
				}
			} else if (parts.length == 2) {
				int id = Integer.parseInt(parts[1]);

				if (cmd.equals("removebook")) {
					return new RemoveBook(id);
				} else if (cmd.equals("removepatron")) {
					return new RemovePatron(id);
				} else if (cmd.equals("showbook")) {
					return new ShowBook(id);
				} else if (cmd.equals("showpatron")) {
					return new ShowPatron(id);
				}
			} else if (parts.length == 3) {
				int patronID = Integer.parseInt(parts[1]);
				int bookID = Integer.parseInt(parts[2]);

				if (cmd.equals("borrow")) {
					return new BorrowBook(patronID, bookID);
				} else if (cmd.equals("renew")) {
					return new RenewBook(patronID, bookID);
				} else if (cmd.equals("return")) {
					return new ReturnBook(patronID, bookID);
				}
			}
		} catch (NumberFormatException ex) {

		}

		throw new LibraryException("Invalid command.");
	}
}
