// will model a book, and produce book objects that
// can be stored int the Library class
public class Book {
	//--more instance variables here--
	private String title;
	private String author;
	private int rating; 
	private boolean isRead;// rating should be 1-5 can be changed, maybe
	                    // change visibility later
		
	public Book() {
		this.title = title;
		this.author = author;
		this.rating = rating;
		this.isRead = false; // default should be false?
	}
	
	public String getTitle() {
		return title;
	}
	
	public String getAuthor() {
		return author;
	}
	
	public int getRating() {
		return rating;
	}
	
	
	// return true if book is read, false if not
	public boolean isRead() {
		return isRead;
	}
	
	// change read status of book
	public void read() {
		isRead = true;
		
		
	}
}
