package bcu.cmp5332.librarysystem.commands;

import bcu.cmp5332.librarysystem.model.Library;
import bcu.cmp5332.librarysystem.main.LibraryException;

import java.time.LocalDate;

public class RemovePatron implements Command {

	private final int patronId;

	public RemovePatron(int patronId) {
		this.patronId = patronId;
	}

    @Override
    public void execute(Library library, LocalDate currentDate) throws LibraryException {
		library.removePatron(patronId);
		System.out.println("Patron " + patronId + " removed");
    }
}
