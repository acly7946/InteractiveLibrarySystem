package bcu.cmp5332.librarysystem.gui;

import bcu.cmp5332.librarysystem.model.Book;
import bcu.cmp5332.librarysystem.model.Library;
import bcu.cmp5332.librarysystem.model.Patron;

import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.UIManager;

public class MainWindow extends JFrame implements ActionListener {

    private JMenuBar menuBar;
    private JMenu adminMenu;
    private JMenu booksMenu;
    private JMenu membersMenu;

    private JMenuItem adminExit;

    private JMenuItem booksView;
    private JMenuItem booksAdd;
    private JMenuItem booksDel;
    private JMenuItem booksIssue;
    private JMenuItem booksReturn;

    private JMenuItem memView;
    private JMenuItem memAdd;
    private JMenuItem memDel;

    private Library library;

    public MainWindow(Library library) {

        initialize();
        this.library = library;
    }

    public Library getLibrary() {
        return library;
    }

    /**
     * Initialize the contents of the frame.
     */
    private void initialize() {

        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception ex) {

        }

        setTitle("Library Management System");

        menuBar = new JMenuBar();
        setJMenuBar(menuBar);

        //adding adminMenu menu and menu items
        adminMenu = new JMenu("Admin");
        menuBar.add(adminMenu);

        adminExit = new JMenuItem("Exit");
        adminMenu.add(adminExit);
        adminExit.addActionListener(this);

        // adding booksMenu menu and menu items
        booksMenu = new JMenu("Books");
        menuBar.add(booksMenu);

        booksView = new JMenuItem("View");
        booksAdd = new JMenuItem("Add");
        booksDel = new JMenuItem("Delete");
        booksIssue = new JMenuItem("Issue");
        booksReturn = new JMenuItem("Return");
        booksMenu.add(booksView);
        booksMenu.add(booksAdd);
        booksMenu.add(booksDel);
        booksMenu.add(booksIssue);
        booksMenu.add(booksReturn);
        for (int i = 0; i < booksMenu.getItemCount(); i++) {
            booksMenu.getItem(i).addActionListener(this);
        }

        // adding membersMenu menu and menu items
        membersMenu = new JMenu("Members");
        menuBar.add(membersMenu);

        memView = new JMenuItem("View");
        memAdd = new JMenuItem("Add");
        memDel = new JMenuItem("Delete");

        membersMenu.add(memView);
        membersMenu.add(memAdd);
        membersMenu.add(memDel);

        memView.addActionListener(this);
        memAdd.addActionListener(this);
        memDel.addActionListener(this);

        setSize(800, 500);

        setVisible(true);
        setAutoRequestFocus(true);
        toFront();
        setDefaultCloseOperation(EXIT_ON_CLOSE);
/* Uncomment the following line to not terminate the console app when the window is closed */
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

    }

/* Uncomment the following code to run the GUI version directly from the IDE */
//    public static void main(String[] args) throws IOException, LibraryException {
//        Library library = LibraryData.load();
//        new MainWindow(library);
//    }



    @Override
    public void actionPerformed(ActionEvent ae) {

		try {
			if (ae.getSource() == adminExit) {
				System.exit(0);
			} else if (ae.getSource() == booksView) {
				displayBooks();
			} else if (ae.getSource() == booksAdd) {
				new AddBookWindow(this);
			} else if (ae.getSource() == booksDel) {

			} else if (ae.getSource() == booksIssue) {

			} else if (ae.getSource() == booksReturn) {

			} else if (ae.getSource() == memView) {
				displayPatrons();
			} else if (ae.getSource() == memAdd) {
				new AddPatronWindow(this);
			} else if (ae.getSource() == memDel) {

			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(this, e, "Error", JOptionPane.ERROR_MESSAGE);
		}
    }

    public void displayBooks() {
        List<Book> libraryData = library.getBooks();
        List<Book> booksList = new ArrayList<>(libraryData);
		List<Book> deletedBooks = new ArrayList<>();
		JTable table = new JTable();
		String[] columns;
		Object[][] data;

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
		table.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent mouseEvent) {
				JTable table =(JTable) mouseEvent.getSource();
				Point point = mouseEvent.getPoint();
				int row = table.rowAtPoint(point);
				if ((mouseEvent.getClickCount() == 2)) {
					if (booksList.get(row).isOnLoan()) {
						new PatronDetailsWindow(MainWindow.this, booksList.get(row).getLoan().getPatron());
					}
				}
			}
		});
        this.getContentPane().removeAll();
        this.getContentPane().add(new JScrollPane(table));
        this.revalidate();
    }

	public void displayPatrons() {
		List<Patron> libraryData = library.getPatrons();
		List<Patron> patronsList = new ArrayList<>(libraryData);
		List<Patron> deletedPatrons = new ArrayList<>();
		JTable table = new JTable();
		String[] columns;
		Object[][] data;

		for (Patron patron : patronsList) {
			if (patron.getDeleted()) {
				deletedPatrons.add(patron);
			}
		}
		patronsList.removeAll(deletedPatrons);

		// headers for the table
		columns = new String[]{"Name", "Phone", "Email"};
		data = new Object[patronsList.size()][3];
		for (int i = 0; i < patronsList.size(); i++) {
			Patron patron = patronsList.get(i);
			data[i][0] = patron.getName();
			data[i][1] = patron.getPhone();
			data[i][2] = patron.getEmail();
		}

		table = new JTable(data, columns);
		// Show details window on double click
		table.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent mouseEvent) {
				JTable table =(JTable) mouseEvent.getSource();
				Point point = mouseEvent.getPoint();
				int row = table.rowAtPoint(point);
				if ((mouseEvent.getClickCount() == 2)) {
					new BooksLoanedWindow(MainWindow.this, patronsList.get(row).getBooks());
				}
			}
		});

		this.getContentPane().removeAll();
		this.getContentPane().add(new JScrollPane(table));
		this.revalidate();
    }
}
