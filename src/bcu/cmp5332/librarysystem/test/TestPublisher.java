package bcu.cmp5332.librarysystem.test;

import bcu.cmp5332.librarysystem.model.Book;

import static org.junit.Assert.assertEquals;

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

}
