import static org.junit.Assert.*;

import org.junit.Test;

import java.io.*;

public class AudioBookTest extends FormatTester {
	public static final String CLASSNAME = "AudioBook";
	
	public AudioBookTest() {
		super(CLASSNAME, false);
	}
	
	private void testClassDefinition(){
		String[] instanceVars = {"int sizeInKB", "double playbackRatio"};
		assertTrue("Instance variables should be private with correct name and type.", instanceVariablesArePrivate(instanceVars));

		assertTrue("Class should not have the default constructor.", noDefaultConstructor());
		
		assertFalse("Should not override getTitle.", hasMethod("String getTitle()"));
		assertFalse("Should not override (or call) difficulty.", hasMethod("String difficulty()"));
		assertFalse("Should not override (or call) setTitle.", hasMethod("void setTitle()"));
		assertFalse("Should not override (or call) setNumberOfWords.", hasMethod("void setNumberOfWords()"));
	}
	
	// Testing constructors
	@Test
	public void test_Constructor_sizeInKB_Zero_GoodNumber(){
		testClassDefinition();
		AudioBook c = new AudioBook("Black Beauty", 15000, 0);
		assertEquals("Created book with valid size of 0", 0, c.getSizeInKB());
	}
	
	@Test
	public void test_Constructor_sizeInKB_LessThanZero_BadNumber(){
		testClassDefinition();
		AudioBook c = new AudioBook("Heidi", 15000, -1);
		assertEquals("Created book with an invalid size of -1", 0, c.getSizeInKB());
	}
	
	@Test
	public void test_Constructor_sizeInKB_GoodNumber(){
		testClassDefinition();
		AudioBook c = new AudioBook("Black Beauty", 15000, 100);
		assertEquals("Created book with a valid size of 100", 100, c.getSizeInKB());
	}
	
	@Test 
	public void testCopyConstructor()
	{
		testClassDefinition();
		AudioBook c = new AudioBook("The Fifth Season", 80000, 1920000 );
		c.setPlaybackRatio(1.4);
		AudioBook c1 = new AudioBook(c);
		assertEquals("Created book with title The Fifth Season", "The Fifth Season", c1.getTitle());
		assertEquals("Created book with numberOfWords 80000", 80000, c1.getNumberOfWords());
		assertEquals("Created book with sizeInKB 1920000", 1920000, c1.getSizeInKB());
		assertEquals("Created book with playbackRatio 1.4", 1.4, c1.getPlaybackRatio(), 0.000001);
	}
	
	// test setters and getters
	
	@Test
	public void test_setter_playbackRatio_zero_BadNumber(){
		testClassDefinition();
		AudioBook c = new AudioBook("Heidi", 5000, 1920);
		c.setPlaybackRatio(0);
		assertEquals("Created book with an invalid playback ratio", 1.0, c.getPlaybackRatio(), 0.000001);
	}
	
	@Test
	public void test_setter_playbackRatio_one_GoodNumber(){
		testClassDefinition();
		AudioBook c = new AudioBook("Heidi", 5000, 1920);
		c.setPlaybackRatio(1.1);
		assertEquals("Created book with a valid playback ratio", 1.1, c.getPlaybackRatio(), 0.00001);
	}
	
	// test minutesToConsume
	@Test
	public void test_minutesToConsume()
	{
		testClassDefinition();
		AudioBook c = new AudioBook("The Steerswoman", 10000, 192000);
		c.setPlaybackRatio(1.25);
		assertEquals("size 192000, playback ratio 1.25 Minutes to Consume 80", 80, c.minutesToConsume());
	}
	
	@Test
	public void test_minutesToConsume2()
	{
		testClassDefinition();
		AudioBook c = new AudioBook("The Steerswoman", 10000, 2000000);
		c.setPlaybackRatio(2.0);
		assertEquals("size 2,000,000, playback ratio 2.0 Minutes to Consume 520", 520, c.minutesToConsume());
	}
	
	// testing that parent method difficulty invoked overridden method
	@Test
	public void test_difficulty_easy(){
		testClassDefinition();
		AudioBook b = new AudioBook("Test", 1000, 19200);
		b.setPlaybackRatio(1.25);
		assertEquals("num words: 1000, sizeInKB: 19200, playback ratio: 1.25 expected difficulty Easy", "Easy", b.difficulty());
	}
	
	@Test
	public void test_difficulty_moderate(){
		testClassDefinition();
		AudioBook b = new AudioBook("Test", 10000, 192000);
		b.setPlaybackRatio(.95);
		assertEquals("num words: 10000, sizeInKB: 192000 playback ratio: .95", "Moderate", b.difficulty());
	}
	
	@Test
	public void test_difficulty_hard(){
		testClassDefinition();
		AudioBook b = new AudioBook("Test", 200000,250000);
		b.setPlaybackRatio(1.0);
		assertEquals("numWords: 200000, sizeInKB: 250,000 playback ratio: 1", "Hard", b.difficulty());
	}
	
	@Test
	public void test_difficulty_extra_challenge(){
		testClassDefinition();
		AudioBook b = new AudioBook("Test", 500000, 1000000);
		b.setPlaybackRatio(2.0);
		assertEquals("numWords: 500,000 sizeInKB: 1,000,000 playback ratio: 2", "Extra Challenge", b.difficulty());
	}	
	
	
	// test toString
	@Test
	public void test_toString()
	{
		testClassDefinition();
		AudioBook c = new AudioBook("Neverwhere", 10000, 192000);
		assertEquals("Neverwhere, 10000 words, 192000Kb", "Title: Neverwhere Difficulty: Moderate Size: 192000 Ratio: 1.0", c.toString());
	}
	
}