package bcu.cmp5332.librarysystem.gui;

import bcu.cmp5332.librarysystem.commands.RemoveBook;
import bcu.cmp5332.librarysystem.commands.Command;
import bcu.cmp5332.librarysystem.main.LibraryException;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.UIManager;

public class RemoveBookWindow extends JFrame implements ActionListener {

    private MainWindow mw;
    private JTextField idText = new JTextField();

    private JButton removeBtn = new JButton("Remove");
    private JButton cancelBtn = new JButton("Cancel");

    public RemoveBookWindow(MainWindow mw) {
        this.mw = mw;
        initialize();
    }

    /**
     * Initialize the contents of the frame.
     */
    private void initialize() {

		JPanel topPanel = new JPanel();
		JPanel bottomPanel = new JPanel();

        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception ex) {

        }
        setTitle("Remove a Book");
        setSize(300, 200);
        topPanel.setLayout(new GridLayout(5, 2));
        bottomPanel.setLayout(new GridLayout(1, 3));

        topPanel.add(new JLabel("ID: "));
        topPanel.add(idText);
        bottomPanel.add(new JLabel("     "));
        bottomPanel.add(removeBtn);
        bottomPanel.add(cancelBtn);
        removeBtn.addActionListener(this);
        cancelBtn.addActionListener(this);

        this.getContentPane().add(topPanel, BorderLayout.CENTER);
        this.getContentPane().add(bottomPanel, BorderLayout.SOUTH);
        setLocationRelativeTo(mw);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == removeBtn) {
            removeBook();
        } else if (ae.getSource() == cancelBtn) {
            this.setVisible(false);
        }

    }

    private void removeBook() {
        try {
            int bookId = Integer.parseInt(idText.getText());
			if (idText.getText().isEmpty()) {
				throw new LibraryException("Please fill in all fields.");
			}
            // create and execute the RemoveBook Command
            Command removeBook = new RemoveBook(bookId);
            removeBook.execute(mw.getLibrary(), LocalDate.now());
            // refresh the view with the list of books
            mw.displayBooks();
            // hide (close) the RemoveBookWindow
            this.setVisible(false);
        } catch (LibraryException ex) {
            JOptionPane.showMessageDialog(this, ex, "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

}
