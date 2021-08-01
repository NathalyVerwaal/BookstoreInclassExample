package model;

// full name of the class is model.Book

public class Book {
	private String title = "";
	private double averageRating;
	private double highestRating;
	private int numRatings;
	private int numOfWords;
	
	public Book(String aTitle) {
		this(aTitle, 0);
	}
	
	public Book(String aTitle, int numOfWords) {
		title = aTitle;
		this.numOfWords = numOfWords;
		
	}

	public Book(Book b) {
		title = b.title;
		averageRating = b.averageRating;
		highestRating = b.highestRating;
		numRatings = b.numRatings;
	}
	public void addRating(double aRating) {
		if (aRating < 0) aRating = 0;
		if (aRating > 10) aRating = 10;
		
		if (aRating > highestRating) highestRating = aRating;
		
		double sumOfAllRatingsSoFar = numRatings * averageRating + aRating;
		numRatings++;
		averageRating = sumOfAllRatingsSoFar/numRatings;		
	}
	
	@Override
	public String toString() {
		return title;
	}

	public String getTitle() {
		// TODO Auto-generated method stub
		return title;
	}

	public double getAverageRating() {
		return averageRating;
	}

	public double getHighestRating() {
		return highestRating;
	}
	
	public Object difficulty() {
		// TODO Auto-generated method stub
		return null;
	}


	public Book mergeRatings(Book b) {
		if (title.equalsIgnoreCase(b.title)) {
			Book mergedBook = new Book(title,numOfWords);
			mergedBook.highestRating= Math.max(highestRating, b.highestRating);
			mergedBook.numRatings = numRatings + b.numRatings;
			mergedBook.averageRating = 
					(averageRating * numRatings + b.averageRating * b.numRatings)/mergedBook.numRatings;
			return mergedBook;
			
		}
		return null;
	}

	public int getNumberOfWords() {
		// TODO Auto-generated method stub
		return numOfWords;
	}

	public void setNumberOfWords(int numOfWords) {
		this.numOfWords = numOfWords;
		// TODO Auto-generated method stub
		
	}

	public void setTitle(String aTitle) {
		title = aTitle;
		// TODO Auto-generated method stub
		
	}

	public int minutesToConsume() {
		return numOfWords/400;
	}

}
