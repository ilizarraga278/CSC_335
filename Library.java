// i think this is where we will store book instances

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;

public class Library {
	private ArrayList<Book> theLibrary;

	// --- add instance variable here---

	// collection structure to store book objects
	public Library() {
		theLibrary = new ArrayList<Book>();
	}

	// command should ask for user book info for adding
	// library structure .add book(title,aut
	public void addBook(String author, String title) {
		Book bookToAdd = new Book(author, title);
		theLibrary.add(bookToAdd);
	}

	// asks user for filename input file,reads and adds books to library
	public String addBooks(String file) {
		
		File fileName = new File(file);
		try {
			Scanner sc = new Scanner(fileName);
			while (sc.hasNextLine()) {
				String line = sc.nextLine();
				String[] bookInfo = line.split(";");
				String title = bookInfo[0];
				String author = bookInfo[1];

				Book book = new Book(title, author);
				theLibrary.add(book);
			}
			sc.close();
			return("Books are now in library");
		} catch (FileNotFoundException e) {
			return("File not found");

		}
	}

	// retrieve random UNREAD book from library
	public String suggestRead() {
		// randomize theLibray
		Collections.shuffle(theLibrary);
		// return random book
		for (Book book : theLibrary) {
			if (book.isRead() == false) {
				return(book.getTitle() + ",by " + book.getAuthor());
			}
		}
		return("Sorry all books have been read.");
	}

	// ask user for book to update,set to read,unreading a
	// book is imposisble
	// I'm thinking we have the main method of MyLibrary
	// further prompt user for book name and then just pass
	// it into setToRead. maybe same for other commands
	public String setToRead(String title, String author) {
		for (Book libraryBook : theLibrary) {
			if (libraryBook.getTitle().equals(title) && libraryBook.getAuthor().equals(author)) {
				libraryBook.read();
				return("Book has been set to read");
			}

		}
		return("Book not in library");

	}

	// ask user what book to rate
	// ask for rating
	// set rating
	// ratings should be updateable
	// i changed rating attr in book to public, so
	// it is updateable
	public String rate(String title, String author, int rate) {
		for (Book libraryBook : theLibrary) {
			if (libraryBook.getTitle().equals(title) && libraryBook.getAuthor().equals(author)) {
				libraryBook.setRating(rate);
				return ("Rating has been set");
			}

		}
		return("Book not in library");

	}

	// allow user to choose method for search
	// : title,author,rating
	// ask for appropriate info
	// reteve info
	// * search will probably be used in getBooks()
	public String search(String searchMethod, String searchId) {

		ArrayList<Book> match = new ArrayList<Book>();
		searchHelper(searchMethod, searchId, match);

		if (match.isEmpty()) {
			return("No matches found");
		}
		
		StringBuilder result = new StringBuilder();
		for (Book book : match) {
			result.append(book.toString()).append("\n");
		}
		return result.toString();
	}

	private ArrayList<Book> searchHelper(String searchMethod, String searchId, ArrayList<Book> match) {
		for (Book book : theLibrary) {
			if (searchMethod.equals("title")) {
				if (book.getTitle().equals(searchId)) {
					match.add(book);
				}
			}
			if (searchMethod.equals("author")) {
				if (book.getAuthor().equals(searchId)) {
					match.add(book);
				}
			}
			if (searchMethod.equals("rating")) {
				if (book.getRating() == Integer.parseInt(searchId)) {
					match.add(book);
				}
			}
		}
		return match;
	}

	// retrieve and display a list of books
	// according to :
	// all books sorted by title
	// all books sorted by author
	// all books that have been read
	// all books that have not been read
	//
	public String getBooks(String sortMethod) {
		if (sortMethod.equals("author") || sortMethod.equals("title")) {
			return (sortByString(sortMethod));

		}

		if (sortMethod.equals("read") || sortMethod.equals("unread")) {
			return (sortByState(sortMethod));
		}
		return("Search Method Not Available");

	}

	private String sortByString(String sortMethod) {
		StringBuilder result = new StringBuilder();
		if (sortMethod.equals("title")) {
			Collections.sort(theLibrary, Comparator.comparing(Book::getTitle));
			
			for (Book book : theLibrary) {
				result.append(book.toString()).append("\n");
			}

		} else {
			Collections.sort(theLibrary, Comparator.comparing(Book::getAuthor));
			for (Book book : theLibrary) {
				result.append(book.toString()).append("\n");
			}
		}
		return(result.toString());
	}

	private String sortByState(String state) {
		ArrayList<Book> sortedBooks = new ArrayList<Book>();
		if (state.equals("read")) {
			for (Book book : theLibrary) {
				if (book.isRead()) {
					sortedBooks.add(book);
				}
			}
		} else {
			for (Book book : theLibrary) {
				if (!book.isRead()) {
					sortedBooks.add(book);
				}
			}
		}
		StringBuilder result = new StringBuilder();
		for (Book book : sortedBooks) {
			result.append(book.toString()).append("\n");
		}
		return(result.toString());
	}
}

