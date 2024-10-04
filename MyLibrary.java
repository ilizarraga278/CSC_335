// should contain main method that runs text based user interface
// where user can enter commands to get or add info to library
// MyLibrary is user interface

import java.util.Scanner;

public class MyLibrary {
	public static void main(String args[]) {
		Library myLibrary = new Library();
		Scanner keyboard = new Scanner(System.in);
		boolean ctrue = true;
		
		while(true) {
			System.out.println("Enter a command: ");
			String command = keyboard.nextLine();
			command(command.toLowerCase(),myLibrary);
			
			
			
			
		}
		
		
		
	}

	private static void command(String command, Library myLibrary) {
		if (command.equals("search")) {
			System.out.println("Enter a search method: ");
			Scanner keyboard = new Scanner(System.in);
			String searchMethod = keyboard.nextLine();
			System.out.println("Enter the search id/info: ");
			String searchId = keyboard.nextLine();// might have to add toLowerCase() later
			myLibrary.search(searchMethod, searchId);
			
		}
		if (command.equals("addBook")) {
			System.out.println("Enter the title and author: "){
				
			}
		}
		// TODO Auto-generated method stub
		
	}
	
	

}
