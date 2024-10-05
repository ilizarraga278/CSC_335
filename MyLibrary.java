// should contain main method that runs text based user interface
// where user can enter commands to get or add info to library
// MyLibrary is user interface

import java.util.Scanner;

public class MyLibrary {
	public static void main(String args[]) {
		Library myLibrary = new Library();
		Scanner scanner = new Scanner(System.in);

		System.out.println("Enter a command or type 'exit' to quit: ");
		String userInput = scanner.nextLine();
		
		while(!userInput.equals("exit")) {
			command(userInput,myLibrary,scanner);	
			System.out.println("Enter a command or type 'exit' to quit: ");
			userInput = scanner.nextLine();
		}
		
	}

	private static void command(String command, Library myLibrary, Scanner scanner) {
		//maybe add input validation to ensure command is a valid command
		if (command.equals("search")) {
			System.out.println("Enter a search method: ");
			String searchMethod = scanner.nextLine();
			System.out.println("Enter the search id/info: ");
			String searchId = scanner.nextLine();// might have to add toLowerCase() later
			myLibrary.search(searchMethod, searchId);
			
		}
		if (command.equals("addBook")) {
			System.out.println("Enter the title: ");
			String title = scanner.nextLine();
			System.out.println("Enter the author: ");
			String author = scanner.nextLine();
			myLibrary.addBook(title,author);	
			}
		
		if (command.equals("setToRead")) {
			System.out.println("What book do you want to update? Enter the title and author in TITLE;FORM");
			String[] titleAuthor = scanner.nextLine().split(";");
			String title = titleAuthor[0];
			String author = titleAuthor[1];
			myLibrary.setToRead(title,author);
		}
		if (command.equals("rate")) {
			System.out.println("Enter title and author of the book you want to rate in TITLE;AUTHOR;RATING form:");
			String[] info = scanner.nextLine().split(";");
			String title = info[0];
			String author = info[1];
			String rating = info[2];
			myLibrary.rate(title,author,Integer.parseInt(rating));
		}
		if (command.equals("getBooks")) {
			System.out.println("Enter a method to sort the books from these options(title,author,read,unread)");
			String sortMethod = scanner.nextLine();
			myLibrary.getBooks(sortMethod);
		}
		if(command.equals("suggestRead")) {
			System.out.println("Here is your random read:");
			myLibrary.suggestRead();
			
		}
		if (command.equals("addBooks")) {
			myLibrary.addBooks(scanner);
			System.out.println("Thank you your books have been added to myLibary");
		}
		}
}
		// TODO Auto-generated method stub
		


