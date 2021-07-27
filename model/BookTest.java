import static org.junit.Assert.*;

import org.junit.Test;

import java.io.*;

public class BookTest extends FormatTester{
	
 	public class BookMock extends Book{
		int next = 0;

		public BookMock(String mockTitle, int mockNumberOfWords) {
			super(mockTitle, mockNumberOfWords);
		}
		
		public BookMock(BookMock c) {
			super(c);
		}
		
		public int minutesToConsume(){
			return next;
		}
	} 

	public static final String CLASSNAME = "Book";
	
	public BookTest() {
		super(CLASSNAME, false);
	}
		
	private void testClassDefinition(){
		String[] instanceVars = {"String title", "int numberOfWords"};
		assertTrue("Instance variables should be private with correct name and type.", instanceVariablesArePrivate(instanceVars));

		assertTrue("Class should not have the default constructor.", noDefaultConstructor());
		
		String[] abstractMethods = {"int minutesToConsume()"};
		assertTrue("Class should have abstract method minutesToConsume that returns an int (minimize whitespace in signature).", hasRequiredAbstractMethods(abstractMethods));
		
		String[] protectedMethods = {"void setTitle", "void setNumberOfWords", "int getNumberOfWords"};
		assertTrue("Class should have protected methods setTitle, setNumberOfWords and getNumberOfWords.", hasRequiredProtectedMethods(protectedMethods));

	}
	
	// Testing constructors
	@Test
	public void test_Constructor_numberOfWords_Zero_GoodNumber(){
		testClassDefinition();
		Book c = new BookMock("Black Beauty", 0);
		assertEquals("Created book with valid but zero words", 0, c.getNumberOfWords());
	}
	
	@Test
	public void test_Constructor_numberOfWords_LessThanZero_BadNumber(){
		testClassDefinition();
		Book c = new BookMock("Heidi", -1);
		assertEquals("Created book with an invalid number of words", 0, c.getNumberOfWords());
	}
	
	@Test
	public void test_Constructor_numberOfWords_AtMax_GoodNumber(){
		testClassDefinition();
		Book c = new BookMock("Black Beauty", 500000);
		assertEquals("Created book with valid and maximum words", 500000, c.getNumberOfWords());
	}
	
	@Test
	public void test_Constructor_numberOfWords_OverMax_GoodNumber(){
		testClassDefinition();
		Book c = new BookMock("Heidi", 500001);
		assertEquals("Created book with an invalid number of words", 0, c.getNumberOfWords());
	}
	
	@Test 
	public void testCopyConstructor()
	{
		testClassDefinition();
		BookMock c = new BookMock("Detropia", 35000);
		Book c1 = new BookMock(c);
		assertEquals("Created book with title Detropia", "Detropia", c1.getTitle());
		assertEquals("Created book with numberOfWords 35000", 35000, c1.getNumberOfWords());
	
	}
	
	// Testing setter and getters
	@Test
	public void test_setter_and_getter_title(){
		testClassDefinition();
		Book c = new BookMock("Old Title", 1);
		c.setTitle("New Title");
		assertEquals("Changed title", "New Title", c.getTitle());
	}
	
	@Test
	public void test_setter_and_getter_numberOfWords_GoodNumber(){
		testClassDefinition();
		Book c = new BookMock("Black Beauty", 1);
		c.setNumberOfWords(7500);
		assertEquals("Created book with valid number of words", 7500, c.getNumberOfWords());
	}
	
	@Test
	public void test_setter_and_getter_numberOfWords_Zero_GoodNumber(){
		testClassDefinition();
		Book c = new BookMock("Black Beauty", 1);
		c.setNumberOfWords(0);
		assertEquals("Created book with valid but zero words", 0, c.getNumberOfWords());
	}
	
	@Test
	public void test_setter_and_getter_numberOfWords_LessThanZero_BadNumber(){
		testClassDefinition();
		Book c = new BookMock("Heidi", 0);
		c.setNumberOfWords(-1);
		assertEquals("Created book with an invalid number of words", 0, c.getNumberOfWords());
	}
	
	@Test
	public void test_setter_and_getter_numberOfWords_AtMax_GoodNumber(){
		testClassDefinition();
		Book c = new BookMock("Black Beauty", 0);
		c.setNumberOfWords(500000);
		assertEquals("Created book with valid but 500000 words", 500000, c.getNumberOfWords());
	}
	
	@Test
	public void test_setter_and_getter_numberOfWords_OverMax_GoodNumber(){
		testClassDefinition();
		Book c = new BookMock("Heidi", 10);
		c.setNumberOfWords(500001);
		assertEquals("Created book with an invalid number of words", 10, c.getNumberOfWords());
	}
	
	// Test difficulty
	@Test
	public void test_difficulty_easy(){
		testClassDefinition();
		BookMock b = new BookMock("Test", 500);
		b.next = 1;
		assertEquals("minutes to consume is 1, expected difficulty Easy", "Easy", b.difficulty());
		b.next = 29;
		assertEquals("minutes to consume is 29, expected difficulty Easy", "Easy", b.difficulty());
	}
	
	@Test
	public void test_difficulty_moderate(){
		testClassDefinition();
		BookMock b = new BookMock("Test", 500);
		b.next = 30;
		assertEquals("minutes to consume is 30", "Moderate", b.difficulty());
		b.next = 119;
		assertEquals("minutes to consume is 119", "Moderate", b.difficulty());
	}
	
	@Test
	public void test_difficulty_hard(){
		testClassDefinition();
		BookMock b = new BookMock("Test", 500);
		b.next = 120;
		assertEquals("minutes to consume is 120", "Hard", b.difficulty());
		b.next = 239;
		assertEquals("minutes to consume is 239", "Hard", b.difficulty());
	}
	
	@Test
	public void test_difficulty_extra_challenge(){
		testClassDefinition();
		BookMock b = new BookMock("Test", 500);
		b.next = 240;
		assertEquals("minutes to consume is 240", "Extra Challenge", b.difficulty());
		b.next = 500;
		assertEquals("minutes to consume is 500", "Extra Challenge", b.difficulty());
		b.next = 12345676;
		assertEquals("minutes to consume is 12345676", "Extra Challenge", b.difficulty());
	}
	

	
	// Test toString
	@Test
	public void test_toString()
	{
		testClassDefinition();
		BookMock c = new BookMock("Neverwhere", 75000);
		c.next = 100;
		assertEquals("Neverwhere with 100 minutes to consume", "Title: Neverwhere Difficulty: Moderate", c.toString());
	}	

	@Test
	public void test_toString2()
	{
		testClassDefinition();
		BookMock c = new BookMock("Neverwhere", 75000);
		c.next = 500;
		assertEquals("Neverwhere with 500 minutes to consume", "Title: Neverwhere Difficulty: Extra Challenge", c.toString());
	}	
}