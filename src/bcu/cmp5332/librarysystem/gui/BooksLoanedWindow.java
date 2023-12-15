package bcu.cmp5332.librarysystem.gui;

import bcu.cmp5332.librarysystem.model.Book;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.UIManager;

import java.util.ArrayList;
import java.util.List;

public class BooksLoanedWindow extends JFrame implements ActionListener {

    private MainWindow mw;
	private List<Book> patronBooks;
	private List<Book> booksList;
	private List<Book> deletedBooks;
    private JButton closeBtn = new JButton("Close");

	public BooksLoanedWindow(MainWindow mw, List<Book> books) {
		this.mw = mw;
		this.patronBooks = books;
		initialize();
	}

    /**
     * Initialize the contents of the frame.
     */
    private void initialize() {

        JPanel topPanel = new JPanel();
		JPanel bottomPanel = new JPanel();
		JTable table = new JTable();
		String[] columns;
		Object[][] data;

        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception ex) {

        }
        setTitle("Books on loan");
        setSize(400, 300);
		topPanel.setLayout(new BorderLayout());
        bottomPanel.setLayout(new GridLayout(0, 1));

		booksList = new ArrayList<>(patronBooks);
		deletedBooks = new ArrayList<>();
		for (Book book : booksList) {
			if (book.getDeleted()) {
				deletedBooks.add(book);
			}
		}
		booksList.removeAll(deletedBooks);

        // headers for the table
        columns = new String[]{"Title", "Author", "Publisher", "Pub. Year", "Status"};
        data = new Object[booksList.size()][6];
        for (int i = 0; i < booksList.size(); i++) {
            Book book = booksList.get(i);
            data[i][0] = book.getTitle();
            data[i][1] = book.getAuthor();
            data[i][2] = book.getPublisher();
            data[i][3] = book.getPublicationYear();
            data[i][4] = book.getStatus();
        }

        table = new JTable(data, columns);
		topPanel.add(new JScrollPane(table), BorderLayout.CENTER);
        bottomPanel.add(closeBtn);
        closeBtn.addActionListener(this);

        this.getContentPane().add(topPanel, BorderLayout.CENTER);
        this.getContentPane().add(bottomPanel, BorderLayout.SOUTH);
        setLocationRelativeTo(mw);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == closeBtn) {
            this.setVisible(false);
        }

    }
}