// i think this is where we will store book instances

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;
public class Library {
	private ArrayList<Book> theLibrary;
	
	// --- add instance variable here---
	
	// collection structure to store book objects
	public Library() {
		theLibrary = new ArrayList<Book>();
	}
	
	// command should ask for user book info for adding
	//library structure .add book(title,author,rating)
	public void addBook(Book bookToAdd) {
		theLibrary.add(bookToAdd);
	}
	
	// asks user for filename input file,reads and adds books to library
	public void addBooks() {
		Scanner keyboard = new Scanner(System.in);
		System.out.println("Enter name of input file:");
		String file = keyboard.nextLine();
		File fileName = new File(file);
		try {
			Scanner scanner = new Scanner(fileName);
			while(scanner.hasNextLine()) {
				String line = scanner.nextLine();
				String[] bookInfo =line.split(";");
				String title = bookInfo[0];
				String author = bookInfo[1];
				int rating = Integer.MIN_VALUE;
				Book book = new Book(title,author,rating,false);
				theLibrary.add(book);
			}
			scanner.close();
		}catch(FileNotFoundException e) {
			System.out.println("File not found");
			
		}
		keyboard.close();
	}
	
	// retrieve random UNREAD book from library
	public Book suggestRead() {
		//randomize theLibray
		Collections.shuffle(theLibrary);
		// return random book
		for(Book book : theLibrary){
			if(book.isRead() == false) {
				return book;
			}
		}
		return null;
	}
	
	// ask user for book to update,set to read,unreading a
	// book is imposisble
	public void setToRead() {
		
	}
	
	//ask user what book to rate
	// ask for rating
	// set rating
	// ratings should be updateable
	public void rate() {
		
	}

	// allow user to choose method for search
	//: title,author,rating
	// ask for appropriate info
	// reteve info 
	//* search will probbaly be used in getBooks()
	public void search() {
		
	
	}
	// retrieve and display a list of books
	// according to :
	// all books sorted by title
	// all books sorted by author
	// all books that have been read
	// all books that have not been read
	// 


	public ArrayList<E> getBooks(){
		
	}
	

}

