package application;

import java.util.Scanner;

import model.Book;
import model.Bookshelf;

public class BookStoreApp {

	public static void main(String[] args) {
		Bookshelf shelf1 = new Bookshelf("My First Shelf");
		
		System.out.println("I have an empty shelf, lets add some books");
		System.out.print("What is the title of the first book to add? ");
		Scanner keyboard = new Scanner(System.in);
		String title = keyboard.nextLine();
		Book b1 = new Book(title);
		
		System.out.println("Enter book ratings, enter -1 when done");
		System.out.print("Rating: ");
		double rating = keyboard.nextDouble();
		while (rating >= 0.0) {
			b1.addRating(rating);
			rating = keyboard.nextDouble();
		}
		
		shelf1.addBook(b1);
		
		System.out.print("What is the title of the second book to add? ");
		keyboard = new Scanner(System.in);
		title = keyboard.nextLine();
		Book b2 = new Book(title);
		
		System.out.println("Enter book ratings, enter -1 when done");
		System.out.print("Rating: ");
		rating = keyboard.nextDouble();
		while (rating >= 0.0) {
			b1.addRating(rating);
			rating = keyboard.nextDouble();
		}
		
		shelf1.addBook(b2);
		
		System.out.println("Book with highest rating on shelf: " + 
				shelf1.bookWithHighestRating().getTitle());
		
		
	}

}
