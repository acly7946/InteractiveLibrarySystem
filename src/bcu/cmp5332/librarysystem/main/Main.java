package bcu.cmp5332.librarysystem.main;

import bcu.cmp5332.librarysystem.data.LibraryData;
import bcu.cmp5332.librarysystem.commands.Command;
import bcu.cmp5332.librarysystem.model.Library;

import java.io.*;
import java.time.LocalDate;

/**
 * The Main class of the application. The {@link main} method of the class gets
 * executed when the application is started.
 */
public class Main {

	/**
	 *
	 * This method gets executed when the application starts. The method first loads
	 * the stored data from the file storage to a {@link Library} object using the
	 * {@link LibraryData} class. Then it displays a prompt and waits for the user's input
	 * at the command line. When a command is given by the user, it gets passed to
	 * the {@link CommandParser} class to be parsed and checked for its validity. If the
	 * command is invalid the CommandParser throws a {@link LibraryException} which is
	 * caught and its message gets displayed to the user. If the command is valid,
	 * the CommandParser return an implementation of the {@link Command} interface which is
	 * executed. If the command "exit" is given by the user, the method stores all
	 * data from the Library object to the file storage and then exits.
	 *
	 * @param args
	 * @throws IOException      if there is an error when reading the input from the
	 *                          user
	 * @throws LibraryException if something goes wrong when loading or storing data
	 *                          to
	 *                          the file storage
	 * @see Library
	 * @see LibraryData
	 * @see Command
	 * @see CommandParser
	 *
	 */
	public static void main(String[] args) throws IOException, LibraryException {

        Library library = LibraryData.load();
		Library libraryBackup = LibraryData.load();

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		System.out.println("Library system");
		System.out.println("Enter 'help' to see a list of available commands.");
		while (true) {
			System.out.print("> ");
			String line = br.readLine();
			if (line.equals("exit")) {
				break;
			}

            try {
				libraryBackup = library;  // update backup
                Command command = CommandParser.parse(line);
                command.execute(library, LocalDate.now());
				LibraryData.store(library);
            } catch (Exception ex) {
				System.out.println("An error occurred: " + ex.getMessage());
				library = libraryBackup; // restore from backup
			}
        }
        System.exit(0);
    }
}
