package application;

import java.io.IOException;
import java.net.URL;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;

public class ViewSinglePropertyToRentController  implements Initializable{
	
	private House selectedHouse;
	
	@FXML
	private Label type;
	
	@FXML
	private Label bedrooms;
	
	@FXML
	private Label bathrooms;
	
	@FXML
	private Label postcode;
	
	@FXML
	private Label size;
	
	@FXML
	private Label rent;
	
	@FXML
	private Label furnishing;
	
	@FXML
	private Label garden;
	
	@FXML
	private Label listed;
	
	@FXML
	private Label latitude;

	@FXML
	private Label longitude;

	@FXML
	private Label distanceLabel;

	@FXML
	private Label distanceValM;

	@FXML
	private Label distanceValK;

	@FXML
	private Button rentProperty;
	
	@FXML
	private ComboBox<String> comboPlaces;

	
	@FXML
	private Button back;
	
	@FXML 
	private BorderPane borderPane;
	
	//private HouseList houseList;

	private PlaceList placeList;
	private PlaceOfInterest placeOfInterest;
	private ArrayList<String> places;
	private String selectedPlace = "";
	private static final DecimalFormat df = new DecimalFormat("0.00");
	
	public void initData(House house)
	{
		selectedHouse = house;
		System.out.println(selectedHouse.toString());
		type.setText(selectedHouse.getType());
		postcode.setText(selectedHouse.getPostcode());
		bedrooms.setText(Integer.toString(selectedHouse.getBedrooms())+" Bedrooms");
		bathrooms.setText(Integer.toString(selectedHouse.getBathrooms())+" Bathrooms");
		size.setText(Integer.toString(selectedHouse.getSize()));
		furnishing.setText(selectedHouse.getFurnishingStatus());
		garden.setText(selectedHouse.isGarden()?"Yes":"No");
		rent.setText("£"+Integer.toString(selectedHouse.getRent())+ " pcm");
		listed.setText(selectedHouse.getListed());
		latitude.setText(Double.toString(selectedHouse.getLatitude()));
		longitude.setText(Double.toString(selectedHouse.getLongitude()));
		distanceLabel.setVisible(false);
		distanceValM.setVisible(false);
		distanceValK.setVisible(false);
		//houseList = hList;
		comboPlaces.getItems().addAll( places);
		
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		System.out.println("Inside view Single Property");
		try {
			placeList = DataHandler.readPlaceCSV();
			places = new ArrayList();
			for(PlaceOfInterest p: placeList) {
				//if(!(c.isRented())) {
				places.add(p.getPlace());
				//}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		
	}
	
	public void ComboBoxListener() {		

		selectedPlace = comboPlaces.getValue();
		System.out.println(comboPlaces.getValue());
		//for loop in places and calculate distance
		for(PlaceOfInterest p: placeList) {
			if(p.getPlace().equals(selectedPlace)) {
				String s = ViewSinglePropertyToRentController.distance(p.getLatitude(), p.getLongitude(), selectedHouse.getLatitude(), selectedHouse.getLongitude());
				distanceLabel.setText(selectedPlace);
				String strArray[] = s.split(",");
				
				distanceValK.setText(strArray[0]+" Kilometers");
				distanceValM.setText(strArray[1]+" Miles");
				distanceLabel.setVisible(true);
				distanceValM.setVisible(true);
				distanceValK.setVisible(true);
			}
		}
		//submit.setVisible(true);

	}
	
	public void rentListener() throws IOException {
		//System.out.println("Button CLicked");
		
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("HouseRent.fxml"));
		//BorderPane a = FXMLLoader.load(getClass().getResource("PropertyManagement.fxml")); 
		BorderPane a = loader.load();
		HouseRentController controller = loader.getController();
		//System.out.println(selectedHouse.toString());
		controller.initData(selectedHouse);

		borderPane.setCenter(a);
		Object x = borderPane.getTop();
		borderPane.setTop(null);


	}
	
	private static String distance(double lat1, double lon1, double lat2, double lon2) {
		String str="";
		if ((lat1 == lat2) && (lon1 == lon2)) {
			return "0";
		}
		else {
			double theta = lon1 - lon2;
			double dist = Math.sin(Math.toRadians(lat1)) * Math.sin(Math.toRadians(lat2)) + Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2)) * Math.cos(Math.toRadians(theta));
			dist = Math.acos(dist);
			dist = Math.toDegrees(dist);
			dist = dist * 60 * 1.1515;
			//			if (unit.equals("K")) {
			dist = dist * 1.609344;
			str+=df.format(dist);
			//			} else if (unit.equals("N")) {
			dist = dist * 0.8684;
			str+=","+df.format(dist);
			//}
			System.out.println("Distance: "+str);
			return str;
		}
	}

}
