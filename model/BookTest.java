package model;

import static org.junit.Assert.*;

import org.junit.Test;

public class BookTest {

	// Testing constructors
	@Test 
	public void test_Constructor_emptyTitle()
	{
		Book b = new Book("");
		assertEquals("Created book with empty title", "", b.getTitle());
		assertEquals("Created book with no ratings yet", 0, b.getAverageRating(), 0.000001);
		assertEquals("Created book with no ratings yet", 0, b.getHighestRating(), 0.000001);
		}

	@Test 
	public void test_Constructor_nonemptyTitle()
	{
		Book b = new Book("Gulliver's Travels");
		assertEquals("Created book with title", "Gulliver's Travels", b.getTitle());
	}
	
	@Test
	public void test_CopyConstructor_emptyTitle_noRatings()
	{
		Book b = new Book("");
		Book b1 = new Book(b);
		
		assertEquals("Created book with empty title", "", b1.getTitle());
		assertEquals("Created book with no ratings yet", 0, b1.getAverageRating(), 0.000001);
		assertEquals("Created book with no ratings yet", 0, b1.getHighestRating(), 0.000001);
		
	}
	
	@Test
	public void test_CopyConstructor_hasTitle_oneRatings()
	{
		Book b = new Book("Roots");
		b.addRating(5.0);
		Book b1 = new Book(b);
		assertEquals("Created book with title", "Roots", b1.getTitle());
		assertEquals("Created book with one rating: 5.0", 5.0, b1.getAverageRating(), 0.000001);
		assertEquals("Created book with one rating: 5.0", 5.0, b1.getHighestRating(), 0.000001);
	}
 	
	@Test
	public void test_CopyConstructor_multipleRatings()
	{
		Book b = new Book("Roots");
		b.addRating(9.0);
		b.addRating(3.0);
		b.addRating(10.0);
		b.addRating(5.0);
		Book b1 = new Book(b);
		
		assertEquals("Created book with title", "Roots", b1.getTitle());
		assertEquals("Created book with multiple ratings (9.0,3.0,10.0,5.0)", 6.75, b1.getAverageRating(), 0.000001);
		assertEquals("Created book with multiple ratings (9.0,3.0,10.0,5.0)", 10.0, b1.getHighestRating(), 0.000001);
	}
	
	// Testing getters
	
	@Test
	public void test_setter_and_getter_title_emptyString(){
		Book c = new Book("");
		assertEquals("Set title to empty string", "", c.getTitle());
	}
	
	@Test 
	public void test_setter_and_getter_title_nonEmpty(){
		Book b = new Book("One Hundred Years of Solitude");
		assertEquals("non-empty title", "One Hundred Years of Solitude", b.getTitle());
	}
		
			
	// Testing other functions
	@Test
	public void test_addRating_toolow()
	{
		Book b = new Book("The Adventures of Peter Rabbit");
		b.addRating(-1.0);
	
		assertEquals("Added a single rating less than zero", 0.0, b.getAverageRating(), 0.000001);
		assertEquals("Added a single rating less than zero", 0.0, b.getHighestRating(), 0.000001);
	}
	
	@Test
	public void test_addRating_tooHigh()
	{
		Book b = new Book("The Tailor of Gloucester");
		b.addRating(11.0);
	
		assertEquals("Added a single rating greater than ten", 10.0, b.getAverageRating(), 0.000001);
		assertEquals("Added a single rating greater than ten", 10.0, b.getHighestRating(), 0.000001);
	}
	
	@Test
	public void test_addRating_twoRatings()
	{
		Book b = new Book("The Tale of Tom Kitten");
		b.addRating(8.0);
		b.addRating(6.0);
	
		assertEquals("Added two ratings", 7.0, b.getAverageRating(), 0.000001);
		assertEquals("Added two ratings", 8.0, b.getHighestRating(), 0.000001);
	}
	
	@Test
	public void test_addRating_multipleRatings()
	{
		Book b = new Book("The Tale of Tom Kitten");
		b.addRating(8.0);
		b.addRating(6.0);
		b.addRating(-2);
		b.addRating(11.0);
		b.addRating(4.5);
	
		assertEquals("Added many ratings (8.0,6.0,-2,11,4.5)", 5.7, b.getAverageRating(), 0.000001);
		assertEquals("Added many ratings(8.0,6.0,-2,11,4.5)", 10.0, b.getHighestRating(), 0.000001);
	}
	
	@Test
	public void test_mergeRatings_oneRatingEach()
	{
		Book b = new Book("Hamlet");
		b.addRating(8.0);
		
		Book b1 = new Book("Hamlet");
		b1.addRating(6.0);
		
		Book m = b1.mergeRatings(b);
		assertEquals("Testing merged book average rating", 7.0, m.getAverageRating(), 0.000001);
		assertEquals("Testing merged book highest rating", 8.0, m.getHighestRating(), 0.000001);
		
		assertEquals("Testing first book average rating (should be unchanged)", 8.0, b.getAverageRating(), 0.000001);
		assertEquals("Testing first book highest rating (should be unchanged)", 8.0, b.getHighestRating(), 0.000001);

		assertEquals("Testing second book average rating (should be unchanged)", 6.0, b1.getAverageRating(), 0.000001);
		assertEquals("Testing second book highest rating (should be unchanged)", 6.0, b1.getHighestRating(), 0.000001);
	}

	@Test
	public void test_mergeRatings_onlyOneBookHasRatings()
	{
		Book b = new Book("Hamlet");
		b.addRating(8.0);
		b.addRating(7.0);
		
		Book b1 = new Book("Hamlet");
		
		Book m = b1.mergeRatings(b);
		assertEquals("Merged ratings", 7.5, m.getAverageRating(), 0.000001);
		assertEquals("Merged ratings", 8.0, m.getHighestRating(), 0.000001);

		assertEquals("Testing first book average rating (should be unchanged)", 7.5, b.getAverageRating(), 0.000001);
		assertEquals("Testing first book highest rating (should be unchanged)", 8.0, b.getHighestRating(), 0.000001);

		assertEquals("Testing second book average rating (should be unchanged)", 0.0, b1.getAverageRating(), 0.000001);
		assertEquals("Testing second book highest rating (should be unchanged)", 0.0, b1.getHighestRating(), 0.000001);
	}

	@Test
	public void test_mergeRatings_bothBooksHaveMultipleRatings()
	{
		Book b = new Book("The Very Hungry Caterpillar");
		b.addRating(10.0);
		b.addRating(4.0);
		b.addRating(12.0);
		b.addRating(9.75);
		
		Book b1 = new Book("The Very Hungry Caterpillar");
		b1.addRating(-11);
		b1.addRating(6.5);
		b1.addRating(4.3);
		
		Book m = b1.mergeRatings(b);
		assertEquals("Merged ratings", 6.3642857, m.getAverageRating(), 0.000001);
		assertEquals("Merged ratings", 10.0, m.getHighestRating(), 0.000001);

		assertEquals("Testing first book average rating (should be unchanged)", 8.4375, b.getAverageRating(), 0.000001);
		assertEquals("Testing first book highest rating (should be unchanged)", 10, b.getHighestRating(), 0.000001);

		assertEquals("Testing second book average rating (should be unchanged)", 3.6, b1.getAverageRating(), 0.000001);
		assertEquals("Testing second book highest rating (should be unchanged)", 6.5, b1.getHighestRating(), 0.000001);
	}

	@Test
	public void test_mergeRatings_titleMismatch()
	{
		Book b = new Book("A Midsummer Night's Dream");
		b.addRating(8.0);
		
		Book b1 = new Book("Macbeth");
		b1.addRating(6.0);
		
		assertNull("Merged ratings", b.mergeRatings(b1));

		assertEquals("Testing first book average rating (should be unchanged)", 8.0, b.getAverageRating(), 0.000001);
		assertEquals("Testing first book highest rating (should be unchanged)", 8.0, b.getHighestRating(), 0.000001);

		assertEquals("Testing second book average rating (should be unchanged)", 6.0, b1.getAverageRating(), 0.000001);
		assertEquals("Testing second book highest rating (should be unchanged)", 6.0, b1.getHighestRating(), 0.000001);
	}
	
}
