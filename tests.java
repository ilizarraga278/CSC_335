import static org.junit.jupiter.api.Assertions.*;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.ByteArrayInputStream;

import java.io.PrintStream;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.util.Scanner;

import org.junit.jupiter.api.Test;

class tests {

	
	//Book Class Test:
	@Test
	void testBookConstructor() {
		Book book = new Book("Clifford","E.B White");
		assertEquals("Clifford",book.getTitle());
		assertEquals("E.B White",book.getAuthor());
	}
	
	@Test
	void testGetRating() {
		Book book = new Book("Clifford", "E.B White");
		book.setRating(5);
		assertEquals(5, book.getRating());
		
		Book book1 = new Book("The Lorax", "Dr. Seuss");
		assertEquals(0, book1.getRating());
		
		book.setRating(3);
		assertEquals(3, book.getRating());
	}
	
	@Test
	void testIsRead() {
		Book book = new Book("Clifford", "E.B White");
		assertFalse(book.isRead());
		book.read();
		assertTrue(book.isRead());
	}
	
	@Test
	void testGetAuthor() {
		Book book = new Book("Clifford", "E.B White");
		assertEquals("E.B White", book.getAuthor());		
	}
	
	@Test
	void testGetTitle() {
		Book book = new Book("Clifford", "E.B White");
		assertEquals("Clifford", book.getTitle());
	}
	
	@Test
	void testToString() {
		Book book = new Book("Clifford", "E.B White");
		assertEquals("Clifford by E.B White. rating: 0. read: false", book.toString());
		book.setRating(5);
		book.read();
		assertEquals("Clifford by E.B White. rating: 5. read: true", book.toString());
	}
	
	
	
	//Library Class Test:
	@Test
	void testLibraryConstructor() {
		Library library = new Library();
        assertNotNull(library);
        
        library.addBook("Clifford","E.B White");
        library.addBook("Harry Potter","J.K Rowling");
	}
	
	@Test
	void testAddBooks() throws IOException {
		Library library = new Library();
		
		String result = library.addBooks("books.txt");
		assertFalse(result.equals("File not found"));
	}
		
	@Test
	void testAddBooksFileNotFound() {
		//test for file not found
		Library library1 = new Library();
		String result1 = library1.addBooks("file.txt");
		assertEquals("File not found", result1);
	}
	
	@Test
	void testSuggestRead() {
		Library library = new Library();
		library.addBook("Clifford","E.B White");
		assertEquals("Clifford,by E.B White", library.suggestRead());
		library.setToRead("Clifford", "E.B White");
		assertEquals("Sorry all books have been read.", library.suggestRead());
		
		library.addBook("Harry Potter","J.K Rowling");
		assertEquals("Harry Potter,by J.K Rowling", library.suggestRead());
	}
	
	@Test
	void testSetToRead() {
		Library library = new Library();
		library.addBook("Clifford","E.B White");
		assertEquals("Book has been set to read", library.setToRead("Clifford", "E.B White"));
		assertEquals("Book not in library", library.setToRead("Harry Potter", "J.K Rowling"));
	}
	
	@Test
	void testRate() {
		Library library = new Library();
		library.addBook("Clifford","E.B White");
		assertEquals("Rating has been set", library.rate("Clifford", "E.B White", 5));
		assertEquals("Book not in library", library.rate("Harry Potter", "J.K Rowling", 5));
	}
	
	@Test
	void testSearch() {
		Library library = new Library();
		library.addBook("Clifford","E.B White");
		assertEquals("Clifford by E.B White. rating: 0. read: false\n", library.search("title", "Clifford"));
		assertEquals("No matches found", library.search("title", "Harry Potter"));
		
		assertEquals("Clifford by E.B White. rating: 0. read: false\n", library.search("author", "E.B White"));
		assertEquals("No matches found", library.search("author", "J.K Rowling"));
		
		assertEquals("Clifford by E.B White. rating: 0. read: false\n", library.search("rating", "0"));
		assertEquals("No matches found", library.search("rating", "5"));
		
		
	}
	
	@Test
	void testGetBooks() {
		Library library = new Library();
		assertEquals("", library.getBooks("title"));
		assertEquals("Search Method Not Available", library.getBooks("length"));
		library.addBook("Clifford","E.B White");
		assertEquals("Clifford by E.B White. rating: 0. read: false\n", library.getBooks("title"));
		
		library.setToRead("Clifford", "E.B White");
		assertEquals("Clifford by E.B White. rating: 0. read: true\n", library.getBooks("read"));
		assertEquals("", library.getBooks("unread"));
		library.addBook("Harry Potter","J.K Rowling");
		assertEquals("Harry Potter by J.K Rowling. rating: 0. read: false\n", library.getBooks("unread"));
		
		assertEquals("Clifford by E.B White. rating: 0. read: true\nHarry Potter by J.K Rowling. rating: 0. read: false\n", library.getBooks("author"));
		
	}
	
	//MyLibrary Class Test:
	@Test 
	void testMyLibraryConstructor() {
        MyLibrary myLibrary = new MyLibrary();
        assertNotNull(myLibrary); 
	}
	
	@Test
	void testMain() {
		MyLibrary myLibrary = new MyLibrary();
		Library library = new Library();
		InputStream usrIn = new ByteArrayInputStream("addBook\nClifford\nE.B White\ngetBooks\ntitle\nexit\n".getBytes());
		System.setIn(usrIn);
		ByteArrayOutputStream output = new ByteArrayOutputStream();
		System.setOut(new PrintStream(output));
		myLibrary.main(new String[] {});
		String expectedOut = "Enter a command or type 'exit' to quit: \nEnter the title: \nEnter the author:\nBook has been added to the library.\n"+ "Enter a command or type 'exit' to quit: \nEnter a method to sort the books from these options(title,author,read,unread)"
				+ "\nClifford by E.B White. rating: 0. read: false\n"+
				"\n" + "Enter a command or type 'exit' to quit: \n";
		assertEquals(expectedOut, output.toString());

	}


}
