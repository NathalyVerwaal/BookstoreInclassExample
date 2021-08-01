package application;

import java.util.Random;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import model.*;

public class BookstoreController {

    @FXML
    private TextField ratingTextfield;

    @FXML
    private TextArea bookshelfTextArea;

    @FXML
    private ComboBox<Book> shelfComboBox;

    @FXML
    private TextField bookTitleTextfield;
    
    private Bookshelf shelf = new Bookshelf("My first shelf");

    @FXML
    void getHighestRatedBook(ActionEvent event) {
    	System.out.println("get highest rated book");

    }

    @FXML
    void addRating(ActionEvent event) {
    	System.out.println("add rating");

    }

    @FXML
    void addBook(ActionEvent event) {
    	// Get the title of the book to add
    	String title = bookTitleTextfield.getText();
    	Book aBook = null;
    	
    	Random rand = new Random();
    	String type = "";
    	if (rand.nextBoolean()) {
    		aBook = new PaperBook(title,10000,500);
    		type = "paper";
    	} else {
    		aBook = new AudioBook(title,50000,1.0);
    		type = "audio";
    	}
    	
    	// add the book to the shelf (in the model)
    	shelf.addBook(aBook);
    	bookshelfTextArea.setText(shelf.toString());
    	
    	// update the view to the updates shelf in the model
    	shelf.getBooksOnShelf().toArray(new Book[1]);
    	ObservableList<Book> comboBoxList = FXCollections.observableArrayList(shelf.getBooksOnShelf());
    	shelfComboBox.setItems(comboBoxList);
    	
    	System.out.println("add book " + type);

    }

}
