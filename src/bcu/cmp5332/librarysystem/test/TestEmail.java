package bcu.cmp5332.librarysystem.test;

import static org.junit.Assert.assertEquals;

import bcu.cmp5332.librarysystem.main.LibraryException;
import bcu.cmp5332.librarysystem.model.Patron;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

import org.junit.Test;

public class TestEmail {

	Patron patron = new Patron(-1, "Test", "123", "Email@Email.com");

	@Test
	public void testGetEmail() throws LibraryException, IOException {
		assertEquals(patron.getEmail(), "Email@Email.com");
	}

	@Test
	public void testSetEmail() throws LibraryException, IOException {
		patron.setEmail("NEWMAIL@NEWMAIL.com");
		assertEquals(patron.getEmail(), "NEWMAIL@NEWMAIL.com");
	}

	@Test
	public void testSavedFormat() throws FileNotFoundException, LibraryException {
		final String RESOURCE = "./resources/data/patrons.txt";
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
