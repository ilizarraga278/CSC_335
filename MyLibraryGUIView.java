//GUI view for library project

import javax.swing.*;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MyLibraryGUIView {
	private Library model;
	private JTextArea outputArea;
	private JTextField titleField;
	private JTextField authorField;
	private JPanel panel;

	public MyLibraryGUIView() {
		model = new Library();
		setUp();
	}

	private void setUp() {
		JFrame jframe = new JFrame("Welcome to My Library");
		// setting size of frame
		jframe.setSize(600, 400);
		jframe.setLayout(new BorderLayout());

		JTextArea outputArea = new JTextArea();
		outputArea.setBackground(Color.pink);
		outputArea.setEditable(false);

		JScrollPane scrollPane = new JScrollPane(outputArea);
		scrollPane.setBackground(Color.blue);

		panel = new JPanel();
		panel.setLayout(new GridLayout(6, 6, 6, 6));

		JLabel titleLabel = new JLabel("Title:");
		titleField = new JTextField();

		JLabel authorLabel = new JLabel("Author:");
		authorField = new JTextField();

		JButton addBookButton = new JButton("Add Book");
		addBookButton.setBackground(Color.green);

		JButton addBooksButton = new JButton("Add Books");
		addBooksButton.setBackground(Color.green);

		JButton searchButton = new JButton("Search Books");
		searchButton.setBackground(Color.green);

		JButton suggestButton = new JButton("Suggest Read");
		suggestButton.setBackground(Color.green);

		JButton setToReadButton = new JButton("Set Book to Read");
		setToReadButton.setBackground(Color.green);

		JButton rateButton = new JButton("Rate Book");
		rateButton.setBackground(Color.green);

		JButton getBooksButton = new JButton("Get Books");
		getBooksButton.setBackground(Color.green);

		panel.add(titleLabel);
		panel.add(titleField);

		panel.add(authorLabel);
		panel.add(authorField);

		panel.add(addBookButton);
		panel.add(addBooksButton);
		panel.add(searchButton);
		panel.add(suggestButton);
		panel.add(setToReadButton);
		panel.add(rateButton);
		panel.add(getBooksButton);

		jframe.add(scrollPane, BorderLayout.CENTER);
		jframe.add(panel, BorderLayout.NORTH);

		jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// handles the addBook request
		addBookButton.addActionListener(e -> {
			String title = titleField.getText();
			String author = authorField.getText();

			if (title.isEmpty() || author.isEmpty()) {
				outputArea.append("Enter a title and author\n");
			} else {
				model.addBook(title, author);
				outputArea.append("Book added: " + title + " by " + author + "\n");
				titleField.setText("");
				authorField.setText("");
			}
		});

		// handles the search request
		searchButton.addActionListener(e -> {
			String searchMethod = JOptionPane.showInputDialog("Enter a search method(title,author,rating): ");
			String searchId = JOptionPane.showInputDialog("Enter the search id/info: ");
			if (searchMethod == null || searchId == null) {
				outputArea.append("Enter a search method and search id/info\n");
			} else {
				outputArea.append(model.search(searchMethod, searchId) + "\n");
			}
		});

		// handles the suggestRead request
		suggestButton.addActionListener(e -> {
			outputArea.append(model.suggestRead() + "\n");
		});

		// handles the setToRead request
		setToReadButton.addActionListener(e -> {
			String req = JOptionPane.showInputDialog("Enter title and author in TITLE:AUTHOR format: ").trim();
			if (req.isEmpty()) {
				outputArea.append("Enter a title and author\n");
			} else {
				String[] titleAuthor = req.split(";");
				String title = titleAuthor[0];
				String author = titleAuthor[1];
				outputArea.append(model.setToRead(title, author) + "\n");
			}
		});

		// handles the rateBook request
		rateButton.addActionListener(e -> {
			String req = JOptionPane.showInputDialog("Enter the book you want to rate in TITLE;AUTHOR;RATING form:")
					.trim();
			if (req.isEmpty()) {
				outputArea.append("Enter a title, author, and rating\n");
			} else {
				String[] info = req.split(";");
				String title = info[0];
				String author = info[1];
				String rating = info[2];
				outputArea.append(model.rate(title, author, Integer.parseInt(rating)) + "\n");
			}
		});

		// handles the getBOOKS REQUEST
		getBooksButton.addActionListener(e -> {
			String req = JOptionPane.showInputDialog("Enter a method to sort books(title,author,read,unread): ").trim();
			if (req.isEmpty()) {
				outputArea.append("Enter a method to sort the books\n");
			} else {
				outputArea.append(model.getBooks(req) + "\n");
			}
		});

		// handles the addBooks request
		addBooksButton.addActionListener(e -> {
			String filename = JOptionPane.showInputDialog("Enter the filename for addBooks: ");
			if (filename == null) {
				outputArea.append("Enter a filename: ");
			} else {
				outputArea.append(model.addBooks(filename) + "\n");
			}
		});

		jframe.setVisible(true);

	}

	public static void main(String args[]) {
		SwingUtilities.invokeLater(MyLibraryGUIView::new);

	}
}
