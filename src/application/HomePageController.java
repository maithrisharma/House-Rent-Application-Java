package application;

import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.layout.*;


public class HomePageController
{


	@FXML
	private BorderPane borderPane;
	
	@FXML 
	private Button homePane;
	
	@FXML 
	private Button registerPane;
	
	@FXML 
	private Button propertyPane;
	



	public void initialize() {
		
		System.out.println("Welcome Home Page");

		
	}
	
	public void registerPaneListener(ActionEvent e) throws IOException {


		BorderPane a = FXMLLoader.load(getClass().getResource("RegisterCustomer.fxml"));
		
		borderPane.setCenter(a);
		
    		
    }
	
	public void homePaneListener(ActionEvent e) throws IOException {

		BorderPane a = FXMLLoader.load(getClass().getResource("HomePage.fxml"));
		
		borderPane.setCenter(a);
		Object x = borderPane.getTop();
		borderPane.setTop(null);
		borderPane.setBottom(null);

    }
	
	public void propertyPaneListener(ActionEvent e) throws IOException {
    	
		BorderPane a = FXMLLoader.load(getClass().getResource("PropertyMain.fxml"));
		
		borderPane.setCenter(a);
		
		
    }


}
