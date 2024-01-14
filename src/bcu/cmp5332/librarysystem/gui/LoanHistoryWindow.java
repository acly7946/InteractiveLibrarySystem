package bcu.cmp5332.librarysystem.gui;

import bcu.cmp5332.librarysystem.model.Book;
import bcu.cmp5332.librarysystem.model.Loan;

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

import java.util.List;

public class LoanHistoryWindow extends JFrame implements ActionListener {

    private MainWindow mw;
	private List<Loan> loanList;
    private JButton closeBtn = new JButton("Close");

	public LoanHistoryWindow(MainWindow mw, List<Loan> loans) {
		this.mw = mw;
		loanList = loans;
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
        setTitle("Loan History");
        setSize(400, 300);
		topPanel.setLayout(new BorderLayout());
        bottomPanel.setLayout(new GridLayout(0, 1));

		// headers for the table
		columns = new String[]{"Book ID", "Borrowed", "Due Date", "Returned"};
		data = new Object[loanList.size()][5];
		for (int i = 0; i < loanList.size(); i++) {
			Loan loan = loanList.get(i);
			data[i][0] = loan.getBook().getId();
			data[i][1] = loan.getStartDate();
			data[i][2] = loan.getDueDate();
			data[i][3] = loan.getReturnDate();
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