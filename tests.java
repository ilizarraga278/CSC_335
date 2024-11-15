import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.io.IOException;
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
		File file = Files.createTempFile("books",".txt").toFile();
		file.deleteOnExit();
		
		try (PrintWriter out = new PrintWriter(file)) {
			out.println("Clifford;E.B White");
			out.println("Harry Potter;J.K Rowling");
			
		}
		Library library = new Library();
		Scanner scanner = new Scanner(file.getAbsolutePath());
		String result = library.addBooks(scanner);
		assertEquals("Books are now in library", result);
		assertEquals("Clifford by E.B White. rating: 0. read: false\nHarry Potter by J.K Rowling. rating: 0. read: false\n", library.getBooks("title"));
	}
		
	@Test
	void testAddBooksFileNotFound() {
		//test for file not found
		Library library1 = new Library();
		Scanner scanner1 = new Scanner("file.txt");
		String result1 = library1.addBooks(scanner1);
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
        Scanner scanner = new Scanner("addBook\nClifford\nE.B White\nexit\n");
        myLibrary.main(scanner, library);
        assertEquals("Clifford by E.B White. rating: 0. read: false\n", library.getBooks("title"));
	}


}
