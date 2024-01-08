package bcu.cmp5332.librarysystem.data;

import bcu.cmp5332.librarysystem.model.Library;
import bcu.cmp5332.librarysystem.model.Loan;
import bcu.cmp5332.librarysystem.model.Patron;
import bcu.cmp5332.librarysystem.main.LibraryException;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.util.Scanner;

public class HistoryDataManager implements DataManager {

    private final String RESOURCE = "./resources/data/history.txt";

    @Override
    public void loadData(Library library) throws IOException, LibraryException {
        try (Scanner sc = new Scanner(new File(RESOURCE))) {
            int line_idx = 1;
            while (sc.hasNextLine()) {
                String line = sc.nextLine();
                String[] properties = line.split(SEPARATOR, -1);
                try {
                    int patronId = Integer.parseInt(properties[0]);
					int bookId = Integer.parseInt(properties[1]);
					LocalDate startDate = LocalDate.parse(properties[2]);
					LocalDate dueDate = LocalDate.parse(properties[3]);
					LocalDate returnDate = LocalDate.parse(properties[4]);
					library.getPatronByID(patronId).addLoanHistory(new Loan(library.getPatronByID(patronId), library.getBookByID(bookId), startDate, dueDate, returnDate));
                } catch (NumberFormatException ex) {
                    throw new LibraryException("Unable to parse loan history on line " + line_idx
                        + "\nError: " + ex);
                }
                line_idx++;
            }
        }
    }

    @Override
    public void storeData(Library library) throws IOException {
        try (PrintWriter out = new PrintWriter(new FileWriter(RESOURCE))) {
            for (Patron patron : library.getPatrons()) {
				for (Loan loan : patron.getLoanHistory()) {
					out.print(loan.getPatron().getId() + SEPARATOR);
					out.print(loan.getBook().getId() + SEPARATOR);
					out.print(loan.getStartDate() + SEPARATOR);
					out.print(loan.getDueDate() + SEPARATOR);
					out.print(loan.getReturnDate() + SEPARATOR);
					out.println();
				}
            }
        }
    }
}
