package model;

import static org.junit.Assert.*;

import org.junit.Test;

import java.util.ArrayList;

public class BookshelfTest {
	
		@Test
		public void test_ConstructorAndGetter() {
			Bookshelf c = new Bookshelf("Test Constructor and Getter");
			assertEquals("Testing constructor and getter", "Test Constructor and Getter", c.getName());
		}
		
		@Test
		public void test_addBook_addingOne() {
			Bookshelf c = new Bookshelf("TestAddingOne");
			Book b = new Book("Pride and Prejudice");
			c.addBook(b);
			ArrayList<Book> list = c.getBooksOnShelf();
			Book b2 = null;
			
			if (list.size() > 0){
				b2 = list.get(0);
			}
			
			
			assertEquals("Bookshelf only has one book - Pride and Prejudice", 1, list.size());
			assertEquals("Bookshelf only has one book - Pride and Prejudice", "Pride and Prejudice", b2.getTitle());
		}
		
		@Test
		public void test_AddBook_addDuplicates() {
			Bookshelf c = new Bookshelf("TestAddingDuplicates");
			Book b = new Book("Sorcery and Cecelia");
			b.addRating(8.0);
			Book bDupe = new Book("Sorcery and Cecelia");
			bDupe.addRating(9.0);
			
			c.addBook(b);
			c.addBook(bDupe);
			
			ArrayList<Book> list = c.getBooksOnShelf();
			Book b1 = null;
			
			if (list.size() > 0){
				b1 = list.get(0);
			}
			
			assertEquals("Bookshelf only has one book", 1, list.size());
			assertEquals("Bookshelf only has one book - Sorcery and Cecelia", "Sorcery and Cecelia" , b1.getTitle());
			assertEquals("Bookshelf only has one book - Sorcery and Cecelia and merged ratings", 8.5, b1.getAverageRating(), 0.00001);
			assertEquals("Bookshelf only has one book - Sorcery and Cecelia and merged ratings", 9.0, b1.getHighestRating(), 0.00001);
		}		


		@Test
		public void test_addBook_addingMany_noDuplicates() {
			Bookshelf c = new Bookshelf("TestAddingMany");
			Book m1 = new Book("The Cat in the Hat");
			Book m2 = new Book("The Lorax");
			Book m3 = new Book("Green Eggs and Ham");
			Book m4 = new Book("Oh, the Places You'll Go!");
			Book m5 = new Book("Horton Hears a Who");
			Book m6 = new Book("The Grinch Who Stole Christmas");
			c.addBook(m1);
			c.addBook(m2);
			c.addBook(m3);
			c.addBook(m4);
			c.addBook(m5);
			c.addBook(m6);
			
			ArrayList<Book> list = c.getBooksOnShelf();
			
			assertEquals("Expected list of size 6 after adding 6 Books", 6, list.size());			
			assertEquals("Book 1 test: The Cat in the Hat", "The Cat in the Hat", list.get(0).getTitle());
			assertEquals("Book 2 test: The Lorax", "The Lorax", list.get(1).getTitle());
			assertEquals("Book 3 test: Green Eggs and Ham", "Green Eggs and Ham", list.get(2).getTitle());
			assertEquals("Book 4 test: Oh, the Places You'll Go!", "Oh, the Places You'll Go!", list.get(3).getTitle());
			assertEquals("Book 5 test: Horton Hears a Who", "Horton Hears a Who", list.get(4).getTitle());
			assertEquals("Book 6 test: The Grinch Who Stole Christmas", "The Grinch Who Stole Christmas", list.get(5).getTitle());
		}

		@Test
		public void test_addBook_addingMany_someDuplicates() {
			Bookshelf c = new Bookshelf("TestAddingManyWithDuplicates");
			Book m1 = new Book("The Cat in the Hat");
			Book m2 = new Book("The Lorax");
			Book m1Dup = new Book("The Cat in the Hat");
			m1.addRating(7.5);
			m1.addRating(6.9);
			m1Dup.addRating(9.8);
			
			Book m3 = new Book("Green Eggs and Ham");
			Book m4 = new Book("Oh, the Places You'll Go!");
			Book m5 = new Book("Horton Hears a Who");
			Book m6 = new Book("The Grinch Who Stole Christmas");
			Book m6Dup = new Book("The Grinch Who Stole Christmas");
			m6.addRating(5.0);
			m6.addRating(5.0);
			m6Dup.addRating(6.0);
			m6Dup.addRating(7.0);
			m6Dup.addRating(8.0);
			
			c.addBook(m1);
			c.addBook(m2);
			c.addBook(m1Dup);
			c.addBook(m3);
			c.addBook(m4);
			c.addBook(m5);
			c.addBook(m6);
			c.addBook(m6Dup);
			
			ArrayList<Book> list = c.getBooksOnShelf();
			
			assertEquals("Expected list of size 6 after adding 8 Books with 2 duplicates", 6, list.size());			
			assertEquals("Book 1 test: The Cat in the Hat", "The Cat in the Hat", list.get(0).getTitle());
			assertEquals("Book 1 test: average rating of merged book", 8.066667, list.get(0).getAverageRating(), 0.00001);
			assertEquals("Book 2 test: The Lorax", "The Lorax", list.get(1).getTitle());
			assertEquals("Book 2 test: average rating of unmerged book", 0.0, list.get(1).getAverageRating(), 0.00001);
			assertEquals("Book 3 test: Green Eggs and Ham", "Green Eggs and Ham", list.get(2).getTitle());
			assertEquals("Book 3 test: average rating of unmerged book", 0.0, list.get(2).getAverageRating(), 0.00001);
			assertEquals("Book 4 test: Oh, the Places You'll Go!", "Oh, the Places You'll Go!", list.get(3).getTitle());
			assertEquals("Book 4 test: average rating of unmerged book", 0.0, list.get(3).getAverageRating(), 0.00001);
			assertEquals("Book 5 test: Horton Hears a Who", "Horton Hears a Who", list.get(4).getTitle());
			assertEquals("Book 5 test: average rating of unmerged book", 0.0, list.get(4).getAverageRating(), 0.00001);
			assertEquals("Book 6 test: The Grinch Who Stole Christmas", "The Grinch Who Stole Christmas", list.get(5).getTitle());
			assertEquals("Book 6 test: average rating of merged book", 6.2, list.get(5).getAverageRating(), 0.00001);
			
		}

		@Test
		public void test_addBook_addingOne_EncapsulationTest() {
			Bookshelf c = new Bookshelf("Test");
			Book m = new Book("1Q84");
			c.addBook(m);
			m.addRating(8.0);
			ArrayList<Book> list = c.getBooksOnShelf();
			Book m2 = null;
			
			if (list.size() > 0){
				m2 = list.get(0);
			}
			
			assertEquals("Bookshelf only has one Book - testing encapsulation (added a rating).", 0.0, m2.getAverageRating(), 0.00001);
			
		}
		
		@Test
		public void test_getBookList_Encapsulation_ChangeBookOnShelf() {
			Bookshelf c = new Bookshelf("Encapsulation");
			Book m1 = new Book("So You Want to be a Wizard");
			Book m2 = new Book("Deep Wizardry");
			Book m3 = new Book("High Wizardry");
			Book m4 = new Book("A Wizard Abroad");
			Book m5 = new Book("The Wizard's Dilemma");
			Book m6 = new Book("A Wizard Alone");
			c.addBook(m1);
			c.addBook(m2);
			c.addBook(m3);
			c.addBook(m4);
			c.addBook(m5);
			c.addBook(m6);
			
			ArrayList<Book> list = c.getBooksOnShelf();
			
			list.get(0).addRating(8.1);
			list.get(1).addRating(8.2);
			list.get(2).addRating(8.3);
			list.get(3).addRating(8.4);
			list.get(4).addRating(8.5);
			list.get(5).addRating(8.6);
			
			list = c.getBooksOnShelf();
				
			assertEquals("Book 1 test - testing averageRating", 0.0, list.get(0).getAverageRating(), 0.00001);
			assertEquals("Book 2 test - testing averageRating", 0.0, list.get(1).getAverageRating(), 0.00001);
			assertEquals("Book 3 test - testing averageRating", 0.0, list.get(2).getAverageRating(), 0.00001);
			assertEquals("Book 4 test - testing averageRating", 0.0, list.get(3).getAverageRating(), 0.00001);
			assertEquals("Book 5 test - testing averageRating", 0.0, list.get(4).getAverageRating(), 0.00001);
			assertEquals("Book 6 test - testing averageRating", 0.0, list.get(5).getAverageRating(), 0.00001);
			
		}

		@Test
		public void test_getBookList_Encapsulation_ChangeBookList() {
			Bookshelf c = new Bookshelf("Encapsulation");
			Book m1 = new Book("So You Want to be a Wizard");
			Book m2 = new Book("Deep Wizardry");
			Book m3 = new Book("High Wizardry");
			Book m4 = new Book("A Wizard Abroad");
			Book m5 = new Book("The Wizard's Dilemma");
			Book m6 = new Book("A Wizard Alone");
			c.addBook(m1);
			c.addBook(m2);
			c.addBook(m3);
			c.addBook(m4);
			c.addBook(m5);
			c.addBook(m6);
			
			ArrayList<Book> list = c.getBooksOnShelf();
			list.add(new Book("Extra to test encapsulation"));
			
			assertEquals("No book should have been added when adding book to returned list (length stays 6)", 6, c.getBooksOnShelf().size());
		}

		@Test
		public void test_bookWithHighestRating_emptyList() {
			Bookshelf c = new Bookshelf("EmptyListTest");
			assertEquals("No Books added to list.", null, c.bookWithHighestRating());
		}
		
		@Test
		public void test_bookWithHighestRating_OneBookOnBookshelf() {
			Bookshelf c = new Bookshelf("OneBookOnBookshelf");
			Book m = new Book("Saltation");
			c.addBook(m);
			Book highestRated = c.bookWithHighestRating();
			assertEquals("Bookshelf only has one Book (Saltation) - testing averageRating", 0.0, highestRated.getAverageRating(), 0.00001);
		}

		@Test
		public void test_bookWithHighestRating_ListHasTwoBooksWithSameRating() {
			Bookshelf c = new Bookshelf("Two books with same rating");
			Book m1 = new Book("Jhereg");
			m1.addRating(7.0);
			Book m2 = new Book("Issola");
			m2.addRating(7.0);
			c.addBook(m1);
			c.addBook(m2);
			
			Book highestRated = c.bookWithHighestRating();
			
			assertEquals("BookPit only has two Books with same rating, expected to get first (Jhereg) - testing title.", "Jhereg", highestRated.getTitle());
			assertEquals("BookPit only has two Books with same rating, expected to get first added (Jhereg) - testing ratings.", 7.0, highestRated.getAverageRating(), 0.00001);
		}
		
		@Test
		public void test_BookWithHighestRating_highestInMiddle() {
			Bookshelf c = new Bookshelf("HighestInMiddle");
			Book m1 = new Book("Sabriel"); 
			m1.addRating(3.0);
			Book m2 = new Book("Lirael");
			m2.addRating(7.0);
			Book m3 = new Book("Abhorsen"); 
			m3.addRating(2.0);
			m3.addRating(5.0);
			c.addBook(m1);
			c.addBook(m2);
			c.addBook(m3);
			
			Book highestRated = c.bookWithHighestRating();
			
			assertEquals("Bookshelf has three Books, highest rated in middle (Lirael) - testing title.", "Lirael", highestRated.getTitle());
			assertEquals("Bookshelf has three Books, highest rated in middle (Lirael)- testing rating.", 7.0, highestRated.getAverageRating(), 0.000001);
		}
		
		@Test
		public void test_bookWithHighestRating_LastHasHighest() {
			Bookshelf c = new Bookshelf("LastHasHighest");
			Book m1 = new Book("The Atrocity Archives");
			Book m2 = new Book("The Jennifer Morgue");
			Book m3 = new Book("The Fuller Memorandum");
			Book m4 = new Book("The Apocalypse Codex");
			c.addBook(m1);
			c.addBook(m2);
			Book m5 = new Book("The Rhesus Chart");
			m5.addRating(8.0);
			c.addBook(m3);
			c.addBook(m4);
			c.addBook(m5);
			
			Book highestRated = c.bookWithHighestRating();
			
			assertEquals("Bookshelf has highest rating at end", "The Rhesus Chart", highestRated.getTitle());
			assertEquals("Bookshelf has highest rating at end - testing rating.", 8.0, highestRated.getAverageRating(), 0.00001);

		}
		
		@Test
		public void test_bookWithHighestRating_encapsulationTest() {
			Bookshelf c = new Bookshelf("LastHasHighest");
			Book m1 = new Book("The Atrocity Archives");
			Book m2 = new Book("The Jennifer Morgue");
			Book m3 = new Book("The Fuller Memorandum");
			Book m4 = new Book("The Apocalypse Codex");
			c.addBook(m1);
			c.addBook(m2);
			Book m5 = new Book("The Rhesus Chart");
			m5.addRating(8.0);
			c.addBook(m3);
			c.addBook(m4);
			c.addBook(m5);
			
			Book highestRated = c.bookWithHighestRating();
			
			assertEquals("Bookshelf has highest rating at end", "The Rhesus Chart", highestRated.getTitle());
			assertEquals("Bookshelf has highest rating at end - testing rating.", 8.0, highestRated.getAverageRating(), 0.00001);

			highestRated.addRating(9.0);
			Book b = ((ArrayList<Book>)c.getBooksOnShelf()).get(4);
			
			assertEquals("Bookshelf has highest rating at end - testing encapsulation.", 8.0, b.getAverageRating(), 0.00001);
		}
		
}
