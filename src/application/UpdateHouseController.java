package application;

import java.time.LocalDate;

import javafx.fxml.FXML;
import javafx.scene.control.*;

public class UpdateHouseController {
	
	private House house;
	private String error = "";

	@FXML
	private TextField type;
	
	@FXML
	private TextField latitude;
	
	@FXML
	private TextField longitude;
	
	@FXML
	private TextField postcode;
	
	@FXML
	private TextField furnishingType;
	
	@FXML
	private TextField rent;
	
	@FXML
	private TextField bedrooms;
	
	@FXML
	private TextField bathrooms;
	
	@FXML
	private TextField size;
	
	@FXML
	private TextField garden;
	
//	@FXML
//	private Label message;
	
//	@FXML
//	private Button ok;
//	
	
	public void initialize() {
		
		System.out.println("Dialog Box");
		//message.setVisible(false);

		
	}
	
	public String getError() {
		return error;
	}

	public void setHouse(House h) {
		System.out.println("House Id:"+ h.getId());
		this.house = h;
		
		type.setText(h.getType());
		//type.setEditable(false);
		postcode.setText(h.getPostcode());
		rent.setText(Integer.toString(h.getRent()));
		bedrooms.setText(Integer.toString(h.getBedrooms()));
		bathrooms.setText(Integer.toString(h.getBathrooms()));
		furnishingType.setText(h.getFurnishingStatus());
		size.setText(Integer.toString(h.getSize()));
		latitude.setText(Double.toString(h.getLatitude()));
		latitude.setEditable(false);
		longitude.setText(Double.toString(h.getLongitude()));
		longitude.setEditable(false);
		garden.setText(h.isGarden()?"Yes":"No");
		
	}
	
	public House getHouse() {
		
		//House h = new House(house.getListed(),Integer.parseInt(bedrooms.getText()));
		
		return new House(house,type.getText(),Integer.parseInt(bedrooms.getText()),Integer.parseInt(bathrooms.getText()),Integer.parseInt(rent.getText()),Integer.parseInt(size.getText()),postcode.getText(),furnishingType.getText(),garden.getText());
	}
	
	
	
	public House addHouse() {
		//LocalDate today = LocalDate.now().toString();
		boolean b = true;
		error = "";
		if(!(isInteger(bedrooms.getText()))){
			bedrooms.setStyle("-fx-text-fill: red;");
			b = false;
		}
		if(!(isInteger(bathrooms.getText()))) {
			bathrooms.setStyle("-fx-text-fill: red;");
			b = false;
			 
		}
			
		if(!(isInteger(rent.getText()))) {
			rent.setStyle("-fx-text-fill: red;");
			b = false;
		}
		if(!(isInteger(size.getText()))) {
			size.setStyle("-fx-text-fill: red;");
			b = false;
		}
		if(!(isDouble(latitude.getText()))){ 
			latitude.setStyle("-fx-text-fill: red;");
			b = false;
			
		}
			if(!(isDouble(longitude.getText()))) {
			longitude.setStyle("-fx-text-fill: red;");
			b = false;
		}
		if(!(garden.getText().toLowerCase().equals("yes") || garden.getText().toLowerCase().equals("no"))){
			garden.setStyle("-fx-text-fill: red;");
			b = false;
		}
		if(postcode.getText().length()>7) {
			postcode.setStyle("-fx-text-fill: red;");
			b = false;
			
		}
		if(!b) {
			//ok.setDisable(true);
			
			return null;
		}
		
		boolean g = garden.getText().equals("Yes")?true:false;
		return new House(LocalDate.now().toString(),Integer.parseInt(bedrooms.getText()),Integer.parseInt(bathrooms.getText()),Integer.parseInt(rent.getText()),Integer.parseInt(size.getText()),postcode.getText(),Double.parseDouble(latitude.getText()),Double.parseDouble(longitude.getText()),furnishingType.getText(),type.getText(),g,false);
		
		
	}
	
	public static boolean isInteger(String strNum) {
	    if (strNum == null) {
	        return false;
	    }
	    try {
	    	int i = Integer.parseInt(strNum);
	        //double d = Double.parseDouble(strNum);
	    } catch (NumberFormatException nfe) {
	        return false;
	    }
	    return true;
	}
	
	public static boolean isDouble(String strNum) {
	    if (strNum == null) {
	        return false;
	    }
	    try {
	    	
	        double d = Double.parseDouble(strNum);
	    } catch (NumberFormatException nfe) {
	        return false;
	    }
	    return true;
	}
	
}
