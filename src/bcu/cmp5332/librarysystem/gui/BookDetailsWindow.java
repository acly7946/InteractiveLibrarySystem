package bcu.cmp5332.librarysystem.gui;

import bcu.cmp5332.librarysystem.model.Book;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.UIManager;
import java.util.List;

public class BookDetailsWindow extends JFrame implements ActionListener {

    private MainWindow mw;
	private List<Book> books;

    private JButton closeBtn = new JButton("Close");

public BookDetailsWindow(MainWindow mw, List<Book> books) {
	this.mw = mw;
	this.books = books;
	initialize();
}

    /**
     * Initialize the contents of the frame.
     */
    private void initialize() {

        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception ex) {

        }

        setTitle("Books on loan");

        setSize(400, 300);
        JPanel topPanel = new JPanel();
		JTable table = new JTable();

		topPanel.setLayout(new BorderLayout());

        // headers for the table
        String[] columns = new String[]{"Title", "Author", "Publisher", "Pub. Year", "Status"};

        Object[][] data = new Object[books.size()][6];
        for (int i = 0; i < books.size(); i++) {
            Book book = books.get(i);

            data[i][0] = book.getTitle();
            data[i][1] = book.getAuthor();
            data[i][2] = book.getPublisher();
            data[i][3] = book.getPublicationYear();
            data[i][4] = book.getStatus();
        }
        table = new JTable(data, columns);
		topPanel.add(new JScrollPane(table), BorderLayout.CENTER);

        JPanel bottomPanel = new JPanel();
        bottomPanel.setLayout(new GridLayout(0, 1));
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