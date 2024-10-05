// will model a book, and produce book objects that
// can be stored int the Library class
public class Book {
	//--more instance variables here--
	private String title;
	private String author;
	public int rating; 
	private boolean isRead;// rating should be 1-5 can be changed, maybe
	                    // change visibility later
		
	public Book(String title,String author) {
		this.title = title;
		this.author = author;
		this.rating = 0;
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
	
	public void setRating(int rate) {
		rating = rate;
	}
	
	
	// return true if book is read, false if not
	public boolean isRead() {
		return isRead;
	}
	
	// change read status of book
	public void read() {
		isRead = true;
		
	}
	
	public String toString() {
		return title + " by " + author + ". rating: " + rating + ". read: " + isRead;
    }
}

