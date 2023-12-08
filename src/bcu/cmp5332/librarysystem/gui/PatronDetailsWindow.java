package bcu.cmp5332.librarysystem.gui;

import bcu.cmp5332.librarysystem.model.Patron;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.UIManager;

public class PatronDetailsWindow extends JFrame implements ActionListener {

    private MainWindow mw;
	private Patron patron;

    private JButton closeBtn = new JButton("Close");

    public PatronDetailsWindow(MainWindow mw, Patron patron) {
        this.mw = mw;
		this.patron = patron;
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

        setTitle("Patron details");

        setSize(400, 300);
        JPanel topPanel = new JPanel();
        topPanel.setLayout(new GridLayout(5, 2));
        topPanel.add(new JLabel("Name : " + patron.getName()));
        topPanel.add(new JLabel("Phone : " + patron.getPhone()));
        topPanel.add(new JLabel("Email : " + patron.getEmail()));

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