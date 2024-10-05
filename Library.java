// i think this is where we will store book instances

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;
public class Library implements Comparator<Book> {
	private ArrayList<Book> theLibrary;
	
	// --- add instance variable here---
	
	// collection structure to store book objects
	public Library() {
		theLibrary = new ArrayList<Book>();
	}
	
	// command should ask for user book info for adding
	//library structure .add book(title,aut
	public void addBook(String author, String title) {
		Book bookToAdd = new Book(author,title);
		theLibrary.add(bookToAdd);
	}
	
	// asks user for filename input file,reads and adds books to library
	public void addBooks(Scanner scanner) {
		System.out.println("Please enter a filename for your books:");
		String file = scanner.nextLine();
		File fileName = new File(file);
		try {
			Scanner sc = new Scanner(fileName);
			while(sc.hasNextLine()) {
				String line = sc.nextLine();
				String[] bookInfo =line.split(";");
				String title = bookInfo[0];
				String author = bookInfo[1];
				
				Book book = new Book(title,author);
				theLibrary.add(book);
			}
			sc.close();
		}catch(FileNotFoundException e) {
			System.out.println("File not found");
			
		}
	}
	
	// retrieve random UNREAD book from library
	public void suggestRead() {
		//randomize theLibray
		Collections.shuffle(theLibrary);
		// return random book
		for(Book book : theLibrary){
			if(book.isRead() == false) {
				System.out.println(book.getTitle() + ",by " + book.getAuthor());
			}
		}
		System.out.println("Sorry all books have been read.");
	}
	
	// ask user for book to update,set to read,unreading a
	// book is imposisble
	//I'm thinking we have the main method of MyLibrary
	// further prompt user for book name and then just pass
	// it into setToRead. maybe same for other commands
	public void setToRead(String title, String author) {
		for (Book libraryBook: theLibrary) {
			if(libraryBook.getTitle().equals(title) && libraryBook.getAuthor().equals(author)){
				libraryBook.read();
			}
			
		}
		
	}
	
	//ask user what book to rate
	// ask for rating
	// set rating
	// ratings should be updateable
	// i changed rating attr in book to public, so 
	// it is updateable
	public void rate(String title, String author, int rate) {
		for (Book libraryBook: theLibrary) {
			if(libraryBook.getTitle().equals(title) && libraryBook.getAuthor().equals(author)){
				libraryBook.setRating(rate);
			}
			
		}
		
	}

	// allow user to choose method for search
	//: title,author,rating
	// ask for appropriate info
	// reteve info 
	//* search will probably be used in getBooks()
	public void search(String searchMethod, String searchId) {
		
		ArrayList<Book> match = new ArrayList<Book>();
		searchHelper(searchMethod,searchId,match);
		
		if (match.isEmpty()) {
			System.out.println("No matches found");
		}
		
		for (Book book : match) {
			System.out.println(book.toString());
		}
	}
	
	private ArrayList<Book> searchHelper(String searchMethod, String searchId, ArrayList<Book> match) {
		for (Book book: theLibrary) {
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
	public void getBooks(String sortMethod){
		if (sortMethod.equals("author")) {
			sortByAuthor();
		}
		if(sortMethod.equals("title")) {
			sortByTitle();
		}
		
		if(sortMethod.equals("read") || sortMethod.equals("unread")) {
			sortByState(sortMethod);
		}
		
	}

		private void sortByState(String state) {
	        ArrayList<Book> sortedBooks = new ArrayList<Book>();
	        if (state.equals("read")) {
	            for(Book book : theLibrary) {
	                if(book.isRead()) {
	                    sortedBooks.add(book);
	                }
	            }
	        }
	        else {
	            for(Book book : theLibrary) {
	                if(!book.isRead()) {
	                    sortedBooks.add(book);
	                }
	            }
	        }
	        for(Book book: sortedBooks) {
	            System.out.println(book.toString());
	        }
	    }

	// sort alphabetically by the authors
	private void sortByTitle() {
		Collections.sort(theLibrary, new Comparator<Book>() {
			public int compare(Book b1, Book b2) {
				return b1.getTitle().compareTo(b2.getTitle());
			}
		});
		for (Book book : theLibrary) {
			System.out.println(book.toString());
		}
	}
		

	private void sortByAuthor() {
		Collections.sort(theLibrary, new Comparator<Book>() {
			public int compare(Book b1, Book b2) {
				return b1.getAuthor().compareTo(b2.getAuthor());
			}
		});
		// TODO Auto-generated method stub
		
	}

	@Override
	public int compare(Book o1, Book o2) {
		// TODO Auto-generated method stub
		return 0;
	}
	
	
}


