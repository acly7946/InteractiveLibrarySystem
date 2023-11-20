package bcu.cmp5332.librarysystem.test;

import bcu.cmp5332.librarysystem.main.LibraryException;
import bcu.cmp5332.librarysystem.model.Book;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import org.junit.Test;

public class TestPublisher {

	Book book = new Book(-1, "Test", "Test", "PUBLISHER", "Year");

	@Test
	public void testGetPublisher() {
		assertEquals(book.getPublisher(), "PUBLISHER");
	}

	@Test
	public void testSetPublisher() {
		book.setPublisher("NEWPUBLISHER");
		assertEquals(book.getPublisher(), "NEWPUBLISHER");
	}

	@Test
	public void testSavedFormat() throws FileNotFoundException, LibraryException {
		final String RESOURCE = "./resources/data/books.txt";
		final String SEPARATOR = "::";

		try (Scanner sc = new Scanner(new File(RESOURCE))) {
            while (sc.hasNextLine()) {
                String line = sc.nextLine();
                String[] properties = line.split(SEPARATOR, -1);
				assertEquals(5, properties.length);
            }
        }
	}

}
