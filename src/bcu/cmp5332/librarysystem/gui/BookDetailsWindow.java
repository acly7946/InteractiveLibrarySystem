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
import javax.swing.UIManager;

public class BookDetailsWindow extends JFrame implements ActionListener {

    private MainWindow mw;
	private Book book;

    private JButton closeBtn = new JButton("Close");

    public BookDetailsWindow(MainWindow mw, Book book) {
        this.mw = mw;
		this.book = book;
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

        setTitle("Book details");

        setSize(400, 300);
        JPanel topPanel = new JPanel();
        topPanel.setLayout(new GridLayout(5, 2));
		topPanel.add(new JLabel("Title : " + book.getTitle()));
		topPanel.add(new JLabel("Author : " + book.getAuthor()));
		topPanel.add(new JLabel("Publisher : " + book.getPublisher()));
		topPanel.add(new JLabel("Publication Year : " + book.getPublicationYear()));

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