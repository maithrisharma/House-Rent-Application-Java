package application;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class PropertyMainController {

	@FXML
	private Button viewAll;
	
	@FXML
	private Button viewAvailable;
	
	@FXML
	private Button viewPlaces;
	
	@FXML
	private Button viewRented;
	
	
	@FXML 
	private Button homePane;
	
	@FXML 
	private Button registerPane;
	
	@FXML 
	private Button propertyPane;
	
	@FXML 
	private BorderPane borderPane; 

	public void initialize() {
		
		System.out.println("Welcome Property Main Page");

		
	}
	
	public void viewAllListener(ActionEvent e) throws IOException {
		BorderPane a = FXMLLoader.load(getClass().getResource("viewAll.fxml")); 
		
		borderPane.setCenter(a);
    }
	

//	public void xx_viewAllListener(ActionEvent e) throws IOException {
//		Parent parent = FXMLLoader.load(
//	               getClass().getResource("PropertyManagement.fxml")); 
//	      
//	      // Build the scene graph.
//	      Scene scene = new Scene(parent); 
//	
//	      Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
//	      // Display our window, using the scene graph.
//	      stage.setTitle("Propety Management"); 
//	      stage.setScene(scene);
//	      stage.show(); 
//    }
	
	public void viewAvailableListener(ActionEvent e) throws IOException {
		BorderPane a = FXMLLoader.load(getClass().getResource("AvailableProperties.fxml")); 
		
		borderPane.setCenter(a);
		borderPane.setTop(null);
    }
	
//	public void viewAllListener(ActionEvent e) throws IOException {
//    	Parent parent = FXMLLoader.load(
//	               getClass().getResource("PropertyManagment.fxml")); 
//	      
//	      // Build the scene graph.
//	      Scene scene = new Scene(parent); 
//	
//	      Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
//	      // Display our window, using the scene graph.
//	      stage.setTitle("Propety Management"); 
//	      stage.setScene(scene);
//	      stage.show(); 
//    }
	

}
