import static org.junit.Assert.*;

import org.junit.Test;

import java.io.*;

public class PaperBookTest extends FormatTester {
	public static final String CLASSNAME = "PaperBook";
	
	private void testClassDefinition(){
		String[] instanceVars = {"int numPages"};
		assertTrue("Instance variables should be private with correct name and type.", instanceVariablesArePrivate(instanceVars));

		assertTrue("Class should not have the default constructor.", noDefaultConstructor());
		
		assertFalse("Should not override getTitle.", hasMethod("String getTitle()"));
		assertFalse("Should not override (or call) setTitle.", hasMethod("setTitle"));
		assertFalse("Should not override (or call) setNumberOfWords.", hasMethod("setNumberOfWords"));
		assertFalse("Should not override (or call) difficulty.", hasMethod("difficulty"));
	}
	
	public PaperBookTest() {
		super(CLASSNAME, false);
	}
		
	// Testing constructors
	@Test
	public void test_Constructor_numberOfPages_One_GoodNumber(){
		testClassDefinition();
		PaperBook c = new PaperBook("Black Beauty", 0, 1);
		assertEquals("Created book with valid one page", 1, c.getNumPages());
	}
	
	@Test
	public void test_Constructor_numberOfPages_LessThanOne_BadNumber(){
		testClassDefinition();
		PaperBook c = new PaperBook("Heidi", 0, 0);
		assertEquals("Created book with an invalid number of pages", 1, c.getNumPages());
		c = new PaperBook("Heidi", 0, -1);
		assertEquals("Created book with an invalid number of pages", 1, c.getNumPages());
	}
	
	@Test 
	public void testCopyConstructor()
	{
		testClassDefinition();
		PaperBook c = new PaperBook("The Fifth Season", 80000, 100);
		PaperBook c1 = new PaperBook(c);
		assertEquals("Created book with title The Fifth Season", "The Fifth Season", c1.getTitle());
		assertEquals("Created book with numberOfWords 80000", 80000, c1.getNumberOfWords());
		assertEquals("Created book with numPages 100", 100, c1.getNumPages());
	}
	
	// test setters and getters
	
	@Test
	public void test_setter_numPages_zero_BadNumber(){
		testClassDefinition();
		PaperBook c = new PaperBook("Heidi", 5000, 50);
		c.setNumPages(0);
		assertEquals("Created book with 50 pages, then set to 0 pages.", 1, c.getNumPages());
	}
	
	@Test
	public void test_setter_numPages_one_GoodNumber(){
		testClassDefinition();
		PaperBook c = new PaperBook("Heidi", 5000, 50);
		c.setNumPages(1);
		assertEquals("Created book with a valid number of pages", 1, c.getNumPages());
	}
	
	@Test
	public void test_setter_numPages_many_GoodNumber(){
		testClassDefinition();
		PaperBook c = new PaperBook("Heidi", 5000, 1);
		c.setNumPages(10);
		assertEquals("Created book with a valid number of pages", 10, c.getNumPages());
	}
	
	// test minutesToConsume
	@Test
	public void test_minutesToConsume_smallestRatio()
	{
		testClassDefinition();
		PaperBook c = new PaperBook("The Steerswoman", 9500, 1);
		c.setNumPages(500);
		assertEquals("Ratio: 9500/500", 250, c.minutesToConsume());
		c.setNumPages(9500);
		assertEquals("Ratio: 9500/9500", 4750, c.minutesToConsume());
		c.setNumPages(700);
		assertEquals("Ratio: 9500/700", 350, c.minutesToConsume());
	}
	
	@Test
	public void test_minutesToConsume_ratioInSecondInterval()
	{
		testClassDefinition();
		PaperBook c = new PaperBook("The Steerswoman", 9500, 1);
		c.setNumPages(475);
		assertEquals("Ratio: 9500/499", 475 , c.minutesToConsume());
		c.setNumberOfWords(396);
		c.setNumPages(4);
		assertEquals("Ratio: 396/4", 4, c.minutesToConsume());
	}
	
	@Test
	public void test_minutesToConsume_ratioInThirdInterval()
	{
		testClassDefinition();
		PaperBook c = new PaperBook("The Steerswoman", 100000, 1);
		c.setNumPages(1000);
		assertEquals("Ratio: 400000/1000", 2000 , c.minutesToConsume());
		c.setNumberOfWords(498);
		c.setNumPages(2);
		assertEquals("Ratio: 498/2", 4, c.minutesToConsume());
	}
	
	@Test
	public void test_minutesToConsume_largestRatio()
	{
		testClassDefinition();
		PaperBook c = new PaperBook("The Steerswoman", 500, 1);
		c.setNumPages(2);
		assertEquals("Ratio: 500/2", 8 , c.minutesToConsume());
		c.setNumberOfWords(500000);
		c.setNumPages(100);
		assertEquals("Ratio: 500000", 400, c.minutesToConsume());
	}
	
	@Test
	public void test_difficulty_easy(){
		testClassDefinition();
		PaperBook b = new PaperBook("Test", 20, 3);
		assertEquals("num words: 20, numPage: 3, expected difficulty Easy", "Easy", b.difficulty());
	}
	
	@Test
	public void test_difficulty_moderate(){
		testClassDefinition();
		PaperBook b = new PaperBook("Test", 10000, 20);
		assertEquals("num words: 10000, num pages: 20", "Moderate", b.difficulty());
	}
	
	@Test
	public void test_difficulty_hard(){
		testClassDefinition();
		PaperBook b = new PaperBook("Test", 2000,300);
		assertEquals("numWords: 2000, numPages: 300", "Hard", b.difficulty());
	}
	
	@Test
	public void test_difficulty_extra_challenge(){
		testClassDefinition();
		PaperBook b = new PaperBook("Test", 500000, 100);
		assertEquals("numWords: 500,000 numPages: 100", "Extra Challenge", b.difficulty());
	}	
	
	// test toString
	@Test
	public void test_toString()
	{
		testClassDefinition();
		assertTrue("Should override toString and it should invoke parent toString (not getter methods in parent).", toStrInvokesParentToStr());

		PaperBook c = new PaperBook("Neverwhere", 75000, 100);
		assertEquals("Neverwhere, 75000 words, 100 pages", "Title: Neverwhere Difficulty: Extra Challenge Pages: 100", c.toString());
	}
	
	@Test
	public void test_toString2()
	{
		testClassDefinition();
		assertTrue("Should override toString and it should invoke parent toString (not getter methods in parent).", toStrInvokesParentToStr());

		PaperBook c = new PaperBook("Another book", 5000, 25);
		assertEquals("Another book, 5000 words, 25 pages", "Title: Another book Difficulty: Moderate Pages: 25", c.toString());
	}
	
}