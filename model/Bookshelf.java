package model;

import java.util.ArrayList;

/*
 * All instance variables must be completely encapsulated. There should be no privacy leaks.
• addBook: Adds a book to booksOnShelf. There should be no duplicates – 
	if there is already a book on the shelf with the same title, 
	then replace the book on the shelf with a merged book. 
	(Merge the book to add with the book on the shelf.) 
	Note that ArrayList has a method add(int,Object) that will replace the element at specified index with the Object specified.
• getBookWithHighestRating returns the book in the Bookshelf that has the highest average rating. If the list is empty, this method will return null.
 */
public class Bookshelf extends Object {

	private ArrayList<Book> booksOnShelf = new ArrayList<Book>();
	private String name = "";

	public Bookshelf(String name) {
		this.name = name; // cause of common bugs
	}
	
	@Override
	public String toString() {
		// for debugging purposes, it can help to have the state of the object
		StringBuilder asString = new StringBuilder();
		asString.append("Name of shelf: ");
		asString.append(name);
		asString.append("\n");
		for (Book aBook : booksOnShelf) {
			asString.append(aBook.toString());
			asString.append(" minutes to consume: ");
			asString.append(aBook.minutesToConsume());
			asString.append("\n");
		}
		//loop to add all the books to the string
		return asString.toString();
	}

	public void addBook(Book b) {
		if (b != null) {
		// is a book with the same title on the shelf already.
			for (int index = 0; index < booksOnShelf.size(); index++) {
				Book current = booksOnShelf.get(index);
				if (current.getTitle().equalsIgnoreCase(b.getTitle())) {
					Book merged = current.mergeRatings(b);
					booksOnShelf.add(index,merged);
					return;
				}
			}
			booksOnShelf.add(b);
		}
	}

	public String getName() {
		return name;
	}

	public ArrayList<Book> getBooksOnShelf() {
		ArrayList<Book> copyOfShelf = new ArrayList<Book>();
		for (Book b : booksOnShelf) {
			copyOfShelf.add(new Book(b));
		}
		return copyOfShelf;
	}

	public Book bookWithHighestRating() {
		Book highest = null;
		if (booksOnShelf.size() > 0) {
			highest = booksOnShelf.get(0);
		}
		for (int index = 1; index < booksOnShelf.size(); index++) {
			Book current = booksOnShelf.get(index);
			if (current.getHighestRating() > highest.getHighestRating()) {
				highest = current;
			}
		}
		return highest;
	}

}
